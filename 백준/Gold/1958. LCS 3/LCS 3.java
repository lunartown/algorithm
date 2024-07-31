import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {
    static int N, M, K;

    static int[][][] makeLCS(String str1, String str2, String str3) {
        //초기화
        int[][][] lcs = new int[N + 1][M + 1][K + 1];
        for (int i = 0; i <= N; i++) {
            for (int j = 0; j <= M; j++) {
                lcs[i][j][0] = 0;
            }
        }
        for (int j = 0; j <= M; j++) {
            for (int k = 0; k <= K; k++) {
                lcs[0][j][k] = 0;
            }
        }
        for (int k = 0; k <= K; k++) {
            for (int i = 0; i <= N; i++) {
                lcs[i][0][k] = 0;
            }
        }

        //채우기
        //셋이 같을 땐 +1
        //셋이 다를 땐 최댓값 이어가기
        for (int i = 1; i <= N; i++) {
            for (int j = 1; j <= M; j++) {
                for (int k = 1; k <= K; k++) {
                    if (str1.charAt(i - 1) == str2.charAt(j - 1) && str1.charAt(i - 1) == str3.charAt(k - 1)) {
                        lcs[i][j][k] = lcs[i - 1][j - 1][k - 1] + 1;
                    } else {
                        lcs[i][j][k] = Math.max(Math.max(lcs[i - 1][j][k], lcs[i][j - 1][k]), lcs[i][j][k - 1]);
                    }
                }
            }
        }

        return lcs;
    }

    static String findLCS(int[][][] lcs, String str1) {
        char[] arr = new char[lcs[N][M][K]];
        int i = N;
        int j = M;
        int k = K;
        while (lcs[i][j][k] > 0) {
            if (lcs[i - 1][j][k] == lcs[i][j][k]) {
                i = i - 1;
            } else if (lcs[i][j - 1][k] == lcs[i][j][k]) {
                j = j - 1;
            } else if (lcs[i][j][k - 1] == lcs[i][j][k]) {
                k = k - 1;
            } else {
                arr[lcs[i][j][k] - 1] = str1.charAt(i - 1);
                i = i - 1;
                j = j - 1;
                k = k - 1;
            }
        }

        return new String(arr);
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String str1 = br.readLine();
        String str2 = br.readLine();
        String str3 = br.readLine();

        N = str1.length();
        M = str2.length();
        K = str3.length();

        System.out.println(findLCS(makeLCS(str1, str2, str3), str1).length());
    }
}