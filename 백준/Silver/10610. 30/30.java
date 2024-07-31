import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.math.BigInteger;
import java.util.Collections;
import java.util.stream.Collector;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        String str = br.readLine();

        BigInteger num = new BigInteger((str.chars()
                .mapToObj(i -> i - '0')
                .sorted(Collections.reverseOrder())
                .collect(Collector
                        .of(StringBuilder::new, StringBuilder::append,
                                StringBuilder::append, StringBuilder::toString))));

        if (!(num.remainder(new BigInteger("3")).equals(BigInteger.ZERO)
                && num.remainder(new BigInteger("10")).equals(BigInteger.ZERO))) {
            sb.append(-1);
        } else {
            sb.append(num);
        }

        System.out.println(sb);
    }
}