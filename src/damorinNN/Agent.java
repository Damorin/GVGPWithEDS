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

    private NeuralNetwork currentNN;
    private NeuralNetwork bestNN;
    private List<Double> lastChromosome; // Get rid of
    private Double bestNNScore;

    private int noOfHiddenLayers;
    private int neuronsPerLayer;

    public Agent(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        noOfHiddenLayers = 3;
        neuronsPerLayer = 5;
        bestNNScore = Double.MIN_VALUE;
        currentNN = new NeuralNetwork(noOfHiddenLayers, neuronsPerLayer, stateObs.getAvailableActions().size(), new Random());
        lastChromosome = new ArrayList<>();
        lastChromosome.addAll(currentNN.getWeights());
    }

    @Override
    public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        List<Double> inputs = new ArrayList<>();
        inputs.add(stateObs.getGameScore());
        inputs.add(stateObs.getAvatarPosition().x);
        inputs.add(stateObs.getAvatarPosition().y);

        List<Double> outputs = currentNN.run(inputs);

        int bestAction = -1;
        double bestScore = -Double.MAX_VALUE;
        for (int i = 0; i < outputs.size(); i++) {
            if (outputs.get(i) > bestScore) {
                bestAction = i;
                bestScore = outputs.get(i);
            }
        }

        if (bestAction == -1) {
            bestAction = (int) (Math.random() * stateObs.getAvailableActions().size());
        }

        StateObservation copy = stateObs.copy();
        copy.advance(stateObs.getAvailableActions().get(bestAction));

        if (copy.isGameOver())  {
            if (copy.getGameScore() > bestNNScore) {
                bestNNScore = copy.getGameScore(); // Variable to hold best score ever
                lastChromosome.clear();
                lastChromosome.addAll(currentNN.getWeights()); // Store the full network and its score
                currentNN.mutateWeights();
                System.out.println("New champion found!");
            } else {
                currentNN.revertWeights();
                currentNN.mutateWeights();
            }
        }

        return stateObs.getAvailableActions().get(bestAction);
    }
}