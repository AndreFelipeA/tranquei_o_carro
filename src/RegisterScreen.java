import java.util.Scanner;

public class RegisterScreen {
    public static void register() {
        Scanner scanner = new Scanner(System.in);
        BancoDeDados db = BancoDeDados.getInstance();

        System.out.println("Registrar");
        System.out.println("Digite seu nome!\n");
        String nome = scanner.nextLine();
        System.out.println("Digite seu e-mail!\n");
        String email = scanner.nextLine();
        System.out.println("Digite sua senha!\n");
        String senha = scanner.nextLine();
        db.salvarUsuario(nome, email, senha);
        
        scanner.close();
    }
}
