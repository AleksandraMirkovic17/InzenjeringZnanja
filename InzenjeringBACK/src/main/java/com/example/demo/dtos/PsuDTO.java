package com.example.demo.dtos;

public class PsuDTO {
	String name;
	private String certificate;
	private int power;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getCertificate() {
		return certificate;
	}
	public void setCertificate(String certificate) {
		this.certificate = certificate;
	}
	public int getPower() {
		return power;
	}
	public void setPower(int power) {
		this.power = power;
	}
	
	public PsuDTO(String name, String certificate, int power) {
		this.name = name;
		this.certificate = certificate;
		this.power = power;
	}
}
