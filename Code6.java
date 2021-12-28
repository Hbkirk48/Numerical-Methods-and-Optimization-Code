/**
*
* CSC 2262 Programming project No 6
*
* @author Hudson Kirkpatrick
* @since 11/06/2020
*
*/
package code6;

public class Code6 {
    
    /**This method returns the sine of the value x
     * 
     * @param x
     * @return sin(x)
     */
    public static double f(double x){
        return Math.sin(x);
    }
    
    
    public static void main(String[] args) {
        
        //Establshing the x value and the h values for the iterations
        double x = (2.0/3.0) * Math.PI;
        double h[]= new double[]{0.1, 0.05, 0.025, 0.0125, 0.00625};
        
        //Declaring the arrays of the differentiation methods to be filled in the iterations
        double FD[] = new double[h.length];
        double BD[] = new double[h.length];
        double LB[] = new double[h.length];
        double UC[] = new double[h.length];
        
        //Printing out the header for the output
        System.out.printf("%12s %12s %12s %12s %12s \n", "h", "FDF", "BDF", "LB", "UC");
        
        //Running each of the methods through the iterations
        for (int i = 0; i<h.length; i++){
            FD[i] = (f(x + h[i]) - f(x)) /h[i];
            BD[i] = (f(x) - f(x-h[i]))/h[i];
            LB[i] = (f(x + h[i]) - f(x - h[i]))/ (2*h[i]);
            UC[i] = (f(x + h[i]) - (2*f(x)) + f(x - h[i]))/ (h[i]*h[i]);
            System.out.printf("%.10f %.10f %.10f %.10f %.10f \n", h[i], FD[i], BD[i], LB[i], UC[i]);
        }
        
        
        
    } 
    
}
