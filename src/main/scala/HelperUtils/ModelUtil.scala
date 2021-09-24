package HelperUtils

import com.typesafe.config.ConfigFactory

//Wrapper classes to get the configurations from conf files of Service Model
class IaaS_Requirement {
  private val config = ConfigFactory.load("cloud_services")
  val os: String = config.getString("cloud_services.IaaS_Services.os")
  val vmNumber: Int = config.getInt("cloud_services.IaaS_Services.vmNumber")
  val vmMips: Int = config.getInt("cloud_services.IaaS_Services.vmMips")
  val vmPes: Int = config.getInt("cloud_services.IaaS_Services.vmPes")
  val vmRam: Int = config.getInt("cloud_services.IaaS_Services.vmRam")
  val vmBw: Int = config.getInt("cloud_services.IaaS_Services.vmBw")
  val vmSize: Int = config.getInt("cloud_services.IaaS_Services.vmSize")

}

class PaaS_Requirement {
  private val config = ConfigFactory.load("cloud_services")
  val language: String = config.getString("cloud_services.PaaS_Services.language")
  val dataBase: String = config.getString("cloud_services.PaaS_Services.database")

}

class IaaS_DC_conf(current: String) {
  private val config = ConfigFactory.load(current)
  val costPerBw: Int = config.getInt(current + ".dataCenter_IaaS.costPerBandWidth")
  val costPerMemory: Int = config.getInt(current + ".dataCenter_IaaS.costPerMemory")
  val costPerStorage: Int = config.getInt(current + ".dataCenter_IaaS.costPerStorage")
  val costPerSecond: Int = config.getInt(current + ".dataCenter_IaaS.cost")

}

class PaaS_DC_conf(current: String) {
  private val config = ConfigFactory.load(current)
  val costPerBw: Int = config.getInt(current + ".dataCenter_PaaS.costPerBandWidth")
  val costPerMemory: Int = config.getInt(current + ".dataCenter_PaaS.costPerMemory")
  val costPerStorage: Int = config.getInt(current + ".dataCenter_PaaS.costPerStorage")
  val costPerSecond: Int = config.getInt(current + ".dataCenter_PaaS.cost")

}


class SaaS_DC_conf(current: String) {
  private val config = ConfigFactory.load(current)
  val costPerBw: Int = config.getInt(current + ".dataCenter_SaaS.costPerBandWidth")
  val costPerMemory: Int = config.getInt(current + ".dataCenter_SaaS.costPerMemory")
  val costPerStorage: Int = config.getInt(current + ".dataCenter_SaaS.costPerStorage")
  val costPerSecond: Int = config.getInt(current + ".dataCenter_SaaS.cost")

}
