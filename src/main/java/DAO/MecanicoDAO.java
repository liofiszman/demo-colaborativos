package DAO;

import Classes.Opcion;
import DTO.Cliente;
import DTO.Mecanico;
import DataAccess.IDAOMecanico;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.Predicate;

public class MecanicoDAO implements IDAOMecanico {

    public MecanicoDAO(){
        try {
            List<DTO.Mecanico> mecanicos = obtenerMecanicos();
            if(mecanicos.isEmpty()) {
                Mecanico mecanico = new Mecanico();
                mecanico.set_nombre("Gabriel Martinez");
                mecanico.set_tipo_documento("DNI");
                mecanico.set_documento("33.333.333");
                mecanico.set_telefono("3323232323");
                mecanico.set_especialidad("Mecánica en general");
                CreateMecanico(mecanico);

                mecanico = new Mecanico();
                mecanico.set_nombre("Yago Marti");
                mecanico.set_tipo_documento("DNI");
                mecanico.set_documento("55.333.333");
                mecanico.set_telefono("5523232323");
                mecanico.set_especialidad("Frenos");
                CreateMecanico(mecanico);

                mecanico = new Mecanico();
                mecanico.set_nombre("Juan Perez");
                mecanico.set_tipo_documento("DNI");
                mecanico.set_documento("44.333.333");
                mecanico.set_telefono("4423232323");
                mecanico.set_especialidad("Chapa y pintura");
                CreateMecanico(mecanico);
            }
        }
        catch (Exception ex) {}
    }

    public int CreateMecanico(Mecanico p) throws Exception {
        String sql = "insert into mecanico (nombre, telefono, apellido, tipo_documento, documento,especialidad) values (?, ?, ?, ?, ?,?)";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        preparedStatement.setString(1 ,p.get_nombre());
        preparedStatement.setString(2 ,p.get_telefono());
        preparedStatement.setString(3 ,p.get_apellido());
        preparedStatement.setString(4 ,p.get_tipo_documento());
        preparedStatement.setString(5 ,p.get_documento());
        preparedStatement.setString(6,p.get_especialidad());

        return preparedStatement.executeUpdate();
    }

    public List<Mecanico> ReadMecanicoList() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select * from mecanico");

        List<Mecanico> mecanicoList = new ArrayList<>();
        while(rs.next()) {
            Mecanico mecanico = new Mecanico();
            mecanico.set_id(rs.getInt("id"));
            mecanico.set_nombre(rs.getString("nombre"));
            mecanico.set_telefono(rs.getString("telefono"));
            mecanico.set_apellido(rs.getString("apellido"));
            mecanico.set_tipo_documento(rs.getString("tipo_documento"));
            mecanico.set_documento(rs.getString("documento"));
            mecanico.set_especialidad(rs.getString("especialidad"));
            mecanicoList.add(mecanico);
        }


