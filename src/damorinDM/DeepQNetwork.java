package damorinDM;

import core.game.StateObservation;
import ontology.Types;
import tools.ElapsedCpuTimer;

/**
 * Add a description of the class here.
 *
 * @author Damien Anderson (Damorin)
 *         10/06/2016
 */
public class DeepQNetwork {

    private ElapsedCpuTimer timer;

    // Network Architecture

    // Hyperparamaters

    // Eventually create a constructor for taking in some values, nodes/layers etc

    // Step 1. Take in an input, go through a single node, output an action.
    public Types.ACTIONS calculate(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        this.timer = elapsedTimer;
        return null;
    }
}
