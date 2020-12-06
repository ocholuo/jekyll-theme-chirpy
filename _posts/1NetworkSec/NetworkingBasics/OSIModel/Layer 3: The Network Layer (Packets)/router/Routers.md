# Routers 
(each port 1 collision domain, 1 broadcast/port) Layer 3
	▪	makes decisions based on logical network address (like IP address) 
	⁃	has the capability to consider high-layer traffic parameters (like quality of service [QoS] settings) in making its forwarding decisions. 
	▪	why router rather than multilayer switch? 
	⁃	more feature-rich
	⁃	support a broader range of interface types. 
	⁃	example, if you need to connect a Layer 3 device out to your Internet service provider (ISP) using a serial port, you will be more likely to find a serial port expansion module for your router, rather than your multilayer switch. 

Connections of router:
	▪	Straight through:
	⁃	Switch-router, switch-PC, router-server, hub-PC. Hub-router
	▪	Cross-over:
	⁃	switch-hub
	⁃	Switch-switch, PC-PC, hub-hub, router-router

A router :
	▪	connects multiple network segments together into a single network.
	⁃	routes traffic between the segments. 
	⁃	Example, the Internet is effectively a single network hosting billions of computers. Routers route the traffic from segment to segment.
	▪	routers don’t pass broadcasts
	⁃	effectively reduce traffic on single segment. 
	⁃	Segments separated by routers are one broadcast domains. 
	⁃	If a network has too many computers on a single segment, broadcasts can result in excessive collisions and reduce network performance. 
	⁃	Moving computers to a different segment separated by a router can significantly improve overall performance. 
	⁃	Similarly, subnetting networks creates separate broadcast domains.


Cisco routers are popular, but many other brands exist. 
	▪	physical devices, most efficient. 
	▪	However, it’s also possible to add routing software to computers with more than one NIC. 
	▪	Example: Windows Server products can function as routers by adding additional services to the server.


difference between router and switch. 
	▪	switch: simple, forward packets in a single network, uses learned associations to reduce the use of broadcasting. 
	▪	router:sophisticated, forward packets to multiple networks, uses routing tables to determine how to forward packets, avoiding broadcast altogether.

