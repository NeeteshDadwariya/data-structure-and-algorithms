package array_and_strings;

import java.util.HashMap;

public class CountOccurrence {
    public static void main(String[] args) {
        String str = "aabbccdd";
        HashMap<Character, Integer> map = new HashMap();

        for(Character ch : str.toCharArray()) {
            int countVal = map.getOrDefault(ch, 0);
            map.put(ch, ++countVal);
        }

        map.entrySet().forEach(entry -> {
            System.out.println(String.format("Count of %s=%d", entry.getKey(), entry.getValue()));
        });
    }
}