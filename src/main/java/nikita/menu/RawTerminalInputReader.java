package nikita.menu;

import java.io.IOException;

import org.jline.terminal.Terminal;
import org.jline.terminal.TerminalBuilder;

public class RawTerminalInputReader {

	public static String readLine(String prompt) throws IOException {
		Terminal terminal = TerminalBuilder.builder().system(true).jna(true).build();

		try {
			terminal.enterRawMode();
			terminal.writer().print(prompt);
			terminal.flush();

			StringBuilder input = new StringBuilder();
			while (true) {
				int c = terminal.reader().read();

				if (c == '\r' || c == '\n') {
					terminal.writer().println();
					return input.toString();
				}
				else if (c == 27) { // ESC key code
					terminal.writer().println();
					return null;
				}
				else if (c == 127 || c == 8) {
					if (input.length() > 0) {
						input.deleteCharAt(input.length() - 1);
						terminal.writer().print("\b \b");
						terminal.flush();
					}
				}
				else if (c == 3) {
					throw new IOException("\nInput interrupted by user");
				}

				else if (c >= 32 && c <= 126) {
					input.append((char) c);
					terminal.writer().print((char) c);
					terminal.flush();
				}
			}
		} finally {
			terminal.close();
		}
	}
}