package com.aws.classifyMicroservice;

import org.springframework.stereotype.Service;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.evaluation.Evaluation;
import weka.classifiers.functions.Logistic;
import weka.classifiers.rules.ZeroR;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class ClassifyService {

    private List<List<String>> matrix;

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

            String result = generateResultString(naiveBayes, null, null, eval, splitFilePath, trainPercentage);
            return result;
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String zeroR(String splitFilePath, String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);
            //build model
            ZeroR zeroR = new ZeroR();
            zeroR.buildClassifier(train);

            Attribute target = test.attribute(train.numAttributes()-1);
            int cols = target.numValues();
//            System.out.println(target.numValues());

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(zeroR, test);

            String result = generateResultString(null, zeroR, null, eval, splitFilePath, trainPercentage);
            return result;
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String logistic(String splitFilePath, String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);

            //build model
            Logistic logistic = new Logistic();
            logistic.setOptions(Utils.splitOptions("-R 1.0E-8 -M -1 " +
                    "-num-decimal-places 4"));
            logistic.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(logistic, test);

            String result = generateResultString(null, null, logistic, eval, splitFilePath, trainPercentage);
            return result;
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public List<List<String>> getMatrix() {
        return this.matrix;
    }


    // HELPER METHODS ---------------------------------------------------------------------------------------------------------

    private String generateResultString(NaiveBayes nb, ZeroR zr, Logistic l, Evaluation eval, String filePath, String trainPercentage) throws Exception {

        String scheme = "";
        if (nb != null) {
            scheme = nb.getClass().getName();
        } else if (zr != null) {
            scheme = zr.getClass().getName();
        } else if (l != null) {
            scheme = l.getClass().getName();
        }

        String information = "<h1>Information: </h1><br/>" +
                "<p><b>Scheme:</b> " + scheme + "</p>" +
                "<p><b>Predicted attribute:</b> " + getLastAttributeNameType(filePath) + "</p>" +
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

        String matrix = eval.toMatrixString();
        List<String> matrix_rows = new ArrayList<>(Arrays.asList(matrix.split("\n")));
        matrix_rows.remove(0);

        this.matrix = new ArrayList<>();
        for(String row: matrix_rows) {
            List<String> data = new ArrayList<>(Arrays.asList(row.split("(\\s\\s)+")));
            List<String> rem = new ArrayList<>();
            for(String i: data) {
                if(i.equals("")) {
                    rem.add(i);
                }
            }
            data.removeAll(rem);
            this.matrix.add(data);
            System.out.println(data);
        }

//        String matrix = eval.toMatrixString("<br/><br/><h1>Confusion Matrix </h1>");
//        s = new ArrayList<>(Arrays.asList(matrix.split("\n")));
//        for(int i=0; i<s.size(); i++) {
//            String data = s.get(i);
//            data = "<p>" + data + "</p>";
//            s.set(i, data);
//        }
//        matrix = String.join("", s);

        String result = information + summary + details;
        return result;
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
