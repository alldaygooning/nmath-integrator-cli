package nikita.menu.solve.method;

import java.util.List;
import java.util.stream.Collectors;

import nikita.math.solver.integrate.Integrator;
import nikita.menu.TerminalMenu;
import nikita.menu.TerminalMenuItem;

public class MethodMenu extends TerminalMenu<Integrator> {

	public MethodMenu() {
		super(getItems());
		setName("Select Integration Method");
	}

	@SuppressWarnings("unchecked")
	private static TerminalMenuItem<Integrator>[] getItems() {
		List<Integrator> integrators = Integrator.INTEGRATORS.values().stream().collect(Collectors.toList());
		TerminalMenuItem<Integrator>[] items = new TerminalMenuItem[integrators.size()];

		int index = 0;
		for (Integrator integrator : integrators) {
			items[index++] = new TerminalMenuItem<Integrator>(integrator.getFullName(), new MethodAction(integrator));
		}
		return items;
	}
}
