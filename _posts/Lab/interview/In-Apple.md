



```py
def lambda_handler(event, context):
    # test one regions example
    regions = ['us-east-1']

    # set the region
    region =

    # set the client
    client = boto3.client('ec2', region)

    response = client.enable_ebs_encryption_by_default()


    # result =

    print("Default EBS Encryption setup for region", region,": ", response['EbsEncryptionByDefault'])

    return {
        'statusCode': 200,
        'body': json.dumps(event)
    }
```

```py
# If I was to give you 2 ordered sets of any data type you like (array, set, list, etc),
# such as 2,4,6,8 and 3,5,7,9 can you combine them into 1 ordered set so the final set
# is ordered 2,3,4,5,6,7,8,9.

# Extended challenge: can you write the logic to integrate the two sets of ordered numbers
# manually, so that while they are being merged they are in order (eg whever you are in
# merging the sets, the final set remains in incremental order)

def sortThem():
  #  example:
  a = [2,4,6,8]
  b = [3,5,7,9]
  # a = a+b
  # a.sort()
  # print(a)

  c = []

  i = 0
  j = 0

  while i < len(a):
    if (a[i] < b[j]):
      # print("a is smaller")
      # c[i]=a[i] #c.add(a[i])
      c.append(a[i])
      i+=1
    else:
      c.append(b[j])
      j+=1
    print(i,j)

  c = c + a[i:] + b[j:]

  print(c)

sortThem()

```
