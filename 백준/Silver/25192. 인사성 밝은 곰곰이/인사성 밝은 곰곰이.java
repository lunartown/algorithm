import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

//곰곰티콘 사용횟수 구하기
//겹치지 않는 이름 수
//set 쓰면 될 듯

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Set<String> set = new HashSet<>();
        int gomgom = 0;

        for(int i = 0; i < N; i++) {
            String str = br.readLine();
            if(str.equals("ENTER")) {
                gomgom += set.size();
                set.clear();
            } else {
                set.add(str);
            }
        }
        gomgom += set.size();
        System.out.println(gomgom);
    }
}