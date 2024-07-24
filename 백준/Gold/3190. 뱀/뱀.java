import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

class Movement {
    int sec;
    char dir;

    public Movement(int sec, char dir) {
        this.sec = sec;
        this.dir = dir;
    }
}

public class Main {
    private static int[] dx = {0, 1, 0, -1};
    private static int[] dy = {1, 0, -1, 0};

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        //N, K 입력
        int N = Integer.parseInt(br.readLine());
        int K = Integer.parseInt(br.readLine());

        //보드 그리고 벽 1로 채우기
        int[][] board = new int [N + 2][N + 2];
        for(int i = 0; i <= N + 1; i++) {
            board[0][i] = 1;
            board[i][0] = 1;
            board[N + 1][i] = 1;
            board[i][N + 1] = 1;
        }

        //사과는 2
        for(int i = 0; i < K; i++) {
            st = new StringTokenizer(br.readLine());
            board[Integer.parseInt(st.nextToken())][Integer.parseInt(st.nextToken())] = 2;
        }

        //이동 입력받기
        int L = Integer.parseInt(br.readLine());
        Queue<Movement> queue = new ArrayDeque<>();
        for(int i = 0; i < L; i++) {
            st = new StringTokenizer(br.readLine());
            queue.offer(new Movement(Integer.parseInt(st.nextToken()), st.nextToken().charAt(0)));
        }

        //뱀 초기설정
        int[] snakeHead = new int[] {1, 1};
        Queue<int[]> snake = new ArrayDeque<>();
        snake.offer(snakeHead);
        board[1][1] = 3;
        int recordTime;
        int dir = 0;
        Movement nextMove = new Movement(queue.peek().sec, queue.poll().dir);
        for(recordTime = 1; true; recordTime++) {
//            System.out.println("머리" + snakeHead[0] + " " + snakeHead[1] +"꼬리" + snake.peek()[0] + " " + snake.peek()[1]);
            int[] nextCell = new int[] {snakeHead[0] + dx[dir], snakeHead[1] + dy[dir]};
            if(board[nextCell[0]][nextCell[1]] == 1 || board[nextCell[0]][nextCell[1]] == 3) {
                break;
            } else {
                snakeHead = nextCell;
                snake.offer(snakeHead);
//                System.out.println();
//                System.out.println("dksad");
//                System.out.println();
//                System.out.println(nextCell[0] + " " + nextCell[1]);
//                System.out.println();
//                System.out.println(board[nextCell[0]][nextCell[1]]);
//                System.out.println();
                if(board[nextCell[0]][nextCell[1]] == 0) {
                    int[] snakeTail = snake.poll();
                    board[snakeTail[0]][snakeTail[1]] = 0;
                }
                board[snakeHead[0]][snakeHead[1]] = 3;
            }
            if(recordTime == nextMove.sec) {
                if(nextMove.dir == 'D') {
                    dir = (dir + 1) % 4;
                } else if(nextMove.dir == 'L') {
                    dir = (dir + 3) % 4;
                }
                if(!queue.isEmpty())
                    nextMove = new Movement(queue.peek().sec, queue.poll().dir);
            }
        }
        System.out.println(recordTime);
    }
}