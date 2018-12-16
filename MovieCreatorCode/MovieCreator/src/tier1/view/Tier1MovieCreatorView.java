package tier1.view;

import tier1.controller.Tier1MovieCreatorController;
/**
 * The interface to the view. Other parts of the system will only have access to this
 * @author Stefan
 *
 */
public interface Tier1MovieCreatorView {
	/**
	 * Displays a message to the user and takes input from them
	 * @param string
	 * @return the data from the user
	 */
	String get(String string);
	/**
	 * Displays a message to the user
	 * @param text
	 */
	void show(String text);
	/**
	 * Used to inject the controller and launch the view
	 * @param controller
	 */
	void startView(Tier1MovieCreatorController controller);
	
}
