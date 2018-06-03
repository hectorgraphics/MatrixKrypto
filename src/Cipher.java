import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class Cipher {

	private char[] reference = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S',
	                            'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', '?', ' '};
	private final List<Character> msgList;
	private List<Integer> msgIntVal;
	private char[] msgChar;
	public Cipher(String msg) {
		msgList = new ArrayList<>();
		msgChar = msg.toCharArray();
		msgIntVal = new ArrayList<>();

		for (int i = 0; i < msgChar.length; i++) {
			msgList.add(msgChar[i]); // brings the message into an array
		}
		start();
	}

	private void start() {
		System.out.println("Current message: " + msgList.toString());
		encipher(msgChar);
		reshaper(msgIntVal, 4);
	}

	/**
	 * @param entry is the message that is entered from the user
	 * @return the value if the letter according to the list
	 */
	private List<Integer> encipher(char[] entry) {
		for (var i = 0; i < entry.length; i++) {
			var letter = entry[i] % 65; // removes the ASCII equivalent
			if (letter == 32) letter = 28; // converts spaces to match the array
//                System.out.print(letter + " ");
			msgIntVal.add(letter);
		}
		return msgIntVal;
	}

	// Shaper must be a multiple of 4
	private void reshaper(List<Integer> entry, int shaper) {
		System.out.println("Reshaper Parameter = " + entry);
		// ROW = 4, COLUMN = Entry / ROW
		var matrix = new int[shaper][columnSizer(entry, shaper)];
		var counter = 0; var i = 0; var j = 0;
		while (counter != entry.size()) {
			if (i == shaper - 1) {
				if (j == (int) Math.ceil(entry.size()/4) -1) break;
				j++; i = 0;
			}
			else {
				for (i = 0; i < shaper-1; i++) {
					matrix[i][j] = entry.get(counter);
					counter++;
				}
			}
		}

		showBoard(matrix);
		System.out.println();
	}

	// determines if a line needs an extra column or not
	private int columnSizer(List<Integer> entry, int shaper) {
		return entry.size() % shaper != 0 ?
		       (int) Math.floor(entry.size() / shaper) + 1 :
		       (int) Math.floor(entry.size() / shaper);
	}

	private void showBoard(int[][] matrix) {

		System.out.println("-----------------------------------------------------------");
		for(int i = 0; i < 4; i++) {
			System.out.print("| ");
			for(int j = 0; j < matrix[i].length; j++) {
				System.out.print(matrix[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-----------------------------------------------------------");
		}
	}

//    private char[] setAlphabet() {
//        // 0x5B using the ASCII representation for { being after 'Z'
//        return IntStream.range('A', 0x5B)
//                        .mapToObj(c -> (char) c)
//                        .forEach(s -> reference.add(s));
//    }

	public static void main(String[] args) {
//        Scanner in = new Scanner(System.in);
//        System.out.println("Please enter the message to cipher:");
//        var currentMsg = in.nextLine();
		var currentMsg = "ABORT OPERATION IMMEDIATELY";
		new Cipher(currentMsg); // TODO: To be modified so that it takes args instead of an input
	}

	public String toString() {
		var s = ""; for (int i = 0; i < msgChar.length; i++) {
			s += i;
		} return s;
	}
}
