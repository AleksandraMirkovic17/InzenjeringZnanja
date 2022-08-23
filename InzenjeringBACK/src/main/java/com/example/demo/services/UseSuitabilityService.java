package com.example.demo.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dtos.PsuDTO;
import com.example.demo.dtos.SpecificationDTO;
import com.example.demo.dtos.cpuDTO;
import com.example.demo.dtos.gpuDTO;
import com.example.demo.dtos.hddDTO;
import com.example.demo.dtos.ramDTO;
import com.example.demo.dtos.ssdDTO;
import com.example.demo.model.UseSuitability;

@Service
public class UseSuitabilityService {
	/*
    private final OntologyServices service;

    @Autowired
    public QueryService(OntologyServices service) {
        this.service = service;
    }*/
	
	@Autowired
	private QueryService queryService; // Ovde koristi servis

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
		float minStorage = 0.5f;
		float minVram = 3;
		
		return isMinimumSatisfied(cores, gpuSpeed, psu, ram, storage, vram, minCores, minGpuSpeed, minPsu, minRam, minStorage, minVram);
	}
	
	private boolean IsMinimumMining(int cores, float gpuSpeed, int psu, float ram, float storage, float vram) {
		int minCores = 1;
		float minGpuSpeed = 10f;
		int minPsu = 650;
		float minRam = 5;
		float minStorage = 0.5f;
		float minVram = 5;
		
		return isMinimumSatisfied(cores, gpuSpeed, psu, ram, storage, vram, minCores, minGpuSpeed, minPsu, minRam, minStorage, minVram);
	}
	
	private boolean IsMinimumHosting(int cores, float gpuSpeed, int psu, float ram, float storage, float vram) {
		int minCores = 8;
		float minGpuSpeed = 0;
		int minPsu = 650;
		float minRam = 16;
		float minStorage = 0;
		float minVram = 0;
		
		return isMinimumSatisfied(cores, gpuSpeed, psu, ram, storage, vram, minCores, minGpuSpeed, minPsu, minRam, minStorage, minVram);
	}
	
	public UseSuitability getSuitabilityScores(SpecificationDTO specificationDTO) {
		cpuDTO cpu = queryService.getCpuDetails(specificationDTO.getCpu());
		System.out.println(cpu.getCpuMemorySpeed());
		System.out.println(cpu.getName());
		System.out.println(cpu.getSocket());
		System.out.println(cpu.getTurboBoost());
		System.out.println(cpu.getPhysicalCores());
		
		System.out.println("------");
		gpuDTO gpu = queryService.getGpuDetails(specificationDTO.getGpu());
		System.out.println(gpu.getName());
		System.out.println(gpu.getGpuSpeed());
		System.out.println(gpu.getGpuMemory());
		System.out.println(gpu.getGpuTeraflops());
		
		System.out.println("------");
		hddDTO hdd = queryService.getHddDetails(specificationDTO.getHdd());
		System.out.println(hdd.getName());
		System.out.println(hdd.getMemoryCapacity());
		
		System.out.println("------");
		ssdDTO ssd = queryService.getSsdDetails(specificationDTO.getSsd());
		System.out.println(ssd.getName());
		System.out.println(ssd.getMemoryCapacity());
		
		System.out.println("------");
		PsuDTO psu = queryService.getPsuDetails(specificationDTO.getPsu());
		System.out.println(psu.getName());
		System.out.println(psu.getPower());
		System.out.println(psu.getCertificate());
		
		System.out.println("------");
		ramDTO ram = queryService.getRamDetails(specificationDTO.getRam());
		System.out.println(ram.getName());
		System.out.println(ram.getRamCapacity());
		System.out.println(ram.getRamFrequency());
		System.out.println(specificationDTO.getRamCount());
		
		float hddStorage = 0, ssdStorage = 0;
		if (hdd.getMemoryCapacity() != null)
			hddStorage = Float.parseFloat(hdd.getMemoryCapacity());
		if (ssd.getMemoryCapacity() != null)
			ssdStorage = Float.parseFloat(ssd.getMemoryCapacity());
		
		int coreNumber = cpu.getPhysicalCores();
		float gpuSpeed = gpu.getGpuTeraflops();
		int psuWatts = psu.getPower();
		float ramCapacity = ram.getRamCapacity() * specificationDTO.getRamCount();
		float storage = hddStorage + ssdStorage; //Treba i SSD
		float vramCapacity = gpu.getGpuMemory();
		
		System.out.println("Cores: " + coreNumber);
		System.out.println("TFLOPS: " + gpuSpeed);
		System.out.println("Power: " + psuWatts);
		System.out.println("RAM: " + ramCapacity);
		System.out.println("Storage: " + storage);
		System.out.println("VRAM: " + vramCapacity);
		
		float weightingFactor = 0.2f;
		
		UseSuitability suitabilityScores = FuzzyUseSuitabilityService.getFuzzyUseSuitability(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity);
		
		if (!IsMinimumCasual(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity)) {
			suitabilityScores.casualScore *= weightingFactor;
			System.out.println("C");
		}
		if (!IsMinimumGaming(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity)) {
			suitabilityScores.gamingScore *= weightingFactor;
			System.out.println("G");
		}
		if (!IsMinimumMining(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity)) {
			suitabilityScores.miningScore *= weightingFactor;
			System.out.println("M");
		}
		if (!IsMinimumHosting(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity)) {
			suitabilityScores.hostingScore *= weightingFactor;
			System.out.println("H");
		}
		
		return suitabilityScores;
	}
	
	public void testSuitability() {
		/*
		int coreNumber = 8;
		float gpuSpeed = 15;
		int psuWatts = 600;
		float ramCapacity = 8;
		float storage = 1.5f;
		float vramCapacity = 10;
		
		UseSuitability suitability = FuzzyUseSuitabilityService.getFuzzyUseSuitability(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity);
		
		if (!IsMinimumCasual(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity)) {
			suitability.casualScore = 0;
			System.out.println("C");
		}
		if (!IsMinimumGaming(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity)) {
			suitability.gamingScore = 0;
			System.out.println("G");
		}
		if (!IsMinimumMining(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity)) {
			suitability.miningScore = 0;
			System.out.println("M");
		}
		if (!IsMinimumHosting(coreNumber, gpuSpeed, psuWatts, ramCapacity, storage, vramCapacity)) {
			suitability.hostingScore = 0;
			System.out.println("H");
		}
		
		suitability.print();
		*/
	}
}
