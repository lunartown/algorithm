import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.StringTokenizer;

//최소 건물 개수 구하기
//스택에 넣었다 뺐다
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());
        ArrayDeque<Integer> stack = new ArrayDeque<>();
        //첫번째 처리
        st = new StringTokenizer(br.readLine());
        st.nextToken();
        stack.push(0);
        stack.push(Integer.parseInt(st.nextToken()));

        //높은 게 들어오면, push
        //낮은 게 들어오면, pop 하면서 건물 개수 추가
        //같은 게 들어오면 pass
        int count = 0;
        for (int i = 1; i <= N; i++) {
            int before = stack.pop();

            int next;
            if (i == N) {
                next = 0;
            } else {
                st = new StringTokenizer(br.readLine());
                st.nextToken();
                next = Integer.parseInt(st.nextToken());
            }

            while(next < before) {
                count++;
                before = stack.pop();
            }

            if (next > before) {
                stack.push(before);
                stack.push(next);
            } else {
                stack.push(before);
            }
        }

        System.out.println(count);
    }
}