import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 시간 더하기
// 60s = 1min, 60min = 1h
// 모두 int
// 24시 넘기는 걸 주의하자

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int h = Integer.parseInt(st.nextToken());
        int min = Integer.parseInt(st.nextToken());
        int s = Integer.parseInt(st.nextToken());

        int timer = Integer.parseInt(br.readLine());

        int newH, newMin, newS;
        newS = (s + timer) % 60;
        newMin = (min + ((s + timer) - newS) / 60) % 60;
        newH = (h + ((min + ((s + timer) - newS) / 60) - newMin) / 60) % 24;

        sb.append(newH).append(" ").append(newMin).append(" ").append(newS);
        System.out.println(sb);
    }
}