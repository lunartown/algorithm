import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//연결된 지하철 역들을 세 개씩 끊는 게 가능한가?
//N-1개의 간선이 있음.
//트리형태라고 힌트를 주었다.
//1. leaf node가 2개 있거나
//2. 나의 node가 leaf node 1개 밖에 갖지 않았을 경우
//바로 카트!
//dfs라고 할 수 있다.
//1번 노드를 root로 보고 탐색하자.
public class Main {
    private static int N;
    private static List<Integer>[] edges;
    private static List<int[]> threes;

    private static boolean dfs(int node, int parent) {
        //먼저 자식부터 다녀옴
        for (int i = edges[node].size() - 1; i >= 0; i--) {
            while (edges[node].size() <= i) i--;
            int nextNode = edges[node].get(i);
            //부모나 리프노드는 방문x
            if (nextNode == parent || edges[nextNode].size() == 1)
                continue;
            if (!dfs(nextNode, node))
                return false;
        }

        //자식이 나랑 끊었을 수도 있기 때문에 개수는 다시 세야함
        //총 엣지 개수, 리프노드 개수
        List<Integer> parentLeaf = new ArrayList<>();
        List<Integer> leaves = new ArrayList<>();
        for (int nextNode : edges[node]) {
            if (nextNode == parent) continue;
            //리프노드 세기
            if (edges[nextNode].size() == 1)
                leaves.add(nextNode);

                //리프노드 부모 세기 (이중 진자 모양)
            else if (edges[nextNode].size() == 2) {
                int node1 = edges[nextNode].get(0);
                int node2 = edges[nextNode].get(1);
                if (node1 == node) node1 = node2;
                if (edges[node1].size() == 1)
                    parentLeaf.add(nextNode);
            }
        }

        //잎이 3개인 경우
        if (leaves.size() >= 3)
            return false;

        //잎이 2개인 경우
        if (leaves.size() == 2) {
            //간선 제거
            for (int nextNode : edges[node]) {
                edges[nextNode].remove(Integer.valueOf(node));
            }

            threes.add(new int[]{node, leaves.get(0), leaves.get(1)});
            return true;
        }

        if (parentLeaf.size() == 1) {
            //간선 제거
            for (int nextNode : edges[node]) {
                edges[nextNode].remove(Integer.valueOf(node));
            }

            threes.add(new int[]{node, parentLeaf.get(0), edges[parentLeaf.get(0)].get(0)});
            return true;
        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        N = Integer.parseInt(br.readLine());

        //그래프 그리기
        edges = new List[N + 1];
        int[] edgesCount = new int[N + 1];
        for (int i = 1; i <= N; i++) {
            edges[i] = new ArrayList<>();
        }
        threes = new ArrayList<>();

        for (int i = 1; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int nodeA = Integer.parseInt(st.nextToken());
            int nodeB = Integer.parseInt(st.nextToken());

            //간선 추가
            edges[nodeA].add(nodeB);
            edgesCount[nodeA]++;
            edges[nodeB].add(nodeA);
            edgesCount[nodeB]++;
        }

        dfs(1, 0);
        if (threes.size() == N / 3) {
            sb.append('S').append('\n');
            for (int[] three : threes) {
                sb.append(three[0]).append(' ').append(three[1])
                        .append(' ').append(three[2]).append('\n');
            }
        } else {
            sb.append('U');
        }
        System.out.println(sb);
    }
}