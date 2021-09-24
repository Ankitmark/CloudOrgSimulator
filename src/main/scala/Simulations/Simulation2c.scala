package Simulations

import HelperUtils.{CreateLogger, ObtainConfigReference,ModelBroker,SimulationUtil}

import org.cloudbus.cloudsim.allocationpolicies.{VmAllocationPolicyRoundRobin, VmAllocationPolicyFirstFit}
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudsimplus.builders.tables.CloudletsTableBuilder
import org.slf4j.LoggerFactory

class Simulation2c

object Simulation2c:
  @main def start2c()= {

    val logger = LoggerFactory.getLogger("simulation2b")

    val SimulationUtil = new SimulationUtil()

    // Creating CloudSim object
    val cloudSim = new CloudSim()

    // Creating Broker
    val dataCenterBrokerSimple = SimulationUtil.create_Broker(cloudSim)

    // Creating DataCenter
    val dataCenterSimple = SimulationUtil.create_DataCenter("simulation2c", new VmAllocationPolicyFirstFit)

    // Creating VMs
    val vmList = SimulationUtil.create_Vm()

    // Creating Cloudlets
    val cloudletList = SimulationUtil.create_Cloudlet()

    // Submit VmList and CloudletLists to the broker
    dataCenterBrokerSimple.submitVmList(vmList).submitCloudletList(cloudletList)

    logger.info("Simulation2c starting")

    cloudSim.start()

    new CloudletsTableBuilder(dataCenterBrokerSimple.getCloudletFinishedList).build()

    // Display the total costs for running the simulation
    logger.info("-----Cost for simulation2c is-----: " + SimulationUtil.cost(dataCenterSimple, cloudletList).toString)

  }
