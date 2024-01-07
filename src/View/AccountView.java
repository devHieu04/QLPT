package View;

import Controller.AccountController;
import Models.Account;
//
//import java.util.Scanner;
//
public class AccountView {
//
//    private AccountController accountController = new AccountController();
//
//    Scanner scanner = new Scanner(System.in);
//    public void run() {
//
//
//        while (true) {
//            System.out.println("1. Đăng ký tài khoản");
//            System.out.println("2. Đăng nhập");
//            System.out.println("3. Thoát");
//            System.out.print("Nhập lựa chọn: ");
//            int choice = scanner.nextInt();
//
//            switch (choice) {
//                case 1:
//                    registerAccount();
//                    break;
//                case 2:
//                    login();
//                    break;
//                case 3:
//                    System.exit(0);
//                default:
//                    System.out.println("Lựa chọn không hợp lệ!");
//            }
//        }
//    }
//
//    private void registerAccount() {
//        System.out.print("Nhập username: ");
//        String username = scanner.next();
//        System.out.print("Nhập password: ");
//        String password = scanner.next();
//        System.out.print("Nhập email: ");
//        String email = scanner.next();
//        System.out.print("Nhập role (admin/user): ");
//        String role = scanner.next();
//
//        Account account = new Account(username, password, email, role);
//        boolean isRegistered = accountController.registerAccount(account);
//
//        if (isRegistered) {
//            System.out.println("Đăng ký tài khoản thành công!");
//        } else {
//            System.out.println("Đăng ký tài khoản thất bại!");
//        }
//    }
//
//    private void login() {
//        System.out.print("Nhập username: ");
//        String username = scanner.next();
//        System.out.print("Nhập password: ");
//        String password = scanner.next();
//
//        boolean isLoggedIn = accountController.login(username, password);
//
//        if (isLoggedIn) {
//            System.out.println("Đăng nhập thành công!");
//        } else {
//            System.out.println("Tên đăng nhập hoặc mật khẩu không đúng!");
//        }
//    }
//
//    public static void main(String[] args) {
//        AccountView view = new AccountView();
//        view.run();
//    }
}
