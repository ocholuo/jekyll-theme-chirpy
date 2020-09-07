
# [link](https://linuxacademy.com/cp/exercises/view/id/712/module/168)


# 5. Exercise: Interacting with External Commands
# It’s not uncommon for a process to run on a server and listen to a port. Unfortunately, you sometimes don’t want that process to keep running, but all you know is the port that you want to free up.
# write a script to make it easy to get rid of those pesky processes.
# Write a script that does the following:
# - Takes a port_number as its only argument.
# - Calls out to lsof to determine if there is a process listening on that port.
#   - If there is a process, kill the process and inform the user.
#   - If there is no process, print that there was no process running on that port.

# Python’s standard library comes with an HTTP server to start a server listening on a port 5500:
# python -m http.server 5500

# install lsof
# sudo yum install -y lsof
# lsof -n -i4TCP:PORT_NUMBER


import subprocess
import os
from argparse import ArgumentParser

parser = ArgumentParser(description='kill the running process listening on a given port')
parser.add_argument("-port", "-p", help="prot number")
# args = parser.parse_args
port = parser.parse_args().port

try:
    result = subprocess.run(
            ['lsof', '-n', "-i4TCP:%s" % port],
            stdout=subprocess.PIPE,
            stderr=subprocess.PIPE)
except subprocess.CalledProcessError:
    print(f"No process listening on port {port}")
    exit(1)
else:
    listening = None
    for line in result.stdout.splitlines():
        if "LISTEN" in str(line):
            listening = line
            break

    if listening:
        # PID is the second column in the output
        pid = int(listening.split()[1])
        os.kill(pid, 9)
        print(f"Killed process {pid}")
    else:
        print(f"No process listening on port {port}")
        exit(1)


