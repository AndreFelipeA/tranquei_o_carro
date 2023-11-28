package controllers; // Update the package declaration

import models.Usuario;
import db.BancoDeDados;
import models.Carro;

public class Credenciais {
    public static boolean verificaCredenciais(String email, String senha)
    {
        BancoDeDados db = BancoDeDados.getInstance();

        Usuario user = db.buscarUsuarioPorEmail(email);
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
