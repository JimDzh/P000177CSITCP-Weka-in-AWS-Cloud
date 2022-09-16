package com.aws.loadDataMicroservice;

import weka.classifiers.Evaluation;
import weka.classifiers.bayes.NaiveBayes;
import weka.classifiers.functions.*;
import weka.classifiers.lazy.IBk;
import weka.classifiers.rules.ZeroR;
import weka.classifiers.trees.J48;
import weka.classifiers.trees.REPTree;
import weka.core.Attribute;
import weka.core.Instances;
import weka.core.Utils;
import weka.core.converters.ConverterUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Yitao Ma
 * @description TODO
 * @date 2022-09-14  4:35 pm
 */
public class Classifier {
    public Classifier() {
    }

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
            return "=== Information ===\n\n" + "Scheme: " + naiveBayes.getClass().getName() +
                    "\n\n" + "Predicted attribute: " + getLastAttributeNameType(splitFilePath) + "\n\n" + "Mode: " + "Split " + trainPercentage +
                    "%" + " " +
                    "train, remainder " +
                    "test\n\n" + eval.toSummaryString("=== Summary ===\n",
                    false) + "\n" + eval.toClassDetailsString() + "\n" + eval.toMatrixString();
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

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(zeroR, test);
            return "=== Information ===\n\n" + "Scheme: " + zeroR.getClass().getName() +
                    "\n\n" + "Predicted attribute: " + getLastAttributeNameType(splitFilePath) + "\n\n" + "Mode: " + "Split " + trainPercentage +
                    "%" + " " +
                    "train, remainder " +
                    "test\n\n" + eval.toSummaryString("=== Summary ===\n",
                    false) + "\n" + eval.toClassDetailsString() + "\n" + eval.toMatrixString();
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
            return "=== Information ===\n\n" + "Scheme: " + logistic.getClass().getName() +
                    "\n\n" + "Predicted attribute: " + getLastAttributeNameType(splitFilePath) + "\n\n" + "Mode: " + "Split " + trainPercentage +
                    "%" + " " +
                    "train, remainder " +
                    "test\n\n" + eval.toSummaryString("=== Summary ===\n",
                    false) + "\n" + eval.toClassDetailsString() + "\n" + eval.toMatrixString();
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String j48(String splitFilePath, String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);

            //build model
            J48 j48 = new J48();
            j48.setOptions(Utils.splitOptions("-C 0.25 -M 2"));
            j48.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(j48, test);
            return "=== Information ===\n\n" + "Scheme: " + j48.getClass().getName() +
                    "\n\n" + "Predicted attribute: " + getLastAttributeNameType(splitFilePath) + "\n\n" + "Mode: " + "Split " + trainPercentage +
                    "%" + " " +
                    "train, remainder " +
                    "test\n\n" + j48 + "\n" + eval.toSummaryString("=== " +
                            "Summary ===\n",
                    false) + "\n" + eval.toClassDetailsString() + "\n" + eval.toMatrixString();
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String smo(String splitFilePath, String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);

            //build model
            SMO smo = new SMO();
            smo.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(smo, test);
            return "=== Information ===\n\n" + "Scheme: " + smo.getClass().getName() +
                    "\n\n" + "Predicted attribute: " + getLastAttributeNameType(splitFilePath) + "\n\n" + "Mode: " + "Split " + trainPercentage +
                    "%" + " " +
                    "train, remainder " +
                    "test\n\n" + eval.toSummaryString("=== Summary ===\n",
                    false) + "\n" + eval.toClassDetailsString() + "\n" + eval.toMatrixString();
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String ibk(String splitFilePath, String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);

            //build model
            IBk ibk = new IBk();
            ibk.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(ibk, test);
            return "=== Information ===\n\n" + "Scheme: " + ibk.getClass().getName() +
                    "\n\n" + "Predicted attribute: " + getLastAttributeNameType(splitFilePath) + "\n\n" + "Mode: " + "Split " + trainPercentage +
                    "%" + " " +
                    "train, remainder " +
                    "test\n\n" + eval.toSummaryString("=== Summary ===\n",
                    false) + "\n" + eval.toClassDetailsString() + "\n" + eval.toMatrixString();
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String linearRegression(String splitFilePath,
                                   String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);

            //build model
            LinearRegression linearRegression = new LinearRegression();
            linearRegression.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(linearRegression, test);
            return "=== Information ===\n\n" + "Scheme: " + linearRegression.getClass().getName() +
                    "\n\n" + "Predicted attribute: " + getLastAttributeNameType(splitFilePath) + "\n\n" + "Mode: " + "Split " + trainPercentage +
                    "%" + " " +
                    "train, remainder " +
                    "test\n\n" + eval.toSummaryString("=== Summary ===\n",
                    false);
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String repTree(String splitFilePath,
                          String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);

            //build model
            REPTree repTree = new REPTree();
            repTree.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(repTree, test);
            return "=== Information ===\n\n" + "Scheme: " + repTree.getClass().getName() +
                    "\n\n" + "Predicted attribute: " + getLastAttributeNameType(splitFilePath) + "\n\n" + "Mode: " + "Split " + trainPercentage +
                    "%" + " " +
                    "train, remainder " +
                    "test\n\n" + repTree + "\n\n" + eval.toSummaryString("===" +
                            " " +
                            "Summary " +
                            "===\n",
                    false);
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String smoReg(String splitFilePath,
                         String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);

            //build model
            SMOreg smoReg = new SMOreg();
            smoReg.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(smoReg, test);
            return "=== Information ===\n\n" + "Scheme: " + smoReg.getClass().getName() +
                    "\n\n" + "Predicted attribute: " + getLastAttributeNameType(splitFilePath) + "\n\n" + "Mode: " + "Split " + trainPercentage +
                    "%" + " " +
                    "train, remainder " +
                    "test\n\n" + eval.toSummaryString("=== Summary " +
                            "===\n",
                    false);
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    public String multilayerPerceptron(String splitFilePath,
                                       String trainPercentage) throws Exception {
        if (tpInputValid(trainPercentage)) { // if input is valid
            double sanitizedInputTP = Double.parseDouble(trainPercentage);
            List<Instances> trainAndTest = splitDataset(splitFilePath,
                    sanitizedInputTP);
            Instances train = trainAndTest.get(0);
            Instances test = trainAndTest.get(1);

            //build model
            MultilayerPerceptron multilayerPerceptron =
                    new MultilayerPerceptron();
            multilayerPerceptron.buildClassifier(train);

            Evaluation eval = new Evaluation(train);
            eval.evaluateModel(multilayerPerceptron, test);
            return "=== Information ===\n\n" + "Scheme: " + multilayerPerceptron.getClass().getName() +
                    "\n\n" + "Predicted attribute: " + getLastAttributeNameType(splitFilePath) + "\n\n" + "Mode: " + "Split " + trainPercentage +
                    "%" + " " +
                    "train, remainder " +
                    "test\n\n" + eval.toSummaryString("=== Summary " +
                            "===\n",
                    false);
        } else { // if input is invalid
            return "Input should not be empty and percentage must be a number" +
                    " between 0 and 100";
        }
    }

    private Boolean tpInputValid(String inputTrainPercentage) {
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

    private String getLastAttributeNameType(String splitFilePath) throws Exception {
        ConverterUtils.DataSource source =
                new ConverterUtils.DataSource(splitFilePath);

        Instances originalDataset = source.getDataSet();
        Attribute predictedAttribute =
                originalDataset.attribute(originalDataset.numAttributes() -
                        1);
        return predictedAttribute.name() + " (" + predictedAttribute.typeToString(predictedAttribute) + ")";
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
