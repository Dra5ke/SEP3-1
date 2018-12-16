package tier2.model;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import common.Movie;

public class Validation {
	private static Validation validation = null;

	/*
	 * A private Constructor prevents any other class from instantiating.
	 */
	private Validation() {
	};

	/* Static 'instance' method */
	public static Validation getInstance() {
		if (validation == null) {
			validation = new Validation();
		}
		return validation;
	}

	public String checkMovie(Movie movie, String price) {
		String result = "";
		if (movie.getTitle() == null)
		{
			result = result + "The Title is null ";
			return result;
		}
		if (movie.getYearCreation() == null)
		{
			result = result + "The Year of Creation is null ";
			return result;
		}
		if (movie.getReleaseDate() == null)
		{
			result = result + "The Release Date is null ";
			return result;
		}
		if (price == null)
		{
			result = result + "The Price is null ";
			return result;
		}
		if (movie.getNameStudio() == null)
		{
			result = result + "The Title is null ";
			return result;
		}
		if (movie.getNameDirector() == null)
		{
			result = result + "The Title is null ";
			return result;
		}
		if (movie.getDescription() == null)
		{
			result = result + "The Title is null ";
			return result;
		}
		if (movie.getNameMainActor() == null)
		{
			result = result + "The Title is null ";
			return result;
		}
		if (movie.getTitle().length() > 60)
			result = result + "The Title is too long ";
		if (movie.getTitle().length() < 2)
			result = result + "The Title is too short ";
		if (movie.getYearCreation().length() > 20)
			result = result + "The year of creation is too long ";
		if (movie.getYearCreation().length() < 2)
			result = result + "The year of creation is too short ";
		if (movie.getReleaseDate().length() > 20)
			result = result + "The release date is too long ";
		if (movie.getReleaseDate().length() < 2)
			result = result + "The release date is too short ";
		if (price.length() < 2)
			result = result + "The price is too short ";
		if (price.length() > 18)
			result = result + "The price is too long ";
		if (movie.getNameStudio().length() > 80)
			result = result + "The name of the studio is too long ";
		if (movie.getNameStudio().length() < 2)
			result = result + "The name of the studio is too short ";
		if (movie.getNameDirector().length() > 60)
			result = result + "The name of the director is too long ";
		if (movie.getNameDirector().length() < 2)
			result = result + "The name of the director is too short ";
		if (movie.getDescription().length() > 200)
			result = result + "The Title is too long ";
		if (movie.getDescription().length() < 2)
			result = result + "The Title is too short";
		if (dateIsValid(movie.getReleaseDate()))
			result = result + "Please enter the date in this exact format: [DD/MM/YYYY] ";
		try {
			Double.parseDouble(price);
		} catch (Exception e) {
			result = result + "Input a price made by digits ";
		}
		try {
			Integer.parseInt(movie.getYearCreation());
		} catch (Exception e) {
			result = result + "Input a creation year made by digits ";
		}
		return result;
	}

	public boolean dateIsValid(String value) {
		try {
			new SimpleDateFormat("dd/mm/yyyy").parse(value);
			return false;
		} catch (ParseException e) {
			return true;
		}
	}
}
