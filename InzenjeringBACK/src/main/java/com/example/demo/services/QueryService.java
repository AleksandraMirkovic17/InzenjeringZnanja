package com.example.demo.services;

import com.example.demo.dtos.*;

import org.apache.jena.base.Sys;
import org.apache.jena.query.QuerySolution;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class QueryService {
    private final OntologyServices service;

    @Autowired
    public QueryService(OntologyServices service) {
        this.service = service;
    }

    public List<String> getAllMotherboards() {
        List<QuerySolution> results = service.executeQuery(queryGetAllMotherboards());
        List<String> allMotherboards = addInListMotherboards(results);
        return allMotherboards;
    }

    public List<String> getAllCPU() {
        List<QuerySolution> results = service.executeQuery(queryGetAllCPU());

        List<String> allCPU = new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            if(!name.equals(""))
                allCPU.add(name);
        }
        return allCPU;
    }

    public List<String> getAllRAM() {
        List<QuerySolution> results = service.executeQuery(queryGetAllRAM());

        List<String> allRAM = new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            if(!name.equals(""))
                allRAM.add(name);
        }
        return allRAM;
    }

    public List<String> getAllGPU() {
        List<QuerySolution> results = service.executeQuery(queryGetAllGPU());

        List<String> allGPU = new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            if(!name.equals(""))
                allGPU.add(name);
        }
        return allGPU;
    }


    public List<String> getAllHDD() {
        List<QuerySolution> results = service.executeQuery(queryGetAllHDD());

        List<String> allHDD = new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            if(!name.equals(""))
                allHDD.add(name);
        }
        return allHDD;
    }

    public List<String> getAllSSD() {
        List<QuerySolution> results = service.executeQuery(queryGetAllSSD());

        List<String> allSSD = new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            if(!name.equals(""))
                allSSD.add(name);
        }
        return allSSD;
    }
    
    public List<String> getAllPSU() {
        List<QuerySolution> results = service.executeQuery(queryGetAllPSU()); //TODO: Refaktorisati tako da je getAll samo jedna funkcija

        List<String> allPSU = new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            if(!name.equals(""))
                allPSU.add(name);
        }
        return allPSU;
    }

    public List<String> addInListMotherboards(List<QuerySolution> results)
    {
        List<String> allMotherboards = new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            if(!name.equals(""))
                allMotherboards.add(name);
        }
        return allMotherboards;
    }
    public List<ramDTO> upgradeRAM(String selectedMotherboard) {
        List<QuerySolution> results = service.executeQuery(queryGetAllCompatibleRam(selectedMotherboard));
        System.out.println("***********************************");

        return addInListRam(results);
    }

    private List<ramDTO> addInListRam(List<QuerySolution> results) {
        List<ramDTO> rams = new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            int ramCapacity = qs.get("ramCapacity").asLiteral().getInt();
            int ramFrequency = qs.get("ramFrequency").asLiteral().getInt();
            if(!name.equals(""))
                rams.add(new ramDTO(name, ramCapacity, ramFrequency));
        }
        System.out.println(rams);
        return rams;
    }


    public List<gpuDTO> upgradeGPU(String selectedGPU)
    {
        List<QuerySolution> results = service.executeQuery(queryGetAllInformationGPU());
        List<QuerySolution> gpu = service.executeQuery(queryGetSelectedGpu(selectedGPU));
        int teraflops=0;
        for (QuerySolution qs : gpu){
            teraflops=qs.get("gpuTeraflops").asLiteral().getInt();
        }
        List<gpuDTO> gpus= new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            String gpuSpeed = qs.get("gpuSpeed").asLiteral().getValue().toString();
            int gpuMemory = qs.get("gpuMemory").asLiteral().getInt();
            int gpuTeraflops = qs.get("gpuTeraflops").asLiteral().getInt();
            if(!name.equals("") && gpuTeraflops >=teraflops)
                gpus.add(new gpuDTO(name, gpuSpeed, gpuMemory,gpuTeraflops));
        }

        return gpus;
    }

    public List<hddDTO> upgradeHDD(String selectedHDD)
    {
        List<QuerySolution> results = service.executeQuery(queryGetAllInformationHDD());
        List<QuerySolution> hdd = service.executeQuery(queryGetSelectedHdd(selectedHDD));
        String memoryCapacity="";
        for (QuerySolution qs : hdd){
            memoryCapacity = qs.get("memoryCapacity").asLiteral().getValue().toString();
        }
        int memorycap=Integer.parseInt(memoryCapacity);
        List<hddDTO> hdds= new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            String mc = qs.get("memoryCapacity").asLiteral().getValue().toString();
            String memoryInterface = qs.get("memoryInterface").asLiteral().getValue().toString();

            int mc1= Integer.parseInt(mc);
            if(!name.equals("") && mc1 >=memorycap)
                hdds.add(new hddDTO(name, mc, memoryInterface));
        }

        return hdds;
    }

    public List<ssdDTO> upgradeSSD(String selectedSSD)
    {
        List<QuerySolution> results = service.executeQuery(queryGetAllInformationSSD());
        List<QuerySolution> ssd = service.executeQuery(queryGetSelectedSSD(selectedSSD));
        String memoryCapacity="";
        for (QuerySolution qs : ssd){
            memoryCapacity = qs.get("memoryCapacity").asLiteral().getValue().toString();
        }
        int memorycap=Integer.parseInt(memoryCapacity);
        List<ssdDTO> ssds= new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            String mc = qs.get("memoryCapacity").asLiteral().getValue().toString();
            String memoryInterface = qs.get("memoryInterface").asLiteral().getValue().toString();

            int mc1= Integer.parseInt(mc);
            if(!name.equals("") && mc1 >=memorycap)
                ssds.add(new ssdDTO(name, mc, memoryInterface));
        }

        return ssds;
    }

    public List<cpuDTO> upgradeCPU(String selectedMotherboard, String selectedCPU) {
        List<QuerySolution> results = service.executeQuery(queryGetAllCompatibleCPU(selectedMotherboard));
       List<QuerySolution> cpu = service.executeQuery(queryGetSelectedCpu(selectedCPU));
        int turboBoostSelectedCPU=0;
        for (QuerySolution qs : cpu){
            turboBoostSelectedCPU=qs.get("turboBoost").asLiteral().getInt();
        }
        System.out.println("***********************************");
        List<cpuDTO> rams = new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            String socket = qs.get("socket").asLiteral().getValue().toString();
            int turboBoost = qs.get("turboBoost").asLiteral().getInt();
            int cpuMemorySpeed = qs.get("cpuMemorySpeed").asLiteral().getInt();
            if(!name.equals("") && turboBoost >=turboBoostSelectedCPU)
                rams.add(new cpuDTO(turboBoost, cpuMemorySpeed, socket,name));
        }
        System.out.println(rams);
        return rams;
    }
    
    public cpuDTO getCpuDetails(String selectedCPU) {
    	List<QuerySolution> cpu = service.executeQuery(queryGetCpuDetails(selectedCPU));
    	int turboBoost = 0;
    	int cpuMemorySpeed = 0;
    	int cores = 0;
    	int baseClock = 0;
    	String name = null;
    	String socket = null;
    	System.out.println("Selected: " + selectedCPU);
    	for (QuerySolution qs : cpu) {
    		turboBoost = qs.get("turboBoost").asLiteral().getInt();
    		baseClock = qs.get("baseClock").asLiteral().getInt();
    		cpuMemorySpeed = qs.get("cpuMemorySpeed").asLiteral().getInt();
    		name = selectedCPU;
    		socket = qs.get("socket").asLiteral().getValue().toString();
    		cores = qs.get("physicalCores").asLiteral().getInt();
    	}
    	cpuDTO details = new cpuDTO(turboBoost, cpuMemorySpeed, socket, name);
    	details.setPhysicalCores(cores);
    	details.setBaseClock(baseClock);
    	return details;
    }
    
    public gpuDTO getGpuDetails(String selectedGPU)
    {
        List<QuerySolution> gpu = service.executeQuery(queryGetGpuDetails(selectedGPU));
        int teraflops = 0;
        int memory = 0;
        String gpuSpeed = null;
        String name = null;
        for (QuerySolution qs : gpu){
            teraflops = qs.get("gpuTeraflops").asLiteral().getInt();
            memory = qs.get("gpuMemory").asLiteral().getInt();
            gpuSpeed = qs.get("gpuSpeed").asLiteral().getValue().toString();
            name = selectedGPU;
        }
        gpuDTO details = new gpuDTO(name, gpuSpeed, memory, teraflops);

        return details;
    }
    
    public hddDTO getHddDetails(String selectedHDD)
    {
        List<QuerySolution> hdd = service.executeQuery(queryGetHddDetails(selectedHDD));
        String memoryCapacity = null;
        String name = null;
        String memoryInterface = null;
        for (QuerySolution qs : hdd){
            memoryCapacity = qs.get("memoryCapacity").asLiteral().getValue().toString();
            name = selectedHDD;
            //memoryInterface = qs.get("memoryInterface").asLiteral().getValue().toString();
        }
        hddDTO details = new hddDTO(name, memoryCapacity, memoryInterface);

        return details;
    }
    
    public ssdDTO getSsdDetails(String selectedSSD)
    {
        List<QuerySolution> ssd = service.executeQuery(queryGetSsdDetails(selectedSSD));
        String memoryCapacity = null;
        String name = null;
        String memoryInterface = null;
        for (QuerySolution qs : ssd){
            memoryCapacity = qs.get("memoryCapacity").asLiteral().getValue().toString();
            name = selectedSSD;
            //memoryInterface = qs.get("memoryInterface").asLiteral().getValue().toString();
        }
        ssdDTO details = new ssdDTO(name, memoryCapacity, memoryInterface);

        return details;
    }
    
    public PsuDTO getPsuDetails(String selectedPSU)
    {
        List<QuerySolution> ssd = service.executeQuery(queryGetPsuDetails(selectedPSU));
        int power = 0;
        String certificate = null;
        String name = null;
        for (QuerySolution qs : ssd){
        	power = qs.get("psuPower").asLiteral().getInt();
            name = selectedPSU;
            certificate =  qs.get("psuCertificate").asLiteral().getValue().toString();
        }
        PsuDTO details = new PsuDTO(name, certificate, power);

        return details;
    }
    
    public ramDTO getRamDetails(String selectedRAM)
    {
        List<QuerySolution> ram = service.executeQuery(queryGetRamDetails(selectedRAM));
        int capacity = 0;
        int frequency = 0;
        String name = null;
        for (QuerySolution qs : ram){
        	capacity = qs.get("ramCapacity").asLiteral().getInt();
            name = selectedRAM;
            frequency = qs.get("ramFrequency").asLiteral().getInt();
        }
        ramDTO details = new ramDTO(name, capacity, frequency);

        return details;
    }
    

    private String queryGetSelectedCpu(String selectedCpu) {
        return service.getQuery() +
                "SELECT ?turboBoost \n" +
                "WHERE {\n" +
                ":"+selectedCpu+" :turboBoost ?turboBoost .\n" +
                "}\n";
    }
    private String queryGetSelectedGpu(String selectedGpu) {
        return service.getQuery() +
                "SELECT ?gpuTeraflops \n" +
                "WHERE {\n" +
                ":"+selectedGpu+" :gpuTeraflops ?gpuTeraflops .\n" +
                "}\n";
    }
    private String queryGetSelectedHdd(String selectedHdd) {
        return service.getQuery() +
                "SELECT ?memoryCapacity \n" +
                "WHERE {\n" +
                ":"+selectedHdd+" :memoryCapacity ?memoryCapacity .\n" +
                "}\n";
    }

    private String queryGetSelectedSSD(String selectedSsd) {
        return service.getQuery() +
                "SELECT ?memoryCapacity \n" +
                "WHERE {\n" +
                ":"+selectedSsd+" :memoryCapacity ?memoryCapacity .\n" +
                "}\n";
    }
    private String queryGetAllMotherboards() {
        return service.getQuery() +
                "SELECT ?name \n" +
                "WHERE {\n" +
                "?name rdf:type :Motherboard .\n" +
                "}\n";
    }
    private String queryGetAllCPU() {
        return service.getQuery() +
                "SELECT ?name \n" +
                "WHERE {\n" +
                "?name rdf:type :CPU .\n" +
                "}\n";
    }
    private String queryGetAllInformationGPU() {
        return service.getQuery() +
                "SELECT ?name ?gpuSpeed ?gpuMemory ?gpuTeraflops\n" +
                "WHERE {\n" +
                "?name rdf:type :GPU .\n" +
                "?name :gpuSpeed ?gpuSpeed .\n"+
                "?name :gpuMemory ?gpuMemory .\n"+
                "?name :gpuTeraflops ?gpuTeraflops .\n"+
                "}\n";
    }
    private String queryGetAllGPU() {
        return service.getQuery() +
                "SELECT ?name \n" +
                "WHERE {\n" +
                "?name rdf:type :GPU .\n" +
                "}\n";
    }
    private String queryGetAllRAM() {
        return service.getQuery() +
                "SELECT ?name \n" +
                "WHERE {\n" +
                "?name rdf:type :RAM .\n" +
                "}\n";
    }

    private String queryGetAllSSD() {
        return service.getQuery() +
                "SELECT ?name \n" +
                "WHERE {\n" +
                "?name rdf:type :SSD .\n" +
                "}\n";
    }

    private String queryGetAllHDD() {
        return service.getQuery() +
                "SELECT ?name \n" +
                "WHERE {\n" +
                "?name rdf:type :HDD .\n" +
                "}\n";
    }
    
    private String queryGetAllPSU() {
        return service.getQuery() +
                "SELECT ?name \n" +
                "WHERE {\n" +
                "?name rdf:type :PSU .\n" +
                "}\n";
    }

    private String queryGetAllInformationHDD() {
        return service.getQuery() +
                "SELECT ?name ?memoryCapacity ?memoryInterface\n" +
                "WHERE {\n" +
                "?name rdf:type :HDD .\n" +
                "?name :memoryCapacity ?memoryCapacity .\n" +
                "?name :memoryInterface ?memoryInterface .\n"+
                "}\n";
    }

    private String queryGetAllInformationSSD() {
        return service.getQuery() +
                "SELECT ?name ?memoryCapacity ?memoryInterface\n" +
                "WHERE {\n" +
                "?name rdf:type :SSD .\n" +
                "?name :memoryCapacity ?memoryCapacity .\n" +
                "?name :memoryInterface ?memoryInterface .\n"+
                "}\n";
    }

    private String queryGetAllCompatibleRam(String nameMotherboard)
    {
        return service.getQuery() +
                "SELECT ?name ?ramCapacity ?ramFrequency\n" +
                "WHERE {\n" +
                "?name rdf:type :RAM .\n" +
                "?name :compatibleRAM "+nameMotherboard+" .\n" +
                "?name :ramCapacity ?ramCapacity .\n"+
                "?name :ramFrequency ?ramFrequency .\n"+
                "}\n";
    }

    private String queryGetAllCompatibleCPU(String nameMotherboard)
    {
        return service.getQuery() +
                "SELECT ?name ?turboBoost ?cpuMemorySpeed ?socket\n" +
                "WHERE {\n" +
                ":"+nameMotherboard+" :motherboardSocket ?socket .\n" +
                "?name rdf:type :CPU .\n" +
                "?name :socket ?socket.\n" +
                "?name :baseClock ?baseClock .\n"+
                "?name :cpuMemorySpeed ?cpuMemorySpeed .\n"+
                "?name :turboBoost ?turboBoost .\n"+
                "}\n";
    }
    
    private String queryGetCpuDetails(String selectedCpu) {
        return service.getQuery() +
                "SELECT ?socket ?baseClock ?turboBoost ?cpuMemorySpeed ?socket ?physicalCores\n" +
                "WHERE {\n" +
                ":"+selectedCpu+" :socket ?socket.\n" +
                ":"+selectedCpu+" :baseClock ?baseClock .\n"+
                ":"+selectedCpu+" :turboBoost ?turboBoost .\n"+
                ":"+selectedCpu+" :socket ?socket .\n"+
                ":"+selectedCpu+" :physicalCores ?physicalCores .\n"+
                ":"+selectedCpu+" :cpuMemorySpeed ?cpuMemorySpeed .\n"+
                "}\n";
    }
    
    private String queryGetGpuDetails(String selectedGpu) {
        return service.getQuery() +
                "SELECT ?gpuTeraflops ?gpuMemory ?gpuSpeed\n" +
                "WHERE {\n" +
                ":"+selectedGpu+" :gpuTeraflops ?gpuTeraflops.\n" +
                ":"+selectedGpu+" :gpuSpeed ?gpuSpeed .\n"+
                ":"+selectedGpu+" :gpuMemory ?gpuMemory .\n"+
                "}\n";
    }
    
    private String queryGetHddDetails(String selectedHdd) {
        return service.getQuery() +
                "SELECT ?memoryCapacity\n" +
                "WHERE {\n" +
                ":"+selectedHdd+" :memoryCapacity ?memoryCapacity.\n" +
                "}\n";
    }
    
    private String queryGetSsdDetails(String selectedSsd) {
        return service.getQuery() +
                "SELECT ?memoryCapacity\n" +
                "WHERE {\n" +
                ":"+selectedSsd+" :memoryCapacity ?memoryCapacity.\n" +
                "}\n";
    }
    
    private String queryGetPsuDetails(String selectedPsu) {
        return service.getQuery() +
                "SELECT ?psuCertificate ?psuPower\n" +
                "WHERE {\n" +
                ":"+selectedPsu+" :psuCertificate ?psuCertificate.\n" +
                ":"+selectedPsu+" :psuPower ?psuPower.\n" +
                "}\n";
    }
    
    private String queryGetRamDetails(String selectedRam) {
        return service.getQuery() +
                "SELECT ?ramCapacity ?ramFrequency\n" +
                "WHERE {\n" +
                ":"+selectedRam+" :ramCapacity ?ramCapacity.\n" +
                ":"+selectedRam+" :ramFrequency ?ramFrequency.\n" +
                "}\n";
    }


}
