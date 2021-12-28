/**
*
* CSC 2262 Programming project No 3
*
* @author Hudson Kirkpatrick
* @since 9/30/2020
*
*/

package code3;

import java.math.*;
import java.text.DecimalFormat;

public class Code3 {

    public static float pro(int i, float v, float x[]){
        float p = 1;
        for (int j = 0; j<i; j++){
            p = p * (v - x[j]);
        }
        return p;
    }
    
    public static void ddTable(float x[], float y[][], int n){
        
        for(int i=1; i<n; i++){
            for(int j=0; j<i; j++){
                y[j][i] = (y[j][i-1]-y[j+1][i-1])/(x[j]-x[i-j]);
            }
        }
    }
    
    public static float form(float v, float x[], float y[][], int n){
        float sum = y[0][0];
        
        for (int i = 1; i<n; i++){
            sum += (pro(i, v, x) * y[0][i]);
        }
        return sum;
    }
    
    public static void pddTable(float y[][], int n){
        
        DecimalFormat df = new DecimalFormat("#.########");
        
        for(int i = 0; i<n; i++){
            for(int j = 0; j<n-1; j++){
                String s = df.format(y[i][j]);
                System.out.print(s + "\t");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        
        int n = 6;
        float y[][] = new float[10][10];
        float x[] = {0.0f, 0.2f, 0.4f, 0.6f, 0.8f, 1.0f, 1.2f};
        
        for (int i = 0; i<x.length; i++){
            y[i][0] = (float) Math.sin(x[i]);
        }
        
        System.out.printf("%5s %10s %10s %10s \n","i", "x_i", "sin(x_i)", "D_i");
        
        ddTable(x, y, n);
        
        pddTable(y, n);
        
        float v[] = {0.1f, 0.3f, 0.5f};
        
        System.out.printf("%5s %10s %10s %10s \n","n", "Pn(0.1)", "Pn(0.3)", "Pn(0.5)");
        for(float i = 0; i<=n; i++){
            System.out.printf("%.0f %10s %10s %10s \n",i, form(v[0], x, y, n), form(v[1], x, y, n), form(v[2], x, y, n));
        }
    }
    
}
