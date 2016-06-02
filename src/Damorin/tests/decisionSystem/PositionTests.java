package damorin.tests.decisionSystem;

import static org.hamcrest.CoreMatchers.is;
import static org.hamcrest.CoreMatchers.notNullValue;
import static org.junit.Assert.assertThat;

import org.junit.Before;
import org.junit.Test;

import damorin.model.Position;
import damorin.model.impl.PositionImpl;

/**
 * Tests to ensure that the {@link Position} class is functioning correctly.
 * 
 * @author Damien Anderson (damorin)
 *
 */
public class PositionTests {

	private static final int Y_COORDINATE = 13;
	private static final int X_COORDINATE = 12;
	private Position position;

	@Before
	public void setUp() {
		position = new PositionImpl(X_COORDINATE, Y_COORDINATE);
	}

	@Test
	public void testConstruction() {
		assertThat("Test Construction", position, is(notNullValue()));
	}

	@Test
	public void testGetX() {
		assertThat("Test Construction", position.getX(), is(X_COORDINATE));
	}

	@Test
	public void testGetY() {
		assertThat("Test Construction", position.getY(), is(Y_COORDINATE));
	}

}
