


[toc]


# Boto3


## Installation

```bash
pip install boto3
pip3 install boto3 botostubs
pip install boto3==1.0.0

$ aws configure
$ aws configure --profile produser
AWS Access Key ID [None]: aa
AWS Secret Access Key [None]: bb/2Zp9Utk/h3yCo8nvbEXAMPLEKEY
Default region name [None]: us-east-1
Default output format [None]: json


~/.aws/credentials
```


## Configuration

Before you can begin using Boto3, set up authentication credentials.

```bash
# If you have the AWS CLI installed, then you can use it to configure credentials file:
aws configure

# can create the credential fileself.
# By default, its location is at ~/.aws/credentials:
[default]
aws_access_key_id =_ACCESS_KEY
aws_secret_access_key =_SECRET_KEY

# You may also want to set a default region.
# This can be done in the configuration file. By default, its location is at ~/.aws/config:
[default]
region=us-east-1

# Alternatively, you can pass a region_name when creating clients and resources.

# This sets up credentials for the default profile as well as a default region to use when creating connections.
```


## Using Boto3


```py
# import it and tell it what service you are going to use:

import boto3

# use Amazon S3
s3 = boto3.resource('s3')

# Now that you have an s3 resource, you can make requests and process responses from the service.
# The following uses the buckets collection to print out all bucket names:
# Print out bucket names
for bucket in s3.buckets.all():
    print(bucket.name)


# It's also easy to upload and download binary data.
# For example, the following uploads a new file to S3.
# It assumes that the bucket my-bucket already exists:
# Upload a new file
data = open('test.jpg', 'rb')
s3.Bucket('my-bucket').put_object(Key='test.jpg', Body=data)
```









---

# AWS SQS `boto3.resource('sqs')`
SQS allows you to queue and then process messages. This tutorial covers how to create a new queue, get and use an existing queue, push new messages onto the queue, and process messages from the queue by using Resources and Collections.

Queues are created with a name. You may also optionally set queue attributes, such as the number of seconds to wait before an item may be processed.


```py
sqs = boto3.resource('sqs')

queue = sqs.create_queue(QueueName='test', Attributes={'DelaySeconds': '5'})    # queue

for queue in sqs.queues.all()


# Get the queue. This returns an SQS.Queue instance
queue = sqs.get_queue_by_name(QueueName='test')
    queue.url
    queue.attributes['QueueArn'].split(':')[-1]
    queue.attributes.get('DelaySeconds')
    queue.send_message(MessageBody='world')
        response = queue.send_message(MessageBody='world')
        response = queue.send_message(MessageBody='boto3', MessageAttributes={ 'ABC': {'StringValue': 'abc','DataType': 'String'}} )
        response = queue.send_messages(Entries=[{'Id': '1','MessageBody': 'hello'}, {'Id': '2','MessageBody': 'world'}] )
            response.get('MessageId'))
            response.get('MD5OfMessageBody')
            response.get('Failed')

    queue.receive_messages(MessageAttributeNames=['ABC']):
        for message in queue.receive_messages(MessageAttributeNames=['ABC'])
            message.message_attributes
            message.message_attributes.get('ABC').get('StringValue')
            message.delete()

```




```py

# ------------------------- Creating a queue -------------------------
# get the SQS service resource
sqs = boto3.resource('sqs')

# Create the queue. This returns an SQS.Queue instance
queue = sqs.create_queue(QueueName='test', Attributes={'DelaySeconds': '5'})

#  now access identifiers and attributes
print(queue.url)
print(queue.attributes.get('DelaySeconds'))
# The code above may throw an exception if you already have a queue named test.




# ------------------------- Using an existing queue -------------------------
# Get the queue. This returns an SQS.Queue instance
queue = sqs.get_queue_by_name(QueueName='test')


# list all of existing queues:
# Print out each queue name, which is part of its ARN
for queue in sqs.queues.all():
    print(queue.url)


# To get the name from a queue, you must use its ARN, which is available in the queue's attributes attribute.
queueName = queue.attributes['QueueArn'].split(':')[-1]
# will return its name.




# ------------------------- Sending messages -------------------------

# a new message, adds it to the end of the queue:
response = queue.send_message(MessageBody='world')


# The response is NOT a resource, but gives you a message ID and MD5
print(response.get('MessageId'))
print(response.get('MD5OfMessageBody'))



# You can also create messages with custom attributes:
queue.send_message(MessageBody='boto3', MessageAttributes={
    'Author': {
        'StringValue': 'Daniel',
        'DataType': 'String'
    }
})


# Messages can also be sent in batches. For example, sending the two messages described above in a single request would look like the following:
response = queue.send_messages(Entries=[
    {
        'Id': '1',
        'MessageBody': 'world'
    },
    {
        'Id': '2',
        'MessageBody': 'boto3',
        'MessageAttributes': {
            'Author': {
                'StringValue': 'Daniel',
                'DataType': 'String'
            }
        }
    }
])

# Print out any failures
print(response.get('Failed'))
# In this case, the response contains lists of Successful and Failed messages, so you can retry failures if needed.






# ------------------------- Processing messages -------------------------
    # {
    #     'Id': '1',
    #     'MessageBody': 'world'
    # },
    # {
    #     'Id': '2',
    #     'MessageBody': 'boto3',
    #     'MessageAttributes': {
    #         'Author': {
    #             'StringValue': 'Daniel',
    #             'DataType': 'String'
    #         }
    #     }
    # }


# Process messages by printing out body and optional author name
for message in queue.receive_messages(MessageAttributeNames=['Author']):
    # Get the custom author message attribute if it was set
    author_text = ''
    if message.message_attributes is not None:
        author_name = message.message_attributes.get('Author').get('StringValue')
        if author_name:
            author_text = ' ({0})'.format(author_name)

    # Print out the body and author (if set)
    print('Hello, {0}!{1}'.format(message.body, author_text))

    # Let the queue know that the message is processed
    message.delete()


# Given only the messages that were sent in a batch with SQS.Queue.send_messages() in the previous section, the above code will print out:
Hello, world!
Hello, boto3! (Daniel)
```




