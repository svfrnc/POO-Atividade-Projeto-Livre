package model.entities;

public class EtapaServico extends Entidade {
    private int idEtapa;
    private int idServico;
    private double quantidade;
    public EtapaServico(int id, int idEtapa, int idServico, double quantidade) {
        super(id);
        setIdEtapa(idEtapa);
        setIdServico(idServico);
        setQuantidade(quantidade);
    }
    public int getIdEtapa() { return idEtapa; }
    public void setIdEtapa(int idEtapa) { this.idEtapa = idEtapa; }
    public int getIdServico() { return idServico; }
    public void setIdServico(int idServico) { this.idServico = idServico; }
    public double getQuantidade() { return quantidade; }
    public void setQuantidade(double quantidade) {
        if (quantidade <= 0) throw new IllegalArgumentException("Quantidade deve ser maior que zero.");
        this.quantidade = quantidade;
    }
}