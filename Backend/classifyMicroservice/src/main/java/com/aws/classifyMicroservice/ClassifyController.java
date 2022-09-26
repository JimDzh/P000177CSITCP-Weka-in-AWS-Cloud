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

//    @Autowired
//    private com.aws.loadDataMicroservice.LoadDataService loadDataService;

    // link: http://localhost:8082/api/classify/setFilename
    @PostMapping("/setFilename")
    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

//    // link: http://localhost:8082/api/classify/getDataSummary
//    @GetMapping("/getDataSummary")
//    public List<String> getDataSummary(String algorithm, String percentage) {
//
////        String fileName = loadDataService.getFileName();
//        List<String> s = null;
//
//        if(algorithm.equals("NaiveBayes")) {
//            try {
//                String summary = classifyService.naiveBayes("./Datasets/"+ this.fileName, percentage);
//                s = new ArrayList<String>(Arrays.asList(summary.split("\n")));
//                return s;
//            } catch (Exception e) {
//                System.out.println(e);
//            }
//        }
//        return s;
//    }


    // link: http://localhost:8082/api/classify/getDataSummary
    @GetMapping("/getDataSummary")
    public String getDataSummary(String algorithm, String percentage) {

        if(algorithm.equals("NaiveBayes")) {
            try {
                String summary = classifyService.naiveBayes("./Datasets/"+ this.fileName, percentage);
                return summary;
            } catch (Exception e) {
                System.out.println(e);
            }
        }
        return "";
    }

}