---


# Amazon CloudWatch `boto3.client('cloudwatch')`

```py
cloudwatch = boto3.client('cloudwatch')

cloudwatch.get_paginator()  # paginator
for response in paginator.paginate(StateValue='INSUFFICIENT_DATA'):
    response['MetricAlarms']

cloudwatch.put_metric_alarm()
cloudwatch.delete_alarms()

```




```py

# -------------------------- Creating alarms in Amazon CloudWatch --------------------------
cloudwatch = boto3.client('cloudwatch')


# -------------------------- List alarms of insufficient data through the pagination interface --------------------------
# -------------------------- cloudwatch.get_paginator('describe_alarms')
paginator = cloudwatch.get_paginator('describe_alarms')
for response in paginator.paginate(StateValue='INSUFFICIENT_DATA'):
    print(response['MetricAlarms'])


# -------------------------- Create/update alarm for a CloudWatch Metric alarm --------------------------
# -------------------------- put_metric_alarm.
# creates an alarm:
# the alarm state is immediately set to INSUFFICIENT_DATA.
# The alarm is evaluated and its state is set appropriately.
# Any actions associated with the state are then executed.
# update an existing alarm
# its state is left unchanged, but the update completely overwrites the previous configuration of the alarm.
cloudwatch.put_metric_alarm(
    AlarmName='Web_Server_CPU_Utilization',
    ComparisonOperator='GreaterThanThreshold',
    EvaluationPeriods=1,
    MetricName='CPUUtilization',
    Namespace='AWS/EC2',
    Period=60,
    Statistic='Average',
    Threshold=70.0,
    ActionsEnabled=False,
    AlarmDescription='Alarm when server CPU exceeds 70%',
    Dimensions=[
        {
          'Name': 'InstanceId',
          'Value': 'INSTANCE_ID'
        },
    ],
    Unit='Seconds'
)


# -------------------------- Delete an alarm --------------------------
cloudwatch.delete_alarms(
  AlarmNames=['Web_Server_CPU_Utilization'],
)

```




---

# Amazon DynamoDB `boto3.resource('dynamodb')`

By following this guide, you will learn how to use the `DynamoDB.ServiceResource` and `DynamoDB.Table` resources in order to create tables, write items to tables, modify existing items, retrieve items, and query/filter the items in the table.

```py
dynamodb = boto3.resource('dynamodb')
table = dynamodb.create_table(
    TableName='users',
    KeySchema=[
        {
            'AttributeName': 'username',
            'KeyType': 'HASH'
        },
        {
            'AttributeName': 'last_name',
            'KeyType': 'RANGE'
        }
    ],
    AttributeDefinitions=[
        {
            'AttributeName': 'username',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'last_name',
            'AttributeType': 'S'
        },
    ],
    ProvisionedThroughput={
        'ReadCapacityUnits': 5,
        'WriteCapacityUnits': 5
    }
)

table = dynamodb.Table('users')
table.item_count
table.creation_date_time

table.put_item(Item={'a':'x', 'b':'y', 'c':'z'})

table.update_item(
    Key={
        'username': 'janedoe',
        'last_name': 'Doe'
    },
    UpdateExpression='SET age = :val1',
    ExpressionAttributeValues={
        ':val1': 26
    }
)

response = table.get_item(Key={'a':'x', 'b':'y'})
item = response['Item']


with table.batch_writer() as batch:
    batch.put_item(Item={'a':'x', 'b':'y', 'c':'z'})
    batch.delete_item(Key={'partition_key': 'a','sort_key': 'b'})

with table.batch_writer(overwrite_by_pkeys=['partition_key', 'sort_key']) as batch:
    batch.put_item(Item={'a':'x', 'b':'y', 'c':'z'})
    batch.put_item(Item={'a':'x', 'b':'y', 'c':'z'})

from boto3.dynamodb.conditions import Key, Attr
response = table.query(KeyConditionExpression = Key('username').eq('johndoe'))
response = table.scan(FilterExpression = Attr('a').eq('x'))
response = table.scan(FilterExpression = Attr('a').eq('x') & Attr('a').begins_with('x'))
response = table.scan(FilterExpression = Attr('a.aa').eq('x'))

table.delete()
```


