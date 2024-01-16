package View.Account;

import View.Login;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  AccountView extends JFrame implements ActionListener {
    JPanel pnMain, pnRight, pnLeft, pnUser, pnExpense, pnLogout;
    CardLayout cardLayout;
    JButton btnTenant, btnExpense, btnLogout;
    ImageIcon imgTenant = new ImageIcon(ClassLoader.getSystemResource("Icon/tent.png"));
    ImageIcon imgHouse = new ImageIcon(ClassLoader.getSystemResource("Icon/house.png"));
    ImageIcon imgLogin = new ImageIcon(ClassLoader.getSystemResource("Icon/login.png"));
    Color color = new Color(93, 185, 187);
    String email;
    public AccountView(String s) {
        super(s);
        addView();
    }
    public void showView(String email) {
        this.setSize(1000, 600);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(imgLogin.getImage());
        this.setVisible(true);
        this.email = email;
    }

    private void addView() {
        Container container = getContentPane();

        pnMain = new JPanel();
        pnMain.setLayout(new BorderLayout());

        pnLeft = new JPanel();
        pnLeft.setLayout(new BoxLayout(pnLeft, BoxLayout.Y_AXIS));

        pnUser = new JPanel();
        pnUser.setLayout(new BorderLayout());

        pnExpense = new JPanel();
        pnExpense.setLayout(new BorderLayout());

        pnLogout = new JPanel();
        pnLogout.setLayout(new BorderLayout());

        btnTenant = new JButton("Người thuê nhà");
        btnTenant.setBackground(Color.WHITE);
        btnTenant.setForeground(color);
        btnTenant.setFont(btnTenant.getFont().deriveFont(Font.BOLD, 20));
        btnTenant.setIcon(imgTenant);
        btnTenant.setFocusPainted(false);
        btnTenant.addActionListener(this);

        btnExpense = new JButton("Chi phí");
        btnExpense.setBackground(Color.WHITE);
        btnExpense.setForeground(color);
        btnExpense.setFont(btnExpense.getFont().deriveFont(Font.BOLD, 20));
        btnExpense.setIcon(imgHouse);
        btnExpense.setFocusPainted(false);
        btnExpense.addActionListener(this);

        btnLogout = new JButton("Đăng xuất");
        btnLogout.setBackground(Color.WHITE);
        btnLogout.setForeground(color);
        btnLogout.setFont(btnLogout.getFont().deriveFont(Font.BOLD, 20));
        btnLogout.setFocusPainted(false);
        btnLogout.addActionListener(this);

        pnUser.add(btnTenant, BorderLayout.CENTER);
        pnExpense.add(btnExpense, BorderLayout.CENTER);
        pnLogout.add(btnLogout, BorderLayout.CENTER);

        pnLeft.add(pnUser);
        pnLeft.add(pnExpense);
        pnLeft.add(pnLogout);

        pnRight = new JPanel();
        pnRight.setLayout(new CardLayout());
        cardLayout = (CardLayout) pnRight.getLayout();

        pnMain.add(pnLeft, BorderLayout.WEST);
        pnMain.add(pnRight, BorderLayout.CENTER);

        container.add(pnMain);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnTenant) {
            pnRight.removeAll();
            TenantView tenantView = new TenantView(email);
            pnRight.add(tenantView);
            pnRight.revalidate();
            pnRight.repaint();
        }
        else if (e.getSource() == btnExpense) {
            pnRight.removeAll();
            ExpenseView expenseView = new ExpenseView(email);
            pnRight.add(expenseView);
            pnRight.revalidate();
            pnRight.repaint();
        }
        else if (e.getSource() == btnLogout) {
            this.dispose();
            Login login = new Login("Welcome to Login");
            login.showView();
        }
    }
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
