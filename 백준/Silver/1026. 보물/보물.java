import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

//문제 : 곱의 합을 최소로 만들어라
//시그마A*B
//B의 역순으로 A를 정렬하면 된다.
//정렬된 A를 만들고 나서 곱을 구하고 싶었다.
//B를 어떤 방법으로든 정렬하면 낭만이 없기에..
public class Main {
    private static void quickSort(int[] arr, int start, int end) {
        if(start >= end)
            return;

        int pivot = start;
        int left = start + 1;
        int right = end;

        while(left <= right) {
            while(left <= end && arr[left] <= arr[pivot])
                left++;
            while(right > start && arr[right] >= arr[pivot])
                right--;

            int temp = arr[right];
            if(left > right) {
                arr[right] = arr[pivot];
                arr[pivot] = temp;
            } else {
                arr[right] = arr[left];
                arr[left] = temp;
            }
        }

        quickSort(arr, start, right - 1);
        quickSort(arr, right + 1, end);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        int N = Integer.parseInt(br.readLine());
        int[] A = new int[N];
        int[] B = new int[N];
        int[] countA = new int[101];
        int[] countB = new int[101];
        int[] orderB = new int[N];

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            A[i] = Integer.parseInt(st.nextToken());
            countA[A[i]]++;
        }

        //B 개수 세기
        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            B[i] = Integer.parseInt(st.nextToken());
            countB[B[i]]++;
        }

        //누적합 배열
        int sum = 0;
        for(int i = 0; i <= 100; i++) {
            sum += countB[i];
            countB[i] = sum;
        }

        //순서 찾기
        for(int i = 0; i < N; i++) {
            //B[i]가 3이라면, 3의 누적합이 나의 순서
            orderB[i] = countB[B[i]];
            //하나 빼주기
            countB[B[i]]--;
        }

        //퀵 정렬
        quickSort(A, 0, A.length - 1);

        //정렬된 A 구하기
        int[] sortedA = Arrays.stream(orderB).map(i -> A[N - i]).toArray();

        //곱 구하기!
        int S = 0;
        for(int i = 0; i < N; i++) {
            S += sortedA[i] * B[i];
        }
        System.out.println(S);
    }
}