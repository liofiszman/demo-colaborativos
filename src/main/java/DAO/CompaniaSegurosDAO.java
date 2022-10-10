package DAO;

import DTO.CompaniaSeguro;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class CompaniaSegurosDAO {


    public int create_compania_seguro(CompaniaSeguro p) throws Exception {
        String sql = "insert into compania_seguros values (?)";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1 ,p.get_nombre());

        return preparedStatement.executeUpdate();
    }

    public List<CompaniaSeguro> read_compania_seguro_list() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from cliente");

        List<CompaniaSeguro> companiaSeguroList = new ArrayList<>();
        while(rs.next()) {
            CompaniaSeguro companiaSeguro = new CompaniaSeguro();
            companiaSeguro.set_id(rs.getInt("Id"));
            companiaSeguro.set_nombre(rs.getString("nombre"));
            companiaSeguroList.add(companiaSeguro);
        }

        return companiaSeguroList;
    }


    public CompaniaSeguro read_compania_seguro(Integer id) throws Exception {


        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement("select * from cliente where id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        CompaniaSeguro companiaSeguro = new CompaniaSeguro();
        companiaSeguro.set_id(rs.getInt("Id"));
        companiaSeguro.set_nombre(rs.getString("nombre"));

        return companiaSeguro;
    }


    public int update_compania_seguro(CompaniaSeguro p) throws Exception {

        String sql = "update compania_seguros set nombre=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,p.get_nombre());
        preparedStatement.setInt(2,p.get_id());


        return preparedStatement.executeUpdate();
    }


    public int update_compania_seguro(String nombre, Integer id) throws Exception {

        String sql = "update compania_seguros set nombre=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,nombre);
        preparedStatement.setInt(2,id);

        return preparedStatement.executeUpdate();

    }


    public int delete_compania_seguro(Integer id) throws Exception {

        String sql = "delete from compania_seguros where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }


}
