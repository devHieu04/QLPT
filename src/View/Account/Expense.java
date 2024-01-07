package View.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class Expense extends JPanel implements ActionListener {
    public Expense() {
        super();
        addView();
    }

    private void addView() {
        setVisible(true);
        setBackground(Color.RED);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
