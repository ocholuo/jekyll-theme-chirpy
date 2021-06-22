---
title: NetworkSec - Calico
date: 2018-05-18 11:11:11 -0400
categories: [15NetworkSec, NetworkBasic]
tags: [NetworkSec]
math: true
toc: true
---

[toc]

- ref
  - [Calico](https://feisky.gitbooks.io/sdn/content/container/calico/)
  - [https://xuxinkun.github.io/2016/07/22/cni-cnm/](https://xuxinkun.github.io/2016/07/22/cni-cnm/)
  - [https://www.projectcalico.org/](https://www.projectcalico.org/)
  - [http://blog.dataman-inc.com/shurenyun-docker-133/](http://blog.dataman-inc.com/shurenyun-docker-133/)
  - [calico](https://jimmysong.io/kubernetes-handbook/concepts/calico.html)
  - [Calico 介绍. 原理与使用](https://cloud.tencent.com/developer/article/1638845)
  - [详细配置！！！](http://dockerone.com/article/10709)
  - [1](https://qiankunli.github.io/2018/02/04/calico.html)


---

# Calico

![006tNc79gy1fz65bt7ieej30c90bsgn2](https://i.imgur.com/HoeTaXs.jpg)

---

## calico basic

[Calico](https://www.projectcalico.org/)
- 一个纯三层的数据中心网络方案（不需要Overlay）
  - 每个容器会分配一个可路由的IP。由于通信时不需要解包和封包，网络性能损耗小，易于排查，且易于水平扩展。

- 与OpenStack. Kubernetes. AWS. GCE等IaaS和容器平台都有良好的集成。


- Calico在每一个计算节点利用Linux Kernel实现了一个高效的vRouter来负责数据转发
  - 每个vRouter通过BGP协议负责把自己上运行的workload的路由信息像整个Calico网络内传播
  - 每个vRouter都通过BGP1协议把在本节点上运行的容器的路由信息向整个Calico网络广播，并自动设置到达其他节点的路由转发规则。

- Calico节点组网时可以直接利用数据中心的网络结构（L2或者L3）
  - 不需要额外的NAT、隧道或者Overlay Network
  - 没有额外的封包解包，能够节约CPU运算，提高网络效率。
  - ![abf5c66ed068fce3192ff6f112b30a57](https://i.imgur.com/cfshmWs.png)

- Calico保证所有容器之间的数据流量都是通过IP路由的方式完成互联互通的。
  - 小规模部署可以通过BGP client直接互联
  - 大规模下可通过指定的BGP route reflector来完成。
  - ![ee6da4769a5c9b5dfd3bcb21aec65e8a](https://i.imgur.com/8eDh85a.png)


- Calico基于iptables还提供了丰富而灵活的网络Policy
  - 保证通过各个节点上的ACLs来提供Workload的多租户隔离. 安全组以及其他可达性限制等功能。
  - 提供了丰富的网络策略，实现了Kubernetes的Network Policy策略，提供容器间网络可达性限制的功能。



---


## Calico 优势 与 劣势


优势
- 没有封包和解包过程，完全基于两端宿主机的路由表进行转发
- 可以配合使用 Network Policy 做 pod 和 pod 之前的访问控制
- 细粒度网络访问控制
  - flannel是不支持的，calico支持，
  - 做多租户网络方面的控制ACL，选择 calico。

劣势
- Calico控制平面的设计要求物理网络得是L2 Fabric，这样vRouter间都是直接可达的
- 要求宿主机处于同一个2层网络下
  - 也就是连在一台交换机上
- 路由的数目与容器数目相同，非常容易超过路由器、三层交换、甚至node的处理能力，从而限制了整个网络的扩张。(可以使用大规模方式解决)
- 三层实现，不支持VRF
- 每个node上会设置大量（海量)的iptables规则、路由，运维、排障难度大。
- 原理决定了它不可能支持VPC，容器只能从calico设置的网段中获取ip。
- 不支持多租户网络的隔离功能，在多租户场景下会有网络安全问题



flannel vs calico
- 网络性能
  - 选择 flannel host-gw 模式 和 calico BGP 模式
- 集群规模
  - 如果规模不大，100以下节点可以使用flannel，优点是维护比较简单。
- 维护能力
  - calico的路由表很多，而且走BGP协议，一旦出现问题排查起来也比较困难，上百台的，路由表去排查也是很麻烦，这个具体需求需要根据自己的情况而定。





---

## Calico架构

![calico](https://i.imgur.com/lHfVmwk.png)

![Screen Shot 2021-03-29 at 22.38.39](https://i.imgur.com/M5Xtfdt.png)


![x2crhv7hyj](https://i.imgur.com/EJowDvz.png)

Calico主要由Felix. etcd. BGP client, BGP Route Reflector组成

1. <font color=red> Felix </font>
   - Calico Agent
   - calico的核心组件，运行在每个节点上。跑在每台需要运行Workload的节点上
   - 主要负责配置路由及ACLs等信息来确保Endpoint的连通状态；
   - 主要的功能有接口管理. 路由规则. ACL规则和状态报告
     - **接口管理**：Felix为内核编写一些接口信息，以便让内核能正确的处理主机endpoint的流量。特别是主机之间的ARP请求和处理ip转发。
     - **路由规则**：Felix负责主机之间路由信息写到linux内核的FIB（Forwarding Information Base）转发信息库，保证数据包可以在主机之间相互转发。
     - **ACL规则**：Felix负责将ACL策略写入到linux内核中，保证主机endpoint的为有效流量不能绕过calico的安全措施。
     - **状态报告**：Felix负责提供关于网络健康状况的数据。特别是，它报告配置主机时出现的错误和问题。这些数据被写入etcd，使其对网络的其他组件和操作人员可见。

2. <font color=red> etcd </font>
   - 分布式键值存储
   - 负责存储网络信息
   - 主要负责网络元数据一致性，确保Calico网络状态的准确性；
   - 为保证数据的可靠和容错建议至少三个以上etcd节点。

3. Bird：BGP客户端，Calico在每个节点上的都会部署一个BGP客户端，它的作用是将Felix的路由信息读入内核，并通过BGP协议在集群中分发。当Felix将路由插入到Linux内核FIB中时，BGP客户端将获取这些路由并将它们分发到部署中的其他节点。这可以确保在部署时有效地路由流量。

4. <font color=red> BGP Client（BIRD） </font>
   - 主要负责把Felix写入Kernel的路由信息分发到当前Calico网络
   - 确保Workload间的通信的有效性；

5. <font color=red> BGP Route Reflector（BIRD）</font>
   - 大规模部署时使用
   - 摒弃所有节点互联的 mesh 模式
     - 大型网络仅仅使用 BGP client 形成 mesh 全网互联的方案就会导致规模限制
     - 所有节点需要 N^2 个连接
   - 作为BGP client的中心连接点，可以避免每个节点互联
     - 通过一个或者多个BGP Route Reflector来完成集中式的路由分发。
     - 使所有 BGP Client 仅与特定 RR 节点互联并做路由同步
     - 大大减少连接数。

6. <font color=red> calico/calico-ipam </font>
   - 主要用作Kubernetes的CNI插件

7. Calicoctl：calico 命令行管理工具。

8. Orchestrator plugin：协调器插件负责允许kubernetes或OpenStack等原生云平台方便管理Calico，可以通过各自的API来配置Calico网络实现无缝集成。如kubernetes的cni网络插件。


![calico2](https://i.imgur.com/2x3xypD.png)



---

## Calico 网络模式

- <font color=red> BGP 边界网关协议（Border Gateway Protocol, BGP） </font>
  - 核心的去中心化自治路由协议。
  - BGP不使用传统的内部网关协议（IGP）的指标。
  - BGP两种模式
    - 全互联模式(node-to-node mesh)
    - 路由反射模式Router Reflection（RR）

- <font color=red> IPIP模式 </font>
  - 把 IP 层封装到 IP 层的一个 tunnel。
  - 作用其实基本上就相当于一个基于IP层的网桥！
  - 一般来说，普通的网桥是基于mac层的，不需 IP，而这个 ipip 则是通过两端的路由做一个 tunnel，把两个本来不通的网络通过点对点连接起来。


---

### BGP 概述

BGP（border gateway protocol）
- 外部路由协议（边界网关路由协议）
  - 用来在AS之间传递路由信息
  - 是一种增强的距离矢量路由协议（应用场景）
  - 是一个边界路由器，在每个自治系统的最边界与其他自治系统的传输规则
  - 而这些节点之间组成的BGP网络是一个全网通的网络，这个网络就称为一个BGP Peer

- 基本功能是在 自治系统 间自动交换 无环路的路由信息
  - 无环路保证主要通过其AS-PATH实现
  - 通过交换带有自治系统号序列属性的路径可达信息，来构造自治系统的拓扑图
  - 从而消除路由环路并实施用户配置的路由策略。

- BGP是基于策略的路由协议
  - 其策略通过丰富的路径属性(attributes)进行控制。
  - BGP工作在应用层，在传输层采用可靠的TCP作为传输协议（BGP传输路由的邻居关系建立在可靠的TCP会话的基础之上）。
  - 在路径传输方式上，BGP类似于距离矢量路由协议。
  - 而BGP路由的好坏不是基于距离（多数路由协议选路都是基于带宽的），它的选路基于丰富的路径属性，而这些属性在路由传输时携带，所以我们可以把BGP称为路径矢量路由协议。
  - 如果把自治系统浓缩成一个路由器来看待，BGP作为路径矢量路由协议这一特征便不难理解了。

- 除此以外，BGP又具备很多链路状态（LS）路由协议的特征，比如触发式的增量更新机制，宣告路由时携带掩码等。）


#### Calico BGP 概述


Calico 项目提供的 BGP 网络解决方案，与 Flannel 的 host-gw 模式几乎一样。
- 也就是说，Calico也是基于路由表实现容器数据包转发，
- 但不同于Flannel使用flanneld进程来维护路由信息的做法，而Calico项目使用BGP协议来自动维护整个集群的路由信息。

BGP 是怎么工作的？
- 这个也是跨节点之间的通信，与flannel类似
- 其实这张图相比于flannel，通过一个路由器来路由，flannel.1 就相比于vxlan模式去掉，所以会发现这里是没有网桥存在，完全就是通过路由来实现
- 这个数据包也是先从veth设备对另一口发出，到达宿主机上的cali开头的虚拟网卡上，到达这一头也就到达了宿主机上的网络协议栈，另外就是当创建一个pod时帮你先起一个infra containers的容器，调用calico的二进制帮你去配置容器的网络，然后会根据路由表决定这个数据包到底发送到哪里去，可以从ip route看到路由表信息，这里显示是目的cni分配的子网络和目的宿主机的网络
- 当进行跨主机通信的时候之间转发到下一跳地址走宿主机的eth0网卡出去，也就是一个直接的静态路由，这个下一跳就跟host-gw的形式一样
  - 和host-gw最大的区别是
    - calico使用BGP路由交换
    - 而host-gw是使用自己的路由交换
- BGP这个方案比较成熟，在大型网络中用的也比较多，所以要比flannel的方式好，而这些路由信息都是由BGP client传输。



#### BGP两种模式

- 全互联模式(node-to-node mesh)
  - 全互联模式 每一个BGP Speaker都需要和其他BGP Speaker建立BGP连接，这样BGP连接总数就是N^2，如果数量过大会消耗大量连接。
  - 如果集群数量超过100台官方不建议使用此种模式。

- 路由反射模式Router Reflection（RR）
  - Calico 维护的网络在默认是（Node-to-Node Mesh）全互联模式，Calico集群中的节点之间都会相互建立连接，用于路由交换。
  - 但是随着集群规模的扩大，mesh模式将形成一个巨大服务网格，连接数成倍增加。
  - 这时就需要使用 Route Reflector（路由器反射）模式解决这个问题。
  - RR模式 中会指定一个或多个BGP Speaker为RouterReflection，它与网络中其他Speaker建立连接，每个Speaker只要与Router Reflection建立BGP就可以获得全网的路由信息。在calico中可以通过Global Peer实现RR模式。



![j41ngbry2b](https://i.imgur.com/R8DEq7n.png)

Pod 1 访问 Pod 2 流程如下
1. 数据包从 Pod1 出到达Veth Pair另一端（宿主机上，以cali前缀开头）
2. 宿主机根据路由规则，将数据包转发给下一跳（网关）
3. 到达 Node2，根据路由规则将数据包转发给 cali 设备，从而到达 Pod2。
- 最核心的 下一跳 路由规则，就是由 Calico 的 Felix 进程负责维护的。
  - 这些路由规则信息，通过 BGP Client 中 BIRD 组件，使用 BGP 协议来传输。
  - Calico 项目实际上将集群里的所有节点，都当作是边界路由器来处理，它们一起组成了一个全连通的网络，互相之间通过 BGP 协议交换路由规则。这些节点，我们称为 BGP Peer。
- Flannel host-gw 和 Calico 的唯一不一样的地方就是当数据包下一跳到达node2节点容器时发生变化，并且出数据包也发生变化，知道它是从veth的设备流出，容器里面的数据包到达宿主机上，这个数据包到达node2之后，它又根据一个特殊的路由规则，这个会记录目的通信地址的cni网络，然后通过cali设备进去容器，这个就跟网线一样，数据包通过这个网线发到容器中，这也是一个二层的网络互通才能实现。



#### Route Reflector 模式（RR）（路由反射）


Calico 维护的网络默认是 （Node-to-Node Mesh）全互联模式，
- 在BGP中可以通过calicoctl node status看到启动是 node-to-node mesh 网格的形式，
- 这种形式是一个全互联的模式
  - 默认的BGP在k8s的每个节点担任了一个BGP的一个喇叭，一直吆喝着扩散到其他节点
  - calico集群中的节点之间都会相互建立连接，用于路由交换。
- 但是随着集群规模的扩大，mesh模式将形成一个巨大服务网格，连接数成倍增加。
  - 上百台节点就要构建上百台链接，就是全互联的方式，
  - 要来回建立连接来保证网络的互通性，
  - 增加一个节点就要成倍的增加这种链接保证网络的互通性，
  - 大量的网络消耗

- 这时就需要使用 Route Reflector（路由器反射）模式解决这个问题。
  - 确定一个或多个Calico节点充当路由反射器，让其他节点从这个RR节点获取路由信息。



#### Route Reflector 模式（RR）（路由反射）


- 使用Route reflector，也就是找几个大的节点，让他们去这个大的节点建立连接，也叫RR
- 所以要找节点或着多个节点充当路由反射器，建议至少是2到3个，一个做备用，一个在维护的时候不影响其他的使用。


---

### IP-in-IP


不过，考虑到并非所有的网络都能支持BGP，以及Calico控制平面的设计要求物理网络必须是二层网络, 以确保 vRouter间均直接可达，路由不能够将物理设备当作下一跳等原因，为了支持三层网络，Calico还推出了IP-in-IP叠加的模型，它也使用Overlay的方式来传输数据。
- IPIP的包头非常小，而且也是内置在内核中，因此理论上它的速度要比VxLAN快一点 ，但安全性更差。Calico 3.x的默认配置使用的是IPIP类型的传输方案而非BGP。



- Calico控制平面的设计要求物理网络得是L2 Fabric，这样vRouter间都是直接可达的，路由不需要把物理设备当做下一跳。
- 为了支持L3 Fabric，Calico推出了IPinIP的选项。
- IPIP 是linux内核的驱动程序，可以对数据包进行隧道


![sx37u2c3y2](https://i.imgur.com/aGhHpsk.png)


- 上图可以看到两个不同的网络 vlan1 和 vlan2。基于现有的以太网将原始包中的原始IP进行一次封装，通过tunl0解包，这个tunl0类似于ipip模块，和Flannel vxlan的veth很类似。

- Pod1 访问 Pod2 流程如下：
  1. 数据包从 Pod1 出到达Veth Pair另一端（宿主机上，以cali前缀开头）。
  2. 进入IP隧道设备（tunl0），由Linux内核IPIP驱动封装，把源容器ip换成源宿主机ip，目的容器ip换成目的主机ip，这样就封装成 Node1 到 Node2 的数据包。

        ```
        此时包的类型：
        原始IP包：
        源IP：10.244.1.10
        目的IP：10.244.2.10

        TCP：
        源IP: 192.168.31.62
        目的iP：192.168.32.63
        ```

  3. 数据包经过路由器三层转发到 Node2。

  4. Node2 收到数据包后，网络协议栈会使用IPIP驱动进行解包，从中拿到原始IP包。

  5. 然后根据路由规则，将数据包转发给cali设备，从而到达 Pod2。


实际查看路由情况

跨宿主机之间容器访问：

环境：

Pod 名称 | Pod IP | 宿主机 IP
---|---|---
zipkin-dependencies-production | 10.20.169.155 | 192.168.162.248
zipkin-production | 10.20.36.85 | 192.168.163.40

zipkin-dependencies-production -> zipkin-production 过程如下：

1. 登录 zipkin-dependencies-production 容器
   - 查看路由信息
   - 0.0.0.0 指向了 169.254.1.1（eth0）
   - 容器ip地址: `10.20.169.155/32`
   - eth0@if1454 为在宿主机上创建的虚拟网桥的一端，另一端为 calic775b4c8175

![vs9udu13gv](https://i.imgur.com/ABSFeOg.png)

![pwn3q8srp0](https://i.imgur.com/pO8kObx.png)


2. 去往容器 zipkin-production 的数据包，由zipkin-dependencies-production 容器里经过网桥设备转发到 zipkin-dependencies-production 所在的宿主机上
   - 在宿主机上查看路由表。
   - 可以看到 zipkin-production (pod-ip 10.20.36.85) 的下一跳是 `192.168.163.40`，也就是 zipkin-production 所在的宿主机ip地址，网络接口是 tunl0
   - tunl0 设备是一个ip隧道（ip tunnel）设备，技术原理是IPIP，ip包进入ip隧道后会被linux内核的IPIP驱动接管并重新封装（伪造）成去宿主机网络的ip包，然后发送出去。这样原始ip包经过封装后下一跳地址就是 192.168.163.40

![gln9tu8bsa](https://i.imgur.com/efoI6F1.png)


3. 数据包到达 zipkin-production 的宿主机 `192.168.163.40`
   - 经过解包查找 10.20.36.85 的路由为网桥设备 cali0393db3e4a6

![brgcf5oibe](https://i.imgur.com/mLHj2Gt.png)


4. 最终 10.20.36.85 经过网桥设备 cali0393db3e4a6 被转发到容器 zipkin-production 内部，返回的数据包路径也是一样。


![iyi1el56py](https://i.imgur.com/GbhuLO4.png)







---


## Calico CNI


见[https://github.com/projectcalico/cni-plugin](https://github.com/projectcalico/cni-plugin)。


---


## Calico CNM


Calico通过Pool和Profile的方式实现了docker CNM网络：

1. Pool
   - 定义可用于Docker Network的IP资源范围
   - 比如：`10.0.0.0/8` 或者 `192.168.0.0/16`；
2. Profile
   - 定义Docker Network Policy的集合，由tags和rules组成；
   - 每个 Profile默认拥有一个和Profile名字相同的Tag，每个Profile可以有多个Tag，以List形式保存。

- 具体实现见[https://github.com/projectcalico/libnetwork-plugin](https://github.com/projectcalico/libnetwork-plugin)，
- 使用方法可以参考[http://docs.projectcalico.org/v3.0/getting-started/docker/](http://docs.projectcalico.org/v3.0/getting-started/docker/)。


---




## 部署


运行下面的命令可以部署 calico 网络。


```bash
mkdir /etc/cni/net.d/

kubectl apply -f https://docs.projectcalico.org/v3.0/getting-started/kubernetes/installation/rbac.yaml

wget https://docs.projectcalico.org/v3.0/getting-started/kubernetes/installation/hosted/calico.yaml

# 修改etcd_endpoints的值和默认的192.168.0.0/16(不能和已有网段冲突)

kubectl apply -f calico.yaml
```

2. calicoctl 工具安装

```bash
# 下载工具：https://github.com/projectcalico/calicoctl/releases
wget -O /usr/local/bin/calicoctl https://github.com/projectcalico/calicoctl/releases/download/v3.13.3/calicoctl
chmod +x /usr/local/bin/calicoctl

export CALICO_DATASTORE_TYPE=kubernetes

export CALICO_KUBECONFIG=~/.kube/config

calicoctl get ippool

calicoctl get node

# 查看集群节点状态
# 使用calicoctl查看服务状态：
$ ./calicoctl node status
Calico process is running.
IPv4 BGP status
+--------------+-------------------+-------+----------+-------------+
| PEER ADDRESS |     PEER TYPE     | STATE |  SINCE   |    INFO     |
+--------------+-------------------+-------+----------+-------------+
| 192.168.2.62 | node-to-node mesh | up    | 02:58:05 | Established |
| 192.168.2.63 | node-to-node mesh | up    | 03:08:46 | Established |
+--------------+-------------------+-------+----------+-------------+
IPv6 BGP status
No IPv6 peers found.

# 实际上，使用calicoctl查看Node状态就是调用系统查看的，与netstat效果一样。
$ netstat -antp|grep bird  
tcp        0      0 0.0.0.0:179             0.0.0.0:*               LISTEN      62709/bird          
tcp        0      0 192.168.2.61:179        192.168.2.63:58963      ESTABLISHED 62709/bird          
tcp        0      0 192.168.2.61:179        192.168.2.62:37390      ESTABLISHED 62709/bird
```


如果使用 calicoctl get node，需要指定 calicoctl 配置，默认使用 /etc/calico/calicoctl.cfg

```bash
# 设置 calicoctl 配置文件
$ vim /etc/calico/calicoctl.cfg

apiVersion: projectcalico.org/v3
kind: CalicoAPIConfig
metadata:
spec:
  datastoreType: "etcdv3"
  etcdEndpoints: https://10.10.0.174:2379
  etcdKeyFile: /opt/kubernetes/ssl/server-key.pem
  etcdCertFile: /opt/kubernetes/ssl/server.pem
  etcdCACertFile: /opt/kubernetes/ssl/ca.pem

# 查看 calico 节点
$ calicoctl get nodes

# 查看 IPAM的IP地址池
$ calicoctl get ippool -o wide

# 查看bgp网络配置情况
$ calicoctl get bgpconfig

# 查看ASN号，一个编号就是一个自治系统
$ calicoctl get nodes --output=wide

# 查看 bgp peer
$ calicoctl get bgppeer
```


---

### Calico Kubernetes

在Kubernetes中部署Calico的主要步骤如下：
1. 修改Kubernetes服务的 启动参数，并重启服务。
   1. 设置Master上 `kube-apiserver` 服务的启动参数：
      - `--allowprivileged=true`
      - 因为calico-node需要以特权模式运行在各Node上
   1. 设置各Node上 `kubelet` 服务的启动参数：
      - `--networkplugin=cni`
      - 使用CNI网络插件

2. 创建Calico服务，主要包括calico-node和calico policy controller。需要创建的资源对象如下：
   1. 创建 `ConfigMap calico-config`，包含Calico所需的配置参数
   2. 创建 `Secret calico-etcd-secrets`，用于使用TLS方式连接etcd。
   3. 在每个Node上都运行 `calico/node容器`，部署为DaemonSet
   4. 在每个Node上都安装 `Calico`（由install-cni容器完成）
   5. 部署一个名为 calico/kube-policy-controller 的Deployment，以对接Kubernetes集群中为Pod设置的Network Policy




对于使用 kubeadm 创建的 Kubernetes 集群，使用以下配置安装 calico 时需要配置

- `--pod-network-cidr=192.168.0.0/16`
- `--service-cidr=10.96.0.0/12` （不能与 Calico 网络重叠）

各版本的安装方法:

```bash
# - 对于 Kubernetes 1.7.x 或者更新的版本
kubectl apply -f https://docs.projectcalico.org/v3.0/getting-started/kubernetes/installation/hosted/kubeadm/1.7/calico.yaml

# - 对于 Kubernetes 1.6.x:
kubectl apply -f http://docs.projectcalico.org/v2.3/getting-started/kubernetes/installation/hosted/kubeadm/1.6/calico.yaml

# - 对于 Kubernetes 1.5.x:
kubectl apply -f http://docs.projectcalico.org/v2.3/getting-started/kubernetes/installation/hosted/kubeadm/1.5/calico.yaml
```

- 详细的自定义配置方法见[https://docs.projectcalico.org/v3.0/getting-started/kubernetes](https://docs.projectcalico.org/v3.0/getting-started/kubernetes)。

- 在Pod中启动Calico-etcd，
- 在所有Node上启动bird6. felix以及confd，
- 并配置CNI网络为calico插件：

![calico-components](https://i.imgur.com/hcUKcgH.png)

```bash
# Calico相关进程
$ ps -ef | grep calico | grep -v grep
root      9012  8995  0 14:51 ?        00:00:00 /bin/sh -c /usr/local/bin/etcd --name=calico --data-dir=/var/etcd/calico-data --advertise-client-urls=http://$CALICO_ETCD_IP:6666 --listen-client-urls=http://0.0.0.0:6666 --listen-peer-urls=http://0.0.0.0:6667
root      9038  9012  0 14:51 ?        00:00:01 /usr/local/bin/etcd --name=calico --data-dir=/var/etcd/calico-data --advertise-client-urls=http://10.146.0.2:6666 --listen-client-urls=http://0.0.0.0:6666 --listen-peer-urls=http://0.0.0.0:6667
root      9326  9325  0 14:51 ?        00:00:00 bird6 -R -s /var/run/calico/bird6.ctl -d -c /etc/calico/confd/config/bird6.cfg
root      9327  9322  0 14:51 ?        00:00:00 confd -confdir=/etc/calico/confd -interval=5 -watch --log-level=debug -node=http://10.96.232.136:6666 -client-key= -client-cert= -client-ca-keys=
root      9328  9324  0 14:51 ?        00:00:00 bird -R -s /var/run/calico/bird.ctl -d -c /etc/calico/confd/config/bird.cfg
root      9329  9323  1 14:51 ?        00:00:04 calico-felix


# CNI网络插件配置
$ cat /etc/cni/net.d/10-calico.conf
{
    "name": "k8s-pod-network",
    "cniVersion": "0.1.0",
    "type": "calico",
    "etcd_endpoints": "http://10.96.232.136:6666",
    "log_level": "info",
    "ipam": {
        "type": "calico-ipam"
    },
    "policy": {
        "type": "k8s",
         "k8s_api_root": "https://10.96.0.1:443",
         "k8s_auth_token": "<token>"
    },
    "kubernetes": {
        "kubeconfig": "/etc/cni/net.d/calico-kubeconfig"
    }
}


$ cat /etc/cni/net.d/calico-kubeconfig
# Kubeconfig file for Calico CNI plugin.
apiVersion: v1
kind: Config
clusters:
- name: local
  cluster:
    insecure-skip-tls-verify: true
users:
- name: calico
contexts:
- name: calico-context
  context:
    cluster: local
    user: calico
current-context: calico-context
```

![calico-flow](https://i.imgur.com/ldPbGZJ.png)



---





.
