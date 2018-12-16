import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import common.Movie;
import common.Room;
import common.ScheduledMovie;
import tier2.model.Validation;

class ValidationJUnitTestMovieScheduler {

	private Validation validation = null;
	private String day = null;
	private String time = null;
	private ArrayList<ScheduledMovie> list = null;
	private Movie movie = null;
	private Room room = null;

	@BeforeEach
	void setUp() throws Exception {
		validation = Validation.getInstance();
		day = "Monday";
		time = "10:00";
		movie = new Movie("testTitle", "2018", "12/12/2018", 40, "testStudio", "testDirector",
				"testDescription", "testActor");
		room = new Room(20, "testDescription");
		ScheduledMovie movieTest = new ScheduledMovie("10:00", "Monday", movie, room);
		list = new ArrayList<ScheduledMovie>();
		list.add(movieTest);
	}

	// Zero
	@Test
	public void testCanBeInstantiated() {
		assertNotNull(validation);
	}
	
	@Test
	public void testListIsEmpty()
	{
		list = new ArrayList<ScheduledMovie>();
		if (validation.CheckIfNotAvailableTimeAndDay(day, time, list))
			fail("It should return false because there is nothing in the list");
	}

	// One

	@Test
	public void testElementCanCheckList() {
		if (!validation.CheckIfNotAvailableTimeAndDay(day, time, list))
			fail("It returns true if the day and time are not available");
	}

	@Test
	public void testElementCanCheckMovieIfNull() {
		if (validation.CheckIfMovieIsNull(movie))
			fail("It returns true if the movie is null");
	}

	@Test
	public void testElementCanCheckRoomIfNull() {
		if (validation.CheckIfRoomIsNull(room))
			fail("It returns true if the room is null");
	}

	// Many/More
	@Test
	public void testElementCanCheckMultipleMoviesInAList() {
		ScheduledMovie movieTest = new ScheduledMovie("13:00", "Monday", movie, room);
		list.add(movieTest);
		if (!validation.CheckIfNotAvailableTimeAndDay("Monday", "13:00", list))
			fail("It returns true if the day and time are present in the list with multiple scheduled movies");
	}

}