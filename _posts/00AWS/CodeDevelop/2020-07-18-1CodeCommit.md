---
title: AWS - CodeDevelop - CodeCommit
date: 2020-07-18 11:11:11 -0400
categories: [00AWS, CodeDevelop]
tags: [AWS]
toc: true
image:
---

[toc]

---

# CodeCommit

![CodeCommit](https://i.imgur.com/ENCpW6N.png)

- a <font color=red> version control service </font> hosted by Amazon Web Services
  - tracks and manages code changes
  - Maintains version history
- <font color=red> Centralized Code Repository </font>
  - use to privately store and manage assets (such as documents, source code, and binary files) in the cloud.
  - place to store source code, binaries, libraries, images, HTML files ...
  - based on Git
- <font color=red> Enables Collaboration </font>
  - manages updates from multiple users.

---

## basic
- secure, highly scalable, managed source control service that hosts private Git repositories.
- eliminates the need to manage your own source control system or scaling the infrastructure.
- use CodeCommit to store anything from code to binaries.
- It supports the standard functionality of Git, works seamlessly with your existing Git-based tools.

With CodeCommit, you can:

1. <font color=red> fully managed service </font> hosted by AWS.
   - high service availability and durability and eliminates the administrative overhead of managing your own hardware and software.
   - no hardware to provision and scale and no server software to install, configure, and update.

2. <font color=red> Store code securely </font>
   - Encryption
     - CodeCommit repositories are auto encrypted at rest and in transit through AWS Key Management Service (AWS KMS) using customer-specific keys.
     - You can transfer your files to and from AWS CodeCommit using HTTPS or SSH, as you prefer.
   - Access Control
     - AWS CodeCommit uses AWS Identity and Access Management to control and monitor who can access the data and how, when, and where they can access it. 
     - CodeCommit also helps you monitor your repositories via `AWS CloudTrail and AWS CloudWatch`.


3. <font color=red> Work collaboratively on code </font>
   - CodeCommit repositories <font color=blue> support pull requests </font>
     - provide a mechanism to request code reviews and discuss code with collaborators. 
   - users can review and comment on each other's code changes before merging them to branches;
      - easily <font color=blue> commit, branch, and merge </font> the code to easily maintain control of team’s projects.
   - notifications that automatically send emails to users about pull requests and comments; and more.

4. <font color=red> Easily scale the version control projects </font>
   - CodeCommit repositories can scale up to meet your development needs.
   - The service can handle repositories with large numbers of files or branches, large file sizes, and lengthy revision histories.

5. <font color=red> Store anything, anytime </font>
   - no limit on the size of repositories or the file types to store.

6. Integrate with other AWS and third-party services.
   - CodeCommit keeps your repositories close to your other production resources in the AWS Cloud, which helps increase the speed and frequency of your development lifecycle.
   - It is integrated with IAM and can be used with other AWS services and in parallel with other repositories.

7. <font color=red> Easy Access and Integration </font>
   - Easily migrate files from other remote repositories.
     - migrate to CodeCommit from any Git-based repository.
   - use the AWS Management Console, AWS CLI, and AWS SDKs to manage your repositories. 
   - can also use Git commands or Git graphical tools to interact with your repository source files. 
     - AWS CodeCommit supports all Git commands and works with your existing Git tools. 
     - You can integrate with your development environment plugins or continuous integration/continuous delivery systems.


8. <font color=red> High Availability and Durability </font>
   - AWS CodeCommit stores your repositories in Amazon S3 and Amazon DynamoDB.  
   - encrypted data is redundantly stored across multiple facilities.
   - increases the availability and durability of the repository data.
   - Unlimited Repositories
     - create as many repositories as you need
     - up to 1,000 repositories by default and no limits upon request. 
     - You can store and version any kind of file, including application assets such as images and libraries alongside your code.


9. Notifications and Custom Scripts
   - receive notifications for events impacting your repositories. 
   - Notifications will come in the form of Amazon SNS notifications. 
   - Each notification will include a status message as well as a link to the resources whose event generated that notification. 
   - Additionally, using AWS CodeCommit repository triggers, you can send notifications and create HTTP webhooks with Amazon SNS or invoke AWS Lambda functions in response to the repository events you choose.

---


## work flow

- similar to Git-based repositories
- provides a console for easy creation of repositories and the listing of existing repositories and branches.
- find information about a repository and clone it to their computer, creating a local repo where they can make changes and then push them to the CodeCommit repository.
- Users can work from
  - the command line on local machines
  - or use a GUI-based editor.

![arc-workflow](https://i.imgur.com/dCtKMc0.png)


![Screen Shot 2020-12-27 at 03.33.55](https://i.imgur.com/NDqsZwC.png)


![Screen Shot 2020-12-27 at 03.34.24](https://i.imgur.com/a99WuOp.png)


![Screen Shot 2020-12-27 at 03.34.47](https://i.imgur.com/Tb6tPsF.png)












.
