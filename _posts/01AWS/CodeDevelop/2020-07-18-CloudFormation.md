---
title: AWS - CodeDevelop - CloudFormation
date: 2020-07-18 11:11:11 -0400
categories: [01AWS, CodeDevelop]
tags: [AWS]
toc: true
image:
---

[toc]

---

#  AWS CloudFormation   

> Infrastructure as code solution.

![Screen Shot 2021-01-18 at 16.28.28](https://i.imgur.com/nt8tRpD.png)


---

## benefits

1. <font color=red> manage, configure and provision the AWS infrastructure as code </font>
   - repeatedly and predictably model and provision resources
   - infrastructure is provisioned consistently
     - fewer mistakes
     - less time and effort than configure manually

2. <font color=red> Supports almost all the AWS services and programable </font>
   - provision a broad range of AWS resources.
     - <font color=blue> compare </font>
     - <font color=blue> Elastic Beanstalk </font>
       - more focussed on deploying web applications on EC2 
       - PaaS
     - <font color=blue> CloudFormation </font>
       - can deploy Elastic Beanstalk-hosted applications
       - however the reverse is not possible.

3. resources are <font color=red> defined by CloudFormation template </font>
   - Supports YAML and JSON
     - Logical IDs: reference resources within the template.
     - Physical IDs: identify resources outside of AWS CloudFormation templates, but only after the resources have been created.

4. CloudFormation <font color=red> interprets the template and makes the appropriate API calls </font> to create the resources defined.

5. <font color=red> version control </font> and peer review the templates
   - can be used to manage udpates & dependencies
   - can be used to rollback and delete the entire stack as well


6. AWS CloudFormation provides 2 methods for <font color=red> updating stacks </font>
   - <font color=red> direct update a stack </font>
     - submit changes
     - AWS CloudFormation immediately deploys them.
     - Use direct updates to quickly deploy the updates.
   - creating and executing <font color=red> change sets </font>
     - preview the changes AWS CloudFormation will make to your stack
     - and then decide whether to apply those changes.


6. free service (resources created would be charged)



---


## templates, stacks and change sets:

![CloudFormation](https://i.imgur.com/zu1yJYA.png)

![Pasted Graphic](https://i.imgur.com/71RbCIM.jpg)

---


### Template

template is used to <font color=red> describe the endstate of the infrustructure either provisioning or changing </font>
1. after created, upload it to CloudFormation using S3
2. CloudFormation reads the template and makes the API calles
3. the resulting resources are called a <font color=red> Stack </font>

CloudFormation template
- create templates to launch, configure, and connect AWS resources
  - standard templates for deploying infrastructure
- template can be stored in CodeCommit
  - maintain a history of the template and the infrastructure that has been deployed.

- CloudFormation determines the order of provisioning.
  - easy way to create a collection of related AWS resources and provision them in an orderly and predictable fashion.
  - Don’t need to worry about dependencies.
  - Architectural designs.

- Treat it as code
  - manage it by using version control, such as Gitor Subversion
  - Create, update and delete templates.

- is the <font color=red> single source of truth </font> for cloud environment.
  - Define an entire application stack (all the resources required for application) in a JSON  template file.
  - provides a common language to to model, describe and provision all the infrastructure resources and properties in the cloud environment.
  - model and provision in an automated and secure manner all the resources needed for your applications across all regions and accounts.


- Define runtime parameters for a template
  - such as EC2—Instance Size, Amazon EC2 Key Pair, etc.




Templates can be created by
- code editor supports JSON syntax, (Atom or Sublime Text)
- third party WYSIWYG editor
- build visually by CloudFormation Designer tool
  - Available in AWS Management Console
  - allows visualize using a drag and drop interface.
  - drag and drop resources onto a design area to automatically generate a JSON/YAML-formatted CloudFormation template
  - edite properties of the JSON or YAML template on same page.
  - can open and edite Existing CloudFormation templates  

YAML or JSON
- JavaScript Object Notation (JSON) / YAML-formatted templates.
- Both YAML/JSON-formatted templates have the same structure, support all the same feature

<font color=red> do not recommend </font>
- build all of an application's within one template
  - Resources should be grouped into templates
  - based on the ownership and the place in the application lifecycle.
  - minimum should separate network, security, and application resources into own templates.
- test environment and production environment should not share same templates.
  - Resources in a test environment need to change frequently
  - resources in a production environment should be relatively stable.
- sharing templates across management teams
  - because different needs and standards can impact teams inappropriately.



Organizing template:
- Avoid sharing a single template across applications for resources of the same type
  - unless you are deliberately centralizing control of that resource type.
  - no too many things inside of one template across numerous applications.
  - application template that supports several applications,
    - changes to the template will affect several applications
    - changes can cause all of the applications to be retested.  
- share template could potentially break
  - things that are specific to your environment,
    - such as Amazon EC2 key pairs, security group names, subnet IDs, and EBS—snapshot IDs.
  - It can be fixed by using parameters, mappings, and condition section in temple.
- storing templates contain security resources in a separate repository from other templates.



Template elements:
- Mandatory:
  - File format and version.
  - List of resources and associated configuration values.
- Not Mandatory:
  - Template parameters (limited to 60).
  - Output values (limited to 60).
  - List of data tables.


---


### Engine:
- Aws service component
- Interprets AWS cloudFormation template into stacks of AWS resources.

---


### Group:

![Screen Shot 2020-06-26 at 10.10.37](https://i.imgur.com/k3VHJRq.png)

- allows you to quickly provision a test environment to investigate possible breaches into your EC2 instance.
- Puppet and Chef integration is supported.
- Can use bootstrap scripts.
- Can define deletion policies.
- Can create roles in IAM.
- VPCs can be created and customized.
- VPC peering in the same AWS account can be performed.
- Route 53 is supported.



---


### Stack


---

#### Stack

1. <font color=red> A collection of resources </font> created by AWS cloudFormation templates
   - All the resources in a stack are defined by the stack's `AWS CloudFormation template`.
     - Deployed resources based on templates.
     - Create, update and delete stacks using templates.
     - Deployed through the Management Console, CLI or APIs.
     - Tracked and reviewable in the AWS management console

2. a collection of AWS resources that can <font color=red> manage as a single unit </font>
   - AWS CloudFormation treats the stack resources as a single unit
   - create, update, or delete a collection of resources by <font color=blue> creating, updating, or deleting stacks </font>
   - Example:
     - A stack can include all the resources required to run a web application
       - such as a web server, a database, and networking rules.
     - If no longer require that web application, simply delete the stack, and all of its related resources are deleted.

3. AWS CloudFormation ensures all `stack resources` are created or deleted as appropriate.
   - If a resource cannot be created, AWS CloudFormation rolls the stack back and automatically deletes any resources that were created.
   - If a resource cannot be deleted, any remaining resources are retained until the stack can be successfully deleted.
   - can work with stacks by using the AWS CloudFormation console, API, or AWS CLI.

4. Stacks can't create logical resources
   - stack listens to the direction of a template containing logical resources.
     - template describes a stack,
       - a collection of AWS resources want to deploy together as a group.
     - stack manages physical resources based on a logical resource template
     - stack making resources through the direction of a template.

   - Actions to resources can be tracked in CloudFormation's stack details.

5. Stacks control resources
   - if a stack is removed, the resource also be deleted, so are the resources that it created.
   - Stacks could disrupt a resource when performing updates to the resource.


7. Stack creation errors:
   - Automatic rollback on error is enabled by default.
   - will be charged for resources provisioned even if there is an error.



---



#### Nested stacks

1. Nested stacks are <font color=red> stacks created as part of other stacks </font>

2. Use nested stacks <font color=red> to declare common components (best practice) </font>
   - allow <font color=blue> re-use of CloudFormation code for common use cases </font>
     - such as standard configuration for load balancer, web server, application server, etc.

   - As infrastructure grows, declare the same components in multiple templates
     - separate out these common components and create a standard dedicated templates for each common use case
     - store it in S3
     - and refenrece it in the Resources section of other template using the Stack resource type
     - `Resourses: Type: AWS::CloudFormation::Stack`

   - Example:
     - a load balancer configuration that use for most of the stacks.
     - Instead of copying and pasting the same configurations into the templates,
     - create a dedicated template for the load balancer.
     - Then, just use the resource to reference that template from within other templates.

3. Nested stacks can contain other nested stacks,
   - resulting in a <font color=blue> hierarchy of stacks </font>

4. Certain stack operations, such as stack updates, should be initiated from the `root stack` rather than performed directly on `nested stacks themselves`.



---

Example:

```
Resourses:
  Type: AWS::CloudFormation::Stack
  Properties:
    NotificationARNs:
      - String
    Parameters:
      AWS CloudFormation Stack Parameters
    Tags:
      - Resource Tag
    TemplateURL: https://s3.amazonaws.com/.../template.yml
    TimeoutInMinutes: Integer
```


<img src="https://i.imgur.com/TU6BesT.png" width="200">

The <font color=red> root stack </font>
- the top-level stack to which all the nested stacks ultimately belong.
- each nested stack has an immediate parent stack.
- For the first level of nested stacks, the root stack is also the parent stack.

> Stack A is the `root stack` for all the other, nested, stacks in the hierarchy.
> For stack B, stack A is both the `parent stack`, as well as the `root stack`.
> For stack D, stack C is the `parent stack`;
> for stack C, stack B is the `parent stack`.



1. <kbd>AWS Management Console</kbd> -> <kbd>AWS CloudFormation console</kbd> -> Select the <kbd>stack</kbd>
2. Nested stacks display `NESTED` next to the stack name.
3. <font color=red> To view the root stack of a nested stack </font>
   - <kbd>Overview tab</kbd>: click the stack name listed as `Root stack`.
4. <font color=red> To view the nested stacks that belong to a root stack </font>
   - <kbd>AWS CloudFormation console</kbd> -> Click the name of the root stack whose nested stacks want to view.
   - Expand the <kbd>Resources</kbd> section.
   - Look for resources of type `AWS::CloudFormation::Stack`.


---



#### Cross stack references
- share outputs from one stack with another stack.
  - share things like IAM—roles, VPC—information, and security groups.
  - Before, use AWS CloudFormation custom resources to accomplish these tasks.
  - Now, export values from one stack and import them to another stack by using the new ImportValueintrinsic function.
- useful for customers who
  - separate their AWS infrastructure into logical components that grouped by stack
    - such as a network stack, an application stack, etc.
  - need a way to loosely couple stacks together as an alternative to nested stacks

---

### StackSets.

AWS CloudFormation StackSets

- extends the functionality of stacks by enabling <font color=red> create, update, or delete stacks across multiple accounts and regions with a single operation </font>

- An administrator account
  - the AWS account in which you create stack sets.
  - define and manage an AWS CloudFormation template
  - use the template as the basis for provisioning stacks into selected target accounts across specified regions.
  - A stack set is managed by signing in to the AWS administrator account in which it was created.

- A target account
  - the account into which you create, update, or delete one or more stacks in your stack set.
  - Before use a stack set to create stacks in a target account, must set up a trust relationship between the administrator and target accounts.

---


## Best Practices.
- AWS provides Python “helper scripts” which can help you install software and start services on your EC2 instances.
- Use CloudFormation to make changes to your landscape rather than going directly into the resources.
- Make use of Change Sets to identify potential trouble spots in your updates.
- Use Stack Policies to explicitly protect sensitive portions of your stack.
- Use a version control system such as CodeCommit or GitHub to track changes to templates.



---


## Charges:
- no additional charge for AWS CloudFormation.
- pay for AWS resources (such as EC2 instances, ELB load balancers, etc.) created using AWS CloudFormation in the same manner as if you created them manually.
- only pay for what you use, as you use it;
- there are no minimum fees and no required upfront commitments.


---


# setup

1. cloudformation
2. create stack
   1. select template
   2. stack name
   3. keypaire
   4. rollback on failure
3. delete stack


---


# CloudFormationTemplate.yml

```yml
AWSTemplateFormatVersion: 2010-09-09

# text string that describes the template    
Description: Template to create an EC2 instance and enable SSH



# data about the data, Some AWS CloudFormation features retrieve settings or configuration information that you define from the Metadata section.
Metadata:



# input custom values, pass the value of your template at runtime.
Parameters:
  KeyName:
    Description: Name of SSH KeyPair
    Type: 'AWS::EC2::KeyPair::KeyName'
    ConstraintDescription: Provide the name of an existing SSH key pair
  InstanceTypeParameter:
    Type: Sting
    Default: t2.micro
    AllowedValues: ["t2.micro", "m1.small", "m1.large"]
    Description: 'Enter t2.micro, m1.small or m1.large'



# provision resources based on environment
Conditions:



# Mandatory
# the AWS resource be included / created in the stack
Resources:
  # Logical ID:
  #   Type: 'ARNs'
  #   Properties:
  MyEC2Instance:
    Type: 'AWS::EC2::Instance'
    Properties:
      # InstanceType: t2.micro
      InstanceType: ('Ref': InstanceTypeParameter)
      ImageId: ami-0bdb1d6c15a40392c
      KeyName: !Ref KeyName
      SecurityGroups:
       - Ref: InstanceSecurityGroup
      Tags:
        - Key: Name
          Value: My CF Instance
      # How AWS CloudFormation should wait to launch a resource
      # until a specific, different resource has finished being created.
      DependsOn: myDB
  InstanceSecurityGroup:
    Type: 'AWS::EC2::SecurityGroup'
    Properties:
      GroupDescription: Enable SSH access via port 22
      SecurityGroupIngress:
        IpProtocol: tcp
        FromPort: 22
        ToPort: 22
        CidrIp: 0.0.0.0/0


# create custom mappings
# like different Region for diffenet AMI
# customize the properties of a resource based on certain conditions, which enables you to have fine-grained control over how your templates are launched.
Mappings:
  RegionMap:
    us-east-1: (t2.micro: ami-0bdb1d6c15a40392c)
    us-west-1: (t2.micro: ami-0bdb1d6c15a40392c)

# refernce code located in S3
# Lambda code or reusable snippets of CloudFormation code
Transforms:


# values that are returned whenever you view the properties of your stack.
Outputs:
  InstanceID:
    Description: The Instance ID
    Value: !Ref MyEC2Instance
```




---

# template Section


---

## Description: text string that describes the template    ￼
![Screen Shot 2020-06-26 at 10.13.23](https://i.imgur.com/ZcU1VMh.png)


---


## Metadata: data about the data  
  ￼
![Screen Shot 2020-06-26 at 10.44.06](https://i.imgur.com/xGuoh3a.png)

- Some AWS CloudFormation features retrieve settings or configuration information defined from the Metadata section.
- Define in the AWS CloudFormation-specific metadata keys:
  - `AWS::CloudFormation::Init`
    - Defines configuration tasks for the cfn-init helper script.
    - This script is useful for configuring and installing app on EC2 instances.
  - `AWS::CloudFormation::Interface`
    - Defines the grouping and ordering of input parameters when they are displayed in the AWS CloudFormation console.
    - By default, the AWS CloudFormationconsole alphabetically sorts parameters by their logical ID.
  - `AWS::CloudFormation::Designer`
    - Describes how your resources are laid out in AWS CloudFormationDesigner.
    - Designer automatically adds this information when you use it create and update templates.

---




## Parameter : to pass the value of the template at runtime

```json
"Parameters" : {
    "InstanceTypeParameter" : {
        "Type" : "String",
        "Default" : "t2.micro",
        "AllowedValues" : [ "t2.micro", "m1.small", "m1.large"],
        "Description" : "Enter t2.micro, m1.small, m1.large. Default is t2.micro"
        // appears in the AWS CloudFormationConsole when the template is launched.
    }
},
"Resources" : {
    // when an EC2 instance is launched in the Resources section
    "Instances" : {
        "Type" : "AWS::EC2::Instance",
        "Properties" : {
            // the Properties section of the instance can reference the InstanceTypeParameter specification.
            // the "Ec2Instance" resource references the InstanceTypeParameter specification for its instancetype.
            "InstanceType" : { "Ref" : "InstanceTypeParameter" },
            "ImageId" : "ami-20b65349",
        }
    }
}
```

- to pass the value of the template at runtime.
  - to customize things in the stack.

- specify details like
  - the range of acceptable AMI ImageIdnumbers,
  - key pairs,
  - subnets,
  - or any properties that must be specified for a resource.

- can specify allowed and default values for each parameter.

- declare parameters in a template's Parameters object.

- A parameter contains a list of attributes that define its value, and constraints against its value.
  - Type:
    - The only required attribute
    - can be String, Number, or CommaDelimitedList.
  - Description attribute:
    - what kind of value they should specify.
  - The parameter's name and description appear in the Specify Parameters page when a user uses the template in the Create Stack wizard


---



## Resources: declare the AWS resources be included/created in stack

![Screen Shot 2020-06-26 at 10.44.06](https://i.imgur.com/fQBHEj4.png)

- declare the AWS resources be included / created in the stack
  - such as an EC2 instance, an S3 bucket.
- These properties could also be set in the Parameters or Conditions sections

- must declare each resource separately;
- can specify multiple resources of the same type.
  - declare multiple resources, separate them with commas

```json
// a resource declaration defines 2 resources.
"Resources" : {
  // 1st resource is an EC2 instance, MyInstance
  "Instances1" : {
      "Type" : "AWS::EC2::Instance",
      "Properties" : {
          // MyQueue resource as part of its UserData property,
          "UserData" : { "Fn::Base64" : { "Fn::Join" : [ "", [ "Queue=", { "Ref" : "MyQueue" }]]}},
          // AvailabilityZone setting: the EC2 instance will be hosted in Northern Virginia us-east-1a.
          "AvailabilityZOne" : "us-east-1a",
          "ImageId" : "ami-20b65349"
      },
      // DependsOn:
      // How AWS CloudFormation should wait to launch a resource until a specific, different resource has finished being created.
      // create the EC@ after the myDB instance has been created
      "DependsOn" : "myDB"
  },
  // 2nd resource is an Amazon Simple Queue Service SQS—queue "MyQueue".
  "MyQueue" : {
      "Type" : "AWS::SQS::Queue",
      "Properties" : {}
  },
  "myDB" : {
      "Type" : "AWS::RDS::DBInstance",
      "Properties" : {}
  }
}
```


### DependsOn

```json
"Resources" : {
  "Instances1" : {
      "Type" : "AWS::EC2::Instance",
      "Properties" : {
          "UserData" : { "Fn::Base64" : { "Fn::Join" : [ "", [ "Queue=", { "Ref" : "MyQueue" }]]}},
          "AvailabilityZOne" : "us-east-1a",
          "ImageId" : "ami-20b65349"
      },
      // DependsOn:
      // How AWS CloudFormation should wait to launch a resource until a specific, different resource has finished being created.
      // create the EC@ after the myDB instance has been created
      "DependsOn" : "myDB"
  },
  "myDB" : {
      "Type" : "AWS::RDS::DBInstance",
      "Properties" : {}
  }
}
```

The DependsOn attribute should be used when
- need to wait for something.
- Some resources in a VPC require a gateway (either internet gateway / VPN gateway)
  - If AWS CloudFormation template defines a VPC, a gateway, and a gateway attachment,
  - any resources that require the gateway depend on the gateway attachment.

  - Other VPC-dependent resources
    - Auto Scaling groups,
    - Amazon EC2 instances,
      - an EC2 instance with a public IP address depends on the VPC gateway attachment if the VPC and internet gateway resources are also declared in the same template.
    - Elastic Load Balancing load balancers,
    - Elastic IP addresses,
    - Amazon RDS—database instances,
    - Amazon Virtual Private Cloud VPC—routes that include the internet gateway



### wait condition: wait/pause and receive a signal to continue

```json
"Resources" : {
  "Instances1" : {
      "Type" : "AWS::EC2::Instance",
      "Properties" : {
          "UserData" : { "Fn::Base64" : { "Fn::Join" : [ "", [ "Queue=", { "Ref" : "MyQueue" }]]}},
          "AvailabilityZOne" : "us-east-1a",
          "ImageId" : "ami-20b65349"
      },
      // DependsOn:
      // How AWS CloudFormation should wait to launch a resource until a specific, different resource has finished being created.
      // create the EC2 after the myDB instance has been created
      "DependsOn" : "myDB"
  },
  "myWaitCondition" : {
      "Type" : "AWS::CloudFormation::WaitCondition",
      // create the EC after the myDB instance has been created
      "DependsOn" : "myDB",
      "Properties" : {
          "Handle" : { "R2ef" : "myWaitHandle"},
          "Timeout" : "4500"
          // It will wait for that EC2 instance or it will time out after 4,500 seconds.
      }
  },
  "myDB" : {
      "Type" : "AWS::RDS::DBInstance",
      "Properties" : {}
  }
}
```

- `AWS::CloudFormation::WaitConditionHandle`
  - has no properties.
  - reference the `WaitCondition Handlere source` by using the Ref function,
    - AWS CloudFormation returns a pre-signed URL.
    - You pass this URL to applications or scripts that are running on your EC2 instances to send signals to that URL.
  - An associated `AWS::CloudFormation::WaitCondition` resource checks the URL for the required number of success signals or for a failure signal.
  - The timeout value is in seconds


### creation policy: pause stack creation and wait for specified number of successful signals.

```json
"Resources" : {
  "AutoScalingGroup" : {
      "Type" : "AWS::AutoScaling::AutoScalingGroup",
      "Properties" : {
          "AvailabilityZOne" : {"Fn::GetAZs" : ""},
          "LaunchConfigurationName" : { "Ref" : "LaunchConfig" },
          "DesiredCapacity" : "3",
          "MinSize" : "1",
          "MaxSize" : "4"
      },
      "CreationPolicy" : {
          " ResourceSignal" : {
              "Count" : "3",
              // “PT#H#M#S: # is the number of hours, minutes, and seconds.
              "Timeout" : "PT15M"
              // wait for 3 AutoCaling instance but time out after 15m
          }
      }
  }
}
```

- This creation policy is associated with <font color=red> the creation of an Auto Scaling group </font>
  - <font color=blue> three successful signals within fifteen minutes are required or it will time out </font>
    - Set timeouts to give resources enough time to get up and running.
  - When the timeout period expires, or a failure signal is received,
  - the creation of the resource fails,
  - and AWS CloudFormation rolls the stack back.

---


## Mappings : keys and their associated values

![Screen Shot 2020-06-26 at 13.18.49](https://i.imgur.com/lTWjdxg.png)


- specify conditional parameter values.
- customize the properties of a resource based on certain conditions
  - enables fine-grained control over how the templates launched.


```json
"Mappings" : {
  "RegionAndAMIID" : {
    "us-east-1" : {
        "m1.small" : " ami-aa",
        "te.micro" : " ami-bb",
    },
    "us-east-2" : {
        "m1.small" : " ami-cc",
        "te.micro" : " ami-dd",
    }
  }
}
```


- For example,
  - use Regions and specify multiple mapping levels
    - an AMI ImageId number is unique to a Region, and the person who use the template not necessarily know which AMI to use.
    - provide the `AMI lookup list` using the Mappings parameter.
    - contains a map for Regions.
    - The mapping
      - lists the AMI that should be used, based on the Region the instance will launch in
      - specifies an AMI based on the type of instance that is launched within a specific Region.
      - if an m1.small instance is used, the AMI be used is ami-1ccae774.
      - This mapping ties specific machine images to instances.

---

## Conditions section : includes statements, control (optional)

![Screen Shot 2020-06-26 at 13.25.07](https://i.imgur.com/wr4a92A.png)

- whether certain resources are created, or certain properties are assigned a value during the creation or update of a stack.
  - can compare whether a value is equal to another value.
  - Based on the result of that condition, conditionally create resources.
  - If multiple conditions, separate them with commas.

- use conditions when
  - reuse a template that can create resources in different contexts,
    - such as a test environment vs a production environment.
    - In template, add an EnvironmentType input parameter, which accepts either “prod” or “test” as inputs.
      - For the production environment,
        - include EC2 instances with certain capabilities;
      - for the test environment,
        - use reduced capabilities to save money.
    - define which resources are created, and how they're configured for each environment type.

- Conditions are evaluated based on input parameter values specified when create or update a stack.
  - if values or tags have been assigned,
  - the template will do something different based on the assigned value.

- Within each condition, you can reference another condition, a parameter value, or a mapping.

- After define all conditions, associate them with `resources` and `resource properties` in the `Resources` and `Outputs` sections of a template.


```json
"Parameters" : {
    "InstanceTypeParameter" : {
        "Type" : "String",
        "Default" : "t2.micro",
        "AllowedValues" : [ "t2.micro", "m1.small", "m1.large"],
        "Description" : "Enter t2.micro, m1.small, m1.large. Default is t2.micro"
    },
    // the EnvType parameter specifies whether to create a Dev / QA / Prod environment.
    "EnvType" : {
        "Type" : "String",
        "Default" : "Dev",
        "AllowedValues" : [ "Dev", "QA", "Prod"],
        "Description" : "Enter the environment"
    },
},
"Resources" : {
    "Instances" : {
        "Type" : "AWS::EC2::Instance",
        "Properties" : {
            "InstanceType" : { "Ref" : "InstanceTypeParameter" },
            "ImageId" : "ami-20b65349",
        }
    }
},

// use “Condition” to evaluate this, and specify appropriate resources for each environment.
"Conditions" : {
    "CreateProdResources" : { "Fn::Equals" : [{ "Ref" : "EnvType"}, "Prod" ]}
}
```

- Example
  - the EnvType parameter specifies whether to create a Dev environment, a QA—environment, or a Prod environment.
  - Depending on the environment, to specify different configurations, such as which database it points to.
  - use “Condition” to evaluate this, and specify appropriate resources for each environment.


![Screen Shot 2020-06-26 at 15.06.07](https://i.imgur.com/YdjU8aF.png)

- Build environment with conditions:  
  - when the target environment is development DEV.
    - only one set of resources in one Availability Zone is launched
  - When this template is used in production PROD
    - the solution launches two sets of resources in two different AZ.
  - get a redundant environment from the same template without single change

- production environment and DEV environment
  - must have the same stack
  - in order to ensure that application works the way that it was designed.

- DEV environment and QA environment
  - must have the same stack of applications and the same configuration.
  - You might have several QA environments for functional testing, user acceptance testing, load testing, and so on.
  - The process of creating those environments manually can be -prone.
  - use a Conditions statement in the template to solve this problem.



---


## Output     ￼


![Screen Shot 2020-06-26 at 15.17.56](https://i.imgur.com/pI7Bf8n.png)

- Outputs are values that are returned whenever view the properties of the stack.

- For example,
  - if something executes properly,
  - it is helpful to provide an indication that the execution completed and was successful.

- Outputs can specify the string output of any logical identifier that is available in the template.

- It's a convenient way to capture important information about your resources or input parameters


---

# Resources and Features outside AWS CloudFormation

For Resources and Features Not Directly Supported by AWS CloudFormation

- AWS CloudFormation is extensible with custom resources
  - so can use part of your own logic to create stacks.
  - With custom resources, write custom provisioning logic in templates.
  - CloudFormation runs the custom logic when you create, update, or delete stacks.

- For example
- to include resources that are not available as AWS CloudFormation resource types.
  - include those resources by using custom resources,
  - which means that you can still manage all your related resources in a single stack.
  - Use the `AWS::CloudFormation::CustomResource` or `Custom::String` resource type to define custom resources in your templates.
  - Custom resources require one property: <font color=red> the service token </font>
    - specifies where AWS CloudFormationsends requests to, such as an Amazon SNS topic.
  - Examples include
    - provisioning a third-party application subscription and passing the authentication key back to the EC2 instance that needs it.
    - use an AWS Lambda function to peer a new VPC with another VPC



example:


![Screen Shot 2020-06-26 at 15.24.01](https://i.imgur.com/RSa5TWf.png)


```json
cfnVerifier
    Type: AWS::CloudFormation::CustomResource
    Properties:
        ServiceToken
          Fn::Join [ "", [ "arn:aws:lambda:", !Ref: "AWS::Region", ":", !Ref: "AWS::AccountId", ":function:cfnVerifierLambda"]]
```

- user creates an AWS CloudFormation template by using a stack that has a `custom resource operation`.
  - This custom resource operation was defined by using `AWS::CloudFormation::CustomResource` or `Custom::CustomResource`.
- The template includes a <font color=red> ServiceToken </font>
  - from the third-party resource provider
  - used for authentication.
- The template also includes any provider-defined parameters required for the custom resource.  

- AWS CloudFormation
  - communicates with the custom resource provider by using Amazon Simple Notification Service SNS—message that includes
    - a Create, Update, or Delete request.
    - any input data that is stored in the stack template
    - and an Amazon S3 URL for the response.
- The custom resource provider
  - processes the message
  - returns a Success or Fail response to AWS CloudFormation.
  - can also return 
    - the names and values of resource attributes if the request succeeded (output data) 
    - or send a string that provides details when the request fails.

- AWS CloudFormation
  - sets the stack status according to the response that is received,
  - provides the values of any custom resource output data.

- can use an AWS Lambda function to act as a custom resource.
  - To implement this, can replace the ServiceToken for custom resource with the Amazon Resource Name, ARN, of your Lambda custom resource.
  - do not need to create an Amazon SNS topic for a custom resource when you use AWS Lambda because AWS CloudFormation is Lambda-aware.

- As in the previous scenarios, your code is responsible for doing any required processing.

- It uses the pre-signed URL (sent by AWS CloudFormation) to signal to the service that the creation of the custom resource either succeeded or failed.














.
