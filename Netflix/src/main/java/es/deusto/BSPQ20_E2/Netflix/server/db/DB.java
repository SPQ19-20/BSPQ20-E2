package es.deusto.BSPQ20_E2.Netflix.server.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;

/**
 * Java integration with MySQL
 * 
 * @author Jorge El Busto
 *
 */
public class DB {
	private final static Logger LOGGER = Logger.getLogger(DB.class.getName());
	public static Connection connect() throws Exception {
		Class.forName("com.mysql.jdbc.Driver");
		Connection con = null;
		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost:3306/Netflix", "root", "root");
		} catch (SQLException e) {
			System.out.println(e.getMessage());
		}
		return con;
	}

	/**
	 * Method to check in the DB if the user credentials are valid
	 * 
	 * @param con  Connection with the DB
	 * @param user String with the username passed by the TextField
	 * @param pass String with the password passed by the JPasswordField
	 * @return new user which will be running the program
	 */
	public static User logged(String user, String pass) {
		try {
			Connection con = DB.connect();
			String sql = "SELECT * FROM USER WHERE CODE='" + user + "' AND PASSWORD='" + pass + "';";

			User u = new User("", "", "", "", 0);
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {

				while (rs.next()) {
					u = new User(String.valueOf(rs.getString("CODE")), String.valueOf(rs.getString("NAME")),
							String.valueOf(rs.getString("SURNAME")), String.valueOf(rs.getString("PASSWORD")),
							rs.getInt("SALARY"));
				}

				con.close();
				
				return u;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return null;
			}
		} catch (Exception e2) {
			System.out.println(e2.getMessage());
			return null;

		}
	}

	/**
	 * Method to retrieve every single film in the DB
	 * @param con Connection to the DB
	 * @return an array with every film in the FILM table
	 */
	public static ArrayList<Film> retrieveFilms() {
		try {
			Connection con = connect();
			String sql = "SELECT * FROM FILM;";
			ArrayList<Film> films = new ArrayList<>();
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
				while (rs.next()) {
					Film film = new Film(String.valueOf(rs.getInt("FILM_ID")), String.valueOf(rs.getString("TITLE")),
							String.valueOf(rs.getString("GENRE")), String.valueOf(rs.getString("DIRECTOR")),
							rs.getInt("YEAR"), rs.getFloat("PRICE"), String.valueOf(rs.getString("URL")));
					films.add(film);
					LOGGER.log(Level.INFO, "Film retrieved: " + film.toString());
				}
				con.close();
				return films;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	/**
	 * Method to search films
	 * @param con Connection to the DB
	 * @param condition String to be searched retrieved from the main window
	 * @return Every film whose director, name, genre, year, title, or price contains the indicated string
	 */
	public static ArrayList<Film> searchFilms(String condition) {
		try {
			Connection con = connect();
			String sql;
			try {
				int cond = Integer.parseInt(condition);
				sql = "SELECT * FROM FILM WHERE YEAR=" + cond + " OR PRICE=" + cond + ";";
				System.out.println(sql);
			} catch (Exception e) {
				sql = "SELECT * FROM FILM WHERE TITLE LIKE '%" + condition
						+ "%' OR GENRE LIKE '%" + condition + "%' OR DIRECTOR LIKE '%" + condition + "%';";
				System.out.println(sql);

			}
			ArrayList<Film> films = new ArrayList<>();
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
				while (rs.next()) {
					films.add(new Film(String.valueOf(rs.getString("ID")), String.valueOf(rs.getString("TITLE")),
							String.valueOf(rs.getString("GENRE")), String.valueOf(rs.getString("DIRECTOR")),
							rs.getInt("YEAR"), rs.getFloat("PRICE"), String.valueOf(rs.getString("URL"))));
				}
				con.close();
				return films;
			} catch (SQLException e) {
				System.out.println(e.getMessage());
				return null;
			}
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}

	}

	/**
	 * Method to process the information in the DB so that the price of the bought film is taken away from the credit of the user who buys it.
	 * @param f Film whose price is going to be taken
	 * @param u User who is interested in buying the film
	 */
	public static void buyFilm(Film f, User u) {
		String sql = "UPDATE USER SET SALARY = SALARY - " + f.getPrice() + " WHERE CODE='" + u.getCode() + "';";
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			con.close();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}
	/**
	 * Method to register users into the SQL Database
	 * @param code Username
	 * @param name Name of the user
	 * @param surname Surname of the user
	 * @param password Password of the account
	 * @param salary Salary of the account
	 */
	public static void register(String code, String name, String surname, String password, double salary) {
		String sql = "INSERT INTO USER (CODE, NAME, SURNAME, PASSWORD, SALARY) VALUES ('" + code + "', '" + name + "', '" +surname + "', '" +password + "', " + String.valueOf(salary)+");";
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			con.close();
			LOGGER.log(Level.INFO, "New user registered: " + code);
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}



}


