package nikita.menu.captcha;

import java.util.List;
import java.util.Random;

public class Captchas {

	private final List<Captcha> captchaList;
	private final Random random;

	public Captchas() {
		this.random = new Random();
		this.captchaList = List.of(new Captcha("Who meows?", List.of("Dog", "Cat", "Fish", "Bird"), 1),
				new Captcha("Who barks?", List.of("Cat", "Dog", "Student", "Elephant"), 1),
				new Captcha("Who chirps?", List.of("Bird", "Fish", "Cat", "Dog"), 0),
				new Captcha("Who swims?", List.of("Cat", "Dog", "Fish", "Elephant"), 2),
				new Captcha("What is the color of the sky (on a clear day)?", List.of("Green", "Blue", "Red", "Yellow"), 1),
				new Captcha("What is 2 + 2?", List.of("3", "4", "5", "22"), 1),
				new Captcha("Who roars?", List.of("Lion", "Cat", "Dog", "Elephant"), 0),
				new Captcha("Which animal is known as man's best friend?", List.of("Cat", "Dog", "Parrot", "Hamster"), 1),
				new Captcha("What color are most bananas when ripe?", List.of("Blue", "Yellow", "Red", "Green"), 1),
				new Captcha("What is the opposite of cold?", List.of("Hot", "Warm", "Cool", "Freezing"), 0),
				new Captcha("Which fruit is commonly red?", List.of("Banana", "Apple", "Grape", "Kiwi"), 1),
				new Captcha("Who purrs?", List.of("Dog", "Cat", "Bird", "Fish"), 1),
				new Captcha("What do bees produce?", List.of("Milk", "Honey", "Silk", "Bread"), 1),
				new Captcha("What is frozen water called?", List.of("Steam", "Snow", "Ice", "Sleet"), 2),
				new Captcha("What do you call a baby dog?", List.of("Kitten", "Cub", "Puppy", "Chick"), 2),
				new Captcha("Which animal is known for its trunk?", List.of("Giraffe", "Elephant", "Hippo", "Lion"), 1),
				new Captcha("What do you wear on your feet?", List.of("Gloves", "Hat", "Shoes", "Scarf"), 2),
				new Captcha("What is the opposite of up?", List.of("Down", "Left", "Right", "Over"), 0),
				new Captcha("Which planet is known as the Red Planet?", List.of("Earth", "Venus", "Mars", "Jupiter"), 2),
				new Captcha("What do you use to write on paper?", List.of("Brush", "Pen", "Chisel", "Keyboard"), 1));
	}

	public Captcha getRandomCaptcha() {
		int index = random.nextInt(captchaList.size());
		return captchaList.get(index);
	}

}