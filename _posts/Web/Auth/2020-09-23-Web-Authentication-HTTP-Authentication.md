---
title: Web Authentication - HTTP Authentication
# author: Grace JyL
date: 2020-09-23 11:11:11 -0400
description: 
excerpt_separator: 
categories: [Web, Authentication]
tags: [Authentication]
math: true
# pin: true
toc: true
# image: /assets/img/sample/devices-mockup.png
---

# Web Authentication - HTTP Authentication

[toc]

---

## HTTP authentication

![Basic authentication](https://i.imgur.com/W4lnwIN.png)

- the simplest possible way to enforce access control
  - as it `doesn't require cookies, sessions or anything else`.
- the client
  - provide username and password when making a request.
  - has to send the `Authorization` header along with every request it makes.
- the exchange must happen over an **HTTPS (TLS) connection** to be secure.

The username and password are not encrypted, but constructed this way:
1. username and password are concatenated into a single string: `username:password`
2. this string is encoded with `Base64`
3. the Basic keyword is put before this encoded value


### Basic authentication step:

![HTTPAuth](https://i.imgur.com/HMO7vyi.png)

1. client access come protected URL.
2. server check the request has `Authorization header` with `valid usrname and passwd`
   - The server responds to a client with a `401 (Unauthorized) response status` and provides information on how to authorize with a WWW-Authenticate response header containing at least one challenge.
     - `401 Unauthorized`: invalid, authentication is impossible for this user.
     - `200 OK`: exist and vaild
     - `403 forbidden`: valid credentials that are inadequate to access a given resource
     - `407 (Proxy Authentication Required)`: authentication is impossible for this user.
   - realm:
     - protection space:
     - group of pages use the same credential.
     - browser can cache the calid credentials for given realm and use them in future
   - "free text":
     - server is responsible for defininf realms and do the authentication
3. browser notice the `WWW-Authenticate` header in response:
   - show the window, presents the alert for credentials
4. user submit username and passwd.
5. browser encode it with  `base64` and sends in the next request
   - Browsers use `utf-8` encoding for usernames and passwords.
   - `base64("username:passwd")`
   - send a `Authorization` request header with the credentials.
6. server do step 2 again

```
1. browser
https://some.url

2. server
HTTTP/1.1 401 Unauthorized
Date: Sat, 16 May 2020 16:50:53 GMT
WWW-Authenticate: Basic realm="MyApp"

3. browser show window

4. user input credential

5. browser encode with base64 and send it
Authorization: Basic 123456789==

6. server do step 2 again
```


Example

```
curl --header "Authorization: Basic am9objpzZWNyZXQ=" my-website.com
```

The same can be observed in Chrome as well:

![google_chrome_basic_web_authentication_method-1448359567226](https://i.imgur.com/V1x3yw7.png)

Implementing in `Node.js`

```js
import basicAuth from 'basic-auth';

function unauthorized(res) {  
  res.set('WWW-Authenticate', 'Basic realm=Authorization Required');
  return res.send(401);
};

export default function auth(req, res, next) {
  const {name, pass} = basicAuth(req) || {};

  if (!name || !pass) {
    return unauthorized(res);
  };

  if (name === 'john' && pass === 'secret') {
    return next();
  }
  return unauthorized(res);
};
```

### drawbacks of Basic authentication
1. the **username and password are sent with every request**
   - not secure unless used with TLS/HTTPS.
     - anyone can eavesdrop and decode the credentials.
   - potentially exposing them
   - even sent via a secure connection connected to SSL/TLS, if a website uses weak encryption, or an attacker can break it, the usernames and passwords will be exposed immediately
2. **no way to log out** the user using Basic auth
3. expiration of credentials is not trivial
   - have to ask the user to change password to do so


### Proxy authentication
The same challenge and response mechanism can be used for proxy authentication. 
- As both resource authentication and proxy authentication can coexist
- but different set of `headers` and `status codes` is needed. 

1. the challenging status code is `407 (Proxy Authentication Required)`
   - the `Proxy-Authenticate` response header 
     - contains at least one challenge applicable to the proxy
     - used for providing the credentials to the proxy server.

### Authentication of cross-origin images
security hole recently been fixed by browsers is `authentication of cross-site images`. 
- From Firefox 59
- **image resources loaded from different origins to the current document** are no longer able to trigger `HTTP authentication dialogs` (bug 1423146), 
- preventing user credentials being stolen if attackers were able to embed an arbitrary image into a third-party page.


### server: `WWW-Authenticate` and `Proxy-Authenticate` headers
The `WWW-Authenticate` and `Proxy-Authenticate` response headers **define the authentication method that should be used** to gain access to a resource. 
- They must specify which authentication scheme is used, so that the client that wishes to authorize knows how to provide the credentials.

The syntax for these headers is the following:

```html
WWW-Authenticate: <type> realm=<realm>
Proxy-Authenticate: <type> realm=<realm>
```

`<type>` is the **authentication scheme**
- `Basic` : the most common scheme and introduced below
  
`realm` : describe the protected area or to indicate the scope of protection. 
- This could be a message like "Access to the staging site" or similar
- so that the user knows to which space they are trying to get access to.


### client: `Authorization` and `Proxy-Authorization` headers

The `Authorization` and `Proxy-Authorization` request headers
- contain the credentials to authenticate a user agent with a (proxy) server
- `<type>` is needed again
- credentials: be encoded or encrypted depending on which authentication scheme is used.

```html
Authorization: <type> 12345678
Proxy-Authorization: <type> 123456
```

## Authentication schemes
The general HTTP authentication framework is used by several authentication schemes. 
- Schemes can differ in security strength and in their availability in client or server software.
- there are other schemes offered by host services, such as Amazon AWS.

Schemes | Note
---|---
`Basic` | RFC 7617, **base64-encoded** credentials. 
`Bearer` | See RFC 6750, bearer tokens to access OAuth 2.0-protected resources
`Digest` | See RFC 7616, only md5 hashing is supported in Firefox, see bug 472823 for SHA encryption support
`HOBA` | See RFC 7486, Section 3, HTTP Origin-Bound Authentication, digital-signature-based
`Mutual` | See RFC 8120
`AWS4-HMAC-SHA256` | See AWS docs


### `Basic authentication scheme`
- transmits credentials as user ID/password pairs, encoded using `base64`.

#### Security of basic authentication
the user ID and password are passed over the network as clear text
- base64 encoded, but is a reversible encoding
- the `basic authentication scheme` is not secure. 
- HTTPS/TLS should be used for basic authentication. 
  - Without additional security enhancements
  - basic authentication should not be used to protect sensitive or valuable information.

#### Restricting access with `Apache` and basic authentication
To password-protect a directory on an Apache server
- need a `.htaccess` and a `.htpasswd` file.

```
.htaccess file:

AuthType Basic
AuthName "Access to the staging site"
AuthUserFile /path/to/.htpasswd
Require valid-user
```

```
.htpasswd file :

aladdin:$apr1$ZjTqBB3f$IF9gdYAGlMrs2fuINjHsz.
user2:$apr1$O04r.y2H$/vEkesPhVInBByJUkXitA/
```

`.htpasswd file`:
- each line consists of a username and a password 
- separated by a colon (:). 
- the passwords are hashed (MD5-based hashing)
- can name the `.htpasswd file` differently
  - but keep in mind this file shouldn't be accessible to anyone. 
  - Apache usually configured to prevent access to `.ht* files`


#### Restricting access with `nginx` and basic authentication
1. a `location` going to protect 
2. the `auth_basic directive`: provides the name to the password-protected area. 
3. The `auth_basic_user_file directive` : points to a `.htpasswd file` containing the encrypted user credentials, just like Apache

```
location /status {                                       
    auth_basic           "Access to the staging site";
    auth_basic_user_file /etc/apache2/.htpasswd;
}
```

#### Access using `credentials in the URL`
- Many clients can avoid the login prompt by using an `encoded URL` containing the credentials

`https://username:password@www.example.com/`

The use of these URLs is deprecated. 
- In Chrome, the `username:password@` part in URLs is even stripped out for security reasons. 
- In Firefox, it is checked if the site actually requires authentication and if not, 
  - Firefox will warn the user with a prompt 
  - "You are about to log in to the site “www.example.com” with the username “username”, but the website does not require authentication. This may be an attempt to trick you."

---