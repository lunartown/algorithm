import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

// 참외밭 넓이 구하기
// 방향과 길이 주어짐
// 큰 사각형 - 작은 사각형
public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int K = Integer.parseInt(br.readLine());
        int[] length = new int[6];

        int maxHeight = 0;
        int maxWidth = 0;
        int maxHeightIndex = 0;
        int maxWidthIndex = 0;

        for(int i = 0; i < 6; i++) {
            st = new StringTokenizer(br.readLine());
            st.nextToken();
            length[i] = Integer.parseInt(st.nextToken());
            if(i % 2 == 0) {
                if(length[i] > maxHeight) {
                    maxHeight = length[i];
                    maxHeightIndex = i;
                }
            } else {
                if(length[i] > maxWidth) {
                    maxWidth = length[i];
                    maxWidthIndex = i;
                }
            }
        }

        int littleSquareIndex;
        if(maxHeightIndex > maxWidthIndex) {
            if(maxWidthIndex == 0 && maxHeightIndex == 5) littleSquareIndex = 2;
            else littleSquareIndex = (maxHeightIndex + 2) % 6;
        } else {
            if(maxHeightIndex == 0 && maxWidthIndex == 5) littleSquareIndex = 2;
            else littleSquareIndex = (maxWidthIndex + 2) % 6;
        }

        int area = maxHeight * maxWidth - length[littleSquareIndex] * length[(littleSquareIndex + 1) % 6];
        System.out.println(area * K);
    }
}