package com.example.demo.dtos;

public class ssdDTO {
    public String name;
    public String memoryCapacity;
    public String memoryInterface;

    public ssdDTO()
    {

    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getMemoryCapacity() {
        return memoryCapacity;
    }

    public void setMemoryCapacity(String memoryCapacity) {
        this.memoryCapacity = memoryCapacity;
    }

    public String getMemoryInterface() {
        return memoryInterface;
    }

    public void setMemoryInterface(String memoryInterface) {
        this.memoryInterface = memoryInterface;
    }
}
