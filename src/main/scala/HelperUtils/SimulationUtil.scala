package HelperUtils

import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicy
import org.cloudbus.cloudsim.brokers.{DatacenterBroker, DatacenterBrokerSimple}
import org.cloudbus.cloudsim.cloudlets.{Cloudlet, CloudletSimple}
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.datacenters.{Datacenter, DatacenterSimple}
import org.cloudbus.cloudsim.hosts.{Host, HostSimple}
import org.cloudbus.cloudsim.network.topologies.BriteNetworkTopology
import org.cloudbus.cloudsim.resources.{Pe, PeSimple}
import org.cloudbus.cloudsim.schedulers.cloudlet.{CloudletSchedulerSpaceShared, CloudletSchedulerTimeShared}
import org.cloudbus.cloudsim.schedulers.vm.{VmSchedulerSpaceShared, VmSchedulerTimeShared}
import org.cloudbus.cloudsim.utilizationmodels.*
import org.cloudbus.cloudsim.vms.{Vm, VmSimple}
import org.slf4j.{Logger, LoggerFactory}

import java.util
import scala.collection.mutable.ListBuffer
import scala.jdk.CollectionConverters.*

class SimulationUtil {

  val logger: Logger = LoggerFactory.getLogger("SimulationUtil")
  var current: String = _
  var cloudSim: CloudSim = _


  /**
   * Creates a Simple DataCenter with its operating costs
   * @param current: Simulation Number
   * @param vmAllocationPolicy: Allocation Policy for the Vm of the DataCenter
   * @return SimpleDataCenter object
   */
  def create_DataCenter(current: String, vmAllocationPolicy: VmAllocationPolicy): DatacenterSimple = {
    this.current = current
    val simulated_DC = new DataCenter_conf(current)
    val dc = new DatacenterSimple(cloudSim, create_Host(), vmAllocationPolicy)
    dc.getCharacteristics
      .setCostPerBw(simulated_DC.costPerBw)
      .setCostPerMem(simulated_DC.costPerMemory)
      .setCostPerSecond(simulated_DC.costPerSecond)
      .setCostPerStorage(simulated_DC.costPerStorage)
    logger.info("DataCenter Created")
    dc
  }

  /**
   * Creates a SaaS DataCenter with its operating costs
   * @param current: Simulation Number
   * @param vmAllocationPolicy: Allocation Policy for the Vm of the DataCenter
   * @return SimpleDataCenter object
   */
  def create_SaaSDC(cloudSim: CloudSim, current: String, vmAllocationPolicy: VmAllocationPolicy): DatacenterSimple = {
    this.current = current
    val simulated_DC = new SaaS_DC_conf(current)
    val dc = new DatacenterSimple(cloudSim, create_Host(), vmAllocationPolicy)
    dc.getCharacteristics
      .setCostPerBw(simulated_DC.costPerBw)
      .setCostPerMem(simulated_DC.costPerMemory)
      .setCostPerSecond(simulated_DC.costPerSecond)
      .setCostPerStorage(simulated_DC.costPerStorage)
    logger.info("SaaS DataCenter Created")
    dc
  }

  /**
   * Creates a IaaS DataCenter, with its operating costs
   * @param current: Simulation Number
   * @param vmAllocationPolicy: Allocation Policy for the Vm of the DataCenter
   * @return SimpleDataCenter object
   */
  def create_IaaSDC(cloudSim: CloudSim, current: String, vmAllocationPolicy: VmAllocationPolicy) : DatacenterSimple = {
    this.current = current
    val simulated_DC = new IaaS_DC_conf(current)
    val dc = new DatacenterSimple(cloudSim, create_Host(), vmAllocationPolicy)
    dc.getCharacteristics
      .setCostPerBw(simulated_DC.costPerBw)
      .setCostPerMem(simulated_DC.costPerMemory)
      .setCostPerSecond(simulated_DC.costPerSecond)
      .setCostPerStorage(simulated_DC.costPerStorage)
    logger.info("IaaS DataCenter Created")
    dc
  }

