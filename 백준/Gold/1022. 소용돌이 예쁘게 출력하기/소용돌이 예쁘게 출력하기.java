import java.io.*;
import java.util.Arrays;
import java.util.StringTokenizer;

//소용돌이 예쁘게 출력하기
//예쁘게 출력하자
public class Main {
    static int r1, r2, c1, c2;

    static int shiftX(int x) {
        return x - r1;
    }

    static int shiftY(int y) {
        return y - c1;
    }

    static boolean isMovable(int x, int y) {
        return x >= r1 && x <= r2 && y >= c1 && y <= c2;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        System.setOut(new PrintStream(new FileOutputStream("log.txt")));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        r1 = Integer.parseInt(st.nextToken());
        c1 = Integer.parseInt(st.nextToken());
        r2 = Integer.parseInt(st.nextToken());
        c2 = Integer.parseInt(st.nextToken());
        long[][] vortex = new long[r2 - r1 + 1][c2 - c1 + 1];

        //소용돌이 계산
        //r, c 중 절대값이 큰 것 고르기 ex) r
        //(2r + 1)^2 +...
        //(r - 1, r)에서 시작
        int maxR = Math.max(Math.max(Math.abs(r1), Math.abs(r2)), Math.max(Math.abs(c1), Math.abs(c2)));
        int minR = Math.min(Math.min(Math.abs(r1), Math.abs(r2)), Math.min(Math.abs(c1), Math.abs(c2)));
        long value = 0;

        for (int r = 0; r <= maxR + 1; r++) {
            if (r == 0 && isMovable(0, 0))
                vortex[shiftX(0)][shiftY(0)] = 1;
            long start = (long) (2 * r - 1) * (2 * r - 1);
            //첫번째 열
            for (int i = 1; i <= 2 * r; i++) {
                int x = r - i;
                int y = r;
                if (isMovable(x, y)) {
                    value = start + i;
                    vortex[shiftX(x)][shiftY(y)] = value;
                }
            }
            //두번째 열
            for (int i = 1; i <= 2 * r; i++) {
                int x = -r;
                int y = r - i;
                if (isMovable(x, y)) {
                    value = start + 2L * r + i;
                    vortex[shiftX(x)][shiftY(y)] = value;
                }
            }
            //세번째 열
            for (int i = 1; i <= 2 * r; i++) {
                int x = -r + i;
                int y = -r;
                if (isMovable(x, y)) {
                    value = start + 4L * r + i;
                    vortex[shiftX(x)][shiftY(y)] = value;
                }
            }
            //네번째 열
            for (int i = 1; i <= 2 * r; i++) {
                int x = r;
                int y = -r + i;
                if (isMovable(x, y)) {
                    value = start + 6L * r + i;
                    vortex[shiftX(x)][shiftY(y)] = value;
                }
            }
        }

        //예쁘게 출력
        int maxLength = String.valueOf(value).length();
        Arrays.stream(vortex).forEach(arr -> {
            Arrays.stream(arr).forEach(i -> {
                sb.append(String.format("%" + maxLength + "d", i)).append(' ');
            });
            sb.append('\n');
        });

        System.out.print(sb);
    }
}