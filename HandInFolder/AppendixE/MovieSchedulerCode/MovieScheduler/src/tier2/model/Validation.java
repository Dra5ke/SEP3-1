package tier2.model;

import java.util.ArrayList;

import common.Movie;
import common.Room;
import common.ScheduledMovie;

public class Validation {
	private static Validation validation = null;
	
	 /* A private Constructor prevents any other
	    * class from instantiating.
	    */
	private Validation() {};
	
	/* Static 'instance' method */
	public static Validation getInstance()
	{
		 if(validation == null) {
			 validation= new Validation();
	      }
	      return validation;
	}
	
	/* Other methods protected by singleton-ness */
	public boolean CheckIfNotAvailableTimeAndDay(String day, String time, ArrayList<ScheduledMovie> list)
	{
		
		for(int i = 0; i < list.size(); i++)
		{
			
			if(list.get(i).getDay().equals(day) && list.get(i).getTime().equals(time))
				
				return true;
		}
		
		return false;
	}
	
	public boolean CheckIfMovieIsNull(Movie movie)
	{
		if(movie == null)
			return true;
		return false;
	}
	
	public boolean CheckIfRoomIsNull(Room room)
	{
		if(room == null)
			return true;
		return false;
	}
}
