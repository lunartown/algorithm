import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;
import java.util.stream.Collectors;
import java.util.stream.Stream;

//완전 이진트리 (포화 이진트리)
//dfs 구현하는 건 줄 알았는데, 완전 반대였다;;
//상근이가 방문한 집의 순서를 주고 마을 지도를 역으로 그리는 것
//사실 푸는 방법은 똑같다, K에 해당하는 지도를 먼저 그리고
//다시 이름을 붙혀주면 된다
public class Main {
    private static int size;

    private static void dfsMap(List<Integer> map, int idx) {
        if (idx << 1 < size) {
            dfsMap(map, idx << 1);
            map.add(idx);
            dfsMap(map, (idx << 1) + 1);
        } else {
            map.add(idx);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();
        int K = Integer.parseInt(br.readLine());
        size = 1 << K;

        //상근이의 여행기록
        //1번 인덱스부터
        int[] order = new int[size];
        StringTokenizer st = new StringTokenizer(br.readLine());
        for(int i = 1; i < size; i++) {
            order[i] = Integer.parseInt(st.nextToken());
        }

        //지도 먼저 그리기
        List<Integer> map = new ArrayList<>();

        //1번 인엑스부터 쓰자
        map.add(0);
        dfsMap(map, 1);

        //지도 다 그렸으면 상근이 리스트랑 대조
        int[] town = new int[size];
        //역시 1번 인덱스부터
        //상근이 리스트에서 하나하나 까면서 마을에 삽입하자
        for (int i = 1; i < size; i++) {
            town[map.get(i)] = order[i];
        }

        for (int i = 0; i < K; i++) {
            for (int j = 0; j < 1 << i; j++)
                sb.append(town[(1 << i) + j]).append(' ');
            sb.append('\n');
        }

        System.out.println(sb);
    }

}