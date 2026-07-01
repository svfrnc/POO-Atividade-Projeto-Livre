package model.entities;

public class Empreiteiro extends Entidade {
    private String nomeEmpresa;
    private String cnpj;

    public Empreiteiro(int id, String nomeEmpresa, String cnpj) {
        super(id);
        this.nomeEmpresa = nomeEmpresa;
        this.cnpj = cnpj;
    }

    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}