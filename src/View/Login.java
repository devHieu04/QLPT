package View;

import Controller.AccountController;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Login extends JFrame {
    JTextField txtUser ;
    JPasswordField  txtPassword;
    JButton btLogin,btRegister;
    public Login(String s)
    {
        super(s);
        addView();
        addEvent();
    }

    public void showView()
    {
        this.setSize(500,250);
        this.setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.setLocationRelativeTo(null);
        this.setIconImage(Toolkit.getDefaultToolkit().getImage("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/login.png"));
        this.setVisible(true);
    }
    private void addView() {

        Container con = getContentPane();
        Color color = new Color(225,123,16);
        JPanel pnMain = new JPanel();
        JLabel label = new JLabel();
        label.setBackground(color);
        con.add(pnMain);
        Color mycolor = new Color(93,185,187);
        pnMain.setLayout(new BoxLayout(pnMain, BoxLayout.Y_AXIS));
        JPanel pnTop = new JPanel();
        pnTop.setLayout(new FlowLayout());
        JLabel lbTop = new JLabel();
        lbTop.setText("LOG IN APP ");
        lbTop.setFont(new Font("Ariel Signature", Font.BOLD, 20));
        lbTop.setForeground(mycolor);
        pnTop.add(lbTop);
        pnMain.add(pnTop);


        JPanel pnCenter = new JPanel();
        pnCenter.setLayout(new BorderLayout());
        JPanel pnRight = new JPanel();
        JPanel pnLeft = new JPanel();
        JLabel lbimg = new JLabel();
//        lbimg.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/lgapp.png"));
        pnLeft.add(lbimg);
        pnCenter.add(pnLeft,"West");

        JPanel pnUser = new JPanel();
        pnUser.setLayout(new FlowLayout());
        JLabel lbuser = new JLabel();
        lbuser.setText("User:");
        txtUser = new JTextField(20);
        pnUser.add(lbuser);
        pnUser.add(txtUser);
        pnRight.add(pnUser);

        JPanel pnPassword = new JPanel();
        pnPassword.setLayout(new FlowLayout());
        JLabel lbpass = new JLabel();
        lbpass.setText("Password:");
        txtPassword = new JPasswordField(20);
        pnPassword.add(lbpass);
        pnPassword.add(txtPassword);
        pnRight.add(pnPassword);

        pnCenter.add(pnRight,"Center");
        pnMain.add(pnCenter);

        JPanel pnBot = new JPanel();
        pnBot.setLayout(new FlowLayout());
        btLogin = new JButton("Log In");
        btLogin.setBackground(color);
        btLogin.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/loginbutton.png"));
        btLogin.setPreferredSize(new Dimension(120, 40)); // Thay đổi kích thước ở đây
        pnBot.add(btLogin);

        btRegister= new JButton("Register");
        btRegister.setBackground(color);
        btRegister.setIcon(new ImageIcon("/Users/nguyenduyhieu/Documents/JAVAKII/QLPT/src/Icon/btnRegister.png"));
        btRegister.setPreferredSize(new Dimension(120, 40)); // Thay đổi kích thước ở đây
        pnBot.add(btRegister);

        pnMain.add(pnBot);


        lbuser.setPreferredSize(lbpass.getPreferredSize());


    }
    private void addEvent()
    {
        btLogin.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = txtUser.getText();
                String password = String.valueOf(txtPassword.getPassword());
                AccountController account = new AccountController();
                int stt = account.login(username, password);
                if(stt==2)
                {
                    System.out.println("ok admin nhé");
                }
                else if(stt==1)
                {
                    System.out.println("ok user nhé");
                }
                else
                {
                    System.out.println("sai òi");
                }

            }
        });
        btRegister.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {

            }
        });

    }
}
