package views;

import java.util.Scanner;

import dao.UsuarioDao;

public class RegisterScreen {
    public static void register() {
        Scanner sc = new Scanner(System.in);

        System.out.println("Registrar");
        System.out.println("Digite seu nome!\n");
        String nome = sc.nextLine();
        System.out.println("Digite seu e-mail!\n");
        String email = sc.nextLine();
        System.out.println("Digite sua senha!\n");
        String senha = sc.nextLine();

        UsuarioDao.salvarUsuario(nome, email, senha);
    }
}
