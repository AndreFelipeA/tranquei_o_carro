package models;

import java.util.ArrayList;

public class Usuario {
    private String nome;
    private String email;
    private String senha;
    private ArrayList<Carro> carros;
    private int id_usuario;

    public Usuario(String nome, String email, String senha) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.carros = new ArrayList<Carro>();
    }

    public Usuario(String nome, String email, String senha, int id_usuario) {
        this.nome = nome;
        this.email = email;
        this.senha = senha;
        this.carros = new ArrayList<Carro>();
        this.id_usuario = id_usuario;
    }

    public int getId() {
        return id_usuario;
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

    public void adicionarCarro(Carro carro) {
        this.carros.add(carro);
    }

    public void removerCarro(Carro carro) {
        this.carros.remove(carro);
    }

    public ArrayList<Carro> getCarros() {
        return this.carros;
    }

    public String getSenha() {
        return senha;
    }

    public void deleteCar(Carro car)
    {
        carros.remove(car);
    }

}
