package views;

import models.Usuario;

public class CarsListScreen {
    public static void list(Usuario user) {
        System.out.println("Lista de carros");

        user.obterCarros();
    }
}
