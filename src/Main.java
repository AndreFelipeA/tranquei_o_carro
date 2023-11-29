import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controllers.BancoDeDados;
import views.CarsListScreen;
import views.LoginScreen;
import views.RegisterScreen;

public class Main {
    public static void main(String[] args) throws IOException {

        // display login options
        System.out.println("Digite 1 para login e 2 para se registrar!");
        Integer cmd = 0;

        // Scanner sc = new Scanner(System.in);
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String choice = in.readLine().toString();
        cmd = Integer.parseInt(choice);

        if (cmd.equals(1)) {

            // login and list cars
            if (LoginScreen.login()) {
                boolean exit = false;
                while (!exit) {
                    exit = CarsListScreen.menu();
                }
            }

        } else {
            RegisterScreen.register();
        }

        BancoDeDados.endConnection();
    }
}
