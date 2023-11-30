package controllers; // Update the package declaration

import models.Usuario;
import models.Carro;
import dao.UsuarioDao;

public class Credenciais {
    private static Usuario usuarioLogado;

    public static boolean verificaCredenciais(String email, String senha) {

        Usuario user = UsuarioDao.buscarUsuarioPorEmail(email);
        if (user == null) {
            return false;
        }
        
        System.out.println(user.getSenha());
        if (senha.equals(user.getSenha())) {
            usuarioLogado = user;
            return true;
        }

        return false;
    }

    public static Usuario getUsuarioLogado() {
        return usuarioLogado;
    }

    public boolean verificaCredenciaisC(Carro carro) {
        return true;
    }

    public String gerarCredenciais() {
        return "teste";
    }
}
