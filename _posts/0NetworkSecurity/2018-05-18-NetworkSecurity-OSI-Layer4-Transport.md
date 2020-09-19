---
title: Meow's NetworkSecurity - OSI Layer 4 - The Transport Layer (Segments, Packets)
date: 2018-05-18 11:11:11 -0400
categories: [NetworkSecurity, OSI]
tags: [NetworkSecurity, OSI, Layer3]
toc: true
image:
---

# Meow's NetworkSecurity - OSI Layer 4 - The Transport Layer (Segments, Packets)

[toc]

---

## 4 The Transport Layer 

on top of the network layer

supports communication between machines and processes. 

Reponsible for communication between host computer and verifying that both the sender and receiver are ready to initiate the data transfer.
- messages are taken from upper layers (Layers 5–7), encapsulated into segments for transmission to the lower layers (Layers 1–3). 
- data streams coming from lower layers are decapsulated and sent to Layer 5 (the session layer), or some other upper layer, depending on the protocol. 
- Function: 
  - the network layer & transport layer: ensure reliability of communication, guaranteeds the link between stations.
  - Transport Layer: aurantees the actual delivery of data.

Extended addressing capability is achieved in the transport layer:
- viewing each machine (own one IP address) as having a collection of ports, 
- each port can be the source or destination port for communication with a port on another machine. 

Indeed, the transport layer protocols for the Internet specify 16-bit source and destination port numbers in their headers. 
- Each port is associated with a certain type of service offered by a host. 

Two primary protocols operate at the transport layer for the Internet: 
- Transmission Control Protocol (TCP) 
- User Datagram Protocol (UDP). 

## TCP: 
- more sophisticated 老练的 of these two and was defined together with IP as one of the original protocols for the Internet (refer to Internet protocols as “TCP/IP.”) 
- TCP is used for some of the most fundamental operations of the Internet. 
- The main extra feature of TCP is that:
  - connection oriented 导向的 transport protocol.
  - provides a reliable stream of bytes between two communicating parties with a guarantee that information arrives intact and in order.
  - If a packet in such a stream is lost, TCP guarantees that it will be resent, no loss of data. 
  - preferred protocol for transfer files, web pages, and email. 

## UDP 
- provides a best-effort communication channel between two ports. 
- It is used primarily for applications where communication speed is more important than completeness, like voice-over-IP conversation, where short drops are acceptable (lost packet), but not long pauses (waiting for a lost packet to be resent). 
- A connectionless transport protocol. 

## Novell’s Sequenced Packet Exchange (SPX): (less popular Layer 4 protocol)
- Similar to the TCP/IP stack of protocols, Novell’s solution was the IPX/SPX stack of protocols. 
- However, most modern Novell networks rely on TCP/IP rather than IPX/SPX. 
- Microsoft introduced its own implementation of Novell’s IPX/SPX, named NWLink IPX/SPX.

