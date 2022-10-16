package DAO;

import DTO.Cliente;
import DTO.FichaConformidad;
import DataAccess.IDAOFichaConformidad;
import Utils.DBConnection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FichaConformidadDAO implements IDAOFichaConformidad {
    public FichaConformidadDAO(){
        try {
            List<DTO.FichaConformidad> fichasConformidad = obtenerFichasConformidad();
            if(fichasConformidad.isEmpty()) {
                FichaConformidad fichaConformidad = new FichaConformidad();
                fichaConformidad.set_firmada(true);
                fichaConformidad.set_firmada_conforme(true);
                CreateFichaConformidad(fichaConformidad);

                fichaConformidad = new FichaConformidad();
                fichaConformidad.set_firmada(true);
                fichaConformidad.set_firmada_conforme(false);
                fichaConformidad.set_motivos_disconforme("Precios");
                CreateFichaConformidad(fichaConformidad);
            }
        }
        catch (Exception ex) {}
    }

    public int CreateFichaConformidad(FichaConformidad p) throws Exception {
        String sql = "insert into ficha_conformidad (motivos_disconforme, firmada, firmada_conforme) values (?, ?, ?)";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1 ,p.get_motivos_disconforme());
        preparedStatement.setBoolean(2 ,p.get_firmada());
        preparedStatement.setBoolean(3 ,p.get_firmada_conforme());

        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public List<FichaConformidad> ReadFichaConformidadList() throws Exception {
        Statement st = DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select id, motivos_disconforme,firmada,firmada_conforme from ficha_conformidad");

        List<FichaConformidad> fichaConformidadList = new ArrayList<>();
        while(rs.next()) {
            FichaConformidad fichaConformidad = new FichaConformidad();
            fichaConformidad.set_id(rs.getInt("id"));
            fichaConformidad.set_firmada(rs.getBoolean("firmada"));
            fichaConformidad.set_motivos_disconforme(rs.getString( "motivos_disconforme"));
            fichaConformidad.set_firmada_conforme(rs.getBoolean( "firmada_conforme"));
            fichaConformidadList.add(fichaConformidad);
        }
        return fichaConformidadList;
    }


    public FichaConformidad ReadFichaConformidad(Integer id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id, motivos_disconforme,firmada,firmada_conforme from ficha_conformidad where id = ?");
        preparedStatement.setInt(1,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();
        rs.next();

        FichaConformidad fichaConformidad = new FichaConformidad();
        fichaConformidad.set_id(rs.getInt("id"));
        fichaConformidad.set_motivos_disconforme(rs.getString("motivos_disconforme"));
        fichaConformidad.set_firmada(rs.getBoolean("firmada"));
        fichaConformidad.set_firmada_conforme(rs.getBoolean("firmada_conforme"));

        return fichaConformidad;
    }


    public int UpdateFichaConformidad(FichaConformidad p) throws Exception {

        String sql = "update ficha_conformidad set motivos_disconforme=?, firmada=?, firmada_conforme=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,p.get_motivos_disconforme());
        preparedStatement.setBoolean(2,p.get_firmada());
        preparedStatement.setBoolean(3,p.get_firmada_conforme());
        preparedStatement.setInt(4 ,p.get_id());

        return preparedStatement.executeUpdate();
    }


    public int UpdateFichaConformidad(String motivos_disconforme, Boolean firmada, Boolean firmada_conforme, Integer id) throws Exception {
        String sql = "update ficha_conformidad set motivos_disconforme=?, firmada=?, firmada_conforme=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,motivos_disconforme);
        preparedStatement.setBoolean(2,firmada);
        preparedStatement.setBoolean(3,firmada_conforme);
        preparedStatement.setInt(4,id);

        return preparedStatement.executeUpdate();
    }


    public int DeleteFichaConformidad(Integer id) throws Exception {

        String sql = "delete from ficha_conformidad where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }


    @Override
    public List<FichaConformidad> obtenerFichasConformidad() {
        try { return ReadFichaConformidadList();}
        catch (Exception ex) {
            return null;}
    }

    @Override
    public FichaConformidad obtenerFichaConformidad(String id) {
        return obtenerFichaConformidad(Integer.valueOf(id));
    }

    public FichaConformidad obtenerFichaConformidad(Integer id) {
        try { return ReadFichaConformidad(id);}
        catch (Exception ex) {return null;}
    }

    public void firmar(int id, boolean conforme) {
        try {String sql = "update ficha_conformidad set firmada=true, firmada_conforme=? where id=?";

            PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setBoolean(1 ,conforme);
            preparedStatement.setInt(2 ,id);

            preparedStatement.executeUpdate();}
        catch (Exception ex) {}

    }
}
