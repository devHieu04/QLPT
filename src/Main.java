import View.Login;
import View.RegisterView;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login("Welcome to Login");
                RegisterView registerView = new RegisterView("Register");
                registerView.showView();
//                login.showView();
            }
        });
    }
}
