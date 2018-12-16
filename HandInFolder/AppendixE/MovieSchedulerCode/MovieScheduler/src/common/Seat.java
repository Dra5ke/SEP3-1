package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * The Seat class that holds only if the seat is booked or not
 * @author Claudiu
 *
 */
public class Seat {

	/**
	 * Indicated if the seat is booked or not
	 */
	@SerializedName("Booked")
	@Expose
	private boolean booked = false;

	/**
	 * Constructor to set a certain value to the booked status
	 * @param booked
	 */
	public Seat(boolean booked) {
		this.booked = booked;
	}
	/**
	 * A default constructor that just sets the seat's booked status to false
	 */
	public Seat() {
		booked = false;
	}
}
