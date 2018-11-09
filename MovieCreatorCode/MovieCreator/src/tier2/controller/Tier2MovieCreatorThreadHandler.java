package tier2.controller;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import com.google.gson.Gson;

import tier2.view.Tier2MovieCreatorView;
import common.Package;

public class Tier2MovieCreatorThreadHandler  implements Runnable {

	private Socket clientSocket;
	private Socket serverSocket;
	private DataInputStream inputStream;
	private DataOutputStream outputStream;
	private Tier2MovieCreatorView view;
	private String ip;
	
	public Tier2MovieCreatorThreadHandler(Socket clientSocket, Tier2MovieCreatorView view) throws IOException {
		super();
		this.clientSocket = clientSocket;
		this.view = view;
		// Read from stream : String tmp = inputStream.readUTF();
		inputStream = new DataInputStream(clientSocket.getInputStream());

		// Write into stream : outputStream.writeUTF(new String("text to send"));
		outputStream = new DataOutputStream(clientSocket.getOutputStream());

		this.ip = clientSocket.getInetAddress().getHostAddress();
		view.show(ip + " connected");
	}

	/**
	 * This method waits for a request Package from the client then sends a reply Package back to him
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
				Package request = gson.fromJson(line, Package.class);
				view.show("package: " + request.getText());

				// creating reply by communicating with tier 3 server
				Package reply = operation(request);

				// convert to JSon
				// sending reply to client
				String json = gson.toJson(reply);
				outputStream.writeUTF(json);
				view.show("Server to " + ip + "> " + reply);
				if (reply.getText().equalsIgnoreCase("EXIT")) {
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
	 * Method that takes the request Package then uses the model to create a reply Package depending on the request 
	 * @param request The Package received from the client
	 * @return a Package containing what the client requested
	 * @throws IOException 
	 * @throws UnknownHostException 
	 * @see Package
	 */
	
//Are we using packages or something else? What about the package class itself?
	private Package operation(Package request) throws UnknownHostException, IOException {
			serverSocket = new Socket("localhost", 1097);
			// Read from stream : String tmp = inputStream.readUTF();
			DataInputStream inputStream = new DataInputStream(serverSocket.getInputStream());

			// Write into stream : outputStream.writeUTF(new String("text to send"));
			DataOutputStream outputStream = new DataOutputStream(serverSocket.getOutputStream());

			this.ip = clientSocket.getInetAddress().getHostAddress();
			view.show(ip + " connected");
		switch (request.getText()) {
		case Package.GET:
			// sending request to tier 3 server
			Gson gson = new Gson();
			String json = gson.toJson(request);
			outputStream.writeUTF(json);
			
			// getting reply from tier 3 server
			String line = inputStream.readUTF();
			view.show(ip + "> " + line);

			// convert from JSon
			
			Package replyFromServer = gson.fromJson(line, Package.class);
			view.show("package: " + replyFromServer.getText());
			return replyFromServer;
			/*String list = controller.getMovies();
			if (list.length() <= 0)
				return new Package("NO MOVIES", list);
			return new Package(Package.GET, list);

		case Package.CREATE:
			title, yearCreation, releaseDate, price, nameStudio, nameDirector, description, nameMainActor
			controller.createMovie(request.getTitle(), request.getYearCreation(), request.getPrice(), request.getNameStudio(), request.getNameDirector(), request.getDescription(), request.getNameMainActor());
			return new Package("MOVIE CREATED");*/
			
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
