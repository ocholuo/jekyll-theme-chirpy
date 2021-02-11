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

traditional
- deploy an application on its own physical computer.

virtualization
- takes less time to deploy new solutions
- waste less of the resources on those physical computers
- get some improved portability because virtual machines can be imaged and then moved around.

containers
- more efficient way to resolve the dependency problem
- Implement abstraction at the level of the application and its dependencies.
- Don't have to virtualize the entire machine or operating system, but just the user space. 
- the user space is all the code that resides above the kernel, and includes the applications and their dependencies. 


---


## Container

- a method of operating system virtualization

- fast, portable, and infrastructure-agnostic execution environment.
  - next step in the evolution of managing code
  - containers as delivery vehicles for application code,
  - lightweight, stand-alone, resource efficient, portable execution packages. 
  - an application-centric way to deliver <font color=blue> high performance and scalable applications </font>

- containers <font color=red> share a virtualized operating system </font>
  - <font color=red> run as resource-isolated processes </font>
  - smaller than virtual machines,
  - do not contain an entire operating system.

- isolated user spaces per running application code. 

- ensure <font color=red> quick, reliable, and consistent deployments </font> of applications

  - quick
    - lightweight because they don't carry a full operating system, 
      - can be scheduled or packed tightly onto the underlying system, which is very efficient. 
      - can be created and shut down very quickly 
        - because just start/stop the processes that make up the application 
        - not booting up an entire VM or initializing an OS for each application. 
    - container images are usually an order of magnitude smaller than virtual machines.
    - Spinning up a container happens in hundreds of milliseconds.

  - deliver <font color=red> environmental consistency </font>
    - regardless of deployment environment.
    - all <font color=blue> application's code, configurations, and dependencies </font> are packaged into a single object.
      - a easy-to-use building blocks
      - Containers hold everything that the software needs to run,
        - <font color=blue> such as libraries, system tools, code, and the runtime </font>
      - the container's the same and runs the same anywhere. 

- operational efficiency,
  - give more granular control over resources, which gives your infrastructure improved efficiency.

- developer productivity, and version control.
  -  You make incremental changes to a container based on a production image, you can deploy it very quickly with a single file copy, this speeds up your development process. 

- execute your code on VMs without worrying about software dependencies 
  - like application run times, system tools, system libraries, and other settings. 
  - package your code with all the dependencies it needs, and the engine that executes your container, is responsible for making them available at runtime. 

- Microservices
  - application are created using independent stateless components or microservices runningn in containers  
  - loosely coupled, fine-grained components.
  - allows the operating system to scale and also upgrade components of an application without affecting the application as a whole.



---


## different container

- Docker or Windows Containers
  - docker for linux.
  - Windows Containers for win workloads.


---

## virtualmachine (VM)-based vs container-based deployment.
- ![Container](https://i.imgur.com/xtbJiVc.png)
- ![VM](https://i.imgur.com/SmOVHbs.png)
