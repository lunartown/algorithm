import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

//두 용액
//https://www.acmicpc.net/problem/2470

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //용액의 수
        int N = Integer.parseInt(br.readLine());

        //배열
        int[] arr = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        //정렬
        Arrays.sort(arr);

        //투 포인터
        int left = 0;
        int right = N - 1;
        int min = Integer.MAX_VALUE;
        int[] answer = new int[2];

        while (left < right) {
            int sum = arr[left] + arr[right];

            if (Math.abs(sum) < min) {
                min = Math.abs(sum);
                answer[0] = arr[left];
                answer[1] = arr[right];
            }

            if (sum < 0) {
                left++;
            } else {
                right--;
            }
        }

        sb.append(answer[0]).append(" ").append(answer[1]);
        System.out.println(sb);
    }
}