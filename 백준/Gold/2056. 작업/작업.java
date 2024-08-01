import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        List<Integer>[] precede = new List[N + 1];
        int[] time = new int[N + 1];
        int[] endTime = new int[N + 1];

        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            time[i] = Integer.parseInt(st.nextToken());
            int preCount = Integer.parseInt(st.nextToken());
            precede[i] = new ArrayList<>();
            for (int j = 0; j < preCount; j++) {
                precede[i].add(Integer.parseInt(st.nextToken()));
            }
        }

        for (int i = 1; i <= N; i++) {
            endTime[i] = time[i];
            for (int p : precede[i]) {
                if (endTime[p] + time[i] > endTime[i]) {
                    endTime[i] = endTime[p] + time[i];
                }
            }
        }

        System.out.println(Arrays.stream(endTime).max().getAsInt());
    }
}