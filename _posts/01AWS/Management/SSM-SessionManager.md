



[toc]


---


# Session Manager:

- makes it easy to comply with corporate policies that require controlled access to instances, strict security practices, and fully auditable logs with instance access details, while still providing end users with simple one-click cross-platform access to the managed instances.
  - automating common administrative tasks across groups of instances 
    - such as registry edits, user management, and software and patch installations.
  - improve security and audit posture, reduce operational overhead by centralizing access control on instances, and reduce inbound instance access.
  - monitor and track instance access and activity, close down inbound ports on instances, or enable connections to instances that do not have a public IP address.
  - grant and revoke access from a single location, and who want to provide one solution to users for Linux, macOS, and Windows Server instances.
  - connect to an instance with just one click from the browser or AWS CLI without having to provide SSH keys.

- integration with AWS IAM
  - can apply granular permissions to control the actions users can perform on instances.
  - Centralized access control to instances using IAM policies
  - single place to grant and revoke access to instances. 
  - Using only AWS IAM policies, control which individual users or groups in organization can use Session Manager and which instances they can access.
  - You can also provide temporary access to your instances. 
    - Example
    - give an on-call user/group of user access to production servers only for the duration of their rotation.


- No open inbound ports and no need to manage bastion hosts or SSH keys
  - provides safe, secure remote management of the instances at scale 
  - without logging into the servers, 
    - replacing the need for bastion hosts, SSH, or remote PowerShell.
    - Leaving inbound SSH ports and remote PowerShell ports open on the instances greatly increases the risk of entities running unauthorized or malicious commands on the instances. 
    - without the need to open inbound ports, manage SSH keys and certificates, bastion hosts, and jump boxes.
  - manage EC2/on-premises instances, and VMs through an `interactive one-click browser-based shell` or through the `AWS CLI`. 


- Port forwarding
  - Redirect any port inside the remote instance to a local port on a client. 
  - connect to the local port and access the server application that is running inside the instance.
  - Tunneling
    - In a session, use a Session-type SSM document to tunnel traffic, such as http or a custom protocol, between a local port on a client machine and a remote port on an instance.
  
- AWS PrivateLink support for instances without public IP addresses
  - can set up VPC Endpoints for Systems Manager using AWS PrivateLink to further secure the sessions. 
  - AWS PrivateLink limits all network traffic between managed instances, Systems Manager, and Amazon EC2 to the Amazon network. 
  


- One-click access to instances from the console and CLI
  - start a session with a single click. 
  - Using the AWS CLI, can also start a session that runs a single command or a sequence of commands. 
  - as permissions to instances are provided by IAM policies not SSH keys or other mechanisms, the connection time is greatly reduced.
  - Interactive commands
    - Create a Session-type SSM document that uses a session to interactively run a single command, giving you a way to manage what users can do on an instance.


- Cross-platform support for Windows, Linux, and macOS
  - support for Windows, Linux, and macOS from a single tool.
  - establish secure connections to EC2/on-premises instances, and VMs. 
    - support for on-premises servers is provided for the advanced-instances tier only.  
  - Example
    - SSH client for Linux and macOS instances 
    - RDP connection for Windows Server instances.

- Logging and auditing session activity
  - All actions taken with Systems Manager are recorded by AWS CloudTrail
    - audit changes throughout the environment.
    - receive notifications when a user in the organization starts or ends session activity.
  - provides secure and auditable instance management 
    - Note:
    - <font color=blue> Logging is not available for Session Manager sessions that connect through port forwarding or SSH </font>
    - because SSH encrypts all session data, and Session Manager only serves as a tunnel for SSH connections.
  - Logging and auditing capabilities are provided through integration with the following AWS services:
    - AWS CloudTrail
      - captures information about Session Manager API calls made in the AWS account and writes it to log files that are stored in an S3 bucket you specify. 
      - One bucket is used for all CloudTrail logs for the account.  

    - Amazon S3
      - choose to store session log data in an S3 bucket of the choice for debugging and troubleshooting purposes. 
      - Log data can be sent to the S3 bucket with or without encryption using the AWS Key Management Service (AWS KMS) key.  

    - Amazon CloudWatch Logs
      - monitor, store, and access log files from various AWS services. 
      - You can send session log data to a CloudWatch Logs log group for debugging and troubleshooting purposes. 
      - Log data can be sent to the log group with or without AWS KMS encryption using the AWS KMS key. For more information, see Logging session data using Amazon CloudWatch Logs (console).

    - Amazon EventBridge and Amazon Simple Notification Service 
      - EventBridge lets you set up rules to detect when changes happen to AWS resources that you specify. You can create a rule to detect when a user in the organization starts or stops a session, and then receive a notification through Amazon SNS (for example, a text or email message) about the event. 
      - You can also configure a CloudWatch event to initiate other responses. 

    - Console, CLI, and SDK access to Session Manager capabilities
      - The AWS Systems Manager console: 
        - includes access to all the Session Manager capabilities for both administrators and end users. You can perform any task that's related to your sessions by using the Systems Manager console.
      - The Amazon EC2 console 
        - provides the ability for end users to connect to the EC2 instances for which they have been granted session permissions.
      - The AWS CLI 
        - includes access to Session Manager capabilities for end users. 
        - can start a session, view a list of sessions, and permanently end a session by using the AWS CLI.
        - To use the AWS CLI to run session commands, you must be using version 1.16.12 of the CLI (or later), and you must have installed the Session Manager plugin on your local machine.  
        - Configurable shell profiles
        - Session Manager provides you with options to configure preferences within sessions. These customizable profiles enable you to define preferences such as shell preferences, environment variables, working directories, and running multiple commands when a session is started.
      - The Session Manager SDK 
        - consists of libraries and sample code that enables application developers to build front-end applications, such as custom shells or self-service portals for internal users that natively use Session Manager to connect to instances. 
        - Developers and partners can integrate Session Manager into their client-side tooling or Automation workflows using the Session Manager APIs. 
        - You can even build custom solutions.

    - Customer key data encryption support
      - configure Session Manager to <font color=blue> encrypt the session data logs send to S3 bucket or stream to a CloudWatch Logs log group </font>
      - configure Session Manager to further encrypt the data transmitted between client machines and your instances during your sessions.
































---

## Monitoring


### Logging AWS Systems Manager API calls with AWS CloudTrail.











.