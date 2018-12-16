package tier1.controller;

import java.io.BufferedWriter;
import java.io.DataInputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.net.Socket;

import com.google.gson.Gson;

import common.Init;
import common.Movie;
import common.Package;
import common.Room;
import common.ScheduledMovie;
import tier1.view.Tier1MovieSchedulerView;

/**
 * The class that controls the data flow to Tier 2 in the Movie Scheduler component
 * @author Claudiu
 *
 */
public class Tier1MovieSchedulerController {
	/**
	 * The socket to create the connection to the server on Tier 2
	 */
	private Socket serverSocket;
	/**
	 * The Interface for the view
	 */
	private Tier1MovieSchedulerView view;
	/**
	 * The stream from which the controller will receive information
	 */
	private DataInputStream inputStream;
	/**
	 * The stream from which the controller will output information to
	 */
	//private DataOutputStream outputStream;
	private BufferedWriter outputStream;
	/**
	 * A Gson Object that it's used to translate java Objects into json Objects
	 */
	private Gson gson;


	/**
	 * A constructor to inject the view and set up the input and output streams
	 * @param view
	 */
	public Tier1MovieSchedulerController(Tier1MovieSchedulerView view) {
		try {
			this.view = view;
			serverSocket = new Socket(Init.getInstance().getIp(), Init.getInstance().getPort());

			// Read from stream 
			inputStream = new DataInputStream(serverSocket.getInputStream());
			// Write into stream 
			//new BufferedReader(new InputStreamReader(inputStream, "UTF8"))
			outputStream = new BufferedWriter(new OutputStreamWriter(serverSocket.getOutputStream(), "UTF8"));

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Method to execute the choice made by the user in the view that hasd multiple cases
	 * case 0: EXIT
	 * case 1: Displaying the information needed to schedule a movie
	 * case 2: Sending the schedule through tier 2 to the database
	 * case 3: Canceling the scheduling
	 * case 4: Getting an displaying the list of rooms available
	 * case 5: Displaying the Schedule and the available rented movies
	 * @param choice
	 */
	public void execute(int choice) {
		// Choices done for testing
		gson = new Gson();

		switch (choice) {
		case 0:
			System.exit(1);
			break;

		case 1:
			// case 1 should get a list of rooms from t2, display it (does it receive an
			// array list of rooms?), select Id,
			// display list of days of the week, select day, display standardized time,
			// select time, get a list of rented movies from
			// t2, display list of rented movies, select the id of a rented movie, create
			// scheduled movie with info, send scheduled movie
			// then ask if continue (repeat) or finish (SENDSCHEDULE package to let t2 know
			// we're done) or CANCEL (send CANCELSCHEDULE package
			// to let t2 know we reset the schedule).
			Package GETROOMS = new Package("GETROOMS");
			// send package to tier 2 server
			String json = gson.toJson(GETROOMS);
			try {
				// sending 'GETROOMS' package to tier 2 in json format
				//outputStream.write(json+"\n",0,(json+"\n").length());
				outputStream.write(json);
				outputStream.newLine();
				outputStream.flush();
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showRooms(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			// Send request for receiving rented movies
			Package GETRENTEDMOVIES = new Package("GETRENTEDMOVIES");
			String json1 = gson.toJson(GETRENTEDMOVIES);
			try {
				outputStream.write(json1);
				outputStream.newLine();
				outputStream.flush();
				// sending 'GETRENTEDMOVIES' package to tier 2 in json format
				//outputStream.write(json1+"\n",0,(json1+"\n").length());
				//outputStream.write(json1+"\n");
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showMovies(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			view.showTime(
					"Available days: Monday, Tuesday, Wednesday, Thursday, Friday, Saturday, Sunday \n Select time: 10:00, 13:00, 16:00, 20:00");
			break;
		case 2:
			// finishes schedule, sends package
			Package SENDSCHEDULE = new Package("SENDSCHEDULE");
			String json9 = gson.toJson(SENDSCHEDULE);
			try {
				// sending 'SENDSCHEDULE' package to tier 2 in json format
				outputStream.write(json9);
				outputStream.newLine();
				outputStream.flush();
				//outputStream.write(json9+"\n",0,(json9+"\n").length());
				//outputStream.write(json9+"\n");
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showSchedule(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;
		case 3:
			// cancel schedule, send package
			Package CANCELSCHEDULE = new Package("CANCELSCHEDULE");
			String json10 = gson.toJson(CANCELSCHEDULE);
			try {
				// sending 'CANCELSCHEDULE' package to tier 2 in json format
				outputStream.write(json10);
				outputStream.newLine();
				outputStream.flush();
				//outputStream.write(json10+"\n",0,(json10+"\n").length());
				//outputStream.write(json10+"\n");
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showSchedule(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;

		case 4: // get all rooms
			view.showRooms("Getting rooms...\n");
			Package GETROOMS1 = new Package("GETROOMS");

			// send to tier 2 server
			String json4 = gson.toJson(GETROOMS1);
			try {
				// sending 'GETROOMS' package to tier 2 in json format
				outputStream.write(json4);
				outputStream.newLine();
				outputStream.flush();
				//outputStream.write(json4+"\n",0,(json4+"\n").length());
				//outputStream.write(json4+"\n");
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showRooms(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;

		case 5: // get current schedule, empty package request to get schedule
			view.showSchedule("Getting schedule...\n");
			Package GETSCHEDULE = new Package("GETSCHEDULE");

			// send to tier 2 server
			String json6 = gson.toJson(GETSCHEDULE);
			view.showSchedule("Request sent.");
			try {
				// sending 'GETSCHEDULE' package to tier 2 in json format
				outputStream.write(json6);
				outputStream.newLine();
				outputStream.flush();
				//outputStream.write(json6+"\n",0,(json6+"\n").length());
				//outputStream.write(json6+"\n");
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showSchedule(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}

			// Send request for receiving rented movies
			Package GETRENTEDMOVIES2 = new Package("GETRENTEDMOVIES");
			String json8 = gson.toJson(GETRENTEDMOVIES2);
			try {
				// sending 'GETRENTEDMOVIES' package to tier 2 in json format
				outputStream.write(json8);
				outputStream.newLine();
				outputStream.flush();
				//outputStream.write(json8+"\n",0,(json8+"\n").length());
				//outputStream.write(json8+"\n");
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showMovies(request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;

		default:
			view.showSchedule("INVALID INPUT");
			break;
		}

	}

	/**
	 * A method to delete a room using the ID of the room
	 * @param ID
	 */
	public void deleteRoom(String ID) {
		Package REMOVEROOM = new Package("REMOVEROOM", ID);

		// send to tier 2 server
		String json5 = gson.toJson(REMOVEROOM);
		view.showRooms("Id sent.");
		try {
			// sending 'REMOVEROOM' package to tier 2 in json format
			outputStream.write(json5);
			outputStream.newLine();
			outputStream.flush();
			//outputStream.write(json5+"\n",0,(json5+"\n").length());
			//outputStream.write(json5+"\n");
			String answer = inputStream.readUTF();
			Package request = gson.fromJson(answer, Package.class);
			view.showRooms(request.getBody());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Sending the details about a Room to have the size checked if it is the proper format on tier 2
	 * @param size
	 * @param description
	 */
	public void addRoom(String size, String description) {

		Room room = new Room(description);
		Package ADDROOM = new Package("ADDROOM", size, room);

		// send to tier 2 server
		String json3 = gson.toJson(ADDROOM);

		try {
			// sending 'ADDROOM' package to tier 2 in json format
			outputStream.write(json3);
			outputStream.newLine();
			outputStream.flush();
			//outputStream.write(json3+"\n",0,(json3+"\n").length());
			//outputStream.write(json3+"\n");
			String answer = inputStream.readUTF();
			Package request = gson.fromJson(answer, Package.class);
			view.showRooms(request.getBody());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Sending the details of the scheduled movie to be checked on tier 2
	 * @param roomId
	 * @param movieId
	 * @param day
	 * @param time
	 */
	public void addScheduledMovie(String roomId, String movieId, String day, String time) {
		Room room = null;
		// After selecting id, send package with it to t2
		Package GETROOM = new Package("GETROOM", roomId);
		String json7 = gson.toJson(GETROOM);
		try {
			// sending 'GETROOM' package to tier 2 in json format
			outputStream.write(json7);
			outputStream.newLine();
			outputStream.flush();
			//outputStream.write(json7+"\n",0,(json7+"\n").length());
			//outputStream.write(json7+"\n");
			String answer = inputStream.readUTF();
			Package request = gson.fromJson(answer, Package.class);
			// check if the database provided you with the room
			if (request.getRoom() != null) {
				room = request.getRoom();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		Movie movie = null;
		// After selecting id, send package with it to t2
		Package GETMOVIE = new Package("GETMOVIE", movieId);
		String json8 = gson.toJson(GETMOVIE);
		try {
			// sending 'GETMOVIE' package to tier 2 in json format
			outputStream.write(json8);
			outputStream.newLine();
			outputStream.flush();
			//outputStream.write(json8+"\n",0,(json8+"\n").length());
			//outputStream.write(json8+"\n");
			String answer = inputStream.readUTF();
			Package request = gson.fromJson(answer, Package.class);
			// check if the database provided you with the movie
			if (request.getMovie() != null) {
				movie = request.getMovie();
			}
		} catch (IOException e) {

			e.printStackTrace();
		}
		ScheduledMovie scheduledMovie = new ScheduledMovie(time, day, movie, room);

		Package SCHEDULEDMOVIE = new Package("SCHEDULEDMOVIE", scheduledMovie);
		String json2 = gson.toJson(SCHEDULEDMOVIE);
		try {
			// sending 'SCHEDULEDMOVIE' package to tier 2 in json format
			outputStream.write(json2);
			outputStream.newLine();
			outputStream.flush();
			//outputStream.write(json2+"\n",0,(json2+"\n").length());
			//outputStream.write(json2+"\n");
			String answer = inputStream.readUTF();
			Package request = gson.fromJson(answer, Package.class);
			view.showSchedule(request.getBody());
		} catch (IOException e) {

			e.printStackTrace();
		}
	}

}