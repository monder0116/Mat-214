/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */


/**
 *
 * @author onder
 */
@SuppressWarnings("unchecked")
public class Solver {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {

            //$./solver ­i system.txt ­m GESP
            if (args.length != 4) {
                System.err.println("Useage=java Solver ­i system.txt ­m GESP");
                return;
            }
            CalculateEquations dene = new CalculateEquations();
            String filename = "";
            if (args[0].equals("-i")) {
                filename = args[1];
            } else if (args[2].equals("-i")) {
                filename = args[3];
            }
            if (filename.equals("")) {
                System.err.println("Please enter File name parameter");
                return;
            }
             double[] CalculateTheRoots=null;
             String methodname="";
            if (args[0].equals("-m")) {
                if(args[1].equals("GESP"))
                    CalculateTheRoots=dene.gaussCalculator(filename);
                else if(args[1].equals("JCB"))
                    CalculateTheRoots=dene.jacobyCalculator(filename);
                else {
                     System.err.println("Method is invalid");
                     return;
                }
                   
               
            } else if (args[2].equals("-m")) {
                 if(args[3].equals("GESP")){
                       CalculateTheRoots=dene.gaussCalculator(filename);
                       methodname="GESP";
                 }
                  
                else if(args[3].equals("JCB")){
                      CalculateTheRoots=dene.jacobyCalculator(filename);
                      methodname="JCB";
                }
                  
                else {
                     System.err.println("Method is invalid");
                     return;
                }
            }else
            {
              System.err.println("Useage example= java Solver ­i system.txt ­m GESP");
                 return;
            }
            

            System.out.print(methodname+" iteration Result;");
            for (Double t : CalculateTheRoots) {
                if(t.isInfinite()|| t.isNaN())
                    throw new Exception("There is no soliton with "+ methodname+" method!");
                
                System.out.print(  t+",");
            }
            System.out.println("");
            // TODO code application logic here

        } catch (Exception ex) {
            System.err.println(ex.toString());
        }
    }

}
