# Run FTP Load Test on Sterling B2B Integrator


## Creating Partners in Sterling File Gateway

Create some partners, on IBM Sterling File Gateway. For example:

* demo_partner01
* demo_partner02
* demo_partner03
* demo_partner04
* demo_partner05

## Creating the Load Test using the FTP Protocol

1) Create a new test plan "Test Plan"

* Name: SB2Bi FTP Test Plan

2) Select the 'Test Plan' node and right click on it.

3) Hover over Add -> Topic (Users) -> Thread Group "Thread Group"

* Number of Threads (Users) = 5
* Ramp-up Period (seconds) = 1
* LoopCount = 5

4) Add a Sample > FTP and specify connection settings:

* Server Name
* Port Number
* Username
* Password (unencrypted)
* FTP command to be executed (such as get or put)
* Remote File
* Local File

5) Optional: Create other Samples by changing FTP

6) Add to Listener > View Results Tree

7) Add to Listener > View Results in Table

8) Add to Listener > Summary Report

9) Run the test


![JMeter FTP to Sterling](docs/jmeter-ftp-sterling01.png)
