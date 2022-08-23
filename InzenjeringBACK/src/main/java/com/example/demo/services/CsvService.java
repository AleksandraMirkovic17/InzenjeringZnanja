package com.example.demo.services;

import com.example.demo.connector.CsvConnector;
import com.example.demo.dtos.csvDTO;
import com.example.demo.model.Csv;
import org.springframework.stereotype.Service;
import ucm.gaia.jcolibri.casebase.LinealCaseBase;
import ucm.gaia.jcolibri.cbraplications.StandardCBRApplication;
import ucm.gaia.jcolibri.cbrcore.*;
import ucm.gaia.jcolibri.exception.ExecutionException;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNConfig;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.NNScoringMethod;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.global.Average;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Equal;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.EqualsStringIgnoreCase;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Interval;
import ucm.gaia.jcolibri.method.retrieve.NNretrieval.similarity.local.Threshold;
import ucm.gaia.jcolibri.method.retrieve.RetrievalResult;
import ucm.gaia.jcolibri.method.retrieve.selection.SelectCases;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Service
public class CsvService implements StandardCBRApplication {
    Connector _connector;  /** Connector object */
    CBRCaseBase _caseBase;  /** CaseBase object */
    NNConfig simConfig;  /** KNN configuration */

    public CsvService()
    {}

    public void configure() throws ExecutionException {
        _connector =  new CsvConnector();

        _caseBase = new LinealCaseBase();

        simConfig = new NNConfig();
        simConfig.setDescriptionSimFunction(new Average());

        // TODO
        simConfig.addMapping(new Attribute("motherboardDDR", csvDTO.class), new Equal());
        simConfig.addMapping(new Attribute("hddMemoryCapacity", csvDTO.class), new Interval(16));
        simConfig.addMapping(new Attribute("ramCapacity", csvDTO.class), new Interval(16));
        simConfig.addMapping(new Attribute("motherboardSocket", csvDTO.class), new EqualsStringIgnoreCase());
        simConfig.addMapping(new Attribute("gpuTeraflops", csvDTO.class), new Interval(15));
        simConfig.addMapping(new Attribute("ramFrequency", csvDTO.class), new Threshold(2500));
        simConfig.addMapping(new Attribute("turboBoost", csvDTO.class), new Threshold(3000));
        simConfig.addMapping(new Attribute("cpuMemorySpeed", csvDTO.class), new Threshold(2500));

        // Equal - returns 1 if both individuals are equal, otherwise returns 0 [cn, ramType, ]
        // Interval - returns the similarity of two number inside an interval: sim(x,y) = 1-(|x-y|/interval) [other]
        // Threshold - returns 1 if the difference between two numbers is less than a threshold, 0 in the other case [ramFreq]
        // EqualsStringIgnoreCase - returns 1 if both String are the same despite case letters, 0 in the other case
        // MaxString - returns a similarity value depending of the biggest substring that belong to both strings
        // EnumDistance - returns the similarity of two enum values as the their distance: sim(x,y) = |ord(x) - ord(y)|
        // EnumCyclicDistance - computes the similarity between two enum values as their cyclic distance
        // Table - uses a table to obtain the similarity between two values. Allowed values are Strings or Enums. The table is read from a text file.
        // TableSimilarity(List<String> values).setSimilarity(value1,value2,similarity)
    }

    public void cycle(CBRQuery query) throws ExecutionException {
        Collection<RetrievalResult> eval = NNScoringMethod.evaluateSimilarity(_caseBase.getCases(), query, simConfig);
        eval = SelectCases.selectTopKRR(eval, 5);

        List<String> cases = new ArrayList<>();
        for (RetrievalResult nse : eval)
            cases.add(nse.get_case().getDescription().toString());

        addToFile(cases);
    }
    private List<csvDTO> read() {
        List<csvDTO> des = new ArrayList<>();
        try {
            String path="..\\Data\\result.txt";
            File file = new File(path);
            BufferedReader br = new BufferedReader(new FileReader(file));
            String st;

            while ((st = br.readLine()) != null)
                des.add(processResult(st));
        } catch (Exception e) {
            System.out.println(e);
        }
        return des;
    }

    private csvDTO processResult(String result) {
        String[] parts = result.split(",");
        int motherboardDDR = Integer.parseInt(parts[0].split("=")[1]);
        int hddMemoryCapacity = Integer.parseInt(parts[1].split("=")[1]);
        int ramCapacity = Integer.parseInt(parts[2].split("=")[1]);
        String motherboardSocket = parts[3].split("=")[1];
        String motherboardName = parts[4].split("=")[1];
        String ramName = parts[7].split("=")[1];
        int physicalCores = Integer.parseInt(parts[9].split("=")[1]);
        int ramFrequency = Integer.parseInt(parts[5].split("=")[1]);
        String cpuName = parts[10].split("=")[1];
        int turboBoost = Integer.parseInt(parts[6].split("=")[1]);
        int cpuMemorySpeed = Integer.parseInt(parts[8].split("=")[1]);
        int gpuTeraflops= Integer.parseInt(parts[11].split("=")[1]);
        int gpuMemory= Integer.parseInt(parts[12].split("=")[1]);

        return new csvDTO(motherboardDDR, hddMemoryCapacity, ramCapacity, motherboardSocket, motherboardName, ramFrequency, turboBoost,
                ramName, cpuMemorySpeed, physicalCores, cpuName,gpuTeraflops,gpuMemory);
    }
    @Override
    public void postCycle() throws ExecutionException {

    }

    private void addToFile(List<String> cases) {
        try{
            String content = "";
            for (String c : cases)
                content += c + "\n";

            String path="..\\Data\\result.txt";
            File file = new File(path);

            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);

            bw.write(content);
            bw.close();
        }
        catch(Exception e){
            System.out.println(e);
        }
    }

    public CBRCaseBase preCycle() throws ExecutionException {
        _caseBase.init(_connector);
        java.util.Collection<CBRCase> cases = _caseBase.getCases();
        //for (CBRCase c: cases)
        //    System.out.println(c.getDescription());
        return _caseBase;
    }

    public List<csvDTO> main(int motherboardDDR, String motherboardSocket, int hddMemoryCapacity, int ramCapacity, int turboBoost, int cpuMemorySpeed, int gpuTeraflops,int ramFrequency )
    {
        StandardCBRApplication recommender = new CsvService();
        try {
            recommender.configure();

            recommender.preCycle();

            CBRQuery query = new CBRQuery();
            Csv csv = new Csv();

            // TODO
            csv.setMotherboardDDR(motherboardDDR);
            csv.setMotherboardSocket(motherboardSocket);
            csv.setHddMemoryCapacity(hddMemoryCapacity);
            csv.setRamCapacity(ramCapacity);
            csv.setRamFrequency(ramFrequency);
            csv.setTurboBoost(turboBoost);
            csv.setCpuMemorySpeed(cpuMemorySpeed);

            csv.setGpuTeraflops(gpuTeraflops);


            query.setDescription( csv );

            recommender.cycle(query);

            return  read();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return new ArrayList<>();
    }

}
