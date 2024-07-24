import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.ListIterator;
import java.util.StringTokenizer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;
        LinkedList<Character> string = br.readLine().chars()
                .mapToObj(i -> (char) i)
                .collect(Collectors.toCollection(LinkedList::new));

        ListIterator<Character> iterator = string.listIterator(string.size());

        int N = Integer.parseInt(br.readLine());
        for(int i = 0; i < N; i++) {
            st = new StringTokenizer(br.readLine());
            switch(st.nextToken()) {
                case "L":
                    if(iterator.hasPrevious()) {
                        iterator.previous();
                    }
                    break;
                case "D":
                    if(iterator.hasNext()) {
                        iterator.next();
                    }
                    break;
                case "B":
                    if(iterator.hasPrevious()) {
                        iterator.previous();
                        iterator.remove();

                    }
                    break;
                case "P":
                    iterator.add(st.nextToken().charAt(0));
                    break;
                default:
                    throw new IOException("Invalid input");
            }
        }
        System.out.println(string.stream()
                .map(i -> i.toString())
                .collect(Collectors.joining()));
    }
}