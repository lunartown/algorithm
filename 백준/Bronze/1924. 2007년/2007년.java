import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

//오늘은 2007년 1월 1일 월요일이다. 그렇다면 2007년 x월 y일은 무슨 요일일까?
//입력 : x (int), y (int)
//(오늘 날짜 - 1월 1일) % 7
//배열로 요일을 설정해두자 : 나머지가 0일 때 월요일

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine());

        int x = Integer.parseInt(st.nextToken());
        int y = Integer.parseInt(st.nextToken());
        int[] daysInMonth = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
        String[] weeks = {"MON", "TUE", "WED", "THU", "FRI", "SAT", "SUN"};

        int days = 0;
        for(int i = 1; i < x; i++) {
            days += daysInMonth[i - 1];
        }

        days += y - 1;

        System.out.println(weeks[days % 7]);
    }
}