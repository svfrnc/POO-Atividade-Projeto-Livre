package model.entities;

import model.enums.TipoPerfil;

public class Cliente extends Usuario {
    private String cpf;
    private String telefone;

    public Cliente(int id, String login, String senha, TipoPerfil tipoPerfil, String cpf, String telefone) {
        super(id, login, senha, tipoPerfil);
        this.cpf = cpf;
        this.telefone = telefone;
    }

    public String getCpf() { return cpf; }
    public void setCpf(String cpf) { this.cpf = cpf; }
    public String getTelefone() { return telefone; }
    public void setTelefone(String telefone) { this.telefone = telefone; }
}