package DAO;

import DTO.CompaniaSeguro;
import DTO.FichaMecanica;
import DataAccess.IDAOFichaMecanica;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class FichaMecanicaDAO implements IDAOFichaMecanica {
    FichaConformidadDAO fichasConfomidadDAO = new FichaConformidadDAO();

    public FichaMecanicaDAO() {
        List<DTO.FichaMecanica> fichasMecanicas = obtenerFichasMecanicas();
        if(fichasMecanicas.isEmpty()) {
            try {
                FichaMecanica fichaMecanica = new FichaMecanica();
                fichaMecanica.set_actividades("Cambio de aceite.");
                fichaMecanica.set_repuestos("LION400, $2200.");
                fichaMecanica.set_ficha_conformidad_id(fichasConfomidadDAO.obtenerFichasConformidad().stream().findFirst().get().get_id());
                CreateFichaMecanica(fichaMecanica);

                fichaMecanica = new FichaMecanica();
                fichaMecanica.set_actividades("Alineado y balanceado.");
                fichaMecanica.set_repuestos("Sin insumos.");
                fichaMecanica.set_ficha_conformidad_id(fichasConfomidadDAO.obtenerFichasConformidad().get(fichasConfomidadDAO.obtenerFichasConformidad().size() - 1).get_id());
                CreateFichaMecanica(fichaMecanica);
            }
            catch (Exception ex) {}
        }
    }

    public int CreateFichaMecanica(FichaMecanica p) throws Exception {
        String sql = "insert into ficha_mecanica (actividades, ficha_conformidad_id, repuestos) values (?, ?, ?)";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1 ,p.get_actividades());
        preparedStatement.setInt(2 ,p.get_ficha_conformidad_id());
        preparedStatement.setString(3 ,p.get_repuestos());

        preparedStatement.executeUpdate();
        ResultSet rs = preparedStatement.getGeneratedKeys();
        rs.next();
        return rs.getInt(1);
    }

    public List<FichaMecanica> ReadFichaMecanicaList() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select id,actividades,ficha_conformidad_id,repuestos from ficha_mecanica");

        List<FichaMecanica> fichaMecanicaList = new ArrayList<>();
        while(rs.next()) {
            FichaMecanica fichaMecanica = new FichaMecanica();
            fichaMecanica.set_id(rs.getInt("id"));
            fichaMecanica.set_actividades(rs.getString("actividades"));
            fichaMecanica.set_ficha_conformidad_id(rs.getInt("ficha_conformidad_id"));
            fichaMecanica.set_repuestos(rs.getString("repuestos"));
            fichaMecanicaList.add(fichaMecanica);
        }
        return fichaMecanicaList;
    }


    @Override
    public List<FichaMecanica> obtenerFichasMecanicas() {
        try {return ReadFichaMecanicaList();}
        catch (Exception ex) {return null;}
    }

    public FichaMecanica obtenerFichaMecanica(String id) {
        return obtenerFichaMecanica(Integer.valueOf(id));
    };

    public void registrarActividades(int id, String actividadesText,String insumosText) {
        try {
            String sql = "update ficha_mecanica set actividades=?, repuestos=? where id=?";

            PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
            preparedStatement.setString(1 ,actividadesText);
            preparedStatement.setString(2 ,insumosText);
            preparedStatement.setInt(3 ,id);

            preparedStatement.executeUpdate();}
        catch (Exception ex) {}
    }

    public FichaMecanica obtenerFichaMecanica(int id) {
        try {
            return ReadFichaMecanica(id);
        }
        catch (Exception ex) {
            return null;
        }
    };

    public FichaMecanica ReadFichaMecanica(Integer id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement("select * from ficha_mecanica where id=?");
        preparedStatement.setInt(1,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();
        rs.next();

        FichaMecanica fichaMecanica = new FichaMecanica();
        fichaMecanica.set_id(rs.getInt("Id"));
        fichaMecanica.set_actividades(rs.getString("actividades"));
        fichaMecanica.set_ficha_conformidad_id(rs.getInt("ficha_conformidad_id"));
        fichaMecanica.set_repuestos(rs.getString("repuestos"));
        return fichaMecanica;
    }


    public int UpdateFichaMecanica(FichaMecanica p) throws Exception {
        String sql = "update ficha_mecanica set actividades=?, ficha_conformidad_id=?, repuestos=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,p.get_actividades());
        preparedStatement.setInt(2,p.get_ficha_conformidad_id());
        preparedStatement.setString(3,p.get_repuestos());
        preparedStatement.setInt(4,p.get_id());

        return preparedStatement.executeUpdate();
    }


    public int UpdateFichaMecanica(String actividades, Integer ficha_conformidad_id, String repuestos, Integer id) throws Exception {

        String sql = "update ficha_mecanica set actividades=?, ficha_conformidad_id=?, repuestos=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,actividades);
        preparedStatement.setInt(2,ficha_conformidad_id);
        preparedStatement.setString(3,repuestos);
        preparedStatement.setInt(4,id);

        return preparedStatement.executeUpdate();

    }


    public int DeleteFichaMecanica(Integer id) throws Exception {

        String sql = "delete from ficha_mecanica where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }

    public List<FichaMecanica> get_ficha_mecanica_by_ficha_conformidad(Integer ficha_conformidad_id) throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select id,actividades,ficha_conformidad_id,repuestos from ficha_mecanica where ficha_conformidad_id=?");
        List<FichaMecanica> fichaMecanicaList = new ArrayList<>();
        while(rs.next()) {
            FichaMecanica fichaMecanica = new FichaMecanica();
            fichaMecanica.set_id(rs.getInt("id"));
            fichaMecanica.set_actividades(rs.getString("actividades"));
            fichaMecanica.set_ficha_conformidad_id(rs.getInt("ficha_conformidad_id"));
            fichaMecanica.set_repuestos(rs.getString("repuestos"));
            fichaMecanicaList.add(fichaMecanica);
        }
        return fichaMecanicaList;
    }


}
