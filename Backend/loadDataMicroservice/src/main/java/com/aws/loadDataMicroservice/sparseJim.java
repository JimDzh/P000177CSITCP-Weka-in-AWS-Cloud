package com.aws.loadDataMicroservice;

import java.io.File;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.instance.NonSparseToSparse;
import weka.classifiers.meta.FilteredClassifier;
import weka.classifiers.trees.J48;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import java.io.File;
import weka.core.converters.ConverterUtils.DataSource;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;



public class sparseJim {

    public static void main(String[] args) throws Exception{

        //load dataset
        DataSource source = new DataSource("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff.arff");
        Instances dataset = source.getDataSet();

        // set class index to the last attribute
        dataset.setClassIndex(dataset.numAttributes()-1);

        // the base classifier
        J48 tree = new J48();

        // the filter
        Remove remove = new Remove();

        //remove.setAttributeIndices("1");
        String[] opts = new String[]{"-R","1"};

        // set the filter options
        remove.setOptions(opts);

        //Create the FilteredClassifier object
        FilteredClassifier fc = new FilteredClassifier();

        //specify filter
        fc.setFilter(remove);

        //specify base classifier
        fc.setClassifier(tree);

        //build the meta-classifier
        fc.buildClassifier(dataset);

//        System.out.println(tree.graph());
//        //now save the dataset
//        ArffSaver saver = new ArffSaver();
//        saver.setInstances(dataset);
//        saver.setFile(new File("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff3.arff"));
//        saver.writeBatch();

    }

}
