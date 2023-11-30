package models;

public class InformacaoDoCarro {
  private String dataManutencao;
  private String descricao;
  private String situacao;

  public InformacaoDoCarro(String dataManutencao, String descricao, String situacao)
  {
    this.dataManutencao = dataManutencao;
    this.descricao = descricao;
    this.situacao = situacao;
  }

  public String getDataManutencao()
  {
    return this.dataManutencao;
  }

  public String getDecricao()
  {
    return this.descricao;
  }

  public String getSituacao()
  {
    return this.situacao;
  }

  public void setDataManutencao(String dataManutencao)
  {
    this.dataManutencao = dataManutencao;
  }

  public void setDescricao(String descricao)
  {
    this.descricao = descricao;
  }

  public void setSituacao(String situacao)
  {
    this.situacao = situacao;
  }


  public String obterInfo() {       
    return "Informações\n" + "DataManutenção: " + dataManutencao + "\n" + "Descrição: " + descricao + "\n" + "Situação: " + situacao;
  }

  public void modificarInfo(String dataManutencao, String descricao, String situacao) {
    this.dataManutencao = dataManutencao;
    this.descricao = descricao;
    this.situacao = situacao;
  }
}