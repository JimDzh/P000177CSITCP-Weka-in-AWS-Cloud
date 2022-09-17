package com.aws.clusterMicroservice;

import weka.clusterers.ClusterEvaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.clusterers.EM;


public class EMCluster {
    public static void main(String[] args) throws Exception{


        //load data
        Instances train = DataSource.read("./Datasets/segment-challenge.arff");
        Instances test = DataSource.read("./Datasets/segment-test.arff");


        String[] options = new String[2];
        options[0] = "-I";                   // max. iterations
        options[1] = "100";
        EM clusterer = new EM();             // new instance of clusterer
        clusterer.setOptions(options);       // set the options
        clusterer.buildClusterer(train);     // build the clusterer


        // Evaluation
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(clusterer);
        eval.evaluateClusterer(new Instances(train));
        System.out.println(eval.clusterResultsToString());


    }
}
