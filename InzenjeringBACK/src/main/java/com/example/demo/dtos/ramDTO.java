package com.example.demo.dtos;

public class ramDTO {
    public String name;
    public int ramDDR;
    public int ramCapacity;
    public int ramFrequency;


    public ramDTO() {

    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRamDDR() {
        return ramDDR;
    }

    public void setRamDDR(int ramDDR) {
        this.ramDDR = ramDDR;
    }

    public int getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(int ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public int getRamFrequency() {
        return ramFrequency;
    }

    public void setRamFrequency(int ramFrequency) {
        this.ramFrequency = ramFrequency;
    }
}
