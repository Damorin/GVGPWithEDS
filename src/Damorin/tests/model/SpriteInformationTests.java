package Damorin.tests.model;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.hamcrest.CoreMatchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Mockito.mock;

import org.junit.Before;
import org.junit.Test;

import Damorin.model.SpriteInformation;
import Damorin.model.impl.PositionImpl;
import Damorin.model.impl.SpriteInformationImpl;
import core.game.Observation;

/**
 * Tests for checking the functionality of the {@link SpriteInformationImpl}
 * class.
 * 
 * @author Damien Anderson (Damorin)
 *
 */
public class SpriteInformationTests {

	private SpriteInformation sprite;
	private Observation observation;
	private PositionImpl position;

	@Before
	public void setUp() {
		observation = mock(Observation.class);
		position = mock(PositionImpl.class);
	}

	@Test
	public void testNullParameters() {
		sprite = new SpriteInformationImpl(null);
		assertThat("Test Construction", sprite, is(notNullValue()));
	}

	@Test
	public void testCorrectObservation() {
		sprite = new SpriteInformationImpl(observation);
		assertThat("Test Construction", sprite, is(notNullValue()));
	}

	@Test
	public void testSecondConstructorNull() {
		sprite = new SpriteInformationImpl(null, null);
		assertThat("Test Construction", sprite, is(notNullValue()));
	}

	@Test
	public void testSecondConstructorNullPosition() {
		sprite = new SpriteInformationImpl(null, observation);
		assertThat("Test Construction", sprite, is(notNullValue()));
	}

	@Test
	public void testSecondConstructorNullObservation() {
		sprite = new SpriteInformationImpl(position, null);
		assertThat("Test Construction", sprite, is(notNullValue()));
	}

	@Test
	public void testSecondConstructorCorrect() {
		sprite = new SpriteInformationImpl(position, observation);
		assertThat("Test Construction", sprite, is(notNullValue()));
	}

	@Test
	public void testGetObservationFirstConst() {
		sprite = new SpriteInformationImpl(observation);
		assertThat("Test Get Observation", sprite.getObservation(),
				is(observation));
	}

	@Test
	public void testGetObservationSecondConst() {
		sprite = new SpriteInformationImpl(position, observation);
		assertThat("Test Get Observation", sprite.getObservation(),
				is(observation));
	}

	@Test
	public void testGetPositionSecondConst() {
		sprite = new SpriteInformationImpl(position, observation);
		assertThat("Test Get Position", sprite.getPosition(), is(position));
	}

	@Test
	public void testGetPositionFirstConst() {
		sprite = new SpriteInformationImpl(observation);
		assertThat("Test Get Position", sprite.getPosition(), is(nullValue()));
	}

	@Test
	public void testGoalValidityFalse() {
		sprite = new SpriteInformationImpl(position, observation);
		assertThat("Is this goal valid", sprite.isBeneficialGoal(), is(false));
	}

	@Test
	public void testGoalValidityTrue() {
		sprite = new SpriteInformationImpl(position, observation);
		sprite.setIsBeneficialSprite(true);
		assertThat("Is this goal valid", sprite.isBeneficialGoal(), is(true));
	}
}
