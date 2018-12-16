package tier2.controller;

import java.io.IOException;
import java.net.UnknownHostException;
import tier2.view.Tier2MovieCreatorView;
/**
 * This class starts the server in a thread
 * @author Stefan
 *
 */
public class Tier2MovieCreatorController {
	/**
	 * The controller has access to the view through an interface
	 */
	private Tier2MovieCreatorView view;
	/**
	 * The controller has access to the server
	 */
	private Tier2MovieCreatorServer server;
	/**
	 * The controller has access to the view through dependency injection
	 * @param view
	 * @throws UnknownHostException
	 */
	public Tier2MovieCreatorController(Tier2MovieCreatorView view) throws UnknownHostException {
		
		this.view = view;
		try {
			this.server = new Tier2MovieCreatorServer(1098, this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Thread t = new Thread(this.server);
		t.start();
	}

	public Tier2MovieCreatorView getView() {
		return this.view;
	}
}
