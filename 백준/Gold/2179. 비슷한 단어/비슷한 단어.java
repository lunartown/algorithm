import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

public class Main {
    static int maxLength = 0;
    static List<String[]> maxPrefixList = new ArrayList<>();

    static int getPrefixLength(int index, String... strings) {
        if (index == maxLength) {
            maxPrefixList.add(strings);
        } else if (index > maxLength) {
            maxPrefixList = new ArrayList<>();
            maxPrefixList.add(strings);
            maxLength = index;
        }

        int[] alphabet = new int[26];
        for (String s : strings) {
            if(index >= s.length())
                continue;
            alphabet[s.charAt(index) - 'a']++;
        }

        int maxPrefix = -1;
        for (int i = 0; i < 26; i++) {
            if(alphabet[i] >= 2) {
                List<String> newStrings = new ArrayList<>();
                for (String s : strings) {
                    if(index >= s.length())
                        continue;
                    if (s.charAt(index) == (char)(i + 'a')) {
                        newStrings.add(s);
                    }
                }
                getPrefixLength(index + 1, newStrings.toArray(new String[0]));
            }
        }

        return maxPrefix + 1;
    }


    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int N = Integer.parseInt(br.readLine());
        String[] strings = new String[N];
        HashMap<String, Integer> map = new HashMap<>();
        for (int i = 0; i < N; i++) {
            strings[i] = br.readLine();
            map.put(strings[i], i);
        }

        getPrefixLength(0, strings);
//        maxPrefixList.stream().flatMap(Arrays::stream).forEach(System.out::println);
        maxPrefixList.sort(Comparator.comparing(a -> map.get(a[0])));

        sb.append(maxPrefixList.get(0)[0]).append('\n');
        sb.append(maxPrefixList.get(0)[1]);
        System.out.println(sb);
    }
}