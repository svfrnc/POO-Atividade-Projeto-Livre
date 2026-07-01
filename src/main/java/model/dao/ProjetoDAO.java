package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Projeto;
import java.util.ArrayList;

public class ProjetoDAO extends DAO<Projeto> {
    public ProjetoDAO() {
        super("src/main/java/data/projetos.json", new TypeToken<ArrayList<Projeto>>(){}.getType());
    }
}