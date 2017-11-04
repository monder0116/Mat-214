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
public class Part1Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        // TODO code application logic here
        Zooming temp=new Zooming();
        int arr[][]=new int[2][2];
        
        int k=1;
        for (int i = 0; i < 2; i++) {
            for (int j = 0; j < 2; j++) {
                arr[i][j]=k++;
            }
        }
        
      arr=  temp.nearestNeighborInterpolation(arr, 2);
        for (int i = 0; i < 4; i++) {
            for (int j = 0; j < 4; j++) {
                System.out.print(arr[i][j] +", ");
            }
            System.out.println("");
        }
    }
    
}
