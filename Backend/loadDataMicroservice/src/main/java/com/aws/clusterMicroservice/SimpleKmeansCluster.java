package com.aws.clusterMicroservice;
import weka.clusterers.ClusterEvaluation;
import weka.clusterers.SimpleKMeans;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;



public class SimpleKmeansCluster {
    public static void main(String[] args) throws Exception {
        //load dataset
        String dataset = "./Datasets/segment-challenge.arff";
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
        model.setSeed(100);
        //build the clusterer
        model.buildClusterer(data);
//        System.out.println(model);

        //Evaluation
        ClusterEvaluation eval = new ClusterEvaluation();
        //load dataset
        String dataset1 = "./Datasets/segment-test.arff";
        DataSource source1 = new DataSource(dataset);
        //get instances object
        Instances data1 = source.getDataSet();

        eval.setClusterer(model);
        eval.evaluateClusterer(data1); //this should be test dataset

        System.out.println("# of clusters " + eval.getNumClusters());

        System.out.println(eval.clusterResultsToString());

    }
}
