package model.entities;
import model.enums.TipoPerfil;

public class Cliente extends Usuario {
    private String nome;
    public Cliente(int id, String login, String senha, String nome) {
        super(id, login, senha, TipoPerfil.COMUM);
        setNome(nome);
    }
    public String getNome() { return nome; }
    public void setNome(String nome) {
        if (nome == null || nome.trim().isEmpty()) throw new IllegalArgumentException("Nome inválido.");
        this.nome = nome;
    }
}