import java.io.IOException;

import controllers.BancoDeDados;
import views.CarsListScreen;
import views.LoginScreen;

public class Main {
    public static void main(String[] args) throws IOException {

        System.out.print("\033[H\033[2J");  
        Boolean auth = LoginScreen.display();
        if (auth) {
            System.out.print("\033[H\033[2J");  
            CarsListScreen.display();
        }

        if (BancoDeDados.isConnected()) {
            BancoDeDados.endConnection();
        }
    }
}
