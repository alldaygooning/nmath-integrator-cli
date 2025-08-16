package nikita.menu.captcha;

import java.util.List;

public class Captcha {

private String question;
	private List<String> choices;
	private int correctChoiceIndex;

	public Captcha(String question, List<String> choices, int correctChoiceIndex) {
		if (choices == null || choices.isEmpty()) {
			throw new IllegalArgumentException("Choices cannot be null or empty.");
		}
		if (correctChoiceIndex < 0 || correctChoiceIndex >= choices.size()) {
			throw new IllegalArgumentException("Correct choice index is out of bounds.");
		}
		this.question = question;
		this.choices = choices;
		this.correctChoiceIndex = correctChoiceIndex;
	}

	public String getQuestion() {
		return question;
	}

	public List<String> getChoices() {
		return choices;
	}

	public String getCorrectChoice() {
		return choices.get(correctChoiceIndex);
	}

	public boolean isCorrectAnswer(String answer) {
		if (answer == null) {
			return false;
		}
		return answer.equalsIgnoreCase(getCorrectChoice());
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("Captcha Question: ").append(question).append("\nChoices:\n");
		for (int i = 0; i < choices.size(); i++) {
			sb.append("   ").append(i).append(": ").append(choices.get(i)).append("\n");
    }
		return sb.toString();
}

}