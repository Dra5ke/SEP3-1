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

import common.Init;
import common.Package;
import tier2.view.Tier2MovieManagerView;

public class Tier2MovieManagerThreadHandler implements Runnable {

	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Socket serverSocket;
	private Tier2MovieManagerView view;
	private String ip;

	
	/**
	 * Construct that assigns received argument to local field, 
	 *  sets up input and output streams
	 * @param clientSocket
	 * @param view
	 * @throws IOException
	 */
	public Tier2MovieManagerThreadHandler(Socket clientSocket, Tier2MovieManagerView view) throws IOException {
		super();

		// Read from client stream
		inputStream = new DataInputStream(clientSocket.getInputStream());

		// Write into client stream
		outputStream = new DataOutputStream(clientSocket.getOutputStream());

		this.view = view;

		this.ip = clientSocket.getInetAddress().getHostAddress();
		view.show(ip + " connected");
	}

	
	/**
	 * Method that read the request from the clients stream,  
	 * gets data from Tier 3 server and send a reply back to the client
	 */
	@Override
	public void run() {
		boolean continueCommuticating = true;
		try {
			while (continueCommuticating) {

				String line = inputStream.readUTF();
				view.show(ip + "> " + line);

				Gson gson = new Gson();
				System.out.println(line);
				Package request = gson.fromJson(line, Package.class);
				view.show("package: " + request.getHeader());

				Package reply = operation(request);

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
	 * Method that takes the request Package then uses the model to create a reply
	 * Package depending on the request
	 * 
	 * @param request
	 *            The Package received from the client
	 * @return a Package containing what the client requested
	 * @throws IOException
	 * @throws UnknownHostException
	 * @see Package
	 */
	private Package operation(Package request) throws IOException {

		DataInputStream inputStream;
		DataOutputStream outputStream;
		BufferedReader in;
		GsonBuilder gsonBuilder = new GsonBuilder();
		gsonBuilder.serializeNulls();
		Gson gson = gsonBuilder.create();
		String json = "";
		String line = "";
		Package replyFromServer;

		try {
			view.show("Connecting to tier3 server");
			serverSocket = new Socket(Init.getInstance().getIpDb(), Init.getInstance().getPortDb());
		} catch (IOException e) {
			view.show("Database offline, couldn't connect to server");
			e.printStackTrace();
		}

		switch (request.getHeader()) {
		case Package.GETAVAILABLEMOVIES:
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
			
			view.show(ip + "> " + line);

			// convert from JSon
			replyFromServer = gson.fromJson(line, Package.class);
			view.show("package: " + replyFromServer.getBody());
			
			// Close the streams when you are done
			inputStream.close();
			outputStream.close();

			return replyFromServer;

		case Package.RENT:
			inputStream = new DataInputStream(serverSocket.getInputStream());

			outputStream = new DataOutputStream(serverSocket.getOutputStream());

			json = gson.toJson(request);
			outputStream.writeUTF(json);

			in = new BufferedReader(new InputStreamReader(serverSocket.getInputStream(), "UTF8"));
			line = in.readLine();
			view.show(ip + "> " + line);

			replyFromServer = gson.fromJson(line, Package.class);
			view.show("package: " + replyFromServer.getBody());

			inputStream.close();
			outputStream.close();

			return replyFromServer;

		default:
			return new Package("WRONG FORMAT");
		}
	}

}