        return mecanicoList;
    }

    public List<String> ReadEspecialidades() throws Exception {
        Statement st = Utils.DBConnection.getConnection().createStatement();
        ResultSet rs = st.executeQuery("select distinct especialidad from mecanico");

        List<String> especialidades = new ArrayList<>();
        while(rs.next()) {
            String especialidad = rs.getString("especialidad");
            especialidades.add(especialidad);
        }
        return especialidades;
    }

    public Mecanico ReadMecanico(Integer id) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement("select * from mecanico where id = ?");
        preparedStatement.setInt(1 ,id);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        Mecanico mecanico = new Mecanico();
        mecanico.set_id(rs.getInt("id"));
        mecanico.set_nombre(rs.getString("nombre"));
        mecanico.set_telefono(rs.getString("telefono"));
        mecanico.set_apellido(rs.getString("apellido"));
        mecanico.set_tipo_documento(rs.getString("tipo_documento"));
        mecanico.set_documento(rs.getString("documento"));
        mecanico.set_especialidad(rs.getString("especialidad"));
        return mecanico;
    }

    public Mecanico ReadMecanicoNombre(String nombre) throws Exception {
        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(
                "select id,nombre,telefono,apellido,tipo_documento,documento,especialidad from mecanico where nombre = ?");
        preparedStatement.setString(1 ,nombre);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        Mecanico mecanico = new Mecanico();
        mecanico.set_id(rs.getInt("id"));
        mecanico.set_nombre(rs.getString("nombre"));
        mecanico.set_telefono(rs.getString("telefono"));
        mecanico.set_apellido(rs.getString("apellido"));
        mecanico.set_tipo_documento(rs.getString("tipo_documento"));
        mecanico.set_documento(rs.getString("documento"));
        mecanico.set_especialidad(rs.getString("especialidad"));
        return mecanico;
    }

    public List<Mecanico> ReadMecanico(String especialidad) throws Exception {


        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement("select * from mecanico where especialidad = ?");
        preparedStatement.setString(1 ,especialidad);
        preparedStatement.setMaxRows(1);
        ResultSet rs  = preparedStatement.executeQuery();

        List<Mecanico> mecanicoList = new ArrayList<>();
        while(rs.next()) {
            Mecanico mecanico = new Mecanico();
            mecanico.set_id(rs.getInt("id"));
            mecanico.set_nombre(rs.getString("nombre"));
            mecanico.set_telefono(rs.getString("telefono"));
            mecanico.set_apellido(rs.getString("apellido"));
            mecanico.set_tipo_documento(rs.getString("tipo_documento"));
            mecanico.set_documento(rs.getString("documento"));
            mecanico.set_especialidad(rs.getString("especialidad"));
            mecanicoList.add(mecanico);
        }


        return mecanicoList;
    }

    public int UpdateMecanico(Mecanico p) throws Exception {

        String sql = "update mecanico set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=?, especialidad=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,p.get_nombre());
        preparedStatement.setString(2 ,p.get_telefono());
        preparedStatement.setString(3 ,p.get_apellido());
        preparedStatement.setString(4 ,p.get_tipo_documento());
        preparedStatement.setString(5 ,p.get_documento());
        preparedStatement.setString(6,p.get_especialidad());
        preparedStatement.setInt(6 ,p.get_id());


        return preparedStatement.executeUpdate();
    }

    public int UpdateMecanico(String nombre, String telefono, String apellido, String tipo_documento, String documento, Integer id,String especialidad) throws Exception {

        String sql = "update mecanico set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=?, especialidad=? where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setString(1 ,nombre);
        preparedStatement.setString(2 ,telefono);
        preparedStatement.setString(3 ,apellido);
        preparedStatement.setString(4 ,tipo_documento);
        preparedStatement.setString(5 ,documento);
        preparedStatement.setString(6,especialidad);
        preparedStatement.setInt(6 ,id);

        return preparedStatement.executeUpdate();

    }

    public int DeleteMecanico(Integer id) throws Exception {

        String sql = "delete from mecanico where id=?";

        PreparedStatement preparedStatement = Utils.DBConnection.getConnection().prepareStatement(sql);
        preparedStatement.setInt(1 ,id);

        return preparedStatement.executeUpdate();
    }

    public List<DTO.Mecanico> obtenerMecanicos(){
        try {
            return this.ReadMecanicoList();
        }catch (Exception ex){
            return null;
        }
    }

    public DTO.Mecanico obtenerMecanico(String id){
        try {
            return this.ReadMecanico(Integer.valueOf(id));
        }
        catch (Exception ex) {
            return null;
        }
    }

    public DTO.Mecanico obtenerMecanico(int id){
        try {
            return this.ReadMecanico(id);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public DTO.Mecanico obtenerMecanicoNombre(String nombre){
        try {
            return this.ReadMecanicoNombre(nombre);
        }
        catch (Exception ex) {
            return null;
        }
    }

    public List<String> obtenerEspecialidades(){
        return this.obtenerEspecialidades();
    }

    public DTO.Mecanico getByID(String id) {
       try{
           return this.ReadMecanico(Integer.valueOf(id));
       }catch (Exception ex)
       {
           return null;
       }
    }

    public List<DTO.Mecanico> obtenerTurnos(Opcion opcion) {
        try{
            return this.ReadMecanico(opcion.getEspecialidad());
        }catch (Exception ex)
        {
            return null;
        }
    }
}
