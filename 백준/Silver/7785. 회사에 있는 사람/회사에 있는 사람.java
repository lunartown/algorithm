import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//출근한 사람 세기
//enter하고 leave 안 하면 출근자
//tree에 넣어서 마지막 입력만 받자
//TreeMap에 Comparator써서 자동으로 정렬하자
//EntrySet으로 출력

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        StringTokenizer st;

        TreeMap<String, String> map = new TreeMap<>((a, b)->
                b.compareTo(a));

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            map.put(st.nextToken(), st.nextToken());
        }

        map.entrySet().stream().filter((entry) -> entry.getValue().equals("enter"))
                .forEach((entry) -> sb.append(entry.getKey()).append("\n"));

        System.out.println(sb);
    }
}