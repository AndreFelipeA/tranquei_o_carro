package views;

import java.util.Scanner;

import controllers.Credenciais;

public class LoginScreen {
    public static boolean login() {
        System.out.println("\nLogin");
        System.out.println("Digite seu e-mail!");
        Scanner sc = new Scanner(System.in);
        String email = sc.nextLine().toString();
        System.out.println("Digite sua senha!");
        String senha = sc.nextLine().toString();
        
        boolean auth = Credenciais.verificaCredenciais(email, senha);
        if (auth) {
            System.out.println("Logado com sucesso!");
        } else {
            System.out.println("Credenciais inválidas!");
        }
        
        return auth;
    }
}
