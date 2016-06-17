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

    private List<Double> inputs;
    private List<Double> weights;
    private Random rng;

    public Neuron(int numOfInputs) {
        inputs = new ArrayList<>(numOfInputs + 1);
        weights = new ArrayList<>(numOfInputs + 1);
        rng = new Random();

        for (int i = 0; i <= numOfInputs; i++) {
            weights.add(rng.nextDouble() * 2 - 1);
        }
    }

    private void activation() {
        Double total = 0.0;
        for (int index = 0; index < inputs.size(); index++) {
            total += inputs.get(index) * weights.get(index);
        }
    }

}
