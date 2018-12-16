package tier2.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import common.Init;
import tier2.view.Tier2MovieSchedulerView;

/**
 * The class that injects the view and starts the server
 * @author Claudiu
 *
 */
public class Tier2MovieSchedulerController {
	/**
	 * Interface to the view on tier2
	 */
	private Tier2MovieSchedulerView view;
	private Tier2MovieSchedulerServer server;
	
	/**
	 * The constructor for this class that throws an exception if the IP of the host can't be indicated
	 * @param view
	 * @throws UnknownHostException
	 */
	public Tier2MovieSchedulerController(Tier2MovieSchedulerView view) throws UnknownHostException
	{
		this.view = view;
		try {
			this.server = new Tier2MovieSchedulerServer(Init.getInstance().getPort(), this);
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Thread t = new Thread(this.server);
		t.start();
	}
	
	public Tier2MovieSchedulerView getView()
	{
		return this.view;
	}
}
