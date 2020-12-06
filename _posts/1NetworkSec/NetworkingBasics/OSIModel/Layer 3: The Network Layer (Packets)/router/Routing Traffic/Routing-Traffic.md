


# Basic Routing Processes 


- **PC1** needs to send traffic to **Server1**:
  - devices are on different networks.
  - How packet from source IP address 192.168.1.2 get routed to destination IP address 192.168.3.2.


process step by step: 

- step 1: **PC1** to **Router R1**
  - **PC1** compare two `IP` and `subnet mask`, 192.168.1.2/24, 192.168.3.2/24.
  - **PC1** concludes that the destination IP address resides on a remote subnet.
  - **PC1** send the packet to its **default gateway** 192.168.1.1 (**Router R1**).
    - been `manually configured` on **PC1** 
    - or `dynamically learned via DHCP (Dynamic Host Configuration Protoco`
  - To construct a Layer 2 frame, **PC1** needs R1’s MAC address. 
  - **PC1** sends an `Address Resolution Protocol (ARP)`
    - broadcast-based protocol
    - request for **Router R1**’s MAC address.
  - **PC1** receives an ARP reply from R1
  - **PC1** adds **Router R1**’s MAC address to its ARP cache. 
  - **PC1** now sends its data in a frame destined for **Server1**. 
- step 2.  Router R1 to Router R2 
  - R1 receives frame from **PC1**
  - R1 interrogates the IP header. 
  - IP header contains a Time to Live (TTL) field: 
  - decremented once for each router hop. 
  - When reduced to 0:
  - the router discards the frame 
  - and sends a time exceeded Internet Control Message Protocol (ICMP) message back to the source. 
  - Assuming the TTL is not decremented to 0
  - R1 checks its routing table to determine the best path to reach network 192.168.3.0/24. 
  - network 192.168.3.0/24 is accessible via interface Serial 1/1. 
  - R1 forwards the frame out of its Serial 1/1 interface. 
- step 3. Router R2 
  - R2 receives the frame
  - R2 decrements the TTL in the IP header, like **Router R1** did.
  - Assuming the TTL did not get decremented to 0
  - R2 interrogates the IP header to determine the destination network. 
  - the destination network: 192.168.3.0/24
  - directly attached to router R2’s Fast Ethernet 0/0 interface. 
  - R2 sends an ARP request to determine the MAC address of **Server1**. 
  - After an ARP Reply is received from **Server1**
  - R2 forwards the frame out of its Fast Ethernet 0/0 interface to **Server1**.



Router table:
- allows a router to quickly look up the best path that can be used to send the data.
- updated on a regular schedule 
  - to ensure that info is accurate 
  - to account for changing network conditions.
- Routers rely on internal routing table to make packet forwarding decisions.
  - consulted its routing table to find the best match. 
  - The best match: the route that has the longest prefix.
- Example:
  - a router has an entry for network 10.0.0.0/8 and for network 10.1.1.0/24. 
  - the router is seeking the best match for a destination address of 10.1.1.1/24. 
  - The router would select the 10.1.1.0/24 route entry as the best entry, 
  - because that route entry has the longest prefix.
- Layer 3 to Layer 2 mapping:
  - ARP cache contained Layer 3 to Layer 2 mapping information. 
  - the ARP cache mapping MAC address to IP address.




1st
735827464

2nd
736268883






.