package com.example.demo.dtos;

public class ProbabilityDTO {

    private String node;
    private float probability;

    public ProbabilityDTO() { }

    public ProbabilityDTO(String name, float probability) {
        this.node = name;
        this.probability = probability;
    }

    public String getNode() {
        return node;
    }

    public void setNode(String node) {
        this.node = node;
    }

    public double getProbability() {
        return probability;
    }

    public void setProbability(float probability) {
        this.probability = probability;
    }

}
