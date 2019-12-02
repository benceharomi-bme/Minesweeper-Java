import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** The test class of the LeaderBoard class
 * @author Bence
 *
 */
public class LeaderBoardTest {

	LeaderBoard lb;
	
	@Before
	public void setUp() {
		lb = new LeaderBoard();
		lb.addPlayer("Player", 101);
	}
	
	/** Test the getName function
	 */
	@Test
	public void testGetName() {
		String actual = lb.getName(0);
		Assert.assertEquals("Player", actual);
	}
	
	
	/** Tests the getTime function 
	 */
	@Test
	public void testGetTime() {
		int actual = lb.getTime(0);
		Assert.assertEquals(101, actual);
	}

	/** Tests the addPlayer function
	 */
	@Test
	public void testAddPlayer() {
		Assert.assertEquals(1, lb.getSize());
		lb.addPlayer("Player2", 100);
		Assert.assertEquals(2, lb.getSize());
	}
}
