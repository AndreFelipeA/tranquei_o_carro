package views;

import java.util.Scanner;

import controllers.Credenciais;
import models.Usuario;

public class LoginScreen {
    public static Usuario login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite 1 para login e 2 para se registrar!");

        System.out.println("Login");
        System.out.println("Digite seu e-mail!\n");
        String email = scanner.nextLine().toString();
        System.out.println("Digite sua senha!\n");
        String senha = scanner.nextLine().toString();

        Usuario user = Credenciais.verificaCredenciais(email, senha);
        if (user != null) {
            System.out.println("Logado com sucesso!");
        } else {
            System.out.println("Credenciais inválidas!");
        }

        scanner.close();
        return user;
    }
}
