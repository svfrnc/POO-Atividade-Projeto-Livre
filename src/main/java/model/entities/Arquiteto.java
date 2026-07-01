package model.entities;

import model.enums.TipoPerfil;

public class Arquiteto extends Usuario {
    private String cau;

    public Arquiteto(int id, String login, String senha, TipoPerfil tipoPerfil, String cau) {
        super(id, login, senha, tipoPerfil);
        this.cau = cau;
    }

    public String getCau() { return cau; }
    public void setCau(String cau) { this.cau = cau; }
}