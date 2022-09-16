package com.aws.clusterMicroservice;

import weka.clusterers.ClusterEvaluation;
import weka.core.Instances;
import weka.core.converters.ConverterUtils.DataSource;
import weka.clusterers.EM;
import weka.core.Utils;



public class clusterMicroserviceApplication {
    public static void main(String[] args) throws Exception{


        //load data
        Instances train = DataSource.read("./Datasets/resultArff.arff");
        Instances test = DataSource.read("./Datasets/resultArff.arff");


        String[] options = new String[2];
        options[0] = "-I";                   // 最大迭代次数max. iterations
        options[1] = "100";
        EM clusterer = new EM();             // 聚类器的新实例new instance of clusterer
        clusterer.setOptions(options);       // 设置选项set the options
        clusterer.buildClusterer(train);     // 构建聚类器build the clusterer


        // 评估
        ClusterEvaluation eval = new ClusterEvaluation();
        eval.setClusterer(clusterer);
        eval.evaluateClusterer(new Instances(train));
        System.out.println(eval.clusterResultsToString());

        // 执行聚类
        for (int i = 0; i < test.numInstances(); i++) {
            int cluster = clusterer.clusterInstance(test.instance(i));
            double[] dist = clusterer.distributionForInstance(test.instance(i));
            System.out.print((i + 1));
            System.out.print(" - ");
            System.out.print(cluster);
            System.out.print(" - ");
            System.out.print(Utils.arrayToString(dist));
            System.out.println();
        }
    }
}
