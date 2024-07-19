import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//하노이 탑 이동순서
//한 번에 한 개의 원판만을 다른 탑으로 옮길 수 있다.
//쌓아 놓은 원판은 항상 위의 것이 아래의 것보다 작아야 한다.
//가장 큰 원판을 3에 넣고, 나머지를 2에 쌓는 방식으로 반복
//재귀적으로 구현

public class Main {
    public static StringBuilder sb = new StringBuilder();

    public static void hanoi(int k, int start, int tmp, int end) {
        if (k == 1) {
            sb.append(start).append(' ').append(end).append('\n');
            return;
        }
        hanoi(k - 1, start, end, tmp);
        sb.append(start).append(' ').append(end).append('\n');
        hanoi(k - 1, tmp, start, end);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int K = Integer.parseInt(br.readLine());
        sb.append((int)Math.pow(2, K) - 1).append('\n');
        hanoi(K, 1, 2, 3);
        System.out.println(sb);
    }
}