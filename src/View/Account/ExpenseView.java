package View.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ExpenseView extends JPanel implements ActionListener {
    public ExpenseView() {
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
