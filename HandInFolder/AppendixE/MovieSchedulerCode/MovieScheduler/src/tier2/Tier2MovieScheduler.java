package tier2;

import java.net.UnknownHostException;

import common.Init;
import tier2.controller.Tier2MovieSchedulerController;
import tier2.view.Console;
import tier2.view.Tier2MovieSchedulerView;

public class Tier2MovieScheduler {
	public static void main(String[] args) 
	{	
		//Init
		Init.getInstance().getData();
		//view
		Tier2MovieSchedulerView view = new Console();					
		//controller
		try {
			@SuppressWarnings("unused")
			Tier2MovieSchedulerController controller = new Tier2MovieSchedulerController(view);
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
	}
}
