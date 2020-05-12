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
	 * @param l login window
	 */
	private static Login l;

	/**
	 * Method that set up the values of the user and film
	 */
	@BeforeClass
	public static void setUpValues() throws Exception {
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


}
