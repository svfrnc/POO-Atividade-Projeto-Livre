package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Ambiente;
import java.util.ArrayList;

public class AmbienteDAO extends DAO<Ambiente> {
    public AmbienteDAO() {
        super("src/main/java/data/ambientes.json", new TypeToken<ArrayList<Ambiente>>(){}.getType());
    }
}