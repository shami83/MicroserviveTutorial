package com.example.EmployeeDashBoardService.domain.model;

public class EmployeeInfo {
	private Long employeeId;
	private String name;
	private String practiceArea;
	private String designation;
	private String companyInfo;
	public Long getEmployeeId() {
		return employeeId;
	}
	public void setEmployeeId(Long employeeId) {
		this.employeeId = employeeId;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getPracticeArea() {
		return practiceArea;
	}
	public void setPracticeArea(String practiceArea) {
		this.practiceArea = practiceArea;
	}
	public String getDesignation() {
		return designation;
	}
	public void setDesignation(String designation) {
		this.designation = designation;
	}
	public String getCompanyInfo() {
		return companyInfo;
	}
	public void setCompanyInfo(String companyInfo) {
		this.companyInfo = companyInfo;
	}
	@Override
	public String toString() {
		return "EmployeeInfo [employeeId=" + employeeId + ", name=" + name + ", practiceArea=" + practiceArea
				+ ", designation=" + designation + ", companyInfo=" + companyInfo + "]";
	}
	
	

}
