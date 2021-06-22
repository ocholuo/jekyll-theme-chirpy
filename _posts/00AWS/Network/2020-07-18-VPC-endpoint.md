---
title: AWS - VPC - VPC endpointis
date: 2020-07-18 11:11:11 -0400
categories: [00AWS, Network]
tags: [AWS, Network, VPC]
toc: true
image:
---

[toc]

---

# VPC endpointis
- a virtual device
- horizontally scaled, redundant, and highly available VPC components.
- They allow communication between instances in your VPC and services without imposing availability risks.

- use When a private instance needs to access a supported AWS public services without leaving the AWS network


> By default, IAM users do not have permission to work with endpoints.
> - create an IAM user policy that grants users the permissions to create, modify, describe, and delete endpoints.


---

## 3 types of VPC endpoints

![Screen Shot 2020-05-05 at 23.19.04](https://i.imgur.com/WtBhpLe.png)

![Pasted Graphic 6](https://i.imgur.com/iYbP71R.jpg)


### Interface endpoint

<font color=blue> an elastic network interface with a private IP address </font> from the IP address range of your subnet
- use DNS names to resolve requests to a public AWS service.

- It serves as an entry point for <font color=blue> traffic destined to a supported AWS service or a VPC endpoint service </font>
  - Interface endpoints are powered by <font color=blue> AWS PrivateLink </font>
  - connect to services that are powered by <font color=blue> AWS PrivateLink </font>
  - These services include:
    - some AWS services,
    - services that are hosted by other AWS customers and AWS Partner Network (APN) Partners in their own VPCs (referred to as endpoint services),
    - and supported AWS Marketplace APN Partner services.
- service provider: The owner of the service
- service consumer: you, the principal who creates the interface endpoint
  - You are charged for creating and using an interface endpoint to a service.
  - Hourly usage rates and data processing rates apply.


### Gateway endpoints:
- a target for a specified route in your route table, used for traffic destined to a supported AWS service.
- incurs no additional charge.
  - Standard charges for data transfer and resource usage apply.
- Gateway endpoints are only available for:
  - Amazon DyanmoDB
  - Amazon S3
- use When a private instance needs to access a supported AWS public services such as DynamoDB or S3 without leaving the AWS network


### Gateway Load Balancer endpoints

<font color=blue> an elastic network interface with a private IP address </font> from the IP address range of your subnet
- It serves as an entry point to <font color=blue> intercept traffic and route it to a service configured using Gateway Load Balancers </font>
  - for example, for security inspection.
  - Gateway Load Balancer endpoints are powered by AWS PrivateLink.

- provides private connectivity between <font color=blue> virtual appliances in service provider VPC </font> and <font color=blue> application servers in service consumer VPC </font>
  - deploy the Gateway Load Balancer in the same VPC as the virtual appliances.
  - register the virtual appliances with a target group for the Gateway Load Balancer.
  - specify a Gateway Load Balancer endpoint as a target for a route in a route table.
  - Traffic to and from a Gateway Load Balancer endpoint is configured using route tables.
    - <font color=red> Traffic flows </font>
      - from the service consumer VPC over the Gateway Load Balancer endpoint
      - to the Gateway Load Balancer in the service provider VPC,
      - and then returns to the service consumer VPC.
    - create the <font color=blue> Gateway Load Balancer endpoint </font> and <font color=blue> the application servers </font> in different subnets.
    - This enables you to configure the Gateway Load Balancer endpoint as the next hop in the route table for the application subnet.
- Gateway Load Balancer endpoints are supported for endpoint services that are configured for Gateway Load Balancers only.


---

# AWS PrivateLink
- a PrivateLink connection
- connects an AWS public service to a VPC using a <font color=red> private connection </font>

- privately access services by using private IP addresses.
  - privately connect <font color=red> VPC </font> to
    - supported AWS services
    - services hosted by other AWS accounts (VPC endpoint services)
    - supported AWS Marketplace partner services.
    - that are powered by <font color=red> AWS PrivateLink </font>
  - An Interface for endpoint to uses <font color=red> AWS PrivateLink </font>
  - an <font color=blue> elastic network interface (ENI) </font> with a <font color=red> private IP address </font> that serves as <font color=blue> an entry point for traffic destined to a supported service </font>
    - <font color=red> connectionn from instances in VPC to the services </font>
      - does not require an <font color=blue> internet gateway, NAT device, VPN connection, or AWS Direct Connect connection </font>
      - Instances in the VPC <font color=blue> do not require public IP addresses </font> to communicate with resources in the service.
      - Traffic between the VPC and the other service <font color=blue> does not leave the Amazon network </font>

---

## AWS PrivateLink access over Inter-Region VPC Peering:
AWS PrivateLink
- Applications in an AWS VPC can <font color=red> securely access AWS PrivateLink endpoints across AWS Regions </font> using <font color=red> Inter-Region VPC Peering </font>
- privately access services hosted on AWS in a highly available and scalable manner,
  - without using public IPs or let traffic traverse the Internet.
  - Traffic using Inter-Region VPC Peering stays on the global AWS backbone and never traverses the public Internet.
- privately connect to a service even if the service endpoint resides in a different AWS Region.
