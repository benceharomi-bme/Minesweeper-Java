import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/** The test class of the Table class
 * @author Bence
 *
 */
public class TableTest {

	Table table;
	
	@Before
	public void setUp() {
		table = new Table(10,10);
	}
	
	/** Tests the getNumOfMines function
	 */
	@Test
	public void testGetNumOfMines() {
		table.setMines(10);
		int actual = table.getNumOfMines();
		Assert.assertEquals(10, actual);
	}
	
	/** Tests the getNumOfMarked function
	 */
	@Test
	public void testGetNumOfMarked() {
		table.mark(0, 0);
		int actual = table.getNumOfMarked();
		Assert.assertEquals(1, actual);
	}
	
	/** Tests the discover function
	 */
	@Test
	public void testDiscover() {
		int actual = table.discover(0, 0);
		Assert.assertEquals(0, actual);
	}
	
	/** Tests the isCovered function
	 */
	@Test
	public void testIsCovered() {
		boolean actual = table.isCovered(0, 0);
		Assert.assertEquals(true, actual);
	}

}
