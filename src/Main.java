import java.io.IOException;

import controllers.BancoDeDados;
import views.CarsListScreen;
import views.LoginScreen;

public class Main {
    public static void main(String[] args) throws IOException {

        Boolean auth = LoginScreen.display();
        if (auth) {
            CarsListScreen.display();
        }

        if (BancoDeDados.isConnected()) {
            BancoDeDados.endConnection();
        }
    }
}
