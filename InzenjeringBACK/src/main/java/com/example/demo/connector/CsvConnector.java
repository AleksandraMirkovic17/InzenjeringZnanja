package com.example.demo.connector;

import com.example.demo.dtos.csvDTO;

import com.example.demo.model.Csv;
import ucm.gaia.jcolibri.cbrcore.CBRCase;
import ucm.gaia.jcolibri.cbrcore.CaseBaseFilter;
import ucm.gaia.jcolibri.cbrcore.Connector;
import ucm.gaia.jcolibri.exception.InitializingException;
import ucm.gaia.jcolibri.util.FileIO;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.Collection;
import java.util.LinkedList;

public class CsvConnector implements Connector{

    @Override
    public Collection<CBRCase> retrieveAllCases() {
        LinkedList<CBRCase> cases = new LinkedList<CBRCase>();

        try {
            BufferedReader br = new BufferedReader(new InputStreamReader(FileIO.openFile("../Data/data.csv")));

            String line = "";
            while ((line = br.readLine()) != null) {
                if (line.startsWith("#") || (line.length() == 0))
                    continue;
                String[] values = line.split(";");

                CBRCase cbrCase = new CBRCase();

                Csv csvDTO = new Csv();

                // TODO
                csvDTO.setMotherboardDDR(Integer.parseInt(values[0]));
                csvDTO.setMotherboardSocket(values[1]);
                csvDTO.setMotherboardName(values[2]);
                csvDTO.setHddMemoryCapacity(Integer.parseInt(values[3]));
                csvDTO.setRamCapacity(Integer.parseInt(values[4]));
                csvDTO.setRamFrequency(Integer.parseInt(values[5]));
                csvDTO.setRamName(values[6]);
                csvDTO.setTurboBoost(Integer.parseInt(values[7]));
                csvDTO.setCpuMemorySpeed(Integer.parseInt(values[8]));
                csvDTO.setPhysicalCores(Integer.parseInt(values[9]));
                csvDTO.setCpuName(values[10]);
                csvDTO.setGpuTeraflops(Integer.parseInt(values[11]));
                csvDTO.setGpuMemory(Integer.parseInt(values[12]));

                cbrCase.setDescription(csvDTO);
                cases.add(cbrCase);
            }
            br.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return cases;
    }

    @Override
    public Collection<CBRCase> retrieveSomeCases(CaseBaseFilter arg0) {
        return null;
    }

    @Override
    public void storeCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void close() {
    }

    @Override
    public void deleteCases(Collection<CBRCase> arg0) {
    }

    @Override
    public void initFromXMLfile(URL arg0) throws InitializingException {
    }
}
