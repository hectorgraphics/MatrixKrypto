import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

@SuppressWarnings("all")
public class Cipher {

	private final char[] REFERENCE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
	                                  'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', '?', ' '};
	private final List<Character> msgList;
	private List<Integer> msgIntVal;
	private char[] msgChar;

	private final int SHAPER = 4;

	public Cipher(String msg) {
		msgList = new ArrayList<>();
		msgChar = msg.toCharArray();
		msgIntVal = new ArrayList<>();

		for (int i = 0; i < msgChar.length; i++) {
			msgList.add(msgChar[i]); // brings the message into an array
		}
		start();
	}

	public void start() {
		System.out.println("Current message: " + msgList.toString());
		encipher(msgChar);
		reshaper(msgIntVal);
	}

	/**
	 * @param entry is the message that is entered from the user
	 * @return the value if the letter according to the list
	 */
	private List<Integer> encipher(char[] entry) {
		for (var i = 0; i < entry.length; i++) {
			var letterVal = entry[i] % 65; // removes the ASCII equivalent

			if (letterVal == 32)
				letterVal = 28; // converts spaces to match the array
			//                System.out.print(letter + " ");
			msgIntVal.add(letterVal);
		}
		return msgIntVal;
	}

	// Shaper must be a multiple of 4
	private void reshaper(List<Integer> entry) {
		System.out.println("Reshaper Parameter = " + entry);
		// ROW = 4, COLUMN = Entry / ROW
		var matrix = new int[SHAPER + 1][columnSizer(entry, SHAPER)];
		var counter = 0;
		var row = 0;
		var col = 0;
		try {
			for (col = 0; col < matrix[row].length; col++) {
				for (row = 0; row < SHAPER; row++) { // removing - 1 returns an error
					matrix[row][col] = entry.get(counter);
					if (entry.size() / SHAPER % SHAPER != 0) {
						matrix[row + 1][col] = entry.get(counter);
					}
					counter++;
				}
			}
		} catch (IndexOutOfBoundsException ioobe) {
			ioobe.getMessage();
		}
		dispMatrix(matrix);
		System.out.println("==================================================");
		transpose(msgIntVal);
	}

	// determines if a line needs an extra column or not
	private int columnSizer(List<Integer> entry, int SHAPER) {
		return entry.size() % SHAPER != 0 ? (int) Math.floor(entry.size() / SHAPER) + 1 : (int) Math.floor(entry.size() / SHAPER);
	}

	private void transpose(List<Integer> entry) {
		var matrix = new int[columnSizer(entry, SHAPER)][SHAPER + 1];
		var counter = 0;
		var row = 0;
		var col = 0;
		try {
			for (row = 0; row < SHAPER; row++) { // removing - 1 returns an error
				for (col = 0; col < matrix[row].length; col++) {
					matrix[row][col] = entry.get(counter);
					if (entry.size() / SHAPER % SHAPER != 0) {
						matrix[row+1][col] = entry.get(counter);
					}
					counter++;
				}
			}
		} catch (IndexOutOfBoundsException ioobe) {
			ioobe.getMessage();
		}
		dispMatrix(matrix);
	}

	private void dispMatrix(int[][] matrix) {

		System.out.println("-----------------------------------");
		for (int i = 0; i < matrix.length-1; i++) { // The '-1' doesn't display the last line in the matrix
			System.out.print("| ");
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] < 11)
					System.out.print(matrix[i][j] + "  | ");
				else
					System.out.print(matrix[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-----------------------------------");
		}
	}

	public static void main(String[] args) {
		//        Scanner in = new Scanner(System.in);
		//        System.out.println("Please enter the message to cipher:");
		//        var currentMsg = in.nextLine();
		var currentMsg = "ABORT OPERATION IMMEDIATELY";
		new Cipher(currentMsg); // TODO: To be modified so that it takes args instead of an input
		Operations operations = new Operations();

	}

	public String toString() {
		var s = "";
		for (int i = 0; i < msgChar.length; i++) {
			s += i;
		}
		return s;
	}
}
