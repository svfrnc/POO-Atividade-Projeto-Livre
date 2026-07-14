package model.entities;

import model.enums.TipoPerfil;

public class Arquiteto extends Usuario {
    private String cau;

    public Arquiteto(int id, String login, String senha, String cau) {
        super(id, login, senha, TipoPerfil.ADMIN);
        setCau(cau);
    }

    public String getCau() {
        return cau;
    }

    public void setCau(String cau) {
        if (cau == null || cau.trim().isEmpty()) {
            throw new IllegalArgumentException("O número do CAU não pode estar vazio.");
        }
        this.cau = cau;
    }
}