import java.io.*;
import java.util.stream.Stream;

//1. 윷판 그리기
//2. 4개 중 말을 하나 골라 이동
//3. 내가 이동하고자 하는 곳에 다른 말이 있으면 못 감(도착 칸 제외)
//4. 도착하면 그 칸 숫자 점수 획득
//5. 파란색 칸에서는 파란 화살표로 이동 가능

//풀이 : Node 연결 상태를 구현하자
class Node {
    int point;
    Node next;
    Node blueNext;
    boolean taken = false;

    Node(int point, Node next) {
        this.point = point;
        this.next = next;
    }

    Node(int point, Node next, Node blueNext) {
        this.point = point;
        this.next = next;
        this.blueNext = blueNext;
    }

    Node() {
    }

    Node move(int num) {
        Node nextNode = this;
        if (this.blueNext != null) {
            nextNode = this.blueNext;
        } else if (this.next != null) {
            nextNode = this.next;
        } else {
            return this;
        }
        for (int i = 1; i < num && nextNode.next != null; i++) {
            nextNode = nextNode.next;
        }
        return nextNode;
    }
}

public class Main {
    static Node[] normalRoute;
    static Node[] topRoute;
    static Node[] leftRoute;
    static Node[] rightRoute;
    static Node[] bottomRoute;
    static int[] moves;
    static Node[] pieces;

    static void drawMap() {
        //판 그리기
        //일반 루트
        normalRoute = new Node[22];
        normalRoute[21] = new Node(0, null);
        for (int i = 20; i >= 0; i--) {
            normalRoute[i] = new Node(i * 2, normalRoute[i + 1]);
        }
        //특수 루트
        topRoute = new Node[3];
        topRoute[2] = new Node(35, normalRoute[20]);
        topRoute[1] = new Node(30, topRoute[2]);
        topRoute[0] = new Node(25, topRoute[1]);

        leftRoute = new Node[3];
        leftRoute[2] = new Node(19, topRoute[0]);
        leftRoute[1] = new Node(16, leftRoute[2]);
        leftRoute[0] = new Node(13, leftRoute[1]);

        rightRoute = new Node[3];
        rightRoute[2] = new Node(26, topRoute[0]);
        rightRoute[1] = new Node(27, rightRoute[2]);
        rightRoute[0] = new Node(28, rightRoute[1]);

        bottomRoute = new Node[2];
        bottomRoute[1] = new Node(24, topRoute[0]);
        bottomRoute[0] = new Node(22, bottomRoute[1]);

        //BlueNodes
        normalRoute[5] = new Node(10, normalRoute[6], leftRoute[0]);
        normalRoute[10] = new Node(20, normalRoute[11], bottomRoute[0]);
        normalRoute[15] = new Node(30, normalRoute[16], rightRoute[0]);

        normalRoute[4].next = normalRoute[5];
        normalRoute[9].next = normalRoute[10];
        normalRoute[14].next = normalRoute[15];
    }

    static int move(int depth, String str, String str2) {
        if (depth >= 10) {
//            System.out.println(str);
//            System.out.println(str2);
//            System.out.println(Stream.of(str2.split(" ")).mapToInt(Integer::parseInt).sum());
            return 0;
        }


        int maxPoint = 0;
        for (int i = 0; i < 4; i++) {
            Node cur = pieces[i];
            Node next = pieces[i].move(moves[depth]);
            //비어있다면
            if (!next.taken || next.equals(normalRoute[21])) {
                //이동
                cur.taken = false;
                pieces[i] = next;
                next.taken = true;

                //dfs
                int point = next.point + move(depth + 1, str + " " + i, str2 + " " + next.point);
                if (point > maxPoint)
                    maxPoint = point;

                //이동 취소 처리
                next.taken = false;
                pieces[i] = cur;
                cur.taken = true;
            }
        }

        return maxPoint;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
//        File file = new File("log.txt");
//        System.setOut(new PrintStream(new FileOutputStream(file)));

        //맵 그리기
        drawMap();

        //이동 입력
        moves = Stream.of(br.readLine().split(" "))
                .mapToInt(Integer::parseInt)
                .toArray();

        //출발
        pieces = new Node[]{normalRoute[0], normalRoute[0], normalRoute[0], normalRoute[0]};
        int maxPoint = move(0, "0", "0");
        System.out.println(maxPoint);
    }
}