package model.entities;

public class Material extends Entidade {
    private int idEtapa;
    private String descricao;
    private double quantidade;
    private String unidade;
    public Material(int id, int idEtapa, String descricao, double quantidade, String unidade) {
        super(id);
        setIdEtapa(idEtapa);
        setDescricao(descricao);
        setQuantidade(quantidade);
        setUnidade(unidade);
    }
    public int getIdEtapa() { return idEtapa; }
    public void setIdEtapa(int idEtapa) { this.idEtapa = idEtapa; }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public double getQuantidade() { return quantidade; }
    public void setQuantidade(double quantidade) { this.quantidade = quantidade; }
    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }
}