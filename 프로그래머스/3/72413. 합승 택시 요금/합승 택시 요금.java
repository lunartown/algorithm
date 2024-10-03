import java.util.*;

class Solution {
    final int MAX = Integer.MAX_VALUE;
    List<Edge>[] edges;
    
    void Djik(int start, int[] costs) {
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        Arrays.fill(costs, MAX);
        
        costs[start] = 0;
        
        for(Edge e : edges[start]) {
            pq.add(e);
            costs[e.to] = e.cost;
        }
        
        while(!pq.isEmpty()) {
            Edge edge = pq.poll();
            int node = edge.to;
            
            for(Edge next : edges[node]) {
                int to = next.to;
                int cost = next.cost;
                
                if(costs[node] + cost < costs[to]) {
                    costs[to] = costs[node] + cost;
                    pq.add(next);
                }
            }
        }
    }
    
    public int solution(int n, int s, int a, int b, int[][] fares) {
        int[] sCost = new int[n + 1];
        int[] aCost = new int[n + 1];
        int[] bCost = new int[n + 1];
        
        edges = new List[n + 1];
        for(int i = 1; i <= n; i++) {
            edges[i] = new ArrayList<>();
        }
        
        for(int[] fare : fares) {
            int from = fare[0];
            int to = fare[1];
            int cost = fare[2];
            
            edges[from].add(new Edge(to, cost));
            edges[to].add(new Edge(from, cost));
        }
        
        Djik(s, sCost);
        Djik(a, aCost);
        Djik(b, bCost);
        
        int min = aCost[s] + bCost[s];
        for(int i = 1; i <= n; i++) {
            int cost = sCost[i] + aCost[i] + bCost[i];
            min = cost < min ? cost : min;
        }
        
        int answer = min;
        return answer;
    }
}

class Edge implements Comparable<Edge>{
    int to;
    int cost;

    Edge(int to, int cost) {
        this.to = to;
        this.cost = cost;
    }
    
    @Override
    public int compareTo(Edge e) {
        return this.cost - e.cost;
    }
}