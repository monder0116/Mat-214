/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SayisalHW4;

import com.sun.media.sound.InvalidDataException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author onder
 */
public class Interpolation {

    /**
     * This function return array which is contain divided differences
     * polynomial's coefficients
     *
     * @param arrx x coordinate array
     * @param arry y coordinate array
     * @return coefficients array
     */
    public float[] dividedDifferances(float arrx[], float arry[]) {
        float katsayilar[] = new float[arrx.length];
        for (int j = 0; j < arrx.length; j++) {
            katsayilar[j] = arry[0];
            for (int i = 0; i < arrx.length - 1 - j; i++) {
                arry[i] = (arry[i + 1] - arry[i]) / (arrx[i + j + 1] - arrx[i]);
            }

        }
        return katsayilar;

    }
    public String dividedDifferancesStr(float arrx[],float arry[]){
       StringBuilder str=new StringBuilder();
       
        float result[]=dividedDifferances(arrx, arry);
        for (int i = 0; i < result.length; i++) {
            str.append(result[i]);
            if(i!=0)
                str.append("*");
            
            for (int j = 0; j < i && i!=0; j++) {
                
                str.append("(x-"+arrx[j]+")");
                if(j!=i-1)
                    str.append("*");
            }
            str.append("+");
        }
        return str.toString();
    }
    /**
     * This function return string which is contain the least
     * squares polynomial of degree at most m fitting the given coordinates
     *
     * @param arrx x array coordinates
     * @param arry y array coordinates
     * @param polynomial degree 
     * @return polynomial string
     */
    public String leastSquaresPolynomialStr(float arrx[], float arry[], int degree) {
        double coeff[] = leastSquaresPolynomial(arrx, arry, degree);
        StringBuilder str = new StringBuilder();
        str.append("y=");
        for (int i = 0; i < coeff.length; i++) {
            str.append(coeff[i] + "*(x^" + i + ")");
            if (i != coeff.length - 1) {
                str.append("+");
            }
        }
       
        return str.toString();
    }
        /**
     * This function return coefficient which is contain the least
     * squares polynomial of degree at most m fitting the given coordinates
     *
     * @param arrx x array coordinates
     * @param arry y array coordinates
     * @param degree polynomial degree
     * @return coefficient array
     */
    public double[] leastSquaresPolynomial(float arrx[], float arry[], int degree) {
        if (degree >= arrx.length - 1) {
            System.err.println("Degree is not valid");
            return null;
        }

        ArrayList<Double[]> unknownArr = new ArrayList<>();

        for (int n = 0; n <= degree; n++) {
            Double[] pivotxarr = new Double[degree + 2];
            for (int i = 0; i <= degree; i++) {
                double sum = 0;
                for (int j = 0; j < arrx.length; j++) {
                    float xpow = (float) Math.pow(arrx[j], i + n);
                    sum += xpow;

                }
                pivotxarr[i] = sum;
            }
            Double sumy = 0.0;
            for (int j = 0; j < arry.length; j++) {
                sumy += arry[j] * Math.pow(arrx[j], n);
            }
            pivotxarr[degree + 1] = sumy;
            unknownArr.add(pivotxarr);
        }

        double result[] = null;
        try {

            result = gaussCalculator(unknownArr);

        } catch (InvalidDataException ex) {
            System.out.println(ex);
        }

        return result;

    }

    public double[] gaussCalculator(ArrayList<Double[]> array) throws InvalidDataException {

        ArrayList<Double[]> mainRowarr = array;
        double maxValues[] = new double[mainRowarr.size()];
        for (int i = 0; i < mainRowarr.size(); i++) {
            if (mainRowarr.size() != mainRowarr.get(i).length - 1) {
                throw new InvalidDataException("this not a square matris");
            }

        }

        for (int i = 0; i < mainRowarr.size(); i++) {

            double tempmaxValue = mainRowarr.get(i)[0];

            for (int j = 0; j < mainRowarr.get(i).length - 1; j++) {
                if (tempmaxValue < mainRowarr.get(i)[j]) {
                    tempmaxValue = mainRowarr.get(i)[j];

                }

            }
            maxValues[i] = tempmaxValue;
        }
        for (int i = 0; i < maxValues.length; i++) {
            if (maxValues[i] == 0) {
                throw new InvalidDataException("The scaled Partial Pivoting is zero");
            }

        }
        for (int i = 0; i < mainRowarr.size(); i++) {
            calculateScaleChangeRow(mainRowarr, maxValues, i);
            double pivot = mainRowarr.get(i)[i];
            for (int j = i + 1; j < mainRowarr.size(); j++) {
                if (j != i) {
                    double tempdiv = mainRowarr.get(j)[i] / pivot;
                    mainRowarr.get(j)[i] = mainRowarr.get(j)[i] - tempdiv * pivot;
                    for (int k = i + 1; k < mainRowarr.get(j).length; k++) {

                        mainRowarr.get(j)[k] = mainRowarr.get(j)[k] - tempdiv * mainRowarr.get(i)[k];
                    }
                }

            }

        }

        double[] x = new double[mainRowarr.size()];
        for (int i = mainRowarr.size() - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j = mainRowarr.size() - 1; j >= 0; j--) {
                sum += mainRowarr.get(i)[j] * x[j];
            }
            if (mainRowarr.get(i)[i] == 0 && mainRowarr.get(i)[mainRowarr.get(i).length - 1] != 0) {
                throw new InvalidDataException("This matrix has no solition");
            }

            x[i] = (mainRowarr.get(i)[mainRowarr.get(i).length - 1] - sum) / mainRowarr.get(i)[i];
        }
        return x;

    }

    private void calculateScaleChangeRow(ArrayList<Double[]> rows, double[] maxs, int pivotindex) {
        int maxscaleindex = 0;
        double maxscalevalue = rows.get(pivotindex)[pivotindex] / maxs[pivotindex];
        Boolean changed = false;
        for (int i = pivotindex; i < rows.size(); i++) {
            double tempscale = rows.get(i)[pivotindex] / maxs[i];
            if (tempscale > maxscalevalue) {
                changed = true;
                maxscaleindex = i;
                maxscalevalue = tempscale;
            }
        }
        if (!changed) {
            return;
        }
        Double[] tempvalues = new Double[rows.get(pivotindex).length];
        tempvalues = rows.get(pivotindex).clone();
        rows.set(pivotindex, rows.get(maxscaleindex));
        rows.set(maxscaleindex, tempvalues);
        double tempmax = maxs[pivotindex];
        maxs[pivotindex] = maxs[maxscaleindex];
        maxs[maxscaleindex] = tempmax;

    }
}
