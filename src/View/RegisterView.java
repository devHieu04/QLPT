//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package View;

import Controller.AccountController;
import Models.Account;
import TryCatch.EmailCheck;
import TryCatch.PasswordCheck;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class RegisterView extends JFrame implements ActionListener {
    JPanel pnMain, pnRight, pnLeft;
    JLabel lbRegister, lbUsername, lbEmail, lbPassword, lbConfirmPassword, lbQues, lbLogo, lbTitle;
    JTextField txtUsername, txtEmail;
    JPasswordField txtPassword, txtConfirmPassword;
    JButton btnLogin, btnRegister;
    Color color = new Color(93, 185, 187);
    ImageIcon RegisterIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/register.png"));
    ImageIcon Logo = new ImageIcon(ClassLoader.getSystemResource("Icon/logo.png"));
    Login login = new Login("Welcome to Login");

    public RegisterView(String s) {
        super(s);
        addView();
    }

    public void showView() {
        setSize(800, 500);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setLocationRelativeTo(null);
        setIconImage(RegisterIcon.getImage());
        setVisible(true);
    }

    private void addView() {
        Container container = getContentPane();

        pnMain = new JPanel();
        pnMain.setLayout(null);

        pnLeft = new JPanel();
        pnLeft.setLayout(null);
        pnLeft.setMinimumSize(new Dimension(400, 500));
        pnLeft.setBounds(0, 0, 400, 500);
        pnLeft.setBackground(color);

        lbLogo = new JLabel(Logo);
        lbLogo.setBounds(100, 100, 200, 200);

        lbTitle = new JLabel("Welcome to Tra Giang house");
        lbTitle.setBounds(50, 300, 300, 50);
        lbTitle.setFont(new Font("Segoe UI", Font.BOLD, 20));
        lbTitle.setForeground(Color.WHITE);

        pnLeft.add(lbLogo);
        pnLeft.add(lbTitle);

        pnRight = new JPanel();
        pnRight.setLayout(null);
        pnRight.setMinimumSize(new Dimension(400, 500));
        pnRight.setBounds(400, 0, 400, 500);
        pnRight.setBackground(Color.WHITE);

        lbRegister = new JLabel("REGISTER");
        lbRegister.setBounds(140, 30, 200, 50);
        lbRegister.setFont(new Font("Segoe UI", Font.BOLD, 30));
        lbRegister.setForeground(Color.BLACK);

        lbUsername = new JLabel("Username");
        lbUsername.setBounds(50, 100, 100, 20);
        lbUsername.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lbUsername.setForeground(Color.BLACK);

        txtUsername = new JTextField();
        txtUsername.setBounds(50, 125, 300, 30);
        txtUsername.setFont(new Font("SansSerif", Font.PLAIN, 20));

        lbEmail = new JLabel("Email");
        lbEmail.setBounds(50, 160, 100, 20);
        lbEmail.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lbEmail.setForeground(Color.BLACK);

        txtEmail = new JTextField();
        txtEmail.setBounds(50, 185, 300, 30);
        txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 20));

        lbPassword = new JLabel("Password");
        lbPassword.setBounds(50, 220, 100, 20);
        lbPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lbPassword.setForeground(Color.BLACK);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 245, 300, 30);
        txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));

        lbConfirmPassword = new JLabel("Confirm Password");
        lbConfirmPassword.setBounds(50, 280, 200, 20);
        lbConfirmPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lbConfirmPassword.setForeground(Color.BLACK);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(50, 305, 300, 30);
        txtConfirmPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));

        btnRegister = new JButton("Register");
        btnRegister.setBounds(50, 350, 300, 30);
        btnRegister.setFont(new Font("SansSerif", Font.PLAIN, 20));
        btnRegister.setBackground(Color.GRAY);
        btnRegister.setForeground(Color.BLACK);
        btnRegister.addActionListener(this);

        lbQues = new JLabel("Already have an account?");
        lbQues.setBounds(50, 400, 300, 30);
        lbQues.setFont(new Font("SansSerif", Font.BOLD, 15));
        lbQues.setForeground(Color.BLACK);

        btnLogin = new JButton("Login");
        btnLogin.setBounds(240, 400, 50, 30);
        btnLogin.setFont(new Font("SansSerif", Font.PLAIN, 15));
        btnLogin.setBackground(null);
        btnLogin.setBorder(null);
        btnLogin.setForeground(Color.BLACK);
        btnLogin.addActionListener(this);

        pnRight.add(lbRegister);
        pnRight.add(lbUsername);
        pnRight.add(lbEmail);
        pnRight.add(lbPassword);
        pnRight.add(lbConfirmPassword);
        pnRight.add(txtUsername);
        pnRight.add(txtEmail);
        pnRight.add(txtPassword);
        pnRight.add(txtConfirmPassword);
        pnRight.add(btnRegister);
        pnRight.add(lbQues);
        pnRight.add(btnLogin);

        pnMain.add(pnLeft);
        pnMain.add(pnRight);

        container.add(pnMain);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == btnRegister) {
            String username = txtUsername.getText();
            String password = String.valueOf(txtPassword.getPassword());
            String confirm_password = String.valueOf(txtConfirmPassword.getPassword());
            String email = txtEmail.getText();
            boolean emailValid = EmailCheck.isValidEmail(email);
            if (emailValid) {
                if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirm_password.isEmpty()) {
                    JOptionPane.showMessageDialog(null, "Không được để trống các trường thông tin");
                } else {
                    if (password.equals(confirm_password)) {
                        boolean passcheck = PasswordCheck.isPasswordValid(password);
                        AccountController accountController = new AccountController();
                        boolean checkMail = accountController.checkEmailExists(email);
                        if (checkMail) {
                            JOptionPane.showMessageDialog(null, "chọn email khác nhé!!!");
                        } else {
                            if (passcheck) {
                                Account account = new Account(username, password, email, "user");
                                boolean i = accountController.registerAccount(account);
                                if (i) {
                                    JOptionPane.showMessageDialog(null, "Successful!");
                                    txtEmail.setText("");
                                    txtUsername.setText("");
                                    txtPassword.setText("");
                                    txtConfirmPassword.setText("");
                                    login.showView();
                                    dispose();
                                }
                            } else {
                                JOptionPane.showMessageDialog(null, "password phải bao gồm ít nhất 1 chữ in hoa , 1 chữ số , 1 kí tự đặc biệt và có độ dài hơn 8 kí tự");
                            }
                        }
                    } else {
                        JOptionPane.showMessageDialog(null, "Register fail! vui lòng nhập lại các trường dữ liệu khác");
                    }
                }
            } else {
                JOptionPane.showMessageDialog(null, "nhập email đúng định dạng ví dụ: tragiang05@gmail.com ");
            }
        }

        if (e.getSource() == btnLogin) {
            login.showView();
            dispose();
        }
    }
}