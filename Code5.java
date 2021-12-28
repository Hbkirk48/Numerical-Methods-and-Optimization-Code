/**
*
* CSC 2262 Programming project No 5
*
* @author Hudson Kirkpatrick
* @since 10/28/2020
*
*/

package code5;

public class Code5 {
    
    /**This method returns the natural logarithm of the value x
     * 
     * @param x
     * @return ln(x)
     */
    public static double F(double x){
        return Math.log(x);
    }
    
    public static void main(String[] args) {
        
        double n = 512;
        
        double a = 1;
        
        double b = 3;
        
        double h = (b-a)/n;
        
        double[] X = new double[512];
        
        //Establishing the x values for the iterations
        for (int i = 0; i<X.length; i++){
            X[i] = a + (i * h);
        }
        
        //Runnin the Trapezoidal Rule 
        double fsum = 0;
        
        for (int i = 1; i<X.length; i++){
            fsum += F(X[i]);
        }
        
        double Tsum = h * (0.5*F(a) + fsum + 0.5*F(b));
        
        //Running the Simpson's rule iterations
        double Ssum = 0;
        
        for (int i = 0; i<X.length - 2; i+=2){
            Ssum += (h/3)*(F(X[i]) + 4*F(X[i+1]) + F(X[i+2]));
        }
        
        
        System.out.printf("Trapezoidal Rule: T512(f) = %.10f\n", Tsum);
        
        System.out.printf("Simpson Rule: T512(f) = %.10f\n", Ssum);
    }
    
}
