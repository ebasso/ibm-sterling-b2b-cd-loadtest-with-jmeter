# Setting up JMeter to do load testing on IBM Sterling B2B and Connect:Direct


Do not use GUI mode for load testing!, only for test creation and test debugging.

For load testing, use CLI mode (not GUI):

```bash
jmeter -n -t [jmx file] -l [results file] -e -o [Path to web report folder]
```

increase the Java Heap to meet your testing requirements Best Practices with JMeter

## Load Tests

* [Run FTP Load Test on Sterling B2B Integrator](sb2bi-ftp-readme.md)
* [Run SFTP Load Test on Sterling B2B Integrator](sb2bi-ftp-readme.md)

* [Run Load Test on IBM Sterling Connect:Direct](cd-readme.md)

