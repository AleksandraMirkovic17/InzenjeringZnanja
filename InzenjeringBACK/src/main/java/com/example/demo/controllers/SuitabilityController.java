package com.example.demo.controllers;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dtos.SpecificationDTO;
import com.example.demo.model.UseSuitability;
import com.example.demo.services.QueryService;
import com.example.demo.services.UseSuitabilityService;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("suitability")
public class SuitabilityController {
    private final UseSuitabilityService suitabilityService;
    
    public SuitabilityController(UseSuitabilityService suitabilityService) {
        this.suitabilityService = suitabilityService;
    }

	
    @PostMapping("")
    public ResponseEntity<UseSuitability> getSuitabilityScores(@RequestBody SpecificationDTO specificationDTO) {
        return new ResponseEntity<>(suitabilityService.getSuitabilityScores(specificationDTO), HttpStatus.OK);
    }
	
}
