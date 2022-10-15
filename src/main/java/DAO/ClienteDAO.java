package DAO;

import DTO.Cliente;
import DTO.CompaniaSeguro;
import DataAccess.IDAOCliente;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ClienteDAO implements IDAOCliente {

    public ClienteDAO(){
        try {
            List<DTO.Cliente> clientes = obtenerClientes();
            if(clientes.isEmpty()) {
                Cliente cliente = new Cliente();
                cliente.set_nombre("Lionel Fizman");
                cliente.set_telefono("3323232323");
                CreateCliente(cliente);

                cliente = new Cliente();
                cliente.set_nombre("Hernan Cossu");
                cliente.set_telefono("5559595959");
                CreateCliente(cliente);
            }
        }
        catch (Exception ex) {}
    }

    public int CreateCliente(Cliente p) throws Exception {
        String sql = "insert into cliente (nombre, telefono, apellido, tipo_documento, documento) values (?, ?, ?, ?, ?)";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1 ,p.get_nombre());
        preparedStatement.setString(2 ,p.get_telefono());
        preparedStatement.setString(3 ,p.get_apellido());
        preparedStatement.setString(4 ,p.get_tipo_documento());
        preparedStatement.setString(5 ,p.get_documento());

        return preparedStatement.executeUpdate();
    }

    public List<Cliente> ReadClienteList() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery(
                "select id,nombre,telefono,apellido,tipo_documento,documento from cliente");

        List<Cliente> clienteList = new ArrayList<>();
        while(rs.next()) {
            Cliente cliente = new Cliente();
            cliente.set_id(rs.getInt("id"));
            cliente.set_nombre(rs.getString("nombre"));
            cliente.set_telefono(rs.getString("telefono"));
            cliente.set_apellido(rs.getString("apellido"));
            cliente.set_tipo_documento(rs.getString("tipo_documento"));
            cliente.set_documento(rs.getString("documento"));
            clienteList.add(cliente);
        }

        return clienteList;
    }


    public Cliente ReadCliente(Integer id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id,nombre,telefono,apellido,tipo_documento,documento from cliente where id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        Cliente cliente = new Cliente();
        cliente.set_id(rs.getInt("id"));
        cliente.set_nombre(rs.getString("nombre"));
        cliente.set_telefono(rs.getString("telefono"));
        cliente.set_apellido(rs.getString("apellido"));
        cliente.set_tipo_documento(rs.getString("tipo_documento"));
        cliente.set_documento(rs.getString("documento"));

        return cliente;
    }

    public Cliente ReadCliente(String nombre) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id,nombre,telefono,apellido,tipo_documento,documento from cliente where nombre = ?");
        preparedStatement.setString(1,nombre);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        Cliente cliente = new Cliente();
        cliente.set_id(rs.getInt("id"));
        cliente.set_nombre(rs.getString("nombre"));
        cliente.set_telefono(rs.getString("telefono"));
        cliente.set_apellido(rs.getString("apellido"));
        cliente.set_tipo_documento(rs.getString("tipo_documento"));
        cliente.set_documento(rs.getString("documento"));

        return cliente;
    }

    public int UpdateCliente(Cliente p) throws Exception {

        String sql = "update cliente set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,p.get_nombre());
        preparedStatement.setString(2 ,p.get_telefono());
        preparedStatement.setString(3 ,p.get_apellido());
        preparedStatement.setString(4 ,p.get_tipo_documento());
        preparedStatement.setString(5 ,p.get_documento());
        preparedStatement.setInt(6 ,p.get_id());


        return preparedStatement.executeUpdate();
    }


    public int UpdateCliente(String nombre, String telefono, String apellido, String tipo_documento, String documento, Integer id) throws Exception {

        String sql = "update cliente set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,nombre);
        preparedStatement.setString(2 ,telefono);
        preparedStatement.setString(3 ,apellido);
        preparedStatement.setString(4 ,tipo_documento);
        preparedStatement.setString(5 ,documento);
        preparedStatement.setInt(6 ,id);

        return preparedStatement.executeUpdate();

    }


    public int DeleteCliente(Integer id) throws Exception {

        String sql = "delete from cliente where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }

    public List<DTO.Cliente> obtenerClientes() {
        try {
            return ReadClienteList();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public DTO.Cliente obtenerCliente(String id) {
        try {
            return ReadCliente(Integer.valueOf(id));
        }
        catch (Exception ex) {
            return null;
        }
    }

    public DTO.Cliente obtenerClienteNombre(String nombre) {
        try {
            return ReadCliente(nombre);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
