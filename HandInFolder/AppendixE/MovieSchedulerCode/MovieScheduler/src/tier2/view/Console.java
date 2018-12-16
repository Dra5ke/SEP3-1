package tier2.view;

/**
 * Class that displays the information received from the controller in the console
 * @author Claudiu
 *
 */
public class Console implements Tier2MovieSchedulerView {

	public Console() {
	}

	@Override
	public void show(String text) {
		System.out.println(text);

	}
}
