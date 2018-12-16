package tier1.view;

import tier1.controller.Tier1MovieManagerController;

public interface Tier1MovieManagerView {
	
	String getId();
	
	String getMovies();

	void showMovies(String text);

	void show(int a);
	
	void startView(Tier1MovieManagerController controller);
}