```py
# Get the service resource.
dynamodb = boto3.resource('dynamodb')

# -------------------------- Creating a new table --------------------------
# -------------------------- DynamoDB.ServiceResource.create_table()
# Create the DynamoDB table.
table = dynamodb.create_table(
    TableName='users',
    KeySchema=[
        {
            'AttributeName': 'username',
            'KeyType': 'HASH'
        },
        {
            'AttributeName': 'last_name',
            'KeyType': 'RANGE'
        }
    ],
    AttributeDefinitions=[
        {
            'AttributeName': 'username',
            'AttributeType': 'S'
        },
        {
            'AttributeName': 'last_name',
            'AttributeType': 'S'
        },
    ],
    ProvisionedThroughput={
        'ReadCapacityUnits': 5,
        'WriteCapacityUnits': 5
    }
)

# Wait until the table exists.
table.meta.client.get_waiter('table_exists').wait(TableName='users')

# Print out some data about the table.
print(table.item_count)

# Expected output:
# 0

# This creates a table named users that respectively has the hash and range primary keys username and last_name.
# This method will return a DynamoDB.Table resource to call additional methods on the created table.




# -------------------------- Using an existing table --------------------------
# create a DynamoDB.Table resource from an existing table:
# Instantiate a table resource object without actually creating a DynamoDB table.
# Note that the attributes of this table
# are lazy-loaded: a request is not made nor are the attribute
# values populated until the attributes on the table resource are accessed or its load() method is called.
table = dynamodb.Table('users')

# Print out some data about the table.
# This will cause a request to be made to DynamoDB and its attribute
# values will be set based on the response.
print(table.creation_date_time)

# Expected output
# 2015-06-26 12:42:45.149000-07:00






# -------------------------- Creating a new item
# -------------------------- DynamoDB.Table.put_item()
# Valid DynamoDB types: all of the valid types that can be used for an item
table.put_item(
   Item={
        'username': 'janedoe',
        'first_name': 'Jane',
        'last_name': 'Doe',
        'age': 25,
        'account_type': 'standard_user',
    }
)




# -------------------------- Getting an item
# -------------------------- DynamoDB.Table.get_item()
response = table.get_item(
    Key = {
        'username': 'janedoe',
        'last_name': 'Doe'
    }
)
item = response['Item']
print(item)
# Expected output:
# {u'username': u'janedoe',
#  u'first_name': u'Jane',
#  u'last_name': u'Doe',
#  u'account_type': u'standard_user',
#  u'age': Decimal('25')}




# -------------------------- Updating an item
# -------------------------- DynamoDB.Table.update_item()
table.update_item(
    Key={
        'username': 'janedoe',
        'last_name': 'Doe'
    },
    UpdateExpression='SET age = :val1',
    ExpressionAttributeValues={
        ':val1': 26
    }
)




# -------------------------- Deleting an item
# -------------------------- DynamoDB.Table.delete_item()
table.delete_item(
    Key={
        'username': 'janedoe',
        'last_name': 'Doe'
    }
)




# -------------------------- Batch writing
# loading a lot of data at a time, to both speed up the process and reduce the number of write requests made to the service.
# -------------------------- DynamoDB.Table.batch_writer()
# This method returns a handle to a batch_writer object that will automatically handle buffering and sending items in batches.
# In addition, the batch_writer will also automatically handle any unprocessed items and resend them as needed.
# All you need to do is call put_item for any items you want to add, and delete_item for any items you want to delete:
with table.batch_writer() as batch:
    batch.put_item(
        Item={
            'account_type': 'standard_user',
            'username': 'johndoe',
            'first_name': 'John',
            'last_name': 'Doe',
            'age': 25,
            'address': {
                'road': '1 Jefferson Street',
                'city': 'Los Angeles',
                'state': 'CA',
                'zipcode': 90001
            }
        }
    )
    batch.put_item(
        Item={
            'account_type': 'super_user',
            'username': 'janedoering',
            'first_name': 'Jane',
            'last_name': 'Doering',
            'age': 40,
            'address': {
                'road': '2 Washington Avenue',
                'city': 'Seattle',
                'state': 'WA',
                'zipcode': 98109
            }
        }
    )
# The batch writer is even able to handle a very large amount of writes to the table.
with table.batch_writer() as batch:
    for i in range(50):
        batch.put_item(
            Item={
                'account_type': 'anonymous',
                'username': 'user' + str(i),
                'first_name': 'unknown',
                'last_name': 'unknown'
            }
        )

# The batch writer can help to de-duplicate request by specifying overwrite_by_pkeys=['partition_key', 'sort_key']
# if you want to bypass no duplication limitation of single batch write request as botocore.exceptions.ClientError: An error occurred (ValidationException) when calling the BatchWriteItem operation: Provided list of item keys contains duplicates.
# It will drop request items in the buffer if their primary keys(composite) values are the same as newly added one, as eventually consistent with streams of individual put/delete operations on the same item.

with table.batch_writer(overwrite_by_pkeys=['partition_key', 'sort_key']) as batch:
    batch.put_item(
        Item={
            'partition_key': 'p1',
            'sort_key': 's2',
            'other': '111',
        }
    )
    batch.delete_item(
        Key={
            'partition_key': 'p1',
            'sort_key': 's2'
        }
    )
    batch.put_item(
        Item={
            'partition_key': 'p1',
            'sort_key': 's2',
            'other': '444',
        }
    )

# after de-duplicate:
batch.put_item(
    Item={
        'partition_key': 'p1',
        'sort_key': 's1',
        'other': '222',
    }
)
batch.put_item(
    Item={
        'partition_key': 'p1',
        'sort_key': 's1',
        'other': '444',
    }
)






# -------------------------- Querying and scanning the items in the table using
# -------------------------- DynamoDB.Table.query() or DynamoDB.Table.scan()
# To add conditions to scanning and querying the table
# import the boto3.dynamodb.conditions.Key and boto3.dynamodb.conditions.Attr] classes.
# boto3.dynamodb.conditions.Key:  used when the condition is related to the key of the item.
# boto3.dynamodb.conditions.Attr:  used when the condition is related to an attribute of the item:
from boto3.dynamodb.conditions import Key, Attr

# queries for all of the users whose username key equals johndoe:
response = table.query(KeyConditionExpression = Key('username').eq('johndoe'))
items = response['Items']
print(items)

# Expected output:
# [{u'username': u'johndoe',
#   u'first_name': u'John',
#   u'last_name': u'Doe',
#   u'account_type': u'standard_user',
#   u'age': Decimal('25'),
#   u'address': {u'city': u'Los Angeles',
#                u'state': u'CA',
#                u'zipcode': Decimal('90001'),
#                u'road': u'1 Jefferson Street'}}]


# scan the table based on attributes of the items.
# scans for all the users whose age is less than 27:
response = table.scan(FilterExpression = Attr('age').lt(27))
items = response['Items']
print(items)


# chain conditions together using the logical operators: & (and), | (or), and ~ (not).
# scans for all users whose first_name starts with J and account_type is super_user:
response = table.scan(FilterExpression = Attr('first_name').begins_with('J') & Attr('account_type').eq('super_user'))
items = response['Items']
print(items)



# scan based on conditions of a nested attribute.
# For example this scans for all users whose state in their address is CA:
response = table.scan(FilterExpression=Attr('address.state').eq('CA'))
items = response['Items']
print(items)



# -------------------------- Deleting a table
# -------------------------- DynamoDB.Table.delete()
table.delete()
```



