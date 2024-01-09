package View.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class  AccountView extends JFrame implements ActionListener {
    JPanel pnMain, pnRight, pnLeft, pnUser, pnExpense, pnManager;
    CardLayout cardLayout;
    JButton btnTenant, btnExpense, btnManager;
    ImageIcon imgTenant = new ImageIcon(ClassLoader.getSystemResource("Icon/tent.png"));
    ImageIcon imgHouse = new ImageIcon(ClassLoader.getSystemResource("Icon/house.png"));
    ImageIcon imgRental = new ImageIcon(ClassLoader.getSystemResource("Icon/rent.png"));
    Color color = new Color(93, 185, 187);
    String email;
    public AccountView(String s) {
        super(s);
        addView();
    }
    public void showView(String email) {
        setSize(1000, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setVisible(true);
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

        pnManager = new JPanel();
        pnManager.setLayout(new BorderLayout());

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

        btnManager = new JButton("Quản lý nhà");
        btnManager.setBackground(Color.WHITE);
        btnManager.setForeground(color);
        btnManager.setFont(btnManager.getFont().deriveFont(Font.BOLD, 20));
        btnManager.setIcon(imgRental);
        btnManager.setFocusPainted(false);
        btnManager.addActionListener(this);

        pnUser.add(btnTenant, BorderLayout.CENTER);
        pnExpense.add(btnExpense, BorderLayout.CENTER);
        pnManager.add(btnManager, BorderLayout.CENTER);

        pnLeft.add(pnUser);
        pnLeft.add(pnExpense);
        pnLeft.add(pnManager);

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
            ExpenseView expenseView = new ExpenseView();
            pnRight.add(expenseView);
            pnRight.revalidate();
            pnRight.repaint();
        }
        else if (e.getSource() == btnManager) {
            pnRight.removeAll();
            ManagerView managerView = new ManagerView();
            pnRight.add(managerView);
            pnRight.revalidate();
            pnRight.repaint();
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
