package com.aws.filterMicroservice;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.File;
import java.util.ArrayList;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.hasProperty;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class FilterMicroserviceApplicationTests {


	private static FilterService filterService;

	@BeforeAll
	public static void initialise() { // initialise the controller and service

		filterService = new FilterService();// for directly test the
		// service class
	}

	@AfterEach
	public void reset() {
		// Reset file name
		filterService.setFilePath("");
	}

	/*	Deletes the specified attribute from a dataset,
	 *	then determines if the attribute still exists,
	 *	if not, the delete function is available
	 */
	@Test
	void removeAttribute_CorrectList_IfEmpty() {
		// Please remember to change the path to the dataset to your own path before testing
		filterService.setFilePath("/Users/jim/Desktop/test pp1 final/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/forFilterTest1.arff");
		List<String> list1 = new ArrayList<>();
		list1 = filterService.removeAttribute("age");

		File myObj = new File("/Users/jim/Desktop/test pp1 final/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/forFilterTest1RemoveAttr.arff");
		if (myObj.delete()) {
			System.out.println("Deleted the file: " + myObj.getName());
		} else {
			System.out.println("Failed to delete the file.");}

//		System.out.println(list1.get(5));
		boolean judge = false;
		if(list1.get(5).equals("1 age Num 0% 0% 100% 0/0% 1/33% 2 ")){
			judge = true;
		}
		assertFalse(judge);
	}
	/*
	 * Specify the replace type as numeric and a value of 0.
	 * Perform the filter once on both datasets.
	 * One of the datasets is the result that should
	 * be displayed after the other dataset has been filtered once,
	 * which means that the result will not change if the dataset is filtered again.
	 * If the two datasets are the same after filtering, then the function is available.
	 */
	@Test
	void replaceMissingWithConstantNumeric_CorrectList_IfSameAsTheCorrectSample() {
		// Please remember to change the path to the dataset to your own path before testing
		filterService.setFilePath("/Users/jim/Desktop/test pp1 final/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/forFilterTest2.arff");
		List<String> list1 = new ArrayList<>();
		list1 = filterService.replaceMissingWithConstant("0","numeric");
		filterService.setFilePath("/Users/jim/Desktop/test pp1 final/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/forFilterTest2C.arff");
		List<String> list2 = new ArrayList<>();
		list2 = filterService.replaceMissingWithConstant("0","numeric");
		File myObj = new File("/Users/jim/Desktop/test pp1 final/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/forFilterTest2ReplaceConst_numeric.arff");
		File myObj2 = new File("/Users/jim/Desktop/test pp1 final/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/forFilterTest2CReplaceConst_numeric.arff");
		if (myObj.delete()&&myObj2.delete()) {
			System.out.println("Deleted the file: " + myObj.getName());
		} else {
			System.out.println("Failed to delete the file.");}
		assertEquals(list1,list2);
	}

	/*
	 * The filtering is performed once on both datasets.
	 * One of the datasets is the result that should be displayed
	 * after the other dataset has been filtered once,
	 * which means that the result will not change if the dataset
	 * is filtered again. If the two datasets are the same after filtering,
	 * then the function is available.
	 */
	@Test
	void replaceMissingValueMean_CorrectList_IfSameAsTheCorrectSample() {
		// Please remember to change the path to the dataset to your own path before testing
		filterService.setFilePath("/Users/jim/Desktop/test pp1 final/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/forFilterTest3.arff");
		List<String> list1 = new ArrayList<>();
		list1 = filterService.replaceMissingValueMean();
		filterService.setFilePath("/Users/jim/Desktop/test pp1 final/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/forFilterTest3C.arff");
		List<String> list2 = new ArrayList<>();
		list2 = filterService.replaceMissingValueMean();
		assertEquals(list1,list2);
		File myObj = new File("/Users/jim/Desktop/test pp1 final/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/forFilterTest3CReplaceMean.arff");
		File myObj2 = new File("/Users/jim/Desktop/test pp1 final/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/forFilterTest3ReplaceMean.arff");
		if (myObj.delete()&&myObj2.delete()) {
			System.out.println("Deleted the file: " + myObj.getName());
		} else {
			System.out.println("Failed to delete the file.");}
	}
}
