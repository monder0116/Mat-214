/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package SayisalHW4;

/**
 *
 * @author onder
 */
public class Zooming {

    public int[][] nearestNeighborInterpolation(int arr[][], int n) {
        int newarr[][] = new int[arr.length * n][arr.length * n];
        //worst case
        /* for (int i = 0; i < arr.length; i++) {
            for (int j = 0; j < arr.length; j++) {
                for (int k = 0; k < n; k++) {
                    for (int l = 0; l < n; l++) {
                         newarr[i*n+l][j*n+k]=arr[i][j];
                    }
                }
            }
        }*/
        //best case

        for (int i = 0; i < newarr.length; i++) {
            for (int j = 0; j < newarr.length; j++) {
                newarr[i][j] = arr[i / n][j / n];
            }
        }
        return newarr;
    }

    /**
     *
     * I think that; Bilinear interpolation values is nearly average them
     * So I calculate that with average
     *
     * @param arr matrix
     * @param n zooming factor
     * @return bilinearInterpolation
     */
    public float[][] bilinearInterpolation(float arr[][], int n) {
        float newarr[][] = new float[arr.length * n][arr.length * n];

        for (int i = 0; i < newarr.length; i++) {
            for (int j = 0; j < newarr.length; j++) {
                if (i % n == 0 && j % n == 0) {
                    newarr[i][j] = arr[i / n][j / n];
                }

            }
        }
        float oldarr[][] = newarr.clone();

        for (int i = 0; i < newarr.length; i += n) {
            for (int j = 0; j < newarr.length; j++) {
                if (i % n != 0 || j % n != 0) {

                    newarr[i][j] = (findMaxhorizontal(oldarr, i, j) + findMinhorizontal(oldarr, i, j)) / 2;

                }
               

            }
        }
        for (int i = 0; i < newarr.length; i ++) {
            for (int j = 0; j < newarr.length; j+=n) {
                if (i % n != 0 || j % n != 0) {

                     newarr[i][j] = (findMaxvertical(oldarr, i, j) + findMinvertical(oldarr, i, j)) / 2;
                }
              

            }
        }
          for (int i = 0; i < newarr.length; i ++) {
            for (int j = 0; j < newarr.length; j++) {
                if (i % n != 0 && j % n != 0) {

                     newarr[i][j] = (findMaxvertical(oldarr, i, j) + findMinvertical(oldarr, i, j)) / 2;
                }
              

            }
        }
      

        return newarr;
    }

    private float findMinhorizontal(float[][] arr, int i, int j) {

        for (int k = j - 1; k >= 0; k--) {
            if (arr[i][k] != 0) {

                return arr[i][k];
            }

        }
        return 0;

    }

    private float findMinvertical(float[][] arr, int i, int j) {

        for (int k = i - 1; k >= 0; k--) {
            if (arr[k][j] != 0) {

                return arr[k][j];
            }

        }
        return arr[i][j];

    }

    private float findMaxhorizontal(float[][] arr, int i, int j) {

        for (int k = j + 1; k < arr.length; k++) {
            if (arr[i][k] != 0) {

                return arr[i][k];
            }

        }
        return arr[i][j];

    }

    private float findMaxvertical(float[][] arr, int i, int j) {

        for (int k = i + 1; k < arr.length; k++) {
            if (arr[k][j] != 0) {

                return arr[k][j];
            }

        }
        return arr[i][j];

    }
}
