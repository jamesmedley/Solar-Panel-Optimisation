package model;

public class Matrix {

    private final int ROWS; //Holds the number of rows in the matrix
    private final int COLUMNS; //Holds the number of columns in the matrix

    private double[][] matrix; //Holds values stored in the matrix object

    public Matrix(int rows, int columns) {
        this.ROWS = rows;
        this.COLUMNS = columns;
        build();
    }

    public double[][] getMatrix() {
        return this.matrix;
    }

    private void build() { //Creates an n*m dimensional array that is used to fundamentally hold the data stored in a matrix object
        matrix = new double[ROWS][COLUMNS]; //matrix array given dimentions of matrix
        for (int i = 0; i < ROWS; i++) {
            double[] row = new double[COLUMNS];
            matrix[i] = row;
        }
    }

    public void add(double val, int row, int column) { //Adds a value to a specific position in the matrix
        matrix[row][column] = val;
    }

    public Matrix inverse(Matrix matrix) { //Performs an inversing operation on the matrix that is passed into the method. The method then returns the inversed version of that matrix.
        Matrix inverse = new Matrix(ROWS, COLUMNS); //new matrix object created to hold inverse matrix
        for (int i = 0; i < matrix.ROWS; i++) {//loops through every element
            for (int j = 0; j < matrix.matrix[i].length; j++) {
                inverse.matrix[i][j] = Math.pow(-1, i + j) * determinant(submatrix(matrix, i, j)); //uses determinant and submatrix method to calculate value of element in inverse array and then depending on position makes it negative
            }
        }
        double multiplier = 1 / determinant(matrix);
        for (int i = 0; i < inverse.ROWS; i++) {
            for (int j = 0; j <= i; j++) {
                double temp = inverse.matrix[i][j];
                inverse.matrix[i][j] = inverse.matrix[j][i] * multiplier;//mulitplies every element by multiplier
                inverse.matrix[j][i] = temp * multiplier;
            }
        }
        return inverse;
    }

    public Matrix leftMultiply(Matrix rMatrix) { //Performs matrix multiplication and returns the result of the operation as a matrix.
        Matrix product = new Matrix(rMatrix.ROWS, rMatrix.COLUMNS);
        for (int i = 0; i < rMatrix.ROWS; i++) {
            double sum = 0;
            for (int j = 0; j < COLUMNS; j++) {
                sum += rMatrix.matrix[j][0] * matrix[i][j];
            }
            product.add(sum, i, 0);
        }
        return product;
    }

    private double determinant(Matrix matrix) { //Calculates the determinant of a given inputted matrix
        if (matrix.ROWS == 2) {
            return matrix.matrix[0][0] * matrix.matrix[1][1] - matrix.matrix[0][1] * matrix.matrix[1][0]; //special case if matrix is only 2x2
        }
        double det = 0;
        for (int i = 0; i < matrix.ROWS; i++) {
            det += Math.pow(-1, i) * matrix.matrix[0][i] * determinant(submatrix(matrix, 0, i)); //recursion as determinant of each submatrix needs to be found, every other value multiplied by -1
        }
        return det;
    }

    private Matrix submatrix(Matrix matrix, int row, int column) { //Finds the particular submatrix for a given row and column of a matrix.
        Matrix submatrix = new Matrix(matrix.ROWS - 1, matrix.COLUMNS - 1); //new matrix object to hold submatrix
        for (int i = 0; i < matrix.ROWS; i++) {
            for (int j = 0; (i != row) && (j < matrix.matrix[i].length); j++) {
                if (j != column) {
                    submatrix.matrix[i < row ? i : i - 1][j < column ? j : j - 1] = matrix.matrix[i][j];
                }
            }
        }
        return submatrix;
    }
}
