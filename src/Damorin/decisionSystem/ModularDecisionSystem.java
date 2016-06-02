package damorin.decisionSystem;

import java.util.ArrayList;
import java.util.List;

import tools.ElapsedCpuTimer;
import damorin.model.WorldInformation;
import damorin.model.impl.WorldInformationImpl;
import damorin.voices.Opinion;
import damorin.voices.Voice;
import damorin.voices.longRange.AnalysisExplorationVoice;
import damorin.voices.mediumRange.MCTSVoice;
import damorin.voices.shortRange.SurvivalVoice;
import core.game.StateObservation;

/**
 * A {@link CentralArbitrator} which will use {@link Voice}s for deciding the
 * best Action to use. This particular implementation uses three voices. Short,
 * Medium and Long Range voices.
 * 
 * Voices are treated without any particular priority, their {@link Opinion}s
 * are trusted.
 * 
 * @author Damien Anderson (damorin)
 *
 */
public class ModularDecisionSystem implements CentralArbitrator {

	private List<Voice> voices;
	private WorldInformation worldInformation;

	/**
	 * The constructor for initialising an {@link ModularDecisionSystem}
	 * 
	 * @param stateObs
	 *            the {@link StateObservation}
	 * @param elapsedTimer
	 *            the {@link ElapsedCpuTimer}
	 */
	public ModularDecisionSystem(final StateObservation stateObs,
			final ElapsedCpuTimer elapsedTimer) {

		if (stateObs == null) {
			throw new IllegalArgumentException();
		}

		worldInformation = new WorldInformationImpl(stateObs, elapsedTimer);
		initialiseVoices(stateObs);
	}

	private void initialiseVoices(final StateObservation stateObs) {
		voices = new ArrayList<>();
		voices.add(new SurvivalVoice(stateObs));
		voices.add(new AnalysisExplorationVoice(stateObs));
		voices.add(new MCTSVoice(stateObs));
	}

	@Override
	public void update(final StateObservation stateObs) {
		worldInformation.update(stateObs);

		for (Voice voice : voices) {
			voice.update(stateObs);
		}
	}

	@Override
	public int selectAction(final ElapsedCpuTimer elapsedTimer) {
		List<Opinion> opinions = new ArrayList<>();
		for (Voice voice : voices) {
			opinions.add(voice.askOpinion(elapsedTimer, worldInformation));
		}

		worldInformation.reset();

		return determineHighestPriority(opinions);
	}

	private int determineHighestPriority(List<Opinion> opinions) {
		double highestUrgency = -1.0;
		int action = 0;

		for (Opinion opinion : opinions) {
			if (opinion.getUrgency() > highestUrgency) {
				highestUrgency = opinion.getUrgency();
				action = opinion.getAction();
			}
		}
		return action;
	}
}
