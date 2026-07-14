package model.entities;
import model.enums.StatusProjeto;
import java.time.LocalDate;

public class Projeto extends Entidade {
    private String titulo;
    private int idCliente;
    private StatusProjeto status;
    private LocalDate dataPrevisaoConclusao;

    public Projeto(int id, String titulo, int idCliente, StatusProjeto status, LocalDate dataPrevisaoConclusao) {
        super(id);
        setTitulo(titulo);
        setIdCliente(idCliente);
        setStatus(status);
        setDataPrevisaoConclusao(dataPrevisaoConclusao);
    }
    public String getTitulo() { return titulo; }
    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) throw new IllegalArgumentException("Título vazio.");
        this.titulo = titulo;
    }
    public int getIdCliente() { return idCliente; }
    public void setIdCliente(int idCliente) {
        if (idCliente <= 0) throw new IllegalArgumentException("ID Cliente inválido.");
        this.idCliente = idCliente;
    }
    public StatusProjeto getStatus() { return status; }
    public void setStatus(StatusProjeto status) { this.status = status; }
    public LocalDate getDataPrevisaoConclusao() { return dataPrevisaoConclusao; }
    public void setDataPrevisaoConclusao(LocalDate dataPrevisaoConclusao) {
        if (dataPrevisaoConclusao == null) throw new IllegalArgumentException("Data inválida.");
        this.dataPrevisaoConclusao = dataPrevisaoConclusao;
    }
}