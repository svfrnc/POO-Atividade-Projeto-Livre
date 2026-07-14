package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Material;
import java.util.List;

public class MaterialDAO extends DAO<Material> {
    public MaterialDAO() { super("materiais.json", new TypeToken<List<Material>>(){}.getType()); }
}