package es.deusto.BSPQ20_E2.Netflix.pojo;
import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

/**
 * User class
 * @author Jorge El Busto, Inigo Orue
 *
 */
@PersistenceCapable
@Inheritance(strategy=InheritanceStrategy.NEW_TABLE)

public class User {

    protected String code = null;
    protected String name = null;
    protected String surname = null;
    protected String password = null;
    protected double salary = 0;

    public User(String code, String name, String surname, String password, double salary) {
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.salary = salary;
    }
    
    public User(String name, String surname, String password, double salary) {
        this.code = code;
        this.name = name;
        this.surname = surname;
        this.password = password;
        this.salary = salary;
    }

    public void setCode(String code) { this.code = code; }
    public String getCode() { return code;  }
    public void setName(String name) { this.name = name;     }
    public String getName() {  return name;     }

    public String getSurname() {return surname; }
    public void setSurname(String surname) { this.surname = surname; }

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

	@Override
    public String toString() {
        return name + " " + surname;
    }
}
