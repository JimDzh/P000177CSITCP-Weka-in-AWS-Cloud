package com.aws.loadDataMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import weka.core.Instances;
import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@SpringBootApplication
public class LoadDataMicroserviceApplication {

	public static void main(String[] args) {

//		// CONVERTING CSV TO ARFF
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
//		try{
//			Instances dataset = new Instances(new BufferedReader(new FileReader("./Datasets/testArff.arff")));
//			String summary = dataset.toSummaryString();
//			String[] summary_split = dataset.toSummaryString().split("\n");
//			List<String> summary = new ArrayList<String>(Arrays.asList(dataset.toSummaryString().split("\n")));

//			List<String> headers = new ArrayList<>();
//			Summary s = new Summary(summary.get(0), summary.get(1), summary.get(2));
//			List<String> header_s = new ArrayList<String>(Arrays.asList(summary.get(4).split(" ")));
//
//			for (int i = 0; i < header_s.size(); i++) {
//				if (header_s.get(i).length() > 2) {
//					String data = header_s.get(i).replaceAll("\\s+", "");
//					headers.add(data);
//				}
//			}
//			s.setHeader(headers);
//
//			List<List<String>> rows;
//			for (int i = 5; i < summary.size(); i++) {
//				List<String> st = new ArrayList<String>(Arrays.asList(summary.get(i).split(" ")));
//				for (int j = 0; j < st.size(); j++) {
//					if (st.get(j).length() >= 1) {
//						String data = st.get(j).replaceAll("\\s+", "");
//						rows.add(data);
//					}
//				}
//			}

//			List<String> testing;
//			for(String s:summary) {
//				String data = s.replaceAll("\\s/(\\s+)", "/");
//				data = data.replaceAll("\\s+", "      ");
//				System.out.println(data);
//			}

//			System.out.println(summary);


//			System.out.println(dataset.relationName());
//			System.out.println(dataset.numInstances());
//			System.out.println(dataset.numAttributes());
//			Enumeration<Attribute> atts = dataset.enumerateAttributes();
//			List<AttributeModel> attributesList = new ArrayList<>();
//
//			System.out.println("\nAttributes\n");
//			while(atts.hasMoreElements()) {
//				Attribute attribute = atts.nextElement();
//				AttributeModel model = new AttributeModel(attribute.name(), attribute.typeToString(attribute),
//										dataset.numDistinctValues(attribute));
//				attributesList.add(model);
//
//				System.out.println("Name: " + attribute.name());
//				System.out.println("Type: " + attribute.typeToString(attribute));
//				System.out.println("Distinct values: " + dataset.numDistinctValues(attribute) + "\n");
//			}

//		} catch (Exception e) {
//			System.out.println("ATTEMPT UNSUCCESSFUL");
//			System.out.println(e);
//		}

		SpringApplication.run(LoadDataMicroserviceApplication.class, args);
	}

}
