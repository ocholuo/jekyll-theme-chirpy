numOrders= 6
orderList = [
  ["zld","93","12"],
  ["fp","kindle","book"],
  ["10a","echo","show"],
  ["17g","12","25","6"],
  ["ab1","kindle","book"],
  ["125","echo","dot","second","generation"]
]

# numOrders= 4
# orderList = [
#   ["mi2","jog","mid","pet"],
#   ["wz3","34","54","398"],
#   ["al","alps","cow","bar"],
#   ["x4","45","21","7"]
# ]



# Divide into seperate list O(n)
nonprimelist,primelist = [],[]
for i in orderList:
  if (''.join(i[1:])).isdigit():
    nonprimelist.append(i)
  else:
    primelist.append(i)

# Sorting with multiple keys O(n*log(n))
primelist.sort(key=lambda x:(' '.join(x[1:]), x[0]))
print(primelist + nonprimelist)