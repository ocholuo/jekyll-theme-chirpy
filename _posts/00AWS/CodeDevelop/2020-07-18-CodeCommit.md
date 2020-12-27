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

- a version control service hosted by Amazon Web Services
  - tracks and manages code changes
  - Maintains version history
- Centralized Code Repository
  - use to privately store and manage assets (such as documents, source code, and binary files) in the cloud.
  - place to store source code, binaries, libraries, images, HTML files ... 
  - based on Git
- Enables Collaboration
  - manages updates from multiple users.

![Screen Shot 2020-12-27 at 03.18.10](https://i.imgur.com/rr6Wma7.png)

---

## Introducing CodeCommit
- secure, highly scalable, managed source control service that hosts private Git repositories.
- eliminates the need to manage your own source control system or scaling the infrastructure.
- You can use CodeCommit to store anything from code to binaries.
- It supports the standard functionality of Git, so it works seamlessly with your existing Git-based tools.

With CodeCommit, you can:

1. fully managed service hosted by AWS.
   - high service availability and durability and eliminates the administrative overhead of managing your own hardware and software.
   - no hardware to provision and scale and no server software to install, configure, and update.

2. Store your code securely.
   - CodeCommit repositories are encrypted at rest as well as in transit.

3. Work collaboratively on code.
   - CodeCommit repositories <font color=blue> support pull requests </font>, where users can review and comment on each other's code changes before merging them to branches;
   - notifications that automatically send emails to users about pull requests and comments; and more.

4. Easily scale the version control projects.
   - CodeCommit repositories can scale up to meet your development needs.
   - The service can handle repositories with large numbers of files or branches, large file sizes, and lengthy revision histories.

5. Store anything, anytime.
   - no limit on the size of repositories or the file types to store.

6. Integrate with other AWS and third-party services.
   - CodeCommit keeps your repositories close to your other production resources in the AWS Cloud, which helps increase the speed and frequency of your development lifecycle.
   - It is integrated with IAM and can be used with other AWS services and in parallel with other repositories.


7. Easily migrate files from other remote repositories.
   - You can migrate to CodeCommit from any Git-based repository.

8. Use the Git tools you already know.
   - supports Git commands as well as its own AWS CLI commands and APIs.

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
