package tier2.controller;

import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import common.Movie;
import common.Package;
import tier2.model.Validation;
import tier2.view.Tier2MovieCreatorView;

/**
 * Class that handles the communication and flow of data from the client
 * 
 * @author Stefan
 *
 */
public class Tier2MovieCreatorThreadHandler implements Runnable {

	/**
	 * Stream for receiving data from the client
	 */
	private DataInputStream inputStream;
	/**
	 * Stream for sending data to the client
	 */
	private DataOutputStream outputStream;
	/**
	 * Socket used to connect to tier3
	 */
	private Socket serverSocket;
	/**
	 * Access to the view
	 */
	private Tier2MovieCreatorView view;
	/**
	 * IP address of the client
	 */
	private String ip;
	/**
	 * A validation class used to check if movie is valid for being persisted
	 */
	private Validation validation = Validation.getInstance();
	/**
	 * Input and output streams are established. Access to the client socket given through dependency injection
	 * @param clientSocket
	 * @param view
	 * @throws IOException
	 */
	public Tier2MovieCreatorThreadHandler(Socket clientSocket, Tier2MovieCreatorView view) throws IOException {
		super();

		inputStream = new DataInputStream(clientSocket.getInputStream());

		outputStream = new DataOutputStream(clientSocket.getOutputStream());

		this.view = view;

		this.ip = clientSocket.getInetAddress().getHostAddress();
		view.show(ip + " connected");
	}

	/**
	 * This method waits for a request Package from the client then sends a reply
	 * Package back to him
	 * 
	 * @see operation
	 * @see Package
	 */
	@Override
	public void run() {
		boolean continueCommuticating = true;
		try {
			while (continueCommuticating) {

				String line = inputStream.readUTF();
				view.show(ip + "> " + line);

				// convert from JSon
				// getting request from client
				Gson gson = new Gson();

				System.out.println(line);
				Package request = gson.fromJson(line, Package.class);
				view.show("package: " + request.getHeader());

				// creating reply by communicating with tier 3 server
				Package reply = operation(request);

				// convert to JSon
				// sending reply to client
				String json = gson.toJson(reply);
				outputStream.writeUTF(json);
				view.show("Server to " + ip + "> " + reply);
				if (reply.getHeader().equalsIgnoreCase("EXIT")) {
					continueCommuticating = false;
				}
			}
			view.show("Closing connection to client: " + ip);

		} catch (Exception e) {
			String message = e.getMessage();
			if (message == null) {
				message = "Connection lost";
			}
			view.show("Error for client: " + ip + " - Message: " + message);
		}
	}

	/**
	 * Method that takes the request Package and depending on the header field creates a reply
	 * Package.
	 * In order to create the reply package socket communication to tier3 is established in this method
	 * @param request
	 *            The Package received from the client
	 * @return a Package containing what the client requested
	 * @throws IOException
	 * @throws UnknownHostException
	 * @see Package
	 */
	private Package operation(Package request) throws UnknownHostException, IOException {

		DataInputStream inputStream;
		DataOutputStream outputStream;
		BufferedReader in;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		Gson gson = gsonBuilder.create();
		String json = "";
		String line = "";
		Package replyFromServer;
		Package requestToServer;

		try {
			view.show("Connecting to tier3 server");
			serverSocket = new Socket("localhost", 1097);
		} catch (IOException e) {
			view.show("Database offline, couldn't connect to server");
			e.printStackTrace();
		}

		switch (request.getHeader()) {
		case Package.GETMOVIES:
			// Read from database server stream
			inputStream = new DataInputStream(serverSocket.getInputStream());

			// Write into database server stream
			outputStream = new DataOutputStream(serverSocket.getOutputStream());
			// sending request to tier 3 server

			json = gson.toJson(request);
			outputStream.writeUTF(json);

			// getting reply from tier 3 server
			// Makes sure the message is read in UTF8
			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream(), "UTF8"));
			line = in.readLine();
			// line = inputStream.readUTF();
			view.show(ip + "> " + line);

			// convert from JSon

			replyFromServer = gson.fromJson(line, Package.class);
			view.show("package: " + replyFromServer.getBody());
			// Close the streams when you are done
			inputStream.close();
			outputStream.close();

			return replyFromServer;

		case Package.ADD:
			String validationResult = validation.checkMovie(request.getMovie(), request.getBody());
			if (validationResult.equals("")) {
				// Read from server stream
				inputStream = new DataInputStream(serverSocket.getInputStream());

				// Write into server stream
				outputStream = new DataOutputStream(serverSocket.getOutputStream());
				// Preparing the movie to be sent
				request.getMovie().setPrice(Double.parseDouble(request.getBody()));
				Movie movieToSend = request.getMovie();
				// sending request to tier 3 server
				requestToServer = new Package("ADD", movieToSend);
				json = gson.toJson(requestToServer);
				outputStream.writeUTF(json);

				// getting reply from tier 3 server
				// Makes sure the message is read in UTF8
				in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream(), "UTF8"));
				line = in.readLine();
				view.show(ip + "> " + line);

				// convert from JSon
				replyFromServer = gson.fromJson(line, Package.class);
				view.show("package: " + replyFromServer.getBody());

				// Close the streams when you are done
				inputStream.close();
				outputStream.close();
				return replyFromServer;
			} else {
				return new Package("401", validationResult);
			}

		default:
			return new Package("WRONG FORMAT");

		}
	}

	public void close() {
		try {
			inputStream.close();
			outputStream.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
