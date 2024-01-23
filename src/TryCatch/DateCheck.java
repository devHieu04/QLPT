package TryCatch;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class DateCheck {
    public static boolean isValidDate(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Không cho phép chuyển đổi mềm dẻo

        try {
            Date date = sdf.parse(inputDate);
            Date currentDate = new Date(); // Lấy ngày hiện tại

            // Kiểm tra xem ngày đã nhập có nhỏ hơn ngày hiện tại không
            if (!date.before(currentDate)) {
                return true; // Ngày hợp lệ và không nhỏ hơn ngày hiện tại
            }
        } catch (ParseException e) {
            // Ngày không phù hợp với định dạng "dd/MM/yyyy"
            return false;
        }

        return false;
    }
    public static boolean isBirthday(String inputDate) {
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        sdf.setLenient(false); // Không cho phép chuyển đổi mềm dẻo

        try {
            Date date = sdf.parse(inputDate);
            Date currentDate = new Date(); // Lấy ngày hiện tại

            // Kiểm tra xem ngày đã nhập có nhỏ hơn ngày hiện tại không
            if (date.before(currentDate)) {
                return true; // Ngày hợp lệ và không nhỏ hơn ngày hiện tại
            }
        } catch (ParseException e) {
            // Ngày không phù hợp với định dạng "dd/MM/yyyy"
            return false;
        }

        return false;
    }

    public static void main(String[] args) {
        String inputDate = "01/01/2024"; // Đổi chuỗi ngày tháng để kiểm tra
        boolean isValid = isValidDate(inputDate);

        if (isValid) {
            System.out.println("Ngày hợp lệ và không nhỏ hơn ngày hiện tại.");
        } else {
            System.out.println("Ngày không hợp lệ hoặc nhỏ hơn ngày hiện tại.");
        }
    }
}