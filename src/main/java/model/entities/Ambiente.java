package model.entities;

public class Ambiente extends Entidade {
    private String nome;
    private double metragem;
    private int idProjeto;

    public Ambiente(int id, String nome, double metragem, int idProjeto) {
        super(id);
        this.nome = nome;
        this.metragem = metragem;
        this.idProjeto = idProjeto;
    }

    public String getNome() { return nome; }
    public void setNome(String nome) { this.nome = nome; }
    public double getMetragem() { return metragem; }
    public void setMetragem(double metragem) { this.metragem = metragem; }
    public int getIdProjeto() { return idProjeto; }
    public void setIdProjeto(int idProjeto) { this.idProjeto = idProjeto; }
}