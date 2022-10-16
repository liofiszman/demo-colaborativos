package DAO;

import DTO.Cliente;
import DTO.CompaniaSeguro;
import DTO.Vehiculo;
import DataAccess.IDAOVehiculo;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDAO implements IDAOVehiculo {
    CompaniaSegurosDAO companiaSeguros = new CompaniaSegurosDAO();
    ClienteDAO clientes = new ClienteDAO();

    public int CreateVehiculo(Vehiculo p) throws Exception {
        String sql = "insert into vehiculo (id, compania_seguro_id, cliente_id, numero_poliza, marca, patente) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1 ,p.get_id());
        preparedStatement.setInt(2 ,p.get_compania_seguro_id());
        preparedStatement.setInt(3 ,p.get_cliente_id());
        preparedStatement.setString(4 ,p.get_numero_poliza());
        preparedStatement.setString(5 ,p.get_marca());
        preparedStatement.setString(6 ,p.get_patente());
        return preparedStatement.executeUpdate();
    }

    public int CreateVehiculo(Integer id, Integer compania_seguro_id, Integer cliente_id, String numero_poliza, String marca, String patente) throws Exception {
        String sql = "insert into vehiculo (id, compania_seguro_id, cliente_id, numero_poliza, marca, patente) values (?, ?, ?, ?, ?, ?)";
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setInt(1 ,id);
        preparedStatement.setInt(2 ,compania_seguro_id);
        preparedStatement.setInt(3 ,cliente_id);
        preparedStatement.setString(4 ,numero_poliza);
        preparedStatement.setString(5 ,marca);
        preparedStatement.setString(6 ,patente);
        return preparedStatement.executeUpdate();
    }

    public List<Vehiculo> ReadVehiculoList() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from vehiculo");
        List<Vehiculo> vehiculoList = new ArrayList<>();
        while(rs.next()) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.set_id(rs.getInt("Id"));
            vehiculo.set_compania_seguro_id(rs.getInt("compania_seguro_id"));
            vehiculo.set_cliente_id(rs.getInt("cliente_id"));
            vehiculo.set_numero_poliza(rs.getString("numero_poliza"));
            vehiculo.set_marca(rs.getString("marca"));
            vehiculo.set_patente(rs.getString("patente"));
            vehiculoList.add(vehiculo);
        }
        return vehiculoList;
    }


    public Vehiculo ReadVehiculo(Integer id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement("select * from vehiculo where id=?");
        preparedStatement.setInt(1,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.set_id (rs.getInt("id"));
        vehiculo.set_compania_seguro_id(rs.getInt("compania_seguro_id"));
        vehiculo.set_cliente_id(rs.getInt("cliente_id"));
        vehiculo.set_numero_poliza(rs.getString("numero_poliza"));
        vehiculo.set_marca(rs.getString("marca"));
        vehiculo.set_patente(rs.getString("patente"));
        return vehiculo;
    }


    public int UpdateVehiculo(Vehiculo p) throws Exception {
        String sql = "update vehiculo set compania_seguro_id=?, cliente_id=?, numero_poliza=?, marca=?, patente=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,p.get_compania_seguro_id());
        preparedStatement.setInt(2,p.get_cliente_id());
        preparedStatement.setString(3,p.get_numero_poliza());
        preparedStatement.setString(4,p.get_marca());
        preparedStatement.setString(5,p.get_patente());
        preparedStatement.setInt(6,p.get_id());
        return preparedStatement.executeUpdate();
    }

    public int UpdateVehiculo(Integer compania_seguro_id, Integer cliente_id, String numero_poliza, String marca, String patente, Integer id) throws Exception {
        String sql = "update vehiculo set compania_seguro_id=?, cliente_id=?, numero_poliza=?, marca=?, patente=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,compania_seguro_id);
        preparedStatement.setInt(2,cliente_id);
        preparedStatement.setString(3,numero_poliza);
        preparedStatement.setString(4,marca);
        preparedStatement.setString(5,patente);
        preparedStatement.setInt(5,id);
        return preparedStatement.executeUpdate();
    }



    public int DeleteVehiculo(Integer id) throws Exception {

        String sql = "delete from vehiculo where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }

    public List<Vehiculo> GetVehiculoByCliente(Integer cliente_id) throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from vehiculo where cliente_id=?");
        List<Vehiculo> vehiculoList = new ArrayList<>();
        while(rs.next()) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.set_id(rs.getInt("Id"));
            vehiculo.set_compania_seguro_id(rs.getInt("compania_seguro_id"));
            vehiculo.set_cliente_id(rs.getInt("cliente_id"));
            vehiculo.set_numero_poliza(rs.getString("numero_poliza"));
            vehiculo.set_marca(rs.getString("marca"));
            vehiculo.set_patente(rs.getString("patente"));
            vehiculoList.add(vehiculo);
        }
        return vehiculoList;
    }

    public List<Vehiculo> GetVehiculoByCompaniaSeguro(Integer compania_seguro_id) throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from vehiculo where compania_seguro_id=?");
        List<Vehiculo> vehiculoList = new ArrayList<>();
        while(rs.next()) {
            Vehiculo vehiculo = new Vehiculo();
            vehiculo.set_id(rs.getInt("Id"));
            vehiculo.set_compania_seguro_id(rs.getInt("compania_seguro_id"));
            vehiculo.set_cliente_id(rs.getInt("cliente_id"));
            vehiculo.set_numero_poliza(rs.getString("numero_poliza"));
            vehiculo.set_marca(rs.getString("marca"));
            vehiculo.set_patente(rs.getString("patente"));
            vehiculoList.add(vehiculo);
        }
        return vehiculoList;
    }

    public Vehiculo GetVehiculoByPatente(String patente) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select * from vehiculo where patente=?");
        preparedStatement.setString(1,patente);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        Vehiculo vehiculo = new Vehiculo();
        vehiculo.set_id (rs.getInt("id"));
        vehiculo.set_compania_seguro_id(rs.getInt("compania_seguro_id"));
        vehiculo.set_cliente_id(rs.getInt("cliente_id"));
        vehiculo.set_numero_poliza(rs.getString("numero_poliza"));
        vehiculo.set_marca(rs.getString("marca"));
        vehiculo.set_patente(rs.getString("patente"));
        return vehiculo;
    }

    public List<Vehiculo> obtenerVehiculos() {
        try { return ReadVehiculoList(); }
        catch (Exception ex) {return null; }
    }

    public Vehiculo obtenerVehiculo(String id) {
        return obtenerVehiculo(Integer.valueOf(id));
    }

    public Vehiculo obtenerVehiculo(int id) {
        try {return ReadVehiculo(id);}
        catch (Exception ex) {return null;}
    }

    public Vehiculo obtenerVehiculoPatente(String patente, String compania, String nombreCliente) {
        try {
            Vehiculo vehiculo = GetVehiculoByPatente(patente);
            if(vehiculo != null)
                return vehiculo;

            CompaniaSeguro companiaSeguro = companiaSeguros.obtenerCompaniaSeguroNombre(compania);
            Cliente cliente = clientes.obtenerClienteNombre(nombreCliente);
            vehiculo = new Vehiculo();
            vehiculo.set_patente(patente);
            vehiculo.set_compania_seguro_id(companiaSeguro.getId());
            vehiculo.set_cliente_id(cliente.get_id());

            return vehiculo;
        }
        catch (Exception ex) {return null;}
    }
}
