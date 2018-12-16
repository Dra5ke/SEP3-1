package tier2.controller;

import java.io.IOException;
import java.net.UnknownHostException;

import common.Init;
import tier2.view.Tier2MovieManagerView;

public class Tier2MovieManagerController {
	private Tier2MovieManagerView view;
	private Tier2MovieManagerServer server;
	
	
	/**
	 * Constructor that sets view field value to the Tier2MovieManager view,
	 * sets sets server field value to Tier2 server value 
	 * @param view
	 * @throws UnknownHostException
	 */
	public Tier2MovieManagerController(Tier2MovieManagerView view) throws UnknownHostException
	{
		this.view = view;
		try {
			this.server = new Tier2MovieManagerServer(Init.getInstance().getPort(), this); //TODO hardcoded port number
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		Thread t = new Thread(this.server);
		t.start();
	}
	
	/**
	 * 
	 * @return Tier2MovieManagerView class
	 */
	public Tier2MovieManagerView getView()
	{
		return this.view;
	}
}
