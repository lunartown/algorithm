import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class Main {
    static final int MAX = 4000;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        //준비
        int sum = 0;
        int[] arr = new int[N];
        Map<Integer, Integer> map = new TreeMap<>();
        int max = -MAX;
        int min = MAX;

        for (int i = 0; i < N; i++) {
            int num = Integer.parseInt(br.readLine());
            //평균
            sum += num;
            //중앙값
            arr[i] = num;
            //최빈값
            map.merge(num, 1, Integer::sum);
            //범위
            max = Math.max(max, num);
            min = Math.min(min, num);
        }

        //평균
        sb.append(Math.round((double) sum / (double) N)).append('\n');

        //중앙값
        Arrays.sort(arr);
        sb.append(arr[arr.length / 2]).append('\n');

        //최빈값
        int modeCount = map.values().stream()
                .mapToInt(Integer::intValue)
                .max()
                .getAsInt();

        int[] modes = map.entrySet().stream()
                .filter(e -> e.getValue() == modeCount)
                .map(Map.Entry::getKey)
                .mapToInt(Integer::intValue)
                .toArray();

        int mode = modes.length == 1 ? modes[0] : modes[1];
        sb.append(mode).append('\n');

        //범위
        sb.append(max - min);
        System.out.println(sb);
    }
}