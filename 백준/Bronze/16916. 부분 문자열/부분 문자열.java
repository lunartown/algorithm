import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static final int MODE = 100_000_007;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String test = br.readLine();
        String pattern = br.readLine();
        int length = pattern.length();

        short hashTest = 0;
        short hashPattern = 0;
        short power = 1;
        boolean find = false;

        for (int i = 0; i <= test.length() - length; i++) {
            if (i == 0) {
                for (int j = 0; j < length; j++) {
                    hashTest = (short) (hashTest + test.charAt(length - j - 1) * power);
                    hashPattern = (short) (hashPattern + pattern.charAt(length - j - 1) * power);

                    if (j < length - 1)
                        power = (short) (power * 31);
                }
            } else {
                hashTest = (short) (31 * (hashTest - test.charAt(i - 1) * power) + test.charAt(i + length - 1));
            }

            if (hashTest == hashPattern) {
                find = true;
                for (int j = 0; j < length; j++) {
                    if (test.charAt(j + i) != pattern.charAt(j)) {
                        find = false;
                        break;
                    }
                }
                if (find)
                    break;
            }
        }
        System.out.println(find ? 1 : 0);
    }
}