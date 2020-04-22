package es.deusto.BSPQ20_E2.Netflix.pojo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

/**
 * Film class
 * 
 * @author Jorge El Busto
 *
 */
@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)

public class Film {
	protected String id = null;
	protected String title = null;
	protected String genre = null;
	protected String director = null;
	protected int year = 0;
	protected float price = 0;

	protected Film() {
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getGenre() {
		return genre;
	}

	public void setGenre(String genre) {
		this.genre = genre;
	}

	public String getDirector() {
		return director;
	}

	public void setDirector(String director) {
		this.director = director;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Film(String id, String title, String genre, String director, int year, float price) {
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.director = director;
		this.year = year;
		this.price = price;
	}

	public String toString() {
		return "Film : " + title + " [ Genre:" + genre + " Director: " + director + " Year: " + year + " Price: " + price
				+ "]";
	}
}
