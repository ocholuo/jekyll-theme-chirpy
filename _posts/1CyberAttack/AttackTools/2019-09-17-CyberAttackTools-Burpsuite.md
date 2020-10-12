---
title: Meow's CyberAttackTools - Burpsuite
date: 2019-09-17 11:11:11 -0400
categories: [CyberAttack, CyberAttackTools]
tags: [CyberAttack, CyberAttackTools]
toc: true
image:
---

# Burpsuite

[toc]

---

## XSS Exploitation

1. execute payload as `<script>alert(“hello”)</script>`


# install burpsuite


[file](https://drive.google.com/drive/folders/1YKAeBIXPeUFW78buqRlJ9Yg1Ln6W2f1R)

```bash
# Install homebrew

rm -rf /Library/Java/JavaVirtualMachines/adoptopenjdk-14.jdk

# Tap homebrew/cask-versions
brew tap homebrew/cask-versions

# install java8:
brew cask install adoptopenjdk8

# Check if java8 is successfully installed or not
/usr/libexec/java_home -verbose

# cd to the file
cd /Applications/Burp\ Suite\ Community\ Edition.app/Contents/java/app 

# Window1:
/Library/Java/JavaVirtualMachines/adoptopenjdk-14.jdk/Contents/Home/bin/java -jar burp-loader-keygen-2_1_07.jar

-jar burp-loader-keygen.jar

# add file
-Xbootclasspath/p:burp-loader-keygen-2_1_07.jar

# Window2:
/Library/Java/JavaVirtualMachines/adoptopenjdk-14.jdk/Contents/Home/bin/java -jar burpsuite_pro_v2.1.07.jar

/Library/Java/JavaVirtualMachines/liberica-jdk-8.jdk/Contents/Home/bin/java -jar burpsuite_pro_v1.7.31


# Create a Bash Alias
# If you are missing a .bash_profile
burp2.1()
{
/Library/Java/JavaVirtualMachines/adoptopenjdk-14.jdk/Contents/Home/bin/java -jar /Applications/Burp\ Suite\ Community\ Edition.app/Contents/java/app/burpsuite_pro_v2.1.07.jar
}

# Run
source ~/.bash_profile
# or
source ~/.zshrc

# Now you can type burp2.1 in terminal to open Burp Suite directly
```

[link](https://www.cnblogs.com/shaosks/p/13367761.html)
[link](https://github.com/raystyle/BurpSuite_Pro_v1.7.32)
[link](https://resources.infosecinstitute.com/burpsuite-tutorial/)






.
