/**
*
* CSC 4512 Programming project No 1
*
* @author Hudson Kirkpatrick
*
*/
package optimization_1;


import java.util.*;


public class Optimization_1 {
    
    private final double[][] A;
    private final int M;
    private final int N;
    
    public Optimization_1(double[][] a, double[] b, double[] c){
        
        M = b.length;
        
        N = c.length;
        
        A = new double[M+1][M+N+1];
        
        //Inputs the regular coefficients of the tableaux
        for(int i = 0; i<M; i++){
            for(int j=0; j<N; j++){
                A[i][j] = a[i][j];
            }
        }
        //Creates an identity matrix for the slack variables
        for (int j=N; j<M+N; j++){
            A[j-N][j] = 1.0;
        }
        
        //Inputs the row with the optimal coefficents
        for (int j=0; j<N; j++){
            A[M][j] = c[j];
        }
        
        //Inputs RHS column
        for (int i=0; i<M; i++){
            A[i][M+N] = b[i];
        }
        
        
        solve();
        
    }
    
    //Method to find the entering variable/column
    private int eVar(){
        for(int j = 0; j<M+N; j++){
            if(A[M][j] > 0) return j;
        }
        return -1;
    }
    
    
    private int minRat(int q){
        int p = -1;
        for (int i = 0; i < M; i++){
            if(A[i][q] <=0) continue;//Skips to the next entry if the ratio is negative
            else if (p == -1) p = i;//Sets positive ratio as the pivot row
            else if ((A[i][M+N]/A[i][q]) < (A[p][M+N]/A[p][q]))//Compares the positive ratio to the set pivot row
                p = i;//Resets the pivot row if a new minimum is found
        }
        return p;
    }
    
    public void pivot(int p, int q){
        for (int i=0; i<=M; i++){
            for (int j = 0; j<=M+N; j++){
                if(i==p || j==q) continue;//Ignores either row p or column q.
                else
                    A[i][j] -= A[p][j] * A[i][q] / A[p][q];//Rescales every other entry
            }
        }
        for (int i = 0; i<=M; i++){
            if (i!=p) A[i][q] = 0.0;//Zeroes out every entry on column q except point(p,q)
        }
        for (int j = 0; j<=M+N; j++){
            if (j!=q) A[p][j] /= A[p][q];//Refactors the rest of row p
        }
        A[p][q] = 1.0;//Sets the pivot point to 1
    }
    
    public double value() {
        return -A[M][M+N];
    }
    
    private void solve(){
        while(true){
            int q = eVar();//Sets q as the entering variable column
            
            if (q== -1) break;
            
            int p = minRat(q);//Sets p as the pivot row
            
            if (p==-1) throw new ArithmeticException("Linear program is unbounded");
            
            pivot(p, q);
        }
        System.out.println("Optimal Value = " + value());
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Scanners used to input different problem setups
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the amount of constraints");
        int m = scan.nextInt();
        System.out.println("Enter the amount of variables");
        int n = scan.nextInt();
        
        double[] c = new double[n];
        System.out.println("Enter the optimal coefficients");
        for (int i=0; i<n; i++){
            c[i] = scan.nextDouble();
        }            
        double[] b = new double[m];
        System.out.println("Enter the RHS values");
        for (int j=0; j<m; j++){
            b[j] = scan.nextDouble();
        }
        double[][] A = new double[m][m+n];
        
        System.out.println("Enter the coefficients of the tableaux");
        for (int i = 0; i < m; i++)
          {
              for (int j = 0; j < n; j++)
              {
                  A[i][j] = scan.nextDouble();//Only input the regular variables as the slack variables are added in the LP Method
              }
          }
        Optimization_1 lp = new Optimization_1(A, b, c);//Runs the solver and outputs the optimal value
    }
    
}
