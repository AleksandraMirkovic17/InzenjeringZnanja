package com.example.demo.services;

import com.example.demo.model.UseSuitability;

public class UseSuitabilityService {

	private boolean isMinimumSatisfied(int cores, float gpuSpeed, int psu, float ram, float storage, float vram,
									   int minCores, float minGpuSpeed, int minPsu, float minRam, float minStorage, float minVram) {
		if (cores < minCores)
			return false;
		if (gpuSpeed < minGpuSpeed)
			return false;
		if (psu < minPsu)
			return false;
		if (ram < minRam)
			return false;
		if (storage < minStorage)
			return false;
		if (vram < minVram)
			return false;
		return true;
	}
	
	private boolean IsMinimumCasual(int cores, float gpuSpeed, int psu, float ram, float storage, float vram) {
		int minCores = 1;
		float minGpuSpeed = 0;
		int minPsu = 300;
		float minRam = 2;
		float minStorage = 0;
		float minVram = 0;
		
		return isMinimumSatisfied(cores, gpuSpeed, psu, ram, storage, vram, minCores, minGpuSpeed, minPsu, minRam, minStorage, minVram);
	}
	
	private boolean IsMinimumGaming(int cores, float gpuSpeed, int psu, float ram, float storage, float vram) {
		int minCores = 2;
		float minGpuSpeed = 0.5f;
		int minPsu = 400;
		float minRam = 4;
		float minStorage = 0;
		float minVram = 3;
		
		return isMinimumSatisfied(cores, gpuSpeed, psu, ram, storage, vram, minCores, minGpuSpeed, minPsu, minRam, minStorage, minVram);
	}
	
	private boolean IsMinimumMining(int cores, float gpuSpeed, int psu, float ram, float storage, float vram) {
		int minCores = 1;
		float minGpuSpeed = 10f;
		int minPsu = 800;
		float minRam = 5;
		float minStorage = 0.5f;
		float minVram = 5;
		
		return isMinimumSatisfied(cores, gpuSpeed, psu, ram, storage, vram, minCores, minGpuSpeed, minPsu, minRam, minStorage, minVram);
	}
	
	private boolean IsMinimumHosting(int cores, float gpuSpeed, int psu, float ram, float storage, float vram) {
		int minCores = 8;
		float minGpuSpeed = 0;
		int minPsu = 800;
		float minRam = 16;
		float minStorage = 0;
		float minVram = 0;
		
		return isMinimumSatisfied(cores, gpuSpeed, psu, ram, storage, vram, minCores, minGpuSpeed, minPsu, minRam, minStorage, minVram);
	}
	
	public void testSuitability() {
		int coreNumber = 8;
		float gpuSpeed = 15;
		int psuWatts = 600;
		float ramCapacity = 8;
		float storage = 1.5f;
		float vramCapacity = 10;
		
		UseSuitability suitability = FuzzyUseSuitabilityService.getFuzzyUseSuitability(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity);
		
		if (IsMinimumCasual(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity))
			suitability.casualScore = 0;
		if (IsMinimumGaming(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity))
			suitability.gamingScore = 0;
		if (IsMinimumMining(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity))
			suitability.miningScore = 0;
		if (IsMinimumHosting(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity))
			suitability.hostingScore = 0;
		
		suitability.print();
	}
}
