package tier2.view;
public class Console implements Tier2MovieCreatorView {

	public Console() {
	}

	@Override
	public void show(String text) {
		System.out.println(text);

	}

}
