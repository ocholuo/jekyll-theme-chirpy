

# OpenVAS on Kali Linux

[toc]

---

## Setting up Kali for installing OpenVAS

1. make sure Kali is up-to-date and install the latest OpenVAS. 
2. run the `openvas-setup` command to setup OpenVAS, download the latest rules, create an admin user, and start up the various services. 

```bash
root@kali:~# apt-get update && apt-get dist-upgrade -y
root@kali:~# reboot
root@kali:~# apt-get install openvas -y

root@kali:~# openvas-setup
[>] Checking redis.conf
[*] Editing redis.conf
[>] Checking openvassd.conf
[*] Adding to openvassd.conf
[>] Restarting redis-server
[>] Checking OpenVAS certificate infrastructure
[*] Creating OpenVAS certificate infrastructure
[>] Updating OpenVAS feeds
[*] Opening Web UI (https://127.0.0.1:9392) in: 5... 4... 3... 2... 1... 
[>] Checking for admin user
[*] Creating admin user
User created with password 'e432aa97-2fd3-4c1b-8c16-1166cbd19d70'.
[+] Done
```

---

### Checking for OpenVAS ports
Once `openvas-setup` completes its process, the OpenVAS manager, scanner, and GSAD services should be listening:

```bash
root@kali:~# root@kali:~# netstat -antp
Active Internet connections (servers and established)
Proto Recv-Q Send-Q Local Address           Foreign Address         State       PID/Program name    
tcp        0      0 127.0.0.1:80            0.0.0.0:*               LISTEN      4782/gsad           
tcp        0      0 127.0.0.1:9392          0.0.0.0:*               LISTEN      4774/gsad           
tcp        0      0 127.0.0.1:9390          0.0.0.0:*               LISTEN      4776/openvasmd      
# 9392 is for WebGUI/OpenVAS Web Interface.
```

### Checking OpenVAS services
run `openvas-check-setup` before launching OpenVAS just in case something went missing.

```bash

root@kali:~# openvas-check-setup 
openvas-check-setup 2.3.7
  Test completeness and readiness of OpenVAS-9
  ...
 ERROR: Your OpenVAS-9 installation is not yet complete!

Please follow the instructions marked with FIX above and run this
script again.

If you think this result is wrong, please report your observation
and help us to improve this check routine:
http://lists.wald.intevation.org/mailman/listinfo/openvas-discuss
Please attach the log-file (/tmp/openvas-check-setup.log) to help us analyze the problem.
# The fix is given as well, run greenbone-scapdata-sync and it will sync OpenVAS SCAP database files.


root@kali:~# greenbone-scapdata-sync
OpenVAS community feed server - http://www.openvas.org/
This service is hosted by Greenbone Networks - http://www.greenbone.net/
sent 10,379 bytes  received 884,066,503 bytes  2,847,268.54 bytes/sec
total size is 926,410,667  speedup is 1.05
part 0 Done
part 1 Done
part 0 Done
part 1 Done
/usr/sbin/openvasmd
```

---

## Starting OpenVAS services

```bash
root@kali:~# openvas-start
[*] Please wait for the OpenVAS services to start.
[*] Web UI (Greenbone Security Assistant): https://127.0.0.1:9392

● greenbone-security-assistant.service - Greenbone Security Assistant
   Loaded: loaded (/lib/systemd/system/greenbone-security-assistant.service; disabled; vendor preset: disabled)
[*] Opening Web UI (https://127.0.0.1:9392) in: 5... 4... 3... 2... 1... 
```

---

### Setup OpenVAS User account and changing password

```bash
# set manual password and create a new user from CLI.
root@kali:~# openvasmd --create-user=blackmore
User created with password '19c29356-c59e-481a-8c3d-80225f80302b'.
root@kali:~# openvasmd --create-user=blackmoreops
User created with password 'b4f70c8b-1c45-442d-a41b-b87b24f473b6'.
root@kali:~# 
root@kali:~# openvasmd --user=blackmoreops --new-password=operations1
root@kali:~# openvasmd --user=admin --new-password=administrator1
root@kali:~# openvasmd --user=blackmore --new-password=operations1

root@kali:~# openvasmd --get-users
admin
blackmore
blackmoreops
```

---

## Connecting to the OpenVAS Web Interface
1. https://127.0.0.1:9392
2. accept the self signed SSL certificate `confirm security exception` 
3. Type in Admin username and password or one of the new users you’ve setup and bang

---

## 

Search
MONDAY , SEPTEMBER 28 2020
HomeKali LinuxHackingHow toPopular CategoriesDisclaimerPrivacy PolicyContact Us

