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
