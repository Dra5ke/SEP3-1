package common;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Package {
	public static final String GETMOVIES = "GETMOVIES";
	public static final String RENT = "RENT";
	public static final String EXIT = "EXIT";
	public static final String GETAVAILABLEMOVIES = "GETAVAILABLEMOVIES";
	
	@SerializedName("Header")
	@Expose
	private String header;
	@SerializedName("Body")
	@Expose
	private String body;
	@SerializedName("Id")
	@Expose
	private int id;

	
	/**
	 * Constructor that sets the values of the fields based on the arguments received
	 * @param header
	 */
	public Package(String header) {
		this.header = header;
	}

   /**
   * Constructor that sets the values of the fields based on the arguments received
   * @param header
   * @param body
   */
	public Package(String header, String body) {
		this.header = header;
		this.body = body;
	}

	/**
	 * Constructor that sets the values of the fields based on the arguments received
	 * @param header
	 * @param id
	 */
	public Package(String header, int id) {
		this.header = header;
		this.id = id;
	}

	/**
	 * 
	 * @return String with the body of the package
	 */
	public String getBody() {
		return body;
	}

	/**
	 * Method sets the body of the package
	 * @param body
	 */
	public void setBody(String body) {
		this.body = body;
	}

	/**
	 * Method sets the header of the package
	 * @param header
	 */
	public void setHeader(String header) {
		this.header = header;
	}

	/**
	 * @return String with header of the package
	 */
	public String getHeader() {
		return header;
	}

	/**
	 * Method sets Id for the package
	 * @param id
	 */
	public void setId(int id) {
		this.id = id;
	}

	/**
	 * 
	 * @return Integer with id of package
	 */
	public int getId() {
		return id;
	}
}
