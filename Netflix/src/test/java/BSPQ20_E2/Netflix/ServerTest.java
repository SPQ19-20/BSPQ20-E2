package BSPQ20_E2.Netflix;

import static org.junit.Assert.assertTrue;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import org.junit.Test;

import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;
import es.deusto.BSPQ20_E2.Netflix.server.db.DB;

public class ServerTest {

	@Test
	public void checkCredentials() {
			try {
				User u = DB.logged("jorge", "pass");
				if (u.getSalary() > 0) {
					assertTrue(true);
				} else
					assertTrue(false);
			} catch (Exception e) {
				e.printStackTrace();
			}
	}
	
	@Test
	public void checkConnection() throws Exception {
		
		Connection con = null;
		try {
			con = DB.connect();
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		
		if(con!=null) {
			assertTrue(true);
		}else {
			assertTrue(false);
		}
	}
	
	@Test
	public void checkFilmRetriever(){
		ArrayList<Film> filmsDatabase= DB.retrieveFilms();
		if(filmsDatabase!=null) {
			assertTrue(true);
		}else {
			assertTrue(false);
		}
	}
	
	@Test
	public void checkFilmSearch() {
		ArrayList<Film> filmsSearched=DB.searchFilms("2010");
		if(filmsSearched!=null) {
			assertTrue(true);
		}else {
			assertTrue(false);
		}
	}
	
	//TODO put test to check registration
	

}
