package model.entities;
import model.enums.TipoPerfil;

public abstract class Usuario extends Entidade {
    private String login;
    private String senha;
    private TipoPerfil tipoPerfil;

    public Usuario(int id, String login, String senha, TipoPerfil tipoPerfil) {
        super(id);
        setLogin(login);
        setSenha(senha);
        setTipoPerfil(tipoPerfil);
    }
    public String getLogin() { return login; }
    public void setLogin(String login) {
        if (login == null || login.trim().isEmpty()) throw new IllegalArgumentException("Login inválido.");
        this.login = login;
    }
    public String getSenha() { return senha; }
    public void setSenha(String senha) {
        if (senha == null || senha.trim().isEmpty()) throw new IllegalArgumentException("Senha inválida.");
        this.senha = senha;
    }
    public TipoPerfil getTipoPerfil() { return tipoPerfil; }
    public void setTipoPerfil(TipoPerfil tipoPerfil) {
        if (tipoPerfil == null) throw new IllegalArgumentException("Perfil nulo.");
        this.tipoPerfil = tipoPerfil;
    }
}