import java.util.HashMap;
import java.util.Map;

public class TwoSum {
  public static int[] solution(int[] numbs, int target) {
    Map<Integer, Integer> map = new HashMap<>();

    for (int i = 0; i < numbs.length; i++) {
      int complement = target - numbs[i];
      if (map.containsKey(complement)) {
        return new int[] {map.get(complement), i};
      }
      map.put(numbs[i], i);
    }

    throw new IllegalArgumentException("Tidak ditemukan");
  }
  public static void main(String[] args) {
    int[] nums = {2, 7, 11, 15};
    int target = 9;
    int[] result = solution(nums, target);
    System.out.println("Indices: [" + result[0] + ", " + result[1] + "]");
  }
}
