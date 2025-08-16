package nikita.menu;

import java.io.IOException;

import org.jline.keymap.BindingReader;
import org.jline.keymap.KeyMap;
import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class TerminalMenu<T> {
	private static final String ANSI_CURSOR_UP_FORMAT = "\033[%dA";
	private static final String ANSI_CLEAR_LINE = "\033[2K";
	private static final String ANSI_CURSOR_UP_ONE = "\033[1A";
	private static final String ANSI_UP = "\033[A";
	private static final String ANSI_DOWN = "\033[B";
	private static final String ANSI_ENTER_CR = "\r";
	private static final String ANSI_ENTER_LF = "\n";
	private static final String ANSI_ESC = "\033";

	private static final String ANSI_GREEN_BACKGROUND = "\033[42m";
	private static final String ANSI_WHITE_TEXT = "\033[37m";
	private static final String ANSI_RESET = "\033[0m";

	private static Terminal terminal;

	static {
		try {
			terminal = TerminalBuilder.builder().system(true).jna(true).build();
			terminal.enterRawMode();
		} catch (IOException e) {
			e.printStackTrace();
			System.exit(1);
		}
	}

	private TerminalMenuItem<T>[] items;
	private int selectedIndex = 0;
	private boolean isRunning = false;
	private boolean firstDraw = true;
	private T result = null;
	private String name = null;

	public TerminalMenu() {
	}

	@SafeVarargs
	public final void setItems(TerminalMenuItem<T>... items) {
		this.items = items;
		this.selectedIndex = 0;
		this.firstDraw = true;
	}

	@SafeVarargs
	public TerminalMenu(TerminalMenuItem<T>... items) {
		this.items = items;
	}

	public void setName(String name) {
		this.name = name;
	}

	private int getMenuLineCount() {
		return 2 + items.length;
	}

	public T display() {
		isRunning = true;
		selectedIndex = 0;
		BindingReader bindingReader = new BindingReader(terminal.reader());
		KeyMap<String> keyMap = new KeyMap<>();
		keyMap.bind("UP", ANSI_UP);
		keyMap.bind("DOWN", ANSI_DOWN);
		keyMap.bind("ENTER", ANSI_ENTER_CR);
		keyMap.bind("ENTER", ANSI_ENTER_LF);
		keyMap.bind("ESC", ANSI_ESC);

		while (isRunning) {
			draw(false);
			String key = bindingReader.readBinding(keyMap);
			if ("UP".equals(key)) {
				selectedIndex = (selectedIndex - 1 + items.length) % items.length;
			} else if ("DOWN".equals(key)) {
				selectedIndex = (selectedIndex + 1) % items.length;
			} else if ("ENTER".equals(key)) {
				draw(true);
				try {
					Thread.sleep(500);
				} catch (InterruptedException e) {
					Thread.currentThread().interrupt();
				}
				result = items[selectedIndex].getAction().run();
				isRunning = false;
			} else if ("ESC".equals(key)) {
				hide(true);
			}
		}
		return result;
	}

	private void draw(boolean chosen) {
		int menuLineCount = getMenuLineCount();
		if (!firstDraw) {
			terminal.writer().print(String.format(ANSI_CURSOR_UP_FORMAT, menuLineCount));
		}
		printTopSeparator();
		for (int i = 0; i < items.length; i++) {
			terminal.writer().print(ANSI_CLEAR_LINE);
			if (i == selectedIndex) {
				terminal.writer().println(formatOption(items[i].getName(), true, chosen));
			} else {
				terminal.writer().println(formatOption(items[i].getName(), false, false));
			}
		}
		printBottomSeparator();
		terminal.flush();
		firstDraw = false;
	}

	private String formatOption(String option, boolean isSelected, boolean isChosen) {
		String prefix = isSelected ? "> " : "  ";
		if (isChosen && isSelected) {
			return prefix + ANSI_GREEN_BACKGROUND + ANSI_WHITE_TEXT + option + ANSI_RESET;
		}
		return prefix + option;
	}

	public void hide(boolean clearScreen) {
		if (clearScreen) {
			int totalLinesToClear = getMenuLineCount() + 1;
			for (int i = 0; i < totalLinesToClear; i++) {
				terminal.writer().print(ANSI_CLEAR_LINE);
				if (i < totalLinesToClear - 1) {
					terminal.writer().print(ANSI_CURSOR_UP_ONE);
				}
			}
		}
		isRunning = false;
		terminal.flush();
	}

	private void printTopSeparator() {
		terminal.writer().print(ANSI_CLEAR_LINE);
		int separatorLength = terminal.getWidth();
		if (name != null && !name.trim().isEmpty()) {
			String trimmedName = name.trim();
			String prefix = "--";
			int remainingDashes = separatorLength - prefix.length() - trimmedName.length();
			remainingDashes = Math.max(remainingDashes, 0);
			String dashes = new String(new char[remainingDashes]).replace('\0', '-');
			terminal.writer().println(prefix + trimmedName + dashes);
		} else {
			String dashes = new String(new char[separatorLength]).replace('\0', '-');
			terminal.writer().println(dashes);
		}
	}

	private void printBottomSeparator() {
		terminal.writer().print(ANSI_CLEAR_LINE);
		int separatorLength = terminal.getWidth();
		String dashes = new String(new char[separatorLength]).replace('\0', '-');
		terminal.writer().println(dashes);
	}

	public static void shutdownTerminal() {
		if (terminal != null) {
			try {
				terminal.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
