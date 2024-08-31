import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.StringTokenizer;

//두 용액
//https://www.acmicpc.net/problem/2470

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        //용액의 수
        int N = Integer.parseInt(br.readLine());

        //long 타입으로 선언
        List<Long> positive = new ArrayList<>();
        List<Long> negative = new ArrayList<>();

        //용액 입력
        StringTokenizer st = new StringTokenizer(br.readLine());
        for (int i = 0; i < N; i++) {
            long num = Long.parseLong(st.nextToken());
            if (num > 0) {
                positive.add(num);
            } else {
                negative.add(num);
            }
        }

        //양수, 음수 정렬
        positive.sort(null);
        negative.sort(Comparator.comparing(Long::longValue).reversed());

        //투 포인터
        long min = Long.MAX_VALUE;
        int pIdx = 0;
        int nIdx = 0;

        for (int p = 0, n = 0; p < positive.size() && n < negative.size(); ) {
            long sum = positive.get(p) + negative.get(n);

            if (Math.abs(sum) < Math.abs(min)) {
                min = sum;
                pIdx = p;
                nIdx = n;
            }

            if (sum < 0) {
                p++;
            } else {
                n++;
            }
        }

        long posMin = positive.size() >= 2 ? positive.get(0) + positive.get(1) : Long.MAX_VALUE;
        long negMin = negative.size() >= 2 ? negative.get(0) + negative.get(1) : Long.MAX_VALUE;

        if (Math.abs(min) < Math.min(Math.abs(posMin), Math.abs(negMin))) {
            sb.append(negative.get(nIdx)).append(" ").append(positive.get(pIdx));
        } else if (Math.abs(posMin) < Math.abs(negMin)) {
            sb.append(positive.get(0)).append(" ").append(positive.get(1));
        } else {
            sb.append(negative.get(1)).append(" ").append(negative.get(0));
        }

        System.out.println(sb);
    }
}