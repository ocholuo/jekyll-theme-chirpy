---
title: Virtualization - Container
date: 2020-11-26 11:11:11 -0400
categories: [19VMs, Containers]
tags: [Linux, VMs]
math: true
image:
---

[toc]

---

# Virtualization - Container

---

## basic

- a method of operating system virtualization
- fast, portable, and infrastructure-agnostic execution environment.


- containers <font color=red> share a virtualized operating system </font>
  - <font color=red> run as resource-isolated processes </font>
  - smaller than virtual machines,
  - do not contain an entire operating system.

- ensure <font color=red> quick, reliable, and consistent deployments </font> of applications, regardless of deployment environment.
  
- quick
  - space, container images are usually an order of magnitude smaller than virtual machines.
  - Spinning up a container happens in hundreds of milliseconds.

- deliver <font color=red> environmental consistency </font>
  - all <font color=blue> application's code, configurations, and dependencies </font> are packaged into a single object. 
  - a easy-to-use building blocks
  - Containers hold everything that the software needs to run,
      - <font color=blue> such as libraries, system tools, code, and the runtime </font>

- operational efficiency,
  - give more granular control over resources, which gives your infrastructure improved efficiency.

- developer productivity, and version control.

- Microservices
  - application are created using independent stateless components or microservices runningn in containers

- Docker or Windows Containers
  - docker for linux.
  - Windows Containers for win workloads.


---

## virtualmachine (VM)-based vs container-based deployment.
- ![Container](https://i.imgur.com/xtbJiVc.png)
- ![VM](https://i.imgur.com/SmOVHbs.png)


