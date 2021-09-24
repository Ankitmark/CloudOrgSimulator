package HelperUtils

import com.typesafe.config.ConfigFactory

//Wrapper classes to get the configurations from conf files of different Simulation
class DataCenter_conf(current : String) {
  private val config = ConfigFactory.load(current)
  val costPerBw: Int = config.getInt(current + ".dataCenter.costPerBandWidth")
  val costPerMemory: Int = config.getInt(current + ".dataCenter.costPerMemory")
  val costPerStorage: Int = config.getInt(current + ".dataCenter.costPerStorage")
  val costPerSecond: Int = config.getInt(current + ".dataCenter.cost")
}


class Host_conf(current : String) {
  private val config = ConfigFactory.load(current)
  val number: Int = config.getInt(current + ".host.number")
  val pesNumber: Int = config.getInt(current + ".host.pesNumber")
  val mips: Int = config.getInt(current + ".host.mips")
  val ram: Int = config.getInt(current + ".host.ram")
  val bw: Int = config.getInt(current + ".host.bw")
  val storage: Int = config.getInt(current + ".host.storage")
  val scheduler: String = config.getString(current + ".host.scheduler")
}


class Vm_conf(current : String) {
  private val config = ConfigFactory.load(current)
  val number: Int = config.getInt(current + ".vm.number")
  val mips: Int = config.getInt(current + ".vm.mips")
  val ram: Int = config.getInt(current + ".vm.ram")
  val bw: Int = config.getInt(current + ".vm.bw")
  val size: Int = config.getInt(current + ".vm.size")
  val pes: Int = config.getInt(current + ".vm.pes")
  val scheduler: String = config.getString(current + ".vm.scheduler")
}


class CloudLet_conf(current : String) {
  private val config = ConfigFactory.load(current)
  val number: Int = config.getInt(current + ".cloudLet.number")
  val length: Int = config.getInt(current + ".cloudLet.length")
  val pesNumber: Int = config.getInt(current + ".cloudLet.pesNumber")
  val utilizationModel: String = config.getString(current + ".cloudLet.utilizationModel")
}

