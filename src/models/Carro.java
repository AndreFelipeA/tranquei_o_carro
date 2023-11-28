package models;

public class Carro {
    private String marca;
    private String modelo;
    private Integer ano;
    private Usuario proprietario;
    private Integer idCarro;

    public Carro(String marca, String modelo, Integer ano, Usuario proprietario, Integer idCarro) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.proprietario = proprietario;
        this.idCarro = idCarro;
    }

    public Carro(String marca, String modelo, int ano, Usuario proprietario) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.proprietario = proprietario;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public Integer getAno() {
        return ano;
    }

    public void setAno(Integer ano) {
        this.ano = ano;
    }

    public Usuario getProprietario() {
        return proprietario;
    }

    public void setProprietario(Usuario proprietario) {
        this.proprietario = proprietario;
    }

    public Integer getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(Integer idCarro) {
        this.idCarro = idCarro;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", proprietario=" + proprietario +
                ", idCarro=" + idCarro +
                '}';
    }
}
