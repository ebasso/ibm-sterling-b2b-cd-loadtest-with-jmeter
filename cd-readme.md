# Run Load Test on IBM Sterling Connect:Direct

In this example i add the number of threads as 16. this is the same value in netmap.cfg parameter:

```
# Local Connect:Direct connection information
local.node:\
 :api.max.connects=16:\
```

## Download CDJAI.jar from IBM fix Central

To run the load test will need to have CDJAI.jar to submit process

## Compiling the java code

Change the variables in your java code to your environment

```java
private static final String LOCALNODE = "<IP Address>";
private static final String LOCALNODEAPIPORT = "1363";
private static final String LOCALNODEUSERID = "cdadmin";
private static final String LOCALNODEPASSWORD = "< cd password>";
private static final String PROCESS_PATH = "sendfile.cdp";
private static final String SNODE = "< CDNODE (SNODE) >";
private static final String SOURCEFILE = "/home/cdadmin/cdunix/ndm/cfg/msgfile.cfg"; // Example source file name
private static final String DESTFILE = "/home/cdadmin02/cdunix/work/cddelete.me";
    
```

After change compile the java code:

```sh
javac -cp ./CDJAI.jar:. CDProcessRunner.java 
```

Test your code using command:

```sh
java -cp ./CDJAI.jar:. CDProcessRunner
```


## Creating the Load Test to Connect Direct

1) Create a new test plan "Test Plan"

* Name: CD Test Plan

2) Select the 'Test Plan' node and right click on it.

3) Hover over Add -> Topic (Users) -> Thread Group "Thread Group"

* Number of Threads (Users) = 16
* Ramp-up Period (seconds) = 1
* LoopCount = 5

4) Add a Sample > 'OS Process Sampler and specify connection settings:

* Command: /usr/sbin/java
* Working Directory: /home/ebasso/CDPerformanceLoader

On command parameters, click 'Add' to each line

* -cp 
* ./CDJAI.jar:.
* CDProcessRunner

On Return Code Configuration:

* check the 'Check Return Code'
* Expected Return Code: '0'

5) Add to Listener > View Results Tree

6) Add to Listener > View Results in Table

7) Add to Listener > Summary Report

8) Run the test