  /**
   * Creates a PaaS DataCenter with its operating costs
   * @param current: Simulation Number
   * @param vmAllocationPolicy: Allocation Policy for the Vm of the DataCenter
   * @return SimpleDataCenter object
   */
  def create_PaaSDC(cloudSim: CloudSim, current: String, vmAllocationPolicy: VmAllocationPolicy): DatacenterSimple = {
    this.current = current
    val simulated_DC = new PaaS_DC_conf(current)
    val dc = new DatacenterSimple(cloudSim, create_Host(), vmAllocationPolicy)
    dc.getCharacteristics
      .setCostPerBw(simulated_DC.costPerBw)
      .setCostPerMem(simulated_DC.costPerMemory)
      .setCostPerSecond(simulated_DC.costPerSecond)
      .setCostPerStorage(simulated_DC.costPerStorage)
    logger.info("PaaS DataCenter Created")
    dc
  }

  /**
   * Creates a list of hosts based on the specified parameters in the config file
   * @return List of Host
   */
  def create_Host(): util.List[Host] = {
    val simulated_Host = new Host_conf(current)
    val number = simulated_Host.number
    val peList: List[Pe] = List.tabulate(simulated_Host.pesNumber) (_ => new PeSimple(simulated_Host.mips))
    var hostList: ListBuffer[Host] = new ListBuffer[Host]
    if(simulated_Host.scheduler == "time"){
      hostList = ListBuffer.tabulate(number) (_ => new HostSimple(simulated_Host.ram, simulated_Host.bw, simulated_Host.storage, peList.asJava).setVmScheduler(new VmSchedulerTimeShared()))
    }
    else{
      hostList = ListBuffer.tabulate(number) (_ => new HostSimple(simulated_Host.ram, simulated_Host.bw, simulated_Host.storage, peList.asJava).setVmScheduler(new VmSchedulerSpaceShared()))
    }
    logger.info("Host List Created")
    hostList.asJava
  }

  /**
   * Creates a list of Vms based on the specified parameters in the config file
   * @return List of Vm
   */
  def create_Vm(): util.List[Vm] ={
    val simulated_Vm = new Vm_conf(current)
    val vmList: List[Vm] = List.tabulate(simulated_Vm.number) (_ => {
      val vm = new VmSimple(simulated_Vm.mips, simulated_Vm.pes)
      vm.setRam(simulated_Vm.ram).setBw(simulated_Vm.bw).setSize(simulated_Vm.size)
      if(simulated_Vm.scheduler == "time"){
        vm.setCloudletScheduler(new CloudletSchedulerTimeShared())
      }
      else{
        vm.setCloudletScheduler(new CloudletSchedulerSpaceShared())
      }
      vm
    })
    logger.info("Vm List Created")
    vmList.asJava
  }

  /**
   * Creates a list of IaaS Vms based on the user input
   * @return List of Vm
   */
  def create_IaaSVm(number: Int, mips: Int, pes: Int, ram: Int, bw: Int, size: Int): util.List[Vm] ={
    val vmList: List[Vm] = List.tabulate(number) (_ => {
      val vm = new VmSimple(mips, pes)
      vm.setRam(ram).setBw(bw).setSize(size)
      vm
    })
    logger.info("Vm List Created")
    vmList.asJava
  }

  /**
   * Creates a list of Cloudlets based on the specified parameters in the config file
   * @return List of Cloudlets
   */
  def create_Cloudlet() : util.List[Cloudlet] = {
    val simulatedCloudlet = new CloudLet_conf(current)
    var cloudletList = new ListBuffer[Cloudlet]
    if(simulatedCloudlet.utilizationModel == "Stochastic"){
      cloudletList = ListBuffer.tabulate(simulatedCloudlet.number) (_ => new CloudletSimple(simulatedCloudlet.length, simulatedCloudlet.pesNumber, new UtilizationModelStochastic(5)))
    }
    else{
      cloudletList = ListBuffer.tabulate(simulatedCloudlet.number) (_ => new CloudletSimple(simulatedCloudlet.length, simulatedCloudlet.pesNumber, new UtilizationModelFull()))
    }
    logger.info("Cloudlet list created")
    cloudletList.asJava
  }

