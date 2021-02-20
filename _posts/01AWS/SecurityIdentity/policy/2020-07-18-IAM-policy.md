---
title: AWS - IdenAccessManage - IAM policy
date: 2020-07-18 11:11:11 -0400
categories: [01AWS, IdenAccessManage]
tags: [AWS, IdenAccessManage]
toc: true
image:
---

[toc]

---

# IAM policy

---

## IAM JSON policy elements reference

---


### IAM JSON policy elements: Version


```yaml
Version : "2012-10-17"
```

---


### IAM JSON policy elements: Id

The Id element specifies an optional identifier for the policy.
- The ID is used differently in different services.
- For services that let you set an ID element, we recommend you use a UUID (GUID) for the value, or incorporate a UUID as part of the ID to ensure uniqueness.

```yaml
"Id": "cd3ad3d9-2776-4ef1-a904-4c229d1642ee"
```

---


### IAM JSON policy elements: Statement (required)


The Statement element is the main element for a policy.  
- The Statement element can contain a single statement or an array of individual statements.
- Each individual statement block must be enclosed in curly braces { }.
- For multiple statements, the array must be enclosed in square brackets [ ].

```yaml
"Statement": [
    {...},
    {...},
    {...}
]

Version: '2012-10-17'
Statement:
- Effect: Allow
  Action:
  - s3:ListAllMyBuckets
  - s3:GetBucketLocation
  Resource: arn:aws:s3:::*

- Effect: Allow
  Action: s3:ListBucket
  Resource: arn:aws:s3:::BUCKET-NAME
  Condition:
    StringLike:
      s3:prefix:
      - ''
      - home/
      - home/${aws:username}/

- Effect: Allow
  Action: s3:*
  Resource:
  - arn:aws:s3:::BUCKET-NAME/home/${aws:username}
  - arn:aws:s3:::BUCKET-NAME/home/${aws:username}/*
```


---


### IAM JSON policy elements: Sid (statement ID)

The Sid (statement ID) is an optional identifier that you provide for the policy statement.
- You can assign a Sid value to each statement in a statement array.
- In services that let you specify an ID element, such as SQS and SNS,
- the Sid value is just a sub-ID of the policy document's ID.
- In IAM, the Sid value must be unique within a JSON policy.

```yaml
"Sid": "1"
```

---


### IAM JSON policy elements: Effect (required)

- specifies whether the statement results in an allow or an explicit deny.
- Valid values for Effect are `Allow` and `Deny`.

```yaml
"Effect":"Allow/Deny"
```


---


### AWS JSON policy elements: Principal

- to specify the principal that is allowed or denied access to a resource.
- You cannot use the Principal element in an IAM identity-based policy.
- You can use it in the trust policies for IAM roles and in resource-based policies.
  - Resource-based policies are policies that you embed directly in a resource.
  - For example, you can embed policies in an Amazon S3 bucket or an AWS KMS customer master key (CMK).

You can specify any of the following principals in a policy:

- AWS account and root user

- Specific AWS accounts
  - All identities inside the account can access the resource if they have the appropriate IAM permissions attached to explicitly allow access.
  - This includes IAM users and roles in that account.
  - to specify an AWS account,use the account ARN `arn:aws:iam::AWS-account-ID:root`, or a shortened form `AWS:account ID`

    ```yaml
    "Principal": { "AWS": "arn:aws:iam::123456789012:root" }
    "Principal": { "AWS": "123456789012" }

    # more than one AWS account as a principal using an array,
    "Principal": {
        "AWS": [
            "arn:aws:iam::123456789012:root",
            "999999999999"
        ]
    }
    ```

- IAM users

    ```yaml
    "Principal": { "AWS": "arn:aws:iam::AWS-account-ID:user/user-name" }
    "Principal": {
    "AWS": [
        "arn:aws:iam::AWS-account-ID:user/user-name-1", 
        "arn:aws:iam::AWS-account-ID:user/UserName2"
    ]
    }
    ```


- Federated users (using web identity or SAML federation)

    ```yaml
    # "Principal": { "Federated": "arn:aws:iam::AWS-account-ID:saml-provider/provider-name" }
    "Principal": { "Federated": "cognito-identity.amazonaws.com" }
    "Principal": { "Federated": "www.amazon.com" }
    "Principal": { "Federated": "graph.facebook.com" }
    "Principal": { "Federated": "accounts.google.com" }
    ```


- IAM roles

    ```yaml
    "Principal": { "AWS": "arn:aws:iam::AWS-account-ID:role/role-name" }
    ```


