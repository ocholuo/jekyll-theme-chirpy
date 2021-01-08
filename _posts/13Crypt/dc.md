






# Digital Signatures 


Digital signatures <font color=red> combine asymmetric cryptography and hashing </font>

Implement a digital signature system for 2 distinct goals:
- assure the recipient that <font color=red> the message truly came from the claimed sender. </font>
  - enforce nonrepudiation 
  - preclude 阻止 the sender from claiming the message is forgery
- assure the recipient that <font color=red> the message was not altered </font> while in transit between the sender and recipient. 
  - against malicious modification 
    - a third party altering the meaning of the message
  - and against unintentional modification 
    - because of faults in the communications process, such as electrical interference
￼

If Alice wants to digitally sign a message she’s sending to Bob, she performs the following actions: 
1. Alice generates a message digest of the original plaintext message using hashing algorithms, like SHA-512. 
1. Alice encrypts the hash using private key. This encrypted message digest is the digital signature. 
1. Alice appends the digital signature with the plaintext message. 
1. Alice transmits the appended message to Bob. 
1. When Bob receives the digitally signed message, he reverses the procedure:
   - decrypts the digital signature using Alice’s public key. 
   - got hash and plaintext.
   - uses the same hashing function to create a message digest of the plaintext message received.
   - compares the decrypted hash received with the hash he computed. 
   - If match, the message he received was sent by Alice. 
   - If do not match, either the message was not sent by Alice or the message was modified while in transit. 

Digital signatures are used for more than just messages. Software vendors often use digital signature technology to authenticate code distributions that you download from the Internet, such as applets and software patches. 
Note that the digital signature process does not provide any privacy in and of itself. It only ensures that the cryptographic goals of integrity, authentication, and nonrepudiation are met. 
However, if Alice wanted to ensure the privacy of her message to Bob, she could add a step to the message creation process. 
	After appending the signed message digest to the plaintext message, Alice could encrypt the entire message with Bob’s public key. When Bob received the message, he would decrypt it with his own private key before following the steps just outlined. 
