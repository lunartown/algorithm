import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

//박스 넣기
//최장 증가 수열(lis)
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int n = Integer.parseInt(br.readLine());
        int[] boxes = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        int[] length = new int[n];
        for (int i = 0; i < n; i++) {
            length[i] = 1;
            for (int j = 0; j < i; j++) {
                if (boxes[j] < boxes[i] && length[j] + 1 > length[i])
                    length[i] = length[j] + 1;
            }
        }

        System.out.println(Arrays.stream(length).max().getAsInt());
    }
}