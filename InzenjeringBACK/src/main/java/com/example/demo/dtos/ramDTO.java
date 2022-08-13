package com.example.demo.dtos;

public class ramDTO {
    public String name;
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

    public ramDTO(String name, int ramCapacity, int ramFrequency) {
        this.name = name;
        this.ramCapacity = ramCapacity;
        this.ramFrequency = ramFrequency;
    }
}
