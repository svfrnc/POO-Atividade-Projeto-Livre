package model.entities;

public class Material extends Entidade {
    private String descricao;
    private int quantidade;
    private String unidade;
    private int idAmbiente;

    public Material(int id, String descricao, int quantidade, String unidade, int idAmbiente) {
        super(id);
        this.descricao = descricao;
        this.quantidade = quantidade;
        this.unidade = unidade;
        this.idAmbiente = idAmbiente;
    }

    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) { this.descricao = descricao; }
    public int getQuantidade() { return quantidade; }
    public void setQuantidade(int quantidade) { this.quantidade = quantidade; }
    public String getUnidade() { return unidade; }
    public void setUnidade(String unidade) { this.unidade = unidade; }
    public int getIdAmbiente() { return idAmbiente; }
    public void setIdAmbiente(int idAmbiente) { this.idAmbiente = idAmbiente; }
}