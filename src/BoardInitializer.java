import javafx.css.Size;

import java.util.Scanner;

/**
 * @author Jonathan Hector on 15 May 2018.
 */
public class BoardInitializer {
	private String name;
	private final int ROW = 2;
	private final int COL = 2;
	private int[][] board;
	private Scanner input = new Scanner(System.in);
	
	public BoardInitializer(Prompter prompt) {
		name = prompt.getName();
		showBoard();
		boardInitializer();
	}
	
	public void start() {
		Scanner in = new Scanner(System.in);
		var n = in.nextInt();
		var m = in.nextInt();
		getBoard(n, m);
			isFinish();
	}
	// Algorithm for creating a board of any size
	private void showBoard() {
		System.out.println("-------------");
		for(int i = 0; i < 3; i++) {
			System.out.print("| ");
			for(int j = 0; j < 3; j++) {
				System.out.print(board[i][j] + " | ");
			}
			System.out.println();
			System.out.println("-------------");
		}
	}
	
	// TODO: Change the iterator
	private void boardInitializer() {
		for(int i = 0; i < 3; i++) {
			for(int j = 0; j < 3; j++) {
				board[i][j] = '-';
			}
		}
	}

	public void isFinish() {

	}

	// CHECK: The next 3 methods checks if the board is full
	private boolean isRowFull() {
		if(board.equals(Character.isLetter('X')) || board.equals(Character.isLetter('O')) &&
				((board[0][0] == board[1][0] && board[0][0] == board[2][0]) ||
						(board[0][1] == board[1][1] && board[0][1] == board[2][1]) ||
						(board[0][2] == board[1][2] && board[0][2] == board[2][2]))) {
		}
		return false;
	}

	private boolean isColumnFull() {
		if(board.equals(Character.isLetter('X')) || board.equals(Character.isLetter('O')) &&
				((board[0][0] == board[0][1] && board[0][0] == board[0][2]) ||
						(board[1][0] == board[1][1] && board[1][0] == board[1][2]) ||
						(board[2][0] == board[2][1] && board[2][0] == board[2][2]))) {
		}
		return false;
	}

	private boolean isDiagonalFull() {
		if(board.equals(Character.isLetter('X')) || board.equals(Character.isLetter('O')) &&
				((board[0][0] == board[1][1] && board[0][0] == board[2][2]) ||
						(board[2][0] == board[1][1] && board[2][0] == board[0][2]))) {
		}
		return false;
	}
	
	public int[][] getBoard(int n, int m) {
		board = new int[n][m];
		return board;
	}
}
