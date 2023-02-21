import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        
        N = Integer.parseInt(st.nextToken());
        K = Integer.parseInt(st.nextToken());
        
        kits = new int[N];
        program = new int[N];
        vis = new boolean[N];
        
        st = new StringTokenizer(in.readLine());
        for(int i = 0; i < N; i++) {
        	kits[i] = Integer.parseInt(st.nextToken());
        }
        
        permutation(0, 0);
        
        System.out.println(count);
	}
    
    static int N, K, count, kits[], program[];
    static boolean vis[];
    static void permutation(int k, int sum) {
    	if(sum < 0) return;
    	if(k == N) {
    		count++;
    		return;
    	}
    	for(int i = 0; i < N; i++) {
    		if(vis[i] == true) continue;
    		program[k] = kits[i];
    		vis[i] = true;
    		permutation(k+1, sum + program[k] - K);
    		vis[i] = false;
    	}
    }
}