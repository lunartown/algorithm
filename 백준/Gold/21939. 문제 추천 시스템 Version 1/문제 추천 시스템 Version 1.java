import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//풀이 : 너무 어렵다
public class Main {
    static class Problem implements Comparable<Problem> {
        int idx;
        int level;

        Problem(int idx, int level) {
            this.idx = idx;
            this.level = level;
        }

        @Override
        public int compareTo(Problem o) {
            if (this.level == o.level)
                return this.idx - o.idx;
            return this.level - o.level;
        }

        @Override
        public String toString() {
            return "Problem [idx=" + idx + ", level=" + level + "]";
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        TreeSet<Problem> problems = new TreeSet<>();
        Map<Integer, Integer> map = new HashMap<>();

        int N = Integer.parseInt(br.readLine());
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int idx = Integer.parseInt(st.nextToken());
            int level = Integer.parseInt(st.nextToken());
            problems.remove(new Problem(idx, map.getOrDefault(idx, 0)));
            problems.add(new Problem(idx, level));
            map.put(idx, level);
        }

        int M = Integer.parseInt(br.readLine());
        for (int i = 0; i < M; i++) {
            st = new StringTokenizer(br.readLine());
            switch (st.nextToken()) {
                case "recommend":
                    if (st.nextToken().equals("-1"))
                        sb.append(problems.first().idx).append('\n');
                    else
                        sb.append(problems.last().idx).append('\n');
                    break;
                case "add":
                    int idx = Integer.parseInt(st.nextToken());
                    int level = Integer.parseInt(st.nextToken());
                    problems.remove(new Problem(idx, map.getOrDefault(idx, 0)));
                    problems.add(new Problem(idx, level));
                    map.put(idx, level);
                    break;
                case "solved":
                    int no = Integer.parseInt(st.nextToken());
                    problems.remove(new Problem(no, map.get(no)));
                    map.remove(no);
            }

//            problems.forEach(e -> System.out.println(e));
//            System.out.println();
        }
        System.out.println(sb);
    }
}