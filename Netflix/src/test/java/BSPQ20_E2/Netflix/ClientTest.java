package BSPQ20_E2.Netflix;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.Locale;

import org.junit.*;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.junit.ContiPerfRule;

import es.deusto.BSPQ20_E2.Netflix.client.Internationalization;
import es.deusto.BSPQ20_E2.Netflix.client.gui.Login;
import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import es.deusto.BSPQ20_E2.Netflix.server.db.DB;

/**
 * Class containing the tests of the client
 * 
 * @author Diego Rojo, Jorge El Busto
 *
 */
public class ClientTest {
	
	@Rule
	public ContiPerfRule i = new ContiPerfRule();

	/**
	 * @param u user created to check the methods
	 * @param f film created to check the methods
	 * @param l login window
	 */
	private static User u;
	private static Film f;
	private static Login l;

	/**
	 * Method that set up the values of the user and film
	 */
	@BeforeClass
	public static void setUpValues() throws Exception {
		u = new User("U-01", "Diego", "Rojo", "1234", 25);
		f = new Film("F-01", "Thor", "Action", "Taika Waititi", 2011, 4, "www.thorfilm.com");
		l = new Login();
	}

	/**
	 * Method that makes sure that the default language of the app is English
	 */
	@Test
	@PerfTest(invocations = 10)
	public void defLang() {
		l.setLocale(l.getLocale().getDefault());
		assertEquals("Register", Internationalization.resourceBundle.getString("btnRegister"));
	}

	/**
	 * Method that checks that all the films have a price set and there is no price missing
	 */
	@Test
	@PerfTest(invocations = 10)
	public void doFilmsHavePrice() {
		ArrayList<Film> films = DB.retrieveFilms();
		for (int i = 0; i<films.size(); i++)
			assertTrue(films.get(i).getPrice()>0);
	}
	
	/**
	 * Method that proofs that the searching bar works and searches films regardless the attributes
	 * and all the films searched by different conditions are properly retrieved
	 */
	@Test
	@PerfTest(invocations = 10)
	public void searchFilmsWOAttibutes() {
		ArrayList<Film> queryResult = DB.searchFilms("Stanley Kubrick");
		for (int i = 0; i<queryResult.size(); i++)
			assertEquals("Stanley Kubrick", queryResult.get(i).getDirector());
		queryResult = DB.searchFilms("2010");
		for (int i = 0; i<queryResult.size(); i++)
			assertEquals(2010, queryResult.get(i).getYear());
	}


}
