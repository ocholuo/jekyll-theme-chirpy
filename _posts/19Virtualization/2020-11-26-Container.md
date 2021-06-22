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

![Screen Shot 2021-02-11 at 17.21.07](https://i.imgur.com/iUnkAWY.png)

traditional
---
- deploy an application on its own physical computer.



![Screen Shot 2021-02-11 at 17.49.44](https://i.imgur.com/KVMQ1Yk.png)

virtualization
---
- takes less time to deploy new solutions
- waste less of the resources on those physical computers
- get some improved portability because virtual machines can be imaged and then moved around.


![Screen Shot 2021-02-11 at 17.49.28](https://i.imgur.com/Edp2Re5.png)

containers
---
- more efficient way to resolve the dependency problem
- Implement abstraction at the level of the application and its dependencies.
- not virtualize the entire machine /OS,
  - but just the user space.
  - the user space is all the code that resides above the kernel, and includes the applications and their dependencies.
- An application and its dependencies are called an image.
  - A container is simply a running instance of an image.
  - building software into Container images
  - and easily package and ship an application without worrying about the system it will be running on


---

### virtualmachine (VM)-based vs container-based deployment.
- ![Container](https://i.imgur.com/xtbJiVc.png)
- ![VM](https://i.imgur.com/SmOVHbs.png)

---


## Container


<img src="https://i.imgur.com/pb2kD6d.png" width="400">

<img src="https://i.imgur.com/IA6p81K.png" width="400">

- a method of operating system virtualization
  - give the environment independent scalability of workloads like PaaS
  - and an abstraction layer of the operating system and hardware, like IaaS

- fast, portable, and infrastructure-agnostic execution environment.
  - next step in the evolution of managing code
  - containers as delivery vehicles for application code,
  - lightweight, stand-alone, resource efficient, portable execution packages.
  - an application-centric way to deliver <font color=blue> high performance and scalable applications </font>

- containers <font color=red> share a virtualized operating system </font>
  - <font color=red> run as resource-isolated processes </font>
    - smaller than virtual machines,
    - do not contain an entire operating system.
  - starts as quickly as a new process.
    - it visualize the os rather than the hardware.


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
    - makes the code very portable.
      - treat the os and hardware as a black box.
      - can move the code from development, to staging, to production, or from the laptop to the Cloud without changing or rebuilding anything.
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


use case:
- to scale a web server
  - can do so in seconds
  - and deploy dozens or hundreds of them depending on the size of the workload on a single host.
- to build the applications using lots of Containers,
  - each performing their own function, using the micro-services pattern.
  - The units of code running in these Containers can communicate with each other over a network fabric.


---

### build and run containers

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

## containers component

Containers power to isolate workloads is derived from the composition of several technologies.
- Linux process.
  - One foundation is the Linux process.
  - Each Linux process has its own virtual memory address phase, separate from all others.
  - Linux processes are rapidly created and destroyed.
- Linux namespaces
  - Containers use Linux namespaces to control what an application can see,
    - process ID numbers, directory trees, IP addresses, and more.
  - not the same thing as Kubernetes Namespaces
- Linux cgroups
  - Containers use Linux cgroups to control what an application can use,
    - its maximum consumption of CPU time, memory, IO bandwidth, other resources.
- Union File Systems
  - Containers use Union File Systems to efficiently encapsulate applications and their dependencies into a set of clean minimal layers.

---

## container image

- A container image is structured in layers.  
  - The Container manifest:
    - The file, tool to build the image reads instructions
  - Docker formatted Container Image:
    - Docker file.

- Each instruction in the Docker file specifies a layer inside the container image.
  - Each layer is, `Read only` When a Container runs from this image
  - have a writable ephemeral top-most layer.

![Screen Shot 2021-02-11 at 19.22.35](https://i.imgur.com/idTIa2c.png)


---


## Docker file

![Screen Shot 2021-02-11 at 19.24.35](https://i.imgur.com/Z1QHxsH.png)



- The From statement
  - create a base layer pulled from a public repository.

- The Copy command
  - adds a new layer
  - containing some files copied in from your build tools current directory.

- The Run command
  - builds your application using the make command
  - and puts the result of the build into a third layer.

- CMD
  - specifies what command to run within the container when it's launched.

> the best practice is not to build your application in the very same container that you ship and run.


- After all, your build tools are at best just cluttered and deployed Container and at worst, are an additional attack service.
  - Today, Application Packaging relies on a multi-stage build process and one Container builds the final executable image.
  - A separate container receives only what's needed to actually run the application.


Container layer
- When you launch a new container from an image, the Container Runtime adds a new writable layer on the top of the underlying layers.
  - All changes made to the running container,
    - such as writing new files, modifying existing files, and deleting files are written to this thin writable Container layer in their ephemeral
- when the Containers deleted the contents of this writeable layer are lost forever.
  - The underlying Container Image itself remains unchanged.

- to store data permanently
  - do so somewhere other than a running container image.



- Because each Container has its own writable Container layer and all changes are stored in this layer.
  - Multiple Containers can share access to the same underlying image and yet have their own data state.


- Because each layer is only a set of differences from the layer before it, you get smaller images.
  - When you build a container
    - instead of copying the whole image, it creates a layer with just the differences.
  - When you run a container
    - that Container Runtime pulls down the layers it needs.
  - When you update
    - only need to copy the difference
    - much faster than running a new virtual machine.




---


## Container Registry

1. download containerized software from a <font color=red> container registry </font>
   - use publicly available open source Container images as a base for images or for unmodified use.
   - Google maintains a Container Registry, `gcr.Io`.
     - contains many public, open source images  
       - find Container images and other public repositories, Docker Hub Registry, GitLab and others.
     - Google Container Registry is integrated with Cloud IAM.
       - can use it to store your images that aren't public.
       - Instead, they're private to your project.

2. build your own container using <font color=red> docker </font>
   - downside
     - whoever building Containers with a Docker is that you must trust the computer that you do your builds on.
     - it doesn't offer a way to orchestrate those applications at scale like Kubernetes does.


3. build your own container using <font color=red> Cloud Build </font>
   - a managed service  
   - for building Containers that's also integrated with Cloud IAM.
   - Cloud build can retrieve the source code for your builds from a variety of different storage locations.
     - Cloud Source Repositories, Cloud Storage, which is GCP is Object Storage service or git compatible repositories like GitHub and Bitbucket to generate a buildup Cloud Build, you define as series of steps.
     - example
       - configure build steps to fetch dependencies, compile source code, run integration tests, or use tools such as Docker, Gradle, and Maven.
       - Each build step and Cloud build runs in a Docker container.
       - Then Cloud build can deliver your newly built images to various execution environments, not only GKE, but also App Engine and Cloud Functions.

![Screen Shot 2021-02-11 at 22.23.06](https://i.imgur.com/Ks5ny3j.png)

---

## software for container

software to build Container images and to run them.

1. Docker for linux.
   - open source technology
   - create and run applications in containers

2. Windows Containers for win workloads.

3. Kubernetes
   - make applications modular.
     - They deploy it easily and scale independently across a group of hosts.
   - The host can scale up and down, and start and stop Containers as demand for the application changes, or even as hosts fail and are replaced.

   - A tool that helps you do this well is Kubernetes.
     - Kubernetes makes it easy to orchestrate many Containers on many hosts.
     - Scale them,
     - roll out new versions of them, and even roll back to the old version if things go wrong.
























.
