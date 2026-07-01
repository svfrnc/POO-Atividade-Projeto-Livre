package model.dao;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.entities.Entidade;

import java.io.*;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T extends Entidade> {
    protected String arquivo;
    protected Gson gson;
    protected Type tipoLista;

    public DAO(String arquivo, Type tipoLista) {
        this.arquivo = arquivo;
        this.tipoLista = tipoLista;
        this.gson = new GsonBuilder().setPrettyPrinting().create();
    }

    public void inserir(T obj) {
        List<T> lista = listar();
        lista.add(obj);
        salvarArquivo(lista);
    }

    public List<T> listar() {
        try (Reader reader = new FileReader(arquivo)) {
            List<T> lista = gson.fromJson(reader, tipoLista);
            return lista != null ? lista : new ArrayList<>();
        } catch (FileNotFoundException e) {
            return new ArrayList<>();
        } catch (IOException e) {
            System.err.println("Erro ao ler JSON: " + e.getMessage());
            return new ArrayList<>();
        }
    }

    public void atualizar(T obj) {
        List<T> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == obj.getId()) {
                lista.set(i, obj);
                break;
            }
        }
        salvarArquivo(lista);
    }

    public void excluir(int id) {
        List<T> lista = listar();
        lista.removeIf(item -> item.getId() == id);
        salvarArquivo(lista);
    }

    protected void salvarArquivo(List<T> lista) {
        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            System.err.println("Erro ao salvar JSON: " + e.getMessage());
        }
    }
}