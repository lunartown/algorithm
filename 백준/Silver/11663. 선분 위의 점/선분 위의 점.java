import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;
import java.util.stream.Stream;

//선분에 포함된 점의 개수 찾기
//TreeMap으로 바로 될 것 같은데...? X
//누적합으로
//이진탐색을 결국 써야겠구만..
public class Main {
    //이진탐색
    //나보다 작은 애 중 최댓값 가져오기
    private static int binarySearchLeft(long[] arr, long key) {
        int left = 0;
        int right = arr.length - 1;
        while(left < right) {
            int mid = (left + right + 1) / 2;
            if(key == arr[mid]) {
                return mid;
            } else if(key > arr[mid]) {
                left = mid;
            } else {
                right = mid - 1;
            }
        }
        return left;
    }

    private static int binarySearchRight(long[] arr, long key) {
        int left = 0;
        int right = arr.length - 1;
        while(left < right) {
            int mid = (left + right) / 2;
            if(key == arr[mid]) {
                return mid;
            } else if(key > arr[mid]) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }
        return left;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //점의 개수
        int N = Integer.parseInt(st.nextToken());
        //선분의 개수
        int M = Integer.parseInt(st.nextToken());

        long[] arr = new long[N + 2];
        arr[0] = 0;
        arr[N + 1] = Long.MAX_VALUE;
        st = new StringTokenizer(br.readLine());
        for(int i = 1; i <= N; i++) {
            arr[i] = Long.parseLong(st.nextToken());
        }
        Arrays.sort(arr);

        long[][] lines = new long[M][2];
        for(int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            lines[i][0] = Long.parseLong(st.nextToken());
            lines[i][1] = Long.parseLong(st.nextToken());
        }

        for(int i = 0; i < M; i++) {
            long left = lines[i][0];
            long right = lines[i][1];
            if(left >= right) {
                long tmp = right;
                right = left;
                left = tmp;
            }
            long leftCount = binarySearchRight(arr, left);
            long rightCount = binarySearchLeft(arr, right);
            sb.append(rightCount - leftCount + 1).append('\n');
        }

        System.out.println(sb);
    }
}