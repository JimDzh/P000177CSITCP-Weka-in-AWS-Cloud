package com.aws.loadDataMicroservice;

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

	@Resource
	LoadDataController loadDataController;




	@Test
	void testAUploadFile(){
		File file = new File("../../Datasets/heart-clean.arff");
		System.out.println(file.getName());
		try {
			MultipartFile cMultiFile = new MockMultipartFile("file", file.getName(), null, new FileInputStream(file));
			loadDataController.uploadFile(cMultiFile);
		} catch (IOException e) {
			e.printStackTrace();
			assert false;
		}

	}

	@Test
	void testBGetDataSummary() {
		List<String> summary = loadDataController.getDataSummary("heart-clean.arff");
		System.out.println("dataset："+summary);

		assert summary != null;
	}



	@Test
	public void testHandleCsv() {
		LoadDataService loadDataService = new LoadDataService();
		String csvPath = "testCsv.csv";
		boolean b = loadDataService.handleCsv(csvPath);
		assertEquals(true, b);
	}


}
