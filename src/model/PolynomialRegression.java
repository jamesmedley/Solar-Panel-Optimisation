package model;

public class PolynomialRegression {

    private final double[] XDATA; //Holds double values for the x data in a data set

    private final double[] YDATA; //Holds double values for the y data in a data set

    private double[] coeff; //Holds a set of values that are the coefficients to the polynomial function

    PolynomialRegression(double[] xData, double[] yData, int degree, boolean init) {
        this.XDATA = xData;
        this.YDATA = yData;
        if (init) {
            coeffCalc(degree);//object can be initialised with coefficients so model doesnt need to be recalculated every time to improve efficiency 
        }
    }

    public double[] getCoeff() {
        return coeff;
    }

    public void setCoeff(double[] coeff) {
        this.coeff = coeff;
    }

    public double curveFunction(double x) { //Returns a value based on an input value using the polynomial function stored in that object
        double value = 0;
        for (int i = 0; i < coeff.length; i++) {
            value += coeff[i] * (Math.pow(x, i)); //adds a power to each domain in incrementing values for polynomial funciton
        }
        return value;
    }

    private void coeffCalc(int degree) { //Uses matrices and arrays of x and y data to perform least squares regression to produces a polynomial function that represents a best fit for a set of data.
        int m = degree + 1;
        coeff = new double[m];
        Matrix xMatrix = new Matrix(m, m);
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < m; j++) {
                xMatrix.add(sum(XDATA, i + j), i, j);//sum of Xs matrix
            }
        }
        Matrix xyMatrix = new Matrix(m, 1);
        double[] XYdata;
        for (int i = 0; i < m; i++) {
            XYdata = arrayProduct(i);
            xyMatrix.add(sum(XYdata, 1), i, 0);//sum of XYs matrix
        }
        Matrix inverse = xMatrix.inverse(xMatrix);//inverses matrix
        Matrix coeffMat = inverse.leftMultiply(xyMatrix); //multiplys xMatrix and xyMatrix to get vector matrix with coefficient values in
        for (int i = 0; i < m; i++) {
            coeff[i] = coeffMat.getMatrix()[i][0];
        }
    }

    private double sum(double[] data, int power) { //Sums data in an array where each data item is raised to the power in the parameter
        double sum = 0;
        for (int i = 0; i < data.length; i++) {
            sum += Math.pow(data[i], power);
        }
        return sum;
    }

    private double[] arrayProduct(int power) { //Combines the x data and y data by multiplying each y term by the corresponding x term raised to the power of their index
        double[] XYdata = new double[XDATA.length];
        for (int i = 0; i < XYdata.length; i++) {
            XYdata[i] = Math.pow(XDATA[i], power) * YDATA[i];
        }
        return XYdata;
    }
}
