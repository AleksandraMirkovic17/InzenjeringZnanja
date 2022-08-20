package com.example.demo.dtos;

public class gpuDTO {

    public String name;
    public String gpuSpeed;
    public int gpuMemory;
    public int gpuTeraflops;

    public gpuDTO()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getGpuSpeed() {
        return gpuSpeed;
    }

    public void setGpuSpeed(String gpuSpeed) {
        this.gpuSpeed = gpuSpeed;
    }

    public int getGpuMemory() {
        return gpuMemory;
    }

    public void setGpuMemory(int gpuMemory) {
        this.gpuMemory = gpuMemory;
    }

    public gpuDTO(String name, String gpuSpeed, int gpuMemory, int gpuTeraflops) {
        this.name = name;
        this.gpuSpeed = gpuSpeed;
        this.gpuMemory = gpuMemory;
        this.gpuTeraflops = gpuTeraflops;
    }
}
