package damorinNN;

import core.game.StateObservation;
import core.player.AbstractPlayer;
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
    private NeuralNetwork copy;

    public Agent(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        nn = new NeuralNetwork(2, 5, stateObs.getAvailableActions().size(), new Random());
    }

    @Override
    public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        List<Double> inputs = new ArrayList<>();
        inputs.add(stateObs.getGameScore());
        inputs.add(stateObs.getAvatarPosition().x);
        inputs.add(stateObs.getAvatarPosition().y);

        List<Double> outputs = nn.run(inputs);
        return null;
    }
}