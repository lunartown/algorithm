import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.StringTokenizer;

public class Main {

	public static void main(String[] args) throws NumberFormatException, IOException {

		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = null;
		StringBuilder sb = new StringBuilder();
		int a, b;
		
		int N = Integer.parseInt(in.readLine());
		Node[] tree = new Node[N+1];
		ArrayDeque<Node> stack = new ArrayDeque<>();
		int[] treeNode = new int[N+1];
		
		for(int i = 1; i <= N; i++)
			tree[i] = new Node(i);
		
		for(int i = 1; i < N; i++) {
			st = new StringTokenizer(in.readLine());
			a = Integer.parseInt(st.nextToken());
			b = Integer.parseInt(st.nextToken());
			
			tree[a].add(tree[b]);
			tree[b].add(tree[a]);
			}
		
		stack.offer(tree[1]);
		treeNode[1] = -1;
		
		while(!stack.isEmpty()) {
			Node node = stack.poll();
			for(int j = 0; j < node.nodes.size(); j++) {
				if(node.nodes.get(j) != null && treeNode[node.nodes.get(j).value]==0) {
					stack.offer(node.nodes.get(j));
					treeNode[node.nodes.get(j).value] = node.value;
				}
			}
		}
		
		for(int i = 2; i <= N; i++)
			sb.append(treeNode[i]).append('\n');
		
		System.out.println(sb);
	}
	
//	Node라는 클래스를 만듭니다. 이름은 value입니다.
	static class Node {
		int value;
		
//		간선을 많이 가질 수 있기 때문에 ArrayList로 만듭니다.
		ArrayList<Node> nodes = new ArrayList<>();
		
//		생성자
		Node(int value){
			this.value = value;
		}
		
//		간선을 추가
		void add(Node node) {
			this.nodes.add(node);
		}
	}
}