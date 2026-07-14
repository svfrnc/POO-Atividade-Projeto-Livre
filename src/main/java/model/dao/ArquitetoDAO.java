package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Arquiteto;
import java.util.List;

public class ArquitetoDAO extends DAO<Arquiteto> {
    public ArquitetoDAO() { super("data/arquitetos.json", new TypeToken<List<Arquiteto>>(){}.getType()); }
}