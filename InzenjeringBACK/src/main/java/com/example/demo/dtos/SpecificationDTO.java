package com.example.demo.dtos;

public class SpecificationDTO {
	private String cpu;
	private String gpu;
	private String hdd;
	private String ssd;
	private String ram;
	private String psu;
	
	public SpecificationDTO() {}
	
	public SpecificationDTO(String cpu, String gpu, String hdd, String ssd, String ram, String psu) {
		this.cpu = cpu;
		this.gpu = gpu;
		this.hdd = hdd;
		this.ssd = ssd;
		this.ram = ram;
		this.psu = psu;
	}
	
	public String getCpu() {
		return cpu;
	}
	public void setCpu(String cpu) {
		this.cpu = cpu;
	}
	public String getGpu() {
		return gpu;
	}
	public void setGpu(String gpu) {
		this.gpu = gpu;
	}
	public String getHdd() {
		return hdd;
	}
	public void setHdd(String hdd) {
		this.hdd = hdd;
	}
	public String getSsd() {
		return ssd;
	}
	public void setSsd(String ssd) {
		this.ssd = ssd;
	}
	public String getRam() {
		return ram;
	}
	public void setRam(String ram) {
		this.ram = ram;
	}
	public String getPsu() {
		return psu;
	}
	public void setPsu(String psu) {
		this.psu = psu;
	}
}
