package views;

import java.util.Scanner;

import controllers.Credenciais;
import models.Carro;
import models.Usuario;
import dao.CarroDao;

public class CarsListScreen {
    public static void list() {
        System.out.println("\nLista de carros");

        Usuario user = Credenciais.getUsuarioLogado();

        user.obterCarros();
        System.out.println("");
    }

    public static void newCar() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Digite a marca do carro:");
        String marca = sc.nextLine();
        System.out.println("Digite o modelo do carro:");
        String modelo = sc.nextLine();
        System.out.println("Digite o ano do carro:");
        String ano = sc.nextLine();
        Usuario user = Credenciais.getUsuarioLogado();

        Carro car = new Carro(marca, modelo, Integer.parseInt(ano), user);
        CarroDao.adicionarCarro(marca, modelo, Integer.parseInt(ano), user.getId());

        user.adicionarCarro(car);
    }
}
