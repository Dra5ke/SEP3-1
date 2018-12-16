package tier2;

import java.io.IOException;
import java.net.UnknownHostException;

import tier2.controller.Tier2MovieCreatorController;
import tier2.view.Console;
import tier2.view.Tier2MovieCreatorView;

public class Tier2MovieCreator {

	public static void main(String[] args) throws UnknownHostException, IOException {

		Tier2MovieCreatorView view = new Console();
		@SuppressWarnings("unused")
		Tier2MovieCreatorController controller = new Tier2MovieCreatorController(view);
	}

}
