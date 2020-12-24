---
title: SecConcept - Secure Coding Review and Analysis
date: 2020-11-11 11:11:11 -0400
categories: [10SecConcept]
tags: [SecConcept]
toc: true
image:
---

[toc]

---

## Secure Coding Review and Analysis

---


# Secure Coding Review and Analysis

- applications require a “`last look`” to ensure that the application and its’ components, are free of security flaws.
- A secure code review serves to `detect all the inconsistencies` that weren’t found in other types of security testing – and to ensure the `application’s logic and business code` is sound.
- Reviews can be done via both `manual` and `automated methods`
- **cut down on time and resources it would take if vulnerabilities were detected after release**.
  - The security bugs being looked for during a secure code review have been the cause of countless breaches which have resulted in billions of dollars in lost revenue, fines, and abandoned customers.
- **focus on finding flaws in areas**:
  - Authentication, authorization, security configuration,
  - session management, logging,
  - data validation, error handling, and encryption.
- Code reviewers should be well-versed in the language of the application they’re testing, as well as knowledgeable on the secure coding practices and security controls that they need to be looking out for.
- **need to understand the full context of the application**,
  - including its intended audience and use cases
  - Without that context, code may look secure at first glance, but easily be attacked.
  - Knowing the context by which an app is going to be used and how it will function is the only way to certify that the code adequately protects whatever you’ve relegating to it.

- **5 Tips to a Better Secure Code Review**:
  1. **Produce code review checklists** to ensure consistency between reviews and by different developers
     1. all reviewers are working by the same `comprehensive checklist`. reviewers can forget to certain checks without a well-designed checklist.
     2. enforce time constraints as well as `mandatory breaks` for manual code reviewers. especially when looking at high value applications.

  2. Ensure a **positive security culture by not singling out developers**
     1. It can be easy, especially with reporting by some tools being able to compare results over time, to point the finger at developers who routinely make the same mistakes. It’s important when building a security culture to refrain from playing the blame game with developers; this only serves to deepen the gap between security and development. Use your findings to help guide your security education and awareness program, using those common mistakes as a jumping off point and relevant examples developers should be looking out for.
     2. Again, developers aren’t going to improve in security if they feel someone’s watching over their shoulder, ready to jump at every mistake made. Facilitate their security awareness in more positive ways and your relationship with the development team, but more importantly the organization in general, will reap the benefits.

  3. Review code each time a **meaningful change** in the code has been introduced
     1. If you have a secure SDLC in place, you understand the value of testing code on a regular basis. Secure code reviews don’t have to wait until just before release. For major applications, we suggest performing manual code reviews when new changes are introduced, saving time and human brainpower by having the app reviewed in chunks.

  4. A **mix of human review and tool use is best** to detect all flaws
     1. Tools aren’t (yet) armed with the mind of a human, and therefore can’t detect issues in the logic of code and are hard-pressed to correctly estimate the risk to the organization if such a flaw is left unfixed in a piece of code. Thus, as we discussed above, a mix of static analysis testing and manual review is the best combination to avoid missing blind spots in the code. Use your teams’ expertise to review more complicated code and valuable areas of the application and rely on automated tools to cover the rest.

  5. **Continuously monitor and track patterns** of insecure code
     1. By tracking repetitive issues you see between reports and applications, help inform future reviews by modifying your secure code review checklist, as well as your AppSec awareness training. Monitoring your code offers great insight into the patterns that could be the cause of certain flaws, and will help you when you’re updating your review guide.
