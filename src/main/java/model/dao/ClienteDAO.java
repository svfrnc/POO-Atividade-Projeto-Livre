package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Cliente;
import java.util.List;

public class ClienteDAO extends DAO<Cliente> {
    public ClienteDAO() { super("clientes.json", new TypeToken<List<Cliente>>(){}.getType()); }
}