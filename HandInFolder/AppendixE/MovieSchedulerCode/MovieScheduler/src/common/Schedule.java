package common;

import java.util.ArrayList;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Schedule {
	@SerializedName("ScheduledMovieList")
	@Expose
	private ArrayList<ScheduledMovie> list;

	public Schedule() {
		list = new ArrayList<ScheduledMovie>();
	}

	public void addScheduledMovie(ScheduledMovie scheduledMovie) {
		list.add(scheduledMovie);
	}

	@Override
	public String toString() {
		return list.toString();
	}

	public ArrayList<ScheduledMovie> getList() {
		return list;
	}
	public void setList(ArrayList<ScheduledMovie> list) {
		this.list = list;
	}
}
