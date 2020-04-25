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
	
	private static User u;
	private static Film f;
	
	@BeforeClass
	 public static void setUpClass() throws Exception{
	 u = new User("U-01", "Diego", "Rojo", "1234", 25);
	 f = new Film("F-01", "Thor", "Action", "Taika Waititi", 2011, 4, "www.thorfilm.com");
	 }
	 
	@Test
	 public void checkSetSalary() {
		 DB.buyFilm(f, u);
		 u.setSalary(u.getSalary()-f.getPrice());
		 assertEquals(21, u.getSalary(),0.01);
	 }

}

