

## Containers, Kubernetes, and Kubernetes Engine

Welcome to this module on software containers and running them using Google Kubernetes Engine. 
- We've already discussed Compute Engine, which is GCPs Infrastructure as a Service offering, which lets you run Virtual Machine in the cloud and gives you persistent storage and networking for them,and App Engine, which is one of GCP's platform as a service offerings. 
- Now I'm going to introduce you to a service called Kubernetes Engine. 
- It's like an Infrastructure as a Service offering in that it saves you infrastructure chores. 
- It's also like a platform as a service offering, in that it was built with the needs of developers in mind. 
- First, I'll tell you about a way to package software called Containers. 
- I'll describe why Containers are useful, and how to manage them in Kubernetes Engine. 
- Let's begin by remembering that infrastructure as a service offering let you share compute resources with others by virtualizing the hardware. 
- Each Virtual Machine has its own instance of an operating system, your choice, and you can build and run applications on it with access to memory, file systems, networking interfaces, and the other attributes that physical computers also have. 
- But flexibility comes with a cost. 
- In an environment like this, the smallest unit of compute is a Virtual Machine together with its application. 
- The guest OS, that is the operating system maybe large, even gigabytes in size. 
- It can take minutes to boot up. 
- Often it's worth it. 
- Virtual Machine are highly configurable, and you can install and run your tools of choice. 
- So you can configure the underlying system resources such as disks and networking, and you can install your own web server database or a middle ware. 
- But suppose your application is a big success. 
- As demand for it increases, you have to scale out in units of an entire Virtual Machine with a guest operating system for each. 
- That can mean your resource consumption grows faster than you like. 
- Now, let's make a contrast with a Platform as a Service environment like App Engine. 
- From the perspective of someone deploying on App Engine, it feels very different. 
- Instead of getting a blank Virtual Machine, you get access to a family of services that applications need. 
- So all you do is write your code and self-contained workloads that use these services and include any dependent libraries. 
- As demand for your application increases, the platform scales your applications seamlessly and independently by workload and infrastructure. 
- This scales rapidly, but you give up control of the underlying server architecture. 
- That's where Containers come in. 
- The idea of a Container is to give you the independent scalability of workloads like you get in a PaaS environment, and an abstraction layer of the operating system and hardware, like you get in an Infrastructure as a Service environment. 
- What do you get as an invisible box around your code and its dependencies with limited access to its own partition of the file system and hardware? Remember that in Windows, Linux, and other operating systems, a process is an instance of a running program. 
- A Container starts as quickly as a new process. 
- Compare that to how long it takes to boot up an entirely new instance of an operating system. 
- All you need on each host is an operating system that supports Containers and a Container run-time. 
- In essence, you're visualizing the operating system rather than the hardware. 
- The environment scales like PaaS but gives you nearly the same flexibility as Infrastructure as a Service. 
- The container abstraction makes your code very portable. 
- You can treat the operating system and hardware as a black box. 
- So you can move your code from development, to staging, to production, or from your laptop to the Cloud without changing or rebuilding anything. 
- If you went to scale for example a web server, you can do so in seconds, and deploy dozens or hundreds of them depending on the size of your workload on a single host. 
- Well, that's a simple example. 
- Let's consider a more complicated case. 
- You'll likely want to build your applications using lots of Containers, each performing their own function, say using the micro-services pattern. 
- The units of code running in these Containers can communicate with each other over a network fabric. 
- If you build this way, you can make applications modular. 
- They deploy it easily and scale independently across a group of hosts. 
- The host can scale up and down, and start and stop Containers as demand for your application changes, or even as hosts fail and are replaced. 
- A tool that helps you do this well is Kubernetes. 
- Kubernetes makes it easy to orchestrate many Containers on many hosts. 
- Scale them, roll out new versions of them, and even roll back to the old version if things go wrong. 
- First, I'll show you how you build and run containers. 
- The most common format for Container images is the one defined by the open source tool Docker. 
- In my example, I'll use Docker to bundle an application and its dependencies into a Container. 
- You could use a different tool. 
- For example, Google Cloud offers Cloud Build, a managed service for building Containers. 
- It's up to you. 
- Here is an example of some code you may have written. 
- It's a Python web application, and it uses the very popular Flask framework. 
- Whenever a web browser talks to it by asking for its top-most document, it replies "hello world". 
- Or if the browser instead appends/version to the request, the application replies with its version. 
- Great. 
- So how do you deploy this application? It needs a specific version of Python and a specific version of Flask, which we control using Python's requirements.txt file, together with its other dependencies too. 
- So you use a Docker file to specify how your code gets packaged into a Container. 
- For example, Ubuntu is a popular distribution of Linux. 
- Let's start there. 
- You can install Python the same way you would on your development environment. 
- Of course, now that it's in a file, it's repeatable. 
- Let's copy in the requirements.txt file we created earlier, and use it to install our applications dependencies. 
- We'll also copy in the files that make up our application and tell the environment that launches this Container how to run it. 
- Then I use the Docker build command to build the Container. 
- This builds the Container and stores it on the local system as a runnable image. 
- Then I can use the docker run command to run the image. 
- In a real-world situation, you'd probably upload the image to a Container Registry service, such as the Google Container Registry and share or download it from there. 
- Great, we packaged an application, but building a reliable, scalable, distributed system takes a lot more. 
- How about application configuration, service discovery, managing updates, and monitoring? In the next lesson, we'll talk about how a Kubernetes and Kubernetes Engine help us there.


