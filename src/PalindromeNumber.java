public class PalindromeNumber {

//  public static boolean solution(int number) {
//    if (number < 0) return false;
//
//    String word = String.valueOf(number);
//    int i = 0, j = word.length() - 1;
//
//    while (i < j) {
//      if (word.charAt(i) != word.charAt(j)) {
//        return false;
//      }
//      i++;
//      j--;
//    }
//
//    return true;
//  }

  public static boolean solution(int x) {
    if (x < 0) return false;
    if (x == 0) return true;
    if (x % 10 == 0) return false;

    int reversed = 0;
    int temp = x;

    while (temp > 0) {
      reversed = reversed * 10 + temp % 10;
      temp /= 10;
    }

    return x == reversed;
  }

  public static void main(String[] args) {
    System.out.println(solution(121));
  }
}