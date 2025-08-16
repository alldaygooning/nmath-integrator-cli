package nikita.menu.main;

import nikita.menu.TerminalMenuAction;

public class ExitAction implements TerminalMenuAction<String> {

	@Override
	public String run() {
		System.exit(0);
		return null;
	}
}
