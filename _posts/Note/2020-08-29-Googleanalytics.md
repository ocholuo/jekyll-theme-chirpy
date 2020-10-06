---
title: Google Analytics
date: 2020-08-29 11:11:11 -0400
description: IT Blog Pool
categories: [Note]
img: /assets/img/sample/rabbit.png
tags: [OnePage]
---

# Google Analytics

[toc]


---


# get app from github


Project ID
myochosite-291718





# setup and configure


# Google APIs 创建项目

用 Google 账户登陆 **Google APIs Dashboard**
1. `Create Project`新建一个 Project，如起名cotes-blog-ga，
2. “Location” 项默认为 No organization。
3. 新建完毕后，为项目开启 API 和服务。`+ ENABLE APIS AND SERVICES` 进入API Library
4. 搜索栏中搜关键词 “analytic” 即可找到`Analytics API`，点击 `Enable`
5. 开启 API 后页面会自动回到 Dashboard，根据 ⚠️ 信息提示点击 `Create credentials` 为 API 创建 credentials。
6. 创建页面作如下操作：
   1. Which API are you using? `Google Analytics API`
   2. Where will you be calling the API from? `Web brower(Javascript)`
   3. What data will you be accessing? `User data`
Create an OAuth 2.0 client ID
Client ID 自定义命名，笔者为 blog-oauth
Restrictions 两项暂时留空，往后将会写入 GAE 的项目地址。
Set up the OAuth 2.0 consent screen
Email 保持默认值
产品名称自定义命名，不与其他公司产品重名即可，例笔者为 cotes-blog-ga
Download credentials
视个人需要决定下载与否，供 SuperProxy 使用的 Client ID，Client secret 都可以在 Dashboard 直接查看。




# deploy



















.