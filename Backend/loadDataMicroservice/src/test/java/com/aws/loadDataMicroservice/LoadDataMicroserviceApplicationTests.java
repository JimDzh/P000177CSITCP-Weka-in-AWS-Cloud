package com.aws.loadDataMicroservice;


import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.List;


import static org.junit.jupiter.api.Assertions.assertEquals;


@SpringBootTest
class LoadDataMicroserviceApplicationTests {


	//Get the summary when the createSummaryString is invoked with an Arff File, Check the summary is not a Null result.
	@Test
	void createSummaryString_GetDataSummary_IfDataSummaryInvokedWithArffFile() {
        LoadDataService loadDataService = new LoadDataService();
        List<String> summary = loadDataService.createSummaryString("heart-clean.arff");
		System.out.println("dataset："+summary);

		assert summary != null;
	}

	//Get the summary when the createSummaryString is invoked with a CSV File, Check the summary is not a Null result.
	@Test
	void createSummaryString_GetDataSummary_IfDataSummaryInvokedWithCSVFile () {
        LoadDataService loadDataService = new LoadDataService();
        List<String> summary = loadDataService.createSummaryString("testCsv.csv");
		System.out.println("dataset："+summary);

		assert summary != null;
	}


	//Get the summary when the createSummaryString is invoked with a PNG File, Check the summary is a Null result.
	@Test
	void createSummaryString_getErrorDataSummary_IfDataSummaryInvokedWithApngFile () {
		LoadDataService loadDataService = new LoadDataService();
		List<String> summary = loadDataService.createSummaryString("logo192.png");
		System.out.println("dataset："+summary);

		assert summary == null;
	}


	//Check that whether the handleCsv method is invoked successfully, If select a CSV file.
	@Test
	public void HandleCsv_HandleSuccessfully_IfHandleCsvFile() {
		LoadDataService loadDataService = new LoadDataService();
		String csvPath = "testCsv.csv";
		boolean b = loadDataService.handleCsv(csvPath);
		assertEquals(true, b);
	}


	//Check that whether the handleCsv method is invoked successfully, If select an Arff file.
	@Test
	public void HandleCsv_HandleUnsucessfully_IfHandleArffFile() {
		LoadDataService loadDataService = new LoadDataService();
		String csvPath = "heart-clean.arff";
		boolean b = loadDataService.handleCsv(csvPath);
		assertEquals(false, b);
	}


	//Check that Whether the UploadFile is invoked with a CSV File and the CSV File is handled to Arff file.
	//Check the file whether exist in the Datasets folder.
	@Test
	public void UploadFile_UploadAFile_IfUploadACSVFile() {
		String oldPathName = "/Users/zehuliu/Desktop/testCsv.csv";
		File file = new File(oldPathName);
		String fileName = file.getName();
		System.out.println(file.getName());

        LoadDataService loadDataService = new LoadDataService();
        try {
			MultipartFile mFile = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
			loadDataService.uploadFile(mFile);

			String newFilePath = null;
			if(fileName.endsWith(".csv")){
				fileName = fileName.replace(".csv","") + ".arff";
			}
			newFilePath = "../../Datasets/" + fileName;
			System.out.println("newFilePath:" + newFilePath);
			File targetFile = new File(newFilePath);
			assert targetFile.exists();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}


	//When UploadFile is invoked with an ARFF File, check the file whether exist in the Datasets folder.
    @Test
    public void UploadFile_UploadAFile_IfUploadAnArffFile() {
        String oldPathName = "/Users/zehuliu/Desktop/testArff.arff";
        File file = new File(oldPathName);
        String fileName = file.getName();
        System.out.println(file.getName());

        LoadDataService loadDataService = new LoadDataService();
        try {
            MultipartFile mFile = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
            loadDataService.uploadFile(mFile);

            String newFilePath = null;
            if(fileName.endsWith(".csv")){
                fileName = fileName.replace(".csv","") + ".arff";
            }
            newFilePath = "../../Datasets/" + fileName;
            System.out.println("newFilePath:" + newFilePath);
            File targetFile = new File(newFilePath);
            assert targetFile.exists();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
