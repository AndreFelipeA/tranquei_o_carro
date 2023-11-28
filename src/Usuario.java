import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private ArrayList<Carro> carros;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void adicionarCarro(Carro carro)
    {
        this.carros.add(carro);
    }

    public void removerCarro(Carro carro)
    {
        this.carros.remove(carro);
    }
    

    public void obterCarros()
    {
        for (Carro carro : this.carros)
        {
            System.out.print(carro);
        }
    }

    public String getSenha()
    {
        return senha;
    }

}
