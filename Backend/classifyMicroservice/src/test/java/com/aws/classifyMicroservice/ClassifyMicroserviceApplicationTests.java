package com.aws.classifyMicroservice;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class ClassifyMicroserviceApplicationTests {
    private static ClassifyService classifyService;

    @BeforeAll
    public static void initialise() { // initialise the controller and service
        classifyService = new ClassifyService();// for directly test the
        // service class
    }

    @AfterEach
    public void reset() {
        classifyService.setFilePath("");
        classifyService.setMatrix(null);
        classifyService.setDetails(null);
    }

    // ----- below test classifyService class's methods -----

    // NaiveBayes
    @Test
    void naiveBayes_CorrectSummary_IfNaiveBayesInvokedCorrectly() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        // the result summary should contain the string 78
        assertThat(classifyService.naiveBayes("78"),
                CoreMatchers.containsString("78"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRangeNegative() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes("-1"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRangeZero() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes("0"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRange100() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyService.naiveBayes("100"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRange() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes("200"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithCharacterPercentage() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes(
                "fsdfdsf"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithEmptyPercentage() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes(""));
    }

    // ZeroR
    @Test
    void zeroR_CorrectSummary_IfZeroRInvokedCorrectly() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertThat(classifyService.zeroR("78"),
                CoreMatchers.containsString("78"));
    }

    @Test
    void zeroR_ErrorMessage_IfZeroRInvokedWithWrongPercentageRangeNegative() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.zeroR("-1"));
    }

    @Test
    void zeroR_ErrorMessage_IfZeroRInvokedWithWrongPercentageRangeZero() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.zeroR("0"));
    }

    @Test
    void zeroR_ErrorMessage_IfZeroRInvokedWithWrongPercentageRange100() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyService.zeroR("100"));
    }

    @Test
    void zeroR_ErrorMessage_IfZeroRInvokedWithWrongPercentageRange() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.zeroR("200"));
    }

    @Test
    void zeroR_ErrorMessage_IfZeroRInvokedWithCharacterPercentage() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.zeroR("fsdfdsf"));
    }

    @Test
    void zeroR_ErrorMessage_IfZeroRInvokedWithEmptyPercentage() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.zeroR(""));
    }

    //Logistic
    @Test
    void logistic_CorrectSummary_IfLogisticInvokedCorrectly() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertThat(classifyService.logistic("78"),
                CoreMatchers.containsString("78"));
    }

    @Test
    void logistic_ErrorMessage_IfLogisticInvokedWithWrongPercentageRangeNegative() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.logistic("-1"));
    }

    @Test
    void logistic_ErrorMessage_IfLogisticInvokedWithWrongPercentageRangeZero() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.logistic("0"));
    }

    @Test
    void logistic_ErrorMessage_IfLogisticInvokedWithWrongPercentageRange100() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyService.logistic("100"));
    }

    @Test
    void logistic_ErrorMessage_IfLogisticInvokedWithWrongPercentageRange() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.logistic("200"));
    }

    @Test
    void logistic_ErrorMessage_IfLogisticInvokedWithCharacterPercentage() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.logistic("fsdfdsf"
        ));
    }

    @Test
    void logistic_ErrorMessage_IfLogisticInvokedWithEmptyPercentage() throws Exception {
        String fileName = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.logistic(""));
    }

    // Below test getMatrix()
    @Test
    void getMatrix_null_IfServiceClassMatrixAttributeNotInitialised() throws Exception {
        assertNull(classifyService.getMatrix());
    }

    @Test
    void getMatrix_NotNull_IfServiceClassMatrixAttributeIsInitialised() throws Exception {
        String filePath = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(filePath);
        // as long as any algorithms is invoked the matrix will be
        // initialised
        classifyService.naiveBayes("78");
        assertNotNull(classifyService.getMatrix());
    }

    // Below test getDetails()
    @Test
    void getDetails_null_IfServiceClassDetailsAttributeNotInitialised() throws Exception {
        assertNull(classifyService.getDetails());
    }

    @Test
    void getDetails_NotNull_IfServiceClassDetailsAttributeIsInitialised() throws Exception {
        String filePath = "/Users/ethan/Desktop/Programming " +
                "project/Gthub-RepoNew/P000177CSITCP-Weka-in-AWS-Cloud" +
                "/Datasets/heart-clean.arff";
        classifyService.setFilePath(filePath);
        // as long as any algorithms is invoked the details attribute
        // will be initialised
        classifyService.naiveBayes("78");
        assertNotNull(classifyService.getDetails());
    }
}