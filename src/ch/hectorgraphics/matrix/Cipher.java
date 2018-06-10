package ch.hectorgraphics.matrix;

import java.awt.event.MouseAdapter;
import java.util.ArrayList;
import java.util.IllegalFormatWidthException;
import java.util.List;
import java.util.stream.Stream;

@SuppressWarnings("all")
public class Cipher {

	private final char[] REFERENCE = {'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R',
	                                  'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z', '.', '?', ' '};
	private final List<Character> msgList;
	private List<Integer> msgIntVal;
	private String msg2;
	private char[] msgChar1;
	private double[][] transMat;
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
	private double[][] reshape(List<Integer> entry) {
		// ROW = 4, COLUMN = Entry / ROW
		var matrix = new double[SHAPER + 1][columnSizer(entry)];
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
	private double[][] reducer(double[][] originalMat) {
		var newMatrix = new double[originalMat.length - 1][originalMat[0].length];
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
	private double[][] transpose(double[][] matEntry) {
		//		System.out.println();
		//		System.out.println("==================================");
		//		System.out.println("  ***** TRANSPOSED MATRIX *****");
		//		System.out.println("==================================");
		transMat = new double[matEntry[0].length][matEntry.length];
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
		//		dispMatrix(transMat);
		return transMat;
	}

	/**
	 * @param matA gets the first matrix
	 * @param matB gets the second matrix
	 * @return the addition of both matrixes
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

	/**
	 * @param matA is the first matrix
	 * @param matB is the second matrix
	 * @return returns a new matrix of size n x n
	 */
	private double[][] mulOfMat(double[][] matA, double[][] matB) {
		System.out.println();
		System.out.println("========================================");
		System.out.println("  ***** MUTIPLICATION OF MATRIX *****");
		System.out.println("========================================");

		var resultMat = new double[matA.length][matB[0].length];
		if (matA.length != matB[0].length) {
			System.err.println("Please change the size of the matrix so that the column or row are equal!!!");
			;
			throw new IllegalFormatWidthException(matA[0].length - matB.length);
		}
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
	 * @param originalMat
	 * @return the inverse of a SQUARE matrix
	 */
	private double[][] inverse(double[][] originalMat) {
		System.out.println();
		System.out.println("==========================================================");
		System.out.println("     ************** INVERSE OF MATRIX **************");
		System.out.println("==========================================================");

		var invMat = transpose(matrixMerger(originalMat, matIndentityCreator(originalMat.length, originalMat.length)));
		dispMatrix(invMat);
		swap(swap(invMat)); // done twice so that if there are '1s' and '0s' they get underneath each other
		gaussJordan(invMat);

		dispMatrix(invMat);
		return invMat;
	}

	private double[][] gaussJordan(double[][] mat) {
		return new double[0][];
	}

	private double[][] swap(double[][] mat) {
		try {
			for (var row = 0; row < mat.length; row++) {
				if (mat[0][0] == 0) {
					for (var curCol = 0; curCol < mat[0].length; curCol++) {
								var temp = mat[0][curCol];
								mat[0][curCol] = mat[1][curCol];
								mat[1][curCol] = temp;
					}
				}

				if (mat[row][0] == 1) {
					for (var curCol = 0; curCol < mat[0].length; curCol++) {
						var temp1 = mat[row][curCol];
						mat[row][curCol] = mat[0][curCol];
						mat[0][curCol] = temp1;
					}
				}
			}
		} catch (ArrayIndexOutOfBoundsException aiooe) {
			aiooe.printStackTrace();
		}
		return mat;
	}

	private double[][] matrixMerger(double[][] mat1, double[][] mat2) {
		var result = new double[mat1.length + mat2.length][mat1.length + mat2.length];
		try {
			System.arraycopy(transpose(mat1), 0, result, 0, mat1.length);
			System.arraycopy(mat2, 0, result, mat1.length, mat2.length);
		} catch (ArrayStoreException ase) {
			ase.printStackTrace();
		}
		return result;
	}

	private double[][] matIndentityCreator(int row, int col) {
		var idMat = new double[row][col];
		for (int i = 0, j = 0; i < row; i++, j++) {
			idMat[i][j] = 1;
		}
		//		dispMatrix(idMat);
		return idMat;
	}

	private double determinantFinder(double[][] originalMat) {
		return 0.0;
	}

	/**
	 * @param matrix is the matrix to be displayed
	 */
	private void dispMatrix(double[][] matrix) {
		//		var counter = 1L;
		System.out.println("---------------------------------------------------------");
		for (int i = 0; i < matrix.length; i++) { // The '-1' doesn't display the last line in the matrix
			System.out.print("| ");
			for (int j = 0; j < matrix[i].length; j++) {
				if (matrix[i][j] < 10) {
					System.out.print(matrix[i][j] + "  | ");
				} else if (matrix[i][j] > 99) {
					System.out.print(matrix[i][j] + "  | ");
				} else {
					System.out.print(matrix[i][j] + " | ");
				}
			}
			System.out.println();
			System.out.println("---------------------------------------------------------");
			//			System.out.println("\nCounter: " + counter++);
		}
	}

	/**
	 * Always multiplies the row of matrix A by column of matrix B
	 *
	 * @param n      represents the row
	 * @param m      represents the column
	 * @param maxVal is number representing the
	 * @return a matrix with size n x m within the maxVal limit determined
	 */
	private double[][] matrixGenerator(int row, int col, int maxVal) {
		var genMat = new double[row][col];
		for (int i = 0; i < row; i++) {
			for (int j = 0; j < col; j++) {
				genMat[i][j] = (int) (Math.random() * maxVal);
			}
		}
		dispMatrix(genMat);
		return genMat;
	}

	public static void main(String[] args) {
		//        Scanner in = new Scanner(System.in);
		//        System.out.println("Please enter the message to cipher:");
		//        var currentMsg = in.nextLine();
		var currentMsg = "ABORT OPERATION IMMEDIATELY";
		Cipher cipher = new Cipher(currentMsg); // TODO: To be modified so that it takes args instead of an input
		// fixme: doesn't work if the matrix row is not the same size as the column of the seccond matrix OR
		// fixme: if the row or column is greater than 3
		//		var mat1 = cipher.matrixGenerator(3, 4, 21);
		//		var mat2 = cipher.matrixGenerator(4, 3, 10);
		//		var matInverse = cipher.matrixGenerator(4, 4, 10);
		//		cipher.mulOfMat(mat1, mat2);
		var matInverse = new double[][]{{0, 5, 5, 1}, {6, 7, 7, 3}, {4, 5, 1, 4}, {1, 6, 9, 2}};
		cipher.inverse(matInverse);

	}
}
