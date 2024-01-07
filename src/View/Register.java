//
// Source code recreated from a .class file by IntelliJ IDEA
// (powered by FernFlower decompiler)
//

package View;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Register extends JFrame implements ActionListener {
    JPanel pnMain, pnRight, pnLeft;
    JLabel lbRegister, lbUsername, lbEmail, lbPassword,  lbConfirmPassword, lbQues;
    JTextField txtUsername,  txtEmail;
    JPasswordField txtPassword,  txtConfirmPassword;
    JButton btnLogin,  btnRegister;
    ImageIcon RegisterIcon = new ImageIcon(ClassLoader.getSystemResource("Icon/register.png"));

    public Register(String s) {
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
        pnLeft.setBackground(Color.GRAY);

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
        txtUsername.setBounds(50, 125, 300, 25);
        txtUsername.setFont(new Font("SansSerif", Font.PLAIN, 20));

        lbEmail = new JLabel("Email");
        lbEmail.setBounds(50, 160, 100, 20);
        lbEmail.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lbEmail.setForeground(Color.BLACK);

        txtEmail = new JTextField();
        txtEmail.setBounds(50, 185, 300, 25);
        txtEmail.setFont(new Font("SansSerif", Font.PLAIN, 20));

        lbPassword = new JLabel("Password");
        lbPassword.setBounds(50, 220, 100, 20);
        lbPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lbPassword.setForeground(Color.BLACK);

        txtPassword = new JPasswordField();
        txtPassword.setBounds(50, 245, 300, 25);
        txtPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));

        lbConfirmPassword = new JLabel("Confirm Password");
        lbConfirmPassword.setBounds(50, 280, 200, 20);
        lbConfirmPassword.setFont(new Font("SansSerif", Font.PLAIN, 20));
        lbConfirmPassword.setForeground(Color.BLACK);

        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setBounds(50, 305, 300, 25);
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
        pnRight.add(txtUsername);
        pnRight.add(lbEmail);
        pnRight.add(txtEmail);
        pnRight.add(lbPassword);
        pnRight.add(txtPassword);
        pnRight.add(lbConfirmPassword);
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
            JOptionPane.showMessageDialog((Component)null, "Register Successfully");
        }

        if (e.getSource() == btnLogin) {
            Login login = new Login("Welcome to Login");
            login.showView();
            dispose();
        }
    }
}