- Assumed-role sessions

    ```yaml
    "Principal": { "AWS": "arn:aws:sts::AWS-account-ID:assumed-role/role-name/role-session-name" }
    ```

- AWS services

    ```yaml 
    Version: '2012-10-17'
    Statement:
    - Effect: Allow
    Principal:
        Service:
        - ecs.amazonaws.com
        - elasticloadbalancing.amazonaws.com
    Action: sts:AssumeRole
    ```

- Anonymous users (not recommended)

    ```yaml
    "Principal": "*"
    "Principal" : { "AWS" : "*" }
    ```


Use the Principal element in these ways:
- In IAM roles, use the Principal element in the role's trust policy to specify who can assume the role.
- For cross-account access, you must specify the 12-digit identifier of the trusted account.  
- In resource-based policies, use the Principal element to specify the accounts or users who are allowed to access the resource.

Note:
1. Do not use the Principal element in policies that you attach to IAM users and groups.
2. do not specify a principal in the permission policy for an IAM role.
   - In those cases, the principal is implicitly the user that the policy is attached to (for IAM users) or the user who assumes the role (for role access policies).
   - When the policy is attached to an IAM group, the principal is the IAM user in that group who is making the request.

 


---


### AWS JSON policy elements: NotPrincipal

Example IAM user in the same or a different account

```yaml
Version: '2012-10-17'
Statement:


# all principals  are explicitly denied access to a resource. 
# except the user named Bob in AWS account 444455556666 
- Effect: Deny
  Action: s3:*
  Resource:
  - arn:aws:s3:::BUCKETNAME
  - arn:aws:s3:::BUCKETNAME/*
  NotPrincipal:
    AWS:
    - arn:aws:iam::444455556666:user/Bob
    - arn:aws:iam::444455556666:root


# all principals are explicitly denied access to a resource. 
# except the assumed-role user named cross-account-audit-app in AWS account 444455556666
- Effect: Deny
  Action: s3:*
  Resource:
  - arn:aws:s3:::BUCKETNAME
  - arn:aws:s3:::BUCKETNAME/*
  NotPrincipal:
    AWS:
    - arn:aws:sts::444455556666:assumed-role/cross-account-read-only-role/cross-account-audit-app
    - arn:aws:iam::444455556666:role/cross-account-read-only-role
    - arn:aws:iam::444455556666:root   

```


---


### IAM JSON policy elements: Action


---


### IAM JSON policy elements: NotAction


---


### IAM JSON policy elements: Resource


---


### IAM JSON policy elements: NotResource


---


### IAM JSON policy elements: Condition



---

### Variables and tags




---

### Supported data types







---

## example

## AWS: Allows access based on date and time

```json
// restricts access to actions that occur between April 1, 2020 and June 30, 2020
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Effect": "Allow",
            "Action": "service-prefix:action-name",
            "Resource": "*",
            "Condition": {
                "DateGreaterThan": {"aws:CurrentTime": "2020-04-01T00:00:00Z"},
                "DateLessThan": {"aws:CurrentTime": "2020-06-30T23:59:59Z"}
            }
        }
    ]
}
```


---

## AWS: Denies access to AWS based on the requested Region


```json
{
    "Version": "2012-10-17",
    "Statement": [
        {
            "Sid": "DenyAllOutsideRequestedRegions",
            "Effect": "Deny",
            "NotAction": [
                "cloudfront:*",
                "iam:*",
                "route53:*",
                "support:*"
            ],
            // uses the NotAction element with the Deny effect, which explicitly denies access to all of the actions not listed in the statement.
            // Actions in the CloudFront, IAM, Route 53, and AWS Support services should not be denied
            "Resource": "*",
            "Condition": {
                "StringNotEquals": {
                    "aws:RequestedRegion": [
                        "eu-central-1",
                        "eu-west-1",
                        "eu-west-2",
                        "eu-west-3"
                    ]
                }
            }
        }
    ]
}
```



---

## AWS: Denies access to AWS based on the source IP

denies access to all AWS actions in the account when the request comes `from principals outside the specified IP range`.

```json
{
    "Version": "2012-10-17",
    "Statement": {
        "Effect": "Deny",
        "Action": "*",
        "Resource": "*",
        "Condition": {
            "NotIpAddress": {
                "aws:SourceIp": [
                    "192.0.2.0/24",
                    "203.0.113.0/24"
                ]
            },
            "Bool": {"aws:ViaAWSService": "false"}
        }
    }
}
```
