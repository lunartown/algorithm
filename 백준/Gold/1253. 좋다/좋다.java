import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//문제 : 좋은 수를 출력하라
//다른 두 수의 합
//정렬해서 투포인터로 찾자
public class Main {
    static boolean search(List<Integer> arr, int num) {
        int start = 0;
        int end = arr.size() - 1;

        while (start < end) {
            while (arr.get(start) + arr.get(end) > num && end > start)
                end--;

            while (arr.get(start) + arr.get(end) < num && start < end)
                start++;

            if (arr.get(start) + arr.get(end) == num && start < end)
                return true;
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int N = Integer.parseInt(br.readLine());
        List<Integer> A = Stream.of(br.readLine().split(" "))
                .map(Integer::parseInt)
                .collect(Collectors.toList());

        Collections.sort(A);

        int count = 0;
        for (int i = 0; i < A.size(); i++) {
            int value = A.get(i);
            A.remove(i);
            if (search(A, value))
                count++;
            A.add(i, value);
        }

        System.out.println(count);
    }
}