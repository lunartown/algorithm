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
    private static int findValue(int[] arr, long value) {
        int low = 0;
        int high = arr.length - 1;

        while (low <= high) {
            if(low == high && arr[high] == value)
                return high;

            int mid = (low + high + 1) >>> 1;
            if (arr[mid] < value) {
                low = mid + 1;
            } else if (arr[mid] > value) {
                high = mid - 1;
            } else {
                low = mid;
            }
        }

        return -1;
    }

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
        for (int i = 0; i < n; i++) {
            int sum = 0;
            for (int j = i; j < n; j++) {
                subA[idx++] = sum += A[j];
            }
        }

        idx = 0;
        for (int i = 0; i < m; i++) {
            int sum = 0;
            for (int j = i; j < m; j++) {
                subB[idx++] = sum += B[j];
            }
        }

        Arrays.sort(subA);
        Arrays.sort(subB);

        //이진 탐색
        long count = 0;
        for (int i = 0; i < subA.length;) {
            long sumA = subA[i];
            long sumB = T - sumA;

            long duplB = 0;
            int bUpper = findValue(subB, sumB);
            for (int j = bUpper; j >= 0 && subB[j] == sumB; j--) {
                duplB++;
            }

            long duplA = 0;
            for (; i < subA.length && subA[i] == sumA; i++) {
                duplA++;
            }

            count += duplA * duplB;
        }

        System.out.println(count);
    }
}