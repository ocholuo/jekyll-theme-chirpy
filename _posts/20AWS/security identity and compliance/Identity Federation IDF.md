


# Identity Federation IDF

1. Identity Federation:
   - architecture where identities of an `external identity provider IDP` is recognized
2. single sign on :
   - user can `use the credentials of an external identity` and use those to access local systems such as AWS.


## types of identity federation


`cross account role`:
- create a role in your AWS account.
- trust an external AWS account so a different set of identities and you'll trust them to be able to assume that role and then perform actions in your AWS account.
- You're essentially federating an identity.
- You're allowing an external set of identities to get access to your account, to swap their credentials for something that's valid in your account.
- cross account roles using the same AWS provider of identities, it may be that a different account has their own IAM instance, and you have your own IAM instance but at the backend, it's AWS providing those identities.
- 


`SAML 2.0`
- standard for certain on premises systems such as Microsoft Active Directory or another AWS hosted directory service.
- SAML is a way that integrate different identity providers with applications and allow users or identities to be reused.
- re-use identities, from on-premises systems like Microsoft Active Directory or AWS Directory Service), to assume a role in an AWS account.


`Web Identity Federation`
- use identity providers such as Google, Amazon, and Facebook and `allow them to assume roles inside our AWS accounts and access resources`
- the way that SAML 2.0 or Web Identity Federation is supported in AWS is using the `Cognito` and `secure token service STS.`
  - STS:
    - get short term temporary credentials.
    - a secure token service responsible for generating the temporary or short term security credentials that you will use to access that account.
  - Cognito
    - essentially broker this single sign on or ID Federation,
    - to create ID pools.
    - These could be discreet pools of identities, but also it can support the architecture where you might have a Twitter log in and a Facebook login and a Google login and there are three different identities but they all represent you.
    - to merge identities and treat them as one individual identity.
￼￼
![Screen Shot 2020-08-09 at 11.03.22](https://i.imgur.com/LySeSMV.png)















￼








.
