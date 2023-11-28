import java.sql.SQLException;
import java.util.Scanner;

import controllers.BancoDeDados;
import models.Usuario;
import views.CarsListScreen;
import views.LoginScreen;
import views.RegisterScreen;

public class Main {
    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite 1 para login e 2 para se registrar!");
        String choice = scanner.nextLine();
        Integer c = Integer.parseInt(choice);
        
        if (c.equals(1)) {
            boolean auth = LoginScreen.login();
            while(auth) {
                CarsListScreen.list();
                System.out.println("Digite 1 para Adicionar carro");
                String read = scanner.nextLine();
                Integer add = Integer.parseInt(read);
                if(add.equals(1))
                {
                    CarsListScreen.newCar();
                }
            }
        } else {
            RegisterScreen.register();
        }

        BancoDeDados.endConnection();
        scanner.close();
    }
}
