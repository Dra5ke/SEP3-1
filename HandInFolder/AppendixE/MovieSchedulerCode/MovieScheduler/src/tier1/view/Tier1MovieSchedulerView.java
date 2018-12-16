package tier1.view;

import tier1.controller.Tier1MovieSchedulerController;

public interface Tier1MovieSchedulerView {

	/**
	 * displays the Room related information
	 * @param text
	 */
	void showRooms(String text);
	
	/**
	 * displays the Movies related information
	 * @param text
	 */
	void showMovies(String text);

	/**
	 * displays the Schedule related information
	 * @param text
	 */
	void showSchedule(String text);
	
	/**
	 * displays the list of available days and times
	 * @param text
	 */
	void showTime(String text);

	/**
	 * Starting the view when the controller has been initiated so communication is established
	 * @param controller
	 */
	void startView(Tier1MovieSchedulerController controller);

}