---






# EBS


* EBS
  * Client
  * Paginators


---


## Client

`class EBS.Client`
- A `low-level client` representing Amazon Elastic Block Store (EBS)
- use the `Amazon Elastic Block Store (Amazon EBS) direct APIs` to create EBS snapshots, write data directly to snapshots, read data on snapshots, and identify the differences or changes between two snapshots.
- If you’re an independent software vendor (ISV) who offers backup services for Amazon EBS, the EBS direct APIs make it more efficient and cost-effective to track incremental changes on EBS volumes through snapshots. This can be done without having to create new volumes from snapshots, and then use Amazon Elastic Compute Cloud (Amazon EC2) instances to compare the differences.
- create `incremental snapshots` directly from data on-premises into EBS volumes and the cloud to use for quick disaster recovery.
- With the ability to write and read snapshots, write on-premises data to an EBS snapshot during a disaster. Then after recovery, restore it back to AWS or on-premises from the snapshot. no longer need to build and maintain complex mechanisms to copy data to and from Amazon EBS.

```py
import boto3

ebsclient = boto3.client('ebs')

can_paginate()
complete_snapshot()
generate_presigned_url()
get_paginator()
get_snapshot_block()
get_waiter()
list_changed_blocks()
list_snapshot_blocks()
put_snapshot_block()
start_snapshot()

```


---

### can_paginate(_operation_name_)

