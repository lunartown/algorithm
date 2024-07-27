import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//1~1023이 들어있는 포화 이진트리
//공통 조상 찾기
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int T = Integer.parseInt(br.readLine());
        for (int t = 1; t <= T; t++) {
            StringTokenizer st = new StringTokenizer(br.readLine());
            int A = Integer.parseInt(st.nextToken());
            int B = Integer.parseInt(st.nextToken());

            //부모는 나누기 2, 즉 >>1과 같다
            //그 말은 두 수를 이진법으로 표기했을 때
            //가장 긴 앞부분을 구하면 된다
            String strA = Integer.toBinaryString(A);
            String strB = Integer.toBinaryString(B);

            //최대 공통 길이
            int length = Math.min(strA.length(), strB.length());
            int i = 0;
            for(; i < length; i++) {
                if(strA.charAt(i) != strB.charAt(i))
                    break;
            }

            //그 길이의 부모 찾기
            int parent = Integer.parseInt(strA.substring(0, i), 2) * 10;
            sb.append(parent).append('\n');
        }
        System.out.println(sb);
    }
}