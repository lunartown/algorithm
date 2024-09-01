import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        // HashMap을 사용하여 나무 이름(String)과 그 빈도수(Integer)를 저장
        Map<String, Integer> map = new HashMap<>();
        String s;
        int total = 0; // 입력된 나무의 총 개수를 세기 위한 변수

        // 입력된 각 줄을 읽어서 처리
        while ((s = br.readLine()) != null && !s.isEmpty()) {
            total++; // 입력된 줄(나무 이름)의 개수를 증가
            // 나무 이름이 이미 맵에 존재하면 값을 1 증가시키고, 그렇지 않으면 1로 초기화
            map.put(s, map.getOrDefault(s, 0) + 1);
        }

        StringBuilder sb = new StringBuilder();
        TreeMap<String, Integer> sortedMap = new TreeMap<>(map); // 나무 이름을 오름차순으로 정렬하기 위한 TreeMap

        for (Map.Entry<String, Integer> entry : sortedMap.entrySet()) {
            // 나무 이름과 그 빈도수를 출력
            sb.append(entry.getKey()).append(' ').append(String.format("%.4f", entry.getValue() * 100.0 / total)).append('\n');
        }

        // StringBuilder에 담긴 최종 결과를 출력
        System.out.print(sb);
    }
}