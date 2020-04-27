package es.deusto.BSPQ20_E2.Netflix.pojo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

/**
 * Transaction class to store bought films
 * @author Annette
 *
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)

public class Transaction {
	protected Film f;
	protected User u;
	

	protected Transaction(Film f, User u) {
		this.f = f;
		this.u = u;
	
	}


	public Film getF() {
		return f;
	}


	public void setF(Film f) {
		this.f = f;
	}


	public User getU() {
		return u;
	}


	public void setU(User u) {
		this.u = u;
	}
	
	

}	