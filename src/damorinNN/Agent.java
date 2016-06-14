package damorinNN;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 * Put a description of the purpose of this file here.
 *
 * @author Damien Anderson (Damorin)
 *         01/06/2016.
 */
public class Agent extends AbstractPlayer {

    private NeuralNetwork nn;
    private List<ACTIONS> actions;

    public Agent(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        nn = new NeuralNetwork(1, 2, 5, stateObs.getAvailableActions(true).size(), stateObs);
        actions = new ArrayList<>();
        actions.addAll(stateObs.getAvailableActions(true));
    }

    @Override
    public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        Random rng = new Random();
        return actions.get(nn.calculateAction());

    }
}