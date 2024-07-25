import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 : 나보다 작은 애의 수
//숫자를 센다 - 누적합
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        TreeMap<Integer, Integer> count = new TreeMap<>();
        int N = Integer.parseInt(br.readLine());
        int[] coords = new int[N];
        st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            coords[i] = Integer.parseInt(st.nextToken());
            count.put(coords[i], 1);
        }

        //누적합 구하기
        int accum = 0;
        for(int key : count.keySet()) {
            int value = count.get(key);
            count.put(key, accum);
            accum += value;
        }

        Arrays.stream(coords).map(i -> count.get(i))
                .forEach(i -> sb.append(i).append(' '));

        System.out.println(sb);
    }
}