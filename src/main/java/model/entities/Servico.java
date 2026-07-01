package model.entities;

import model.enums.StatusServico;

public class Servico extends Entidade {
    private String tipo;
    private double valor;
    private StatusServico statusExecucao;

    public Servico(int id, String tipo, double valor, StatusServico statusExecucao) {
        super(id);
        this.tipo = tipo;
        this.valor = valor;
        this.statusExecucao = statusExecucao;
    }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }
    public double getValor() { return valor; }
    public void setValor(double valor) { this.valor = valor; }
    public StatusServico getStatusExecucao() { return statusExecucao; }
    public void setStatusExecucao(StatusServico statusExecucao) { this.statusExecucao = statusExecucao; }
}