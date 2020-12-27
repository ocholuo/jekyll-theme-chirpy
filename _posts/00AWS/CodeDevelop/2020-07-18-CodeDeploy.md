---
title: AWS - CodeDevelop - CodeDeploy
date: 2020-07-18 11:11:11 -0400
categories: [00AWS, CodeDevelop]
tags: [AWS]
toc: true
image:
---

[toc]

---

# CodeDeploy

- a deployment service 
- automates application deployments to EC2 instances, on-premises instances, serverless Lambda functions, or ECS services.
- can deploy a nearly unlimited variety of application content, including:
  - Code
  - Serverless AWS Lambda functions
    - do not need to make changes to your existing code before you can use CodeDeploy.
  - Web and configuration files
  - Executables
  - Packages
  - Scripts
  - Multimedia files


- CodeDeploy can deploy application content that runs on a server and is stored in S3 buckets, GitHub repositories, or Bitbucket repositories. 

- CodeDeploy makes it easier for you to:
  - Rapidly release new features.
  - Update AWS Lambda function versions.
  - Avoid downtime during application deployment.
  - Handle the complexity of updating the applications, without many of the risks associated with error-prone manual deployments.

- The service scales with the infrastructure so can easily deploy to one instance or thousands.
- CodeDeploy works with various systems for `configuration management`, `source control`, `continuous integration, continuous delivery`, and `continuous deployment`.
- The CodeDeploy console also provides a way to quickly search for your resources, such as repositories, build projects, deployment applications, and pipelines. 
  - Choose `Go to resource` or press the / key, and then type the name of the resource. 
  - Any matches appear in the list. 
  - Searches are case insensitive.
  - only see resources that you have permissions to view. 


---


Topics

Blue/Green deployment on an AWS Lambda compute platform
- If you're using the AWS Lambda compute platform, you must choose one of the following deployment configuration types to specify how traffic is shifted from the `original AWS Lambda function version` to the `new AWS Lambda function version`:
  - **Canary** 金丝雀 : 
    - Traffic is `shifted in two increments`. 
    - You can choose from **predefined canary options** that `specify the percentage of traffic shifted to the updated Lambda function version` in the first increment and the interval, in minutes, before the remaining traffic is shifted in the second increment.
    - split traffic, `sending a small percentage of the traffic to the new version of your application`.
  - **Linear**: 
    - Traffic is shifted in `equal increments` with an `equal number of minutes` between each increment. 
    - You can choose from **predefined linear options** that specify the percentage of traffic shifted in each increment and the number of minutes between each increment.
  - **All-at-once**: 
    - All traffic is shifted from the original Lambda function to the updated Lambda function version all at once.
