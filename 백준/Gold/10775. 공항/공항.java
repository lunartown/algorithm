import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        int G = Integer.parseInt(in.readLine());
        int P = Integer.parseInt(in.readLine());
        
        Main main = new Main();
        DisjointSet set = main.new DisjointSet(G);
        
        int i = 0;
        for(; i < P; i++) {
            int num = Integer.parseInt(in.readLine());
            int pGate = set.find(num);
            int curGate = pGate + set.parent[pGate];
            if(curGate <= 0) break;
            else { 
                set.parent[curGate] = -1;
                set.union(pGate,curGate);
            }
            if(curGate > 1 && set.parent[curGate - 1] != 0) set.union(pGate, curGate - 1);
            if(curGate < G && set.parent[curGate + 1] != 0) set.union(curGate + 1, pGate);
        }
        
        System.out.println(i);
    }
    
    class DisjointSet {
        int[] parent;
        
        DisjointSet(int N){
            parent = new int[N+1];
        }
        
        int find(int x) {
            if(parent[x] <= 0) return x;
            return find(parent[x]);
        }
        
        void union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            if(rootX == rootY) return;
            parent[rootX] += parent[rootY];
            parent[rootY] = rootX;
        }
    }
}