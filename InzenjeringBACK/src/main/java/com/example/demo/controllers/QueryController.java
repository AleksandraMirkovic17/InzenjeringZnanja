package com.example.demo.controllers;


import com.example.demo.services.QueryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@CrossOrigin(allowedHeaders = "*")
@RequestMapping(
        value = {"/query"}
)
public class QueryController {
    private final QueryService queryService;


    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }



    @GetMapping("/getAllMotherboards")
    public ResponseEntity<List<String>> getAllMotherboards() {
        return new ResponseEntity<>(queryService.getAllMotherboards(), HttpStatus.OK);
    }


}
