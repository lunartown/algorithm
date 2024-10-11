import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int d = Integer.parseInt(st.nextToken());
        int k = Integer.parseInt(st.nextToken());
        int c = Integer.parseInt(st.nextToken());

        int[] sushi = new int[N];
        for(int i = 0; i < N; i++) {
            sushi[i] = Integer.parseInt(br.readLine());
        }

        int[] num = new int[d + 1];
        num[c] = 1;
        int sum = 1;
        for(int i = 0; i < k; i++) {
            if(num[sushi[i]]++ == 0) sum++;
        }

        int maxSum = sum;
        for(int i = 0; i < N; i++) {
            //초밥 제거
            if(--num[sushi[i]] == 0) sum--;

            //초밥 추가
            if(num[sushi[(i + k) % N]]++ == 0) sum++;

            maxSum = Math.max(sum, maxSum);
        }

        System.out.println(maxSum);
    }
}