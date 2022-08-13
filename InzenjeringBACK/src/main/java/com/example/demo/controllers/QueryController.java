package com.example.demo.controllers;


import com.example.demo.dtos.cpuDTO;
import com.example.demo.dtos.ramDTO;
import com.example.demo.services.QueryService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
@RequestMapping("query")
public class QueryController {
    private final QueryService queryService;
    public QueryController(QueryService queryService) {
        this.queryService = queryService;
    }

    @GetMapping("/getAllMotherboards")
    public ResponseEntity<List<String>> getAllMotherboards() {
        return new ResponseEntity<>(queryService.getAllMotherboards(), HttpStatus.OK);
    }

    @GetMapping("/RAM/{selectedMotherboard}")
    @ResponseBody
    public ResponseEntity<List<ramDTO>> getAllCompatibileRams(@PathVariable String selectedMotherboard) {
        return new ResponseEntity<>(queryService.upgradeRAM(selectedMotherboard), HttpStatus.OK);
    }

    @GetMapping("/CPU/{selectedMotherboard}")
    @ResponseBody
    public ResponseEntity<List<cpuDTO>> getAllCompatibileCpus(@PathVariable String selectedMotherboard) {
        return new ResponseEntity<>(queryService.upgradeCPU(selectedMotherboard), HttpStatus.OK);
    }
}
