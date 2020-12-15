---
title: AWS - AWS Inspector
date: 2020-07-18 11:11:11 -0400
categories: [2AWS, Detection]
tags: [AWS]
toc: true
image:
---

[toc]

---


# AWS Inspector   

> what’s wrong > vulnerabilities for EC2


- automated security assessment service
- assesses applications for `exposure, vulnerabilities, and deviations from best practices`.
- check for unintended `network accessibility and vulnerabilities on EC2 instances`.

![Screen Shot 2020-07-13 at 21.49.29](https://i.imgur.com/xav0ceb.png)

- produces a **detailed list** of the security issues
  - The name of the assessment target, which includes the EC2 instance where this finding was registered
  - The name of the assessment template that was used to produce this finding
  - The assessment run start time, end time, and status
  - The name of the rules package that includes the rule that triggered this finding
  - The name of the finding
  - The severity level of severity of the finding
  - The description of the finding
  - prioritized steps for remediation
  - Select the link to learn more about Amazon Inspector findings.
  - These findings can be reviewed directly or as part of detailed assessment reports which are available via the Amazon Inspector console or API.


- Amazon Inspector includes a knowledge base with hundreds of rules
  - Use `rules packages` to evaluate an application
  - mapped to common `security compliance standards and vulnerability definitions`.
  - whether remote root login is enabled
  - whether vulnerable software versions are installed.
  - These rules are regularly updated by AWS security researchers


- analyze the behavior of the resources and helps `identify potential security issues`.
  - Analyzes the VPC environment for potential security issues.
  - identify EC2 instances for common security vulnerabilities.
  - asses EC2s for vulnerabilities or deviations from best practices.
  - Results come in the form of detailed list of security findings prioritized by level of severity

- helps improve the `security and compliance` of applications deployed on AWS.

- Inspector uses a `defined template` and assesses the environment.
  - define a collection of resources to include in the assessment target and create an `assessment template` to launch a security assessment run of that target.
  - `analyze EC2 instances against pre-defined security templates` to check for vulnerabilities
  - Provides the findings and recommends steps to resolve any potential security issues found.
