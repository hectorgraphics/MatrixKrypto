package ch.hectorgraphics.matrix;

import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("all")
public class Cipher {

	private final char[] REFERENCE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
	                                  'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', '?', ' '};
	private final List<Character> msgList;
	private List<Integer> msgIntVal;
	private char[] msgChar;
	private int[][] matrix;
	private int[][] transMat;
	private final int SHAPER = 4;

	public Cipher(String msg) {
		msgList = new ArrayList<>();
		msgChar = msg.toCharArray();
		msgIntVal = new ArrayList<>();

		System.out.println("Current Message: " + msg);
		start();
	}

	public void start() {
		encipher(msgChar);
		reshape(msgIntVal);
		transpose(matrix);
		addOfMatrix(msgIntVal, msgIntVal);
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

	/**
	 *
	 * @param entry is the original message in the encoded format
	 * @return the encoded message as a  4 x m
	 */
	private int[][] reshape(List<Integer> entry) {
		System.out.println("Encoded Value: " + entry);
		// ROW = 4, COLUMN = Entry / ROW
		matrix = new int[SHAPER + 1][columnSizer(entry, SHAPER)];
		var counter = 0;
		var row = 0;
		var col = 0;
		try {
			for (col = 0; col < matrix[row].length; col++) {
				for (row = 0; row < SHAPER; row++) { // removing - 1 returns an error
					matrix[row][col] = entry.get(counter);
					if ((entry.size() / SHAPER) % SHAPER != 0) {
						matrix[row + 1][col] = entry.get(counter);
					}
					counter++;
				}
			}

		} catch (IndexOutOfBoundsException ioobe) {
			ioobe.getMessage();
		}
		System.out.println("Before shaping up the values");
		dispMatrix(reducer(matrix));

		return matrix;
	}

	// determines if a line needs an extra column or not
	private int columnSizer(List<Integer> entry, int SHAPER) {
		return entry.size() % SHAPER != 0 ? (int) Math.floor(entry.size() / SHAPER) + 1 : (int) Math.floor(entry.size() / SHAPER);
	}

	/**
	 * @param originalMat takes the originalMat
	 * @return the matrix with the last line removed
	 */
	private int[][] reducer(int[][] originalMat) {
		var newMatrix = new int[originalMat.length-1][originalMat[0].length];
		for (int i = 0; i < newMatrix.length; i++) { // The '-1' doesn't display the last line in the matrix
			for (int j = 0; j < newMatrix[i].length; j++) {
				newMatrix[i][j] = originalMat[i][j];
			}
		}
		return newMatrix;
	}

	/**
	 *
	 * @param matEntry takes the original matrix and transposes it
	 */
	private int[][] transpose(int[][] matEntry) {
		System.out.println("==================================");
		System.out.println("  ***** TRANSPOSED MATRIX *****");
		System.out.println("==================================");
		transMat = new int[matEntry[0].length][SHAPER];
		var counter = 0;
		var row = 0;
		var col = 0;
		try {
			for (row = 0; row < matEntry.length; row++) {
				for (col = 0; col < matEntry[0].length; col++) {
					transMat[col][row] = matEntry[row][col];
				}
			}

		} catch (IndexOutOfBoundsException ioobe) {
			ioobe.getMessage();
		}
		dispMatrix(transMat);
		return transMat;
	}

	/**
	 *
	 * @param matA gets the first matrix
	 * @param matB
	 * @return
	 */
	private List<Integer> addOfMatrix(List<Integer> matA, List<Integer> matB) {
		var addMatrix = new ArrayList<Integer>();
		var iterA = matA.iterator();
		var iterB = matB.iterator();
		while (iterA.hasNext()){
			addMatrix.add(iterA.next() + iterB.next());
		}
		reshape(addMatrix);
		return addMatrix;
	}

	private void dispMatrix(int[][] matrix) {
		System.out.println("-----------------------------------");
		for (int i = 0; i < matrix.length; i++) { // The '-1' doesn't display the last line in the matrix
			System.out.print("| ");
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] < 10)
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
