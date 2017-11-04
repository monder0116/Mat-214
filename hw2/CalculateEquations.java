/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author onder
 */
@SuppressWarnings("unchecked")
public class CalculateEquations {

  
    /**
     * This read inputs from file and conver to double array
     * @param filename
     * @return
     * @throws hw3.CalculateEquations.Exception 
     */
    private ArrayList<Double[]> ConvertInputsToMatris(String filename) throws Exception {
        ArrayList<Double[]> colarr = new ArrayList<>();
        try {
            Scanner tara = new Scanner(new File(filename));
            while (tara.hasNextLine()) {
                String line = tara.nextLine();
                String linearr[] = line.split(",");
                Double rowarr[] = new Double[linearr.length];
                for (int i = 0; i < linearr.length; i++) {
                    rowarr[i] = Double.parseDouble(linearr[i].trim());
                }
                colarr.add(rowarr);

            }
            for (int i = 0; i < colarr.size(); i++) {
                Double[] temparr = colarr.get(i);
                if (temparr[i] == 0) {
                    throw new Exception("Wrong Row and col size");
                }

            }

        } catch (FileNotFoundException ex) {
            System.err.println("File cannot openned");

        }
        return colarr;
    }
    /**
     * This calculate the matrix roots which is convergent and square matrix
     * @param filename
     * @return
     * @throws Exception 
     */
    public double[] jacobyCalculator(String filename) throws Exception {
        double results[];
        try {
            ArrayList<Double[]> mainRowarr = ConvertInputsToMatris(filename);
            for (int i = 0; i < mainRowarr.size(); i++) {
                if (mainRowarr.size() != mainRowarr.get(i).length - 1) {
                    throw new Exception("this not a square matris");
                }
                if (mainRowarr.get(i)[i] == 0) {
                    throw new Exception("pivot element is zero");
                }

            }
            double normvalue = 0;
            int matrixn=mainRowarr.size();
            for (int i = 0; i < mainRowarr.size(); i++) {
                for (int j = 0; j < mainRowarr.size(); j++) {
                    normvalue += Math.pow(mainRowarr.get(i)[j],matrixn );

                }
            }
            normvalue = Math.pow(normvalue,1/matrixn);
            if (normvalue > 1) {
                throw new Exception("This is not Convergent matrix");
            }
            double errorvalue=100;
            results = new double[mainRowarr.size()];
            double oldresult[] = new double[mainRowarr.size()];
            for (int k = 0; k < mainRowarr.size(); k++) {

                oldresult = results.clone();
                do {
                    oldresult = results.clone();
                    for (int i = 0; i < mainRowarr.size(); i++) {
                        double pivotNumber = mainRowarr.get(i)[i];
                        double sumOfxi = 0;

                        for (int j = 0; j < mainRowarr.get(i).length - 1; j++) {
                            if (i != j) {
                                sumOfxi += mainRowarr.get(i)[j] * oldresult[j];
                            }

                        }
                        sumOfxi /= -pivotNumber;
                        sumOfxi += mainRowarr.get(i)[mainRowarr.get(i).length - 1] / pivotNumber;
                        results[i] = sumOfxi;
                        errorvalue=Math.abs((oldresult[k] - results[k])) / Math.abs(results[k]) ;
                        
                    }
                } while (errorvalue> Math.pow(10, -3));
            }

        } catch (Exception ex) {
            throw ex;

        }
        return results;
    }
    /**
     * This help to gauss calculator and this calculate the scales all of rows
     *  then change the row according to scales values
     * @param rows all of matrix
     * @param maxs row's maximum value
     * @param pivotindex 
     */
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
    /**
     * This calculate the Matrix with Scaled partial pivoting, matrix need to be
     *  square matrix  
     * @param filename of inputs
     * @return roots
     * @throws hw3.CalculateEquations.Exception 
     */
    public double[] gaussCalculator(String filename) throws Exception {
       

        ArrayList<Double[]> mainRowarr = ConvertInputsToMatris(filename);
        double maxValues[] = new double[mainRowarr.size()];
        for (int i = 0; i < mainRowarr.size(); i++) {
            if (mainRowarr.size() != mainRowarr.get(i).length - 1) {
                throw new Exception("this not a square matris");
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
                throw new Exception("The scaled Partial Pivoting is zero");
            }

        }
        for (int i = 0; i < mainRowarr.size(); i++) {
            calculateScaleChangeRow(mainRowarr, maxValues, i);
            double pivot = mainRowarr.get(i)[i];
            for (int j = i+1; j < mainRowarr.size(); j++) {
                if (j != i) {
                    double tempdiv = mainRowarr.get(j)[i] / pivot;
                    mainRowarr.get(j)[i]=mainRowarr.get(j)[i]-tempdiv*pivot;
                    for (int k = i+1; k < mainRowarr.get(j).length; k++) {
                       
                        mainRowarr.get(j)[k]=mainRowarr.get(j)[k]-tempdiv*mainRowarr.get(i)[k];
                    }
                }

            }

        }
        
       double[] x = new double[mainRowarr.size()];
        for (int i = mainRowarr.size() - 1; i >= 0; i--) {
            double sum = 0.0;
            for (int j =  mainRowarr.size()-1 ; j >=0; j--) {
                sum += mainRowarr.get(i)[j] * x[j];
            }
            if(mainRowarr.get(i)[i]==0 && mainRowarr.get(i)[mainRowarr.get(i).length-1]!=0 )
                throw new Exception("This matrix has no solition");
            
            x[i] = (mainRowarr.get(i)[mainRowarr.get(i).length-1] - sum) / mainRowarr.get(i)[i];
        }
        return x;
        
     

    }
}
