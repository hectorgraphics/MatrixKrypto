package ch.hectorgraphics.matrix;

import java.util.ArrayList;
import java.util.IllegalFormatException;
import java.util.IllegalFormatWidthException;
import java.util.List;
import java.util.stream.IntStream;

@SuppressWarnings("all")
public class Cipher {

	private final char[] REFERENCE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
	                                  'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', '?', ' '};
	private final List<Character> msgList;
	private List<Integer> msgIntVal;
	private String msg2;
	private char[] msgChar1;
//	private int[][] matrix;
	private int[][] transMat;
	private final int SHAPER = 4;

	public Cipher(String msg) {
		msgList = new ArrayList<>();
		msgChar1 = msg.toCharArray();
		msgIntVal = new ArrayList<>();

//		System.out.println("Current Message: " + msg);
		start();
	}

	public void start() {
				encipher(msgChar1);
				reshape(msgIntVal);
		//		transpose(matrix);
		//		addOfMatrix(msgIntVal, msgIntVal);
		//		scalarMul(msgIntVal, 4);
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
	 * @param entry is the original message in the encoded format
	 * @return the encoded message as a  4 x m
	 */
	private int[][] reshape(List<Integer> entry) {
		// ROW = 4, COLUMN = Entry / ROW
		var matrix = new int[SHAPER + 1][columnSizer(entry)];
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
//		dispMatrix(reducer(matrix));
		return matrix;
	}

	/**
	 * determines if a line needs an extra column or not
	 *
	 * @param entry is the original message
	 */
	private int columnSizer(List<Integer> entry) {
		return entry.size() % SHAPER != 0 ? (int) Math.floor(entry.size() / SHAPER) + 1 : (int) Math.floor(entry.size() / SHAPER);
	}

	/**
	 * @param originalMat takes the originalMat
	 * @return the matrix with the last line removed
	 */
	private int[][] reducer(int[][] originalMat) {
		var newMatrix = new int[originalMat.length - 1][originalMat[0].length];
		for (int i = 0; i < newMatrix.length; i++) { // The '-1' doesn't display the last line in the matrix
			for (int j = 0; j < newMatrix[i].length; j++) {
				newMatrix[i][j] = originalMat[i][j];
			}
		}
		return newMatrix;
	}

	/**
	 * @param matEntry takes the original matrix and transposes it
	 */
	private int[][] transpose(int[][] matEntry) {
		System.out.println();
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
	 * @param matA gets the first matrix
	 * @param matB
	 * @return
	 */
	private List<Integer> addOfMatrix(List<Integer> matA, List<Integer> matB) {
		var addMatrix = new ArrayList<Integer>();
		var iterA = matA.iterator();
		var iterB = matB.iterator();
		if (matA.size() != matB.size())
			throw new IllegalFormatWidthException((int) matA.size() - matB.size());
		while (iterA.hasNext()) {
			addMatrix.add(iterA.next() + iterB.next());
		}
		reshape(addMatrix);
		return addMatrix;
	}

	/**
	 * @param matEntry is the original matrix
	 * @param scalar   is the number to be multiplied by
	 * @return returns new matrix having been multiplied by the matrix
	 */
	private List<Integer> scalarMul(List<Integer> matEntry, int scalar) {
		System.out.println();
		System.out.println("==============================================");
		System.out.println("  ***** SCALAR MULTIPLICATION MATRIX *****");
		System.out.println("==============================================");
		var newScalarMat = new ArrayList<Integer>();
		var iterA = matEntry.iterator();
		while (iterA.hasNext())
			newScalarMat.add(scalar * iterA.next());
		reshape(newScalarMat);
		return newScalarMat;
	}

	private int[][] mulOfMat(int[][] matA, int[][] matB) {
		System.out.println();
		System.out.println("========================================");
		System.out.println("  ***** MUTIPLICATION OF MATRIX *****");
		System.out.println("========================================");

		var resultMat = new int[matA.length][matB[0].length];
		if (matA.length != matB[0].length)
			throw new IllegalFormatWidthException(matA[0].length - matB.length);
		try {
			/* Loop through each and get product, then sum up and store the value */
			for (int i = 0; i < matA.length; i++) {
				for (int j = 0; j < matB[0].length; j++) {
					for (int k = 0; k < matA[0].length; k++) {
						resultMat[i][j] += matA[i][k] * matB[k][j];
					}
				}
			}
		} catch (NullPointerException npe) {
			npe.getCause();
		} catch (ArrayIndexOutOfBoundsException aioob) {
			aioob.getCause();
		}
		dispMatrix(resultMat);
		return resultMat;
	}

	/**
	 *
	 * @param matrix is the matrix to be displayed
	 */
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

	/**
	 *
	 * @param n represents the row
	 * @param m represents the column
	 * @param maxVal is number representing the
	 * @return n x n size of the matrix after having been multiplied
	 */
	private int[][] matrixGenerator(int n, int m, int maxVal) {
		var genMat = new int[n][m];
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < m; j++) {
				genMat[i][j] = (int) (Math.random() * maxVal);
			}

		}
		return genMat;
	}

	public static void main(String[] args) {
		//        Scanner in = new Scanner(System.in);
		//        System.out.println("Please enter the message to cipher:");
		//        var currentMsg = in.nextLine();
		var currentMsg = "ABORT OPERATION IMMEDIATELY";
		Cipher cipher = new Cipher(currentMsg); // TODO: To be modified so that it takes args instead of an input
		var mat1 = cipher.matrixGenerator(3, 4, 21);
		var mat2 = cipher.matrixGenerator(5, 5, 21);
		cipher.mulOfMat(mat1, mat2);

	}
}