Check if an operation can be paginated.

Parameters
- **operation_name** (_string_): is the same name as the method name on the client.
- For example, if the method name is create_foo, and you'd normally invoke the operation as client.create_foo(_**kwargs_), if the create_foo operation can be paginated, you can use the call client.get_paginator("create_foo").

Returns
- `True` if the operation can be paginated,
- `False` otherwise.

---


### complete_snapshot(_**kwargs_)

Seals and completes the snapshot after all of the required blocks of data have been written to it.
- Completing the snapshot changes the status to `completed` .
- cannot write new blocks to a snapshot after it has been completed.


**Request Syntax**

```py
response = ebsclient.complete_snapshot(
    SnapshotId='string',                    
    # [REQUIRED]: The ID of the snapshot.
    ChangedBlocksCount=123,                 
    # [REQUIRED]: The number of blocks that were written to the snapshot.
    Checksum='string',                      
    # An aggregated Base-64 SHA256 checksum based on the checksums of each written block.
    # To generate the aggregated checksum using the linear aggregation method,
    # arrange the checksums for each written block in ascending order of their block index,
    # concatenate them to form a single string,
    # and then generate the checksum on the entire string using the SHA256 algorithm.
    ChecksumAlgorithm='SHA256',             
    # The algorithm used to generate the checksum.
    # Currently, the only supported algorithm is SHA256 .
    ChecksumAggregationMethod='LINEAR'      
    # The aggregation method used to generate the checksum.
    # Currently, the only supported aggregation method is LINEAR .
)
```

**Returns**
- Return type: dict
- Response Syntax:

```py
{'Status': 'completed'|'pending'|'error'}   # The status of the snapshot.
```


**Exceptions**

```py
* EBS.Client.exceptions.AccessDeniedException
* EBS.Client.exceptions.ValidationException
* EBS.Client.exceptions.ResourceNotFoundException
* EBS.Client.exceptions.RequestThrottledException
* EBS.Client.exceptions.ServiceQuotaExceededException
* EBS.Client.exceptions.InternalServerException
```


---


### generate_presigned_url(_ClientMethod_, _Params=None_, _ExpiresIn=3600_, _HttpMethod=None_)

Generate a presigned url given a client, its method, and arguments

**Parameters**

* **ClientMethod** (_string_) -- The client method to presign for
* **Params** (_dict_) -- The parameters normally passed to ClientMethod.
* **ExpiresIn** (_int_) -- The number of seconds the presigned url is valid for. By default it expires in an hour (3600 seconds)
* **HttpMethod** (_string_) -- The http method to use on the generated url. By default, the http method is whatever is used in the method's model.

**Returns**: The presigned url



---


### get_paginator(_operation_name_)

Create a paginator for an operation.

**Parameters**
- **operation_name** (_string_), is the same name as the method name on the client. For example,
- if the method name is `create_foo`, invoke the operation as `client.create_foo(**kwargs)`,
- if the create_foo operation can be paginated, you can use the call `client.get_paginator("create_foo")`.

**Raises OperationNotPageableError**
- Raised if the operation is not pageable.
- use the `client.can_paginate` method to check if an operation is pageable.

**Returns**
- Return type: `L{botocore.paginate.Paginator}`
- Returns: A paginator object


---

### get_snapshot_block(_**kwargs_)

Returns the data in a block in an Amazon Elastic Block Store snapshot.

**API**:

```bash
# GetSnapshotBlock

# Request Syntax
GET /snapshots/snapshotId/blocks/blockIndex?blockToken=BlockToken HTTP/1.1

# Response Syntax
HTTP/1.1 200
x-amz-Data-Length: DataLength
x-amz-Checksum: Checksum
x-amz-Checksum-Algorithm: ChecksumAlgorithm
BlockData
```

**Request Syntax**

```py
response = client.get_snapshot_block(
    SnapshotId='string',
    # The ID of the snapshot containing the block
    BlockIndex=123,      
    # The block index of the block from which to get data.
    # Obtain the BlockIndex by running the ListChangedBlocks or ListSnapshotBlocks.
    BlockToken='string'    
    # The block token of the block from which to get data.
    # Obtain the BlockToken by running the ListChangedBlocks or ListSnapshotBlocks operations.
)
```

**Returns**
- Return type: dict
- Returns: Response Syntax
```py
{
    'DataLength': 123,   
    # The size of the data in the block.
    'BlockData': StreamingBody(),
    # The data content of the block.
    'Checksum': 'string',
    # The checksum generated for the block, Base64 encoded.
    'ChecksumAlgorithm': 'SHA256'
    # The algorithm used to generate the checksum for the block, such as SHA256.
}
```

