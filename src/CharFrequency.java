import java.util.*;

public class CharFrequency {
  public static Map<Character, Integer> countFreq(String s) {
    Map<Character, Integer> map = new HashMap<>();
    for (char c : s.toCharArray()) {
      map.put(c, map.getOrDefault(c, 0) + 1);
    }
    return map;
  }

  public static void main(String[] args) {
    System.out.println(countFreq("hello world"));
  }
}
