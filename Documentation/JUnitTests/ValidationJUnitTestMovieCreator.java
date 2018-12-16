import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;

import common.Movie;
import tier2.model.Validation;

class ValidationJUnitTestMovieCreator {

	private Validation validation = null;
	private Movie movie = null;
	private String price = null;

	@BeforeEach
	public void before() throws Exception {
		validation = Validation.getInstance();
		movie = new Movie("testTitle", "2018", "12/12/2018", "testStudio", "testDirector", "testDescription",
				"testActor");
		price = "1000";
	}

	// Zero
	@Test
	public void testCanBeInstantiated() {
		assertNotNull(validation);
	}

	// One
	@Test
	public void testElementCanBeChecked() {
		String result = validation.checkMovie(movie, price);
		if (!result.equals(""))
			fail(result);
	}
	
	@Test
	public void testElementCanBeCheckedForNull() {
		movie.setTitle(null);
		String result = validation.checkMovie(movie, price);
		if (!result.equals("The Title is null "))
			fail(result);
	}

	@Test
	public void testElementCanCheckIfTitleIsTooBig() throws Exception {
		movie.setTitle(
				"Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nam mollis sodales mauris, sit amet varius tortor mollis eu. Suspendisse blandit. ");
		String result = validation.checkMovie(movie, price);
		if (!result.equals("The Title is too long "))
			fail(result);
	}

	@Test
	public void testElementCanCheckIfTitleIsTooSmall() {
		movie.setTitle("L");
		String result = validation.checkMovie(movie, price);
		if (!result.equals("The Title is too short "))
			fail(result);
	}

	@Test
	public void testElementCanCheckIfCreationYearIsWrongFormat() {
		movie.setYearCreation("20test");
		String result = validation.checkMovie(movie, price);
		if (!result.equals("Input a creation year made by digits "))
			fail(result);
	}

	@Test
	public void testElementCanCheckIfReleaseDateIsWrongFormat() {
		movie.setReleaseDate("203060");
		String result = validation.checkMovie(movie, price);
		if (!result.equals("Please enter the date in this exact format: [DD/MM/YYYY] "))
			fail(result);
	}

	@Test
	public void testElementCanCheckIfPriceIsWrongFormat() {
		price = "30test";
		String result = validation.checkMovie(movie, "30test");
		if (!result.equals("Input a price made by digits "))
			fail(result);
	}

	@Test
	public void testElementCanCheckIfDateIsValid() {
		String date = "232563";
		if (!validation.dateIsValid(date))
			fail("It's should return true if it is not valid");

	}

	// Many/More
	@Test
	public void testTwoElementsCanBeChecked() {
		movie.setReleaseDate("2030");
		price = "30test";
		String result = validation.checkMovie(movie, price);
		if (!result.equals("Please enter the date in this exact format: [DD/MM/YYYY] Input a price made by digits "))
			fail(result);
	}


}
