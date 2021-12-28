/**
*
* CSC 2262 Programming project No 1
*
* @author Hudson Kirkpatrick
* @since 9/18/2020
*
*/

package code1;

public class Code1 {

    /**
     * This method returns the summation equation using the smallest to largest approach.
     * @param n
     * @return sum of smallest to largest iterations
     */
    public static double SL(double n){
        
        double sum = 0;
        for(double j = 1; j<=n; j++){
            sum =+ 1/((j*(j+2)));
        }
        return sum;
    }
    /**
     * This method returns the summation equation using the largest to smallest approach.
     * @param n
     * @return sum of largest to smallest iterations
     */
    public static double LS(double n){
        double sum = 0;
        for(double j = n; j>=1; j--){
            sum += 1/((j*(j+2)));
        }
        return sum;
        
        
    }
    
    /**
     * This method returns the true solution to the summation
     * @param n
     * @return true solution
     */
    public static double True(double n){
        double ans = (3/4)-((2*n+3)/(2*(n+1)*(n+2)));
        return ans;
    }

    
    public static void main(String[] args) {
        
        double[] nArray = new double[]{10, 50, 100, 500, 1000, 5000};
        
        System.out.printf("%5s %10s %10s %10s %10s %10s\n","n","True","SL","Error","LS","Error");
        for (int i = 0; i<nArray.length; i++){
            System.out.printf("%5.0f %10f %10f %10f %10f %10f\n",nArray[i],True(nArray[i]),SL(nArray[i]),True(nArray[i])-SL(nArray[i]),LS(nArray[i]),True(nArray[i])-LS(nArray[i]));
        }
        
    }
    
}
