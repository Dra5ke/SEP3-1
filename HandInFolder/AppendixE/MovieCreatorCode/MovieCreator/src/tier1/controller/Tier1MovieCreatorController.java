package tier1.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.Init;
import common.Movie;
import common.Package;
import tier1.view.Tier1MovieCreatorView;

/**
 * Controls data flow from the user to tier2
 * 
 * @author Stefan
 *
 */
public class Tier1MovieCreatorController {
	/**
	 * Socket used to connect to tier2 server
	 */
	private Socket serverSocket;
	/**
	 * The controller has access to the view through an interface
	 */
	private Tier1MovieCreatorView view;
	/**
	 * Stream for receiving information
	 */
	private DataInputStream inputStream;
	/**
	 * Stream for sending information
	 */
	private DataOutputStream outputStream;

	/**
	 * Controller has access to the view through dependency injection. The
	 * connection to tier2 is established, opens input and output streams
	 * 
	 * @param view
	 */
	public Tier1MovieCreatorController(Tier1MovieCreatorView view) {
		try {
			this.view = view;
			view.show("Starting tier1 client");
			serverSocket = new Socket(Init.getInstance().getIp(), Init.getInstance().getPort());

			inputStream = new DataInputStream(serverSocket.getInputStream());

			outputStream = new DataOutputStream(serverSocket.getOutputStream());

		} catch (IOException e) {

			e.printStackTrace();
		}
	}

	/**
	 * Depending on what input the view received from the user, the controller will
	 * handle the data from the user. The controller can use the view in order to
	 * display information to the user or ask them for input
	 * 
	 * @param choice
	 */
	public void execute(int choice) {

		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		Gson gson = gsonBuilder.create();

		switch (choice) {
		case 0:
			System.exit(1);
			break;

		case 1:

			view.show("Creating movie...\n");
			String title = view.get("Title: ");
			String yearCreation = view.get("Creation year: ");
			String releaseDate = view.get("Release Date [DD/MM/YYYY] :");

			String price = view.get("Price: ");
			String nameStudio = view.get("Name of studio: ");
			String nameDirector = view.get("Director name: ");
			String description = view.get("Description: ");
			String nameMainActor = view.get("Main Actor Name: ");
			Movie movie = new Movie(title, yearCreation, releaseDate, nameStudio, nameDirector, description,
					nameMainActor);

			view.show("Movie created! \n");

			Package ADD = new Package("ADD", price, movie);

			String json = gson.toJson(ADD);

			try {

				outputStream.writeUTF(json);
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.show("package: " + request.getBody());
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;

		case 2:
			view.show("Getting movies...");
			String answer;

			try {

				Package GETMOVIES = new Package("GETMOVIES");

				String jsonGET = gson.toJson(GETMOVIES);
				outputStream.writeUTF(jsonGET);
				answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.show("package: " + request.getBody());

			} catch (IOException e) {

				e.printStackTrace();
			}
			break;

		default:
			view.show("INVALID INPUT");
			break;
		}

	}

}