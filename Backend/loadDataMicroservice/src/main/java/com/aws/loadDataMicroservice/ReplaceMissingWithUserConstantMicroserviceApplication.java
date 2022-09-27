package com.aws.loadDataMicroservice;

import java.io.File;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.core.stopwords.Null;
import weka.filters.Filter;
import weka.filters.unsupervised.instance.NonSparseToSparse;
import weka.filters.unsupervised.instance.SparseToNonSparse;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.ReplaceMissingWithUserConstant;

public class ReplaceMissingWithUserConstantMicroserviceApplication {
    public static void main(String args[]) throws Exception{

        //load data
        DataSource source = new DataSource("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testHeartJim.arff");
        Instances dataset = source.getDataSet();
        //create NonSparseToSparse object to save in sparse ARFF format
        ReplaceMissingWithUserConstant sp = new ReplaceMissingWithUserConstant();

        //replace missing value with constant
        sp.setNominalStringReplacementValue("null");

        //specify the dataset
        sp.setInputFormat(dataset);
        //apply

        Instances newData = Filter.useFilter(dataset, sp);
        //save
        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);
        saver.setFile(new File("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff_constant.arff"));
        saver.writeBatch();
    }
}
