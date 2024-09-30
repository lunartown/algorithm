import java.util.*;

class Solution {
    private static int N;
    private static int max;
    private static int[] A;
    private static int[] maxA;
    
    private static void combination(int num, int idx, int[][] dice) {
        if(idx > N / 2) {
            int wins = countCases(dice);
            if(wins > max) {
                max = wins;
                maxA = A.clone();
            }
            return;
        }
        
        if(num > N) return;
        
        combination(num + 1, idx, dice);
        A[idx - 1] = num;
        combination(num + 1, idx + 1, dice);
    }
    
    private static int countCases(int[][] dice) {
        int wins = 0;
        int[] B = new int[N / 2];
        
        // B 만들기
        int i = 0;
        int j = 0;
        for(int num = 1; num <= N; num++) {
            if(i < N / 2 && A[i] == num) {
                i++;
                continue;
            } else {
                B[j] = num;
                j++;
            }
        }
        
        int[] eyes = new int[N / 2];
        
        List<Integer> sumA = new ArrayList<>();
        List<Integer> sumB = new ArrayList<>();
        
        diceRoll(0, A, sumA, eyes, dice);
        diceRoll(0, B, sumB, eyes, dice);
        
        Collections.sort(sumA);
        Collections.sort(sumB);
        
        int idx = 0;
        for(int resultA : sumA) {
            while(idx < sumB.size() && sumB.get(idx) < resultA) {
                idx++;
            }
            
            wins += idx;
        }
        
        return wins;
    }
    
    private static void diceRoll(int depth, 
                                 int[] arr, List<Integer> sumArr, int[] eyes,
                                 int[][] dice) {
        if(depth == N / 2) {
            int sum = 0;
            for(int i = 0; i < N / 2; i++) {
                int eye = dice[arr[i] - 1][eyes[i]];
                sum += eye;
            }
            sumArr.add(sum);
            return;
        }
        
        for(int i = 0; i < 6; i++) {
            eyes[depth] = i;
            diceRoll(depth + 1, arr, sumArr, eyes, dice);
        }
    }
    
    public int[] solution(int[][] dice) {
        N = dice.length;
        max = 0;
        A = new int[N / 2];
        
        //A 조합
        combination(1, 1, dice);
        
        int[] answer = maxA;
        return answer;
    }
}