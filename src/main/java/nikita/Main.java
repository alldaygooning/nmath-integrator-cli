package nikita;

import java.io.IOException;

import nikita.menu.main.MainMenu;

public class Main {

	private static final String PREAMBULE_MESSAGE = "Welcome to Nikita Kopytov's Great Integral Application!";

	public static void main(String[] args) throws IOException {
		System.out.println(PREAMBULE_MESSAGE);


		while (true) {
			new MainMenu().display();
		}
	}
}
