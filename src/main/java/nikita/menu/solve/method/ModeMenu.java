package nikita.menu.solve.method;

import java.util.List;

import nikita.math.solver.integrate.Integrator;
import nikita.math.solver.integrate.IntegratorMode;
import nikita.math.solver.integrate.Multimodal;
import nikita.menu.TerminalMenu;
import nikita.menu.TerminalMenuItem;

public class ModeMenu extends TerminalMenu<IntegratorMode> {

	Integrator integrator;

	@SuppressWarnings("unchecked")
	public ModeMenu(Integrator integrator) {
		this.integrator = integrator;
		setName("Select Mode");
		
		Multimodal multimodal = (Multimodal) integrator;
		List<IntegratorMode> modes = multimodal.getModes();
		TerminalMenuItem<IntegratorMode>[] items = new TerminalMenuItem[modes.size()];

		int index = 0;
		for (IntegratorMode mode : modes) {
			items[index++] = new TerminalMenuItem<IntegratorMode>(mode.getName(), new ModeAction(mode));
		}

		this.setItems(items);
	}
}
