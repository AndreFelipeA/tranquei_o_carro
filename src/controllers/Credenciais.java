package controllers; // Update the package declaration

import models.Usuario;
import models.Carro;

public class Credenciais {
    public static Usuario verificaCredenciais(String email, String senha)
    {
        BancoDeDados db = BancoDeDados.getInstance();

        Usuario user = db.buscarUsuarioPorEmail(email);
        if (senha.equals(user.getSenha())) {
            return user;
        }

        return null;
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
