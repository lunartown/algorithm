import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

//확장자 별로 정리
//묶어서 개수와 함께, 사전순
//TreeMap에 collect 쓰면 딱이다!

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        List<String> extList = new ArrayList<>();

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            extList.add(br.readLine().split("\\.")[1]);
        }

        extList.stream().collect(Collectors.groupingBy(Function.identity(), TreeMap::new, Collectors.counting()))
                .forEach((k, v) -> sb.append(k).append(' ').append(v).append('\n'));

        System.out.println(sb);
    }
}