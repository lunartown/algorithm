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
			System.out.println(treeNode[i]);
	}
	
	static class Node {
		int value;
		ArrayList<Node> nodes = new ArrayList<>();
		
		Node(int value){
			this.value = value;
		}
		
		void add(Node node) {
			this.nodes.add(node);
		}
	}
}