---
title: Palo Alto Networks - Prisma Cloud - 2
# author: Grace JyL
date: 2020-10-18 11:11:11 -0400
description:
excerpt_separator:
categories: [SOC, PaloAlto]
tags: [SOC, Prisma]
math: true
# pin: true
toc: true
image: /assets/img/note/Palo_Alto_Networks_Logo.png
---

[toc]

---

# Prisma Cloud - Monitoring Public Clouds

---

## Core Concepts

Prisma Cloud Core Concepts
- four main Prisma Cloud concepts:
- resource, policy, alert rule, and alert.
- `resource`
  - an entity in your public cloud environment.
  - may be any virtual asset or system user.
  - Cloud resources are acquired by Prisma Cloud after onboard the public cloud accounts.
- `policy`
  - a statement of acceptable state or behavior.
  - A policy has a type, which indicates the underlying mechanism used to apply the policy.
  - 4 types of policies:
    - config,
    - audit event,
    - network,
    - anomaly.
- `alert rule`
  - a collection of one or more `account groups` and one or more `policies` that make up the **acceptable use of the public cloud environment**.
- `alert`
  - An alert is asserted when a resource is in violation of a policy as defined in an alert rule.
  - 4 alert states:
    - open,
    - resolved,
    - dismissed, and
    - snoozed.



<kbd>Demo: Dashboard Asset Inventory</kbd>

![Screen Shot 2020-10-20 at 18.47.42](https://i.imgur.com/SCw4DAp.png)

unique assets: pass | low,medium,high,fail


<kbd>Demo: Dashboard SecOps</kbd>

![Screen Shot 2020-10-21 at 00.14.43](https://i.imgur.com/kTMllws.png)


---

## Policies












.
