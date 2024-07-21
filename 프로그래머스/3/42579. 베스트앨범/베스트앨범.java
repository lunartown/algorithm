import java.util.*;
import java.io.*;


class Solution {
    public int[] solution(String[] genres, int[] plays) throws IOException{
        //장르별 총 재생 수를 기록한 맵
        Map<String, Integer> genrePlays = new HashMap<>();
        //장르별 곡 목록과 그 곡의 재생수를 기록한 맵
        Map<String, Map<Integer, Integer>> genreMusicPlays = new HashMap<String, Map<Integer, Integer>>();
        //최종 리스트 : 장르별 인기순 2곡씩, 인덱스 순으로 정렬한다.
        List<Integer> bestMusic = new ArrayList<>();

        for(int i = 0; i < genres.length; i++) {
            //곡과 장르를 받는다: 곡은 idx, 장르는 genre[i]
            String genre = genres[i];
            //장르별 재생수를 기록한다 : (장르, 총 재생수 = plays[i])
            genrePlays.put(genre, genrePlays.getOrDefault(genre, 0) + plays[i]);
            //장르별 곡 목록과 그 곡의 재생 수를 기록한다 : (genre, map(i, plays[i])
            //없으면 새로 만들고, 아니면 그냥 있는다
            genreMusicPlays.computeIfAbsent(genre, k -> new HashMap<Integer, Integer>());

            //그 맵에 기록한다.
            genreMusicPlays.get(genre).put(i, plays[i]);
        }

        //장르별 총 플레이 수를
        genrePlays.entrySet().stream()
                //소팅한다 : 역순으로, 그 후 entry를 가져와 get한다...?
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder())).map(e -> {
                    return genreMusicPlays.get(e.getKey());
                })
                .forEach(map -> map.entrySet().stream()
                        .sorted((e1, e2) -> {
                            if (e1.getValue().equals(e2.getValue())) {
                                return e1.getKey().compareTo(e2.getKey());
                            } else {
                                return e2.getValue().compareTo(e1.getValue());
                            }
                        })
                        .limit(2).forEach((e) -> bestMusic.add(e.getKey())));


        return bestMusic.stream().mapToInt(i -> i).toArray();
    }
}