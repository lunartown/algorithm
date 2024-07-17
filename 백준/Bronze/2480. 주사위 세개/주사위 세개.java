import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 같은 눈이 3개가 나오면 10,000원+(같은 눈)×1,000원의 상금을 받게 된다.
// 같은 눈이 2개만 나오는 경우에는 1,000원+(같은 눈)×100원의 상금을 받게 된다.
// 모두 다른 눈이 나오는 경우에는 (그 중 가장 큰 눈)×100원의 상금을 받게 된다.
// 그대로 짜면 된다

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int[] eyes = new int[3];
        int max = 0;

        for(int i = 0; i < 3; i++) {
            eyes[i] = Integer.parseInt(st.nextToken());
            if(eyes[i] > max) max = eyes[i];
        }

        if(eyes[0] == eyes[1]) {
            if(eyes[1] == eyes[2]) System.out.println(10000 + eyes[0] * 1000);
            else System.out.println(1000 + eyes[0] * 100);
        } else if(eyes[1] == eyes[2]) {
            System.out.println(1000 + eyes[1] * 100);
        } else if(eyes[0] == eyes[2]) {
            System.out.println(1000 + eyes[0] * 100);
        } else {
            System.out.println(max * 100);
        }
    }
}