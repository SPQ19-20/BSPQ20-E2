package es.deusto.BSPQ20_E2.Netflix.server.db;

import java.awt.Desktop;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.net.URLConnection;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Random;
import java.util.logging.Level;

import javax.swing.JOptionPane;

import org.apache.log4j.Logger;

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
			con = DriverManager.getConnection(
					"jdbc:mysql://localhost:3306/Netflix?verifyServerCertificate=false&useSSL=true&useLegacyDatetimeCode=false&serverTimezone=UTC",
					"root", "root");
		} catch (SQLException e) {
			LOGGER.error(e.getMessage());
		}
		return con;
	}

	/**
	 * Method to check in the DB if the user credentials are valid
	 * 
	 * @throws SQLException
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
				if (u != null)
					return u;
				else
					return null;
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				return null;
			}
		} catch (Exception e2) {
			LOGGER.error(e2.getMessage());
			return null;

		}
	}

	/**
	 * Method to retrieve every single film in the DB
	 * 
	 * @throws SQLException
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
							rs.getInt("YEAR"), rs.getFloat("PRICE"), String.valueOf(rs.getString("URL")),
							String.valueOf(rs.getString("TRAILER")));
					films.add(film);
					LOGGER.info("Film retrieved: " + film.toString());
				}
				con.close();
				return films;
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.info(e.getMessage());
		}
		return null;
	}

	/**
	 * Method to search films regardless the attribute. It accepts the year, the
	 * director, the title... any of them
	 * 
	 * @throws SQLException
	 * @param con       Connection to the DB
	 * @param condition String to be searched retrieved from the main window
	 * @return Every film whose director, name, genre, year, title, or price
	 *         contains the indicated string
	 */
	public static ArrayList<Film> searchFilms(String condition) {
		try {
			Connection con = connect();
			String sql;
			try {
				int cond = Integer.parseInt(condition);
				sql = "SELECT * FROM FILM WHERE YEAR=" + cond + " OR PRICE=" + cond + ";";
				LOGGER.error(sql);
			} catch (Exception e) {
				sql = "SELECT * FROM FILM WHERE TITLE LIKE '%" + condition + "%' OR GENRE LIKE '%" + condition
						+ "%' OR DIRECTOR LIKE '%" + condition + "%';";
				LOGGER.error(sql);

			}
			ArrayList<Film> films = new ArrayList<>();
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
				while (rs.next()) {
					films.add(new Film(String.valueOf(rs.getString("ID")), String.valueOf(rs.getString("TITLE")),
							String.valueOf(rs.getString("GENRE")), String.valueOf(rs.getString("DIRECTOR")),
							rs.getInt("YEAR"), rs.getFloat("PRICE"), String.valueOf(rs.getString("URL")),
							String.valueOf(rs.getString("TRAILER"))));
				}
				con.close();
				return films;
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				return null;
			}
		} catch (Exception e) {
			LOGGER.info(e.getMessage());
			return null;
		}

	}

	/**
	 * Method to process the information in the DB so that the price of the bought
	 * film is taken away from the credit of the user who buys it.
	 * 
	 * @throws SQLException
	 * @param f Film whose price is going to be taken
	 * @param u User who is interested in buying the film
	 */
	public static void buyFilm(Film f, User u) {
		String sql = "UPDATE USER SET SALARY = SALARY - " + f.getPrice() + " WHERE CODE='" + u.getCode() + "';";
		String ins = "INSERT INTO TRANSACTION (F_FILM_ID_OID,U_USER_ID_OID) VALUES (" + f.getId() + "," + getId(u)
				+ ");";
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);

			con.close();
			Connection con2 = connect();
			Statement stmt2 = con2.createStatement();
			stmt2.executeUpdate(ins);
			u.setSalary(u.getSalary() - f.getPrice());
			con2.close();

		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Method to process the information in the DB so that the price of the bought
	 * film is taken away from the credit of the user who buys it.
	 * 
	 * @throws SQLException
	 * @param f Film which is going to be given
	 * @param u User who will get the present
	 */
	public static Film freeFilm(User u) {
		Random r = new Random();
		ArrayList<Film> films = DB.retrieveFilms();
		int low = 1;
		int high = films.size();
		int index = r.nextInt(high - low) + low;
		Film f = films.get(index);
		String ins = "INSERT INTO TRANSACTION (F_FILM_ID_OID,U_USER_ID_OID) VALUES (" + f.getId() + "," + getId(u)
				+ ");";
		try {

			Connection con2 = connect();
			Statement stmt2 = con2.createStatement();
			stmt2.executeUpdate(ins);

			con2.close();
			return f;
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
			return null;
		}
	}

	/**
	 * Method to get the id of the user in MySQL to execute the transaction of
	 * buying a film and storing it in the user's particular bought films cart
	 * 
	 * @throws SQLException
	 * @param u User from which we want to get the name
	 */
	public static String getId(User u) {
		try {
			Connection con = connect();
			String userid = "";
			String sql = "";
			try {
				sql = "SELECT * FROM USER WHERE CODE='" + u.getCode() + "';";
			} catch (Exception e) {
				LOGGER.error(e);
			}
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
				while (rs.next()) {
					userid = String.valueOf(rs.getString("USER_ID"));
				}
				con.close();
				return userid;
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				return null;
			}

		} catch (Exception ee) {
			LOGGER.error(ee.getMessage());
			return null;

		}
	}

	/**
	 * Method to get the count of films bought by a certain user. It works along
	 * with the getID method.
	 * 
	 * @param u User from which we want to get the name
	 */
	public static int getBoughtFilmsCount(User u) {
		try {
			Connection con = connect();
			String userid = "";
			String sql = "";
			try {
				sql = "SELECT COUNT(TRANSACTION_ID) FROM TRANSACTION WHERE U_USER_ID_OID=" + getId(u) + ";";
			} catch (Exception e) {
				LOGGER.error(e);
			}
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
				while (rs.next()) {
					return rs.getInt("COUNT(TRANSACTION_ID)");
				}
				con.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				return 0;
			}
		} catch (Exception ee) {
			LOGGER.error(ee.getMessage());
			return 0;
		}
		return 0;
	}

	/**
	 * Method to register users into the SQL Database.
	 * 
	 * @param code     Username
	 * @param name     Name of the user
	 * @param surname  Surname of the user
	 * @param password Password of the account
	 * @param salary   Salary of the account
	 */
	public static void register(String code, String name, String surname, String password, double salary) {
		String sql = "INSERT INTO USER (CODE, NAME, SURNAME, PASSWORD, SALARY) VALUES ('" + code + "', '" + name
				+ "', '" + surname + "', '" + password + "', " + String.valueOf(salary) + ");";
		try {
			Connection con = connect();
			Statement stmt = con.createStatement();
			stmt.executeUpdate(sql);
			con.close();
			LOGGER.info("New user registered: " + code);
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
	}

	/**
	 * Method to retrieve the films a certain user already owns.
	 * 
	 * @param con Connection to the DB
	 * @return an array with the films a certain user owns
	 */
	public static ArrayList<Film> myFilms(User u) {
		try {
			Connection con = connect();
			String sql = "SELECT * FROM FILM WHERE FILM_ID IN (SELECT F_FILM_ID_OID FROM TRANSACTION WHERE U_USER_ID_OID="
					+ getId(u) + ")";
			ArrayList<Film> films = new ArrayList<>();
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
				while (rs.next()) {
					Film film = new Film(String.valueOf(rs.getInt("FILM_ID")), String.valueOf(rs.getString("TITLE")),
							String.valueOf(rs.getString("GENRE")), String.valueOf(rs.getString("DIRECTOR")),
							rs.getInt("YEAR"), rs.getFloat("PRICE"), String.valueOf(rs.getString("URL")),
							String.valueOf(rs.getString("TRAILER")));
					films.add(film);
					LOGGER.info("Film retrieved: " + film.toString());
				}
				con.close();
				return films;
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
				return null;
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			LOGGER.error(e.getMessage());
		}
		return null;
	}

	/**
	 * Method to open the trailer of a film in the browser. It retrieves all the
	 * attributes of the film from the database, and working with the trailer
	 * getter, the URI is opened in a new browser if the platform in which the app
	 * is being executed supports browsing.
	 * 
	 * @param Film which is going to be opened
	 * @throws IOException, URISyntaxException, SQLException
	 * 
	 */
	public static void openTrailer(Film f) throws IOException {
		String trailer = "";
		try {
			Connection con = connect();
			String sql = "SELECT * FROM FILM WHERE FILM_ID=" + f.getId() + ";";
			try (Statement stmt = con.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
				while (rs.next()) {
					trailer = String.valueOf(rs.getString("TRAILER"));
					LOGGER.info("URL opened: " + trailer);
				}
				con.close();
			} catch (SQLException e) {
				LOGGER.error(e.getMessage());
			}
		} catch (Exception e) {
			LOGGER.error(e.getMessage());
		}
		if (Desktop.isDesktopSupported() && Desktop.getDesktop().isSupported(Desktop.Action.BROWSE)) {
			try {
				Desktop.getDesktop().browse(new URI(trailer));
			} catch (IOException e) {
				LOGGER.error(e.getMessage());
			} catch (URISyntaxException e) {
				LOGGER.error(e.getMessage());
			}
		}
	}
}
