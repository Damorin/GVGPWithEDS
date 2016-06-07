package damorinDM;

import core.game.Observation;
import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;

import java.util.List;

/**
 * Basis for attempts to develop an agent for the GVGAI competition using some of the techniques
 * developed in the Deepmind paper titled "Human Level Control through Deep Reinforcement Learning" in February 2015.
 *
 * @author Damien Anderson (damorin)
 */
public class Agent extends AbstractPlayer {

    private NeuralNetwork network;

    public Agent(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        network = new NeuralNetwork();
    }

    @Override
    public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {

        return network.computeAction(stateObs, elapsedTimer);
    }

}
