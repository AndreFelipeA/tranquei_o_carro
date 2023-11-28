package views;

import java.util.Scanner;

import controllers.Credenciais;

public class LoginScreen {
    public static boolean login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("\nLogin");
        System.out.println("Digite seu e-mail!");
        String email = scanner.nextLine().toString();
        System.out.println("Digite sua senha!");
        String senha = scanner.nextLine().toString();
        scanner.close();

        boolean auth = Credenciais.verificaCredenciais(email, senha);
        if (auth) {
            System.out.println("Logado com sucesso!");
        } else {
            System.out.println("Credenciais inválidas!");
        }

        return auth;
    }
}
