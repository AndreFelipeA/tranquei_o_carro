
public class Credenciais {
    public static boolean verificaCredenciais(String email, String senha)
    {
        BancoDeDados db = BancoDeDados.getInstance();
        Usuario user = db.buscarUsuarioPorEmail(email.toString());
        if (senha.equals(user.getSenha())) {
            return true;
        }

        return false;
    }

    public boolean verificaCredenciaisC(Carro carro)
    {
        return true;
    }

    public String gerarCredenciais()
    {
        return "teste";
    }
}
