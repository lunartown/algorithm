import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//플로이드 워셜?
//트리기 때문에 굳이 쓸 필요는 없을 것 같다
//사이클이 없으므로,
class Edge {
    int node;
    int dis;

    public Edge(int node, int dis) {
        this.node = node;
        this.dis = dis;
    }
}

public class Main {
    static int N;
    static List<Edge>[] edges;

    static int findDist(int root, int child) {
        Queue<int[]> queue = new ArrayDeque<>();
        //현재 노드, root로부터 비용, 부모노드
        queue.add(new int[] {root, 0, 0});
        while (!queue.isEmpty()) {
            int[] curEdge= queue.poll();
            int curNode = curEdge[0];
            int curDis = curEdge[1];
            if (curNode == child) {
                return curDis;
            }

            for(Edge nextEdge : edges[curNode]) {
                //부모로는 안 감
                if(nextEdge.node == curEdge[2]) continue;
                queue.add(new int[] {nextEdge.node, curDis + nextEdge.dis, curNode});
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb= new StringBuilder();

        //노드의 개수
        N = Integer.parseInt(st.nextToken());
        //노드 쌍의 수
        int M = Integer.parseInt(st.nextToken());

        //간선 정보
        edges = new List[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int a = Integer.parseInt(st.nextToken());
            int b = Integer.parseInt(st.nextToken());
            int cost = Integer.parseInt(st.nextToken());
            edges[a].add(new Edge(b, cost));
            edges[b].add(new Edge(a, cost));
        }

        //알고자 하는 정보
        for(int i = 0; i < M; i++) {
            st= new StringTokenizer(br.readLine());
            sb.append(findDist(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken())))
                    .append('\n');
        }

        System.out.println(sb);
    }
}