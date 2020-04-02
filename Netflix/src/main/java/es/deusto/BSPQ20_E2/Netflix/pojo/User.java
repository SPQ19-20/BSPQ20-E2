package es.deusto.BSPQ20_E2.Netflix.pojo;


public class User {

    private String code;
    private String name;
    private String surname;
private String password;
private double salary;
    //Default public constructor required for serialization
    public User() {

    }

    public User(String code, String name, String surname, String password, double salary) {
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
