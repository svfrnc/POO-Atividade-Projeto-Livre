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

    public int getIdEtapa() {
        return idEtapa;
    }

    public void setIdEtapa(int idEtapa) {
        if (idEtapa <= 0) {
            throw new IllegalArgumentException("O ID da etapa deve ser maior que zero.");
        }
        this.idEtapa = idEtapa;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        if (nomeEmpresa == null || nomeEmpresa.trim().isEmpty()) {
            throw new IllegalArgumentException("O nome da empresa/empreiteiro não pode ser vazio.");
        }
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getCnpj() {
        return cnpj;
    }

    public void setCnpj(String cnpj) {
        if (cnpj == null || cnpj.trim().isEmpty()) {
            throw new IllegalArgumentException("O CNPJ não pode ser vazio.");
        }
        this.cnpj = cnpj;
    }
}