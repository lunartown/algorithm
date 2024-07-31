import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String S = br.readLine();
        String T = br.readLine();

        for (int i = T.length() - 1; i >= S.length(); i--) {
            if (T.charAt(i) == 'A') {
                T = T.substring(0, T.length() - 1);
            } else if (T.charAt(i) == 'B') {
                T = T.substring(0, T.length() - 1);
                T = new StringBuffer(T).reverse().toString();
            }
        }

        if (S.equals(T))
            System.out.println(1);
        else
            System.out.println(0);
    }
}