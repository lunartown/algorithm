import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static int[] parent;

    static int find(int node) {
        if(parent[node] < 0) return node;
        return parent[node] = find(parent[node]);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(parentA == parentB) return;

        if(parent[parentA] <= parent[parentB]) {
            parent[parentA] = Math.max(parent[parentA], parent[parentB] - 1);
            parent[parentB] = parentA;
        } else {
            parent[parentA] = parentB;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        while(true) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int M = Integer.parseInt(st.nextToken());
            int N = Integer.parseInt(st.nextToken());
            if(N == 0 && M == 0) break;

            Edge[] edges = new Edge[N];
            int sum = 0;

            for (int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                int from = Integer.parseInt(st.nextToken());
                int to = Integer.parseInt(st.nextToken());
                int cost = Integer.parseInt(st.nextToken());
                edges[i] = new Edge(from, to, cost);
                sum += cost;
            }

            Arrays.sort(edges, (a, b) -> a.cost - b.cost);

            parent = new int[M];
            Arrays.fill(parent, -1);

            int minCost = 0;
            for (int i = 0; i < N; i++) {
                int from = edges[i].from;
                int to = edges[i].to;
                int cost = edges[i].cost;

                if (find(from) == find(to)) continue;

                union(from, to);
                minCost += cost;
            }

            sb.append(sum - minCost).append("\n");
        }

        System.out.println(sb);
    }
}

class Edge {
    int from;
    int to;
    int cost;

    Edge(int from, int to, int cost) {
        this.from = from;
        this.to = to;
        this.cost = cost;
    }
}