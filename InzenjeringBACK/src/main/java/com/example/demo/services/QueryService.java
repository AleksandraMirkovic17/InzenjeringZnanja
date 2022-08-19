package com.example.demo.services;

import com.example.demo.dtos.cpuDTO;
import com.example.demo.dtos.gpuDTO;
import com.example.demo.dtos.ramDTO;
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
      return null;
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




}
