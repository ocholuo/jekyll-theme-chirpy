

# invite

1. open the Chrome Developers Tools.
2. Go through the elements tab and you will find a script with source (src) as: /js/inviteapi.min.js
3. go to https://www.hackthebox.eu/js/inviteapi.min.js .
4. makeInviteCode looks interesting. go back to https://www.hackthebox.eu/invite and try to find its contents.
5. Goto console tab in Chrome Developer Tools, and type `makeInviteCode()`. You will get a 200 Success status and data as shown below.
6. the text is encrypted and the encoding type is ROT13.
7. decode that message
8. need to make a POST request to “https://www.hackthebox.eu/api/invite/generate”.
9. `curl -XPOST https://www.hackthebox.eu/api/invite/generate`
10. success message as:`{“success”:1,”data”:{“code”: “somerandomcharacters12345”, “format”: “encoded”}, “0”:200}`
11. decoding it

XRCDC-VBPZS-ROGKG-OUDBT-WTHIW











.