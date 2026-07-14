package model.entities;

public class Servico extends Entidade {
    private String descricao;
    private double valorMedida;
    public Servico(int id, String descricao, double valorMedida) {
        super(id);
        setDescricao(descricao);
        setValorMedida(valorMedida);
    }
    public String getDescricao() { return descricao; }
    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) throw new IllegalArgumentException("Descrição vazia.");
        this.descricao = descricao;
    }
    public double getValorMedida() { return valorMedida; }
    public void setValorMedida(double valorMedida) {
        if (valorMedida < 0) throw new IllegalArgumentException("Valor negativo.");
        this.valorMedida = valorMedida;
    }
}