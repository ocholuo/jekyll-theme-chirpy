---
title: Meow's CyberAttackTools - nmap
date: 2019-09-17 11:11:11 -0400
categories: [CyberAttack, CyberAttackTools]
tags: [CyberAttack, CyberAttackTools]
toc: true
image:
---

# nmap

[toc]

---

# Nmap

- free download, port scan machines.
- can reveal what services are running as well as info about the target machine’s operating system.
- 17-20 sec
- can scan a range of IP addresses as well as a single IP.
- set a number of flags (either with the command-line version of nmap or the Windows version) that customize your scan.

- can perform many different types of scans (from simply identifying active machines to port scanning and enumeration)
- can be configured to control the speed at which a scan operates. In general, the slower the scan, the less likely you are to be discovered.
- It comes in both a command-line version and a GUI version (Zenmap), works on multiple OS platforms, and can even scan over TCP and UDP.
- The target can besingle IP address, multiple individual IPs separated by spaces, or an entire subnet range (using CIDR notation).


![Screen Shot 2020-09-22 at 21.20.11](https://i.imgur.com/6j7WGaA.png)

![Nmap-Cheat-Sheet-1](https://i.imgur.com/HgcDLxE.jpg)

![page88image152923104](https://i.imgur.com/6mO8lhp.jpg)

Table 3-4 Nmap Switches

As you can see, quite a few option switches are available for the command.
- `s`: determine the type of scan to perform,
  - run a SYN port scan on a target as quietly as possible. ￼
    - `namap 111.111.11.1/24 -sS T0`
  - wanted an aggressive XMAS scan. ￼
    - `namap 111.111.11.1/24 -sX T4`
- `P`: set up ping sweep options,
- `o`: deal with output.
- `T`: deal with speed and stealth, with the serial methods taking the longest amount of time.
  - Parallel methods are much faster because they run multiple scans simultaneously.
  - Again, the slower you run scans, the less likely you are to be discovered. The choice of which one to run is yours.
  - slower being better, paranoid and sneaky scans can take exceedingly long times to complete.
  - Nmap at very fast (-T5) speeds, you’ll overwhelm your NIC and start getting some really weird results.
  - default: -T3, “normal.”

- Upon successful response from the targeted host,
  - If the command successfully finds a live host, it returns a message indicating that the IP address of the targeted host is up, along with the media access control (MAC) address and the network card vendor.
  - `nmap –sn –v <target IP address>`
- Nmap in a nutshell, offers Host discovery, Port discovery, Service discovery. Operating system version information. Hardware (MAC) address information, Service version detection, Vulnerability & exploit detection using Nmap scripts (NGE).
  - `nmap –sn –PE –PA <port numbers>  <Starting IP/ending IP>`
- output to a file:
  - `nmap 1.1.1.1 > result.txt`

Nmap handles all scan types we discussed in the previous section, using switches identified earlier. In addition to those listed, Nmap offers a “Windows” scan.
- It works like the ACK scan but is intended for use on Windows networks
- provides all sorts of information on open ports.
- Many more switches and options are available for the tool.

NOTE Port sweeping and enumeration on a machine is also known as fingerprinting, although the term is normally associated with examining the OS itself. You can fingerprint operating systems with several tools we’ve discussed already, along with goodies such as SolarWinds, Netcraft, and HTTrack.

---

## os detect

`nmap –O [目標IP]`

- 作業系統偵測的方式
- 使用TTL來大致判斷作業系統的類型
- NMAP也可使用`TCP/IP stack fingerprinting`進行遠端作業系統識別
  - NMAP會送出一系列的 TCP 和 UDP 封包到目標主機
  - 檢驗回傳(response)的每一個bit。
  - 在一連串的測試結果回傳後，NMAP會把這些資料拿去 nmap-os-db 的資料庫比對，他的資料庫中有超過2600種以上類型的作業系統資料，
  - NMAP若在資料庫中找到相符資訊，就會顯示出來。


每一個fingerprint都包含了一些作業系統的文字描述，並可以用來分類供應商(Sun/Microsoft), 作業系統(Solaris/Microsoft Windows), 版本 (10/2016), 及設備類型(route/switch..) 最常見的包含Common Platform Enumeration (CPE) representation例如: cpe:/o:linux:linux_kernel:2.6.

作業系統偵測會採用其他蒐集資料的方式
- 例如`TCP Sequence Predictability Classification`，這個測量標準取決於TCP的連線有多難建立。
- 另外一個作業系統探測的方式，目標主機的開機的時間，使用了TCP的時間戳記來判斷機器上次是何時重新啟動。雖然這種方式有可能因為計數器沒有歸零而不準確，但仍舊是判斷的標準之一。

![20119885QKelRVIEdE](https://i.imgur.com/UVlEJMN.jpg)

Windows 上面開啟的 WireShark也可以看到不同的封包送過來

![201198859tIIAzVaG3](https://i.imgur.com/V2XpawD.jpg)

掃描 RedHat 的結果

![201198850drjWEiMmG](https://i.imgur.com/gRls3fj.jpg)

結果會用「猜的」並配上百分比,非常準確。



![20119885iaQWJN3ocm](https://i.imgur.com/ZWbXAiQ.jpg)

---

## Nmap output
- The GUI version of the tool, `Zenmap`, makes reading this output easy
- the output is available via several methods.
- The default is called interactive, and it is sent to standard output (text sent to the terminal). Normal output displays less run-time information and fewer warnings because it is expected to be analyzed after the scan completes rather than interactively.
- You can also send output as XML (which can be parsed by graphical user interfaces or imported into databases) or in a “greppable” format (for easy searching).

![page89image152295312](https://i.imgur.com/ZaGR0fp.jpg)

![Screen Shot 2020-09-22 at 21.22.01](https://i.imgur.com/Gk9c2A8.png)

![Screen Shot 2020-09-22 at 21.23.12](https://i.imgur.com/p0btjP5.png)

![Screen Shot 2020-09-22 at 21.23.26](https://i.imgur.com/5yeUsUI.png)
.
