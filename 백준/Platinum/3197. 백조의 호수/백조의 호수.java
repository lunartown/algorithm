import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Queue;
import java.util.StringTokenizer;

public class Main {
    static int R, C;
    static int[] dx = {0, 1, 0, -1};
    static int[] dy = {1, 0, -1, 0};

    static void init(int[] parent, char[][] lake) {
//    	모든 좌표 -1로 초기화
        for(int i = 0; i < R * C; i++) {
            parent[i] = -1;
        }

        int nx, ny;
        for(int i = 0; i < R; i++) {
            for(int j = 0; j < C; j++) {
                if(lake[i][j] != 'X') {
                    for(int k = 0; k < 4; k++) {
                        nx = i + dx[k];
                        ny = j + dy[k];
                        if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                        if(lake[nx][ny] != 'X') {
                            union(parent, lake, nx * C + ny, i * C + j);
                        }
                    }
                }
            }
        }
    }

    static int find(int[] parent, int a) {
        if(parent[a] < 0) return a;
        return parent[a] = find(parent, parent[a]);
    }

    static void union(int[] parent, char[][] lake, int a, int b) {
        int rootA = find(parent, a);
        int rootB = find(parent, b);

        if(rootA != rootB) {
            if(parent[rootA] < parent[rootB]) {
                parent[rootA] = Math.min(parent[rootA], parent[rootB] - 1);
                parent[rootB] = rootA;
            }else {
                parent[rootB] = Math.min(parent[rootB], parent[rootA] - 1);
                parent[rootA] = rootB;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(in.readLine());
        List<Integer> swan = new ArrayList<>();
        Queue<Integer> water = new ArrayDeque<>();
        
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        String str;
        char[][] lake = new char[R][C];

//      lake 배열 입력
//      백조, 물 입력 받기 
        for(int i = 0; i < R; i++) {
        	str = in.readLine();
        	for(int j = 0; j < C; j++) {
        		lake[i][j] = str.charAt(j);
                if(lake[i][j] == 'L') {
                    swan.add(i * C + j);
                }
                if(lake[i][j] != 'X') {
                	water.offer(i * C + j);
                }
        	}
        }

//      서로소 집합
        int[] parent = new int[R * C];;
        init(parent, lake);
        
        int day = 0;

        while(find(parent, swan.get(0)) != find(parent, swan.get(1))) {
            int x, y, nx, ny, nnx, nny, l = 0;
            int size = water.size();
            while(l < size) {
            	x = water.peek() / C;
            	y = water.poll() % C;
            	
            	for(int k = 0; k < 4; k++) {
                    nx = x + dx[k];
                    ny = y + dy[k];
                    if(nx < 0 || nx >= R || ny < 0 || ny >= C) continue;
                    if(lake[nx][ny] == 'X') {
                        water.offer(nx * C + ny);
                        lake[nx][ny] = '.';
                        union(parent, lake, nx * C + ny, x * C + y);
                        for(int i = 0; i < 4; i++) {
                        	nnx = nx + dx[i];
                        	nny = ny + dy[i];
                        	if(nnx < 0 || nnx >= R || nny < 0 || nny >= C) continue;
                        	if(lake[nnx][nny] != 'X') {
                        		union(parent, lake, nx * C + ny, nnx * C + nny);
                        	}
                        }
                    }
                }
            	l++;
            }
            
//            size = water.size();
//            while(l < size) {
//            	x = water.peek() / C;
//            	y = water.poll() % C;
//            	
//            	for(int i = 0; i < 4; i++) {
//            		nnx = nx + dx[k];
//            		nny = ny + dy[k];
//            		if(nnx < 0 || nnx >= R || nny < 0 || nny >= C) continue;
//            		if(lake[nnx][nny] != 'X') {
//            			union(parent, lake, nx * C + ny, nnx * C + nny);
//            		}
//            	}
//            	l++;
//            }
            	
            day += 1;
        }
        System.out.println(day);
    }
}