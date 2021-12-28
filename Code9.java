/**
*
* CSC 2262 Programming project No 6
*
* @author Hudson Kirkpatrick
* @since 12/02/2020
*
*/
package code9;


public class Code9 {

       
    //yprime function
    public static double yprime(double x, double y){
        return (x*y) + ((4*x)/y);
    }
    
    public static void main(String[] args) {
        
        //Print out the header
        System.out.printf("%4s %4s  %6s\n", "h", "x", "yh(x)");
        //declaring h and y(0)
        double h = 0.25;
        
        double y_prev = 1;
        
        //Declaring the x interval values
        double[] x= new double[5];
                
        for (int i = 0; i<x.length; i++){
            x[i] = h * i;
        }
        
        System.out.printf("%.2f %.2f  %.4f\n", h, x[0], y_prev + h * yprime(x[0],y_prev));
        
        for(int i = 1; i<x.length; i++){
            double y = y_prev + h * yprime(x[i-1],y_prev);
            System.out.printf("%.2f %.2f  %.4f\n", h, x[i], y);
            y_prev = y;
            
        }
        
    }
    
}
