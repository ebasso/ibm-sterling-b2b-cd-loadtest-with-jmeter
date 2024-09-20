# Run SFTP Load Test on Sterling B2B Integrator


## Creating Partners in Sterling File Gateway

Create some partners, on IBM Sterling File Gateway. For example:

* demo_partner01
* demo_partner02
* demo_partner03
* demo_partner04
* demo_partner05


## Creating the Load Test using the SFTP Protocol

### Prerequisites

1) Download the Plugins Manager JAR file ( https://jmeter-plugins.org/get/ ) and place it in JMeter's **lib/ext** directory.

2) Then start JMeter and go to the **Options** menu to access the Plugin Manager .

3) Under available plugins, search for **SSH** , install the plugin.


### Creating the Test Plan
1) Create a new test plan "Test Plan"

Name: SB2Bi SFTP Test Plan

2) Select the 'Test Plan' node and right click on it.

3) Hover over Add -> Topic (Users) -> Thread Group "Thread Group"

4) Add a Sample > SSH SFTP and specify connection settings:

* Hostname
* Port
* User Name
* Password
* Action
* Source path
* Target path

5) Optional: Create other Samples using SFTP

6) Add to Listener > View Results Tree

7) Add to Listener > View Results in Table

8) Add to Listener > Summary Report

9) Run the test


