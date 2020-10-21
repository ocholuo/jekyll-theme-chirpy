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
image: /assets/img/note/prisma.png
---

[toc]

---

# Prisma Cloud - Monitoring Public Clouds Note

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

Default and Custom Policies
- The `default policies` can be used as templates to create `custom policy`.
- After you set up the policies, any **new or existing resources** that violate these policies are automatically detected.


Predefined Policies
- Adheres to established security best practices such as `PCI, GDPR, HIPAA, and NIST`.
- Predefined policies cannot be modified.

Custom Policies
- Create custom policies to monitor for violations and enforce organizational standards.

- 4 types of policies:
  - config,
  - audit event,
  - network,
  - anomaly.


<kbd>Demo: Policies Tab</kbd>

![Screen Shot 2020-10-21 at 00.25.47](https://i.imgur.com/GGukmnp.png)



<kbd>Demo: Policy Status</kbd>

filter just the ienabled policy

![Screen Shot 2020-10-21 at 00.31.04](https://i.imgur.com/pwQgxwf.png)


---

## Alerts


Alerts are generated after your public cloud account connects to Prisma Cloud.

After Prisma Cloud connects to your public cloud accounts and begins reading the designated logs, alerts are generated based on Prisma Cloud’s `built-in security policies and alert rules`.

These can be the default alert rules contained in the default alert group, which alerts on all policies, or policies that you have selected and associated with your account groups.


![Screen Shot 2020-10-21 at 00.32.52](https://i.imgur.com/InL3fmN.png)


Policies and Alerts
- In order for a resource to be considered clean, it must not violate any policy about its state.
- **policy**
  - A policy has a type, which indicates the underlying mechanism used to apply the policy.
- **Alerts**
  - An alert is an event tied to one or more policies that has been incorporated into an alert rule.
  - The alert is triggered when one or more of the policies has been violated by a resource.
- **Anomaly Alerts**
  - Anomaly alerts are not based on `RQL` but are based on `machine learning`.
  - Anomaly alerts cannot be cloned or modified directly.

- 4 alert states:
  - open,
  - resolved,
  - dismissed, and
  - snoozed. 小睡



<kbd>Demo: Default Alert Rule</kbd>
- how `Prisma Cloud policies` need to be included in an `alert rule` and also enabled in Prisma Cloud

![Screen Shot 2020-10-21 at 00.40.11](https://i.imgur.com/FcQHqwo.png)

![Screen Shot 2020-10-21 at 00.40.22](https://i.imgur.com/ICCJUJh.png)

![Screen Shot 2020-10-21 at 00.40.44](https://i.imgur.com/yvPHK4i.png)

![Screen Shot 2020-10-21 at 00.40.55](https://i.imgur.com/bbohYLQ.png)

![Screen Shot 2020-10-21 at 00.41.06](https://i.imgur.com/nZSN4rB.png)



<kbd>Demo: Configure an Alert Rule</kbd>

![Screen Shot 2020-10-21 at 00.42.37](https://i.imgur.com/KnexZc0.png)

![Screen Shot 2020-10-21 at 00.43.00](https://i.imgur.com/k5RfIHn.png)



<kbd>Demo: Configure a Notification Channel</kbd>


<kbd>Demo: Alert States</kbd>


![Screen Shot 2020-10-21 at 01.02.24](https://i.imgur.com/Dh3p9YX.png)


---

## Compliance Dashboard

Compliance Dashboard

Unlike the <kbd>Asset Inventory Dashboard</kbd> that `aggregates all resources and displays the pass and fail count` for all monitored resources, the <kbd>Compliance Dashboard</kbd> only displays the results for monitored resources that match the policies included within a compliance standard.


Health and Compliance
- Provides information related to the compliance posture across various compliance standards and only displays the results for monitored resources that match the policies included within a compliance standard


Reports
- Prisma Cloud enables administrators to view, assess, report, monitor, and review their cloud infrastructure health and compliance posture.


Monitored Resources
- Administrators can also create reports that contain summary and detailed findings of security and compliance risks in their cloud environment.


<kbd>Demo: Compliance Dashboard</kbd>


![Screen Shot 2020-10-21 at 01.11.58](https://i.imgur.com/svT50C7.png)

Compliance standard:

![Screen Shot 2020-10-21 at 01.13.26](https://i.imgur.com/EjntDGm.png)

![Screen Shot 2020-10-21 at 01.13.58](https://i.imgur.com/byGLd0Z.png)

---

## check

Which two options should you check to determine whether Prisma Cloud is ingesting public cloud data? (Choose two.)
- Dashboard Asset Inventory tab to verify that resources have been ingested
- Public cloud account has been onboarded and has an Active status

Which two actions must have been taken before Prisma Cloud can generate an alert?(Choose two.)
- An alert rule must be configured that includes one or more policies
- A Policy must be enabled and associated with an alert rule
- alert rule >: Policy













.
