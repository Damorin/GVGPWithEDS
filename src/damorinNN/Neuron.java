package damorinNN;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Represents an individual Neuron in the neural network.
 *
 * @author Damien Anderson (Damorin)
 *         16/06/2016
 */
public class Neuron {

    private NeuronConnection biasConnection;

    private List<NeuronConnection> inputConnections;

    private Random rng;

    public Neuron(Random rng) {
        inputConnections = new ArrayList<>();
        this.rng = rng;
    }

    public Double activation(List<Double> inputs, int neuronIndex) {
        Double total = 0.0;
        if (inputConnections.isEmpty()) {
            return inputs.get(neuronIndex);
        }
        for (int i = 0; i < inputs.size(); i++) {
            total += inputs.get(i) * inputConnections.get(i).getWeight();
        }
        // Add RELU method to the returned total
        return total;
    }

    public void addInConnections(List<Neuron> inputs) {
        for (Neuron input : inputs) {
            inputConnections.add(new NeuronConnection(input, this, rng));
        }
    }

    public void addInConnection(Neuron input) {
        inputConnections.add(new NeuronConnection(input, this, rng));
    }

    public void addBiasConnection(Neuron bias) {
        biasConnection = new NeuronConnection(bias, this, rng, true);
    }

    public List<Double> getWeights() {
        List<Double> toReturn = new ArrayList<>();
        for(NeuronConnection connection : inputConnections) {
            toReturn.add(connection.getWeight());
        }
        return toReturn;
    }

    public void mutateWeights() {
        for(NeuronConnection connection : inputConnections) {
            connection.mutateWeight();
        }
    }
}
