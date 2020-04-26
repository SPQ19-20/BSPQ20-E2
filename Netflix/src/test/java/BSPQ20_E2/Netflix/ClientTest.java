package BSPQ20_E2.Netflix;

import static org.junit.Assert.assertEquals;
import org.junit.*;

import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import es.deusto.BSPQ20_E2.Netflix.server.db.DB;

/**
 * Class containing the tests of the client
 * 
 * @author Diego Rojo
 *
 */
public class ClientTest{
	
	/**
	 * @param u user created to check the methods
	 * @param f film created to check the methods
	 *
	 */
	private static User u;
	private static Film f;
	
	
	/**
	 * Method that set up the values of the user and film
	 *
	 */
	@BeforeClass
	 public static void setUpValues() throws Exception{
	 u = new User("U-01", "Diego", "Rojo", "1234", 25);
	 f = new Film("F-01", "Thor", "Action", "Taika Waititi", 2011, 4, "www.thorfilm.com");
	 }
	 
	
	/**
	 * Method that checks that the price of the film is well discounted of the salary
	 *
	 */
	@Test
	 public void checkSetSalary() {
		 DB.buyFilm(f, u);
		 u.setSalary(u.getSalary()-f.getPrice());
		 assertEquals(21, u.getSalary(),0.01);
	 }

}

