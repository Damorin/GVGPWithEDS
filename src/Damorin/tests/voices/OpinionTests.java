package Damorin.tests.voices;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Test;

import Damorin.voices.Opinion;

/**
 * These tests ensure that the {@link Opinion} class is working correctly.
 * 
 * @author Damien Anderson (Damorin)
 *
 */
public class OpinionTests {

	private static final double NoRMAL_VOLUME = 5.0;
	private Opinion opinion;

	@Test
	public void testCorrectParameters() {
		opinion = new Opinion(1, NoRMAL_VOLUME);
		assertThat("Test Construction", opinion, is(notNullValue()));
	}

	@Test
	public void testIncorrectAction() {
		opinion = new Opinion(-1, NoRMAL_VOLUME);
		assertThat(opinion.getAction(), is(-1));
	}

	@Test
	public void testGetAction() {
		opinion = new Opinion(1, NoRMAL_VOLUME);
		assertThat(opinion.getAction(), is(1));
	}

	@Test
	public void testGetUrgency() {
		opinion = new Opinion(1, NoRMAL_VOLUME);
		assertThat(opinion.getUrgency(), is(NoRMAL_VOLUME));
	}

}
