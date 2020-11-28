



```bash
Remington Winters  21:18

Starting Nmap 7.91 ( https://nmap.org ) at 2020-11-13 18:08 PST
Nmap scan report for omni.htb (10.10.10.204)
Host is up (0.16s latency).
Not shown: 65529 filtered ports
PORT      STATE SERVICE
135/tcp   open  msrpc
5985/tcp  open  wsman
8080/tcp  open  http-proxy
29817/tcp open  unknown
29819/tcp open  unknown
29820/tcp open  unknown


$ nmap -Pn -A -p- 10.10.10.204
Starting Nmap 7.80 ( https://nmap.org ) at 2020-11-21 01:40 UTC
Nmap scan report for omni.htb (10.10.10.204)
Host is up (0.010s latency).
Not shown: 65529 filtered ports
PORT      STATE SERVICE  VERSION
135/tcp   open  msrpc    Microsoft Windows RPC
5985/tcp  open  upnp     Microsoft IIS httpd
8080/tcp  open  upnp     Microsoft IIS httpd
| http-auth: 
| HTTP/1.1 401 Unauthorized\x0D
|_  Basic realm=Windows Device Portal
|_http-server-header: Microsoft-HTTPAPI/2.0
|_http-title: Site doesnt have a title.
29817/tcp open  unknown
29819/tcp open  arcserve ARCserve Discovery
29820/tcp open  unknown
1 service unrecognized despite returning data. If you know the service/version, please submit the following fingerprint at https://nmap.org/cgi-bin/submit.cgi?new-service :
SF-Port29820-TCP:V=7.80%I=7%D=11/21%Time=5FB8706A%P=x86_64-pc-linux-gnu%r(
SF:NULL,10,"\*LY\xa5\xfb`\x04G\xa9m\x1c\xc9}\xc8O\x12")%r(GenericLines,10,
SF:"\*LY\xa5\xfb`\x04G\xa9m\x1c\xc9}\xc8O\x12")%r(Help,10,"\*LY\xa5\xfb`\x
SF:04G\xa9m\x1c\xc9}\xc8O\x12")%r(JavaRMI,10,"\*LY\xa5\xfb`\x04G\xa9m\x1c\
SF:xc9}\xc8O\x12");
Service Info: Host: PING; OS: Windows; CPE: cpe:/o:microsoft:windows

Service detection performed. Please report any incorrect results at https://nmap.org/submit/ .
Nmap done: 1 IP address (1 host up) scanned in 177.79 seconds


$ msfconcole



ssh -S /tmp/sshtunnel -O exit research@cdg.io  -p22

$ telnet 10.10.10.204 8080
Trying 10.10.10.204...
Connected to 10.10.10.204.
Escape character is '^]'.

https://securethelogs.com/2019/08/30/hacking-windows-remote-management-winrm/

```













.