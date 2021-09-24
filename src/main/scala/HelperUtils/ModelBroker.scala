package HelperUtils

import org.cloudbus.cloudsim.brokers.DatacenterBrokerSimple
import org.cloudbus.cloudsim.core.CloudSim
import org.cloudbus.cloudsim.datacenters.DatacenterSimple

import java.util

class ModelBroker(cloudSim: CloudSim) extends DatacenterBrokerSimple(cloudSim: CloudSim){

  /**
   * Separates the Cloudlets based on their services
   * @param IaaS_DC: IaaS DataCenter object
   * @param PaaS_DC: PaaS DataCenter object
   * @param SaaS_DC: SaaS DataCenter object
   * @param cloudletList: List of Model Cloudlet objects
   * @return List of List of separated service cloudlets
   */
  def allocate(IaaS_DC: DatacenterSimple, PaaS_DC: DatacenterSimple, SaaS_DC: DatacenterSimple, cloudletList: java.util.List[ModelCloudlet]) : util.List[util.List[ModelCloudlet]] = {
    val list: util.List[util.List[ModelCloudlet]] = new util.ArrayList[util.List[ModelCloudlet]]
    val IaaSCloudlet: util.List[ModelCloudlet] =  new util.ArrayList[ModelCloudlet]
    val PaaSCloudlet: util.List[ModelCloudlet] = new util.ArrayList[ModelCloudlet]
    val SaaSCloudlet: util.List[ModelCloudlet] = new util.ArrayList[ModelCloudlet]

    cloudletList.forEach(x => {
       if(x.service == "SaaS"){
        x.assignToDatacenter(SaaS_DC)
        SaaSCloudlet.add(x)
      }
      else if(x.service == "PaaS"){
        x.assignToDatacenter(PaaS_DC)
        PaaSCloudlet.add(x)
      }
       else if(x.service == "IaaS"){
         x.assignToDatacenter(IaaS_DC)
         IaaSCloudlet.add(x)
       }
    })
    list.add(IaaSCloudlet)
    list.add(PaaSCloudlet)
    list.add(SaaSCloudlet)
    list
  }
}
