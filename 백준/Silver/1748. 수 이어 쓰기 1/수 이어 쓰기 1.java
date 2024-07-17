import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// 1부터 N까지 이어서 쓴 자릿수
// 한 자리수는 +1, 두 자리 수는 +2
// 범위는 long

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String numberString = br.readLine();
        long N = Long.parseLong(numberString);
        int length = numberString.length();
        long sum = 0;
        for(int i = 1; i < length; i++){
            long first = (long)Math.pow(10, i - 1);
            long last = (long)Math.pow(10, i) - 1;
            sum += (last - first + 1) * i;
        }

        sum += length * (N - (long)Math.pow(10, length - 1) + 1);

        System.out.println(sum);
    }
}