package com.example.demo.services;

import com.example.demo.dtos.ProbabilityDTO;
import org.springframework.stereotype.Service;
import unbbayes.io.BaseIO;
import unbbayes.io.NetIO;
import unbbayes.prs.Node;
import unbbayes.prs.bn.JunctionTreeAlgorithm;
import unbbayes.prs.bn.ProbabilisticNetwork;
import unbbayes.prs.bn.ProbabilisticNode;
import unbbayes.util.extension.bn.inference.IInferenceAlgorithm;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Service
public class BayesService {
    private ProbabilisticNetwork net;

    public BayesService() throws IOException {
        BaseIO io = new NetIO();
        net = (ProbabilisticNetwork) io.load(new File("..\\bayes\\bayes.net"));
    }

    private void compile() {
        IInferenceAlgorithm algorithm = new JunctionTreeAlgorithm();
        algorithm.setNetwork(net);
        algorithm.run();
    }
    private void addEvidence(String node) throws Exception {
        compile();
        ProbabilisticNode factNode = (ProbabilisticNode)net.getNode(node);
        int stateIndex = 0;
        factNode.addFinding(stateIndex);
        try {
            net.updateEvidences();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public List<ProbabilityDTO> getAllProbabilities(String node) throws Exception {
        addEvidence(node);
        List<ProbabilityDTO> ret = new ArrayList<>();
        for (Node parent : net.getNode(node).getParents())
            ret.add(makeProbabilityDTO(parent.getName(), ((ProbabilisticNode) parent).getMarginalAt(0)));
        return ret;
    }

    private ProbabilityDTO makeProbabilityDTO(String node, float probability) {
        return new ProbabilityDTO(node, Math.round(probability * 100));
    }
}
