package views;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import dao.UsuarioDao;

public class RegisterScreen {
    public static void register() throws IOException {

        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Registrar");
        System.out.println("Digite seu nome!\n");
        String nome = in.readLine().toString();
        System.out.println("Digite seu e-mail!\n");
        String email = in.readLine().toString();
        System.out.println("Digite sua senha!\n");
        String senha = in.readLine().toString();

        UsuarioDao.salvarUsuario(nome, email, senha);
    }
}
