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


class ModelTestSuite extends AnyFlatSpec with Matchers {

  val logger: Logger = LoggerFactory.getLogger("models")
  val config: Config = ConfigFactory.load("models")
  val cloudSim = new CloudSim()
  val broker = new ModelBroker(cloudSim)
  val SimulationUtil = new SimulationUtil()

  // Creating DataCenters
  val dataCenterSimple_IaaS = SimulationUtil.create_IaaSDC(cloudSim, "models", new VmAllocationPolicyRoundRobin)
  behavior of "DataCenter IaaS is created properly"

  "IaaS Datacenter" should "be successfully created" in {
    assert(dataCenterSimple_IaaS.getCharacteristics.getCostPerSecond == config.getDouble("models.dataCenter_IaaS.cost"))
  }


  val dataCenterSimple_PaaS = SimulationUtil.create_PaaSDC(cloudSim, "models", new VmAllocationPolicyRoundRobin)
  behavior of "DataCenter IaaS is created properly"

  "PaaS Datacenter" should "be successfully created" in {
    assert(dataCenterSimple_PaaS.getCharacteristics.getCostPerSecond == config.getDouble("models.dataCenter_PaaS.cost"))
  }

  val dataCenterSimple_SaaS = SimulationUtil.create_SaaSDC(cloudSim, "models", new VmAllocationPolicyRoundRobin)
  behavior of "DataCenter IaaS is created properly"

  "SaaS Datacenter" should "be successfully created" in {
    assert(dataCenterSimple_SaaS.getCharacteristics.getCostPerSecond == config.getDouble("models.dataCenter_SaaS.cost"))
  }

  // Creating Cloudlets
  val cloudletList: util.List[ModelCloudlet] = SimulationUtil.createModelCloudlets("Python", "MongoDB")
  behavior of "Cloudlets are created properly"
  "Cloudlets" should "be successfully created" in{
    assert(cloudletList.size() == config.getInt("models.cloudLet.number") * 3)
  }



}
