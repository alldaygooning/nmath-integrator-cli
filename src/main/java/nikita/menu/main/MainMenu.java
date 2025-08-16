package nikita.menu.main;

import nikita.menu.TerminalMenu;
import nikita.menu.TerminalMenuItem;

public class MainMenu extends TerminalMenu<String> {
	private static final String NAME = "Main menu";

	private static final TerminalMenuItem<String> EXIT = new TerminalMenuItem<String>("Exit", new ExitAction());
	private static final TerminalMenuItem<String> HELP = new TerminalMenuItem<String>("Help", new HelpAction());
	private static final TerminalMenuItem<String> SOLVE = new TerminalMenuItem<String>("Solve", new SolveAction());
	public MainMenu() {
		super(HELP, SOLVE, EXIT);
		this.setName(NAME);
	}
}
