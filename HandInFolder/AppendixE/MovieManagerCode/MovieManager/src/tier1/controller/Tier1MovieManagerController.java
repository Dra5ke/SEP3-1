package tier1.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import common.Init;
import common.Package;
import tier1.view.Tier1MovieManagerView;

public class Tier1MovieManagerController {
	private Socket serverSocket;
	private Tier1MovieManagerView view;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Gson gson;

	/**
	 * Constructor that assigns received argument to local fields,
	 * sets up socket communication and input/output stream, 
	 * switches view to Tier1MovieManager view
	 * @param view
	 */
	public Tier1MovieManagerController(Tier1MovieManagerView view) {
		try {
			this.view = view;
			//view.show("Starting tier1 client");
			serverSocket = new Socket(Init.getInstance().getIp(), Init.getInstance().getPort());

			// Read from stream : String tmp = inputStream.readUTF();
			inputStream = new DataInputStream(serverSocket.getInputStream());
			// Write into stream : outputStream.writeUTF(new String("text to send"));
			outputStream = new DataOutputStream(serverSocket.getOutputStream());

		} catch (IOException e) {

			e.printStackTrace();
		}
	}
/**
 * Based on the parameter received, method sends packages to Tier2 to execute renting or getting available movie list
 * @param choice
 */
	public void execute(int choice) {
		// Choices done for testing
		gson = new Gson();

		switch (choice) {
		case 0:
			System.exit(1);
			break;

		case 1: // case 1 should take an id (for now) and send it to t2.
			
			int id = Integer.parseInt(view.getId());
			
			Package RENT = new Package("RENT", id);

			// send to tier 2 server
			String json = gson.toJson(RENT);

			try {
				// sending 'RENT' package to tier 2 in json format
				outputStream.writeUTF(json);
				String answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				if (request.getHeader().equals("WRONG FORMAT")) {
					view.show(0);
				} 
				else if (!view.getMovies().equals("")){
					view.show(2);
					execute(2);	
				}
				
			} catch (IOException e) {

				e.printStackTrace();
			}
			
			break;

		case 2: // case 2 should get movies... maybe gets them as a list of strings with the id to the left
			// and the rest of the info to the right, the user just needs visual clarification as to what id he needs to input in
			// case 1
			//  t1 doesn't need to know what a movie is just the info it contains.
			//view.show("Getting movies...");
			String answer;

			try {
				// receive from tier 2 server
				Package GETAVAILABLEMOVIES = new Package("GETAVAILABLEMOVIES");

				// send to tier 2 server
				String jsonGET = gson.toJson(GETAVAILABLEMOVIES);
				outputStream.writeUTF(jsonGET);
				answer = inputStream.readUTF();
				Package request = gson.fromJson(answer, Package.class);
				view.showMovies(request.getBody());
				if (request.getBody().equals("")) {
					view.show(1);
				} else {
					view.show(3);
				}
			} catch (IOException e) {

				e.printStackTrace();
			}
			break;
			
		default:
			view.show(0);
			break;
		}

	}

}