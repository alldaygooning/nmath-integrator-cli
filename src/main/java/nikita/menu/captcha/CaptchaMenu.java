package nikita.menu.captcha;

import java.util.List;

import nikita.menu.TerminalMenu;
import nikita.menu.TerminalMenuItem;

public class CaptchaMenu extends TerminalMenu<Boolean> {

	public CaptchaMenu() {
		Captcha captcha = new Captchas().getRandomCaptcha();
		setName("Captcha: " + captcha.getQuestion());

		List<String> choices = captcha.getChoices();
		@SuppressWarnings("unchecked")
		TerminalMenuItem<Boolean>[] items = new TerminalMenuItem[choices.size()];

		for (int i = 0; i < choices.size(); i++) {
			items[i] = new TerminalMenuItem<>(choices.get(i), new CaptchaAction(captcha.isCorrectAnswer(choices.get(i))));
		}

		this.setItems(items);
	}
}
