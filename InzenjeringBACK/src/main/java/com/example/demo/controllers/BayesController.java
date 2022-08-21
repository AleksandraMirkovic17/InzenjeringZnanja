package com.example.demo.controllers;


import com.example.demo.dtos.ProbabilityDTO;
import com.example.demo.services.BayesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("bayes")
public class BayesController {
    private BayesService bayesService;

    @Autowired
    public BayesController(BayesService bayesService) {
        this.bayesService = bayesService;
    }

    @GetMapping("/{node}")
    public ResponseEntity<List<ProbabilityDTO>> getProbability(@PathVariable String node) throws Exception {
        return new ResponseEntity<>( this.bayesService.getAllProbabilities(node), HttpStatus.OK);
    }
}
