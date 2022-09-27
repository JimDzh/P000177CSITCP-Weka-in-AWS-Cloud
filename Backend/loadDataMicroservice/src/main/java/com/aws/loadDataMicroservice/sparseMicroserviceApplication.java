package com.aws.loadDataMicroservice;

import java.io.File;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.NonSparseToSparse;
import weka.filters.unsupervised.instance.SparseToNonSparse;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.ReplaceMissingWithUserConstant;
import weka.filters.unsupervised.attribute.ReplaceWithMissingValue;

public class sparseMicroserviceApplication {

    public static void main(String args[]) throws Exception{

        //load data
        DataSource source = new DataSource("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff.arff");
        Instances dataset = source.getDataSet();
        //create NonSparseToSparse object to save in sparse ARFF format
        ReplaceMissingValues sp = new ReplaceMissingValues();

        //specify the dataset
        sp.setInputFormat(dataset);
        //apply
        sp.setIgnoreClass(true);
        Instances newData = Filter.useFilter(dataset, sp);
        //save
        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);
        saver.setFile(new File("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff_sparse.arff"));
        saver.writeBatch();
    }
}
