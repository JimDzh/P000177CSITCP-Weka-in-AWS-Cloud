package com.aws.classifyMicroservice;

import org.hamcrest.CoreMatchers;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ClassifyMicroserviceApplicationTests {
    // Variable declared
    private static ClassifyController classifyController;

    private static ClassifyService classifyService;

    @BeforeAll
    public static void initialise() {
        // Initialise required object and set project name
        classifyController = new ClassifyController(); // initialised only
        // once same as the ClassifyService attribute
        // inside the ClassifyController

        classifyService = new ClassifyService();
    }

    @AfterEach
    public void reset() {
        // Reset project name
        classifyController.setFileName(null);
    }

    @Test
    void getFileName_UploadedFileName_IfSetFileNameInvoked() {
        // Arrange
        String fileName = "test.arff";
        // Act
        classifyController.setFileName(fileName);
        // Assert
        assertEquals(fileName, classifyController.getFileName());
    }

    @Test
    void getFileName_null_IfSetFileNameNotInvoked() {
        // Arrange
        String fileName = null;
        // Act
        // Assert
        assertEquals(fileName, classifyController.getFileName());
    }

    @Test
    void getDataSummary_NaiveBayesSummary_IfNaiveBayesInvoked() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        // as long as the summary contains information it is correct
        String x = "78";
        assertThat(classifyController.getDataSummary("NaiveBayes", "78"),
                CoreMatchers.containsString(x));
    }

    @Test
    void getDataSummary_EmptyString_IfNaiveBayesNotInvoked() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("", classifyController.getDataSummary("ZeroR", "70"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRange() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("NaiveBayes", "101"));
    }

    @Test
    void getDataSummary_ErrorMessage_IfNaiveBayesInvokedWithEmptyPercentage() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("NaiveBayes", ""));
    }

    @Test
    void getDataSummary_ErrorMessage_IfNaiveBayesInvokedWithCharacterPercentage() throws Exception {
        String fileName = "heart-clean.arff";
        classifyController.setFileName(fileName);
        assertEquals("Input should not be empty and percentage must be a " +
                        "number between 0 and 100",
                classifyController.getDataSummary("NaiveBayes", "sdfdsf"));
    }
    // ----- below test classifyService

    @Test
    void naiveBayes_CorrectSummary_IfNaiveBayesInvoked() throws Exception {
        assertThat(classifyService.naiveBayes("/Users/ethan/Desktop" +
                        "/Programming project/Github-Repo/P000177CSITCP-Weka" +
                        "-in-AWS" +
                        "-Cloud/Datasets/heart-clean.arff", "78"),
                CoreMatchers.containsString("78"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithWrongPercentageRange() throws Exception {
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes(
                "/Users/ethan/Desktop/Programming " +
                        "project/Github-Repo/P000177CSITCP-Weka-in" +
                        "-AWS-Cloud/Datasets/heart-clean.arff", "200"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithCharacterPercentage() throws Exception {
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes(
                "/Users/ethan/Desktop/Programming " +
                        "project/Github-Repo/P000177CSITCP-Weka-in" +
                        "-AWS-Cloud/Datasets/heart-clean.arff",
                "fsdfdsf"));
    }

    @Test
    void naiveBayes_ErrorMessage_IfNaiveBayesInvokedWithEmptyPercentage() throws Exception {
        assertEquals("Input should not be empty and percentage must be a " +
                "number between 0 and 100", classifyService.naiveBayes(
                "/Users/ethan/Desktop/Programming " +
                        "project/Github-Repo/P000177CSITCP-Weka-in" +
                        "-AWS-Cloud/Datasets/heart-clean.arff", ""));
    }

}
