package com.example.demo.dtos;

public class cpuDTO {

    public int turboBoost;
    public int cpuMemorySpeed;
    public int logicalCores;
    public int physicalCores;
    public int baseClock;
    public String socket;
    public String name;

    public cpuDTO() {}

    public cpuDTO(int turboBoost, int cpuMemorySpeed, String socket, String name) {
        this.turboBoost = turboBoost;
        this.cpuMemorySpeed = cpuMemorySpeed;
        this.socket = socket;
        this.name = name;
    }

    public int getTurboBoost() {
        return turboBoost;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setTurboBoost(int turboBoost) {
        this.turboBoost = turboBoost;
    }

    public int getCpuMemorySpeed() {
        return cpuMemorySpeed;
    }

    public void setCpuMemorySpeed(int cpuMemorySpeed) {
        this.cpuMemorySpeed = cpuMemorySpeed;
    }

    public int getLogicalCores() {
        return logicalCores;
    }

    public void setLogicalCores(int logicalCores) {
        this.logicalCores = logicalCores;
    }

    public String getSocket() {
        return socket;
    }

    public void setSocket(String socket) {
        this.socket = socket;
    }

	public int getPhysicalCores() {
		return physicalCores;
	}

	public void setPhysicalCores(int physicalCores) {
		this.physicalCores = physicalCores;
	}

	public int getBaseClock() {
		return baseClock;
	}

	public void setBaseClock(int baseClock) {
		this.baseClock = baseClock;
	}
	
	
}
