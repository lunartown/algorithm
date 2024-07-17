import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.StringTokenizer;

// 나누기
// BigDecimal
// 자릿수 기입하지 않으면 Arithmetic Exception 발생하니 주의(무한소수)

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        BigDecimal N = new BigDecimal(st.nextToken());
        BigDecimal M = new BigDecimal(st.nextToken());

        System.out.println(N.divide(M, 1001, BigDecimal.ROUND_HALF_UP));
    }
}