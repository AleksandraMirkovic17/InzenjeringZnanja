package com.example.demo.services;

import org.apache.jena.base.Sys;
import org.apache.jena.query.*;
import org.apache.jena.rdf.model.Model;
import org.apache.jena.rdf.model.ModelFactory;
import org.apache.jena.riot.Lang;
import org.apache.jena.riot.RDFDataMgr;
import org.semanticweb.owlapi.apibinding.OWLManager;
import org.semanticweb.owlapi.model.OWLOntology;
import org.semanticweb.owlapi.model.OWLOntologyManager;
import org.springframework.asm.TypeReference;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

@Service
public class OntologyServices {
    private Model model;

    public OntologyServices() {

        String filePath = "..\\ontology\\classesAndInstances.owl";
        this.model = ModelFactory.createDefaultModel();
        try {
            InputStream is = new FileInputStream(filePath);
            RDFDataMgr.read(this.model, is, Lang.TURTLE);
            is.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public void executeTestQuery(String query) {
        QueryExecution qe = QueryExecutionFactory.create(QueryFactory.create(query), model);
        ResultSet rs = qe.execSelect();
        while (rs.hasNext()) {
            QuerySolution qs = rs.nextSolution();
            System.out.println(qs.toString());
        }
    }

    public List<QuerySolution> executeQuery(String query) {
        List<QuerySolution> retVal = new ArrayList<>();
        QueryExecution qe = QueryExecutionFactory.create(QueryFactory.create(query), model);
        ResultSet rs = qe.execSelect();
        while (rs.hasNext()) {
            retVal.add(rs.nextSolution());
        }
        return retVal;
    }

    public String getQuery() {
        return "prefix : <http://www.semanticweb.org/darko/ontologies/2022/6/untitled-ontology-7#>\n" +
                "prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
                "prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
                "prefix xml: <http://www.w3.org/XML/1998/namespace>\n" +
                "prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
                "prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n";
    }
    public void test()
    {
        List<QuerySolution> querySolutions=executeQuery(getQuery());
        for(QuerySolution q : querySolutions)
        {
            System.out.println(q.toString());
        }
    }



}