package Simulations

import com.typesafe.config.ConfigFactory//-1

import HelperUtils.{CreateLogger, ObtainConfigReference,ModelBroker,SimulationUtil,IaaS_Requirement,PaaS_Requirement}
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicyRoundRobin
import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple
import org.cloudbus.cloudsim.cloudlets.CloudletSimple
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.datacenters.DatacenterSimple
import org.cloudbus.cloudsim.hosts.HostSimple
import org.cloudbus.cloudsim.resources.{Pe, PeSimple}
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModelDynamic
import org.cloudbus.cloudsim.vms.VmSimple
import org.cloudsimplus.builders.tables.CloudletsTableBuilder
import org.slf4j.LoggerFactory

import scala.collection.JavaConverters.*

class Model_Sim

object Model_Sim:

  val logger = LoggerFactory.getLogger("models")

  @main def start()= {
    val cloudSim = new CloudSim()
    val broker = new ModelBroker(cloudSim)
    val SimulationUtil = new SimulationUtil()


    // Creating service data DataCenters
    val IaaS_DataCenter = SimulationUtil.create_IaaSDC(cloudSim, "models", new VmAllocationPolicyRoundRobin)
    val PaaS_DataCenter = SimulationUtil.create_PaaSDC(cloudSim, "models", new VmAllocationPolicyRoundRobin)
    val SaaS_DataCenter = SimulationUtil.create_SaaSDC(cloudSim, "models", new VmAllocationPolicyRoundRobin)

    // Gather required service configurations
    val IaaSRequest = new IaaS_Requirement()
    val PaaSRequest = new PaaS_Requirement()

    // Creating VMs for different services
    IaaS_DataCenter.getCharacteristics.setOs(IaaSRequest.os)
    val vmList_IaaS = SimulationUtil.create_IaaSVm(IaaSRequest.vmNumber, IaaSRequest.vmMips, IaaSRequest.vmPes, IaaSRequest.vmRam, IaaSRequest.vmBw, IaaSRequest.vmSize)
    val vmList_PaaS = SimulationUtil.create_Vm()
    val vmList_SaaS = SimulationUtil.create_Vm()

    // Creating Cloudlets
    val cloudletList = SimulationUtil.createModelCloudlets(PaaSRequest.language, PaaSRequest.dataBase) // -1

    // Broker allocates cloudlets to respective datacenters
    val list = broker.allocate(IaaS_DataCenter, PaaS_DataCenter, SaaS_DataCenter, cloudletList)

    // Submit VmList and CloudletLists to the broker
    broker.submitVmList(vmList_IaaS).submitCloudletList(list.get(0))
    broker.submitVmList(vmList_PaaS).submitCloudletList(list.get(1))
    broker.submitVmList(vmList_SaaS).submitCloudletList(list.get(2))

    logger.info("Model Simulation starting")
    cloudSim.start()

    // Creates and Displays the CloudletTableBuilder
    new CloudletsTableBuilder(broker.getCloudletFinishedList).build()

    // Displays the total costs for each of the service
    logger.info("------Cost of IaaS model is------: " + SimulationUtil.modelcost(IaaS_DataCenter, list.get(0)).toString)
    logger.info("------Cost of PaaS model is------: " + SimulationUtil.modelcost(PaaS_DataCenter, list.get(1)).toString)
    logger.info("------Cost of SaaS model is------: " + SimulationUtil.modelcost(SaaS_DataCenter, list.get(2)).toString)

  }