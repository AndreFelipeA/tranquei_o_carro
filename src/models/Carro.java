package models;

import java.util.Optional;

import controllers.ControlaTrancamento;

public class Carro {
    private String marca;
    private String modelo;
    private Integer ano;
    private Integer idCarro;
    private ControlaTrancamento controlaTrancamento;
    private VerificaLocalizacao verificaLocalizacao;
    public Carro(String marca, String modelo, Integer ano, Integer idCarro, Optional<VerificaLocalizacao> verificaLocalizacao) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.idCarro = idCarro;

        this.controlaTrancamento = new ControlaTrancamento(false);

        this.verificaLocalizacao = verificaLocalizacao.orElse(null);
        

    }
    public Carro(String marca, String modelo, Integer ano, Integer idCarro, VerificaLocalizacao verificaLocalizacao) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;
        this.idCarro = idCarro;

        this.controlaTrancamento = new ControlaTrancamento(false);

        this.verificaLocalizacao = verificaLocalizacao;
        

    }

    public Carro(String marca, String modelo, int ano,Optional<VerificaLocalizacao> verificaLocalizacao) {
        this.marca = marca;
        this.modelo = modelo;
        this.ano = ano;

        this.controlaTrancamento = new ControlaTrancamento(false);
        this.verificaLocalizacao = verificaLocalizacao.orElse(null);
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

    public Integer getIdCarro() {
        return idCarro;
    }

    public void setIdCarro(Integer idCarro) {
        this.idCarro = idCarro;
    }

    public ControlaTrancamento getControlaTrancamento() {
        return controlaTrancamento;
    }

    @Override
    public String toString() {
        return "Carro{" +
                "marca='" + marca + '\'' +
                ", modelo='" + modelo + '\'' +
                ", ano=" + ano +
                ", idCarro=" + idCarro +
                '}';
    }

    public VerificaLocalizacao getVerificaLocalizacao()
    {
        return verificaLocalizacao;
    }
    
}
