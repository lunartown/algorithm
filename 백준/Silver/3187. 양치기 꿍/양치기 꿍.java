import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

//영역 안에 양이 늑대보다 많으면 늑대가 잡아먹힌다
//아니면 양이 잡아먹힌다
//양과 늑대의 수를 출력하라
//union-find...?
//농장을 순회하면서 dx, dy로 union 하자
//그냥 dfs가 낫겠다
public class Main {
    private static int R, C;
    private static char[][] farm;
    private static int fenceSheep;
    private static int fenceWolves;
    private static boolean[][] vis;

    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    private static void dfsSheep(int x, int y) {
        //범위 초과, 벽, 방문한 곳은 리턴
        if (x < 0 || x >= R || y < 0 || y >= C
                || farm[x][y] == '#'
                || vis[x][y])
            return;

        vis[x][y] = true;
        if (farm[x][y] == 'k')
            fenceSheep++;
        else if (farm[x][y] == 'v')
            fenceWolves++;

        for (int dir = 0; dir < 4; dir++) {
            dfsSheep(x + dx[dir], y + dy[dir]);
        }
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());
        StringBuilder sb = new StringBuilder();

        //행과 열 갯수
        R = Integer.parseInt(st.nextToken());
        C = Integer.parseInt(st.nextToken());

        //양, 늑대 리스트
        List<int[]> sheep = new ArrayList<>();
        List<int[]> wolves = new ArrayList<>();
        int survivedSheep = 0;
        int survivedWolves = 0;

        //농장, 양, 늑대 초기화
        farm = new char[R][C];
        vis = new boolean[R][C];
        for (int i = 0; i < R; i++) {
            String str = br.readLine();
            for (int j = 0; j < C; j++) {
                farm[i][j] = str.charAt(j);
                if (farm[i][j] == 'k')
                    sheep.add(new int[]{i, j});
                if (farm[i][j] == 'v')
                    wolves.add(new int[]{i, j});
            }
        }

        //양, 늑대 dfs하면서 영역 안의 양, 늑대 찾음
        //dfs: x, y에서 for문 돌며 nx, ny로 진입
        //벽 만나면 리턴, vis면 리턴
        for(int[] pos : sheep) {
            if(vis[pos[0]][pos[1]])
                continue;
            //이 양이 들어있는 울타리는 전부 조회
            fenceSheep = 0;
            fenceWolves = 0;
            dfsSheep(pos[0], pos[1]);
            if(fenceSheep > fenceWolves)
                survivedSheep += fenceSheep;
            else
                survivedWolves += fenceWolves;
        }

        for(int[] pos : wolves) {
            if(vis[pos[0]][pos[1]])
                continue;
            //이 늑대가 들어있는 울타리는 전부 조회
            fenceSheep = 0;
            fenceWolves = 0;
            dfsSheep(pos[0], pos[1]);
            if(fenceSheep > fenceWolves)
                survivedSheep += fenceSheep;
            else
                survivedWolves += fenceWolves;
        }

        sb.append(survivedSheep).append(' ').append(survivedWolves);
        System.out.println(sb.toString());
    }
}