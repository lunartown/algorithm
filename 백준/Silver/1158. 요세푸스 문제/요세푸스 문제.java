import org.w3c.dom.traversal.NodeIterator;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.StringTokenizer;
import java.util.stream.IntStream;

class Node {
    private Node left, right;
    private int number;

    public Node getLeft() {
        return left;
    }

    public void setLeft(Node left) {
        this.left = left;
    }

    public Node getRight() {
        return right;
    }

    public void setRight(Node right) {
        this.right = right;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Node(int number) {
        this.number = number;
    }
}

class Circle<T> {
    int size = 0;
    private Node firstNode = null;

    Circle(){

    }

    Circle(T... nodes) {
        this.size = nodes.length;
        this.firstNode = (Node) nodes[0];
        for (int i = 0; i < nodes.length; i++) {
            Node node = (Node) nodes[i];
            node.setLeft(i == 0 ? (Node) nodes[nodes.length - 1] : (Node) nodes[i - 1]);
            node.setRight(i == nodes.length - 1 ? (Node) nodes[0] : (Node) nodes[i + 1]);
        }
    }

    Node getFirstNode() {
        return firstNode;
    }

    Node get(int idx) {
        Node node = firstNode;
        for (int i = 0; i < idx; i++) {
            node = node.getRight();
        }
        return node;
    }

    boolean remove(int idx) {
        if (size == 0) return false;
        size--;
        if (size == 0) {
            this.firstNode = null;
            return true;
        }
        Node node = firstNode;
        for (int i = 0; i < idx; i++) {
            node = node.getRight();
        }
        Node left = node.getLeft();
        Node right = node.getRight();

        left.setRight(right);
        right.setLeft(left);
        return true;
    }

    boolean add(Node node) {
        size++;
        if (size == 1) {
            this.firstNode = node;
            node.setLeft(node);
            node.setRight(node);
        } else {
            Node last = this.firstNode.getLeft();
            last.setRight(node);
            node.setLeft(last);
            this.firstNode.setLeft(node);
            node.setRight(this.firstNode);
        }
        return true;
    }

    int size() {
        return size;
    }

    boolean addAll(Circle<T> circle) {
        if (circle.size() == 0) return false;
        size = size + circle.size();
        Node last = this.firstNode.getLeft();
        Node circleLast = circle.get(0).getLeft();

        this.firstNode.setLeft(circleLast);
        circleLast.setRight(this.firstNode);
        circle.get(0).setLeft(last);
        last.setLeft(circle.get(0));

        return true;
    }

    boolean isEmpty() {
        return size == 0;
    }

    CircleIterator<Node> iterator() {
        return new CircleIterator(this);
    }
}

class CircleIterator<T> implements Iterator<T> {
    Node node;
    Circle<T> circle = null;

    public CircleIterator(Circle<T> circle) {
        this.circle = circle;
        this.node = circle.getFirstNode().getLeft();
    }

    @Override
    public boolean hasNext() {
        return true;
    }

    @Override
    public T next() {
        return (T) node.getRight();
    }

    public T next(int num) {
        for(int i = 0; i < num; i++) {
            node = node.getRight();
        }

        return (T) node;
    }

    @Override
    public void remove() {
        Node left = node.getLeft();
        Node right = node.getRight();

        left.setRight(right);
        right.setLeft(left);

        this.circle.size--;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Circle<Node> circle = IntStream.range(1, N + 1).mapToObj(i -> i)
                .map(Node::new)
                .collect(Circle::new, Circle::add, Circle::addAll);

        sb.append('<');
        Node node = circle.get(0);
        CircleIterator<Node> it = circle.iterator();
        while(!circle.isEmpty()) {
            sb.append(it.next(K).getNumber()).append(',').append(' ');
            it.remove();
        }
        sb.delete(sb.length() - 2, sb.length());
        sb.append('>');

        System.out.println(sb);
    }
}