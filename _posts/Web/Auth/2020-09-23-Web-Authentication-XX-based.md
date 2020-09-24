---
title: Web Authentication - Concept Explained
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

# Web Authentication - Concept Explained

[toc]

---

## session-based authentication
1. the server `creates and stores the session data` in the **server memory** when the user logs in 
2. and then `stores the session id` in a **cookie on the user browser**.
3. The `session Id` is then sent on subsequent requests to the server
4. the server compares it with the `stored session data` and proceeds to process the requested action.








.