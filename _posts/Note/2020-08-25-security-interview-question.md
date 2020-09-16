---
layout: post
title: Cyber Security knowledge point
date: 2020-08-25 11:11:11 -0400
description: Cyber Security Interview Questiones
categories: [Note]
img: /assets/img/sample/rabbit.png
tags: [Interview]
---

# Cyber Security knowledge point

---

![osi](./../../assets/img/sample/OSILayer.jpg)

1. What is information security and how is it achieved?

2. What are the core principles of information security?
   1. CIA

3. What is non-repudiation (as it applies to IT security)?
4. What is the relationship between information security and data availability?
5. What is a security policy and why do we need one?
6. What is the difference between logical and physical security? Can you give an example of both?
7. What’s an acceptable level of risk?
8. What are the most common types of attacks that threaten enterprise data security?
9.  What is the difference between a threat and a vulnerability?
10. Can you give me an example of common security vulnerabilities?
11. Are you familiar with any security management frameworks such as ISO/IEC 27002?
12. What is a security control?
13. What are the different types of security control?
14. Can you describe the information lifecycle? How do you ensure information security at each phase?
15. What is Information Security Governance?
16. What are your professional values? Why are professional ethics important in the information


security field?

1.  Are open-source projects more or less secure than proprietary ones?
2.  Who do you look up to within the field of Information Security? Why?
3.  Where do you get your security news from?
4.  What’s the difference between symmetric and public-key cryptography?
5.  What kind of network do you have at home?
6.  What are the advantages offered by bug bounty programs over normal testing practices?
7.  What are your first three steps when securing a Linux server?
8.  What are your first three steps when securing a Windows server?
9.  Who’s more dangerous to an organization, insiders or outsiders?
10. Why is DNS monitoring important?
11. How would traceroute help you find out where a breakdown in communication is?
12. Why would you want to use SSH from a Windows PC?
13. How would you find out what a POST code means?
14. What is the difference between a black hat and a white hat?
15. What do you think of social networking sites such as Facebook and LinkedIn?
16. Why are internal threats often more successful than external threats?
17. Why is deleted data not truly gone when you delete it?
18. What is the Chain of Custody?
19. How would you permanently remove the threat of data falling into the wrong hands?
20. What is exfiltration?
21. How do you protect your home wireless access point?
22. If you were going to break into a database-based website, how would you do it?
23. What is the CIA triangle?
24. What is the difference between information protection and information assurance?
25. How would you lock down a mobile device?
26. What is the difference between closed-source and open-source? Which is better?
27. What is your opinion on hacktivist groups such as Anonymous?

# Network security

44. Explain what **Address Resolution Protocol** is.  
    1.  ![v2-cfdda1ceb830edd5a8d28ae31c6ac8f6_hd](/assets/img/sample/arp.png)
    2.  Data link-layer protocol
    3.  resolve IP addresses to MAC addresses
    4.  broadcasting requests that queries all the network interfaces on a local-area network, and caching responses for future use
        1.  ARP request
        2.  ARP reply
        3.  vulnerability with ARP:
            - It will believe any ARP reply packet
            - nothing to authenticate the request
            - Attackers can easily create ARP reply packets with spoofed or bogus MAC addresses, reply and poison the ARP cache on systems in the network.  Gratuitous ARP

45. What port does **ping** work over?
    1.  ping test uses `ICMP`
    2.  no real ports being used.
    3.  `ICMP` basically roofs, or sits on top of, the IP address. not a layer four protocol.


46. Do you prefer **filtered ports or closed ports** on your firewall?
    1.  For small company servers / back-end systems / intranet sites: close ports (REJECT).
        - The reason
        - because those server are not usually targeted by DDoS attacks 
        - because the external apps that requires to consume services hosted in the the servers can quickly report failures instead to hang the connections during minutes.


47. How exactly does **traceroute/tracert** work at the protocol level?
    1.  Traceroute (tracert) works by `sending a packet to an open UDP port` on a destination machine.
    2.  The router then discards the packet and sends off an `ICMP` notification packet to the original host with the message that the TTL expired from the router.
    3.  Traceroute transmits packets with small TTL (Time To Live) values. 
        - The TTL is an IP header field that is used to prevent packets from running into endless loops. 
        - When a router that handles the packet, subtracts one from the packet's TTL. 
        - The packet expires and it's discarded when the TTL reaches zero.


