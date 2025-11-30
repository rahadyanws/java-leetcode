import java.util.regex.Pattern;

public class PhoneValidator {
  public static boolean isValidPhoneNumber(String phone) {
    if (phone == null) return false;
    String cleaned = phone.replaceAll("[\\s-]", "");
    String regex = "^(\\+628|628|08)\\d{8,12}$";
    return Pattern.matches(regex, cleaned);
  }

  public static void main(String[] args) {
    System.out.println(isValidPhoneNumber("081234567890")); // true
    System.out.println(isValidPhoneNumber("0812-3456-7890")); // true
    System.out.println(isValidPhoneNumber("0812 3456 7890")); // true
    System.out.println(isValidPhoneNumber("+6281234567890")); // true
    System.out.println(isValidPhoneNumber("0123456789")); // false
  }
}