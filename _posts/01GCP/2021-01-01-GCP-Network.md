---
title: GCP - Network
date: 2021-01-01 11:11:11 -0400
categories: [21GCP]
tags: [GCP]
toc: true
image:
---

- [GCP - Virtual Private Cloud](#gcp---virtual-private-cloud)
  - [Virtual Private Cloud](#virtual-private-cloud)
    - [routing tables](#routing-tables)
    - [global distributed firewall.](#global-distributed-firewall)
    - [VPC connection](#vpc-connection)
    - [Cloud Load Balancing](#cloud-load-balancing)
    - [DNS](#dns)
    - [Google Cloud CDN](#google-cloud-cdn)
    - [connection](#connection)

- ref
  - [GCP Google Cloud Platform Fundamentals - Core Infrastructure](https://www.coursera.org/learn/gcp-infrastructure-foundation/lecture/WZq76/lab-intro-vpc-networking)


---

# GCP - Virtual Private Cloud

---

## Virtual Private Cloud

- define the own VPC inside the GCP project, or choose the default VPC and get started with that.

- VPC networks connect the Google Cloud platform resources to each other and to the internet.
  - segment the networks,
  - use firewall rules to restrict access to instances,
  - and create static routes to forward traffic to specific destinations.

- <font color=red> Networks are global; subnets are regional </font>
  - The Virtual Private Cloud networks have global scope.
  - can have subnets in any GCP region worldwide
  - subnets can span the zones that make up a region.

- benifit:
  - define the own network layout with global scope.
  - can have resources in different zones on the same subnet.
  - can dynamically increase the size of a subnet in a custom network by expanding the range of IP addresses allocated to it.
  - Doing that doesn't affect already configured VMs.

- VPC subnets have regional scope.
  - subnet can span the zones that make up a region.
  - solutions can incorporate fault tolerance without complicating the network topology.

example
- the VPC has one network.
- it has one subnet defined in GCP us-east1 region.
- it has two Compute Engine VMs attached to it.
- They're neighbors on the same subnet even though they are in different zones.
- solutions that are resilient but still have simple network layouts.

![Screen Shot 2021-02-03 at 15.48.15](https://i.imgur.com/xVdAWp7.png)

---

### routing tables
- Much like physical networks, VPCs have routing tables.
- used to forward traffic from one instance to another instance within the same network.
- Even across sub-networks and even between GCP zones without requiring an external IP address.
- VPCs routing tables are built in,
  - don't have to provision or manage a router.
  - don't have to provision or manage for GCP, a firewall instance.


---

### global distributed firewall.
- control to restrict access to instances, both incoming and outgoing traffic.
- define firewall rules in terms of metadata tags on Compute Engine instances
- example
- tag all the web servers with say, "web,"
- and write a firewall rule saying that traffic on ports 80 or 443 is allowed into all VMs with the "web" tag, no matter what their IP address happens to be.


---

### VPC connection

> VPCs belong to GCP projects
> if the company has several GCP projects and the VPCs need to talk to each other

VPC Peering
- peering relationship between two VPCs to exchange traffic  
- to interconnect networks in GCP projects

Shared VPC
- to share a network, or individual subnets, with other GCP projects
- to use the full power of IAM
- to control who and what in one project can interact with a VPC in another


---


### Cloud Load Balancing

- a fully distributed, software-defined managed service for all the traffic.
- because the load balancers don't run in VMs, no worry about scaling or managing them.
- put Cloud Load Balancing in front of all the traffic
  - HTTP and HTTPS, TCP and SSL traffic, and UDP traffic too.

- Cloud Load Balancing
  - basic
    - you application presents a single front-end to the world
    - users get a single, global anycast IP address
    - Traffic goes over the google backbone from the closet point-of-presence to the user
      - backend instances in regions around the world.
  - provides <font color=red> cross-region load balancing </font>
    - including automatic multi-region failover,
  - backbone are selected based on load
    - Cloud Load Balancing reacts quickly to changes in users, traffic, backend health, network conditions, and other related conditions.  
    - gently moves traffic in fractions if backends become unhealthy.
    - only healthy backends receive reaffic
    - no pre-warming is required

different load balancer
1. <font color=red> global HTTPS load balancer </font>
   - layer 7 load balancing based on load
   - cross regional load balancing for a web application
   - route different URLs to different back ends

2. <font color=red> global SSL proxy load balancer </font>
   - layer 4 load balancing of non-HTTPS SSL traffic based on load
   - For Secure Sockets Layer traffic that is not HTTP
   - Supported on specific port numbers
   - only work for TCP and specific port numbers

3. <font color=red> global TCP proxy load balancer </font>
   - layer 4 load balancing of non-SSL TCP traffic
   - If it's other TCP traffic that does not use Secure Sockets Layer
   - Supported on specific port numbers
   - only work for TCP and specific port numbers

4. <font color=red> regional load balancer </font>
   - load balancing of any traffic (TCP/UDP) on any port number
   - load balance across a GCP region with the regional load balancer

5. <font color=red> internal load balancer </font>
   - load balance traffic inside a VPC / the project
   - for traffic
     - coming into the Google network from the internet.
       - example:
       - between the presentation layer and the business logic layer of the application
     - TCP traffic on arbitrary port numbers
     - UDP traffic
   - accepts traffic on a GCP internal IP address and load balances it across Compute Engine VMs.
   - for the interbal tiers of multi-tier applications
   - to load-balance traffic among the back-end VMs that form part of a multi-tier application.  



![Screen Shot 2021-02-03 at 20.26.54](https://i.imgur.com/LSAsJ01.png)

---


### DNS
- 8.8.8.8
  - Google services
  - provides a public domain name service to the world.
    - DNS is what translates internet host names to addresses.
  - Google has a highly developed DNS infrastructure.
    - It makes 8.8.8.8 available so that everybody can take advantage of it.  
- Cloud DNS
  - for the internet host names and addresses of applications build in GCP
  - to help the world find them
  - a managed DNS service running on the same infrastructure as Google.
  - It has low latency and high availability
  - a cost-effective way to make the applications and services available to the users.
  - The DNS information you publish is served from redundant locations around the world.
  - Cloud DNS is also programmable.
    - publish and manage millions of DNS zones and records
    - using the GCP console, the command line interface or the API.

---


### Google Cloud CDN
- Google has a global system of edge caches.
- You can use this system to accelerate content delivery in the application
- the customers
  - experience lower network latency.
- The origins of the content
  - experience reduced load
  - and save money too.
- Once you've set up HTTPS load balancing, simply enable Cloud CDN with a single checkbox.
- CDN is a part of GCPs,
  - CDN interconnect partner program and you can continue to use it.

---

### connection

![Screen Shot 2021-02-03 at 20.38.04](https://i.imgur.com/pcVgd0Y.png)
- interconnect their networks to Google VPCs
  - such as on-premises networks or their networks in other clouds.  

- Virtual Private Network
  - Virtual Private Network connection over the internet using the IPSEC protocol.
  - Cloud Router
    - simple way to let a VPN into Google VPC continue to work in spite of routing changes,
    - Cloud Router lets their networks and the Google VPC exchange route information over the VPN using the Border Gateway Protocol.
    - example
      - add a new subnet to the Google VPC,
      - the on-premises network will automatically get routes to it.

- Direct Peering
  - don't want to use the internet
    - either because of security concerns or need more reliable bandwidth.
  - peering with Google using Direct Peering.
  - putting a router in the same public data center as a <font color=red> Google point of presence </font> and exchanging traffic.
  - Google has more than 100 <font color=red> points of presence </font> around the world.
  - Customers who aren't already in a point of presence can contract with a partner in the carrier peering program to get connected.
  - One downside:
    - it isn't covered by a Google service level agreement.
    - Customers who want the highest uptimes for their interconnection with Google should use Dedicated Interconnect
  - If these connections have topologies that meet Google's specifications, they can be covered by up to a 99.99 percent SLA.
  - These connections can be backed up by a VPN for even greater reliability.

- Dedicated Interconnect
  - customers get one or more direct private connections to Google.
  - highest uptimes for their interconnection with Google












.