import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;

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
        int finalTotal = total;
        map.keySet().stream().sorted().forEach(key -> {
            // 각 나무 이름과 해당 빈도를 백분율로 계산하여 출력
            sb.append(String.format("%s %.4f\n", key, map.get(key) * 100.0 / finalTotal));
        });

        // StringBuilder에 담긴 최종 결과를 출력
        System.out.print(sb);
    }
}