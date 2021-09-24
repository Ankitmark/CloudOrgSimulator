package Simulations

import Simulations.BasicCloudSimPlusExample.config
import org.scalatest.flatspec.AnyFlatSpec
import org.scalatest.matchers.should.Matchers
import org.slf4j.{Logger, LoggerFactory}
import com.typesafe.config.{Config, ConfigFactory}
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.datacenters.DatacenterSimple
import org.cloudbus.cloudsim.vms.Vm
import HelperUtils.{CreateLogger, IaaS_Requirement, ModelBroker, ModelCloudlet, ObtainConfigReference, PaaS_Requirement, SimulationUtil}
import org.cloudbus.cloudsim.allocationpolicies.VmAllocationPolicyRoundRobin

import java.util

class SimulationTestSuite extends AnyFlatSpec with Matchers {

  val config = ConfigFactory.load("simulation1a")
  val SimulationUtil = new SimulationUtil()
  val cloudSim = new CloudSim()

  val dataCenterBrokerSimple = SimulationUtil.create_Broker(cloudSim)
  val dataCenterSimple: DatacenterSimple = SimulationUtil.create_DataCenter("simulation1a", new VmAllocationPolicyRoundRobin)

  behavior of "DataCenter is created properly"

  "Datacenter" should "be successfully created" in {
    assert(dataCenterSimple != null)
  }

  val vmList: util.List[Vm] = SimulationUtil.create_Vm()
  behavior of "DataCenter IaaS is created properly"

  "VMs" should "be successfully created" in {
    assert(vmList != null)
    assert(config.getInt("simulation1a" + ".vm.number") === vmList.size())
  }

  val cloudletList = SimulationUtil.create_Cloudlet()

  behavior of "Cloudlet is created properly"

  "Cloudlet" should "be successfully created" in{
    assert(cloudletList != null)
    assert(config.getInt("simulation1a" + ".cloudLet.number") === cloudletList.size())
  }
  dataCenterBrokerSimple.submitVmList(vmList).submitCloudletList(cloudletList)

}
