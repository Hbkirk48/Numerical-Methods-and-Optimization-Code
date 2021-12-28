/**
*
* CSC 4512 Programming project No 2
*
* @author Hudson Kirkpatrick
*
*/
package optimization_2;


import java.util.*;


public class Optimization_2 {
    
    private final double[][] A;
    private final int M;
    private final int N;
    private final int MaxorMin;
    
    private final String[] bVars;
    private final String[] nbVars;
    
    public Optimization_2(int maxormin, double[][] a, double[] b, double[] c){
        
        M = b.length;
        
        N = c.length;
        
        A = new double[M+1][M+N+1];
        
        MaxorMin = maxormin;
        //Generates a string array for basic s(1-m) and nonbasic x(1-n) variables 
        nbVars = new String[N];
        bVars = new String[M];
        
        for (int i = 0; i<nbVars.length; i++){
            nbVars[i] = "x"+(i+1);
        }
        for (int i = 0; i<bVars.length; i++){
            bVars[i] = "s"+(i+1);
        }
        
        
        //Inputs the regular coefficients of the tableaux
        for(int i = 0; i<M; i++){
            for(int j=0; j<N; j++){
                    
                A[i][j] = a[i][j];
                nbVars[j] = "x"+(j+1);
            }
        }
        //Creates an identity matrix for the slack variables
        for (int j=N; j<M+N; j++){
            A[j-N][j] = 1.0;
            
        }
        
        //Inputs the row with the optimal coefficents
        for (int j=0; j<N; j++){
            if (maxormin == 0){
                A[M][j] = c[j];
            }
            //Flips the optimal coefficients to negative if minimizing
            else{
                A[M][j] = -c[j];
            }
        }
        
        //Inputs RHS column
        for (int i=0; i<M; i++){
            A[i][M+N] = b[i];
        }
        //Adjusts for negative RHS values
        for (int i = 0; i < M; i++){
            if (b[i]<0){
                for (int j = 0; j <= N+M; j++){
                    A[i][j] = -A[i][j];
                }
            }
        }

        System.out.printf("There are %d constraints and %d variables\n", M, N);
        
        //Prints out the basic and non-basic variables
        System.out.println("The basic variables are:");
        for (int i = 0; i<bVars.length; i++){
            System.out.printf("%s = %.2f\n", bVars[i], b[i]);
        }
        
        System.out.println("The non-basic variables are:");
        for (String nbVar : nbVars) {
            System.out.printf("%s = 0\n", nbVar);
        }
        
        printTableaux();
        solve(maxormin);
        
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
    
    public final void pivot(int p, int q){
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
    
    private void solve(int MaxorMin){
        while(true){
            int q = eVar();//Sets q as the entering variable column
                        
            if (q== -1) break;
            
            System.out.printf("The entering variable is in column %d and is %s\n", q, nbVars[q]);
            
            int p = minRat(q);//Sets p as the pivot row
            
            if (p==-1) throw new ArithmeticException("Linear program is unbounded");
            
            System.out.printf("The pivot row(leaving variable) is row %d and is %s\n", p, bVars[p]);
            
            pivot(p, q);
            //Sets the entering and leaving variables to the corresponding basic/non-basic variable string
            String tempNBvar = nbVars[q];
            String tempBvar = bVars[p];
            //Swaps the variables
            nbVars[q] = tempBvar;
            bVars[p] = tempNBvar;
            //Prints out the new basic and non-basic variables
            System.out.println("The basic variables are:");
            for (int i = 0; i<bVars.length; i++) {
                System.out.printf("%s = %.2f\n", bVars[i], A[i][M+N]);
            }
        
            System.out.println("The non-basic variables are:");
            for (String nbVar : nbVars) {
                System.out.printf("%s = 0\n", nbVar);
            }
            printTableaux();
        }
        if (MaxorMin == 0){
            System.out.println("Optimal Value = " + value());
        }
        else{
            System.out.println("Optimal Value = " + -value());
        }
    }
    
    public final void printTableaux(){
        
        for (int i = 0; i < M+1; i++) {
            for (int j = 0; j <= N+M; j++) {
                System.out.printf("%6.2f | ", A[i][j]);
                if (j == N+M-1) System.out.print(" |");
            }
            System.out.println();
        }

        System.out.println();
    }
    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        
        //Scanners used to input different problem setups
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter 0 for max, 1 for min");
        int maxormin = scan.nextInt();
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
        Optimization_2 lp = new Optimization_2(maxormin, A, b, c);//Runs the solver and outputs the optimal value
    }
    
}