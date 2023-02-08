import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Stack;
import java.util.StringTokenizer;

class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringBuilder sb = new StringBuilder();
		StringTokenizer st = new StringTokenizer(in.readLine());
				
		int L = Integer.parseInt(st.nextToken());
		int C = Integer.parseInt(st.nextToken());
		int[] pos = new int[L + 1];
		char[] let = new char[C + 1];
		st = new StringTokenizer(in.readLine());
		
		for(int i = 1; i <= C; i++)
			let[i] = st.nextToken().charAt(0);
		for(int i = 1; i <= L - 1; i++)
			pos[i] = i;
		pos[L] = L - 1;
		
		Arrays.sort(let);
		int p = 0;
			
//		포인터 옮기기 -> 맨 오른쪽부터 출발
		label:while(true) {
			p = 0;
			
//			움직일 수 있는 포인터는 어디에?
			while(pos[L - p] >= C - p) {
				if(p == L - 1) break label;
				p++;
			}
			pos[L - p]++;
			
//			재정렬
			for(int k = L - p; k < L; k++)
				pos[k + 1] = pos[k] + 1;
			
//			조건 확인
			int con = 0;
			int vow = 0;
			label2:for(int k = 1; k <= L; k++) {
				for(int l = 0; l < 5; l++) {
					if(let[pos[k]] == vowel[l]) {
						vow++;
						continue label2;
					}
				}
				con++;
			}
			
			if(vow < 1 || con < 2) continue;
			
//			출력
			for(int k = 1; k <= L; k++)
				sb.append(let[pos[k]]);
			sb.append('\n');
		}
		
		System.out.println(sb);
		
	}
	
	static char[] vowel = {'a', 'e', 'i', 'o', 'u'};
}