import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;

//풀이 : 너무 어렵다
//생각해보니 엄청 쉬운 문제였다...젠장
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int i = 0; i < N; i++) {
            pq.offer(Integer.parseInt(br.readLine()));
        }

        int count = 0;
        while(pq.size() > 1) {
            int deck1 = pq.poll();
            int deck2 = pq.poll();
            int sum = deck1 + deck2;
            count += sum;
            pq.offer(sum);
        }

        System.out.println(count);
    }
}