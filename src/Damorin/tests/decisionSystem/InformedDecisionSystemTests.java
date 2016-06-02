package damorin.tests.decisionSystem;

import static ontology.Types.ACTIONS.ACTION_NIL;
import static ontology.Types.ACTIONS.ACTION_UP;
import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;
import static org.powermock.api.mockito.PowerMockito.mockStatic;

import java.util.ArrayList;

import ontology.Types;
import ontology.Types.ACTIONS;

import org.junit.Before;
import org.junit.Test;

import tools.ElapsedCpuTimer;
import tools.Vector2d;
import damorin.Agent;
import damorin.decisionSystem.CentralArbitrator;
import damorin.decisionSystem.InformedDecisionSystem;
import core.game.Observation;
import core.game.StateObservation;

/**
 * The tests to ensure that the {@link InformedDecisionSystem} is functioning
 * correctly.
 * 
 * @author Damien Anderson (damorin)
 *
 */
public class InformedDecisionSystemTests {

	private static final double HUGE_POSITIVE = 10000.0;
	private static final double HUGE_NEGATIVE = -10000.0;
	private CentralArbitrator decisionSystem;
	private ElapsedCpuTimer elapsedTimer;

	private StateObservation stateObs;
	private StateObservation goodState;
	private StateObservation badState;

	@Before
	public void setUp() {
		initialiseMocks();

		ArrayList<Observation>[][] observationGrid = new ArrayList[1][1];
		Observation observation = mockObservation();

		observationGrid[0][0] = new ArrayList<Observation>();
		observationGrid[0][0].add(observation);
		when(stateObs.getObservationGrid()).thenReturn(observationGrid);
		when(stateObs.getAvatarPosition()).thenReturn(new Vector2d(2, 2));

		Types.ACTIONS[] actionsArray = new Types.ACTIONS[1];
		actionsArray[0] = ACTION_NIL;

		ArrayList<Types.ACTIONS> actions = new ArrayList<Types.ACTIONS>();
		actions.add(ACTIONS.ACTION_NIL);

		when(stateObs.getAvailableActions()).thenReturn(actions);
		Agent.availableActions = actionsArray;

	}

	private void initialiseMocks() {
		mockStatic(damorin.Agent.class);
		stateObs = mock(StateObservation.class);
		badState = mock(StateObservation.class);
		goodState = mock(StateObservation.class);
		elapsedTimer = mock(ElapsedCpuTimer.class);
	}

	private Observation mockObservation() {
		Observation observation = mock(Observation.class);
		observation.obsID = 1;
		observation.position = new Vector2d(1, 1);
		return observation;
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullParameters() {
		decisionSystem = new InformedDecisionSystem(null, null);
		assertThat("Test Construction", decisionSystem, is(notNullValue()));
	}

	@Test(expected = IllegalArgumentException.class)
	public void testNullStateObs() {
		decisionSystem = new InformedDecisionSystem(null, elapsedTimer);
	}

	@Test
	public void testNullTimer() {
		decisionSystem = new InformedDecisionSystem(stateObs, null);
		assertThat("Test Construction", decisionSystem, is(notNullValue()));
	}

	@Test
	public void testCorrectParameters() {
		decisionSystem = new InformedDecisionSystem(stateObs, elapsedTimer);
		assertThat("Test Construction", decisionSystem, is(notNullValue()));
	}

	@Test
	public void testSelectAction() {
		decisionSystem = new InformedDecisionSystem(stateObs, elapsedTimer);
		int action = decisionSystem.selectAction(elapsedTimer);
		assertThat(action, is(0));
	}

	@Test
	public void testSelectBestAction() {
		decisionSystem = new InformedDecisionSystem(stateObs, elapsedTimer);
		createActions();

		int action = decisionSystem.selectAction(new ElapsedCpuTimer());
		assertThat(action, is(0));
	}

	private void createActions() {
		Types.ACTIONS[] actionsArray = new Types.ACTIONS[2];
		actionsArray[0] = ACTION_UP;
		actionsArray[1] = ACTION_NIL;

		ArrayList<Types.ACTIONS> actions = new ArrayList<Types.ACTIONS>();
		actions.add(ACTION_UP);
		actions.add(ACTION_NIL);

		when(badState.getGameScore()).thenReturn(HUGE_NEGATIVE);
		when(goodState.getGameScore()).thenReturn(HUGE_POSITIVE);

		when(stateObs.getAvailableActions()).thenReturn(actions);
		Agent.availableActions = actionsArray;
	}
}
