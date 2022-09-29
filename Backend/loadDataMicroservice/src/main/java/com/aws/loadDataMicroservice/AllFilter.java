package com.aws.loadDataMicroservice;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.ReplaceMissingWithUserConstant;
import weka.filters.unsupervised.attribute.ReplaceWithMissingValue;

import java.io.File;

public class AllFilter {
    public static void main(String[] args) throws Exception{


        //delete data
        //load dataset
        ConverterUtils.DataSource source = new ConverterUtils.DataSource("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff.arff");
        Instances dataset = source.getDataSet();
        //use filter to remove a certain attribute
        //set up options to remove 2nd attribute
        String[] opts = new String[]{ "-R", "1"};
        //create a Remove object (this is the filter class)
        Remove remove = new Remove();
        //set the filter options
        remove.setOptions(opts);
        //pass the dataset to the filter
        remove.setInputFormat(dataset);
        //apply the filter
        Instances newData = Filter.useFilter(dataset, remove);
        //now save the dataset
        ArffSaver saver = new ArffSaver();
        saver.setInstances(newData);
        saver.setFile(new File("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff2.arff"));
        saver.writeBatch();


        //Replace Missing Value with User Constant numerical--0 nominal--null
//        //load data
//        ConverterUtils.DataSource source = new ConverterUtils.DataSource("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testHeartJim.arff");
//        Instances dataset = source.getDataSet();
//        //create NonSparseToSparse object to save in sparse ARFF format
//        ReplaceMissingWithUserConstant sp = new ReplaceMissingWithUserConstant();
//
//        //replace missing value with constant
//        sp.setNominalStringReplacementValue("null");
//
//        //specify the dataset
//        sp.setInputFormat(dataset);
//        //apply
//
//        Instances newData = Filter.useFilter(dataset, sp);
//        //save
//        ArffSaver saver = new ArffSaver();
//        saver.setInstances(newData);
//        saver.setFile(new File("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff_constant.arff"));
//        saver.writeBatch();

        //Replace Missing Value with mean
        //load data
//        ConverterUtils.DataSource source = new ConverterUtils.DataSource("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff.arff");
//        Instances dataset = source.getDataSet();
//        //create NonSparseToSparse object to save in sparse ARFF format
//        ReplaceMissingValues sp = new ReplaceMissingValues();
//
//        //specify the dataset
//        sp.setInputFormat(dataset);
//        //apply
//        sp.setIgnoreClass(true);
//        Instances newData = Filter.useFilter(dataset, sp);
//        //save
//        ArffSaver saver = new ArffSaver();
//        saver.setInstances(newData);
//        saver.setFile(new File("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff_sparse.arff"));
//        saver.writeBatch();

        //Replace with Missing Value
        //load data
//        ConverterUtils.DataSource source = new ConverterUtils.DataSource("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff.arff");
//        Instances dataset = source.getDataSet();
//
//        ReplaceWithMissingValue sp = new ReplaceWithMissingValue();
//        //specify the dataset
//        sp.setInputFormat(dataset);
//        //apply
//        Instances newData = Filter.useFilter(dataset, sp);
//        //save
//        ArffSaver saver = new ArffSaver();
//        saver.setInstances(newData);
//        saver.setFile(new File("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff_withmissingvalue.arff"));
//        saver.writeBatch();
    }
}
