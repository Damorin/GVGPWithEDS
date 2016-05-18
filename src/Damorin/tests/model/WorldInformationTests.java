package Damorin.tests.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import tools.ElapsedCpuTimer;
import Damorin.model.WorldInformation;
import Damorin.model.impl.WorldInformationImpl;
import core.game.Observation;
import core.game.StateObservation;

/**
 * Tests to ensure that the functionality of the {@link WorldInformation} class
 * is correct.
 * 
 * @author Damien Anderson (Damorin)
 *
 */
public class WorldInformationTests {

	private WorldInformation worldInformation;
	private StateObservation stateObs;
	private ElapsedCpuTimer elapsedTimer;
	private Observation goal;

	@Before
	public void setUp() {
		stateObs = mock(StateObservation.class);
		elapsedTimer = mock(ElapsedCpuTimer.class);
		goal = mock(Observation.class);
	}

	@Test
	public void testNullParameters() {
		worldInformation = new WorldInformationImpl(null, null);
		assertThat("Test Construction", worldInformation, is(notNullValue()));
	}

	@Test
	public void testNullStateObservation() {
		worldInformation = new WorldInformationImpl(null, elapsedTimer);
		assertThat("Test Construction", worldInformation, is(notNullValue()));
	}

	@Test
	public void testNullTimer() {
		worldInformation = new WorldInformationImpl(stateObs, null);
		assertThat("Test Construction", worldInformation, is(notNullValue()));
	}

	@Test
	public void testCorrectConstruction() {
		worldInformation = new WorldInformationImpl(stateObs, elapsedTimer);
		assertThat("Test Construction", worldInformation, is(notNullValue()));
	}

	@Test
	public void testSetGoal() {
		worldInformation = new WorldInformationImpl(stateObs, elapsedTimer);
		worldInformation.setGoal(goal);
		assertThat("Test Set Goal", worldInformation.getGoal(), is(goal));
	}

	@Test
	public void testGetGoal() {
		worldInformation = new WorldInformationImpl(stateObs, elapsedTimer);
		worldInformation.setGoal(goal);
		assertThat("Test Get Goal", worldInformation.getGoal(), is(goal));
	}

	@Test
	public void testGoalHasNotBeenSet() {
		worldInformation = new WorldInformationImpl(stateObs, elapsedTimer);
		assertThat("Has Goal been set", worldInformation.hasGoalBeenSet(),
				is(false));
	}

	@Test
	public void testGoalHasBeenSet() {
		worldInformation = new WorldInformationImpl(stateObs, elapsedTimer);
		worldInformation.setGoal(goal);
		assertThat("Has Goal been set", worldInformation.hasGoalBeenSet(),
				is(true));
	}
	
	@Test
	public void testInvalidGoal() {
		worldInformation = new WorldInformationImpl(stateObs, elapsedTimer);
		worldInformation.setGoal(goal);
		worldInformation.setGoalValidity(false);
		assertThat("Is the Goal Valid", worldInformation.hasGoalBeenSet(),
				is(false));
	}

}
