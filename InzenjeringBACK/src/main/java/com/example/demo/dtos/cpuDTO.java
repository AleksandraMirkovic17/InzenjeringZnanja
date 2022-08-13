package com.example.demo.dtos;

public class cpuDTO {

    public int baseClock;
    public int cpuMemorySpeed;
    public int logicalCores;
    public String socket;
    public String name;


    public cpuDTO(int baseClock, int cpuMemorySpeed, String socket, String name) {
        this.baseClock = baseClock;
        this.cpuMemorySpeed = cpuMemorySpeed;
        this.socket = socket;
        this.name = name;
    }

    public int getBaseClock() {
        return baseClock;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setBaseClock(int baseClock) {
        this.baseClock = baseClock;
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
}
