package tier1.view;

import java.util.Scanner;

import tier1.controller.Tier1MovieCreatorController;

public class Tier1MovieCreatorConsole implements Tier1MovieCreatorView, Runnable {

	private Scanner input;
	private Tier1MovieCreatorController controller;

	public Tier1MovieCreatorConsole() {
		input = new Scanner(System.in);
	}

	@Override
	public String get(String text) {
		System.out.print("Enter " + text + ": ");
		return input.nextLine();
	}

	@Override
	public void show(String text) {
		System.out.println(text);

	}

	@Override
	public void startView(Tier1MovieCreatorController controller) {
		this.controller = controller;
		Thread t = new Thread(this);
		t.start();
	}

	@Override
	public void run() {
		boolean continueWorking = true;
		while (continueWorking) {
			// Read input from user input.

			System.out.println("1) Create Movie (Test)");
			System.out.println("2) List Movies (Test)");
			System.out.println("0) Quit");
			System.out.print("\nSelect: ");

			int choice = input.nextInt();
			input.nextLine();

			controller.execute(choice);
			if (choice == 0) {
				continueWorking = false;
			}
		}
	}
}
