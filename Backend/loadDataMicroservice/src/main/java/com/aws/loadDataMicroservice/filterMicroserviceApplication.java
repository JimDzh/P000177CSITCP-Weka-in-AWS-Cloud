package com.aws.loadDataMicroservice;

import weka.core.Instances;
import weka.core.converters.ArffSaver;
import java.io.File;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;

public class filterMicroserviceApplication {
    public static void main(String[] args) throws Exception{

        //load dataset
        DataSource source = new DataSource("/Users/chrissindarto/Documents/PPROJECT1/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff.arff");
        Instances dataset = source.getDataSet();

        //use filter to remove a certain attribute
        //set up options to remove 2nd attribute
        String[] opts = new String[]{ "-R", "2"};
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
        saver.setFile(new File("/Users/chrissindarto/Documents/PPROJECT1/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff2.arff"));
        saver.writeBatch();
    }
}