Search
blackMORE Ops
Home
Kali Linux
Hacking
How to
Popular Categories
Disclaimer
Privacy Policy
Contact Us
 Home/How to/Configure, Tune, Run and Automate OpenVAS on Kali Linux
Configure, Tune, Run and Automate OpenVAS on Kali Linux
October 3, 2018 How to, Kali Linux 1 Comment

Users often request the addition of vulnerability scanners to Kali, most notably the ones that begin with “N”, but due to licensing constraints, we do not include them in the distribution. Fortunately, Kali includes the very capable OpenVAS, which is free and open source. Although we briefly covered OpenVAS in the past, we decided to devote a more thorough post on how to Configure, Tune, Run and Automate OpenVAS on Kali Linux.

This is simply because vulnerability scanners often have a poor reputation, primarily because their role and purpose is misunderstood. Vulnerabilty scanners scan for vulnerabilities–they are not magical exploit machines and should be one of many sources of information used in an assessment. Blindly running a vulnerability scanner against a target will almost certainly end in disappointment and woe, with dozens (or even hundreds) of low-level or uninformative results.

System Requirements
The main complaint we receive about OpenVAS (or any other vulnerability scanner) can be summarized as “it’s too slow and crashes and doesn’t work and it’s bad, and you should feel bad”. In nearly every case, slowness and/or crashes are due to insufficient system resources. OpenVAS has tens of thousands of signatures and if you do not give your system enough resources, particularly RAM, you will find yourself in a world of misery. Some commercial vulnerability scanners require a minimum of 8GB of RAM and recommend even more.

OpenVAS does not require anywhere near that amount of memory but the more you can provide it, the smoother your scanning system will run. For this post, our Kali virtual machine has 3 CPUs and 3GB of RAM, which is generally sufficient to scan small numbers of hosts at once.

Initial OpenVAS Setup in Kali
OpenVAS has many moving parts and setting it up manually can sometimes be a challenge. Fortunately, Kali contains an easy-to-use utility called ‘openvas-setup’ that takes care of setting up OpenVAS, downloading the signatures, and creating a password for the admin user.

This initial setup can take quite a long while, even with a fast Internet connection so just sit back and let it do its thing. At the end of the setup, the automatically-generated password for the admin user will be displayed. Be sure to save this password somewhere safe.

root@kali:~# openvas-setup
ERROR: Directory for keys (/var/lib/openvas/private/CA) not found!
ERROR: Directory for certificates (/var/lib/openvas/CA) not found!
ERROR: CA key not found in /var/lib/openvas/private/CA/cakey.pem
ERROR: CA certificate not found in /var/lib/openvas/CA/cacert.pem
ERROR: CA certificate failed verification, see /tmp/tmp.7G2IQWtqwj/openvas-manage-certs.log for details. Aborting.ERROR: Your OpenVAS certificate infrastructure did NOT pass validation.
See messages above for details.
Generated private key in /tmp/tmp.PerU5lG2tl/cakey.pem.
Generated self signed certificate in /tmp/tmp.PerU5lG2tl/cacert.pem.
...
/usr/sbin/openvasmd
User created with password 'xxxxxxx-xxxx-xxxx-xxxx-xxxxxxxxxx'.
Dealing with Setup Errors
Occasionally, the ‘openvas-setup’ script will display errors at the end of the NVT download similar to the following.

(openvassd:2272): lib kb_redis-CRITICAL **: get_redis_ctx: redis connection error: No such file or directory
(openvassd:2272): lib kb_redis-CRITICAL **: redis_new: cannot access redis at '/var/run/redis/redis.sock'
(openvassd:2272): lib kb_redis-CRITICAL **: get_redis_ctx: redis connection error: No such file or directory
openvassd: no process found
If you are unfortunate enough to encounter this issue, you can run ‘openvas-check-setup’ to see what component is causing issues. In this particular instance, we receive the following from the script.

...
ERROR: The number of NVTs in the OpenVAS Manager database is too low.
FIX: Make sure OpenVAS Scanner is running with an up-to-date NVT collection and run 'openvasmd --rebuild'.
...
The ‘openvas-check-setup’ scipt detects the issue and even provides the command to run to (hopefully) resolve the issue. After rebuilding the NVT collection as recommended, all checks are passed.

root@kali:~# openvasmd --rebuild
root@kali:~# openvas-check-setup
openvas-check-setup 2.3.7
Test completeness and readiness of OpenVAS-9
...
It seems like your OpenVAS-9 installation is OK.
...
Managing OpenVAS Users
If you need (or want) to create additional OpenVAS users, run ‘openvasmd’ with the –create-user option, which will add a new user and display the randomly-generated password.

