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
3. 新建完毕后，为项目开启 API 和服务。<kbd>+ ENABLE APIS AND SERVICES</kbd> 进入API Library
4. 搜索栏中搜关键词 “analytic” 即可找到`Analytics API`，点击 `Enable`
5. 开启 API 后页面会自动回到 Dashboard，根据 ⚠️ 信息提示点击 `Create credentials` 为 API 创建 credentials。
6. 创建页面作如下操作：
   1. findout what kind of credentials needed:
      1. Which API are you using? `Google Analytics API`
      2. Where will you be calling the API from? `Web brower(Javascript)`
      3. What data will you be accessing? `User data`
   2. Create an OAuth 2.0 client ID
      1. Client ID 自定义命名: `blog-oauth`
      2. Restrictions 两项暂时留空，往后将会写入 GAE 的项目地址。
   3. Set up the OAuth 2.0 consent screen
      1. Email 保持默认值
      2. 产品名称自定义命名，不与其他公司产品重名即可，例笔者为 cotes-blog-ga
   4. Download credentials
      1. 视个人需要决定下载与否，
      2. `Client ID	318175415936-rdlkiaaf422e7kuenfq3blrnv0s5rn64.apps.googleusercontent.com`
      3. 供 SuperProxy 使用的 Client ID，Client secret 都可以在 Dashboard 直接查看。
   5. `完成后即可生成新 OAuth 2.0 client ID`:

# 下载配置 SuperProxy

安装 Python 27


安装 Cloud SDK for Python

```bash
$ python -V
Python 2.7.16
# Cloud SDK requires Python. Supported versions are 3.5 to 3.7, and 2.7.9 or higher.

# download the install filecd 
./google-cloud-sdk/install.sh
./google-cloud-sdk/bin/gcloud init
```

# deploy



















.