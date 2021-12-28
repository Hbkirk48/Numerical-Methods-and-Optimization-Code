/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package code8;

/**
 *
 * @author brand
 */
public class Code8 {

    public static double[] Gauss_Seid(double[][] A, double[] b){
        
        double eps = 0.0001;
        
        int k = 0;
        
        double[][] x = null; 
        for (int i = 0; i<A.length; i++){
            x[0][i] = 0;
        }

        while(true){
            k++;
            for(int i = 0; i<A.length; i++){
                double sum = 0;
                for(int j = 0; j<i-1; j++){
                    sum += A[i][j]*x[k][j];
                }
                for (int j = i+1; j<A.length;j++){
                sum += A[i][j]*x[k-1][j];
            }
            }
            boolean stop = true;
            for (int i = 0; i < A.length && stop; i++)
                if (Math.abs(x[k][i] - x[k-1][i]) > eps)
                    stop = false;
            if(stop) break;
        }
        return b;
        
        

    }
    
    public static void main(String[] args) {
        
        
        
        double[][] A= {
            {9, 1, 1},
            {2, 10, 3},
            {3, 4, 11},
        };
        
        double[] B= {10, 19, 0};
        
        System.out.println("Ax = b");
        System.out.println("A =");
        for (int i = 0; i<A.length; i++){
            for(int j = 0; j<A[i].length; j++){
                System.out.printf("| %.3f ", A[i][j]);
            }
            System.out.println("|");
        }
        
        double[] x = Gauss_Seid(A, B);
        
        System.out.println("x =");
        for (int i = 0; i < A.length; i++) {
            System.out.printf("| %.3f |\n",x[i]);
        }
    }
    
}
