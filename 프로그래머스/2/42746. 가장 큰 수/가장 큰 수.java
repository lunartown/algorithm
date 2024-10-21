import java.util.*;
import java.util.stream.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = Arrays.stream(numbers)
            .mapToObj(String::valueOf)
            .sorted((a, b) -> (b + a).compareTo(a + b))
            .collect(Collectors.joining());
    
        return answer.charAt(0) == '0' ? "0" : answer;
    }
}