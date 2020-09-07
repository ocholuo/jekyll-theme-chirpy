
# Project - WheelofPython

[toc]

From the Python by University of Michigan in Coursera: [Worked Example: BeautifulSoup (Chapter 12)](https://www.coursera.org/learn/python-network-data/lecture/S4FIR/worked-example-beautifulsoup-chapter-12)


---

## Assignment

1. install the BeautifulSoup

`pip install beautifulsoup4`

2. make the Soup

```py
import urllib.request, urllib.parse, urllib.error
import ssl
from bs4 import BeautifulSoup

ctx = ssl.create_default_context()   # for https
ctx.check_hostname = False
ctx.verify_mode = ssl.CERT_NONE

url = input('Enter - ')  #http://www.dr-chuck.com/
html = urllib.request.urlopen(url, context=ctx).read() # return entire website in a single string
soup = BeautifulSoup(html, 'html.parser')

# retrieve all of the anchor tags
tags = soup('a')
for tag in tags:
    print(tag.get('href', None))

# result
Enter - http://www.dr-chuck.com/
https://www.dr-chuck.com/csev-blog/
https://www.si.umich.edu/
https://www.ratemyprofessors.com/ShowRatings.jsp?tid=1159280
https://www.dr-chuck.com/csev-blog/
https://www.twitter.com/drchuck/
https://www.dr-chuck.com/dr-chuck/resume/speaking.htm
https://www.slideshare.net/csev
/dr-chuck/resume/index.htm
https://amzn.to/1K5Q81K
```