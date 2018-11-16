package tier2.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Tier2MovieManagerServer implements Runnable {

	private ServerSocket welcomeSocket;
	private Tier2MovieManagerController controller;

	public Tier2MovieManagerServer(int port, Tier2MovieManagerController controller) throws IOException {
		super();
		this.controller = controller;
		this.welcomeSocket = new ServerSocket(port);
	}

	@Override
	public void run() {
		while (true)
	      {
	         controller.getView().show("Waiting for a client...");
	         try
	         {
	            Socket socket = welcomeSocket.accept();
	            Tier2MovieManagerThreadHandler c;
	            c = new Tier2MovieManagerThreadHandler(socket, controller.getView());
	            Thread t = new Thread(c);
	            t.start();
	            controller.getView().show("Client connected");
	         }
	         catch (IOException e)
	         {
	        	 controller.getView().show("Error in server. Message: " + e.getMessage());
	         }
	      }
	}

}
