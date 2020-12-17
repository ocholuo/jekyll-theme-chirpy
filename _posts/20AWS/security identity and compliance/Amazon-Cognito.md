

# Amazon Cognito


1. add user sign-up, sign-in, and access control to web and mobile apps.

2. provides `authentication, authorization, and user management for your web and mobile apps`.

3. control access to AWS resources from your application.
   - define roles and map users to different roles, 
   - so application can access only the resources that are authorized for each user.

4. uses common identity management standards, such as `Security Assertion Markup Language (SAML) 2.0`.
   - SAML: open standard for exchanging identity and security information with applications and service providers.
   - Applications and service providers that support SAML enable you to `sign in by using your corporate directory credentials` (user name and password from Microsoft Active Directory)
   - With SAML, you can use single sign-on (SSO) to sign in to all of your SAML-enabled applications by using a single set of credentials.

5. meet multiple security and compliance requirements, including requirements for highly regulated organizations such as healthcare companies and merchants.
   - eligible for use with the `US Health Insurance Portability and Accountability Act (HIPAA)`.
   - for workloads that are compliant with the `Payment Card Industry Data Security Standard(PCI DSS)`; `the American Institute of CPAs (AICPA)`, `Service Organization Control (SOC)`; `the International Organization for Standardization (ISO)` `and International Electrotechnical Commission (IEC)` `standardsISO/IEC 27001`, `ISO/IEC 27017`, and `ISO/IEC 27018`; and `ISO 9001`

6. The two main components of Amazon Cognito are `user pools` and `identity pools`. 
   - User pools 
     - user directories that provide sign-up and sign-in options for your app users. 
   - Identity pools 
     - enable to grant your users access to other AWS services. 
   - can use identity pools and user pools separately or together.
