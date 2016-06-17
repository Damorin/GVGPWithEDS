package damorinNN;

import java.util.ArrayList;
import java.util.List;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         16/06/2016
 */
public class NeuralNetwork {

    private List<NeuronLayer> layers;

    public NeuralNetwork(int numOfHiddenLayers, int neuronsPerHiddenLayer) {
        layers = new ArrayList<>();
        layers.add(new NeuronLayer(26));

        for(int i = 0; i < numOfHiddenLayers; i++) {
            layers.add(new NeuronLayer(neuronsPerHiddenLayer));
        }


    }

    public void update(List<Float> inputs) {

    }

}
