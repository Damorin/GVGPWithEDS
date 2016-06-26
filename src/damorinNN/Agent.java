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
    private List<Double> lastChromosome;
    private Double lastScore;

    private int noOfHiddenLayers;
    private int neuronsPerLayer;

    public Agent(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        noOfHiddenLayers = 2;
        neuronsPerLayer = 5;
        lastScore = Double.MIN_VALUE;
        nn = new NeuralNetwork(noOfHiddenLayers, neuronsPerLayer, stateObs.getAvailableActions().size(), new Random());
        lastChromosome = new ArrayList<>();
        lastChromosome.addAll(nn.getWeights());
    }

    @Override
    public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        List<Double> inputs = new ArrayList<>();
        inputs.add(stateObs.getGameScore());
        inputs.add(stateObs.getAvatarPosition().x);
        inputs.add(stateObs.getAvatarPosition().y);

        List<Double> outputs = nn.run(inputs);

        StateObservation copy = stateObs.copy();

        int bestAction = -1;
        double bestScore = Double.MIN_VALUE;
        for (int i = 0; i < outputs.size(); i++) {
            if (outputs.get(i) > bestScore) {
                bestAction = i;
                bestScore = outputs.get(i);
            }
        }

        if (bestAction == -1) {
            bestAction = (int) (Math.random() * stateObs.getAvailableActions().size());
        }

        copy.advance(stateObs.getAvailableActions().get(bestAction));

        if (copy.isGameOver())  {
            if (copy.getGameScore() > lastScore) {
                lastScore = copy.getGameScore();
                lastChromosome.clear();
                lastChromosome.addAll(nn.getWeights());
                nn.mutateWeights();
                System.out.println("Improvement, new network in trial");
            }
            nn.mutateWeights();
        }

        return stateObs.getAvailableActions().get(bestAction);
    }
}