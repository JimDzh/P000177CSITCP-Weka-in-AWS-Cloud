package com.aws.filterMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.List;

@SpringBootApplication
public class FilterMicroserviceApplication {

	public static void main(String[] args) {

//		try {
//			FilterService f = new FilterService();
//			List<String> s = f.removeAttribute("LastName", "./Datasets/sample.arff");
//			for(String r: s) {
//				System.out.println(r);
//			}
//		} catch(Exception e) {
//			System.out.println(e);
//		}

		SpringApplication.run(FilterMicroserviceApplication.class, args);
	}

}
