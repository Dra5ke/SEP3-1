package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Room {
	@SerializedName("Size")
	@Expose
	private int size;
	@SerializedName("Description")
	@Expose
	private String description;

	public Room(int size, String description) {
		this.size = size;
		this.description = description;
	}

	public Room(String description) {
		this.description = description;
	}

	public int getSize() {
		return size;
	}

	public String getDescription() {
		return description;
	}

	public void setSize(int size) {
		this.size = size;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
