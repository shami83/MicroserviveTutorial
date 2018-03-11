package com.example.EmployeeDashBoardService.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.example.EmployeeDashBoardService.domain.model.EmployeeInfo;
import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;

@RefreshScope
@RestController
public class EmployeeInfoController {
	
	 @Autowired
	 private RestTemplate restTemplate;
	 
	 @Autowired
	 private EurekaClient eurekaClient;
	 
	 @Value("${service.employyesearch.serviceId}")
	 private String employeeSearchServiceId;


	@RequestMapping("/dashboard/{myself}")
	@HystrixCommand(fallbackMethod="defaultMe")
	public EmployeeInfo findme(@PathVariable Long myself){
		Application application = eurekaClient.getApplication(employeeSearchServiceId);
	    InstanceInfo instanceInfo = application.getInstances().get(0);
	    String url = "http://"+instanceInfo.getIPAddr()+ ":"+instanceInfo.getPort()+"/"+"employee/find/"+myself;
	    System.out.println("URL" + url);
	    EmployeeInfo emp = restTemplate.getForObject(url, EmployeeInfo.class);
	    System.out.println("RESPONSE " + emp);
	    return emp;
	}
	
	private EmployeeInfo defaultMe(Long id){
		EmployeeInfo info = new EmployeeInfo();
		info.setEmployeeId(id);
		info.setName("Hystrix fallback");
		info.setCompanyInfo("Netfilx");
		info.setDesignation("Fallback");
		return info;
	}
	
	
	@RequestMapping("/dashboard/peers")
	public  Collection<EmployeeInfo> findPeers(){
		Application application = eurekaClient.getApplication(employeeSearchServiceId);
	    InstanceInfo instanceInfo = application.getInstances().get(0);
	    String url = "http://"+instanceInfo.getIPAddr()+ ":"+instanceInfo.getPort()+"/"+"employee/findall";
	    System.out.println("URL" + url);
	    Collection<EmployeeInfo> list= restTemplate.getForObject(url, Collection.class);
	     System.out.println("RESPONSE " + list);
	    return list;
	}
}
