package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Etapa;
import java.util.List;

public class EtapaDAO extends DAO<Etapa> {
    public EtapaDAO() { super("etapas.json", new TypeToken<List<Etapa>>(){}.getType()); }
}