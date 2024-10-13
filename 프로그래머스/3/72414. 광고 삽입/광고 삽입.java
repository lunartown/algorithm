import java.util.*;
import java.io.*;
import java.util.stream.*;

class Solution {
    int toSecond(String str) {
        int[] arr = Stream.of(str.split(":")).mapToInt(Integer::parseInt).toArray();
        return (arr[0] * 60 + arr[1]) * 60 + arr[2];
    }
    
    String toTime(int num) {
        StringBuilder sb = new StringBuilder();
        
        String hour = String.format("%02d", num / 3600);
        String minute = String.format("%02d", (num % 3600) / 60);
        String second = String.format("%02d", num % 60);
        
        sb.append(hour).append(":").append(minute).append(":").append(second);
        return sb.toString();
    }
    
    public String solution(String play_time, String adv_time, String[] logs) {
        try {
            int range = toSecond(play_time);
            int length = toSecond(adv_time);
            
            long[] views = new long[range + 1];
            
            for(String log : logs) {
                String[] logArr = log.split("-");
                int start = toSecond(logArr[0]);
                int end = toSecond(logArr[1]);
                
                views[start]++;
                views[end]--;
            }
            
            Arrays.parallelPrefix(views, (a, b) -> a + b);
            Arrays.parallelPrefix(views, (a, b) -> a + b);
            long max = 0;
            int maxIdx = 0;
            
            for(int i = 0; i <= range - length + 1; i++) {
                long sum = views[i + length - 1] - (i == 0 ? 0 : views[i - 1]);
                if(sum > max) {
                    max = sum;
                    maxIdx = i;
                }
            }
            
            // System.out.println(toTime(max));
            
            return toTime(maxIdx);
        } catch(Exception e) {
            StringWriter sw = new StringWriter();
            e.printStackTrace(new PrintWriter(sw));
            System.out.println(sw.toString());
        }
        
        return null;
    }
}