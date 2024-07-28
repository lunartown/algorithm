import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.sql.Array;
import java.util.*;
import java.util.stream.Stream;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        //M: node 개수 M: 간선 개
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        //간선 정보
        List<Integer>[] arcs = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            arcs[i] = new ArrayList<>();
        }
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            arcs[a].add(b);
        }

        //팬클럽
        int S = Integer.parseInt(br.readLine());
        boolean[] fans = new boolean[N + 1];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < S; i++) {
            fans[Integer.parseInt(st.nextToken())] = true;
        }


        //bfs준비
        Queue<Integer> queue = new ArrayDeque<>();
        boolean[] visited = new boolean[N + 1];
        boolean succeed = false;

        //출발!
        if(!fans[1])
            queue.offer(1);

        while (!queue.isEmpty()) {
            int curNode = queue.poll();
            visited[curNode] = true;

            //끝에 도달했으면 성공
            if(arcs[curNode].isEmpty()) {
                succeed = true;
                break;
            }

            for (int nextNode : arcs[curNode]) {
                //방문했거나, 팬이 있으면 안 감
                if(visited[nextNode] || fans[nextNode]) {
                    continue;
                }
                queue.offer(nextNode);
            }
        }

        System.out.println(succeed? "yes" : "Yes");
    }
}