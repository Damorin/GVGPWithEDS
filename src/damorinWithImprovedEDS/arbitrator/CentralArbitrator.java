package damorinWithImprovedEDS.arbitrator;

import core.game.StateObservation;
import ontology.Types.ACTIONS;
import tools.ElapsedCpuTimer;

public interface CentralArbitrator {

	ACTIONS analyse(StateObservation stateObs, ElapsedCpuTimer elapsedTimer);
	
}
