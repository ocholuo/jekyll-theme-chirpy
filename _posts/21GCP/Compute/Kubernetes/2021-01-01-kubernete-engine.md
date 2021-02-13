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


## GCP Compute Engine

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


## Kubernetes

![Screen Shot 2021-02-07 at 14.29.23](https://i.imgur.com/zoPNLpx.png)

> kubectl

![Screen Shot 2021-02-11 at 23.08.12](https://i.imgur.com/lUUjWuY.png)

- a software layer that sits between the applications and the hardware infrastructure.

- an open-source orchestrator
  - a project of the Vendor Neutral Cloud Native Computing Foundation.

- a popular container management and orchestration solution
  - for containers to better manage and scale the applications.
  - describe a set of applications and how they should interact with each other,
  - and Kubernetes figures out how to make that happen.
  - it abstracts away the underlying infrastructure,
  - to easier consistently run and manage the applications.

- offers an API that let authorized people control its operation through several utilities.

- deploy containers on a set of nodes called cluster


- Kubernetes also facilitates
  - the features of PaaS
    - it automates the deployment scaling, load balancing, logging, monitoring, and other management features of containerized applications.  
  - the features of IaaS
    - such as allowing a wide range of user preferences and configuration flexibility.


- Kubernetes supports <font color=red> declarative configurations </font>
  - administer the infrastructure declaratively,
    - describe the desired state to achieve instead of commands to achieve that state.
    - Kubernetes make the deployed system conform to the desired state
    - and then keep it there in spite of failures.
  - Declarative configuration
    - saves you work.
    - Because the system is desired state is always documented,
    - reduces the risk of error.

- Kubernetes also allows <font color=red> imperative configuration </font>
  - issue commands to change the system state.
  - But administering Kubernetes as scale imperatively, will be a big missed opportunity.

  - <font color=blue> One of the primary strengths of Kubernetes is its ability to automatically keep a system in a state that you declare </font>
    - Experienced Kubernetes administrators use imperative configuration
      - only for quick temporary fixes
      - and as a tool in building a declarative configuration.


features.
- Kubernetes supports different workload types.
  - stateless applications
    - such as an Nginx or Apache web server,
  - stateful applications
    - where user in session data can be stored persistently.
  - It also supports batched jobs and demon tasks.

- automatically scale in and out containerized applications based on resource utilization.
  - can specify resource requests levels and resource limits for the workloads and Kubernetes will obey them.
  - These resource controls like Kubernetes, improve overall workload performance within the cluster.

- extensibility
  - Developers extend Kubernetes through a rich ecosystem of plugins and add-ons.
  - For example, there's a lot of creativity going on currently with Kubernetes custom resource definitions which bring the Kubernetes declarative Management Model to amazing variety of other things that need to be managed.

- portability
  - open source,
  - Kubernetes also supports workload portability across On-premises or multiple Cloud service providers such as GCP and others.
  - This allows Kubernetes to be deployed anywhere.
  - You can move Kubernetes workloads freely without a vendor login.


---

## to build Kubernetes cluster

![Screen Shot 2021-02-07 at 14.45.26](https://i.imgur.com/o0IN3Ou.png)

to build Kubernetes cluster

1. build on the own hardware/environment that provides virtual machines
   - built it theself, you have to maintain it.
   - That's even more toil.

2. <font color=red> Google Kubernetes Engine GKE </font>
   - deploy, manage and scale Kubernetes environments for the containerized applications on GCP.
     - easy to brings Kubernetes as a managed service on Google Cloud Platform.
     - building, scheduling, load balancing, and monitoring workloads,
     - providing for discovery of services,
     - managing role-based access control and security,
     - and providing persistent storage to these applications.
   - a component of the GCP compute offerings

![Screen Shot 2021-02-12 at 01.06.57](https://i.imgur.com/VHpeVXq.png)
 
---

## GKE (Kubernetes Engine)

Google Kubernetes Engine GKE
 
  - Kubernetes, a way to orchestrate code in those containers.



- <font color=red> fully managed </font>
  - don't have to provision the underlying resources
  - These operating systems are maintained by Google.
  - optimized to scale quickly and with a minimal resource footprint.


- <font color=red> an orchestration system for applications in containers </font>
  - uses a <font color=red> container-optimized operating system </font>
    - <font color=blue> containerization </font>
      - a way to package code that's designed to be highly portable and to use resources very efficiently.
    - <font color=blue> Kubernetes </font>
      - a way to orchestrate code in those containers.
  - automates deployment, scaling, load balancing, logging, and monitoring, and other management features
  - A managed environment for deploying containerized applications
  - run containerized applications on a Cloud environment that Google Cloud manages for you under the administrative control.

- Google Kubernetes Engine <font color=red> extends Kubernetes management on GCP </font>
  - by adding features and integrating with other GCP services automatically
  - <font color=red> adding features </font>
    - GKE supports 
      - <font color=blue> cluster scaling </font>
      - <font color=blue> persistent disks </font>
      - <font color=blue> automated updates to the latest version of Kubernetes </font>
      - <font color=blue> and auto repair for unhealthy nodes </font>
    1. Just as Kubernetes support scaling workloads
       - GKE support scaling the cluster itself.
    2. direct the service to instantiate a <font color=blue> Kubernetes system, cluster </font>
       - GKE clusters can be customized
         - support different machine types, numbers of nodes and network settings.
       - the resources used to build Kubernetes Engine clusters come from Compute Engine
         - <font color=blue> Kubernetes Engine workloads run in clusters built from Compute Engine virtual machines </font>
         - Kubernetes Engine gets to take advantage of Compute Engine’s and Google VPC’s capabilities. 
       - If enable GKE's `auto upgrade feature`
         - the clusters are automatically upgraded with the latest and greatest version of Kubernetes.
         - and you can enable automatic node upgrades too.
    3. <font color=blue> nodes, the virtual machines </font> that host the containers inside of a GKE cluster
       - If enable GKE's `auto repair feature`
       - the service will automatically repair unhealthy nodes
         - make periodic health checks on each node in the cluster.
         - If a node is determined to be unhealthy and requires repair, GKE would drain the node.
         - cause it's workloads to gracefully exit and then recreate that node.
 
  - <font color=red> seamlessly integrates with </font>
    1. with <font color=blue> Google Cloud build and container registry. </font>
       - create container using Cloud Build
       - and storing a container in Container Registry.
       - automate deployment using private container images that securely stored in container registry.

    2. with <font color=blue> Google's identity and access management </font>
       - control access through the use of accounts and role permissions.

    3. with <font color=blue> Stackdriver monitoring </font>
       - Stackdriver, Google Cloud system for monitoring and management for services, containers, applications, and infrastructure.
       - to help you understand the applications performance.

    4. with <font color=blue> Google VPCs </font> virtual private clouds
       - makes use of GCP's networking features.

    5. <font color=blue> the GCP console </font> 
       - provides insights into GKE clusters and the resources 
       - view, inspect and delete resources in those clusters.
       - open source Kubernetes 
         - contains a dashboard
         - but takes a lot of work to set it up securely.
       - the GCP console
         - dashboard for GKE clusters and workloads that you don't have to manage.
         - more powerful than the Kubernetes dashboard.

    6. <font color=blue> Existing workloads running within on-premise clusters can easily be moved on to GCP </font> 

- very well suited for 
  - containerized applications. 
  - Cloud-native distributed systems and hybrid applications.  

![Screen Shot 2021-02-12 at 01.19.15](https://i.imgur.com/0nlsQ3W.png)



---

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

### build Kubernetes cluster, deploy pods, by Kubernetes Engine

> deploy a Kubernetes cluster using GKE, deploy pods to a GKE cluster and view and managed Kubernetes objects.

- create a Kubernetes cluster with Kubernetes Engine
  - by `GCP console`
  - or the `g-cloud command` by the Cloud SDK.

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
