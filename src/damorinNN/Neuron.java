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
public class Neuron {

    private NeuronConnection biasConnection;

    private List<NeuronConnection> inputConnections;

    public Neuron(Random rng) {
        biasConnection = new NeuronConnection(new Neuron(rng), this, rng);
        inputConnections = new ArrayList<>();
    }

    public Double activation() {
        Double total = 0.0;
//        for (int index = 0; index < inputs.size(); index++) {
//            total += inputs.get(index). * weights.get(index);
//        }
        return total;
    }

    public void addInConnection(List<Neuron> inputs) {

    }

    public void addBiasConnection(Neuron bias) {

    }

}
