package es.deusto.BSPQ20_E2.Netflix.pojo;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Inheritance;
import javax.jdo.annotations.InheritanceStrategy;

@PersistenceCapable
@Inheritance(strategy = InheritanceStrategy.NEW_TABLE)

public class Film {
	private String id;
	private String title;
	private String genre;
	private String director;
	private int year;
	private float price;

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
		super();
		this.id = id;
		this.title = title;
		this.genre = genre;
		this.director = director;
		this.year = year;
		this.price = price;
	}

}
