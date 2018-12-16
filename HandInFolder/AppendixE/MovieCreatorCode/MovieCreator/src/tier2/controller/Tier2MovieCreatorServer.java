package tier2.controller;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
/**
 * Class used to listen for connections from clients.
 * Once a client connects they will be put into a thread in order to continue listening for more
 * @author Stefan
 *
 */
public class Tier2MovieCreatorServer implements Runnable {
	/**
	 * Socket used to accept connections from the clients
	 */
	private ServerSocket welcomeSocket;
	/**
	 * The controller from tier2
	 */
	private Tier2MovieCreatorController controller;
	/**
	 * Injects the controller and tells the socket to start listening for connections at the given port
	 * @param port
	 * @param controller
	 * @throws IOException
	 */
	public Tier2MovieCreatorServer(int port, Tier2MovieCreatorController controller) throws IOException {
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
	            Tier2MovieCreatorThreadHandler c;
	            c = new Tier2MovieCreatorThreadHandler(socket, controller.getView());
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
