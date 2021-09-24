package Simulations

import HelperUtils.{CreateLogger, ObtainConfigReference,ModelBroker,SimulationUtil}

import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicyRoundRobin
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudsimplus.builders.tables.CloudletsTableBuilder
import org.slf4j.LoggerFactory

class Simulation1b

object Simulation1b:
  @main def start1b()= {

  val logger = LoggerFactory.getLogger("simulation1b")

  val SimulationUtil = new SimulationUtil()

  // Creating CloudSim object
  val cloudSim = new CloudSim()

  // Creating Broker
  val dataCenterBrokerSimple = SimulationUtil.create_Broker(cloudSim)

  // Creating DataCenter
  val dataCenterSimple = SimulationUtil.create_DataCenter("simulation1b", new VmAllocationPolicyRoundRobin)

  // Creating VMs
  val vmList = SimulationUtil.create_Vm()

  // Creating Cloudlets
  val cloudletList = SimulationUtil.create_Cloudlet()

  // Submit VmList and CloudletLists to the broker
  dataCenterBrokerSimple.submitVmList(vmList).submitCloudletList(cloudletList)

  logger.info("Simulation1b starting")

  cloudSim.start()

  new CloudletsTableBuilder(dataCenterBrokerSimple.getCloudletFinishedList).build()

  // Display the total costs for running the simulation
  logger.info("-----Cost for simulation1b is-----: : " + SimulationUtil.cost(dataCenterSimple, cloudletList).toString)

}
