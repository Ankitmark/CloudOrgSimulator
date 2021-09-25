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


## ProjectStructure

1.The src/main/resource folder consists of the configuration files needed for the simulations.

2.The src/main/scala folder consists of two subfolders: Simulations and HelperUtils.

3.The src/main/scala/Simulations folder consists of seven simulation files.

4.The src/main/scala/HelperUtils folder consists of helper classes which are required to run the simulations.

5.The src/test/scala/Simulations filder consist files for test cases.


## Implemented Cloud Models

1.Infrastructure as a Service (IaaS)

2.Platform as a Service (PaaS)

3.Software as a Service (SaaS)

* Created three DataCenter for three model. 

* Provied customer requirements for IaaS and PaaS model from the configuration file as per the request.

* Broker allocates the cloudlets to respective datacenter depending on the which service the cloudlet belongs to.

## Implemented and Compared Policies

### 1. VM Scheduler Policies

* SpaceShared - Simulation1a implements space shared VM Scheduler policy. 

* TimeShared - Simulation1b implements time shared VM Scheduler policy.


### 2. VM Allocation Policies

* VmAllocationPolicyRoundRobin - Simulation2b implements VmAllocationPolicyRoundRobin VM allocation policy. 

* VmAllocationPolicyFirstFit - Simulation2c implements VmAllocationPolicyFirstFit VM allocation policy.


### 3. Cloudlet Utilization Models

* Stochastic - Simulation2a implements Stochastic Utilization Models. 

* Full - Simulation2b implements Full Utilization Models


## Results

### Simulation1a

* Uses a Space shared VM Scheduler policy.

* Result of this simulation is as below.


```

                                         SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       0|SUCCESS| 2|   0|       20| 0|        4|       1000|          4|        0|         3|       3
       1|SUCCESS| 2|   1|       20| 1|        4|       1000|          4|        0|         3|       3
       2|SUCCESS| 2|   2|       20| 2|        4|       1000|          4|        0|         3|       3
       3|SUCCESS| 2|   3|       20| 3|        4|       1000|          4|        0|         3|       3
       4|SUCCESS| 2|   4|       20| 4|        4|       1000|          4|        0|         3|       3
       5|SUCCESS| 2|   0|       20| 0|        4|       1000|          4|        4|        14|      11
       6|SUCCESS| 2|   1|       20| 1|        4|       1000|          4|        4|        14|      11
-----------------------------------------------------------------------------------------------------
19:18:00.032 [main] INFO  simulation1a - -----Cost for simulation1a is-----: 240.35230594123712

```

### Simulation1b

* Uses a Time shared VM Scheduler policy keeping other parameters as same configuration values as Simulation1a.

* Result of this simulation is as below.


```

                                         SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       2|SUCCESS| 2|   2|       20| 2|        4|       1000|          4|        0|         3|       3
       3|SUCCESS| 2|   3|       20| 3|        4|       1000|          4|        0|         3|       3
       4|SUCCESS| 2|   4|       20| 4|        4|       1000|          4|        0|         3|       3
       0|SUCCESS| 2|   0|       20| 0|        4|       1000|          4|        0|       375|     374
       5|SUCCESS| 2|   0|       20| 0|        4|       1000|          4|        0|       375|     374
       1|SUCCESS| 2|   1|       20| 1|        4|       1000|          4|        0|       375|     374
       6|SUCCESS| 2|   1|       20| 1|        4|       1000|          4|        0|       375|     374
-----------------------------------------------------------------------------------------------------
19:19:53.461 [main] INFO  simulation1b - -----Cost for simulation1b is-----: : 7546.087829197795

```

### Simulation2a

* Uses a Stochastic Utilization Models for cloudlets.

* Result of this simulation is as below.


```

                                         SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       0|SUCCESS| 2|   0|        8| 0|        4|      10000|          4|        0|        18|      18
       1|SUCCESS| 2|   1|        8| 1|        4|      10000|          4|        0|        18|      18
       2|SUCCESS| 2|   2|        8| 2|        4|      10000|          4|        0|        18|      18
       3|SUCCESS| 2|   3|        8| 3|        4|      10000|          4|        0|        18|      18
       4|SUCCESS| 2|   4|        8| 4|        4|      10000|          4|        0|        18|      18
       5|SUCCESS| 2|   5|        8| 5|        4|      10000|          4|        0|        18|      18
       6|SUCCESS| 2|   6|        8| 6|        4|      10000|          4|        0|        18|      18
       7|SUCCESS| 2|   7|        8| 7|        4|      10000|          4|        0|        18|      18
       8|SUCCESS| 2|   8|        8| 8|        4|      10000|          4|        0|        18|      18
       9|SUCCESS| 2|   9|        8| 9|        4|      10000|          4|        0|        18|      18
-----------------------------------------------------------------------------------------------------
19:23:49.011 [main] INFO  simulation2a - -----Cost for simulation2a is-----:  926.806463683548

```

### Simulation2b

* Uses a Full Utilization Models for cloudlets keeping other parameters as same configuration value as Simulation2a.

* Result of this simulation is as below.


