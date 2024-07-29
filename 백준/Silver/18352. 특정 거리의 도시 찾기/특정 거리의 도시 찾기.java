import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//거리가 정확히 K인 도시의 수?
//다익스트라로 최단 거리 찾기
public class Main {
    static class Node {
        int position;
        int distance;

        public Node(int position, int distance) {
            this.position = position;
            this.distance = distance;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //도시의 개수
        int N = Integer.parseInt(st.nextToken());
        //도로의 개수
        int M = Integer.parseInt(st.nextToken());
        //거리
        int K = Integer.parseInt(st.nextToken());
        //출발 도시
        int X = Integer.parseInt(st.nextToken());

        //간선 목록
        List<Integer>[] edges = new List[N + 1];
        for(int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            edges[Integer.parseInt(st.nextToken())]
                    .add(Integer.parseInt(st.nextToken()));
        }

        //다익스트라 어떻게 하더라?
        //모든 점의 거리를 INF로 초기화
        //내 노드에 연결된 간선을 순회하면서
        //그 간선의 길이 + 나의 value가 next의 value보다 짧다면
        //갱신
        int[] dist = new int[N + 1];
        for(int i = 1; i <= N; i++) {
            dist[i] = Integer.MAX_VALUE;
        }
        dist[X] = 0;
        PriorityQueue<Node> pq = new PriorityQueue<>(
                Comparator.comparingInt((Node a) -> a.distance)
        );
        pq.add(new Node(X, 0));
        while(!pq.isEmpty()) {
            Node cur = pq.poll();
            if(cur.distance > dist[cur.position])
                continue;
            for(int i = 0; i < edges[cur.position].size(); i++) {
                int next = edges[cur.position].get(i);
                int value = 1 + dist[cur.position];
                if(dist[next] <= value)
                    continue;
                dist[next] = value;
                pq.add(new Node(next, value));
            }
        }

        for(int i = 0; i <= N; i++) {
            if(dist[i] == K)
                sb.append(i).append('\n');
        }

        System.out.println(sb.length() == 0? -1 : sb.toString());
    }
}