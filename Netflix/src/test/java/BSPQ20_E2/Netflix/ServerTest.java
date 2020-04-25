package BSPQ20_E2.Netflix;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import es.deusto.BSPQ20_E2.Netflix.server.db.DB;

/**
 * Class containing the tests of the server
 * 
 * @author Diego Rojo
 *
 */
public class ServerTest {

	/**
	 * Method that tests the logged method to check if credentials are valid
	 * 
	 * @param u user that correspond that credentials
	 *
	 */
	@Test
	public void checkCredentials() {
			try {
				User u = DB.logged("jorge", "pass");
				if (u!=null) {
					//If the user is not null the test is correct
					assertTrue(true);
				} else
					assertTrue(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	/**
	 * Method that tests the connect method
	 * 
	 * @param con connection created to check if the method works
	 *
	 */
	@Test
	public void checkConnection() throws Exception {
		
		Connection con = null;
		try {
			con = DB.connect();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(con!=null) {
			//If the connection is not null, the method works
			assertTrue(true);
		}else {
			assertTrue(false);
		}
	}
	
	/**
	 * Method that tests the retrieveFilms method that gets the films of the database
	 * 
	 * @param filmsDatabase Arraylist of films created to check the method
	 *
	 */
	@Test
	public void checkFilmRetriever(){
		ArrayList<Film> filmsDatabase= DB.retrieveFilms();
		if(filmsDatabase!=null) {
			//If the ArrayList is not null, the method works fine
			assertTrue(true);
		}else {
			assertTrue(false);
		}
	}
	
	/**
	 * Method that tests the searchFilms method
	 * 
	 * @param filmsSearched arrayList of films created to check if the method works
	 *
	 */
	@Test
	public void checkFilmSearch() {
		ArrayList<Film> filmsSearched=DB.searchFilms("2010");
		if(filmsSearched!=null) {
			//If the arrayList is not null, the method works
			assertTrue(true);
		}else {
			assertTrue(false);
		}
	}
	
	//TODO put test to check registration
	

}
