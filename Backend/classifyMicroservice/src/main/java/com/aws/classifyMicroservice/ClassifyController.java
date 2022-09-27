package com.aws.classifyMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/classify")
public class ClassifyController {

    @Autowired
    private ClassifyService classifyService;
    private String fileName;


    // link: http://localhost:8082/api/classify/setFilename
    @PostMapping("/setFilename")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }


    // link: http://localhost:8082/api/classify/getDataSummary
    @GetMapping("/getDataSummary")
    public String getDataSummary(String algorithm, String percentage) {

        String summary = "";

        try {
            switch (algorithm) {
                case "NaiveBayes":
                    summary = classifyService.naiveBayes("./Datasets/"+ this.fileName, percentage);
                    break;
                case "ZeroR":
                    summary = classifyService.zeroR("./Datasets/"+ this.fileName, percentage);
                    break;
                case "Logistic":
                    summary = classifyService.logistic("./Datasets/"+ this.fileName, percentage);
                    break;
            }
        } catch (Exception e) {
            System.out.println(e);
        }
        return summary;
    }

    // link: http://localhost:8082/api/classify/getConfusionMatrix
    @GetMapping("/getConfusionMatrix")
    public List<List<String>> getConfusionMatrix() {
        return classifyService.getMatrix();
    }

}
