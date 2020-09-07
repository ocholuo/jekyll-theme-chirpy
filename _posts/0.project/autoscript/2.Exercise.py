
# 2. Exercise: Using the 'os' Package and Environment Variables
# from os import getenv
# from math import pi

innumber = os.getenv("DIGITS")
if innumber==None:
    print("%.*f" % (10, pi))
else:
    print("%.*f" % (int(innumber), pi))

digits = int(getenv("DIGITS") or 10)
print("%.*f" % (digits, pi))