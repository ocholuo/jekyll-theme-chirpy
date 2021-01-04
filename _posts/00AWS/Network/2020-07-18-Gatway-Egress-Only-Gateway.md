---
title: AWS - VPC Gateway - Egress-Only Gateway
date: 2020-07-18 11:11:11 -0400
categories: [00AWS, Network]
tags: [AWS, Network, VPC]
toc: true
image:
---

[toc]

---

# Egress-Only Gateway


- require a private subnet on IPv6-enabled VPCs, the Egress-only Internet Gateway, to allow <font color=red> one-way access to internet resources </font>
  - With the Egress-only Internet Gateway, outgoing traffic to the internet will be allowed. However, incoming traffic that’s initiated from the internet will be blocked.
  - no additional charge to use the Egress-only Internet Gateways.
  - However, data transfer charges apply.

- IPv6 addresses are globally unique and are therefore public by default.
  - Must create a custom route for `::/0` to the Egress-Only Internet Gateway.
  - Use Egress-Only Internet Gateway instead of NAT for IPv6.	

- Stateful 
  - forwards traffic from instance to Internet and then sends back the response.

- allows IPv6 traffic from instances to the Internet. 
  - does accept inbound traffic, but it is a certain type of inbound traffic.

- Prevents inbound access to those IPv6 instances.
  - Internet initiated traffic to those instances is blocked.
- Provides outbound Internet access for IPv6 addressed instances.










.