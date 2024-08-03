import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

//daldidalgo daldidan
//8초 + 1초 + 1초 + ... + 1초(daldida) + 1초(n)
//밤양갱
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        //시작 : +8초, 끝 : +2초
        //반복 횟수만 세면 된다
        //log2 + 1
        int count = 10;
        int log2 = 31 - Integer.numberOfLeadingZeros(N);
        count += log2;
        System.out.println(count);
    }
}