package com.aws.loadDataMicroservice;

import java.io.File;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.NonSparseToSparse;

public class sparseMicroserviceApplication {

    public static void main(String args[]) throws Exception{
        //load data
        DataSource source = new DataSource("/Users/chrissindarto/Documents/PPROJECT1/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff.arff");
        Instances dataset = source.getDataSet();
        //create NonSparseToSparse object to save in sparse ARFF format
        NonSparseToSparse sp = new NonSparseToSparse();

        //specify the dataset
        sp.setInputFormat(dataset);
        //apply
        Instances newData = Filter.useFilter(dataset, sp);
        //save
        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);
        saver.setFile(new File("/Users/chrissindarto/Documents/PPROJECT1/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff_sparse.arff"));
        saver.writeBatch();
    }
}
