package views;

import controllers.BancoDeDados;
import controllers.Credenciais;
import models.Usuario;
import java.util.Scanner;
import models.Carro;
public class CarsListScreen {
    public static void list() {
        System.out.println("\nLista de carros");

        Usuario user = Credenciais.getUsuarioLogado();
        
        user.obterCarros();
    }

    public static void newCar()
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite a marca do carro:");
        String marca = scanner.nextLine();
        System.out.println("Digite o modelo do carro:");
        String modelo = scanner.nextLine();
        System.out.println("Digite o ano do carro:");
        String ano = scanner.nextLine();
        Usuario user = Credenciais.getUsuarioLogado();

        Carro car = new Carro(marca,modelo,Integer.parseInt(ano),user);
        BancoDeDados db = BancoDeDados.getInstance();
        db.adicionarCarro(marca, modelo, Integer.parseInt(ano), user.getId());
        user.adicionarCarro(car);
        scanner.close();

    }
}
