import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 : 보석 N개
//제일 비싼 것부터 넣을 수 있는 가장 가벼운 가방에 넣으면 되지 않을까?
public class Main {
    static class Gem implements Comparable<Gem> {
        int mass;
        int value;

        public Gem(int mass, int value) {
            this.mass = mass;
            this.value = value;
        }

        @Override
        public int compareTo(Gem o) {
            if(this.mass == o.mass) {
                return o.value - this.value;
            }
            return this.mass - o.mass;
        }
    }

    private static void findUpperAndRemove(List<Long> bags, int mass) {
        int left = 0;
        int right = bags.size() - 1;
        while (left < right) {
            int mid = (left + right) / 2;
            if (bags.get(mid) == mass) {
                bags.remove(mid);
                return;
            } else if (bags.get(mid) > mass) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }
        bags.remove(left);
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int N = Integer.parseInt(st.nextToken());
        int K = Integer.parseInt(st.nextToken());

        Gem[] gems = new Gem[N];
        long[] bags = new long[K];

        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            gems[i] = new Gem(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
        }

        for (int i = 0; i < K; i++) {
            bags[i] = Long.parseLong(br.readLine());
        }

        Arrays.sort(gems);
        Arrays.sort(bags);

        PriorityQueue<Integer> pq = new PriorityQueue<>(Collections.reverseOrder());
        long totalValue = 0;
        for(int i = 0, j = 0; i < K; i++) {
            while(j < N && gems[j].mass <= bags[i]) {
                pq.offer(gems[j++].value);
            }

            if(!pq.isEmpty())
                totalValue += pq.poll();
        }

        System.out.println(totalValue);
    }
}