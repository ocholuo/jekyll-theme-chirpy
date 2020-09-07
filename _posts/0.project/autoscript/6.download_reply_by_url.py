

# [link](https://linuxacademy.com/cp/exercises/view/id/712/module/168)

# 5. Exercise: Utilizing Third-Party Packages
# have the requests package installed. 
# write a script that does the following:
# - Accepts a URL and destination file name from the user calling the script.
# - Utilizes requests to make an HTTP request to the given URL.
# - Has an optional flag to state whether or not the response should be JSON or HTML (HTML by default).
# - Writes the contents of the page out to the destination.
# Note: You’ll want to use the text attribute to get the HTML.

import sys
import json
import request
from argparse import ArgumentParser

parser = ArgumentParser()
parser.add_argument('url', help='the URL to store the contents of')
parser.add_argument('filename', help='the filename to store the content under')
parser.add_argument('-content-type', '-c', 
                    default='html', choices=['html', 'json'], 
                    help='the content-type of the URL being requested')
args = parser.parse_args()

res = request.get(args.url)

if res.status_code >= 400:
    print(f"Error code received: {res.status_code}")
    sys.exit(1)

if args.content_type == 'json':
    try:
        content = json.dumps(res.json())
    except ValueError:
        print("Error: Content is not JSON")
        sys.exit(1)
else:
    content = res.text


with open(args.file, 'w', encoding='UTF-8') as f:
    f.write(content)
    f.close()
    print(f"Content written to '{args.filename}'")

with open(args.file, 'r') as f:
    a=f.read()
    print(a)
