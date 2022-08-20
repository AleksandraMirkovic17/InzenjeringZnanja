package com.example.demo.controllers;


import com.example.demo.dtos.*;
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

    @GetMapping("/getAllCPU")
    public ResponseEntity<List<String>> getAllCPU() {
        return new ResponseEntity<>(queryService.getAllCPU(), HttpStatus.OK);
    }

    @GetMapping("/getAllRAM")
    public ResponseEntity<List<String>> getAllRAM() {
        return new ResponseEntity<>(queryService.getAllRAM(), HttpStatus.OK);
    }

    @GetMapping("/getAllGPU")
    public ResponseEntity<List<String>> getAllGPU() {
        return new ResponseEntity<>(queryService.getAllGPU(), HttpStatus.OK);
    }

    @GetMapping("/getAllHDD")
    public ResponseEntity<List<String>> getAllHDD() {
        return new ResponseEntity<>(queryService.getAllHDD(), HttpStatus.OK);
    }

    @GetMapping("/getAllSSD")
    public ResponseEntity<List<String>> getAllSSD() {
        return new ResponseEntity<>(queryService.getAllSSD(), HttpStatus.OK);
    }

    @GetMapping("/RAM/{selectedMotherboard}")
    @ResponseBody
    public ResponseEntity<List<ramDTO>> getAllCompatibileRams(@PathVariable String selectedMotherboard) {
        return new ResponseEntity<>(queryService.upgradeRAM(selectedMotherboard), HttpStatus.OK);
    }

    @GetMapping("/CPU/{selectedMotherboard}/{selectedCPU}")
    @ResponseBody
    public ResponseEntity<List<cpuDTO>> getAllCompatibileCpus(@PathVariable String selectedMotherboard, @PathVariable String selectedCPU) {
        System.out.println(selectedCPU);
        System.out.println("***********************************************************");

        System.out.println(selectedMotherboard);
        return new ResponseEntity<>(queryService.upgradeCPU(selectedMotherboard, selectedCPU), HttpStatus.OK);
    }

    @GetMapping("/GPU/{selectedGpu}")
    @ResponseBody
    public ResponseEntity<List<gpuDTO>> getAllCompatibileGpu(@PathVariable String selectedGpu) {
        return new ResponseEntity<>(queryService.upgradeGPU(selectedGpu), HttpStatus.OK);
    }
    @GetMapping("/HDD/{selectedHdd}")
    @ResponseBody
    public ResponseEntity<List<hddDTO>> getAllCompatibileHdd(@PathVariable String selectedHdd) {
        return new ResponseEntity<>(queryService.upgradeHDD(selectedHdd), HttpStatus.OK);
    }

    @GetMapping("/SSD/{selectedSsd}")
    @ResponseBody
    public ResponseEntity<List<ssdDTO>> getAllCompatibileSsd(@PathVariable String selectedSsd) {
        return new ResponseEntity<>(queryService.upgradeSSD(selectedSsd), HttpStatus.OK);
    }

}
