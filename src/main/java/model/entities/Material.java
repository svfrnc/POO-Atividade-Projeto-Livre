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

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        if (idEtapa <= 0) {
            throw new IllegalArgumentException("O ID da etapa deve ser maior que zero.");
        }
        this.idEtapa = idEtapa;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição do material não pode ser vazia.");
        }
        this.descricao = descricao;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade de material deve ser maior que zero.");
        }
        this.quantidade = quantidade;
    }

    public String getUnidade() {
        return unidade;
    }

    public void setUnidade(String unidade) {
        if (unidade == null || unidade.trim().isEmpty()) {
            throw new IllegalArgumentException("A unidade de medida não pode ser vazia.");
        }
        this.unidade = unidade;
    }
}