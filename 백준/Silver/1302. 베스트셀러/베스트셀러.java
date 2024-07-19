import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

//베스트셀러 구하기
//또 Map 쓰면 될 듯?
//사전 순으로 앞서는 것 출력하기

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());
        Map<String, Integer> map = new HashMap<>();

        for (int i = 0; i < N; i++) {
            String newBook = br.readLine();
            map.put(newBook, map.getOrDefault(newBook, 0) + 1);
        }

        List<String> keys = new ArrayList<>(map.keySet());
        Collections.sort(keys, (key1, key2) -> {
            if(map.get(key1) == map.get(key2)) {
                return key1.compareTo(key2);
            } else {
                return map.get(key2) - map.get(key1);
            }
        });

        System.out.println(keys.get(0));
    }
}