import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int[] arr = Arrays.stream(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();

        int left = 0, right = arr.length - 1;
        int minSum = Integer.MAX_VALUE;
        int minA = arr[0];
        int minB = arr[right];

        while(left < right) {
            int a = arr[left];
            int b = arr[right];
            int sum = a + b;

            if(Math.abs(sum) < minSum) {
                minSum = Math.abs(sum);
                minA = a;
                minB = b;
            }

            if (sum == 0) break;

            if(sum > 0) {
                right--;
            } else {
                left++;
            }
        }

        System.out.println(minA + " " + minB);
    }
}