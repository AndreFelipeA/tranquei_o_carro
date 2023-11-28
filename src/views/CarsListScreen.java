package views;

import controllers.Credenciais;
import models.Usuario;

public class CarsListScreen {
    public static void list() {
        System.out.println("\nLista de carros");

        Usuario user = Credenciais.getUsuarioLogado();

        user.obterCarros();
    }
}
