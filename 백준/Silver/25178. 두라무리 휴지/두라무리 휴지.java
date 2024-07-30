import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Objects;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        String str1 = br.readLine();
        String str2 = br.readLine();

        //1. 재배열해서 같은 단어가 되어야 한다.
        int[] letterCount1 = new int[26];
        int[] letterCount2 = new int[26];
        for(int i = 0; i < N; i++) {
            letterCount1[str1.charAt(i) - 'a']++;
            letterCount2[str2.charAt(i) - 'a']++;
        }
        if(!Arrays.equals(letterCount1, letterCount2)){
            System.out.println("NO");
            System.exit(0);
        }

        //2. 첫 글자와 마지막 글자가 동일해야 한다.
        if(!(str1.charAt(0) == str2.charAt(0) && str1.charAt(str1.length() - 1) == str2.charAt(str2.length() - 1))) {
            System.out.println("NO");
            System.exit(0);
        }

        //3. a, e, i, o, u를 제거한 문자열은 동일해야 한다.
        str1 = str1.replaceAll("[aieou]", "");
        str2 = str2.replaceAll("[aieou]", "");
        if(!str1.equals(str2)) {
            System.out.println("NO");
            System.exit(0);
        }

        //출력
        System.out.println("YES");
    }
}