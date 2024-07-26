import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//문제 : 면접 점수와 서류 점수가 어떤 다른 면접자보다 둘 다 낮지 않은
//면접자의 수...
//서류점수로 정렬해서
//최소 점수를 갱신하면서 추가한다
public class Main {
    static class Candidate implements Comparable<Candidate> {
        int paper;
        int interview;

        public Candidate(int paper, int interview) {
            this.paper = paper;
            this.interview = interview;
        }

        @Override
        public int compareTo(Candidate o) {
            return this.paper - o.paper;
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;
        int T = Integer.parseInt(br.readLine());
        for(int tc = 0; tc < T; tc++) {
            int N = Integer.parseInt(br.readLine());
            Candidate[] candidates = new Candidate[N];
            for(int i = 0; i < N; i++) {
                st = new StringTokenizer(br.readLine());
                candidates[i] = new Candidate(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            }
            //서류점수로 정렬
            Arrays.sort(candidates);

            //나보다 서류점수가 높은 사람 중
            //나보다 면접점수가 높은 사람이 한 명이라도 있으면 안 됨
            int count = 1;
            int best = candidates[0].interview;

            for(int i = 0; i < N; i++) {
                if(candidates[i].interview < best) {
                    count++;
                    best = candidates[i].interview;
                }
            }
            sb.append(count).append('\n');
        }
        System.out.println(sb);
    }
}