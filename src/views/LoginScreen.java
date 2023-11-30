package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import controllers.Credenciais;
import dao.UsuarioDao;

public class LoginScreen {

    public static boolean display() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));

        Boolean exit = false;
        Boolean auth = false;
        while (!exit) {
            System.out.println("Digite 0 para Login, 1 para Registrar ou -1 para Sair.");

            Integer cmd = 0;
            String choice = in.readLine();
            cmd = Integer.parseInt(choice);

            switch (cmd) {
                case 0:
                    auth = LoginScreen.login();
                    exit = auth;
                    break;

                case 1:
                    LoginScreen.register();
                    break;

                case -1:
                    exit = true;
                    auth = false;
                    break;

                default:
                    System.out.println("Comando inválido!");
                    break;
            }
        }

        return auth;
    }

    private static boolean login() throws IOException {
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

    private static void register() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("\nRegistrar");
        System.out.println("Digite seu nome!");
        String nome = in.readLine().toString();
        System.out.println("Digite seu e-mail!");
        String email = in.readLine().toString();
        System.out.println("Digite sua senha!");
        String senha = in.readLine().toString();
        System.out.println();

        UsuarioDao.salvarUsuario(nome, email, senha);
        System.out.println("Usuário registrado com sucesso!");
    }
}
