package damorinDM;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;


/**
 * Basis for attempts to develop an agent for the GVGAI competition using some of the techniques
 * developed in the Deepmind paper titled "Human Level Control through Deep Reinforcement Learning" in February 2015.
 *
 * @author Damien Anderson (damorin)
 */
public class Agent extends AbstractPlayer {

    private DeepQNetwork dqn;

    public Agent(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        dqn = new DeepQNetwork();
    }

    @Override
    public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
        return null;
    }

}
