import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Queue;

//이모티콘
//https://www.acmicpc.net/problem/14226
//이게 bfs라고..??
//뭔가 더 좋은 방법이 있을 것 같은데, 일단 내일까지 풀어야 하므로 bfs로 ㄱㄱ..
//bfs로 실패, dp로 다시 풀어보자

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int S = Integer.parseInt(br.readLine());

        int[][] time = new int[1001][1001];

        for (int i = 0; i < 1001; i++) {
            for (int j = 0; j < 1001; j++) {
                time[i][j] = -1;
            }
        }

        time[1][0] = 0;

        Queue<int[]> queue = new ArrayDeque<>();
        queue.add(new int[]{1, 0});
        while (!queue.isEmpty()) {
            int[] current = queue.poll();
            int screen = current[0];
            int clipboard = current[1];
//            System.out.println("screen: " + screen + ", clipboard: " + clipboard + ", time: " + time[screen][clipboard]);

            if (screen == S) {
                System.out.println(time[screen][clipboard]);
                break;
            }

            if (time[screen][screen] == -1) {
                time[screen][screen] = time[screen][clipboard] + 1;
                queue.add(new int[]{screen, screen});
            }

            if (screen + clipboard <= S && time[screen + clipboard][clipboard] == -1) {
                time[screen + clipboard][clipboard] = time[screen][clipboard] + 1;
                queue.add(new int[]{screen + clipboard, clipboard});
            }

            if (screen - 1 >= 0 && time[screen - 1][clipboard] == -1) {
                time[screen - 1][clipboard] = time[screen][clipboard] + 1;
                queue.add(new int[]{screen - 1, clipboard});
            }
        }
    }
}
