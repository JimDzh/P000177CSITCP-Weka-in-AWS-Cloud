package com.aws.clusterMicroservice;

import weka.clusterers.*;
import weka.core.Instance;
import weka.core.Instances;
import weka.core.converters.ArffLoader;
import weka.core.converters.ConverterUtils.DataSource;

import java.io.File;
import java.sql.SQLOutput;

public class Cluster {
    public static void main(String[] args) throws Exception {
//         //EM
//        Cluster.EMCluster("./Datasets/segment-challenge.arff", "./Datasets/segment-test.arff");
//        //SimpleKmeansCluster
//        Cluster.SimpleKmeansCluster("./Datasets/segment-challenge.arff", "./Datasets/segment-test.arff");
//        //Cobweb
//        cobweb("./Datasets/segment-challenge.arff", "./Datasets/segment-test.arff");
        //Canopy
        canopy("./Datasets/segment-challenge.arff", "./Datasets/segment-test.arff");
    }

    public static void SimpleKmeansCluster(String dataset, String dataset1) throws Exception {
        //load dataset

        //String dataset = "./Datasets/segment-challenge.arff";

        DataSource source = new DataSource(dataset);
        //get instances object
        Instances data = source.getDataSet();
        //new instances of cluster
        SimpleKMeans model = new SimpleKMeans();
        //number of clusters
        model.setNumClusters(4);
        //set distance function
        model.setDistanceFunction(new weka.core.EuclideanDistance());
        //set seed
        model.setSeed(10);
        //build the clusterer
        model.buildClusterer(data);
//        System.out.println(model);

        //Evaluation
        ClusterEvaluation eval = new ClusterEvaluation();
        //load dataset

        //String dataset1 = "./Datasets/segment-test.arff";

        DataSource source1 = new DataSource(dataset);
        //get instances object
        Instances data1 = source.getDataSet();

        eval.setClusterer(model);
        eval.evaluateClusterer(data1); //this should be test dataset

        System.out.println("# of clusters " + eval.getNumClusters());

        System.out.println(eval.clusterResultsToString());
    }

    public static void EMCluster(String filePath1, String filePath2) throws Exception {
        //load data


        //Instances train = DataSource.read("./Datasets/segment-challenge.arff");
        //Instances test = DataSource.read("./Datasets/segment-test.arff");

        Instances train = DataSource.read(filePath1);
        Instances test = DataSource.read(filePath2);


        String[] options = new String[2];
        options[0] = "-I";                   // max. iterations
        options[1] = "100";
//        options[0] = "-S";                    // Set seed
//        options[1] = "10";
        EM clusterer = new EM();             // new instance of clusterer
        clusterer.setOptions(options);       // set the options
        clusterer.buildClusterer(train);     // build the clusterer


        // Evaluation
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(clusterer);
        eval.evaluateClusterer(new Instances(train));
        System.out.println(eval.clusterResultsToString());
    }



    public static void cobweb(String dataset, String filePath1) throws Exception {
        //load dataset
        DataSource source = new DataSource(dataset);
        //get instances object
        Instances data = source.getDataSet();

        Cobweb cw = new Cobweb();
        cw.setSeed(20);
        cw.buildClusterer(data);

        ClusterEvaluation eval = new ClusterEvaluation();

        DataSource source1 = new DataSource(dataset);

        Instances data1 = source.getDataSet();

        eval.setClusterer(cw);
        eval.evaluateClusterer(data1);

        System.out.println("user supplied test set");
        System.out.println(eval.clusterResultsToString());

    }

    public static void canopy(String dataset, String filePath1) throws Exception {
        //load dataset
        DataSource source = new DataSource(dataset);
        //get instances object
        Instances data = source.getDataSet();

        Canopy canopy = new Canopy();
        canopy.setSeed(1);
        canopy.buildClusterer(data);

        ClusterEvaluation eval = new ClusterEvaluation();

        DataSource source1 = new DataSource(dataset);

        Instances data1 = source.getDataSet();

        eval.setClusterer(canopy);
        eval.evaluateClusterer(data1);

        System.out.println("user supplied test set");
        System.out.println(eval.clusterResultsToString());

    }


}



