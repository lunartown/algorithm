import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashSet;

//서로 다른 부분 문자열의 갯수
//같은 걸 찾은 다음에 빼자
//안 되겠다 그냥 다 세자

public class Main {
    public static HashSet<String> splitString(String str) {
        if(str.length() == 1) return new HashSet<>(Arrays.asList(str));
        HashSet<String> result = splitString(str.substring(1, str.length()));

        for(int i = 1; i <= str.length(); i++) {
            result.add(str.substring(0, i));
        }

        return result;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str = br.readLine();

        System.out.println(splitString(str).size());
    }
}