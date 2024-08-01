import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.IntStream;
import java.util.stream.Stream;

//바이토닉 부분 수열 구하기
//최장 증가 수열(lis)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] increase = new int[N];
        int[] decrease = new int[N];

        for(int i = 0; i < N; i++) {
            increase[i] = 1;
            for(int j = 0; j < i; j++) {
                if(arr[j] < arr[i] && increase[j] + 1 > increase[i])
                    increase[i] = increase[j] + 1;
            }
        }

        for(int i = N - 1; i >= 0; i--) {
            decrease[i] = 1;
            for(int j = N - 1; j > i; j--) {
                if(arr[j] < arr[i] && decrease[j] + 1 > decrease[i])
                    decrease[i] = decrease[j] + 1;
            }
        }

        System.out.println(
                IntStream.range(0, N)
                        .map(i -> increase[i] + decrease[i] - 1)
                        .max()
                        .getAsInt()
        );
    }
}