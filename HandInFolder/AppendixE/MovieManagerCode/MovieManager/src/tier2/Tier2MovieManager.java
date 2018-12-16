package tier2;

import java.net.UnknownHostException;

import common.Init;
import tier2.controller.Tier2MovieManagerController;
import tier2.view.Console;
import tier2.view.Tier2MovieManagerView;





public class Tier2MovieManager {

	
	public static void main(String[] args) 
	{	
		//Init
		Init.getInstance().getData();
		//view
		Tier2MovieManagerView view = new Console();					
		//controller
		try {
			@SuppressWarnings("unused")
			Tier2MovieManagerController controller = new Tier2MovieManagerController(view);
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} 
	}
}
