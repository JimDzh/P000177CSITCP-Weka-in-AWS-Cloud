package com.aws.loadDataMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import weka.core.converters.CSVLoader;

@SpringBootApplication
public class LoadDataMicroserviceApplication {

	public static void main(String[] args) {

		// CONVERTING CSV TO ARFF
//		CSVLoader loader = new CSVLoader();
//		try{
//			loader.setSource(new File("./Datasets/testCsv.csv"));
//			Instances data = loader.getDataSet();
//			ArffSaver saver = new ArffSaver();
//			saver.setInstances(data);
//			saver.setFile(new File("./Datasets/resultArff.arff"));
//			saver.writeBatch();
//		} catch (Exception e) {
//			System.out.println("ATTEMPT UNSUCCESSFUL");
//		}

		// LOADING A DATASET
		try{
			Instances dataset = new Instances(new BufferedReader(new FileReader("/Users/jim/Desktop/JH-177 CloudWeka/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/testArff_sparse.arff")));
			System.out.println(dataset.toSummaryString());
		} catch (Exception e) {
			System.out.println("ATTEMPT UNSUCCESSFUL");
		}

		SpringApplication.run(LoadDataMicroserviceApplication.class, args);
	}
}
