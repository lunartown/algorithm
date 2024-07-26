import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

//문제 : S번 sort해서 가장 정렬이 잘 되게 만드는 법(사전순)
//1. 1~S범위에서 최대값을 가장 앞으로 옮긴다, 횟수 차감
//2. 앞에 거 가만히 두고 반복

public class Main {
    static void swapMax(int[] arr, int start, int S) {
        if (S <= 0 || start >= arr.length - 1) return;

        //0번째도 최대가 될 수 있게
        int max = arr[start];
        int idx = start;

        //start ~ start + S 안에서 최대값 찾기
        for (int i = start + 1; i <= start + S && i < arr.length; i++) {
            if (arr[i] > max) {
                idx = i;
                max = arr[i];
            }
        }

        for (int i = idx; i > start; i--) {
            int tmp = arr[i];
            arr[i] = arr[i - 1];
            arr[i - 1] = tmp;
        }

        swapMax(arr, start + 1, S - (idx - start));
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        int[] arr = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int S = Integer.parseInt(br.readLine());

        swapMax(arr, 0, S);
        Arrays.stream(arr).forEach(i -> sb.append(i).append(' '));
        System.out.println(sb);
    }
}