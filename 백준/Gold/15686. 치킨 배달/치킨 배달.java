import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

// 치킨집 살리기 운동
// 1. combination으로 살아있는 치킨집 조합을 모두 구한다.
// 2. map이 주어졌을 때 도시의 치킨거리를 구하는 메소드를 작성한다.
// 2-1. 치킨거리는 각 집의 치킨거리의 합
// 3. min 밸류를 가지고 실패 시 백트래킹한다.

public class Main {
    private static int N, M;
    private static int[][] city;
    private static int[][] remain;
    private static List<int[]> houses = new ArrayList<>();
    private static List<int[]> chickens = new ArrayList<>();
    private static int minChicken = Integer.MAX_VALUE;

    private static void combination(int depth, int idx) {
        if (depth == M) {
            //치킨거리 구하는 공식
            getCityChickenLength();
            return;
        }

        if (idx == chickens.size()) {
            return;
        }

        remain[depth] = chickens.get(idx).clone();
        combination(depth + 1, idx + 1);
        combination(depth, idx + 1);
    }

    private static void getCityChickenLength() {
        int sum = 0;
        int housesNum = houses.size();

        for (int i = 0; i < housesNum; i++) {
            int[] house = houses.get(i);
            //최대 거리
            int min = (N - 1) * 2;
            for (int[] chicken : remain) {
                int length = getChickenLength(house, chicken);
                if (length < min) min = length;
            }
            sum += min;
            if (sum + (housesNum - i - 1) >= minChicken) {
                return;
            }
        }

        if (sum < minChicken) {
            minChicken = sum;
        }
    }

    private static int getChickenLength(int[] h1, int[] h2) {
        return Math.abs(h1[0] - h2[0]) + Math.abs(h1[1] - h2[1]);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        city = new int[N][N];
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            for (int j = 0; j < N; j++) {
                city[i][j] = Integer.parseInt(st.nextToken());
                if (city[i][j] == 1) {
                    houses.add(new int[]{i, j});
                } else if (city[i][j] == 2) {
                    chickens.add(new int[]{i, j});
                }
            }
        }

        remain = new int[M][];
        combination(0, 0);
        System.out.println(minChicken);
    }
}