package ch.hectorgraphics.matrix;

import java.util.Scanner;

public class Operations extends BoardInitializer {
    
    private int n, m;
    private int[][] matrix;
    private final int ROW = 2;
    private final int COL = 2;
    private Scanner in = new Scanner(System.in);
    
    public Operations(BoardInitializer board) {
        super();
        matrix = board.getBoard(n, m);
    }

    public Operations() {

    }

    public int[][] getMatrixSize(int n, int m) {
        return new int[n][m];
    }
    
    private boolean sizeCheck(double[][] matrix) {
        return matrix.length == matrix[n][m];
    }
    
    public int[][] transpose() {
        for (var i = 0; i < this.n - 1; i++) {
            for (var j = 0; j < this.m - 1; j++) {
                matrix[i][0] = matrix[0][j];
            }
        }
        return matrix;
    }
    
    public int[][] add(int[][] mat) {
        for (var i = 0; i < this.n - 1; i++) {
            for (var j = 0; j < this.m - 1; j++) {
                matrix[i][j] += mat[i][j];
            }
        }
        return matrix;
    }
    
    
}