## Introduction to Kubernetes and GKE


Now that we've covered the basics of containers and containerizing your applications, I'll show you where Kubernetes comes in.
Play video starting at ::10 and follow transcript0:10
Kubernetes is an open-source orchestrator for containers so you can better manage and scale your applications. 
- Kubernetes offers an API that lets people, that is authorized people, not just anybody, control its operation through several utilities. 
- Very soon we'll meet one of those utilities, the kubectl command. 
- Kubernetes lets you deploy containers on a set of nodes called a cluster. 
- What's a cluster? It's a set of master components that control the system as a whole and a set of nodes that run containers. 
- In Kubernetes, a node represents a computing instance. 
- In Google Cloud, nodes are virtual machines running in Compute Engine.
Play video starting at :1: and follow transcript1:00
To use Kubernetes, you can describe a set of applications and how they should interact with each other, and Kubernetes figures out how to make that happen.
Play video starting at :1:11 and follow transcript1:11
Kubernetes makes it easy to run containerized applications like the one we built in the last lesson, but how do you get a Kubernetes cluster?
Play video starting at :1:21 and follow transcript1:21
You can always build one yourself on your own hardware, or in any environment that provides virtual machines, but that's work. 
- And if you've built it yourself, you have to maintain it. 
- That's even more toil.
Play video starting at :1:36 and follow transcript1:36
Because that effort is not always a valuable use of your time, Google Cloud provides Kubernetes Engine, which is Kubernetes as a managed service in the cloud. 
- You can create a Kubernetes cluster with Kubernetes Engine using the GCP console or the g-cloud command that's provided by the Cloud SDK.
Play video starting at :1:59 and follow transcript1:59
GKE clusters can be customized, and they support different machine types, numbers of nodes and network settings. 
- Here's a sample command for building a Kubernetes cluster using GKE. 
- gcloud container clusters create k1. 
- When this command completes, you will have a cluster called K1, complete, configured and ready to go. 
- You can check its status in the GCP console. 
- Whenever Kubernetes deploys a container or a set of related containers, it does so inside an abstraction called a pod. 
- A pod is the smallest deployable unit in Kubernetes. 
- Think of a pod as if it were a running process on your cluster. 
- It could be one component of your application or even an entire application.
Play video starting at :2:52 and follow transcript2:52
It's common to have only one container per pod. 
- But if you have multiple containers with a hard dependency, you can package them into a single pod. 
- They'll automatically share networking and they can have disk storage volumes in common. 
- Each pod in Kubernetes gets a unique IP address and set of ports for your containers. 
- Because containers inside a pod can communicate with each other using the localhost network interface, they don't know or care which nodes they're deployed on. 
- One way to run a container in a pod in Kubernetes is to use the kubectl run command. 
- We'll learn a better way later in this module, but this gets you started quickly. 
- Running the kubectl run command starts a deployment with a container running a pod. 
- In this example, the container running inside the pod is an image of the popular nginx open source web server. 
- The kubectl command is smart enough to fetch an image of nginx of the version we request from a container registry. 
- So what is a deployment? A deployment represents a group of replicas of the same pod. 
- It keeps your pods running even if a node on which some of them run on fails. 
- You can use a deployment to contain a component of your application or even the entire application. 
- In this case, it's the nginx web server. 
- To see the running nginx pods, run the command kubectl get pods. 
- By default, pods in a deployment or only accessible inside your cluster, but what if you want people on the Internet to be able to access the content in your nginx web server? To make the pods in your deployment publicly available, you can connect a load balancer to it by running the kubectl expose command. 
- Kubernetes then creates a service with a fixed IP address for your pods. 
- A service is the fundamental way Kubernetes represents load balancing. 
- To be specific, you requested Kubernetes to attach an external load balancer with a public IP address to your service so that others outside the cluster can access it. 
- In GKE, this kind of load balancer is created as a network load balancer. 
- This is one of the managed load balancing services that Compute Engine makes available to virtual machines. 
- GKE makes it easy to use it with containers. 
- Any client that hits that IP address will be routed to a pod behind the service. 
- In this case, there is only one pod, your simple nginx pod. 
- So what exactly is a service? A service groups a set of pods together and provides a stable endpoint for them. 
- In our case, a public IP address managed by a network load balancer, although there are other choices. 
- But why do you need a service? Why not just use pods' IP addresses directly? Suppose instead your application consisted of a front end and a back end. 
- Couldn't the front end just access the back end using those pods' internal IP addresses without the need for a service? Yes, but it would be a management problem. 
- As deployments create and destroy pods, pods get their own IP addresses, but those addresses don't remain stable over time. 
- Services provide that stable endpoint you need. 
- As you learn more about Kubernetes, you'll discover other service types that are suitable for internal application back ends. 
- The kubectl get services command shows you your service's public IP address. 
- Clients can use this address to hit the nginx container remotely. 
- What if you need more power? To scale a deployment, run the kubectl scale command. 
- Now our deployment has 3 nginx web servers, but they're all behind the service and they're all available through one fixed IP address. 
- You could also use auto scaling with all kinds of useful parameters. 
- For example, here's how to auto scale a deployment based on CPU usage. 
- In the command shown, you specify a minimum number of pods, 10, a maximum number of pods, 15, and the criteria for scaling up. 
- In this case, Kubernetes will scale up the number of pods when CPU usage hits 80% of capacity. 
- So far, I've shown you how to run imperative commands like expose and scale. 
- This works well to learn and test Kubernetes step by step, but the real strength of Kubernetes comes when you work in a declarative way. 
- Instead of issuing commands, you provide a configuration file that tells Kubernetes what you want your desired state to look like and Kubernetes figures out how to do it. 
- These configuration files then become your management tools. 
- To make a change, edit the file and then present the changed version to Kubernetes. 
- The command on the slide is one way we could get a starting point for one of these files based on the work we've already done. 
- That command's output would look something like this. 
- These files are intimidating the first time you see them because they're long and they contain syntax you don't yet understand. 
- But with a little familiarity, they're easy to work with. 
- And you can save them in a version control system to keep track of the changes you made to your infrastructure. 
- In this case, the deployment configuration file declares that you want 3 replicas of your nginx pod. 
- It defines a selector field, so your deployment knows how to group specific pods as replicas. 
- It works because all of those specific pods share a label. 
- Their app is tagged as nginx. 
- To illustrate the flexibility of this declarative method, in order to run 5 replicas instead of 3, all you need to do is edit the deployment config file, changing 3 to 5. 
- And then run the kubectl apply command to use the updated config file. 
- Now use the kubectl get replicasets command to view your replicas and see their updated state. 
- Then use the kubectl get pods command to watch the pods come online. 
- In this case, all 5 are ready and running. 
- Finally, let's check the deployments to make sure the proper number of replicas are running using kubectl get deployments. 
- In this case, all 5 pod replicas are available. 
- And clients can still hit your endpoint, just like before. 
- The kubectl get services command confirms that the external IP of the service is unaffected. 
- Now you have 5 copies of your nginx pod running in GKE, and you have a single service that's proxying the traffic to all 5 pods. 
- This technique allows you to share the load and scale your service in Kubernetes. 
- Remember the Python application you containerized in the previous lesson? You could have substituted it in place of nginx and used all the same tools to deploy and scale it too. 
- The last question we will answer is, what happens when you want to update the version of your application? You will definitely want to update your container and get the new code out in front of your users as soon as possible, but it could be risky to roll out all those changes at once. 
- You do not want your users to experience downtime while your application rebuilds and redeploys. 
- That's why one attribute of a deployment is its update strategy. 
- Here's an example, a rolling update. 
- When you choose a rolling update for a deployment and then give it a new version of the software that it manages, Kubernetes will create pods of the new version one-by-one, waiting for each new version pod to become available before destroying one of the old version pods. 
- Rolling updates are a quick way to push out a new version of your application while still sparing your users from experiencing downtime.

