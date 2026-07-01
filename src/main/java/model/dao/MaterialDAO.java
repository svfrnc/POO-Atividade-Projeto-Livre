package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Material;
import java.util.ArrayList;

public class MaterialDAO extends DAO<Material> {
    public MaterialDAO() {
        super("src/main/java/data/materiais.json", new TypeToken<ArrayList<Material>>(){}.getType());
    }
}