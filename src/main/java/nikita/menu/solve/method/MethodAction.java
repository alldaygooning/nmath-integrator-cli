package nikita.menu.solve.method;

import nikita.math.solver.integrate.Integrator;
import nikita.math.solver.integrate.IntegratorMode;
import nikita.math.solver.integrate.Multimodal;
import nikita.menu.TerminalMenuAction;

public class MethodAction implements TerminalMenuAction<Integrator> {

	private Integrator integrator;

	public MethodAction(Integrator integrator) {
		this.integrator = integrator;
	}

	@Override
	public Integrator run() {
		if (integrator instanceof Multimodal) {
			IntegratorMode mode = new ModeMenu(integrator).display();
			if (mode == null) {
				return null;
			}
			((Multimodal) integrator).setMode(mode);
		}
		return integrator;
	}
}
