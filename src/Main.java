import java.sql.Connection;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
    	Scanner scanner = new Scanner(System.in);
        System.out.println("Digite 1 para login e 2 para se registrar!");
        String choice = scanner.nextLine();
        Integer c = Integer.parseInt(choice);
        Usuario user;
        BancoDeDados db = BancoDeDados.getInstance();
        if(c.equals(1))
        {
            System.out.println("Login");
            System.out.println("Digite seu e-mail!\n");
            String email = scanner.nextLine();
            System.out.println("Digite sua senha!\n");
            String senha = scanner.nextLine();
            user = db.buscarUsuarioPorEmail(email.toString());
            if(senha.equals(user.getSenha()));
            {
                System.out.println("Autenticado");
            }
        }
        else
        {
            System.out.println("Registrar");
            System.out.println("Digite seu nome!\n");
            String nome = scanner.nextLine();
            System.out.println("Digite seu e-mail!\n");
            String email = scanner.nextLine();
            System.out.println("Digite sua senha!\n");
            String senha = scanner.nextLine();
            db.salvarUsuario(nome, email, senha);
            user = new Usuario(nome, email, senha);

        }


        try {
            db.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
        scanner.close();


    }
}
