package com.example.demo;

import org.semanticweb.owlapi.model.OWLOntology;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.example.demo.services.OntologyServices;

@SpringBootApplication
public class InzenjeringBackApplication implements CommandLineRunner {
	@Autowired
	public OntologyServices service;

	public static void main(String[] args) {
		SpringApplication.run(InzenjeringBackApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		System.out.println("********************************************************************************");
		System.out.println("********************************************************************************");
		System.out.println("********************************************************************************");
		OWLOntology ontology=service.creatOntology();
		String query = getQuery();
		System.out.println(query);
		System.out.println("********************************************************************************");
		service.executeTestQuery(query);

	}

	private String getQuery() {
		return "prefix : <http://www.semanticweb.org/darko/ontologies/2022/6/untitled-ontology-7#>\n" +
				"prefix owl: <http://www.w3.org/2002/07/owl#>\n" +
				"prefix rdf: <http://www.w3.org/1999/02/22-rdf-syntax-ns#>\n" +
				"prefix xml: <http://www.w3.org/XML/1998/namespace>\n" +
				"prefix xsd: <http://www.w3.org/2001/XMLSchema#>\n" +
				"prefix rdfs: <http://www.w3.org/2000/01/rdf-schema#>\n" +
				"SELECT ?x ?y ?z \n" +
				"WHERE {\n" +
				" ?x ?y ?z\n" +
				"}\n";
	}

}
