import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

//가장 긴 감소하는 부분수열
//dp
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] length = new int[N];
        for(int i = 0; i < N; i++) {
            length[i] = 1;
            for(int j = 0; j < i; j++) {
                if(arr[j] > arr[i] && length[j] + 1 > length[i]) {
                        length[i] = length[j] + 1;
                }
            }
        }

        System.out.println(Arrays.stream(length).max().getAsInt());
    }
}