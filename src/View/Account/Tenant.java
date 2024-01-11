package View.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Tenant extends JPanel implements ActionListener {
    public Tenant() {
        super();
        addView();
    }

    private void addView() {
        setVisible(true);
        setBackground(Color.GREEN);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