**Exceptions**
* EBS.Client.exceptions.AccessDeniedException
* EBS.Client.exceptions.ValidationException
* EBS.Client.exceptions.ResourceNotFoundException
* EBS.Client.exceptions.RequestThrottledException
* EBS.Client.exceptions.ServiceQuotaExceededException
* EBS.Client.exceptions.InternalServerException



---

### get_waiter(_waiter_name_)
Returns an object that can wait for some condition.

**Parameters**
- **waiter_name** (_str_) -- The name of the waiter to get. 


**Returns**
- The specified waiter object.
- Return type: `botocore.waiter.Waiter`



--- 

### list_changed_blocks(_**kwargs_)
Returns information about `the blocks that are different between two Amazon Elastic Block Store snapshots` of the same volume/snapshot lineage.

**Request Syntax**

```py
response = client.list_changed_blocks(
    FirstSnapshotId='string',
    # The ID of the first snapshot to use for the comparison.
    # Warning: The FirstSnapshotID parameter must be specified with a SecondSnapshotId parameter; otherwise, an error occurs.    
    SecondSnapshotId='string',
    # **[REQUIRED]**
    # The ID of the second snapshot to use for the comparison.
    # Warning: The SecondSnapshotId parameter must be specified with a FirstSnapshotID parameter; otherwise, an error occurs.    
    NextToken='string',
    # The token to request the next page of results.
    MaxResults=123, 
    # The number of results to return.
    StartingBlockIndex=123
    # The block index from which the comparison should start.
    # The list in the response will start from this block index or the next valid block index in the snapshots.    
)
```

**Returns**
- Return type: dict
- **Response Syntax**

```py
# _(dict) --_
{
    'ChangedBlocks': # An array of objects containing information about the changed blocks.
    [   
    # _(dict) --_ A block of data in an Amazon Elastic Block Store snapshot that is different from another snapshot of the same volume/snapshot lineage.
        {
            'BlockIndex': 123,            
            # The block index.
            'FirstBlockToken': 'string',
            # The block token for the block index of the FirstSnapshotId specified in the ListChangedBlocks operation. This value is absent if the first snapshot does not have the changed block that is on the second snapshot.
            'SecondBlockToken': 'string'
            # The block token for the block index of the SecondSnapshotId specified in the ListChangedBlocks operation.
        },
    ],
    'ExpiryTime': datetime(2015, 1, 1),
    'VolumeSize': 123,
    'BlockSize': 123,
    'NextToken': 'string'
}
```


          * **SecondBlockToken** _(string) --_

                The block token for the block index of the SecondSnapshotId specified in the ListChangedBlocks operation.

  * **ExpiryTime** _(datetime) --_

        The time when the BlockToken expires.

  * **VolumeSize** _(integer) --_

        The size of the volume in GB.

  * **BlockSize** _(integer) --_

        The size of the block.

  * **NextToken** _(string) --_

        The token to use to retrieve the next page of results. This value is null when there are no more results to return.


**Exceptions**

* EBS.Client.exceptions.AccessDeniedException
* EBS.Client.exceptions.ValidationException
* EBS.Client.exceptions.ResourceNotFoundException
* EBS.Client.exceptions.RequestThrottledException
* EBS.Client.exceptions.ServiceQuotaExceededException
* EBS.Client.exceptions.InternalServerException


---


### list_snapshot_blocks(_**kwargs_)

Returns information about the blocks in an Amazon Elastic Block Store snapshot.

