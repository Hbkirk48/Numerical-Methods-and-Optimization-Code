/**
*
* CSC 2262 Programming project No 7
*
* @author Hudson Kirkpatrick
* @since 11/13/20
*
*/
package code7;


public class Code7 {

    public static double[] Gauss(double[][] A, double[] b){
        
        for (int pv = 0; pv<b.length; pv++){
            
            //Find pivot
            int max = pv;
            for(int i = pv+1; i<b.length; i++){
                if (Math.abs(A[i][pv]) > Math.abs(A[max][pv])){
                    max = i;
                }
            }
            double t = b[pv];
            b[pv] = b[max];
            b[max] = t;
            
            double[] tmp = A[pv];
            A[pv] = A[max];
            A[max] = tmp;
            
            //Pivot
            for (int i = pv+1; i<b.length; i++){
                double a = A[i][pv] / A[pv][pv];
                
                b[i] -= a * b[pv];
                
                for (int j = pv; j<b.length; j++){
                    A[i][j] -= a * A[pv][j];
                }
            }
        }
        double[] x = new double[b.length];
        
        //Substitution
        for (int i = b.length-1; i>=0; i--){
            double sum = 0.0;
            for(int j = i+1; j<b.length; j++){
                sum += A[i][j] * x[j];
            }
            x[i] = (b[i] - sum) / A[i][i];
        }
        return x;
    }
    
    public static void main(String[] args) {
        

        
        double[][] A = {
            {2, 1, -1, -2},
            {4, 4, 1, 3},
            {-6, -1, 10, 10},
            {-2, 1, 8, 4}
        };
        
        double[] B = {2, 4, -5, 1};
        
        System.out.println("Ax = b");
        System.out.println("A =");
        for (int i = 0; i<A.length; i++){
            for(int j = 0; j<A[i].length; j++){
                System.out.printf("| %.3f ", A[i][j]);
            }
            System.out.println("|");
        }
        
        
        double[] x = Gauss(A, B);
        
        System.out.println("x =");
        for (int i = 0; i < A.length; i++) {
            System.out.printf("| %.3f |\n",x[i]);
        }
        
        double[] b = {2, 4, -5, 1};
        
        System.out.println("b =");
        for (int i = 0; i < A.length; i++) {
            System.out.printf("| %.3f |\n",b[i]);
        }
    }
    
}
