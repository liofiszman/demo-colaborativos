package DAO;

import DTO.CompaniaSeguro;
import DataAccess.IDAOCompaniaSeguro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

public class CompaniaSegurosDAO implements IDAOCompaniaSeguro {
    public CompaniaSegurosDAO(){
        try {
            List<DTO.CompaniaSeguro> _companiasSeguro = obtenerCompaniasSeguro();

            if(_companiasSeguro.isEmpty()) {
                CompaniaSeguro compania = new CompaniaSeguro();
                compania.setNombre("Sancor");
                CreateCompaniaSeguro(compania);

                compania = new CompaniaSeguro();
                compania.setNombre("La Caja");
                CreateCompaniaSeguro(compania);

                compania = new CompaniaSeguro();
                compania.setNombre("San Cristobal");
                CreateCompaniaSeguro(compania);

                compania = new CompaniaSeguro();
                compania.setNombre("Orbis");
                CreateCompaniaSeguro(compania);
            }
        }
        catch (Exception ex) {}
    }

    public int CreateCompaniaSeguro(DTO.CompaniaSeguro p) throws Exception {
        String sql = "insert into compania_seguros (nombre) values (?)";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1 ,p.getNombre());

        return preparedStatement.executeUpdate();
    }

    public List<CompaniaSeguro> ReadCompaniaSeguroList() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select id,nombre from compania_seguros");

        List<CompaniaSeguro> companiaSeguroList = new ArrayList<>();
        while(rs.next()) {
            CompaniaSeguro companiaSeguro = new CompaniaSeguro();
            companiaSeguro.setId(rs.getInt("id"));
            companiaSeguro.setNombre(rs.getString("nombre"));
            companiaSeguroList.add(companiaSeguro);
        }

        return companiaSeguroList;
    }


    public CompaniaSeguro ReadCompaniaSeguro(Integer id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id,nombre from compania_seguros where id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();
        rs.next();

        CompaniaSeguro companiaSeguro = new CompaniaSeguro();
        companiaSeguro.setId(rs.getInt("id"));
        companiaSeguro.setNombre(rs.getString("nombre"));

        return companiaSeguro;
    }

    public CompaniaSeguro ReadCompaniaSeguro(String nombre) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id,nombre from compania_seguros where nombre = ?");
        preparedStatement.setString(1,nombre);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();
        rs.next();

        CompaniaSeguro companiaSeguro = new CompaniaSeguro();
        companiaSeguro.setId(rs.getInt("id"));
        companiaSeguro.setNombre(rs.getString("nombre"));

        return companiaSeguro;
    }


    public int UpdateCompaniaSeguro(CompaniaSeguro p) throws Exception {

        String sql = "update compania_seguros set nombre=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,p.getNombre());
        preparedStatement.setInt(2,p.getId());


        return preparedStatement.executeUpdate();
    }

    public int UpdateCompaniaSeguro(String nombre, Integer id) throws Exception {

        String sql = "update compania_seguros set nombre=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,nombre);
        preparedStatement.setInt(2,id);

        return preparedStatement.executeUpdate();

    }

    public int DeleteCompaniaSeguro(Integer id) throws Exception {

        String sql = "delete from compania_seguros where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }


    public List<CompaniaSeguro> obtenerCompaniasSeguro(){
        try {
            return ReadCompaniaSeguroList();
        }
        catch (Exception ex) {
            return null;
        }
    }

    public DTO.CompaniaSeguro obtenerCompaniaSeguro(int id){
        try {
            return ReadCompaniaSeguro(id);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public DTO.CompaniaSeguro obtenerCompaniaSeguroNombre(String nombre){
        try {
            return ReadCompaniaSeguro(nombre);
        }
        catch (Exception ex) {
            return null;
        }
    }
}
