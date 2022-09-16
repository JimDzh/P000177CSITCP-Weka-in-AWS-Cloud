package com.aws.loadDataMicroservice;

/**
 * @author Yitao Ma
 * @description TODO
 * @date 2022-09-14  5:21 pm
 */
public class TestClassifier {
    public static void main(String[] args) throws Exception {
        Classifier classifier = new Classifier();

        /** Used for if predicted attribute is type nominal**/

        System.out.println(classifier.naiveBayes("./Datasets/heart-clean.arff", "80.556"));

        //System.out.println(classifier.zeroR("./Datasets/heart-clean.arff","70"));

        //System.out.println(classifier.logistic("./Datasets/heart-clean.arff", "70"));

        /** J48 will output tree**/
        //System.out.println(classifier.j48("./Datasets/heart-clean.arff","70"));

        //System.out.println(classifier.smo("./Datasets/heart-clean.arff","65.25"));

        // System.out.println(classifier.ibk("./Datasets/heart-clean.arff","90.56789"));

        /** Used for if predicted attribute is type numeric**/

        //System.out.println(classifier.linearRegression("./Datasets/studentAssessmentNew.arff", "90.2"));

        /** REPTree will output tree**/
        //System.out.println(classifier.repTree("./Datasets/studentAssessmentNew.arff", "70.6"));

        // System.out.println(classifier.multilayerPerceptron("./Datasets/pedalme_edges.arff", "70"));

        //System.out.println(classifier.smoReg("./Datasets/pedalme_edges.arff", "70"));
    }
}
