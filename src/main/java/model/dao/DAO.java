package model.dao;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import model.entities.Entidade;
import java.io.*;
import java.lang.reflect.Type;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public abstract class DAO<T extends Entidade> {
    protected String arquivo;
    protected Gson gson;
    protected Type tipoLista;

    public DAO(String arquivo, Type tipoLista) {
        this.arquivo = arquivo;
        this.tipoLista = tipoLista;

        // 1. Pega o caminho do arquivo (ex: "data/servicos.json") e extrai a pasta "data"
        File file = new File(arquivo);
        File parentDir = file.getParentFile();

        // 2. Se houver uma pasta no caminho e ela ainda não existir no computador, cria automaticamente
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }

        // 3. Inicializa o Gson mantendo o suporte ao LocalDate
        this.gson = new GsonBuilder()
                .registerTypeAdapter(LocalDate.class, new LocalDateAdapter())
                .setPrettyPrinting()
                .create();
    }

    public void inserir(T obj) {
        List<T> lista = listar();
        int maxId = lista.stream().mapToInt(Entidade::getId).max().orElse(0);
        obj.setId(maxId + 1);
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
            throw new RuntimeException("Erro ao ler JSON: " + arquivo, e);
        }
    }

    public void atualizar(T obj) {
        List<T> lista = listar();
        for (int i = 0; i < lista.size(); i++) {
            if (lista.get(i).getId() == obj.getId()) {
                lista.set(i, obj);
                salvarArquivo(lista);
                return;
            }
        }
    }

    public void excluir(int id) {
        List<T> lista = listar();
        lista.removeIf(item -> item.getId() == id);
        salvarArquivo(lista);
    }

    private void salvarArquivo(List<T> lista) {
        try (Writer writer = new FileWriter(arquivo)) {
            gson.toJson(lista, writer);
        } catch (IOException e) {
            throw new RuntimeException("Erro ao salvar JSON", e);
        }
    }
}