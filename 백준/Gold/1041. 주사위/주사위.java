import java.io.*;
import java.util.*;
import java.util.stream.*;

public class Main {
    static boolean isFacing(int a, int b) {
        return (a + b) == 5;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        long N = Long.parseLong(br.readLine());
        // A-F, B-E, C-D
        long[] dice = Stream.of(br.readLine().split(" "))
                .mapToLong(Long::parseLong)
                .toArray();

        int[] idx = IntStream.range(0, 6).boxed().sorted((a, b) -> Long.compare(dice[a], dice[b]))
                .mapToInt(Integer::valueOf).toArray();

        if(N == 1) {
            long sum = Arrays.stream(dice).sum();
            System.out.println(sum - dice[idx[5]]);
        } else {

            long count = 0;

            int[] minIdx = new int[3];
            long[] minValue = new long[3];

            minIdx[0] = idx[0];
            minValue[0] = dice[minIdx[0]];

            int i = 1;
            if (isFacing(minIdx[0], idx[i])) {
                i++;
            }
            minIdx[1] = idx[i];
            minValue[1] = minValue[0] + dice[minIdx[1]];

//        System.out.println(minIdx[0] + " " + minIdx[1]);

            long min = Long.MAX_VALUE;

            for (int j = 0; j < 6; j++) {
                for (int k = 0; k < 6; k++) {
                    if (j == k || isFacing(j, k)) continue;
                    for (int l = 0; l < 6; l++) {
                        if (j == l || k == l || isFacing(j, l) || isFacing(k, l)) continue;
                        min = Math.min(min, dice[j] + dice[k] + dice[l]);
                    }
                }
            }

            minValue[2] = min;

            //한 면
            count += (((N - 2L) * (N - 1L)) * 4L + (N - 2L) * (N - 2L)) * minValue[0];

            //두 면
            count += (((N - 2L) * 8L + 4L)) * minValue[1];

            //세 면
            count += 4 * minValue[2];

            System.out.println(count);
        }
    }
}