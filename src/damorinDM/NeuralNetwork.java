package damorinDM;

import core.game.Observation;
import core.game.StateObservation;
import ontology.Types;
import tools.ElapsedCpuTimer;

import java.util.ArrayList;
import java.util.List;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 * 06/06/2016
 */
public class NeuralNetwork {

    private Neuron input;
    private Neuron oneA;
    private Neuron oneB;
    private Neuron oneC;
    private Neuron oneD;
    private Neuron oneE;
    private Neuron twoA;
    private Neuron twoB;
    private Neuron twoC;
    private Neuron twoD;
    private Neuron twoE;

    public Types.ACTIONS computeAction(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {

        createInputNeurons(stateObs);

        createHiddenLayers();

        createOutputLayers(stateObs);

        connectLayers();

        input.fire();
        return null;
    }

    private void connectLayers() {
        input.connect(oneA, oneB, oneC);

        oneA.connect(twoA, twoB, twoC);
        oneB.connect(twoA, twoB, twoC);
        oneC.connect(twoA, twoB, twoC);
    }

    private void createInputNeurons(StateObservation stateObs) {
        input = new Neuron(stateObs.getGameScore());

//        for(List<Observation>[] observationGrid : stateObs.getObservationGrid()) {
//            for(List<Observation> observationList : observationGrid) {
//                for(Observation observation : observationList) {
//                    neurons.add(new Neuron(observation));
//                }
//            }
//        }
    }

    private void createHiddenLayers() {
        oneA = new Neuron();
        oneB = new Neuron();
        oneC = new Neuron();

//        neurons.add(oneD = new Neuron());
//        neurons.add(oneE = new Neuron());
        twoA = new Neuron();
        twoB = new Neuron();
        twoC = new Neuron();
//        neurons.add(twoD = new Neuron());
//        neurons.add(twoE = new Neuron());

    }

    private void createOutputLayers(StateObservation stateObs) {
        for(Types.ACTIONS action : stateObs.getAvailableActions(false)) {
            Neuron output = new Neuron(action);
            twoA.connect(output);
            twoB.connect(output);
            twoA.connect(output);
//            neurons.add(output);
        }
    }
}
