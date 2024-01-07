import View.Login;
import View.Register;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login("Welcome to Login");
                login.showView();
            }
        });
    }
}
