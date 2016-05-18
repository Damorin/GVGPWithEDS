package Damorin.tests;

import static ontology.Types.ACTIONS.ACTION_NIL;
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
import org.junit.runner.RunWith;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

import tools.ElapsedCpuTimer;
import tools.Vector2d;
import Damorin.Agent;
import core.game.Observation;
import core.game.StateObservation;

/**
 * A Test suite for ensuring that the {@link Agent} performs as expected.
 * 
 * @author Damien Anderson (Damorin)
 *
 */
@RunWith(PowerMockRunner.class)
@PrepareForTest(Agent.class)
public class AgentTests {

	private static final int TIMEOUT = 40;
	private StateObservation stateObs;
	private ElapsedCpuTimer timer;
	private Agent agent;

	@Before
	public void setUp() {
		mockStatic(Agent.class);
		stateObs = mock(StateObservation.class);
		timer = mock(ElapsedCpuTimer.class);

		Types.ACTIONS[] actionsArray = new Types.ACTIONS[1];
		actionsArray[0] = ACTION_NIL;

		ArrayList<Types.ACTIONS> actions = new ArrayList<Types.ACTIONS>();
		actions.add(ACTIONS.ACTION_NIL);

		when(stateObs.getAvailableActions()).thenReturn(actions);
		when(stateObs.copy()).thenReturn(stateObs);
		Agent.availableActions = actionsArray;

	}

	@Test(expected = NullPointerException.class)
	public void testWithNullParameters() {
		agent = new Agent(null, null);
	}

	@Test(expected = NullPointerException.class)
	public void testWithNullStateObs() {
		agent = new Agent(null, timer);
	}

	@Test
	public void testWithNullTimer() {
		agent = new Agent(stateObs, null);
		assertThat("Test Construction with Correct Parameters", agent,
				is(notNullValue()));
	}

	@Test
	public void testWithCorrectParameters() {
		agent = new Agent(stateObs, timer);
		assertThat("Test Construction with Correct Parameters", agent,
				is(notNullValue()));
	}

	@Test
	public void testActWithinTimeLimit() {
		@SuppressWarnings("unchecked")
		ArrayList<Observation>[][] observationGrid = new ArrayList[1][1];
		Observation observation = mock(Observation.class);
		observation.obsID = 1;
		observation.position = new Vector2d(1, 1);

		observationGrid[0][0] = new ArrayList<Observation>();
		observationGrid[0][0].add(observation);
		when(stateObs.getObservationGrid()).thenReturn(observationGrid);
		when(stateObs.getAvatarPosition()).thenReturn(new Vector2d(2, 2));
		ElapsedCpuTimer timerToCheck = new ElapsedCpuTimer();
		timerToCheck.setMaxTimeMillis(TIMEOUT);

		agent = new Agent(stateObs, timerToCheck);

		agent.act(stateObs, timerToCheck);

		System.out.println(timerToCheck.elapsedMillis());

		assertThat("Test computational time is within 40ms",
				timerToCheck.elapsedMillis() > TIMEOUT, is(false));
	}
}
