/**
*
* CSC 2262 Programming project No 1
*
* @author Hudson Kirkpatrick
* @since 10/05/2020
*
*/
package code4;


public class Code4 {
    
    

    public static void main(String[] args) {
        double[] j = new double[]{0, 1, 2, 3, 4, 5, 6};
        double[] xj = new double[]{17, 20, 23, 24, 25, 27, 27.7};
        double[] fxj = new double[]{4.5, 7.0, 6.1, 5.6, 5.8, 5.2, 4.1};
        
        double[] h = new double[7];
        double[] b = new double[7];
        double[] c = new double[7];
        double[] d = new double[7];
        double[] l = new double[7];
        double[] m = new double[7];
        double[] z = new double[7];
        
        
        int n = 6;
        
        //Step 1 of the Algorithm
        //values for step 2 already given.
        for(int i = 0; i<n; i++){
            h[i] = xj[i+1] - xj[i];
        }
        
        //Step 3
        l[0] = 1;
        m[0] = 0;
        z[0] = 0;
        
        
        
        // Step 4
        for(int i=1; i<n; i++){
            l[i] = 2*(xj[i+1]-xj[i-1])-h[i-1]*m[i-1];
            m[i] = h[i]/l[i];
            z[i] = (fxj[i]-h[i-1]*z[i-1])/l[i];
        }
        
        //Step 5
        l[n] = 1;
        z[n] = 0;
        c[n] = 0;
        
        //Step 6
        for(int i = n-1; i>=0; i--){
            c[i] = z[i]-m[i]*c[i+1];
            b[i] = ((fxj[i+1] - fxj[i])/h[i]) - ((h[i]*(c[i+1]+ 2 * c[i]))/3);
            d[i] = (c[i+1] - c[i])/(3*h[i]);
        }
        
        //Step 7 Output
        System.out.printf("%5s %10s %10s %10s %10s %10s\n","j","x_j","a_j=f(x_j)","b_j","c_j","d_j");
        for (int i = 0; i<=n; i++){
            System.out.printf("%5.0f %10.1f %10.1f %10f %10f %10f\n",j[i], xj[i], fxj[i], b[i], c[i], d[i]);
        }
    }
    
}
