package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Empreiteiro;
import java.util.ArrayList;

public class EmpreiteiroDAO extends DAO<Empreiteiro> {
    public EmpreiteiroDAO() {
        super("src/main/java/data/empreiteiros.json", new TypeToken<ArrayList<Empreiteiro>>(){}.getType());
    }
}