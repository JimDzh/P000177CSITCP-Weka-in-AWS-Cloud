package com.aws.classifyMicroservice;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ClassifyMicroserviceApplication {

    public static void main(String[] args) {

        ClassifyService classifyService = new ClassifyService();
        try {
            String summary = classifyService.logistic("./Datasets/"+ "heart-clean.arff", "80");
        } catch (Exception e) {
            System.out.println(e);
        }

        SpringApplication.run(ClassifyMicroserviceApplication.class, args);
    }

}
