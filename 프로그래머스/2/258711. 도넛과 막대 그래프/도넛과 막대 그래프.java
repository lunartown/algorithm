import java.util.*;

//자신으로부터 나가는 간선밖에 없는 것은
//막대 모양 그래프의 루트 노드, 그리고 새로 생성한 정점밖에 없다.
//만약 나가는 간선만 있으며 2 이상이면, 무조건 새로 생성한 정점
//1이라면, 그래프가 총 1개 : 확인해봐야 함
//간선이 2개인 노드의 부모 = 새로 생성한 정점
//그래프 확인법 : 간선 개수 세면 됨
class Solution {
    private static final int SIZE = 1_000_001;
    
    public int[] solution(int[][] edges) {
        int[] answer = new int[4];
        
        //그래프 그리기
        //나가는 건 세기 쉬우므로 들어오는 갯수 따로 써두기
        List<Integer>[] graph = new List[SIZE];
        int[] income = new int[SIZE];
        for(int[] edge : edges) {
            int from = edge[0];
            int to = edge[1];
            if(graph[from] == null) {
                graph[from] = new ArrayList<>();
            }
            graph[from].add(to);
            income[to]++;
        }
        
        //들어오는 게 없고
        //나가는 게 2 이상이라면 : 거기가 생성한 정점
        for(int i = 1; i < SIZE; i++) {
            if(income[i] == 0 && graph[i] != null && graph[i].size() > 1) {
                answer[0] = i;
                break;
            }
        }
        
        if(answer[0] == 0)
            return answer;
        
        //정점의 수 : 아까 셈
        //그래프의 수 : 생성한 정점의 간선 수
        //그래프 찾기 : 생성한 정점과 연결된 노드부터 탐색 시작
        for(int start : graph[answer[0]]) {
            income[start]--;
            
            int node = start;
            while(graph[node] != null && graph[node].size() == 1) {
                node = graph[node].get(0);
                if(node == start) {
                    break;
                }
            }
            
            if(graph[node] == null || graph[node].size() == 0) {
                answer[2]++;
            } else if (graph[node].size() == 2) {
                answer[3]++;
            } else if (graph[node].size() == 1 && node == start) {
                answer[1]++;
            }
        }
        
        return answer;
    }
}