```

                                         SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       0|SUCCESS| 2|   0|        8| 0|        4|      10000|          4|        0|        10|      10
       1|SUCCESS| 2|   1|        8| 1|        4|      10000|          4|        0|        10|      10
       2|SUCCESS| 2|   2|        8| 2|        4|      10000|          4|        0|        10|      10
       3|SUCCESS| 2|   3|        8| 3|        4|      10000|          4|        0|        10|      10
       4|SUCCESS| 2|   4|        8| 4|        4|      10000|          4|        0|        10|      10
       5|SUCCESS| 2|   5|        8| 5|        4|      10000|          4|        0|        10|      10
       6|SUCCESS| 2|   6|        8| 6|        4|      10000|          4|        0|        10|      10
       7|SUCCESS| 2|   7|        8| 7|        4|      10000|          4|        0|        10|      10
       8|SUCCESS| 2|   8|        8| 8|        4|      10000|          4|        0|        10|      10
       9|SUCCESS| 2|   9|        8| 9|        4|      10000|          4|        0|        10|      10
-----------------------------------------------------------------------------------------------------
19:26:27.199 [main] INFO  simulation2b - -----Cost for simulation2b is-----: 505.0

```

### Simulation2c

* Uses a VmAllocationPolicyFirstFit while Simulation2b uses VmAllocationPolicyRoundRobin keeping other parameters as same configuration value.

* Result of this simulation is as below.


```

                                         SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       0|SUCCESS| 2|   0|        8| 0|        4|      10000|          4|        0|        10|      10
       1|SUCCESS| 2|   1|        8| 1|        4|      10000|          4|        0|        10|      10
       2|SUCCESS| 2|   2|        8| 2|        4|      10000|          4|        0|        10|      10
       3|SUCCESS| 2|   3|        8| 3|        4|      10000|          4|        0|        10|      10
       4|SUCCESS| 2|   4|        8| 4|        4|      10000|          4|        0|        10|      10
       5|SUCCESS| 2|   5|        8| 5|        4|      10000|          4|        0|        10|      10
       6|SUCCESS| 2|   6|        8| 6|        4|      10000|          4|        0|        10|      10
       7|SUCCESS| 2|   7|        8| 7|        4|      10000|          4|        0|        10|      10
       8|SUCCESS| 2|   8|        8| 8|        4|      10000|          4|        0|        10|      10
       9|SUCCESS| 2|   9|        8| 9|        4|      10000|          4|        0|        10|      10
-----------------------------------------------------------------------------------------------------
19:26:27.199 [main] INFO  simulation2c - -----Cost for simulation2c is-----: 505.0

```

### Model_Sim

* Uses a VmAllocationPolicyRoundRobin, space shared VM Scheduler policy and Full Utilization Models for cloudlets.

* Result of this simulation is as below.


```

                                         SIMULATION RESULTS

Cloudlet|Status |DC|Host|Host PEs |VM|VM PEs   |CloudletLen|CloudletPEs|StartTime|FinishTime|ExecTime
      ID|       |ID|  ID|CPU cores|ID|CPU cores|         MI|  CPU cores|  Seconds|   Seconds| Seconds
-----------------------------------------------------------------------------------------------------
       3|SUCCESS| 2|   3|       25| 3|        4|       1000|          2|        0|         2|       2
       4|SUCCESS| 2|   4|       25| 4|        4|       1000|          2|        0|         2|       2
       5|SUCCESS| 2|   5|       25| 5|        4|       1000|          2|        0|         2|       2
       6|SUCCESS| 2|   6|       25| 6|        4|       1000|          2|        0|         2|       2
       7|SUCCESS| 3|   0|       25| 7|        4|       1000|          2|        0|         2|       2
       8|SUCCESS| 3|   1|       25| 8|        4|       1000|          2|        0|         2|       2
       9|SUCCESS| 3|   2|       25| 9|        4|       1000|          2|        0|         2|       2
      10|SUCCESS| 3|   3|       25|10|        4|       1000|          2|        0|         2|       2
      11|SUCCESS| 3|   4|       25|11|        4|       1000|          2|        0|         2|       2
       0|SUCCESS| 2|   0|       25| 0|        3|       1000|          2|        0|         3|       3
       1|SUCCESS| 2|   1|       25| 1|        3|       1000|          2|        0|         3|       3
       2|SUCCESS| 2|   2|       25| 2|        3|       1000|          2|        0|         3|       3
-----------------------------------------------------------------------------------------------------
19:49:09.457 [main] INFO  models - ------Cost of IaaS model is------: 272.79999999999995
19:49:09.457 [main] INFO  models - ------Cost of PaaS model is------: 144.6
19:49:09.457 [main] INFO  models - ------Cost of SaaS model is------: 96.39999999999999

```

## Result Analysis

1.Results show Simulation1a is cheaper than Simulation1b where the only difference is the Simulation1a runs with space share VM scheduler where as Simulation1b runs with time shared VM scheduler policy. This is due to the fact that whenever there are not enough VMs to run all the cloudlets independently, the TimeShared policy allocates cloudlets on the same VM and runs than concurrently, therefore yelding to a higher cost. We can think of it as if the computational resources (i.e. CPUs) are used by the two cloudlets alternatively (shared CPUs).

2.We can see the cost of Simulation2a is expensive than Simulation2b where the only difference is Simulaiton2a runs with Stochastic Utilization Models for cloudlets where as Simulation2b runs with Full Utilization Models for cloudlets. This is due to the randomness in the model. So, Full UtilizationModel would work better in general unless some excellent seed value is provided to the Stochastic Model.

3.The cost of Simulation2b and Simulation2c are same where the Simulation2b uses VmAllocationPolicyRoundRobin where as Simulation2c uses VmAllocationPolicyFirstFit, which shows that there is not much difference of VM allocation policy on these simulaions in term of cost.

4.From the results of model_Sim we can see as expected from the pricing criteria defined, the IaaS service costs the most expensive followed by the PaaS and then SaaS service, which asserts that greater flexibility in providing infrastructure control leads to greater costs as expected.
