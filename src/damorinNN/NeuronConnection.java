package damorinNN;

import java.util.Random;

/**
 * Represents a connection between two {@link Neuron}s. The from indicates the input neuron, and the to neuron indicates
 * where the input is going.
 *
 * @author Damien Anderson (Damorin)
 *         20/06/2016
 */
public class NeuronConnection {

    private Double weight;

    private Neuron from;

    private Neuron to;

    public NeuronConnection(Neuron from, Neuron to, Random rng) {
        this(from, to, rng, false);
    }

    public NeuronConnection(Neuron from, Neuron to, Random rng, boolean isBias) {
        this.from = from;
        this.to = to;

        if (!isBias) {
            weight = rng.nextDouble() * 2 - 1;
        } else {
            weight = -1.0;
        }
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.weight = weight;
    }

    public Neuron getFromNeuron() {
        return from;
    }

    public Neuron getToNeuron() {
        return to;
    }
}
