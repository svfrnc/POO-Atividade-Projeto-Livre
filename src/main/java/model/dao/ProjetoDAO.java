package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Projeto;
import java.util.List;

public class ProjetoDAO extends DAO<Projeto> {
    public ProjetoDAO() { super("projetos.json", new TypeToken<List<Projeto>>(){}.getType()); }
}