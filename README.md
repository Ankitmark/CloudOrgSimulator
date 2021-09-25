# Homework 1

### Ankit Kumar Singh
### 651288872
### Email: asing200@uic.edu

## Overview

This project aims to analyze and build various cloud architectures with multiple datacenters. Where each datacenter having multiple hosts. Each host having its own build and allocation policies of VMs. Each VM houses Cloudlets with custom scheduling policies. The cloudletes/application are scheduled by the various load balancing algorithms. Finally analysing the costs associated with execution of diffrent combination of resource allocation policies on this cloud infrastructure.

## Instruction to run this Project:

Download this repository onto your system or clone using git,

```git clone https://github.com/Ankitmark/CloudOrgSimulator.git```


### Using IntelliJ
1.Open IntelliJ, Click on open project and then select: CloudOrgSimulator

2.No go to the src/main/scala/Simulations/ and choose any simulation file. Now click the green arrow next to the main method of the simulation file you wish to run.

3.To run test cases go to src/test/scala/Simulations/ and run test cases by clicking the green arrow next to test case.


### Using SBT from CLI

1.Open the Command Prompt (if using Windows) or the Terminal (if using Linux/Mac) and browse to the project directory: CloudOrgSimulator

2.Run the command ```sbt clean compile test``` to run all the test cases.

3.Run the command ```sbt clean compile run```. Now, you will see a list of all the simulations you can choose to run.

4.Choose the one you want to run by entering the simulation's corresponding number.

