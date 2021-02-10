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

## compute infrastructure

2 GCP products that provide the compute infrastructure for applications:
- Compute Engine
- Kubernetes Engine
- App Engine
  - Standard
  - Flexible
- Cloud Function

![Screen Shot 2021-02-09 at 23.26.11](https://i.imgur.com/Zghiw6i.png)


---

### Compute Engine

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

- preemptible VMs 抢先的
  - have a workload that no human being is sitting around waiting to finish, say a batch job analyzing large dataset
  - benifit:
    - save money by choosing preemptible VMs to run the job.
    - although be sure to make the job able to be stopped and restarted.
  - different from an ordinary Compute Engine VM in only one respect.
    - given compute engine permission to terminate it if it's resources are needed elsewhere.  

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