48. What are Linux’s strengths and weaknesses vs. Windows?
    1.  Price
    2.  Ease Of Use
    3.  Reliability. Linux is notoriously reliable and secure.
    4.  Software
    5.  Hardware
    6.  Security


49. What is a firewall? And provide an example of how a firewall can be bypassed by an outsider to
access the corporate network.
    1.  `PACKET-FILTERING FIREWALLS`
        1.  examines the packet source and destination IP address
        2.  either prohibits or allows them to pass based on the established security rule set.
        3.  `Stateless`
        4.  `Stateful`: remember information associated with previously passed packets and thus provide much better security.
    2.  `NEXT-GENERATION FIREWALLS (NGFW)`
        1.  traditional firewall features coupled with additional functionality 
            - like anti-virus, intrusion prevention systems, encrypted traffic inspection, and deep packet inspection (DPI).
        2.  Unlike basic firewalls which only check packet headers, DPI examines the data within the packet, thus allowing users to stop, identify, or categorize packets containing malicious data.
    3. `PROXY FIREWALLS`
       1. application level
       2. the client’s request is evaluated against security rules and based on these rules, it is permitted or blocked.
       3. use both stateful and deep packet inspection.
       4. They are mostly used for monitoring traffics for layer 7 protocols like HTTP and FTP.
    4. **bypass**:
       1. incognito window
       2. `HTTP tunneling` is a firewall evasion technique
          1. lots of things can be wrapped within an HTTP shell (Microsoft Office has been doing this for years).
            - because port 80 is almost never filtered by a firewall
            - can craft port 80 segments to carry payload for protocols the firewall may have otherwise blocked.
            - HTTP beacons and HTTP tunnels are the de facto standard implant technology for hackers.

1.  Besides firewalls, what other devices are used to enforce network boundaries?
    1.  IDS/IPS, Procy, VPN, ACLs, subnetting, NAT, PAT,


2.  What is the role of network boundaries in information security?
3.  What does an intrusion detection system do? How does it do it?

4.  What is a honeypot? What type of attack does it defend against?
    1.  a server left open or appears to have been sloppily locked down, allowing an attacker relatively easy access.
    2.  Divert attackers from the live network:
    3.  diverts the attacker away from the live network.
    4.  location of the honeypot is of utmost importance, more realistic placement is inside the DMZ.
    5.  tool to gather intelligence on the attacker

5.  What technologies and approaches are used to secure information and services deployed on cloud computing infrastructure?
6.  What information security challenges are faced in a cloud computing environment?

7.  an overview of IP multicast?
    1.  delivers application source traffic to multiple receivers without burdening the source or the receivers while `using a minimum of network bandwidth`.
    2.  multicast sender could send traffic destined for a Class D IP address, known as a multicast group,
    3.  devices on a network wanting to receive that transmission could join that multicast group.
    4.  send that traffic only to devices in a network wanting to receive that traffic.

8.  How many bits do you need for a subnet size?
    1.  32 bits

9.  What is packet filtering?
    
10. Can you explain the difference between a packet filtering firewall and an application layer firewall?


11. layers of the OSI model?
    1.  Physical, Data Link, Network, Transport, Session, Presentation, and Application.


12. How would you login to Active Directory from a Linux or Mac box?
13. What is an easy way to configure a network to allow only a single computer to login on a particular jack?
14. What are the three ways to authenticate a person?
15. You find out that there is an active problem on your network. You can fix it, but it is out of your jurisdiction. What do you do?
16. How would you compromise an “office workstation” at a hotel?
17. What is worse in firewall detection, a false negative or a false positive? And why?
18. How would you judge if a remote server is running IIS or Apache?
19. What is the difference between an HIDS and a NIDS?

---

# Application security

68. Describe the last program or script that you wrote. What problem did it solve?

69. Can you briefly discuss the role of information security in each phase of the software development lifecycle?

70. How would you implement a secure login field on a high traffic website where performance is a consideration?

71. What are the various ways to handle account brute forcing?

72. What is cross-site request forgery?

73. How does one defend against CSRF?
    
74. If you were a site administrator looking for incoming CSRF attacks, what would you look for?
75. What’s the difference between HTTP and HTML?
76. How does HTTP handle state?
77. What exactly is cross-site scripting?
78. What’s the difference between stored and reflected XSS?
79. What are the common defenses against XSS?
80. You are remoted in to a headless system in a remote area. You have no physical access to the hardware and you need to perform an OS installation. What do you do?
81. On a Windows network, why is it easier to break into a local account than an AD account?

