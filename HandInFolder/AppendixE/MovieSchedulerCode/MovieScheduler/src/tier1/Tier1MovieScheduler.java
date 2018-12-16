package tier1;

import common.Init;
import tier1.controller.Tier1MovieSchedulerController;
import tier1.view.Tier1MovieSchedulerGUI;
import tier1.view.Tier1MovieSchedulerView;

public class Tier1MovieScheduler {
	public static void main(String[] args) 
	{	
		//Init
		Init.getInstance().getData();
		//view
		Tier1MovieSchedulerView view = new Tier1MovieSchedulerGUI();					
		//controller
		Tier1MovieSchedulerController controller = new Tier1MovieSchedulerController(view); 
		//starts thread in it
		view.startView(controller);   
		
	}
}
