package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Servico;
import java.util.List;

public class ServicoDAO extends DAO<Servico> {
    public ServicoDAO() { super("servicos.json", new TypeToken<List<Servico>>(){}.getType()); }
}