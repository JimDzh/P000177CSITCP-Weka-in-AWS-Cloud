package com.aws.filterMicroservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class FilterMicroserviceApplication {

	public static void main(String[] args) {

//		try {
//			FilterService f = new FilterService();
//			f.removeAttribute("LastName", "./Datasets/sample.arff");
//		} catch(Exception e) {
//			System.out.println(e);
//		}

		SpringApplication.run(FilterMicroserviceApplication.class, args);
	}

}
