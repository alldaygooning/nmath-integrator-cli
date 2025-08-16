package nikita.menu.solve.expression;

import nikita.math.construct.expression.Expression;
import nikita.menu.TerminalMenu;
import nikita.menu.TerminalMenuItem;

public class ExpressionMenu extends TerminalMenu<Expression> {

	private static final Expression EXPRESSION_1 = new Expression("2x^3-3x^2+5x-9");
	private static final Expression EXPRESSION_2 = new Expression("x^2");
	private static final Expression EXPRESSION_3 = new Expression("e^(-x^2)");
	private static final Expression EXPRESSION_4 = new Expression("sin(x)/x");
	private static final Expression EXPRESSION_5 = new Expression("sin(x)*ln(x)");
	private static final Expression EXPRESSION_6 = new Expression(
			"tan(x) + cot(3*x) + (x^3 + sin(x))/(x + 1) + e^x/(ln(x + 2) + 3) + cos(x)/(x^2 + 3.14159) + sqrt(x - 1)/(e + x^2) + arctan(x)/(ln(x + 3.14159) + e) + ((x + 5)^2)/(sin(x) + cos(x) + 1)");
	private static final Expression EXPRESSION_7 = new Expression("1/(ln(x^2))");

	private static final TerminalMenuItem<Expression> OPTION_1 = new TerminalMenuItem<>(EXPRESSION_1.toString(),
			new ExpressionAction(EXPRESSION_1));
	private static final TerminalMenuItem<Expression> OPTION_2 = new TerminalMenuItem<>(EXPRESSION_2.toString(),
			new ExpressionAction(EXPRESSION_2));
	private static final TerminalMenuItem<Expression> OPTION_3 = new TerminalMenuItem<>(EXPRESSION_3.toString(),
			new ExpressionAction(EXPRESSION_3));
	private static final TerminalMenuItem<Expression> OPTION_4 = new TerminalMenuItem<>(EXPRESSION_4.toString(),
			new ExpressionAction(EXPRESSION_4));
	private static final TerminalMenuItem<Expression> OPTION_5 = new TerminalMenuItem<>(EXPRESSION_5.toString(),
			new ExpressionAction(EXPRESSION_5));
	private static final TerminalMenuItem<Expression> OPTION_6 = new TerminalMenuItem<>(EXPRESSION_6.toString(),
			new ExpressionAction(EXPRESSION_6));
	private static final TerminalMenuItem<Expression> OPTION_7 = new TerminalMenuItem<Expression>(EXPRESSION_7.toString(),
			new ExpressionAction(EXPRESSION_7));
	private static final TerminalMenuItem<Expression> CUSTOM = new TerminalMenuItem<>("Custom (keyboard input)",
			new CustomExpressionAction());

	public ExpressionMenu() {
		super(OPTION_1, OPTION_2, OPTION_3, OPTION_4, OPTION_5, OPTION_6, OPTION_7, CUSTOM);
		setName("Select Integrand");
	}
}
