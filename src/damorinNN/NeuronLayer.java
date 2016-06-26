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
public class NeuronLayer {

    private List<Neuron> neurons;

    public NeuronLayer(int numOfNeurons, Random rng) {
        this(numOfNeurons, rng, null, null);
    }

    public NeuronLayer(int numOfNeurons, Random rng, NeuronLayer prevLayer, Neuron bias) {
        neurons = new ArrayList<>(numOfNeurons);
        if (prevLayer != null) {
            for (int i = 0; i < numOfNeurons; i++) {
                Neuron neuron = new Neuron(rng);
                neuron.addInConnections(prevLayer.neurons);
                neuron.addBiasConnection(bias);
                neurons.add(neuron);
            }
        } else {
            for (int i = 0; i < numOfNeurons; i++) {
                Neuron neuron = new Neuron(rng);
                neurons.add(neuron);
            }
        }
    }

    public int size() {
        return neurons.size();
    }

    public List<Double> run(List<Double> inputs) {
        List<Double> outputs = new ArrayList<>();
        for (int i = 0; i < inputs.size(); i++) {
            outputs.add(neurons.get(i).activation(inputs));
        }
        return outputs;
    }
}