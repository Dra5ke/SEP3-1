/**
 * 
 */
package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * This is the class that holds the details regarding a movie
 * 
 * @author Claudiu
 *
 */
public class Movie {
	/**
	 * The title of the movie
	 */
	@SerializedName("Title")
	@Expose
	private String title;
	/**
	 * The year of creation for the movie
	 */
	@SerializedName("YearCreation")
	@Expose
	private String yearCreation;
	/**
	 * The release date for the movie
	 */
	@SerializedName("ReleaseDate")
	@Expose
	private String releaseDate;
	/**
	 * The price to rent the movie
	 */
	@SerializedName("Price")
	@Expose
	private double price;
	/**
	 * The name of the studio that made the movie
	 */
	@SerializedName("NameStudio")
	@Expose
	private String nameStudio;
	@SerializedName("NameDirector")
	@Expose
	private String nameDirector;
	/**
	 * The description of the movie
	 */
	@SerializedName("Description")
	@Expose
	private String description;
	/**
	 * The main actor for the movie
	 */
	@SerializedName("NameMainActor")
	@Expose
	private String nameMainActor;
	/**
	 * A boolean value to check if the movie is rented or not
	 */
	@SerializedName("Rented")
	@Expose
	private boolean rented;

	/**
	 * A simple constructor for when we don't have enough details to complete all the movie
	 * @param title
	 */
	public Movie(String title) {
		this.title = title;
	}

	/**
	 * Constructor with all the details of the Movie, that also sets by default the rented status to false because it just has been added to the database
	 * @param title
	 * @param yearCreation
	 * @param releaseDate
	 * @param price
	 * @param nameStudio
	 * @param nameDirector
	 * @param description
	 * @param nameMainActor
	 */
	public Movie(String title, String yearCreation, String releaseDate, double price, String nameStudio,
			String nameDirector, String description, String nameMainActor) {
		super();
		this.title = title;
		this.yearCreation = yearCreation;
		this.releaseDate = releaseDate;
		this.price = price;
		this.nameStudio = nameStudio;
		this.nameDirector = nameDirector;
		this.description = description;
		this.nameMainActor = nameMainActor;
		this.rented = false;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getYearCreation() {
		return yearCreation;
	}

	public void setYearCreation(String yearCreation) {
		this.yearCreation = yearCreation;
	}

	public String getReleaseDate() {
		return releaseDate;
	}

	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public String getNameStudio() {
		return nameStudio;
	}

	public void setNameStudio(String nameStudio) {
		this.nameStudio = nameStudio;
	}

	public String getNameDirector() {
		return nameDirector;
	}

	public void setNameDirector(String nameDirector) {
		this.nameDirector = nameDirector;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getNameMainActor() {
		return nameMainActor;
	}

	public void setNameMainActor(String nameMainActor) {
		this.nameMainActor = nameMainActor;
	}

	@Override
	public String toString() {
		return "Movie [Title=" + title + ", yearCreation=" + yearCreation + ", releaseDate=" + releaseDate + ", price="
				+ price + ", nameStudio=" + nameStudio + ", nameDirector=" + nameDirector + ", description="
				+ description + ", nameMainActor=" + nameMainActor + "]";
	}

	public boolean getRented() {
		return rented;
	}

	public void setRented(boolean rented) {
		this.rented = rented;
	}

}