## Introduction to Hybrid and Multi-Cloud Computing (Anthos)

Now that you understand containers, let's take that understanding a step further and talk about using them in a modern hybrid cloud on multi-cloud architecture. 
- But before we do that, let's have a quick look at a typical on-premises distributed systems architecture. 
- Which is how business is traditionally made their enterprise computing needs before cloud computing. 
- As you may know, most enterprise scale applications are designed as distributed systems. 
- Spreading the computing workload required to provide services over two or more network servers. 
- Over the past few years, containers have become a popular way to break these workloads down into microservices, so they can be more easily maintained and expanded. 
- Traditionally, these Enterprise systems and their workloads, containerized or not, have been housed on-premises, which means they're housed on a set of high-capacity servers running somewhere within the company's network or within a company own data center. 
- When an application's computing needs begin to outstrip its available computing resources, a company using on-premises systems would need to procure more powerful servers. 
- Install them on the company network after any necessary network changes or expansions. 
- Configure the new servers and finally load the application and it's dependencies onto the new servers before resource bottlenecks could be resolved. 
- The time required to complete an on-premises upgrade of this kind could be anywhere from several months to one or more years. 
- It may also be quite costly, especially when you consider the useful lifespan of the average server is only three to five years. 
- But, what if you need more computing power now, not months from now? What if your company wants to begin to relocate some workloads away from on-premises to the Cloud to take advantage of lower cost and higher availability, but is unwilling or unable to move the enterprise application from the on-premises network? What if you want to use specialized products and services that only available in the Cloud? This is where a modern hybrid or multi-cloud architecture can help. 
- To summarize, it allows you to keep parts of your systems infrastructure on-premises while moving other parts to the Cloud, creating an environment that is uniquely suited to your company's needs. 
- Move only specific workloads to the Cloud at your own pace because a full scale migration is not required for it to work. 
- Take advantage of the flexibility, scalability, and lower computing costs offered by cloud services for running the workloads you decide to migrate. 
- Add specialized services such as machine learning, content caching, data analysis, long-term storage, and IoT to your computing resources tool kit. 
- You may have heard a lot of discussions recently concerning the adoption of hybrid architecture for powering distributed systems and services. 
- You may have even heard discussions of Google's answer to modern hybrid and multi-cloud distributed systems and service management called Anthos. 
- But, what is Anthos? Anthos is a hybrid and multi-cloud solution powered by the latest innovations in distributed systems, and service management software from Google. 
- The Anthos framework rests on Kubernetes and Google Kubernetes engine deployed on-prem. 
- Which provides the foundation for an architecture that is fully integrated with centralized management through a central control plane that supports policy based application lifecycle delivery across hybrid and multi-cloud environments. 
- Anthos also provides a rich set of tools for monitoring and maintaining the consistency of your applications across all of your network, whether on-premises, in the Cloud, or in multiple clouds. 
- Let's take a deeper look at this framework as we build a modern hybrid infrastructure stack step by step with Anthos. 
- First, let's look at Google Kubernetes Engine on the Cloud site of your hybrid network. 
- Google Kubernetes Engine is a managed production-ready environment for deploying containerized applications. 
- Operates seamlessly with high availability and an SLA. 
- Runs certified Kubernetes ensuring portability across clouds and on-premises. 
- Includes auto-node repair, and auto-upgrade, and auto-scaling. 
- Uses regional clusters for high availability with multiple masters. 
- Node storage replication across multiple zones. 
- This is as of October 2019, the number of zones is three. 
- Its counterpart on the on-premises side of a hybrid network is Google Kubernetes Engine deployed on-prem. 
- GKE deployed on-prem is a turn-key production-grade conformed version of Kubernetes with the best practice configuration already pre-loaded. 
- Provides an easy upgrade path to the latest Kubernetes releases that have been validated and tested by Google. 
- Provides access to container services on Google Cloud platform, such as Cloud build, container registry, audit logging, and more. 
- It also integrates with Istio, Knative and Marketplace Solutions. 
- Ensures a consistent Kubernetes version and experience across Cloud and on-premises environments. 
- As mentioned, both Google Kubernetes Engine in the Cloud and Google Kubernetes Engine deployed on-premises integrate with Marketplace, so that all of the clusters in your network, whether on-remises or in the Cloud, have access to the same repository of containerized applications. 
- This allows you to use the same configurations on both the sides of the network, reducing the time spent developing applications. 
- It's like right ones replicate anywhere and maintaining conformity between your clusters. 
- Enterprise applications may use hundreds of microservices to handle computing workloads. 
- Keeping track of all of these services and monitoring their health can quickly become a challenge. 
- Anthos, an Istio Open Source service mesh take all of these guesswork out of managing and securing your microservices. 
- These service mesh layers communicate across the hybrid network using Cloud interconnect, as shown to sync and pass their data. 
- Stackdriver is the built-in logging and monitoring solution for Google Cloud. 
- Stackdriver offers a fully managed logging, metrics collection, monitoring dashboarding, and alerting solution that watches all sides of your hybrid on multi-cloud network. 
- Stackdriver is the ideal solution for customers wanting a single easy to configure powerful cloud-based observability solution, that also gives you a single pane of glass dashboard to monitor all of your environments. 
- Lastly, Anthos Configuration Management provides a single source of truth for your clusters configuration. 
- That source of truth is kept in the policy repository, which is actually a git repository. 
- In this illustration, this repository is happen to be located on-premises, but it can also be hosted in the Cloud. 
- The Anthos Configuration Management agents use the policy repository to enforce configurations locally in each environment, managing the complexity of owning clusters across environments. 
- Anthos Configuration Management also provides administrators and developers the ability to deploy code changes with a single repository commit. 
- And the option to implement configuration inheritance, by using namespaces. 
- If you would like to learn more about Anthos, here are some resources to get you started.