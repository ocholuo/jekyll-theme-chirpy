---
title: AWS - CodeDevelop - CloudFormation
date: 2020-07-18 11:11:11 -0400
categories: [00AWS, CodeDevelop]
tags: [AWS]
toc: true
image:
---

[toc]

---

#  AWS CloudFormation   

> Infrastructure as code solution.

- allows you to manage, configure and provision the AWS infrastructure as code.
  - repeatedly and predictably model and provision resources
  - Supports almost all the AWS services and programable
    - can be used to provision a broad range of AWS resources.
      - Elastic Beanstalk: more focussed on deploying web applications on EC2 (PaaS).
      - CloudFormation: can deploy Elastic Beanstalk-hosted applications however the reverse is not possible.
    - Logical IDs: reference resources within the template.
    - Physical IDs: identify resources outside of AWS CloudFormation templates, but only after the resources have been created.

- resources are defined using a CloudFormation template

- CloudFormation interprets the template and makes the appropriate API calls to create the resources you have defined.

- Supports YAML and JSON


---

## benefits

- infrastructure is provisioned consistently
  - fewer mistakes
  - less time and effort than configurating things manually
- version control and peer review the templates
- free service (resources created would be charged)
- can be used to manage udpates & dependencies
- can be used to rollback and delete the entire stack as well




---


## templates, stacks and change sets:

![CloudFormation](https://i.imgur.com/zu1yJYA.png)

![Pasted Graphic](https://i.imgur.com/71RbCIM.jpg)

---


### AWS CloudFormation template

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




templates can be created by
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


### AWS CloudFormation engine:
- Aws service component
- Interprets AWS cloudFormation template into stacks of AWS resources.

---


### AWS CloudFormation group:

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


### AWS CloudFormation Stack

Stack
- A collection of resources created by AWS cloudFormation
- Deployed resources based on templates.
- Create, update and delete stacks using templates.
- Deployed through the Management Console, CLI or APIs.
- Tracked and reviewable in the AWS management console

Cross stack references
- share outputs from one stack with another stack.
  - share things like IAM—roles, VPC—information, and security groups.
- Before, use AWS CloudFormation custom resources to accomplish these tasks.
  - Now, export values from one stack and import them to another stack by using the new ImportValueintrinsic function.
- useful for customers who
  - separate their AWS infrastructure into logical components that grouped by stack
    - such as a network stack, an application stack, etc.
  - need a way to loosely couple stacks together as an alternative to nested stacks



- Stacks can't create logical resources
  - a stack listens to the direction of a template containing logical resources.
    - Manages physical resources based on a logical resource template
  - Stacks making resources through the direction of a template.
    - template describes a stack,
      - a collection of AWS resources want to deploy together as a group.
  - Actions to resources can be tracked in CloudFormation's stack details.

- Stacks control resources
  - if a stack is removed, the resource also be deleted, so are the resources that it created.
  - Stacks could disrupt a resource when performing updates to the resource.
  - if the stack is deleted


Stack creation errors:
- Automatic rollback on error is enabled by default.
- You will be charged for resources provisioned even if there is an error.


AWS CloudFormation provides 2 methods for <font color=red> updating stacks </font>
- <font color=red> direct update a stack </font>
  - submit changes and AWS CloudFormation immediately deploys them.
  - Use direct updates to quickly deploy the updates.
- creating and executing <font color=red> change sets </font>
  - you can preview the changes AWS CloudFormation will make to your stack
  - and then decide whether to apply those changes.

---

### StackSets.
- AWS CloudFormation StackSets extends the functionality of stacks by enabling you to create, update, or delete stacks across multiple accounts and regions with a single operation.
- Using an administrator account
  - you define and manage an AWS CloudFormation template
  - use the template as the basis for provisioning stacks into selected target accounts across specified regions.
- An administrator account
  - the AWS account in which you create stack sets.
  - A stack set is managed by signing in to the AWS administrator account in which it was created.
- A target account
  - the account into which you create, update, or delete one or more stacks in your stack set.
  - Before use a stack set to create stacks in a target account, must set up a trust relationship between the administrator and target accounts.

Best Practices.
- AWS provides Python “helper scripts” which can help you install software and start services on your EC2 instances.
- Use CloudFormation to make changes to your landscape rather than going directly into the resources.
- Make use of Change Sets to identify potential trouble spots in your updates.
- Use Stack Policies to explicitly protect sensitive portions of your stack.
- Use a version control system such as CodeCommit or GitHub to track changes to templates.

Charges:
- no additional charge for AWS CloudFormation.
- pay for AWS resources (such as EC2 instances, ELB load balancers, etc.) created using AWS CloudFormation in the same manner as if you created them manually.
- only pay for what you use, as you use it;
- there are no minimum fees and no required upfront commitments.


---

## CloudFormationTemplate.yml

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

## setup

1. cloudformation
2. create stack
   1. select template
   2. stack name
   3. keypaire
   4. rollback on failure
3. delete stack
