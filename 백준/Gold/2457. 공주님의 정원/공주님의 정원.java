import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.PriorityQueue;
import java.util.StringTokenizer;

class Flower {
    int from;
    int to;

    Flower(int from, int to) {
        this.from = from;
        this.to = to;
    }
}

public class Main {
    static int[] months = {31, 28, 31, 30, 31, 30, 31, 31, 30, 31, 30, 31};
    static int[] sumMonth;

    //날짜 -> 숫자
    static int dateToNumber(int month, int date) {
        return sumMonth[month - 1] + date;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //월 설정 : 날짜 -> 숫자로 변경
        sumMonth = new int[12];
        sumMonth[0] = 0;
        for (int i = 1; i < 12; i++) {
            sumMonth[i] = sumMonth[i - 1] + months[i - 1];
        }

        //꽃 배열 입력
        int N = Integer.parseInt(br.readLine());
        PriorityQueue<Flower> flowers = new PriorityQueue<>((f1, f2) -> f1.from - f2.from);
        for (int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            int from = dateToNumber(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            int to = dateToNumber(Integer.parseInt(st.nextToken()), Integer.parseInt(st.nextToken()));
            flowers.add(new Flower(from, to));
        }

        //기간 설정
        int startDate = dateToNumber(3, 1);
        int endDate = dateToNumber(11, 30);

        //지는 날짜가 가장 뒤인 것을 가져오기
        //지는 날짜 pq
        int count = 0;
        PriorityQueue<Integer> pq = new PriorityQueue<>(Comparator.reverseOrder());
        while (startDate <= endDate && !flowers.isEmpty()) {
            pq.clear();

            //피는 날짜가 startDate보다 작거나 같은 꽃 가져오기
            //지는 날짜가 많이 남은 순으로 정렬
            Flower flower = flowers.poll();
            while (flower != null && flower.from <= startDate) {
                pq.offer(flower.to);
                flower = flowers.poll();
            }
            if (flower != null)
                flowers.offer(flower);

            //그런 꽃이 없으면 탈출
            if (pq.isEmpty())
                break;

            //가장 늦게 지는 꽃을 선정
            startDate = pq.poll();
            count++;
        }

        if (startDate > endDate)
            System.out.println(count);
        else System.out.println(0);
    }
}