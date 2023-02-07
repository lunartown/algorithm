import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
	
	public static void main(String[] args) throws NumberFormatException, IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		
		int N = Integer.parseInt(in.readLine());
		int[] sw = new int[N];
		int[] cmd = {0, 0};
		
//		스위치 입력 받기
		StringTokenizer st = new StringTokenizer(in.readLine());
		for(int i = 0; i < N; i++)
			sw[i] = Integer.parseInt(st.nextToken());
		
//		학생 수
		int M = Integer.parseInt(in.readLine());
		
//		명령 입력
		for(int i = 0; i < M; i++) {
			st = new StringTokenizer(in.readLine());
			cmd[0] = Integer.parseInt(st.nextToken());
			cmd[1] = Integer.parseInt(st.nextToken());
			
//			남학생
			if(cmd[0] == 1) {
//				j는 숫자의 배수
				for(int j = cmd[1]; j <= N; j += cmd[1]) {
					if(sw[j - 1] == 0) sw[j - 1] = 1;
					else if(sw[j - 1] == 1) sw[j - 1] = 0;
				}
			}
			
//			여학생
			else if(cmd[0] == 2) {
				for(int j = 0; cmd[1] - 1 - j >= 0 && cmd[1] - 1 + j < N; j++) {
//					왼쪽 오른쪽이 같다면
					if(sw[cmd[1] - 1 - j] == sw[cmd[1] - 1 + j]) {
//						뒤집어라
						if(sw[cmd[1] - 1 - j] == 1) {
							sw[cmd[1] - 1 - j] = 0;
							sw[cmd[1] - 1 + j] = 0;
						} else if(sw[cmd[1] - 1 - j] == 0) {
							sw[cmd[1] - 1 - j] = 1;
							sw[cmd[1] - 1 + j] = 1;
						}
//						실패 시 탈출
					} else break;
				}
			}			
		}
		
		for(int i = 0; i < N; i++) {
			System.out.print(sw[i] + " ");
			if(i % 20 == 19) System.out.println();
		}
	}

}
