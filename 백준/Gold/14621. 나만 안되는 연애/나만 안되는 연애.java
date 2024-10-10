import java.io.*;
import java.util.*;

public class Main {
    static int[] parent;

    static int find(int node) {
        if(parent[node] < 0) return node;
        return parent[node] = find(parent[node]);
    }

    static void union(int a, int b) {
        int parentA = find(a);
        int parentB = find(b);

        if(parent[parentA] <= parent[parentB]) {
            parent[parentA] = Math.min(parent[parentA], parent[parentB] - 1);
            parent[parentB] = parentA;
        } else if(parent[parentA] > parent[parentB]) {
            parent[parentA] = parentB;
        }
    }

    public static void main(String[] args) throws IOException {
        //최소 신장 트리
        //union-find
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int[] univ = Arrays.stream(br.readLine().split(" ")).mapToInt(i -> i.charAt(0)).toArray();
        List<Edge> edges = new ArrayList<>();

        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            int from = Integer.parseInt(st.nextToken()) - 1;
            int to = Integer.parseInt(st.nextToken()) - 1;
            if(univ[from] == univ[to]) continue;
            int dis = Integer.parseInt(st.nextToken());
            edges.add(new Edge(from, to, dis));
        }

        parent = new int[N];
        for(int i = 0; i < N; i++) {
            parent[i] = -1;
        }

        Collections.sort(edges);

        int size = edges.size();
        int count = 0;
        int idx = 0;
        int distance = 0;
        while(count < N - 1 && idx < size) {
            Edge edge = edges.get(idx);
            int from = edge.from;
            int to = edge.to;
            int dis = edge.dis;
            idx++;

            if(find(from) == find(to)) continue;
            distance += dis;
            count++;
            union(from, to);
        }
        
        System.out.println(count == N - 1 ? distance : -1);
    }
}

class Edge implements Comparable<Edge>{
    int from;
    int to;
    int dis;

    Edge(int from, int to, int dis) {
        this.from = from;
        this.to = to;
        this.dis = dis;
    }

    @Override
    public int compareTo(Edge e) {
        return this.dis - e.dis;
    }
}