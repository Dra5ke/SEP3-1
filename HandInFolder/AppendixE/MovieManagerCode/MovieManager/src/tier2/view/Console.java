package tier2.view;

public class Console implements Tier2MovieManagerView {

	public Console() {
	}
   
	/**
	 * Method displays text received as parameter in the view
	 * @param text
	 */
	@Override
	public void show(String text) {
		System.out.println(text);

	}
}
