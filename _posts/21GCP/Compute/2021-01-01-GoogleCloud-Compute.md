---
title: GCP - Google Cloud Computing Solutions
date: 2021-01-01 11:11:11 -0400
categories: [21GCP]
tags: [GCP]
toc: true
image:
---

[toc]

---


# Google Cloud Computing Solutions


---

## Cloud Computing


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

## Google Cloud Computing Solutions


> GCP products that provide the compute infrastructure for applications

![Screen Shot 2021-02-12 at 13.25.46](https://i.imgur.com/uuTClRK.png)

![Screen Shot 2021-02-03 at 14.34.02](https://i.imgur.com/e2nAsAC.png)

![Screen Shot 2021-02-09 at 23.26.11](https://i.imgur.com/Zghiw6i.png)


- <font color=red> Compute Engine </font>
  - [detailed page](https://ocholuo.github.io/posts/Compute-engine/)
  - <font color=blue> Infrastructure as a Service </font>
  - A managed environment for deploying virtual machines 
  - Fully customizable VMs
    - Compute Engine offers virtual machines that run on GCP
    - create and run virtual machines on Google infrastructure.
    - run virtual machines on demand in the Cloud.  
      - select predefined VM configurations
      - create customized configurations
  - no upfront investments
  - run thousands of virtual CPUs on a system that is designed to be fast and to offer consistent performance.
  - choice:
    - have complete control over your infrastructure
      - maximum flexibility
      - for people who prefer to manage those server instances themselves.
      - customize operating systems and even run applications that rely on a mix of operating systems.
    - best option when other computing options don't support your applications or requirements
      - easily lift and shift your on-premises workloads into GCP without rewriting the applications or making any changes.


- <font color=red> GKE Kubernetes Engine</font>
  - [detailed page](https://ocholuo.github.io/posts/kubernete-engine/)
  - A managed environment for deploying containerized applications
  - to run containerized applications on a Cloud environment that Google Cloud manages for you under the administrative control.
  - containerization, a way to package code that's designed to be highly portable and to use resources very efficiently.
  - Kubernetes, a way to orchestrate code in those containers.


- <font color=red> App Engine </font>
  - [detailed page](https://ocholuo.github.io/posts/app-engine/)
  - <font color=blue> Platform as a Service </font>
  - fully managed serverless application framework.
    - deploy an application on App Engine
      - hand App Engine the code
      - and the App Engine service takes care of the rest.
    - focus on code and run code in the Cloud 
      - without worry about infrastructure.
      - focus on building applications instead of deploying and managing the environment. 
      - Google deal with all the provisioning and resource management.
        - no worry about building the highly reliable and scalable infrastructure 
        - zero server management or configuration deployments for deploying applications
        - The App Engine platform manages the hardware and networking infrastructure for the code.

  - provides built-in services that many web applications need.
    - code the application to take advantage of these services and App Engine provides them.
    - `NoSQL databases, in-memory caching, load balancing, health checks, logging` and a `way to authenticate users`.
    - could also `run container workloads`. 
    - `Stackdriver monitoring, logging, and diagnostics`
      - such as debugging and error reporting are also tightly integrated with App Engine. 
      - use Stackdriver's real time debugging features to analyze and debug your source code. 
      - Stackdriver integrates with tools such as Cloud SDK, cloud source repositories, IntelliJ, Visual Studio, and PowerShell. 
    - App Engine also supports `version control and traffic splitting`.

  - scale the application automatically in response to the amount of traffic it receives.

  - only pay for those resources you use.
    - no servers to provision or maintain.

  - App Engine offers two environments:
    - standard and flexible

  - App Engine supports popular languages like Java and Node.js, Python, PHP, C#, .NET, Ruby, and Go. 
   
  - especially suited for applications
    - where the workload is highly variable or unpredictable
    - like web applications and mobile backend.
    - for websites, mobile apps, gaming backends, 
    - and as a way to present a RESTful API to the Internet
      - an application program interface
      - resembles the way a web browser interacts with the web server. 
      - RESTful APIs are easy for developers to work with and extend. 
      - And App Engine makes them easy to operate
 


- <font color=red> Cloud Run </font>
  - Cloud Run

- <font color=red> Cloud Function </font>
  - <font color=blue> functions as a Service </font>
  - completely serverless execution environment
  - A managed serverless platform for deploying event-driven functions
  - It executes the code in response to events,
    - whether those occur once a day or many times per second.
  - Google scales resources as required, but you only pay for the service while the code runs.

---