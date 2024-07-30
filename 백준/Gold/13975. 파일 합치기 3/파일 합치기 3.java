import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.PriorityQueue;
import java.util.TreeSet;
import java.util.stream.Stream;

//파일 크기 작은 순으로 나열
//TreeSet 사용
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for(int t = 1; t <= T; t++) {
            //초기화
            int K = Integer.parseInt(br.readLine());
            PriorityQueue<Long> pages = Stream.of(br.readLine().split(" "))
                    .mapToLong(Long::parseLong)
                    .collect(PriorityQueue::new, PriorityQueue::add, PriorityQueue::addAll);

            long totalCost = 0;
            while(pages.size() > 1) {
                long first = pages.poll();
                long second = pages.poll();

                long sum = first + second;
                totalCost += sum;
                pages.add(sum);
            }

            sb.append(totalCost).append('\n');
        }
        System.out.println(sb);
    }
}