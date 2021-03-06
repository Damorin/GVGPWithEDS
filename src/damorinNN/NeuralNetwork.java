package damorinNN;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         16/06/2016
 */
public class NeuralNetwork {

    private NeuronLayer inputLayer;
    private List<NeuronLayer> hiddenLayers;
    private NeuronLayer outputLayer;
    private Neuron bias;

    public NeuralNetwork(int numOfHiddenLayers, int neuronsPerHiddenLayer, int numOfActions, Random rng) {
        inputLayer = new NeuronLayer(numOfActions, rng);
        bias = new Neuron(rng);
        hiddenLayers = new ArrayList<>();
        for (int i = 0; i < numOfHiddenLayers; i++) {
            if (i == 0) {
                hiddenLayers.add(new NeuronLayer(neuronsPerHiddenLayer, rng, inputLayer, bias));
            } else {
                hiddenLayers.add(new NeuronLayer(neuronsPerHiddenLayer, rng, hiddenLayers.get(i - 1), bias));
            }
        }
        outputLayer = new NeuronLayer(numOfActions, rng, hiddenLayers.get(hiddenLayers.size() - 1), bias);
    }

    public List<Double> run(List<Double> inputs) {
        List<Double> outputs = inputLayer.run(inputs);
        for (int i = 0; i < hiddenLayers.size(); i++) {
            outputs = hiddenLayers.get(i).run(outputs);
        }
        return outputLayer.run(outputs);
    }

    public void mutateWeights() {

        for(NeuronLayer layer : hiddenLayers) {
            layer.mutateWeights();
        }
        outputLayer.mutateWeights();

    }

    public List<Double> getWeights() {
        List<Double> toReturn = new ArrayList<>();
        for(NeuronLayer layer : hiddenLayers) {
            toReturn.addAll(layer.getWeights());
        }
        toReturn.addAll(outputLayer.getWeights());
        return toReturn;
    }

    public void revertWeights() {
        for (NeuronLayer layer : hiddenLayers) {
            layer.revertWeights();
        }

        outputLayer.revertWeights();
    }
}
