---
title: GCP - LAB
date: 2021-01-01 11:11:11 -0400
categories: [21GCP, GCPLab]
tags: [GCP]
toc: true
image:
---

[toc]

---

# Lab1 - Google Cloud Fundamentals: Cloud Marketplace


use Cloud Marketplace to quickly and easily deploy a LAMP stack on a Compute Engine instance.
- The Bitnami LAMP Stack provides a complete web development environment for Linux that can be launched in one click.

| Component   | Role      |
| ------------------ | ------------------------- |
| Linux    | Operating system   |
| Apache HTTP Server | Web server    |
| MySQL    | Relational database  |
| PHP    | Web application framework |
| phpMyAdmin   | PHP administration tool |

[Bitnami LAMP Stack Documentation](https://docs.bitnami.com/google/infrastructure/lamp).

---

Task 1: Sign in to the Google Cloud Platform (GCP) Console
----------------------------------------------------------


Task 2: Use Cloud Marketplace to deploy a LAMP stack
----------------------------------------------------

1. In the GCP Console, on the **Navigation menu** (![Navigation menu](https://cdn.qwiklabs.com/LyLHJ5I3gtYdRN1pHDZ2JK2vbd1sM6W2viT0OzyRPTs%3D)), click **Marketplace**.

2. In the search bar
 - type and click **LAMP Certified by Bitnami**.
 - On the LAMP page, click **Launch**.

3. Leave the remaining settings as their defaults.

4. Click **Deploy**.
 - The status of the deployment appears in the console window: **lampstack-1 is being deployed**. When the deployment of the infrastructure is complete, the status changes to **lampstack-1 has been deployed**.


Task 3: Verify your deployment
------------------------------

1. When the deployment is complete, click the **Site address** link in the right pane.

2. On the GCP Console, under **Get started with LAMP Certified by Bitnami**, click **SSH**.
 - a secure login shell session on your virtual machine appears.

```bash
# change the current working directory to `/opt/bitnami`, execute the following command:
cd /opt/bitnami


# To copy the `phpinfo.php` script from the installation directory to a publicly accessible location under the web server document root
sudo sh -c 'echo "<?php phpinfo(); ?>" > apache2/htdocs/phpinfo.php'

# The phpinfo.php script displays your PHP configuration.
# It is often used to verify a new PHP installation.

# close the SSH window
exit
```

3. Open a new browser tab. `http://SITE_ADDRESS/phpinfo.php`
 - A summary of the PHP configuration of your server is displayed.

4. Close the **phpinfo** tab.

---




# Lab2 - Google Cloud Fundamentals: Compute Engine

perform the following tasks:

* Create a Compute Engine virtual machine using the Google Cloud Platform (GCP) Console.

* Create a Compute Engine virtual machine using the gcloud command-line interface.

* Connect between the two instances.

---

Task 1: Sign in to the Google Cloud Platform (GCP) Console
----------------------------------------------------------


Task 2: Create a virtual machine using the GCP Console
------------------------------------------------------

1. In the **Navigation menu** (![Navigation menu](https://cdn.qwiklabs.com/LyLHJ5I3gtYdRN1pHDZ2JK2vbd1sM6W2viT0OzyRPTs%3D)), click **Compute Engine** > **VM instances**.
2. Click **Create**.
3. On the **Create an Instance** page
   1. for **Name**, type `my-vm-1`
   2. For **Region** and **Zone**, select the region and zone assigned by Qwiklabs.
   3. For **Machine type**, accept the default.
   4. For **Boot disk**, if the **Image** shown is not **Debian GNU/Linux 9 (stretch)**, click **Change** and select **Debian GNU/Linux 9 (stretch)**.
4. Leave the defaults for **Identity and API access** unmodified.
5. For Firewall, click **Allow HTTP traffic**.
6. Leave all other defaults unmodified.
7. To create and launch the VM, click **Create**.


Task 3: Create a virtual machine using the gcloud command line
--------------------------------------------------------------

1. In GCP console, on the top right toolbar, click the **Open Cloud Shell button** > **Continue**  

```bash
# display a list of all the zones in the region to which Qwiklabs assigned you
gcloud compute zones list | grep us-central1
# us-central1-c              us-central1              UP
# us-central1-a              us-central1              UP
# us-central1-f              us-central1              UP
# us-central1-b              us-central1              UP

# Choose a zone from that list other than the zone to which Qwiklabs assigned you. For example, if Qwiklabs assigned you to region `us-central1` and zone `us-central1-a` you might choose zone `us-central1-b`.

# To set your default zone to the one you just chose, enter this partial command `gcloud config set compute/zone` followed by the zone you chose.
gcloud config set compute/zone us-central1-b


# To create a VM instance called **my-vm-2** in that zone, execute this command:

gcloud compute instances create "my-vm-2"
  --machine-type "n1-standard-1"
  --image-project "debian-cloud"
  --image "debian-9-stretch-v20190213"
  --subnet "default"
# NAME     ZONE           MACHINE_TYPE   PREEMPTIBLE  INTERNAL_IP  EXTERNAL_IP    STATUS
# my-vm-2  us-central1-b  n1-standard-1               10.128.0.3   35.184.46.186  RUNNING

# To close the Cloud Shell, execute the following command:
exit
```


Task 4: Connect between VM instances
------------------------------------

1. In the **Navigation menu** (![Navigation menu](https://cdn.qwiklabs.com/LyLHJ5I3gtYdRN1pHDZ2JK2vbd1sM6W2viT0OzyRPTs%3D)), click **Compute Engine > VM instances**.
   - two VM instances in a different zone.
   - Notice that the Internal IP addresses of these two instances share the first three bytes in common.
   - They reside on the same subnet in their Google Cloud VPC even though they are in different zones.

2. To open a command prompt on the **my-vm-2** instance, click **SSH** in its row in the **VM instances** list.

```bash

# confirm that **my-vm-2** can reach **my-vm-1** over the network:
ping my-vm-1.us-central1-a
# the complete hostname of **my-vm-1** is **my-vm-1.us-central1-a.c.PROJECT_ID.internal**, where PROJECT_ID is the name of your Google Cloud Platform project. GCP automatically supplies Domain Name Service (DNS) resolution for the internal IP addresses of VM instances.

# Use the **ssh** command to open a command prompt on **my-vm-1**:
ssh my-vm-1.us-central1-a

# install the Nginx web server:
sudo apt-get install nginx-light -y


# add a custom message to the home page of the web server:
sudo nano /var/www/html/index.nginx-debian.html


# below the `h1` header. Add text like this, and replace YOUR_NAME with your name:
Hi from YOUR_NAME
# Press **Ctrl+O** and then press **Enter** to save your edited file, and then press **Ctrl+X** to exit the nano text editor.

# Confirm that the web server is serving your new page.
# At the command prompt on **my-vm-1**, execute this command:
curl http://localhost/

# exit the command prompt on **my-vm-1**, execute this command:
exit


# return to the command prompt on **my-vm-2**


# confirm that **my-vm-2** can reach the web server on **my-vm-1**, at the command prompt on **my-vm-2**, execute this command:
curl http://my-vm-1.us-central1-a/
```

2. In the **Navigation menu** (![Navigation menu](https://cdn.qwiklabs.com/LyLHJ5I3gtYdRN1pHDZ2JK2vbd1sM6W2viT0OzyRPTs%3D)), click **Compute Engine > VM instances**.

3. Copy the External IP address for **my-vm-1** and paste it into the address bar of a new browser tab. You will see your web server's home page, including your custom text.
   - If you forgot to click **Allow HTTP traffic** when you created the **my-vm-1** VM instance, your attempt to reach your web server's home page will fail. You can add a [firewall rule](https://cloud.google.com/vpc/docs/firewalls) to allow inbound traffic to your instances, although this topic is out of scope for this course.


---


# Lab3 - Google Cloud Fundamentals: Cloud Storage and Cloud SQL


* Create a Cloud Storage bucket and place an image into it.

* configure an application running in Compute Engine to use a database managed by Cloud SQL.

* configure a web server with PHP, a web development environment that is the basis for popular blogging softwar, Connect to the Cloud SQL instance from a web server.

* Use the image in the Cloud Storage bucket on a web page.

---

Task 1: Sign in to the Google Cloud Platform (GCP) Console
----------------------------------------------------------

Task 2: Deploy a web server VM instance
---------------------------------------

1. In the GCP Console, on the **Navigation menu** (![Navigation menu](https://cdn.qwiklabs.com/LyLHJ5I3gtYdRN1pHDZ2JK2vbd1sM6W2viT0OzyRPTs%3D)), click **Compute Engine** > **VM instances**.

2. Click **Create**.

3. On the **Create an Instance** page,
    1. for **Name**, type `bloghost`
    2. For **Region** and **Zone**, select the region and zone assigned by Qwiklabs.
    3. For **Machine type**, accept the default.
    4. For **Boot disk**, if the **Image** shown is not **Debian GNU/Linux 9 (stretch)**, click **Change** and select **Debian GNU/Linux 9 (stretch)**.
    5. Leave the defaults for **Identity and API access** unmodified.
    6. For **Firewall**, click **Allow HTTP traffic**.
    7. Click **Management, security, disks, networking, sole tenancy** to open that section of the dialog.
    8. Enter the following script as the value for **Startup script**:


        apt-get update
        apt-get install apache2 php php-mysql -y
        service apache2 restart

4. Leave the remaining settings as their defaults, and click **Create**.

5. On the **VM instances** page, copy the **bloghost** VM instance's internal and external IP addresses:
   - Internal IP	10.128.0.2 (nic0)
   - External IP  35.232.96.34


Task 3: Create a Cloud Storage bucket using the gsutil command line
-------------------------------------------------------------------

All Cloud Storage bucket names must be globally unique.
- To ensure that your bucket name is unique, these instructions will guide you to give your bucket the same name as your Cloud Platform project ID, which is also globally unique.
- Cloud Storage buckets can be associated with either a region or a multi-region location: **US**, **EU**, or **ASIA**.
- In this activity, you associate your bucket with the multi-region closest to the region and zone that Qwiklabs or your instructor assigned you to.


1. On the **Google Cloud Platform** menu, click **Activate Cloud Shell** ![Activate Cloud Shell](https://cdn.qwiklabs.com/sqKx45X8b2P7ygEtesyerKaHyXQGXOYNqXOqo%2Bl8nDA%3D). If a dialog box appears, click **Start Cloud Shell**.

```bash
# enter your chosen location into an environment variable called LOCATION. Enter one of these commands:
export LOCATION=US
# export LOCATION=EU
# export LOCATION=ASIA


# In Cloud Shell, the DEVSHELL_PROJECT_ID environment variable contains your project ID.
echo $$DEVSHELL_PROJECT_ID


# to make a bucket named after your project ID:
gsutil mb -l $LOCATION gs://$DEVSHELL_PROJECT_ID
# Creating gs://qwiklabs-gcp-00-9740a5240906/...


# Retrieve a banner image from a publicly accessible Cloud Storage location:
gsutil cp gs://cloud-training/gcpfci/my-excellent-blog.png my-excellent-blog.png


# Copy the banner image to your newly created Cloud Storage bucket:
gsutil cp my-excellent-blog.png gs://$DEVSHELL_PROJECT_ID/my-excellent-blog.png


# Modify the Access Control List of the object you just created so that it is readable by everyone:
gsutil acl ch -u allUsers:R gs://$DEVSHELL_PROJECT_ID/my-excellent-blog.png
```


Task 4: Create the Cloud SQL instance
-------------------------------------

1. In the GCP Console, on the **Navigation menu** (![Navigation menu](https://cdn.qwiklabs.com/LyLHJ5I3gtYdRN1pHDZ2JK2vbd1sM6W2viT0OzyRPTs%3D)), click **SQL**.

2. Click **Create instance**.
   1. For **Choose a database engine**, select **MySQL**.
   2. For **Instance ID,** type **blog-db**,
   3. for **Root password** type a password of your choice. `root`
   4. Set the region and zone assigned by Qwiklabs.
      - same region and zone into which you launched the **bloghost** instance.
      - The best performance is achieved by placing the client and the database close to each other.
3. Click **Create**.

4. Click on the name of the instance, **blog-db**, to open its details page.

5. From the SQL instances details page
   - the **Public IP address** for your SQL instance: `34.69.146.67`

6. Click on **Users** menu on the left-hand side, click **ADD USER ACCOUNT**.

7. For **User name**, type `blogdbuser`

8. For **Password**, type a password of your choice. `dbuser`

9. Click **ADD** to add the user account in the database.

10. Click the **Connections** tab
    - click **Add network**.
    - If you are offered the choice between a **Private IP** connection and a **Public IP** connection, choose **Public IP** for purposes of this lab.  

    1.  For **Name**, type `web front end`

    2.  For **Network**, type the external IP address of your **bloghost** VM instance, followed by `/32`
        - `35.232.96.34/32`
        - Be sure to use the external IP address of your VM instance followed by `/32`.
        - Do not use the VM instance's internal IP address.
        - Do not use the sample IP address shown here.

11. Click **Done** to finish defining the authorized network.

12. Click **Save** to save the configuration change.



Task 5: Configure an application in a Compute Engine instance to use Cloud SQL
------------------------------------------------------------------------------

1. On the **Navigation menu** (![Navigation menu](https://cdn.qwiklabs.com/LyLHJ5I3gtYdRN1pHDZ2JK2vbd1sM6W2viT0OzyRPTs%3D)), click **Compute Engine** > **VM instances**.

2. In the VM instances list, click **SSH** in the row for your VM instance **bloghost**.

```bash
# In your ssh session on **bloghost**

# change your working directory to the document root of the web server:
cd /var/www/html


# Use the **nano** text editor to edit a file called **index.php**:
sudo nano index.php


# Paste the content below into the file:
# <html>
# <head><title>Welcome to my excellent blog</title></head>
# <body>
# <h1>Welcome to my excellent blog</h1>
# <?php
#  $dbserver = "CLOUDSQLIP";
# $dbuser = "blogdbuser";
# $dbpassword = "DBPASSWORD";
# // In a production blog, we would not store the MySQL
# // password in the document root. Instead, we would store it in a
# // configuration file elsewhere on the web server VM instance.

# $conn = new mysqli($dbserver, $dbuser, $dbpassword);

# if (mysqli_connect_error()) {
#         echo ("Database connection failed: " . mysqli_connect_error());
# } else {
#         echo ("Database connection succeeded.");
# }
# ?>
# </body></html>
# Press **Ctrl+O**, and then press **Enter** to save your edited file.
# Press **Ctrl+X** to exit the nano text editor.


# In a later step, you will insert your Cloud SQL instance's IP address and your database password into this file.

# Restart the web server:
sudo service apache2 restart


# Open a new web browser tab and paste into the address bar your **bloghost** VM instance's external IP address followed by **/index.php**.
# The URL will look like this:
35.232.96.34/index.php
# Be sure to use the external IP address of your VM instance followed by /index.php.
# Do not use the VM instance's internal IP address. Do not use the sample IP address shown here.


# When you load the page, you will see that its content includes an error message beginning with the words:
Database connection failed: php_network_getaddresses: getaddrinfo failed: Name or service not known
# This message occurs because you have not yet configured PHP's connection to your Cloud SQL instance.


# Return to your ssh session on **bloghost**. Use the **nano** text editor to edit **index.php** again.
sudo nano index.php


# replace `CLOUDSQLIP` with the Cloud SQL instance Public IP address 34.69.146.67

# replace `DBPASSWORD` with the Cloud SQL database password that you defined above. Leave the quotation marks around the value in place.

# Press **Ctrl+O**, and then press **Enter** to save your edited file.

# Press **Ctrl+X** to exit the nano text editor.

# Restart the web server:
sudo service apache2 restart


# Return to the web browser tab in which you opened your **bloghost** VM instance's external IP address. When you load the page, the following message appears:
Database connection succeeded.
```

![Screen Shot 2021-02-10 at 02.17.27](https://i.imgur.com/mzmZ1nf.png)


> In an actual blog, the database connection status would not be visible to blog visitors.
> Instead, the database connection would be managed solely by the administrator.


Task 6: Configure an application in a Compute Engine instance to use a Cloud Storage object
-------------------------------------------------------------------------------------------

1. In the GCP Console, click **Storage > Browser**.

2. Click on the bucket that is named after your GCP project.

3. In this bucket, there is an object called **my-excellent-blog.png**. Copy the URL behind the link icon that appears in that object's **Public access** column, or behind the words "Public link" if shown.

4. Return to your ssh session on your **bloghost** VM instance.

```bash
# set your working directory to the document root of the web server:
cd /var/www/html


# Use the **nano** text editor to edit **index.php**:
sudo nano index.php


# Use the arrow keys to move the cursor to the line that contains the **h1** element. Press **Enter** to open up a new, blank screen line, and then paste the URL you copied earlier into the line.

# Paste this HTML markup immediately before the URL:
<img src='https://storage.googleapis.com/qwiklabs-gcp-00-9740a5240906/my-excellent-blog.png'>

# The effect of these steps is to place the line containing `<img src='...'>` immediately before the line containing `<h1>...</h1>`

# Press **Ctrl+O**, and then press **Enter** to save your edited file.
# Press **Ctrl+X** to exit the nano text editor.


# Restart the web server:
sudo service apache2 restart
```

5. Return to the web browser tab in which you opened your **bloghost** VM instance's external IP address. When you load the page, its content now includes a banner image.
35.232.96.34/index.php


---































.
