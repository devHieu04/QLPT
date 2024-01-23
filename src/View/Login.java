package View;

import Controller.AccountController;
import Models.Account;
import View.Account.AccountView;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Login extends JFrame implements ActionListener {
    JPanel pnMain, pnRight,  pnLeft;
    JLabel lbLogin, lbUsername, lbPassword, lbQues, lbLogo, lbTitle;
    JTextField txtUsername;
    JPasswordField txtPassword;
    JButton btnLogin, btnRegister, btnForgot;
    Color color = new Color(93, 185, 187);
    ImageIcon loginIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/login.png"));
    ImageIcon Logo = new ImageIcon(ClassLoader.getSystemResource("Icon/logo.jpg"));
    AdminView adminView = new AdminView("Admin");
    AccountView accountView = new AccountView("Người thuê nhà");
    public Login(String s) {
        super(s);
        addView();
    }
    public void showView() {
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(loginIcon.getImage());
        setVisible(true);
        getRootPane().setDefaultButton(btnLogin);
    }

    private void addView() {
        Container container = getContentPane();

        pnMain = new JPanel();
        pnMain.setLayout(new GridLayout(1, 2));

        pnLeft = new JPanel();
        pnLeft.setLayout(null);
        pnLeft.setMinimumSize(new Dimension(400, 500));
        pnLeft.setBackground(Color.WHITE);

        lbLogo = new JLabel(Logo);
        lbLogo.setBounds(125, 50, 200, 200);

        lbTitle = new JLabel("Welcome to Tra Giang House");
        lbTitle.setBounds(60, 300, 300, 50);
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 22));
        lbTitle.setForeground(Color.BLACK);

        pnLeft.add(lbLogo);
        pnLeft.add(lbTitle);

        pnRight = new JPanel();
        pnRight.setLayout(null);
        pnRight.setMinimumSize(new Dimension(400, 500));
        pnRight.setBackground(Color.WHITE);

        lbLogin = new JLabel("LOGIN");
        lbLogin.setBounds(140, 30, 100, 50);
        lbLogin.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lbLogin.setForeground(Color.BLACK);

        lbUsername = new JLabel("Username");
        lbUsername.setBounds(50, 100, 100, 30);
        lbUsername.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lbUsername.setForeground(Color.BLACK);

        txtUsername = new JTextField();
        txtUsername.setBounds(50, 130, 300, 30);
        txtUsername.setFont(new Font("SansSerif", Font.PLAIN, 20));

        lbPassword = new JLabel("Password");
        lbPassword.setBounds(50, 200, 100, 30);
        lbPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lbPassword.setForeground(Color.BLACK);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 230, 300, 30);
        txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));

        btnLogin = new JButton("Login");
        btnLogin.setBounds(50, 300, 300, 30);
        btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 20));
        btnLogin.setBackground(Color.GRAY);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.addActionListener(this);

        btnForgot = new JButton("Forgot password?");
        btnForgot.setBounds(50, 350, 300, 30);
        btnForgot.setFont(new Font("SansSerif", Font.PLAIN, 15));
        btnForgot.setBackground(null);
        btnForgot.setBorder(null);
        btnForgot.setForeground(Color.BLACK);
        btnForgot.addActionListener(this);

        lbQues = new JLabel("Don't have an account?");
        lbQues.setBounds(50, 400, 300, 30);
        lbQues.setFont(new Font("SansSerif", Font.BOLD, 15));
        lbQues.setForeground(Color.BLACK);

        btnRegister = new JButton("Register");
        btnRegister.setBounds(225, 400, 75, 30);
        btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 15));
        btnRegister.setBackground(null);
        btnRegister.setBorder(null);
        btnRegister.setForeground(Color.BLACK);
        btnRegister.addActionListener(this);

        pnRight.add(lbLogin);
        pnRight.add(lbUsername);
        pnRight.add(txtUsername);
        pnRight.add(lbPassword);
        pnRight.add(txtPassword);
        pnRight.add(btnLogin);
        pnRight.add(btnForgot);
        pnRight.add(lbQues);
        pnRight.add(btnRegister);

        pnMain.add(pnLeft);
        pnMain.add(pnRight);

        container.add(pnMain);
    }

    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnLogin) {
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());
            AccountController accountC = new AccountController();
            Account acc = accountC.login(username, password);
            if (acc.getRole().equals("admin")) {
                adminView.showView();
                dispose();
            }
            else if (acc.getRole().equals("user")) {
                accountView.showView(acc.getEmail());
                dispose();
            }
            else
                JOptionPane.showMessageDialog(null, "Sai tên đăng nhập hoặc mật khẩu", "Lỗi", JOptionPane.ERROR_MESSAGE);
        }
        if (e.getSource() == btnRegister) {
            RegisterView register = new RegisterView("Register");
            register.showView();
            dispose();
        }
    }


}