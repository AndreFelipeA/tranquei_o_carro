package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controllers.Credenciais;

public class LoginScreen {
    public static boolean login() throws IOException {
        System.out.println("\nLogin");

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Digite seu e-mail!");
        String email = in.readLine().toString();
        System.out.println("Digite sua senha!");
        String senha = in.readLine().toString();

        boolean auth = Credenciais.verificaCredenciais(email, senha);
        if (auth) {
            System.out.println("Logado com sucesso!");
        } else {
            System.out.println("Credenciais inválidas!");
        }

        return auth;
    }
}