root@kali:~# openvasmd --create-user=dookie
User created with password 'yyyyyyyy-yyyy-yyyy-yyyy-yyyyyyyyyy'.
root@kali:~# openvasmd --get-users
admin
dookie
If you’re anything like us, you will forget to save the admin password or accidentally delete it. Fortunately, changing OpenVAS user passwords is easily accomplished with ‘openvasmd’ and the –new-password option.

root@kali:~# openvasmd --user=dookie --new-password=s3cr3t
root@kali:~# openvasmd --user=admin --new-password=sup3rs3cr3t
Starting and Stopping OpenVAS
Network services are disabled by default in Kali Linux so if you haven’t configured OpenVAS to start at boot, you can start the required services by running ‘openvas-start’.

root@kali:~# openvas-start
Starting OpenVas Services
When the services finish initializing, you should find TCP ports 9390 and 9392 listening on your loopback interface.

root@kali:~# ss -ant
State Recv-Q Send-Q Local Address:Port Peer Address:Port
LISTEN 0 128 127.0.0.1:9390 *:*
LISTEN 0 128 127.0.0.1:9392 *:*
Due to the strain on system resources, you will likely want to stop OpenVAS whenever you are done using it, especially if you are not using a dedicated system for vulnerability scanning. OpenVAS can be stopped by running ‘openvas-stop’.

root@kali:~# openvas-stop
Stopping OpenVas Services
Using the Greenbone Security Assistant
The Greenbone Security Assistant is the OpenVAS web interface, available on your local machine (after starting OpenVAS) at https://localhost:9392. After accepting the self-signed certificate, you will be presented with the login page and once authenticated, you will see the main dashboard.



Configuring Credentials
Vulnerability scanners provide the most complete results when you are able to provide the scanning engine with credentials to use on scanned systems. OpenVAS will use these credentials to log in to the scanned system and perform detailed enumeration of installed software, patches, etc. You can add credentials via the “Credentials” entry under the “Configuration” menu.



Target Configuration
OpenVAS, like most vulnerability scanners, can scan for remote systems but it’s a vulnerability scanner, not a port scanner. Rather than relying on a vulnerability scanner for identifying hosts, you will make your life much easier by using a dedicated network scanner like Nmap or Masscan and import the list of targets in OpenVAS.

root@kali:~# nmap -sn -oA nmap-subnet-86 192.168.86.0/24
root@kali:~# grep Up nmap-subnet-86.gnmap | cut -d " " -f 2 > live-hosts.txt
Once you have your list of hosts, you can import them under the “Targets” section of the “Configuration” menu.





Scan Configuration
Prior to launching a vulnerability scan, you should fine-tune the Scan Config that will be used, which can be done under the “Scan Configs” section of the “Configuration” menu. You can clone any of the default Scan Configs and edit its options, disabling any services or checks that you don’t require. If you use Nmap to conduct some prior analysis of your target(s), you can save hours of vulnerability scanning time.



Task Configuration
Your credentials, targets, and scan configurations are setup so now you’re ready to put everything together and run a vulnerability scan. In OpenVAS, vulnerability scans are conducted as “Tasks”. When you set up a new task, you can further optimize the scan by either increasing or decreasing the concurrent activities that take place. With our system with 3GB of RAM, we adjusted our task settings as shown below.



With our more finely-tuned scan settings and target selection, the results of our scan are much more useful.



Automating OpenVAS
One of the lesser-known features of OpenVAS is its command-line interface, which you interact with via the ‘omp’ command. Its usage isn’t entirely intuitive but we aren’t the only fans of OpenVAS and we came across a couple of basic scripts that you can use and extend to automate your OpenVAS scans.

The first is openvas-automate.sh by mgeeky, a semi-interactive Bash script that prompts you for a scan type and takes care of the rest. The scan configs are hard-coded in the script so if you want to use your customized configs, they can be added under the “targets” section.

root@kali:~# apt -y install pcregrep
root@kali:~# ./openvas-automate.sh 192.168.86.61:: OpenVAS automation script.
mgeeky, 0.1[>] Please select scan type:
1. Discovery
2. Full and fast
3. Full and fast ultimate
4. Full and very deep
5. Full and very deep ultimate
6. Host Discovery
7. System Discovery
9. Exit

--------------------------------
Please select an option: 5

