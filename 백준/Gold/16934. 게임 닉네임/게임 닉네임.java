import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

//prefix
//가장 짧은 안 겹치는 접두사
//없을 시 닉네임+숫자
class Node {
    //자식 노드
    Map<Character, Node> childNode = new HashMap<>();
    //개수를 세줘야 하므로, end of word에 count 추가
    int count = -1;
}

class Trie {
    //root
    Node rootNode = new Node();

    void insert(String str) {
        Node node = this.rootNode;

        //해당 문자가 childNode에 없으면 만들고
        //있으면 그 노드를 타고 감
        for (int i = 0; i < str.length(); i++) {
            node = node.childNode.computeIfAbsent(str.charAt(i), key -> new Node());
        }

        node.count--;
    }

    int search(String str) {
        Node node = this.rootNode;

        for (int i = 0; i < str.length(); i++) {
            //처음으로 달라지는 시점을 확인한다면
            node = node.childNode.getOrDefault(str.charAt(i), null);
            //가장 긴 접두사
            if (node == null)
                return i;
        }

        //모두 존재한다면 음수로 count 출력
        return node.count;
    }
}

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] users = new String[N];
        String[] nicknames = new String[N];
        for (int i = 0; i < N; i++) {
            users[i] = br.readLine();
        }

        //시작
        Trie trie = new Trie();
        for (int i = 0; i < N; i++) {
            String str = users[i];
            //검색부터
            int pre = trie.search(str);
            //-1이면 가능한 접두사가 없는 것
            if (pre < 0) {
                //없으면 그냥, 있으면 숫자 추가
                nicknames[i] = str + (pre == -1 ? "" : -pre);
            } else {
                //-1이 아니면 pre값이 처음으로 안 겹치는 시점
                nicknames[i] = str.substring(0, pre + 1);
            }

            //검색하고 나서, trie에 나 추가
            trie.insert(str);
        }

        //출력
        Arrays.stream(nicknames).forEach(a -> sb.append(a).append('\n'));
        System.out.println(sb);
    }
}