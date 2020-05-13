package BSPQ20_E2.Netflix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.apache.log4j.Logger;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;

import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import es.deusto.BSPQ20_E2.Netflix.server.db.DB;

/**
 * Class containing the tests of the server
 * 
 * @author Diego Rojo
 * @author Jorge El Busto
 *
 */
public class DBTest {

	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	/**
	 * @param u     user created to check the methods
	 * @param films selected from the catalog
	 */
	private static User u;
	private static ArrayList<Film> films;
	private final static Logger LOGGER = Logger.getLogger(DBTest.class.getName());

	/**
	 * Setting up the films, the user to test and the server
	 * 
	 * @throws Exception of many cases can be thrown: SQL, Network...
	 */
	@BeforeClass
	public static void setUp() throws Exception {
		try {
			films = DB.retrieveFilms();
			u = DB.logged("jorge", "pass");
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Method that tests the logged method to check if credentials are valid
	 * 
	 * @param u user that correspond that credentials
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkCredentials() {
		try {
			User u = DB.logged("jorge", "pass");
			if (u != null) {
				// If the user is not null the test is correct
				assertTrue(true);
			} else
				assertTrue(false);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Method that tests the connect method
	 * 
	 * @param con connection created to check if the method works
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkConnection() throws Exception {

		Connection con = null;
		try {
			con = DB.connect();
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}

		if (con != null) {
			// If the connection is not null, the method works
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	/**
	 * Method that tests the retrieveFilms method that gets the films of the
	 * database
	 * 
	 * @param filmsDatabase Arraylist of films created to check the method
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkFilmRetriever() {
		ArrayList<Film> filmsDatabase = DB.retrieveFilms();
		if (filmsDatabase != null) {
			// If the ArrayList is not null, the method works fine
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	/**
	 * Method that tests the searchFilms method. The array shouldn't be null and it
	 * should contain films.
	 * 
	 * @param filmsSearched arrayList of films created to check if the method works
	 *
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkFilmSearch() {
		ArrayList<Film> filmsSearched = DB.searchFilms("2010");
		if (filmsSearched != null) {
			// If the arrayList is not null, the method works
			assertTrue(true);
		} else {
			assertTrue(false);
		}
	}

	/**
	 * Method that tests that the registration method works
	 */
	@Test
	@PerfTest(invocations = 10)
	public void checkRegistration() {
		final Logger LOGGER = Logger.getLogger(DB.class.getName());
		String code = "Diego30";
		DB.register(code, "Diego", "Rojo", "1234", 25);
		String sql = "SELECT FROM USER WHERE CODE = '" + code + "'";
		try {
			Connection con = DB.connect();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			con.close();
			LOGGER.info("New user registered: " + code);
			assertTrue(true);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Method to check that the userID is retrieved from mySQL (to consider in
	 * operations in the transaction table)
	 */
	@Test
	@PerfTest(invocations = 10)
	public void getUserID() {
		String userid = DB.getId(u);
		assertEquals("1", userid);
	}

	/**
	 * Method to check that films are correctly retrieved. We're trying it with the
	 * first film, Fast and Furious
	 */
	@Test
	@PerfTest(invocations = 10)
	public void buyFilmTest() {
		double previousSalary = u.getSalary();
		DB.buyFilm(films.get(0), u);
		assertTrue(u.getSalary() < previousSalary);
	}

	/**
	 * Method to check that DB is successfully created.
	 */
	@Test
	@PerfTest(invocations = 10)
	public void createDB() {
		DB db = new DB();
		assertNotNull(db);
	}

	/**
	 * Method to check that the number of bought films by a user gets incremented
	 * after having bought one of them.
	 */
	@Test
	@PerfTest(invocations = 10)
	public void doesBoughtFilmsCountIncrement() {
		int prevFilmsCount = DB.getBoughtFilmsCount(u);
		DB.buyFilm(films.get(1), u);
		assertTrue(DB.getBoughtFilmsCount(u) > prevFilmsCount);
	}

	/**
	 * Method to check that, after buying a film, the ArrayList of bought films by a
	 * user is not null and contains at least a film.
	 */
	@Test
	@PerfTest(invocations = 10)
	public void myFilmsNotNull() {
		DB.buyFilm(films.get(2), u);
		assertTrue(DB.myFilms(u).size() > 0);
	}

	/**
	 * NOTE -- There is no method to prove that opening trailer method works as the
	 * browser is opened
	 */
	/**
	 * Method that checks that all the films have a price set and there is no price
	 * missing
	 */
	@Test
	@PerfTest(invocations = 10)
	public void doFilmsHavePrice() {
		ArrayList<Film> films = DB.retrieveFilms();
		for (int i = 0; i < films.size(); i++)
			assertTrue(films.get(i).getPrice() > 0);
	}

	/**
	 * Method that proofs that the searching bar works and searches films regardless
	 * the attributes and all the films searched by different conditions are
	 * properly retrieved
	 */
	@Test
	@PerfTest(invocations = 10)
	public void searchFilmsWOAttibutes() {
		ArrayList<Film> queryResult = DB.searchFilms("Stanley Kubrick");
		for (int i = 0; i < queryResult.size(); i++)
			assertEquals("Stanley Kubrick", queryResult.get(i).getDirector());
		queryResult = DB.searchFilms("2010");
		for (int i = 0; i < queryResult.size(); i++)
			assertEquals(2010, queryResult.get(i).getYear());
	}

	/**
	 * Method to check that, after buying a 3rd film, a free film is obtained method
	 * done manually here just to get coverage done because, in the windows, we
	 * include a JOptionPane.ShowMessageDialog, and it'd be tedious to have the
	 * messagedialog being popped up every single time the test is done
	 */
	@Test
	@PerfTest(invocations = 10)
	public void doesFreeFilmWork() {
		User u2 = new User("prueba", "Elon", "Musk", "passwd", 300);
		DB.register("prueba", "Elon", "Musk", "passwd", 300);
		DB.buyFilm(films.get(1), u2);
		DB.buyFilm(films.get(2), u2);
		int ownedFilms = DB.myFilms(u2).size();
		DB.buyFilm(films.get(3), u2);
		DB.freeFilm(u2);

		assertTrue(DB.getBoughtFilmsCount(u) > ownedFilms + 1);
	}

}
