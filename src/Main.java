import View.Account.AccountView;
import View.Login;

public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                Login login = new Login("Welcome to Login");
                AccountView accountView = new AccountView("Người thuê nhà");
//                login.showView();
                accountView.showView();
            }
        });
    }
}
