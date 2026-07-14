package model.entities;

public class Empreiteiro extends Entidade {
    private int idEtapa;
    private String nomeEmpresa;
    private String cnpj;
    public Empreiteiro(int id, int idEtapa, String nomeEmpresa, String cnpj) {
        super(id);
        setIdEtapa(idEtapa);
        setNomeEmpresa(nomeEmpresa);
        setCnpj(cnpj);
    }
    public int getIdEtapa() { return idEtapa; }
    public void setIdEtapa(int idEtapa) { this.idEtapa = idEtapa; }
    public String getNomeEmpresa() { return nomeEmpresa; }
    public void setNomeEmpresa(String nomeEmpresa) { this.nomeEmpresa = nomeEmpresa; }
    public String getCnpj() { return cnpj; }
    public void setCnpj(String cnpj) { this.cnpj = cnpj; }
}