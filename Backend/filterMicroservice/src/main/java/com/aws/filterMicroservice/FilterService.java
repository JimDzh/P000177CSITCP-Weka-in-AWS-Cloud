package com.aws.filterMicroservice;

import org.springframework.stereotype.Service;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.ConverterUtils;
import weka.filters.Filter;
import weka.filters.unsupervised.attribute.Remove;
import weka.filters.unsupervised.attribute.ReplaceMissingValues;
import weka.filters.unsupervised.attribute.ReplaceMissingWithUserConstant;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@Service
public class FilterService {

    private String filePath;

    // Remove selected attribute based on user input
    public List<String> removeAttribute (String input, String filePath) {
        try {
            Instances dataset = loadDataSet(filePath);
            //delete an Attribute
            Attribute target = dataset.attribute(input);
            String target_index = String.valueOf(target.index());
            //use filter to remove a certain attribute
            //set up options to remove input attribute
            String[] opts = new String[]{"-R", target_index};
            //create a Remove object (this is the filter class)
            Remove remove = new Remove();
            //set the filter options
            remove.setOptions(opts);
            //pass the dataset to the filter
            remove.setInputFormat(dataset);
            //apply the filter
            Instances newData = Filter.useFilter(dataset, remove);
            saveData(newData, filePath);

            List<String> summary = createSummaryString(dataset);
            return summary;
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    // Replace missing value with constant
    public List<String> replaceMissingWithConstant (String input, String filePath) {

        try {
            //create NonSparseToSparse object to save in sparse ARFF format
            ReplaceMissingWithUserConstant sp = new ReplaceMissingWithUserConstant();
            //specify the dataset
            Instances dataset = loadDataSet(filePath);
//            String attributeType = getAttributeType(dataset, input);

//            if(attributeType.equals("num")) {
//                sp.setNumericReplacementValue(input);    // for numerical attribute
//            } else {
//                sp.setNominalStringReplacementValue(input);    // for nominal attribute
//            }

            sp.setNumericReplacementValue(input);    // for numerical attribute
            sp.setNominalStringReplacementValue("null");    // for nominal attribute
            sp.setInputFormat(dataset);
            //apply
            Instances newData = Filter.useFilter(dataset, sp);
            saveData(newData, filePath);

            List<String> summary = createSummaryString(dataset);
            return summary;
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }


    //Replace missing value with mean
    public List<String> replaceMissingValueMean (String filePath) {

        try {
            //create NonSparseToSparse object to save in sparse ARFF format
            ReplaceMissingValues sp = new ReplaceMissingValues();
            //specify the dataset
            Instances dataset = loadDataSet(filePath);
            sp.setInputFormat(dataset);
            //apply
            sp.setIgnoreClass(true);
            Instances newData = Filter.useFilter(dataset, sp);
            saveData(newData, filePath);

            List<String> summary = createSummaryString(dataset);
            return summary;
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public List<String> getAttributes(String filePath) {
        Instances dataset = loadDataSet(filePath);
        List<String> attributes = new ArrayList<>();
        for(int i=0; i<dataset.numAttributes(); i++) {
            String name = dataset.attribute(i).name();
            attributes.add(name);
        }
        return attributes;
    }


    // HELPER METHODS ---------------------------------------------------------------------------------------------------------

    public List<String> createSummaryString(Instances dataset) {
        try{
            List<String> ls = new ArrayList<String>(Arrays.asList(dataset.toSummaryString().split("\n")));
            List<String> testing = new ArrayList<String>();
            for(String s:ls) {
                String data = s.replaceAll("(\\s*)/(\\s*)", "\\/");
                data = data.replaceAll("\\s+", " ");
                testing.add(data);
            }
            return testing;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("SUMMARY PROCESS UNSUCCESSFUL");
        }
        return null;
    }

    public Instances loadDataSet(String filePath) {
        try {
            //load dataset
            ConverterUtils.DataSource source = new ConverterUtils.DataSource(filePath);
            Instances dataset = source.getDataSet();
            return dataset;
        } catch(Exception e) {
            System.out.println(e);
        }
        return null;
    }

    public void saveData(Instances instances, String filePath) {
        try {
            ArffSaver saver = new ArffSaver();
            saver.setInstances(instances);
            saver.setFile(new File(filePath));
            saver.writeBatch();
        } catch(Exception e) {
            System.out.println(e);
        }
    }

    public String getAttributeType(Instances instances, String attributeName) {
        Attribute attribute = instances.attribute(attributeName);
        String attributeType = attribute.typeToString(attribute);
        return attributeType;
    }

}
