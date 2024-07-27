import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.stream.Stream;

//상근이가 해당 정수 카드를 가지고 있는지 찾자
//이진 탐색, 정렬
public class Main {
    private static void quickSort(int[] arr, int start, int end) {
        if (start >= end)
            return;

        int pivot = start;
        int left = start + 1;
        int right = end;

        //탈출 조건 : left > right 되는 순간
        while (left <= right) {
            //left가 피벗보다 크고
            while (left <= end && arr[left] <= arr[pivot]) {
                left++;
            }
            //right가 피벗보다 작은 경우
            while (right > start && arr[right] >= arr[pivot]) {
                right--;
            }

            //left right 교환
            int temp = arr[right];
            //단, 탈출 시에는 피벗을 위치시킴
            if (left > right) {
                arr[right] = arr[pivot];
                arr[pivot] = temp;
            } else {
                arr[right] = arr[left];
                arr[left] = temp;
            }
        }

        //right에 피벗 놨으니까 그 기준으로 재귀
        quickSort(arr, start, right - 1);
        quickSort(arr, right + 1, end);
    }

    //이진탐색
    private static int binarySearch(int[] arr, int key) {
        int left = 0;
        int right = arr.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (key == arr[mid]) {
                return 1;
            } else if (key > arr[mid]) {
                left = mid + 1;
            } else if (key < arr[mid]) {
                right = mid - 1;
            }
        }

        return 0;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //숫자 카드
        int N = Integer.parseInt(br.readLine());
        int[] cards = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        //정수들
        int M = Integer.parseInt(br.readLine());
        int[] numbers = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        //퀵소트(이진탐색 하려고)
        Arrays.sort(cards);

        for (int number : numbers) {
            sb.append(binarySearch(cards, number)).append(' ');
        }

        System.out.println(sb);
    }
}