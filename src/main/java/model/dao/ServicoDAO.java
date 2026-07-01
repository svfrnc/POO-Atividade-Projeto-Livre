package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Servico;
import java.util.ArrayList;

public class ServicoDAO extends DAO<Servico> {
    public ServicoDAO() {
        super("src/main/java/data/servicos.json", new TypeToken<ArrayList<Servico>>(){}.getType());
    }
}