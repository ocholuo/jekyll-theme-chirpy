---
title: Palo Alto Networks - Prisma Cloud - 6
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

# Prisma Cloud: Integrating with Third-Party Security Applications

--

## Overview of Integration Core Concepts

Prisma Cloud can be used to integrate with your existing security workflows and with the technologies you already use.


Integrations
- In Prisma Cloud, you can integrate with your existing tools to achieve compliance and manage security risks across your Amazon Web Services, Microsoft Azure, Google Cloud Platform, and Alibaba Cloud environments.
- Options
  - Prisma Cloud provides multiple out-of-the-box integration options, which can be used to integrate Prisma Cloud into existing security workflows and technologies, as and if needed.
- Support
  - Integration support is available for both data ingestion, and for outbound alert notifications.
- Ability
  - Prisma Cloud supports the ability to enable, disable, or delete integrations.
  - So if you just need to take a third party platform offline, you can disable that platform in Prisma Cloud and not have to delete it and then reintegrate the target platform over again.
- Process
  - The integration process for each application differs slightly.
  - Procedures are available in the Help Center that describe the specific steps required for each third-party platform.


<kbd>Demo: Third-Party Platform Support</kbd>

setting > integration

![Screen Shot 2020-10-22 at 18.27.25](https://i.imgur.com/s4WPi03.png)

![Screen Shot 2020-10-22 at 18.28.11](https://i.imgur.com/yaG0e6x.png)

![Screen Shot 2020-10-22 at 18.28.49](https://i.imgur.com/ZDxCxUO.png)

![Screen Shot 2020-10-22 at 18.28.58](https://i.imgur.com/tGAJRQB.png)

![Screen Shot 2020-10-22 at 18.29.09](https://i.imgur.com/ABPY4UI.png)


---

## Inbound Integrations

Prisma Cloud supports the configuration of inbound integrations for the detection and analysis of host vulnerabilities in your cloud infrastructure.

Inbound Integration Platforms
- Prisma Cloud can integrate with two platforms.
- `Qualys`
  - Qualys specializes in **vulnerability management security software**.
  - Prisma Cloud integrates with the `Qualys` platform to `ingest and visualize vulnerability data` for your resources that are deployed on the AWS and Azure cloud platforms.
- `Tenable`
  - `Tenable.io` is a cloud-hosted **Vulnerability Management solution**
  - designed to provide accurate visibility and insight about dynamic assets and vulnerabilities in changing environments like the public cloud.  
  - Prisma Cloud integrates with the `Tenable.io` platform to ingest and present vulnerability data within Prisma Cloud.


<kbd>Demo: Access Help for Inbound Integrations</kbd>

![Screen Shot 2020-10-22 at 18.33.13](https://i.imgur.com/iwEbJad.png)



<kbd>Demo: Configure an Inbound Integration</kbd>

key in `Tenable.io`

setting > integration

![Screen Shot 2020-10-22 at 18.34.43](https://i.imgur.com/Y4dlcNv.png)



<kbd>Demo: Use RQL to Identify Host Vulnerabilities</kbd>

investigate > RQL > resource > Audit trail > CVE vulnerability

![Screen Shot 2020-10-22 at 18.36.26](https://i.imgur.com/OhI2GHM.png)

![Screen Shot 2020-10-22 at 18.36.46](https://i.imgur.com/TG9UuCS.png)


---

## Outbound Integrations

Prisma Cloud provides support for the outbound integrations for adding third-party notification channels.

Outbound Integration Platforms
- Prisma Cloud can integrate with various platforms.


<kbd>AWS Security Hub</kbd>
- Provides a view of the security state in AWS and helps measure compliance with the security industry standards and best practices
- It collects security data from across AWS accounts, services, and supported third-party partners and analyzes the security trends and identifies the highest priority security issues.


<kbd>Google Cloud Security Command Center</kbd>
- Can be used as a central console for centralized visibility into security and compliance risks of cloud assets on the Google Cloud Platform
- Enable the `CSCC integration` in `alert rules` so that the notifications are sent to the CSCC console


<kbd>Cortex XSOAR</kbd>
- A Palo Alto Networks company
- Cortex XSOAR is a `comprehensive security, orchestration, and response (SOAR) platform` that combines case management, automation, and real-time collaboration for security teams.


<kbd>Jira</kbd>
- An issue tracking, ticketing, and project management tool
- can be integrated to receive Prisma Cloud alert notifications in Jira accounts.
- With Jira, notification templates can be created to configure and customize Prisma Cloud alerts. Prisma Cloud will fetch all the project settings from the Jira account.


<kbd>Microsoft Teams</kbd>
- A cloud-based team collaboration platform
- part of the Office 365 suite of applications
- used for workplace chat, video meetings, file storage, and application integration.


<kbd>PagerDuty</kbd>
- Provides alerting, on-call scheduling, escalation policies, and incident tracking to increase uptime of apps, servers, websites, and databases
- PagerDuty integration sends Prisma Cloud alert information to PagerDuty service.
- The incident response teams can investigate and remediate the security incidents.


<kbd>ServiceNow</kbd>
- An incident, asset, and ticket management tool
- Prisma Cloud integrates with ServiceNow and sends notifications of `Prisma Cloud alerts` as `ServiceNow tickets`.


<kbd>Amazon Simple Queue Service (Amazon SQS)</kbd>
- Use Amazon SQS to send, store, and receive messages between software components without losing messages or requiring other services to be available.
- Prisma Cloud supports using Amazon SQS to send alerts that can be consumed through a Splunk add-on or through CloudFormation to enable custom workflows.


<kbd>Slack</kbd>
- An online instant messaging and collaboration system that centralizes all notifications
- Alert notifications can be forwarded to a Slack channel for posting.


<kbd>Splunk</kbd>
- A cloud-based software platform
- search, analyze, and visualize machine-generated data gathered from external websites and platforms that can also receive alert notifications from Prisma Cloud.


<kbd>Webhooks</kbd>
- Integrate the Prisma Cloud Service with Webhooks to send `Prisma Cloud alerts` to Webhooks
- and pass information to any third-party integrations that are not natively supported on the Prisma Cloud service.



<kbd>Demo: Access Help for Outbound Integrations</kbd>

![Screen Shot 2020-10-22 at 18.47.05](https://i.imgur.com/Gs4en3M.png)



<kbd>Demo: Configure an Outbound Integration</kbd>

setting > integration > add new > Splunk

![Screen Shot 2020-10-22 at 18.48.28](https://i.imgur.com/VDQzzI3.png)



<kbd>Demo: Add a Notification Channel to an Alert Rule</kbd>


alert > alert rules >

![Screen Shot 2020-10-22 at 18.49.15](https://i.imgur.com/6GiQYL7.png)

![Screen Shot 2020-10-22 at 18.49.27](https://i.imgur.com/i9Lv0wQ.png)

---

## Prisma Cloud API Support

Prisma Cloud provides support for system `RESTful APIs`.

`REST API` features.
- Method
  - The method of integration with Prisma Cloud is through the system REST APIs.
- RESTful API
  - A RESTful API is an application program interface that uses `HTTP requests`.
- Responses
  - All responses will be in JSON format.
- HTTP Methods
  - The Prisma Cloud Rest API supports the following HTTP methods: `POST, PUT, GET, OPTIONS, DELETE, and PATCH`.  


<kbd>Demo: Using the Prisma Cloud API</kbd>

![Screen Shot 2020-10-22 at 18.53.47](https://i.imgur.com/i2Wejo0.png)

![Screen Shot 2020-10-22 at 18.54.02](https://i.imgur.com/Z8bDh3a.png)

![Screen Shot 2020-10-22 at 18.54.34](https://i.imgur.com/mz1TIS6.png)

![Screen Shot 2020-10-22 at 18.54.47](https://i.imgur.com/vRsUFBA.png)


---

## check

Which two platform capabilities are needed in order for Prisma Cloud to support third-party integrations? (Choose two.)
- provides inbound data ingestion used to monitor host vulnerabilities
- communicates with Prisma Cloud for outbound alert notification


Which two resources are provided on the Prisma Cloud API DOCs reference page? (Choose two.)
- documentation that describes the Prisma Cloud RESTful APIs
- support for a Try It feature to execute the API calls


Tenable and Qualys are examples of which type of integration?
- inbound integration for data ingestion












.
