package com.aws.classifyMicroservice;

import org.springframework.stereotype.Service;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.converters.ConverterUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClassifyService {

    // TESTING ----------------------------------------------------------------------------------------------------------------------------------------

    public String naiveBayes(String splitFilePath, String trainPercentage) throws Exception {

        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);
            //build model
            NaiveBayes naiveBayes = new NaiveBayes();
            naiveBayes.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(naiveBayes, test);

            String information = "<h1>Information: </h1><br/>" +
                    "<p><b>Scheme:</b> " + naiveBayes.getClass().getName() + "</p>" +
                    "<p><b>Predicted attribute:</b> " + getLastAttributeNameType(splitFilePath) + "</p>" +
                    "<p><b>Mode:</b> " + "Split " + trainPercentage + "% for train, " +
                    (100-Integer.parseInt(trainPercentage)) + "% for test</p>";

            String summary = eval.toSummaryString("<br/><br/><h1>Summary </h1>", false) ;
            List<String> s = new ArrayList<>(Arrays.asList(summary.split("\n")));
            for(int i=0; i<s.size(); i++) {
                String data = s.get(i);
                data = "<p>" + data + "</p>";
                s.set(i, data);
            }
            summary = String.join("", s);

            String details = eval.toClassDetailsString("<br/><br/><h1>Detailed Accuracy By Class </h1>");
            s = new ArrayList<>(Arrays.asList(details.split("\n")));
            for(int i=0; i<s.size(); i++) {
                String data = s.get(i);
                data = "<p>" + data + "</p>";
                s.set(i, data);
            }
            details = String.join("", s);

            String matrix = eval.toMatrixString("<br/><br/><h1>Confusion Matrix </h1>");
            s = new ArrayList<>(Arrays.asList(matrix.split("\n")));
            for(int i=0; i<s.size(); i++) {
                String data = s.get(i);
                data = "<p>" + data + "</p>";
                s.set(i, data);
            }
            matrix = String.join("", s);

            String result = information + summary + details + matrix;

//            return "<h1>Information: </h1><br/>" +
//                    "<p><b>Scheme:</b> " + naiveBayes.getClass().getName() + "</p>" +
//                    "<p><b>Predicted attribute:</b> " + getLastAttributeNameType(splitFilePath) + "</p>" +
//                    "<p><b>Mode:</b> " + "Split " + trainPercentage + "%" + " " + "train, remainder " + "test</p>" +
//                    eval.toSummaryString("<br/><br/><h1>Summary </h1>", false) +
//                    eval.toClassDetailsString("<br/><br/><h1>Detailed Accuracy By Class </h1>")  +
//                    eval.toMatrixString("<br/><br/><h1>Confusion Matrix </h1>");

            return result;
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    private String getLastAttributeNameType(String splitFilePath) throws Exception {
        ConverterUtils.DataSource source =
                new ConverterUtils.DataSource(splitFilePath);

        Instances originalDataset = source.getDataSet();
        Attribute predictedAttribute =
                originalDataset.attribute(originalDataset.numAttributes() -
                        1);
        return predictedAttribute.name() + " (" + predictedAttribute.typeToString(predictedAttribute) + ")";
    }

    private boolean tpInputValid(String inputTrainPercentage) {
        if (inputTrainPercentage == null || inputTrainPercentage.isEmpty()) {
            return false;
        }
        try {
            Double trainPercentage = Double.parseDouble(inputTrainPercentage);
            if (trainPercentage <= 0 || trainPercentage >= 100) {
                return false;
            }
        } catch (NumberFormatException e) {
            return false;
        }
        return true;
    }

    private List<Instances> splitDataset(String splitFilePath,
                                         Double trainPercentage) throws Exception {
        double sanitizedTrainPercentage = trainPercentage / 100.0;

        List<Instances> result = new ArrayList<>();

        ConverterUtils.DataSource source =
                new ConverterUtils.DataSource(splitFilePath);

        Instances originalDataset = source.getDataSet();
        // assume the last attribute is the target attribute (predicted
        // attribute) in the dataset.
        // User can not specify the target attribute
        originalDataset.setClassIndex(originalDataset.numAttributes() - 1);

        // must use the seed 1. Otherwise, the split will be different every
        // time
        originalDataset.randomize(new java.util.Random(1));

        int trainSize =
                (int) Math.round(originalDataset.numInstances() * sanitizedTrainPercentage);
        int testSize = originalDataset.numInstances() - trainSize;
        Instances train = new Instances(originalDataset, 0, trainSize);
        Instances test = new Instances(originalDataset, trainSize, testSize);

        result.add(train);
        result.add(test);

        return result;
    }

}
