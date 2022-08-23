package com.example.demo.controllers;

import com.example.demo.dtos.csvDTO;
import com.example.demo.services.CsvService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*", allowedHeaders = "*")
@RestController
public class CsvController {

    private final CsvService csvService;

    public CsvController(CsvService csvService) {
        this.csvService = csvService;
    }

    @GetMapping("/Similar/{motherboardDDR}/{motherboardSocket}/{hddMemoryCapacity}/{ramCapacity}/{turboBoost}/{cpuMemorySpeed}/{gpuTeraflops}/{ramFrequency}")
    @ResponseBody
    public ResponseEntity<List<csvDTO>> getSimilarity(@PathVariable int motherboardDDR, @PathVariable String motherboardSocket,
                                                      @PathVariable int hddMemoryCapacity,
                                                      @PathVariable int ramCapacity,@PathVariable int turboBoost,
                                                      @PathVariable int cpuMemorySpeed,@PathVariable int gpuTeraflops,
                                                      @PathVariable int ramFrequency) throws Exception {
        System.out.println("******************************STIGAO SAM******************************");
        System.out.println(motherboardDDR);
        System.out.println(motherboardSocket);
        System.out.println(hddMemoryCapacity);System.out.println(ramCapacity);
        System.out.println(turboBoost);
        System.out.println(cpuMemorySpeed);
        System.out.println(gpuTeraflops);System.out.println(motherboardDDR);






        return new ResponseEntity<>( this.csvService.main(motherboardDDR, motherboardSocket, hddMemoryCapacity, ramCapacity, turboBoost, cpuMemorySpeed, gpuTeraflops, ramFrequency), HttpStatus.OK);
    }


}
