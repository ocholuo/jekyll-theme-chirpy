---
title: GCP - Kubernetes and Kubernetes Engine
date: 2021-01-01 11:11:11 -0400
categories: [21GCP]
tags: [GCP]
toc: true
image:
---

[toc]

---


# Kubernetes and Kubernetes Engine


---


## Introduction to Containers

![Screen Shot 2021-02-07 at 00.06.44](https://i.imgur.com/DIreiTC.png)

Compute Engine
- GCPs Infrastructure as a Service
- run Virtual Machine in the cloud and gives you persistent storage and networking for them

App Engine
- one of GCP's platform as a service offerings.

Kubernetes Engine.
- like Infrastructure as a Service,
  - it saves you infrastructure chores.
- like a platform as a service offering,
  - it was built with the needs of developers in mind.


---

### infrastructure as a service

<img src="https://i.imgur.com/FLT3IBp.png" width="300">


- share compute resources with others by virtualizing the hardware.
- Each Virtual Machine has its own instance of an operating system
- and you build and run applications on it with access to <font color=blue> memory, file systems, networking interfaces, and the other attributes </font> that physical computers also have.

- <font color=red> But flexibility comes with a cost </font>
  - the smallest unit of compute is a <font color=red> Virtual Machine together with its application </font>
  - The guest operating system maybe large, even gigabytes
  - It can take minutes to boot up.
  - Virtual Machine
    - highly configurable
    - can install and run the tools of choice.
    - can configure the underlying system resources
      - such as disks and networking,
    - can install the own web server database or a middle ware.

  - But the application demand increases,
    - have to scale out in units of an entire Virtual Machine with a guest operating system for each.
    - the resource consumption grows faster


---

### Platform as a Service


<img src="https://i.imgur.com/Gldwl2x.png" width="400">

- like App Engine
  - Instead of getting a blank Virtual Machine, get access to a family of services that applications need.
  - write the code and self-contained workloads that use these services and include any dependent libraries.
- As demand for the application increases
  - the platform scales the applications seamlessly and independently by workload and infrastructure.
  - scales rapidly
  - but you give up control of the underlying server architecture.

---

### Containers

<img src="https://i.imgur.com/pb2kD6d.png" width="400">

- give the independent scalability of workloads like PaaS, and an abstraction layer of the operating system and hardware, like Infrastructure as a Service.
- starts as quickly as a new process.
  - Compare that to how long it takes to boot up an entirely new instance of an operating system.
  - All you need on each host is an operating system that supports Containers and a Container run-time.
  - In essence, it visualize the os rather than the hardware.
- The environment scales like PaaS
- but gives you nearly the same flexibility as Infrastructure as a Service.
- The container abstraction makes the code very portable.
  - treat the os and hardware as a black box.
  - can move the code from development, to staging, to production, or from the laptop to the Cloud without changing or rebuilding anything.

- case:
  - to scale a web server
    - can do so in seconds
    - and deploy dozens or hundreds of them depending on the size of the workload on a single host.
  - to build the applications using lots of Containers,
    - each performing their own function, using the micro-services pattern.
    - The units of code running in these Containers can communicate with each other over a network fabric.
- If you build this way, you can make applications modular.
  - They deploy it easily and scale independently across a group of hosts.
- The host can scale up and down, and start and stop Containers as demand for the application changes, or even as hosts fail and are replaced.
- A tool that helps you do this well is Kubernetes.
  - Kubernetes makes it easy to orchestrate many Containers on many hosts.
  - Scale them,
  - roll out new versions of them, and even roll back to the old version if things go wrong.


#### build and run containers

> Google Cloud offers Cloud Build
> a managed service for building Containers.

Docker
- to bundle an application and its dependencies into a Container.

```py
# a Python web application
# uses the very popular Flask framework.

app.py
from flask import Flask
app = Flask(__name__)
# - Whenever a web browser go to its top-most document, it replies "hello world".
@app.route("/")
def hello():
    return "Hello World!\n"
# - Or if the browser instead appends/version to the request, the application replies with its version.
@app.route("/version")
def hello():
    return "Hello World!\n"
if __name__ == "__main__":
    app.run(host='0.0.0.0')
```

- deploy this application

```bash
requirements.txt
# It needs a specific version of Python and a specific version of Flask, which we control using Python's requirements.txt file, together with its other dependencies too.
Flask==0.12
uwsgi==2.0.15
```


- use a Docker file to specify how the code gets packaged into a Container.

```Docker
From ubuntu:18.10
RUN apt-get update -y
COPY requirement.txt /app/requirement.txt
WORKDIR /app
RUN pip3 install - r requirement.txt
COPY . /app
ENTRYPOINT ["python3", "app.py"]
```

- use the Docker build command to build the Container.

```bash
# - builds the Container and stores it on the local system as a runnable image.
docker build -t py-server .

# - to run the image.
docker run -d py-server
```



---


## Kubernetes

![Screen Shot 2021-02-07 at 14.29.23](https://i.imgur.com/zoPNLpx.png)

> kubectl

- an open-source orchestrator for containers to better manage and scale the applications.
  - describe a set of applications and how they should interact with each other, and Kubernetes figures out how to make that happen.
- Kubernetes offers an API that let authorized people control its operation through several utilities.
- Kubernetes deploy containers on a set of nodes called cluster


### cluster
- a set of master components that control the system as a whole and a set of nodes that run containers.
- A group of machines where Kubernetes can schedule workloads

### node
- a group of containers
- a node represents a computing instance.
- nodes are virtual machines running in Compute Engine.

### pod

![Screen Shot 2021-02-07 at 14.51.15](https://i.imgur.com/86QrtZe.png)

- <font color=red> pod </font>
  - Kubernetes deploys <font color=blue> a container or a set of related containers </font> inside pod.
  - the <font color=blue> smallest deployable unit </font> in Kubernetes.
    - pod is like running process on the cluster.
  - the pod could be <font color=blue> one component of the application or an entire application </font>
  - container : pod
    - common:
      - only one container per pod.
    - have multiple containers with a hard dependency
      - package them into a single pod.
      - They'll automatically share networking
      - and they can have disk storage volumes in common.
  - Each pod in Kubernetes gets <font color=red> a unique IP address and set of ports </font> for containers.
    - containers inside a pod can communicate with each other
    - using the <font color=blue> localhost network interface </font>, they don't know or care which nodes they're deployed on.

---

## GKE (Kubernetes Engine)

![Screen Shot 2021-02-07 at 14.45.26](https://i.imgur.com/o0IN3Ou.png)


to build Kubernetes cluster

1. build on the own hardware, or environment that provides virtual machines
   - if you've built it theself, you have to maintain it.
   - That's even more toil.

2. <font color=red> Kubernetes Engine </font>
   - Kubernetes as a managed service in the cloud.
   - create a Kubernetes cluster with Kubernetes Engine
     - by `GCP console` 
     - or the `g-cloud command` by the Cloud SDK.
   - GKE clusters can be customized
     - support different machine types, numbers of nodes and network settings.
   - the resources used to build Kubernetes Engine clusters come from Compute Engine
     - Kubernetes Engine gets to take advantage of Compute Engine’s and Google VPC’s capabilities.
     - <font color=blue> Kubernetes Engine workloads run in clusters built from Compute Engine virtual machines </font>
   - The Kubernetes Engine team periodically performs automatic upgrades of your cluster master to newer stable versions of Kubernetes, and you can enable automatic node upgrades too.


---

### build Kubernetes cluster by Kubernetes Engine

```bash
# ------------------- google cloud shell
gcloud compute instances list

# set up the zone
export MY_ZONE=us-cnetral1-f


# ------------------- building a Kubernetes cluster using GKE.
gcloud container clusters create webfrontend \
    --zone $MY_ZONE \
    --num-nodes 2
# cloud console
# > Compute Engine: VM instances
# > Kubernetes Engine: Kubernetes clusters

# check the version
kubectl version


# ------------------- starts a deployment with a container running a pod.
# - the container is an image of nginx open source web server.
# - fetch an image of nginx container registry.
kubectl run nginx \
    --image=nginx:1.15.7
# deployment "nginx" created

# To see the running nginx pods,
kubectl get pods


# ------------------- create service
kubectl expose deployment nginx \
    --port 80 \
    --type LoadBalancer
# service "nginx" exposed

# shows you the service's public IP address.
kubectl get services

# use this address to hit the nginx container remotely.



# ------------------- To scale a deployment
kubectl scale deployment nginx --replicas  
kubectl get pods  # got 3 now


# To auto scale a deployment based on CPU usage.
# Kubernetes will scale up the number of pods when CPU usage hits 80% of capacity
kubectl autoscale nginx --min=10 --max=15 --cpu=80

```


- <font color=red> deployment </font>
  - A deployment: represents <font color=blue> a group of replicas of the same pod. </font>
  - keeps the pods running
    - even if a node on which some of them run on fails.
  - use a deployment to contain a component of application or entire application.


![Screen Shot 2021-02-07 at 15.30.03](https://i.imgur.com/vZNKFIV.png)

> cluster > master + node > pod > containers



- <font color=red> pod access </font>
  - default
    - pods in a deployment is <font color=blue> only accessible inside the cluster </font>

  - To <font color=blue> make the pods in the deployment publicly available </font>
    - to let people on the Internet to access the content in nginx web server
    - <font color=red> connect a load balancer </font> to it
      ```bash
      kubectl expose deployments nginx --port=80 --type=LoadBalancer
      ```
      1. Kubernetes <font color=blue> creates a service with a fixed public IP address </font> for the pods.
         - A <font color=red> service </font>
           - the fundamental way Kubernetes represents load balancing.
           - A service groups a set of pods together and provides a stable endpoint for them.
           - Suppose the application consisted of a front end and a back end.
             - <font color=blue> the front end can access the back end using those pods' internal IP addresses </font>
             - without the need for a service
             - but it would be a management problem.
               - As deployments create and destroy pods, pods get their own IP addresses,
               - but those addresses don't remain stable over time.
             - <font color=blue> Services provide that stable endpoint you need </font>
      2. Kubernetes <font color=blue> attach an external load balancer with a public IP address to the service </font>
         - so that others outside the cluster can access it.
      3. Any client hits that IP address
         - will be routed to a pod behind the service.

      - In GKE, this kind of load balancer is <font color=red> network load balancer </font>
        - one of the managed load balancing services that Compute Engine makes available to virtual machines.

- <font color=red> replica </font>
  - This technique allows you to share the load and scale the service in Kubernetes.


### configuration file

- <font color=red> configuration file </font>
  - use configuration file tells Kubernetes the desired state
  - These configuration files then become the management tools.
  - To make a change, edit the file and then present the changed version to Kubernetes.


```yaml
# configuration file

# get a starting point for one of these files based on the work we've already done.
kubectl get pods -l "app=nginx" -o yaml
# output
# nginx.deployment.yaml
apiVerison: v1
kind: Deployment
metadata:
    name: nginx
    labels:
        app: nginx
spec:
    replicas: 3  # 3 replicas of the nginx pod
    selector:    # how to group specific pods as replicas
        matchLabels:   # all of those specific pods share a label
            app: nginx # app is tagged as nginx
    tamplate:
        metadata:
            labels:
                app: nginx
        spec:
            containers:
            - name: nginx
              image: nginx: 1.15.7
              ports:
              - containerPort: 80

# --------------- 1.
# To scale a deployment
kubectl scale nginx --replicas=3
# To auto scale a deployment based on CPU usage.
# Kubernetes will scale up the number of pods when CPU usage hits 80% of capacity
kubectl autoscale nginx --min=10 --max=15 --cpu=80

# --------------- 2. edit the deployment config file
# then updated config file.
kubectl apply -f nginx.deployment.yaml

# view the replicas and see their updated state.
kubectl get replicasets

# watch the pods come online.
kubectl get pods

# check the deployments to make sure the proper number of replicas are running
kubectl get deployments

# shows public IP of the service
# Clients can use this address to hit the nginx container remotely.
kubectl get services

```  

---

### update version of the application/container

- <font color=red> update version of the application/container </font>  
  - <font color=red> roll out all changes at once </font>
    - could be risky
    - users experience downtime while the application rebuilds and redeploys.
  - <font color=red> rolling update </font>
    - one attribute of a deployment is its update strategy.
    - example
       - choose a rolling update for a deployment
       - when give it a new version of the software that it manages,
       - Kubernetes will <font color=blue> create pods of the new version one-by-one </font>
       - <font color=red> waiting for each new version pod to become available before destroying one of the old version pods </font>
    - a quick way to push out a new version of the application
    - while sparing the users from experiencing downtime.


---


## modern Hybrid on Multi-Cloud Computing (Anthos)

---

### on-premises distributed systems architecture.

![Screen Shot 2021-02-07 at 23.20.20](https://i.imgur.com/dX9TDe3.png)

> how business is traditionally made the enterprise computing needs before cloud computing.

- most enterprise scale applications are designed as distributed systems.
  - Spreading the computing workload required to provide services over two or more network servers.
  - containers can break these workloads down into microservices,
  - more easily maintained and expanded.
- Traditionally, Enterprise systems and workloads, containerized or not, have been housed on-premises,
  - housed on a set of high-capacity servers running in the company's network or data center.

- <font color=red> on-premises systems </font>
  - When an application's computing needs begin to outstrip its available computing resources
    - would need to procure more powerful servers.
    - Install them on the company network after any necessary network changes or expansions.
    - Configure the new servers
    - and finally load the application and it's dependencies onto the new servers before resource bottlenecks could be resolved.
  - shortcut
    - The time required to complete an on-premises upgrade could be from months to years.
    - also costly, the useful lifespan of the average server is only three to five years.


> what if you need more computing power now, not months from now?
> What if the company wants to begin to relocate some workloads away from on-premises to the Cloud to take advantage of lower cost and higher availability, but is unwilling or unable to move the enterprise application from the on-premises network?
> What if you want to use specialized products and services that only available in the Cloud?
> This is where a modern hybrid or multi-cloud architecture can help.

---

### modern hybrid ON multi-cloud architecture

- creating an environment uniquely suited to the company's needs.
  - keep parts of the systems infrastructure on-premises
  - Move only specific workloads to the Cloud at the own pace
    - because a full scale migration is not required for it to work.

- benefits:
  - Take advantage of the cloud services for running the workloads you decide to migrate.
    - <font color=red> flexibility, scalability, and lower computing costs </font>
  - Add specialized services to the computing resources tool kit.
    - such as <font color=red> machine learning, content caching, data analysis, long-term storage, and IoT </font>

- the adoption of hybrid architecture for powering distributed systems and services.


---

## Anthos

- modern solution for <font color=blue> hybrid and multi-cloud distributed systems and service management </font>
  - powered by the latest innovations in distributed systems, and service management software from Google.
- On-permises and Cloud environments stay in sync
  - The Anthos framework rests on Kubernetes and GKE on-prem.
- provides
  - the foundation for an architecture
    - the foundation that is fully integrated with centralized management through a central control plane that supports <font color=blue> policy based application lifecycle </font> delivery across <font color=blue> hybrid and multi-cloud environments </font>
  - a rich set of tools
    - Manage sevices on-permises and in the cloud
    - monitor systems and services
      - for monitoring and maintaining the consistency of the applications across all network (on-premises, Cloud, multiple clouds)
    - migrate application from VMs into the clusters
    - maintain consistent policies across across all network (on-premises, Cloud, multiple clouds)

---

### build a modern hybrid infrastructure stack with Anthos.

![Screen Shot 2021-02-07 at 23.50.31](https://i.imgur.com/7LTuSeN.png)


- <font color=red> Google Kubernetes Engine on the Cloud site </font> of the hybrid network.
  - Google Kubernetes Engine is a managed production-ready environment for <font color=blue> deploying containerized applications </font>
  - Operates seamlessly with high availability and an SLA.
  - Runs certified Kubernetes ensuring portability across clouds and on-premises.
  - Includes auto-node repair, and auto-upgrade, and auto-scaling.
  - Uses regional clusters for high availability with multiple masters.
  - Node storage replication across multiple zones.

- <font color=red> Google Kubernetes Engine deployed ON-PREM </font>
  - a turn-key production-grade conformed version of Kubernetes
  - with the best practice configuration already pre-loaded.
  - Provides
    - <font color=blue> easy upgrade path to the latest validated Kubernetes releases </font> by Google.
    - <font color=blue> Provides access to container services </font> on Google Cloud platform,
      - such as Cloud build, container registry, audit logging, and more.
    - <font color=blue> integrates with Istio, Knative and Marketplace Solutions </font>
  - Ensures a consistent Kubernetes version and experience across Cloud and on-premises environments.

- <font color=red> Marketplace </font>
  - both <font color=blue> Google Kubernetes Engine in the Cloud </font> and <font color=blue> Google Kubernetes Engine deployed on-premises </font> integrate with <font color=blue> Marketplace </font>
  - so all of the clusters in network (on-premises or in the Cloud), have access to the same repository of containerized applications.
  - benefits:
    - use the same configurations on both the sides of the network,
    - reducing the time spent developing applications.
    - use ones replicate anywhere
    - maintaining conformity between the clusters.

> Enterprise applications may use hundreds of microservices to handle computing workloads.
> Keeping track of all of these services and monitoring their health can quickly become a challenge.



- <font color=red> Anthos </font>
  - an Istio Open Source service mesh
  - take these guesswork out of managing and securing the microservices.

- <font color=red> Cloud interconnect </font>
  - These service mesh layers communicate across the hybrid network by Cloud interconnect
  - to sync and pass their data.

- <font color=red> Stackdriver </font>
  - the <font color=blue> built-in logging and monitoring solution </font> for Google Cloud.
    - offers a fully managed logging, metrics collection, monitoring dashboarding, and alerting solution that watches all sides of the hybrid on multi-cloud network.
  - the ideal solution for <font color=blue> single easy configure powerful cloud-based observability solution </font>
  - a single pane of class dashboard to monitor all of the environments.

- <font color=red> Anthos Configuration Management </font>
  - provides
    - a single source of truth for the clusters configuration.
      - source of truth is kept in the policy repository, a git repository.
      - this repository can be located on-premises or in the Cloud.
    - deploy code changes with a single repository commit.
    - implement configuration inheritance, by using namespaces.

- <font color=red> Anthos Configuration Management agents </font>
  - use the policy repository to enforce configurations locally in each environment,
  - managing the complexity of owning clusters across environments.
















.
