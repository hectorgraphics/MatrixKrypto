/**
 * @author jonathanhector on 27.02.17.
 */
public class TheMatrix {

	public static void main(String[] args) {
		String name = "";
		Prompter prompt = new Prompter(name);
		BoardInitializer init = new BoardInitializer(prompt);
		init.start();
	}
}
