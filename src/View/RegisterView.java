package View;

import javax.swing.*;
import java.awt.*;

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
        Container container = getContentPane();
        JPanel panelMain = new JPanel();
        panelMain.setLayout(new BoxLayout(panelMain,BoxLayout.Y_AXIS));
        container.add(panelMain);

        JPanel panelTitle = new JPanel();
        panelTitle.setLayout(new FlowLayout());
        JLabel lbSignup = new JLabel("Sign Up with app");
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
        panelMain.add(panelButton);





    }

    private void addEvent()
    {

    }
}
