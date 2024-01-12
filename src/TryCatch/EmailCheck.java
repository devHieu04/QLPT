package TryCatch;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class EmailCheck {
    public static boolean isValidEmail(String email) {
        // Biểu thức chính quy để kiểm tra định dạng email
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\.[a-zA-Z0-9_+&*-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,7}$";

        Pattern pattern = Pattern.compile(emailRegex);
        Matcher matcher = pattern.matcher(email);

        return matcher.matches(); // Trả về true nếu email hợp lệ, ngược lại trả về false
    }
    public boolean isNumericAndLength12(String input) {
        if (input == null || input.length() != 12) {
            return false;
        }
        try {
            Long.parseLong(input);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

}