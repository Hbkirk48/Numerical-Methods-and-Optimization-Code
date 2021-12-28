/**
*
* CSC 2262 Programming project No 2
*
* @author Hudson Kirkpatrick
* @since 9/25/2020
*
*/

package code2;

public class Code2 {

    /**
     * This method returns the output of the function
     * @param x
     * @return cos(x)-x
     */
    public static double F(double x){
        return Math.cos(x)-x;
    }
    

    
    public static void main(String[] args) {
        
        //Declaring the two endpoints as the first two iterations of the method.
        double x_0 = 0, x_1 = Math.PI/2,x_n,e, ea = .0001;
        int n = 0;
        boolean notroot = true;
        
        //Printing out reulst of first two iterations.
        System.out.printf("%5s %10s %10s %12s\n","n","x_n","f(x_n)","x_n-x_(n-1)");
        
        System.out.printf("%5d %10f %10f \n", n, x_0, F(x_0));
        
        n++;
        
        System.out.printf("%5d %10f %10f %12f\n", n, x_1, F(x_1), F(x_1)-F(x_0));
        
        n++;
        
        //Running the method.
        while (notroot){
            
            x_n = x_0 - ((F(x_0)*(x_0 - x_1))/(F(x_0)-F(x_1)));
            
            e = Math.abs((x_n - x_0)/x_n) * 100;
            
            //Outputting results of every other iteration.
            System.out.printf("%5d %10f %10f %12f\n", n, x_n, F(x_n), x_n - x_0);
            
            n++;
            
            x_0 = x_1;
            x_1 = x_n;
            
            //Checking against acceptable error at the end of every loop.
            if (e < ea){
                notroot = false;
            }
        }
    }
    
}