package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Empreiteiro;
import java.util.List;

public class EmpreiteiroDAO extends DAO<Empreiteiro> {
    public EmpreiteiroDAO() { super("empreiteiros.json", new TypeToken<List<Empreiteiro>>(){}.getType()); }
}