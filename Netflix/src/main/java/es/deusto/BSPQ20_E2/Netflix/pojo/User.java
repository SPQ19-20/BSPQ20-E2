package es.deusto.BSPQ20_E2.Netflix.pojo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

/**
 * This is the user class, used for every single user operation such as logging
 * in, buying films, checking my films... Given the persistance capability,
 * it'll be used in Datanucleus and several data will be retrieved from it using
 * MySQL.
 * 
 * @author Jorge El Busto, Inigo Orue
 * @category Pojo
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)

public class User {

	protected String code = null;
	protected String name = null;
	protected String surname = null;
	protected String password = null;
	protected double salary = 0;

	/**
	 * Constructor of the user class
	 * 
	 * @param code     Username to be used in logging in processes
	 * @param name     Name to be displayed in the main window
	 * @param surname  Surname to be stored in the DB
	 * @param password Password of the user used in order to log in
	 * @param salary   Salary to be used when buying films
	 */
	public User(String code, String name, String surname, String password, double salary) {
		this.code = code;
		this.name = name;
		this.surname = surname;
		this.password = password;
		this.salary = salary;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getCode() {
		return code;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}

	public String getSurname() {
		return surname;
	}

	public void setSurname(String surname) {
		this.surname = surname;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public double getSalary() {
		return salary;
	}

	public void setSalary(double salary) {
		this.salary = salary;
	}

	/**
	 * Method for the user to be printed in loggers
	 * 
	 * @return string to be displayed in loggers
	 */
	@Override
	public String toString() {
		return "User " + code + ": [" + name + " " + surname + ", Salary: " + salary + "$ ]";
	}
}
