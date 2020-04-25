package BSPQ20_E2.Netflix;

import static org.junit.Assert.assertEquals;
import org.junit.*;
import es.deusto.BSPQ20_E2.Netflix.pojo.Film;
import es.deusto.BSPQ20_E2.Netflix.pojo.User;

public class PojoTest {
	
	private User u;
	private Film f;
	
	@BeforeClass
	 public void setUp() {
	 u= new User(null, null, null, null, 0);
	 f = new Film(null, null, null, null, 1960, 0, null);
	 }

	@Test
	 public void checkUserCode() {
		 u.setCode("U-01"); 
		 assertEquals("U-01", u.getCode()); 
	 }
	@Test
	 public void checkUserName() {
		u.setName("Diego");
		assertEquals("Diego", u.getName());
	 }
	@Test
	 public void checkUserSurname() {
		u.setSurname("Rojo");
		assertEquals("Rojo", u.getSurname());
	 }
	@Test
	 public void checkUserPassword() {
		u.setPassword("123456");
		assertEquals("123456", u.getPassword());
	 }
	@Test
	 public void checkUserSalary() {
		 u.setSalary(25);
		 assertEquals(25, u.getSalary(),0.01);
	 }
	
	
	@Test
	 public void checkFilmId() {
		f.setId("F-01");
		assertEquals("F-01", f.getId());
	 }
	@Test
	 public void checkFilmTitle() {
		f.setTitle("Thor");
		assertEquals("Thor", f.getTitle());
	 }
	@Test
	 public void checkFilmGenre() {
		f.setGenre("Action");
		assertEquals("Action", f.getGenre());
	 }
	@Test
	 public void checkFilmDirector() {
		f.setDirector("Kenneth Branagh");
		assertEquals("Kenneth Branagh", f.getDirector());
	 }
	@Test
	 public void checkFilmYear() {
		 f.setYear(2011); 
		 assertEquals(2011, f.getYear());
	 }
	@Test
	 public void checkFilmPrice() {
		 f.setPrice(4.5f); 
		 assertEquals(4.5, f.getPrice(),0.01);
	 }
	@Test
	 public void checkFilmURL() {
		 f.setUrl("www.thorfilm.com");  
		 assertEquals("www.thorfilm.com", f.getUrl());
	 }
	
}
