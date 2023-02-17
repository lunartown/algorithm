import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;


public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(in.readLine());
		
		int N = Integer.parseInt(st.nextToken());
		int M = Integer.parseInt(st.nextToken());
		
		int[][] city = new int[N+1][N+1];
		List<int[]> house = new ArrayList<>();
		List<int[]> chicken = new ArrayList<>();
		List<int[]> newChicken = new ArrayList<>();
		
//		도시 입력받기
//		집, 치킨집의 위치 리스트에 저장
		for(int i = 1; i <= N; i++) {
			st = new StringTokenizer(in.readLine());
			for(int j = 1; j <= N; j++) {
				city[i][j] = Integer.parseInt(st.nextToken());
				if(city[i][j] == 1) house.add(new int[] {i, j});
				else if(city[i][j] == 2) chicken.add(new int[] {i, j});
			}
		}
		
		combination(house, chicken, newChicken, M, 0, -1);
		System.out.println(min);
		
	}
	
	static int min = Integer.MAX_VALUE;
	
	static void combination(List<int[]> house, List<int[]> chicken, List<int[]> newChicken, int M, int cnt, int flag) {
		if(newChicken.size() >= M) {
			min = Math.min(min, chickenDis(house, newChicken));
			return;
		}
		
		for(int i = 0; i < chicken.size(); i++) {
			if(i <= flag) continue;
			newChicken.add(chicken.get(i));
			combination(house, chicken, newChicken, M, cnt+1, i);
			newChicken.remove(newChicken.size() - 1);
		}
	}
	
	static int chickenDis(List<int[]> house, List<int[]> chicken) {
		int dis, disCity = 0;
		for(int i = 0; i < house.size(); i++) {
			dis = Integer.MAX_VALUE;
			for(int j = 0; j < chicken.size(); j++) {
				dis = Math.min(dis, distance(house.get(i), chicken.get(j)));
			}
			disCity += dis;
		}
		return disCity;
	}
	
	static int distance(int[] house, int[] chicken) {
		return (Math.abs(house[0] - chicken[0]) + Math.abs(house[1] - chicken[1]));
	}
	
}