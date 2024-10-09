import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.TreeSet;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Node root = new Node("ROOT");

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            String[] foods = br.readLine().split(" ");
            Node parent = root;
            for(int j = 1; j <= Integer.parseInt(foods[0]); j++) {
                Node child = new Node(foods[j]);
                if(parent.children.contains(child)) {
                    parent = parent.children.ceiling(child);
                } else {
                    parent.children.add(child);
                    parent = child;
                }
            }
        }

        System.out.println(printTree(root, 0));
    }

    private static String printTree(Node root, int depth) {
        StringBuilder sb = new StringBuilder();
        for(Node child : root.children) {
            for(int i = 0; i < depth; i++) {
                sb.append("--");
            }
            sb.append(child.food).append("\n");
            sb.append(printTree(child, depth + 1));
        }
        return sb.toString();
    }
}

class Node implements Comparable<Node>{
    String food;
    TreeSet<Node> children;

    @Override
    public int compareTo(Node o) {
        return this.food.compareTo(o.food);
    }

    Node(String food) {
        this.food = food;
        this.children = new TreeSet<>();
    }
}
