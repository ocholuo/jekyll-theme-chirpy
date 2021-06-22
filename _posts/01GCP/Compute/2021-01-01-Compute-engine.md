---
title: GCP - Google Cloud Computing - Compute Engine
date: 2021-01-01 11:11:11 -0400
categories: [21GCP]
tags: [GCP]
toc: true
image:
---

- [Google Cloud Computing - Compute Engine](#google-cloud-computing---compute-engine)
  - [basic](#basic)
  - [create a virtual machine instance](#create-a-virtual-machine-instance)

---


# Google Cloud Computing - Compute Engine

---

## basic

Compute Engine
- <font color=red> Infrastructure as a Service </font>
- <font color=red> A managed environment </font> for deploying virtual machines
  - run thousands of virtual CPUs on a system that is designed to be fast and to offer consistent performance.
- <font color=red> Fully customizable VMs </font>
  - Compute Engine offers virtual machines that run on GCP
  - create and run virtual machines on Google infrastructure.
  - run virtual machines on demand in the Cloud.  
    - select predefined VM configurations
    - create customized configurations
- <font color=red> no upfront investments </font>

- choice:
  - have <font color=blue> complete control over your infrastructure </font>
    - maximum flexibility
    - for people who prefer to manage those server instances themselves.
    - customize operating systems and even run applications that rely on a mix of operating systems.
  - best option <font color=blue> if other computing options don't support your applications or requirements </font>
    - easily lift and shift your on-premises workloads into GCP without rewriting the applications or making any changes.

---

## create a virtual machine instance
- by Google Cloud Platform console or the GCloud command line tool.

<font color=red> OS </font>
- Linux and Windows Server images provided by Google or customized versions of these images
- import images for many of the physical servers.

<font color=red> Machine type </font>
- how much memory and virtual CPUs
- range from very small to very large indeed.
- can make a custom VM.

<font color=red> Processing power </font>
- machine learning and data processing that can take advantage of GPUs, many GCP zones have GPU's available for you.
- Just like physical computers need disks, so do VM.

Virtual machines need <font color=red> block storage </font>  
- (2 main  choices)
1. 2 persistent storage
   - standard or SSD.
   - offer network stores that can scale up to 64 terabytes
   - can easily take snapshots of the disks for backup and mobility
2. attach a local SSD
   - If application needs high-performance scratch space
   - enable very high input/output operations per second
- to store data of permanent value somewhere else
   - local SSDs content doesn't last past when the VM terminates.
   - persistent disks can.

<font color=red> boot image. </font>
- choose a boot image.
  - GCP offers lots of versions of Linux and Windows
  - can import the own images too.

<font color=red> VM startup scripts </font>
- let the VMs come up with certain configurations
  - like installing software packages on first boot.
- pass <font color=red> GCP VM startup scripts </font> to do so.
  - can also pass in other kinds of metadata too.

<font color=red> snapshot </font>
- Once the VMs are running
- take a durable snapshot of their disks.
- keep these as backups or use when need to migrate a VM to another region.


<font color=red> grained control of costs </font>
- <font color=blue> per second billing </font>
  - GCP enables fine grained control of costs of Compute Engine resources by providing per second billing.
  - helps reduce the costs when deploying compute resources for short periods of time
    - such as batch processing jobs

<font color=blue> preemptible VMs instances 抢先的 </font>

- provide significantly cheaper pricing for the workloads that can be interrupted safely
  - have a workload that no human being is sitting around waiting to finish
  - such as batch job analyzing large dataset
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

<font color=red> Auto scaling </font>
- add and take away VMs from the application based on load metrics.
- place the Compute Engine workloads behind global load balancers that support autoscaling



- balancing the incoming traffic across the VMs
  - Google VPC supports several different kinds of load balancing


<font color=red> managed instance groups </font>
- define resources that are automatically deployed to meet demand



<font color=red> Availability policies </font>
- If a VM is stopped (outage or a hardware failure), the automatic restart feature starts it back up. Is this the behavior you want? Are the applications idempotent (written to handle a second startup properly)?
- During host maintenance, the VM is set for live migration. However, you can have the VM terminated instead of migrated.
