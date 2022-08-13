package com.example.demo.services;

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
    public List<ramDTO> upgradeRAM(String mbName) {
        List<QuerySolution> results = service.executeQuery(queryGetAllCompatibleRam(mbName));
        System.out.println("***********************************");

        return processRAMResults(results);
    }

    private List<ramDTO> processRAMResults(List<QuerySolution> results) {
        List<ramDTO> retVal = new ArrayList<>();
        for (QuerySolution qs : results) {
            String name = qs.get("name").asResource().getLocalName();
            int ramCapacity = qs.get("ramCapacity").asLiteral().getInt();
            int ramFrequency = qs.get("ramFrequency").asLiteral().getInt();
            if(!name.equals(""))
                retVal.add(new ramDTO(name, ramCapacity, ramFrequency));
        }
        System.out.println(retVal);
        return retVal;
    }

    private String queryGetAllMotherboards() {
        return service.getQuery() +
                "SELECT ?name \n" +
                "WHERE {\n" +
                "?name rdf:type :Motherboard .\n" +
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




}
