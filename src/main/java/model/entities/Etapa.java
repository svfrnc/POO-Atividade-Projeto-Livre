package model.entities;

import java.time.LocalDate;

public class Etapa extends Entidade {
    private int idProjeto;
    private String descricao;
    private LocalDate dataLimite;

    public Etapa(int id, int idProjeto, String descricao, LocalDate dataLimite) {
        super(id);
        setIdProjeto(idProjeto);
        setDescricao(descricao);
        setDataLimite(dataLimite);
    }

    public int getIdProjeto() {
        return idProjeto;
    }

    public void setIdProjeto(int idProjeto) {
        if (idProjeto <= 0) {
            throw new IllegalArgumentException("O ID do projeto vinculado deve ser maior que zero.");
        }
        this.idProjeto = idProjeto;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        if (descricao == null || descricao.trim().isEmpty()) {
            throw new IllegalArgumentException("A descrição da etapa não pode ser vazia.");
        }
        this.descricao = descricao;
    }

    public LocalDate getDataLimite() {
        return dataLimite;
    }

    public void setDataLimite(LocalDate dataLimite) {
        if (dataLimite == null) {
            throw new IllegalArgumentException("A data limite da etapa não pode ser nula.");
        }
        this.dataLimite = dataLimite;
    }
}