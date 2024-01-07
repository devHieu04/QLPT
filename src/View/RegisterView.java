package View;

import Controller.AccountController;
import Models.Account;
import TryCatch.EmailCheck;
import TryCatch.PasswordCheck;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class RegisterView extends JFrame {
    JTextField txtUsername,txtEmail;
    JPasswordField txtPassword, txtConfirmPassword;

    JButton btnRegister , btnCancel;

public RegisterView(String s)
{
    super(s);
    addView();
    addEvent();
}
public void showView()
{
    this.setSize(500,350);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setResizable(false);
    this.setLocationRelativeTo(null);
    this.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/register.png"));
    this.setVisible(true);
}
    private void addView() {
        Color mycolor = new Color(93,185,187);
        Container container = getContentPane();
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain,BoxLayout.Y_AXIS));
        container.add(panelMain);

        JPanel panelTitle = new JPanel();
        panelTitle.setLayout(new FlowLayout());
        JLabel lbSignup = new JLabel("Sign Up with app");

        Font font = lbSignup.getFont();
        Font largerFont = font.deriveFont(font.getSize() * 1.5f); // Đặt kích thước 1.5 lần so với kích thước hiện tại
        lbSignup.setFont(largerFont);
        lbSignup.setForeground(mycolor);
        panelTitle.add(lbSignup);
        panelMain.add(panelTitle);


        JPanel panelEmail = new JPanel();
        panelEmail.setLayout(new FlowLayout());
        JLabel lbMail = new JLabel();
        lbMail.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/gmail.png"));
        panelEmail.add(lbMail);
        txtEmail = new JTextField();
        txtEmail.setPreferredSize(new Dimension(300,35));
        panelEmail.add(txtEmail);
        panelMain.add(panelEmail);

        JPanel panelUser = new JPanel();
        panelUser.setLayout(new FlowLayout());
        JLabel lbUser = new JLabel();
        lbUser.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/user.png"));
        panelUser.add(lbUser);
        txtUsername = new JTextField();
        txtUsername.setPreferredSize(new Dimension(300,35));
        panelUser.add(txtUsername);
        panelMain.add(panelUser);

        JPanel panelPassword = new JPanel();
        panelPassword.setLayout(new FlowLayout());
        JLabel lbPassword = new JLabel();
        lbPassword.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/password.png"));
        panelPassword.add(lbPassword);
        txtPassword = new JPasswordField();
        txtPassword.setPreferredSize(new Dimension(300, 35));
        panelPassword.add(txtPassword);
        panelMain.add(panelPassword);

        // Panel cho Confirm Password
        JPanel panelConfirmPassword = new JPanel();
        panelConfirmPassword.setLayout(new FlowLayout());
        JLabel lbConfirmPassword = new JLabel();
        lbConfirmPassword.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/confirm_password.png"));
        panelConfirmPassword.add(lbConfirmPassword);
        txtConfirmPassword = new JPasswordField();
        txtConfirmPassword.setPreferredSize(new Dimension(300, 35));
        panelConfirmPassword.add(txtConfirmPassword);
        panelMain.add(panelConfirmPassword);

        JPanel panelButton = new JPanel();
        panelButton.setLayout(new FlowLayout());
        btnRegister = new JButton("Register");
        btnRegister.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/btnRegister.png"));
        btnRegister.setPreferredSize(new Dimension(200,50));
        panelButton.add(btnRegister);
        btnCancel = new JButton("Cancel");
        btnCancel.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/cancel.png"));
        btnCancel.setPreferredSize(new Dimension(200,50));
        panelButton.add(btnCancel);
        panelMain.add(panelButton);



    }

    private void addEvent()
    {
        btnRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUsername.getText();
                String password = String.valueOf(txtPassword.getPassword());
                String confirm_password = String.valueOf(txtConfirmPassword.getPassword());
                String email = txtEmail.getText();
                EmailCheck emailCheck = new EmailCheck();
                boolean emailValid = emailCheck.isValidEmail(email);
                if(emailValid)
                {
                    if(username.equals("")||email.equals("")||password.equals("")||confirm_password.equals(""))
                    {
                        JOptionPane.showMessageDialog(null, "Không được để trống các trường thông tin");
                    }
                    else
                    {
                        if (password.equals(confirm_password))
                        {
                            PasswordCheck passwordCheck = new PasswordCheck();

                            boolean passcheck = passwordCheck.isPasswordValid(password);

                            AccountController accountController = new AccountController();
                            boolean checkMail = accountController.checkEmailExists(email);
                            if(checkMail)
                            {
                                JOptionPane.showMessageDialog(null, "chọn email khác nhé!!!");
                            }
                            else
                            {
                                if(passcheck)
                                {
                                    Account account = new Account(username , password , email, "user" );
                                    boolean  i = accountController.registerAccount(account);

                                    if(i)
                                    {
                                        JOptionPane.showMessageDialog(null, "Successful!");
                                        txtEmail.setText("");
                                        txtUsername.setText("");
                                        txtPassword.setText("");
                                        txtConfirmPassword.setText("");
                                    }
                                }
                                else
                                {
                                    JOptionPane.showMessageDialog(null,"password phải bao gồm ít nhất 1 chữ in hoa , 1 chữ số , 1 kí tự đặc biệt và có độ dài hơn 8 kí tự");
                                }
                            }

                        }
                        else
                        {
                            JOptionPane.showMessageDialog(null, "Register fail! vui lòng nhập lại các trường dữ liệu khác");
                        }
                    }
                }
                else
                {
                    JOptionPane.showMessageDialog(null,"nhập email đúng định dạng ví dụ: tragiang05@gmail.com ");
                }


            }
        });
        btnCancel.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
    }
}
