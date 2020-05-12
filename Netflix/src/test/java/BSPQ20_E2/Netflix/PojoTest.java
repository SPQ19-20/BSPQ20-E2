package BSPQ20_E2.Netflix;

import static org.junit.Assert.assertEquals;

import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.*;
import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;

/**
 * Class containing the tests of the classes of Film and User
 * 
 * @author Diego Rojo, Jorge El Busto
 *
 */
public class PojoTest {

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	/**
	 * @param u user created to check the methods
	 * @param f film created to check the methods
	 *
	 */
	private static User u;
	private static User u2;
	private static Film f;

	/**
	 * Method to put the values of the film and user to null
	 *
	 */
	@BeforeClass
	public static void setUp() {
		u = new User(null, null, null, null, 0);
		u2 = new User("busto2", "Jorge", "El Busto", "pass", 400);
		f = new Film(null, null, null, null, 1960, 0, null, null);
	}

	/**
	 * Method to check the setCode and getCode methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkUserCode() {
		u.setCode("U-01");
		assertEquals("U-01", u.getCode());
	}

	/**
	 * Method to check the setName and getName methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkUserName() {
		u.setName("Diego");
		assertEquals("Diego", u.getName());
	}

	/**
	 * Method to check the setSurname and getSurname methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkUserSurname() {
		u.setSurname("Rojo");
		assertEquals("Rojo", u.getSurname());
	}

	/**
	 * Method to check the setPassword and getPassword methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkUserPassword() {
		u.setPassword("123456");
		assertEquals("123456", u.getPassword());
	}

	/**
	 * Method to check the setSalary and getSalary methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkUserSalary() {
		u.setSalary(25);
		assertEquals(25, u.getSalary(), 0.01);
	}

	/**
	 * Method to check the setId and getId methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkFilmId() {
		f.setId("F-01");
		assertEquals("F-01", f.getId());
	}

	/**
	 * Method to check the setTitle and getTitle methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkFilmTitle() {
		f.setTitle("Thor");
		assertEquals("Thor", f.getTitle());
	}

	/**
	 * Method to check the setGenre and getGenre methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkFilmGenre() {
		f.setGenre("Action");
		assertEquals("Action", f.getGenre());
	}

	/**
	 * Method to check the setDirector and getDirector methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkFilmDirector() {
		f.setDirector("Kenneth Branagh");
		assertEquals("Kenneth Branagh", f.getDirector());
	}

	/**
	 * Method to check the setYear and getYear methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkFilmYear() {
		f.setYear(2011);
		assertEquals(2011, f.getYear());
	}

	/**
	 * Method to check the setPrice and getPrice methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkFilmPrice() {
		f.setPrice(4.5f);
		assertEquals(4.5, f.getPrice(), 0.01);
	}

	/**
	 * Method to check the setURL and getURL methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkFilmURL() {
		f.setUrl("www.thorfilm.com");
		assertEquals("www.thorfilm.com", f.getUrl());
	}

	/**
	 * Method to check the setTrailer and getTrailer methods
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkTrailer() {
		f.setTrailer("www.youtube.com");
		assertEquals("www.youtube.com", f.getTrailer());
	}

	/**
	 * Method to proof that toString methods for user already work
	 */
	@Test
	@PerfTest(invocations = 10)
	public void toStringTest() {
		assertEquals("User busto2: [Jorge El Busto, Salary: 400.0$ ]", u2.toString());
	}

}
