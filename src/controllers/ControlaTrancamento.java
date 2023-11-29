package controllers;

public class ControlaTrancamento {

    private boolean trancado;

    public ControlaTrancamento(boolean trancado) {
        this.trancado = trancado;
    }

    public boolean getTrancado() {
        return this.trancado;
    }

    public void switchStatus() {
        this.trancado = !this.trancado;
        String status = this.trancado ? "trancado" : "destrancado";
        System.out.println("Carro " + status + "\n");
    }

}