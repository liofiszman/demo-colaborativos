package DAO;

import DTO.FichaConformidad;
import Utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FichaConformidadDAO {


    public int create_compania_seguro(FichaConformidad p) throws Exception {
        String sql = "insert into ficha_conformidad (motivos_disconforme, firmada, firmada_conforme) values (?, ?, ?)";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1 ,p.get_motivos_disconforme());
        preparedStatement.setBoolean(2 ,p.get_firmada());
        preparedStatement.setBoolean(3 ,p.get_firmada_conforme());

        return preparedStatement.executeUpdate();
    }

    public List<FichaConformidad> read_ficha_conformidad_list() throws Exception {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from ficha_conformidad where id=?");

        List<FichaConformidad> fichaConformidadList = new ArrayList<>();
        while(rs.next()) {
            FichaConformidad fichaConformidad = new FichaConformidad();
            fichaConformidad.set_id(rs.getInt("Id"));
            fichaConformidad.set_firmada(rs.getBoolean("firmada"));
            fichaConformidad.set_motivos_disconforme(rs.getString( "motivos_disconforme"));
            fichaConformidad.set_firmada_conforme(rs.getBoolean( "firmada_conforme"));
        }
        return fichaConformidadList;
    }


    public FichaConformidad read_ficha_conformidad(Integer id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement("select * from ficha_conformidad where id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        FichaConformidad fichaConformidad = new FichaConformidad();
        fichaConformidad.set_id(rs.getInt("Id"));
        fichaConformidad.set_motivos_disconforme(rs.getString("motivos_disconforme"));
        fichaConformidad.set_firmada(rs.getBoolean("firmada"));
        fichaConformidad.set_firmada_conforme(rs.getBoolean("firmada_conforme"));

        return fichaConformidad;
    }


    public int update_ficha_conformidad(FichaConformidad p) throws Exception {

        String sql = "update ficha_conformidad set motivos_disconforme=?, firmada=?, firmada_conforme=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,p.get_motivos_disconforme());
        preparedStatement.setBoolean(2,p.get_firmada());
        preparedStatement.setBoolean(3,p.get_firmada_conforme());
        preparedStatement.setInt(4 ,p.get_id());

        return preparedStatement.executeUpdate();
    }


    public int update_compania_seguro(String motivos_disconforme, Boolean firmada, Boolean firmada_conforme, Integer id) throws Exception {
        String sql = "update ficha_conformidad set motivos_disconforme=?, firmada=?, firmada_conforme=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,motivos_disconforme);
        preparedStatement.setBoolean(2,firmada);
        preparedStatement.setBoolean(3,firmada_conforme);
        preparedStatement.setInt(4,id);

        return preparedStatement.executeUpdate();
    }


    public int delete_compania_seguro(Integer id) throws Exception {

        String sql = "delete from ficha_conformidad where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }


}