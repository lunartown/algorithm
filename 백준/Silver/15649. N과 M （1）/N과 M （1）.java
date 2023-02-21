import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;


public class Main {

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());
        
        num = new int[M];
        vis = new boolean[N+1];
        		
        permutation(0);
        System.out.println(sb);
    }
    
    static StringBuilder sb = new StringBuilder();
    static int[] num;
    static boolean[] vis;
    static int N, M;
    
    static void permutation(int k) {
    	if(k == M) {
    		for(int i = 0; i < M; i++) {
    			sb.append(num[i]).append(' ');
    		}
    		sb.append('\n');
    		return;
    	}
    	for(int i = 1; i <= N; i++) {
    		if(vis[i] == true) continue;
    		num[k] = i;
    		vis[i] = true;
    		permutation(k+1);
    		vis[i] = false;
    	}
    }
}