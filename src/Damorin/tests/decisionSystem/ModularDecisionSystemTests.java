package damorin.tests.decisionSystem;

import static ontology.Types.ACTIONS.ACTION_NIL;
import static ontology.Types.ACTIONS.ACTION_UP;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.ArrayList;

import ontology.Types;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import tools.ElapsedCpuTimer;
import tools.Vector2d;
import damorin.Agent;
import damorin.decisionSystem.CentralArbitrator;
import damorin.decisionSystem.ModularDecisionSystem;
import core.game.Observation;
import core.game.StateObservation;

/**
 * Tests to ensure that the {@link ModularDecisionSystem} is working correctly.
 * 
 * @author Damien Anderson (damorin)
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Agent.class)
public class ModularDecisionSystemTests {

	private CentralArbitrator decisionSystem;
	private ElapsedCpuTimer elapsedTimer;
	private StateObservation stateObs;

	@Before
	public void setUp() {
		mockStatic(damorin.Agent.class);
		stateObs = mock(StateObservation.class);
		elapsedTimer = mock(ElapsedCpuTimer.class);

		ArrayList<Observation>[][] observationGrid = new ArrayList[1][1];
		Observation observation = mock(Observation.class);
		observation.obsID = 1;
		observation.position = new Vector2d(1, 1);

		observationGrid[0][0] = new ArrayList<Observation>();
		observationGrid[0][0].add(observation);
		when(stateObs.getObservationGrid()).thenReturn(observationGrid);
		when(stateObs.getAvatarPosition()).thenReturn(new Vector2d(2, 2));

		createActions();

	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullParameters() {
		decisionSystem = new ModularDecisionSystem(null, null);
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullStateObs() {
		decisionSystem = new ModularDecisionSystem(null, elapsedTimer);
	}

	@Test
	public void testNullTimer() {
		decisionSystem = new ModularDecisionSystem(stateObs, null);
	}

	@Test
	public void testCorrectParameters() {
		decisionSystem = new ModularDecisionSystem(stateObs, elapsedTimer);
	}

	@Test
	public void testSelectAction() {
		decisionSystem = new ModularDecisionSystem(stateObs, elapsedTimer);

		when(stateObs.copy()).thenReturn(stateObs);

		int action = decisionSystem.selectAction(elapsedTimer);
		assertThat(action, is(0));
	}

	@Test
	public void testSelectBestAction() {
		decisionSystem = new ModularDecisionSystem(stateObs, elapsedTimer);

		when(stateObs.copy()).thenReturn(stateObs);

		int action = decisionSystem.selectAction(new ElapsedCpuTimer());
		assertThat(action, is(0));
	}

	private void createActions() {
		Types.ACTIONS[] actionsArray = new Types.ACTIONS[2];
		actionsArray[0] = ACTION_NIL;
		actionsArray[1] = ACTION_UP;

		ArrayList<Types.ACTIONS> actions = new ArrayList<Types.ACTIONS>();
		actions.add(ACTION_NIL);
		actions.add(ACTION_UP);

		when(stateObs.getAvailableActions()).thenReturn(actions);
		Agent.availableActions = actionsArray;
	}
}
