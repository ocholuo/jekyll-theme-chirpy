---
title: Kubernetes
date: 2019-11-26 11:11:11 -0400
categories: [Linux, VMs]
tags: [Linux, VMs, Kubernetes]
math: true
image:
---

[toc]

---


# Kubernetes

![k8s_architecture](https://i.imgur.com/ibs26v8.png)

- Google 团队发起并维护的基于 Docker 的开源容器集群管理系统，
- 目标是管理跨多个主机的容器，提供基本的部署，维护以及运用伸缩，主要实现语言为 Go 语言 
- 它不仅支持常见的云平台，而且支持内部数据中心。
  - 易学：轻量级，简单，容易理解
  - 便携：支持公有云，私有云，混合云，以及多种云平台
  - 可拓展：模块化，可插拔，支持钩子，可任意组合
  - 自修复：自动重调度，自动重启，自动复制

- 建于 Docker 之上的 Kubernetes 可以构建一个容器的调度服务，其目的是让用户透过 Kubernetes 集群来进行云端容器集群的管理，而无需用户进行复杂的设置工作。
- 系统会自动选取合适的工作节点来执行具体的容器集群调度处理工作。
- 其核心概念是 Container Pod。
  - 一个 Pod 由一组工作于同一物理工作节点的容器构成。
  - 这些组容器拥有相同的网络命名空间、IP以及存储配额，也可以根据实际情况对每一个 Pod 进行端口映射。
  - 此外，Kubernetes 工作节点会由主系统进行管理，节点包含了能够运行 Docker 容器所用到的服务。






















.