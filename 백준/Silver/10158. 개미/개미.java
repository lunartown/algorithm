import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 개미의 위치 찾기
// t가 long인 것에 주의
// 일단 개미가 무한히 움직인다고 가정하고
// 그 위치를 가로세로의 두배로 나눈 나머지를 구한다
// 그 후 반 접는다

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        int w = Integer.parseInt(st.nextToken());
        int h = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine());
        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());

        long t = Long.parseLong(br.readLine());

        x = (int)((x + t) % (2 * w));
        y = (int)((y + t) % (2 * h));

        if(x > w) x = 2 * w - x;
        if(y > h) y = 2 * h - y;

        sb.append(x).append(" ").append(y);
        System.out.println(sb.toString());
    }
}