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

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        if (titulo == null || titulo.trim().isEmpty()) {
            throw new IllegalArgumentException("O título do projeto não pode ser vazio.");
        }
        this.titulo = titulo;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        if (idCliente <= 0) {
            throw new IllegalArgumentException("O ID do cliente vinculado deve ser maior que zero.");
        }
        this.idCliente = idCliente;
    }

    public StatusProjeto getStatus() {
        return status;
    }

    public void setStatus(StatusProjeto status) {
        if (status == null) {
            throw new IllegalArgumentException("O status do projeto não pode ser nulo.");
        }
        this.status = status;
    }

    public LocalDate getDataPrevisaoConclusao() {
        return dataPrevisaoConclusao;
    }

    public void setDataPrevisaoConclusao(LocalDate dataPrevisaoConclusao) {
        if (dataPrevisaoConclusao == null) {
            throw new IllegalArgumentException("A data de previsão não pode ser nula.");
        }
        this.dataPrevisaoConclusao = dataPrevisaoConclusao;
    }
}