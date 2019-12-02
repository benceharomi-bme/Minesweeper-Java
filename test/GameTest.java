import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** The test class of the Game class
 * @author Bence
 *
 */
public class GameTest {
	
	Game game;
	
	@Before
	public void setUp() {
		game = new Game(10,10,0);
	}
	
	/** Tests the discover function
	 */
	@Test
	public void testDiscover() {
		boolean actual = game.discover(0,0);
		Assert.assertEquals(true, actual);
	}
	
	/** Tests the isCovered function
	 */
	@Test
	public void testIsCovered() {
		boolean actual = game.isCovered(0,0);
		Assert.assertEquals(true, actual);
	}
	
	/** Tests the isMarked funnction
	 */
	@Test
	public void testIsMarked() {
		game.mark(0,0);
		boolean actual = game.isMarked(0,0);
		Assert.assertEquals(true, actual);
	}

}
