package damorinWithImprovedEDS;

import core.game.StateObservation;
import core.player.AbstractPlayer;
import damorinWithImprovedEDS.arbitrator.CentralArbitrator;
import damorinWithImprovedEDS.arbitrator.CentralArbitratorImpl;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;

public class Agent extends AbstractPlayer {
	
	private CentralArbitrator centralArbitrator;
	
	public Agent() {
		centralArbitrator = new CentralArbitratorImpl();
	}
	
	@Override
	public ACTIONS act(StateObservation stateObs, ElapsedCpuTimer elapsedTimer) {
		centralArbitrator.analyse(stateObs, elapsedTimer);
		return ACTIONS.ACTION_RIGHT;
	}

}
