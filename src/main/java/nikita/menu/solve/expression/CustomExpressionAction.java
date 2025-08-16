package nikita.menu.solve.expression;

import java.io.IOException;

import org.matheclipse.core.eval.ExprEvaluator;
import org.matheclipse.parser.client.SyntaxError;

import nikita.math.construct.expression.Expression;
import nikita.menu.RawTerminalInputReader;
import nikita.menu.TerminalMenuAction;

public class CustomExpressionAction implements TerminalMenuAction<Expression> {

	@Override
	public Expression run() {
		while (true) {
			try {
				String input = RawTerminalInputReader.readLine("Integrand: ");
				if (input == null) {
					return null;
				}
				try {
					ExprEvaluator evaluator = new ExprEvaluator();
					evaluator.eval(input);
					return new Expression(input);
				} catch (SyntaxError e) {
					System.err.println(String.format("\nExpression '%s' contains syntax error(s): %s", input, e.getMessage()));
				}
			} catch (IOException e) {
				System.err.println("Input error: " + e.getMessage());
				return null;
			}
		}
	}
}