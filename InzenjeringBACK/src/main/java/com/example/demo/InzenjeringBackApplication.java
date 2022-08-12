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



	}



}
