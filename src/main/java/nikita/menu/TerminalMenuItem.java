package nikita.menu;

public class TerminalMenuItem<T> {
	private String name;
	private TerminalMenuAction<T> action;

	public TerminalMenuItem(String name, TerminalMenuAction<T> action) {
		this.name = name;
		this.action = action;
	}

	public String getName() {
		return name;
	}

	public TerminalMenuAction<T> getAction() {
		return action;
	}
}
