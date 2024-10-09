import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    //여러번 눌러도 똑같다
    //누르는 순서는 상관없다
    //즉, 01010의 배열
    //case : a1 == 0 or a1 == 1
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        String current = br.readLine();
        String target = br.readLine();
        int[] diff = new int[N];
        for(int i = 0; i < N; i++) {
            diff[i] = current.charAt(i) ^ target.charAt(i);
        }
        int[] button = new int[N];

        int count = 0;
        int result = -1;

        //case : a1 == 0
        button[0] = 0;
        button[1] = diff[0] ^ button[0];
        if(button[1] == 1) count++;

        for(int i = 2; i < N; i++) {
            button[i] = diff[i - 1] ^ button[i - 1] ^ button[i - 2];
            if(button[i] == 1) count++;
        }
        if((button[N - 1] ^ button[N - 2]) == diff[N - 1]) {
            result = count;
        }

        //case : a1 == 1
        button = new int[N];
        count = 1;
        button[0] = 1;
        button[1] = diff[0] ^ button[0];
        if(button[1] == 1) count++;

        for(int i = 2; i < N; i++) {
            button[i] = diff[i - 1] ^ button[i - 1] ^ button[i - 2];
            if(button[i] == 1) count++;
        }

        if((button[N - 1] ^ button[N - 2]) == diff[N - 1]) {
            if(result == -1) {
                result = count;
            } else {
                result = Math.min(result, count);
            }
        }

        System.out.println(result);
    }
}