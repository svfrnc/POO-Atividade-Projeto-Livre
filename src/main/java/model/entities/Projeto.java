package model.entities;

import model.enums.StatusProjeto;

public class Projeto extends Entidade {
    private String nome;
    private StatusProjeto status;
    private int idCliente;

    public Projeto(int id, String nome, StatusProjeto status, int idCliente) {
        super(id);
        this.nome = nome;
        this.status = status;
        this.idCliente = idCliente;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public StatusProjeto getStatus() { return status; }
    public void setStatus(StatusProjeto status) { this.status = status; }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) { this.idCliente = idCliente; }

    @Override
    public String toString() {
        return "Projeto [ID=" + getId() + ", Nome=" + nome + ", Status=" + status + ", ID Cliente=" + idCliente + "]";
    }
}