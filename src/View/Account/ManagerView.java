package View.Account;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class ManagerView extends JPanel implements ActionListener {
    public ManagerView() {
        super();
        addView();
    }

    private void addView() {
        setVisible(true);
        setBackground(Color.BLUE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {

    }
}
