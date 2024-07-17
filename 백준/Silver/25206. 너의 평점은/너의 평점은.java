import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Map;
import java.util.StringTokenizer;

//전공 평점 합 구하기
//(학점 X 과목평점) / 학점총합
//평점 A+ = 4.5 -> 맵으로 구현

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        Map<String, Double> gradeTable = Map.of(
                "A+", 4.5,
                "A0", 4.0,
                "B+", 3.5,
                "B0", 3.0,
                "C+", 2.5,
                "C0", 2.0,
                "D+", 1.5,
                "D0", 1.0,
                "F", 0.0
        );

        double creditSum = 0.0;
        double gradeSum = 0.0;

        String readLine;
        while((readLine = br.readLine()) != null) {
            st = new StringTokenizer(readLine);
            st.nextElement();
            double credit = Double.parseDouble(st.nextToken());
            String grade = st.nextToken();
            if(grade.equals("P")) continue;
            creditSum += credit;
            gradeSum += credit * gradeTable.get(grade);
        }

        System.out.println(creditSum != 0 ? gradeSum / creditSum : 0.0);
    }
}