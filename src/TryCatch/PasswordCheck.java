package TryCatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class PasswordCheck {
    public static boolean checkLength(String password) {
        return password.length() >= 8;
    }

    public static boolean checkSpecialCharacter(String password) {
        Pattern specialCharPattern = Pattern.compile("[^a-zA-Z0-9]");
        Matcher matcher = specialCharPattern.matcher(password);
        return matcher.find();
    }

    public static boolean checkUpperCase(String password) {
        Pattern upperCasePattern = Pattern.compile("[A-Z]");
        Matcher matcher = upperCasePattern.matcher(password);
        return matcher.find();
    }

    public static boolean checkLowerCase(String password) {
        Pattern lowerCasePattern = Pattern.compile("[a-z]");
        Matcher matcher = lowerCasePattern.matcher(password);
        return matcher.find();
    }

    public static boolean checkDigit(String password) {
        Pattern digitPattern = Pattern.compile("[0-9]");
        Matcher matcher = digitPattern.matcher(password);
        return matcher.find();
    }

    public static boolean isPasswordValid(String password) {
        return checkLength(password)
                && checkSpecialCharacter(password)
                && checkUpperCase(password)
                && checkLowerCase(password)
                && checkDigit(password);
    }
}
