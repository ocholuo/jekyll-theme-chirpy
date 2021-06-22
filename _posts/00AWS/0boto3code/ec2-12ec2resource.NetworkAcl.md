---
title: AWS - boto3 - boto3.resource('ec2').NetworkACL('id')
date: 2020-07-18 11:11:11 -0400
categories: [00AWS, boto3]
tags: [AWS]
toc: true
image:
---


[toc]


---

# EC2 - client

Table of Contents

- EC2
  1. Client
  1. Paginators
  1. Waiters
  1. Service Resource
  1. ClassicAddress
  1. DhcpOptions
  1. Image
  1. Instance
  1. InternetGateway
  1. KeyPair
  1. KeyPairInfo
  1. NetworkAcl
  1. NetworkInterface
  1. NetworkInterfaceAssociation
  1. PlacementGroup
  1. Route 
  1. RouteTable
  1. RouteTableAssociation
  1. SecurityGroup
  1. Snapshot
  1. Subnet
  1. Tag
  1. Volume
  1. Vpc
  1. VpcPeeringConnection
  1. VpcAddress

---


# EC2 - NetworkAcl

_class_ EC2.NetworkAcl(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) NetworkAcl:

```py
import boto3

ec2resource = boto3.resource('ec2')
ec2network_acl = ec2resource.NetworkAcl('id')
```

available identifiers:
- id `_(string)_ The NetworkAcl's id identifier. This **must** be set.`

available attributes:
- associations
- entries
- is_default
- network_acl_id
- owner_id
- tags
- vpc_id

available references:
- vpc

available actions:
- create_entry()
- create_tags()
- delete()
- delete_entry()
- get_available_subresources()
- load()
- reload()
- replace_association()
- replace_entry()



## Actions


### create_entry(_**kwargs_) and delete_entry(_**kwargs_)

1. Creates an entry (a rule) in a network ACL with the specified rule number.
   - Each network ACL has a set of numbered ingress rules and a separate set of numbered egress rules. When determining whether a packet should be allowed in or out of a subnet associated with the ACL, we process the entries in the ACL according to the rule numbers, in ascending order.
   - Each network ACL has a set of ingress rules and a separate set of egress rules.

   > recommend that you leave room between the rule numbers (for example, 100, 110, 120, ...), and not number them one right after the other (for example, 101, 102, 103, ...).
   > This makes it easier to add a rule between existing ones without having to renumber the rules.

2. Deletes the specified ingress or egress entry (rule) from the specified network ACL.


After you add an entry, can't modify it;
- either replace it, or create an entry and delete the old one.


**Request Syntax**

```py

import boto3

ec2network_acl = boto3.resource('ec2').NetworkAcl('id')


response = ec2network_acl.create_entry(
    CidrBlock='string',
    # (_string_) -- The IPv4 network range to allow or deny, in CIDR notation (for example 172.16.0.0/24 ).
    # modify the specified CIDR block to its canonical form;
    # for example, if you specify 100.68.0.18/18 , we modify it to 100.68.0.0/18 .
    DryRun=True|False,
    Egress=True|False,
    # (_boolean_) -- **[REQUIRED]** Indicates whether this is an egress rule (rule is applied to traffic leaving the subnet).
    IcmpTypeCode={
        # (_dict_) -- ICMP protocol: The ICMP or ICMPv6 type and code.
        # Required if specifying protocol 1 (ICMP) or protocol 58 (ICMPv6) with an IPv6 CIDR block.
        'Code': 123,
        # _(integer) -- The ICMP code. A value of -1 means all codes for the specified ICMP type.
        'Type': 123
        # _(integer) -- The ICMP type. A value of -1 means all types.
    },

    Ipv6CidrBlock='string',
    # (_string_) -- The IPv6 network range to allow or deny, in CIDR notation (for example 2001:db8:1234:1a00::/64 ).
    PortRange={
        # (_dict_) -- TCP or UDP protocols: The range of ports the rule applies to. Required if specifying protocol 6 (TCP) or 17 (UDP).
        'From': 123,
        # _(integer) -- The first port in the range.
        'To': 123
        # _(integer) -- The last port in the range.
    },
    Protocol='string',
    # (_string_) -- **[REQUIRED]** The protocol number.
    # A value of "-1" means all protocols.
    # If you specify "-1" or a protocol number other than "6" (TCP), "17" (UDP), or "1" (ICMP), traffic on all ports is allowed, regardless of any ports or ICMP types or codes that you specify.
    # If you specify protocol "58" (ICMPv6) and specify an IPv4 CIDR block, traffic for all ICMP types and codes allowed, regardless of any that you specify. If you specify protocol "58" (ICMPv6) and specify an IPv6 CIDR block, you must specify an ICMP type and code.
    RuleAction='allow'|'deny',
    RuleNumber=123
    # (_integer_) -- **[REQUIRED]** The rule number for the entry (for example, 100). ACL entries are processed in ascending order by rule number.
    # Constraints: Positive integer from 1 to 32766. The range 32767 to 65535 is reserved for internal use.
)





response = ec2network_acl.delete_entry(
    DryRun=True|False,
    Egress=True|False,
    # (_boolean_) -- **[REQUIRED]** Indicates whether the rule is an egress rule.
    RuleNumber=123
    # (_integer_) -- **[REQUIRED]** The rule number of the entry to delete.
)



response = ec2network_acl.replace_entry(
    CidrBlock='string',
    DryRun=True|False,
    Egress=True|False,
    IcmpTypeCode={
        'Code': 123,
        'Type': 123
    },
    Ipv6CidrBlock='string',
    PortRange={
        'From': 123,
        'To': 123
    },
    Protocol='string',
    RuleAction='allow'|'deny',
    RuleNumber=123
)

```


Returns: None



---

### create_tags(_**kwargs_)

- Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources.
- When you specify an existing tag key, the value is overwritten with the new value.
- Each resource can have a maximum of 50 tags.
- Each tag consists of a key and optional value.
- Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
tag = ec2network_acl.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Return:
- Return type: list(ec2.Tag)
- A list of Tag resources


---


### get_available_subresources()

Returns a list of all the available sub-resources for this Resource.

Returns
- Return type: list of str
- A list containing the name of each sub-resource for this resource

---



### load() and reload()

- Calls `EC2.Client.describe_network_acls()` to update the attributes of the NetworkAcl resource.
- Note that the `load()` and `reload()` methods are the same method and can be used interchangeably.


**Request Syntax**

```py

ec2network_acl = boto3.resource('ec2').NetworkAcl('id')

ec2network_acl.load()
ec2network_acl.reload()
```

Returns: None


---

### replace_association(_**kwargs_)

- Changes which network ACL a subnet is associated with.
- By default when you create a subnet, it's automatically associated with the default network ACL.  
- This is an idempotent operation.


**Request Syntax**

```py
response = ec2network_acl.replace_association(
    AssociationId='string',
    # (_string_) -- **[REQUIRED]** The ID of the current association between the original network ACL and the subnet.
    DryRun=True|False,
)
```

Return:
- Return type: dict
- **Response Syntax**

```py
{
    'NewAssociationId': 'string'
    # _(string) -- The ID of the new association.
}
```


replace_entry(_**kwargs_)

Replaces an entry (rule) in a network ACL. For more information, see [Network ACLs](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_ACLs.html) in the _Amazon Virtual Private Cloud User Guide_ .


**Request Syntax**

```py
response = ec2network_acl.replace_entry(
    CidrBlock='string',
    DryRun=True|False,
    Egress=True|False,
    IcmpTypeCode={
        'Code': 123,
        'Type': 123
    },
    Ipv6CidrBlock='string',
    PortRange={
        'From': 123,
        'To': 123
    },
    Protocol='string',
    RuleAction='allow'|'deny',
    RuleNumber=123
)
```



[NetworkInterface](#id1245)
------------------------------------------------------------------------------

_class_ EC2.NetworkInterface(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) NetworkInterface:

import boto3

ec2 = boto3.resource('ec2')
network_interface = ec2.NetworkInterface('id')
```

Parameters

**id** (_string_) -- The NetworkInterface's id identifier. This **must** be set.

available identifiers:

- [id](#EC2.NetworkInterface.id "EC2.NetworkInterface.id")

available attributes:

- [association_attribute](#EC2.NetworkInterface.association_attribute "EC2.NetworkInterface.association_attribute")
- [attachment](#EC2.NetworkInterface.attachment "EC2.NetworkInterface.attachment")
- [availability_zone](#EC2.NetworkInterface.availability_zone "EC2.NetworkInterface.availability_zone")
- [description](#EC2.NetworkInterface.description "EC2.NetworkInterface.description")
- [groups](#EC2.NetworkInterface.groups "EC2.NetworkInterface.groups")
- [interface_type](#EC2.NetworkInterface.interface_type "EC2.NetworkInterface.interface_type")
- [ipv6_addresses](#EC2.NetworkInterface.ipv6_addresses "EC2.NetworkInterface.ipv6_addresses")
- [mac_address](#EC2.NetworkInterface.mac_address "EC2.NetworkInterface.mac_address")
- [network_interface_id](#EC2.NetworkInterface.network_interface_id "EC2.NetworkInterface.network_interface_id")
- [outpost_arn](#EC2.NetworkInterface.outpost_arn "EC2.NetworkInterface.outpost_arn")
- [owner_id](#EC2.NetworkInterface.owner_id "EC2.NetworkInterface.owner_id")
- [private_dns_name](#EC2.NetworkInterface.private_dns_name "EC2.NetworkInterface.private_dns_name")
- [private_ip_address](#EC2.NetworkInterface.private_ip_address "EC2.NetworkInterface.private_ip_address")
- [private_ip_addresses](#EC2.NetworkInterface.private_ip_addresses "EC2.NetworkInterface.private_ip_addresses")
- [requester_id](#EC2.NetworkInterface.requester_id "EC2.NetworkInterface.requester_id")
- [requester_managed](#EC2.NetworkInterface.requester_managed "EC2.NetworkInterface.requester_managed")
- [source_dest_check](#EC2.NetworkInterface.source_dest_check "EC2.NetworkInterface.source_dest_check")
- [status](#EC2.NetworkInterface.status "EC2.NetworkInterface.status")
- [subnet_id](#EC2.NetworkInterface.subnet_id "EC2.NetworkInterface.subnet_id")
- [tag_set](#EC2.NetworkInterface.tag_set "EC2.NetworkInterface.tag_set")
- [vpc_id](#EC2.NetworkInterface.vpc_id "EC2.NetworkInterface.vpc_id")

available references:

- [association](#EC2.NetworkInterface.association "EC2.NetworkInterface.association")
- [subnet](#EC2.NetworkInterface.subnet "EC2.NetworkInterface.subnet")
- [vpc](#EC2.NetworkInterface.vpc "EC2.NetworkInterface.vpc")

available actions:

- [assign_private_ip_addresses()](#EC2.NetworkInterface.assign_private_ip_addresses "EC2.NetworkInterface.assign_private_ip_addresses")
- [attach()](#EC2.NetworkInterface.attach "EC2.NetworkInterface.attach")
- [create_tags()](#EC2.NetworkInterface.create_tags "EC2.NetworkInterface.create_tags")
- [delete()](#EC2.NetworkInterface.delete "EC2.NetworkInterface.delete")
- [describe_attribute()](#EC2.NetworkInterface.describe_attribute "EC2.NetworkInterface.describe_attribute")
- [detach()](#EC2.NetworkInterface.detach "EC2.NetworkInterface.detach")
- [get_available_subresources()](#EC2.NetworkInterface.get_available_subresources "EC2.NetworkInterface.get_available_subresources")
- [load()](#EC2.NetworkInterface.load "EC2.NetworkInterface.load")
- [modify_attribute()](#EC2.NetworkInterface.modify_attribute "EC2.NetworkInterface.modify_attribute")
- [reload()](#EC2.NetworkInterface.reload "EC2.NetworkInterface.reload")
- [reset_attribute()](#EC2.NetworkInterface.reset_attribute "EC2.NetworkInterface.reset_attribute")
- [unassign_private_ip_addresses()](#EC2.NetworkInterface.unassign_private_ip_addresses "EC2.NetworkInterface.unassign_private_ip_addresses")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

id

_(string)_ The NetworkInterface's id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.NetworkInterface.load "EC2.NetworkInterface.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

association_attribute

- _(dict) --_

    The association information for an Elastic IP address (IPv4) associated with the network interface.

    - **AllocationId** _(string) --
        The allocation ID.

    - **AssociationId** _(string) --
        The association ID.

    - **IpOwnerId** _(string) --
        The ID of the Elastic IP address owner.

    - **PublicDnsName** _(string) --
        The public DNS name.

    - **PublicIp** _(string) --
        The address of the Elastic IP address bound to the network interface.

    - **CustomerOwnedIp** _(string) --
        The customer-owned IP address associated with the network interface.

    - **CarrierIp** _(string) --
        The carrier IP address associated with the network interface.

        This option is only available when the network interface is in a subnet which is associated with a Wavelength Zone.


attachment

- _(dict) --_

    The network interface attachment.

    - **AttachTime** _(datetime) --
        The timestamp indicating when the attachment initiated.

    - **AttachmentId** _(string) --
        The ID of the network interface attachment.

    - **DeleteOnTermination** _(boolean) --
        Indicates whether the network interface is deleted when the instance is terminated.

    - **DeviceIndex** _(integer) -- The device index of the network interface attachment on the instance.

    - **NetworkCardIndex** _(integer) -- The index of the network card.

    - **InstanceId** _(string) --
        The ID of the instance.

    - **InstanceOwnerId** _(string) --
        The AWS account ID of the owner of the instance.

    - **Status** _(string) --
        The attachment state.


availability_zone

- _(string) --_

    The Availability Zone.


description

- _(string) --_

    A description.


groups

- _(list) --_

    Any security groups for the network interface.

    - _(dict) --
        Describes a security group.

        - **GroupName** _(string) --     
            The name of the security group.

        - **GroupId** _(string) --     
            The ID of the security group.


interface_type

- _(string) --_

    The type of network interface.


ipv6_addresses

- _(list) --_

    The IPv6 addresses associated with the network interface.

    - _(dict) --
        Describes an IPv6 address associated with a network interface.

        - **Ipv6Address** _(string) --     
            The IPv6 address.


mac_address

- _(string) --_

    The MAC address.


network_interface_id

- _(string) --_

    The ID of the network interface.


outpost_arn

- _(string) --_

    The Amazon Resource Name (ARN) of the Outpost.


owner_id

- _(string) --_

    The AWS account ID of the owner of the network interface.


private_dns_name

- _(string) --_

    The private DNS name.


private_ip_address

- _(string) --_

    The IPv4 address of the network interface within the subnet.


private_ip_addresses

- _(list) --_

    The private IPv4 addresses associated with the network interface.

    - _(dict) --
        Describes the private IPv4 address of a network interface.

        - **Association** _(dict) --     
            The association information for an Elastic IP address (IPv4) associated with the network interface.

            - **AllocationId** _(string) --         
                The allocation ID.

            - **AssociationId** _(string) --         
                The association ID.

            - **IpOwnerId** _(string) --         
                The ID of the Elastic IP address owner.

            - **PublicDnsName** _(string) --         
                The public DNS name.

            - **PublicIp** _(string) --         
                The address of the Elastic IP address bound to the network interface.

            - **CustomerOwnedIp** _(string) --         
                The customer-owned IP address associated with the network interface.

            - **CarrierIp** _(string) --         
                The carrier IP address associated with the network interface.

                This option is only available when the network interface is in a subnet which is associated with a Wavelength Zone.

        - **Primary** _(boolean) --     
            Indicates whether this IPv4 address is the primary private IPv4 address of the network interface.

        - **PrivateDnsName** _(string) --     
            The private DNS name.

        - **PrivateIpAddress** _(string) --     
            The private IPv4 address.


requester_id

- _(string) --_

    The ID of the entity that launched the instance on your behalf (for example, AWS Management Console or Auto Scaling).


requester_managed

- _(boolean) --_

    Indicates whether the network interface is being managed by AWS.


source_dest_check

- _(boolean) --_

    Indicates whether traffic to or from the instance is validated.


status

- _(string) --_

    The status of the network interface.


subnet_id

- _(string) --_

    The ID of the subnet.


tag_set

- _(list) --_

    Any tags assigned to the network interface.

    - _(dict) --
        Describes a tag.

        - **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        - **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


vpc_id

- _(string) --_

    The ID of the VPC.


References

References are related resource instances that have a belongs-to relationship. For more information about references refer to the [_Resources Introduction Guide_](../../guide/resources.html#references-intro).

association

(NetworkInterfaceAssociation) The related association if set, otherwise None.

subnet

(Subnet) The related subnet if set, otherwise None.

vpc

(Vpc) The related vpc if set, otherwise None.

Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

assign_private_ip_addresses(_**kwargs_)

Assigns one or more secondary private IP addresses to the specified network interface.

You can specify one or more specific secondary IP addresses, or you can specify the number of secondary IP addresses to be automatically assigned within the subnet's CIDR block range. The number of secondary IP addresses that you can assign to an instance varies by instance type. For information about instance types, see [Instance Types](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/instance-types.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about Elastic IP addresses, see [Elastic IP Addresses](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/elastic-ip-addresses-eip.html) in the _Amazon Elastic Compute Cloud User Guide_ .

When you move a secondary private IP address to another network interface, any Elastic IP address that is associated with the IP address is also moved.

Remapping an IP address is an asynchronous operation. When you move an IP address from one network interface to another, check network/interfaces/macs/mac/local-ipv4s in the instance metadata to confirm that the remapping is complete.

You must specify either the IP addresses or the IP address count in the request.


**Request Syntax**

```py
response = network_interface.assign_private_ip_addresses(
    AllowReassignment=True|False,
    PrivateIpAddresses=[
        'string',
    ],
    SecondaryPrivateIpAddressCount=123
)
```

Parameters

- **AllowReassignment** (_boolean_) -- Indicates whether to allow an IP address that is already assigned to another network interface or instance to be reassigned to the specified network interface.
- **PrivateIpAddresses** (_list_) --

    One or more IP addresses to be assigned as a secondary private IP address to the network interface. You can't specify this parameter when also specifying a number of secondary IP addresses.

    If you don't specify an IP address, Amazon EC2 automatically selects an IP address within the subnet range.

    - _(string) --_
- **SecondaryPrivateIpAddressCount** (_integer_) -- The number of secondary IP addresses to assign to the network interface. You can't specify this parameter when also specifying private IP addresses.
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'NetworkInterfaceId': 'string',
    'AssignedPrivateIpAddresses': [
        {
            'PrivateIpAddress': 'string'
        },
    ]
}
```


**Response Structure**

- _(dict) --_

    - **NetworkInterfaceId** _(string) --
        The ID of the network interface.

    - **AssignedPrivateIpAddresses** _(list) --
        The private IP addresses assigned to the network interface.

        - _(dict) --     
            Describes the private IP addresses assigned to a network interface.

            - **PrivateIpAddress** _(string) --         
                The private IP address assigned to the network interface.


attach(_**kwargs_)

Attaches a network interface to an instance.


**Request Syntax**

```py
response = network_interface.attach(
    DeviceIndex=123,
    DryRun=True|False,
    InstanceId='string',
    NetworkCardIndex=123
)
```

Parameters

- **DeviceIndex** (_integer_) -- **[REQUIRED]** The index of the device for the network interface attachment.

- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **InstanceId** (_string_) -- **[REQUIRED]** The ID of the instance.

- **NetworkCardIndex** (_integer_) -- The index of the network card. Some instance types support multiple network cards. The primary network interface must be assigned to network card index 0. The default is network card index 0.
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'AttachmentId': 'string',
    'NetworkCardIndex': 123
}
```


**Response Structure**

- _(dict) --_

    Contains the output of AttachNetworkInterface.

    - **AttachmentId** _(string) --
        The ID of the network interface attachment.

    - **NetworkCardIndex** _(integer) -- The index of the network card.


create_tags(_**kwargs_)

Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources. When you specify an existing tag key, the value is overwritten with the new value. Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
tag = network_interface.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Parameters

- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **Tags** (_list_) -- **[REQUIRED]** The tags. The value parameter is required, but if you don't want the tag to have a value, specify the parameter with no value, and we set the value to an empty string.

    - _(dict) --
        Describes a tag.

        - **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        - **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: list(ec2.Tag)
- A list of Tag resources

delete(_**kwargs_)

Deletes the specified network interface. You must detach the network interface before you can delete it.


**Request Syntax**

```py
response = network_interface.delete(
    DryRun=True|False,

)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

describe_attribute(_**kwargs_)

Describes a network interface attribute. You can specify only one attribute at a time.


**Request Syntax**

```py
response = network_interface.describe_attribute(
    Attribute='description'|'groupSet'|'sourceDestCheck'|'attachment',
    DryRun=True|False,

)
```

Parameters

- **Attribute** (_string_) -- The attribute of the network interface. This parameter is required.
- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'Attachment': {
        'AttachTime': datetime(2015, 1, 1),
        'AttachmentId': 'string',
        'DeleteOnTermination': True|False,
        'DeviceIndex': 123,
        'NetworkCardIndex': 123,
        'InstanceId': 'string',
        'InstanceOwnerId': 'string',
        'Status': 'attaching'|'attached'|'detaching'|'detached'
    },
    'Description': {
        'Value': 'string'
    },
    'Groups': [
        {
            'GroupName': 'string',
            'GroupId': 'string'
        },
    ],
    'NetworkInterfaceId': 'string',
    'SourceDestCheck': {
        'Value': True|False
    }
}
```


**Response Structure**

- _(dict) --_

    Contains the output of DescribeNetworkInterfaceAttribute.

    - **Attachment** _(dict) --
        The attachment (if any) of the network interface.

        - **AttachTime** _(datetime) --     
            The timestamp indicating when the attachment initiated.

        - **AttachmentId** _(string) --     
            The ID of the network interface attachment.

        - **DeleteOnTermination** _(boolean) --     
            Indicates whether the network interface is deleted when the instance is terminated.

        - **DeviceIndex** _(integer) --     
            The device index of the network interface attachment on the instance.

        - **NetworkCardIndex** _(integer) --     
            The index of the network card.

        - **InstanceId** _(string) --     
            The ID of the instance.

        - **InstanceOwnerId** _(string) --     
            The AWS account ID of the owner of the instance.

        - **Status** _(string) --     
            The attachment state.

    - **Description** _(dict) --
        The description of the network interface.

        - **Value** _(string) --     
            The attribute value. The value is case-sensitive.

    - **Groups** _(list) --
        The security groups associated with the network interface.

        - _(dict) --     
            Describes a security group.

            - **GroupName** _(string) --         
                The name of the security group.

            - **GroupId** _(string) --         
                The ID of the security group.

    - **NetworkInterfaceId** _(string) --
        The ID of the network interface.

    - **SourceDestCheck** _(dict) --
        Indicates whether source/destination checking is enabled.

        - **Value** _(boolean) --     
            The attribute value. The valid values are true or false .


detach(_**kwargs_)

Detaches a network interface from an instance.


**Request Syntax**

```py
response = network_interface.detach(
    DryRun=True|False,
    Force=True|False
)
```

Parameters

- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **Force** (_boolean_) --

    Specifies whether to force a detachment.

    Note

    - Use the Force parameter only as a last resort to detach a network interface from a failed instance.
    - If you use the Force parameter to detach a network interface, you might not be able to attach a different network interface to the same index on the instance without first stopping and starting the instance.
    - If you force the detachment of a network interface, the [instance metadata](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-metadata.html) might not get updated. This means that the attributes associated with the detached network interface might still be visible. The instance metadata will get updated when you stop and start the instance.

- None

get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_network_interfaces()](#EC2.Client.describe_network_interfaces "EC2.Client.describe_network_interfaces") to update the attributes of the NetworkInterface resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
network_interface.load()
- None

modify_attribute(_**kwargs_)

Modifies the specified network interface attribute. You can specify only one attribute at a time. You can use this action to attach and detach security groups from an existing EC2 instance.


**Request Syntax**

```py
response = network_interface.modify_attribute(
    Attachment={
        'AttachmentId': 'string',
        'DeleteOnTermination': True|False
    },
    Description={
        'Value': 'string'
    },
    DryRun=True|False,
    Groups=[
        'string',
    ],
    SourceDestCheck={
        'Value': True|False
    }
)
```

Parameters

- **Attachment** (_dict_) --

    Information about the interface attachment. If modifying the 'delete on termination' attribute, you must specify the ID of the interface attachment.

    - **AttachmentId** _(string) --
        The ID of the network interface attachment.

    - **DeleteOnTermination** _(boolean) --
        Indicates whether the network interface is deleted when the instance is terminated.

- **Description** (_dict_) --

    A description for the network interface.

    - **Value** _(string) --
        The attribute value. The value is case-sensitive.

- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **Groups** (_list_) --

    Changes the security groups for the network interface. The new set of groups you specify replaces the current set. You must specify at least one group, even if it's just the default security group in the VPC. You must specify the ID of the security group, not the name.

    - _(string) --_
- **SourceDestCheck** (_dict_) --

    Indicates whether source/destination checking is enabled. A value of true means checking is enabled, and false means checking is disabled. This value must be false for a NAT instance to perform NAT. For more information, see [NAT Instances](https://docs.aws.amazon.com/AmazonVPC/latest/UserGuide/VPC_NAT_Instance.html) in the _Amazon Virtual Private Cloud User Guide_ .

    - **Value** _(boolean) --
        The attribute value. The valid values are true or false .

- None

reload()

Calls [EC2.Client.describe_network_interfaces()](#EC2.Client.describe_network_interfaces "EC2.Client.describe_network_interfaces") to update the attributes of the NetworkInterface resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
network_interface.reload()
- None

reset_attribute(_**kwargs_)

Resets a network interface attribute. You can specify only one attribute at a time.


**Request Syntax**

```py
response = network_interface.reset_attribute(
    DryRun=True|False,
    SourceDestCheck='string'
)
```

Parameters

- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **SourceDestCheck** (_string_) -- The source/destination checking attribute. Resets the value to true .
- None

unassign_private_ip_addresses(_**kwargs_)

Unassigns one or more secondary private IP addresses from a network interface.


**Request Syntax**

```py
response = network_interface.unassign_private_ip_addresses(
    PrivateIpAddresses=[
        'string',
    ]
)
```

Parameters

**PrivateIpAddresses** (_list_) --

**[REQUIRED]**

The secondary private IP addresses to unassign from the network interface. You can specify this option multiple times to unassign more than one IP address.

- _(string) --_
- None

[NetworkInterfaceAssociation](#id1246)
----------------------------------------------------------------------------------------------------

_class_ EC2.NetworkInterfaceAssociation(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) NetworkInterfaceAssociation:

import boto3

ec2 = boto3.resource('ec2')
network_interface_association = ec2.NetworkInterfaceAssociation('id')
```

Parameters

**id** (_string_) -- The NetworkInterfaceAssociation's id identifier. This **must** be set.

available identifiers:

- [id](#EC2.NetworkInterfaceAssociation.id "EC2.NetworkInterfaceAssociation.id")

available attributes:

- [carrier_ip](#EC2.NetworkInterfaceAssociation.carrier_ip "EC2.NetworkInterfaceAssociation.carrier_ip")
- [ip_owner_id](#EC2.NetworkInterfaceAssociation.ip_owner_id "EC2.NetworkInterfaceAssociation.ip_owner_id")
- [public_dns_name](#EC2.NetworkInterfaceAssociation.public_dns_name "EC2.NetworkInterfaceAssociation.public_dns_name")
- [public_ip](#EC2.NetworkInterfaceAssociation.public_ip "EC2.NetworkInterfaceAssociation.public_ip")

available references:

- [address](#EC2.NetworkInterfaceAssociation.address "EC2.NetworkInterfaceAssociation.address")

available actions:

- [delete()](#EC2.NetworkInterfaceAssociation.delete "EC2.NetworkInterfaceAssociation.delete")
- [get_available_subresources()](#EC2.NetworkInterfaceAssociation.get_available_subresources "EC2.NetworkInterfaceAssociation.get_available_subresources")
- [load()](#EC2.NetworkInterfaceAssociation.load "EC2.NetworkInterfaceAssociation.load")
- [reload()](#EC2.NetworkInterfaceAssociation.reload "EC2.NetworkInterfaceAssociation.reload")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

id

_(string)_ The NetworkInterfaceAssociation's id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.NetworkInterfaceAssociation.load "EC2.NetworkInterfaceAssociation.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

carrier_ip

- _(string) --_

    The carrier IP address associated with the network interface.


ip_owner_id

- _(string) --_

    The ID of the owner of the Elastic IP address.


public_dns_name

- _(string) --_

    The public DNS name.


public_ip

- _(string) --_

    The public IP address or Elastic IP address bound to the network interface.


References

References are related resource instances that have a belongs-to relationship. For more information about references refer to the [_Resources Introduction Guide_](../../guide/resources.html#references-intro).

address

(VpcAddress) The related address if set, otherwise None.

Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

delete(_**kwargs_)

Disassociates an Elastic IP address from the instance or network interface it's associated with.

An Elastic IP address is for use in either the EC2-Classic platform or in a VPC. For more information, see [Elastic IP Addresses](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/elastic-ip-addresses-eip.html) in the _Amazon Elastic Compute Cloud User Guide_ .

This is an idempotent operation. If you perform the operation more than once, Amazon EC2 doesn't return an error.


**Request Syntax**

```py
response = network_interface_association.delete(
    PublicIp='string',
    DryRun=True|False
)
```

Parameters

- **PublicIp** (_string_) -- [EC2-Classic] The Elastic IP address. Required for EC2-Classic.
- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_network_interfaces()](#EC2.Client.describe_network_interfaces "EC2.Client.describe_network_interfaces") to update the attributes of the NetworkInterfaceAssociation resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
network_interface_association.load()
- None

reload()

Calls [EC2.Client.describe_network_interfaces()](#EC2.Client.describe_network_interfaces "EC2.Client.describe_network_interfaces") to update the attributes of the NetworkInterfaceAssociation resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
network_interface_association.reload()
- None

[PlacementGroup](#id1247)
--------------------------------------------------------------------------

_class_ EC2.PlacementGroup(_name_)

A resource representing an Amazon Elastic Compute Cloud (EC2) PlacementGroup:

import boto3

ec2 = boto3.resource('ec2')
placement_group = ec2.PlacementGroup('name')
```

Parameters

**name** (_string_) -- The PlacementGroup's name identifier. This **must** be set.

available identifiers:

- [name](#EC2.PlacementGroup.name "EC2.PlacementGroup.name")

available attributes:

- [group_id](#EC2.PlacementGroup.group_id "EC2.PlacementGroup.group_id")
- [group_name](#EC2.PlacementGroup.group_name "EC2.PlacementGroup.group_name")
- [partition_count](#EC2.PlacementGroup.partition_count "EC2.PlacementGroup.partition_count")
- [state](#EC2.PlacementGroup.state "EC2.PlacementGroup.state")
- [strategy](#EC2.PlacementGroup.strategy "EC2.PlacementGroup.strategy")
- [tags](#EC2.PlacementGroup.tags "EC2.PlacementGroup.tags")

available actions:

- [delete()](#EC2.PlacementGroup.delete "EC2.PlacementGroup.delete")
- [get_available_subresources()](#EC2.PlacementGroup.get_available_subresources "EC2.PlacementGroup.get_available_subresources")
- [load()](#EC2.PlacementGroup.load "EC2.PlacementGroup.load")
- [reload()](#EC2.PlacementGroup.reload "EC2.PlacementGroup.reload")

available collections:

- [instances](#EC2.PlacementGroup.instances "EC2.PlacementGroup.instances")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

name

_(string)_ The PlacementGroup's name identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.PlacementGroup.load "EC2.PlacementGroup.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

group_id

- _(string) --_

    The ID of the placement group.


group_name

- _(string) --_

    The name of the placement group.


partition_count

- _(integer) --_

    The number of partitions. Valid only if **strategy** is set to partition .


state

- _(string) --_

    The state of the placement group.


strategy

- _(string) --_

    The placement strategy.


tags

- _(list) --_

    Any tags applied to the placement group.

    - _(dict) --
        Describes a tag.

        - **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        - **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

delete(_**kwargs_)

Deletes the specified placement group. You must terminate all instances in the placement group before you can delete the placement group. For more information, see [Placement groups](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/placement-groups.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = placement_group.delete(
    DryRun=True|False,

)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_placement_groups()](#EC2.Client.describe_placement_groups "EC2.Client.describe_placement_groups") to update the attributes of the PlacementGroup resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
placement_group.load()
- None

reload()

Calls [EC2.Client.describe_placement_groups()](#EC2.Client.describe_placement_groups "EC2.Client.describe_placement_groups") to update the attributes of the PlacementGroup resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
placement_group.reload()
- None

Collections

Collections provide an interface to iterate over and manipulate groups of resources. For more information about collections refer to the [_Resources Introduction Guide_](../../guide/collections.html#guide-collections).

instances

A collection of Instance resources.A Instance Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all Instance resources in the collection.


**Request Syntax**

```py
instance_iterator = placement_group.instances.all()
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

create_tags(_**kwargs_)

Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources. When you specify an existing tag key, the value is overwritten with the new value. Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = placement_group.instances.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Parameters

- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **Tags** (_list_) -- **[REQUIRED]** The tags. The value parameter is required, but if you don't want the tag to have a value, specify the parameter with no value, and we set the value to an empty string.

    - _(dict) --
        Describes a tag.

        - **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        - **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

- None

filter(_**kwargs_)

Creates an iterable of all Instance resources in the collection filtered by kwargs passed to method.A Instance collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
instance_iterator = placement_group.instances.filter(
    InstanceIds=[
        'string',
    ],
    DryRun=True|False,
    MaxResults=123,
    NextToken='string'
)
```

Parameters

- **InstanceIds** (_list_) --

    The instance IDs.

    Default: Describes all your instances.

    - _(string) --_
- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **MaxResults** (_integer_) -- The maximum number of results to return in a single call. To retrieve the remaining results, make another call with the returned NextToken value. This value can be between 5 and 1000. You cannot specify this parameter and the instance IDs parameter in the same call.
- **NextToken** (_string_) -- The token to request the next page of results.
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of Instance resources in the collection.


**Request Syntax**

```py
instance_iterator = placement_group.instances.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

monitor(_**kwargs_)

Enables detailed monitoring for a running instance. Otherwise, basic monitoring is enabled. For more information, see [Monitoring your instances and volumes](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-cloudwatch.html) in the _Amazon Elastic Compute Cloud User Guide_ .

To disable detailed monitoring, see .


**Request Syntax**

```py
response = placement_group.instances.monitor(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'InstanceMonitorings': [
        {
            'InstanceId': 'string',
            'Monitoring': {
                'State': 'disabled'|'disabling'|'enabled'|'pending'
            }
        },
    ]
}
```


**Response Structure**

- _(dict) --_
    - **InstanceMonitorings** _(list) --
        The monitoring information.

        - _(dict) --     
            Describes the monitoring of an instance.

            - **InstanceId** _(string) --         
                The ID of the instance.

            - **Monitoring** _(dict) --         
                The monitoring for the instance.

                - **State** _(string) --             
                    Indicates whether detailed monitoring is enabled. Otherwise, basic monitoring is enabled.


page_size(_**kwargs_)

Creates an iterable of all Instance resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
instance_iterator = placement_group.instances.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

reboot(_**kwargs_)

Requests a reboot of the specified instances. This operation is asynchronous; it only queues a request to reboot the specified instances. The operation succeeds if the instances are valid and belong to you. Requests to reboot terminated instances are ignored.

If an instance does not cleanly shut down within a few minutes, Amazon EC2 performs a hard reboot.

For more information about troubleshooting, see [Getting console output and rebooting instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/instance-console.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = placement_group.instances.reboot(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

start(_**kwargs_)

Starts an Amazon EBS-backed instance that you've previously stopped.

Instances that use Amazon EBS volumes as their root devices can be quickly stopped and started. When an instance is stopped, the compute resources are released and you are not billed for instance usage. However, your root partition Amazon EBS volume remains and continues to persist your data, and you are charged for Amazon EBS volume usage. You can restart your instance at any time. Every time you start your Windows instance, Amazon EC2 charges you for a full instance hour. If you stop and restart your Windows instance, a new instance hour begins and Amazon EC2 charges you for another full instance hour even if you are still within the same 60-minute period when it was stopped. Every time you start your Linux instance, Amazon EC2 charges a one-minute minimum for instance usage, and thereafter charges per second for instance usage.

Before stopping an instance, make sure it is in a state from which it can be restarted. Stopping an instance does not preserve data stored in RAM.

Performing this operation on an instance that uses an instance store as its root device returns an error.

For more information, see [Stopping instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Stop_Start.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = placement_group.instances.start(
    AdditionalInfo='string',
    DryRun=True|False
)
```

Parameters

- **AdditionalInfo** (_string_) -- Reserved.
- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'StartingInstances': [
        {
            'CurrentState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            },
            'InstanceId': 'string',
            'PreviousState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            }
        },
    ]
}
```


**Response Structure**

- _(dict) --_

    - **StartingInstances** _(list) --
        Information about the started instances.

        - _(dict) --     
            Describes an instance state change.

            - **CurrentState** _(dict) --         
                The current state of the instance.

                - **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    - 0 : pending
                    - 16 : running
                    - 32 : shutting-down
                    - 48 : terminated
                    - 64 : stopping
                    - 80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                - **Name** _(string) --             
                    The current state of the instance.

            - **InstanceId** _(string) --         
                The ID of the instance.

            - **PreviousState** _(dict) --         
                The previous state of the instance.

                - **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    - 0 : pending
                    - 16 : running
                    - 32 : shutting-down
                    - 48 : terminated
                    - 64 : stopping
                    - 80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                - **Name** _(string) --             
                    The current state of the instance.


stop(_**kwargs_)

Stops an Amazon EBS-backed instance.

You can use the Stop action to hibernate an instance if the instance is [enabled for hibernation](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html#enabling-hibernation) and it meets the [hibernation prerequisites](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html#hibernating-prerequisites) . For more information, see [Hibernate your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html) in the _Amazon Elastic Compute Cloud User Guide_ .

We don't charge usage for a stopped instance, or data transfer fees; however, your root partition Amazon EBS volume remains and continues to persist your data, and you are charged for Amazon EBS volume usage. Every time you start your Windows instance, Amazon EC2 charges you for a full instance hour. If you stop and restart your Windows instance, a new instance hour begins and Amazon EC2 charges you for another full instance hour even if you are still within the same 60-minute period when it was stopped. Every time you start your Linux instance, Amazon EC2 charges a one-minute minimum for instance usage, and thereafter charges per second for instance usage.

You can't stop or hibernate instance store-backed instances. You can't use the Stop action to hibernate Spot Instances, but you can specify that Amazon EC2 should hibernate Spot Instances when they are interrupted. For more information, see [Hibernating interrupted Spot Instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/spot-interruptions.html#hibernate-spot-instances) in the _Amazon Elastic Compute Cloud User Guide_ .

When you stop or hibernate an instance, we shut it down. You can restart your instance at any time. Before stopping or hibernating an instance, make sure it is in a state from which it can be restarted. Stopping an instance does not preserve data stored in RAM, but hibernating an instance does preserve data stored in RAM. If an instance cannot hibernate successfully, a normal shutdown occurs.

Stopping and hibernating an instance is different to rebooting or terminating it. For example, when you stop or hibernate an instance, the root device and any other devices attached to the instance persist. When you terminate an instance, the root device and any other devices attached during the instance launch are automatically deleted. For more information about the differences between rebooting, stopping, hibernating, and terminating instances, see [Instance lifecycle](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-lifecycle.html) in the _Amazon Elastic Compute Cloud User Guide_ .

When you stop an instance, we attempt to shut it down forcibly after a short while. If your instance appears stuck in the stopping state after a period of time, there may be an issue with the underlying host computer. For more information, see [Troubleshooting stopping your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/TroubleshootingInstancesStopping.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = placement_group.instances.stop(
    Hibernate=True|False,
    DryRun=True|False,
    Force=True|False
)
```

Parameters

- **Hibernate** (_boolean_) --

    Hibernates the instance if the instance was enabled for hibernation at launch. If the instance cannot hibernate successfully, a normal shutdown occurs. For more information, see [Hibernate your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html) in the _Amazon Elastic Compute Cloud User Guide_ .

    Default: false

- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **Force** (_boolean_) --

    Forces the instances to stop. The instances do not have an opportunity to flush file system caches or file system metadata. If you use this option, you must perform file system check and repair procedures. This option is not recommended for Windows instances.

    Default: false

Return:
- Return type: dict
- **Response Syntax**

```py
{
    'StoppingInstances': [
        {
            'CurrentState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            },
            'InstanceId': 'string',
            'PreviousState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            }
        },
    ]
}
```


**Response Structure**

- _(dict) --_

    - **StoppingInstances** _(list) --
        Information about the stopped instances.

        - _(dict) --     
            Describes an instance state change.

            - **CurrentState** _(dict) --         
                The current state of the instance.

                - **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    - 0 : pending
                    - 16 : running
                    - 32 : shutting-down
                    - 48 : terminated
                    - 64 : stopping
                    - 80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                - **Name** _(string) --             
                    The current state of the instance.

            - **InstanceId** _(string) --         
                The ID of the instance.

            - **PreviousState** _(dict) --         
                The previous state of the instance.

                - **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    - 0 : pending
                    - 16 : running
                    - 32 : shutting-down
                    - 48 : terminated
                    - 64 : stopping
                    - 80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                - **Name** _(string) --             
                    The current state of the instance.


terminate(_**kwargs_)

Shuts down the specified instances. This operation is idempotent; if you terminate an instance more than once, each call succeeds.

If you specify multiple instances and the request fails (for example, because of a single incorrect instance ID), none of the instances are terminated.

Terminated instances remain visible after termination (for approximately one hour).

By default, Amazon EC2 deletes all EBS volumes that were attached when the instance launched. Volumes attached after instance launch continue running.

You can stop, start, and terminate EBS-backed instances. You can only terminate instance store-backed instances. What happens to an instance differs if you stop it or terminate it. For example, when you stop an instance, the root device and any other devices attached to the instance persist. When you terminate an instance, any attached EBS volumes with the DeleteOnTermination block device mapping parameter set to true are automatically deleted. For more information about the differences between stopping and terminating instances, see [Instance lifecycle](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-lifecycle.html) in the _Amazon Elastic Compute Cloud User Guide_ .

For more information about troubleshooting, see [Troubleshooting terminating your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/TroubleshootingInstancesShuttingDown.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = placement_group.instances.terminate(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'TerminatingInstances': [
        {
            'CurrentState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            },
            'InstanceId': 'string',
            'PreviousState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            }
        },
    ]
}
```


**Response Structure**

- _(dict) --_
    - **TerminatingInstances** _(list) --
        Information about the terminated instances.

        - _(dict) --     
            Describes an instance state change.

            - **CurrentState** _(dict) --         
                The current state of the instance.

                - **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    - 0 : pending
                    - 16 : running
                    - 32 : shutting-down
                    - 48 : terminated
                    - 64 : stopping
                    - 80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                - **Name** _(string) --             
                    The current state of the instance.

            - **InstanceId** _(string) --         
                The ID of the instance.

            - **PreviousState** _(dict) --         
                The previous state of the instance.

                - **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    - 0 : pending
                    - 16 : running
                    - 32 : shutting-down
                    - 48 : terminated
                    - 64 : stopping
                    - 80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                - **Name** _(string) --             
                    The current state of the instance.


unmonitor(_**kwargs_)

Disables detailed monitoring for a running instance. For more information, see [Monitoring your instances and volumes](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-cloudwatch.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = placement_group.instances.unmonitor(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'InstanceMonitorings': [
        {
            'InstanceId': 'string',
            'Monitoring': {
                'State': 'disabled'|'disabling'|'enabled'|'pending'
            }
        },
    ]
}
```


**Response Structure**

- _(dict) --_
    - **InstanceMonitorings** _(list) --
        The monitoring information.

        - _(dict) --     
            Describes the monitoring of an instance.

            - **InstanceId** _(string) --         
                The ID of the instance.

            - **Monitoring** _(dict) --         
                The monitoring for the instance.

                - **State** _(string) --             
                    Indicates whether detailed monitoring is enabled. Otherwise, basic monitoring is enabled.


[Route](#id1248)
--------------------------------------------------------

_class_ EC2.Route(_route_table_id_, _destination_cidr_block_)

A resource representing an Amazon Elastic Compute Cloud (EC2) Route:

import boto3

ec2 = boto3.resource('ec2')
route = ec2.Route('route_table_id','destination_cidr_block')
```

Parameters

- **route_table_id** (_string_) -- The Route's route_table_id identifier. This **must** be set.
- **destination_cidr_block** (_string_) -- The Route's destination_cidr_block identifier. This **must** be set.

available identifiers:

- [route_table_id](#EC2.Route.route_table_id "EC2.Route.route_table_id")
- [destination_cidr_block](#EC2.Route.destination_cidr_block "EC2.Route.destination_cidr_block")

available attributes:

- [carrier_gateway_id](#EC2.Route.carrier_gateway_id "EC2.Route.carrier_gateway_id")
- [destination_ipv6_cidr_block](#EC2.Route.destination_ipv6_cidr_block "EC2.Route.destination_ipv6_cidr_block")
- [destination_prefix_list_id](#EC2.Route.destination_prefix_list_id "EC2.Route.destination_prefix_list_id")
- [egress_only_internet_gateway_id](#EC2.Route.egress_only_internet_gateway_id "EC2.Route.egress_only_internet_gateway_id")
- [gateway_id](#EC2.Route.gateway_id "EC2.Route.gateway_id")
- [instance_id](#EC2.Route.instance_id "EC2.Route.instance_id")
- [instance_owner_id](#EC2.Route.instance_owner_id "EC2.Route.instance_owner_id")
- [local_gateway_id](#EC2.Route.local_gateway_id "EC2.Route.local_gateway_id")
- [nat_gateway_id](#EC2.Route.nat_gateway_id "EC2.Route.nat_gateway_id")
- [network_interface_id](#EC2.Route.network_interface_id "EC2.Route.network_interface_id")
- [origin](#EC2.Route.origin "EC2.Route.origin")
- [state](#EC2.Route.state "EC2.Route.state")
- [transit_gateway_id](#EC2.Route.transit_gateway_id "EC2.Route.transit_gateway_id")
- [vpc_peering_connection_id](#EC2.Route.vpc_peering_connection_id "EC2.Route.vpc_peering_connection_id")

available actions:

- [delete()](#EC2.Route.delete "EC2.Route.delete")
- [get_available_subresources()](#EC2.Route.get_available_subresources "EC2.Route.get_available_subresources")
- [replace()](#EC2.Route.replace "EC2.Route.replace")

available sub-resources:

- [RouteTable()](#EC2.Route.RouteTable "EC2.Route.RouteTable")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

route_table_id

_(string)_ The Route's route_table_id identifier. This **must** be set.

destination_cidr_block

_(string)_ The Route's destination_cidr_block identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the load() method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

carrier_gateway_id

- _(string) --_

    The ID of the carrier gateway.


destination_ipv6_cidr_block

- _(string) --_

    The IPv6 CIDR block used for the destination match.


destination_prefix_list_id

- _(string) --_

    The prefix of the AWS service.


egress_only_internet_gateway_id

- _(string) --_

    The ID of the egress-only internet gateway.


gateway_id

- _(string) --_

    The ID of a gateway attached to your VPC.


instance_id

- _(string) --_

    The ID of a NAT instance in your VPC.


instance_owner_id

- _(string) --_

    The AWS account ID of the owner of the instance.


local_gateway_id

- _(string) --_

    The ID of the local gateway.


nat_gateway_id

- _(string) --_

    The ID of a NAT gateway.


network_interface_id

- _(string) --_

    The ID of the network interface.


origin

- _(string) --_

    Describes how the route was created.

    - CreateRouteTable - The route was automatically created when the route table was created.
    - CreateRoute - The route was manually added to the route table.
    - EnableVgwRoutePropagation - The route was propagated by route propagation.

state

- _(string) --_

    The state of the route. The blackhole state indicates that the route's target isn't available (for example, the specified gateway isn't attached to the VPC, or the specified NAT instance has been terminated).


transit_gateway_id

- _(string) --_

    The ID of a transit gateway.


vpc_peering_connection_id

- _(string) --_

    The ID of a VPC peering connection.


Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

delete(_**kwargs_)

Deletes the specified route from the specified route table.


**Request Syntax**

```py
response = route.delete(
    DestinationIpv6CidrBlock='string',
    DestinationPrefixListId='string',
    DryRun=True|False,

)
```

Parameters

- **DestinationIpv6CidrBlock** (_string_) -- The IPv6 CIDR range for the route. The value you specify must match the CIDR for the route exactly.
- **DestinationPrefixListId** (_string_) -- The ID of the prefix list for the route.
- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

replace(_**kwargs_)

Replaces an existing route within a route table in a VPC. You must provide only one of the following: internet gateway, virtual private gateway, NAT instance, NAT gateway, VPC peering connection, network interface, egress-only internet gateway, or transit gateway.

For more information, see [Route Tables](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Route_Tables.html) in the _Amazon Virtual Private Cloud User Guide_ .


**Request Syntax**

```py
response = route.replace(
    DestinationIpv6CidrBlock='string',
    DestinationPrefixListId='string',
    DryRun=True|False,
    VpcEndpointId='string',
    EgressOnlyInternetGatewayId='string',
    GatewayId='string',
    InstanceId='string',
    LocalTarget=True|False,
    NatGatewayId='string',
    TransitGatewayId='string',
    LocalGatewayId='string',
    CarrierGatewayId='string',
    NetworkInterfaceId='string',
    VpcPeeringConnectionId='string'
)
```

Parameters

- **DestinationIpv6CidrBlock** (_string_) -- The IPv6 CIDR address block used for the destination match. The value that you provide must match the CIDR of an existing route in the table.
- **DestinationPrefixListId** (_string_) -- The ID of the prefix list for the route.
- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **VpcEndpointId** (_string_) -- The ID of a VPC endpoint. Supported for Gateway Load Balancer endpoints only.
- **EgressOnlyInternetGatewayId** (_string_) -- [IPv6 traffic only] The ID of an egress-only internet gateway.
- **GatewayId** (_string_) -- The ID of an internet gateway or virtual private gateway.
- **InstanceId** (_string_) -- The ID of a NAT instance in your VPC.
- **LocalTarget** (_boolean_) -- Specifies whether to reset the local route to its default target (local ).
- **NatGatewayId** (_string_) -- [IPv4 traffic only] The ID of a NAT gateway.
- **TransitGatewayId** (_string_) -- The ID of a transit gateway.
- **LocalGatewayId** (_string_) -- The ID of the local gateway.
- **CarrierGatewayId** (_string_) -- [IPv4 traffic only] The ID of a carrier gateway.
- **NetworkInterfaceId** (_string_) -- The ID of a network interface.
- **VpcPeeringConnectionId** (_string_) -- The ID of a VPC peering connection.
- None

Sub-resources

Sub-resources are methods that create a new instance of a child resource. This resource's identifiers get passed along to the child. For more information about sub-resources refer to the [_Resources Introduction Guide_](../../guide/resources.html#subresources-intro).

RouteTable()

Creates a RouteTable resource.:

route_table = route.RouteTable()
Return:
- Return type: [EC2.RouteTable](#EC2.RouteTable "EC2.RouteTable")
- A RouteTable resource

[RouteTable](#id1249)
------------------------------------------------------------------

_class_ EC2.RouteTable(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) RouteTable:

import boto3

ec2 = boto3.resource('ec2')
route_table = ec2.RouteTable('id')
```

Parameters

**id** (_string_) -- The RouteTable's id identifier. This **must** be set.

available identifiers:

- [id](#EC2.RouteTable.id "EC2.RouteTable.id")

available attributes:

- [associations_attribute](#EC2.RouteTable.associations_attribute "EC2.RouteTable.associations_attribute")
- [owner_id](#EC2.RouteTable.owner_id "EC2.RouteTable.owner_id")
- [propagating_vgws](#EC2.RouteTable.propagating_vgws "EC2.RouteTable.propagating_vgws")
- [route_table_id](#EC2.RouteTable.route_table_id "EC2.RouteTable.route_table_id")
- [routes_attribute](#EC2.RouteTable.routes_attribute "EC2.RouteTable.routes_attribute")
- [tags](#EC2.RouteTable.tags "EC2.RouteTable.tags")
- [vpc_id](#EC2.RouteTable.vpc_id "EC2.RouteTable.vpc_id")

available references:

- [associations](#EC2.RouteTable.associations "EC2.RouteTable.associations")
- [routes](#EC2.RouteTable.routes "EC2.RouteTable.routes")
- [vpc](#EC2.RouteTable.vpc "EC2.RouteTable.vpc")

available actions:

- [associate_with_subnet()](#EC2.RouteTable.associate_with_subnet "EC2.RouteTable.associate_with_subnet")
- [create_route()](#EC2.RouteTable.create_route "EC2.RouteTable.create_route")
- [create_tags()](#EC2.RouteTable.create_tags "EC2.RouteTable.create_tags")
- [delete()](#EC2.RouteTable.delete "EC2.RouteTable.delete")
- [get_available_subresources()](#EC2.RouteTable.get_available_subresources "EC2.RouteTable.get_available_subresources")
- [load()](#EC2.RouteTable.load "EC2.RouteTable.load")
- [reload()](#EC2.RouteTable.reload "EC2.RouteTable.reload")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

id

_(string)_ The RouteTable's id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.RouteTable.load "EC2.RouteTable.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

associations_attribute

- _(list) --_

    The associations between the route table and one or more subnets or a gateway.

    - _(dict) --
        Describes an association between a route table and a subnet or gateway.

        - **Main** _(boolean) --     
            Indicates whether this is the main route table.

        - **RouteTableAssociationId** _(string) --     
            The ID of the association.

        - **RouteTableId** _(string) --     
            The ID of the route table.

        - **SubnetId** _(string) --     
            The ID of the subnet. A subnet ID is not returned for an implicit association.

        - **GatewayId** _(string) --     
            The ID of the internet gateway or virtual private gateway.

        - **AssociationState** _(dict) --     
            The state of the association.

            - **State** _(string) --         
                The state of the association.

            - **StatusMessage** _(string) --         
                The status message, if applicable.


owner_id

- _(string) --_

    The ID of the AWS account that owns the route table.


propagating_vgws

- _(list) --_

    Any virtual private gateway (VGW) propagating routes.

    - _(dict) --
        Describes a virtual private gateway propagating route.

        - **GatewayId** _(string) --     
            The ID of the virtual private gateway.


route_table_id

- _(string) --_

    The ID of the route table.


routes_attribute

- _(list) --_

    The routes in the route table.

    - _(dict) --
        Describes a route in a route table.

        - **DestinationCidrBlock** _(string) --     
            The IPv4 CIDR block used for the destination match.

        - **DestinationIpv6CidrBlock** _(string) --     
            The IPv6 CIDR block used for the destination match.

        - **DestinationPrefixListId** _(string) --     
            The prefix of the AWS service.

        - **EgressOnlyInternetGatewayId** _(string) --     
            The ID of the egress-only internet gateway.

        - **GatewayId** _(string) --     
            The ID of a gateway attached to your VPC.

        - **InstanceId** _(string) --     
            The ID of a NAT instance in your VPC.

        - **InstanceOwnerId** _(string) --     
            The AWS account ID of the owner of the instance.

        - **NatGatewayId** _(string) --     
            The ID of a NAT gateway.

        - **TransitGatewayId** _(string) --     
            The ID of a transit gateway.

        - **LocalGatewayId** _(string) --     
            The ID of the local gateway.

        - **CarrierGatewayId** _(string) --     
            The ID of the carrier gateway.

        - **NetworkInterfaceId** _(string) --     
            The ID of the network interface.

        - **Origin** _(string) --     
            Describes how the route was created.

            - CreateRouteTable - The route was automatically created when the route table was created.
            - CreateRoute - The route was manually added to the route table.
            - EnableVgwRoutePropagation - The route was propagated by route propagation.
        - **State** _(string) --     
            The state of the route. The blackhole state indicates that the route's target isn't available (for example, the specified gateway isn't attached to the VPC, or the specified NAT instance has been terminated).

        - **VpcPeeringConnectionId** _(string) --     
            The ID of a VPC peering connection.


tags

- _(list) --_

    Any tags assigned to the route table.

    - _(dict) --
        Describes a tag.

        - **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        - **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


vpc_id

- _(string) --_

    The ID of the VPC.


References

References are related resource instances that have a belongs-to relationship. For more information about references refer to the [_Resources Introduction Guide_](../../guide/resources.html#references-intro).

associations

(RouteTableAssociation) The related associations if set, otherwise None.

routes

(Route) The related routes if set, otherwise None.

vpc

(Vpc) The related vpc if set, otherwise None.

Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

associate_with_subnet(_**kwargs_)

Associates a subnet in your VPC or an internet gateway or virtual private gateway attached to your VPC with a route table in your VPC. This association causes traffic from the subnet or gateway to be routed according to the routes in the route table. The action returns an association ID, which you need in order to disassociate the route table later. A route table can be associated with multiple subnets.

For more information, see [Route Tables](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Route_Tables.html) in the _Amazon Virtual Private Cloud User Guide_ .


**Request Syntax**

```py
route_table_association = route_table.associate_with_subnet(
    DryRun=True|False,
    SubnetId='string',
    GatewayId='string'
)
```

Parameters

- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **SubnetId** (_string_) -- The ID of the subnet.
- **GatewayId** (_string_) -- The ID of the internet gateway or virtual private gateway.
Return:
- Return type: ec2.RouteTableAssociation
- RouteTableAssociation resource

create_route(_**kwargs_)

Creates a route in a route table within a VPC.

You must specify one of the following targets: internet gateway or virtual private gateway, NAT instance, NAT gateway, VPC peering connection, network interface, egress-only internet gateway, or transit gateway.

When determining how to route traffic, we use the route with the most specific match. For example, traffic is destined for the IPv4 address 192.0.2.3 , and the route table includes the following two IPv4 routes:

- 192.0.2.0/24 (goes to some target A)
- 192.0.2.0/28 (goes to some target B)

Both routes apply to the traffic destined for 192.0.2.3 . However, the second route in the list covers a smaller number of IP addresses and is therefore more specific, so we use that route to determine where to target the traffic.

For more information about route tables, see [Route Tables](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Route_Tables.html) in the _Amazon Virtual Private Cloud User Guide_ .


**Request Syntax**

```py
route = route_table.create_route(
    DestinationCidrBlock='string',
    DestinationIpv6CidrBlock='string',
    DestinationPrefixListId='string',
    DryRun=True|False,
    VpcEndpointId='string',
    EgressOnlyInternetGatewayId='string',
    GatewayId='string',
    InstanceId='string',
    NatGatewayId='string',
    TransitGatewayId='string',
    LocalGatewayId='string',
    CarrierGatewayId='string',
    NetworkInterfaceId='string',
    VpcPeeringConnectionId='string'
)
```

Parameters

- **DestinationCidrBlock** (_string_) -- The IPv4 CIDR address block used for the destination match. Routing decisions are based on the most specific match. We modify the specified CIDR block to its canonical form; for example, if you specify 100.68.0.18/18 , we modify it to 100.68.0.0/18 .
- **DestinationIpv6CidrBlock** (_string_) -- The IPv6 CIDR block used for the destination match. Routing decisions are based on the most specific match.
- **DestinationPrefixListId** (_string_) -- The ID of a prefix list used for the destination match.
- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **VpcEndpointId** (_string_) -- The ID of a VPC endpoint. Supported for Gateway Load Balancer endpoints only.
- **EgressOnlyInternetGatewayId** (_string_) -- [IPv6 traffic only] The ID of an egress-only internet gateway.
- **GatewayId** (_string_) -- The ID of an internet gateway or virtual private gateway attached to your VPC.
- **InstanceId** (_string_) -- The ID of a NAT instance in your VPC. The operation fails if you specify an instance ID unless exactly one network interface is attached.
- **NatGatewayId** (_string_) -- [IPv4 traffic only] The ID of a NAT gateway.
- **TransitGatewayId** (_string_) -- The ID of a transit gateway.
- **LocalGatewayId** (_string_) -- The ID of the local gateway.
- **CarrierGatewayId** (_string_) --

    The ID of the carrier gateway.

    You can only use this option when the VPC contains a subnet which is associated with a Wavelength Zone.

- **NetworkInterfaceId** (_string_) -- The ID of a network interface.
- **VpcPeeringConnectionId** (_string_) -- The ID of a VPC peering connection.
Return:
- Return type: ec2.Route
- Route resource

create_tags(_**kwargs_)

Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources. When you specify an existing tag key, the value is overwritten with the new value. Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
tag = route_table.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Parameters

- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **Tags** (_list_) -- **[REQUIRED]** The tags. The value parameter is required, but if you don't want the tag to have a value, specify the parameter with no value, and we set the value to an empty string.

    - _(dict) --
        Describes a tag.

        - **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        - **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: list(ec2.Tag)
- A list of Tag resources

delete(_**kwargs_)

Deletes the specified route table. You must disassociate the route table from any subnets before you can delete it. You can't delete the main route table.


**Request Syntax**

```py
response = route_table.delete(
    DryRun=True|False,

)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_route_tables()](#EC2.Client.describe_route_tables "EC2.Client.describe_route_tables") to update the attributes of the RouteTable resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
route_table.load()
- None

reload()

Calls [EC2.Client.describe_route_tables()](#EC2.Client.describe_route_tables "EC2.Client.describe_route_tables") to update the attributes of the RouteTable resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
route_table.reload()
- None

[RouteTableAssociation](#id1250)
----------------------------------------------------------------------------------------

_class_ EC2.RouteTableAssociation(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) RouteTableAssociation:

import boto3

ec2 = boto3.resource('ec2')
route_table_association = ec2.RouteTableAssociation('id')
```

Parameters

**id** (_string_) -- The RouteTableAssociation's id identifier. This **must** be set.

available identifiers:

- [id](#EC2.RouteTableAssociation.id "EC2.RouteTableAssociation.id")

available attributes:

- [association_state](#EC2.RouteTableAssociation.association_state "EC2.RouteTableAssociation.association_state")
- [gateway_id](#EC2.RouteTableAssociation.gateway_id "EC2.RouteTableAssociation.gateway_id")
- [main](#EC2.RouteTableAssociation.main "EC2.RouteTableAssociation.main")
- [route_table_association_id](#EC2.RouteTableAssociation.route_table_association_id "EC2.RouteTableAssociation.route_table_association_id")
- [route_table_id](#EC2.RouteTableAssociation.route_table_id "EC2.RouteTableAssociation.route_table_id")
- [subnet_id](#EC2.RouteTableAssociation.subnet_id "EC2.RouteTableAssociation.subnet_id")

available references:

- [route_table](#EC2.RouteTableAssociation.route_table "EC2.RouteTableAssociation.route_table")
- [subnet](#EC2.RouteTableAssociation.subnet "EC2.RouteTableAssociation.subnet")

available actions:

- [delete()](#EC2.RouteTableAssociation.delete "EC2.RouteTableAssociation.delete")
- [get_available_subresources()](#EC2.RouteTableAssociation.get_available_subresources "EC2.RouteTableAssociation.get_available_subresources")
- [replace_subnet()](#EC2.RouteTableAssociation.replace_subnet "EC2.RouteTableAssociation.replace_subnet")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

id

_(string)_ The RouteTableAssociation's id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the load() method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

association_state

- _(dict) --_

    The state of the association.

    - **State** _(string) --
        The state of the association.

    - **StatusMessage** _(string) --
        The status message, if applicable.


gateway_id

- _(string) --_

    The ID of the internet gateway or virtual private gateway.


main

- _(boolean) --_

    Indicates whether this is the main route table.


route_table_association_id

- _(string) --_

    The ID of the association.


route_table_id

- _(string) --_

    The ID of the route table.


subnet_id

- _(string) --_

    The ID of the subnet. A subnet ID is not returned for an implicit association.


References

References are related resource instances that have a belongs-to relationship. For more information about references refer to the [_Resources Introduction Guide_](../../guide/resources.html#references-intro).

route_table

(RouteTable) The related route_table if set, otherwise None.

subnet

(Subnet) The related subnet if set, otherwise None.

Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

delete(_**kwargs_)

Disassociates a subnet or gateway from a route table.

After you perform this action, the subnet no longer uses the routes in the route table. Instead, it uses the routes in the VPC's main route table. For more information about route tables, see [Route Tables](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Route_Tables.html) in the _Amazon Virtual Private Cloud User Guide_ .


**Request Syntax**

```py
response = route_table_association.delete(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

replace_subnet(_**kwargs_)

Changes the route table associated with a given subnet, internet gateway, or virtual private gateway in a VPC. After the operation completes, the subnet or gateway uses the routes in the new route table. For more information about route tables, see [Route Tables](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Route_Tables.html) in the _Amazon Virtual Private Cloud User Guide_ .

You can also use this operation to change which table is the main route table in the VPC. Specify the main route table's association ID and the route table ID of the new main route table.


**Request Syntax**

```py
route_table_association = route_table_association.replace_subnet(
    DryRun=True|False,
    RouteTableId='string'
)
```

Parameters

- **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- **RouteTableId** (_string_) -- **[REQUIRED]** The ID of the new route table to associate with the subnet.

Return:
- Return type: ec2.RouteTableAssociation
- RouteTableAssociation resource

[SecurityGroup](#id1251)
------------------------------------------------------------------------

_class_ EC2.SecurityGroup(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) SecurityGroup:

import boto3

ec2 = boto3.resource('ec2')
security_group = ec2.SecurityGroup('id')
```

Parameters

**id** (_string_) -- The SecurityGroup's id identifier. This **must** be set.

available identifiers:

- [id](#EC2.SecurityGroup.id "EC2.SecurityGroup.id")

available attributes:

- [description](#EC2.SecurityGroup.description "EC2.SecurityGroup.description")
- [group_id](#EC2.SecurityGroup.group_id "EC2.SecurityGroup.group_id")
- [group_name](#EC2.SecurityGroup.group_name "EC2.SecurityGroup.group_name")
- [ip_permissions](#EC2.SecurityGroup.ip_permissions "EC2.SecurityGroup.ip_permissions")
- [ip_permissions_egress](#EC2.SecurityGroup.ip_permissions_egress "EC2.SecurityGroup.ip_permissions_egress")
- [owner_id](#EC2.SecurityGroup.owner_id "EC2.SecurityGroup.owner_id")
- [tags](#EC2.SecurityGroup.tags "EC2.SecurityGroup.tags")
- [vpc_id](#EC2.SecurityGroup.vpc_id "EC2.SecurityGroup.vpc_id")

available actions:

- [authorize_egress()](#EC2.SecurityGroup.authorize_egress "EC2.SecurityGroup.authorize_egress")
- [authorize_ingress()](#EC2.SecurityGroup.authorize_ingress "EC2.SecurityGroup.authorize_ingress")
- [create_tags()](#EC2.SecurityGroup.create_tags "EC2.SecurityGroup.create_tags")
- [delete()](#EC2.SecurityGroup.delete "EC2.SecurityGroup.delete")
- [get_available_subresources()](#EC2.SecurityGroup.get_available_subresources "EC2.SecurityGroup.get_available_subresources")
- [load()](#EC2.SecurityGroup.load "EC2.SecurityGroup.load")
- [reload()](#EC2.SecurityGroup.reload "EC2.SecurityGroup.reload")
*   [revoke_egress()](#EC2.SecurityGroup.revoke_egress "EC2.SecurityGroup.revoke_egress")
*   [revoke_ingress()](#EC2.SecurityGroup.revoke_ingress "EC2.SecurityGroup.revoke_ingress")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

id

_(string)_ The SecurityGroup's id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.SecurityGroup.load "EC2.SecurityGroup.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

description

*   _(string) --_

    A description of the security group.


group_id

*   _(string) --_

    The ID of the security group.


group_name

*   _(string) --_

    The name of the security group.


ip_permissions

*   _(list) --_

    The inbound rules associated with the security group.

    *   _(dict) --
        Describes a set of permissions for a security group rule.

        *   **FromPort** _(integer) --     
            The start of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 type number. A value of -1 indicates all ICMP/ICMPv6 types. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **IpProtocol** _(string) --     
            The IP protocol name (tcp , udp , icmp , icmpv6 ) or number (see [Protocol Numbers](http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml) ).

            [VPC only] Use -1 to specify all protocols. When authorizing security group rules, specifying -1 or a protocol number other than tcp , udp , icmp , or icmpv6 allows traffic on all ports, regardless of any port range you specify. For tcp , udp , and icmp , you must specify a port range. For icmpv6 , the port range is optional; if you omit the port range, traffic for all types and codes is allowed.

        *   **IpRanges** _(list) --     
            The IPv4 ranges.

            *   _(dict) --         
                Describes an IPv4 range.

                *   **CidrIp** _(string) --             
                    The IPv4 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv4 address, use the /32 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv4 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **Ipv6Ranges** _(list) --     
            [VPC only] The IPv6 ranges.

            *   _(dict) --         
                [EC2-VPC only] Describes an IPv6 range.

                *   **CidrIpv6** _(string) --             
                    The IPv6 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv6 address, use the /128 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv6 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **PrefixListIds** _(list) --     
            [VPC only] The prefix list IDs.

            *   _(dict) --         
                Describes a prefix list ID.

                *   **Description** _(string) --             
                    A description for the security group rule that references this prefix list ID.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **PrefixListId** _(string) --             
                    The ID of the prefix.

        *   **ToPort** _(integer) --     
            The end of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 code. A value of -1 indicates all ICMP/ICMPv6 codes. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **UserIdGroupPairs** _(list) --     
            The security group and AWS account ID pairs.

            *   _(dict) --         
                Describes a security group and AWS account ID pair.

                *   **Description** _(string) --             
                    A description for the security group rule that references this user ID group pair.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **GroupId** _(string) --             
                    The ID of the security group.

                *   **GroupName** _(string) --             
                    The name of the security group. In a request, use this parameter for a security group in EC2-Classic or a default VPC only. For a security group in a nondefault VPC, use the security group ID.

                    For a referenced security group in another VPC, this value is not returned if the referenced security group is deleted.

                *   **PeeringStatus** _(string) --             
                    The status of a VPC peering connection, if applicable.

                *   **UserId** _(string) --             
                    The ID of an AWS account.

                    For a referenced security group in another VPC, the account ID of the referenced security group is returned in the response. If the referenced security group is deleted, this value is not returned.

                    [EC2-Classic] Required when adding or removing rules that reference a security group in another AWS account.

                *   **VpcId** _(string) --             
                    The ID of the VPC for the referenced security group, if applicable.

                *   **VpcPeeringConnectionId** _(string) --             
                    The ID of the VPC peering connection, if applicable.


ip_permissions_egress

*   _(list) --_

    [VPC only] The outbound rules associated with the security group.

    *   _(dict) --
        Describes a set of permissions for a security group rule.

        *   **FromPort** _(integer) --     
            The start of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 type number. A value of -1 indicates all ICMP/ICMPv6 types. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **IpProtocol** _(string) --     
            The IP protocol name (tcp , udp , icmp , icmpv6 ) or number (see [Protocol Numbers](http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml) ).

            [VPC only] Use -1 to specify all protocols. When authorizing security group rules, specifying -1 or a protocol number other than tcp , udp , icmp , or icmpv6 allows traffic on all ports, regardless of any port range you specify. For tcp , udp , and icmp , you must specify a port range. For icmpv6 , the port range is optional; if you omit the port range, traffic for all types and codes is allowed.

        *   **IpRanges** _(list) --     
            The IPv4 ranges.

            *   _(dict) --         
                Describes an IPv4 range.

                *   **CidrIp** _(string) --             
                    The IPv4 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv4 address, use the /32 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv4 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **Ipv6Ranges** _(list) --     
            [VPC only] The IPv6 ranges.

            *   _(dict) --         
                [EC2-VPC only] Describes an IPv6 range.

                *   **CidrIpv6** _(string) --             
                    The IPv6 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv6 address, use the /128 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv6 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **PrefixListIds** _(list) --     
            [VPC only] The prefix list IDs.

            *   _(dict) --         
                Describes a prefix list ID.

                *   **Description** _(string) --             
                    A description for the security group rule that references this prefix list ID.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **PrefixListId** _(string) --             
                    The ID of the prefix.

        *   **ToPort** _(integer) --     
            The end of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 code. A value of -1 indicates all ICMP/ICMPv6 codes. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **UserIdGroupPairs** _(list) --     
            The security group and AWS account ID pairs.

            *   _(dict) --         
                Describes a security group and AWS account ID pair.

                *   **Description** _(string) --             
                    A description for the security group rule that references this user ID group pair.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **GroupId** _(string) --             
                    The ID of the security group.

                *   **GroupName** _(string) --             
                    The name of the security group. In a request, use this parameter for a security group in EC2-Classic or a default VPC only. For a security group in a nondefault VPC, use the security group ID.

                    For a referenced security group in another VPC, this value is not returned if the referenced security group is deleted.

                *   **PeeringStatus** _(string) --             
                    The status of a VPC peering connection, if applicable.

                *   **UserId** _(string) --             
                    The ID of an AWS account.

                    For a referenced security group in another VPC, the account ID of the referenced security group is returned in the response. If the referenced security group is deleted, this value is not returned.

                    [EC2-Classic] Required when adding or removing rules that reference a security group in another AWS account.

                *   **VpcId** _(string) --             
                    The ID of the VPC for the referenced security group, if applicable.

                *   **VpcPeeringConnectionId** _(string) --             
                    The ID of the VPC peering connection, if applicable.


owner_id

*   _(string) --_

    The AWS account ID of the owner of the security group.


tags

*   _(list) --_

    Any tags assigned to the security group.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


vpc_id

*   _(string) --_

    [VPC only] The ID of the VPC for the security group.


Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

authorize_egress(_**kwargs_)

[VPC only] Adds the specified egress rules to a security group for use with a VPC.

An outbound rule permits instances to send traffic to the specified IPv4 or IPv6 CIDR address ranges, or to the instances associated with the specified destination security groups.

You specify a protocol for each rule (for example, TCP). For the TCP and UDP protocols, you must also specify the destination port or port range. For the ICMP protocol, you must also specify the ICMP type and code. You can use -1 for the type or code to mean all types or all codes.

Rule changes are propagated to affected instances as quickly as possible. However, a small delay might occur.

For more information about VPC security group limits, see [Amazon VPC Limits](https://docs.aws.amazon.com/vpc/latest/userguide/amazon-vpc-limits.html) .


**Request Syntax**

```py
response = security_group.authorize_egress(
    DryRun=True|False,
    IpPermissions=[
        {
            'FromPort': 123,
            'IpProtocol': 'string',
            'IpRanges': [
                {
                    'CidrIp': 'string',
                    'Description': 'string'
                },
            ],
            'Ipv6Ranges': [
                {
                    'CidrIpv6': 'string',
                    'Description': 'string'
                },
            ],
            'PrefixListIds': [
                {
                    'Description': 'string',
                    'PrefixListId': 'string'
                },
            ],
            'ToPort': 123,
            'UserIdGroupPairs': [
                {
                    'Description': 'string',
                    'GroupId': 'string',
                    'GroupName': 'string',
                    'PeeringStatus': 'string',
                    'UserId': 'string',
                    'VpcId': 'string',
                    'VpcPeeringConnectionId': 'string'
                },
            ]
        },
    ],
    CidrIp='string',
    FromPort=123,
    IpProtocol='string',
    ToPort=123,
    SourceSecurityGroupName='string',
    SourceSecurityGroupOwnerId='string'
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **IpPermissions** (_list_) --

    The sets of IP permissions. You can't specify a destination security group and a CIDR IP address range in the same set of permissions.

    *   _(dict) --
        Describes a set of permissions for a security group rule.

        *   **FromPort** _(integer) --     
            The start of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 type number. A value of -1 indicates all ICMP/ICMPv6 types. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **IpProtocol** _(string) --     
            The IP protocol name (tcp , udp , icmp , icmpv6 ) or number (see [Protocol Numbers](http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml) ).

            [VPC only] Use -1 to specify all protocols. When authorizing security group rules, specifying -1 or a protocol number other than tcp , udp , icmp , or icmpv6 allows traffic on all ports, regardless of any port range you specify. For tcp , udp , and icmp , you must specify a port range. For icmpv6 , the port range is optional; if you omit the port range, traffic for all types and codes is allowed.

        *   **IpRanges** _(list) --     
            The IPv4 ranges.

            *   _(dict) --         
                Describes an IPv4 range.

                *   **CidrIp** _(string) --             
                    The IPv4 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv4 address, use the /32 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv4 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **Ipv6Ranges** _(list) --     
            [VPC only] The IPv6 ranges.

            *   _(dict) --         
                [EC2-VPC only] Describes an IPv6 range.

                *   **CidrIpv6** _(string) --             
                    The IPv6 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv6 address, use the /128 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv6 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **PrefixListIds** _(list) --     
            [VPC only] The prefix list IDs.

            *   _(dict) --         
                Describes a prefix list ID.

                *   **Description** _(string) --             
                    A description for the security group rule that references this prefix list ID.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **PrefixListId** _(string) --             
                    The ID of the prefix.

        *   **ToPort** _(integer) --     
            The end of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 code. A value of -1 indicates all ICMP/ICMPv6 codes. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **UserIdGroupPairs** _(list) --     
            The security group and AWS account ID pairs.

            *   _(dict) --         
                Describes a security group and AWS account ID pair.

                *   **Description** _(string) --             
                    A description for the security group rule that references this user ID group pair.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **GroupId** _(string) --             
                    The ID of the security group.

                *   **GroupName** _(string) --             
                    The name of the security group. In a request, use this parameter for a security group in EC2-Classic or a default VPC only. For a security group in a nondefault VPC, use the security group ID.

                    For a referenced security group in another VPC, this value is not returned if the referenced security group is deleted.

                *   **PeeringStatus** _(string) --             
                    The status of a VPC peering connection, if applicable.

                *   **UserId** _(string) --             
                    The ID of an AWS account.

                    For a referenced security group in another VPC, the account ID of the referenced security group is returned in the response. If the referenced security group is deleted, this value is not returned.

                    [EC2-Classic] Required when adding or removing rules that reference a security group in another AWS account.

                *   **VpcId** _(string) --             
                    The ID of the VPC for the referenced security group, if applicable.

                *   **VpcPeeringConnectionId** _(string) --             
                    The ID of the VPC peering connection, if applicable.

*   **CidrIp** (_string_) -- Not supported. Use a set of IP permissions to specify the CIDR.
*   **FromPort** (_integer_) -- Not supported. Use a set of IP permissions to specify the port.
*   **IpProtocol** (_string_) -- Not supported. Use a set of IP permissions to specify the protocol name or number.
*   **ToPort** (_integer_) -- Not supported. Use a set of IP permissions to specify the port.
*   **SourceSecurityGroupName** (_string_) -- Not supported. Use a set of IP permissions to specify a destination security group.
*   **SourceSecurityGroupOwnerId** (_string_) -- Not supported. Use a set of IP permissions to specify a destination security group.
- None

authorize_ingress(_**kwargs_)

Adds the specified ingress rules to a security group.

An inbound rule permits instances to receive traffic from the specified IPv4 or IPv6 CIDR address ranges, or from the instances associated with the specified destination security groups.

You specify a protocol for each rule (for example, TCP). For TCP and UDP, you must also specify the destination port or port range. For ICMP/ICMPv6, you must also specify the ICMP/ICMPv6 type and code. You can use -1 to mean all types or all codes.

Rule changes are propagated to instances within the security group as quickly as possible. However, a small delay might occur.

For more information about VPC security group limits, see [Amazon VPC Limits](https://docs.aws.amazon.com/vpc/latest/userguide/amazon-vpc-limits.html) .


**Request Syntax**

```py
response = security_group.authorize_ingress(
    CidrIp='string',
    FromPort=123,
    GroupName='string',
    IpPermissions=[
        {
            'FromPort': 123,
            'IpProtocol': 'string',
            'IpRanges': [
                {
                    'CidrIp': 'string',
                    'Description': 'string'
                },
            ],
            'Ipv6Ranges': [
                {
                    'CidrIpv6': 'string',
                    'Description': 'string'
                },
            ],
            'PrefixListIds': [
                {
                    'Description': 'string',
                    'PrefixListId': 'string'
                },
            ],
            'ToPort': 123,
            'UserIdGroupPairs': [
                {
                    'Description': 'string',
                    'GroupId': 'string',
                    'GroupName': 'string',
                    'PeeringStatus': 'string',
                    'UserId': 'string',
                    'VpcId': 'string',
                    'VpcPeeringConnectionId': 'string'
                },
            ]
        },
    ],
    IpProtocol='string',
    SourceSecurityGroupName='string',
    SourceSecurityGroupOwnerId='string',
    ToPort=123,
    DryRun=True|False
)
```

Parameters

*   **CidrIp** (_string_) --

    The IPv4 address range, in CIDR format. You can't specify this parameter when specifying a source security group. To specify an IPv6 address range, use a set of IP permissions.

    Alternatively, use a set of IP permissions to specify multiple rules and a description for the rule.

*   **FromPort** (_integer_) --

    The start of port range for the TCP and UDP protocols, or an ICMP type number. For the ICMP type number, use -1 to specify all types. If you specify all ICMP types, you must specify all codes.

    Alternatively, use a set of IP permissions to specify multiple rules and a description for the rule.

*   **GroupName** (_string_) -- [EC2-Classic, default VPC] The name of the security group. You must specify either the security group ID or the security group name in the request.
*   **IpPermissions** (_list_) --

    The sets of IP permissions.

    *   _(dict) --
        Describes a set of permissions for a security group rule.

        *   **FromPort** _(integer) --     
            The start of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 type number. A value of -1 indicates all ICMP/ICMPv6 types. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **IpProtocol** _(string) --     
            The IP protocol name (tcp , udp , icmp , icmpv6 ) or number (see [Protocol Numbers](http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml) ).

            [VPC only] Use -1 to specify all protocols. When authorizing security group rules, specifying -1 or a protocol number other than tcp , udp , icmp , or icmpv6 allows traffic on all ports, regardless of any port range you specify. For tcp , udp , and icmp , you must specify a port range. For icmpv6 , the port range is optional; if you omit the port range, traffic for all types and codes is allowed.

        *   **IpRanges** _(list) --     
            The IPv4 ranges.

            *   _(dict) --         
                Describes an IPv4 range.

                *   **CidrIp** _(string) --             
                    The IPv4 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv4 address, use the /32 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv4 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **Ipv6Ranges** _(list) --     
            [VPC only] The IPv6 ranges.

            *   _(dict) --         
                [EC2-VPC only] Describes an IPv6 range.

                *   **CidrIpv6** _(string) --             
                    The IPv6 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv6 address, use the /128 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv6 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **PrefixListIds** _(list) --     
            [VPC only] The prefix list IDs.

            *   _(dict) --         
                Describes a prefix list ID.

                *   **Description** _(string) --             
                    A description for the security group rule that references this prefix list ID.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **PrefixListId** _(string) --             
                    The ID of the prefix.

        *   **ToPort** _(integer) --     
            The end of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 code. A value of -1 indicates all ICMP/ICMPv6 codes. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **UserIdGroupPairs** _(list) --     
            The security group and AWS account ID pairs.

            *   _(dict) --         
                Describes a security group and AWS account ID pair.

                *   **Description** _(string) --             
                    A description for the security group rule that references this user ID group pair.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **GroupId** _(string) --             
                    The ID of the security group.

                *   **GroupName** _(string) --             
                    The name of the security group. In a request, use this parameter for a security group in EC2-Classic or a default VPC only. For a security group in a nondefault VPC, use the security group ID.

                    For a referenced security group in another VPC, this value is not returned if the referenced security group is deleted.

                *   **PeeringStatus** _(string) --             
                    The status of a VPC peering connection, if applicable.

                *   **UserId** _(string) --             
                    The ID of an AWS account.

                    For a referenced security group in another VPC, the account ID of the referenced security group is returned in the response. If the referenced security group is deleted, this value is not returned.

                    [EC2-Classic] Required when adding or removing rules that reference a security group in another AWS account.

                *   **VpcId** _(string) --             
                    The ID of the VPC for the referenced security group, if applicable.

                *   **VpcPeeringConnectionId** _(string) --             
                    The ID of the VPC peering connection, if applicable.

*   **IpProtocol** (_string_) --

    The IP protocol name (tcp , udp , icmp ) or number (see [Protocol Numbers](http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml) ). To specify icmpv6 , use a set of IP permissions.

    [VPC only] Use -1 to specify all protocols. If you specify -1 or a protocol other than tcp , udp , or icmp , traffic on all ports is allowed, regardless of any ports you specify.

    Alternatively, use a set of IP permissions to specify multiple rules and a description for the rule.

*   **SourceSecurityGroupName**```
 (_string_) -- [EC2-Classic, default VPC] The name of the source security group. You can't specify this parameter in combination with the following parameters: the CIDR IP address range, the start of the port range, the IP protocol, and the end of the port range. Creates rules that grant full ICMP, UDP, and TCP access. To create a rule with a specific IP protocol and port range, use a set of IP permissions instead. For EC2-VPC, the source security group must be in the same VPC.
*   **SourceSecurityGroupOwnerId** (_string_) -- [nondefault VPC] The AWS account ID for```
 the source security group, if the source security group is in a different account. You can't specify this parameter in combination with the following parameters: the CIDR IP address range, the IP protocol, the start of the port range, and the end of the port range. Creates rules that grant full ICMP, UDP, and TCP access. To create a rule with a specific IP protocol and port range, use a set of IP permissions instead.
*   **ToPort** (_integer_) --

    The end of port range for the TCP and UDP protocols, or an ICMP code number. For the ICMP code number, use -1 to specify all codes. If you specify all ICMP types, you must specify all codes.

    Alternatively, use a set of IP permissions to specify multiple rules and a description for the rule.

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

create_tags(_**kwargs_)

Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources. When you specify an existing tag key, the value is overwritten with the new value. Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
tag = security_group.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Tags** (_list_) -- **[REQUIRED]** The tags. The value parameter is required, but if you don't want the tag to have a value, specify the parameter with no value, and we set the value to an empty string.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: list(ec2.Tag)
- A list of Tag resources

delete(_**kwargs_)

Deletes a security group.

If you attempt to delete a security group that is associated with an instance, or is referenced by another security group, the operation fails with InvalidGroup.InUse in EC2-Classic or DependencyViolation in EC2-VPC.


**Request Syntax**

```py
response = security_group.delete(
    GroupName='string',
    DryRun=True|False
)
```

Parameters

*   **GroupName** (_string_) -- [EC2-Classic, default VPC] The name of the security group. You can specify either the security group name or the security group ID.
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_security_groups()](#EC2.Client.describe_security_groups "EC2.Client.describe_security_groups") to update the attributes of the SecurityGroup resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
security_group.load()
- None

reload()

Calls [EC2.Client.describe_security_groups()](#EC2.Client.describe_security_groups "EC2.Client.describe_security_groups") to update the attributes of the SecurityGroup resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
security_group.reload()
- None

revoke_egress(_**kwargs_)

[VPC only] Removes the specified egress rules from a security group for EC2-VPC. This action does not apply to security groups for use in EC2-Classic. To remove a rule, the values that you specify (for example, ports) must match the existing rule's values exactly.

Note

[Default VPC] If the values you specify do not match the existing rule's values, no error is returned, and the output describes the security group rules that were not revoked.

AWS recommends that you use DescribeSecurityGroups to verify that the rule has been removed.

Each rule consists of the protocol and the IPv4 or IPv6 CIDR range or source security group. For the TCP and UDP protocols, you must also specify the destination port or range of ports. For the ICMP protocol, you must also specify the ICMP type and code. If the security group rule has a description, you do not have to specify the description to revoke the rule.

Rule changes are propagated to instances within the security group as quickly as possible. However, a small delay might occur.


**Request Syntax**

```py
response = security_group.revoke_egress(
    DryRun=True|False,
    IpPermissions=[
        {
            'FromPort': 123,
            'IpProtocol': 'string',
            'IpRanges': [
                {
                    'CidrIp': 'string',
                    'Description': 'string'
                },
            ],
            'Ipv6Ranges': [
                {
                    'CidrIpv6': 'string',
                    'Description': 'string'
                },
            ],
            'PrefixListIds': [
                {
                    'Description': 'string',
                    'PrefixListId': 'string'
                },
            ],
            'ToPort': 123,
            'UserIdGroupPairs': [
                {
                    'Description': 'string',
                    'GroupId': 'string',
                    'GroupName': 'string',
                    'PeeringStatus': 'string',
                    'UserId': 'string',
                    'VpcId': 'string',
                    'VpcPeeringConnectionId': 'string'
                },
            ]
        },
    ],
    CidrIp='string',
    FromPort=123,
    IpProtocol='string',
    ToPort=123,
    SourceSecurityGroupName='string',
    SourceSecurityGroupOwnerId='string'
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **IpPermissions** (_list_) --

    The sets of IP permissions. You can't specify a destination security group and a CIDR IP address range in the same set of permissions.

    *   _(dict) --
        Describes a set of permissions for a security group rule.

        *   **FromPort** _(integer) --     
            The start of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 type number. A value of -1 indicates all ICMP/ICMPv6 types. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **IpProtocol** _(string) --     
            The IP protocol name (tcp , udp , icmp , icmpv6 ) or number (see [Protocol Numbers](http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml) ).

            [VPC only] Use -1 to specify all protocols. When authorizing security group rules, specifying -1 or a protocol number other than tcp , udp , icmp , or icmpv6 allows traffic on all ports, regardless of any port range you specify. For tcp , udp , and icmp , you must specify a port range. For icmpv6 , the port range is optional; if you omit the port range, traffic for all types and codes is allowed.

        *   **IpRanges** _(list) --     
            The IPv4 ranges.

            *   _(dict) --         
                Describes an IPv4 range.

                *   **CidrIp** _(string) --             
                    The IPv4 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv4 address, use the /32 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv4 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **Ipv6Ranges** _(list) --     
            [VPC only] The IPv6 ranges.

            *   _(dict) --         
                [EC2-VPC only] Describes an IPv6 range.

                *   **CidrIpv6** _(string) --             
                    The IPv6 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv6 address, use the /128 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv6 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **PrefixListIds** _(list) --     
            [VPC only] The prefix list IDs.

            *   _(dict) --         
                Describes a prefix list ID.

                *   **Description** _(string) --             
                    A description for the security group rule that references this prefix list ID.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **PrefixListId** _(string) --             
                    The ID of the prefix.

        *   **ToPort** _(integer) --     
            The end of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 code. A value of -1 indicates all ICMP/ICMPv6 codes. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **UserIdGroupPairs** _(list) --     
            The security group and AWS account ID pairs.

            *   _(dict) --         
                Describes a security group and AWS account ID pair.

                *   **Description** _(string) --             
                    A description for the security group rule that references this user ID group pair.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **GroupId** _(string) --             
                    The ID of the security group.

                *   **GroupName** _(string) --             
                    The name of the security group. In a request, use this parameter for a security group in EC2-Classic or a default VPC only. For a security group in a nondefault VPC, use the security group ID.

                    For a referenced security group in another VPC, this value is not returned if the referenced security group is deleted.

                *   **PeeringStatus** _(string) --             
                    The status of a VPC peering connection, if applicable.

                *   **UserId** _(string) --             
                    The ID of an AWS account.

                    For a referenced security group in another VPC, the account ID of the referenced security group is returned in the response. If the referenced security group is deleted, this value is not returned.

                    [EC2-Classic] Required when adding or removing rules that reference a security group in another AWS account.

                *   **VpcId** _(string) --             
                    The ID of the VPC for the referenced security group, if applicable.

                *   **VpcPeeringConnectionId** _(string) --             
                    The ID of the VPC peering connection, if applicable.

*   **CidrIp** (_string_) -- Not supported. Use a set of IP permissions to specify the CIDR.
*   **FromPort** (_integer_) -- Not supported. Use a set of IP permissions to specify the port.
*   **IpProtocol** (_string_) -- Not supported. Use a set of IP permissions to specify the protocol name or number.
*   **ToPort** (_integer_) -- Not supported. Use a set of IP permissions to specify the port.
*   **SourceSecurityGroupName** (_string_) -- Not supported. Use a set of IP permissions to specify a destination security group.
*   **SourceSecurityGroupOwnerId** (_string_) -- Not supported. Use a set of IP permissions to specify a destination security group.
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'Return': True|False,
    'UnknownIpPermissions': [
        {
            'FromPort': 123,
            'IpProtocol': 'string',
            'IpRanges': [
                {
                    'CidrIp': 'string',
                    'Description': 'string'
                },
            ],
            'Ipv6Ranges': [
                {
                    'CidrIpv6': 'string',
                    'Description': 'string'
                },
            ],
            'PrefixListIds': [
                {
                    'Description': 'string',
                    'PrefixListId': 'string'
                },
            ],
            'ToPort': 123,
            'UserIdGroupPairs': [
                {
                    'Description': 'string',
                    'GroupId': 'string',
                    'GroupName': 'string',
                    'PeeringStatus': 'string',
                    'UserId': 'string',
                    'VpcId': 'string',
                    'VpcPeeringConnectionId': 'string'
                },
            ]
        },
    ]
}
```


**Response Structure**

*   _(dict) --_

    *   **Return** _(boolean) --
        Returns true if the request succeeds; otherwise, returns an error.

    *   **UnknownIpPermissions** _(list) --
        The outbound rules that were unknown to the service. In some cases, unknownIpPermissionSet might be in a different format from the request parameter.

        *   _(dict) --     
            Describes a set of permissions for a security group rule.

            *   **FromPort** _(integer) --         
                The start of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 type number. A value of -1 indicates all ICMP/ICMPv6 types. If you specify all ICMP/ICMPv6 types, you must specify all codes.

            *   **IpProtocol** _(string) --         
                The IP protocol name (tcp , udp , icmp , icmpv6 ) or number (see [Protocol Numbers](http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml) ).

                [VPC only] Use -1 to specify all protocols. When authorizing security group rules, specifying -1 or a protocol number other than tcp , udp , icmp , or icmpv6 allows traffic on all ports, regardless of any port range you specify. For tcp , udp , and icmp , you must specify a port range. For icmpv6 , the port range is optional; if you omit the port range, traffic for all types and codes is allowed.

            *   **IpRanges** _(list) --         
                The IPv4 ranges.

                *   _(dict) --             
                    Describes an IPv4 range.

                    *   **CidrIp** _(string) --                 
                        The IPv4 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv4 address, use the /32 prefix length.

                    *   **Description** _(string) --                 
                        A description for the security group rule that references this IPv4 address range.

                        Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

            *   **Ipv6Ranges** _(list) --         
                [VPC only] The IPv6 ranges.

                *   _(dict) --             
                    [EC2-VPC only] Describes an IPv6 range.

                    *   **CidrIpv6** _(string) --                 
                        The IPv6 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv6 address, use the /128 prefix length.

                    *   **Description** _(string) --                 
                        A description for the security group rule that references this IPv6 address range.

                        Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

            *   **PrefixListIds** _(list) --         
                [VPC only] The prefix list IDs.

                *   _(dict) --             
                    Describes a prefix list ID.

                    *   **Description** _(string) --                 
                        A description for the security group rule that references this prefix list ID.

                        Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                    *   **PrefixListId** _(string) --                 
                        The ID of the prefix.

            *   **ToPort** _(integer) --         
                The end of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 code. A value of -1 indicates all ICMP/ICMPv6 codes. If you specify all ICMP/ICMPv6 types, you must specify all codes.

            *   **UserIdGroupPairs** _(list) --         
                The security group and AWS account ID pairs.

                *   _(dict) --             
                    Describes a security group and AWS account ID pair.

                    *   **Description** _(string) --                 
                        A description for the security group rule that references this user ID group pair.

                        Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                    *   **GroupId** _(string) --                 
                        The ID of the security group.

                    *   **GroupName** _(string) --                 
                        The name of the security group. In a request, use this parameter for a security group in EC2-Classic or a default VPC only. For a security group in a nondefault VPC, use the security group ID.

                        For a referenced security group in another VPC, this value is not returned if the referenced security group is deleted.

                    *   **PeeringStatus** _(string) --                 
                        The status of a VPC peering connection, if applicable.

                    *   **UserId** _(string) --                 
                        The ID of an AWS account.

                        For a referenced security group in another VPC, the account ID of the referenced security group is returned in the response. If the referenced security group is deleted, this value is not returned.

                        [EC2-Classic] Required when adding or removing rules that reference a security group in another AWS account.

                    *   **VpcId** _(string) --                 
                        The ID of the VPC for the referenced security group, if applicable.

                    *   **VpcPeeringConnectionId** _(string) --                 
                        The ID of the VPC peering connection, if applicable.


revoke_ingress(_**kwargs_)

Removes the specified ingress rules from a security group. To remove a rule, the values that you specify (for example, ports) must match the existing rule's values exactly.

Note

[EC2-Classic , default VPC] If the values you specify do not match the existing rule's values, no error is returned, and the output describes the security group rules that were not revoked.

AWS recommends that you use DescribeSecurityGroups to verify that the rule has been removed.

Each rule consists of the protocol and the CIDR range or source security group. For the TCP and UDP protocols, you must also specify the destination port or range of ports. For the ICMP protocol, you must also specify the ICMP type and code. If the security group rule has a description, you do not have to specify the description to revoke the rule.

Rule changes are propagated to instances within the security group as quickly as possible. However, a small delay might occur.


**Request Syntax**

```py
response = security_group.revoke_ingress(
    CidrIp='string',
    FromPort=123,
    GroupName='string',
    IpPermissions=[
        {
            'FromPort': 123,
            'IpProtocol': 'string',
            'IpRanges': [
                {
                    'CidrIp': 'string',
                    'Description': 'string'
                },
            ],
            'Ipv6Ranges': [
                {
                    'CidrIpv6': 'string',
                    'Description': 'string'
                },
            ],
            'PrefixListIds': [
                {
                    'Description': 'string',
                    'PrefixListId': 'string'
                },
            ],
            'ToPort': 123,
            'UserIdGroupPairs': [
                {
                    'Description': 'string',
                    'GroupId': 'string',
                    'GroupName': 'string',
                    'PeeringStatus': 'string',
                    'UserId': 'string',
                    'VpcId': 'string',
                    'VpcPeeringConnectionId': 'string'
                },
            ]
        },
    ],
    IpProtocol='string',
    SourceSecurityGroupName='string',
    SourceSecurityGroupOwnerId='string',
    ToPort=123,
    DryRun=True|False
)
```

Parameters

*   **CidrIp** (_string_) -- The CIDR IP address range. You can't specify this parameter when specifying a source security group.
*   **FromPort** (_integer_) -- The start of port range for the TCP and UDP protocols, or an ICMP type number. For the ICMP type number, use -1 to specify all ICMP types.
*   **GroupName** (_string_) -- [EC2-Classic, default VPC] The name of the security group. You must specify either the security group ID or the security group name in the request.
*   **IpPermissions** (_list_) --

    The sets of IP permissions. You can't specify a source security group and a CIDR IP address range in the same set of permissions.

    *   _(dict) --
        Describes a set of permissions for a security group rule.

        *   **FromPort** _(integer) --     
            The start of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 type number. A value of -1 indicates all ICMP/ICMPv6 types. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **IpProtocol** _(string) --     
            The IP protocol name (tcp , udp , icmp , icmpv6 ) or number (see [Protocol Numbers](http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml) ).

            [VPC only] Use -1 to specify all protocols. When authorizing security group rules, specifying -1 or a protocol number other than tcp , udp , icmp , or icmpv6 allows traffic on all ports, regardless of any port range you specify. For tcp , udp , and icmp , you must specify a port range. For icmpv6 , the port range is optional; if you omit the port range, traffic for all types and codes is allowed.

        *   **IpRanges** _(list) --     
            The IPv4 ranges.

            *   _(dict) --         
                Describes an IPv4 range.

                *   **CidrIp** _(string) --             
                    The IPv4 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv4 address, use the /32 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv4 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **Ipv6Ranges** _(list) --     
            [VPC only] The IPv6 ranges.

            *   _(dict) --         
                [EC2-VPC only] Describes an IPv6 range.

                *   **CidrIpv6** _(string) --             
                    The IPv6 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv6 address, use the /128 prefix length.

                *   **Description** _(string) --             
                    A description for the security group rule that references this IPv6 address range.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

        *   **PrefixListIds** _(list) --     
            [VPC only] The prefix list IDs.

            *   _(dict) --         
                Describes a prefix list ID.

                *   **Description** _(string) --             
                    A description for the security group rule that references this prefix list ID.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **PrefixListId** _(string) --             
                    The ID of the prefix.

        *   **ToPort** _(integer) --     
            The end of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 code. A value of -1 indicates all ICMP/ICMPv6 codes. If you specify all ICMP/ICMPv6 types, you must specify all codes.

        *   **UserIdGroupPairs** _(list) --     
            The security group and AWS account ID pairs.

            *   _(dict) --         
                Describes a security group and AWS account ID pair.

                *   **Description** _(string) --             
                    A description for the security group rule that references this user ID group pair.

                    Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                *   **GroupId** _(string) --             
                    The ID of the security group.

                *   **GroupName** _(string) --             
                    The name of the security group. In a request, use this parameter for a security group in EC2-Classic or a default VPC only. For a security group in a nondefault VPC, use the security group ID.

                    For a referenced security group in another VPC, this value is not returned if the referenced security group is deleted.

                *   **PeeringStatus** _(string) --             
                    The status of a VPC peering connection, if applicable.

                *   **UserId** _(string) --             
                    The ID of an AWS account.

                    For a referenced security group in another VPC, the account ID of the referenced security group is returned in the response. If the referenced security group is deleted, this value is not returned.

                    [EC2-Classic] Required when adding or removing rules that reference a security group in another AWS account.

                *   **VpcId** _(string) --             
                    The ID of the VPC for the referenced security group, if applicable.

                *   **VpcPeeringConnectionId** _(string) --             
                    The ID of the VPC peering connection, if applicable.

*   **IpProtocol** (_string_) -- The IP protocol name (tcp , udp , icmp ) or number (see [Protocol Numbers](http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml) ). Use -1 to specify all.
*   **SourceSecurityGroupName**```
 (_string_) -- [EC2-Classic, default VPC] The name of the source security group. You can't specify this parameter in combination with the following parameters: the CIDR IP address range, the start of the port range, the IP protocol, and the end of the port range. For EC2-VPC, the source security group must be in the same VPC. To revoke a specific rule for an IP protocol and port range, use a set of IP permissions instead.
*   **SourceSecurityGroupOwnerId** (_string_) -- [EC2-Classic] The AWS account ID of t```
he source security group, if the source security group is in a different account. You can't specify this parameter in combination with the following parameters: the CIDR IP address range, the IP protocol, the start of the port range, and the end of the port range. To revoke a specific rule for an IP protocol and port range, use a set of IP permissions instead.
*   **ToPort** (_integer_) -- The end of port range for the TCP and UDP protocols, or an ICMP code number. For the ICMP code number, use -1 to specify all ICMP codes for the ICMP type.
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'Return': True|False,
    'UnknownIpPermissions': [
        {
            'FromPort': 123,
            'IpProtocol': 'string',
            'IpRanges': [
                {
                    'CidrIp': 'string',
                    'Description': 'string'
                },
            ],
            'Ipv6Ranges': [
                {
                    'CidrIpv6': 'string',
                    'Description': 'string'
                },
            ],
            'PrefixListIds': [
                {
                    'Description': 'string',
                    'PrefixListId': 'string'
                },
            ],
            'ToPort': 123,
            'UserIdGroupPairs': [
                {
                    'Description': 'string',
                    'GroupId': 'string',
                    'GroupName': 'string',
                    'PeeringStatus': 'string',
                    'UserId': 'string',
                    'VpcId': 'string',
                    'VpcPeeringConnectionId': 'string'
                },
            ]
        },
    ]
}
```


**Response Structure**

*   _(dict) --_

    *   **Return** _(boolean) --
        Returns true if the request succeeds; otherwise, returns an error.

    *   **UnknownIpPermissions** _(list) --
        The inbound rules that were unknown to the service. In some cases, unknownIpPermissionSet might be in a different format from the request parameter.

        *   _(dict) --     
            Describes a set of permissions for a security group rule.

            *   **FromPort** _(integer) --         
                The start of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 type number. A value of -1 indicates all ICMP/ICMPv6 types. If you specify all ICMP/ICMPv6 types, you must specify all codes.

            *   **IpProtocol** _(string) --         
                The IP protocol name (tcp , udp , icmp , icmpv6 ) or number (see [Protocol Numbers](http://www.iana.org/assignments/protocol-numbers/protocol-numbers.xhtml) ).

                [VPC only] Use -1 to specify all protocols. When authorizing security group rules, specifying -1 or a protocol number other than tcp , udp , icmp , or icmpv6 allows traffic on all ports, regardless of any port range you specify. For tcp , udp , and icmp , you must specify a port range. For icmpv6 , the port range is optional; if you omit the port range, traffic for all types and codes is allowed.

            *   **IpRanges** _(list) --         
                The IPv4 ranges.

                *   _(dict) --             
                    Describes an IPv4 range.

                    *   **CidrIp** _(string) --                 
                        The IPv4 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv4 address, use the /32 prefix length.

                    *   **Description** _(string) --                 
                        A description for the security group rule that references this IPv4 address range.

                        Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

            *   **Ipv6Ranges** _(list) --         
                [VPC only] The IPv6 ranges.

                *   _(dict) --             
                    [EC2-VPC only] Describes an IPv6 range.

                    *   **CidrIpv6** _(string) --                 
                        The IPv6 CIDR range. You can either specify a CIDR range or a source security group, not both. To specify a single IPv6 address, use the /128 prefix length.

                    *   **Description** _(string) --                 
                        A description for the security group rule that references this IPv6 address range.

                        Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

            *   **PrefixListIds** _(list) --         
                [VPC only] The prefix list IDs.

                *   _(dict) --             
                    Describes a prefix list ID.

                    *   **Description** _(string) --                 
                        A description for the security group rule that references this prefix list ID.

                        Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                    *   **PrefixListId** _(string) --                 
                        The ID of the prefix.

            *   **ToPort** _(integer) --         
                The end of port range for the TCP and UDP protocols, or an ICMP/ICMPv6 code. A value of -1 indicates all ICMP/ICMPv6 codes. If you specify all ICMP/ICMPv6 types, you must specify all codes.

            *   **UserIdGroupPairs** _(list) --         
                The security group and AWS account ID pairs.

                *   _(dict) --             
                    Describes a security group and AWS account ID pair.

                    *   **Description** _(string) --                 
                        A description for the security group rule that references this user ID group pair.

                        Constraints: Up to 255 characters in length. Allowed characters are a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=;{}!$*

                    *   **GroupId** _(string) --                 
                        The ID of the security group.

                    *   **GroupName** _(string) --                 
                        The name of the security group. In a request, use this parameter for a security group in EC2-Classic or a default VPC only. For a security group in a nondefault VPC, use the security group ID.

                        For a referenced security group in another VPC, this value is not returned if the referenced security group is deleted.

                    *   **PeeringStatus** _(string) --                 
                        The status of a VPC peering connection, if applicable.

                    *   **UserId** _(string) --                 
                        The ID of an AWS account.

                        For a referenced security group in another VPC, the account ID of the referenced security group is returned in the response. If the referenced security group is deleted, this value is not returned.

                        [EC2-Classic] Required when adding or removing rules that reference a security group in another AWS account.

                    *   **VpcId** _(string) --                 
                        The ID of the VPC for the referenced security group, if applicable.

                    *   **VpcPeeringConnectionId** _(string) --                 
                        The ID of the VPC peering connection, if applicable.


[Snapshot](#id1252)
--------------------------------------------------------------

_class_ EC2.Snapshot(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) Snapshot:

import boto3

ec2 = boto3.resource('ec2')
snapshot = ec2.Snapshot('id')
```

Parameters

**id** (_string_) -- The Snapshot's id identifier. This **must** be set.

available identifiers:

*   [id](#EC2.Snapshot.id "EC2.Snapshot.id")

available attributes:

*   [data_encryption_key_id](#EC2.Snapshot.data_encryption_key_id "EC2.Snapshot.data_encryption_key_id")
*   [description](#EC2.Snapshot.description "EC2.Snapshot.description")
*   [encrypted](#EC2.Snapshot.encrypted "EC2.Snapshot.encrypted")
*   [kms_key_id](#EC2.Snapshot.kms_key_id "EC2.Snapshot.kms_key_id")
*   [owner_alias](#EC2.Snapshot.owner_alias "EC2.Snapshot.owner_alias")
*   [owner_id](#EC2.Snapshot.owner_id "EC2.Snapshot.owner_id")
*   [progress](#EC2.Snapshot.progress "EC2.Snapshot.progress")
*   [snapshot_id](#EC2.Snapshot.snapshot_id "EC2.Snapshot.snapshot_id")
*   [start_time](#EC2.Snapshot.start_time "EC2.Snapshot.start_time")
*   [state](#EC2.Snapshot.state "EC2.Snapshot.state")
*   [state_message](#EC2.Snapshot.state_message "EC2.Snapshot.state_message")
*   [tags](#EC2.Snapshot.tags "EC2.Snapshot.tags")
*   [volume_id](#EC2.Snapshot.volume_id "EC2.Snapshot.volume_id")
*   [volume_size](#EC2.Snapshot.volume_size "EC2.Snapshot.volume_size")

available references:

*   [volume](#EC2.Snapshot.volume "EC2.Snapshot.volume")

available actions:

*   [copy()](#EC2.Snapshot.copy "EC2.Snapshot.copy")
*   [create_tags()](#EC2.Snapshot.create_tags "EC2.Snapshot.create_tags")
*   [delete()](#EC2.Snapshot.delete "EC2.Snapshot.delete")
*   [describe_attribute()](#EC2.Snapshot.describe_attribute "EC2.Snapshot.describe_attribute")
*   [get_available_subresources()](#EC2.Snapshot.get_available_subresources "EC2.Snapshot.get_available_subresources")
*   [load()](#EC2.Snapshot.load "EC2.Snapshot.load")
*   [modify_attribute()](#EC2.Snapshot.modify_attribute "EC2.Snapshot.modify_attribute")
*   [reload()](#EC2.Snapshot.reload "EC2.Snapshot.reload")
*   [reset_attribute()](#EC2.Snapshot.reset_attribute "EC2.Snapshot.reset_attribute")

available waiters:

*   [wait_until_completed()](#EC2.Snapshot.wait_until_completed "EC2.Snapshot.wait_until_completed")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

id

_(string)_ The Snapshot's id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.Snapshot.load "EC2.Snapshot.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

data_encryption_key_id

*   _(string) --_

    The data encryption key identifier for the snapshot. This value is a unique identifier that corresponds to the data encryption key that was used to encrypt the original volume or snapshot copy. Because data encryption keys are inherited by volumes created from snapshots, and vice versa, if snapshots share the same data encryption key identifier, then they belong to the same volume/snapshot lineage. This parameter is only returned by DescribeSnapshots .


description

*   _(string) --_

    The description for the snapshot.


encrypted

*   _(boolean) --_

    Indicates whether the snapshot is encrypted.


kms_key_id

*   _(string) --_

    The Amazon Resource Name (ARN) of the AWS Key Management Service (AWS KMS) customer master key (CMK) that was used to protect the volume encryption key for the parent volume.


owner_alias

*   _(string) --_

    The AWS owner alias, from an Amazon-maintained list (amazon ). This is not the user-configured AWS account alias set using the IAM console.


owner_id

*   _(string) --_

    The AWS account ID of the EBS snapshot owner.


progress

*   _(string) --_

    The progress of the snapshot, as a percentage.


snapshot_id

*   _(string) --_

    The ID of the snapshot. Each snapshot receives a unique identifier when it is created.


start_time

*   _(datetime) --_

    The time stamp when the snapshot was initiated.


state

*   _(string) --_

    The snapshot state.


state_message

*   _(string) --_

    Encrypted Amazon EBS snapshots are copied asynchronously. If a snapshot copy operation fails (for example, if the proper AWS Key Management Service (AWS KMS) permissions are not obtained) this field displays error state details to help you diagnose why the error occurred. This parameter is only returned by DescribeSnapshots .


tags

*   _(list) --_

    Any tags assigned to the snapshot.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


volume_id

*   _(string) --_

    The ID of the volume that was used to create the snapshot. Snapshots created by the CopySnapshot action have an arbitrary volume ID that should not be used for any purpose.


volume_size

*   _(integer) --_

    The size of the volume, in GiB.


References

References are related resource instances that have a belongs-to relationship. For more information about references refer to the [_Resources Introduction Guide_](../../guide/resources.html#references-intro).

volume

(Volume) The related volume if set, otherwise None.

Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

copy(_**kwargs_)

Copies a point-in-time snapshot of an EBS volume and stores it in Amazon S3. You can copy the snapshot within the same Region or from one Region to another. You can use the snapshot to create EBS volumes or Amazon Machine Images (AMIs).

Copies of encrypted EBS snapshots remain encrypted. Copies of unencrypted snapshots remain unencrypted, unless you enable encryption for the snapshot copy operation. By default, encrypted snapshot copies use the default AWS Key Management Service (AWS KMS) customer master key (CMK); however, you can specify a different CMK.

To copy an encrypted snapshot that has been shared from another account, you must have permissions for the CMK used to encrypt the snapshot.

Snapshots created by copying another snapshot have an arbitrary volume ID that should not be used for any purpose.

For more information, see [Copying an Amazon EBS snapshot](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-copy-snapshot.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = snapshot.copy(
    Description='string',
    Encrypted=True|False,
    KmsKeyId='string',
    SourceRegion='string',
    TagSpecifications=[
        {
            'ResourceType': 'client-vpn-endpoint'|'customer-gateway'|'dedicated-host'|'dhcp-options'|'egress-only-internet-gateway'|'elastic-ip'|'elastic-gpu'|'export-image-task'|'export-instance-task'|'fleet'|'fpga-image'|'host-reservation'|'image'|'import-image-task'|'import-snapshot-task'|'instance'|'internet-gateway'|'key-pair'|'launch-template'|'local-gateway-route-table-vpc-association'|'natgateway'|'network-acl'|'network-interface'|'network-insights-analysis'|'network-insights-path'|'placement-group'|'reserved-instances'|'route-table'|'security-group'|'snapshot'|'spot-fleet-request'|'spot-instances-request'|'subnet'|'traffic-mirror-filter'|'traffic-mirror-session'|'traffic-mirror-target'|'transit-gateway'|'transit-gateway-attachment'|'transit-gateway-connect-peer'|'transit-gateway-multicast-domain'|'transit-gateway-route-table'|'volume'|'vpc'|'vpc-peering-connection'|'vpn-connection'|'vpn-gateway'|'vpc-flow-log',
            'Tags': [
                {
                    'Key': 'string',
                    'Value': 'string'
                },
            ]
        },
    ],
    DryRun=True|False
)
```

Parameters

*   **Description** (_string_) -- A description for the EBS snapshot.
*   **DestinationRegion** (_string_) --

    The destination Region to use in the PresignedUrl parameter of a snapshot copy operation. This parameter is only valid for specifying the destination Region in a PresignedUrl parameter, where it is required.

    The snapshot copy is sent to the regional endpoint that you sent the HTTP request to (for example, ec2.us-east-1.amazonaws.com ). With the AWS CLI, this is specified using the --region parameter or the default Region in your AWS configuration file.

    > Please note that this parameter is automatically populated if it is not provided. Including this parameter is not required

*   **Encrypted** (_boolean_) -- To encrypt a copy of an unencrypted snapshot if encryption by default is not enabled, enable encryption using this parameter. Otherwise, omit this parameter. Encrypted snapshots are encrypted, even if you omit this parameter and encryption by default is not enabled. You cannot set this parameter to false. For more information, see [Amazon EBS encryption](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EBSEncryption.html) in the _Amazon Elastic Compute Cloud User Guide_ .
*   **KmsKeyId** (_string_) --

    The identifier of the AWS Key Management Service (AWS KMS) customer master key (CMK) to use for Amazon EBS encryption. If this parameter is not specified, your AWS managed CMK for EBS is used. If KmsKeyId is specified, the encrypted state must be true .

    You can specify the CMK using any of the following:

    *   Key ID. For example, 1234abcd-12ab-34cd-56ef-1234567890ab.
    *   Key alias. For example, alias/ExampleAlias.
    *   Key ARN. For example, arn:aws:kms:us-east-1:012345678910:key/1234abcd-12ab-34cd-56ef-1234567890ab.
    *   Alias ARN. For example, arn:aws:kms:us-east-1:012345678910:alias/ExampleAlias.

    AWS authenticates the CMK asynchronously. Therefore, if you specify an ID, alias, or ARN that is not valid, the action can appear to complete, but eventually fails.

*   **PresignedUrl** (_string_) --

    When you copy an encrypted source snapshot using the Amazon EC2 Query API, you must supply a pre-signed URL. This parameter is optional for unencrypted snapshots. For more information, see [Query requests](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/Query-Requests.html) .

    The PresignedUrl s```
    hould use the snapshot source endpoint, the CopySnapshot action, and include the SourceRegion , SourceSnapshotId , and DestinationRegion parameters. The PresignedUrl must be signed using AWS Signature Version 4. Because EBS snapshots a```
    re stored in Amazon S3, the signing algorithm for this parameter uses the same logic that is described in [Authenticating Requests: Using Query Parameters (AWS Signature Version 4)](https://docs.aws.amazon.com/AmazonS3/latest/API/sigv4-query-string-auth.html) in the _Amazon Simple Storage Service API Reference_ . An invalid or improperly signed PresignedUrl will cause the copy operation to fail asynchronously, and the snapshot will move to an error state.

    > Please note that this parameter is automatically populated if it is not provided. Including this parameter is not required

*   **SourceRegion** (_string_) -- **[REQUIRED]** The ID of the Region that contains the snapshot to be copied.

*   **TagSpecifications** (_list_) --

    The tags to apply to the new snapshot.

    *   _(dict) --
        The tags to apply to a resource when the resource is being created.

        *   **ResourceType** _(string) --     
            The type of resource to tag. Currently, the resource types that support tagging on creation are: capacity-reservation | carrier-gateway | client-vpn-endpoint | customer-gateway | dedicated-host | dhcp-options | egress-only-internet-gateway | elastic-ip | elastic-gpu | export-image-task | export-instance-task | fleet | fpga-image | host-reservation | image | import-image-task | import-snapshot-task | instance | internet-gateway | ipv4pool-ec2 | ipv6pool-ec2 | key-pair | launch-template | local-gateway-route-table-vpc-association | placement-group | prefix-list | natgateway | network-acl | network-interface | reserved-instances [|](#id1076)route-table | security-group | snapshot | spot-fleet-request | spot-instances-request | snapshot | subnet | traffic-mirror-filter | traffic-mirror-session | traffic-mirror-target | transit-gateway | transit-gateway-attachment | transit-gateway-multicast-domain | transit-gateway-route-table | volume [|](#id1078)vpc | vpc-peering-connection | vpc-endpoint (for interface and gateway endpoints) | vpc-endpoint-service (for AWS PrivateLink) | vpc-flow-log | vpn-connection | vpn-gateway .

            To tag a resource after it has been created, see [CreateTags](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateTags.html) .

        *   **Tags** _(list) --     
            The tags to apply to the resource.

            *   _(dict) --         
                Describes a tag.

                *   **Key** _(string) --             
                    The key of the tag.

                    Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

                *   **Value** _(string) --             
                    The value of the tag.

                    Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'SnapshotId': 'string',
    'Tags': [
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
}
```


**Response Structure**

*   _(dict) --_

    *   **SnapshotId** _(string) --
        The ID of the new snapshot.

    *   **Tags** _(list) --
        Any tags applied to the new snapshot.

        *   _(dict) --     
            Describes a tag.

            *   **Key** _(string) --         
                The key of the tag.

                Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

            *   **Value** _(string) --         
                The value of the tag.

                Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


create_tags(_**kwargs_)

Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources. When you specify an existing tag key, the value is overwritten with the new value. Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
tag = snapshot.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Tags** (_list_) -- **[REQUIRED]** The tags. The value parameter is required, but if you don't want the tag to have a value, specify the parameter with no value, and we set the value to an empty string.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: list(ec2.Tag)
- A list of Tag resources

delete(_**kwargs_)

Deletes the specified snapshot.

When you make periodic snapshots of a volume, the snapshots are incremental, and only the blocks on the device that have changed since your last snapshot are saved in the new snapshot. When you delete a snapshot, only the data not needed for any other snapshot is removed. So regardless of which prior snapshots have been deleted, all active snapshots will have access to all the information needed to restore the volume.

You cannot delete a snapshot of the root device of an EBS volume used by a registered AMI. You must first de-register the AMI before you can delete the snapshot.

For more information, see [Deleting an Amazon EBS snapshot](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-deleting-snapshot.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = snapshot.delete(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

describe_attribute(_**kwargs_)

Describes the specified attribute of the specified snapshot. You can specify only one attribute at a time.

For more information about EBS snapshots, see [Amazon EBS snapshots](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EBSSnapshots.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = snapshot.describe_attribute(
    Attribute='productCodes'|'createVolumePermission',
    DryRun=True|False
)
```

Parameters

*   **Attribute** (_string_) -- **[REQUIRED]** The snapshot attribute you would like to view.

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'CreateVolumePermissions': [
        {
            'Group': 'all',
            'UserId': 'string'
        },
    ],
    'ProductCodes': [
        {
            'ProductCodeId': 'string',
            'ProductCodeType': 'devpay'|'marketplace'
        },
    ],
    'SnapshotId': 'string'
}
```


**Response Structure**

*   _(dict) --_

    *   **CreateVolumePermissions** _(list) --
        The users and groups that have the permissions for creating volumes from the snapshot.

        *   _(dict) --     
            Describes the user or group to be added or removed from the list of create volume permissions for a volume.

            *   **Group** _(string) --         
                The group to be added or removed. The possible value is all .

            *   **UserId** _(string) --         
                The AWS account ID to be added or removed.

    *   **ProductCodes** _(list) --
        The product codes.

        *   _(dict) --     
            Describes a product code.

            *   **ProductCodeId** _(string) --         
                The product code.

            *   **ProductCodeType** _(string) --         
                The type of product code.

    *   **SnapshotId** _(string) --
        The ID of the EBS snapshot.


get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_snapshots()](#EC2.Client.describe_snapshots "EC2.Client.describe_snapshots") to update the attributes of the Snapshot resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
snapshot.load()
- None

modify_attribute(_**kwargs_)

Adds or removes permission settings for the specified snapshot. You may add or remove specified AWS account IDs from a snapshot's list of create volume permissions, but you cannot do both in a single operation. If you need to both add and remove account IDs for a snapshot, you must use multiple operations. You can make up to 500 modifications to a snapshot in a single operation.

Encrypted snapshots and snapshots with AWS Marketplace product codes cannot be made public. Snapshots encrypted with your default CMK cannot be shared with other accounts.

For more information about modifying snapshot permissions, see [Sharing snapshots](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-modifying-snapshot-permissions.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = snapshot.modify_attribute(
    Attribute='productCodes'|'createVolumePermission',
    CreateVolumePermission={
        'Add': [
            {
                'Group': 'all',
                'UserId': 'string'
            },
        ],
        'Remove': [
            {
                'Group': 'all',
                'UserId': 'string'
            },
        ]
    },
    GroupNames=[
        'string',
    ],
    OperationType='add'|'remove',
    UserIds=[
        'string',
    ],
    DryRun=True|False
)
```

Parameters

*   **Attribute** (_string_) -- The snapshot attribute to modify. Only volume creation permissions can be modified.
*   **CreateVolumePermission** (_dict_) --

    A JSON representation of the snapshot attribute modification.

    *   **Add** _(list) --
        Adds the specified AWS account ID or group to the list.

        *   _(dict) --     
            Describes the user or group to be added or removed from the list of create volume permissions for a volume.

            *   **Group** _(string) --         
                The group to be added or removed. The possible value is all .

            *   **UserId** _(string) --         
                The AWS account ID to be added or removed.

    *   **Remove** _(list) --
        Removes the specified AWS account ID or group from the list.

        *   _(dict) --     
            Describes the user or group to be added or removed from the list of create volume permissions for a volume.

            *   **Group** _(string) --         
                The group to be added or removed. The possible value is all .

            *   **UserId** _(string) --         
                The AWS account ID to be added or removed.

*   **GroupNames** (_list_) --

    The group to modify for the snapshot.

    *   _(string) --_
*   **OperationType** (_string_) -- The type of operation to perform to the attribute.
*   **UserIds** (_list_) --

    The account ID to modify for the snapshot.

    *   _(string) --_
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

reload()

Calls [EC2.Client.describe_snapshots()](#EC2.Client.describe_snapshots "EC2.Client.describe_snapshots") to update the attributes of the Snapshot resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
snapshot.reload()
- None

reset_attribute(_**kwargs_)

Resets permission settings for the specified snapshot.

For more information about modifying snapshot permissions, see [Sharing snapshots](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-modifying-snapshot-permissions.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = snapshot.reset_attribute(
    Attribute='productCodes'|'createVolumePermission',
    DryRun=True|False
)
```

Parameters

*   **Attribute** (_string_) -- **[REQUIRED]** The attribute to reset. Currently, only the attribute for permission to create volumes can be reset.

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

Waiters

Waiters provide an interface to wait for a resource to reach a specific state. For more information about waiters refer to the [_Resources Introduction Guide_](../../guide/resources.html#waiters-intro).

wait_until_completed(_**kwargs_)

Waits until this Snapshot is completed. This method calls EC2.Waiter.snapshot_completed.wait() which polls. [EC2.Client.describe_snapshots()](#EC2.Client.describe_snapshots "EC2.Client.describe_snapshots") every 15 seconds until a successful state is reached. An error is returned after 40 failed checks.


**Request Syntax**

```py
snapshot.wait_until_completed(
    Filters=[
        {
            'Name': 'string',
            'Values': [
                'string',
            ]
        },
    ],
    MaxResults=123,
    NextToken='string',
    OwnerIds=[
        'string',
    ],
    RestorableByUserIds=[
        'string',
    ],
    DryRun=True|False
)
```

Parameters

*   **Filters** (_list_) --

    The filters.

    *   description - A description of the snapshot.
    *   encrypted - Indicates whether the snapshot is encrypted (true | false )
    *   owner-alias - The owner alias, from an Amazon-maintained list (amazon ). This is not the user-configured AWS account alias set using the IAM console. We recommend that you use the related parameter instead of this filter.
    *   owner-id - The AWS account ID of the owner. We recommend that you use the related parameter instead of this filter.
    *   progress - The progress of the snapshot, as a percentage (for example, 80%).
    *   snapshot-id - The snapshot ID.
    *   start-time - The time stamp when the snapshot was initiated.
    *   status - The status of the snapshot (pending | completed | error ).
    *   tag :<key> - The key/value combination of a tag assigned to the resource. Use the tag key in the filter name and the tag value as the filter value. For example, to find all resources that have a tag with the key Owner and the value TeamA , specify tag:Owner for the filter name and TeamA for the filter value.
    *   tag-key - The key of a tag assigned to the resource. Use this filter to find all resources assigned a tag with a specific key, regardless of the tag value.
    *   volume-id - The ID of the volume the snapshot is for.
    *   volume-size - The size of the volume, in GiB.

    *   _(dict) --
        A filter name and value pair that is used to return a more specific list of results from a describe operation. Filters can be used to match a set of resources by specific criteria, such as tags, attributes, or IDs. The filters supported by a describe operation are documented with the describe operation. For example:

        *   DescribeAvailabilityZones
        *   DescribeImages
        *   DescribeInstances
        *   DescribeKeyPairs
        *   DescribeSecurityGroups
        *   DescribeSnapshots
        *   DescribeSubnets
        *   DescribeTags
        *   DescribeVolumes
        *   DescribeVpcs

        *   **Name** _(string) --     
            The name of the filter. Filter names are case-sensitive.

        *   **Values** _(list) --     
            The filter values. Filter values are case-sensitive.

            *   _(string) --_
*   **MaxResults** (_integer_) -- The maximum number of snapshot results returned by DescribeSnapshots in paginated output. When this parameter is used, DescribeSnapshots only returns MaxResults results in a single page along with a NextToken response element. The remaining results of the initial request can be seen by sending another DescribeSnapshots request with the returned NextToken value. This value can be between 5 and 1,000; if MaxResults is given a value larger than 1,000, only 1,000 results are returned. If this parameter is not used, then DescribeSnapshots returns all results. You cannot specify this parameter and the snapshot IDs parameter in the same request.
*   **NextToken** (_string_) -- The NextToken value returned from a previous paginated DescribeSnapshots request where MaxResults was used and the results exceeded the value of that parameter. Pagination continues from the end of the previous results that returned the NextToken value. This value is null when there are no more results to return.
*   **OwnerIds** (_list_) --

    Scopes the results to snapshots with the specified owners. You can specify a combination of AWS account IDs, self , and amazon .

    *   _(string) --_
*   **RestorableByUserIds** (_list_) --

    The IDs of the AWS accounts that can create volumes from the snapshot.

    *   _(string) --_
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

[Subnet](#id1253)
----------------------------------------------------------

_class_ EC2.Subnet(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) Subnet:

import boto3

ec2 = boto3.resource('ec2')
subnet = ec2.Subnet('id')
```

Parameters

**id** (_string_) -- The Subnet's id identifier. This **must** be set.

available identifiers:

*   [id](#EC2.Subnet.id "EC2.Subnet.id")

available attributes:

*   [assign_ipv6_address_on_creation](#EC2.Subnet.assign_ipv6_address_on_creation "EC2.Subnet.assign_ipv6_address_on_creation")
*   [availability_zone](#EC2.Subnet.availability_zone "EC2.Subnet.availability_zone")
*   [availability_zone_id](#EC2.Subnet.availability_zone_id "EC2.Subnet.availability_zone_id")
*   [available_ip_address_count](#EC2.Subnet.available_ip_address_count "EC2.Subnet.available_ip_address_count")
*   [cidr_block](#EC2.Subnet.cidr_block "EC2.Subnet.cidr_block")
*   [customer_owned_ipv4_pool](#EC2.Subnet.customer_owned_ipv4_pool "EC2.Subnet.customer_owned_ipv4_pool")
*   [default_for_az](#EC2.Subnet.default_for_az "EC2.Subnet.default_for_az")
*   [ipv6_cidr_block_association_set](#EC2.Subnet.ipv6_cidr_block_association_set "EC2.Subnet.ipv6_cidr_block_association_set")
*   [map_customer_owned_ip_on_launch](#EC2.Subnet.map_customer_owned_ip_on_launch "EC2.Subnet.map_customer_owned_ip_on_launch")
*   [map_public_ip_on_launch](#EC2.Subnet.map_public_ip_on_launch "EC2.Subnet.map_public_ip_on_launch")
*   [outpost_arn](#EC2.Subnet.outpost_arn "EC2.Subnet.outpost_arn")
*   [owner_id](#EC2.Subnet.owner_id "EC2.Subnet.owner_id")
*   [state](#EC2.Subnet.state "EC2.Subnet.state")
*   [subnet_arn](#EC2.Subnet.subnet_arn "EC2.Subnet.subnet_arn")
*   [subnet_id](#EC2.Subnet.subnet_id "EC2.Subnet.subnet_id")
*   [tags](#EC2.Subnet.tags "EC2.Subnet.tags")
*   [vpc_id](#EC2.Subnet.vpc_id "EC2.Subnet.vpc_id")

available references:

*   [vpc](#EC2.Subnet.vpc "EC2.Subnet.vpc")

available actions:

*   [create_instances()](#EC2.Subnet.create_instances "EC2.Subnet.create_instances")
*   [create_network_interface()](#EC2.Subnet.create_network_interface "EC2.Subnet.create_network_interface")
*   [create_tags()](#EC2.Subnet.create_tags "EC2.Subnet.create_tags")
*   [delete()](#EC2.Subnet.delete "EC2.Subnet.delete")
*   [get_available_subresources()](#EC2.Subnet.get_available_subresources "EC2.Subnet.get_available_subresources")
*   [load()](#EC2.Subnet.load "EC2.Subnet.load")
*   [reload()](#EC2.Subnet.reload "EC2.Subnet.reload")

available collections:

*   [instances](#EC2.Subnet.instances "EC2.Subnet.instances")
*   [network_interfaces](#EC2.Subnet.network_interfaces "EC2.Subnet.network_interfaces")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

id

_(string)_ The Subnet's id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.Subnet.load "EC2.Subnet.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

assign_ipv6_address_on_creation

*   _(boolean) --_

    Indicates whether a network interface created in this subnet (including a network interface created by RunInstances ) receives an IPv6 address.


availability_zone

*   _(string) --_

    The Availability Zone of the subnet.


availability_zone_id

*   _(string) --_

    The AZ ID of the subnet.


available_ip_address_count

*   _(integer) --_

    The number of unused private IPv4 addresses in the subnet. The IPv4 addresses for any stopped instances are considered unavailable.


cidr_block

*   _(string) --_

    The IPv4 CIDR block assigned to the subnet.


customer_owned_ipv4_pool

*   _(string) --_

    The customer-owned IPv4 address pool associated with the subnet.


default_for_az

*   _(boolean) --_

    Indicates whether this is the default subnet for the Availability Zone.


ipv6_cidr_block_association_set

*   _(list) --_

    Information about the IPv6 CIDR blocks associated with the subnet.

    *   _(dict) --
        Describes an IPv6 CIDR block associated with a subnet.

        *   **AssociationId** _(string) --     
            The association ID for the CIDR block.

        *   **Ipv6CidrBlock** _(string) --     
            The IPv6 CIDR block.

        *   **Ipv6CidrBlockState** _(dict) --     
            Information about the state of the CIDR block.

            *   **State** _(string) --         
                The state of a CIDR block.

            *   **StatusMessage** _(string) --         
                A message about the status of the CIDR block, if applicable.


map_customer_owned_ip_on_launch

*   _(boolean) --_

    Indicates whether a network interface created in this subnet (including a network interface created by RunInstances ) receives a customer-owned IPv4 address.


map_public_ip_on_launch

*   _(boolean) --_

    Indicates whether instances launched in this subnet receive a public IPv4 address.


outpost_arn

*   _(string) --_

    The Amazon Resource Name (ARN) of the Outpost.


owner_id

*   _(string) --_

    The ID of the AWS account that owns the subnet.


state

*   _(string) --_

    The current state of the subnet.


subnet_arn

*   _(string) --_

    The Amazon Resource Name (ARN) of the subnet.


subnet_id

*   _(string) --_

    The ID of the subnet.


tags

*   _(list) --_

    Any tags assigned to the subnet.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


vpc_id

*   _(string) --_

    The ID of the VPC the subnet is in.


References

References are related resource instances that have a belongs-to relationship. For more information about references refer to the [_Resources Introduction Guide_](../../guide/resources.html#references-intro).

vpc

(Vpc) The related vpc if set, otherwise None.

Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

create_instances(_**kwargs_)

Launches the specified number of instances using an AMI for which you have permissions.

You can specify a number of options, or leave the default options. The following rules apply:

*   [EC2-VPC] If you don't specify a subnet ID, we choose a default subnet from your default VPC for you. If you don't have a default VPC, you must specify a subnet ID in the request.
*   [EC2-Classic] If don't specify an Availability Zone, we choose one for you.
*   Some instance types must be launched into a VPC. If you do not have a default VPC, or if you do not specify a subnet ID, the request fails. For more information, see [Instance types available only in a VPC](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-vpc.html#vpc-only-instance-types) .
*   [EC2-VPC] All instances have a network interface with a primary private IPv4 address. If you don't specify this address, we choose one from the IPv4 range of your subnet.
*   Not all instance types support IPv6 addresses. For more information, see [Instance types](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/instance-types.html) .
*   If you don't specify a security group ID, we use the default security group. For more information, see [Security groups](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-network-security.html) .
*   If any of the AMIs have a product code attached for which the user has not subscribed, the request fails.

You ```
can create a [launch template](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-launch-templates.html) , which is a resource that contains the par```
ameters to launch an instance. When you launch an instance using RunInstances , you can specify the launch template instead of specifying the launch parameters.

To ensure faster instance launches, break up large requests into smaller batches. For example, create five separate launch requests for 100 instances each instead of one launch request for 500 instances.

An instance is ready for you to use when it's in the running state. You can check the state of your instance using DescribeInstances . You can tag instances and EBS volumes during launch, after launch, or both. For more information, see CreateTags and [Tagging your Amazon EC2 resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) .

Linux instances have access to the public key of the key pair at boot. You can use this key to provide secure access to the instance. Amazon EC2 public images use this feature to provide secure access without passwords. For more information, see [Key pairs](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-key-pairs.html) in the _Amazon Elastic Compute Cloud User Guide_ .

For troubleshooting, see [What to do if an instance immediately terminates](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_InstanceStraightToTerminated.html) , and [Troubleshooting connecting to your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/TroubleshootingInstancesConnecting.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
instance = subnet.create_instances(
    BlockDeviceMappings=[
        {
            'DeviceName': 'string',
            'VirtualName': 'string',
            'Ebs': {
                'DeleteOnTermination': True|False,
                'Iops': 123,
                'SnapshotId': 'string',
                'VolumeSize': 123,
                'VolumeType': 'standard'|'io1'|'io2'|'gp2'|'sc1'|'st1'|'gp3',
                'KmsKeyId': 'string',
                'Throughput': 123,
                'Encrypted': True|False
            },
            'NoDevice': 'string'
        },
    ],
    ImageId='string',
    InstanceType='t1.micro'|'t2.nano'|'t2.micro'|'t2.small'|'t2.medium'|'t2.large'|'t2.xlarge'|'t2.2xlarge'|'t3.nano'|'t3.micro'|'t3.small'|'t3.medium'|'t3.large'|'t3.xlarge'|'t3.2xlarge'|'t3a.nano'|'t3a.micro'|'t3a.small'|'t3a.medium'|'t3a.large'|'t3a.xlarge'|'t3a.2xlarge'|'t4g.nano'|'t4g.micro'|'t4g.small'|'t4g.medium'|'t4g.large'|'t4g.xlarge'|'t4g.2xlarge'|'m1.small'|'m1.medium'|'m1.large'|'m1.xlarge'|'m3.medium'|'m3.large'|'m3.xlarge'|'m3.2xlarge'|'m4.large'|'m4.xlarge'|'m4.2xlarge'|'m4.4xlarge'|'m4.10xlarge'|'m4.16xlarge'|'m2.xlarge'|'m2.2xlarge'|'m2.4xlarge'|'cr1.8xlarge'|'r3.large'|'r3.xlarge'|'r3.2xlarge'|'r3.4xlarge'|'r3.8xlarge'|'r4.large'|'r4.xlarge'|'r4.2xlarge'|'r4.4xlarge'|'r4.8xlarge'|'r4.16xlarge'|'r5.large'|'r5.xlarge'|'r5.2xlarge'|'r5.4xlarge'|'r5.8xlarge'|'r5.12xlarge'|'r5.16xlarge'|'r5.24xlarge'|'r5.metal'|'r5a.large'|'r5a.xlarge'|'r5a.2xlarge'|'r5a.4xlarge'|'r5a.8xlarge'|'r5a.12xlarge'|'r5a.16xlarge'|'r5a.24xlarge'|'r5b.large'|'r5b.xlarge'|'r5b.2xlarge'|'r5b.4xlarge'|'r5b.8xlarge'|'r5b.12xlarge'|'r5b.16xlarge'|'r5b.24xlarge'|'r5b.metal'|'r5d.large'|'r5d.xlarge'|'r5d.2xlarge'|'r5d.4xlarge'|'r5d.8xlarge'|'r5d.12xlarge'|'r5d.16xlarge'|'r5d.24xlarge'|'r5d.metal'|'r5ad.large'|'r5ad.xlarge'|'r5ad.2xlarge'|'r5ad.4xlarge'|'r5ad.8xlarge'|'r5ad.12xlarge'|'r5ad.16xlarge'|'r5ad.24xlarge'|'r6g.metal'|'r6g.medium'|'r6g.large'|'r6g.xlarge'|'r6g.2xlarge'|'r6g.4xlarge'|'r6g.8xlarge'|'r6g.12xlarge'|'r6g.16xlarge'|'r6gd.metal'|'r6gd.medium'|'r6gd.large'|'r6gd.xlarge'|'r6gd.2xlarge'|'r6gd.4xlarge'|'r6gd.8xlarge'|'r6gd.12xlarge'|'r6gd.16xlarge'|'x1.16xlarge'|'x1.32xlarge'|'x1e.xlarge'|'x1e.2xlarge'|'x1e.4xlarge'|'x1e.8xlarge'|'x1e.16xlarge'|'x1e.32xlarge'|'i2.xlarge'|'i2.2xlarge'|'i2.4xlarge'|'i2.8xlarge'|'i3.large'|'i3.xlarge'|'i3.2xlarge'|'i3.4xlarge'|'i3.8xlarge'|'i3.16xlarge'|'i3.metal'|'i3en.large'|'i3en.xlarge'|'i3en.2xlarge'|'i3en.3xlarge'|'i3en.6xlarge'|'i3en.12xlarge'|'i3en.24xlarge'|'i3en.metal'|'hi1.4xlarge'|'hs1.8xlarge'|'c1.medium'|'c1.xlarge'|'c3.large'|'c3.xlarge'|'c3.2xlarge'|'c3.4xlarge'|'c3.8xlarge'|'c4.large'|'c4.xlarge'|'c4.2xlarge'|'c4.4xlarge'|'c4.8xlarge'|'c5.large'|'c5.xlarge'|'c5.2xlarge'|'c5.4xlarge'|'c5.9xlarge'|'c5.12xlarge'|'c5.18xlarge'|'c5.24xlarge'|'c5.metal'|'c5a.large'|'c5a.xlarge'|'c5a.2xlarge'|'c5a.4xlarge'|'c5a.8xlarge'|'c5a.12xlarge'|'c5a.16xlarge'|'c5a.24xlarge'|'c5ad.large'|'c5ad.xlarge'|'c5ad.2xlarge'|'c5ad.4xlarge'|'c5ad.8xlarge'|'c5ad.12xlarge'|'c5ad.16xlarge'|'c5ad.24xlarge'|'c5d.large'|'c5d.xlarge'|'c5d.2xlarge'|'c5d.4xlarge'|'c5d.9xlarge'|'c5d.12xlarge'|'c5d.18xlarge'|'c5d.24xlarge'|'c5d.metal'|'c5n.large'|'c5n.xlarge'|'c5n.2xlarge'|'c5n.4xlarge'|'c5n.9xlarge'|'c5n.18xlarge'|'c5n.metal'|'c6g.metal'|'c6g.medium'|'c6g.large'|'c6g.xlarge'|'c6g.2xlarge'|'c6g.4xlarge'|'c6g.8xlarge'|'c6g.12xlarge'|'c6g.16xlarge'|'c6gd.metal'|'c6gd.medium'|'c6gd.large'|'c6gd.xlarge'|'c6gd.2xlarge'|'c6gd.4xlarge'|'c6gd.8xlarge'|'c6gd.12xlarge'|'c6gd.16xlarge'|'c6gn.medium'|'c6gn.large'|'c6gn.xlarge'|'c6gn.2xlarge'|'c6gn.4xlarge'|'c6gn.8xlarge'|'c6gn.12xlarge'|'c6gn.16xlarge'|'cc1.4xlarge'|'cc2.8xlarge'|'g2.2xlarge'|'g2.8xlarge'|'g3.4xlarge'|'g3.8xlarge'|'g3.16xlarge'|'g3s.xlarge'|'g4ad.4xlarge'|'g4ad.8xlarge'|'g4ad.16xlarge'|'g4dn.xlarge'|'g4dn.2xlarge'|'g4dn.4xlarge'|'g4dn.8xlarge'|'g4dn.12xlarge'|'g4dn.16xlarge'|'g4dn.metal'|'cg1.4xlarge'|'p2.xlarge'|'p2.8xlarge'|'p2.16xlarge'|'p3.2xlarge'|'p3.8xlarge'|'p3.16xlarge'|'p3dn.24xlarge'|'p4d.24xlarge'|'d2.xlarge'|'d2.2xlarge'|'d2.4xlarge'|'d2.8xlarge'|'d3.xlarge'|'d3.2xlarge'|'d3.4xlarge'|'d3.8xlarge'|'d3en.xlarge'|'d3en.2xlarge'|'d3en.4xlarge'|'d3en.6xlarge'|'d3en.8xlarge'|'d3en.12xlarge'|'f1.2xlarge'|'f1.4xlarge'|'f1.16xlarge'|'m5.large'|'m5.xlarge'|'m5.2xlarge'|'m5.4xlarge'|'m5.8xlarge'|'m5.12xlarge'|'m5.16xlarge'|'m5.24xlarge'|'m5.metal'|'m5a.large'|'m5a.xlarge'|'m5a.2xlarge'|'m5a.4xlarge'|'m5a.8xlarge'|'m5a.12xlarge'|'m5a.16xlarge'|'m5a.24xlarge'|'m5d.large'|'m5d.xlarge'|'m5d.2xlarge'|'m5d.4xlarge'|'m5d.8xlarge'|'m5d.12xlarge'|'m5d.16xlarge'|'m5d.24xlarge'|'m5d.metal'|'m5ad.large'|'m5ad.xlarge'|'m5ad.2xlarge'|'m5ad.4xlarge'|'m5ad.8xlarge'|'m5ad.12xlarge'|'m5ad.16xlarge'|'m5ad.24xlarge'|'m5zn.large'|'m5zn.xlarge'|'m5zn.2xlarge'|'m5zn.3xlarge'|'m5zn.6xlarge'|'m5zn.12xlarge'|'m5zn.metal'|'h1.2xlarge'|'h1.4xlarge'|'h1.8xlarge'|'h1.16xlarge'|'z1d.large'|'z1d.xlarge'|'z1d.2xlarge'|'z1d.3xlarge'|'z1d.6xlarge'|'z1d.12xlarge'|'z1d.metal'|'u-6tb1.metal'|'u-9tb1.metal'|'u-12tb1.metal'|'u-18tb1.metal'|'u-24tb1.metal'|'a1.medium'|'a1.large'|'a1.xlarge'|'a1.2xlarge'|'a1.4xlarge'|'a1.metal'|'m5dn.large'|'m5dn.xlarge'|'m5dn.2xlarge'|'m5dn.4xlarge'|'m5dn.8xlarge'|'m5dn.12xlarge'|'m5dn.16xlarge'|'m5dn.24xlarge'|'m5n.large'|'m5n.xlarge'|'m5n.2xlarge'|'m5n.4xlarge'|'m5n.8xlarge'|'m5n.12xlarge'|'m5n.16xlarge'|'m5n.24xlarge'|'r5dn.large'|'r5dn.xlarge'|'r5dn.2xlarge'|'r5dn.4xlarge'|'r5dn.8xlarge'|'r5dn.12xlarge'|'r5dn.16xlarge'|'r5dn.24xlarge'|'r5n.large'|'r5n.xlarge'|'r5n.2xlarge'|'r5n.4xlarge'|'r5n.8xlarge'|'r5n.12xlarge'|'r5n.16xlarge'|'r5n.24xlarge'|'inf1.xlarge'|'inf1.2xlarge'|'inf1.6xlarge'|'inf1.24xlarge'|'m6g.metal'|'m6g.medium'|'m6g.large'|'m6g.xlarge'|'m6g.2xlarge'|'m6g.4xlarge'|'m6g.8xlarge'|'m6g.12xlarge'|'m6g.16xlarge'|'m6gd.metal'|'m6gd.medium'|'m6gd.large'|'m6gd.xlarge'|'m6gd.2xlarge'|'m6gd.4xlarge'|'m6gd.8xlarge'|'m6gd.12xlarge'|'m6gd.16xlarge'|'mac1.metal',
    Ipv6AddressCount=123,
    Ipv6Addresses=[
        {
            'Ipv6Address': 'string'
        },
    ],
    KernelId='string',
    KeyName='string',
    MaxCount=123,
    MinCount=123,
    Monitoring={
        'Enabled': True|False
    },
    Placement={
        'AvailabilityZone': 'string',
        'Affinity': 'string',
        'GroupName': 'string',
        'PartitionNumber': 123,
        'HostId': 'string',
        'Tenancy': 'default'|'dedicated'|'host',
        'SpreadDomain': 'string',
        'HostResourceGroupArn': 'string'
    },
    RamdiskId='string',
    SecurityGroupIds=[
        'string',
    ],
    SecurityGroups=[
        'string',
    ],
    UserData='string',
    AdditionalInfo='string',
    ClientToken='string',
    DisableApiTermination=True|False,
    DryRun=True|False,
    EbsOptimized=True|False,
    IamInstanceProfile={
        'Arn': 'string',
        'Name': 'string'
    },
    InstanceInitiatedShutdownBehavior='stop'|'terminate',
    NetworkInterfaces=[
        {
            'AssociatePublicIpAddress': True|False,
            'DeleteOnTermination': True|False,
            'Description': 'string',
            'DeviceIndex': 123,
            'Groups': [
                'string',
            ],
            'Ipv6AddressCount': 123,
            'Ipv6Addresses': [
                {
                    'Ipv6Address': 'string'
                },
            ],
            'NetworkInterfaceId': 'string',
            'PrivateIpAddress': 'string',
            'PrivateIpAddresses': [
                {
                    'Primary': True|False,
                    'PrivateIpAddress': 'string'
                },
            ],
            'SecondaryPrivateIpAddressCount': 123,
            'SubnetId': 'string',
            'AssociateCarrierIpAddress': True|False,
            'InterfaceType': 'string',
            'NetworkCardIndex': 123
        },
    ],
    PrivateIpAddress='string',
    ElasticGpuSpecification=[
        {
            'Type': 'string'
        },
    ],
    ElasticInferenceAccelerators=[
        {
            'Type': 'string',
            'Count': 123
        },
    ],
    TagSpecifications=[
        {
            'ResourceType': 'client-vpn-endpoint'|'customer-gateway'|'dedicated-host'|'dhcp-options'|'egress-only-internet-gateway'|'elastic-ip'|'elastic-gpu'|'export-image-task'|'export-instance-task'|'fleet'|'fpga-image'|'host-reservation'|'image'|'import-image-task'|'import-snapshot-task'|'instance'|'internet-gateway'|'key-pair'|'launch-template'|'local-gateway-route-table-vpc-association'|'natgateway'|'network-acl'|'network-interface'|'network-insights-analysis'|'network-insights-path'|'placement-group'|'reserved-instances'|'route-table'|'security-group'|'snapshot'|'spot-fleet-request'|'spot-instances-request'|'subnet'|'traffic-mirror-filter'|'traffic-mirror-session'|'traffic-mirror-target'|'transit-gateway'|'transit-gateway-attachment'|'transit-gateway-connect-peer'|'transit-gateway-multicast-domain'|'transit-gateway-route-table'|'volume'|'vpc'|'vpc-peering-connection'|'vpn-connection'|'vpn-gateway'|'vpc-flow-log',
            'Tags': [
                {
                    'Key': 'string',
                    'Value': 'string'
                },
            ]
        },
    ],
    LaunchTemplate={
        'LaunchTemplateId': 'string',
        'LaunchTemplateName': 'string',
        'Version': 'string'
    },
    InstanceMarketOptions={
        'MarketType': 'spot',
        'SpotOptions': {
            'MaxPrice': 'string',
            'SpotInstanceType': 'one-time'|'persistent',
            'BlockDurationMinutes': 123,
            'ValidUntil': datetime(2015, 1, 1),
            'InstanceInterruptionBehavior': 'hibernate'|'stop'|'terminate'
        }
    },
    CreditSpecification={
        'CpuCredits': 'string'
    },
    CpuOptions={
        'CoreCount': 123,
        'ThreadsPerCore': 123
    },
    CapacityReservationSpecification={
        'CapacityReservationPreference': 'open'|'none',
        'CapacityReservationTarget': {
            'CapacityReservationId': 'string',
            'CapacityReservationResourceGroupArn': 'string'
        }
    },
    HibernationOptions={
        'Configured': True|False
    },
    LicenseSpecifications=[
        {
            'LicenseConfigurationArn': 'string'
        },
    ],
    MetadataOptions={
        'HttpTokens': 'optional'|'required',
        'HttpPutResponseHopLimit': 123,
        'HttpEndpoint': 'disabled'|'enabled'
    },
    EnclaveOptions={
        'Enabled': True|False
    }
)
```

Parameters

*   **BlockDeviceMappings** (_list_) --

    The block device mapping entries.

    *   _(dict) --
        Describes a block device mapping.

        *   **DeviceName** _(string) --     
            The device name (for example, /dev/sdh or xvdh ).

        *   **VirtualName** _(string) --     
            The virtual device name (ephemeral N). Instance store volumes are numbered starting from 0. An instance type with 2 available instance store volumes can specify mappings for ephemeral0 and ephemeral1 . The number of available instance store volumes depends on the instance type. After you connect to the instance, you must mount the volume.

            NVMe instance store volumes are automatically enumerated and assigned a device name. Including them in your block device mapping has no effect.

            Constraints: For M3 instances, you must specify instance store volumes in the block device mapping for the instance. When you launch an M3 instance, we ignore any instance store volumes specified in the block device mapping for the AMI.

        *   **Ebs** _(dict) --     ```

            Parameters used to automatically set up EBS volumes when the instance is launched.

            *   **DeleteOnTermination** _(boolean) --         
                Indicates whether the EBS volume is deleted on instance termination. For more information, see [Preserving Amazon EBS volumes on instance termination](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/terminating-instances.html#preserving-volumes-on-termination) in the Amazon Elastic Compute Cloud User Guide.

            *   **Iops** _(integer) --         
                The number of I/O operations per second (IOPS). For gp3 , io1 , and io2 volumes, this represents the number of IOPS that are provisioned for the volume. For gp2 volumes, this represents the baseline performance of the volume and the rate at which the volume accumulates I/O credits for bursting.

                The following are the supported values for each volume type:

                *   gp3 : 3,000-16,000 IOPS
                *   io1 : 100-64,000 IOPS
                *   io2 : 100-64,000 IOPS

                For io1 and io2 volumes, we guarantee 64,000 IOPS only for [Instances built on the Nitro System](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/instance-types.html#ec2-nitro-instances) . Other instance families guarantee performance up to 32,000 IOPS.

                This parameter is required for io1 and io2 volumes. The default for gp3 volumes is 3,000 IOPS. This parameter is not supported for gp2 , st1 , sc1 , or standard volumes.

            *   **SnapshotId** _(string) --         
                The ID of the snapshot.

            *   **VolumeSize** _(integer) --         
                The size of the volume, in GiBs. You must specify either a snapshot ID or a volume size. If you specify a snapshot, the default is the snapshot size. You can specify a volume size that is equal to or larger than the snapshot size.

                The following are the supported volumes sizes for each volume type:

                *   gp2 and gp3 :1-16,384
                *   io1 and io2 : 4-16,384
                *   st1 : 500-16,384
                *   sc1 : 500-16,384
                *   standard : 1-1,024
            *   **VolumeType** _(string) --         
                The volume type. For more information, see [Amazon EBS volume types](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EBSVolumeTypes.html) in the _Amazon Elastic Compute Cloud User Guide_ . If the volume type is io1 or io2 , you must specify the IOPS that the volume supports.

            *   **KmsKeyId** _(string) --         
                Identifier (key ID, key alias, ID ARN, or alias ARN) for a customer managed CMK under which the EBS volume is encrypted.

                This parameter is only supported on BlockDeviceMapping objects called by [RunInstances](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_RunInstances.html) , [RequestSpotFleet](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_RequestSpotFleet.html) , and [RequestSpotInstances](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_RequestSpotInstances.html) .

            *   **Throughput** _(integer) --         
                The throughput that the volume supports, in MiB/s.

                This parameter is valid only for gp3 volumes.

                Valid Range: Minimum value of 125. Maximum value of 1000.

            *   **Encrypted** _(boolean) --         
                Indicates whether the encryption state of an EBS volume is changed while being restored from a backing snapshot. The effect of setting the encryption state to true depends on the volume origin (new or from a snapshot), starting encryption state, ownership, and whether encryption by default is enabled. For mo```
                re information, see [Amazon EBS Encryption](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EBSEncryption.html#encryption-parameters) in the _Amazon Elastic Compute Cloud User Guide_ .

                In no case can you remove encryption from an encrypted volume.

                Encrypted volumes can only be attached to instances that support Amazon EBS encryption. For more information, see [Supported instance types](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EBSEncryption.html#EBSEncryption_supported_instances) .

                This parameter is not returned by .

        *   **NoDevice** _(string) --     
            Suppresses the specified device included in the block device mapping of the AMI.

*   **ImageId** (_string_) -- The ID of the AMI. An AMI ID is required to launch an instance and must be specified here or in a launch template.
*   **InstanceType** (_string_) --

    The instance type. For more information, see [Instance types](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/instance-types.html) in the _Amazon Elastic Compute Cloud User Guide_ .

    Default: m1.small

*   **Ipv6AddressCount** (_integer_) --

    [EC2-VPC] The number of IPv6 addresses to associate with the primary network interface. Amazon EC2 chooses the IPv6 addresses from the range of your subnet. You cannot specify this option and the option to assign specific IPv6 addresses in the same request. You can specify this option if you've specified a minimum number of instances to launch.

    You cannot specify this option and the network interfaces option in the same request.

*   **Ipv6Addresses** (_list_) --

    [EC2-VPC] The IPv6 addresses from the range of the subnet to associate with the primary network interface. You cannot specify this option and the option to assign a number of IPv6 addresses in the same request. You cannot specify this option if you've specified a minimum number of instances to launch.

    You cannot specify this option and the network interfaces option in the same request.

    *   _(dict) --
        Describes an IPv6 address.

        *   **Ipv6Address** _(string) --     
            The IPv6 address.

*   **KernelId** (_string_) --

    The ID of the kernel.

    Warning

    We recommend that you use PV-GRUB instead of kernels and RAM disks. For more information, see [PV-GRUB](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/UserProvidedkernels.html) in the _Amazon Elastic Compute Cloud User Guide_ .

*   **KeyName** (_string_) --

    The name of the key pair. You can create a key pair using [CreateKeyPair](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateKeyPair.html) or [ImportKeyPair](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_ImportKeyPair.html) .

    Warning

    If you do not specify a key pair, you can't connect to the instance unless you choose an AMI that is configured to allow users another way to log in.

*   **MaxCount** (_integer_) -- **[REQUIRED]** The maximum number of instances to launch. If you specify more instances than Amazon EC2 can launch in the target Availability Zone, Amazon EC2 launches the largest possible number of instances above MinCount .

    Constraints: Between 1 and the maximum number you're allowed for the specified instance type. For more information about the default limits, and how to request an increase, see [How many instances can I run in Amazon EC2](http://aws.amazon.com/ec2/faqs/#How_many_instances_can_I_run_in_Amazon_EC2) in the Amazon EC2 FAQ.

*   **MinCount** (_integer_) -- **[REQUIRED]** The minimum number of instances to launch. If you specify a minimum that is more instances than Amazon EC2 can launch in the target Availability Zone, Amazon EC2 launches no instances.

    Constraints: Between 1 and the maximum number you're allowed for the specified instance type. For more information about the default limits, and how to request an increase, see [How many instances can I run in Amazon EC2](http://aws.amazon.com/ec2/faqs/#How_many_instances_can_I_run_in_Amazon_EC2) in the Amazon EC2 General FAQ.

*   **Monitoring** (_dict_) --

    Specifies whether detailed monitoring is enabled for the instance.

    *   **Enabled** _(boolean) --_ **[REQUIRED]**

        Indicates whether detailed monitoring is enabled. Otherwise, basic monitoring is enabled.

*   **Placement** (_dict_) --

    The placement for the instance.

    *   **AvailabilityZone** _(string) --
        The Availability Zone of the instance.

        If not specified, an Availability Zone will be automatically chosen for you based on the load balancing criteria for the Region.

        This parameter is not supported by [CreateFleet](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateFleet) .

    *   **Affinity** _(string) --
        The affinity setting for the instance on the Dedicated Host. This parameter is not supported for the [ImportInstance](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_ImportInstance.html) command.

        This parameter is not supported by [CreateFleet](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateFleet) .

    *   **GroupName** _(string) --
        The name of the placement group the instance is in.

    *   **PartitionNumber** _(integer) -- The number of the partition the instance is in. Valid only if the placement group strategy is set to partition .

        This parameter is not supported by [CreateFleet](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateFleet) .

    *   **HostId** _(string) --
        The ID of the Dedicated Host on which the instance resides. This parameter is not supported for the [ImportInstance](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_ImportInstance.html) command.

        This parameter is not supported by [CreateFleet](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateFleet) .

    *   **Tenancy** _(string) --
        The tenancy of the instance (if the instance is running in a VPC). An instance with a tenancy of dedicated runs on single-tenant hardware. The host tenancy is not supported for the [ImportInstance](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_ImportInstance.html) command.

        This parameter is not supported by [CreateFleet](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateFleet) .

    *   **SpreadDomain** _(string) --
        Reserved for future use.

        This parameter is not supported by [CreateFleet](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateFleet) .

    *   **HostResourceGroupArn** _(string) --
        The ARN of the host resource group in which to launch the instances. If you specify a host resource group ARN, omit the **Tenancy** parameter or set it to host .

        This parameter is not supported by [CreateFleet](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateFleet) .

*   **RamdiskId** (_string_) --

    The ID of the RAM disk to select. Some kernels require additional drivers at launch. Check the kernel requirements for information about whether you need to specify a RAM disk. To find kernel requirements, go to the AWS Resource Center and search for the kernel ID.

    Warning

    We recommend that you use PV-GRUB instead of kernels and RAM disks. For more information, see [PV-GRUB](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/UserProvidedkernels.html) in the _Amazon Elastic Compute Cloud User Guide_ .

*   **SecurityGroupIds** (_list_) --

    The IDs of the security groups. You can create a security group using [CreateSecurityGroup](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateSecurityGroup.html) .

    If you specify a network interface, you must specify any security groups as part of the network interface.

    *   _(string) --_
*   **SecurityGroups** (_list_) --

    [EC2-Classic, default VPC] The names of the security groups. For a nondefault VPC, you must use security group IDs instead.

    If you specify a network interface, you must specify any security groups as part of the network interface.

    Default: Amazon EC2 uses the default security group.

    *   _(string) --_
*   **UserData** (_string_) --

    The user data to make available to the instance. For more information, see [Running commands on your Linux instance at launch](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/user-data.html) (Linux) and [Adding User Data](https://docs.aws.amazon.com/AWSEC2/latest/WindowsGuide/ec2-instance-metadata.html#instancedata-add-user-data) (Windows). If you are using a command line tool, base64-encoding is performed for you, and you can load the text from a file. Otherwise, you must provide base64-encoded text. User data is limited to 16 KB.

    > **This value will be base64 encoded automatically. Do not base64 encode this value prior to performing the operation.**

*   **AdditionalInfo** (_string_) -- Reserved.
*   **ClientToken** (_string_) --

    Unique, case-sensitive identifier you provide to ensure the idempotency of the request. If you do not specify a client token, a randomly generated token is used for the request to ensure idempotency.

    For more information, see [Ensuring Idempotency](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/Run_Instance_Idempotency.html) .

    Constraints: Maximum 64 ASCII characters

    This field is autopopulated if not provided.

*   **DisableApiTermination** (_boolean_) --

    If you set this parameter to true , you can't terminate the instance using the Amazon EC2 console, CLI, or API; otherwise, you can. To change this attribute after launch, use [ModifyInstanceAttribute](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_ModifyInstanceAttribute.html) . Alternatively, if you set InstanceInitiatedShutdownBehavior to terminate , you can terminate the instance by running the shutdown command from the instance.

    Default: false

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **EbsOptimized** (_boolean_) --

    Indicates whether the instance is optimized for Amazon EBS I/O. This optimization provides dedicated throughput to Amazon EBS and an optimized configuration stack to provide optimal Amazon EBS I/O performance. This optimization isn't available with all instance types. Additional usage charges apply when using an EBS-optimized instance.

    Default: false

*   **IamInstanceProfile** (_dict_) --

    The IAM instance profile.

    *   **Arn** _(string) --
        The Amazon Resource Name (ARN) of the instance profile.

    *   **Name** _(string) --
        The name of the instance profile.

*   **InstanceInitiatedShutdownBehavior** (_string_) --

    Indicates whether an instance stops or terminates when you initiate shutdown from the instance (using the operating system command for system shutdown).

    Default: stop

*   **NetworkInterfaces** (_list_) --

    The network interfaces to associate with the instance. If you specify a network interface, you must specify any security groups and subnets as part of the network interface.

    *   _(dict) --
        Describes a network interface.

        *   **AssociatePublicIpAddress** _(boolean) --     
            Indicates whether to assign a public IPv4 address to an instance you launch in a VPC. The public IP address can only be assigned to a network interface for eth0, and can only be assigned to a new network interface, not an existing one. You cannot specify more than one network interface in the request. If launching into a default subnet, the default value is true .

        *   **DeleteOnTermination** _(boolean) --     
            If set to true , the interface is deleted when the instance is terminated. You can specify true only if creating a new network interface when launching an instance.

        *   **Description** _(string) --     
            The description of the network interface. Applies only if creating a network interface when launching an instance.

        *   **DeviceIndex** _(integer) --     
            The position of the network interface in the attachment order. A primary network interface has a device index of 0.

            If you specify a network interface when launching an instance, you must specify the device index.

        *   **Groups** _(list) --     
            The IDs of the security groups for the network interface. Applies only if creating a network interface when launching an instance.

            *   _(string) -- *   **Ipv6AddressCount** _(integer) --     
            A number of IPv6 addresses to assign to the network interface. Amazon EC2 chooses the IPv6 addresses from the range of the subnet. You cannot specify this option and the option to assign specific IPv6 addresses in the same request. You can specify this option if you've specified a minimum number of instances to launch.

        *   **Ipv6Addresses** _(list) --     
            One or more IPv6 addresses to assign to the network interface. You cannot specify this option and the option to assign a number of IPv6 addresses in the same request. You cannot specify this option if you've specified a minimum number of instances to launch.

            *   _(dict) --         
                Describes an IPv6 address.

                *   **Ipv6Address** _(string) --             
                    The IPv6 address.

        *   **NetworkInterfaceId** _(string) --     
            The ID of the network interface.

            If you are creating a Spot Fleet, omit this parameter because you can’t specify a network interface ID in a launch specification.

        *   **PrivateIpAddress** _(string) --     
            The private IPv4 address of the network interface. Applies only if creating a network interface when launching an instance. You cannot specify this option if you're launching more than one instance in a [RunInstances](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_RunInstances.html) request.

        *   **PrivateIpAddresses** _(list) --     
            One or more private IPv4 addresses to assign to the network interface. Only one private IPv4 address can be designated as primary. You cannot specify this option if you're launching more than one instance in a [RunInstances](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_RunInstances.html) request.

            *   _(dict) --         
                Describes a secondary private IPv4 address for a network interface.

                *   **Primary** _(boolean) --             
                    Indicates whether the private IPv4 address is the primary private IPv4 address. Only one IPv4 address can be designated as primary.

                *   **PrivateIpAddress** _(string) --             
                    The private IPv4 addresses.

        *   **SecondaryPrivateIpAddressCount** _(integer) --     
            The number of secondary private IPv4 addresses. You can't specify this option and specify more than one private IP address using the private IP addresses option. You cannot specify this option if you're launching more than one instance in a [RunInstances](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_RunInstances.html) request.

        *   **SubnetId** _(string) --     
            The ID of the subnet associated with the network interface. Applies only if creating a network interface when launching an instance.

        *   **AssociateCarrierIpAddress** _(boolean) --     
            Indicates whether to assign a carrier IP address to the network interface.

            You can only assign a carrier IP address to a network interface that is in a subnet in a Wavelength Zone. For more information about carrier IP addresses, see Carrier IP addresses in the AWS Wavelength Developer Guide.

        *   **InterfaceType** _(string) --     
            The type of network interface.

            To create an Elastic Fabric Adapter (EFA), specify efa . For more information, see [Elastic Fabric Adapter](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/efa.html) in the _Amazon Elastic Compute Cloud User Guide_ .

            If you are not creating an EFA, specify interface or omit this parameter.

            Valid values: interface | efa

        *   **NetworkCardIndex** _(integer) --     
            The index of the network card. Some instance types support multiple network cards. The primary network interface must be assigned to network card index 0. The default is network card index 0.

*   **PrivateIpAddress** (_string_) --

    [EC2-VPC] The primary IPv4 address. You must specify a value from the IPv4 address range of the subnet.

    Only one private IP address can be designated as primary. You can't specify this option if you've specified the option to designate a private IP address as the primary IP address in a network interface specification. You cannot specify this option if you're launching more than one instance in the request.

    You cannot specify this option and the network interfaces option in the same request.

*   **ElasticGpuSpecification** (_list_) --

    An elastic GPU to associate with the instance. An Elastic GPU is a GPU resource that you can attach to your Windows instance to accelerate the graphics performance of your applications. For more information, see [Amazon EC2 Elastic GPUs](https://docs.aws.amazon.com/AWSEC2/latest/WindowsGuide/elastic-graphics.html) in the _Amazon Elastic Compute Cloud User Guide_ .

    *   _(dict) --
        A specification for an Elastic Graphics accelerator.

        *   **Type** _(string) --_ **[REQUIRED]**

            The type of Elastic Graphics accelerator. For more information about the values to specify for Type , see [Elastic Graphics Basics](https://docs.aws.amazon.com/AWSEC2/latest/WindowsGuide/elastic-graphics.html#elastic-graphics-basics) , specifically the Elastic Graphics accelerator column, in the _Amazon Elastic Compute Cloud User Guide for Windows Instances_ .

*   **ElasticInferenceAccelerators** (_list_) --

    An elastic inference accelerator to associate with the instance. Elastic inference accelerators are a resource you can attach to your Amazon EC2 instances to accelerate your Deep Learning (DL) inference workloads.

    You cannot specify accelerators from different generations in the same request.

    *   _(dict) --
        Describes an elastic inference accelerator.

        *   **Type** _(string) --_ **[REQUIRED]**

            The type of elastic inference accelerator. The possible values are eia1.medium , eia1.large , eia1.xlarge , eia2.medium , eia2.large , and eia2.xlarge .

        *   **Count** _(integer) --     
            The number of elastic inference accelerators to attach to the instance.

            Default: 1

*   **TagSpecifications** (_list_) --

    The tags to apply to the resources during launch. You can only tag instances and volumes on launch. The specified tags are applied to all instances or volumes that are created during launch. To tag a resource after it has been created, see [CreateTags](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateTags.html) .

    *   _(dict) --
        The tags to apply to a resource when the resource is being created.

        *   **ResourceType** _(string) --     
            The type of resource to tag. Currently, the resource types that support tagging on creation are: capacity-reservation | carrier-gateway | client-vpn-endpoint | customer-gateway | dedicated-host | dhcp-options | egress-only-internet-gateway | elastic-ip | elastic-gpu | export-image-task | export-instance-task | fleet | fpga-image | host-reservation | image | import-image-task | import-snapshot-task | instance | internet-gateway | ipv4pool-ec2 | ipv6pool-ec2 | key-pair | launch-template | local-gateway-route-table-vpc-association | placement-group | prefix-list | natgateway | network-acl | network-interface | reserved-instances [|](#id1089)route-table | security-group | snapshot | spot-fleet-request | spot-instances-request | snapshot | subnet | traffic-mirror-filter | traffic-mirror-session | traffic-mirror-target | transit-gateway | transit-gateway-attachment | transit-gateway-multicast-domain | transit-gateway-route-table | volume [|](#id1091)vpc | vpc-peering-connection | vpc-endpoint (for interface and gateway endpoints) | vpc-endpoint-service (for AWS PrivateLink) | vpc-flow-log | vpn-connection | vpn-gateway .

            To tag a resource after it has been created, see [CreateTags](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateTags.html) .

        *   **Tags** _(list) --     
            The tags to apply to the resource.

            *   _(dict) --         
                Describes a tag.

                *   **Key** _(string) --             
                    The key of the tag.

                    Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

                *   **Value** _(string) --             
                    The value of the tag.

                    Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

*   **LaunchTemplate** (_dict_) --
    ```

    The launch template to use to launch the instances. Any parameters that you specify in RunInstances override the same parameters in the launch template. You can specify either the name or ID of a launch template, but not both.

    *   **LaunchTemplateId** _(string) --
        The ID of the launch template.

    *   **LaunchTemplateName** _(string) --
        The name of the launch template.

    *   **Version** _(string) --
        The version number of the launch template.

        Default: The default version for the launch template.

*   **InstanceMarketOptions** (_dict_) --

    The market (purchasing) option for the instances.

    For RunInstances , persistent Spot Instance requests are only supported when **InstanceInterruptionBehavior** is set to either hibernate or stop .

    *   **MarketType** _(string) --
        The market type.

    *   **SpotOptions** _(dict) --
        The options for Spot Instances.

        *   **MaxPrice** _(string) --     
            The maximum hourly price you're willing to pay for the Spot Instances. The default is the On-Demand price.

        *   **SpotInstanceType** _(string) --     
            The Spot Instance request type. For [RunInstances](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_RunInstances) , persistent Spot Instance requests are only supported when **InstanceInterruptionBehavior** is set to either hibernate or stop .

        *   **BlockDurationMinutes** _(integer) --     
            The required duration for the Spot Instances (also known as Spot blocks), in minutes. This value must be a multiple of 60 (60, 120, 180, 240, 300, or 360).

            The duration period starts as soon as your Spot Instance receives its instance ID. At the end of the duration period, Amazon EC2 marks the Spot Instance for termination and provides a Spot Instance termination notice, which gives the instance a two-minute warning before it terminates.

            You can't specify an Availability Zone group or a launch group if you specify a duration.

            New accounts or accounts with no previous billing history with AWS are not eligible for Spot Instances with a defined duration (also known as Spot blocks).

        *   **ValidUntil** _(datetime) --     
            The end date of the request, in UTC format (_YYYY_ -_MM_ -_DD_ T*HH* :_MM_ :_SS_ Z). Supported only for persistent requests.

            *   For a persistent request, the request remains active until the ValidUntil date and time is reached. Otherwise, the request remains active until you cancel it.
            *   For a one-time request, ValidUntil is not supported. The request remains active until all instances launch or you cancel the request.
        *   **InstanceInterruptionBehavior** _(string) --     
            The behavior when a Spot Instance is interrupted. The default is terminate .

*   **CreditSpecification** (_dict_) --

    The credit option for CPU usage of the burstable performance instance. Valid values are standard and unlimited . To change this attribute after launch, use [ModifyInstanceCreditSpecification](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_ModifyInstanceCreditSpecification.html) . For more information, see [Burstable performance instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/burstable-performance-instances.html) in the _Amazon Elastic Compute Cloud User Guide_ .

    Default: standard (T2 instances) or unlimited (T3/T3a instances)

    *   **CpuCredits** _(string) --_ **[REQUIRED]**

        The credit option for CPU usage of a T2, T3, or T3a instance. Valid values are standard and unlimited .

*   **CpuOptions** (_dict_) --

    The CPU options for the instance. For more information, see [Optimizing CPU options](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/instance-optimize-cpu.html) in the _Amazon Elastic Compute Cloud User Guide_ .

    *   **CoreCount** _(integer) -- The number of CPU cores for the instance.

    *   **ThreadsPerCore** _(integer) -- The number of threads per CPU core. To disable multithreading for the instance, specify a value of 1 . Otherwise, specify the default value of 2 .

*   **CapacityReservationSpecification** (_dict_) --

    Information about the Capacity Reservation targeting option. If you do not specify this parameter, the instance's Capacity Reservation preference defaults to open , which enables it to run in any open Capacity Reservation that has matching attributes (instance type, platform, Availability Zone).

    *   **CapacityReservationPreference** _(string) --
        Indicates the instance's Capacity Reservation preferences. Possible preferences include:

        *   open - The instance can run in any open Capacity Reservation that has matching attributes (instance type, platform, Availability Zone).
        *   none - The instance avoids running in a Capacity Reservation even if one is available. The instance runs as an On-Demand Instance.
    *   **CapacityReservationTarget** _(dict) --
        Information about the target Capacity Reservation or Capacity Reservation group.

        *   **CapacityReservationId** _(string) --     
            The ID of the Capacity Reservation in which to run the instance.

        *   **CapacityReservationResourceGroupArn** _(string) --     
            The ARN of the Capacity Reservation resource group in which to run the instance.

*   **HibernationOptions** (_dict_) --

    Indicates whether an instance is enabled for hibernation. For more information, see [Hibernate your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html) in the _Amazon Elastic Compute Cloud User Guide_ .

    You can't enable hibernation and AWS Nitro Enclaves on the same instance.

    *   **Configured** _(boolean) --
        If you set this parameter to true , your instance is enabled for hibernation.

        Default: false

*   **LicenseSpecifications** (_list_) --

    The license configurations.

    *   _(dict) --
        Describes a license configuration.

        *   **LicenseConfigurationArn** _(string) --     
            The Amazon Resource Name (ARN) of the license configuration.

*   **MetadataOptions** (_dict_) --

    The metadata options for the instance. For more information, see [Instance metadata and user data](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-metadata.html) .

    *   **HttpTokens** _(string) --
        The state of token usage for your instance metadata requests. If the parameter is not specified in the request, the default state is optional .

        If the state is optional , you can choose to retrieve instance metadata with or without a signed token header on your request. If you retrieve the IAM role credentials without a token, the version 1.0 role credentials are returned. If you retrieve the IAM role credentials using a valid signed token, the version 2.0 role credentials are returned.

        If the state is required , you must send a signed token header with any instance metadata retrieval requests. In this state, retrieving the IAM role credentials always returns the version 2.0 credentials; the version 1.0 credentials are not available.

    *   **HttpPutResponseHopLimit** _(integer) -- The desired HTTP PUT response hop limit for instance metadata requests. The larger the number, the further instance metadata requests can travel.

        Default: 1

        Possible values: Integers from 1 to 64

    *   **HttpEndpoint** _(string) --
        This parameter enables or disables the HTTP metadata endpoint on your instances. If the parameter is not specified, the default state is enabled .

        Note

        If you specify a value of disabled , you will not be able to access your instance metadata.

*   **EnclaveOptions** (_dict_) --

    Indicates whether the instance is enabled for AWS Nitro Enclaves. For more information, see [What is AWS Nitro Enclaves?](https://docs.aws.amazon.com/enclaves/latest/user/nitro-enclave.html) in the _AWS Nitro Enclaves User Guide_ .

    You can't enable AWS Nitro Enclaves and hibernation on the same instance.

    *   **Enabled** _(boolean) --
        To enable the instance for AWS Nitro Enclaves, set this parameter to true .

Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

create_network_interface(_**kwargs_)

Creates a network interface in the specified subnet.

For more information about network interfaces, see [Elastic Network Interfaces](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-eni.html) in the _Amazon Virtual Private Cloud User Guide_ .


**Request Syntax**

```py
network_interface = subnet.create_network_interface(
    Description='string',
    DryRun=True|False,
    Groups=[
        'string',
    ],
    Ipv6AddressCount=123,
    Ipv6Addresses=[
        {
            'Ipv6Address': 'string'
        },
    ],
    PrivateIpAddress='string',
    PrivateIpAddresses=[
        {
            'Primary': True|False,
            'PrivateIpAddress': 'string'
        },
    ],
    SecondaryPrivateIpAddressCount=123,
    InterfaceType='efa',
    TagSpecifications=[
        {
            'ResourceType': 'client-vpn-endpoint'|'customer-gateway'|'dedicated-host'|'dhcp-options'|'egress-only-internet-gateway'|'elastic-ip'|'elastic-gpu'|'export-image-task'|'export-instance-task'|'fleet'|'fpga-image'|'host-reservation'|'image'|'import-image-task'|'import-snapshot-task'|'instance'|'internet-gateway'|'key-pair'|'launch-template'|'local-gateway-route-table-vpc-association'|'natgateway'|'network-acl'|'network-interface'|'network-insights-analysis'|'network-insights-path'|'placement-group'|'reserved-instances'|'route-table'|'security-group'|'snapshot'|'spot-fleet-request'|'spot-instances-request'|'subnet'|'traffic-mirror-filter'|'traffic-mirror-session'|'traffic-mirror-target'|'transit-gateway'|'transit-gateway-attachment'|'transit-gateway-connect-peer'|'transit-gateway-multicast-domain'|'transit-gateway-route-table'|'volume'|'vpc'|'vpc-peering-connection'|'vpn-connection'|'vpn-gateway'|'vpc-flow-log',
            'Tags': [
                {
                    'Key': 'string',
                    'Value': 'string'
                },
            ]
        },
    ]
)
```

Parameters

*   **Description** (_string_) -- A description for the network interface.
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Groups** (_list_) --

    The IDs of one or more security groups.

    *   _(string) --_
*   **Ipv6AddressCount** (_integer_) -- The number of IPv6 addresses to assign to a network interface. Amazon EC2 automatically selects the IPv6 addresses from the subnet range. You can't use this option if specifying specific IPv6 addresses. If your subnet has the AssignIpv6AddressOnCreation attribute set to true , you can specify 0 to override this setting.
*   **Ipv6Addresses** (_list_) --

    One or more specific IPv6 addresses from the IPv6 CIDR block range of your subnet. You can't use this option if you're specifying a number of IPv6 addresses.

    *   _(dict) --
        Describes an IPv6 address.

        *   **Ipv6Address** _(string) --     
            The IPv6 address.

*   **PrivateIpAddress** (_string_) -- The primary private IPv4 address of the network interface. If you don't specify an IPv4 address, Amazon EC2 selects one for you from the subnet's IPv4 CIDR range. If you specify an IP address, you cannot indicate any IP addresses specified in privateIpAddresses as primary (only one IP address can be designated as primary).
*   **PrivateIpAddresses** (_list_) --

    One or more private IPv4 addresses.

    *   _(dict) --
        Describes a secondary private IPv4 address for a network interface.

        *   **Primary** _(boolean) --     
            Indicates whether the private IPv4 address is the primary private IPv4 address. Only one IPv4 address can be designated as primary.

        *   **PrivateIpAddress** _(string) --     
            The private IPv4 addresses.

*   **SecondaryPrivateIpAddressCount** (_integer_) --

    The number of secondary private IPv4 addresses to assign to a network interface. When you specify a number of secondary IPv4 addresses, Amazon EC2 selects these IP addresses within the subnet's IPv4 CIDR range. You can't specify this option and specify more than one private IP address using privateIpAddresses .

    The number of IP addresses you can assign to a network interface varies by instance type. For more information, see [IP Addresses Per ENI Per Instance Type](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-eni.html#AvailableIpPerENI) in the _Amazon Virtual Private Cloud User Guide_ .

*   **InterfaceType** (_string_) -- Indicates the type of network interface. To create an Elastic Fabric Adapter (EFA), specify efa . For more information, see [Elastic Fabric Adapter](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/efa.html) in the _Amazon Elastic Compute Cloud User Guide_ .
*   **TagSpecifications** (_list_) --

    The tags to apply to the new network interface.

    *   _(dict) --
        The tags to apply to a resource when the resource is being created.

        *   **ResourceType** _(string) --     
            The type of resource to tag. Currently, the resource types that support tagging on creation are: capacity-reservation | carrier-gateway | client-vpn-endpoint | customer-gateway | dedicated-host | dhcp-options | egress-only-internet-gateway | elastic-ip | elastic-gpu | export-image-task | export-instance-task | fleet | fpga-image | host-reservation | image | import-image-task | import-snapshot-task | instance | internet-gateway | ipv4pool-ec2 | ipv6pool-ec2 | key-pair | launch-template | local-gateway-route-table-vpc-association | placement-group | prefix-list | natgateway | network-acl | network-interface | reserved-instances [|](#id1094)route-table | security-group | snapshot | spot-fleet-request | spot-instances-request | snapshot | subnet | traffic-mirror-filter | traffic-mirror-session | traffic-mirror-target | transit-gateway | transit-gateway-attachment | transit-gateway-multicast-domain | transit-gateway-route-table | volume [|](#id1096)vpc | vpc-peering-connection | vpc-endpoint (for interface and gateway endpoints) | vpc-endpoint-service (for AWS PrivateLink) | vpc-flow-log | vpn-connection | vpn-gateway .

            To tag a resource after it has been created, see [CreateTags](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateTags.html) .

        *   **Tags** _(list) --     
            The tags to apply to the resource.

            *   _(dict) --         
                Describes a tag.

                *   **Key** _(string) --             
                    The key of the tag.

                    Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

                *   **Value** _(string) --             
                    The value of the tag.

                    Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: ec2.NetworkInterface
- NetworkInterface resource

create_tags(_**kwargs_)

Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources. When you specify an existing tag key, the value is overwritten with the new value. Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
tag = subnet.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Tags** (_list_) -- **[REQUIRED]** The tags. The value parameter is required, but if you don't want the tag to have a value, specify the parameter with no value, and we set the value to an empty string.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: list(ec2.Tag)
- A list of Tag resources

delete(_**kwargs_)

Deletes the specified subnet. You must terminate all running instances in the subnet before you can delete the subnet.


**Request Syntax**

```py
response = subnet.delete(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_subnets()](#EC2.Client.describe_subnets "EC2.Client.describe_subnets") to update the attributes of the Subnet resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
subnet.load()
- None

reload()

Calls [EC2.Client.describe_subnets()](#EC2.Client.describe_subnets "EC2.Client.describe_subnets") to update the attributes of the Subnet resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
subnet.reload()
- None

Collections

Collections provide an interface to iterate over and manipulate groups of resources. For more information about collections refer to the [_Resources Introduction Guide_](../../guide/collections.html#guide-collections).

instances

A collection of Instance resources.A Instance Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all Instance resources in the collection.


**Request Syntax**

```py
instance_iterator = subnet.instances.all()
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

create_tags(_**kwargs_)

Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources. When you specify an existing tag key, the value is overwritten with the new value. Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = subnet.instances.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Tags** (_list_) -- **[REQUIRED]** The tags. The value parameter is required, but if you don't want the tag to have a value, specify the parameter with no value, and we set the value to an empty string.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

- None

filter(_**kwargs_)

Creates an iterable of all Instance resources in the collection filtered by kwargs passed to method.A Instance collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
instance_iterator = subnet.instances.filter(
    InstanceIds=[
        'string',
    ],
    DryRun=True|False,
    MaxResults=123,
    NextToken='string'
)
```

Parameters

*   **InstanceIds** (_list_) --

    The instance IDs.

    Default: Describes all your instances.

    *   _(string) --_
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **MaxResults** (_integer_) -- The maximum number of results to return in a single call. To retrieve the remaining results, make another call with the returned NextToken value. This value can be between 5 and 1000. You cannot specify this parameter and the instance IDs parameter in the same call.
*   **NextToken** (_string_) -- The token to request the next page of results.
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of Instance resources in the collection.


**Request Syntax**

```py
instance_iterator = subnet.instances.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

monitor(_**kwargs_)

Enables detailed monitoring for a running instance. Otherwise, basic monitoring is enabled. For more information, see [Monitoring your instances and volumes](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-cloudwatch.html) in the _Amazon Elastic Compute Cloud User Guide_ .

To disable detailed monitoring, see .


**Request Syntax**

```py
response = subnet.instances.monitor(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'InstanceMonitorings': [
        {
            'InstanceId': 'string',
            'Monitoring': {
                'State': 'disabled'|'disabling'|'enabled'|'pending'
            }
        },
    ]
}
```


**Response Structure**

*   _(dict) --_
    *   **InstanceMonitorings** _(list) --
        The monitoring information.

        *   _(dict) --     
            Describes the monitoring of an instance.

            *   **InstanceId** _(string) --         
                The ID of the instance.

            *   **Monitoring** _(dict) --         
                The monitoring for the instance.

                *   **State** _(string) --             
                    Indicates whether detailed monitoring is enabled. Otherwise, basic monitoring is enabled.


page_size(_**kwargs_)

Creates an iterable of all Instance resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
instance_iterator = subnet.instances.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

reboot(_**kwargs_)

Requests a reboot of the specified instances. This operation is asynchronous; it only queues a request to reboot the specified instances. The operation succeeds if the instances are valid and belong to you. Requests to reboot terminated instances are ignored.

If an instance does not cleanly shut down within a few minutes, Amazon EC2 performs a hard reboot.

For more information about troubleshooting, see [Getting console output and rebooting instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/instance-console.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = subnet.instances.reboot(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

start(_**kwargs_)

Starts an Amazon EBS-backed instance that you've previously stopped.

Instances that use Amazon EBS volumes as their root devices can be quickly stopped and started. When an instance is stopped, the compute resources are released and you are not billed for instance usage. However, your root partition Amazon EBS volume remains and continues to persist your data, and you are charged for Amazon EBS volume usage. You can restart your instance at any time. Every time you start your Windows instance, Amazon EC2 charges you for a full instance hour. If you stop and restart your Windows instance, a new instance hour begins and Amazon EC2 charges you for another full instance hour even if you are still within the same 60-minute period when it was stopped. Every time you start your Linux instance, Amazon EC2 charges a one-minute minimum for instance usage, and thereafter charges per second for instance usage.

Before stopping an instance, make sure it is in a state from which it can be restarted. Stopping an instance does not preserve data stored in RAM.

Performing this operation on an instance that uses an instance store as its root device returns an error.

For more information, see [Stopping instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Stop_Start.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = subnet.instances.start(
    AdditionalInfo='string',
    DryRun=True|False
)
```

Parameters

*   **AdditionalInfo** (_string_) -- Reserved.
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'StartingInstances': [
        {
            'CurrentState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            },
            'InstanceId': 'string',
            'PreviousState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            }
        },
    ]
}
```


**Response Structure**

*   _(dict) --_

    *   **StartingInstances** _(list) --
        Information about the started instances.

        *   _(dict) --     
            Describes an instance state change.

            *   **CurrentState** _(dict) --         
                The current state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.

            *   **InstanceId** _(string) --         
                The ID of the instance.

            *   **PreviousState** _(dict) --         
                The previous state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.


stop(_**kwargs_)

Stops an Amazon EBS-backed instance.

You can use the Stop action to hibernate an instance if the instance is [enabled for hibernation](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html#enabling-hibernation) and it meets the [hibernation prerequisites](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html#hibernating-prerequisites) . For more information, see [Hibernate your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html) in the _Amazon Elastic Compute Cloud User Guide_ .

We don't charge usage for a stopped instance, or data transfer fees; however, your root partition Amazon EBS volume remains and continues to persist your data, and you are charged for Amazon EBS volume usage. Every time you start your Windows instance, Amazon EC2 charges you for a full instance hour. If you stop and restart your Windows instance, a new instance hour begins and Amazon EC2 charges you for another full instance hour even if you are still within the same 60-minute period when it was stopped. Every time you start your Linux instance, Amazon EC2 charges a one-minute minimum for instance usage, and thereafter charges per second for instance usage.

You can't stop or hibernate instance store-backed instances. You can't use the Stop action to hibernate Spot Instances, but you can specify that Amazon EC2 should hibernate Spot Instances when they are interrupted. For more information, see [Hibernating interrupted Spot Instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/spot-interruptions.html#hibernate-spot-instances) in the _Amazon Elastic Compute Cloud User Guide_ .

When you stop or hibernate an instance, we shut it down. You can restart your instance at any time. Before stopping or hibernating an instance, make sure it is in a state from which it can be restarted. Stopping an instance does not preserve data stored in RAM, but hibernating an instance does preserve data stored in RAM. If an instance cannot hibernate successfully, a normal shutdown occurs.

Stopping and hibernating an instance is different to rebooting or terminating it. For example, when you stop or hibernate an instance, the root device and any other devices attached to the instance persist. When you terminate an instance, the root device and any other devices attached during the instance launch are automatically deleted. For more information about the differences between rebooting, stopping, hibernating, and terminating instances, see [Instance lifecycle](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-lifecycle.html) in the _Amazon Elastic Compute Cloud User Guide_ .

When you stop an instance, we attempt to shut it down forcibly after a short while. If your instance appears stuck in the stopping state after a period of time, there may be an issue with the underlying host computer. For more information, see [Troubleshooting stopping your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/TroubleshootingInstancesStopping.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = subnet.instances.stop(
    Hibernate=True|False,
    DryRun=True|False,
    Force=True|False
)
```

Parameters

*   **Hibernate** (_boolean_) --

    Hibernates the instance if the instance was enabled for hibernation at launch. If the instance cannot hibernate successfully, a normal shutdown occurs. For more information, see [Hibernate your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html) in the _Amazon Elastic Compute Cloud User Guide_ .

    Default: false

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Force** (_boolean_) --

    Forces the instances to stop. The instances do not have an opportunity to flush file system caches or file system metadata. If you use this option, you must perform file system check and repair procedures. This option is not recommended for Windows instances.

    Default: false

Return:
- Return type: dict
- **Response Syntax**

```py
{
    'StoppingInstances': [
        {
            'CurrentState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            },
            'InstanceId': 'string',
            'PreviousState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            }
        },
    ]
}
```


**Response Structure**

*   _(dict) --_

    *   **StoppingInstances** _(list) --
        Information about the stopped instances.

        *   _(dict) --     
            Describes an instance state change.

            *   **CurrentState** _(dict) --         
                The current state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.

            *   **InstanceId** _(string) --         
                The ID of the instance.

            *   **PreviousState** _(dict) --         
                The previous state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.


terminate(_**kwargs_)

Shuts down the specified instances. This operation is idempotent; if you terminate an instance more than once, each call succeeds.

If you specify multiple instances and the request fails (for example, because of a single incorrect instance ID), none of the instances are terminated.

Terminated instances remain visible after termination (for approximately one hour).

By default, Amazon EC2 deletes all EBS volumes that were attached when the instance launched. Volumes attached after instance launch continue running.

You can stop, start, and terminate EBS-backed instances. You can only terminate instance store-backed instances. What happens to an instance differs if you stop it or terminate it. For example, when you stop an instance, the root device and any other devices attached to the instance persist. When you terminate an instance, any attached EBS volumes with the DeleteOnTermination block device mapping parameter set to true are automatically deleted. For more information about the differences between stopping and terminating instances, see [Instance lifecycle](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-lifecycle.html) in the _Amazon Elastic Compute Cloud User Guide_ .

For more information about troubleshooting, see [Troubleshooting terminating your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/TroubleshootingInstancesShuttingDown.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = subnet.instances.terminate(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'TerminatingInstances': [
        {
            'CurrentState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            },
            'InstanceId': 'string',
            'PreviousState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            }
        },
    ]
}
```


**Response Structure**

*   _(dict) --_
    *   **TerminatingInstances** _(list) --
        Information about the terminated instances.

        *   _(dict) --     
            Describes an instance state change.

            *   **CurrentState** _(dict) --         
                The current state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.

            *   **InstanceId** _(string) --         
                The ID of the instance.

            *   **PreviousState** _(dict) --         
                The previous state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.


unmonitor(_**kwargs_)

Disables detailed monitoring for a running instance. For more information, see [Monitoring your instances and volumes](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-cloudwatch.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = subnet.instances.unmonitor(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'InstanceMonitorings': [
        {
            'InstanceId': 'string',
            'Monitoring': {
                'State': 'disabled'|'disabling'|'enabled'|'pending'
            }
        },
    ]
}
```


**Response Structure**

*   _(dict) --_
    *   **InstanceMonitorings** _(list) --
        The monitoring information.

        *   _(dict) --     
            Describes the monitoring of an instance.

            *   **InstanceId** _(string) --         
                The ID of the instance.

            *   **Monitoring** _(dict) --         
                The monitoring for the instance.

                *   **State** _(string) --             
                    Indicates whether detailed monitoring is enabled. Otherwise, basic monitoring is enabled.


network_interfaces

A collection of NetworkInterface resources.A NetworkInterface Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all NetworkInterface resources in the collection.


**Request Syntax**

```py
network_interface_iterator = subnet.network_interfaces.all()
Return:
- Return type: list(ec2.NetworkInterface)
- A list of NetworkInterface resources

filter(_**kwargs_)

Creates an iterable of all NetworkInterface resources in the collection filtered by kwargs passed to method.A NetworkInterface collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
network_interface_iterator = subnet.network_interfaces.filter(
    DryRun=True|False,
    NetworkInterfaceIds=[
        'string',
    ],
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **NetworkInterfaceIds** (_list_) --

    One or more network interface IDs.

    Default: Describes all your network interfaces.

    *   _(string) --_
*   **NextToken** (_string_) -- The token to retrieve the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of items to return for this request. The request returns a token that you can specify in a subsequent call to get the next set of results. You cannot specify this parameter and the network interface IDs parameter in the same request.
Return:
- Return type: list(ec2.NetworkInterface)
- A list of NetworkInterface resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of NetworkInterface resources in the collection.


**Request Syntax**

```py
network_interface_iterator = subnet.network_interfaces.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.NetworkInterface)
- A list of NetworkInterface resources

page_size(_**kwargs_)

Creates an iterable of all NetworkInterface resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
network_interface_iterator = subnet.network_interfaces.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.NetworkInterface)
- A list of NetworkInterface resources

[Tag](#id1254)
----------------------------------------------------

_class_ EC2.Tag(_resource_id_, _key_, _value_)

A resource representing an Amazon Elastic Compute Cloud (EC2) Tag:

import boto3

ec2 = boto3.resource('ec2')
tag = ec2.Tag('resource_id','key','value')
```

Parameters

*   **resource_id** (_string_) -- The Tag's resource_id identifier. This **must** be set.
*   **key** (_string_) -- The Tag's key identifier. This **must** be set.
*   **value** (_string_) -- The Tag's value identifier. This **must** be set.

available identifiers:

*   [resource_id](#EC2.Tag.resource_id "EC2.Tag.resource_id")
*   [key](#EC2.Tag.key "EC2.Tag.key")
*   [value](#EC2.Tag.value "EC2.Tag.value")

available attributes:

*   [resource_type](#EC2.Tag.resource_type "EC2.Tag.resource_type")

available actions:

*   [delete()](#EC2.Tag.delete "EC2.Tag.delete")
*   [get_available_subresources()](#EC2.Tag.get_available_subresources "EC2.Tag.get_available_subresources")
*   [load()](#EC2.Tag.load "EC2.Tag.load")
*   [reload()](#EC2.Tag.reload "EC2.Tag.reload")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

resource_id

_(string)_ The Tag's resource_id identifier. This **must** be set.

key

_(string)_ The Tag's key identifier. This **must** be set.

value

_(string)_ The Tag's value identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.Tag.load "EC2.Tag.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

resource_type

*   _(string) --_

    The resource type.


Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

delete(_**kwargs_)

Deletes the specified set of tags from the specified set of resources.

To list the current tags, use DescribeTags . For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = tag.delete(
    DryRun=True|False,

)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_tags()](#EC2.Client.describe_tags "EC2.Client.describe_tags") to update the attributes of the Tag resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
tag.load()
- None

reload()

Calls [EC2.Client.describe_tags()](#EC2.Client.describe_tags "EC2.Client.describe_tags") to update the attributes of the Tag resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
tag.reload()
- None

[Volume](#id1255)
----------------------------------------------------------

_class_ EC2.Volume(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) Volume:

import boto3

ec2 = boto3.resource('ec2')
volume = ec2.Volume('id')
```

Parameters

**id** (_string_) -- The Volume's id identifier. This **must** be set.

available identifiers:

*   [id](#EC2.Volume.id "EC2.Volume.id")

available attributes:

*   [attachments](#EC2.Volume.attachments "EC2.Volume.attachments")
*   [availability_zone](#EC2.Volume.availability_zone "EC2.Volume.availability_zone")
*   [create_time](#EC2.Volume.create_time "EC2.Volume.create_time")
*   [encrypted](#EC2.Volume.encrypted "EC2.Volume.encrypted")
*   [fast_restored](#EC2.Volume.fast_restored "EC2.Volume.fast_restored")
*   [iops](#EC2.Volume.iops "EC2.Volume.iops")
*   [kms_key_id](#EC2.Volume.kms_key_id "EC2.Volume.kms_key_id")
*   [multi_attach_enabled](#EC2.Volume.multi_attach_enabled "EC2.Volume.multi_attach_enabled")
*   [outpost_arn](#EC2.Volume.outpost_arn "EC2.Volume.outpost_arn")
*   [size](#EC2.Volume.size "EC2.Volume.size")
*   [snapshot_id](#EC2.Volume.snapshot_id "EC2.Volume.snapshot_id")
*   [state](#EC2.Volume.state "EC2.Volume.state")
*   [tags](#EC2.Volume.tags "EC2.Volume.tags")
*   [throughput](#EC2.Volume.throughput "EC2.Volume.throughput")
*   [volume_id](#EC2.Volume.volume_id "EC2.Volume.volume_id")
*   [volume_type](#EC2.Volume.volume_type "EC2.Volume.volume_type")

available actions:

*   [attach_to_instance()](#EC2.Volume.attach_to_instance "EC2.Volume.attach_to_instance")
*   [create_snapshot()](#EC2.Volume.create_snapshot "EC2.Volume.create_snapshot")
*   [create_tags()](#EC2.Volume.create_tags "EC2.Volume.create_tags")
*   [delete()](#EC2.Volume.delete "EC2.Volume.delete")
*   [describe_attribute()](#EC2.Volume.describe_attribute "EC2.Volume.describe_attribute")
*   [describe_status()](#EC2.Volume.describe_status "EC2.Volume.describe_status")
*   [detach_from_instance()](#EC2.Volume.detach_from_instance "EC2.Volume.detach_from_instance")
*   [enable_io()](#EC2.Volume.enable_io "EC2.Volume.enable_io")
*   [get_available_subresources()](#EC2.Volume.get_available_subresources "EC2.Volume.get_available_subresources")
*   [load()](#EC2.Volume.load "EC2.Volume.load")
*   [modify_attribute()](#EC2.Volume.modify_attribute "EC2.Volume.modify_attribute")
*   [reload()](#EC2.Volume.reload "EC2.Volume.reload")

available collections:

*   [snapshots](#EC2.Volume.snapshots "EC2.Volume.snapshots")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

id

_(string)_ The Volume's id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.Volume.load "EC2.Volume.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

attachments

*   _(list) --_

    Information about the volume attachments.

    *   _(dict) --
        Describes volume attachment details.

        *   **AttachTime** _(datetime) --     
            The time stamp when the attachment initiated.

        *   **Device** _(string) --     
            The device name.

        *   **InstanceId** _(string) --     
            The ID of the instance.

        *   **State** _(string) --     
            The attachment state of the volume.

        *   **VolumeId** _(string) --     
            The ID of the volume.

        *   **DeleteOnTermination** _(boolean) --     
            Indicates whether the EBS volume is deleted on instance termination.


availability_zone

*   _(string) --_

    The Availability Zone for the volume.


create_time

*   _(datetime) --_

    The time stamp when volume creation was initiated.


encrypted

*   _(boolean) --_

    Indicates whether the volume is encrypted.


fast_restored

*   _(boolean) --_

    Indicates whether the volume was created using fast snapshot restore.


iops

*   _(integer) --_

    The number of I/O operations per second (IOPS). For gp3 , io1 , and io2 volumes, this represents the number of IOPS that are provisioned for the volume. For gp2 volumes, this represents the baseline performance of the volume and the rate at which the volume accumulates I/O credits for bursting.


kms_key_id

*   _(string) --_

    The Amazon Resource Name (ARN) of the AWS Key Management Service (AWS KMS) customer master key (CMK) that was used to protect the volume encryption key for the volume.


multi_attach_enabled

*   _(boolean) --_

    Indicates whether Amazon EBS Multi-Attach is enabled.


outpost_arn

*   _(string) --_

    The Amazon Resource Name (ARN) of the Outpost.


size

*   _(integer) --_

    The size of the volume, in GiBs.


snapshot_id

*   _(string) --_

    The snapshot from which the volume was created, if applicable.


state

*   _(string) --_

    The volume state.


tags

*   _(list) --_

    Any tags assigned to the volume.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


throughput

*   _(integer) --_

    The throughput that the volume supports, in MiB/s.


volume_id

*   _(string) --_

    The ID of the volume.


volume_type

*   _(string) --_

    The volume type.


Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

attach_to_instance(_**kwargs_)

Attaches an EBS volume to a running or stopped instance and exposes it to the instance with the specified device name.

Encrypted EBS volumes must be attached to instances that support Amazon EBS encryption. For more information, see [Amazon EBS encryption](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EBSEncryption.html) in the _Amazon Elastic Compute Cloud User Guide_ .

After you attach an EBS volume, you must make it available. For more information, see [Making an EBS volume available for use](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-using-volumes.html) .

If a volume has an AWS Marketplace product code:

*   The volume can be attached only to a stopped instance.
*   AWS Marketplace product codes are copied from the volume to the instance.
*   You must be subscribed to the product.
*   The instance type and operating system of the instance must support the product. For example, you can't detach a volume from a Windows instance and attach it to a Linux instance.

For more information, see [Attaching Amazon EBS volumes](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-attaching-volume.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = volume.attach_to_instance(
    Device='string',
    InstanceId='string',
    DryRun=True|False
)
```

Parameters

*   **Device** (_string_) -- **[REQUIRED]** The device name (for example, /dev/sdh or xvdh ).

*   **InstanceId** (_string_) -- **[REQUIRED]** The ID of the instance.

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'AttachTime': datetime(2015, 1, 1),
    'Device': 'string',
    'InstanceId': 'string',
    'State': 'attaching'|'attached'|'detaching'|'detached'|'busy',
    'VolumeId': 'string',
    'DeleteOnTermination': True|False
}
```


**Response Structure**

*   _(dict) --_

    Describes volume attachment details.

    *   **AttachTime** _(datetime) --
        The time stamp when the attachment initiated.

    *   **Device** _(string) --
        The device name.

    *   **InstanceId** _(string) --
        The ID of the instance.

    *   **State** _(string) --
        The attachment state of the volume.

    *   **VolumeId** _(string) --
        The ID of the volume.

    *   **DeleteOnTermination** _(boolean) --
        Indicates whether the EBS volume is deleted on instance termination.


create_snapshot(_**kwargs_)

Creates a snapshot of an EBS volume and stores it in Amazon S3. You can use snapshots for backups, to make copies of EBS volumes, and to save data before shutting down an instance.

When a snapshot is created, any AWS Marketplace product codes that are associated with the source volume are propagated to the snapshot.

You can take a snapshot of an attached volume that is in use. However, snapshots only capture data that has been written to your EBS volume at the time the snapshot command is issued; this might exclude any data that has been cached by any applications or the operating system. If you can pause any file systems on the volume long enough to take a snapshot, your snapshot should be complete. However, if you cannot pause all file writes to the volume, you should unmount the volume from within the instance, issue the snapshot command, and then remount the volume to ensure a consistent and complete snapshot. You may remount and use your volume while the snapshot status is pending .

To create a snapshot for EBS volumes that serve as root devices, you should stop the instance before taking the snapshot.

Snapshots that are taken from encrypted volumes are automatically encrypted. Volumes that are created from encrypted snapshots are also automatically encrypted. Your encrypted volumes and any associated snapshots always remain protected.

You can tag your snapshots during creation. For more information, see [Tagging your Amazon EC2 resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ .

For more information, see [Amazon Elastic Block Store](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/AmazonEBS.html) and [Amazon EBS encryption](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EBSEncryption.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
snapshot = volume.create_snapshot(
    Description='string',
    TagSpecifications=[
        {
            'ResourceType': 'client-vpn-endpoint'|'customer-gateway'|'dedicated-host'|'dhcp-options'|'egress-only-internet-gateway'|'elastic-ip'|'elastic-gpu'|'export-image-task'|'export-instance-task'|'fleet'|'fpga-image'|'host-reservation'|'image'|'import-image-task'|'import-snapshot-task'|'instance'|'internet-gateway'|'key-pair'|'launch-template'|'local-gateway-route-table-vpc-association'|'natgateway'|'network-acl'|'network-interface'|'network-insights-analysis'|'network-insights-path'|'placement-group'|'reserved-instances'|'route-table'|'security-group'|'snapshot'|'spot-fleet-request'|'spot-instances-request'|'subnet'|'traffic-mirror-filter'|'traffic-mirror-session'|'traffic-mirror-target'|'transit-gateway'|'transit-gateway-attachment'|'transit-gateway-connect-peer'|'transit-gateway-multicast-domain'|'transit-gateway-route-table'|'volume'|'vpc'|'vpc-peering-connection'|'vpn-connection'|'vpn-gateway'|'vpc-flow-log',
            'Tags': [
                {
                    'Key': 'string',
                    'Value': 'string'
                },
            ]
        },
    ],
    DryRun=True|False
)
```

Parameters

*   **Description** (_string_) -- A description for the snapshot.
*   **TagSpecifications** (_list_) --

    The tags to apply to the snapshot during creation.

    *   _(dict) --
        The tags to apply to a resource when the resource is being created.

        *   **ResourceType** _(string) --     
            The type of resource to tag. Currently, the resource types that support tagging on creation are: capacity-reservation | carrier-gateway | client-vpn-endpoint | customer-gateway | dedicated-host | dhcp-options | egress-only-internet-gateway | elastic-ip | elastic-gpu | export-image-task | export-instance-task | fleet | fpga-image | host-reservation | image | import-image-task | import-snapshot-task | instance | internet-gateway | ipv4pool-ec2 | ipv6pool-ec2 | key-pair | launch-template | local-gateway-route-table-vpc-association | placement-group | prefix-list | natgateway | network-acl | network-interface | reserved-instances [|](#id1122)route-table | security-group | snapshot | spot-fleet-request | spot-instances-request | snapshot | subnet | traffic-mirror-filter | traffic-mirror-session | traffic-mirror-target | transit-gateway | transit-gateway-attachment | transit-gateway-multicast-domain | transit-gateway-route-table | volume [|](#id1124)vpc | vpc-peering-connection | vpc-endpoint (for interface and gateway endpoints) | vpc-endpoint-service (for AWS PrivateLink) | vpc-flow-log | vpn-connection | vpn-gateway .

            To tag a resource after it has been created, see [CreateTags](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateTags.html) .

        *   **Tags** _(list) --     
            The tags to apply to the resource.

            *   _(dict) --         
                Describes a tag.

                *   **Key** _(string) --             
                    The key of the tag.

                    Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

                *   **Value** _(string) --             
                    The value of the tag.

                    Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: ec2.Snapshot
- Snapshot resource

create_tags(_**kwargs_)

Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources. When you specify an existing tag key, the value is overwritten with the new value. Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
tag = volume.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Tags** (_list_) -- **[REQUIRED]** The tags. The value parameter is required, but if you don't want the tag to have a value, specify the parameter with no value, and we set the value to an empty string.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: list(ec2.Tag)
- A list of Tag resources

delete(_**kwargs_)

Deletes the specified EBS volume. The volume must be in the available state (not attached to an instance).

The volume can remain in the deleting state for several minutes.

For more information, see [Deleting an Amazon EBS volume](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-deleting-volume.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = volume.delete(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

describe_attribute(_**kwargs_)

Describes the specified attribute of the specified volume. You can specify only one attribute at a time.

For more information about EBS volumes, see [Amazon EBS volumes](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/EBSVolumes.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = volume.describe_attribute(
    Attribute='autoEnableIO'|'productCodes',
    DryRun=True|False
)
```

Parameters

*   **Attribute** (_string_) -- **[REQUIRED]** The attribute of the volume. This parameter is required.

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'AutoEnableIO': {
        'Value': True|False
    },
    'ProductCodes': [
        {
            'ProductCodeId': 'string',
            'ProductCodeType': 'devpay'|'marketplace'
        },
    ],
    'VolumeId': 'string'
}
```


**Response Structure**

*   _(dict) --_

    *   **AutoEnableIO** _(dict) --
        The state of autoEnableIO attribute.

        *   **Value** _(boolean) --     
            The attribute value. The valid values are true or false .

    *   **ProductCodes** _(list) --
        A list of product codes.

        *   _(dict) --     
            Describes a product code.

            *   **ProductCodeId** _(string) --         
                The product code.

            *   **ProductCodeType** _(string) --         
                The type of product code.

    *   **VolumeId** _(string) --
        The ID of the volume.


describe_status(_**kwargs_)

Describes the status of the specified volumes. Volume status provides the result of the checks performed on your volumes to determine events that can impair the performance of your volumes. The performance of a volume can be affected if an issue occurs on the volume's underlying host. If the volume's underlying host experiences a power outage or system issue, after the system is restored, there could be data inconsistencies on the volume. Volume events notify you if this occurs. Volume actions notify you if any action needs to be taken in response to the event.

The DescribeVolumeStatus operation provides the following information about the specified volumes:

> _Status_ : Reflects the current status of the volume. The possible values are ok , impaired , warning , or insufficient-data . If all checks pass, the overall status of the volume is ok . If the check fails, the overall status is impaired . If the status is insufficient-data , then the checks might still be taking place on your volume at the time. We recommend that you retry the request. For more information about volume status, see [Monitoring the status of your volumes](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/monitoring-volume-status.html) in the _Amazon Elastic Compute Cloud User Guide_ .
>
> _Events_ : Reflect the cause of a volume status and might require you to take action. For example, if your volume returns an impaired status, then the volume event might be potential-data-inconsistency . This means that your volume has been affected by an issue with the underlying host, has all I/O operations disabled, and might have inconsistent data.
>
> _Actions_ : Reflect the actions you might have to take in response to an event. For example, if the status of the volume is impaired and the volume event shows potential-data-inconsistency , then the action shows enable-volume-io . This means that you may want to enable the I/O operations for the volume by calling the EnableVolumeIO action and then check the volume for data consistency.

Volume status is based on the volume status checks, and does not reflect the volume state. Therefore, volume status does not indicate volumes in the error state (for example, when a volume is incapable of accepting I/O.)


**Request Syntax**

```py
response = volume.describe_status(
    Filters=[
        {
            'Name': 'string',
            'Values': [
                'string',
            ]
        },
    ],
    MaxResults=123,
    NextToken='string',
    DryRun=True|False
)
```

Parameters

*   **Filters** (_list_) --

    The filters.

    *   action.code - The action code for the event (for example, enable-volume-io ).
    *   action.description - A description of the action.
    *   action.event-id - The event ID associated with the action.
    *   availability-zone - The Availability Zone of the instance.
    *   event.description - A description of the event.
    *   event.event-id - The event ID.
    *   event.event-type - The event type (for io-enabled : passed | failed ; for io-performance : io-performance:degraded | io-performance:severely-degraded | io-performance:stalled ).
    *   event.not-after - The latest end time for the event.
    *   event.not-before - The earliest start time for the event.
    *   volume-status.details-name - The cause for volume-status.status (io-enabled | io-performance ).
    *   volume-status.details-status - The status of volume-status.details-name (for io-enabled : passed | failed ; for io-performance : normal | degraded | severely-degraded | stalled ).
    *   volume-status.status - The status of the volume (ok | impaired | warning | insufficient-data ).

    *   _(dict) --
        A filter name and value pair that is used to return a more specific list of results from a describe operation. Filters can be used to match a set of resources by specific criteria, such as tags, attributes, or IDs. The filters supported by a describe operation are documented with the describe operation. For example:

        *   DescribeAvailabilityZones
        *   DescribeImages
        *   DescribeInstances
        *   DescribeKeyPairs
        *   DescribeSecurityGroups
        *   DescribeSnapshots
        *   DescribeSubnets
        *   DescribeTags
        *   DescribeVolumes
        *   DescribeVpcs

        *   **Name** _(string) --     
            The name of the filter. Filter names are case-sensitive.

        *   **Values** _(list) --     
            The filter values. Filter values are case-sensitive.

            *   _(string) --_
*   **MaxResults** (_integer_) -- The maximum number of volume results returned by DescribeVolumeStatus in paginated output. When this parameter is used, the request only returns MaxResults results in a single page along with a NextToken response element. The remaining results of the initial request can be seen by sending another request with the returned NextToken value. This value can be between 5 and 1,000; if MaxResults is given a value larger than 1,000, only 1,000 results are returned. If this parameter is not used, then DescribeVolumeStatus returns all results. You cannot specify this parameter and the volume IDs parameter in the same request.
*   **NextToken** (_string_) -- The NextToken value to include in a future DescribeVolumeStatus request. When the results of the request exceed MaxResults , this value can be used to retrieve the next page of results. This value is null when there are no more results to return.
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'NextToken': 'string',
    'VolumeStatuses': [
        {
            'Actions': [
                {
                    'Code': 'string',
                    'Description': 'string',
                    'EventId': 'string',
                    'EventType': 'string'
                },
            ],
            'AvailabilityZone': 'string',
            'OutpostArn': 'string',
            'Events': [
                {
                    'Description': 'string',
                    'EventId': 'string',
                    'EventType': 'string',
                    'NotAfter': datetime(2015, 1, 1),
                    'NotBefore': datetime(2015, 1, 1),
                    'InstanceId': 'string'
                },
            ],
            'VolumeId': 'string',
            'VolumeStatus': {
                'Details': [
                    {
                        'Name': 'io-enabled'|'io-performance',
                        'Status': 'string'
                    },
                ],
                'Status': 'ok'|'impaired'|'insufficient-data'
            },
            'AttachmentStatuses': [
                {
                    'IoPerformance': 'string',
                    'InstanceId': 'string'
                },
            ]
        },
    ]
}
```


**Response Structure**

*   _(dict) --_

    *   **NextToken** _(string) --
        The token to use to retrieve the next page of results. This value is null when there are no more results to return.

    *   **VolumeStatuses** _(list) --
        Information about the status of the volumes.

        *   _(dict) --     
            Describes the volume status.

            *   **Actions** _(list) --         
                The details of the operation.

                *   _(dict) --             
                    Describes a volume status operation code.

                    *   **Code** _(string) --                 
                        The code identifying the operation, for example, enable-volume-io .

                    *   **Description** _(string) --                 
                        A description of the operation.

                    *   **EventId** _(string) --                 
                        The ID of the event associated with this operation.

                    *   **EventType** _(string) --                 
                        The event type associated with this operation.

            *   **AvailabilityZone** _(string) --         
                The Availability Zone of the volume.

            *   **OutpostArn** _(string) --         
                The Amazon Resource Name (ARN) of the Outpost.

            *   **Events** _(list) --         
                A list of events associated with the volume.

                *   _(dict) --             
                    Describes a volume status event.

                    *   **Description** _(string) --                 
                        A description of the event.

                    *   **EventId** _(string) --                 
                        The ID of this event.

                    *   **EventType** _(string) --                 
                        The type of this event.

                    *   **NotAfter** _(datetime) --                 
                        The latest end time of the event.

                    *   **NotBefore** _(datetime) --                 
                        The earliest start time of the event.

                    *   **InstanceId** _(string) --                 
                        The ID of the instance associated with the event.

            *   **VolumeId** _(string) --         
                The volume ID.

            *   **VolumeStatus** _(dict) --         
                The volume status.

                *   **Details** _(list) --             
                    The details of the volume status.

                    *   _(dict) --                 
                        Describes a volume status.

                        *   **Name** _(string) --                     
                            The name of the volume status.

                        *   **Status** _(string) --                     
                            The intended status of the volume status.

                *   **Status** _(string) --             
                    The status of the volume.

            *   **AttachmentStatuses** _(list) --         
                Information about the instances to which the volume is attached.

                *   _(dict) --             
                    Information about the instances to which the volume is attached.

                    *   **IoPerformance** _(string) --                 
                        The maximum IOPS supported by the attached instance.

                    *   **InstanceId** _(string) --                 
                        The ID of the attached instance.


detach_from_instance(_**kwargs_)

Detaches an EBS volume from an instance. Make sure to unmount any file systems on the device within your operating system before detaching the volume. Failure to do so can result in the volume becoming stuck in the busy state while detaching. If this happens, detachment can be delayed indefinitely until you unmount the volume, force detachment, reboot the instance, or all three. If an EBS volume is the root device of an instance, it can't be detached while the instance is running. To detach the root volume, stop the instance first.

When a volume with an AWS Marketplace product code is detached from an instance, the product code is no longer associated with the instance.

For more information, see [Detaching an Amazon EBS volume](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ebs-detaching-volume.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = volume.detach_from_instance(
    Device='string',
    Force=True|False,
    InstanceId='string',
    DryRun=True|False
)
```

Parameters

*   **Device** (_string_) -- The device name.
*   **Force** (_boolean_) -- Forces detachment if the previous detachment attempt did not occur cleanly (for example, logging into an instance, unmounting the volume, and detaching normally). This option can lead to data loss or a corrupted file system. Use this option only as a last resort to detach a volume from a failed instance. The instance won't have an opportunity to flush file system caches or file system metadata. If you use this option, you must perform file system check and repair procedures.
*   **InstanceId** (_string_) -- The ID of the instance. If you are detaching a Multi-Attach enabled volume, you must specify an instance ID.
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'AttachTime': datetime(2015, 1, 1),
    'Device': 'string',
    'InstanceId': 'string',
    'State': 'attaching'|'attached'|'detaching'|'detached'|'busy',
    'VolumeId': 'string',
    'DeleteOnTermination': True|False
}
```


**Response Structure**

*   _(dict) --_

    Describes volume attachment details.

    *   **AttachTime** _(datetime) --
        The time stamp when the attachment initiated.

    *   **Device** _(string) --
        The device name.

    *   **InstanceId** _(string) --
        The ID of the instance.

    *   **State** _(string) --
        The attachment state of the volume.

    *   **VolumeId** _(string) --
        The ID of the volume.

    *   **DeleteOnTermination** _(boolean) --
        Indicates whether the EBS volume is deleted on instance termination.


enable_io(_**kwargs_)

Enables I/O operations for a volume that had I/O operations disabled because the data on the volume was potentially inconsistent.


**Request Syntax**

```py
response = volume.enable_io(
    DryRun=True|False,

)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_volumes()](#EC2.Client.describe_volumes "EC2.Client.describe_volumes") to update the attributes of the Volume resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
volume.load()
- None

modify_attribute(_**kwargs_)

Modifies a volume attribute.

By default, all I/O operations for the volume are suspended when the data on the volume is determined to be potentially inconsistent, to prevent undetectable, latent data corruption. The I/O access to the volume can be resumed by first enabling I/O access and then checking the data consistency on your volume.

You can change the default behavior to resume I/O operations. We recommend that you change this only for boot volumes or for volumes that are stateless or disposable.


**Request Syntax**

```py
response = volume.modify_attribute(
    AutoEnableIO={
        'Value': True|False
    },
    DryRun=True|False
)
```

Parameters

*   **AutoEnableIO** (_dict_) --

    Indicates whether the volume should be auto-enabled for I/O operations.

    *   **Value** _(boolean) --
        The attribute value. The valid values are true or false .

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

reload()

Calls [EC2.Client.describe_volumes()](#EC2.Client.describe_volumes "EC2.Client.describe_volumes") to update the attributes of the Volume resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
volume.reload()
- None

Collections

Collections provide an interface to iterate over and manipulate groups of resources. For more information about collections refer to the [_Resources Introduction Guide_](../../guide/collections.html#guide-collections).

snapshots

A collection of Snapshot resources.A Snapshot Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all Snapshot resources in the collection.


**Request Syntax**

```py
snapshot_iterator = volume.snapshots.all()
Return:
- Return type: list(ec2.Snapshot)
- A list of Snapshot resources

filter(_**kwargs_)

Creates an iterable of all Snapshot resources in the collection filtered by kwargs passed to method.A Snapshot collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
snapshot_iterator = volume.snapshots.filter(
    MaxResults=123,
    NextToken='string',
    OwnerIds=[
        'string',
    ],
    RestorableByUserIds=[
        'string',
    ],
    SnapshotIds=[
        'string',
    ],
    DryRun=True|False
)
```

Parameters

*   **MaxResults** (_integer_) -- The maximum number of snapshot results returned by DescribeSnapshots in paginated output. When this parameter is used, DescribeSnapshots only returns MaxResults results in a single page along with a NextToken response element. The remaining results of the initial request can be seen by sending another DescribeSnapshots request with the returned NextToken value. This value can be between 5 and 1,000; if MaxResults is given a value larger than 1,000, only 1,000 results are returned. If this parameter is not used, then DescribeSnapshots returns all results. You cannot specify this parameter and the snapshot IDs parameter in the same request.
*   **NextToken** (_string_) -- The NextToken value returned from a previous paginated DescribeSnapshots request where MaxResults was used and the results exceeded the value of that parameter. Pagination continues from the end of the previous results that returned the NextToken value. This value is null when there are no more results to return.
*   **OwnerIds** (_list_) --

    Scopes the results to snapshots with the specified owners. You can specify a combination of AWS account IDs, self , and amazon .

    *   _(string) --_
*   **RestorableByUserIds** (_list_) --

    The IDs of the AWS accounts that can create volumes from the snapshot.

    *   _(string) --_
*   **SnapshotIds** (_list_) --

    The snapshot IDs.

    Default: Describes the snapshots for which you have create volume permissions.

    *   _(string) --_
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: list(ec2.Snapshot)
- A list of Snapshot resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of Snapshot resources in the collection.


**Request Syntax**

```py
snapshot_iterator = volume.snapshots.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.Snapshot)
- A list of Snapshot resources

page_size(_**kwargs_)

Creates an iterable of all Snapshot resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
snapshot_iterator = volume.snapshots.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.Snapshot)
- A list of Snapshot resources

[Vpc](#id1256)
----------------------------------------------------

_class_ EC2.Vpc(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) Vpc:

import boto3

ec2 = boto3.resource('ec2')
vpc = ec2.Vpc('id')
```

Parameters

**id** (_string_) -- The Vpc's id identifier. This **must** be set.

available identifiers:

*   [id](#EC2.Vpc.id "EC2.Vpc.id")

available attributes:

*   [cidr_block](#EC2.Vpc.cidr_block "EC2.Vpc.cidr_block")
*   [cidr_block_association_set](#EC2.Vpc.cidr_block_association_set "EC2.Vpc.cidr_block_association_set")
*   [dhcp_options_id](#EC2.Vpc.dhcp_options_id "EC2.Vpc.dhcp_options_id")
*   [instance_tenancy](#EC2.Vpc.instance_tenancy "EC2.Vpc.instance_tenancy")
*   [ipv6_cidr_block_association_set](#EC2.Vpc.ipv6_cidr_block_association_set "EC2.Vpc.ipv6_cidr_block_association_set")
*   [is_default](#EC2.Vpc.is_default "EC2.Vpc.is_default")
*   [owner_id](#EC2.Vpc.owner_id "EC2.Vpc.owner_id")
*   [state](#EC2.Vpc.state "EC2.Vpc.state")
*   [tags](#EC2.Vpc.tags "EC2.Vpc.tags")
*   [vpc_id](#EC2.Vpc.vpc_id "EC2.Vpc.vpc_id")

available references:

*   [dhcp_options](#EC2.Vpc.dhcp_options "EC2.Vpc.dhcp_options")

available actions:

*   [associate_dhcp_options()](#EC2.Vpc.associate_dhcp_options "EC2.Vpc.associate_dhcp_options")
*   [attach_classic_link_instance()](#EC2.Vpc.attach_classic_link_instance "EC2.Vpc.attach_classic_link_instance")
*   [attach_internet_gateway()](#EC2.Vpc.attach_internet_gateway "EC2.Vpc.attach_internet_gateway")
*   [create_network_acl()](#EC2.Vpc.create_network_acl "EC2.Vpc.create_network_acl")
*   [create_route_table()](#EC2.Vpc.create_route_table "EC2.Vpc.create_route_table")
*   [create_security_group()](#EC2.Vpc.create_security_group "EC2.Vpc.create_security_group")
*   [create_subnet()](#EC2.Vpc.create_subnet "EC2.Vpc.create_subnet")
*   [create_tags()](#EC2.Vpc.create_tags "EC2.Vpc.create_tags")
*   [delete()](#EC2.Vpc.delete "EC2.Vpc.delete")
*   [describe_attribute()](#EC2.Vpc.describe_attribute "EC2.Vpc.describe_attribute")
*   [detach_classic_link_instance()](#EC2.Vpc.detach_classic_link_instance "EC2.Vpc.detach_classic_link_instance")
*   [detach_internet_gateway()](#EC2.Vpc.detach_internet_gateway "EC2.Vpc.detach_internet_gateway")
*   [disable_classic_link()](#EC2.Vpc.disable_classic_link "EC2.Vpc.disable_classic_link")
*   [enable_classic_link()](#EC2.Vpc.enable_classic_link "EC2.Vpc.enable_classic_link")
*   [get_available_subresources()](#EC2.Vpc.get_available_subresources "EC2.Vpc.get_available_subresources")
*   [load()](#EC2.Vpc.load "EC2.Vpc.load")
*   [modify_attribute()](#EC2.Vpc.modify_attribute "EC2.Vpc.modify_attribute")
*   [reload()](#EC2.Vpc.reload "EC2.Vpc.reload")
*   [request_vpc_peering_connection()](#EC2.Vpc.request_vpc_peering_connection "EC2.Vpc.request_vpc_peering_connection")

available collections:

*   [accepted_vpc_peering_connections](#EC2.Vpc.accepted_vpc_peering_connections "EC2.Vpc.accepted_vpc_peering_connections")
*   [instances](#EC2.Vpc.instances "EC2.Vpc.instances")
*   [internet_gateways](#EC2.Vpc.internet_gateways "EC2.Vpc.internet_gateways")
*   [network_acls](#EC2.Vpc.network_acls "EC2.Vpc.network_acls")
*   [network_interfaces](#EC2.Vpc.network_interfaces "EC2.Vpc.network_interfaces")
*   [requested_vpc_peering_connections](#EC2.Vpc.requested_vpc_peering_connections "EC2.Vpc.requested_vpc_peering_connections")
*   [route_tables](#EC2.Vpc.route_tables "EC2.Vpc.route_tables")
*   [security_groups](#EC2.Vpc.security_groups "EC2.Vpc.security_groups")
*   [subnets](#EC2.Vpc.subnets "EC2.Vpc.subnets")

available waiters:

*   [wait_until_available()](#EC2.Vpc.wait_until_available "EC2.Vpc.wait_until_available")
*   [wait_until_exists()](#EC2.Vpc.wait_until_exists "EC2.Vpc.wait_until_exists")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

id

_(string)_ The Vpc's id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.Vpc.load "EC2.Vpc.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

cidr_block

*   _(string) --_

    The primary IPv4 CIDR block for the VPC.


cidr_block_association_set

*   _(list) --_

    Information about the IPv4 CIDR blocks associated with the VPC.

    *   _(dict) --
        Describes an IPv4 CIDR block associated with a VPC.

        *   **AssociationId** _(string) --     
            The association ID for the IPv4 CIDR block.

        *   **CidrBlock** _(string) --     
            The IPv4 CIDR block.

        *   **CidrBlockState** _(dict) --     
            Information about the state of the CIDR block.

            *   **State** _(string) --         
                The state of the CIDR block.

            *   **StatusMessage** _(string) --         
                A message about the status of the CIDR block, if applicable.


dhcp_options_id

*   _(string) --_

    The ID of the set of DHCP options you've associated with the VPC.


instance_tenancy

*   _(string) --_

    The allowed tenancy of instances launched into the VPC.


ipv6_cidr_block_association_set

*   _(list) --_

    Information about the IPv6 CIDR blocks associated with the VPC.

    *   _(dict) --
        Describes an IPv6 CIDR block associated with a VPC.

        *   **AssociationId** _(string) --     
            The association ID for the IPv6 CIDR block.

        *   **Ipv6CidrBlock** _(string) --     
            The IPv6 CIDR block.

        *   **Ipv6CidrBlockState** _(dict) --     
            Information about the state of the CIDR block.

            *   **State** _(string) --         
                The state of the CIDR block.

            *   **StatusMessage** _(string) --         
                A message about the status of the CIDR block, if applicable.

        *   **NetworkBorderGroup** _(string) --     
            The name of the unique set of Availability Zones, Local Zones, or Wavelength Zones from which AWS advertises IP addresses, for example, us-east-1-wl1-bos-wlz-1 .

        *   **Ipv6Pool** _(string) --     
            The ID of the IPv6 address pool from which the IPv6 CIDR block is allocated.


is_default

*   _(boolean) --_

    Indicates whether the VPC is the default VPC.


owner_id

*   _(string) --_

    The ID of the AWS account that owns the VPC.


state

*   _(string) --_

    The current state of the VPC.


tags

*   _(list) --_

    Any tags assigned to the VPC.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


vpc_id

*   _(string) --_

    The ID of the VPC.


References

References are related resource instances that have a belongs-to relationship. For more information about references refer to the [_Resources Introduction Guide_](../../guide/resources.html#references-intro).

dhcp_options

(DhcpOptions) The related dhcp_options if set, otherwise None.

Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

associate_dhcp_options(_**kwargs_)

Associates a set of DHCP options (that you've previously created) with the specified VPC, or associates no DHCP options with the VPC.

After you associate the options with the VPC, any existing instances and all new instances that you launch in that VPC use the options. You don't need to restart or relaunch the instances. They automatically pick up the changes within a few hours, depending on how frequently the instance renews its DHCP lease. You can explicitly renew the lease using the operating system on the instance.

For more information, see [DHCP Options Sets](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_DHCP_Options.html) in the _Amazon Virtual Private Cloud User Guide_ .


**Request Syntax**

```py
response = vpc.associate_dhcp_options(
    DhcpOptionsId='string',
    DryRun=True|False
)
```

Parameters

*   **DhcpOptionsId** (_string_) -- **[REQUIRED]** The ID of the DHCP options set, or default to associate no DHCP options with the VPC.

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

attach_classic_link_instance(_**kwargs_)

Links an EC2-Classic instance to a ClassicLink-enabled VPC through one or more of the VPC's security groups. You cannot link an EC2-Classic instance to more than one VPC at a time. You can only link an instance that's in the running state. An instance is automatically unlinked from a VPC when it's stopped - you can link it to the VPC again when you restart it.

After you've linked an instance, you cannot change the VPC security groups that are associated with it. To change the security groups, you must first unlink the instance, and then link it again.

Linking your instance to a VPC is sometimes referred to as _attaching_ your instance.


**Request Syntax**

```py
response = vpc.attach_classic_link_instance(
    DryRun=True|False,
    Groups=[
        'string',
    ],
    InstanceId='string',

)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Groups** (_list_) -- **[REQUIRED]** The ID of one or more of the VPC's security groups. You cannot specify security groups from a different VPC.

    *   _(string) --_
*   **InstanceId** (_string_) -- **[REQUIRED]** The ID of an EC2-Classic instance to link to the ClassicLink-enabled VPC.

Return:
- Return type: dict
- **Response Syntax**

```py
{
    'Return': True|False
}
```


**Response Structure**

*   _(dict) --_

    *   **Return** _(boolean) --
        Returns true if the request succeeds; otherwise, it returns an error.


attach_internet_gateway(_**kwargs_)

Attaches an internet gateway or a virtual private gateway to a VPC, enabling connectivity between the internet and the VPC. For more information about your VPC and internet gateway, see the [Amazon Virtual Private Cloud User Guide](https://docs.aws.amazon.com/vpc/latest/userguide/) .


**Request Syntax**

```py
response = vpc.attach_internet_gateway(
    DryRun=True|False,
    InternetGatewayId='string',

)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **InternetGatewayId** (_string_) -- **[REQUIRED]** The ID of the internet gateway.

- None

create_network_acl(_**kwargs_)

Creates a network ACL in a VPC. Network ACLs provide an optional layer of security (in addition to security groups) for the instances in your VPC.

For more information, see [Network ACLs](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_ACLs.html) in the _Amazon Virtual Private Cloud User Guide_ .


**Request Syntax**

```py
network_acl = vpc.create_network_acl(
    DryRun=True|False,
    TagSpecifications=[
        {
            'ResourceType': 'client-vpn-endpoint'|'customer-gateway'|'dedicated-host'|'dhcp-options'|'egress-only-internet-gateway'|'elastic-ip'|'elastic-gpu'|'export-image-task'|'export-instance-task'|'fleet'|'fpga-image'|'host-reservation'|'image'|'import-image-task'|'import-snapshot-task'|'instance'|'internet-gateway'|'key-pair'|'launch-template'|'local-gateway-route-table-vpc-association'|'natgateway'|'network-acl'|'network-interface'|'network-insights-analysis'|'network-insights-path'|'placement-group'|'reserved-instances'|'route-table'|'security-group'|'snapshot'|'spot-fleet-request'|'spot-instances-request'|'subnet'|'traffic-mirror-filter'|'traffic-mirror-session'|'traffic-mirror-target'|'transit-gateway'|'transit-gateway-attachment'|'transit-gateway-connect-peer'|'transit-gateway-multicast-domain'|'transit-gateway-route-table'|'volume'|'vpc'|'vpc-peering-connection'|'vpn-connection'|'vpn-gateway'|'vpc-flow-log',
            'Tags': [
                {
                    'Key': 'string',
                    'Value': 'string'
                },
            ]
        },
    ]
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **TagSpecifications** (_list_) --

    The tags to assign to the network ACL.

    *   _(dict) --
        The tags to apply to a resource when the resource is being created.

        *   **ResourceType** _(string) --     
            The type of resource to tag. Currently, the resource types that support tagging on creation are: capacity-reservation | carrier-gateway | client-vpn-endpoint | customer-gateway | dedicated-host | dhcp-options | egress-only-internet-gateway | elastic-ip | elastic-gpu | export-image-task | export-instance-task | fleet | fpga-image | host-reservation | image | import-image-task | import-snapshot-task | instance | internet-gateway | ipv4pool-ec2 | ipv6pool-ec2 | key-pair | launch-template | local-gateway-route-table-vpc-association | placement-group | prefix-list | natgateway | network-acl | network-interface | reserved-instances [|](#id1143)route-table | security-group | snapshot | spot-fleet-request | spot-instances-request | snapshot | subnet | traffic-mirror-filter | traffic-mirror-session | traffic-mirror-target | transit-gateway | transit-gateway-attachment | transit-gateway-multicast-domain | transit-gateway-route-table | volume [|](#id1145)vpc | vpc-peering-connection | vpc-endpoint (for interface and gateway endpoints) | vpc-endpoint-service (for AWS PrivateLink) | vpc-flow-log | vpn-connection | vpn-gateway .

            To tag a resource after it has been created, see [CreateTags](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateTags.html) .

        *   **Tags** _(list) --     
            The tags to apply to the resource.

            *   _(dict) --         
                Describes a tag.

                *   **Key** _(string) --             
                    The key of the tag.

                    Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

                *   **Value** _(string) --             
                    The value of the tag.

                    Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: ec2.NetworkAcl
- NetworkAcl resource

create_route_table(_**kwargs_)

Creates a route table for the specified VPC. After you create a route table, you can add routes and associate the table with a subnet.

For more information, see [Route Tables](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Route_Tables.html) in the _Amazon Virtual Private Cloud User Guide_ .


**Request Syntax**

```py
route_table = vpc.create_route_table(
    DryRun=True|False,
    TagSpecifications=[
        {
            'ResourceType': 'client-vpn-endpoint'|'customer-gateway'|'dedicated-host'|'dhcp-options'|'egress-only-internet-gateway'|'elastic-ip'|'elastic-gpu'|'export-image-task'|'export-instance-task'|'fleet'|'fpga-image'|'host-reservation'|'image'|'import-image-task'|'import-snapshot-task'|'instance'|'internet-gateway'|'key-pair'|'launch-template'|'local-gateway-route-table-vpc-association'|'natgateway'|'network-acl'|'network-interface'|'network-insights-analysis'|'network-insights-path'|'placement-group'|'reserved-instances'|'route-table'|'security-group'|'snapshot'|'spot-fleet-request'|'spot-instances-request'|'subnet'|'traffic-mirror-filter'|'traffic-mirror-session'|'traffic-mirror-target'|'transit-gateway'|'transit-gateway-attachment'|'transit-gateway-connect-peer'|'transit-gateway-multicast-domain'|'transit-gateway-route-table'|'volume'|'vpc'|'vpc-peering-connection'|'vpn-connection'|'vpn-gateway'|'vpc-flow-log',
            'Tags': [
                {
                    'Key': 'string',
                    'Value': 'string'
                },
            ]
        },
    ]
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **TagSpecifications** (_list_) --

    The tags to assign to the route table.

    *   _(dict) --
        The tags to apply to a resource when the resource is being created.

        *   **ResourceType** _(string) --     
            The type of resource to tag. Currently, the resource types that support tagging on creation are: capacity-reservation | carrier-gateway | client-vpn-endpoint | customer-gateway | dedicated-host | dhcp-options | egress-only-internet-gateway | elastic-ip | elastic-gpu | export-image-task | export-instance-task | fleet | fpga-image | host-reservation | image | import-image-task | import-snapshot-task | instance | internet-gateway | ipv4pool-ec2 | ipv6pool-ec2 | key-pair | launch-template | local-gateway-route-table-vpc-association | placement-group | prefix-list | natgateway | network-acl | network-interface | reserved-instances [|](#id1148)route-table | security-group | snapshot | spot-fleet-request | spot-instances-request | snapshot | subnet | traffic-mirror-filter | traffic-mirror-session | traffic-mirror-target | transit-gateway | transit-gateway-attachment | transit-gateway-multicast-domain | transit-gateway-route-table | volume [|](#id1150)vpc | vpc-peering-connection | vpc-endpoint (for interface and gateway endpoints) | vpc-endpoint-service (for AWS PrivateLink) | vpc-flow-log | vpn-connection | vpn-gateway .

            To tag a resource after it has been created, see [CreateTags](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateTags.html) .

        *   **Tags** _(list) --     
            The tags to apply to the resource.

            *   _(dict) --         
                Describes a tag.

                *   **Key** _(string) --             
                    The key of the tag.

                    Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

                *   **Value** _(string) --             
                    The value of the tag.

                    Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: ec2.RouteTable
- RouteTable resource

create_security_group(_**kwargs_)

Creates a security group.

A security group acts as a virtual firewall for your instance to control inbound and outbound traffic. For more information, see [Amazon EC2 Security Groups](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-network-security.html) in the _Amazon Elastic Compute Cloud User Guide_ and [Security Groups for Your VPC](https://docs.aws.amazon.com/AmazonVPC/latest/UserGuide/VPC_SecurityGroups.html) in the _Amazon Virtual Private Cloud User Guide_ .

When you create a security group, you specify a friendly name of your choice. You can have a security group for use in EC2-Classic with the same name as a security group for use in a VPC. However, you can't have two security groups for use in EC2-Classic with the same name or two security groups for use in a VPC with the same name.

You have a default security group for use in EC2-Classic and a default security group for use in your VPC. If you don't specify a security group when you launch an instance, the instance is launched into the appropriate default security group. A default security group includes a default rule that grants instances unrestricted network access to each other.

You can add or remove rules from your security groups using AuthorizeSecurityGroupIngress , AuthorizeSecurityGroupEgress , RevokeSecurityGroupIngress , and RevokeSecurityGroupEgress .

For more information about VPC security group limits, see [Amazon VPC Limits](https://docs.aws.amazon.com/vpc/latest/userguide/amazon-vpc-limits.html) .


**Request Syntax**

```py
security_group = vpc.create_security_group(
    Description='string',
    GroupName='string',
    TagSpecifications=[
        {
            'ResourceType': 'client-vpn-endpoint'|'customer-gateway'|'dedicated-host'|'dhcp-options'|'egress-only-internet-gateway'|'elastic-ip'|'elastic-gpu'|'export-image-task'|'export-instance-task'|'fleet'|'fpga-image'|'host-reservation'|'image'|'import-image-task'|'import-snapshot-task'|'instance'|'internet-gateway'|'key-pair'|'launch-template'|'local-gateway-route-table-vpc-association'|'natgateway'|'network-acl'|'network-interface'|'network-insights-analysis'|'network-insights-path'|'placement-group'|'reserved-instances'|'route-table'|'security-group'|'snapshot'|'spot-fleet-request'|'spot-instances-request'|'subnet'|'traffic-mirror-filter'|'traffic-mirror-session'|'traffic-mirror-target'|'transit-gateway'|'transit-gateway-attachment'|'transit-gateway-connect-peer'|'transit-gateway-multicast-domain'|'transit-gateway-route-table'|'volume'|'vpc'|'vpc-peering-connection'|'vpn-connection'|'vpn-gateway'|'vpc-flow-log',
            'Tags': [
                {
                    'Key': 'string',
                    'Value': 'string'
                },
            ]
        },
    ],
    DryRun=True|False
)
```

Parameters

*   **Description** (_string_) -- **[REQUIRED]** A description for the security group. This is informational only.

    Constraints: Up to 255 characters in length

    Constraints for EC2-Classic: ASCII characters

    Constraints for EC2-VPC: a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

*   **GroupName** (_string_) -- **[REQUIRED]** The name of the security group.

    Constraints: Up to 255 characters in length. Cannot start with sg- .

    Constraints for EC2-Classic: ASCII characters

    Constraints for EC2-VPC: a-z, A-Z, 0-9, spaces, and ._-:/()#,@[]+=&;{}!$*

*   **TagSpecifications** (_list_) --

    The tags to assign to the security group.

    *   _(dict) --
        The tags to apply to a resource when the resource is being created.

        *   **ResourceType** _(string) --     
            The type of resource to tag. Currently, the resource types that support tagging on creation are: capacity-reservation | carrier-gateway | client-vpn-endpoint | customer-gateway | dedicated-host | dhcp-options | egress-only-internet-gateway | elastic-ip | elastic-gpu | export-image-task | export-instance-task | fleet | fpga-image | host-reservation | image | import-image-task | import-snapshot-task | instance | internet-gateway | ipv4pool-ec2 | ipv6pool-ec2 | key-pair | launch-template | local-gateway-route-table-vpc-association | placement-group | prefix-list | natgateway | network-acl | network-interface | reserved-instances [|](#id1153)route-table | security-group | snapshot | spot-fleet-request | spot-instances-request | snapshot | subnet | traffic-mirror-filter | traffic-mirror-session | traffic-mirror-target | transit-gateway | transit-gateway-attachment | transit-gateway-multicast-domain | transit-gateway-route-table | volume [|](#id1155)vpc | vpc-peering-connection | vpc-endpoint (for interface and gateway endpoints) | vpc-endpoint-service (for AWS PrivateLink) | vpc-flow-log | vpn-connection | vpn-gateway .

            To tag a resource after it has been created, see [CreateTags](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateTags.html) .

        *   **Tags** _(list) --     
            The tags to apply to the resource.

            *   _(dict) --         
                Describes a tag.

                *   **Key** _(string) --             
                    The key of the tag.

                    Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

                *   **Value** _(string) --             
                    The value of the tag.

                    Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: ec2.SecurityGroup
- SecurityGroup resource

create_subnet(_**kwargs_)

Creates a subnet in a specified VPC.

You must specify an IPv4 CIDR block for the subnet. After you create a subnet, you can't change its CIDR block. The allowed block size is between a /16 netmask (65,536 IP addresses) and /28 netmask (16 IP addresses). The CIDR block must not overlap with the CIDR block of an existing subnet in the VPC.

If you've associated an IPv6 CIDR block with your VPC, you can create a subnet with an IPv6 CIDR block that uses a /64 prefix length.

Warning

AWS reserves both the first four and the last IPv4 address in each subnet's CIDR block. They're not available for use.

If you add more than one subnet to a VPC, they're set up in a star topology with a logical router in the middle.

When you stop an instance in a subnet, it retains its private IPv4 address. It's therefore possible to have a subnet with no running instances (they're all stopped), but no remaining IP addresses available.

For more information about subnets, see [Your VPC and Subnets](https://docs.aws.amazon.com/vpc/latest/userguide/VPC_Subnets.html) in the _Amazon Virtual Private Cloud User Guide_ .


**Request Syntax**

```py
subnet = vpc.create_subnet(
    TagSpecifications=[
        {
            'ResourceType': 'client-vpn-endpoint'|'customer-gateway'|'dedicated-host'|'dhcp-options'|'egress-only-internet-gateway'|'elastic-ip'|'elastic-gpu'|'export-image-task'|'export-instance-task'|'fleet'|'fpga-image'|'host-reservation'|'image'|'import-image-task'|'import-snapshot-task'|'instance'|'internet-gateway'|'key-pair'|'launch-template'|'local-gateway-route-table-vpc-association'|'natgateway'|'network-acl'|'network-interface'|'network-insights-analysis'|'network-insights-path'|'placement-group'|'reserved-instances'|'route-table'|'security-group'|'snapshot'|'spot-fleet-request'|'spot-instances-request'|'subnet'|'traffic-mirror-filter'|'traffic-mirror-session'|'traffic-mirror-target'|'transit-gateway'|'transit-gateway-attachment'|'transit-gateway-connect-peer'|'transit-gateway-multicast-domain'|'transit-gateway-route-table'|'volume'|'vpc'|'vpc-peering-connection'|'vpn-connection'|'vpn-gateway'|'vpc-flow-log',
            'Tags': [
                {
                    'Key': 'string',
                    'Value': 'string'
                },
            ]
        },
    ],
    AvailabilityZone='string',
    AvailabilityZoneId='string',
    CidrBlock='string',
    Ipv6CidrBlock='string',
    OutpostArn='string',
    DryRun=True|False
)
```

Parameters

*   **TagSpecifications** (_list_) --

    The tags to assign to the subnet.

    *   _(dict) --
        The tags to apply to a resource when the resource is being created.

        *   **ResourceType** _(string) --     
            The type of resource to tag. Currently, the resource types that support tagging on creation are: capacity-reservation | carrier-gateway | client-vpn-endpoint | customer-gateway | dedicated-host | dhcp-options | egress-only-internet-gateway | elastic-ip | elastic-gpu | export-image-task | export-instance-task | fleet | fpga-image | host-reservation | image | import-image-task | import-snapshot-task | instance | internet-gateway | ipv4pool-ec2 | ipv6pool-ec2 | key-pair | launch-template | local-gateway-route-table-vpc-association | placement-group | prefix-list | natgateway | network-acl | network-interface | reserved-instances [|](#id1158)route-table | security-group | snapshot | spot-fleet-request | spot-instances-request | snapshot | subnet | traffic-mirror-filter | traffic-mirror-session | traffic-mirror-target | transit-gateway | transit-gateway-attachment | transit-gateway-multicast-domain | transit-gateway-route-table | volume [|](#id1160)vpc | vpc-peering-connection | vpc-endpoint (for interface and gateway endpoints) | vpc-endpoint-service (for AWS PrivateLink) | vpc-flow-log | vpn-connection | vpn-gateway .

            To tag a resource after it has been created, see [CreateTags](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateTags.html) .

        *   **Tags** _(list) --     
            The tags to apply to the resource.

            *   _(dict) --         
                Describes a tag.

                *   **Key** _(string) --             
                    The key of the tag.

                    Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

                *   **Value** _(string) --             
                    The value of the tag.

                    Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

*   **AvailabilityZone** (_string_) --

    The Availability Zone or Local Zone for the subnet.

    Default: AWS selects one for you. If you create more than one subnet in your VPC, we do not necessarily select a different zone for each subnet.

    To create a subnet in a Local Zone, set this value to the Local Zone ID, for example us-west-2-lax-1a . For information about the Regions that support Local Zones, see [Available Regions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-regions-availability-zones.html#concepts-available-regions) in the _Amazon Elastic Compute Cloud User Guide_ .

    To create a subnet in an Outpost, set this value to the Availability Zone for the Outpost and specify the Outpost ARN.

*   **AvailabilityZoneId** (_string_) -- The AZ ID or the Local Zone ID of the subnet.
*   **CidrBlock** (_string_) -- **[REQUIRED]** The IPv4 network range for the subnet, in CIDR notation. For example, 10.0.0.0/24 . We modify the specified CIDR block to its canonical form; for example, if you specify 100.68.0.18/18 , we modify it to 100.68.0.0/18 .

*   **Ipv6CidrBlock** (_string_) -- The IPv6 network range for the subnet, in CIDR notation. The subnet size must use a /64 prefix length.
*   **OutpostArn** (_string_) -- The Amazon Resource Name (ARN) of the Outpost. If you specify an Outpost ARN, you must also specify the Availability Zone of the Outpost subnet.
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: ec2.Subnet
- Subnet resource

create_tags(_**kwargs_)

Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources. When you specify an existing tag key, the value is overwritten with the new value. Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
tag = vpc.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Tags** (_list_) -- **[REQUIRED]** The tags. The value parameter is required, but if you don't want the tag to have a value, specify the parameter with no value, and we set the value to an empty string.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: list(ec2.Tag)
- A list of Tag resources

delete(_**kwargs_)

Deletes the specified VPC. You must detach or delete all gateways and resources that are associated with the VPC before you can delete it. For example, you must terminate all instances running in the VPC, delete all security groups associated with the VPC (except the default one), delete all route tables associated with the VPC (except the default one), and so on.


**Request Syntax**

```py
response = vpc.delete(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

describe_attribute(_**kwargs_)

Describes the specified attribute of the specified VPC. You can specify only one attribute at a time.


**Request Syntax**

```py
response = vpc.describe_attribute(
    Attribute='enableDnsSupport'|'enableDnsHostnames',
    DryRun=True|False
)
```

Parameters

*   **Attribute** (_string_) -- **[REQUIRED]** The VPC attribute.

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'VpcId': 'string',
    'EnableDnsHostnames': {
        'Value': True|False
    },
    'EnableDnsSupport': {
        'Value': True|False
    }
}
```


**Response Structure**

*   _(dict) --_

    *   **VpcId** _(string) --
        The ID of the VPC.

    *   **EnableDnsHostnames** _(dict) --
        Indicates whether the instances launched in the VPC get DNS hostnames. If this attribute is true , instances in the VPC get DNS hostnames; otherwise, they do not.

        *   **Value** _(boolean) --     
            The attribute value. The valid values are true or false .

    *   **EnableDnsSupport** _(dict) --
        Indicates whether DNS resolution is enabled for the VPC. If this attribute is true , the Amazon DNS server resolves DNS hostnames for your instances to their corresponding IP addresses; otherwise, it does not.

        *   **Value** _(boolean) --     
            The attribute value. The valid values are true or false .


detach_classic_link_instance(_**kwargs_)

Unlinks (detaches) a linked EC2-Classic instance from a VPC. After the instance has been unlinked, the VPC security groups are no longer associated with it. An instance is automatically unlinked from a VPC when it's stopped.


**Request Syntax**

```py
response = vpc.detach_classic_link_instance(
    DryRun=True|False,
    InstanceId='string',

)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **InstanceId** (_string_) -- **[REQUIRED]** The ID of the instance to unlink from the VPC.

Return:
- Return type: dict
- **Response Syntax**

```py
{
    'Return': True|False
}
```


**Response Structure**

*   _(dict) --_

    *   **Return** _(boolean) --
        Returns true if the request succeeds; otherwise, it returns an error.


detach_internet_gateway(_**kwargs_)

Detaches an internet gateway from a VPC, disabling connectivity between the internet and the VPC. The VPC must not contain any running instances with Elastic IP addresses or public IPv4 addresses.


**Request Syntax**

```py
response = vpc.detach_internet_gateway(
    DryRun=True|False,
    InternetGatewayId='string',

)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **InternetGatewayId** (_string_) -- **[REQUIRED]** The ID of the internet gateway.

- None

disable_classic_link(_**kwargs_)

Disables ClassicLink for a VPC. You cannot disable ClassicLink for a VPC that has EC2-Classic instances linked to it.


**Request Syntax**

```py
response = vpc.disable_classic_link(
    DryRun=True|False,

)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'Return': True|False
}
```


**Response Structure**

*   _(dict) --_
    *   **Return** _(boolean) --
        Returns true if the request succeeds; otherwise, it returns an error.


enable_classic_link(_**kwargs_)

Enables a VPC for ClassicLink. You can then link EC2-Classic instances to your ClassicLink-enabled VPC to allow communication over private IP addresses. You cannot enable your VPC for ClassicLink if any of your VPC route tables have existing routes for address ranges within the 10.0.0.0/8 IP address range, excluding local routes for VPCs in the 10.0.0.0/16 and 10.1.0.0/16 IP address ranges. For more information, see [ClassicLink](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/vpc-classiclink.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = vpc.enable_classic_link(
    DryRun=True|False,

)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'Return': True|False
}
```


**Response Structure**

*   _(dict) --_
    *   **Return** _(boolean) --
        Returns true if the request succeeds; otherwise, it returns an error.


get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_vpcs()](#EC2.Client.describe_vpcs "EC2.Client.describe_vpcs") to update the attributes of the Vpc resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
vpc.load()
- None

modify_attribute(_**kwargs_)

Modifies the specified attribute of the specified VPC.


**Request Syntax**

```py
response = vpc.modify_attribute(
    EnableDnsHostnames={
        'Value': True|False
    },
    EnableDnsSupport={
        'Value': True|False
    },

)
```

Parameters

*   **EnableDnsHostnames** (_dict_) --

    Indicates whether the instances launched in the VPC get DNS hostnames. If enabled, instances in the VPC get DNS hostnames; otherwise, they do not.

    You cannot modify the DNS resolution and DNS hostnames attributes in the same request. Use separate requests for each attribute. You can only enable DNS hostnames if you've enabled DNS support.

    *   **Value** _(boolean) --
        The attribute value. The valid values are true or false .

*   **EnableDnsSupport** (_dict_) --

    Indicates whether the DNS resolution is supported for the VPC. If enabled, queries to the Amazon provided DNS server at the 169.254.169.253 IP address, or the reserved IP address at the base of the VPC network range "plus two" succeed. If disabled, the Amazon provided DNS service in the VPC that resolves public DNS hostnames to IP addresses is not enabled.

    You cannot modify the DNS resolution and DNS hostnames attributes in the same request. Use separate requests for each attribute.

    *   **Value** _(boolean) --
        The attribute value. The valid values are true or false .

- None

reload()

Calls [EC2.Client.describe_vpcs()](#EC2.Client.describe_vpcs "EC2.Client.describe_vpcs") to update the attributes of the Vpc resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
vpc.reload()
- None

request_vpc_peering_connection(_**kwargs_)

Requests a VPC peering connection between two VPCs: a requester VPC that you own and an accepter VPC with which to create the connection. The accepter VPC can belong to another AWS account and can be in a different Region to the requester VPC. The requester VPC and accepter VPC cannot have overlapping CIDR blocks.

Note

Limitations and rules apply to a VPC peering connection. For more information, see the [limitations](https://docs.aws.amazon.com/vpc/latest/peering/vpc-peering-basics.html#vpc-peering-limitations) section in the _VPC Peering Guide_ .

The owner of the accepter VPC must accept the peering request to activate the peering connection. The VPC peering connection request expires after 7 days, after which it cannot be accepted or rejected.

If you create a VPC peering connection request between VPCs with overlapping CIDR blocks, the VPC peering connection has a status of failed .


**Request Syntax**

```py
vpc_peering_connection = vpc.request_vpc_peering_connection(
    DryRun=True|False,
    PeerOwnerId='string',
    PeerVpcId='string',
    PeerRegion='string',
    TagSpecifications=[
        {
            'ResourceType': 'client-vpn-endpoint'|'customer-gateway'|'dedicated-host'|'dhcp-options'|'egress-only-internet-gateway'|'elastic-ip'|'elastic-gpu'|'export-image-task'|'export-instance-task'|'fleet'|'fpga-image'|'host-reservation'|'image'|'import-image-task'|'import-snapshot-task'|'instance'|'internet-gateway'|'key-pair'|'launch-template'|'local-gateway-route-table-vpc-association'|'natgateway'|'network-acl'|'network-interface'|'network-insights-analysis'|'network-insights-path'|'placement-group'|'reserved-instances'|'route-table'|'security-group'|'snapshot'|'spot-fleet-request'|'spot-instances-request'|'subnet'|'traffic-mirror-filter'|'traffic-mirror-session'|'traffic-mirror-target'|'transit-gateway'|'transit-gateway-attachment'|'transit-gateway-connect-peer'|'transit-gateway-multicast-domain'|'transit-gateway-route-table'|'volume'|'vpc'|'vpc-peering-connection'|'vpn-connection'|'vpn-gateway'|'vpc-flow-log',
            'Tags': [
                {
                    'Key': 'string',
                    'Value': 'string'
                },
            ]
        },
    ]
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **PeerOwnerId** (_string_) --

    The AWS account ID of the owner of the accepter VPC.

    Default: Your AWS account ID

*   **PeerVpcId** (_string_) -- The ID of the VPC with which you are creating the VPC peering connection. You must specify this parameter in the request.
*   **PeerRegion** (_string_) --

    The Region code for the accepter VPC, if the accepter VPC is located in a Region other than the Region in which you make the request.

    Default: The Region in which you make the request.

*   **TagSpecifications** (_list_) --

    The tags to assign to the peering connection.

    *   _(dict) --
        The tags to apply to a resource when the resource is being created.

        *   **ResourceType** _(string) --     
            The type of resource to tag. Currently, the resource types that support tagging on creation are: capacity-reservation | carrier-gateway | client-vpn-endpoint | customer-gateway | dedicated-host | dhcp-options | egress-only-internet-gateway | elastic-ip | elastic-gpu | export-image-task | export-instance-task | fleet | fpga-image | host-reservation | image | import-image-task | import-snapshot-task | instance | internet-gateway | ipv4pool-ec2 | ipv6pool-ec2 | key-pair | launch-template | local-gateway-route-table-vpc-association | placement-group | prefix-list | natgateway | network-acl | network-interface | reserved-instances [|](#id1173)route-table | security-group | snapshot | spot-fleet-request | spot-instances-request | snapshot | subnet | traffic-mirror-filter | traffic-mirror-session | traffic-mirror-target | transit-gateway | transit-gateway-attachment | transit-gateway-multicast-domain | transit-gateway-route-table | volume [|](#id1175)vpc | vpc-peering-connection | vpc-endpoint (for interface and gateway endpoints) | vpc-endpoint-service (for AWS PrivateLink) | vpc-flow-log | vpn-connection | vpn-gateway .

            To tag a resource after it has been created, see [CreateTags](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/API_CreateTags.html) .

        *   **Tags** _(list) --     
            The tags to apply to the resource.

            *   _(dict) --         
                Describes a tag.

                *   **Key** _(string) --             
                    The key of the tag.

                    Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

                *   **Value** _(string) --             
                    The value of the tag.

                    Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

Return:
- Return type: ec2.VpcPeeringConnection
- VpcPeeringConnection resource

Collections

Collections provide an interface to iterate over and manipulate groups of resources. For more information about collections refer to the [_Resources Introduction Guide_](../../guide/collections.html#guide-collections).

accepted_vpc_peering_connections

A collection of VpcPeeringConnection resources.A VpcPeeringConnection Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all VpcPeeringConnection resources in the collection.


**Request Syntax**

```py
vpc_peering_connection_iterator = vpc.accepted_vpc_peering_connections.all()
Return:
- Return type: list(ec2.VpcPeeringConnection)
- A list of VpcPeeringConnection resources

filter(_**kwargs_)

Creates an iterable of all VpcPeeringConnection resources in the collection filtered by kwargs passed to method.A VpcPeeringConnection collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
vpc_peering_connection_iterator = vpc.accepted_vpc_peering_connections.filter(
    DryRun=True|False,
    VpcPeeringConnectionIds=[
        'string',
    ],
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **VpcPeeringConnectionIds** (_list_) --

    One or more VPC peering connection IDs.

    Default: Describes all your VPC peering connections.

    *   _(string) --_
*   **NextToken** (_string_) -- The token for the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of results to return with a single call. To retrieve the remaining results, make another call with the returned nextToken value.
Return:
- Return type: list(ec2.VpcPeeringConnection)
- A list of VpcPeeringConnection resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of VpcPeeringConnection resources in the collection.


**Request Syntax**

```py
vpc_peering_connection_iterator = vpc.accepted_vpc_peering_connections.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.VpcPeeringConnection)
- A list of VpcPeeringConnection resources

page_size(_**kwargs_)

Creates an iterable of all VpcPeeringConnection resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
vpc_peering_connection_iterator = vpc.accepted_vpc_peering_connections.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.VpcPeeringConnection)
- A list of VpcPeeringConnection resources

instances

A collection of Instance resources.A Instance Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all Instance resources in the collection.


**Request Syntax**

```py
instance_iterator = vpc.instances.all()
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

create_tags(_**kwargs_)

Adds or overwrites only the specified tags for the specified Amazon EC2 resource or resources. When you specify an existing tag key, the value is overwritten with the new value. Each resource can have a maximum of 50 tags. Each tag consists of a key and optional value. Tag keys must be unique per resource.

For more information about tags, see [Tagging Your Resources](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Using_Tags.html) in the _Amazon Elastic Compute Cloud User Guide_ . For more information about creating IAM policies that control users' access to resources based on tags, see [Supported Resource-Level Permissions for Amazon EC2 API Actions](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-supported-iam-actions-resources.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = vpc.instances.create_tags(
    DryRun=True|False,
    Tags=[
        {
            'Key': 'string',
            'Value': 'string'
        },
    ]
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Tags** (_list_) -- **[REQUIRED]** The tags. The value parameter is required, but if you don't want the tag to have a value, specify the parameter with no value, and we set the value to an empty string.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

- None

filter(_**kwargs_)

Creates an iterable of all Instance resources in the collection filtered by kwargs passed to method.A Instance collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
instance_iterator = vpc.instances.filter(
    InstanceIds=[
        'string',
    ],
    DryRun=True|False,
    MaxResults=123,
    NextToken='string'
)
```

Parameters

*   **InstanceIds** (_list_) --

    The instance IDs.

    Default: Describes all your instances.

    *   _(string) --_
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **MaxResults** (_integer_) -- The maximum number of results to return in a single call. To retrieve the remaining results, make another call with the returned NextToken value. This value can be between 5 and 1000. You cannot specify this parameter and the instance IDs parameter in the same call.
*   **NextToken** (_string_) -- The token to request the next page of results.
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of Instance resources in the collection.


**Request Syntax**

```py
instance_iterator = vpc.instances.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

monitor(_**kwargs_)

Enables detailed monitoring for a running instance. Otherwise, basic monitoring is enabled. For more information, see [Monitoring your instances and volumes](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-cloudwatch.html) in the _Amazon Elastic Compute Cloud User Guide_ .

To disable detailed monitoring, see .


**Request Syntax**

```py
response = vpc.instances.monitor(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'InstanceMonitorings': [
        {
            'InstanceId': 'string',
            'Monitoring': {
                'State': 'disabled'|'disabling'|'enabled'|'pending'
            }
        },
    ]
}
```


**Response Structure**

*   _(dict) --_
    *   **InstanceMonitorings** _(list) --
        The monitoring information.

        *   _(dict) --     
            Describes the monitoring of an instance.

            *   **InstanceId** _(string) --         
                The ID of the instance.

            *   **Monitoring** _(dict) --         
                The monitoring for the instance.

                *   **State** _(string) --             
                    Indicates whether detailed monitoring is enabled. Otherwise, basic monitoring is enabled.


page_size(_**kwargs_)

Creates an iterable of all Instance resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
instance_iterator = vpc.instances.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.Instance)
- A list of Instance resources

reboot(_**kwargs_)

Requests a reboot of the specified instances. This operation is asynchronous; it only queues a request to reboot the specified instances. The operation succeeds if the instances are valid and belong to you. Requests to reboot terminated instances are ignored.

If an instance does not cleanly shut down within a few minutes, Amazon EC2 performs a hard reboot.

For more information about troubleshooting, see [Getting console output and rebooting instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/instance-console.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = vpc.instances.reboot(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

start(_**kwargs_)

Starts an Amazon EBS-backed instance that you've previously stopped.

Instances that use Amazon EBS volumes as their root devices can be quickly stopped and started. When an instance is stopped, the compute resources are released and you are not billed for instance usage. However, your root partition Amazon EBS volume remains and continues to persist your data, and you are charged for Amazon EBS volume usage. You can restart your instance at any time. Every time you start your Windows instance, Amazon EC2 charges you for a full instance hour. If you stop and restart your Windows instance, a new instance hour begins and Amazon EC2 charges you for another full instance hour even if you are still within the same 60-minute period when it was stopped. Every time you start your Linux instance, Amazon EC2 charges a one-minute minimum for instance usage, and thereafter charges per second for instance usage.

Before stopping an instance, make sure it is in a state from which it can be restarted. Stopping an instance does not preserve data stored in RAM.

Performing this operation on an instance that uses an instance store as its root device returns an error.

For more information, see [Stopping instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Stop_Start.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = vpc.instances.start(
    AdditionalInfo='string',
    DryRun=True|False
)
```

Parameters

*   **AdditionalInfo** (_string_) -- Reserved.
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'StartingInstances': [
        {
            'CurrentState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            },
            'InstanceId': 'string',
            'PreviousState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            }
        },
    ]
}
```


**Response Structure**

*   _(dict) --_

    *   **StartingInstances** _(list) --
        Information about the started instances.

        *   _(dict) --     
            Describes an instance state change.

            *   **CurrentState** _(dict) --         
                The current state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.

            *   **InstanceId** _(string) --         
                The ID of the instance.

            *   **PreviousState** _(dict) --         
                The previous state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.


stop(_**kwargs_)

Stops an Amazon EBS-backed instance.

You can use the Stop action to hibernate an instance if the instance is [enabled for hibernation](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html#enabling-hibernation) and it meets the [hibernation prerequisites](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html#hibernating-prerequisites) . For more information, see [Hibernate your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html) in the _Amazon Elastic Compute Cloud User Guide_ .

We don't charge usage for a stopped instance, or data transfer fees; however, your root partition Amazon EBS volume remains and continues to persist your data, and you are charged for Amazon EBS volume usage. Every time you start your Windows instance, Amazon EC2 charges you for a full instance hour. If you stop and restart your Windows instance, a new instance hour begins and Amazon EC2 charges you for another full instance hour even if you are still within the same 60-minute period when it was stopped. Every time you start your Linux instance, Amazon EC2 charges a one-minute minimum for instance usage, and thereafter charges per second for instance usage.

You can't stop or hibernate instance store-backed instances. You can't use the Stop action to hibernate Spot Instances, but you can specify that Amazon EC2 should hibernate Spot Instances when they are interrupted. For more information, see [Hibernating interrupted Spot Instances](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/spot-interruptions.html#hibernate-spot-instances) in the _Amazon Elastic Compute Cloud User Guide_ .

When you stop or hibernate an instance, we shut it down. You can restart your instance at any time. Before stopping or hibernating an instance, make sure it is in a state from which it can be restarted. Stopping an instance does not preserve data stored in RAM, but hibernating an instance does preserve data stored in RAM. If an instance cannot hibernate successfully, a normal shutdown occurs.

Stopping and hibernating an instance is different to rebooting or terminating it. For example, when you stop or hibernate an instance, the root device and any other devices attached to the instance persist. When you terminate an instance, the root device and any other devices attached during the instance launch are automatically deleted. For more information about the differences between rebooting, stopping, hibernating, and terminating instances, see [Instance lifecycle](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-lifecycle.html) in the _Amazon Elastic Compute Cloud User Guide_ .

When you stop an instance, we attempt to shut it down forcibly after a short while. If your instance appears stuck in the stopping state after a period of time, there may be an issue with the underlying host computer. For more information, see [Troubleshooting stopping your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/TroubleshootingInstancesStopping.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = vpc.instances.stop(
    Hibernate=True|False,
    DryRun=True|False,
    Force=True|False
)
```

Parameters

*   **Hibernate** (_boolean_) --

    Hibernates the instance if the instance was enabled for hibernation at launch. If the instance cannot hibernate successfully, a normal shutdown occurs. For more information, see [Hibernate your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/Hibernate.html) in the _Amazon Elastic Compute Cloud User Guide_ .

    Default: false

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **Force** (_boolean_) --

    Forces the instances to stop. The instances do not have an opportunity to flush file system caches or file system metadata. If you use this option, you must perform file system check and repair procedures. This option is not recommended for Windows instances.

    Default: false

Return:
- Return type: dict
- **Response Syntax**

```py
{
    'StoppingInstances': [
        {
            'CurrentState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            },
            'InstanceId': 'string',
            'PreviousState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            }
        },
    ]
}
```


**Response Structure**

*   _(dict) --_

    *   **StoppingInstances** _(list) --
        Information about the stopped instances.

        *   _(dict) --     
            Describes an instance state change.

            *   **CurrentState** _(dict) --         
                The current state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.

            *   **InstanceId** _(string) --         
                The ID of the instance.

            *   **PreviousState** _(dict) --         
                The previous state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.


terminate(_**kwargs_)

Shuts down the specified instances. This operation is idempotent; if you terminate an instance more than once, each call succeeds.

If you specify multiple instances and the request fails (for example, because of a single incorrect instance ID), none of the instances are terminated.

Terminated instances remain visible after termination (for approximately one hour).

By default, Amazon EC2 deletes all EBS volumes that were attached when the instance launched. Volumes attached after instance launch continue running.

You can stop, start, and terminate EBS-backed instances. You can only terminate instance store-backed instances. What happens to an instance differs if you stop it or terminate it. For example, when you stop an instance, the root device and any other devices attached to the instance persist. When you terminate an instance, any attached EBS volumes with the DeleteOnTermination block device mapping parameter set to true are automatically deleted. For more information about the differences between stopping and terminating instances, see [Instance lifecycle](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/ec2-instance-lifecycle.html) in the _Amazon Elastic Compute Cloud User Guide_ .

For more information about troubleshooting, see [Troubleshooting terminating your instance](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/TroubleshootingInstancesShuttingDown.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = vpc.instances.terminate(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'TerminatingInstances': [
        {
            'CurrentState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            },
            'InstanceId': 'string',
            'PreviousState': {
                'Code': 123,
                'Name': 'pending'|'running'|'shutting-down'|'terminated'|'stopping'|'stopped'
            }
        },
    ]
}
```


**Response Structure**

*   _(dict) --_
    *   **TerminatingInstances** _(list) --
        Information about the terminated instances.

        *   _(dict) --     
            Describes an instance state change.

            *   **CurrentState** _(dict) --         
                The current state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.

            *   **InstanceId** _(string) --         
                The ID of the instance.

            *   **PreviousState** _(dict) --         
                The previous state of the instance.

                *   **Code** _(integer) --             
                    The state of the instance as a 16-bit unsigned integer.

                    The high byte is all of the bits between 2^8 and (2^16)-1, which equals decimal values between 256 and 65,535. These numerical values are used for internal purposes and should be ignored.

                    The low byte is all of the bits between 2^0 and (2^8)-1, which equals decimal values between 0 and 255.

                    The valid values for instance-state-code will all be in the range of the low byte and they are:

                    *   0 : pending
                    *   16 : running
                    *   32 : shutting-down
                    *   48 : terminated
                    *   64 : stopping
                    *   80 : stopped

                    You can ignore the high byte value by zeroing out all of the bits above 2^8 or 256 in decimal.

                *   **Name** _(string) --             
                    The current state of the instance.


unmonitor(_**kwargs_)

Disables detailed monitoring for a running instance. For more information, see [Monitoring your instances and volumes](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/using-cloudwatch.html) in the _Amazon Elastic Compute Cloud User Guide_ .


**Request Syntax**

```py
response = vpc.instances.unmonitor(
    DryRun=True|False
)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'InstanceMonitorings': [
        {
            'InstanceId': 'string',
            'Monitoring': {
                'State': 'disabled'|'disabling'|'enabled'|'pending'
            }
        },
    ]
}
```


**Response Structure**

*   _(dict) --_
    *   **InstanceMonitorings** _(list) --
        The monitoring information.

        *   _(dict) --     
            Describes the monitoring of an instance.

            *   **InstanceId** _(string) --         
                The ID of the instance.

            *   **Monitoring** _(dict) --         
                The monitoring for the instance.

                *   **State** _(string) --             
                    Indicates whether detailed monitoring is enabled. Otherwise, basic monitoring is enabled.


internet_gateways

A collection of InternetGateway resources.A InternetGateway Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all InternetGateway resources in the collection.


**Request Syntax**

```py
internet_gateway_iterator = vpc.internet_gateways.all()
Return:
- Return type: list(ec2.InternetGateway)
- A list of InternetGateway resources

filter(_**kwargs_)

Creates an iterable of all InternetGateway resources in the collection filtered by kwargs passed to method.A InternetGateway collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
internet_gateway_iterator = vpc.internet_gateways.filter(
    DryRun=True|False,
    InternetGatewayIds=[
        'string',
    ],
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **InternetGatewayIds** (_list_) --

    One or more internet gateway IDs.

    Default: Describes all your internet gateways.

    *   _(string) --_
*   **NextToken** (_string_) -- The token for the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of results to return with a single call. To retrieve the remaining results, make another call with the returned nextToken value.
Return:
- Return type: list(ec2.InternetGateway)
- A list of InternetGateway resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of InternetGateway resources in the collection.


**Request Syntax**

```py
internet_gateway_iterator = vpc.internet_gateways.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.InternetGateway)
- A list of InternetGateway resources

page_size(_**kwargs_)

Creates an iterable of all InternetGateway resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
internet_gateway_iterator = vpc.internet_gateways.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.InternetGateway)
- A list of InternetGateway resources

network_acls

A collection of NetworkAcl resources.A NetworkAcl Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all NetworkAcl resources in the collection.


**Request Syntax**

```py
network_acl_iterator = vpc.network_acls.all()
Return:
- Return type: list(ec2.NetworkAcl)
- A list of NetworkAcl resources

filter(_**kwargs_)

Creates an iterable of all NetworkAcl resources in the collection filtered by kwargs passed to method.A NetworkAcl collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
network_acl_iterator = vpc.network_acls.filter(
    DryRun=True|False,
    NetworkAclIds=[
        'string',
    ],
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **NetworkAclIds** (_list_) --

    One or more network ACL IDs.

    Default: Describes all your network ACLs.

    *   _(string) --_
*   **NextToken** (_string_) -- The token for the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of results to return with a single call. To retrieve the remaining results, make another call with the returned nextToken value.
Return:
- Return type: list(ec2.NetworkAcl)
- A list of NetworkAcl resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of NetworkAcl resources in the collection.


**Request Syntax**

```py
network_acl_iterator = vpc.network_acls.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.NetworkAcl)
- A list of NetworkAcl resources

page_size(_**kwargs_)

Creates an iterable of all NetworkAcl resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
network_acl_iterator = vpc.network_acls.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.NetworkAcl)
- A list of NetworkAcl resources

network_interfaces

A collection of NetworkInterface resources.A NetworkInterface Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all NetworkInterface resources in the collection.


**Request Syntax**

```py
network_interface_iterator = vpc.network_interfaces.all()
Return:
- Return type: list(ec2.NetworkInterface)
- A list of NetworkInterface resources

filter(_**kwargs_)

Creates an iterable of all NetworkInterface resources in the collection filtered by kwargs passed to method.A NetworkInterface collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
network_interface_iterator = vpc.network_interfaces.filter(
    DryRun=True|False,
    NetworkInterfaceIds=[
        'string',
    ],
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **NetworkInterfaceIds** (_list_) --

    One or more network interface IDs.

    Default: Describes all your network interfaces.

    *   _(string) --_
*   **NextToken** (_string_) -- The token to retrieve the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of items to return for this request. The request returns a token that you can specify in a subsequent call to get the next set of results. You cannot specify this parameter and the network interface IDs parameter in the same request.
Return:
- Return type: list(ec2.NetworkInterface)
- A list of NetworkInterface resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of NetworkInterface resources in the collection.


**Request Syntax**

```py
network_interface_iterator = vpc.network_interfaces.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.NetworkInterface)
- A list of NetworkInterface resources

page_size(_**kwargs_)

Creates an iterable of all NetworkInterface resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
network_interface_iterator = vpc.network_interfaces.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.NetworkInterface)
- A list of NetworkInterface resources

requested_vpc_peering_connections

A collection of VpcPeeringConnection resources.A VpcPeeringConnection Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all VpcPeeringConnection resources in the collection.


**Request Syntax**

```py
vpc_peering_connection_iterator = vpc.requested_vpc_peering_connections.all()
Return:
- Return type: list(ec2.VpcPeeringConnection)
- A list of VpcPeeringConnection resources

filter(_**kwargs_)

Creates an iterable of all VpcPeeringConnection resources in the collection filtered by kwargs passed to method.A VpcPeeringConnection collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
vpc_peering_connection_iterator = vpc.requested_vpc_peering_connections.filter(
    DryRun=True|False,
    VpcPeeringConnectionIds=[
        'string',
    ],
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **VpcPeeringConnectionIds** (_list_) --

    One or more VPC peering connection IDs.

    Default: Describes all your VPC peering connections.

    *   _(string) --_
*   **NextToken** (_string_) -- The token for the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of results to return with a single call. To retrieve the remaining results, make another call with the returned nextToken value.
Return:
- Return type: list(ec2.VpcPeeringConnection)
- A list of VpcPeeringConnection resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of VpcPeeringConnection resources in the collection.


**Request Syntax**

```py
vpc_peering_connection_iterator = vpc.requested_vpc_peering_connections.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.VpcPeeringConnection)
- A list of VpcPeeringConnection resources

page_size(_**kwargs_)

Creates an iterable of all VpcPeeringConnection resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
vpc_peering_connection_iterator = vpc.requested_vpc_peering_connections.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.VpcPeeringConnection)
- A list of VpcPeeringConnection resources

route_tables

A collection of RouteTable resources.A RouteTable Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all RouteTable resources in the collection.


**Request Syntax**

```py
route_table_iterator = vpc.route_tables.all()
Return:
- Return type: list(ec2.RouteTable)
- A list of RouteTable resources

filter(_**kwargs_)

Creates an iterable of all RouteTable resources in the collection filtered by kwargs passed to method.A RouteTable collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
route_table_iterator = vpc.route_tables.filter(
    DryRun=True|False,
    RouteTableIds=[
        'string',
    ],
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **RouteTableIds** (_list_) --

    One or more route table IDs.

    Default: Describes all your route tables.

    *   _(string) --_
*   **NextToken** (_string_) -- The token for the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of results to return with a single call. To retrieve the remaining results, make another call with the returned nextToken value.
Return:
- Return type: list(ec2.RouteTable)
- A list of RouteTable resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of RouteTable resources in the collection.


**Request Syntax**

```py
route_table_iterator = vpc.route_tables.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.RouteTable)
- A list of RouteTable resources

page_size(_**kwargs_)

Creates an iterable of all RouteTable resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
route_table_iterator = vpc.route_tables.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.RouteTable)
- A list of RouteTable resources

security_groups

A collection of SecurityGroup resources.A SecurityGroup Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all SecurityGroup resources in the collection.


**Request Syntax**

```py
security_group_iterator = vpc.security_groups.all()
Return:
- Return type: list(ec2.SecurityGroup)
- A list of SecurityGroup resources

filter(_**kwargs_)

Creates an iterable of all SecurityGroup resources in the collection filtered by kwargs passed to method.A SecurityGroup collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
security_group_iterator = vpc.security_groups.filter(
    GroupIds=[
        'string',
    ],
    GroupNames=[
        'string',
    ],
    DryRun=True|False,
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **GroupIds** (_list_) --

    The IDs of the security groups. Required for security groups in a nondefault VPC.

    Default: Describes all your security groups.

    *   _(string) --_
*   **GroupNames** (_list_) --

    [EC2-Classic and default VPC only] The names of the security groups. You can specify either the security group name or the security group ID. For security groups in a nondefault VPC, use the group-name filter to describe security groups by name.

    Default: Describes all your security groups.

    *   _(string) --_
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **NextToken** (_string_) -- The token to request the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of results to return in a single call. To retrieve the remaining results, make another request with the returned NextToken value. This value can be between 5 and 1000. If this parameter is not specified, then all results are returned.
Return:
- Return type: list(ec2.SecurityGroup)
- A list of SecurityGroup resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of SecurityGroup resources in the collection.


**Request Syntax**

```py
security_group_iterator = vpc.security_groups.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.SecurityGroup)
- A list of SecurityGroup resources

page_size(_**kwargs_)

Creates an iterable of all SecurityGroup resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
security_group_iterator = vpc.security_groups.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.SecurityGroup)
- A list of SecurityGroup resources

subnets

A collection of Subnet resources.A Subnet Collection will include all resources by default, and extreme caution should be taken when performing actions on all resources.

all()

Creates an iterable of all Subnet resources in the collection.


**Request Syntax**

```py
subnet_iterator = vpc.subnets.all()
Return:
- Return type: list(ec2.Subnet)
- A list of Subnet resources

filter(_**kwargs_)

Creates an iterable of all Subnet resources in the collection filtered by kwargs passed to method.A Subnet collection will include all resources by default if no filters are provided, and extreme caution should be taken when performing actions on all resources.


**Request Syntax**

```py
subnet_iterator = vpc.subnets.filter(
    SubnetIds=[
        'string',
    ],
    DryRun=True|False,
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **SubnetIds** (_list_) --

    One or more subnet IDs.

    Default: Describes all your subnets.

    *   _(string) --_
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **NextToken** (_string_) -- The token for the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of results to return with a single call. To retrieve the remaining results, make another call with the returned nextToken value.
Return:
- Return type: list(ec2.Subnet)
- A list of Subnet resources

limit(_**kwargs_)

Creates an iterable up to a specified amount of Subnet resources in the collection.


**Request Syntax**

```py
subnet_iterator = vpc.subnets.limit(
    count=123
)
```

Parameters

**count** (_integer_) -- The limit to the number of resources in the iterable.
Return:
- Return type: list(ec2.Subnet)
- A list of Subnet resources

page_size(_**kwargs_)

Creates an iterable of all Subnet resources in the collection, but limits the number of items returned by each service call by the specified amount.


**Request Syntax**

```py
subnet_iterator = vpc.subnets.page_size(
    count=123
)
```

Parameters

**count** (_integer_) -- The number of items returned by each service call
Return:
- Return type: list(ec2.Subnet)
- A list of Subnet resources

Waiters

Waiters provide an interface to wait for a resource to reach a specific state. For more information about waiters refer to the [_Resources Introduction Guide_](../../guide/resources.html#waiters-intro).

wait_until_available(_**kwargs_)

Waits until this Vpc is available. This method calls EC2.Waiter.vpc_available.wait() which polls. [EC2.Client.describe_vpcs()](#EC2.Client.describe_vpcs "EC2.Client.describe_vpcs") every 15 seconds until a successful state is reached. An error is returned after 40 failed checks.


**Request Syntax**

```py
vpc.wait_until_available(
    Filters=[
        {
            'Name': 'string',
            'Values': [
                'string',
            ]
        },
    ],
    DryRun=True|False,
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **Filters** (_list_) --

    One or more filters.

    *   cidr - The primary IPv4 CIDR block of the VPC. The CIDR block you specify must exactly match the VPC's CIDR block for information to be returned for the VPC. Must contain the slash followed by one or two digits (for example, /28 ).
    *   cidr-block-association.cidr-block - An IPv4 CIDR block associated with the VPC.
    *   cidr-block-association.association-id - The association ID for an IPv4 CIDR block associated with the VPC.
    *   cidr-block-association.state - The state of an IPv4 CIDR block associated with the VPC.
    *   dhcp-options-id - The ID of a set of DHCP options.
    *   ipv6-cidr-block-association.ipv6-cidr-block - An IPv6 CIDR block associated with the VPC.
    *   ipv6-cidr-block-association.ipv6-pool - The ID of the IPv6 address pool from which the IPv6 CIDR block is allocated.
    *   ipv6-cidr-block-association.association-id - The association ID for an IPv6 CIDR block associated with the VPC.
    *   ipv6-cidr-block-association.state - The state of an IPv6 CIDR block associated with the VPC.
    *   isDefault - Indicates whether the VPC is the default VPC.
    *   owner-id - The ID of the AWS account that owns the VPC.
    *   state - The state of the VPC (pending | available ).
    *   tag :<key> - The key/value combination of a tag assigned to the resource. Use the tag key in the filter name and the tag value as the filter value. For example, to find all resources that have a tag with the key Owner and the value TeamA , specify tag:Owner for the filter name and TeamA for the filter value.
    *   tag-key - The key of a tag assigned to the resource. Use this filter to find all resources assigned a tag with a specific key, regardless of the tag value.
    *   vpc-id - The ID of the VPC.

    *   _(dict) --
        A filter name and value pair that is used to return a more specific list of results from a describe operation. Filters can be used to match a set of resources by specific criteria, such as tags, attributes, or IDs. The filters supported by a describe operation are documented with the describe operation. For example:

        *   DescribeAvailabilityZones
        *   DescribeImages
        *   DescribeInstances
        *   DescribeKeyPairs
        *   DescribeSecurityGroups
        *   DescribeSnapshots
        *   DescribeSubnets
        *   DescribeTags
        *   DescribeVolumes
        *   DescribeVpcs

        *   **Name** _(string) --     
            The name of the filter. Filter names are case-sensitive.

        *   **Values** _(list) --     
            The filter values. Filter values are case-sensitive.

            *   _(string) --_
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **NextToken** (_string_) -- The token for the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of results to return with a single call. To retrieve the remaining results, make another call with the returned nextToken value.
- None

wait_until_exists(_**kwargs_)

Waits until this Vpc is exists. This method calls EC2.Waiter.vpc_exists.wait() which polls. [EC2.Client.describe_vpcs()](#EC2.Client.describe_vpcs "EC2.Client.describe_vpcs") every 1 seconds until a successful state is reached. An error is returned after 5 failed checks.


**Request Syntax**

```py
vpc.wait_until_exists(
    Filters=[
        {
            'Name': 'string',
            'Values': [
                'string',
            ]
        },
    ],
    DryRun=True|False,
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **Filters** (_list_) --

    One or more filters.

    *   cidr - The primary IPv4 CIDR block of the VPC. The CIDR block you specify must exactly match the VPC's CIDR block for information to be returned for the VPC. Must contain the slash followed by one or two digits (for example, /28 ).
    *   cidr-block-association.cidr-block - An IPv4 CIDR block associated with the VPC.
    *   cidr-block-association.association-id - The association ID for an IPv4 CIDR block associated with the VPC.
    *   cidr-block-association.state - The state of an IPv4 CIDR block associated with the VPC.
    *   dhcp-options-id - The ID of a set of DHCP options.
    *   ipv6-cidr-block-association.ipv6-cidr-block - An IPv6 CIDR block associated with the VPC.
    *   ipv6-cidr-block-association.ipv6-pool - The ID of the IPv6 address pool from which the IPv6 CIDR block is allocated.
    *   ipv6-cidr-block-association.association-id - The association ID for an IPv6 CIDR block associated with the VPC.
    *   ipv6-cidr-block-association.state - The state of an IPv6 CIDR block associated with the VPC.
    *   isDefault - Indicates whether the VPC is the default VPC.
    *   owner-id - The ID of the AWS account that owns the VPC.
    *   state - The state of the VPC (pending | available ).
    *   tag :<key> - The key/value combination of a tag assigned to the resource. Use the tag key in the filter name and the tag value as the filter value. For example, to find all resources that have a tag with the key Owner and the value TeamA , specify tag:Owner for the filter name and TeamA for the filter value.
    *   tag-key - The key of a tag assigned to the resource. Use this filter to find all resources assigned a tag with a specific key, regardless of the tag value.
    *   vpc-id - The ID of the VPC.

    *   _(dict) --
        A filter name and value pair that is used to return a more specific list of results from a describe operation. Filters can be used to match a set of resources by specific criteria, such as tags, attributes, or IDs. The filters supported by a describe operation are documented with the describe operation. For example:

        *   DescribeAvailabilityZones
        *   DescribeImages
        *   DescribeInstances
        *   DescribeKeyPairs
        *   DescribeSecurityGroups
        *   DescribeSnapshots
        *   DescribeSubnets
        *   DescribeTags
        *   DescribeVolumes
        *   DescribeVpcs

        *   **Name** _(string) --     
            The name of the filter. Filter names are case-sensitive.

        *   **Values** _(list) --     
            The filter values. Filter values are case-sensitive.

            *   _(string) --_
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **NextToken** (_string_) -- The token for the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of results to return with a single call. To retrieve the remaining results, make another call with the returned nextToken value.
- None

[VpcPeeringConnection](#id1257)
--------------------------------------------------------------------------------------

_class_ EC2.VpcPeeringConnection(_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) VpcPeeringConnection:

import boto3

ec2 = boto3.resource('ec2')
vpc_peering_connection = ec2.VpcPeeringConnection('id')
```

Parameters

**id** (_string_) -- The VpcPeeringConnection's id identifier. This **must** be set.

available identifiers:

*   [id](#EC2.VpcPeeringConnection.id "EC2.VpcPeeringConnection.id")

available attributes:

*   [accepter_vpc_info](#EC2.VpcPeeringConnection.accepter_vpc_info "EC2.VpcPeeringConnection.accepter_vpc_info")
*   [expiration_time](#EC2.VpcPeeringConnection.expiration_time "EC2.VpcPeeringConnection.expiration_time")
*   [requester_vpc_info](#EC2.VpcPeeringConnection.requester_vpc_info "EC2.VpcPeeringConnection.requester_vpc_info")
*   [status](#EC2.VpcPeeringConnection.status "EC2.VpcPeeringConnection.status")
*   [tags](#EC2.VpcPeeringConnection.tags "EC2.VpcPeeringConnection.tags")
*   [vpc_peering_connection_id](#EC2.VpcPeeringConnection.vpc_peering_connection_id "EC2.VpcPeeringConnection.vpc_peering_connection_id")

available references:

*   [accepter_vpc](#EC2.VpcPeeringConnection.accepter_vpc "EC2.VpcPeeringConnection.accepter_vpc")
*   [requester_vpc](#EC2.VpcPeeringConnection.requester_vpc "EC2.VpcPeeringConnection.requester_vpc")

available actions:

*   [accept()](#EC2.VpcPeeringConnection.accept "EC2.VpcPeeringConnection.accept")
*   [delete()](#EC2.VpcPeeringConnection.delete "EC2.VpcPeeringConnection.delete")
*   [get_available_subresources()](#EC2.VpcPeeringConnection.get_available_subresources "EC2.VpcPeeringConnection.get_available_subresources")
*   [load()](#EC2.VpcPeeringConnection.load "EC2.VpcPeeringConnection.load")
*   [reject()](#EC2.VpcPeeringConnection.reject "EC2.VpcPeeringConnection.reject")
*   [reload()](#EC2.VpcPeeringConnection.reload "EC2.VpcPeeringConnection.reload")

available waiters:

*   [wait_until_exists()](#EC2.VpcPeeringConnection.wait_until_exists "EC2.VpcPeeringConnection.wait_until_exists")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

id

_(string)_ The VpcPeeringConnection's id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.VpcPeeringConnection.load "EC2.VpcPeeringConnection.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

accepter_vpc_info

*   _(dict) --_

    Information about the accepter VPC. CIDR block information is only returned when describing an active VPC peering connection.

    *   **CidrBlock** _(string) --
        The IPv4 CIDR block for the VPC.

    *   **Ipv6CidrBlockSet** _(list) --
        The IPv6 CIDR block for the VPC.

        *   _(dict) --     
            Describes an IPv6 CIDR block.

            *   **Ipv6CidrBlock** _(string) --         
                The IPv6 CIDR block.

    *   **CidrBlockSet** _(list) --
        Information about the IPv4 CIDR blocks for the VPC.

        *   _(dict) --     
            Describes an IPv4 CIDR block.

            *   **CidrBlock** _(string) --         
                The IPv4 CIDR block.

    *   **OwnerId** _(string) --
        The AWS account ID of the VPC owner.

    *   **PeeringOptions** _(dict) --
        Information about the VPC peering connection options for the accepter or requester VPC.

        *   **AllowDnsResolutionFromRemoteVpc** _(boolean) --     
            Indicates whether a local VPC can resolve public DNS hostnames to private IP addresses when queried from instances in a peer VPC.

        *   **AllowEgressFromLocalClassicLinkToRemoteVpc** _(boolean) --     
            Indicates whether a local ClassicLink connection can communicate with the peer VPC over the VPC peering connection.

        *   **AllowEgressFromLocalVpcToRemoteClassicLink** _(boolean) --     
            Indicates whether a local VPC can communicate with a ClassicLink connection in the peer VPC over the VPC peering connection.

    *   **VpcId** _(string) --
        The ID of the VPC.

    *   **Region** _(string) --
        The Region in which the VPC is located.


expiration_time

*   _(datetime) --_

    The time that an unaccepted VPC peering connection will expire.


requester_vpc_info

*   _(dict) --_

    Information about the requester VPC. CIDR block information is only returned when describing an active VPC peering connection.

    *   **CidrBlock** _(string) --
        The IPv4 CIDR block for the VPC.

    *   **Ipv6CidrBlockSet** _(list) --
        The IPv6 CIDR block for the VPC.

        *   _(dict) --     
            Describes an IPv6 CIDR block.

            *   **Ipv6CidrBlock** _(string) --         
                The IPv6 CIDR block.

    *   **CidrBlockSet** _(list) --
        Information about the IPv4 CIDR blocks for the VPC.

        *   _(dict) --     
            Describes an IPv4 CIDR block.

            *   **CidrBlock** _(string) --         
                The IPv4 CIDR block.

    *   **OwnerId** _(string) --
        The AWS account ID of the VPC owner.

    *   **PeeringOptions** _(dict) --
        Information about the VPC peering connection options for the accepter or requester VPC.

        *   **AllowDnsResolutionFromRemoteVpc** _(boolean) --     
            Indicates whether a local VPC can resolve public DNS hostnames to private IP addresses when queried from instances in a peer VPC.

        *   **AllowEgressFromLocalClassicLinkToRemoteVpc** _(boolean) --     
            Indicates whether a local ClassicLink connection can communicate with the peer VPC over the VPC peering connection.

        *   **AllowEgressFromLocalVpcToRemoteClassicLink** _(boolean) --     
            Indicates whether a local VPC can communicate with a ClassicLink connection in the peer VPC over the VPC peering connection.

    *   **VpcId** _(string) --
        The ID of the VPC.

    *   **Region** _(string) --
        The Region in which the VPC is located.


status

*   _(dict) --_

    The status of the VPC peering connection.

    *   **Code** _(string) --
        The status of the VPC peering connection.

    *   **Message** _(string) --
        A message that provides more information about the status, if applicable.


tags

*   _(list) --_

    Any tags assigned to the resource.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


vpc_peering_connection_id

*   _(string) --_

    The ID of the VPC peering connection.


References

References are related resource instances that have a belongs-to relationship. For more information about references refer to the [_Resources Introduction Guide_](../../guide/resources.html#references-intro).

accepter_vpc

(Vpc) The related accepter_vpc if set, otherwise None.

requester_vpc

(Vpc) The related requester_vpc if set, otherwise None.

Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

accept(_**kwargs_)

Accept a VPC peering connection request. To accept a request, the VPC peering connection must be in the pending-acceptance state, and you must be the owner of the peer VPC. Use DescribeVpcPeeringConnections to view your outstanding VPC peering connection requests.

For an inter-Region VPC peering connection request, you must accept the VPC peering connection in the Region of the accepter VPC.


**Request Syntax**

```py
response = vpc_peering_connection.accept(
    DryRun=True|False,

)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'VpcPeeringConnection': {
        'AccepterVpcInfo': {
            'CidrBlock': 'string',
            'Ipv6CidrBlockSet': [
                {
                    'Ipv6CidrBlock': 'string'
                },
            ],
            'CidrBlockSet': [
                {
                    'CidrBlock': 'string'
                },
            ],
            'OwnerId': 'string',
            'PeeringOptions': {
                'AllowDnsResolutionFromRemoteVpc': True|False,
                'AllowEgressFromLocalClassicLinkToRemoteVpc': True|False,
                'AllowEgressFromLocalVpcToRemoteClassicLink': True|False
            },
            'VpcId': 'string',
            'Region': 'string'
        },
        'ExpirationTime': datetime(2015, 1, 1),
        'RequesterVpcInfo': {
            'CidrBlock': 'string',
            'Ipv6CidrBlockSet': [
                {
                    'Ipv6CidrBlock': 'string'
                },
            ],
            'CidrBlockSet': [
                {
                    'CidrBlock': 'string'
                },
            ],
            'OwnerId': 'string',
            'PeeringOptions': {
                'AllowDnsResolutionFromRemoteVpc': True|False,
                'AllowEgressFromLocalClassicLinkToRemoteVpc': True|False,
                'AllowEgressFromLocalVpcToRemoteClassicLink': True|False
            },
            'VpcId': 'string',
            'Region': 'string'
        },
        'Status': {
            'Code': 'initiating-request'|'pending-acceptance'|'active'|'deleted'|'rejected'|'failed'|'expired'|'provisioning'|'deleting',
            'Message': 'string'
        },
        'Tags': [
            {
                'Key': 'string',
                'Value': 'string'
            },
        ],
        'VpcPeeringConnectionId': 'string'
    }
}
```


**Response Structure**

*   _(dict) --_
    *   **VpcPeeringConnection** _(dict) --
        Information about the VPC peering connection.

        *   **AccepterVpcInfo** _(dict) --     
            Information about the accepter VPC. CIDR block information is only returned when describing an active VPC peering connection.

            *   **CidrBlock** _(string) --         
                The IPv4 CIDR block for the VPC.

            *   **Ipv6CidrBlockSet** _(list) --         
                The IPv6 CIDR block for the VPC.

                *   _(dict) --             
                    Describes an IPv6 CIDR block.

                    *   **Ipv6CidrBlock** _(string) --                 
                        The IPv6 CIDR block.

            *   **CidrBlockSet** _(list) --         
                Information about the IPv4 CIDR blocks for the VPC.

                *   _(dict) --             
                    Describes an IPv4 CIDR block.

                    *   **CidrBlock** _(string) --                 
                        The IPv4 CIDR block.

            *   **OwnerId** _(string) --         
                The AWS account ID of the VPC owner.

            *   **PeeringOptions** _(dict) --         
                Information about the VPC peering connection options for the accepter or requester VPC.

                *   **AllowDnsResolutionFromRemoteVpc** _(boolean) --             
                    Indicates whether a local VPC can resolve public DNS hostnames to private IP addresses when queried from instances in a peer VPC.

                *   **AllowEgressFromLocalClassicLinkToRemoteVpc** _(boolean) --             
                    Indicates whether a local ClassicLink connection can communicate with the peer VPC over the VPC peering connection.

                *   **AllowEgressFromLocalVpcToRemoteClassicLink** _(boolean) --             
                    Indicates whether a local VPC can communicate with a ClassicLink connection in the peer VPC over the VPC peering connection.

            *   **VpcId** _(string) --         
                The ID of the VPC.

            *   **Region** _(string) --         
                The Region in which the VPC is located.

        *   **ExpirationTime** _(datetime) --     
            The time that an unaccepted VPC peering connection will expire.

        *   **RequesterVpcInfo** _(dict) --     
            Information about the requester VPC. CIDR block information is only returned when describing an active VPC peering connection.

            *   **CidrBlock** _(string) --         
                The IPv4 CIDR block for the VPC.

            *   **Ipv6CidrBlockSet** _(list) --         
                The IPv6 CIDR block for the VPC.

                *   _(dict) --             
                    Describes an IPv6 CIDR block.

                    *   **Ipv6CidrBlock** _(string) --                 
                        The IPv6 CIDR block.

            *   **CidrBlockSet** _(list) --         
                Information about the IPv4 CIDR blocks for the VPC.

                *   _(dict) --             
                    Describes an IPv4 CIDR block.

                    *   **CidrBlock** _(string) --                 
                        The IPv4 CIDR block.

            *   **OwnerId** _(string) --         
                The AWS account ID of the VPC owner.

            *   **PeeringOptions** _(dict) --         
                Information about the VPC peering connection options for the accepter or requester VPC.

                *   **AllowDnsResolutionFromRemoteVpc** _(boolean) --             
                    Indicates whether a local VPC can resolve public DNS hostnames to private IP addresses when queried from instances in a peer VPC.

                *   **AllowEgressFromLocalClassicLinkToRemoteVpc** _(boolean) --             
                    Indicates whether a local ClassicLink connection can communicate with the peer VPC over the VPC peering connection.

                *   **AllowEgressFromLocalVpcToRemoteClassicLink** _(boolean) --             
                    Indicates whether a local VPC can communicate with a ClassicLink connection in the peer VPC over the VPC peering connection.

            *   **VpcId** _(string) --         
                The ID of the VPC.

            *   **Region** _(string) --         
                The Region in which the VPC is located.

        *   **Status** _(dict) --     
            The status of the VPC peering connection.

            *   **Code** _(string) --         
                The status of the VPC peering connection.

            *   **Message** _(string) --         
                A message that provides more information about the status, if applicable.

        *   **Tags** _(list) --     
            Any tags assigned to the resource.

            *   _(dict) --         
                Describes a tag.

                *   **Key** _(string) --             
                    The key of the tag.

                    Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

                *   **Value** _(string) --             
                    The value of the tag.

                    Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.

        *   **VpcPeeringConnectionId** _(string) --     
            The ID of the VPC peering connection.


delete(_**kwargs_)

Deletes a VPC peering connection. Either the owner of the requester VPC or the owner of the accepter VPC can delete the VPC peering connection if it's in the active state. The owner of the requester VPC can delete a VPC peering connection in the pending-acceptance state. You cannot delete a VPC peering connection that's in the failed state.


**Request Syntax**

```py
response = vpc_peering_connection.delete(
    DryRun=True|False,

)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'Return': True|False
}
```


**Response Structure**

*   _(dict) --_
    *   **Return** _(boolean) --
        Returns true if the request succeeds; otherwise, it returns an error.


get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_vpc_peering_connections()](#EC2.Client.describe_vpc_peering_connections "EC2.Client.describe_vpc_peering_connections") to update the attributes of the VpcPeeringConnection resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
vpc_peering_connection.load()
- None

reject(_**kwargs_)

Rejects a VPC peering connection request. The VPC peering connection must be in the pending-acceptance state. Use the DescribeVpcPeeringConnections request to view your outstanding VPC peering connection requests. To delete an active VPC peering connection, or to delete a VPC peering connection request that you initiated, use DeleteVpcPeeringConnection .


**Request Syntax**

```py
response = vpc_peering_connection.reject(
    DryRun=True|False,

)
```

Parameters

**DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'Return': True|False
}
```


**Response Structure**

*   _(dict) --_
    *   **Return** _(boolean) --
        Returns true if the request succeeds; otherwise, it returns an error.


reload()

Calls [EC2.Client.describe_vpc_peering_connections()](#EC2.Client.describe_vpc_peering_connections "EC2.Client.describe_vpc_peering_connections") to update the attributes of the VpcPeeringConnection resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
vpc_peering_connection.reload()
- None

Waiters

Waiters provide an interface to wait for a resource to reach a specific state. For more information about waiters refer to the [_Resources Introduction Guide_](../../guide/resources.html#waiters-intro).

wait_until_exists(_**kwargs_)

Waits until this VpcPeeringConnection is exists. This method calls EC2.Waiter.vpc_peering_connection_exists.wait() which polls. [EC2.Client.describe_vpc_peering_connections()](#EC2.Client.describe_vpc_peering_connections "EC2.Client.describe_vpc_peering_connections") every 15 seconds until a successful state is reached. An error is returned after 40 failed checks.


**Request Syntax**

```py
vpc_peering_connection.wait_until_exists(
    Filters=[
        {
            'Name': 'string',
            'Values': [
                'string',
            ]
        },
    ],
    DryRun=True|False,
    NextToken='string',
    MaxResults=123
)
```

Parameters

*   **Filters** (_list_) --

    One or more filters.

    *   accepter-vpc-info.cidr-block - The IPv4 CIDR block of the accepter VPC.
    *   accepter-vpc-info.owner-id - The AWS account ID of the owner of the accepter VPC.
    *   accepter-vpc-info.vpc-id - The ID of the accepter VPC.
    *   expiration-time - The expiration date and time for the VPC peering connection.
    *   requester-vpc-info.cidr-block - The IPv4 CIDR block of the requester's VPC.
    *   requester-vpc-info.owner-id - The AWS account ID of the owner of the requester VPC.
    *   requester-vpc-info.vpc-id - The ID of the requester VPC.
    *   status-code - The status of the VPC peering connection (pending-acceptance | failed | expired | provisioning | active | deleting | deleted | rejected ).
    *   status-message - A message that provides more information about the status of the VPC peering connection, if applicable.
    *   tag :<key> - The key/value combination of a tag assigned to the resource. Use the tag key in the filter name and the tag value as the filter value. For example, to find all resources that have a tag with the key Owner and the value TeamA , specify tag:Owner for the filter name and TeamA for the filter value.
    *   tag-key - The key of a tag assigned to the resource. Use this filter to find all resources assigned a tag with a specific key, regardless of the tag value.
    *   vpc-peering-connection-id - The ID of the VPC peering connection.

    *   _(dict) --
        A filter name and value pair that is used to return a more specific list of results from a describe operation. Filters can be used to match a set of resources by specific criteria, such as tags, attributes, or IDs. The filters supported by a describe operation are documented with the describe operation. For example:

        *   DescribeAvailabilityZones
        *   DescribeImages
        *   DescribeInstances
        *   DescribeKeyPairs
        *   DescribeSecurityGroups
        *   DescribeSnapshots
        *   DescribeSubnets
        *   DescribeTags
        *   DescribeVolumes
        *   DescribeVpcs

        *   **Name** _(string) --     
            The name of the filter. Filter names are case-sensitive.

        *   **Values** _(list) --     
            The filter values. Filter values are case-sensitive.

            *   _(string) --_
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **NextToken** (_string_) -- The token for the next page of results.
*   **MaxResults** (_integer_) -- The maximum number of results to return with a single call. To retrieve the remaining results, make another call with the returned nextToken value.
- None

[VpcAddress](#id1258)
------------------------------------------------------------------

_class_ EC2.VpcAddress(_allocation_id_)

A resource representing an Amazon Elastic Compute Cloud (EC2) VpcAddress:

import boto3

ec2 = boto3.resource('ec2')
vpc_address = ec2.VpcAddress('allocation_id')
```

Parameters

**allocation_id** (_string_) -- The VpcAddress's allocation_id identifier. This **must** be set.

available identifiers:

*   [allocation_id](#EC2.VpcAddress.allocation_id "EC2.VpcAddress.allocation_id")

available attributes:

*   [association_id](#EC2.VpcAddress.association_id "EC2.VpcAddress.association_id")
*   [carrier_ip](#EC2.VpcAddress.carrier_ip "EC2.VpcAddress.carrier_ip")
*   [customer_owned_ip](#EC2.VpcAddress.customer_owned_ip "EC2.VpcAddress.customer_owned_ip")
*   [customer_owned_ipv4_pool](#EC2.VpcAddress.customer_owned_ipv4_pool "EC2.VpcAddress.customer_owned_ipv4_pool")
*   [domain](#EC2.VpcAddress.domain "EC2.VpcAddress.domain")
*   [instance_id](#EC2.VpcAddress.instance_id "EC2.VpcAddress.instance_id")
*   [network_border_group](#EC2.VpcAddress.network_border_group "EC2.VpcAddress.network_border_group")
*   [network_interface_id](#EC2.VpcAddress.network_interface_id "EC2.VpcAddress.network_interface_id")
*   [network_interface_owner_id](#EC2.VpcAddress.network_interface_owner_id "EC2.VpcAddress.network_interface_owner_id")
*   [private_ip_address](#EC2.VpcAddress.private_ip_address "EC2.VpcAddress.private_ip_address")
*   [public_ip](#EC2.VpcAddress.public_ip "EC2.VpcAddress.public_ip")
*   [public_ipv4_pool](#EC2.VpcAddress.public_ipv4_pool "EC2.VpcAddress.public_ipv4_pool")
*   [tags](#EC2.VpcAddress.tags "EC2.VpcAddress.tags")

available references:

*   [association](#EC2.VpcAddress.association "EC2.VpcAddress.association")

available actions:

*   [associate()](#EC2.VpcAddress.associate "EC2.VpcAddress.associate")
*   [get_available_subresources()](#EC2.VpcAddress.get_available_subresources "EC2.VpcAddress.get_available_subresources")
*   [load()](#EC2.VpcAddress.load "EC2.VpcAddress.load")
*   [release()](#EC2.VpcAddress.release "EC2.VpcAddress.release")
*   [reload()](#EC2.VpcAddress.reload "EC2.VpcAddress.reload")

Identifiers

Identifiers are properties of a resource that are set upon instantation of the resource. For more information about identifiers refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

allocation_id

_(string)_ The VpcAddress's allocation_id identifier. This **must** be set.

Attributes

Attributes provide access to the properties of a resource. Attributes are lazy-loaded the first time one is accessed via the [load()](#EC2.VpcAddress.load "EC2.VpcAddress.load") method. For more information about attributes refer to the [_Resources Introduction Guide_](../../guide/resources.html#identifiers-attributes-intro).

association_id

*   _(string) --_

    The ID representing the association of the address with an instance in a VPC.


carrier_ip

*   _(string) --_

    The carrier IP address associated. This option is only available for network interfaces which reside in a subnet in a Wavelength Zone (for example an EC2 instance).


customer_owned_ip

*   _(string) --_

    The customer-owned IP address.


customer_owned_ipv4_pool

*   _(string) --_

    The ID of the customer-owned address pool.


domain

*   _(string) --_

    Indicates whether this Elastic IP address is for use with instances in EC2-Classic (standard ) or instances in a VPC (vpc ).


instance_id

*   _(string) --_

    The ID of the instance that the address is associated with (if any).


network_border_group

*   _(string) --_

    The name of the unique set of Availability Zones, Local Zones, or Wavelength Zones from which AWS advertises IP addresses.


network_interface_id

*   _(string) --_

    The ID of the network interface.


network_interface_owner_id

*   _(string) --_

    The ID of the AWS account that owns the network interface.


private_ip_address

*   _(string) --_

    The private IP address associated with the Elastic IP address.


public_ip

*   _(string) --_

    The Elastic IP address.


public_ipv4_pool

*   _(string) --_

    The ID of an address pool.


tags

*   _(list) --_

    Any tags assigned to the Elastic IP address.

    *   _(dict) --
        Describes a tag.

        *   **Key** _(string) --     
            The key of the tag.

            Constraints: Tag keys are case-sensitive and accept a maximum of 127 Unicode characters. May not begin with aws: .

        *   **Value** _(string) --     
            The value of the tag.

            Constraints: Tag values are case-sensitive and accept a maximum of 255 Unicode characters.


References

References are related resource instances that have a belongs-to relationship. For more information about references refer to the [_Resources Introduction Guide_](../../guide/resources.html#references-intro).

association

(NetworkInterfaceAssociation) The related association if set, otherwise None.

Actions

Actions call operations on resources. They may automatically handle the passing in of arguments set from identifiers and some attributes. For more information about actions refer to the [_Resources Introduction Guide_](../../guide/resources.html#actions-intro).

associate(_**kwargs_)

Associates an Elastic IP address, or carrier IP address (for instances that are in subnets in Wavelength Zones) with an instance or a network interface. Before you can use an Elastic IP address, you must allocate it to your account.

An Elastic IP address is for use in either the EC2-Classic platform or in a VPC. For more information, see [Elastic IP Addresses](https://docs.aws.amazon.com/AWSEC2/latest/UserGuide/elastic-ip-addresses-eip.html) in the _Amazon Elastic Compute Cloud User Guide_ .

[EC2-Classic, VPC in an EC2-VPC-only account] If the Elastic IP address is already associated with a different instance, it is disassociated from that instance and associated with the specified instance. If you associate an Elastic IP address with an instance that has an existing Elastic IP address, the existing address is disassociated from the instance, but remains allocated to your account.

[VPC in an EC2-Classic account] If you don't specify a private IP address, the Elastic IP address is associated with the primary IP address. If the Elastic IP address is already associated with a different instance or a network interface, you get an error unless you allow reassociation. You cannot associate an Elastic IP address with an instance or network interface that has an existing Elastic IP address.

[Subnets in Wavelength Zones] You can associate an IP address from the telecommunication carrier to the instance or network interface.

You cannot associate an Elastic IP address with an interface in a different network border group.

Warning

This is an idempotent operation. If you perform the operation more than once, Amazon EC2 doesn't return an error, and you may be charged for each time the Elastic IP address is remapped to the same instance. For more information, see the _Elastic IP Addresses_ section of [Amazon EC2 Pricing](http://aws.amazon.com/ec2/pricing/) .


**Request Syntax**

```py
response = vpc_address.associate(
    InstanceId='string',
    PublicIp='string',
    AllowReassociation=True|False,
    DryRun=True|False,
    NetworkInterfaceId='string',
    PrivateIpAddress='string'
)
```

Parameters

*   **InstanceId** (_string_) -- The ID of the instance. This is required for EC2-Classic. For EC2-VPC, you can specify either the instance ID or the network interface ID, but not both. The operation fails if you specify an instance ID unless exactly one network interface is attached.
*   **PublicIp** (_string_) -- The Elastic IP address to associate with the instance. This is required for EC2-Classic.
*   **AllowReassociation** (_boolean_) -- [EC2-VPC] For a VPC in an EC2-Classic account, specify true to allow an Elastic IP address that is already associated with an instance or network interface to be reassociated with the specified instance or network interface. Otherwise, the operation fails. In a VPC in an EC2-VPC-only account, reassociation is automatic, therefore you can specify false to ensure the operation fails if the Elastic IP address is already associated with another resource.
*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
*   **NetworkInterfaceId** (_string_) --

    [EC2-VPC] The ID of the network interface. If the instance has more than one network interface, you must specify a network interface ID.

    For EC2-VPC, you can specify either the instance ID or the network interface ID, but not both.

*   **PrivateIpAddress** (_string_) -- [EC2-VPC] The primary or secondary private IP address to associate with the Elastic IP address. If no private IP address is specified, the Elastic IP address is associated with the primary private IP address.
Return:
- Return type: dict
- **Response Syntax**

```py
{
    'AssociationId': 'string'
}
```


**Response Structure**

*   _(dict) --_

    *   **AssociationId** _(string) --
        [EC2-VPC] The ID that represents the association of the Elastic IP address with an instance.


get_available_subresources()

Returns a list of all the available sub-resources for this Resource.
- A list containing the name of each sub-resource for this resource
Return:
- Return type: list of str

load()

Calls [EC2.Client.describe_addresses()](#EC2.Client.describe_addresses "EC2.Client.describe_addresses") to update the attributes of the VpcAddress resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
vpc_address.load()
- None

release(_**kwargs_)

Releases the specified Elastic IP address.

[EC2-Classic, default VPC] Releasing an Elastic IP address automatically disassociates it from any instance that it's associated with. To disassociate an Elastic IP address without releasing it, use DisassociateAddress .

[Nondefault VPC] You must use DisassociateAddress to disassociate the Elastic IP address before you can release it. Otherwise, Amazon EC2 returns an error (InvalidIPAddress.InUse ).

After releasing an Elastic IP address, it is released to the IP address pool. Be sure to update your DNS records and any servers or devices that communicate with the address. If you attempt to release an Elastic IP address that you already released, you'll get an AuthFailure error if the address is already allocated to another AWS account.

[EC2-VPC] After you release an Elastic IP address for use in a VPC, you might be able to recover it. For more information, see AllocateAddress .


**Request Syntax**

```py
response = vpc_address.release(
    PublicIp='string',
    NetworkBorderGroup='string',
    DryRun=True|False
)
```

Parameters

*   **PublicIp** (_string_) -- [EC2-Classic] The Elastic IP address. Required for EC2-Classic.
*   **NetworkBorderGroup** (_string_) --

    The set of Availability Zones, Local Zones, or Wavelength Zones from which AWS advertises IP addresses.

    If you provide an incorrect network border group, you will receive an InvalidAddress.NotFound error. For more information, see [Error Codes](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/errors-overview.html) .

    Note

    You cannot use a network border group with EC2 Classic. If you attempt this operation on EC2 classic, you will receive an InvalidParameterCombination error. For more information, see [Error Codes](https://docs.aws.amazon.com/AWSEC2/latest/APIReference/errors-overview.html) .

*   **DryRun** (_boolean_) -- Checks whether you have the required permissions for the action, without actually making the request, and provides an error response. If you have the required permissions, the error response is DryRunOperation . Otherwise, it is UnauthorizedOperation .
- None

reload()

Calls [EC2.Client.describe_addresses()](#EC2.Client.describe_addresses "EC2.Client.describe_addresses") to update the attributes of the VpcAddress resource. Note that the load and reload methods are the same method and can be used interchangeably.


**Request Syntax**

```py
vpc_address.reload()
- None

[EBS](ebs.html "previous chapter (use the left arrow)")

[EC2InstanceConnect](ec2-instance-connect.html "next chapter (use the right arrow)")

### Navigation

*   [index](../../genindex.html "General Index")
*   [modules](../../py-modindex.html "Python Module Index") |
*   [next](ec2-instance-connect.html "EC2InstanceConnect") |
*   [previous](ebs.html "EBS") |
*   [Boto3 Docs 1.16.47 documentation](../../index.html) »
*   [Available services](index.html) »

const shortbread = AWSCShortbread({ domain: ".amazonaws.com", }); shortbread.checkForCookieConsent(); [Privacy](http://aws.amazon.com/privacy) | [Site Terms](http://aws.amazon.com/terms) | [Cookie preferences](#) | © Copyright 2020, Amazon Web Services, Inc. Created using [Sphinx](http://sphinx.pocoo.org/).
