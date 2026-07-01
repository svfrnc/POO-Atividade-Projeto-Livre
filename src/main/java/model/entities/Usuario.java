package model.entities;

import model.enums.TipoPerfil;

public abstract class Usuario extends Entidade {
    private String login;
    private String senha;
    private TipoPerfil tipoPerfil;

    public Usuario(int id, String login, String senha, TipoPerfil tipoPerfil) {
        super(id);
        this.login = login;
        this.senha = senha;
        this.tipoPerfil = tipoPerfil;
    }

    public String getLogin() { return login; }
    public void setLogin(String login) { this.login = login; }
    public String getSenha() { return senha; }
    public void setSenha(String senha) { this.senha = senha; }
    public TipoPerfil getTipoPerfil() { return tipoPerfil; }
    public void setTipoPerfil(TipoPerfil tipoPerfil) { this.tipoPerfil = tipoPerfil; }
}