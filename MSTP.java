/**
*
* CSC 4512 Programming project No 3
*
* @author Hudson Kirkpatrick
*
*/
package mstp;

import java.util.*;



public class MSTP {
    
    //Stores the source, destination, and weight for each edge
    class edgeInfo{
        int srcNode;
        int destNode;
        int weight;
    }
    
    private final int nodes;
    
    private final int[][] nodeGraph;
    
    //Sets an N x N matrix with N being the number of nodes
    public MSTP(int node){
        
        nodes = node;
        
        nodeGraph = new int[node][node];
    }
    
    public void addEdge(int src, int dest, int wt){
        //To allow for source and desitnation to be interchangeable since the graph is undirected
        nodeGraph[src][dest] = wt;
        
        nodeGraph[dest][src] = wt;
    }
    
    int getMin(boolean [] mst, int [] key){
        
        int minumumKey = Integer.MAX_VALUE;
        
        int min = -1;
        
        for (int i = 0; i<nodes; i++){
            
            if(mst[i]==false && minumumKey>key[i]){
            
                minumumKey = key[i];
                
                min = i;
            }
        }
        return min;
    }
    
    
    
    public void getMST(){
        boolean[] mst = new boolean[nodes];
        
        edgeInfo[] edge = new edgeInfo[nodes];
        
        int[] key = new int[nodes];
        
        for(int i=0; i<nodes; i++){
            
            key[i] = Integer.MAX_VALUE;
            
            edge[i] = new edgeInfo();
        }
        
        //Initiates node 1 as the starting node 
        key[0] = 0;
        
        edge[0] = new edgeInfo();
        
        edge[0].srcNode = -1;
        
        for (int i = 0; i<nodes; i++){
            
            int Src = getMin(mst, key);
            
            mst[Src] = true;
            
            
            for (int j = 0; j<nodes; j++){
                
                if(nodeGraph[Src][j]>0){
                    
                    
                    if(mst[j]==false && nodeGraph[Src][j]<key[j]){
                        
                        key[j] = nodeGraph[Src][j];
                        
                        
                        edge[j].srcNode = Src;
                        
                        edge[j].destNode = j;
                        
                        edge[j].weight = key[j];
                    }
                    
                }
                
            }
            
        }
        int optimum_soln = 0;
        //Adds the optimal edge weights to the optimum
        for(int i = 1; i<nodes; i++){
            System.out.printf("Edge %d is from node %d to node %d with weight %d\n", i, edge[i].srcNode+1, edge[i].destNode+1, edge[i].weight);
            optimum_soln += edge[i].weight;
        }
        System.out.println("The optimal solution for the tree is: " + optimum_soln);
    }
    
    public static void main(String[] args) {
        
        //Uses scanners to handle data input
        Scanner scan = new Scanner(System.in);
        System.out.println("Enter the amount of nodes");
        int n = scan.nextInt();
        MSTP graph = new MSTP(n);
        System.out.println("Enter the amount of edges");
        int e = scan.nextInt();
        
        //Takes the amount of edges and iterates through them to add to the matrix
        for (int i = 1; i<=e; i++){
            System.out.printf("Enter edge %d in src, dest, weight\n", i);
            int s = scan.nextInt();
            int d = scan.nextInt();
            int w = scan.nextInt();
            //Decrements by 1 since the matrices start at 0
            graph.addEdge(s-1, d-1, w);
        }
        
        graph.getMST();
    }
    
}
