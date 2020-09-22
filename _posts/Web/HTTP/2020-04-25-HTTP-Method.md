---
title: HTTP Referer
# author: Grace JyL
date: 2020-04-25 11:11:11 -0400
description:
excerpt_separator:
categories: [Web, HTTP]
tags: [Web, HTTP]
math: true
# pin: true
toc: true
# image: /assets/img/sample/devices-mockup.png
---


# 4.3 HTTP Method

[toc]

---

## 4.3.1.  GET

![Screen Shot 2020-05-11 at 22.33.45](https://i.imgur.com/Ia7TTNV.png)

Syntax
- `GET /index.html`

The `GET` method
- **requests transfer of a current representation for the target resource**.  
  - Requests using `GET` should **only retrieve data**.
  - Not secure, content will be in the URL.
  - the primary mechanism of information retrieval and the focus of performance optimizations.
    - when speak of retrieving some information via HTTP, it is generally making a `GET` request.
- It is tempting to think of
  - resource identifiers as remote file system pathnames
  - representations as **a copy of the contents of such files**.  
  - In fact, that is how many resources are implemented.  However, there are no such limitations in practice.  

- The HTTP interface for a resource is like a tree of content objects, a programmatic view on various database records, a gateway to other information systems.  
- Even when the URI mapping mechanism is tied to a file system, an origin server might be configured to **execute the files with the request as input** and **send the output as the representation** rather than transfer the files directly.
- only the origin server needs to know
  - how each of its resource identifiers corresponds to an implementation
  - how each implementation manages to select
  - send a current representation of the target resource in a response to `GET`.

- A client can alter the semantics of `GET` to be a "range request",
  - requesting transfer of only some part(s) of the selected representation, by sending a Range header field in the request ([RFC7233]).
- A payload within a `GET` request message has no defined semantics;
  - sending a payload body on a `GET` request might cause some existing implementations to reject the request.
- The response to a `GET` request is cacheable;
  - a cache MAY use it to satisfy subsequent `GET` and `HEAD` requests unless otherwise indicated by the Cache-Control header field (Section 5.2 of [RFC7234]).


---

## 4.3.2.  HEAD

![Screen Shot 2020-05-11 at 22.39.27](https://i.imgur.com/c8yX1pS.png)

Syntax
- `HEAD /index.html`


Method | Body | header
---|---|---
`GET` | server MUST NOT send a message body in the response <br> the response terminates at the end of the header section |
`HEAD` | `HEAD` response should not have a `body`. If so, it must be ignored. | server SHOULD send the same header fields in response to a `HEAD` request as for `GET` request <br> except the payload header fields MAY be omitted.  


- A response to a `HEAD` method should not have a `body`. If so, it must be ignored.
  - Even so, entity headers describing the content of the body, like Content-Length may be included in the response.
  - They don't relate to the body of the `HEAD` response (should be empty), but to the body which a similar request using the `GET` method would have returned as a response.

- If the result of a `HEAD` request shows that a cached resource after a `GET` request is now outdated
  - the cache is invalidated, even if no `GET` request has been made.

- This method can be used for **obtaining metadata about the selected representation without transferring the representation data**
  - often used for testing hypertext links for validity, accessibility, and recent modification.
  - 允许客户端在未获取实际资源的情况下，对资源的首部进行检查。
  - 在不获取资源的情况下了解资源的情况(比如，判断其类型);
  - 通过查看响应中的状态码，看看某个对象是否存在;
  - 通过查看首部，测试资源是否被修改了。

- A payload within a `HEAD` request message has no defined semantics;
  - sending a payload body on a `HEAD` request might cause some existing implementations to reject the request.


- The response to a `HEAD` request is cacheable;
  - a cache MAY use it to satisfy subsequent `HEAD` requests unless otherwise indicated by the Cache-Control header field (Section 5.2 of [RFC7234]).  

- A `HEAD` response might also have an effect on previously cached `GET` responses.
- 服务器开发者必须确保返回的首部与 `GET` 请求所返回的首部完全相同。遵循 HTTP/1.1 规范，就必须实现 `HEAD` 方法。


---

## 4.3.3.  POST

￼￼![Screen Shot 2020-05-11 at 22.55.32](https://i.imgur.com/SpO6ed9.png)

Syntax
- `POST /test`

The `POST` method
- **requests that the target resource process the representation enclosed in the request** according to the resource's own specific semantics.  
- sends data to the server.

- The type of the body of the request is indicated by the `Content-Type` header.



- `POST` is designed to allow a uniform method to cover the following functions:
  - Providing a block of data
    - such as the fields entered into an HTML form, to a data-handling process;
  - Posting a message
    - to a bulletin board, newsgroup, mailing list, blog, or similar group of articles;
  - Creating a new resource that has yet to be identified by the origin server
  - Appending data to a resource's existing representation(s).
  - Annotation of existing resources
  - Adding a new user through a signup modal;
  - Extending a database through an append operation.

The difference between `PUT` and `POST`
- `PUT` is idempotent: calling it once or several times successively has the same effect (no side effect),
- successive identical `POST` may have additional effects, like passing an order several times.

Responses to `POST`:
- origin server indicates response semantics by appropriate status code depending on the result of the `POST` request;
- almost all of the status codes might be received in a response to `POST`
  - (the exceptions being 206 (Partial Content), 304 (Not Modified), and 416 (Range Not Satisfiable)).

- If **resources has been created** on the origin server by processing a `POST` request,
  - the origin server SHOULD send a `201 (Created) response` containing
  - a `Location header` field that provides an identifier for the primary resource created
  - and a `representation` describes the status of the request while referring to the new resource(s).

- If the result of processing a `POST` would be equivalent to a representation of an existing resource
  - an origin server MAY redirect the user agent to that resource by sending a `303 (See Other) response` with the existing resource's identifier in the Location field.  
  - This has the benefits of providing the user agent a resource identifier and transferring the representation via a method more amenable to shared caching, though at the cost of an extra request if the user agent does not already have the representation cached.

> Responses to `POST` requests are only cacheable when they include explicit freshness information.
> However, `POST` caching is not widely implemented.  

- For cases where an origin server wishes the client to be able to **cache the result** of a `POST` in a way that can be reused by a later `GET`,
  - the origin server MAY send a `200 (OK) response` containing
  - the result
  - and a `Content-Location` header field that has the same value as the POST's effective request URI (Section 3.1.4.2).

A `POST` request is typically sent via an **HTML form** and results in a change on the server.
- the `content type` is selected by putting the adequate string in
  - the `enctype` attribute of the `<form>` element
  - or the `formenctype` attribute of the `<input>` or `<button>` elements:
- `application/x-www-form-urlencoded`:
  - the keys and values are encoded in key-value tuples separated by `'&'`, with a `'='` between the key and the value.
  - Non-alphanumeric characters in both keys and values are percent encoded:
  - this is the reason why this type is not suitable to use with binary data (use multipart/form-data instead)
- `multipart/form-data`:
  - each value is sent as a block of data ("body part"), with a user agent-defined delimiter ("boundary") separating each part.
  - The keys are given in the `Content-Disposition` header of each part.
- `text/plain`

When the `POST` request is sent via method other than an HTML form — like **XMLHttpRequest** — the body can take any type.

Example

A simple form using the default `application/x-www-form-urlencoded` content type:

```
POST /test HTTP/1.1
Host: foo.example
Content-Type: application/x-www-form-urlencoded
Content-Length: 27
field1=value1&field2=value2
```

A form using the `multipart/form-data` content type:

```
POST /test HTTP/1.1
Host: foo.example
Content-Type: multipart/form-data;boundary="boundary"
--boundary
Content-Disposition: form-data; name="field1"
value1
--boundary
Content-Disposition: form-data; name="field2"; filename="example.txt"
value2
--boundary--
```

---

## 4.3.4.  PUT

![Screen Shot 2020-05-11 at 22.57.41](https://i.imgur.com/jm6mU3S.png)

Syntax
- `PUT /new.html HTTP/1.1`

URI = Universal Resource Identifier

URL = Universal Resource Locator

The `PUT` method
- requests the state of the target resource be **created or replaced with the state defined by the representation enclosed in the request message payload**.  

Request

```
PUT /new.html HTTP/1.1
Host: example.com
Content-type: text/html
Content-length: 16
<p>New File</p>
```

Responses
- A successful response only implies that the user agent's intent was achieved at the time of its processing by the origin server.
  - A successful `PUT` of a given representation would suggest that a subsequent `GET` on that same target resource will result in an equivalent representation being sent in a `200 (OK) response`.  
  - However, there is no guarantee that such a state change will be observable, since the target resource might be acted upon by other user agents in parallel, or might be subject to dynamic processing by the origin server, before any subsequent `GET` is received.  

If the target resource
- **does not have** a current representation and the `PUT` successfully creates one
   - the origin server MUST inform the user agent by sending a `201 (Created) response`.  
      ```
      HTTP/1.1 201 Created
      Content-Location: /new.html
      ```

- **does have a current representation** and that representation is successfully modified in accordance with the state of the enclosed representation
  - the origin server MUST send either a `200 (OK) / 204 (No Content) response` to indicate successful completion of the request.
    ```
    HTTP/1.1 204 No Content
    Content-Location: /existing.html
    ```

- origin server SHOULD **ignore unrecognized header fields received** in a `PUT` request
  - do not save them as part of the resource state

- origin server SHOULD verify that the `PUT` representation is **consistent with any 参数 constraints the server has for the target resource** that cannot or will not be changed by the `PUT`.  
  - particularly important when the origin server uses internal configuration information related to the URI to set the values for representation metadata on `GET` responses.  
  - When a `PUT` representation is inconsistent 不一致的 with the target resource
    - For example
      - if the target resource is configured to always have a Content-Type of "text/html"
      - and the representation being `PUT` has a Content-Type of "image/jpeg"
    - the origin server SHOULD
      - make them consistent, by transforming the representation or changing the resource configuration
      - transform the `PUT` representation to a format consistent with that of the resource before saving it as the new resource state;
      - reconfigure the target resource to reflect the new media type;
      - or respond with an appropriate error message.
      - containing sufficient information to explain why the representation is unsuitable.  
      - The `409 (Conflict) or 415 (Unsupported Media Type) status codes` are suggested, with the latter being specific to constraints on Content-Type values.
      - reject the request with a `415 (Unsupported Media Type) response` indicating that the target resource is limited to "text/html", perhaps including a link to a different resource that would be a suitable target for the new representation.

- HTTP does not define exactly how a `PUT` method affects the state of an origin server beyond what can be expressed by the intent of the user agent request and the semantics of the origin server response.  
- It does not define what a resource might be, in any sense of that word, beyond the interface provided via HTTP.  
- It does not define how resource state is "stored", nor how such storage might change as a result of a change in resource state, nor how the origin server translates resource state into representations.  
- Generally speaking, all implementation details behind the resource interface are intentionally hidden by the server.

An origin server MUST NOT send a validator header field, such as an ETag or Last-Modified field, in a successful response to `PUT`
- unless the request's representation data was saved without any transformation applied to the body (i.e., the resource's new representation data is identical to the representation data received in the `PUT` request) and the validator field value reflects the new representation.  

This requirement allows a user agent to know when the representation body it has in memory remains current as a result of the PUT, thus not in need of being retrieved again from the origin server, and that the new validator(s) received in the response can be used for future conditional requests in order to prevent accidental overwrites.


POST和PUT请求最根本的区别是 Request-URI 的含义不同。
- POST请求里的URI指示一个能处理请求实体的资源：
  - 资源可能是一段程序，如jsp里的servlet），一个数据接收过程，一个网关（gateway，译注：网关和代理服务器的区别是：网关可以进行协议转换，而代理服务器不能，只是起代理的作用，比如缓存服务器其实就是一个代理服务器），或者一个单独接收注释的实体。
- PUT方法请求里有一个实体：
  - 用户代理知道URI意指什么，并且服务器不能把此请求应用于其他URI指定的资源。
  - 如果服务器期望请求被应用于一个不同的URI，那么它必须发送301（永久移动了）响应；
  - 用户代理可以自己决定是否重定向请求。

The fundamental difference between the `POST` and `PUT` methods:
- the different intent for the enclosed representation.
  - The target resource in a `POST` request is intended to handle the enclosed representation according to the resource's own semantics,
  - the enclosed representation in a `PUT` request is replacing the state of the target resource.  
- the intent of `PUT` is idempotent and visible to intermediaries, even though the exact effect is only known by the origin server.
  - calling it once or several times successively has the same effect (no side effect)



Proper interpretation of a `PUT` request presumes that the user agent knows which target resource is desired.
- A service that selects a proper URI on behalf of the client, after receiving a state-changing request, SHOULD be implemented using the `POST` method rather than PUT.
- If the origin server will not make the requested `PUT` state change to the target resource and instead wishes to have it applied to a different resource, such as when the resource has been moved to a different URI
  - then the origin server MUST send an appropriate 3xx (Redirection) response;
  - the user agent make its own decision regarding whether or not to redirect the request.

A `PUT` request applied to the target resource can have side effects on other resources.  
- For example
- an article have a URI for identifying "the current version" (a resource) that is separate from the URIs identifying each particular version (different resources that at one point shared the same state as the current version resource).  
- A successful `PUT` request on "the current version" URI might therefore create a new version resource in addition to changing the state of the target resource, and might also cause links to be added between the related resources.

An origin server that allows `PUT` on a given target resource MUST send a 400 (Bad Request) response to a `PUT` request that contains a Content-Range header field, since the payload is likely to be partial content that has been mistakenly `PUT` as a full representation.  
- Partial content updates are possible by targeting a separately identified resource with state that overlaps a portion of the larger resource, or by using a different method that has been specifically defined for partial updates (for example, the PATCH method defined in [RFC5789]).

PUT responses are not cacheable.  
- If a successful `PUT` request passes through a cache that has one or more stored responses for the effective request URI, those stored responses will be invalidated.

---

## 4.3.5.  DELETE
￼
![Screen Shot 2020-05-11 at 23.36.00](https://i.imgur.com/GMMJhgV.png)

Syntax
- `DELETE /file.html HTTP/1.1`

![1460000013229199](https://i.imgur.com/hEBTzvg.png)

The `DELETE` method
- requests that the origin server **remove the association between the target resource and its current functionality**.
  - similar to the rm command in UNIX:
  - a deletion operation on the URI mapping of the origin server rather than an expectation that the previously associated information be deleted.

**If the target resource has one or more current representations**, they might or might not be destroyed by the origin server, and the associated storage might or might not be reclaimed,
- depending entirely on the nature of the resource and its implementation by the origin server (which are beyond the scope of this specification).

**other implementation aspects of a resource might need to be deactivated or archived** as a result of a `DELETE`, such as database or gateway connections.  
- In general, it is assumed that the origin server will only allow `DELETE` on resources for which it has a prescribed mechanism for accomplishing the deletion.

Relatively few resources allow the `DELETE` method
- its primary use for remote authoring environments, where the user has some direction regarding its effect.  
- For example,
  - a resource that was previously created using a `PUT` request,
  - or identified via the `Location header` field after a `201 (Created) response` to a `POST` request
  - might allow a corresponding `DELETE` request to undo those actions.  
  - Similarly, custom user agent implementations that implement an authoring function, such as revision control clients using HTTP for remote operations, might use `DELETE` based on an assumption that the server's URI space has been crafted to correspond to a version repository.

Responses

If a `DELETE` method is successfully applied, the origin server SHOULD send
- a `202 (Accepted) status code` if the action will likely succeed but has not yet been enacted,
- a `204 (No Content) status code` if the action has been enacted and no further information is to be supplied,
- a `200 (OK) status code` if the action has been enacted and the response message includes a representation describing the status.

```
HTTP/1.1 200 OK
Date: Wed, 21 Oct 2015 07:28:00 GMT

<html>
	<body>
		<h1>File deleted.</h1>
	</body>
</html>
```

A payload within a `DELETE` request message has no defined semantics; sending a payload body on a` DELET`E request might cause some existing implementations to reject the request.

Responses to the `DELETE` method are not cacheable.  
- If a `DELETE` request passes through a cache that has one or more stored responses for the effective request URI, those stored responses will be invalidated.


---

## 4.3.6.  CONNECT

![Screen Shot 2020-05-12 at 00.08.41](https://i.imgur.com/sFqQGUi.png)

Syntax
- `CONNECT www.example.com:443 HTTP/1.1`

The `CONNECT` method
- a hop-by-hop method.
- requests that `the recipient establish a tunnel to the destination origin server` identified by the request-target, starts two-way communications with the requested resource.
  - if successful, thereafter restrict its behavior to blind forwarding of packets, in both directions, until the tunnel is closed.  
- Tunnels are commonly used to create an end-to-end virtual connection, through one or more proxies, which can then be secured using TLS (Transport Layer Security).

For example, the `CONNECT` method can be used to access websites that use SSL (HTTPS).
- The client asks an HTTP Proxy server to tunnel the TCP connection to the desired destination.
- The server then proceeds to make the connection on behalf of the client.
- Once the connection has been established by the server, the Proxy server continues to proxy the TCP stream to and from the client.


`CONNECT` is intended only for use in requests to a proxy.  
- An origin server that receives a `CONNECT` request for itself MAY respond with a `2xx (Successful) status code` to indicate that a connection is established.  
- However, most origin servers do not implement `CONNECT`.

A client sending a `CONNECT` request MUST send the authority form of request-target;
- i.e., the request-target consists of only the host name and port number of the tunnel destination, separated by a colon.  
- For example,

```
CONNECT server.example.com:80 HTTP/1.1
Host: server.example.com:80
```

**The recipient proxy can establish a tunnel either**
- by directly connecting to the request-target
- or, if configured to use another proxy, by forwarding the `CONNECT` request to the next inbound proxy.

Any `2xx (Successful) response` indicates that the sender (and all inbound proxies) will switch to tunnel mode immediately after the blank line that concludes the successful response's header section; data received after that blank line is from the server identified by the request-target.  

Any response other than a successful response indicates that the tunnel has not yet been formed and that the connection remains governed by HTTP.

A tunnel is closed when a tunnel intermediary detects that either side has closed its connection:
- the intermediary MUST attempt to send any outstanding data that came from the closed side to the other side, close both connections, and then discard any remaining data left undelivered.

Proxy authentication might be used to establish the authority to create a tunnel.  
- For example,

```
CONNECT server.example.com:80 HTTP/1.1
Host: server.example.com:80
Proxy-Authorization: basic aGVsbG86d29ybGQ=
```

significant risks in establishing a tunnel to arbitrary servers, well-known or reserved destination TCP port that is not for Web traffic.  
- For example,
- a `CONNECT` to a request-target of "example.com:25" would suggest that the proxy connect to the reserved port for SMTP traffic;
- if allowed, that could trick the proxy into relaying spam email.  
- Proxies that support `CONNECT` SHOULD restrict its use to a limited set of known ports or a configurable whitelist of safe request targets.

1. A server MUST NOT send any Transfer-Encoding or Content-Length header fields in a 2xx (Successful) response to `CONNECT`.  
2. A client MUST ignore any Content-Length or Transfer-Encoding header fields received in a successful response to `CONNECT`.

A payload within a `CONNECT` request message has no defined semantics;
- sending a payload body on a `CONNECT` request might cause some existing implementations to reject the request.

`CONNECT` Responses are not cacheable.


---

## 4.3.7.  OPTIONS

![Screen Shot 2020-05-12 at 00.30.38](https://i.imgur.com/WGG6NuB.png)

Syntax

```
OPTIONS /index.html HTTP/1.1
OPTIONS * HTTP/1.1
```

The `OPTIONS` method
- requests information about the **communication options available for the target resource**, at either the origin server or an intervening intermediary.  
  - allows a client to determine the options and/or requirements associated with a resource, or the capabilities of a server, without implying a resource action.

An `OPTIONS` request with an asterisk (`"*"`)
- the request-target applies to the server in general rather than to a specific resource.  
- Since a server's communication options typically depend on the resource, the `"*"` request is only useful as a "ping" or "no-op" type of method;
- it does nothing beyond allowing the client to test the capabilities of the server.  
- For example
  - this can be used to test a proxy for HTTP/1.1 conformance (or lack thereof).

If the `OPTIONS` request-target is not an asterisk
- the `OPTIONS` request applies to the options that are available when communicating with the target resource.


A server generating a successful response to `OPTIONS` SHOULD **send any header fields** that indicate `optional features implemented by the server and applicable to the target resource` (e.g., Allow), including potential extensions not defined by this specification.
- The response payload, if any
  - might also describe the communication options in a machine or human-readable representation.  
- A standard format for such a representation is not defined by this specification, but might be defined by future extensions to HTTP.  
- A server MUST generate a `Content-Length field` with a value of "0" if no payload body is sent in the response.

A client MAY send a `Max-Forwards header` field in an `OPTIONS` request to target a specific recipient in the request chain.  
A proxy MUST NOT generate a Max-Forwards header field while forwarding a request unless that request was received with a Max-Forwards field.

A client that generates an `OPTIONS` request containing a payload body MUST send a valid Content-Type header field describing the representation media type.  
- Although this specification does not define any use for such a payload, future extensions to HTTP might use the `OPTIONS` body to make more detailed queries about the target resource.

Responses to the `OPTIONS` method are not cacheable.

### Identifying allowed request methods
find out which request methods a server supports

1. use `curl` and issue an `OPTIONS` request:

```
curl -X OPTIONS http://example.org -i

// The response then contains an Allow header with the allowed methods:

HTTP/1.1 204 No Content
Allow: OPTIONS, GET, HEAD, POST
Cache-Control: max-age=604800
Date: Thu, 13 Oct 2016 11:45:00 GMT
Expires: Thu, 20 Oct 2016 11:45:00 GMT
Server: EOS (lax004/2813)
x-ec-custom-error: 1
```

2. Preflighted requests in CORS
- In CORS, a preflight request with the `OPTIONS` method is sent, server can respond whether it is acceptable to send the request with these parameters.
- The `Access-Control-Request-Method` header
  - notifies the server as part of a preflight request that when the actual request is sent,
  - it will be sent with a `POST` request method.
- The `Access-Control-Request-Headers` header
  - notifies the server that when the actual request is sent, it will be sent with a `X-PINGOTHER and Content-Type` custom headers.
  - The server now has an opportunity to determine whether it wishes to accept a request under these circumstances.

```
OPTIONS /resources/post-here/ HTTP/1.1
Host: bar.other
Accept: text/html,application/xhtml+xml,application/xml;q=0.9,*/*;q=0.8
Accept-Language: en-us,en;q=0.5
Accept-Encoding: gzip,deflate
Accept-Charset: ISO-8859-1,utf-8;q=0.7,*;q=0.7
Connection: keep-alive
Origin: http://foo.example
Access-Control-Request-Method: POST
Access-Control-Request-Headers: X-PINGOTHER, Content-Type
```

- The server responds with `Access-Control-Allow-Methods` and says that `POST, GET, and OPTIONS` are viable methods to query the resource in question.
- This header is similar to the Allow response header, but used strictly within the context of CORS.

```
HTTP/1.1 204 No Content
Date: Mon, 01 Dec 2008 01:15:39 GMT
Server: Apache/2.0.61 (Unix)
Access-Control-Allow-Origin: http://foo.example
Access-Control-Allow-Methods: POST, GET, OPTIONS
Access-Control-Allow-Headers: X-PINGOTHER, Content-Type
Access-Control-Max-Age: 86400
Vary: Accept-Encoding, Origin
Keep-Alive: timeout=2, max=100
Connection: Keep-Alive
```

---

## 4.3.8.  TRACE

![Screen Shot 2020-05-12 at 01.06.06](https://i.imgur.com/Ptp8Hxx.png)

Syntax
- `TRACE /index.html`

The `TRACE` method
- **requests a remote, application-level loop-back of the request message**. 
  - performs a message loop-back test along the path to the target resource
  - useful debugging mechanism.
- The final recipient of the request SHOULD reflect the message received, 
  - excluding some fields described below, back to the client as the message body of a `200 (OK) response` with a Content-Type of "message/http".  
- The final recipient is either the origin server or the first server to receive a `Max-Forwards` value of zero (0) in the request.

`TRACE` request, 
- A client MUST NOT generate header fields containing sensitive data that might be disclosed by the response.
  - do not send stored user credentials or cookies in a `TRACE` request.  
- The final recipient of the request exclude any request header fields that are likely to contain sensitive data when that recipient generates the response body.

`TRACE` allows the client to see what is being received at the other end of the request chain and use that data for testing or diagnostic information.  
- The value of the `Via` header field is of particular interest, since it acts as a trace of the request chain. 
- Use of the Max-Forwards header field allows the client to limit the length of the request chain, which is useful for testing a chain of proxies forwarding messages in an infinite loop.

A client MUST NOT send a message body in a `TRACE` request.

Responses to the `TRACE` method are not cacheable.

---

## PATCH

The HTTP `PATCH` request method 
- applies **partial modifications** to a resource.
- PATCH is somewhat analogous to the "update" concept found in CRUD (in general, HTTP is different than CRUD, and the two should not be confused).

A `PATCH` request is considered a set of instructions on how to modify a resource. 
- Contrast this with `PUT`; which is a **complete representation** of a resource.

A `PATCH` is not necessarily idempotent, although it can be. 
- Contrast with `PUT`, always idempotent. 
- For example
- if an auto-incrementing counter field is an integral part of the resource, then a `PUT` will naturally overwrite it (since it overwrites everything), but not necessarily so for `PATCH`.

`PATCH `(like `PUT`) may have side-effects on other resources.
- To find out whether a server supports `PATCH`
- a server can advertise its support by adding it to the list in the `Allow or Access-Control-Allow-Methods` (for CORS) response headers.
- Another (implicit) indication that `PATCH` is allowed, is the presence of the `Accept-Patch` header, which specifies the patch document formats accepted by the server.

Syntax

```
PATCH /file.txt HTTP/1.1
Example
Request
PATCH /file.txt HTTP/1.1
Host: www.example.com
Content-Type: application/example
If-Match: "e0023aa4e"
Content-Length: 100
[description of changes]
```

Response
- A successful response is indicated by any `2xx status code`.
- `204 response code` is used, because the response does not carry a payload body.
- A `200 response` could have contained a payload body.

```
HTTP/1.1 204 No Content
Content-Location: /file.txt
ETag: "e0023aa4f"
```

---













.
