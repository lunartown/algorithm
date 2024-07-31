import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine(), "+-", true);
        char op = '+';
        int answer = 0;
        boolean isMinus = false;
        while(st.hasMoreTokens()) {
            if(op == '-')
                isMinus = true;
            if(isMinus)
                answer -= Integer.parseInt(st.nextToken());
            else
                answer += Integer.parseInt(st.nextToken());

            if(st.hasMoreTokens()) {
                op = st.nextToken().charAt(0);
            }
        }

        System.out.println(answer);
    }
}