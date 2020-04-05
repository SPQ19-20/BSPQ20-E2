package es.deusto.BSPQ20_E2.Netflix.demo;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;

public class DemoDB {
	public static Connection connect() throws Exception {
		// SQLite connection string
		Class.forName("org.sqlite.JDBC");
		String url = "jdbc:sqlite:src/main/java/es/deusto/BSPQ20_E2/Netflix/demo/demo.db";
		Connection conn = null;
		try {
			conn = DriverManager.getConnection(url);
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return conn;
	}

	public static User logged(Connection con, String user, String pass) {
		String sql = "SELECT * FROM USER WHERE ID='" + user + "' AND PASSWORD='" + pass + "';";
		
		User u = new User("", "", "", "", 0);
		try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

			while (rs.next()) {
				u = new User(String.valueOf(rs.getString("ID")), String.valueOf(rs.getString("NAME")),
						String.valueOf(rs.getString("SURNAME")), String.valueOf(rs.getString("PASSWORD")),
						rs.getInt("SALARY"));
			}
			
			con.close();

			return u;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}
	
	public static ArrayList<Film> retrieveFilms(Connection con) {
		String sql = "SELECT * FROM FILM;";
		ArrayList<Film> films = new ArrayList<>();
		try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
			while (rs.next()) {
				films.add(new Film(String.valueOf(rs.getString("ID")), String.valueOf(rs.getString("TITLE")),
						String.valueOf(rs.getString("GENRE")), String.valueOf(rs.getString("DIRECTOR")),
						rs.getInt("YEAR"), rs.getFloat("PRICE")));
			}
			con.close();
			return films;
		} catch (SQLException e) {
			System.out.println(e.getMessage());
			return null;
		}

	}

}
