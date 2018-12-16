/**
 * 
 */
package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

/**
 * @author Claudiu
 *
 */
public class Movie {
	@SerializedName("Title")
	@Expose
	private String title;
	@SerializedName("YearCreation")
	@Expose
	private String yearCreation;
	@SerializedName("ReleaseDate")
	@Expose
	private String releaseDate;
	@SerializedName("Price")
	@Expose
	private double price;
	@SerializedName("NameStudio")
	@Expose
	private String nameStudio;
	@SerializedName("NameDirector")
	@Expose
	private String nameDirector;
	@SerializedName("Description")
	@Expose
	private String description;
	@SerializedName("NameMainActor")
	@Expose
	private String nameMainActor;
	@SerializedName("Rented")
	@Expose
	private boolean rented;

	
	/**
	 * Constructor that sets the values of the fields based on the arguments received as parameters
	 * 
	 * @param title
	 */
	public Movie(String title) {
		this.title = title;
	}

	/**
	 * Constructor that sets the values of the fields based on the arguments received as parameters
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

	/**
	 * 
	 * @return String with title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * Methods sets the title of the movie
	 * @param title
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * 
	 * @return String with year of creation
	 */
	public String getYearCreation() {
		return yearCreation;
	}

	/**
	 * Method sets year of creation of the movie
	 * @param yearCreation
	 */
	public void setYearCreation(String yearCreation) {
		this.yearCreation = yearCreation;
	}

	/**
	 * 
	 * @return String with release date of the movie
	 */
	public String getReleaseDate() {
		return releaseDate;
	}

	/**
	 * Method sets the release date of the movie
	 * @param releaseDate
	 */
	public void setReleaseDate(String releaseDate) {
		this.releaseDate = releaseDate;
	}

	/**
	 * 
	 * @return double with the price of the movie
	 */
	public double getPrice() {
		return price;
	}

	/**
	 * Method sets the price of the movie
	 * @param price
	 */
	public void setPrice(double price) {
		this.price = price;
	}

	/**
	 * 
	 * @return String with the name of the studio
	 */
	public String getNameStudio() {
		return nameStudio;
	}

	/**
	 * Method sets the name of the studio
	 * @param nameStudio
	 */
	public void setNameStudio(String nameStudio) {
		this.nameStudio = nameStudio;
	}

	/**
	 * 
	 * @return String with the name of the director
	 */
	public String getNameDirector() {
		return nameDirector;
	}

	/**
	 * Method sets name of the director
	 * @param nameDirector
	 */
	public void setNameDirector(String nameDirector) {
		this.nameDirector = nameDirector;
	}

	/**
	 * 
	 * @return String with the description of the movie
	 */
	public String getDescription() {
		return description;
	}

	/**
	 * Method sets the description of the movie
	 * @param description
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * 
	 * @return String with the name of the main actor
	 */
	public String getNameMainActor() {
		return nameMainActor;
	}

	/**
	 * Method sets the name of main actor
	 * @param nameMainActor
	 */
	public void setNameMainActor(String nameMainActor) {
		this.nameMainActor = nameMainActor;
	}

	@Override
	public String toString() {
		return "Movie [Title=" + title + ", yearCreation=" + yearCreation + ", releaseDate=" + releaseDate + ", price="
				+ price + ", nameStudio=" + nameStudio + ", nameDirector=" + nameDirector + ", description="
				+ description + ", nameMainActor=" + nameMainActor + "]";
	}

	/**
	 * 
	 * @return boolean of whether movie is rented or not
	 */
	public boolean getRented() {
		return rented;
	}

	/**
	 * Methods sets the boolean of whether movie is rented or not
	 * @param rented
	 */
	public void setRented(boolean rented) {
		this.rented = rented;
	}

}
