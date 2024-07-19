import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Set;
import java.util.StringTokenizer;

//출근한 사람 세기
//enter하고 leave 안 하면 출근자
//set에 넣어었다 뺐다 하자

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        Set<String> set = new HashSet<String>();

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            String name = st.nextToken();
            String log = st.nextToken();

            if(log.equals("enter")) {
                set.add(name);
            }else if(log.equals("leave")) {
                set.remove(name);
            }
        }

        set.stream().sorted(Comparator.reverseOrder()).forEach(System.out::println);
    }
}