package com.aws.loadDataMicroservice;
import ch.qos.logback.core.util.FileUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import com.aws.loadDataMicroservice.LoadDataService;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


@CrossOrigin
@RestController
@RequestMapping("/api/load-data")
public class LoadDataController {

    @Autowired
    private LoadDataService loadDataService;

    // link: http://localhost:8081/api/load-data/getDataSummary
    @GetMapping("/getDataSummary")
    public List<String> getDataSummary(String fileName){
        String name = fileName.toString();
        List<String> summary = loadDataService.createSummaryString(name);
        return summary;
    }


    /**
     * Method to upload file
     * @param file
     * link: http://localhost:8081/api/load-data/uploadFile
     */
    @PostMapping("/uploadFile")
    public void uploadFile(@RequestParam("file") MultipartFile file) {
        boolean uploadCheck = loadDataService.uploadFile(file);
        if(uploadCheck) {
            System.out.println("SUCCESSFULLY SAVED THE DATASET: " + file.getOriginalFilename());
        } else {
            System.out.println("ATTEMPT UNSUCCESSFUL");
        }
    }

}
