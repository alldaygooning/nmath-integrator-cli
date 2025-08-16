package nikita.menu.main;

import java.io.IOException;
import java.math.BigDecimal;

import nikita.math.construct.Precision;
import nikita.math.construct.Variable;
import nikita.math.construct.calculus.integral.Integral;
import nikita.math.construct.expression.Expression;
import nikita.math.exception.construct.integral.IntegrationException;
import nikita.math.solver.integrate.Integrator;
import nikita.math.solver.integrate.Multimodal;
import nikita.menu.RawTerminalInputReader;
import nikita.menu.TerminalMenuAction;
import nikita.menu.captcha.CaptchaMenu;
import nikita.menu.solve.expression.ExpressionMenu;
import nikita.menu.solve.method.MethodMenu;

public class SolveAction implements TerminalMenuAction<String> {

	static final int N = 4;

    @Override
    public String run() {
		// 1. Read Expression input
		Expression expression = new ExpressionMenu().display();
		if (expression == null) {
			return null;
		}

		// 2. Read Interval input
		BigDecimal upper;
		BigDecimal lower;

		while (true) {
			try {
				String input = RawTerminalInputReader.readLine("\nIntegration Interval ([lowerBound; upperBound]): ");
				if (input == null) {
					return null;
				}
				input = input.trim();

				if (input.startsWith("[") && input.endsWith("]")) {
					input = input.substring(1, input.length() - 1).trim();
				}

				String[] parts = input.split(";");

				if (parts.length != 2) {
					System.err.println("\nError: Please provide both bounds separated by ';'");
					continue;
				}

				String upperBound = parts[0].trim();
				String lowerBound = parts[1].trim();

				if (upperBound.isEmpty() || lowerBound.isEmpty()) {
					System.err.println("\nError: Please provide both bounds separated by ';'");
					continue;
				}

				try {
					upper = new BigDecimal(upperBound);
					lower = new BigDecimal(lowerBound);
					if (upper.compareTo(lower) == 0) {
						System.err.println("\nError: Bounds should not be equal.");
						continue;
					}
				} catch (NumberFormatException e) {
					System.err.println("\nError: Both bounds should be valid numbers.");
					continue;
				}
				break;
			} catch (IOException e) {
				System.err.println("\nInput error: " + e.getMessage());
				return null;
			}
		}

		// 3. Read Precision input

		Precision precision;

		while (true) {
			try {
				String input = RawTerminalInputReader.readLine("\nPrecision: ");
				if (input == null) {
					return null;
				}
				input = input.trim();

				try {
					new BigDecimal(input);
				} catch (NumberFormatException e) {
					System.err.println("\nError: Precision should be a valid number.");
					continue;
				}
				precision = new Precision(input);
				break;
			} catch (IOException e) {
				System.err.println("\nInput error: " + e.getMessage());
				return null;
			}
		}
		
		// 4. Read Method input
		Integrator integrator = new MethodMenu().display();
		if (integrator == null) {
			return null;
		}

		// 5. Read CAPTCHA!!!

		while (true) {
			Boolean correct = new CaptchaMenu().display();
			if (correct == null) {
				System.err.println("\nPlease solve captcha to continue.");
				continue;
			}
			if (!correct) {
				System.err.println("\nAre you a robot? Please solve provided captcha correctly!");
				continue;
			}
			break;
		}

		Integral integral = new Integral(expression, new Variable("x"), upper, lower);
		try {
			String method = integrator.getShortName();
			if (integrator instanceof Multimodal) {
				Multimodal multimodal = (Multimodal) integrator;
				method = method + "-" + multimodal.getMode().getShorthand();
			}
			Integrator.evaluate(integral, N, precision, method);
		} catch (IntegrationException e) {
			System.err.println(e.getMessage());
		}

		return "";
    }
}