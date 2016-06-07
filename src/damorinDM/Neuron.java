package damorinDM;

import core.game.Observation;
import ontology.Types;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * A neuron inside the {@link NeuralNetwork}.
 *
 * @author Damien Anderson (Damorin)
 *         07/06/2016
 */
public class Neuron {

    private Observation observation;

    private Types.ACTIONS action;

    private List<Neuron> inputs;

    private Random rng = new Random();

    private boolean fired = false;

    private double score = 0.0;
    private float weight = 0.0f;

    // Used for input layer
    public Neuron(Observation singleObservation) {
        this.inputs = new ArrayList<>();
        this.observation = singleObservation;
        this.weight = rng.nextFloat();
    }

    // Used for additional input neuron
    public Neuron(double score) {
        inputs = new ArrayList<>();
        this.score = score;
        this.weight = rng.nextFloat();
    }

    // Used for hidden layers
    public Neuron() {
        inputs = new ArrayList<>();
        this.weight = rng.nextFloat();
    }

    // Used for Output layer
    public Neuron(Types.ACTIONS action) {
        inputs = new ArrayList<>();
        this.action = action;
        this.weight = rng.nextFloat();
    }

    public void connect(Neuron ... neurons) {
        for(Neuron neuron : neurons) {
            inputs.add(neuron);
        }
    }

    public void setWeight(float weight) {
        this.weight = weight;
    }

    public double fire() {
        float totalWeight = 0.0f;
        if(action == null) {

            if(!inputs.isEmpty()) {
                for(Neuron input : inputs) {
                    input.fire();
                    return input.weight;
                }
            }
        }
        return totalWeight;
    }
}