82. encrypt and compress data during transmission, which first?
    1.  compression aims to use patterns in data to reduce its size.
    2.  Encryption aims to randomize data so that it's uninterpretable without a secret key.
    3.  encrypt first, then compress, then compression will be useless. `Compression doesn't work on random data.`
    4.  compress first, then encrypt, then an attacker can find patterns in message length (Compression Ratio) to learn something about the data and potentially foil the encryption (like CRIME)

83. What could attackers do with HTTP Header Injection vulnerability?
    - Carriage returns and line feeds (or %0D & %0A) are means to an end that would allow attackers to control HTTP headers
    - could inject XSS via Referer header
    - could set cookie to a value known by the attacker (session fixation)
    - could redirect to a malicious server

84. Describe the last program or script that you wrote. What problem did it solve?
    - Just looking for signs that the candidate has basic understanding of programming concepts and is at least able to write simple programs
  
85. How would you implement a secure login field on a high traffic website where performance is a consideration?
    - TLS (regardless of performance) is a must
    - reducing 3rd party library dependencies, could improve performance and reduce security risks [link](https://hackernoon.com/im-harvesting-credit-card-numbers-and-passwords-from-your-site-here-s-how-9a8cb347c5b5)
    - Content-Security Policy (CSP) to enforce stricter execution rules around JS and CSS [link](https://en.wikipedia.org/wiki/Content_Security_Policy)
    - Subresource Integrity (SRI) to ensure only known, trusted resource files are loaded from 3rd-party servers/CDNs [link](https://en.wikipedia.org/wiki/Subresource_Integrity)

86. What are the various ways to handle brute forcing?
    - Account Lockouts/timeouts
    - API rate limiting
    - IP restrictions
    - Fail2ban
    - ...etc


87. What is **Cross-Site Request Forgery**? And how to defend?
    - attacker gets a victim's browser to make requests with the victim's credentials
    - Example: 
    - if an image tag `<img>` points to a URL with an associated action, e.g. `https://foo.com/logout`
    - Defense includes but are not limited to:
      - check origins header & referer header
      - check CSRF tokens or nonce

88. What is Cross-Site Scripting? What are the different types of XSS? defend?
    - attackers get victim's browsers to execute some code (usually JavaScript) within their browser
    - Traditionally, types have been categorized into `Stored` and `Reflected` XSS attacks.
    - `Stored XSS` is some code that an attacker was able to persist in a database and gets retrieved and presented to victims (e.g. forum)
    - `Reflected XSS` is usually in the form of a maliciously crafted URL which includes the malicious code. When the user clicks on the link, the code runs in their browser
    - `DOM-based XSS`, occurs when attackers can control DOM elements, thus achieve XSS without sending any requests to the server
    - XSS categories tend to overlap, therefore it's much better to describe XSS in terms like Server Stored XSS, Server Reflected XSS, Client Stored XSS (e.g. stored DOM-based XSS), or Client Reflected XSS (e.g. reflected DOM-based XSS)
    - Defense includes:
      - Output encoding (more important)
      - Input validation (less important)

89. How does HTTP handle state?
    - HTTP is stateless
    - State is stored in cookies


## Architect
Have you designed security measures that span overlapping information domains?
Can you give me a few examples of security architecture requirements?
What special security challenges does Service-Oriented-Architecture (SOA) present?
Have you architected a security solution that involved SaaS components? What challenges did you face?
Have you worked on a project in which stakeholders choose to accept identified security risks that worried you? How did you handle the situation?
How do you handle demands from different stakeholders who have conflicting requirements?
How do you ensure that solution architects develop secure solutions?
How do you ensure that a solution continues to be resilient in the face of evolving threats?
What do you think the most important technology is right now? How are we going to secure it?
Blue Team
Given an HTTP traffic log between a machine on your network and a 3rd party website (e.g. Google), what would the source and destination ports look like?
Source port might be some number above port 1024 (aka ephemeral port)
Destination port might be 80 (HTTP) or 443 (HTTPS)
Encryption
What's the difference between encoding, encryption, and hashing?

Encoding ensures message integrity. Can be easily reversible. Example: base64
Encryption guarantees message confidentiality. Reversible only using the appropriate decryption keys. Example: AES256
Hashing is a one-way function. Cannot be reversed. The output is fixed length and usually smaller than the input.

1. Does TLS use symmetric or asymmetric encryption?
   - Both.
   - The initial exchange is done using asymmetric encryption, but bulk data encryption is done using symmetric. 
   - Resources:
   - [link](https://web.archive.org/web/20150206032944/https://technet.microsoft.com/en-us/library/cc785811.aspx)
   - [link](https://en.wikipedia.org/wiki/Transport_Layer_Security)

2. the process of a TLS session set up (visits a secure website).
   - **Client** sends `hello message`, which 
     - lists cryptographic information, such as SSL/TLS version and the client's order of preference of cipher suites. 
     - contains a random byte string that is used in subsequent calculations.  
     - may include data compression methods in the hello message as well.
   - **Server** responds with hello message, which 
     - contains the cipher suite chosen by the server, the server's digital certificate, and another random byte string. 
     - If the server requires client certificate authentication, the server will also send client certificate request to the client.
   - **Client** 
     - verifies server's digital certificate.
     - sends a `random byte string encrypted with the server's public key` to allow both client and server to calculate the secret key used for subsequent encryption between client & server.
     - If server requested a client certificate, the client sends a `random byte string encrypted with the client's private key with the client's digital certificate` or "no digital certificate alert". This alert is only a warning, but some implementations will cause the handshake to fail if client authentication is mandatory.
   - **Server** verified client's digital certificate.
   - **Client** sends finished message encrypted with the calculated secret key
   - **Server** sends finished message encrypted with the calculated secret key
   - For the duration of the TLS session, the server and client can now exchange messages that are symmetrically encrypted with the shared secret key
   - Resources:

3. How is TLS attacked? How has TLS been attacked in the past? Why was it a problem? How was it fixed?
Weak ciphers
Heartbleed
BEAST
CRIME
POODLE


What is Forward Secrecy?

Forward Secrecy is a system that uses ephemeral session keys to do the actual encryption of TLS data so that even if the server’s private key were to be compromised, an attacker could not use it to decrypt captured data that had been sent to that server in the past.
Describe how Diffie-Hellman works.

## Forensics

1. Are open source projects more or less secure than proprietary projects?
   - Both models have pros and cons.
   - There are examples of insecure projects that have come out of both camps.
   - Open source model encourages "many eyes" on a project, but that doesn't necessarily translate to more secure products

2. What's important is not open source vs proprietary, but quality control of the project.
Who do you look up to in the Information Security field? Why?

Where do you get your security news from?

---

# Security architect

82. Explain data leakage and give examples of some of the root causes.
83. What are some effective ways to control data leakage?
84. Describe the 80/20 rules of networking.
85. What are web server vulnerabilities and name a few methods to prevent web server attacks?
86. What are the most damaging types of malwares?
87. What’s your preferred method of giving remote employees access to the company network and are there any weaknesses associated to it?
88. List a couple of tests that you would do to a network to identify security flaws.
89. What kind of websites and cloud services would you block?
90. What type of security flaw is there in VPN?
91. What is a DDoS attack?
92. Can you describe the role of security operations in the enterprise?
93. What is layered security architecture? Is it a good approach? Why?
94. Have you designed security measures that span overlapping information domains? Can you give me a brief overview of the solution?
95. How do you ensure that a design anticipates human error?
96. How do you ensure that a design achieves regulatory compliance?
97. What is capability-based security? Have you incorporated this pattern into your designs? How?
98. Can you give me a few examples of security architecture requirements?
99. Who typically owns security architecture requirements and what stakeholders contribute?
100. What special security challenges does SOA present?
101. What security challenges do unified communications present?
102. Do you take a different approach to security architecture for a COTS vs a custom solution?
103. Have you architected a security solution that involved SaaS components? What challenges did you face?
104. Have you worked on a project in which stakeholders choose to accept identified security risks that worried you? How did you handle the situation?
105. You see a user logging in as root to perform basic functions. Is this a problem?
106. What is data protection in transit vs data protection at rest?
107. You need to reset a password-protected BIOS configuration. What do you do?


---

# Risk management

108. Is there an acceptable level of risk?
109. How do you measure risk? Can you give an example of a specific metric that measures information security risk?
110. Can you give me an example of risk trade-offs (e.g. risk vs cost)?
111. What is incident management?
112. What is business continuity management? How does it relate to security?
113. What is the primary reason most companies haven’t fixed their vulnerabilities?
114. What’s the goal of information security within an organization?
115. What’s the difference between a threat, vulnerability, and a risk?
116. If you were to start a job as head engineer or CSO at a Fortune 500 company due to the previous guy being fired for incompetence, what would your priorities be? [Imagine you start on day one with no knowledge of the environment]
117. As a corporate information security professional, what’s more important to focus on: threats or vulnerabilities?
118. If I’m on my laptop, here inside my company, and I have just plugged in my network cable. How many packets must leave my NIC in order to complete a traceroute to twitter.com?
119. How would you build the ultimate botnet?
120. What are the primary design flaws in HTTP, and how would you improve it?
121. If you could re-design TCP, what would you fix?
122. What is the one feature you would add to DNS to improve it the most?
123. What is likely to be the primary protocol used for the Internet of Things in 10 years?
124. If you had to get rid of a layer of the OSI model, which would it be?
125. What is residual risk?
126. What is the difference between a vulnerability and an exploit?

# Security audits, testing & incident response

127. What is an IT security audit?
128. What is an RFC?
129. What type of systems should be audited?
130. Have you worked in a virtualized environment?
131. What is the most difficult part of auditing for you?
132. Describe the most difficult auditing procedure you’ve implemented.
133. What is change management?
134. What types of RFC or change management software have you used?
135. What do you do if a rollout goes wrong?
136. How do you manage system major incidents?
137. How do you ask developers to document changes?
138. How do you compare files that might have changed since the last time you looked at them?
139. Name a few types of security breaches.
140. What is a common method of disrupting enterprise systems?
141. What are some security software tools you can use to monitor the network?
142. What should you do after you suspect a network has been hacked?
143. How can you encrypt email to secure transmissions about the company?
144. What document describes steps to bring up a network that’s had a major outage?
145. How can you ensure backups are secure?
146. What is one way to do a cross-script hack?
147. How can you avoid cross script hacks?
148. How do you test information security?
149. What is the difference between black box and white box penetration testing?
150. What is a vulnerability scan?
151. In pen testing what’s better, a red team or a blue team?
152. Why would you bring in an outside contractor to perform a penetration test?

# Cryptography


153. What is secret-key cryptography?
154. What is public-key cryptography?
155. What is a session key?


156. What is RSA?
     1.   asymmetric encryption
     2.   based on factoring 2 larger primes.
     3.   RSA works with both encryption and digital signatures, used in many environments, like Secure Sockets Layer (SSL), and key exchange.
          1.   3^5 = 3 to the 5th
          2.   B^Number Mod M = bignum
               1.   B^X Mod M = bignum1
               2.   B^Y Mod M = bignum2
          3.   (B^Y Mod M)^X = B^XY Mod M
               1.   B^X Mod M = B^XY Mod M = bignum1^Y = bignumber3
               2.   B^Y Mod M = B^XY Mod M = bignum2^X = bignumber3


1.   How fast is RSA?
2.   What would it take to break RSA?
3.   Are strong primes necessary for RSA?
4.   How large a module (key) should be used in RSA?
5.   How large should the primes be?
6.   How is RSA used for authentication in practice? What are RSA digital signatures?
7.   What are the alternatives to RSA?
8.   Is RSA currently in use today?
9.   What are DSS and DSA?
10.  What is difference between DSA and RSA?
11.  Is DSA secure?
12.  What are special signature schemes?
13.  What is a blind signature scheme?
14.  What is a designated confirmer signatures?
15.  What is a fail-stop signature scheme?
16.  What is a group signature?
17.  What is blowfish?
18.  What is SAFER?
19.  What is FEAL?
20.  What is Shipjack?
21.  What is stream cipher?
22.  What is the advantage of public-key cryptography over secret-key cryptography?
23.  What is the advantage of secret-key cryptography over public-key cryptography?
24.  What is Message Authentication Code (MAC)?
25.  What is a block cipher?
26.  What are different block cipher modes of operation?
27.  What is a stream cipher? Name a most widely used stream cipher.
28.  What is one-way hash function?
29.  What is collision when we talk about hash functions?
30.  What are the applications of a hash function?
31.  What is trapdoor function?
32.  Cryptographically speaking, what is the main method of building a shared secret over a public medium?
33.  What’s the difference between Diffie-Hellman and RSA?
34.  What kind of attack is a standard Diffie-Hellman exchange vulnerable to?
35.  What’s the difference between encoding, encryption, and hashing?
36.  In public-key cryptography you have a public and a private key, and you often perform both encryption and signing functions. Which key is used for which function?
37.  What’s the difference between Symmetric and Asymmetric encryption?
38.  If you had to both encrypt and compress data during transmission, which would you do first, and why?
39.  What is SSL and why is it not enough when it comes to encryption?
40.  What is salting, and why is it used?
41.  What are salted hashes?
42.  What is the Three-way handshake? How can it be used to create a DOS attack?
43.  What’s more secure, SSL or HTTPS?
44.  Can you describe rainbow tables?

# Source:- sec-community and personal experience



# knowledge point


**What Is Tcp/ip Model?**
- TCP/IP model is an implementation of OSI reference model.
- It has 4 layers.
- Network layer, Internet layer, Transport layer and Application layer.


## TCP/UDP

1. Network traffic mainly categorizes into two types:
   - Transmission Control Protocol (TCP)
   - User Datagram Protocol (UDP).
   - Both protocols help in to establish the connection and transfer data between two ends of the communication. Below are the TCP/UDP interview questions and answers which generally asked in an interview.

2. Explain Transmission Control Protocol, TCP
   - TCP is a `connection-oriented protocol`.
   - when data is transferring from source to destination, `protocol takes care of data integrity` by sending data packet again if it lost during transmission.
   - ensures reliability and error-free data stream.
   - TCP packets contain fields such as Sequence Number, AcK number, Data offset, Reserved, Control bit, Window,  Urgent Pointer, Options, Padding, checksum, Source port, and Destination port.

3. Explain User Datagram Protocol, UDP
   - UDP is a `connection-less protocol`. 
   - if one data packet is lost during transmission, it will not send that packet again.
   - This protocol is suitable where minor data loss is not a major issue.

4. How does TCP work?
   - three-way handshake to establish a connection between client and server.
   - It uses SYN, ACK and FIN flags (1 bit) for connecting two endpoints.
   - After the establishment of the connection, data is transferred sequentially.
   - If there is any loss of packet, it retransmits data.

5. List out common TCP/IP protocols.
   - HTTP – Used between a web client and a web server, for non-secure data transmissions.
   - HTTPS – Used between a web client and a web server, for secure data transmissions.
   - FTP – Used between two or more computers to transfer files.

6. Comparison between TCP/IP & OSI model.
   - TCP/IP is the alternate model that also explains the information flow in the network.
   - It is a simpler representation in comparison to the OSI model but contains fewer details of protocols than the OSI model.

7. Is UDP better than TCP?
   - wants error-free and guarantees to deliver data, TCP 
   - wants fast transmission of data and little loss of data is not a problem, UDP

8. What is the port number of Telnet and DNS?
   - Telnet is a protocol used to access remote server but insecurely. Port no of Telnet is 23.
   - DNS is a protocol used to translate a domain name to IP address. Port no of DNS is 53.

9. What is the UDP packet format?
   - contains four fields:
   - Source Port and Destination Port fields (16 bits each): Endpoints of the connection.
   - Length field (16 bits) : length of the header and data.
   - Checksum field (16 bits) : It allows packet integrity checking (optional).

10. What is the TCP packet format?
    - The TCP packet format consists of these fields:
    - `Source Port and Destination Port` fields (16 bits each); 
    - `Sequence Number field` (32 bits);
    - `Acknowledgement Number field` (32 bits); 
    - Data Offset (a.k.a. Header Length) field (variable length);
    - `Reserved field` (6 bits); 
    - `Flags field` (6 bits) contains the various flags: URG,  ACK, PSH, RST, SYN, FIN; 
    - `Window field` (16 bits); 
    - `Checksum field` (16 bits) ; 
    - `Urgent pointer` field (16 bits) ;
    - Options field (variable length)
    - Data field (variable length).

11. List out some TCP/IP ports and protocols.
    - Protocol	Port Number	Description
    - File Transfer Protocol (FTP)	        20/21	Protocol helps in transferring files between client and server.
    - Secure Shell (SSH)	                    22	This method helps to access remote server securely.
    - Telnet	                                23	This method also helps to access remote server but here, data is transmitted in clear text.
    - Simple Mail Transfer Protocol (SMTP)	25	This protocol helps in managing email services.
    - Domain Name System (DNS)	            53	This protocol helps in translating domain name into IP addresses.






.
