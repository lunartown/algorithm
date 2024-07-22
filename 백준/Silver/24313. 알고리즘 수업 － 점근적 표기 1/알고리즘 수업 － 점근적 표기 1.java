import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.stream.Stream;

//함수 구하기
//무슨 말인지 모르겠지만 시키는대로 대입한다.

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int[] function = Stream.of(br.readLine().split(" ")).mapToInt(Integer::parseInt).toArray();
        int c = Integer.parseInt(br.readLine());
        int n = Integer.parseInt(br.readLine());

        if(function[0] > c) {
            System.out.println(0);
        } else {
            if((c - function[0]) * n >= function[1]) {
                System.out.println(1);
            } else {
                System.out.println(0);
            }
        }
    }
}