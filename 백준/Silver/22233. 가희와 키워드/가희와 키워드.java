import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//메모장에 남은 키워드의 수
//그냥 set에 넣고 remove 하면 안 되나..?

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb= new StringBuilder();
        int N = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        Set<String> note = new HashSet<>();
        for(int i = 0; i < N; i++) {
            note.add(br.readLine());
        }

        for(int i = 0; i < M; i++) {
            note.removeAll(Stream.of(br.readLine().split(",")).collect(Collectors.toList()));
            sb.append(note.size()).append('\n');
        }

        System.out.println(sb);
    }
}