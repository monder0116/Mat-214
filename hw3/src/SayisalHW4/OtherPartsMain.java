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
public class OtherPartsMain {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Zooming temp = new Zooming();
        float arrx[] = new float[5];
        float k = 0;
        for (int i = 0; i < 5; i++) {

            arrx[i] = (float) k;
            k += 0.25;
        }

        float arry[] = new float[5];
        arry[0] = 1;
        arry[1] = (float) 1.284;
        arry[2] = (float) 1.6487;
        arry[3] = (float) 2.117;
        arry[4] = (float) 2.7183;
        Interpolation test1 = new Interpolation();
              System.out.println("Least Squares Polynomial Test Result=");
        System.out.println(test1.leastSquaresPolynomialStr(arrx, arry, 2));
     
        
        
        
        k = 1;
        for (int i = 0; i < 5; i++) {

            arrx[i] = (float) k;
            k += 0.3;
        }
        
        arry[0] = (float) 0.7651977;
        arry[1] = (float) 0.6200860;
        arry[2] = (float) 0.4554022;
        arry[3] = (float) 0.2818186;
        arry[4] = (float) 0.1103623;
        System.out.println("Divided Diffrances Test Result=");
          System.out.println(test1.dividedDifferancesStr(arrx, arry));
          
    }

}