[+] Tasked: 'Full and very deep ultimate' scan against '192.168.86.61'
[>] Reusing target...
[+] Target's id: 6ccbb036-4afa-46d8-b0c0-acbd262532e5
[>] Creating a task...
[+] Task created successfully, id: '8e77181c-07ac-4d2c-ad30-9ae7a281d0f8'
[>] Starting the task...
[+] Task started. Report id: 6bf0ec08-9c60-4eb5-a0ad-33577a646c9b
[.] Awaiting for it to finish. This will take a long while...

8e77181c-07ac-4d2c-ad30-9ae7a281d0f8 Running 1% 192.168.86.61
We also came across a blog post by code16 that introduces and explains their Python script for interacting with OpenVAS. Like the Bash script above, you will need to make some slight edits to the script if you want to customize the scan type.

root@kali:~# ./code16.py 192.168.86.27
------------------------------------------------------------------------------
code16
------------------------------------------------------------------------------
small wrapper for OpenVAS 6[+] Found target ID: 19f3bf20-441c-49b9-823d-11ef3b3d18c2
[+] Preparing options for the scan...
[+] Task ID = 28c527f8-b01c-4217-b878-0b536c6e6416
[+] Running scan for 192.168.86.27
[+] Scan started... To get current status, see below:zZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzz
...
zZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzzZzz

[+] Scan looks to be done. Good.
[+] Target scanned. Finished taskID : 28c527f8-b01c-4217-b878-0b536c6e6416
[+] Cool! We can generate some reports now ... :)
[+] Looking for report ID...
[+] Found report ID : 5ddcb4ed-4f96-4cee-b7f3-b7dad6e16cc6
[+] For taskID : 28c527f8-b01c-4217-b878-0b536c6e6416

[+] Preparing report in PDF for 192.168.86.27

[+] Report should be done in : Report_for_192.168.86.27.pdf
[+] Thanks. Cheers!
With the wide range of options available in OpenVAS, we were only really able to just scratch the surface in this post but if you take your time and effectively tune your vulnerability scans, you will find that the bad reputation of OpenVAS and other vulnerability scanners is undeserved. The number of connected devices in our homes and workplaces is increasing all the time and managing them becomes more of a challenge. Making effective use of a vulnerability scanner can make that management at least a little bit easier.

Previous
Install, setup, configure and run OpenVAS on Kali Linux
Next
Information gathering and correlation with Unicornscan on Kali Linux
Check Also
Install, setup, configure and run OpenVAS on Kali Linux - blackMORE Ops - 13 
Install, setup, configure and run OpenVAS on Kali Linux
Vulnerability scanning is a crucial phase of a penetration test and having an updated vulnerability …

One comment

KrisFebruary 15, 2020 at 6:52 am
great article !

Loading...
Reply
Use WordPress.com, Twitter, Facebook, or Google+ accounts to comment (anonymous comments allowed)

This site uses Akismet to reduce spam. Learn how your comment data is processed.

Recent Comments
Matus: Anyone in 2020 that have an AMD SDK 2.7 linux file fo download.? because with 2....
dsfasf: bulshit....
Denny: openvas-setup is now gvm-setup...
Tony Sandoval: Hi, yesterday i just deleted the partition using the disk part without knowing a...
Fiaz: Spoof the Ip address of one virtual machine using hping3 or other tool.(i.e, sen...
Recent Posts
Accessing ESXi console screen from an SSH session - 2
Accessing ESXi console screen from an SSH session
April 23, 2020
Accessing the RAID setup on an HP Proliant DL380 G7 - 5
Accessing the RAID setup on an HP Proliant DL380 G7
April 23, 2020
hexdump - blackMOREOps - 2
Change IP address in packet capture file (faking IP)
October 7, 2019
SamSam Ransomware
SamSam Ransomware
September 25, 2019
New Exploits for Unsecure SAP Systems - blackMORE Ops
New Exploits for Unsecure SAP Systems
September 24, 2019
Tags
AMD Browser Command Line Interface (CLI) Cracking CUDA Denial of Service Attack Desktop Manager error featured Hacking Hacking Tools How to IP Spoofing Kali Linux Kali Linux 2.0 Kali Linux Tools Kali Sana Kali Tools Linux Linux Administration metasploit MITM News News Articles NVIDIA Others Palo Alto Networks Penetration Test Pyrit Recommended Referral spam Security Spam Spoofing Tor United States Computer Emergency Readiness Team US-Cert US-Cert Alerts Virtualbox Virtual Private Network (VPN) Vulnerability wireless Wireless Cards Wireless LAN (Wi-Fi) WPA2
Email Subscription
Subscribe to our email newsletter.

Enter your e-mail address
Categories
Categories
Select Category
Archives
Archives
Select Month
Designed by blackMORE Ops© Copyright 2020, All Rights Reserved
