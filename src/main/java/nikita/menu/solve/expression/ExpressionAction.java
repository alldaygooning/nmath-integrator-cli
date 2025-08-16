package nikita.menu.solve.expression;

import nikita.math.construct.expression.Expression;
import nikita.menu.TerminalMenuAction;

public class ExpressionAction implements TerminalMenuAction<Expression> {

	Expression expression;

	public ExpressionAction(Expression expression) {
		this.expression = expression;
	}

	@Override
	public Expression run() {
		return this.expression;
	}
}
