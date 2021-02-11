---
title: GCP - Compute Engine
date: 2021-01-01 11:11:11 -0400
categories: [21GCP]
tags: [GCP]
toc: true
image:
---

[toc]

---


# Compute Engine


---


## Cloud Computing and GCP

---

### Cloud Computing


![Screen](https://i.imgur.com/7vMDIFw.png)

![Screen Shot 2021-02-03 at 14.32.13](https://i.imgur.com/6p8blb8.png)

5 fundamental attributes.
1. on-demand and self-service.
   1. automated interface and get the processing power, storage, and network they need with no human intervention.
2. broad network access
   1. resources are accessible over a network from any location.  
3. Resource pooling
   1. Providers allocate resources to customers from a large pool,
   2. allowing them to benefit from economies of scale.
   3. Customers don't have to know or care about the exact physical location of these resources.
4. Rapid elasticity
   1. Resources themselves are elastic.
   2. Customers who need more resources can get them rapidly,
   3. when they need less they can scale back.
5. Measured service
   1. customers pay for only what they use or reserve as they go.
   2. stop using resources, stop paying.

---

### Google Cloud


> GCP products that provide the compute infrastructure for applications


![Screen Shot 2021-02-10 at 21.27.05](https://i.imgur.com/0bNVdA2.png)


- <font color=red> Compute Engine </font>
  - <font color=blue> Infrastructure as a Service </font>
  - A managed environment for deploying virtual machines
  - to run virtual machines on demand in the Cloud.  
  - It provides maximum flexibility
    - for people who prefer to manage those server instances themselves.

- <font color=red> Kubernetes Engine</font>
  - A managed environment for deploying containerized applications
  - to run containerized applications on a Cloud environment that Google Cloud manages for you under your administrative control.
  - containerization, a way to package code that's designed to be highly portable and to use resources very efficiently.
  - Kubernetes, a way to orchestrate code in those containers.

- <font color=red> App Engine </font>
  - <font color=blue> Platform as a Service </font>
  - fully managed Framework.
  - A managed serverless platform for deploying applications
  - run code in the Cloud without worry about infrastructure.
  - focus on just code and let Google deal with all the provisioning and resource management.
  - 2 way:
    - Standard
    - Flexible

- <font color=red> Cloud Function </font>
  - <font color=blue> functions as a Service </font>
  - completely serverless execution environment
  - A managed serverless platform for deploying event-driven functions
  - It executes your code in response to events,
    - whether those occur once a day or many times per second.
  - Google scales resources as required, but you only pay for the service while your code runs.

![Screen Shot 2021-02-09 at 23.26.11](https://i.imgur.com/Zghiw6i.png)


![Screen Shot 2021-02-03 at 14.34.02](https://i.imgur.com/e2nAsAC.png)

---

## Compute Engine

- create and run virtual machines on Google infrastructure.
- no upfront investments
- run thousands of virtual CPUs on a system that is designed to be fast and to offer consistent performance.


create a virtual machine instance
- by Google Cloud Platform console or the GCloud command line tool.

- the VM can run
  - Linux and Windows Server images provided by Google or customized versions of these images
  - import images for many of the physical servers.

- pick a machine type
  - determines how much memory and virtual CPUs
  - These types range from very small to very large indeed.
  - can make a custom VM.

- processing power
  - machine learning and data processing that can take advantage of GPUs, many GCP zones have GPU's available for you.
  - Just like physical computers need disks, so do VM.

- persistent storage
  - 2 persistent storage;
    - standard or SSD.
  - attach a local SSD
    - If application needs high-performance scratch space,
    - be sure to store data of permanent value somewhere else
    - because local SSDs content doesn't last past when the VM terminates.
  - That's why the other kinds are called persistent disks.

- choose a boot image.
  - GCP offers lots of versions of Linux and Windows
  - can import the own images too.

- want the VMs come up with certain configurations
  - like installing software packages on first boot.
  - pass <font color=red> GCP VM startup scripts </font> to do so.
  - can also pass in other kinds of metadata too.

- Once the VMs are running
  - take a durable snapshot of their disks.
  - keep these as backups or use when need to migrate a VM to another region.

- preemptible VMs instances 抢先的
  - have a workload that no human being is sitting around waiting to finish, say a batch job analyzing large dataset 
  - benifit:
    - save money  
      - cost less per hour but can be terminated by Google Cloud at any time. 
  - different from an ordinary Compute Engine VM in only one respect.
    - given compute engine permission to terminate it if it's resources are needed elsewhere.  
    - make sure the job able to be stopped and restarted.
  - can't convert a non-preemptible instance into a preemptible one.
    - must be made at VM creation.



- choose the machine properties of the instances
  - such as the number of virtual CPUs and the amount of memory
  - by using a set of predefined machine types
  - or by creating the own custom machine types.
  - the maximum number of virtual CPUs and the VM was 96 and the maximum memory size was in beta at 624 gigabytes.
  - huge VMs are great for workloads like in-memory databases and CPU intensive analytics
  - but most GCP customers start off with scaling out not scaling up.

- auto scaling
  - add and take away VMs from the application based on load metrics.

- balancing the incoming traffic across the VMs
  - Google VPC supports several different kinds of load balancing



**Availability policies**.
 - If a VM is stopped (outage or a hardware failure), the automatic restart feature starts it back up. Is this the behavior you want? Are your applications idempotent (written to handle a second startup properly)?
 - During host maintenance, the VM is set for live migration. However, you can have the VM terminated instead of migrated.