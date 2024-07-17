import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// N은, 몇 개의 연속된 자연수의 합
// 홀수개 : 나누어 떨어지기
// 짝수개 : 나누었을 때 0.5로 끝나기 -> i로는 나누어 떨어지지 않고 i/2로는 나누어 떨어지기
// 범위는 삼각수까지 : n^2/2 보다 크므로 두배해서 루트 씌우면 안전하다.

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        int count = 1;
        for(int i = 2; i < Math.sqrt(N * 2); i++) {
            if(i % 2 == 0) {
                if(N % i != 0 && N % (i / 2) == 0) count++;
            } else {
                if(N % i == 0) count++;
            }
        }
        System.out.println(count);
    }
}