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
        inputLayer = new NeuronLayer(numOfActions, rng, null, null);
        bias = new Neuron(rng);
        hiddenLayers = new ArrayList<>();
        for (int i = 0; i < numOfHiddenLayers; i++) {
            if (i > 0) {
                hiddenLayers.add(new NeuronLayer(neuronsPerHiddenLayer, rng, inputLayer, bias));
            }
            hiddenLayers.add(new NeuronLayer(neuronsPerHiddenLayer, rng, hiddenLayers.get(i-1), bias));
        }
        outputLayer = new NeuronLayer(numOfActions, rng, hiddenLayers.get(hiddenLayers.size()-1), bias);
    }

    public List<Double> update(List<Double> inputs) {
        return null;
    }

}
