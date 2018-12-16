package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The class that holds the details regarding the Scheduled Movie
 * @author Claudiu
 *
 */
public class ScheduledMovie {
	
	/**
	 * An array of Seat classes that needs to be created with the size of the room
	 */
	@SerializedName("Seats")
	@Expose
	private Seat[] seats;
	/**
	 * A String that holds the value of the time the movie will be displayed
	 */
	@SerializedName("Time")
	@Expose
	private String time;
	/**
	 * A String that holds the value of the day the movie will be displayed
	 */
	@SerializedName("Day")
	@Expose
	private String day;
	/**
	 * It holds the details regarding the movie that will be displayed
	 */
	@SerializedName("Movie")
	@Expose
	private Movie movie;
	/**
	 * It holds the details of the room the movie will be displayed in 
	 */
	@SerializedName("Room")
	@Expose
	private Room room;
	
	/**
	 * A constructor with all the details that uses the size of the room to instantiate the Seat array
	 * @param time
	 * @param day
	 * @param movie
	 * @param room
	 */
	public ScheduledMovie(String time, String day, Movie movie, Room room)
	{
		this.time = time;
		this.day = day;
		this.movie = movie;
		this.room = room;
		if(room != null)
		{
		this.seats = new Seat[room.getSize()];
		for(int i = 0; i < room.getSize(); i++)
		{
			seats[i] = new Seat(false);
		}
		}
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public Movie getMovie() {
		return movie;
	}

	public void setMovie(Movie movie) {
		this.movie = movie;
	}

	public Room getRoom() {
		return room;
	}

	public void setRoom(Room room) {
		this.room = room;
	}

	@Override
	public String toString() {
		return "ScheduledMovie [time=" + time + ", day=" + day + "]";
	}
	
	
}
