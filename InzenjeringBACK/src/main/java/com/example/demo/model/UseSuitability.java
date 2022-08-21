package com.example.demo.model;

public class UseSuitability {
	public float casualScore;
	public float gamingScore;
	public float miningScore;
	public float hostingScore;
	
	public UseSuitability() {}
	
	public UseSuitability(float casualScore, float gamingScore, float miningScore, float hostingScore) {
		this.casualScore = casualScore;
		this.gamingScore = gamingScore;
		this.miningScore = miningScore;
		this.hostingScore = hostingScore;
	}
	
	public void print() {
		System.out.println("Casual Use: " + casualScore);
		System.out.println("Gaming Use: " + gamingScore);
		System.out.println("Mining Use: " + miningScore);
		System.out.println("Hosting Use: " + hostingScore);
	}
}
