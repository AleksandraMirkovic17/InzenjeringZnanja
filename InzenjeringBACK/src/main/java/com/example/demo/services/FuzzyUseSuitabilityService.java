package com.example.demo.services;

import com.example.demo.model.UseSuitability;

import net.sourceforge.jFuzzyLogic.FIS;
import net.sourceforge.jFuzzyLogic.FunctionBlock;
import net.sourceforge.jFuzzyLogic.Gpr;
import net.sourceforge.jFuzzyLogic.plot.JFuzzyChart;
import net.sourceforge.jFuzzyLogic.rule.Variable;


public class FuzzyUseSuitabilityService {

	public static UseSuitability getFuzzyUseSuitability(int cores, float gpuSpeed, int psu, float ram, float storage, float vram) {
		// Load from 'FCL' file
		String fileName = "src/main/resources/logika.fcl";
		FIS fis = FIS.load(fileName, true);
		if (fis == null) { // Error while loading?
			System.err.println("Can't load file: '" + fileName + "'");
			return null;
		}
		
		// Show ruleset
		FunctionBlock functionBlock = fis.getFunctionBlock(null);
		JFuzzyChart.get().chart(functionBlock);
		
		// Set inputs
		functionBlock.setVariable("core_num", cores);
		functionBlock.setVariable("gpu_speed", gpuSpeed);
		functionBlock.setVariable("psu_wattage", psu);
		functionBlock.setVariable("ram_capacity", ram);
		functionBlock.setVariable("storage", storage);
		functionBlock.setVariable("vram", vram);
		
		// Evaluate 
		functionBlock.evaluate();

		// Show output variable's chart
		Variable casual = functionBlock.getVariable("use_casual");
		Variable gaming = functionBlock.getVariable("use_gaming");
		Variable mining = functionBlock.getVariable("use_mining");
		Variable hosting = functionBlock.getVariable("use_hosting");
		JFuzzyChart.get().chart(casual, casual.getDefuzzifier(), true);
		//Gpr.debug("poor[service]: " + functionBlock.getVariable("service").getMembership("poor"));

		// Print ruleSet
		System.out.println(functionBlock);
		System.out.println("Casual:" + casual.getValue() / 100);
		System.out.println("Gaming:" + gaming.getValue() / 100);
		System.out.println("Mining:" + mining.getValue() / 100);
		System.out.println("Hosting:" + hosting.getValue() / 100);
		
		UseSuitability suitability = new UseSuitability();
		suitability.casualScore =  (float) (casual.getValue() / 100);
		suitability.gamingScore =  (float) (gaming.getValue() / 100);
		suitability.miningScore =  (float) (mining.getValue() / 100);
		suitability.hostingScore = (float) (hosting.getValue() / 100);
		
		return suitability;
	}
}