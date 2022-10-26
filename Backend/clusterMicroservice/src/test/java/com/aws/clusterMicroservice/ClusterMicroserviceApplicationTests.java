package com.aws.clusterMicroservice;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;


import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.hamcrest.MatcherAssert.assertThat;

@SpringBootTest
class ClusterMicroserviceApplicationTests {
	private static ClusterController clusterController;
	private static ClusterService clusterService;

	@BeforeAll
	public static void initialise() { // initialise the controller and service
//		clusterController = new ClusterController(); // for test the
//		// controller class's methods
//		clusterController.initialiseClusterServiceAttribute();
//		// initialise the service attribute in the controller class
//		clusterService = new ClusterService();// for directly test the
////		// service class
		clusterService = new ClusterService();
	}

	@AfterEach
	public void reset() {


	}





	// SimpleKmeans
	//Get the Summary when SimpleKmeans invoked, Check if the summary is not a Null result.
	@Test
	void SimpleKmeansClusterer_SimpleKmeansSummary_IfSimpleKmeansClustererInvoked() throws Exception{
		String filepath = "/Users/zehuliu/Downloads/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/segment-challenge.arff";
		String summary = clusterService.SimpleKmeansClusterer(filepath, "80");
		System.out.println(summary);
		assert summary != null;
	}

	//Get the Summary when SimpleKmeans is invoked with a wrong range that "101", Check if the summary will throw the right exception.
	@Test
	void SimpleKmeansClusterer_ErrorMessage_IfSimpleKmeansClustererInvokedWithWrongPercentageRange() throws Exception{
		String filepath = "/Users/zehuliu/Downloads/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/segment-challenge.arff";
		String summary = clusterService.SimpleKmeansClusterer(filepath, "101");
		System.out.println(summary);
		assertEquals("Input should not be empty and percentage must be a number" +
				" between 0 and 100", summary);

	}

	//Get the Summary when SimpleKmeans is invoked with a wrong range that "-1", Check if the summary will throw the right exception.
	@Test
	void SimpleKmeansClusterer_ErrorMessage_IfSimpleKmeansClustererInvokedWithWrongPercentageRangeNegative() throws Exception{
		String filepath = "/Users/zehuliu/Downloads/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/segment-challenge.arff";
		String summary = clusterService.SimpleKmeansClusterer(filepath, "-1");
		System.out.println(summary);
		assertEquals("Input should not be empty and percentage must be a number" +
				" between 0 and 100", summary);

	}

	//Get the Summary when SimpleKmeans is invoked with a wrong range that "0", Check if the summary will throw the right exception.
	@Test
	void SimpleKmeansClusterer_ErrorMessage_IfSimpleKmeansClustererInvokedWithWrongPercentageRangeZero() throws Exception{
		String filepath = "/Users/zehuliu/Downloads/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/segment-challenge.arff";
		String summary = clusterService.SimpleKmeansClusterer(filepath, "0");
		System.out.println(summary);
		assertEquals("Input should not be empty and percentage must be a number" +
				" between 0 and 100", summary);

	}


	//Get the Summary when SimpleKmeans is invoked with an empty range that "", Check if the summary will throw the right exception.
	@Test
	void SimpleKmeansClusterer_ErrorMessage_IfSimpleKmeansClustererInvokedWithEmptyPercentageRange() throws Exception{
		String filepath = "/Users/zehuliu/Downloads/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/segment-challenge.arff";
		String summary = clusterService.SimpleKmeansClusterer(filepath, "");
		System.out.println(summary);
		assertEquals("Input should not be empty and percentage must be a number" +
				" between 0 and 100", summary);

	}



	//EMClusterer
	//Get the Summary when EMClusterer is invoked, Check if the summary is not a Null result.
	@Test
	void EMClusterer_EMClustererSummary_IfEMClustererInvoked() throws Exception{
		String filepath = "/Users/zehuliu/Downloads/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/segment-challenge.arff";
		String summary = clusterService.EMClusterer(filepath, "80");
		System.out.println(summary);
		assert summary != null;

	}

	//Get the Summary when EMClusterer is invoked with a wrong range that "101", Check if the summary will throw the right exception.
	@Test
	void EMClusterer_ErrorMessage_IfEMClustererInvokedWithWrongPercentageRange() throws Exception{
		String filepath = "/Users/zehuliu/Downloads/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/segment-challenge.arff";
		String summary = clusterService.EMClusterer(filepath, "101");
		System.out.println(summary);
		assertEquals("Input should not be empty and percentage must be a number" +
				" between 0 and 100", summary);

	}

	//Get the Summary when EMClusterer is invoked with a wrong range that "-1", Check if the summary will throw the right exception.
	@Test
	void EMClusterer_ErrorMessage_IfEMClustererInvokedWithWrongPercentageRangeNegative() throws Exception{
		String filepath = "/Users/zehuliu/Downloads/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/segment-challenge.arff";
		String summary = clusterService.EMClusterer(filepath, "-1");
		System.out.println(summary);
		assertEquals("Input should not be empty and percentage must be a number" +
				" between 0 and 100", summary);

	}

	//Get the Summary when EMClusterer is invoked with a wrong range that "0", Check if the summary will throw the right exception.
	@Test
	void EMClusterer_ErrorMessage_IfEMClustererInvokedWithWrongPercentageRangeZero() throws Exception{
		String filepath = "/Users/zehuliu/Downloads/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/segment-challenge.arff";
		String summary = clusterService.EMClusterer(filepath, "0");
		System.out.println(summary);
		assertEquals("Input should not be empty and percentage must be a number" +
				" between 0 and 100", summary);

	}

	//Get the Summary when EMClusterer is invoked with an empty range that "", Check if the summary will throw the right exception.
	@Test
	void EMClusterer_ErrorMessage_IfEMClustererInvokedWithEmptyPercentageRange() throws Exception{
		String filepath = "/Users/zehuliu/Downloads/P000177CSITCP-Weka-in-AWS-Cloud/Datasets/segment-challenge.arff";
		String summary = clusterService.EMClusterer(filepath, "");
		System.out.println(summary);
		assertEquals("Input should not be empty and percentage must be a number" +
				" between 0 and 100", summary);

	}




}



