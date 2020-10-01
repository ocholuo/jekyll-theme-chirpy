---
title: InfoSec - Note
date: 2020-09-18 11:11:11 -0400
categories: [0 - LearningPath]
tags: [OnePage]
toc: true
pin: true
---

# InfoSec - Note

```
    __∧_∧__    ~~~~~
　／(*´O｀)／＼
／|￣∪∪￣|＼／
　|＿＿ ＿|／

por favor, no lo usar para hacer algo malo
just learning note, please don't use it to do something wrong
kali用得好，监狱进得早，与君共勉

```

---

# Test
- Common Vulnerabilities and Exposures (CVE®)


---

## Pentest tools

Attack | Tool
------------------------------|-----------------------------------------------------------
`vulnerability scanning & management` | OpenVAS <br> ![openvas10](https://i.imgur.com/8sJLdIh.png)
`Finding vulnerabilities` | Intruder <br> ![Intruder](https://i.imgur.com/7BLUaKx.png)
`Building anti-forensic and evasion tools` | Metasploit <br>![Metasploit](https://i.imgur.com/wY3EGbF.png) <br> - knowing about security vulnerabilities. <br> - Helps in penetration testing, IDS signature development. <br> - You can create security testing tools.
`identified vulnerabilities` | Netsparker <br>![Netsparker](https://i.imgur.com/cuShMOy.jpg)
---------|------------------------------
`phishing` | Gophish - opensource phishing platform |
`Scanning` | Nmap <br> ![Nmap_Logo](https://i.imgur.com/7b3NKgE.png) ![Nmap](https://i.imgur.com/K2OvK5H.png) <br> - **Nmap suite has**: <br> - Data transfer, redirection, and debugging tool(Ncat), <br> - Scan results comparing utility(Ndiff), <br> - Packet generation and response analysis tool (Nping), <br> - GUI and Results viewer (Nping) <br> - **Using raw IP packets it can determine**: <br> - The available hosts, services offered, OS, Packet filters using...
`Scanning` | Angry IP Scanner <br>![AngryIPScanner](https://i.imgur.com/5Qyj91h.png) <br> - scanning the IP addresses and ports. It can scan both on local network and Internet.
---------|------------------------------
`Scanning web server` | Nikto <br>![Nikto](https://i.imgur.com/Kq5bZOe.png) <br> - It can check web servers for over 6700 potentially dangerous files. <br> -It has full HTTP proxy support. <br> -Using Headers, favicons, and files, it can identify the installed software. <br> -It can scan the server for outdated server components.
`web application vulnerabilities` | Acunetix <br>![dashboard-2](https://i.imgur.com/9oeYkzy.png)
---------|------------------------------
`Sniffing` | Ettercap <br>![Ettercap](https://i.imgur.com/bVAdWxn.png) <br> - Sniffing of live connections. <br> - Content filtering. <br> - Active and passive dissection of many protocols. <br> - Network and host analysis.
`Sniffing` | Etherflood
`Sniffing` | dsniff
`Sniffing` | wireshark <br> ![Wireshark](https://i.imgur.com/L1ckSYt.png)
Countermeasure | detect: Antisniff, ArpWatch, Switch Network switch Port Security features
`Wireless sniffer & injector` | Aircrack-Ng <br>![aircrack-ng](https://i.imgur.com/wgVZxJJ.png) <br> - focus on Replay attacks, de-authentication, fake access points, and others. <br> - It supports exporting data to text files. <br> - It can check Wi-Fi cards and driver capabilities. <br> - It can crack WEP keys and for that, it makes use of FMS attack, PTW attack, and dictionary attacks. <br> - It can crack WPA2-PSK and for that, it makes use of dictionary attacks.
---------|------------------------------
`MITM/XSS` | Burp Suite <br>![BurpSuite](https://i.imgur.com/IJkeA2y.png) <br> - web vulnerability scanner
`XSS` | Owasp ZAP <br>![OwaspZAP](https://i.imgur.com/t6cqHVy.png)
`DOM Based XSS` | DOMinator Tool, DOM Snitch
---------|------------------------------
`Code Security/Analysis` | Kluwan <br> ![Kiuwan](https://i.imgur.com/PqQm3mh.png)
`PaswdCrack` | John The Ripper <br>![John-the-Ripper](https://i.imgur.com/zq4Ao7o.png)
---------|------------------------------
**Application Link** | Layer 7
`capturing HTTPS traffic` | sslstrip <br> ![sslstrip](https://i.imgur.com/aiocGts.png)
---------|------------------------------
**Network Link** | Layer 7
`capturing network packet` | PackETH <br> ![PackETH](https://i.imgur.com/HDvf8Du.png)
---------|------------------------------
**Data Link** | Layer 2
`ARP poisoning` | arpspoof <br> ![arpspoof](https://i.imgur.com/ZKZug7g.png)
`ARP poisoning` | Ettercap <br> ![Ettercap](https://i.imgur.com/0ciXVzn.png)
`ARP poisoning` | Cain and Abel <br> ![CainandAbel](https://i.imgur.com/8gSuAdg.png)
`detect ARP poisoning` | AntiARP, ARPon, ArpStar,
`detect ARP poisoning` | XARP <br> ![XARP](https://i.imgur.com/vWTIp91.png)
---------|------------------------------
**Digital Forensic Tools** | Layer 2
. | `Sleuth Kit (+Autopsy)`
---------|------------------------------
🐰 | **Reconnaissance** step 1 information gethering 
`passive` | visualping <br> ![visualping](https://i.imgur.com/TV47wYz.png)
`website mirroring` | HTTrack <br> ![HTTrack](https://i.imgur.com/g0s74oj.png)
`email foot-printing` | TheHarvester <br> ![TheHarvester](https://i.imgur.com/Ey7HrEQ.png)
`link analysis and data mining` | Maltego <br> ![Maltego](https://i.imgur.com/oegrQtd.png) <br> - provide graphical picture about the weak points and abnormalities of the network.
`foot-printing` | Recon-ng <br> ![Recon-ng](https://i.imgur.com/jgc9SSN.png)
`foot-printing` | OSRframwork <br> ![OSRframwork](https://i.imgur.com/vtJNROh.png)
`hacker search engine` | Shodan <br> ![OSRframwork](https://i.imgur.com/F4Hmvxn.jpg) <br> - collects all information about all devices that are directly connected to the internet with the specified keywords that you enter.
`web info gethering` | httprint <br> ![httprint](https://i.imgur.com/koDelg8.png)
`Fingerprint web Framework` | whatweb <br> ![whatweb](https://i.imgur.com/PKmBLXt.png)
`Fingerprint web Framework` | BlindElephant, Wappalyzer

---

GoodWeb | Note
---|---
[CSIS Significant Cyber Incidents](https://www.csis.org/programs/technology-policy-program/significant-cyber-incidents) | summary of incidents from over the last year.
[OWASP Cheat Sheet Series](https://cheatsheetseries.owasp.org/IndexASVS.html) |
[OWASP Cheat Sheet Series2](https://cheatsheetseries.owasp.org/IndexProactiveControls.html) |
[tutorialspoint-infosec](https://www.tutorialspoint.com/security_testing/testing_cross_site_scripting.htm) |
[Youtube-University of Nottingham](https://www.youtube.com/watch?v=1S0aBV-Waeo&ab_channel=Computerphile) |
[securityintelligence 2020 data breach report](https://securityintelligence.com/) |
[Web Application Vulnerabilities Index](https://www.netsparker.com/web-vulnerability-scanner/vulnerabilities/) | 
[Vulnerabilities search]  | [with expolit](https://securitytracker.com/search/search.html), [2](http://www.securityfocus.com/bid/17408), [IBM X-Force](https://exchange.xforce.ibmcloud.com/vulnerabilities/25698)

---

Lab | Note
---|---
[Internet Security](http://site.iugaza.edu.ps/mammar/internet-security/) <br> - [Attacklab](http://site.iugaza.edu.ps/nour/fall-2012/security-disc/) |
[SEEDLab](https://seedsecuritylabs.org/lab_env.html) |
- [Cross-Site Scripting Attack Lab](http://www.cis.syr.edu/~wedu/seed/Labs_12.04/Web/Web_XSS_Elgg/Web_XSS_Elgg.pdf) | ✔️
[InfosecLab](https://67327.cmuis.net/labs) |
[pentest](https://www.pentesterlab.com/exercises) |

---

XiongHaizimen | Note
---|---
[Tanishq](https://tanishq.page/index.html) | cool website and lab!
[coolguy](https://jhalon.github.io/over-the-wire-natas1/) | great solution
[Gaurav Sen](https://www.youtube.com/watch?v=_YlYuNMTCc8&t=5s&ab_channel=GauravSen) | youtuber


---

vulnerability | Note
---|---
[CVE-2005-2453 - Web Server Generic XSS] | [1](https://nvd.nist.gov/vuln/detail/CVE-2005-2453#VulnChangeHistorySection), [2](https://securitytracker.com/id?1014624)













.