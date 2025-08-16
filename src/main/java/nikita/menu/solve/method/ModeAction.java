package nikita.menu.solve.method;

import nikita.math.solver.integrate.IntegratorMode;
import nikita.menu.TerminalMenuAction;

public class ModeAction implements TerminalMenuAction<IntegratorMode> {

	IntegratorMode mode;

	public ModeAction(IntegratorMode mode) {
		this.mode = mode;
	}

	@Override
	public IntegratorMode run() {
		return mode;
	}
}
