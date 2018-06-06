package ch.hectorgraphics.matrix;

import java.util.Scanner;
/**
 * @author jonathanhector on 15.03.17.
 */
public class Prompter {

	private String name;
	private Scanner input = new Scanner(System.in);
	

	public Prompter(String name) {
		this.name = name;
		askName();
	}

	private void askName() {
		System.out.println("Would like to enter your name?");
		System.out.println("'y' for yes\n'n' for no");
		String answer = input.next().toLowerCase();
		switch (answer.charAt(0)) {
			case 'y':
				System.out.println("Please enter your name");
				name = input.next();
				welcomeMessage();
				break;

			case 'n':
				welcomeMessage();
				break;

			default:
				System.out.println("Sorry you have to input an answer!");
				askName();
				break;
		}

	}

	public String welcomeMessage() {
		System.out.println("==================================");
		System.out.printf("   Welcome to the Matrix %s\n", name);
		System.out.println("==================================");
		return name;
	}

	public String getName() {
		return name;
	}
}

