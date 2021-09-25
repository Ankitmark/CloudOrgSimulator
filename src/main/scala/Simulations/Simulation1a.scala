package Simulations

import HelperUtils.{CreateLogger, ObtainConfigReference,ModelBroker,SimulationUtil}

import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicyRoundRobin
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudsimplus.builders.tables.CloudletsTableBuilder
import org.slf4j.LoggerFactory

class Simulation1a

object Simulation1a:
  @main def start1a()= {

  val logger = LoggerFactory.getLogger("simulation1a")

  val SimulationUtil = new SimulationUtil()

  // Creating CloudSim object
  val cloudSim = new CloudSim()

  // Creating Broker
  val dataCenterBrokerSimple = SimulationUtil.create_Broker(cloudSim)

  // Creating DataCenter
  val dataCenterSimple = SimulationUtil.create_DataCenter("simulation1a", new VmAllocationPolicyRoundRobin)

  // Creating VMs
  val vmList = SimulationUtil.create_Vm()

  // Creating Cloudlets
  val cloudletList = SimulationUtil.create_Cloudlet()

  // Submit VmList and CloudletLists to the broker
  dataCenterBrokerSimple.submitVmList(vmList).submitCloudletList(cloudletList)

  logger.info("Simulation1a starting")

  cloudSim.start()

  new CloudletsTableBuilder(dataCenterBrokerSimple.getCloudletFinishedList).build()

  // Display the total costs for running the simulation
  logger.info("-----Cost for simulation1a is-----: " + SimulationUtil.cost(dataCenterSimple, cloudletList).toString)
}
