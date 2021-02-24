



[toc]


- ref
  - [Orchestrating analytics jobs on Amazon EMR Notebooks using Amazon MWAA](https://noise.getoto.net/2021/01/27/orchestrating-analytics-jobs-on-amazon-emr-notebooks-using-amazon-mwaa/)




---

# Amazon Managed Workflows for Apache Airflow (Amazon MWAA)

---

## basic

Amazon MWAA

- a fully managed service that makes it easy to run open-source versions of Apache Airflow on AWS, and to build workflows to run your extract, transform, and load (ETL) jobs and data pipelines.

Benefits of using Amazon MWAA

<font color=red> Setup </font>
- a managed service for Apache Airflow
- build, manage, and maintain Apache Airflow on AWS using services such as Amazon EC2 or Amazon EKS
- sets up Apache Airflow when you create an environment using the same open-source Airflow and user interface available from Apache.
- build workflows to run your extract, transform, and load (ETL) jobs and data pipelines.
- don't need to perform a manual setup or use custom tools to create an environment.
  - not a "branch" of Airflow, nor is it just "compatible with".
  - It is the exact same Apache Airflow that you can download on the own.
- makes it easy for you to build and manage the workflows in the cloud.

<font color=red> Scaling </font>
- use the same familiar Airflow platform with <font color=blue> improved scalability, availability, and security </font>
  - without the operational burden of having to manage the underlying infrastructure.
- uses the Apache Celery Executor to automatically scale workers as needed for the environment.
  - scales capacity up to meet demand
  - and back down to conserve resources and minimize costs.
- monitors the workers in the environment,
  - as demand increases, Amazon MWAA adds additional worker containers.
  - As workers free up, Amazon MWAA removes them.


<font color=red> Security </font>
- Integrated support with AWS Identity and Access Management (IAM), including role-based authentication and authorization for access to the Airflow user interface.
  - Workers assume IAM roles for easy and secure access to AWS services.
  - Workers and Scheduler run in the VPC for access to the resources.
- Amazon MWAA supports accessing the Airflow UI on either a VPC or a public secured endpoint.

<font color=red> Upgrades and patches </font>
- updates and patches Airflow automatically, with scheduled and communicated maintenance windows.
  - manages the provisioning and ongoing maintenance of Apache Airflow
  - automatically applies patches and updates to Apache Airflow in the Amazon MWAA environments.
  - don't need to manage different versions of Airflow using different library versions.
- automatically recovers from failed upgrades and patches.
- Point-point releases available within 7 days
- Minor versions available within 30 days



<font color=red> Monitoring </font>
- integrated with CloudWatch.
- The Apache Airflow logs and performance metrics data for the environment are available in a single location.
- This lets you easily identify workflow errors or task delays.
- Amazon MWAA automatically, if enabled, sends Apache Airflow system metrics and logs to CloudWatch.
- view logs and metrics for multiple environments from a single location
- easily identify task delays or workflow errors without the need for additional third-party tools.

<font color=red> Integration </font>
- easily combine data using any of Apache Airflow’s open source integrations.
- community provides operators (plugins that simplify connections to services) for Apache Airflow to integrate with
  - AWS services
    - such as Amazon S3, Amazon Redshift, Amazon EMR, AWS Batch, and Amazon SageMaker, Amazon Athena, AWS Batch, Amazon CloudWatch, Amazon DynamoDB, AWS DataSync, Amazon EMR, AWS Fargate, Amazon EKS, Amazon Kinesis Data Firehose, AWS Glue, AWS Lambda, Amazon Redshift, Amazon SQS, Amazon SNS, Amazon SageMaker, and Amazon S3
    - integrated with AWS security services to enable secure access to customer data
    - supports single sign-on using the same AWS credentials to access the Apache Airflow UI.
  - as well as hundreds of built-in and community-created operators and sensors
  - services on other cloud platforms.  
  - and popular third-party tools
    - such as Apache Hadoop, Presto, Hive, and Spark to perform data processing tasks.
- Amazon MWAA is committed to maintaining compatibility with the Amazon MWAA API,  


<font color=red> Containers </font>
- Amazon MWAA offers support for using containers to scale the worker fleet on demand and reduce scheduler outages, through AWS Fargate.
- Operators that execute tasks on Amazon ECS containers, as well as Kubernetes operators that create and run pods on a Kubernetes cluster, are supported.


---

## Amazon MWAA and Airflow workflows

- Apache Airflow manages data through a series of tasks called a <font color=red> workflow </font>
- A workflow comprised of these tasks: a <font color=red> Directed Acyclic Graph (DAG) </font>
  - DAGs describe how to run a workflow and are written in Python
- When a workflow is created, tasks are configured
  - so that some tasks must finish before the next task can start without needing to loop back to a previous task.

- Example,
  - tasks that collect and process data must finish collecting and processing all data before attempting to merge the data.
  - collection of tasks for a media distribution company. There is a task for
  - connecting to each content provider service that media is distributed to,
  - requesting the play count and sales for each title,
  - pulling social media impressions,
  - and then loading that data to a storage location, such as an Amazon S3 bucket.
  - After the data is uploaded, a task to process the data starts and converts the data to another format or modifies specific values.
  - The task to merge the data together starts only after all of the preceding tasks are completed.
    - by tools like AWS Glue or Amazon Athena, or perhaps using Amazon SageMaker to identify similar entries that can combined further.
  - After all tasks are complete, the result is a clean and complete data set ready for
    - analysis, such as with Amazon Redshift, or storage with Amazon DynamoDB.

- If a task fails, the workflow is configured to automatically retry the failed task while the subsequent tasks wait for that task to complete.
  - If a manual restart is required, the workflows starts at the failed task rather than the first task in the workflow.
  - save time and resources by not repeating tasks that had already completed successfully.

---


### Amazon S3

- Amazon MWAA uses an S3 bucket to store DAGs and associated support files.
- must create an S3 bucket before you can create the environment.
- must create the bucket in the same Region where you create the environment.

---


### VPC network configurations


- Required VPC networking components requirements:
  - <font color=red> Two private subnets </font>
    - in two different availability zones within the same Region.
  - also need one of the following:
    1. <font color=red> Two public subnets </font>
       - configured to route the private subnet data to the Internet. (via NAT gateways)
    2. Or <font color=red> VPC endpoint services (AWS PrivateLink) </font>

> If you are unable to provide Internet routing for the two private subnets,
> - VPC endpoint services (AWS PrivateLink) access to the AWS services used by the environment is required.
> - AWS services used: Amazon CloudWatch, CloudWatch Logs, Amazon ECR, Amazon S3, Amazon SQS, AWS Key Management Service


- The Airflow UI in the Amazon MWAA environment is accessible over the internet by users granted access in the IAM policy.
- Amazon MWAA attaches an [Application Load Balancer](https://docs.aws.amazon.com/elasticloadbalancing/latest/application/introduction.html) with an HTTPS endpoint for your web server as part of the Amazon MWAA managed service.


---


### VPC endpoints

- VPC endpoints are highly available VPC components
- enable private connections between your VPC and supported AWS services.
- Traffic between your VPC and the other services remains in your AWS network.


For example:
- use the VPC endpoints to ensure extra security, availability, and Amazon S3 data transfer performance:
- An Amazon S3 [gateway VPC endpoint](https://docs.aws.amazon.com/vpc/latest/userguide/vpce-gateway.html) to establish a private connection between the Amazon MWAA VPC and Amazon S3
- An EMR [interface VPC endpoint](https://docs.aws.amazon.com/vpc/latest/userguide/vpce-interface.html) to securely route traffic directly to Amazon EMR from Amazon MWAA, instead of connecting over the internet


---

## Airflow components

Each environment has an Airflow Scheduler and 1 or more Airflow Workers, managed by auto-scaling, that are linked to the VPC.
- The meta database and web servers are isolated in the service’s account, and there are separate instances of each for each Airflow environment created
  - there is no shared tenancy of any components, even within the same account.
- Web server access can then be exposed through an endpoint within the VPC, or more simply can be exposed through a load balancer to a publicly accessible endpoint, in each case secured by IAM and AWS SSO.


---

## Get started with MWAA

Amazon Managed Workflows for Apache Airflow (MWAA) uses
- the Amazon VPC,
- DAG code and supporting files in the Amazon S3 storage bucket to create an environment.
  - You specify the location of the Amazon S3 bucket, the path to the DAG code, and any custom plugins or dependencies on the Amazon MWAA console when you create an environment.  


### Prerequisites
- <font color=red> AWS account </font>
  - An AWS account with permission to use Amazon MWAA and the AWS services and resources used by the environment.
- <font color=red> Amazon S3 bucket </font>
  - An Amazon S3 bucket with versioning enabled.
  - An Amazon S3 bucket is used to store the DAGs and associated files,
  - such as plugins.zip and requirements.txt.
- <font color=red> Amazon VPC </font>
  - The Amazon VPC networking components required by an Amazon MWAA environment.
  - You can use an existing VPC that meets these requirements, or create the VPC and networking components as defined in Create the VPC network.
- <font color=red> Customer master key (CMK) </font>
  - A Customer master key (CMK) for data encryption on the environment.
  - You can choose the default option on the Amazon MWAA console to create an AWS owned CMK when you create an environment.
- <font color=red> Execution role </font>
  - An execution role that allows Amazon MWAA to access AWS resources in the environment.
  - You can choose the default option on the Amazon MWAA console to create an execution role when you create an environment


### 1. Create S3 bucket for Amazon MWAA

- Buckets have configuration properties, including
  - <font color=red> name </font>
    - Avoid including sensitive information in the bucket name.
      - such as account numbers,
    - The bucket name is visible in the URLs that point to the objects in the bucket.
  - <font color=red> geographical Region </font>
  - <font color=red> access settings for the objects in the bucket </font>
    - Amazon MWAA requires that the bucket does not allow public access.
    - You should leave all settings enabled.
  - <font color=red> Bucket Versioning </font>
    - choose Enable.
  - <font color=red> encryption </font>
    - whether to enable server-side encryption for the bucket.
    - If you choose to enable server-side encryption
      - <font color=blue> must use the same key for the S3 bucket and Amazon MWAA environment </font>
  - If you want to <font color=red> enable S3 Object lock </font>
    - can only enable S3 Object lock for bucket when you create it,
    - can't disable it later.
    - Enabling Object lock also enables versioning for the bucket.
    - After you enable Object lock for the bucket, you must configure the Object lock settings before any objects in the bucket are protected.
- Choose Create bucket.


### 2. Create the VPC network
- need the VPC networking components required by an Amazon MWAA environment.
  1. use an existing VPC that meets these requirements,
  2. create the VPC and networking components on the Amazon MWAA console,
  3. use the provided AWS CloudFormation template to create the VPC and other required networking components.

- Amazon MWAA provides private and public networking options for the Apache Airflow web server.
- A <font color=red> public network </font>
  - allows the Airflow UI to be accessed over the Internet by users granted access in the IAM policy.
  - Amazon MWAA attaches an Application Load Balancer with an HTTPS endpoint for the web server as part of the Amazon MWAA managed service.
- A <font color=red> private network </font>
  - limits access to the Airflow UI to users within the VPC.
  - Amazon MWAA attaches a VPC endpoint to the web server.
  - Enabling access to this endpoint requires additional configuration,
    - such as a proxy or Linux Bastion.
  - In addition, you must grant users access in the IAM policy.


### 3. Environment infrastructure

- When create an environment
  - Amazon MWAA
    - creates <font color=red> an Aurora PostgreSQL metadata database and an Fargate container </font>
    - in each of the two private subnets in different availability zones.
  - The Apache Airflow workers on an Amazon MWAA environment
    - use the Celery Executor to queue and distribute tasks to multiple Celery workers from an Apache Airflow platform.
    - The Celery Executor runs in an AWS Fargate container.
      - If a Fargate container in one availability zone fails,
        - Amazon MWAA switches to the other container in a different availability zone to run the Celery Executor,
        - and the Apache Airflow scheduler creates a new task instance in the Amazon Aurora PostgreSQL metadata database.

- When you create an Amazon MWAA environment,
  - it uses the VPC network that you created for Amazon MWAA, and adds the other necessary networking components.
  - it automatically installs the version of Apache Airflow that you specify, including workers, scheduler, and web server.
    - The environment includes a link to access the Apache Airflow UI in the environment.
    - You can create up to 10 environments per account per Region, and each environment can include multiple DAGs.  


Amazon MWAA console > Create environment
---

- provide a name for your environment
- select the Apache Airflow version to use.
  -![mwaa-create-environment-1-1024x342](https://i.imgur.com/njWTLzN.png)
- <font color=red> Under DAG code in Amazon S3: </font>
  -![mwaa-dag-code-s3-1012x1024](https://i.imgur.com/lsE8wTe.png)
  - <font color=blue> For S3 bucket </font>
    - choose the bucket that you created for Amazon MWAA
    - Enter the Amazon S3 URI to the bucket.
  - <font color=blue> For DAGs folder </font>
    - choose the DAG folder that you added to the bucket for Amazon MWAA
    - Enter the Amazon S3 URI to the DAG folder in the bucket.
  - (Optional). <font color=blue> For Plugins file </font>
    - The plugins file is a ZIP file <font color=blue> containing the plugins used by my DAGs </font>
    - do one of the following:
    - Choose Browse S3 and select the plugins.zip file that you added to the bucket. You must also select a version from the drop-down menu.
    - Enter the Amazon S3 URI to the plugin.zip file that you added to the bucket.
    - You can create an environment and then add a plugins.zip file later.
  - (Optional) <font color=blue> For Requirements file </font>
    - The requirements file <font color=blue> describes the Python dependencies to run my DAGs </font>
    - do one of the following:
    - Choose Browse S3 and then select the Python requirements.txt that you added to the bucket. Then select a version for the file from the drop-down menu.
    - Enter the Amazon S3 URI to the requirements.txt file in the bucket.
    - You can add a requirements file to your bucket after you create an environment. After you add or update the file you can edit the environment to modify these settings.

- Configure advanced settings page
  - under Networking
  - ![mwaa-networking-1-881x1024](https://i.imgur.com/I1CbouT.png)
    - choose the VPC that was you created for Amazon MWAA.
  - Under Subnets
    - Only private subnets are supported.
    - You can't change the VPC for an environment after you create it.
  - Under <font color=red> Web server access </font>
    - <font color=blue> Public Network </font>
    - This creates a public URL to access the Apache Airflow user interface in the environment.
    - <font color=blue> Private Network </font>
    - restrict access to the Apache Airflow UI to be accessible only from within the VPC selected
    - This creates a VPC endpoint that requires additional configuration to allow access, including a Linux Bastion.
    - The VPC endpoint for to access the Apache Airflow UI is listed on the Environment details page after you create the environment.

  - Under <font color=red> Security group </font>
    - Create new security group to have Amazon MWAA create a new security group with inbound and outbound rules based on your Web server access selection.
    - select up to 5 security groups from your account to use for the environment.

  - Under <font color=red> Environment class </font>
  - ![mwaa-environment-class-1024x555](https://i.imgur.com/HhVwUCZ.png)
    - You can increase the environment size later as appropriate.
      - The environment size determines the approximate number of workflows that an environment supports.
    - For Maximum worker count
      - specify the maximum number of workers, up to 25, to run concurrently in the environment.
    - Amazon MWAA automatically handles working scaling up to the maximum worker count.
    - The environment class for the Amazon MWAA environment determines the size of:
        - the AWS-managed <font color=blue> AWS Fargate containers </font>
          - where the Celery Executor runs,
        - and the AWS-managed <font color=blue> Amazon Aurora PostgreSQL metadata database </font>
          - where the Apache Airflow scheduler creates task instances.
    - Each environment includes a scheduler, a web server, and a worker.
      - Workers automatically scale up and down according to the workload.  

  - Under <font color=red> Encryption </font>
  - ![mwaa-encryption-1-1024x286](https://i.imgur.com/3rXU8Dr.png)
    - to encrypt your data
    - AWS owned key (by default)
    - or a different AWS KMS key,
      - if you enabled server-side encryption for the S3 bucket you created for Amazon MWAA,
      - you must use the same key for both the S3 bucket and your Amazon MWAA environment.
      - You must also grant permissions for Amazon MWAA to use the key by attaching the policy described in Attach key policy.

  - Under <font color=red> Monitoring, </font>
  - ![mwaa-monitoring-1-1024x715](https://i.imgur.com/a2j0xXb.png)
    - choose whether to enable CloudWatch Metrics.
      - environment performance to CloudWatch Metrics.
      - This is enabled by default, but CloudWatch Metrics can be disabled after launch.
    - For Airflow logging configuration
      - choose whether to enable sending log data to CloudWatch Logs for the following Apache Airflow log categories:
      - Airflow task logs
      - Airflow web server logs
      - Airflow scheduler logs
      - Airflow worker logs
      - Airflow DAG processing logs
    - After you enable a log category, choose the Log level for each as appropriate for your environment.
      - specify the log level and which Airflow components should send their logs to CloudWatch Logs
    - For Airflow configuration options
    - ![mwaa-airflow-configuration-1024x603](https://i.imgur.com/5tdBfTD.png)
      - When you create an environment Apache Airflow is installed using the default configuration options.
      - If you add a custom configuration option, Apache Airflow uses the value from the custom configuration instead of the default.  
      - add a customer configuration option
      - Select the configuration option to use a custom value for, then enter the Custom value.

  - Under <font color=red> Tags </font>
    - add any tags as appropriate for your environment.
    - Choose Add new tag, and then enter a Key and optionally, a Value for the key.

  - Under <font color=red> Permissions, </font>
  - ![mwaa-permissions-1024x361](https://i.imgur.com/tz09fDs.png)
    - choose the role to use as the execution role.
    - To have Amazon MWAA create a role for this environment, choose Create new role.
    - You must have permission to create IAM roles to use this option.
    - If you or someone in your organization created a role to use for Amazon MWAA
    - Choose Create environment.
    - takes about twenty to thirty minutes to create an environment.

---

## Amazon MWAA AWS CloudFormation template

![BDB-1140-1](https://i.imgur.com/eqV5cHY.jpg)


```yaml
# $ aws cloudformation create-stack \
#     --stack-name mwaaenvironment \
#     --template-body file://vpctemplate.yaml


Description:  This template deploys a VPC, with a pair of public and private subnets spread
  across two Availability Zones. It deploys an internet gateway, with a default
  route on the public subnets. It deploys a pair of NAT gateways (one in each AZ),
  and default routes for them in the private subnets.

Parameters:
  EnvironmentName:
    Description: An environment name that is prefixed to resource names
    Type: String
    Default: mwaa-

  VpcCIDR:
    Description: Please enter the IP range (CIDR notation) for this VPC
    Type: String
    Default: 10.192.0.0/16

  PublicSubnet1CIDR:
    Description: Please enter the IP range (CIDR notation) for the public subnet in the first Availability Zone
    Type: String
    Default: 10.192.10.0/24

  PublicSubnet2CIDR:
    Description: Please enter the IP range (CIDR notation) for the public subnet in the second Availability Zone
    Type: String
    Default: 10.192.11.0/24

  PrivateSubnet1CIDR:
    Description: Please enter the IP range (CIDR notation) for the private subnet in the first Availability Zone
    Type: String
    Default: 10.192.20.0/24

  PrivateSubnet2CIDR:
    Description: Please enter the IP range (CIDR notation) for the private subnet in the second Availability Zone
    Type: String
    Default: 10.192.21.0/24

Resources:
  VPC:
    Type: AWS::EC2::VPC
    Properties:
      CidrBlock: !Ref VpcCIDR
      EnableDnsSupport: true
      EnableDnsHostnames: true
      Tags:
    - Key: Name
          Value: !Ref EnvironmentName

  InternetGateway:
    Type: AWS::EC2::InternetGateway
    Properties:
      Tags:
    - Key: Name
          Value: !Ref EnvironmentName

  InternetGatewayAttachment:
    Type: AWS::EC2::VPCGatewayAttachment
    Properties:
      InternetGatewayId: !Ref InternetGateway
      VpcId: !Ref VPC

  PublicSubnet1:
    Type: AWS::EC2::Subnet
    Properties:
      VpcId: !Ref VPC
      AvailabilityZone: !Select [ 0, !GetAZs '' ]
      CidrBlock: !Ref PublicSubnet1CIDR
      MapPublicIpOnLaunch: true
      Tags:
    - Key: Name
          Value: !Sub ${EnvironmentName} Public Subnet (AZ1)

  PublicSubnet2:
    Type: AWS::EC2::Subnet
    Properties:
      VpcId: !Ref VPC
      AvailabilityZone: !Select [ 1, !GetAZs  '' ]
      CidrBlock: !Ref PublicSubnet2CIDR
      MapPublicIpOnLaunch: true
      Tags:
    - Key: Name
          Value: !Sub ${EnvironmentName} Public Subnet (AZ2)

  PrivateSubnet1:
    Type: AWS::EC2::Subnet
    Properties:
      VpcId: !Ref VPC
      AvailabilityZone: !Select [ 0, !GetAZs  '' ]
      CidrBlock: !Ref PrivateSubnet1CIDR
      MapPublicIpOnLaunch: false
      Tags:
    - Key: Name
          Value: !Sub ${EnvironmentName} Private Subnet (AZ1)

  PrivateSubnet2:
    Type: AWS::EC2::Subnet
    Properties:
      VpcId: !Ref VPC
      AvailabilityZone: !Select [ 1, !GetAZs  '' ]
      CidrBlock: !Ref PrivateSubnet2CIDR
      MapPublicIpOnLaunch: false
      Tags:
    - Key: Name
          Value: !Sub ${EnvironmentName} Private Subnet (AZ2)

  NatGateway1EIP:
    Type: AWS::EC2::EIP
    DependsOn: InternetGatewayAttachment
    Properties:
      Domain: vpc

  NatGateway2EIP:
    Type: AWS::EC2::EIP
    DependsOn: InternetGatewayAttachment
    Properties:
      Domain: vpc

  NatGateway1:
    Type: AWS::EC2::NatGateway
    Properties:
      AllocationId: !GetAtt NatGateway1EIP.AllocationId
      SubnetId: !Ref PublicSubnet1

  NatGateway2:
    Type: AWS::EC2::NatGateway
    Properties:
      AllocationId: !GetAtt NatGateway2EIP.AllocationId
      SubnetId: !Ref PublicSubnet2

  PublicRouteTable:
    Type: AWS::EC2::RouteTable
    Properties:
      VpcId: !Ref VPC
      Tags:
    - Key: Name
          Value: !Sub ${EnvironmentName} Public Routes

  DefaultPublicRoute:
    Type: AWS::EC2::Route
    DependsOn: InternetGatewayAttachment
    Properties:
      RouteTableId: !Ref PublicRouteTable
      DestinationCidrBlock: 0.0.0.0/0
      GatewayId: !Ref InternetGateway

  PublicSubnet1RouteTableAssociation:
    Type: AWS::EC2::SubnetRouteTableAssociation
    Properties:
      RouteTableId: !Ref PublicRouteTable
      SubnetId: !Ref PublicSubnet1

  PublicSubnet2RouteTableAssociation:
    Type: AWS::EC2::SubnetRouteTableAssociation
    Properties:
      RouteTableId: !Ref PublicRouteTable
      SubnetId: !Ref PublicSubnet2


  PrivateRouteTable1:
    Type: AWS::EC2::RouteTable
    Properties:
      VpcId: !Ref VPC
      Tags:
    - Key: Name
          Value: !Sub ${EnvironmentName} Private Routes (AZ1)

  DefaultPrivateRoute1:
    Type: AWS::EC2::Route
    Properties:
      RouteTableId: !Ref PrivateRouteTable1
      DestinationCidrBlock: 0.0.0.0/0
      NatGatewayId: !Ref NatGateway1

  PrivateSubnet1RouteTableAssociation:
    Type: AWS::EC2::SubnetRouteTableAssociation
    Properties:
      RouteTableId: !Ref PrivateRouteTable1
      SubnetId: !Ref PrivateSubnet1

  PrivateRouteTable2:
    Type: AWS::EC2::RouteTable
    Properties:
      VpcId: !Ref VPC
      Tags:
    - Key: Name
          Value: !Sub ${EnvironmentName} Private Routes (AZ2)

  DefaultPrivateRoute2:
    Type: AWS::EC2::Route
    Properties:
      RouteTableId: !Ref PrivateRouteTable2
      DestinationCidrBlock: 0.0.0.0/0
      NatGatewayId: !Ref NatGateway2

  PrivateSubnet2RouteTableAssociation:
    Type: AWS::EC2::SubnetRouteTableAssociation
    Properties:
      RouteTableId: !Ref PrivateRouteTable2
      SubnetId: !Ref PrivateSubnet2

  NoIngressSecurityGroup:
    Type: AWS::EC2::SecurityGroup
    Properties:
      GroupName: "no-ingress-sg"
      GroupDescription: "Security group with no ingress rule"
      VpcId: !Ref VPC

Outputs:
  VPC:
    Description: A reference to the created VPC
    Value: !Ref VPC

  PublicSubnets:
    Description: A list of the public subnets
    Value: !Join [ ",", [ !Ref PublicSubnet1, !Ref PublicSubnet2 ]]

  PrivateSubnets:
    Description: A list of the private subnets
    Value: !Join [ ",", [ !Ref PrivateSubnet1, !Ref PrivateSubnet2 ]]

  PublicSubnet1:
    Description: A reference to the public subnet in the 1st Availability Zone
    Value: !Ref PublicSubnet1

  PublicSubnet2:
    Description: A reference to the public subnet in the 2nd Availability Zone
    Value: !Ref PublicSubnet2

  PrivateSubnet1:
    Description: A reference to the private subnet in the 1st Availability Zone
    Value: !Ref PrivateSubnet1

  PrivateSubnet2:
    Description: A reference to the private subnet in the 2nd Availability Zone
    Value: !Ref PrivateSubnet2

  NoIngressSecurityGroup:
    Description: Security group with no ingress rule
    Value: !Ref NoIngressSecurityGroup
```


---


## Managing access to an Amazon MWAA environment


Amazon MWAA needs to be permitted to use other AWS services and resources used in an environment. You also need to be granted permission to access an Amazon MWAA environment and your Apache Airflow UI in AWS Identity and Access Management (IAM).


Amazon MWAA creates a service-linked role when create an Amazon MWAA environment.
- Amazon MWAA creates and attaches a JSON policy to your account's service-linked role
- to allow Amazon MWAA to use other AWS services used by your Amazon MWAA environment.
- For example, permission to CloudWatch logs and the VPC network for your environment.


```json
{
    "PolicyVersion": {
        "Document": {
            "Version": "2012-10-17",
            "Statement": [
                {
                    "Effect": "Allow",
                    "Action": [
                        "logs:CreateLogStream",
                        "logs:CreateLogGroup",
                        "logs:DescribeLogGroups"
                    ],
                    "Resource": "arn:aws:logs:*:*:log-group:airflow-*:*"
                },
                {
                    "Effect": "Allow",
                    "Action": [
                        "ec2:AttachNetworkInterface",
                        "ec2:CreateNetworkInterface",
                        "ec2:CreateNetworkInterfacePermission",
                        "ec2:DeleteNetworkInterface",
                        "ec2:DeleteNetworkInterfacePermission",
                        "ec2:DescribeDhcpOptions",
                        "ec2:DescribeNetworkInterfaces",
                        "ec2:DescribeSecurityGroups",
                        "ec2:DescribeSubnets",
                        "ec2:DescribeVpcEndpoints",
                        "ec2:DescribeVpcs",
                        "ec2:DetachNetworkInterface"
                    ],
                    "Resource": "*"
                },
                {
                    "Effect": "Allow",
                    "Action": "ec2:CreateVpcEndpoint",
                    "Resource": "arn:aws:ec2:*:*:vpc-endpoint/*",
                    "Condition": {
                        "ForAnyValue:StringEquals": {
                            "aws:TagKeys": "AmazonMWAAManaged"
                        }
                    }
                },
                {
                    "Effect": "Allow",
                    "Action": [
                        "ec2:ModifyVpcEndpoint",
                        "ec2:DeleteVpcEndpoints"
                    ],
                    "Resource": "arn:aws:ec2:*:*:vpc-endpoint/*",
                    "Condition": {
                        "Null": {
                            "aws:ResourceTag/AmazonMWAAManaged": false
                        }
                    }
                },
                {
                    "Effect": "Allow",
                    "Action": [
                        "ec2:CreateVpcEndpoint",
                        "ec2:ModifyVpcEndpoint"
                    ],
                    "Resource": [
                        "arn:aws:ec2:*:*:vpc/*",
                        "arn:aws:ec2:*:*:security-group/*",
                        "arn:aws:ec2:*:*:subnet/*"
                    ]
                },
                {
                    "Effect": "Allow",
                    "Action": "ec2:CreateTags",
                    "Resource": "arn:aws:ec2:*:*:vpc-endpoint/*",
                    "Condition": {
                        "StringEquals": {
                            "ec2:CreateAction": "CreateVpcEndpoint"
                        },
                        "ForAnyValue:StringEquals": {
                            "aws:TagKeys": "AmazonMWAAManaged"
                        }
                    }
                }
            ]
        },
    }
}
```

---


















..
