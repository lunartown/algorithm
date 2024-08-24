import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 트리 순회
// https://www.acmicpc.net/problem/1991

public class Main {
    static final int DOT = '.' - 'A' + 1;
    static int[] left;
    static int[] right;

    static void preOrder(int root, StringBuilder sb) {
        sb.append((char) (root + 'A' - 1));
        if (left[root] != 0) preOrder(left[root], sb);
        if (right[root] != 0) preOrder(right[root], sb);
    }

    static void inOrder(int root, StringBuilder sb) {
        if (left[root] != 0) inOrder(left[root], sb);
        sb.append((char) (root + 'A' - 1));
        if (right[root] != 0) inOrder(right[root], sb);
    }

    static void postOrder(int root, StringBuilder sb) {
        if (left[root] != 0) postOrder(left[root], sb);
        if (right[root] != 0) postOrder(right[root], sb);
        sb.append((char) (root + 'A' - 1));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());

        left = new int[N + 1];
        right = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            int root = st.nextToken().charAt(0) - 'A' + 1;
            int l = st.nextToken().charAt(0) - 'A' + 1;
            int r = st.nextToken().charAt(0) - 'A' + 1;

            if (l != DOT) left[root] = l;
            if (r != DOT) right[root] = r;
        }

        preOrder(1, sb);
        sb.append("\n");
        inOrder(1, sb);
        sb.append("\n");
        postOrder(1, sb);

        System.out.println(sb);
    }
}