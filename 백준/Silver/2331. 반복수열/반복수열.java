import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 수열에서 남는 부분
// int를 charArray로 바꾸어서 구한다
// 반복이 나오면 종료
// List를 쓰자
// 범위 : int

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        int A = Integer.parseInt(st.nextToken());
        int P = Integer.parseInt(st.nextToken());

        List<Integer> seq = new ArrayList<Integer>();
        seq.add(A);
        int size = 0;

        loop:
        while (true) {
            String number = seq.get(seq.size() - 1).toString();
            int nextNum = 0;
            for(char c : number.toCharArray()) {
                nextNum += (int) Math.pow((int)c - '0', P);
            }

            for(int i = 0; i < seq.size(); i++) {
                if(seq.get(i) == nextNum) {
                    size = i;
                    break loop;
                }
            }

            seq.add(nextNum);
        }

        System.out.println(size);
    }
}