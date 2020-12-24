





# AWS



---


## AWS Organizations:
- restrict what services and actions are allowed in accounts.
- consolidate multiple AWS accounts into an organization to `create and centrally manage`.
- Each paying account is an independent entity and is not able to access resources of other accounts in the Organization.
- The billing is performed centrally on the root account in the AWS Organization.


---


## AWS Systems Manager
- gives visibility and control of `the infrastructure on AWS`.
- provides a unified user interface, view `operational data from multiple AWS services`
- to `automate operational tasks` across the AWS resources.
￼
![Pasted Graphic 18](https://i.imgur.com/Sov0CJQ.jpg)


---


## AWS Secrets Manager
- `protects the secrets use` for access to applications and services and IT resources.
- easily rotate, manage, and retrieve `database credentials, API keys, and other secrets throughout their lifecycle`.



---


## TAM: Technical account manager:
- Proactive guidance
- Only for the `enterprise support plan`.
- A dedicated voice in AWS to serve as the advocate.
- Proactive guidance and insight into ways to optimize AWS through business and performance reviews.
- Orchestration and access to the full breadth and depth of technical expertise across the full range of AWS.
- And access to resources and best practice recommendations


Infrastructure Event Management
- A common understanding of event objectives and use cases through pre-event planning and preparation.
- Resource recommendations and deployment guidance based on anticipated capacity needs.
- Dedicated attention of the AWS Support team during the event.
- And the ability to immediately scale down resources to normal operating levels post-event


Service Health Dashboard:
- general status of AWS services,  
- shows the current status of services across regions.
- but not provide proactive notifications of scheduled activities or guidance of any kind.

---


## AWS Personal Health Dashboard
- provides alerts and remediation guidance when AWS is experiencing events that may impact you.
- personalized view into the performance and availability of the AWS services underlying the AWS resources.
	⁃	displays relevant and timely information to help manage events in progress, and provides proactive notifications to help plan for scheduled activities
	⁃	forward-looking notifications. You can set alerts across multiple channels, including email and mobile notifications.
	⁃	alerts are triggered by changes in the health of AWS resources, giving you event visibility, and guidance to help quickly diagnose and resolve issues.
- Having an AWS account grants you access to the Personal Health Dashboard to receive alerts and remediation guidance regarding events affecting the services underlying the resources.
- Business or Enterprise support plan, also get AWS Health API for integrating health data and notifications with the existing in-house and third-party IT management tools.


---


## AWS Security Hub
- consolidates view of the security and compliance status in the cloud.
- Unified security and compliance center


---


## AWS support concierge 门房:
- A primary contact to help manage AWS resources.
- account assistance:
- non-tech billing and account level inquiries.
	⁃	Personalized handling of billing inquiries, tax questions, service limits, and bulk reserve instance purchases.
	⁃	answering billing and account questions
	⁃	direct access to an agent to help optimize costs to identify underused resources
- Only for the enterprise support plan.



Guard Duty  identify malicious/unauthorized activity in AWS account and workloads
- a threat detection service
- monitors environment for malicious or unauthorized activiaty.
	⁃	such as unusual API calls or potentially unauthorized deployments that indicate a possible account compromise.
	⁃	detects potentially compromised instances or reconnaissance by attackers.
	⁃	continuously monitor and protect AWS accounts and workloads.
	⁃	can identify malicious or unauthorized activities in AWS accounts
- Use threat intelligence feeds to detect threats to the environment.
- Designed to actively protect the environment from threats.

---


## AWS Config:    audit, and evaluate the configurations
- a service assess,
- audit, and evaluate the configurations of AWS resources.
	⁃	audit and monitor changes to AWS resources.
	⁃	track resource inventory and changes.
	⁃	continuously monitors and records AWS resource configurations and allows to automate the evaluation of recorded configurations against desired configurations.
- review changes in configurations and relationships between AWS resources, dive into detailed resource configuration histories, and determine the overall compliance against the configurations specified in the internal guidelines.
- simplify compliance auditing, security analysis, change management, and operational troubleshooting.
- fully-managed service
- provides you with an AWS resource inventory, configuration history, and configuration change notifications to enable security and regulatory compliance.
- discover existing and deleted AWS resources, determine the overall compliance against rules, and dive into configuration details of a resource at any point in time.
- enables compliance auditing, security analysis, resource change tracking, and troubleshooting.


---


## CloudTrail  got all the log, auditing
- enables governance, compliance, operational auditing, and risk auditing of AWS account.
	⁃	enabled automatically when create AWS account
	⁃	delivers log files within 15min of account activity.
	⁃	can create a trail applies to one Region or to all Regions
	⁃	By default, CloudTrail event log files are encrypted using S3 server-side encryption.
- tracks user activity and API usage
	⁃	log, continuously monitor, and retain account activity related to actions across the AWS infrastructure.
	⁃	provides event history of the AWS account activity
	⁃	including actions taken through the AWS Management Console, AWS SDKs, command line tools, and other AWS services.
- This event history simplifies security analysis, resource change tracking, and troubleshooting.
- to monitor all API activity for all regions in the AWS environment:
	⁃	enable CloudTrail for all regions in the environment and CloudTrail can deliver all log files from all regions to one S3 bucket.
	⁃	Logs API calls made via:
	⁃	AWS Management Console.
	⁃	AWS SDKs.
	⁃	Command line tools.
	⁃	Higher-level AWS services (such as CloudFormation).
- provides a complete audit trail of all AWS services within an account
	⁃	A trail enables CloudTrail to deliver log files to an Amazon S3 bucket.
	⁃	By default, when create a trail in the console, the trail applies to all AWS Regions.
	⁃	The trail logs events from all Regions in the AWS partition and delivers the log files to the Amazon S3 bucket that you specify.

---


## CloudWatch Logs: collect log, create alarm, does not debug or log errors.
- monitor, collect, store, and access logs from resources, applications, and services in near real-time.
	⁃	Compute ( EC2 insatnces, autoscaling groups, elastic load balancers, route53 health checks)
	⁃	Storage, CloudTrail, Lambda functions, and Amazon SQS queues
	⁃	Content Delivery (EBS Volumes, Storage Gateways, CloudFront)
	⁃	CPU, Disk, Network utilization, and others.
	⁃	aggregate 聚集 logs from the EC2 instance.
	⁃	centrally upload logs from all the servers.
	⁃	allow real-time monitoring as well as adjustable retention.
	⁃	collect and track metrics, collect and monitor log files, and set alarms.
	⁃	Basic monitoring collects metrics every 5min
	⁃	detailed monitoring collects metrics every 1min
- providing a unified view of AWS resources, applications and services that run on AWS, and on-premises servers.
- actionable insights to monitor applications, respond to system-wide performance changes, and optimize resource utilization to get a view of the overall operational health.


---


## AWS Trusted Advisor:   what should use
- optimize performance and security.
- provides real-time guidance to help you provision 提供 the resources following AWS best practices and staying within limits.
- auto service, during implement ells right and problems.
- Business and enterprise can use all checks.
- provides valuable guidance for architecting the AWS environment and workloads, but doesn't include AWS service health information.
- offers recommendations for cost optimization, performance, security, fault tolerance and service limits
- Offers a Service Limits check (in the Performance category) that displays the usage and limits for some aspects of some services.
- ￼
￼
