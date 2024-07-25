import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Stream;

//풀이 :
//1. 일단 A, B의 모든 부분집합의 합을 구해서 저장한다.
//2. T를 쪼갤 수 있는 모든 경우의 수를 구해서 A, B에서 찾는다.
//3. 곱한다.
//4. 더한다.
//1000 x 1_000_000이니까 int 안에 다 들어올 듯?
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int T = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());
        int[] A = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int m = Integer.parseInt(br.readLine());
        int[] B = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        //합의 개수 저장할 배열
        int[] subA = new int[n * (n + 1) / 2];
        int[] subB = new int[m * (m + 1) / 2];

        int idx = 0;
        for(int i = 0; i < n; i++) {
            int sum = 0;
            for(int j = i; j < n; j++) {
                subA[idx++] = sum += A[j];
            }
        }

        idx = 0;
        for(int i = 0; i < m; i++) {
            int sum = 0;
            for(int j = i; j < m; j++) {
                subB[idx++] = sum += B[j];
            }
        }

        Arrays.sort(subA);
        Arrays.sort(subB);

        //투 포인터
        int left = 0;
        int right = subB.length - 1;
        long count = 0;
        while(left < subA.length && right >= 0) {
            long sumA = subA[left];
            long sumB = subB[right];
            long sum = sumA + sumB;

            if(sum > T)
                right--;
            else if (sum < T)
                left++;
            else if (sum == T) {
                long duplA = 0;
                long duplB = 0;
                for(;left < subA.length && (subA[left] == sumA); left++)
                    duplA++;
                for(;right >= 0 && (subB[right] == sumB); right--)
                    duplB++;

                count += duplA * duplB;
            }
        }

        System.out.println(count);
    }
}