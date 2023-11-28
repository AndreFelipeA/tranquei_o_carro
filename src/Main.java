import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Digite 1 para login e 2 para se registrar!");
        String choice = scanner.nextLine();
        Integer c = Integer.parseInt(choice);
        BancoDeDados db = BancoDeDados.getInstance();
        if(c.equals(1))
        {
            LoginScreen.login();
        }
        else
        {
            RegisterScreen.register();
        }


        try {
            db.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();

    }
}
