package tier1;

import common.Init;
import tier1.controller.Tier1MovieManagerController;
import tier1.view.Tier1MovieManagerGui;
import tier1.view.Tier1MovieManagerView;





public class Tier1MovieManager {

	
	public static void main(String[] args) 
	{	
		//Init
		Init.getInstance().getData();
		//view
		Tier1MovieManagerView view = new Tier1MovieManagerGui();					
		//controller
		Tier1MovieManagerController controller = new Tier1MovieManagerController(view); 
		//starts thread 
		view.startView(controller);   
	}
}
