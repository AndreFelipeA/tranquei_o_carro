import java.util.Scanner;

public class LoginScreen {
    public static void login() {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Digite 1 para login e 2 para se registrar!");

        System.out.println("Login");
        System.out.println("Digite seu e-mail!\n");
        String email = scanner.nextLine();
        System.out.println("Digite sua senha!\n");
        String senha = scanner.nextLine();

        Boolean auth = Credenciais.verificaCredenciais(email, senha);
        if (auth) {
            System.out.println("Logado com sucesso!");
        } else {
            System.out.println("Credenciais inválidas!");
        }

        scanner.close();
    }
}
