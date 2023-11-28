import java.sql.SQLException;
import java.util.Scanner;

import db.BancoDeDados;
import views.LoginScreen;
import views.RegisterScreen;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite 1 para login e 2 para se registrar!");
        String choice = scanner.nextLine();
        Integer c = Integer.parseInt(choice);
        if (c.equals(1)) {
            LoginScreen.login();
        } else {
            RegisterScreen.register();
        }

        BancoDeDados.endConnection();
        scanner.close();
    }
}
