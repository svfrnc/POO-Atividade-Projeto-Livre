package model.dao;
import com.google.gson.reflect.TypeToken;
import model.entities.Cliente;
import java.util.ArrayList;

public class ClienteDAO extends DAO<Cliente> {
    public ClienteDAO() {
        super("src/main/java/data/clientes.json", new TypeToken<ArrayList<Cliente>>(){}.getType());
    }
}