package nikita.menu.main;

import nikita.menu.TerminalMenuAction;

public class HelpAction implements TerminalMenuAction<String> {

	private static final String HELP_MESSAGE = "\tArrow keys (↑/↓) - menu navigation.\n" + "\tEsc x2 - exit menu.\n"
			+ "\tEnter - confirm selection.";

	@Override
	public String run() {
		System.out.println(HELP_MESSAGE);
		return null;
	}
}
