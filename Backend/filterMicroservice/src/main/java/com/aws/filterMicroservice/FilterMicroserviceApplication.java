package com.aws.filterMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FilterMicroserviceApplication {

	public static void main(String[] args) {

//		try {
//			FilterService f = new FilterService();
//			f.setFilePath("./Datasets/testReplaceConstant.arff");
////			f.getAttributeValues("class");
////			List<String> s = f.replaceMissingWithConstant("2", "numeric");
////			for(String r: s) {
////				System.out.println(r);
////			}
//			f.replaceMissingWithConstant("tested_negative", "nominal");
////			f.replaceMissingWithConstant("2", "numeric");
//		} catch(Exception e) {
//			System.out.println(e);
//		}

		SpringApplication.run(FilterMicroserviceApplication.class, args);
	}

}
