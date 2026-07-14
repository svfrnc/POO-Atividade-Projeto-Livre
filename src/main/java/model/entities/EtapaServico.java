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

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        if (idEtapa <= 0) {
            throw new IllegalArgumentException("O ID da etapa deve ser maior que zero.");
        }
        this.idEtapa = idEtapa;
    }

    public int getIdServico() {
        return idServico;
    }

    public void setIdServico(int idServico) {
        if (idServico <= 0) {
            throw new IllegalArgumentException("O ID do serviço deve ser maior que zero.");
        }
        this.idServico = idServico;
    }

    public double getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(double quantidade) {
        if (quantidade <= 0) {
            throw new IllegalArgumentException("A quantidade do serviço na etapa não pode ser zero ou negativa.");
        }
        this.quantidade = quantidade;
    }
}