package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Arquiteto;
import java.util.ArrayList;

public class ArquitetoDAO extends DAO<Arquiteto> {
    public ArquitetoDAO() {
        super("src/main/java/data/arquitetos.json", new TypeToken<ArrayList<Arquiteto>>(){}.getType());
    }
}