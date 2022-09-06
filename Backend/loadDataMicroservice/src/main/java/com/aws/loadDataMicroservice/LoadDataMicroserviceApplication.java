package com.aws.loadDataMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weka.core.Instances;
import weka.core.converters.ArffSaver;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;

@SpringBootApplication
public class LoadDataMicroserviceApplication {

	public static void main(String[] args) {
		try{
			Instances dataset = new Instances(new BufferedReader(new FileReader("/Users/sreshtaa/Downloads/diabetes.arff")));
			System.out.println(dataset.toSummaryString());
		} catch (Exception e) {
			System.out.println("ATTEMPT UNSUCCESSFUL");
		}

		SpringApplication.run(LoadDataMicroserviceApplication.class, args);
	}

}
