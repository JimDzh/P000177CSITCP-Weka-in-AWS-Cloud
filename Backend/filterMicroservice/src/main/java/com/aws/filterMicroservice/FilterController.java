package com.aws.filterMicroservice;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/api/filter")
public class FilterController {

    @Autowired
    private FilterService filterService;
    private String filePath;

    // link: http://localhost:8083/api/filter/setFilename
    @PostMapping("/setFilename")
    public void setFileName(String fileName) {
        String fn = fileName;
        this.filePath = "./Datasets/"+ fn;
    }

    // link: http://localhost:8083/api/filter/removeAttribute
    @PostMapping("/removeAttribute")
    public List<String> removeAttribute(String attribute) {
//        System.out.println(attribute);
        return filterService.removeAttribute(attribute, this.filePath);
    }

    // link: http://localhost:8083/api/filter/replaceMissing-constant
    @PostMapping("/replaceMissing-constant")
    public List<String> replaceMissingWithConstant(String constant) {
        return filterService.replaceMissingWithConstant(constant, filePath);
    }

    // link: http://localhost:8083/api/filter/replaceMissing-mean
    @PostMapping("/replaceMissing-mean")
    public List<String> replaceMissingWithMean() {
        return filterService.replaceMissingValueMean(filePath);
    }

    // link: http://localhost:8083/api/filter/getAttributes
    @GetMapping("/getAttributes")
    public List<String> getAttributes() {
        return filterService.getAttributes(filePath);
    }


}
