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

    private Double lastWeight;
    private Double weight;
    private Double mutationRate;

    private Neuron from;

    private Neuron to;

    private Random rng;

    public NeuronConnection(Neuron from, Neuron to, Random rng) {
        this(from, to, rng, false);
    }

    public NeuronConnection(Neuron from, Neuron to, Random rng, boolean isBias) {
        this.from = from;
        this.to = to;
        this.rng = rng;
        this.mutationRate = 0.003;

        if (!isBias) {
            weight = rng.nextDouble() * 2 - 1;
            this.lastWeight = this.weight;
        } else {
            weight = -1.0;
            this.lastWeight = this.weight;
        }
    }

    public Double getWeight() {
        return weight;
    }

    public void setWeight(Double weight) {
        this.lastWeight = this.weight;
        this.weight = weight;
    }

    public Neuron getFromNeuron() {
        return from;
    }

    public Neuron getToNeuron() {
        return to;
    }

    public void mutateWeight() {
        if (rng.nextDouble() <= mutationRate) {
            System.out.println("Mutation");
            this.lastWeight = this.weight;
            this.weight += -0.2 + (0.2 - -0.2) * rng.nextDouble();
        }
    }

    public void revertWeight() {
        this.weight = lastWeight;
    }
}
