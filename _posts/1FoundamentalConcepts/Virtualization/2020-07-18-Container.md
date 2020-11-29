


# Amazon elastic container service ECS
￼
![Screen Shot 2020-05-06 at 20.40.15](https://i.imgur.com/VHbtuiM.png)

![Screen Shot 2020-05-06 at 20.40.36](https://i.imgur.com/RLZmFWo.png)


---


## EC2 Mode and Fargate Mode


![Screen Shot 2020-05-06 at 20.43.54](https://i.imgur.com/YlTk8b0.png)

![Screen Shot 2020-07-27 at 10.21.57](https://i.imgur.com/LceUJaL.png)

![Screen Shot 2020-07-27 at 10.22.57](https://i.imgur.com/I8tPJUm.png)

---

## ECS
Amazon EC2 Container Service:  **Use API calls to run container-enabled applications**
- a highly scalable, high-performance container orchestration service
- supports Docker containers.

- easily run applications on a managed cluster of EC2 instances.
  - `eliminates the install, operate, and scale of own cluster management infrastructure`.
    - more fine-grained control for customer application architectures.

  - `Using API calls`
    - launch and stop container-enabled applications,
    - query the complete state of clusters,
    - access many familiar features like security groups, LB, EBS volumes and IAM roles.

  - Possible to use `Elastic Beanstalk` to handle the provisioning of an Amazon ECS cluster, balancing load, auto-scaling, monitoring, and placing your containers across your cluster.


- to schedule the placement of containers across clusters based on resource needs and availability requirements.

- Amazon `ECS launch type`
  - determines the type of infrastructure on which your tasks and services are hosted.

- no additional charge for ECS.
  - pay for AWS resources (e.g. EC2 instances or EBS volumes) you create to store and run your application.

- `can associate a service on ECS to an Application Load Balancer (ALB) `for the Elastic Load Balancing (ELB) service.
  - The ALB supports a target group that contains a set of instance ports.
  - can specify a dynamic port in the ECS task definition, gives the container an unused port when it is scheduled on the EC2 instance.

- ECS provides Blox
  - a collection of open source projects for container management and orchestration.
  - Blox makes it easy to consume events from ECS, store the cluster state locally and query the local data store through APIs.

- can use any AMI that meets the Amazon ECS AMI specification.

---

## ECS VS EKS
- the `Elastic Container Service for Kubernetes (Amazon EKS)`
  - to deploy, manage, and scale containerized applications using Kubernetes on AWS.

![Pasted Graphic](https://i.imgur.com/R2kWxKb.jpg)


---


## ECS LAUNCH TYPES
- determines the type of infrastructure on which your tasks and services are hosted.
- 2 launch types
  - Fargate Launch Type
    - a serverless infrastructure managed by AWS.
    - run containerized applications without the provision and manage the backend infrastructure.
    - Just register task definition and Fargate launches the container for you.
    - only supports container images hosted on `Elastic Container Registry (ECR) or Docker Hub`.
  - EC2 Launch Type
    - run containerized applications on a cluster of EC2 instances that you manage.
    - `Private repositories` are only supported by the EC2 Launch Type.

￼![Amazon-ECS-EC2-vs-Fargate-1024x583](https://i.imgur.com/6gsSsaF.jpg)


---

## ECS TERMS

IMAGES
- Containers are created from a read-only template called an image which has the instructions for creating a Docker container.
- Images are built from a Dockerfile.
  - Only Docker containers are currently supported.
- An image contains the instructions for creating a Docker container.
- Images are stored in a registry such as DockerHub or AWS Elastic Container Registry (ECR).
- ECR is a managed AWS Docker registry service that is secure, scalable and reliable.
- ECR supports private Docker repositories with resource-based permissions using AWS IAM in order to access repositories and images.
- Developers can use the Docker CLI to push, pull and manage images.


TASKS
- A `task definition` is required to run Docker containers in Amazon ECS.
- A task is a `single running copy of any containers defined by a task definition`.
- a text file in JSON format that describes one or more containers, up to a maximum of 10.
  - Task definitions use Docker images to launch containers.
  - specify the number of tasks to run (i.e. the number of containers).
- Some of the parameters can specify in a task definition include:
  - Which Docker images to use with the containers in your task.
  - How much CPU and memory to use with each container.
  - Whether containers are linked together in a task.
  - The Docker networking mode to use for the containers in your task.
  - What (if any) ports from the container are mapped to the host container instances.
  - Whether the task should continue if the container finished or fails.
  - The commands the container should run when it is started.
  - Environment variables that should be passed to the container when it starts.
  - Data volumes that should be used with the containers in the task.
  - IAM role the task should use for permissions.
- You can use Amazon ECS Run task to run one or more tasks once.


CLUSTERS
- ECS Clusters are a logical grouping of container instances the you can place tasks on.
- A default cluster is created but you can then create multiple clusters to separate resources.
- ECS allows the definition of a specified number (desired count) of tasks to run in the cluster.
- Clusters can contain tasks using the Fargate and EC2 launch type.
- For clusters with the EC2 launch type
  - clusters can contain different container instance types.
- Each container instance may only be part of one cluster at a time.
- “Services” provide auto-scaling functions for ECS.
- Clusters are region specific.
- You can create IAM policies for your clusters to allow or restrict users’ access to specific clusters.

SERVICE SCHEDULER
- You can schedule ECS using Service Scheduler and Custom Scheduler.
- Ensures that the specified number of tasks are constantly running and reschedules tasks when a task fails.
- Can ensure tasks are registered against an ELB.

CUSTOM SCHEDULER
- You can create your own schedulers to meet business needs.
- Leverage third party schedulers such as Blox.
- The Amazon ECS schedulers leverage the same cluster state information provided by the Amazon ECS API to make appropriate placement decisions.

ECS CONTAINER AGENT
- The ECS container agent allows container instances to connect to the cluster.
- The container agent runs on each infrastructure resource on an ECS cluster.
- The ECS container agent is included in the Amazon ECS optimized AMI and can also be installed on any EC2 instance that supports the ECS specification (only supported on EC2 instances).
- Linux and Windows based.
- For non-AWS Linux instances to be used on AWS you must manually install the ECS container agent.


**AUTO SCALING**
- `Service Auto Scaling`
  - ECS service can optionally be configured to use Service Auto Scaling to adjust the desired task count up or down automatically.
  - Service Auto Scaling leverages the Application Auto Scaling service to provide this functionality.
  - ECS Service Auto Scaling supports the following types of scaling policies:
    - `Target Tracking Scaling Policies`
      - Increase or decrease the number of tasks that your service runs based on a target value for a specific CloudWatch metric.
      - similar to the way that your thermostat maintains the temperature of your home. 
      - You select temperature and the thermostat does the rest.
    - `Step Scaling Policies`
      - Increase or decrease the number of tasks that your service runs in response to CloudWatch alarms.
      - Step scaling is based on a set of scaling adjustments, known as step adjustments, which vary based on the size of the alarm breach.
- `Cluster Auto Scaling`
  - new feature released in December 2019. 
  - Uses a new ECS resource type called a Capacity Provider.
  - A Capacity Provider can be associated with an EC2 Auto Scaling Group (ASG).
  - When you associate an ECS Capacity Provider with an ASG and add the Capacity Provider to an ECS cluster, the cluster can now scale your ASG automatically by using two new features of ECS:
    - Managed scaling, with an automatically-created scaling policy on your ASG, and a new scaling metric (Capacity Provider Reservation) that the scaling policy uses; and
    - Managed instance termination protection, which enables container-aware termination of instances in the ASG when scale-in happens.
  - Prefer to learn by doing? Watch the AWS Hands-On Labs video tutorial below to learn how to create an Amazon ECS cluster and a task running WordPress. We’ll show you how to do this using a combination of the AWS ECS CLI and the console.



**Security/SLA**
- EC2 instances use an IAM role to access ECS.
- IAM can be used to control access at the container level using IAM roles.
- The container agent makes calls to the ECS API on your behalf through the applied IAM roles and policies.
- You need to apply IAM roles to container instances before they are launched (EC2 launch type).
- AWS recommend limiting the permissions that are assigned to the container instance’s IAM roles.
- Assign extra permissions to tasks through separate IAM roles (IAM Roles for Tasks).
- ECS tasks use an IAM role to access services and resources.
- Security groups attach at the instance or container level.
- You have root level access to the operating system of the EC2 instances.
- The Compute SLA guarantees a Monthly Uptime Percentage of at least 99.99% for Amazon ECS.


LIMITS
Soft limits (default):
- Clusters per region = 1000.
- Instances per cluster = 1000.
- Services per cluster = 500.
Hard limits:
- One load balancer per service.
- 1000 tasks per service (the “desired” count).
- Max 10 containers per task definition.
- Max 10 tasks per instance (host).


PRICING
EC2 Launch Type:
- No additional charge – you pay for the EC2 resources you launch including instances, EBS volumes and load balancers
Fargate:
- You pay for the vCPU and memory allocated to the containers you run.
