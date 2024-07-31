import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int checkPalindrome(String str, int isPalindrome) {
        int length = str.length();
        //회문은 0, 유사 회문은 1, 아니면 2
        for (int i = 0; i < length / 2; i++) {
            if (str.charAt(i) != str.charAt(length - 1 - i)) {
                //이미 한번 틀렸으면
                if (isPalindrome == 1) {
                    isPalindrome = 2;
                    break;
                } else if (isPalindrome == 0) {
                    //처음 틀렸으면
                    isPalindrome = 1;
                    if (checkPalindrome(str.substring(i, length - 1 - i), 1) == 1
                            || checkPalindrome(str.substring(i + 1, length - i), 1) == 1) {
                        isPalindrome = 1;
                    } else {
                        isPalindrome = 2;
                    }
                    break;
                }
            }
        }
        return isPalindrome;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int T = Integer.parseInt(br.readLine());
        for (int t = 0; t < T; t++) {
            String str = br.readLine();
            sb.append(checkPalindrome(str, 0)).append('\n');
        }
        System.out.println(sb);
    }
}