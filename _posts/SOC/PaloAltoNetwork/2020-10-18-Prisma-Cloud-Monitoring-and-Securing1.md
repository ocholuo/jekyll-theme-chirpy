---
title: Palo Alto Networks - Prisma Cloud - 1
# author: Grace JyL
date: 2020-10-18 11:11:11 -0400
description:
excerpt_separator:
categories: [SOC, PaloAlto]
tags: [SOC, Prisma]
math: true
# pin: true
toc: true
image: /assets/img/note/prisma.png
---

[toc]

---

# Prisma Cloud - Onboarding and Initial Setup Note

---

## Prisma Cloud Overview

Prisma Cloud
- a cloud infrastructure security solution
- a Security Operations Center (SOC) enablement tool
- to address risks and secure workloads in a heterogeneous environment (hybrid and multi-cloud) from a single console.

Cloud Security Posture Management with Prisma Cloud

![Screen Shot 2020-10-20 at 18.12.04](https://i.imgur.com/v8I7aDF.png)

1. Comprehensive <kbd>Cloud Configuration Management Database</kbd>
   1. provide a comprehensive cloud `Configuration Management Database`, the data needed for `compliance reporting` and to address `compliance violations,` `threat detection and response`, and `data security`.
2. Integration With Third-Party Applications
   1. can also integrate with additional third-party applications for outbound alert notifications,
   2. such as Splunk, Jira, and many others.
3. Visibility, Detection, and Response
   1. a cloud native security platform
   2. provides visibility, detection, and response to security threats to `public cloud accounts`.
4. Data Collection and Aggregation
   1. It accomplishes this through data collection from the cloud accounts and aggregation of that data.
5. Deployment and Tracking
   1. dynamically discovers resources that are deployed in the cloud,
   2. and tracks historical changes to those resources for auditing and forensics purposes.
6. Ingestion Through APIs
   1. `Resource configurations, user activity, network traffic logs, and host activity and vulnerabilities data` is ingested into Prisma Cloud though the `public cloud APIs`.
7. Supported Cloud Platforms
   1. Prisma Cloud currently supports Amazon Web Services, Alibaba Cloud, Azure, and Google Cloud.
   2. Third-Party Feeds
      1. There is also support for the ingest of data from third-party platforms
      2. such as Tenable and Qualys.


Prisma Cloud Compute
- Twistlock is now branded `Prisma Cloud Compute`.
- Prisma Cloud Compute can be deployed in one of two ways.
  - **SaaS Version**
    - **Prisma Cloud Compute** has been integrated into the **Prisma Cloud SaaS security platform**
    - accessible through the <knd>Compute tab</kbd>.
  - **Self-Hosted Software**
    - **Prisma Cloud Compute Edition** can be deployed as a self-hosted application.


![Screen Shot 2020-10-20 at 18.46.55](https://i.imgur.com/LGlL7xD.png)

![Screen Shot 2020-10-20 at 18.47.42](https://i.imgur.com/tXduNRe.png)

![Screen Shot 2020-10-20 at 18.48.49](https://i.imgur.com/IixaHoC.png)

![Screen Shot 2020-10-20 at 18.49.02](https://i.imgur.com/0QurNUH.png)

![Screen Shot 2020-10-20 at 18.49.20](https://i.imgur.com/pUeOALM.png)

![Screen Shot 2020-10-20 at 18.49.35](https://i.imgur.com/n0axXSN.png)

![Screen Shot 2020-10-20 at 18.50.02](https://i.imgur.com/dqf2kTd.png)

- sys admin

![Screen Shot 2020-10-20 at 18.50.55](https://i.imgur.com/VDXLJBk.png)

![Screen Shot 2020-10-20 at 18.51.12](https://i.imgur.com/xt7gsOL.png)


---

## Onboarding Public Cloud Accounts

Prisma Cloud administrators can use the cloud account onboarding with all supported cloud platforms: AWS, Alibaba Cloud, Azure, and Google Cloud. T

the workflow provides the prerequisites and the steps required for the onboarding of various accounts.

requirements for each cloud provider.

AWS requirements

> Create a `Prisma Cloud read-only custom role` in AWS to be used to connect to your AWS environment.
> Read-write permissions are required if you would like to monitor and protect your account through auto-remediation of policy violations.
> `CloudFormation templates` are available to automate the process of creating the custom role required to add your AWS account to Prisma Cloud.
> Configure `VPC Flow Logs` in order to monitor your network traffic. Make sure the filter setting is configured for all.
> Configure the VPCs to `send Flow Log data to CloudWatch` so that it can be ingested by Prisma Cloud.
> Verify that `CloudTrail` is enabled (typically enabled by default).

![Screen Shot 2020-10-20 at 18.59.24](https://i.imgur.com/NTXuPfB.png)

![Screen Shot 2020-10-20 at 19.00.21](https://i.imgur.com/kpW3jrz.png)

![Screen Shot 2020-10-20 at 19.02.14](https://i.imgur.com/LFl5gvP.png)

![Screen Shot 2020-10-20 at 19.02.43](https://i.imgur.com/QRMMRkK.png)

![Screen Shot 2020-10-20 at 19.03.24](https://i.imgur.com/E2aa2cB.png)

Azure requirements

> Collect your Azure subscription information, which includes your and your Subscription ID and Azure Active Directory ID or Tenant ID.
> Setup `access control` for the Prisma Cloud service. Register the Prisma Cloud service in Azure by `adding the Prisma Cloud application to the Azure Active Directory`.
> Grant permissions to the Prisma Cloud application. enable permissions to monitor (read-only permission), or to monitor and protect (read-write permission).
> Configure the `Azure Network Security Groups Flow Logs` and `assign a storage account to enable Flow Log ingestion`.


GCP requirements

> In your GCP account, create a `custom role` such as Prisma cloud viewer.
> Create a `service account` and generate the required security keys.
> The service account should include the `getACL permission for read access.`
> For auto-remediation, or to write to the gcp account, `computer security admin permission` is required.
> Verify that the `Compute Engine API along with additional APIs` is defined in the documentation.
> `Associate the service account with the GCP project` that you want to monitor.
> Prisma Cloud also supports onboarding multiple GCP projects or an entire organization in a single operation.


Alibaba requirementsAlibaba

> Permissions
> Custom Policy vs. System Policy
> Create RAM Role
> Enter Prisma Cloud Account ID
> Obtain the Alibaba Cloud Resource Name (ARN)


Demo: Validating Cloud Permissions

![Screen Shot 2020-10-20 at 19.07.24](https://i.imgur.com/N1yDLjj.png)


---

## User Administration

Manage the individuals who can access instance by
1. defining them as `users` in the system.
2. Create `account groups`, and assign users to them.
3. Use `roles` to specify what different users and account groups can see and do.

**Prisma Cloud Administrators**
- different <kbd>Prisma Cloud Administrator roles</kbd>.
- Assigned Admin
  - An individual who has been assigned administrative privileges
- Perform Admin tasks
  - Can log in to the system and perform administrative tasks
- Tasks as per role
  - Tasks a user can perform are defined by their role

**Defining Administrator Roles**
- Every user is assigned a <kbd>role</kbd>
- may be one of the `default roles` included in Prisma Cloud,
- or a `custom role` that has been created by another administrator.
- <kbd>Roles</kbd> are associated with
  - <kbd>permission groups</kbd> that define what tasks the user may perform.
  - <kbd>account groups</kbd>, define the scope of cloud accounts that the user may access.

![Screen Shot 2020-10-20 at 19.13.07](https://i.imgur.com/I5YSZYs.png)


**Prisma Cloud Permission Groups**
- 4 <kbd>permission groups</kbd> predefined
- and a fifth permission group that combines the permissions of two of the predefined groups.  

1. <kbd>System Admin</kbd> :
   - Full control (read/write permissions) to the service
   - can create, edit, or delete account groups or cloud accounts.
   - Only System administrators have access to `all Settings on Prisma Cloud` and can view `audit logs` to analyze actions performed by other users who have been assigned administrative privileges.
2. <kbd>Account Group Admin</kbd> :
   - Full control and access to `all Prisma Cloud settings`
   - Read/write permissions for the cloud accounts and account groups to which they are granted access.
   - An account group administrator can only view resources deployed within the cloud accounts to which they have access. Resources deployed on other cloud accounts that Prisma Cloud monitors are excluded from the search or investigation results.
3. <kbd>Cloud Provisioning Admin</kbd> :
   - Permissions to onboard and manage cloud accounts from Prisma Cloud and the APIs
   - create and manage the account groups.
   - With this role access is limited to Settings > Cloud Accounts and Settings > Account Groups on the admin console.
4. <kbd>Account Group Read-Only</kbd> :
      - Read-only access to specified sections of Prisma Cloud
      - This role does not have permissions to modify any settings.
5. <kbd>Account and Cloud Provisioning Admin</kbd> :
   - This role combines the permissions of Account Group Admin and Cloud Provisioning Admin.


Demo: Creating a Role

![Screen Shot 2020-10-20 at 19.20.12](https://i.imgur.com/GDWQMQQ.png)

![Screen Shot 2020-10-20 at 19.21.53](https://i.imgur.com/7A9LeXr.png)

![Screen Shot 2020-10-20 at 19.22.13](https://i.imgur.com/PZ4tE01.png)


Demo: Creating a User

![Screen Shot 2020-10-20 at 19.23.10](https://i.imgur.com/dkSxKzZ.png)

![Screen Shot 2020-10-20 at 19.23.29](https://i.imgur.com/28AKllv.png)


Demo: SSO

![Screen Shot 2020-10-20 at 19.26.42](https://i.imgur.com/8C51qbY.png)

---

## General Settings

Prisma Cloud General Settings
1. <kbd>Access Key</kbd>
   1. Access keys provide a secure method
   2. to enable access to the Prisma Cloud API,
   3. and can be used with external integrations that require access to the APIs,
   4. or for automation systems that may perform operations such as remediation on Prisma Cloud.
2. <kbd>IP Whitelisting</kbd>
   1. `Alert IP Whitelisting` vs `Log IP Whitelisting`
   2. If you have internal networks within your global cloud infrastructure, AWS infrastructure for example,
   3. you can provide IP ranges in the form of a CIDR block so that those internal networks are whitelisted from some of the functions within Prisma Cloud.
   4. This is so that these addresses are not flagged as external IPs for analysis purposes.
3. <kbd>Licensing</kbd>
   1. Prisma Cloud administrators can view a resource count in a graphical view along with a resource trend.
   2. The information can be filtered by cloud account and by time range.


<kbd>Demo: Access Keys</kbd>

![Screen Shot 2020-10-20 at 19.31.26](https://i.imgur.com/ytcnZWA.png)

![Screen Shot 2020-10-20 at 19.32.07](https://i.imgur.com/5cCXJkW.png)


<kbd>Demo: IP Whitelisting</kbd>

![Screen Shot 2020-10-20 at 19.34.32](https://i.imgur.com/pyKSJM9.png)


<kbd>Demo: Licensing</kbd>

![Screen Shot 2020-10-20 at 19.35.36](https://i.imgur.com/patzSdB.png)


---

## Prisma Cloud Enterprise Settings

set the `enterprise level settings` to build standard training models for `Unusual User Activity (UEBA), alert disposition, browser, and user attribution for alerts`

![Screen Shot 2020-10-20 at 23.55.05](https://i.imgur.com/PNjubL9.png)

4 settings under the <kbd>Unusual User Activity/UEBA</kbd> panel on the bottom portion of the page.

1. <kbd>Unusual User Activity/UEBA</kbd>
   - two settings within this section.
   - <kbd>Training Model Threshold</kbd>
     - `Low, Medium, or High`
     - to define the training model that will be used for UEBA or user and entity behavior analytics.
     - Low: 25 event for 7 days
     - Medium: 100 event for 30 days
     - High: 300 event for 90 days
   - <kbd>Alert Disposition</kbd>


2. <kbd>User Idle Timeout</kbd>
   - specifies the time interval when inactive users will be logged out of the system.
   - `Custom: xx mins`


3. <kbd>Auto Enable New Default Policies of the Type</kbd>
   - allows administrators to specify policies to be enabled by default and can be configured by policy severity.
   - `Low, Medium, or High`


4. <kbd>Make Alert Dismissal Reason Mandatory</kbd>
   - on: users may dismiss an alert only after providing an informative note.


5. <kbd>Populate User Attribution In Alerts Notification<kbd>
   - User attribution tries to identify the user who made the change to a resource that resulted in a policy violation.
   - mandates that user attribution be included in the alert payloads.

---

## check

Which two types of data does Prisma Cloud provide visibility for from public cloud accounts? (Choose two.)
- resource configuration and network traffic.

Which two configuration requirements are needed when onboarding an AWS public cloud? (Choose two.)
- VPC Flow logs
- Prisma Cloud Policy and Read-Only role




.