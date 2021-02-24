



# Orchestrating analytics jobs on Amazon EMR Notebooks using Amazon MWAA

- use [Amazon Managed Workflows for Apache Airflow](https://docs.aws.amazon.com/mwaa/latest/userguide/what-is-mwaa.html) (Amazon MWAA) to orchestrate analytics jobs on EMR Notebooks.
- We will start by walking you through the process of using [AWS CloudFormation](https://aws.amazon.com/cloudformation/) to set up an Amazon MWAA environment, which allows you to programmatically author, schedule, and monitor different sorts of workflows on Amazon EMR.
- We will then use this environment to run an EMR notebook example which does data analysis with Hive.

> The data source for the example in this post is from the public [Amazon Customer Reviews Dataset](https://s3.amazonaws.com/amazon-reviews-pds/readme.html). We use the [Parquet](https://en.wikipedia.org/wiki/Apache_Parquet) formatted dataset as the input dataset for our EMR notebook.

---

## Prerequisites

Before getting started, you must have the following prerequisites:

- An AWS account that provides access to AWS services.
- [AWS Command Line Interface](https://aws.amazon.com/cli) (AWS CLI) version 1.18.128 or later installed on your workstation.
- An [Amazon Simple Storage Service](https://aws.amazon.com/s3) (Amazon S3) bucket that meets the following Amazon MWAA requirements:
  - The bucket must be in the same AWS Region where you create the MWAA environment.
  - The bucket name must start with `airflow-` and should be globally unique.
  - Bucket versioning is enabled.
  - A folder named `dags` must be created in the same bucket to store DAGs and associated support files.
- An IAM user with an access key and secret access key to configure the AWS CLI.
  - The IAM user has permissions to create an IAM role and policies, launch an EMR cluster, create an Amazon MWAA environment, and create stacks in AWS CloudFormation.
- A possible limit increase for your account. (Usually a limit increase isn’t necessary. See [AWS service quotas](https://docs.aws.amazon.com/general/latest/gr/aws_service_limits.html) if you encounter a limit error while building the solution.)
- An EMR notebook created through the Amazon EMR console, using the notebook file [find\_best\_sellers.ipynb](https://aws-bigdata-blog.s3.amazonaws.com/artifacts/aws-blog-emr-mwaa/demo/notebook/find_best_sellers.ipynb). See [Creating a Notebook](https://docs.aws.amazon.com/emr/latest/ManagementGuide/emr-managed-notebooks-create.html) for instructions on creating an EMR notebook. Record the ID of the EMR notebook (for example, `<**e-\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\*\***\>`);


---

## Architecture overview


At a high level, this solution uses Amazon MWAA with Amazon EMR to build pipelines for ETL workflow orchestration. The following diagram illustrates the solution architecture.

![BDB-1140-1](https://i.imgur.com/eqV5cHY.jpg)

We use the following services and configurations in this solution:
- Amazon S3
- VPC network configurations
- VPC endpoints

--- 


## Setting up an Amazon MWAA environment 

CloudFormation template takes care of the following tasks 

- [Create an Amazon MWAA execution IAM role](https://docs.aws.amazon.com/mwaa/latest/userguide/mwaa-create-role.html).

- [Set up the VPC network for the Amazon MWAA environment](https://docs.aws.amazon.com/mwaa/latest/userguide/vpc-create.html), deploying the following resources:
  - A <font color=red> VPC with a pair of public and private subnets across two Availability Zones </font>
    - a <font color=blue> VPC </font>
      - `10.192.0.0/16` CIDR rule
    - a <font color=blue> VPC security group </font>
      - directs all inbound traffic to Amazon MWAA environment and all outbound traffic to `0.0.0.0/0`
    - <font color=blue> one public subnet </font>
      - `10.192.10.0/24` CIDR rule in 1st availability zone
    - <font color=blue> one public subnet </font>
      - `10.192.11.0/24` CIDR rule in 2nd availability zone
    - <font color=blue> one private subnet </font>
      - `10.192.20.0/24` CIDR rule in 1st availability zone
    - <font color=blue> one private subnet </font>
      - `10.192.21.0/24` CIDR rule in 2nd availability zone
  - An <font color=red> internet gateway </font> 
    - with a default route on the public subnets.
    - creates and attaches to the public subets
  - A pair of <font color=red> NAT gateways </font> 
    - one in each Availability Zone
    - and default routes for them in the private subnets.
    - creates and attaches to the private subnets
    - `two elastic IP addresses (EIPs)`
      - creates and attaches to the NAT gateways
  - 2 <font color=red> VPC endpoint </font> 
    - Amazon **S3 gateway VPC endpoints** 
    - and **EMR interface VPC endpoints** 
    - in the private subnets in two Availability Zones.
  - A <font color=red> security group </font> 
    - **security group** to be used by the Amazon MWAA environment
    - only allows local inbound traffic and all outbound traffic.

- [Create an Amazon MWAA environment](https://docs.aws.amazon.com/mwaa/latest/userguide/create-environment.html)
  - select `mw1.small` for the environment class and choose maximum worker count as `1`. 
  - For monitoring, publish environment performance to CloudWatch Metrics. 
  - For Airflow logging configuration, send only the task logs and use log level `INFO`.


to manually create, configure, and deploy the Amazon MWAA environment without using AWS CloudFormation, see [Get started with Amazon Managed Workflows for Apache Airflow (MWAA)](https://docs.aws.amazon.com/mwaa/latest/userguide/get-started.html).


---

### Launching the CloudFormation template

To launch your stack and provision your resources, complete the following steps:

1. Choose [**Launch Stack**](https://us-west-2.console.aws.amazon.com/cloudformation/home?region=us-west-2#/stacks/quickcreate?templateURL=https://aws-bigdata-blog.s3.amazonaws.com/artifacts/aws-blog-emr-mwaa/demo/cloudformation/airflow_cft.yml):
   - automatically launches AWS CloudFormation in your AWS account with a template. 
   - It prompts you to sign in as needed. 
   - You can view the template on the AWS CloudFormation console as required. 
   - The Amazon MWAA environment is created in the same Region as you launched the CloudFormation stack. 
   - Make sure that you create the stack in your intended Region.

The CloudFormation stack requires a few parameters:

![The CloudFormation stack requires a few parameters, as shown in the following screenshot.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-2.jpg)

![The CloudFormation stack requires a few parameters, as shown in the following screenshot.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20703%201014%22%3E%3C/svg%3E)


**Parameter** | **Description** | **Default Value**
---|---|---
Stack name | Enter a meaningful name for the stack. We use `MWAAEmrNBDemo` for this example. Replace it with your own value. | None
AirflowBucketName | Name of the S3 bucket to store DAGs and support files. The S3 bucket must be in the same Region where you create the environment. The name must start with `airflow-`. Enter the S3 bucket created as a prerequisite. We use the S3 bucket `airflow-emr-demo-us-west-2` for this post. You must replace it with your own value for this field. | None
EnvironmentName | An MWAA environment name that is prefixed to resource names. All the resources created by this templated are named after the value saved for this field. We name our environment `mwaa-emr-blog-demo` for this post. Replace it with your own value for this field. | mwaa-
PrivateSubnet1CIDR | The IP range (CIDR notation) for the private subnet in the first Availability Zone. For more information, see [AWS CloudFormation VPC stack specifications](https://docs.aws.amazon.com/mwaa/latest/userguide/vpc-create.html#vpc-create-template-components). | 10.192.20.0/24
PrivateSubnet2CIDR | The IP range (CIDR notation) for the private subnet in the second Availability Zone. For more information, see [AWS CloudFormation VPC stack specifications](https://docs.aws.amazon.com/mwaa/latest/userguide/vpc-create.html#vpc-create-template-components).. | 10.192.21.0/24
PublicSubnet1CIDR | The IP range (CIDR notation) for the public subnet in the first Availability Zone. For more information, see [AWS CloudFormation VPC stack specifications](https://docs.aws.amazon.com/mwaa/latest/userguide/vpc-create.html#vpc-create-template-components). | 10.192.10.0/24
PublicSubnet2CIDR | The IP range (CIDR notation) for the public subnet in the second Availability Zone. For more information, see [AWS CloudFormation VPC stack specifications](https://docs.aws.amazon.com/mwaa/latest/userguide/vpc-create.html#vpc-create-template-components). | 10.192.11.0/24
VpcCIDR | The IP range (CIDR notation) for this VPC being created. For more information, see [AWS CloudFormation VPC stack specifications](https://docs.aws.amazon.com/mwaa/latest/userguide/vpc-create.html#vpc-create-template-components). | 10.192.0.0/16


2. Enter the parameter values from the preceding table.

3. Review the details on the **Capabilities** section and select the check boxes confirming AWS CloudFormation might create IAM resources with custom names.

4. Choose **Create Stack**.
   - Stack creation takes a few minutes. A
   - fter the CloudFormation stack is complete, on the **Resources** tab, you can find the resources being created in this CloudFormation stack. 
   - Now, we’re ready to run our example.

---


## Orchestrating Hive analytics jobs on EMR Notebooks using Apache Airflow 

1. As a user, first need to create the DAG file that describes how to run the analytics jobs and upload it to the dags folder under the S3 bucket specified. 
2. The DAG can be triggered in Apache Airflow UI to orchestrate the job workflow, which includes 
   - creating an EMR cluster, 
   - waiting for the cluster to be ready, 
   - running Hive analytics jobs on EMR notebooks, 
   - uploading the results to Amazon S3, 
   - and cleaning up the cluster after the job is complete.

![The following diagram illustrates the workflow.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-3-1.jpg)
 

### Input notebook file

![Let’s take a look at the following input notebook file find_best_sellers.ipynb, which we use for our example.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-NEW.jpg)


input notebook file `find_best_sellers.ipynb`
- Python script that does analysis on the public [Amazon Customer Reviews Dataset](https://s3.amazonaws.com/amazon-reviews-pds/readme.html). 
- It generates the top 20 best sellers in a given list of categories over a given period of time and saves the results to the given S3 output location. 
- For demonstration purpose only, we rank the seller simply by the sum of review star ratings from verified purchases.

The explanations of the default parameters in the first cell and each code block are included in the notebook itself.
- The last line in the first cell, we have `OUTPUT_LOCATION = "s3://airflow-emr-demo-us-west-2/query_output/`” as a default value for the input parameter. Replace it with your own value for the output location. You can also supply a different value for this for this parameter in the Airflow Variables later.


### DAG file

The DAG file [test\_dag.py](https://aws-bigdata-blog.s3.amazonaws.com/artifacts/aws-blog-emr-mwaa/demo/dag/test_dag.py) is used to orchestrate our job flow via Apache Airflow. It performs the following tasks:

1. Create an EMR cluster with one m5.xlarge primary and two m5.xlarge core nodes on release version 6.2.0 with Spark, Hive, Livy and JupyterEnterpriseGateway installed as applications.
2. Wait until the cluster is up and ready.
3. Run the notebook `find_best_sellers.ipynb` on the EMR cluster created in Step 1.
4. Wait until the notebook run is complete.
5. Clean up the EMR cluster.

Here is the full source code of the DAG:

```bash
    # Copyright Amazon.com, Inc. or its affiliates. All Rights Reserved.
    # SPDX-License-Identifier: MIT-0
    from airflow import DAG
    from airflow.operators.python_operator import PythonOperator
    from time import sleep
    from datetime import datetime
    import boto3, time
    from builtins import range
    from pprint import pprint
    from airflow.operators.sensors import BaseSensorOperator
    from airflow.contrib.operators.emr_create_job_flow_operator import EmrCreateJobFlowOperator
    from airflow.contrib.operators.emr_terminate_job_flow_operator import EmrTerminateJobFlowOperator
    from airflow.contrib.sensors.emr_job_flow_sensor import EmrJobFlowSensor
    from airflow.contrib.sensors.emr_step_sensor import EmrStepSensor
    from airflow.contrib.hooks.emr_hook import EmrHook
    from airflow.contrib.sensors.emr_base_sensor import EmrBaseSensor
    from airflow.models import Variable
    from airflow.utils import apply_defaults
    from airflow.utils.dates import days_ago

    # Available categories:
    #
    # Apparel,Automotive,Baby,Beauty,Books,Camera,Digital_Ebook_Purchase,Digital_Music_Purchase,
    # Digital_Software,Digital_Video_Download,Digital_Video_Games,Electronics,Furniture,Gift_Card,
    # Grocery,Health_&_Personal_Care,Home,Home_Entertainment,Home_Improvement,Jewelry,Kitchen,
    # Lawn_and_Garden,Luggage,Major_Appliances,Mobile_Apps,Mobile_Electronics,Music,Musical_Instruments,
    # Office_Products,Outdoors,PC,Personal_Care_Appliances,Pet_Products,Shoes,Software,Sports,Tools,
    # Toys,Video,Video_DVD,Video_Games,Watches,Wireless

    # =============== VARIABLES ===============
    NOTEBOOK_ID = Variable.get('NOTEBOOK_ID')
    NOTEBOOK_FILE_NAME = Variable.get('NOTEBOOK_FILE_NAME')
    CATEGORIES_CSV = Variable.get('CATEGORIES_CSV')
    REGION = Variable.get('REGION')
    SUBNET_ID = Variable.get('SUBNET_ID')
    EMR_LOG_URI = Variable.get('EMR_LOG_URI')
    OUTPUT_LOCATION = Variable.get('OUTPUT_LOCATION')
    FROM_DATE = Variable.get('FROM_DATE')
    TO_DATE = Variable.get('TO_DATE')
    # =========================================

    JOB_FLOW_OVERRIDES = {
        'Name': 'Test-Cluster',
        'ReleaseLabel': 'emr-6.2.0',
        'Applications': [{'Name':'Spark'}, {'Name':'Hive'}, {'Name':'Livy'}, {'Name':'JupyterEnterpriseGateway'}],
        'Configurations': [
              {
                "Classification": "hive-site",
                "Properties": {
                    "hive.execution.engine": "spark"
                }
            }
        ],
        'Instances': {
            'Ec2SubnetId': SUBNET_ID,
            'InstanceGroups': [
                {
                    'Name': 'Master node',
                    'Market': 'ON_DEMAND',
                    'InstanceRole': 'MASTER',
                    'InstanceType': 'm5.xlarge',
                    'InstanceCount': 1,
                },
                {
                    'Name': 'Core node',
                    'Market': 'ON_DEMAND',
                    'InstanceRole': 'CORE',
                    'InstanceType': 'm5.xlarge',
                    'InstanceCount': 2,
                }
            ],
            'KeepJobFlowAliveWhenNoSteps': True,
            'TerminationProtected': False,
        },
        'JobFlowRole': 'EMR_EC2_DefaultRole',
        'ServiceRole': 'EMR_DefaultRole',
        'LogUri': EMR_LOG_URI
    }


    class CustomEmrJobFlowSensor(EmrJobFlowSensor):
        NON_TERMINAL_STATES = ['STARTING', 'BOOTSTRAPPING', 'TERMINATING']

    class NotebookExecutionSensor(EmrBaseSensor):
        NON_TERMINAL_STATES = ['START_PENDING', 'STARTING', 'RUNNING', 'FINISHING', 'STOP_PENDING', 'STOPPING']
        FAILED_STATE = ['FAILING', 'FAILED']
        template_fields = ['notebook_execution_id']
        template_ext = ()
        @apply_defaults
        def __init__(self, notebook_execution_id, *args, **kwargs):
            super(NotebookExecutionSensor, self).__init__(*args, **kwargs)
            self.notebook_execution_id = notebook_execution_id
        def get_emr_response(self):
            emr = EmrHook(aws_conn_id=self.aws_conn_id).get_conn()
            self.log.info('Poking notebook execution %s', self.notebook_execution_id)
            return emr.describe_notebook_execution(NotebookExecutionId=self.notebook_execution_id)
        @staticmethod
        def state_from_response(response):
            return response['NotebookExecution']['Status']
        @staticmethod
        def failure_message_from_response(response):
            state_change_reason = response['NotebookExecution']['LastStateChangeReason']
            if state_change_reason:
                return 'Execution failed with reason: ' + state_change_reason
            return None

    def start_execution(**context):
        ti = context['task_instance']
        cluster_id = ti.xcom_pull(key='return_value', task_ids='create_cluster_task')
        print("Starting an execution using cluster: " + cluster_id)
        # generate a JSON key-pair of <String : String Array>, e.g.
        # "\"CATEGORIES\": [\"Apparel\", \"Automotive\", \"Baby\", \"Books\"]"
        categories_escaped_quotes = ""
        for category in CATEGORIES_CSV.split(','):
            categories_escaped_quotes = categories_escaped_quotes + "\"" + category + "\","
        categories_escaped_quotes = categories_escaped_quotes[:-1]
        categories_parameter = "\"CATEGORIES\" : [" + categories_escaped_quotes + "]"

        output_location_parameter = "\"OUTPUT_LOCATION\": \"" + OUTPUT_LOCATION + "\""
        from_date_parameter = "\"FROM_DATE\": \"" + FROM_DATE + "\""
        to_date_parameter = "\"TO_DATE\": \"" + TO_DATE + "\""
        parameters = f"{{ {categories_parameter}, {output_location_parameter}, {from_date_parameter}, {to_date_parameter} }}"
        emr = boto3.client('emr', region_name=REGION)
        start_resp = emr.start_notebook_execution(
            EditorId=NOTEBOOK_ID,
            RelativePath=NOTEBOOK_FILE_NAME,
            ExecutionEngine={'Id': cluster_id, 'Type': 'EMR'},
            NotebookParams=parameters,
            ServiceRole='EMR_Notebooks_DefaultRole'
        )
        execution_id = start_resp['NotebookExecutionId']
        print("Started an execution: " + execution_id)
        return execution_id



    with DAG('test_dag', description='test dag', schedule_interval='0 * * * *', start_date=datetime(2020,3,30), catchup=False) as dag:
        create_cluster = EmrCreateJobFlowOperator(
            task_id='create_cluster_task',
            job_flow_overrides=JOB_FLOW_OVERRIDES,
            aws_conn_id='aws_default',
            emr_conn_id='emr_default',
        )
        cluster_sensor = CustomEmrJobFlowSensor(
            task_id='check_cluster_task',
            job_flow_id="{{ task_instance.xcom_pull(task_ids='create_cluster_task', key='return_value') }}",
            aws_conn_id='aws_default',
        )
        start_execution = PythonOperator(
            task_id='start_execution_task',
            python_callable=start_execution,
            provide_context=True
        )
        execution_sensor = NotebookExecutionSensor(
            task_id='check_execution_task',
            notebook_execution_id="{{ task_instance.xcom_pull(task_ids='start_execution_task', key='return_value') }}",
            aws_conn_id='aws_default',
        )

        cluster_remover = EmrTerminateJobFlowOperator(
            task_id='terminate_cluster',
            job_flow_id="{{ task_instance.xcom_pull(task_ids='create_cluster_task', key='return_value') }}",
            aws_conn_id='aws_default',
        )

        create_cluster >> cluster_sensor >> start_execution >> execution_sensor >> cluster_remover
```

The very last line of the DAG code explains how the tasks are linked in the orchestration workflow. 
- It’s [overloading](https://docs.python.org/3/reference/datamodel.html#emulating-numeric-types) the right shift `>>` operator to create a dependency, meaning that the task on the left should be run first, and the output passed to the task on the right.

Instead of hard-coding the variables in the DAG code, we choose to supply these variables by importing a JSON file in the Airflow UI before actually running the DAG. This way, we can also update the variables without having to update the DAG code, which requires updating the DAG file in Amazon S3. We walk you through how to do so in the later steps.

1. the lines for `VARIABLES` that we repeated:

```bash
    # =============== VARIABLES ===============
    NOTEBOOK_ID = Variable.get('NOTEBOOK_ID')
    NOTEBOOK_FILE_NAME = Variable.get('NOTEBOOK_FILE_NAME')
    CATEGORIES_CSV = Variable.get('CATEGORIES_CSV')
    REGION = Variable.get('REGION')
    SUBNET_ID = Variable.get('SUBNET_ID')
    EMR_LOG_URI = Variable.get('EMR_LOG_URI')
    OUTPUT_LOCATION = Variable.get('OUTPUT_LOCATION')
    FROM_DATE = Variable.get('FROM_DATE')
    TO_DATE = Variable.get('TO_DATE')
```

2. create a JSON formatted file named `variables.json`  

```json
    {
        "REGION": "us-west-2",
        "SUBNET_ID": "<subnet-********>",
        "EMR_LOG_URI": "s3://<S3 path for EMR logs>/",
        "NOTEBOOK_ID": "<e-*************************>",
        "NOTEBOOK_FILE_NAME": "find_best_sellers.ipynb",
        "CATEGORIES_CSV": "Apparel,Automotive,Baby,Beauty,Books",
        "FROM_DATE": "2015-08-25",
        "TO_DATE": "2015-08-31",
        "OUTPUT_LOCATION": "s3://<S3 path for query output>/"
    }
```
 

### Accessing Apache Airflow UI and running the workflow

To run the workflow, complete the following steps:

1. On the Amazon MWAA console, find the new environment `mwaa-emr-blog-demo` we created earlier with the CloudFormation template.
   - ![On the Amazon MWAA console, find the new environment mwaa-emr-blog-demo we created earlier with the CloudFormation template.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-MISSING.jpg)


2. Choose **Open Airflow UI**.

3. Log in as an authenticated user.
   - ![Log in as an authenticated user.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-5.jpg)
   - import the JSON file for the variables into Airflow UI.
   - to supply the variable values for our DAG definition later upon triggering the DAG in Airflow UI instead of hard-coding the values.

4. On the **Admin** menu, choose **Variables**.
5. Choose **Browse**.
6. Choose **json**.
7. Choose **Import Variables**.
   - ![pic](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-MISSING-2.jpg)

8. Run the following command in the same directory as where file `test_dag.py` is to upload the DAG file to the `dags` folder under the S3 bucket specified for the Airflow environment. 
   - Replace `<**your\_airflow\_bucket\_name**_\>_` with the S3 bucket name that you created as a prerequisite:
   - `test_dag.py` should automatically appear in the Airflow UI.

```bash
aws s3 cp test_dag.py s3://<your_airflow_bucket_name>/dags/
```

9. Trigger the DAG by turning it to **On**
    - ![Trigger the DAG by turning it to On](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-7.jpg)

10. Choose **test\_dag**
    - go to the detail page for the DAG.
    - On the **Graph View** tab, we can see the whole workflow of our pipeline and each individual task as defined in our DAG code.
    - ![On the Graph View tab, we can see the whole workflow of our pipeline and each individual task as defined in our DAG code.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-8.jpg)
  

11. Optionally, to trigger the DAG, choose **Trigger DAG** and add the following JSON formatted configuration before activate the DAG.
    - ![Optionally, to trigger the DAG, choose Trigger DAG and add the following JSON formatted configuration before activate the DAG.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-9.jpg)
    - You now get an email when failure happens on any of the tasks. 
    - You can also configure to get email notification when retry happens as well.

12. On the Amazon EMR console, find the EMR cluster created by the `create_cluster_task` definition.
    - ![On the Amazon EMR console, find the EMR cluster created by the create_cluster_task definition.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-10.jpg) 

13. On the Airflow UI, you can switch tabs to check the status of the workflow tasks.
    - see on the **Tree View** tab that the workflow is complete and all the tasks are successful.
    - ![After a few minutes, we can see on the Tree View tab that the workflow is complete and all the tasks are successful.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-11.jpg)
    - On the **Gantt** tab, we can see the time distribution of all the tasks of our workflow.
    - ![On the Gantt tab, we can see the time distribution of all the tasks of our workflow.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-12.jpg) 
    - As specified in our DAG definition, the EMR cluster is stopped when the workflow is complete.
    - Because we use the cron expression `0 * * * *` as the scheduled running interval for our workflow, if the triggered status of the DAG is **ON**, it runs every hour. You need to switch the status to **OFF** if you don’t want it to run again.

14. On the Amazon S3 console, view the result of our notebook job in the S3 folder.
    - ![On the Amazon S3 console, view the result of our notebook job in the S3 folder.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-13.jpg) 


For example, the following screenshot is the output for the `Books` category that we provided as a value in the `CATEGORIES` parameter. As we can see, `Go Set a Watchman: A Novel` is the best `Books` seller from the week of 8-25-2015 to 8-31-2015.

![As we can see, Go Set a Watchman: A Novel is the best Books seller from the week of 8-25-2015 to 8-31-2015.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/26/BDB-1140-14.jpg)



---


## [Building complex workflows with Amazon MWAA, AWS Step Functions, AWS Glue, and Amazon EMR](https://noise.getoto.net/2021/01/11/building-complex-workflows-with-amazon-mwaa-aws-step-functions-aws-glue-and-amazon-emr/) 


[2021-01-11](https://noise.getoto.net/2021/01/11/building-complex-workflows-with-amazon-mwaa-aws-step-functions-aws-glue-and-amazon-emr/) [Dipankar Ghosal](https://noise.getoto.net/author/dipankar-ghosal/)

Post Syndicated from [Dipankar Ghosal](https://noise.getoto.net/author/0/ "Read other posts by Dipankar Ghosal") original [https://aws.amazon.com/blogs/big-data/building-complex-workflows-with-amazon-mwaa-aws-step-functions-aws-glue-and-amazon-emr/](https://aws.amazon.com/blogs/big-data/building-complex-workflows-with-amazon-mwaa-aws-step-functions-aws-glue-and-amazon-emr/)

[Amazon Managed Workflows for Apache Airflow](https://aws.amazon.com/managed-workflows-for-apache-airflow/) (Amazon MWAA) is a fully managed service that makes it easy to run open-source versions of Apache Airflow on AWS and build workflows to run your [extract, transform, and load](https://en.wikipedia.org/wiki/Extract,_transform,_load) (ETL) jobs and data pipelines.

You can use [AWS Step Functions](https://aws.amazon.com/step-functions/) as a serverless function orchestrator to build scalable big data pipelines using services such as [Amazon EMR](https://aws.amazon.com/emr/) to run Apache Spark and other open-source applications on AWS in a cost-effective manner, and use [AWS Glue](https://aws.amazon.com/glue/) for a serverless environment to prepare (extract and transform) and load large amounts of datasets from a variety of sources for analytics and data processing with Apache Spark ETL jobs

For production pipelines, a common use case is to read data originating from a variety of sources. This data requires transformation to extract business value and generate insights before sending to downstream applications, such as machine learning algorithms, analytics dashboards, and business reports.

This post demonstrates how to use Amazon MWAA as a primary workflow management service to create and run complex workflows and extend the directed acyclic graph (DAG) to start and monitor a state machine created using Step Functions. In Airflow, a DAG is a collection of all the tasks you want to run, organized in a way that reflects their relationships and dependencies.

Architectural overview
----------------------

The following diagram illustrates the architectural overview of the components involved in the orchestration of the workflow. This workflow uses Amazon EMR to preprocess data and starts a Step Functions state machine. The state machine transforms data using AWS Glue.

![The state machine transforms data using AWS Glue.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-1.jpg)

![The state machine transforms data using AWS Glue.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20450%22%3E%3C/svg%3E)

The workflow includes the following core components:

1. Airflow Scheduler triggers the DAG based on a schedule or manually.
2. DAG uses `PythonOperator` to create an EMR cluster and waits for the cluster creation process to complete.
3. DAG uses a custom operator `EmrSubmitAndMonitorStepOperator` to submit and monitor the Amazon EMR step.
4. DAG uses `PythonOperator` to stop the EMR cluster when the preprocessing tasks are complete.
5. DAG starts a Step Functions state machine and monitors it for completion using `PythonOperator`.

You can build complex ETL pipelines with Step Functions separately and trigger them from an Airflow DAG.

Prerequisites
-------------

Before starting, create an Amazon MWAA environment. If this is your first time using Amazon MWAA, see [Introducing Amazon Managed Workflows for Apache Airflow (MWAA)](https://aws.amazon.com/blogs/aws/introducing-amazon-managed-workflows-for-apache-airflow-mwaa/).

Take a note of the [Amazon Simple Storage Service](https://aws.amazon.com/s3) (Amazon S3) bucket that stores the DAGs. It’s located on the environment details page on the Amazon MWAA console.

![Take a note of the Amazon Simple Storage Service (Amazon S3) bucket that stores the DAGs.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-2.jpg)

![Take a note of the Amazon Simple Storage Service (Amazon S3) bucket that stores the DAGs.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20370%22%3E%3C/svg%3E)

Also note the [AWS Identity and Access Management](https://aws.amazon.com/iam) (IAM) execution role. This role should be modified to allow MWAA to read and write from your S3 bucket, submit an Amazon EMR step, start a Step Functions state machine, and read from the [AWS Systems Manager Parameter Store](https://docs.aws.amazon.com/systems-manager/latest/userguide/systems-manager-parameter-store.html). The IAM role is available in the **Permissions** section of the environment details.

![The IAM role is available in the Permissions section of the environment details.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-3.jpg)

![The IAM role is available in the Permissions section of the environment details.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20390%22%3E%3C/svg%3E)

The solution references Systems Manager parameters in an [AWS CloudFormation](https://aws.amazon.com/cloudformation) template and scripts. For information on adding and removing IAM identity permissions, see [Adding and removing IAM identity permissions](https://docs.aws.amazon.com/IAM/latest/UserGuide/access_policies_manage-attach-detach.html). A sample IAM policy is also provided in the GitHub repository [amazon-mwaa-complex-workflow-using-step-functions](https://github.com/aws-samples/amazon-mwaa-complex-workflow-using-step-functions/blob/main/setup/additional_policy.json).

For this post, we use the [MovieLens dataset](https://grouplens.org/datasets/movielens/latest/). We concurrently convert the MovieLens CSV files to Parquet format and save them to Amazon S3 as part of preprocessing.

Setting up the state machine using Step Functions
-------------------------------------------------

Our solution extends the ETL pipeline to run a Step Functions state machine from the Airflow DAG. Step Functions lets you build visual workflows that enable fast translation of business requirements into technical requirements. With Step Functions, you can set up dependency management and failure handling using a JSON-based template. A _workflow_ is a series of steps, such as tasks, choices, parallel runs, and timeouts with the output of one step acting as input into the next. For more information about other use cases, see [AWS Step Functions Use Cases](https://aws.amazon.com/step-functions/use-cases/).

The following diagram shows the ETL process set up through a Step Functions state machine.

![The following diagram shows the ETL process set up through a Step Functions state machine.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-4.jpg)

![The following diagram shows the ETL process set up through a Step Functions state machine.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20222%22%3E%3C/svg%3E)

In the workflow, the Process Data step runs an AWS Glue job, and the Get Job Status step periodically checks for the job completion. The AWS Glue job reads the input datasets and creates output data for the most popular movies and top-rated movies. After the job is complete, the Run Glue Crawler step runs an AWS Glue crawler to catalog the data. The workflow also allows you to monitor and respond to failures at any stage.

Creating resources
------------------

Create your resources by following the installation instructions provided in the [amazon-mwaa-complex-workflow-using-step-functions](https://github.com/aws-samples/amazon-mwaa-complex-workflow-using-step-functions) README.md.

Running the ETL workflow
------------------------

To run your ETL workflow, complete the following steps:

1. On the Amazon MWAA console, choose **Open Airflow UI**.
2. Locate the `mwaa_movielens_demo` DAG.
3. Turn on the DAG.

![Turn on the DAG.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-5.jpg)

![Turn on the DAG.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20379%22%3E%3C/svg%3E)

4. Select the `mwaa_movielens_demo` DAG and choose **Graph View**.

This displays the overall ETL pipeline managed by Airflow.

![This displays the overall ETL pipeline managed by Airflow.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-6.jpg)

![This displays the overall ETL pipeline managed by Airflow.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20384%22%3E%3C/svg%3E)

5. To view the DAG code, choose **Code**.

![To view the DAG code, choose Code.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-7.jpg)

![To view the DAG code, choose Code.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20410%22%3E%3C/svg%3E)

The code for the custom operator can be found in the [amazon-mwaa-complex-workflow-using-step-functions](https://github.com/aws-samples/amazon-mwaa-complex-workflow-using-step-functions/blob/main/dags/mwaalib/emr_submit_and_monitor_step.py) GitHub repo.

6. From the Airflow UI, select the mwaa\_movielens\_demo DAG and choose **Trigger DAG**.
7. Leave the **Optional Configuration** JSON box blank.

![Leave the Optional Configuration JSON box blank.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-8.jpg)

![Leave the Optional Configuration JSON box blank.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20411%22%3E%3C/svg%3E)

When the Airflow DAG runs, the first task calls the `PythonOperator` to create an EMR cluster using Boto3. Boto is the AWS SDK for Python. It enables Python developers to create, configure, and manage AWS services, such as [Amazon Elastic Compute Cloud](https://aws.amazon.com/ec2) (Amazon EC2) and Amazon S3. Boto provides object-oriented API, as well as low-level access to AWS services.

The second task waits until the EMR cluster is ready and in the Waiting state. As soon as the cluster is ready, the data load task runs, followed by the data preprocessing tasks, which are started in parallel using `EmrSubmitAndMonitorStepOperator`. Concurrency in the current Airflow DAG is set to 3, which runs three tasks in parallel. You can change the concurrency of Amazon EMR to run multiple Amazon EMR steps in parallel.

When the data preprocessing tasks are complete, the EMR cluster is stopped and the DAG starts the Step Functions state machine to initiate data transformation.

The final task in the DAG monitors the completion of the Step Functions state machine.

The DAG run should complete in approximately 10 minutes.

Verifying the DAG run
---------------------

While the DAG is running, you can view the task logs.

1. From **Graph View**, select any task and choose **View Log**.

![From Graph View, select any task and choose View Log.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-9.jpg)

![From Graph View, select any task and choose View Log.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20406%22%3E%3C/svg%3E)

2. When the DAG starts the Step Functions state machine, verify the status on the Step Functions console.

![When the DAG starts the Step Functions state machine, verify the status on the Step Functions console.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-10.jpg)

![When the DAG starts the Step Functions state machine, verify the status on the Step Functions console.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20389%22%3E%3C/svg%3E)

3. You can also monitor ETL process completion from the Airflow UI.

![You can also monitor ETL process completion from the Airflow UI.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-11.jpg)

![You can also monitor ETL process completion from the Airflow UI.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20414%22%3E%3C/svg%3E)

4. On the Airflow UI, verify the completion from the log entries.

![On the Airflow UI, verify the completion from the log entries.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-12.jpg)

![On the Airflow UI, verify the completion from the log entries.](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20800%20410%22%3E%3C/svg%3E)

Querying the data
-----------------

After the successful completion of the Airflow DAG, two tables are created in the AWS Glue Data Catalog. To query the data with [Amazon Athena](https://aws.amazon.com/athena), complete the following steps:

1. On the Athena console, choose **Databases**.
2. Select the `mwaa-movielens-demo-db` database.

You should see the two tables. If the tables aren’t listed, verify that the AWS Glue crawler run is complete and that the console is showing the correct Region.

3. Run the following query:

        SELECT * FROM "mwaa-movielens-demo-db"."most_popular_movies" limit 10;


The following screenshot shows the output.

![The following screenshot shows the output.](https://d2908q01vomqb2.cloudfront.net/b6692ea5df920cad691c20319a6fffd7a4a766b8/2021/01/05/BDB1205-13.jpg)





## [Introducing Amazon Managed Workflows for Apache Airflow (MWAA)](https://noise.getoto.net/2020/11/24/introducing-amazon-managed-workflows-for-apache-airflow-mwaa/)


[2020-11-24](https://noise.getoto.net/2020/11/24/introducing-amazon-managed-workflows-for-apache-airflow-mwaa/) [Danilo Poccia](https://noise.getoto.net/author/danilo-poccia/)

Post Syndicated from [Danilo Poccia](https://noise.getoto.net/author/0/ "Read other posts by Danilo Poccia") original [https://aws.amazon.com/blogs/aws/introducing-amazon-managed-workflows-for-apache-airflow-mwaa/](https://aws.amazon.com/blogs/aws/introducing-amazon-managed-workflows-for-apache-airflow-mwaa/)

As the volume and complexity of your data processing pipelines increase, you can simplify the overall process by decomposing it into a series of smaller tasks and coordinate the execution of these tasks as part of a **workflow**. To do so, many developers and data engineers use [Apache Airflow](https://airflow.apache.org/), a platform created by the community to programmatically author, schedule, and monitor workflows. With Airflow you can manage workflows as scripts, monitor them via the user interface (UI), and extend their functionality through a set of powerful plugins. However, manually installing, maintaining, and scaling Airflow, and at the same time handling security, authentication, and authorization for its users takes much of the time you’d rather use to focus on solving actual business problems.

For these reasons, I am happy to announce the availability of [Amazon Managed Workflows for Apache Airflow (MWAA)](https://aws.amazon.com/managed-workflows-for-apache-airflow/), a fully managed service that makes it easy to run open-source versions of Apache Airflow on AWS, and to build workflows to execute your [extract-transform-load (ETL)](https://en.wikipedia.org/wiki/Extract,_transform,_load) jobs and data pipelines.

Airflow workflows retrieve input from sources like [Amazon Simple Storage Service (S3)](https://aws.amazon.com/s3/) using [Amazon Athena](https://aws.amazon.com/athena) queries, perform transformations on [Amazon EMR](https://aws.amazon.com/emr) clusters, and can use the resulting data to train machine learning models on [Amazon SageMaker](https://aws.amazon.com/sagemaker/). Workflows in Airflow are authored as [Directed Acyclic Graphs (DAGs)](https://airflow.apache.org/docs/stable/concepts.html#dags) using the Python programming language.

A key benefit of Airflow is its open extensibility through **plugins** which allows you to create tasks that interact with AWS or on-premise resources required for your workflows including [AWS Batch](https://aws.amazon.com/batch), [Amazon CloudWatch](https://aws.amazon.com/cloudwatch/), [Amazon DynamoDB](https://aws.amazon.com/dynamodb/), [AWS DataSync](https://aws.amazon.com/datasync/), [Amazon ECS](https://aws.amazon.com/ecs/) and [AWS Fargate](https://aws.amazon.com/fargate/ "AWS Fargate"), [Amazon Elastic Kubernetes Service (EKS)](https://aws.amazon.com/eks/), [Amazon Kinesis Firehose](https://aws.amazon.com/kinesis/firehose/), [AWS Glue](https://aws.amazon.com/glue/ "AWS Glue"), [AWS Lambda](https://aws.amazon.com/lambda/), [Amazon Redshift](https://aws.amazon.com/redshift/), [Amazon Simple Queue Service (SQS)](https://aws.amazon.com/sqs/), and [Amazon Simple Notification Service (SNS)](https://aws.amazon.com/sns/).

To improve observability, Airflow **metrics** can be published as CloudWatch Metrics, and **logs** can be sent to CloudWatch Logs. Amazon MWAA provides automatic minor version **upgrades** and patches by default, with an option to designate a maintenance window in which these upgrades are performed.

You can use Amazon MWAA with these three steps:

1. **Create an environment** – Each environment contains your Airflow cluster, including your scheduler, workers, and web server.
2. **Upload your DAGs and plugins to S3** – Amazon MWAA loads the code into Airflow automatically.
3. **Run your DAGs in Airflow** – Run your DAGs from the Airflow UI or command line interface (CLI) and monitor your environment with CloudWatch.

Let’s see how this works in practice!

**How to Create an Airflow Environment Using Amazon MWAA  
**In the [Amazon MWAA console](https://console.aws.amazon.com/mwaa/), I click on **Create environment**. I give the environment a name and select the **Airflow version** to use.

[

![](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/24/mwaa-create-environment-1-1024x342.png)

![](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%201024%20342%22%3E%3C/svg%3E)](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/24/mwaa-create-environment-1.png)

Then, I select the S3 bucket and the folder to load my **DAG code**. The bucket name must start with `airflow-`.

Optionally, I can specify a plugins file and a requirements file:

- The **plugins** file is a ZIP file containing the plugins used by my DAGs.
- The **requirements** file describes the Python dependencies to run my DAGs.

For plugins and requirements, I can select the [S3 object version](https://docs.aws.amazon.com/AmazonS3/latest/dev/Versioning.html) to use. In case the plugins or the requirements I use create a non-recoverable error in my environment, Amazon MWAA will automatically roll back to the previous working version.

[

![](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/17/mwaa-dag-code-s3-1012x1024.png)

![](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%201012%201024%22%3E%3C/svg%3E)](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/17/mwaa-dag-code-s3.png)  
I click **Next** to configure the advanced settings, starting with **networking**. Each environment runs in a [Amazon Virtual Private Cloud](https://aws.amazon.com/vpc/) using private subnets in two [availability zones](https://aws.amazon.com/about-aws/global-infrastructure/regions_az/#Availability_Zones). Web server access to the Airflow UI is always protected by a secure login using [AWS Identity and Access Management (IAM)](https://aws.amazon.com/iam/). However, you can choose to have web server access on a public network so that you can login over the Internet, or on a private network in your VPC. For simplicity, I select a **Public network**. I let Amazon MWAA create a new [security group](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_SecurityGroups.html) with the correct inbound and outbound rules. Optionally, I can add one or more existing security groups to fine-tune control of inbound and outbound traffic for your environment.

[

![](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/24/mwaa-networking-1-881x1024.png)

![](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%20881%201024%22%3E%3C/svg%3E)](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/24/mwaa-networking-1.png)

Now, I configure my **environment class**. Each environment includes a scheduler, a web server, and a worker. Workers automatically scale up and down according to my workload. We provide you a suggestion on which class to use based on the number of DAGs, but you can monitor the load on your environment and modify its class at any time.

[

![](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/17/mwaa-environment-class-1024x555.png)

![](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%201024%20555%22%3E%3C/svg%3E)](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/17/mwaa-environment-class.png)

**Encryption** is always enabled for data at rest, and while I can select a customized key managed by [AWS Key Management Service (KMS)](https://aws.amazon.com/kms/) I will instead keep the default key that AWS owns and manages on my behalf.

[

![](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/24/mwaa-encryption-1-1024x286.png)

![](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%201024%20286%22%3E%3C/svg%3E)](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/24/mwaa-encryption-1.png)

For **monitoring**, I publish environment performance to CloudWatch Metrics. This is enabled by default, but I can disable CloudWatch Metrics after launch. For the **logs**, I can specify the log level and which Airflow components should send their logs to CloudWatch Logs. I leave the default to send only the task logs and use log level `INFO`.

[

![](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/24/mwaa-monitoring-1-1024x715.png)

![](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%201024%20715%22%3E%3C/svg%3E)](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/24/mwaa-monitoring-1.png)

I can modify the default settings for Airflow **configuration options**, such as `default_task_retries` or `worker_concurrency`. For now, I am not changing these values.

[

![](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/17/mwaa-airflow-configuration-1024x603.png)

![](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%201024%20603%22%3E%3C/svg%3E)](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/17/mwaa-airflow-configuration.png)

Finally, but most importantly, I configure the **permissions** that will be used by my environment to access my DAGs, write logs, and run DAGs accessing other AWS resources. I select **Create a new role** and click on **Create environment**. After a few minutes, the new Airflow environment is ready to be used.

[

![](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/17/mwaa-permissions-1024x361.png)

![](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%201024%20361%22%3E%3C/svg%3E)](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/17/mwaa-permissions.png)

**Using the Airflow UI  
**In the Amazon MWAA console, I look for the new environment I just created and click on **Open Airflow UI**. A new browser window is created and I am authenticated with a secure login via AWS IAM.

There, I look for a DAG that I put on S3 in the `movie_list_dag.py` file. The DAG is downloading the [MovieLens dataset](https://grouplens.org/datasets/movielens/), processing the files on S3 using [Amazon Athena](https://aws.amazon.com/athena), and loading the result to a Redshift cluster, creating the table if missing.

Here’s the full source code of the DAG:

    from airflow import DAG
    from airflow.operators.python_operator import PythonOperator
    from airflow.operators import HttpSensor, S3KeySensor
    from airflow.contrib.operators.aws_athena_operator import AWSAthenaOperator
    from airflow.utils.dates import days_ago
    from datetime import datetime, timedelta
    from io import StringIO
    from io import BytesIO
    from time import sleep
    import csv
    import requests
    import json
    import boto3
    import zipfile
    import io
    s3_bucket_name = 'my-bucket'
    s3_key='files/'
    redshift_cluster='redshift-cluster-1'
    redshift_db='dev'
    redshift_dbuser='awsuser'
    redshift_table_name='movie_demo'
    test_http='https://grouplens.org/datasets/movielens/latest/'
    download_http='http://files.grouplens.org/datasets/movielens/ml-latest-small.zip'
    athena_db='demo_athena_db'
    athena_results='athena-results/'
    create_athena_movie_table_query="""
    CREATE EXTERNAL TABLE IF NOT EXISTS Demo_Athena_DB.ML_Latest_Small_Movies (
      `movieId` int,
      `title` string,
      `genres` string
    )
    ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
    WITH SERDEPROPERTIES (
      'serialization.format' = ',',
      'field.delim' = ','
    ) LOCATION 's3://pinwheeldemo1-pinwheeldagsbucketfeed0594-1bks69fq0utz/files/ml-latest-small/movies.csv/ml-latest-small/'
    TBLPROPERTIES (
      'has_encrypted_data'='false',
      'skip.header.line.count'='1'
    );
    """
    create_athena_ratings_table_query="""
    CREATE EXTERNAL TABLE IF NOT EXISTS Demo_Athena_DB.ML_Latest_Small_Ratings (
      `userId` int,
      `movieId` int,
      `rating` int,
      `timestamp` bigint
    )
    ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
    WITH SERDEPROPERTIES (
      'serialization.format' = ',',
      'field.delim' = ','
    ) LOCATION 's3://pinwheeldemo1-pinwheeldagsbucketfeed0594-1bks69fq0utz/files/ml-latest-small/ratings.csv/ml-latest-small/'
    TBLPROPERTIES (
      'has_encrypted_data'='false',
      'skip.header.line.count'='1'
    );
    """
    create_athena_tags_table_query="""
    CREATE EXTERNAL TABLE IF NOT EXISTS Demo_Athena_DB.ML_Latest_Small_Tags (
      `userId` int,
      `movieId` int,
      `tag` int,
      `timestamp` bigint
    )
    ROW FORMAT SERDE 'org.apache.hadoop.hive.serde2.lazy.LazySimpleSerDe'
    WITH SERDEPROPERTIES (
      'serialization.format' = ',',
      'field.delim' = ','
    ) LOCATION 's3://pinwheeldemo1-pinwheeldagsbucketfeed0594-1bks69fq0utz/files/ml-latest-small/tags.csv/ml-latest-small/'
    TBLPROPERTIES (
      'has_encrypted_data'='false',
      'skip.header.line.count'='1'
    );
    """
    join_tables_athena_query="""
    SELECT REPLACE ( m.title , '"' , '' ) as title, r.rating
    FROM demo_athena_db.ML_Latest_Small_Movies m
    INNER JOIN (SELECT rating, movieId FROM demo_athena_db.ML_Latest_Small_Ratings WHERE rating > 4) r on m.movieId = r.movieId
    """
    def download_zip():
        s3c = boto3.client('s3')
        indata = requests.get(download_http)
        n=0
        with zipfile.ZipFile(io.BytesIO(indata.content)) as z:       
            zList=z.namelist()
            print(zList)
            for i in zList:
                print(i)
                zfiledata = BytesIO(z.read(i))
                n += 1
                s3c.put_object(Bucket=s3_bucket_name, Key=s3_key+i+'/'+i, Body=zfiledata)
    def clean_up_csv_fn(**kwargs):    
        ti = kwargs['task_instance']
        queryId = ti.xcom_pull(key='return_value', task_ids='join_athena_tables' )
        print(queryId)
        athenaKey=athena_results+"join_athena_tables/"+queryId+".csv"
        print(athenaKey)
        cleanKey=athena_results+"join_athena_tables/"+queryId+"_clean.csv"
        s3c = boto3.client('s3')
        obj = s3c.get_object(Bucket=s3_bucket_name, Key=athenaKey)
        infileStr=obj['Body'].read().decode('utf-8')
        outfileStr=infileStr.replace('"e"', '')
        outfile = StringIO(outfileStr)
        s3c.put_object(Bucket=s3_bucket_name, Key=cleanKey, Body=outfile.getvalue())
    def s3_to_redshift(**kwargs):    
        ti = kwargs['task_instance']
        queryId = ti.xcom_pull(key='return_value', task_ids='join_athena_tables' )
        print(queryId)
        athenaKey='s3://'+s3_bucket_name+"/"+athena_results+"join_athena_tables/"+queryId+"_clean.csv"
        print(athenaKey)
        sqlQuery="copy "+redshift_table_name+" from '"+athenaKey+"' iam_role 'arn:aws:iam::163919838948:role/myRedshiftRole' CSV IGNOREHEADER 1;"
        print(sqlQuery)
        rsd = boto3.client('redshift-data')
        resp = rsd.execute_statement(
            ClusterIdentifier=redshift_cluster,
            Database=redshift_db,
            DbUser=redshift_dbuser,
            Sql=sqlQuery
        )
        print(resp)
        return "OK"
    def create_redshift_table():
        rsd = boto3.client('redshift-data')
        resp = rsd.execute_statement(
            ClusterIdentifier=redshift_cluster,
            Database=redshift_db,
            DbUser=redshift_dbuser,
            Sql="CREATE TABLE IF NOT EXISTS "+redshift_table_name+" (title	character varying, rating	int);"
        )
        print(resp)
        return "OK"
    DEFAULT_ARGS = {
        'owner': 'airflow',
        'depends_on_past': False,
        'email': ['[email protected]'],
        'email_on_failure': False,
        'email_on_retry': False
    }
    with DAG(
        dag_id='movie-list-dag',
        default_args=DEFAULT_ARGS,
        dagrun_timeout=timedelta(hours=2),
        start_date=days_ago(2),
        schedule_interval='*/10 * * * *',
        tags=['athena','redshift'],
    ) as dag:
        check_s3_for_key = S3KeySensor(
            task_id='check_s3_for_key',
            bucket_key=s3_key,
            wildcard_match=True,
            bucket_name=s3_bucket_name,
            s3_conn_id='aws_default',
            timeout=20,
            poke_interval=5,
            dag=dag
        )
        files_to_s3 = PythonOperator(
            task_id="files_to_s3",
            python_callable=download_zip
        )
        create_athena_movie_table = AWSAthenaOperator(task_id="create_athena_movie_table",query=create_athena_movie_table_query, database=athena_db, output_location='s3://'+s3_bucket_name+"/"+athena_results+'create_athena_movie_table')
        create_athena_ratings_table = AWSAthenaOperator(task_id="create_athena_ratings_table",query=create_athena_ratings_table_query, database=athena_db, output_location='s3://'+s3_bucket_name+"/"+athena_results+'create_athena_ratings_table')
        create_athena_tags_table = AWSAthenaOperator(task_id="create_athena_tags_table",query=create_athena_tags_table_query, database=athena_db, output_location='s3://'+s3_bucket_name+"/"+athena_results+'create_athena_tags_table')
        join_athena_tables = AWSAthenaOperator(task_id="join_athena_tables",query=join_tables_athena_query, database=athena_db, output_location='s3://'+s3_bucket_name+"/"+athena_results+'join_athena_tables')
        create_redshift_table_if_not_exists = PythonOperator(
            task_id="create_redshift_table_if_not_exists",
            python_callable=create_redshift_table
        )
        clean_up_csv = PythonOperator(
            task_id="clean_up_csv",
            python_callable=clean_up_csv_fn,
            provide_context=True     
        )
        transfer_to_redshift = PythonOperator(
            task_id="transfer_to_redshift",
            python_callable=s3_to_redshift,
            provide_context=True     
        )
        check_s3_for_key >> files_to_s3 >> create_athena_movie_table >> join_athena_tables >> clean_up_csv >> transfer_to_redshift
        files_to_s3 >> create_athena_ratings_table >> join_athena_tables
        files_to_s3 >> create_athena_tags_table >> join_athena_tables
        files_to_s3 >> create_redshift_table_if_not_exists >> transfer_to_redshift

In the code, different tasks are created using operators like `PythonOperator`, for generic Python code, or `AWSAthenaOperator`, to use the integration with [Amazon Athena](https://aws.amazon.com/athena). To see how those tasks are connected in the workflow, you can see the latest few lines, that I repeat here (without indentation) for simplicity:

    check_s3_for_key >> files_to_s3 >> create_athena_movie_table >> join_athena_tables >> clean_up_csv >> transfer_to_redshift
    files_to_s3 >> create_athena_ratings_table >> join_athena_tables
    files_to_s3 >> create_athena_tags_table >> join_athena_tables
    files_to_s3 >> create_redshift_table_if_not_exists >> transfer_to_redshift

The Airflow code is [overloading](https://docs.python.org/3/reference/datamodel.html#emulating-numeric-types) the right shift `>>` operator in Python to create a dependency, meaning that the task on the left should be executed first, and the output passed to the task on the right. Looking at the code, this is quite easy to read. Each of the four lines above is adding dependencies, and they are all evaluated together to execute the tasks in the right order.

In the Airflow console, I can see a **graph view** of the DAG to have a clear representation of how tasks are executed:

[

![](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/17/mwaa-graph-view-1024x466.png)

![](data:image/svg+xml,%3Csvg%20xmlns=%22http://www.w3.org/2000/svg%22%20viewBox=%220%200%201024%20466%22%3E%3C/svg%3E)](https://d2908q01vomqb2.cloudfront.net/da4b9237bacccdf19c0760cab7aec4a8359010b0/2020/11/17/mwaa-graph-view.png)

**Available Now  
**[Amazon Managed Workflows for Apache Airflow (MWAA)](https://aws.amazon.com/managed-workflows-for-apache-airflow/) is available today in US East (Northern Virginia), US West (Oregon), US East (Ohio), Asia Pacific (Singapore), Asia Pacific (Toyko), Asia Pacific (Sydney), Europe (Ireland), Europe (Frankfurt), and Europe (Stockholm). You can launch a new Amazon MWAA environment from the console, [AWS Command Line Interface (CLI)](https://aws.amazon.com/cli/), or [AWS SDKs](https://aws.amazon.com/tools/). Then, you can develop workflows in Python using Airflow’s ecosystem of integrations.

With Amazon MWAA, you pay based on the environment class and the workers you use. For more information, see the [pricing page](https://aws.amazon.com/managed-workflows-for-apache-airflow/pricing/).

Upstream compatibility is a core tenet of Amazon MWAA. Our code changes to the AirFlow platform are released back to open source.

With Amazon MWAA you can spend more time building workflows for your engineering and data science tasks, and less time managing and scaling the infrastructure of your Airflow platform.

[**Learn more about Amazon MWAA and get started today!**](https://aws.amazon.com/managed-workflows-for-apache-airflow/)

— [Danilo](https://twitter.com/danilop "Danilo on Twitter")

[Amazon Managed Workflows for Apache Airflow (Amazon MWAA)](https://noise.getoto.net/tag/amazon-managed-workflows-for-apache-airflow-amazon-mwaa/)[announcements](https://noise.getoto.net/tag/announcements/)[Application Integration](https://noise.getoto.net/tag/application-integration/)[launch](https://noise.getoto.net/tag/launch/)[news](https://noise.getoto.net/tag/news/)

The collective thoughts of the interwebz
----------------------------------------
 