See also: [AWS API Documentation](https://docs.aws.amazon.com/goto/WebAPI/ebs-2019-11-02/ListSnapshotBlocks)

**Request Syntax**

response = client.list_snapshot_blocks(
    SnapshotId='string',
    NextToken='string',
    MaxResults=123,
    StartingBlockIndex=123
)

Parameters

* **SnapshotId** (_string_) --

    **[REQUIRED]**

    The ID of the snapshot from which to get block indexes and block tokens.

* **NextToken** (_string_) -- The token to request the next page of results.
* **MaxResults** (_integer_) -- The number of results to return.
* **StartingBlockIndex** (_integer_) -- The block index from which the list should start. The list in the response will start from this block index or the next valid block index in the snapshot.

Return type

dict

Returns

**Response Syntax**

{
    'Blocks': [
        {
            'BlockIndex': 123,
            'BlockToken': 'string'
        },
    ],
    'ExpiryTime': datetime(2015, 1, 1),
    'VolumeSize': 123,
    'BlockSize': 123,
    'NextToken': 'string'
}

**Response Structure**

* _(dict) --_

  * **Blocks** _(list) --_

        An array of objects containing information about the blocks.

      * _(dict) --_

            A block of data in an Amazon Elastic Block Store snapshot.

          * **BlockIndex** _(integer) --_

                The block index.

          * **BlockToken** _(string) --_

                The block token for the block index.

  * **ExpiryTime** _(datetime) --_

        The time when the BlockToken expires.

  * **VolumeSize** _(integer) --_

        The size of the volume in GB.

  * **BlockSize** _(integer) --_

        The size of the block.

  * **NextToken** _(string) --_

        The token to use to retrieve the next page of results. This value is null when there are no more results to return.


**Exceptions**

* EBS.Client.exceptions.AccessDeniedException
* EBS.Client.exceptions.ValidationException
* EBS.Client.exceptions.ResourceNotFoundException
* EBS.Client.exceptions.RequestThrottledException
* EBS.Client.exceptions.ServiceQuotaExceededException
* EBS.Client.exceptions.InternalServerException


---


### put_snapshot_block(_**kwargs_)

Writes a block of data to a snapshot. If the specified block contains data, the existing data is overwritten. The target snapshot must be in the pending state.

Data written to a snapshot must be aligned with 512-byte sectors.

See also: [AWS API Documentation](https://docs.aws.amazon.com/goto/WebAPI/ebs-2019-11-02/PutSnapshotBlock)

**Request Syntax**

response = client.put_snapshot_block(
    SnapshotId='string',
    BlockIndex=123,
    BlockData=b'bytes'|file,
    DataLength=123,
    Progress=123,
    Checksum='string',
    ChecksumAlgorithm='SHA256'
)

Parameters

* **SnapshotId** (_string_) --

    **[REQUIRED]**

    The ID of the snapshot.

* **BlockIndex** (_integer_) --

    **[REQUIRED]**

    The block index of the block in which to write the data. A block index is a logical index in units of 512 KiB blocks. To identify the block index, divide the logical offset of the data in the logical volume by the block size (logical offset of data/524288 ). The logical offset of the data must be 512 KiB aligned.

* **BlockData** (_bytes or seekable file-like object_) --

    **[REQUIRED]**

    The data to write to the block.

    The block data is not signed as part of the Signature Version 4 signing process. As a result, you must generate and provide a Base64-encoded SHA256 checksum for the block data using the **x-amz-Checksum** header. Also, you must specify the checksum algorithm using the **x-amz-Checksum-Algorithm** header. The checksum that you provide is part of the Signature Version 4 signing process. It is validated against a checksum generated by Amazon EBS to ensure the validity and authenticity of the data. If the checksums do not correspond, the request fails. For more information, see [Using checksums with the EBS direct APIs](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-accessing-snapshot.html#ebsapis-using-checksums) in the _Amazon Elastic Compute Cloud User Guide_ .

* **DataLength** (_integer_) --

    **[REQUIRED]**

    The size of the data to write to the block, in bytes. Currently, the only supported size is 524288 .

    Valid values: 524288

* **Progress** (_integer_) -- The progress of the write process, as a percentage.
* **Checksum** (_string_) --

    **[REQUIRED]**

    A Base64-encoded SHA256 checksum of the data. Only SHA256 checksums are supported.

* **ChecksumAlgorithm** (_string_) --

    **[REQUIRED]**

    The algorithm used to generate the checksum. Currently, the only supported algorithm is SHA256 .


Return type

dict

Returns

**Response Syntax**

{
    'Checksum': 'string',
    'ChecksumAlgorithm': 'SHA256'
}

**Response Structure**

* _(dict) --_

  * **Checksum** _(string) --_

        The SHA256 checksum generated for the block data by Amazon EBS.

  * **ChecksumAlgorithm** _(string) --_

        The algorithm used by Amazon EBS to generate the checksum.


**Exceptions**

* EBS.Client.exceptions.AccessDeniedException
* EBS.Client.exceptions.ValidationException
* EBS.Client.exceptions.ResourceNotFoundException
* EBS.Client.exceptions.RequestThrottledException
* EBS.Client.exceptions.ServiceQuotaExceededException
* EBS.Client.exceptions.InternalServerException


---


### start_snapshot(_**kwargs_)

Creates a new Amazon EBS snapshot. The new snapshot enters the pending state after the request completes.

After creating the snapshot, use [PutSnapshotBlock](https://docs.aws.amazon.com/ebs/latest/APIReference/API_PutSnapshotBlock.html) to write blocks of data to the snapshot.

See also: [AWS API Documentation](https://docs.aws.amazon.com/goto/WebAPI/ebs-2019-11-02/StartSnapshot)

**Request Syntax**

response = client.start_snapshot(
    VolumeSize=123,
    ParentSnapshotId='string',
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ],
    Description='string',
    ClientToken='string',
    Encrypted=True|False,
    KmsKeyArn='string',
    Timeout=123
)

Parameters

* **VolumeSize** (_integer_) --

    **[REQUIRED]**

    The size of the volume, in GiB. The maximum size is 16384 GiB (16 TiB).

* **ParentSnapshotId** (_string_) --

    The ID of the parent snapshot. If there is no parent snapshot, or if you are creating the first snapshot for an on-premises volume, omit this parameter.

    If account is enabled for encryption by default, you cannot use an unencrypted snapshot as a parent snapshot. You must first create an encrypted copy of the parent snapshot using [CopySnapshot](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CopySnapshot.html) .

* **Tags** (_list_) --

    The tags to apply to the snapshot.

  * _(dict) --_

        Describes a tag.

      * **Key** _(string) --_

            The key of the tag.

      * **Value** _(string) --_

            The value of the tag.

* **Description** (_string_) -- A description for the snapshot.
* **ClientToken** (_string_) --

    A unique, case-sensitive identifier that you provide to ensure the idempotency of the request. Idempotency ensures that an API request completes only once. With an idempotent request, if the original request completes successfully. The subsequent retries with the same client token return the result from the original successful request and they have no additional effect.

    If you do not specify a client token, one is automatically generated by the AWS SDK.

    For more information, see [Idempotency for StartSnapshot API](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-direct-api-idempotency.html) in the _Amazon Elastic Compute Cloud User Guide_ .

    This field is autopopulated if not provided.

* **Encrypted** (_boolean_) --

    Indicates whether to encrypt the snapshot. To create an encrypted snapshot, specify true . To create an unencrypted snapshot, omit this parameter.

    If you specify a value for **ParentSnapshotId** , omit this parameter.

    If you specify true , the snapshot is encrypted using the CMK specified using the **KmsKeyArn** parameter. If no value is specified for **KmsKeyArn** , the default CMK for account is used. If no default CMK has been specified for account, the AWS managed CMK is used. To set a default CMK for account, use [ModifyEbsDefaultKmsKeyId](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_ModifyEbsDefaultKmsKeyId.html) .

    If account is enabled for encryption by default, you cannot set this parameter to false . In this case, you can omit this parameter.

    For more information, see [Using encryption](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-accessing-snapshot.html#ebsapis-using-encryption) in the _Amazon Elastic Compute Cloud User Guide_ .

* **KmsKeyArn** (_string_) --

    The Amazon Resource Name (ARN) of the AWS Key Management Service (AWS KMS) customer master key (CMK) to be used to encrypt the snapshot. If you do not specify a CMK, the default AWS managed CMK is used.

    If you specify a **ParentSnapshotId** , omit this parameter; the snapshot will be encrypted using the same CMK that was used to encrypt the parent snapshot.

    If **Encrypted** is set to true , you must specify a CMK ARN.

* **Timeout** (_integer_) --

    The amount of time (in minutes) after which the snapshot is automatically cancelled if:

  * No blocks are written to the snapshot.
  * The snapshot is not completed after writing the last block of data.

    If no value is specified, the timeout defaults to 60 minutes.


Return type

dict

Returns

**Response Syntax**

{
    'Description': 'string',
    'SnapshotId': 'string',
    'OwnerId': 'string',
    'Status': 'completed'|'pending'|'error',
    'StartTime': datetime(2015, 1, 1),
    'VolumeSize': 123,
    'BlockSize': 123,
    'Tags': [
        {
            'Key': 'string',
            'Value': 'string'
        },
    ],
    'ParentSnapshotId': 'string',
    'KmsKeyArn': 'string'
}

**Response Structure**

* _(dict) --_

  * **Description** _(string) --_

        The description of the snapshot.

  * **SnapshotId** _(string) --_

        The ID of the snapshot.

  * **OwnerId** _(string) --_

        The AWS account ID of the snapshot owner.

  * **Status** _(string) --_

        The status of the snapshot.

  * **StartTime** _(datetime) --_

        The timestamp when the snapshot was created.

  * **VolumeSize** _(integer) --_

        The size of the volume, in GiB.

  * **BlockSize** _(integer) --_

        The size of the blocks in the snapshot, in bytes.

  * **Tags** _(list) --_

        The tags applied to the snapshot. You can specify up to 50 tags per snapshot. For more information, see [Tagging Amazon EC2 resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ .

      * _(dict) --_

            Describes a tag.

          * **Key** _(string) --_

                The key of the tag.

          * **Value** _(string) --_

                The value of the tag.

  * **ParentSnapshotId** _(string) --_

        The ID of the parent snapshot.

  * **KmsKeyArn** _(string) --_

        The Amazon Resource Name (ARN) of the AWS Key Management Service (AWS KMS) customer master key (CMK) used to encrypt the snapshot.


**Exceptions**

* EBS.Client.exceptions.AccessDeniedException
* EBS.Client.exceptions.ValidationException
* EBS.Client.exceptions.RequestThrottledException
* EBS.Client.exceptions.ResourceNotFoundException
* EBS.Client.exceptions.ServiceQuotaExceededException
* EBS.Client.exceptions.InternalServerException
* EBS.Client.exceptions.ConcurrentLimitExceededException
* EBS.Client.exceptions.ConflictException

---












































.
