package com.aws.loadDataMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import weka.core.converters.CSVLoader;
import weka.core.converters.ConverterUtils;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class LoadDataService {

    private String fileName = "";

    public boolean uploadFile(MultipartFile file) {
        String folderPath = "../../Datasets/";
        // read and write the file to the local folder
        Arrays.asList(file).stream();
        byte[] bytes;
        try {
            bytes = file.getBytes();
            String fileName = file.getOriginalFilename();
            setFileName(fileName);
            Files.write(Paths.get(folderPath + fileName), bytes);
            // checking if it is a csv file and handling it
            String extension = fileName.substring(fileName.length() - 4);
            if(extension.equals(".csv")) {
                boolean update = handleCsv(fileName);
                if(!update) {
                    return false;
                }
            }
            return true;
        } catch (IOException e) {
            System.out.println(e);
            return false;
        }
    }

    public boolean handleCsv(String fileName) {
        // CONVERTING CSV TO ARFF
        CSVLoader loader = new CSVLoader();
        String newName = fileName.substring(0, fileName.length() - 4) + ".arff";
        try{
            File csvFile = new File("../../Datasets/" + fileName);
            loader.setSource(csvFile);
            Instances data = loader.getDataSet();
            ArffSaver saver = new ArffSaver();
            saver.setInstances(data);
            saver.setFile(new File("../../Datasets/" + newName));
            saver.writeBatch();
            csvFile.delete();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        }
    }

    public List<String> createSummaryString(String fileName) {

        String extension = fileName.substring(fileName.length() - 4);
        if(extension.equals(".csv")) {
            fileName = fileName.substring(0, fileName.length() - 4) + ".arff";
        }

        try{
            Instances dataset = new Instances(new BufferedReader(new FileReader("../../Datasets/" + fileName)));
            List<String> ls = new ArrayList<String>(Arrays.asList(dataset.toSummaryString().split("\n")));
            System.out.println(dataset.toSummaryString());
            List<String> testing = new ArrayList<String>();
            for(String s:ls) {
                String data = s.replaceAll("(\\s*)/(\\s*)", "\\/");
                data = data.replaceAll("\\s+", " ");
                testing.add(data);
//                System.out.println(data);
            }
            return testing;
        } catch (Exception e) {
            System.out.println(e);
            System.out.println("SUMMARY PROCESS UNSUCCESSFUL");
        }
        return null;
    }

    private void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileName() {
        return this.fileName;
    }

}
