---
title: NetworkSec - TCP & UDP, TCP/IP & OSI Model
# author: Grace JyL
date: 2018-05-18 11:11:11 -0400
description:
excerpt_separator:
categories: [1NetworkSec, NetworkingBasics]
tags: [NetworkSec, TCP]
math: true
# pin: true
toc: true
image: /assets/img/note/tls-ssl-handshake.png
---

[toc]

---

# TCP & UDP, TCP/IP 

The **Transmission Control Protocol/Internet Protocol (TCP/IP)** suite
- created by the `U.S. Department of Defense (DoD)`
- ensure that communications could survive any conditions and that data integrity wouldn't be compromised under malicious attacks.

The **Open Systems Interconnection Basic Reference Model (OSI Model)**
- an abstract description for network protocol design
- developed as an effort to standardize networking.

---

## The OSI Model
All People Seem To Need Data Processing.
- Layer 1: The physical layer
- Layer 2: The data link layer
- Layer 3: The network layer
- Layer 4: The transport layer
- Layer 5: The session layer
- Layer 6: The presentation layer
- Layer 7: The application layer

![Screen Shot 2020-04-14 at 22.53.56](https://i.imgur.com/qj4yNvP.png)

---

## The TCP/IP

- shorter version of the OSI model.

![TCP1](https://i.imgur.com/wPGfs9K.jpg)

- The ISO developed the OSI reference model to be generic, in terms of what protocols and technologies could be categorized by the model.

- DoD model  / TCP/IP stack:
  - Most traffic on the networks based on the TCP/IP protocol suite.
  - a more relevant model is developed by the United States Department of Defense (DoD). The DoD model / TCP/IP stack.

- Network Control Protocol (NCP):
  - An older protocol, similar to the TCP/IP protocol suite,  
  - a protocol used on ARPANET (the predecessor to the Internet),
  - provided features similar to (not as robust) to TCP/IP suite of protocols.

- TCP/IP suit:
  - `a suite of protocols` that controls the way information travels from location to location
  - 6 protocols generally serve as the foundation of the TCP/IP suite:
    - `IP, DNS, TCP,UDP, ICMP and ARP`.
￼
1. **Application Layer**:
   - `representation, encoding and dialog control issues`.
   - TCP/IP stack vs OSI model: biggest difference is in application layer.
     - Maps to Layers 5, 6, and 7 (the session, presentation, and application layers) of the OSI model.
2. **Host-to-Host**:
   - `application data segmentation, transmission reliability, flow and error control`.
   - Host-to-Host protocol in the TCP/IP model provides more or less the same services with its equivalent Transport protocol in the OSI model.
3. **Internet**:
   - `route packets to their destination independent of the path taken`
   - Internet layer in TCP/IP model provides the same services as the OSIs Network layer.
4. **Network Access**:
   - `physical issues concerning data termination on network media`
   - includes all the concepts of the data link and physical layers of the OSI model for both LAN and WAN media.


### Common Application Protocols in the TCP/IP Stack

![TCP2](https://i.imgur.com/TM6p21I.jpg)

- Application layer protocols in the TCP/IP stack: identifiable by port numbers.
- example:
    - when enter web address in browser, by default communicating with web address, uses TCP port 80. HTTP
    - data send to remote web server has `destination port 80`.
    - **That data is then encapsulated into a TCP segment** at the transport layer.
    - **That segment is then encapsulated into a packet** at the Internet layer,
    - **the packet is sent out on the network** using an underlying network interface layer technology (like Ethernet).
      - the packet:
      - destination IP address of the web server, destination port 80
      - IP address of your computer, your computer selects a port number greater than 1023. (Because your computer is not acting as a web server, its port is not 80.)
    - when the web server sends content back to the PC, the data is destined for the PC’s IP address and for the port number PC selected.
    - With both source and destination port numbers, source and destination IP addresses, two-way communication becomes possible.
    - well-known ports: ports numbered below 1023
    - ephemeral ports: ports numbered above 1023
    - The maximum value of a port is 65,535.

IP Vulnerabilities
- Unencrypted transmission
  - Eavesdropping possible at any intermediate host during routing
- No source authentication
  - Sender can spoof source address, making it difficult to trace packet back to attacker
- No integrity checking
  - Entire packet, header and payload, can be modified while en route to destination, enabling content forgeries, redirections, and man-in-the-middle attacks
- No bandwidth constraints
  - Large number of packets can be injected into network to launch a denial-of-service attack
  - Broadcast addresses provide additional leverage

---

# Host-to-Host Layer Protocols

**Connection-oriented protocol**
- reliable connection stream between two nodes
  - Protocols operate by acknowledging / confirming ever connection request or transmission.
  - Overhead is more and the performance is less.
- Consists of set up, transmission, and tear down phases
- Creates virtual circuit-switched network

**Connectionless protocol**
- Do not require an acknowledge:
  - Faster, lack of requirement.
- Sends data out as soon as there is enough data to be transmitted

---

## TCP vs UDP

Two protocols: `Transmission Control Protocol (TCP)` and `User Datagram Protocol (UDP)` are defined for transmitting datagrams.
1. UDP can be much faster than TCP, which often requires retransmissions and delaying of packets.
2. UDP is often used
   - time-sensitive applications
     - where data integrity is not as important as speed
     - Short client-server request like DNS, single message request
     - Voice over IP (VoIP).
     - High-perfomece networking
     - Application handles reliable transmission
   - Primary use: send small packets of information.
3. TCP is used for
   -  applications where data order and data integrity is important
     - like HTTP, SSH, and FTP.

![TCP7](https://i.imgur.com/pwrzleA.jpg)

---

## Transmission Control Protocol (TCP)
- transport layer protocol
- distinguish data for multiple concurrent applications on the same host.
- Most popular application protocols, like WWW, FTP and SSH are built on top of TCP

If a process needs to send a complete file to another computer
1. chopping it into IP packets
2. sending them to the other machine
3. Double-checking that all the packets made it intact 完整的
4. resending any that were lost

### TCP three-way handshake
1. TCP session: starts by establishing a communication connection between sender and receiver.
2. the client sends a `SYN (synchronize) packet`.
3. The server responds with a `SYN/ACK (synchronize/acknowledge) packet`
4. the client completes the handshake with an `ACK packet` to establish the connection.
5. Once connection created, the parties communicate over the established channel.

![Image2](https://i.imgur.com/n2fQFBa.jpg)


### TCP Features
- **connection-oriented traffic**
- **delivery messages in order**
  - guaranteeing reliable data transfer
  - requiring positive `acknowledgment numbers` of delivery
  - ensures reliable transmission by using `sequence number` (three-way handshake)
  - each party is aware when packets arrive out of order or not at all.
- **Congestion 拥挤 Control**:
  - prevent overwhelming a network with traffic
    - cause poor transmission rates and dropped packets
  - not implemented into TCP packets specifically
    - but based on information gathered by keeping track of `acknowledgments` for previously sent data and the `time required `for certain operations.
  - TCP adjusts
    - `data transmission rates` using this information to prevent network congestion.
    - incorporates a cumulative 累积的 acknowledgment scheme.
      - sender and receiver, communicating via established TCP connection.
      - sender sends the receiver a specified amount of data,
      - the receiver confirms that it has received the data: sending a response packet to the sender with the acknowledgment field set to the next sequence number it expects to receive.
      - If any information has been lost, then the sender will retransmit it.
- **provide flow control**
  - distinguish data for multiple concurrent applications on the same host.
    - like WWW, FTP and SSH are built on top of TCP
  - handle startups and shutdowns
  - manages the amount of data that can be sent by one party, avoiding overwhelming the processing resources of the other / the bandwidth of the network itself.
  - 2 common flow control approaches: `Sliding window` and `Buffering`.
- **Error checking**
  - privides error checking with `checksums`

  - not be cryptographically secure, but to detect inconsistencies in data due to network errors rather than malicious tampering.
  - This checksum is typically supplemented by an additional checksum at the link layer
    - such as Ethernet, which uses the CRC-32 checksum.
  - ensure correctness of data.
    - comparing a checksum of the data with a checksum encoded in the packet

### Sliding window protocol:
- TCP uses it to manage flow control.
  - Sliding window used by the reciever to slow down the sender
- Congestion control: TCP can react to changing network conditions (slow down/speed up dens rates)
  - Does it by adjust the congestion widonw size of the sender.
  - congestion widonw size used by the sender to reduce the network congestion
  - Various techniques exist: back off, then add load
  - Unacknowledged semgment sent send at a point of time,
￼￼
- Serves 3 different roles
  - Reliable
  - Preserve the order 保留順序(過程亂序，但回傳給上層是照順序的)
  - Each frame has a sequence number
  - Delivery order is maintained by sequence number
  - The receiver does not pass a frame up to the next higher-level protocol until it has already passed up all frames with a smaller sequence number
  - Frame control
  - Receiver throttle 节流 the sender, 用RWS來控制
  - Keeps the sender from overrunning the receiver
  - transmitting more data than the receiver is able to process

### Buffering:
- device (like router) allocates a chunk of memory (buffer/queue) to store segments if bandwidth is not currently available to transmit those segments.
  - A queue 队列 has finite capacity, will overflow (drop segments) in the event of sustained network congestion.


### TCP Packet Format
```
Frame 1: Physical layer
Ethernet II, Src: Data layer
Internet Protocol Version 4, Src: 192.168.1.100, Dst: 192.168.1.1 Network layer
Internet Control Message Protocol Transport layer
```

![TCP4](https://i.imgur.com/yxqDZTd.jpg)

- Includes source and destination ports, define the communication connection for this packet and others like it.
- In TCP, connection sessions are maintained beyond the life of a single packet,
  - so TCP connections have a state, defines the status of the connection.
  - TCP communication session, goes from states used to open a connection, to those used to exchange data and acknowledgments, to those used to close a connection.
- TCP is a byte-oriented protocol
  - the sender writes bytes into a TCP connection
  - the receiver reads bytes out of the TCP connection.

- Byte stream:
  - but TCP does not transmit individual bytes,
  - The source host buffers enough bytes from the sender to fill a reasonably sized packet, and then sends this packet to its peer on the destination host.
  - The destination host then empties the contents of the packet into a receiver buffer, and the receiving process reads from this buffer at its leisure.
- The packets exchanged between TCP peers are segments.
- Buffer: make sure every is in order.
- 2 incarnations of the same connection: closed and open a same TCP connection.

![Screen Shot 2019-02-09 at 09.47.12](https://i.imgur.com/me6koF4.png)

TCP Header

![TCP3](https://i.imgur.com/83zsF4g.jpg)

- TCP packet header: (4x6)24 bytes, 64 bits.
- `SrcPor / DstPort`:
  - the source / destination ports.
  - to which upper-layer protocol data should be forwarded,
  - from which upper-layer protocol the data is being sent.
- `SequenceNum`:
  - Indentify the position in the segmented data stream
  - contains the sequence number for the first byte of data carried in that segment.
  - if segments arrive out of order, the recipient can put them back in the appropriate order.
- `AcknowledgmentNum`:
  - the next sequence number expected to receive.
  - The number of the next octet to be received in the data stream
  - This is a way for the receiver to let the sender know that all segments up to and including that point have been received.
- `HeaderLength`:
  - The TCP header is of variable length, HdrLen gives the length of the header in 32-bit words. Also known as Offset field.
- `AdvertisedWindow`:
  - how many bytes a device can receive before expecting an acknowledgment. 	
  - offers flow control.
- `Flags`:
  - relay control information between TCP peers. Used to determine the conditions and status of the TCP connection.
  - Possible flags: SYN, FIN, RESET, PUSH, URG, and ACK.
  - URG flag: contains urgent data. The urgent data is contained at the front of the segment body, and including a UrgPtr bytes into the segment. Indicates that the data contained in the packet is urgent and should process immediately.
  - UrgPtr field: Urgent pointer, indicates where the non-urgent data contained in this segment begins.
  - ACK flag: set any time the Acknowledgment field is valid, implying that the receiver should pay attention to it. Acknowledge the receipt of a packet.
  - PSH / PUSH flag: the sender invoked the push operation, which indicates to the receiver that it should notify the receiving process of this fact. Instructs the sending system to send all buffered data immediately.
  - RST / RESET flag: the receiver has become confused, it received a segment it did not expect to receive, wants to abort the connection. Reset a connection.
  - SYN / FIN flags:
  - SYN: Initiates a connection between two hosts to facilitate communication.
  - FIN: Tells the remote system about the end of the communication. In essence, this gracefully closes a connection.
- `Checksum`:
  - computed over the TCP header, data, and pseudo-header, which is made up of the source address, destination address, and length fields from the IP header.
- The `Checksum` field is used
  - provide extra reliability and security to the TCP segment.
- The `actual user data`
  - included after the end of the header.

TCP options must be in multiples of four (4) bytes. If 10 bytes of options are added to the TCP header, two single byte No-Operation will be added to the options header.

---

## User Datagram Protocol (UDP)

![TCP6](https://i.imgur.com/uCCDhzx.jpg)

UDP is a good for applications need maximize bandwidth and do not require acknowledgments (example, audio or video streams).
- UDP is considered to be a connectionless, unreliable protocol,
1. no initial handshake to establish a connection
   - no handshake, harder to scan and enumerate.
   - allows parties to send messages, known as datagrams, immediately.
   - If a sender wants to communicate via UDP, it need only use a socket (defined with respect to a port on a receiver) and start sending datagrams, no elaborate setup needed.
2. features a 16-bit `checksum`
   - to verify the integrity of each individual packet
3. no sequence number scheme, window size, and acknowledgment numbering present in the header of a TCP segment.
   - transmissions can arrive out of order or may not arrive at all.
   - does not make guarantee about the order or correctness of packet delivery.
   - UDP header is so much smaller than a TCP header,
- It is assumed that checking for missing packets in a sequence of datagrams is left to applications processing these packets.

![TCP5](https://i.imgur.com/Guu1ilc.jpg)




ref:
-[plur](https://www.pluralsight.com/blog/it-ops/networking-basics-tcp-udp-tcpip-osi-models)

.
