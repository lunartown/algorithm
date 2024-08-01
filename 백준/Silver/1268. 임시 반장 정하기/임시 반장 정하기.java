import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int N = Integer.parseInt(br.readLine());

        //학생, 반 리스트 생성
        Set<Integer>[] students = new HashSet[N + 1];
        for(int i = 1; i <= N; i++) {
            students[i] = new HashSet<>();
        }
        List<Integer>[][] classes = new List[6][10];
        for(int i = 1; i <= 5; i++) {
            for(int j = 1; j <= 9; j++) {
                classes[i][j] = new LinkedList<>();
            }
        }

        //반 입력받기
        for (int i = 1; i <= N; i++) {
            st = new StringTokenizer(br.readLine());
            classes[1][Integer.parseInt(st.nextToken())].add(i);
            classes[2][Integer.parseInt(st.nextToken())].add(i);
            classes[3][Integer.parseInt(st.nextToken())].add(i);
            classes[4][Integer.parseInt(st.nextToken())].add(i);
            classes[5][Integer.parseInt(st.nextToken())].add(i);
        }

        //반마다 친구 추가
        for(int i = 1; i <= 5; i++) {
            for(int j = 1; j <= 9; j++) {
                for(int student : classes[i][j]) {
                    students[student].addAll(classes[i][j]);
                }
            }
        }

        //친구 가장 많은 사람 출력
        int max = 0;
        int president = 0;
        for(int i = 1; i <= N; i++) {
            if(students[i].size() > max) {
                max = students[i].size();
                president = i;
            }
        }

        System.out.println(president);
    }
}