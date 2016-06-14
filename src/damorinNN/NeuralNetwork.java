package damorinNN;

import core.game.StateObservation;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         10/06/2016
 */
public class NeuralNetwork {

    // Process variables
    public int patternNumber;
    public double patternError;
    public double outputPrediction;
    public double RMSerror; // Root Mean Squared Error
    // Hyperparamaters
    private int numEpochs;
    private int numInputs;
    private int numHidden;
    private int numOutputs;
    public double[] hiddenVal;
    // Weights of the neurons
    public double[][] weightsIH;
    public double[] weightsHO;
    //------------------Architecture------------------\\
    public double[] trainInputs;
    public double[] trainOutput;
    private double learnRateHO = 0.7;
    private double learnRateIH = 0.07;

    private double epsilon = 0.05;

    private StateObservation state;

    public NeuralNetwork(int epochs, int inputs, int hidden, int outputs, StateObservation stateObs) {
        this.numEpochs = epochs;
        this.numInputs = inputs;
        this.numHidden = hidden;
        this.numOutputs = outputs;
        this.state = stateObs;

        hiddenVal = new double[numHidden];
        weightsIH = new double[numInputs][numHidden];
        weightsHO = new double[numHidden];
        trainInputs = new double[numInputs];
        trainOutput = new double[numOutputs];
    }

    public int calculateAction() {
        for (int j = 0; j < numHidden; j++) {
            weightsHO[j] = (Math.random() - 0.5) / 2;
            for (int i = 0; i < numInputs; i++)
                weightsIH[i][j] = (Math.random() - 0.5) / 5;
        }

        trainInputs[0] = this.state.getGameScore();
        trainInputs[1] = 1;

        for(int i = 0; i < trainOutput.length; i++) {
            trainOutput[i] = i;
        }

        trainNetwork();
        return calculatePredictions();
    }

    private void trainNetwork() {

        for (int j = 0; j <= numEpochs; j++) {

            for (int i = 0; i < numOutputs; i++) {

                // Select a pattern
                patternNumber = (int) ((Math.random() * numOutputs) - 0.001);

                backPropHO();
                backPropIH();
            }

            calculateEpochError();
            System.out.println("epoch = " + j + "  RMS Error = " + RMSerror);

        }

    }

    private int calculatePredictions() {

        for (int i = 0; i < numHidden; i++) {
            hiddenVal[i] = 0.0;

            for (int j = 0; j < numInputs; j++)
                hiddenVal[i] = hiddenVal[i] + (trainInputs[j] * weightsIH[j][i]);

            hiddenVal[i] = relu(hiddenVal[i]);
        }

        outputPrediction = 0.0;

        for (int i = 0; i < numHidden; i++)
            outputPrediction = outputPrediction + hiddenVal[i] * weightsHO[i];

        patternError = outputPrediction - trainOutput[patternNumber];

        if(epsilon >= Math.random()) {
            return (int)(Math.random() * numOutputs);
        }
        return (int) outputPrediction;
    }

    private void backPropHO() {
        for (int k = 0; k < numHidden; k++) {
            double weightChange = learnRateHO * patternError * hiddenVal[k];
            weightsHO[k] = weightsHO[k] - weightChange;

            if (weightsHO[k] < -5)
                weightsHO[k] = -5;
            else if (weightsHO[k] > 5)
                weightsHO[k] = 5;
        }
    }

    private void backPropIH() {
        for (int i = 0; i < numHidden; i++) {
            for (int k = 0; k < numInputs; k++) {
                double x = 1 - (hiddenVal[i] * hiddenVal[i]);
                x = x * weightsHO[i] * patternError * learnRateIH;
              x = x * trainInputs[k];
                double weightChange = x;
                weightsIH[k][i] = weightsIH[k][i] - weightChange;
            }
        }
    }

    private double relu(double input) {
        return Math.max(0, input);
    }

    public void displayResults() {
        for (int i = 0; i < numOutputs; i++) {
            patternNumber = i;
            calculatePredictions();
            System.out.println("pat = " + (patternNumber + 1) + " actual = " + trainOutput[patternNumber] + " neural model = " + outputPrediction);
        }
    }

    private void calculateEpochError() {
        RMSerror = 0.0;
        for (int i = 0; i < numOutputs; i++) {
            patternNumber = i;
            calculatePredictions();
            RMSerror = RMSerror + (patternError * patternError);
        }
        RMSerror = RMSerror / numOutputs;
        RMSerror = java.lang.Math.sqrt(RMSerror);
    }
}

