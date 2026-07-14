package model.entities;

import model.enums.TipoPerfil;

public abstract class Usuario extends Entidade {
    protected String login;
    protected String senha;
    protected TipoPerfil tipoPerfil;

    public Usuario(int id, String login, String senha, TipoPerfil tipoPerfil) {
        super(id);
        setLogin(login);
        setSenha(senha);
        setTipoPerfil(tipoPerfil);
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        if (login == null || login.trim().isEmpty()) {
            throw new IllegalArgumentException("O login não pode estar vazio.");
        }
        this.login = login;
    }

    public String getSenha() {
        return senha;
    }

    public void setSenha(String senha) {
        if (senha == null || senha.trim().length() < 3) {
            throw new IllegalArgumentException("A senha deve ter pelo menos 3 caracteres.");
        }
        this.senha = senha;
    }

    public TipoPerfil getTipoPerfil() {
        return tipoPerfil;
    }

    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        if (tipoPerfil == null) {
            throw new IllegalArgumentException("O tipo de perfil não pode ser nulo.");
        }
        this.tipoPerfil = tipoPerfil;
    }
}