package common;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The Package class is used in communication between the tiers in the
 * components
 * 
 * @author Claudiu
 *
 */
public class Package {
	public static final String SCHEDULEDMOVIE = "SCHEDULEDMOVIE";
	public static final String ADDROOM = "ADDROOM";
	public static final String GETROOMS = "GETROOMS";
	public static final String GETRENTEDMOVIES = "GETRENTEDMOVIES";
	public static final String REMOVEROOM = "REMOVEROOM";
	public static final String GETMOVIES = "GETMOVIES";
	public static final String SENDSCHEDULE = "SENDSCHEDULE";
	public static final String GETSCHEDULE = "GETSCHEDULE";
	public static final String GETROOM = "GETROOM";
	public static final String GETMOVIE = "GETMOVIE";
	public static final String CANCELSCHEDULE = "CANCELSCHEDULE";
	public static final String GETALLSCHEDULE = "GETALLSCHEDULE";
	public static final String UPDATESCHEDULE = "UPDATESCHEDULE";

	/**
	 * The header is used as a indicator for the type of content in the package
	 */
	@SerializedName("Header")
	@Expose
	private String header;
	/**
	 * Most of the content of the packages, it's usually used to get the String
	 * results from the other tiers
	 */
	@SerializedName("Body")
	@Expose
	private String body;
	/**
	 * Package has a Room instance for convenience
	 */
	@SerializedName("Room")
	@Expose
	private Room room;
	/**
	 * Package has a Movie instance for convenience
	 */
	@SerializedName("Movie")
	@Expose
	private Movie movie;
	/**
	 * A list of Scheduled Movies that is sent to the database server
	 */
	@SerializedName("ScheduleList")
	@Expose
	private ArrayList<ScheduledMovie> List;
	/**
	 * Between tier 1 and tier 2 just a Scheduled Movie is sent
	 */
	@SerializedName("ScheduledMovie")
	@Expose
	private ScheduledMovie scheduledMovie;
	/**
	 * A more condensed form of the List of Scheduled Movies that is used for
	 * convenience
	 */
	@SerializedName("Schedule")
	@Expose
	private Schedule schedule;

	/**
	 * A constructor that only uses that header to signal something
	 * 
	 * @param header
	 */
	public Package(String header) {
		this.header = header;
	}

	/**
	 * The constructor that is used most often for communication
	 * 
	 * @param header
	 * @param body
	 */
	public Package(String header, String body) {
		this.header = header;
		this.body = body;
	}

	/**
	 * A constructor to send a Room over other tiers
	 * 
	 * @param header
	 * @param room
	 */
	public Package(String header, Room room) {
		this.header = header;
		this.room = room;
	}

	/**
	 * A constructor used to send a Room that needs to have the size check for
	 * format
	 * 
	 * @param header
	 * @param size
	 * @param room
	 */
	public Package(String header, String size, Room room) {
		this.header = header;
		this.body = size;
		this.room = room;
	}

	/**
	 * A constructor to send a Movie over other tiers
	 * 
	 * @param header
	 * @param movie
	 */
	public Package(String header, Movie movie) {
		this.header = header;
		this.movie = movie;
	}

	/**
	 * A constructor to send a Scheduled Movie over other tiers
	 * 
	 * @param header
	 * @param scheduledMovie
	 */
	public Package(String header, ScheduledMovie scheduledMovie) {
		this.header = header;
		this.scheduledMovie = scheduledMovie;
	}

	/**
	 * A constructor to send a Schedule over other tiers
	 * 
	 * @param header
	 * @param schedule
	 */
	public Package(String header, Schedule schedule) {
		this.header = header;
		this.schedule = schedule;
	}

	/**
	 * A constructor to send a Scheduled Movies List over other tiers
	 * 
	 * @param header
	 * @param List
	 */
	public Package(String header, ArrayList<ScheduledMovie> List) {
		this.header = header;
		this.List = List;
	}

	public String getBody() {
		return body;
	}

	public void setBody(String body) {
		this.body = body;
	}

	public void setHeader(String header) {
		this.header = header;
	}

	public String getHeader() {
		return header;
	}

	public Movie getMovie() {
		return movie;
	}

	public Room getRoom() {
		return room;
	}

	public ScheduledMovie getScheduledMovie() {
		return scheduledMovie;
	}

	public Schedule getSchedule() {
		return schedule;
	}

	public ArrayList<ScheduledMovie> getList() {
		return List;
	}

	public void setList(ArrayList<ScheduledMovie> list) {
		List = list;
	}
}