  /**
   * Creates cloudlets of all the services and returns those in a list
   * @param Language: Language for PaaS Implementation
   * @param dataBase: DataStore for PaaS Implementation
   * @return List of Model Cloudlets
   */
  def createModelCloudlets(Language: String, dataBase: String): util.List[ModelCloudlet] = {
    val simulatedCloudlet = new CloudLet_conf(current)
    val cloudletList1: List[ModelCloudlet] = List.tabulate(simulatedCloudlet.number)(_ => new ModelCloudlet("IaaS", simulatedCloudlet.length, simulatedCloudlet.pesNumber, new UtilizationModelFull()))
    val cloudletList2: List[ModelCloudlet] = List.tabulate(simulatedCloudlet.number)(_ => new ModelCloudlet("PaaS", Language, dataBase, simulatedCloudlet.length, simulatedCloudlet.pesNumber, new UtilizationModelFull()))
    val cloudletList3: List[ModelCloudlet] = List.tabulate(simulatedCloudlet.number)(_ => new ModelCloudlet("SaaS", simulatedCloudlet.length, simulatedCloudlet.pesNumber, new UtilizationModelFull()))
    logger.info("Cloudlet list created")
    (cloudletList1 ::: cloudletList2 ::: cloudletList3).asJava
  }

  /**
   * Creates a SimpleBroker object
   * @param cloudSim: CloudSim object
   * @return SimpleBroker object
   */
  def create_Broker(cloudSim: CloudSim): DatacenterBrokerSimple = {
    this.cloudSim = cloudSim
    new DatacenterBrokerSimple(cloudSim)
  }

  /**
   * Sets the network topology
   * @param NETWORK_TOPOLOGY_FILE: Name of the topology file
   * @param dc: DataCenter object
   * @param broker: Broker object
   */

  def Network_config(NETWORK_TOPOLOGY_FILE : String, dc: Datacenter, broker: DatacenterBroker): Unit = {
    val networkTopology = BriteNetworkTopology.getInstance(NETWORK_TOPOLOGY_FILE)
    cloudSim.setNetworkTopology(networkTopology)
    networkTopology.mapNode(dc, 0)
    networkTopology.mapNode(broker, 3)
    logger.info("Network Configured")
  }

  /**
   * Calculates cost of running the simulation on a datacenter with Model Cloudlet
   * @param dataCenter: dataCenter object
   * @param cloudletList: List of Model cloudlets
   * @return Total cost of the simulation
   */
  def modelcost(dataCenter: Datacenter, cloudletList: util.List[ModelCloudlet]): Double = {
    var cost: Double = 0.0
    val pricePerSecond = cloudletList.asScala.map(x => x.getCostPerSec(dataCenter))
    val finishTime = cloudletList.asScala.map(x => x.getFinishTime)
    cost = List.tabulate(finishTime.size)(x => pricePerSecond(x) * finishTime(x)).sum
    cost
  }

  /**
   * Calculates cost of running the simulation on a datacenter with cloudlets
   * @param dataCenter: dataCenter object
   * @param cloudletList: List of cloudlets
   * @return Total cost of the simulation
   */
  def cost(dataCenter: Datacenter, cloudletList: util.List[Cloudlet]) : Double = {
    var cost: Double = 0.0
    val pricePerSecond = cloudletList.asScala.map(x => x.getCostPerSec(dataCenter))
    val finishTime = cloudletList.asScala.map(x => x.getFinishTime)
    cost = List.tabulate(finishTime.size)(x => pricePerSecond(x) * finishTime(x)).sum
    cost
  }

}
