package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.EtapaServico;
import java.util.List;

public class EtapaServicoDAO extends DAO<EtapaServico> {
    public EtapaServicoDAO() { super("etapas_servicos.json", new TypeToken<List<EtapaServico>>(){}.getType()); }
}