import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//중요도 순으로 출력되되, 앞선 문서가 먼저 출력된다.
//pq? 값을 찾는 게 아니라 순서를 찾는 것
//value를 가지고 한바퀴 돈다.
//그냥 정직하게 큐로 하자..
class Document <Document> {
    int index;
    int priority;

    public Document(int index, int priority) {
        this.index = index;
        this.priority = priority;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        int T = Integer.parseInt(br.readLine());
        for (int tc = 0; tc < T; tc++) {
            //N, M 입력
            st = new StringTokenizer(br.readLine());
            int N = Integer.parseInt(st.nextToken());
            int M = Integer.parseInt(st.nextToken());

            AtomicInteger index = new AtomicInteger(0);
            //각 문서의 중요도 입력
            Queue<Document> queue = Stream.of(br.readLine().split(" "))
                    .map(i -> new Document(index.getAndIncrement(), Integer.parseInt(i)))
                    .collect(Collectors.toCollection(ArrayDeque::new));
            PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
            pq.addAll(queue.stream().map(i -> i.priority).collect(Collectors.toCollection(PriorityQueue::new)));

            //출력 시작
            int count = 0;
            while(!queue.isEmpty()) {
                Document document = queue.poll();
                if(document.priority == pq.peek()) {
                    pq.poll();
                    count++;
                    if(document.index == M) break;
                } else {
                    queue.offer(document);
                }
            }

            sb.append(count).append('\n');
        }
        System.out.println(sb);
    }
}