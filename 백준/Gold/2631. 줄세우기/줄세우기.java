import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;

//줄 세우기
//최장 증가 수열(lis)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] children = new int[N];
        for (int i = 0; i < N; i++) {
            children[i] = Integer.parseInt(br.readLine());
        }

        int[] length = new int[N];
        for (int i = 0; i < N; i++) {
            length[i] = 1;
            for (int j = 0; j < i; j++) {
                if (children[j] < children[i] && length[j] + 1 > length[i])
                    length[i] = length[j] + 1;
            }
        }

        System.out.println(N - Arrays.stream(length).max().getAsInt());
    }
}