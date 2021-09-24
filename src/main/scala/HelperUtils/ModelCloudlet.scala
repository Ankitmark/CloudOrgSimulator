package HelperUtils

import org.cloudbus.cloudsim.cloudlets.CloudletSimple
import org.cloudbus.cloudsim.utilizationmodels.UtilizationModel
import org.slf4j.{Logger, LoggerFactory}

//Create specified cloudlets for different models
class ModelCloudlet(current: String, lang: String, dataStore: String, length: Int, pesNumber: Int, model: UtilizationModel) extends CloudletSimple(length: Int, pesNumber: Int, model: UtilizationModel){
  val service: String = current
  val logger: Logger = LoggerFactory.getLogger("ModelCloudlet")
  def this(current: String, length: Int, pesNumber: Int, model: UtilizationModel) = this(current, "", "", length, pesNumber, model)
}
