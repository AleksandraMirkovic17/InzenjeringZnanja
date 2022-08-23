package com.example.demo.dtos;

import ucm.gaia.jcolibri.cbrcore.Attribute;
import ucm.gaia.jcolibri.cbrcore.CaseComponent;

public class csvDTO {

    private int motherboardDDR;
    private int hddMemoryCapacity;
    private int ramCapacity;
    private String motherboardSocket;
    private String motherboardName;
    private int ramFrequency;
    private int turboBoost;
    private String ramName;
    private int cpuMemorySpeed;
    private int physicalCores;
    private String cpuName;

    private int gpuTeraflops;
    private int gpuMemory;

    public csvDTO()
    {

    }

    public int getMotherboardDDR() {
        return motherboardDDR;
    }

    public void setMotherboardDDR(int motherboardDDR) {
        this.motherboardDDR = motherboardDDR;
    }

    public int getHddMemoryCapacity() {
        return hddMemoryCapacity;
    }

    public void setHddMemoryCapacity(int hddMemoryCapacity) {
        this.hddMemoryCapacity = hddMemoryCapacity;
    }

    public int getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(int ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public String getMotherboardSocket() {
        return motherboardSocket;
    }

    public void setMotherboardSocket(String motherboardSocket) {
        this.motherboardSocket = motherboardSocket;
    }

    public String getMotherboardName() {
        return motherboardName;
    }

    public void setMotherboardName(String motherboardName) {
        this.motherboardName = motherboardName;
    }



    public int getRamFrequency() {
        return ramFrequency;
    }

    public void setRamFrequency(int ramFrequency) {
        this.ramFrequency = ramFrequency;
    }

    public int getTurboBoost() {
        return turboBoost;
    }

    public void setTurboBoost(int turboBoost) {
        this.turboBoost = turboBoost;
    }

    public String getRamName() {
        return ramName;
    }

    public void setRamName(String ramName) {
        this.ramName = ramName;
    }

    public int getCpuMemorySpeed() {
        return cpuMemorySpeed;
    }

    public void setCpuMemorySpeed(int cpuMemorySpeed) {
        this.cpuMemorySpeed = cpuMemorySpeed;
    }

    public int getPhysicalCores() {
        return physicalCores;
    }

    public void setPhysicalCores(int physicalCores) {
        this.physicalCores = physicalCores;
    }

    public String getCpuName() {
        return cpuName;
    }

    public void setCpuName(String cpuName) {
        this.cpuName = cpuName;
    }

    public int getGpuTeraflops() {
        return gpuTeraflops;
    }

    public void setGpuTeraflops(int gpuTeraflops) {
        this.gpuTeraflops = gpuTeraflops;
    }

    public int getGpuMemory() {
        return gpuMemory;
    }

    public void setGpuMemory(int gpuMemory) {
        this.gpuMemory = gpuMemory;
    }

    public csvDTO(int motherboardDDR, int hddMemoryCapacity, int ramCapacity, String motherboardSocket, String motherboardName, int ramFrequency, int turboBoost, String ramName, int cpuMemorySpeed, int physicalCores, String cpuName, int gpuTeraflops, int gpuMemory) {
        this.motherboardDDR = motherboardDDR;
        this.hddMemoryCapacity = hddMemoryCapacity;
        this.ramCapacity = ramCapacity;
        this.motherboardSocket = motherboardSocket;
        this.motherboardName = motherboardName;
        this.ramFrequency = ramFrequency;
        this.turboBoost = turboBoost;
        this.ramName = ramName;
        this.cpuMemorySpeed = cpuMemorySpeed;
        this.physicalCores = physicalCores;
        this.cpuName = cpuName;
        this.gpuTeraflops = gpuTeraflops;
        this.gpuMemory = gpuMemory;
    }
}
