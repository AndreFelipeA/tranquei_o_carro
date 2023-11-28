import java.util.Scanner;

import controllers.BancoDeDados;
import views.CarsListScreen;
import views.LoginScreen;
import views.RegisterScreen;

public class Main {
    public static void main(String[] args) {

        // display login options
        System.out.println("Digite 1 para login e 2 para se registrar!");
        Scanner sc = new Scanner(System.in);
        String choice = sc.nextLine();
        Integer c = Integer.parseInt(choice);

        if (c.equals(1)) {

            // login and list cars
            boolean auth = LoginScreen.login();
            while (auth) {
                CarsListScreen.list();

                // display car options
                System.out.println("Digite 1 para Adicionar carro e 2 para Sair.");
                String read = sc.nextLine();
                Integer cmd = Integer.parseInt(read);
                if (cmd.equals(1)) {
                    CarsListScreen.newCar();
                }

                if (cmd.equals(2)) {
                    auth = false;
                }
            }

        } else {
            RegisterScreen.register();
        }

        BancoDeDados.endConnection();
    }
}
