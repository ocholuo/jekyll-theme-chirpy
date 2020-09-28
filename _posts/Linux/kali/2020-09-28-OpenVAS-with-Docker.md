---
title: OpenVAS on Kali Linux
# author: Grace JyL
date: 2020-09-28 11:11:11 -0400
description: 
excerpt_separator: 
categories: [Linux, Kali]
tags: [Linux, Kali, OpenVAS]
math: true
# pin: true
toc: true
# image: /assets/img/sample/devices-mockup.png
---


# OpenVAS with Docker

==============

[![Travis CI](https://img.shields.io/travis/mikesplain/openvas-docker/master.svg)](https://travis-ci.org/mikesplain/openvas-docker/branches) [![Docker Pulls](https://img.shields.io/docker/pulls/mikesplain/openvas.svg)](https://hub.docker.com/r/mikesplain/openvas/) [![Docker Stars](https://img.shields.io/docker/stars/mikesplain/openvas.svg)](https://hub.docker.com/r/mikesplain/openvas/) [![](https://images.microbadger.com/badges/image/mikesplain/openvas.svg)](https://microbadger.com/images/mikesplain/openvas "Get your own image badge on microbadger.com")


---

## Usage

Simply run:

```
# latest (9)
docker run -d -p 443:443 --name openvas mikesplain/openvas
docker run -d -p 443:443 -p 9390:9390 -p 9391:9391 -e OV_ADMIN_USERNAME=admin -e OV_PASSWORD=admin --name openvas mikesplain/openvas:8 
docker run -d -p 443:443 -p 9390:9390 -p 9391:9391 -e OV_ADMIN_USERNAME=admin -e OV_PASSWORD=admin --name openvas mikesplain/openvas:9

# 9
docker run -d -p 443:443 --name openvas mikesplain/openvas:9
```

This will grab the container from the docker registry and start it up.
Once you see a `It seems like your OpenVAS-9 installation is OK.` process in the logs, the web ui is good to go.  Goto `https://<machinename>`

```
Username: admin
Password: admin
```

To check the status of the process, run:

```
docker top openvas
```

In the output, look for the process scanning cert data.  It contains a percentage.

To run bash inside the container run:

```
docker exec -it openvas bash
```

---

#### Specify DNS Hostname
By default, the system only allows connections for the hostname "openvas".  To allow access using a custom DNS name, you must use this command:

```
docker run -d -p 443:443 -e PUBLIC_HOSTNAME=myopenvas.example.org --name openvas mikesplain/openvas
```

---

#### OpenVAS Manager
To use OpenVAS Manager, add port `9390` to you docker run command:
```
docker run -d -p 443:443 -p 9390:9390 --name openvas mikesplain/openvas
```

---

#### Volume Support
mount your data directory to `/var/lib/openvas/mgr/`:
- local directory must exist prior to running.

```
mkdir data
docker run -d -p 443:443 -v $(pwd)/data:/var/lib/openvas/mgr/ --name openvas mikesplain/openvas
```

---

#### Set Admin Password

- specifying a password at runtime using the env variable `OV_PASSWORD`:

```
docker run -d -p 443:443 -e OV_PASSWORD=securepassword41 --name openvas mikesplain/openvas
```

--

#### Update NVTs

update your container by execing into the container and running a few commands:

```bash
docker exec -it openvas bash
## inside container
greenbone-nvt-sync
openvasmd --rebuild --progress
greenbone-certdata-sync
greenbone-scapdata-sync
openvasmd --update --verbose --progress

/etc/init.d/openvas-manager restart
/etc/init.d/openvas-scanner restart
```

---

#### Docker compose (experimental)

For simplicity a `docker-compose.yml` file is provided, as well as configuration for Nginx as a reverse proxy, with the following features:

* Nginx as a reverse proxy
* Redirect from port 80 (http) to port 433 (https)
* Automatic SSL certificates from [Let's Encrypt](https://letsencrypt.org/)
* A cron that updates daily the NVTs

To run:

* Change `"example.com"` in the following files:
  * [docker-compose.yml] - `(docker-compose.yml)`
  * [conf/nginx.conf] - `(conf/nginx.conf)`
  * [conf/nginx_ssl.conf] - `(conf/nginx_ssl.conf)`
* Change the "OV_PASSWORD" enviromental variable in [docker-compose.yml] - (docker-compose.yml)
* Install the latest [docker-compose] - (https://docs.docker.com/compose/install/)
* run `docker-compose up -d`

---

#### LDAP Support (experimental)
Openvas do not support full ldap integration but only per-user authentication. 
- A workaround is in place here by syncing ldap admin user(defined by `LDAP_ADMIN_FILTER `) with openvas admin users everytime the app start up.  
- To use this, just need to specify the required ldap env variables:

```
docker run -d -p 443:443 -p 9390:9390 --name openvas -e LDAP_HOST=your.ldap.host -e LDAP_BIND_DN=uid=binduid,dc=company,dc=com -e LDAP_BASE_DN=cn=accounts,dc=company,dc=com -e LDAP_AUTH_DN=uid=%s,cn=users,cn=accounts,dc=company,dc=com -e LDAP_ADMIN_FILTER=memberOf=cn=admins,cn=groups,cn=accounts,dc=company,dc=com -e LDAP_PASSWORD=password -e OV_PASSWORD=admin mikesplain/openvas 
```

---

#### Email Support
To configure the postfix server, provide the following env variables at runtime: `OV_SMTP_HOSTNAME`, `OV_SMTP_PORT`, `OV_SMTP_USERNAME`, `OV_SMTP_KEY`

```
docker run -d -p 443:443 -e OV_SMTP_HOSTNAME=smtp.example.com -e OV_SMTP_PORT=587 -e OV_SMTP_USERNAME=username@example.com -e OV_SMTP_KEY=g0bBl3de3Go0k --name openvas mikesplain/openvas
```

---


ref
- [openvas-docker](https://github.com/mikesplain/openvas-docker)