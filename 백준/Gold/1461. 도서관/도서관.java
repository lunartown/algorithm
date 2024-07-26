import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

//문제 : 책을 제자리에 두는 가장 적은 걸음 수
//멀리 가야할 때 같이 갖고 가자
//원점을 통과하면 어차피 의미가 없으므로 음수 양수 나눠서 해도 됨
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());
        //양수는 가장 큰 것부터
        PriorityQueue<Integer> positive = new PriorityQueue<>(Collections.reverseOrder());
        //음수는 작은 것부터
        PriorityQueue<Integer> negative = new PriorityQueue<>();

        st = new StringTokenizer(br.readLine());
        for(int i = 0; i < N; i++) {
            int book = Integer.parseInt(st.nextToken());
            if(book > 0) {
                positive.offer(book);
            } else {
                negative.offer(book);
            }
        }

        //걸음 수 세기
        int steps = 0;
        int maxSteps = 0;
        while(!positive.isEmpty()) {
            int firstBook = positive.poll();
            if(firstBook > maxSteps)
                maxSteps = firstBook;
            for(int i = 1; i < M; i++)
                positive.poll();
            steps += firstBook;
        }
        while(!negative.isEmpty()) {
            int firstBook = negative.poll();
            if(-firstBook > maxSteps)
                maxSteps = -firstBook;
            for(int i = 1; i < M; i++)
                negative.poll();
            steps -= firstBook;
        }

        steps = steps * 2 - maxSteps;
        System.out.println(steps);
    }
}