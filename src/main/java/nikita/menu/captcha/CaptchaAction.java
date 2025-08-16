package nikita.menu.captcha;

import nikita.menu.TerminalMenuAction;

public class CaptchaAction implements TerminalMenuAction<Boolean> {

	Boolean correct;

	public CaptchaAction(Boolean correct) {
		this.correct = correct;
	}

	@Override
	public Boolean run() {
		return correct;
	}
}
