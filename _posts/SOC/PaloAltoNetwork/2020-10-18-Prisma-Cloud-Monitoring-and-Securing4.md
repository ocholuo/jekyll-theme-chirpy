---
title: Palo Alto Networks - Prisma Cloud - 4
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

# Prisma Cloud - Investigating Security Issues with RQL

--


Prisma Cloud helps visualize entire cloud infrastructure and provides insights into security and compliance risks and provides you with a proprietary query language called RQL, `Resource Query Language`

## RQL Core Concepts

Prisma Cloud helps connect the dots between configuration, user activity, and network traffic data, to have the context necessary to define appropriate policies and create alert rules.

Insights of RQL
- structured query language
- to discover, detect, and respond to security incidents in cloud environment.
- easy to use and extensible.
- Helps administrators visualize their entire cloud infrastructure and provides insights into security and compliance risks
- Connects `configuration, user activity, and network traffic data` to **define appropriate policies and create alert rules**

Types of RQL Constructs
- `Config queries`
  - provide a deeper understanding of resource configurations and vulnerabilities in the cloud environment.
- `Event queries`
  - to search and `audit console and API access events` in the cloud environment.
- `Network queries`
  - to `monitor network traffic to and from` assets deployed in the cloud environment, to find previously unidentified network security risks.



<kbd>Demo: Prisma Cloud Investigate</kbd>

![Screen Shot 2020-10-22 at 15.58.50](https://i.imgur.com/J8IMB4S.png)

![Screen Shot 2020-10-22 at 15.59.42](https://i.imgur.com/ZwFm4nG.png)

![Screen Shot 2020-10-22 at 16.00.06](https://i.imgur.com/xHHwDwO.png)

![Screen Shot 2020-10-22 at 16.00.43](https://i.imgur.com/3MSjMbV.png)


---

## Config Query

Prisma Cloud ingests various services and associated configuration data from AWS, Azure, GCP, and Alibaba cloud services.

Features of Config Queries
- Retrieve
  - Retrieve resource information and identify misconfigurations
- Gain
  - Gain operational insights
- Identify
  - Identify policy and compliance violations


Config Query Options
- When select `“config where”` for query, then have a number of choices available to start building your query.
- `api.name​`
  - Cloud APIs are integral to the cloud platform.
  - to identify a specific configuration aspect of the resource.
- `cloud.type`
  - to narrow queries to a cloud type
- `cloud.service`
  - to narrow queries to a cloud service
- `cloud.account`
  - to specify one or more cloud accounts
- `cloud.region`
  - to narrow the cloud region
- `finding.severity` and `finding.type`
  - to identify host related security findings.
  - This option requires data that is ingested from third-party platforms that have been integrated with Prisma Cloud.


<kbd>Demo: RQL Config Query</kbd>

![Screen Shot 2020-10-22 at 16.07.19](https://i.imgur.com/VJgv8x5.png)


<kbd>Demo: Investigate Config Query Resources</kbd>

![Screen Shot 2020-10-22 at 16.08.11](https://i.imgur.com/0T4GIZj.png)

![Screen Shot 2020-10-22 at 16.08.45](https://i.imgur.com/78wlwUp.png)

![Screen Shot 2020-10-22 at 16.09.15](https://i.imgur.com/X5okbVB.png)

![Screen Shot 2020-10-22 at 16.09.47](https://i.imgur.com/EWDXSuE.png)

![Screen Shot 2020-10-22 at 16.09.59](https://i.imgur.com/2QwhsnS.png)

---

## Event Query

Event queries can be used to determine all root user activity without MFA, look for stolen access keys, and find account compromises.

Features of Event Queries
- Investigate
  - Detects and investigates console and API access
- Monitor
  - Monitors and gains insight into privileged activities
- Detect
  - Detects account compromise and unusual user behavior in the cloud environment

Event Query Options
- `cloud.account, cloud.region, cloud.service, cloud.type`
  - narrow the scope of the query
- `crud`
  - to search for users or entities who performed `create, read, update, or delete` operations.
- `ip`
  - to specify an IP address
- `json.rule`
  - to specify a json rule in the query
- `operation`
  - n action performed by users on resources.
  - If an operation is specified, Prisma Cloud will offer a list of matches to the operation criteria.
- `user`
  - to identify a specific user or users


<kbd>Demo: RQL Event Query</kbd>

![Screen Shot 2020-10-22 at 16.15.29](https://i.imgur.com/QSsIsBB.png)

![Screen Shot 2020-10-22 at 16.16.06](https://i.imgur.com/ksjdzsB.png)


---

## Network Query

Network queries can be used to discover network security risks and is currently supported only for AWS, Azure, and GCP cloud accounts.

Features of Network Queries
- Environment
  - Customers can query network events in their cloud environments.
- Detect
  - detect internet exposures and potential data exfiltration attempts
- Discover
  - discover network traffic patterns and security risks.


Network Query Options
- RQL network queries can be used to determine all Internet traffic hitting sensitive workloads such as databases, and instances used to mine crypto currencies.
- `bytes >`
- cloud.account
- cloud.region
- destination or source IP, port, public network, resource IN/ resource where or state
- packets >
- protocol


<kbd>Demo: RQL Network Query</kbd>

![Screen Shot 2020-10-22 at 16.18.44](https://i.imgur.com/JNxu9pI.png)


<kbd>Demo: Investigate Network Query Resources</kbd>

![Screen Shot 2020-10-22 at 16.28.19](https://i.imgur.com/KDxBuAU.png)

![Screen Shot 2020-10-22 at 16.28.51](https://i.imgur.com/v2rVeLX.png)

![Screen Shot 2020-10-22 at 16.29.06](https://i.imgur.com/svDOcS2.png)

![Screen Shot 2020-10-22 at 16.29.55](https://i.imgur.com/SSR2ui3.png)


---

## Custom Policy

create new policies using RQL queries that you develop.
- create a custom policy and also use a saved search query in the custom policy.
- RQL can be used to investigate issues as they occur.
- The queries that are developed in the investigations can be saved.
- Saved queries can also be used to develop new custom policies.


<kbd>Demo: RQL Saved Query</kbd>

![Screen Shot 2020-10-22 at 16.50.41](https://i.imgur.com/tgIDutc.png)


---

## Investigate from Alerts

investigate a security policy violation from the Alerts page.

Investigation Methods
- to initiate an investigation of a security incident in Prisma Cloud.
- Use `RQL queries` from the Investigate page
- Launch an investigation from the Alert details page


<kbd>Demo: Investigate from Alerts</kbd>

![Screen Shot 2020-10-22 at 16.52.53](https://i.imgur.com/euBJle2.png)

![Screen Shot 2020-10-22 at 16.53.19](https://i.imgur.com/wFQPQOp.png)

![Screen Shot 2020-10-22 at 16.53.42](https://i.imgur.com/PBxQrg4.png)

![Screen Shot 2020-10-22 at 16.54.27](https://i.imgur.com/rSxBUb1.png)

---

## Knowledge Check


Which two types of queries does RQL support? (Choose two.)
- Audit event
- Network


A config query can start with which two expressions? (Choose two.)
- `config where cloud.region =`
- `config where api.name =`


Which option shows how to use an alert to investigate a resource with RQL?
- Click the alert, hover on the Resource Name, and click the Investigate button.

















.
