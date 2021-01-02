---
title: AWS - boto3 - boto3.client('cloudwatch') CloudWatch
date: 2020-07-18 11:11:11 -0400
categories: [00AWS, boto3]
tags: [AWS]
toc: true
image:
---

[toc]

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

