package tier2.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import com.google.gson.Gson;

import common.Package;
import tier2.view.Tier2MovieManagerView;
import tier2.view.Tier2MovieManagerView;

public class Tier2MovieManagerThreadHandler implements Runnable {

	private Socket clientSocket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Socket serverSocket;
	private Tier2MovieManagerView view;
	private String ip;

	public Tier2MovieManagerThreadHandler(Socket clientSocket, Tier2MovieManagerView view) throws IOException {
		super();
		// Connecting to client socket
		this.clientSocket = clientSocket;

		// Read from client stream
		inputStream = new DataInputStream(clientSocket.getInputStream());

		// Write into client stream
		outputStream = new DataOutputStream(clientSocket.getOutputStream());

		this.view = view;

		this.ip = clientSocket.getInetAddress().getHostAddress();
		view.show(ip + " connected");
	}

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

	private Package operation(Package request) throws IOException {
		DataInputStream inputStream;
		DataOutputStream outputStream;
		Gson gson = new Gson();
		String json = "";
		String line = "";
		Package replyFromServer;

		try {
			view.show("Connecting to tier3 server");
			serverSocket = new Socket("localhost", 1097);
		} catch (IOException e) {
			view.show("Database offline, couldn't connect to server");
			e.printStackTrace();
		}

		switch (request.getHeader()) {
		case Package.GET:
			// Read from database server stream
			inputStream = new DataInputStream(serverSocket.getInputStream());

			// Write into database server stream
			outputStream = new DataOutputStream(serverSocket.getOutputStream());
			// sending request to tier 3 server
			json = gson.toJson(request);

			// getting reply from tier 3 server
			line = inputStream.readUTF();
			view.show(ip + "> " + line);

			// convert from JSon

			replyFromServer = gson.fromJson(line, Package.class);
			view.show("package: " + replyFromServer.getBody());
			// Close the streams when you are done
			inputStream.close();
			outputStream.close();

			return replyFromServer;

		case Package.ADD:
			// Read from database server stream
			inputStream = new DataInputStream(serverSocket.getInputStream());

			// Write into database server stream
			outputStream = new DataOutputStream(serverSocket.getOutputStream());
			// sending request to tier 3 server

			json = gson.toJson(request);
			outputStream.writeUTF(json);

			// getting reply from tier 3 server
			line = inputStream.readUTF();
			view.show(ip + "> " + line);

			// convert from JSon

			replyFromServer = gson.fromJson(line, Package.class);
			view.show("package: " + replyFromServer.getBody());
			// Close the streams when you are done
			inputStream.close();
			outputStream.close();

			return replyFromServer;

		default:
			return new Package("WRONG FORMAT");
		}
	}

}
