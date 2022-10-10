package DAO;




import DTO.Cliente;
import DTO.CompaniaSeguro;
import DTO.FichaConformidad;
import DTO.FichaMecanica;
import DTO.HorarioAtencion;
import DTO.Mecanico;
import DTO.Turno;
import DTO.Vehiculo;

import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class Dao {

    protected final DataStore ds;

    public Dao(DataStore ds) {
        this.ds = ds;
    }

    /*
        (C)RUD: cliente
        Generated values are passed to DTO.
        Returns the number of affected rows or -1 on error.
     */
    public int create_cliente(Cliente p) throws Exception {
        String sql = "insert into cliente (nombre, telefono, apellido, tipo_documento, documento) values (?, ?, ?, ?, ?)";
        String[] gen_col_nm = new String[]{"id"};
        Object[] gen_values = new Object[gen_col_nm.length];
        int res = ds.insert(sql, gen_col_nm, gen_values, p.get_nombre(), p.get_telefono(), p.get_apellido(), p.get_tipo_documento(), p.get_documento());
        p.set_id(ds.castGeneratedValue(Integer.class, gen_values[0]));
        return res;
    }

    /*
        C(R)UD: cliente
     */
    public List<Cliente> read_cliente_list() throws Exception {
        String sql = "select * from cliente";
        final List<Cliente> res = new ArrayList<Cliente>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                Cliente obj = new Cliente();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
                obj.set_nombre(rd.getValue(String.class, "nombre"));  // t <- t
                obj.set_telefono(rd.getValue(String.class, "telefono"));  // t <- t
                obj.set_apellido(rd.getValue(String.class, "apellido"));  // t <- t
                obj.set_tipo_documento(rd.getValue(String.class, "tipo_documento"));  // t <- t
                obj.set_documento(rd.getValue(String.class, "documento"));  // t <- t
                res.add(obj);
            }
        });
        return res;
    }

    /*
        C(R)UD: cliente
     */
    public Cliente read_cliente(Integer id) throws Exception {
        String sql = "select * from cliente where id=?";
        DataStore.RowData rd = ds.queryRow(sql, id);
        Cliente obj = new Cliente();
        obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
        obj.set_nombre(rd.getValue(String.class, "nombre"));  // t <- t
        obj.set_telefono(rd.getValue(String.class, "telefono"));  // t <- t
        obj.set_apellido(rd.getValue(String.class, "apellido"));  // t <- t
        obj.set_tipo_documento(rd.getValue(String.class, "tipo_documento"));  // t <- t
        obj.set_documento(rd.getValue(String.class, "documento"));  // t <- t
        return obj;
    }

    /*
        CR(U)D: cliente
        Returns the number of affected rows or -1 on error.
     */
    public int update_cliente(Cliente p) throws Exception {
        String sql = "update cliente set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=? where id=?";
        return ds.execDML(sql, p.get_nombre(), p.get_telefono(), p.get_apellido(), p.get_tipo_documento(), p.get_documento(), p.get_id());
    }

    /*
        CR(U)D: cliente
        Returns the number of affected rows or -1 on error.
     */
    public int update_cliente(String nombre, String telefono, String apellido, String tipo_documento, String documento, Integer id) throws Exception {
        String sql = "update cliente set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=? where id=?";
        return ds.execDML(sql, nombre, telefono, apellido, tipo_documento, documento, id);
    }

    /*
        CRU(D): cliente
        Returns the number of affected rows or -1 on error.
     */
    public int delete_cliente(Integer id) throws Exception {
        String sql = "delete from cliente where id=?";
        return ds.execDML(sql, id);
    }

    /*
        (C)RUD: compania_seguros
        Generated values are passed to DTO.
        Returns the number of affected rows or -1 on error.
     */
    public int create_compania_seguro(CompaniaSeguro p) throws Exception {
        String sql = "insert into compania_seguros (nombre) values (?)";
        String[] gen_col_nm = new String[]{"id"};
        Object[] gen_values = new Object[gen_col_nm.length];
        int res = ds.insert(sql, gen_col_nm, gen_values, p.get_nombre());
        p.set_id(ds.castGeneratedValue(Integer.class, gen_values[0]));
        return res;
    }

    /*
        C(R)UD: compania_seguros
     */
    public List<CompaniaSeguro> read_compania_seguro_list() throws Exception {
        String sql = "select * from compania_seguros";
        final List<CompaniaSeguro> res = new ArrayList<CompaniaSeguro>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                CompaniaSeguro obj = new CompaniaSeguro();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
                obj.set_nombre(rd.getValue(String.class, "nombre"));  // t <- t
                res.add(obj);
            }
        });
        return res;
    }


    /*
        (C)RUD: ficha_conformidad
        Generated values are passed to DTO.
        Returns the number of affected rows or -1 on error.
     */
    public int create_ficha_conformidad(FichaConformidad p) throws Exception {
        String sql = "insert into ficha_conformidad (motivos_disconforme, firmada, firmada_conforme) values (?, ?, ?)";
        String[] gen_col_nm = new String[]{"id"};
        Object[] gen_values = new Object[gen_col_nm.length];
        int res = ds.insert(sql, gen_col_nm, gen_values, p.get_motivos_disconforme(), p.get_firmada(), p.get_firmada_conforme());
        p.set_id(ds.castGeneratedValue(Integer.class, gen_values[0]));
        return res;
    }

    /*
        C(R)UD: ficha_conformidad
     */
    public List<FichaConformidad> read_ficha_conformidad_list() throws Exception {
        String sql = "select * from ficha_conformidad";
        final List<FichaConformidad> res = new ArrayList<FichaConformidad>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                FichaConformidad obj = new FichaConformidad();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
                obj.set_motivos_disconforme(rd.getValue(String.class, "motivos_disconforme"));  // t <- t
                obj.set_firmada(rd.getValue(Boolean.class, "firmada"));  // t <- t
                obj.set_firmada_conforme(rd.getValue(Boolean.class, "firmada_conforme"));  // t <- t
                res.add(obj);
            }
        });
        return res;
    }

    /*
        C(R)UD: ficha_conformidad
     */
    public FichaConformidad read_ficha_conformidad(Integer id) throws Exception {
        String sql = "select * from ficha_conformidad where id=?";
        DataStore.RowData rd = ds.queryRow(sql, id);
        FichaConformidad obj = new FichaConformidad();
        obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
        obj.set_motivos_disconforme(rd.getValue(String.class, "motivos_disconforme"));  // t <- t
        obj.set_firmada(rd.getValue(Boolean.class, "firmada"));  // t <- t
        obj.set_firmada_conforme(rd.getValue(Boolean.class, "firmada_conforme"));  // t <- t
        return obj;
    }

    /*
        CR(U)D: ficha_conformidad
        Returns the number of affected rows or -1 on error.
     */
    public int update_ficha_conformidad(FichaConformidad p) throws Exception {
        String sql = "update ficha_conformidad set motivos_disconforme=?, firmada=?, firmada_conforme=? where id=?";
        return ds.execDML(sql, p.get_motivos_disconforme(), p.get_firmada(), p.get_firmada_conforme(), p.get_id());
    }

    /*
        CR(U)D: ficha_conformidad
        Returns the number of affected rows or -1 on error.
     */
    public int update_ficha_conformidad(String motivos_disconforme, Boolean firmada, Boolean firmada_conforme, Integer id) throws Exception {
        String sql = "update ficha_conformidad set motivos_disconforme=?, firmada=?, firmada_conforme=? where id=?";
        return ds.execDML(sql, motivos_disconforme, firmada, firmada_conforme, id);
    }

    /*
        CRU(D): ficha_conformidad
        Returns the number of affected rows or -1 on error.
     */
    public int delete_ficha_conformidad(Integer id) throws Exception {
        String sql = "delete from ficha_conformidad where id=?";
        return ds.execDML(sql, id);
    }

    /*
        (C)RUD: ficha_mecanica
        Generated values are passed to DTO.
        Returns the number of affected rows or -1 on error.
     */
    public int create_ficha_mecanica(FichaMecanica p) throws Exception {
        String sql = "insert into ficha_mecanica (actividades, ficha_conformidad_id, repuestos) values (?, ?, ?)";
        String[] gen_col_nm = new String[]{"id"};
        Object[] gen_values = new Object[gen_col_nm.length];
        int res = ds.insert(sql, gen_col_nm, gen_values, p.get_actividades(), p.get_ficha_conformidad_id(), p.get_repuestos());
        p.set_id(ds.castGeneratedValue(Integer.class, gen_values[0]));
        return res;
    }

    /*
        C(R)UD: ficha_mecanica
     */
    public List<FichaMecanica> read_ficha_mecanica_list() throws Exception {
        String sql = "select * from ficha_mecanica";
        final List<FichaMecanica> res = new ArrayList<FichaMecanica>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                FichaMecanica obj = new FichaMecanica();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
                obj.set_actividades(rd.getValue(String.class, "actividades"));  // t <- t
                obj.set_ficha_conformidad_id(rd.getValue(Integer.class, "ficha_conformidad_id"));  // t <- t
                obj.set_repuestos(rd.getValue(String.class, "repuestos"));  // t <- t
                res.add(obj);
            }
        });
        return res;
    }

    /*
        C(R)UD: ficha_mecanica
     */
    public FichaMecanica read_ficha_mecanica(Integer id) throws Exception {
        String sql = "select * from ficha_mecanica where id=?";
        DataStore.RowData rd = ds.queryRow(sql, id);
        FichaMecanica obj = new FichaMecanica();
        obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
        obj.set_actividades(rd.getValue(String.class, "actividades"));  // t <- t
        obj.set_ficha_conformidad_id(rd.getValue(Integer.class, "ficha_conformidad_id"));  // t <- t
        obj.set_repuestos(rd.getValue(String.class, "repuestos"));  // t <- t
        return obj;
    }

    /*
        CR(U)D: ficha_mecanica
        Returns the number of affected rows or -1 on error.
     */
    public int update_ficha_mecanica(FichaMecanica p) throws Exception {
        String sql = "update ficha_mecanica set actividades=?, ficha_conformidad_id=?, repuestos=? where id=?";
        return ds.execDML(sql, p.get_actividades(), p.get_ficha_conformidad_id(), p.get_repuestos(), p.get_id());
    }

    /*
        CR(U)D: ficha_mecanica
        Returns the number of affected rows or -1 on error.
     */
    public int update_ficha_mecanica(String actividades, Integer ficha_conformidad_id, String repuestos, Integer id) throws Exception {
        String sql = "update ficha_mecanica set actividades=?, ficha_conformidad_id=?, repuestos=? where id=?";
        return ds.execDML(sql, actividades, ficha_conformidad_id, repuestos, id);
    }

    /*
        CRU(D): ficha_mecanica
        Returns the number of affected rows or -1 on error.
     */
    public int delete_ficha_mecanica(Integer id) throws Exception {
        String sql = "delete from ficha_mecanica where id=?";
        return ds.execDML(sql, id);
    }

    public List<FichaMecanica> get_ficha_mecanica_by_ficha_conformidad(Integer ficha_conformidad_id) throws Exception {
        String sql = "select * from ficha_mecanica where ficha_conformidad_id=?";
        final List<FichaMecanica> res = new ArrayList<FichaMecanica>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                FichaMecanica obj = new FichaMecanica();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t [INFO] SQL-shortcut
                obj.set_actividades(rd.getValue(String.class, "actividades"));  // t <- t
                obj.set_ficha_conformidad_id(rd.getValue(Integer.class, "ficha_conformidad_id"));  // t <- t
                obj.set_repuestos(rd.getValue(String.class, "repuestos"));  // t <- t
                res.add(obj);
            }
        }, ficha_conformidad_id);
        return res;
    }

    /*
        (C)RUD: horario_atencion
        Generated values are passed to DTO.
        Returns the number of affected rows or -1 on error.
     */
    public int create_horario_atencion(HorarioAtencion p) throws Exception {
        String sql = "insert into horario_atencion (dia_atencion, hora_desde, hora_hasta, mecanico_id) values (?, ?, ?, ?)";
        String[] gen_col_nm = new String[]{"id"};
        Object[] gen_values = new Object[gen_col_nm.length];
        int res = ds.insert(sql, gen_col_nm, gen_values, p.get_dia_atencion(), p.get_hora_desde(), p.get_hora_hasta(), p.get_mecanico_id());
        p.set_id(ds.castGeneratedValue(Integer.class, gen_values[0]));
        return res;
    }

    /*
        C(R)UD: horario_atencion
     */
    public List<HorarioAtencion> read_horario_atencion_list() throws Exception {
        String sql = "select * from horario_atencion";
        final List<HorarioAtencion> res = new ArrayList<HorarioAtencion>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                HorarioAtencion obj = new HorarioAtencion();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
                obj.set_dia_atencion(rd.getValue(String.class, "dia_atencion"));  // t <- t
                obj.set_hora_desde(rd.getValue(LocalTime.class, "hora_desde"));  // t <- t
                obj.set_hora_hasta(rd.getValue(LocalTime.class, "hora_hasta"));  // t <- t
                obj.set_mecanico_id(rd.getValue(Integer.class, "mecanico_id"));  // t <- t
                res.add(obj);
            }
        });
        return res;
    }

    /*
        C(R)UD: horario_atencion
     */
    public HorarioAtencion read_horario_atencion(Integer id) throws Exception {
        String sql = "select * from horario_atencion where id=?";
        DataStore.RowData rd = ds.queryRow(sql, id);
        HorarioAtencion obj = new HorarioAtencion();
        obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
        obj.set_dia_atencion(rd.getValue(String.class, "dia_atencion"));  // t <- t
        obj.set_hora_desde(rd.getValue(LocalTime.class, "hora_desde"));  // t <- t
        obj.set_hora_hasta(rd.getValue(LocalTime.class, "hora_hasta"));  // t <- t
        obj.set_mecanico_id(rd.getValue(Integer.class, "mecanico_id"));  // t <- t
        return obj;
    }

    /*
        CR(U)D: horario_atencion
        Returns the number of affected rows or -1 on error.
     */
    public int update_horario_atencion(HorarioAtencion p) throws Exception {
        String sql = "update horario_atencion set dia_atencion=?, hora_desde=?, hora_hasta=?, mecanico_id=? where id=?";
        return ds.execDML(sql, p.get_dia_atencion(), p.get_hora_desde(), p.get_hora_hasta(), p.get_mecanico_id(), p.get_id());
    }

    /*
        CR(U)D: horario_atencion
        Returns the number of affected rows or -1 on error.
     */
    public int update_horario_atencion(String dia_atencion, LocalDate hora_desde, LocalDate hora_hasta, Integer mecanico_id, Integer id) throws Exception {
        String sql = "update horario_atencion set dia_atencion=?, hora_desde=?, hora_hasta=?, mecanico_id=? where id=?";
        return ds.execDML(sql, dia_atencion, hora_desde, hora_hasta, mecanico_id, id);
    }

    /*
        CRU(D): horario_atencion
        Returns the number of affected rows or -1 on error.
     */
    public int delete_horario_atencion(Integer id) throws Exception {
        String sql = "delete from horario_atencion where id=?";
        return ds.execDML(sql, id);
    }

    public List<HorarioAtencion> get_horario_atencion_by_mecanico(Integer mecanico_id) throws Exception {
        String sql = "select * from horario_atencion where mecanico_id=?";
        final List<HorarioAtencion> res = new ArrayList<HorarioAtencion>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                HorarioAtencion obj = new HorarioAtencion();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t [INFO] SQL-shortcut
                obj.set_dia_atencion(rd.getValue(String.class, "dia_atencion"));  // t <- t
                obj.set_hora_desde(rd.getValue(LocalTime.class, "hora_desde"));  // t <- t
                obj.set_hora_hasta(rd.getValue(LocalTime.class, "hora_hasta"));  // t <- t
                obj.set_mecanico_id(rd.getValue(Integer.class, "mecanico_id"));  // t <- t
                res.add(obj);
            }
        }, mecanico_id);
        return res;
    }

    /*
        (C)RUD: mecanico
        Generated values are passed to DTO.
        Returns the number of affected rows or -1 on error.
     */
    public int create_mecanico(Mecanico p) throws Exception {
        String sql = "insert into mecanico (nombre, telefono, apellido, tipo_documento, documento) values (?, ?, ?, ?, ?)";
        String[] gen_col_nm = new String[]{"id"};
        Object[] gen_values = new Object[gen_col_nm.length];
        int res = ds.insert(sql, gen_col_nm, gen_values, p.get_nombre(), p.get_telefono(), p.get_apellido(), p.get_tipo_documento(), p.get_documento());
        p.set_id(ds.castGeneratedValue(Integer.class, gen_values[0]));
        return res;
    }

    /*
        C(R)UD: mecanico
     */
    public List<Mecanico> read_mecanico_list() throws Exception {
        String sql = "select * from mecanico";
        final List<Mecanico> res = new ArrayList<Mecanico>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                Mecanico obj = new Mecanico();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
                obj.set_nombre(rd.getValue(String.class, "nombre"));  // t <- t
                obj.set_telefono(rd.getValue(String.class, "telefono"));  // t <- t
                obj.set_apellido(rd.getValue(String.class, "apellido"));  // t <- t
                obj.set_tipo_documento(rd.getValue(String.class, "tipo_documento"));  // t <- t
                obj.set_documento(rd.getValue(String.class, "documento"));  // t <- t
                res.add(obj);
            }
        });
        return res;
    }

    /*
        C(R)UD: mecanico
     */
    public Mecanico read_mecanico(Integer id) throws Exception {
        String sql = "select * from mecanico where id=?";
        DataStore.RowData rd = ds.queryRow(sql, id);
        Mecanico obj = new Mecanico();
        obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
        obj.set_nombre(rd.getValue(String.class, "nombre"));  // t <- t
        obj.set_telefono(rd.getValue(String.class, "telefono"));  // t <- t
        obj.set_apellido(rd.getValue(String.class, "apellido"));  // t <- t
        obj.set_tipo_documento(rd.getValue(String.class, "tipo_documento"));  // t <- t
        obj.set_documento(rd.getValue(String.class, "documento"));  // t <- t
        return obj;
    }

    /*
        CR(U)D: mecanico
        Returns the number of affected rows or -1 on error.
     */
    public int update_mecanico(Mecanico p) throws Exception {
        String sql = "update mecanico set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=? where id=?";
        return ds.execDML(sql, p.get_nombre(), p.get_telefono(), p.get_apellido(), p.get_tipo_documento(), p.get_documento(), p.get_id());
    }

    /*
        CR(U)D: mecanico
        Returns the number of affected rows or -1 on error.
     */
    public int update_mecanico(String nombre, String telefono, String apellido, String tipo_documento, String documento, Integer id) throws Exception {
        String sql = "update mecanico set nombre=?, telefono=?, apellido=?, tipo_documento=?, documento=? where id=?";
        return ds.execDML(sql, nombre, telefono, apellido, tipo_documento, documento, id);
    }

    /*
        CRU(D): mecanico
        Returns the number of affected rows or -1 on error.
     */
    public int delete_mecanico(Integer id) throws Exception {
        String sql = "delete from mecanico where id=?";
        return ds.execDML(sql, id);
    }

    /*
        (C)RUD: turno
        Generated values are passed to DTO.
        Returns the number of affected rows or -1 on error.
     */
    public int create_turno(Turno p) throws Exception {
        String sql = "insert into turno (active, fecha, hora, mecanico_id, vehiculo_id, asistencia, ficha_mecanica_id) values (?, ?, ?, ?, ?, ?, ?)";
        String[] gen_col_nm = new String[]{"id"};
        Object[] gen_values = new Object[gen_col_nm.length];
        int res = ds.insert(sql, gen_col_nm, gen_values, p.get_active(), p.get_fecha(), p.get_hora(), p.get_mecanico_id(), p.get_vehiculo_id(), p.get_asistencia(), p.get_ficha_mecanica_id());
        p.set_id(ds.castGeneratedValue(Integer.class, gen_values[0]));
        return res;
    }

    /*
        C(R)UD: turno
     */
    public List<Turno> read_turno_list() throws Exception {
        String sql = "select * from turno";
        final List<Turno> res = new ArrayList<Turno>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                Turno obj = new Turno();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
                obj.set_active(rd.getValue(Boolean.class, "active"));  // t <- t
                obj.set_fecha(rd.getValue(LocalDate.class, "fecha"));  // t <- t
                obj.set_hora(rd.getValue(LocalTime.class, "hora"));  // t <- t
                obj.set_mecanico_id(rd.getValue(Integer.class, "mecanico_id"));  // t <- t
                obj.set_vehiculo_id(rd.getValue(Integer.class, "vehiculo_id"));  // t <- t
                obj.set_asistencia(rd.getValue(Boolean.class, "asistencia"));  // t <- t
                obj.set_ficha_mecanica_id(rd.getValue(Integer.class, "ficha_mecanica_id"));  // t <- t
                res.add(obj);
            }
        });
        return res;
    }

    /*
        C(R)UD: turno
     */
    public Turno read_turno(Integer id) throws Exception {
        String sql = "select * from turno where id=?";
        DataStore.RowData rd = ds.queryRow(sql, id);
        Turno obj = new Turno();
        obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
        obj.set_active(rd.getValue(Boolean.class, "active"));  // t <- t
        obj.set_fecha(rd.getValue(LocalDate.class, "fecha"));  // t <- t
        obj.set_hora(rd.getValue(LocalTime.class, "hora"));  // t <- t
        obj.set_mecanico_id(rd.getValue(Integer.class, "mecanico_id"));  // t <- t
        obj.set_vehiculo_id(rd.getValue(Integer.class, "vehiculo_id"));  // t <- t
        obj.set_asistencia(rd.getValue(Boolean.class, "asistencia"));  // t <- t
        obj.set_ficha_mecanica_id(rd.getValue(Integer.class, "ficha_mecanica_id"));  // t <- t
        return obj;
    }

    /*
        CR(U)D: turno
        Returns the number of affected rows or -1 on error.
     */
    public int update_turno(Turno p) throws Exception {
        String sql = "update turno set active=?, fecha=?, hora=?, mecanico_id=?, vehiculo_id=?, asistencia=?, ficha_mecanica_id=? where id=?";
        return ds.execDML(sql, p.get_active(), p.get_fecha(), p.get_hora(), p.get_mecanico_id(), p.get_vehiculo_id(), p.get_asistencia(), p.get_ficha_mecanica_id(), p.get_id());
    }

    /*
        CR(U)D: turno
        Returns the number of affected rows or -1 on error.
     */
    public int update_turno(Boolean active, LocalDate fecha, LocalDate hora, Integer mecanico_id, Integer vehiculo_id, Boolean asistencia, Integer ficha_mecanica_id, Integer id) throws Exception {
        String sql = "update turno set active=?, fecha=?, hora=?, mecanico_id=?, vehiculo_id=?, asistencia=?, ficha_mecanica_id=? where id=?";
        return ds.execDML(sql, active, fecha, hora, mecanico_id, vehiculo_id, asistencia, ficha_mecanica_id, id);
    }

    /*
        CRU(D): turno
        Returns the number of affected rows or -1 on error.
     */
    public int delete_turno(Integer id) throws Exception {
        String sql = "delete from turno where id=?";
        return ds.execDML(sql, id);
    }

    public List<Turno> get_turno_by_mecanico(Integer mecanico_id) throws Exception {
        String sql = "select * from turno where mecanico_id=?";
        final List<Turno> res = new ArrayList<Turno>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                Turno obj = new Turno();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t [INFO] SQL-shortcut
                obj.set_active(rd.getValue(Boolean.class, "active"));  // t <- t
                obj.set_fecha(rd.getValue(LocalDate.class, "fecha"));  // t <- t
                obj.set_hora(rd.getValue(LocalTime.class, "hora"));  // t <- t
                obj.set_mecanico_id(rd.getValue(Integer.class, "mecanico_id"));  // t <- t
                obj.set_vehiculo_id(rd.getValue(Integer.class, "vehiculo_id"));  // t <- t
                obj.set_asistencia(rd.getValue(Boolean.class, "asistencia"));  // t <- t
                obj.set_ficha_mecanica_id(rd.getValue(Integer.class, "ficha_mecanica_id"));  // t <- t
                res.add(obj);
            }
        }, mecanico_id);
        return res;
    }

    public List<Turno> get_turno_by_ficha_mecanica(Integer ficha_mecanica_id) throws Exception {
        String sql = "select * from turno where ficha_mecanica_id=?";
        final List<Turno> res = new ArrayList<Turno>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                Turno obj = new Turno();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t [INFO] SQL-shortcut
                obj.set_active(rd.getValue(Boolean.class, "active"));  // t <- t
                obj.set_fecha(rd.getValue(LocalDate.class, "fecha"));  // t <- t
                obj.set_hora(rd.getValue(LocalTime.class, "hora"));  // t <- t
                obj.set_mecanico_id(rd.getValue(Integer.class, "mecanico_id"));  // t <- t
                obj.set_vehiculo_id(rd.getValue(Integer.class, "vehiculo_id"));  // t <- t
                obj.set_asistencia(rd.getValue(Boolean.class, "asistencia"));  // t <- t
                obj.set_ficha_mecanica_id(rd.getValue(Integer.class, "ficha_mecanica_id"));  // t <- t
                res.add(obj);
            }
        }, ficha_mecanica_id);
        return res;
    }

    public List<Turno> get_turno_by_vehiculo(Integer vehiculo_id) throws Exception {
        String sql = "select * from turno where vehiculo_id=?";
        final List<Turno> res = new ArrayList<Turno>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                Turno obj = new Turno();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t [INFO] SQL-shortcut
                obj.set_active(rd.getValue(Boolean.class, "active"));  // t <- t
                obj.set_fecha(rd.getValue(LocalDate.class, "fecha"));  // t <- t
                obj.set_hora(rd.getValue(LocalTime.class, "hora"));  // t <- t
                obj.set_mecanico_id(rd.getValue(Integer.class, "mecanico_id"));  // t <- t
                obj.set_vehiculo_id(rd.getValue(Integer.class, "vehiculo_id"));  // t <- t
                obj.set_asistencia(rd.getValue(Boolean.class, "asistencia"));  // t <- t
                obj.set_ficha_mecanica_id(rd.getValue(Integer.class, "ficha_mecanica_id"));  // t <- t
                res.add(obj);
            }
        }, vehiculo_id);
        return res;
    }

    /*
        (C)RUD: vehiculo
        Returns the number of affected rows or -1 on error.
     */
    public int create_vehiculo(Vehiculo p) throws Exception {
        String sql = "insert into vehiculo (id, compania_seguro_id, cliente_id, numero_poliza, marca, patente) values (?, ?, ?, ?, ?, ?)";
        return ds.execDML(sql, p.get_id(), p.get_compania_seguro_id(), p.get_cliente_id(), p.get_numero_poliza(), p.get_marca(), p.get_patente());
    }

    /*
        (C)RUD: vehiculo
        Returns the number of affected rows or -1 on error.
     */
    public int create_vehiculo(Integer id, Integer compania_seguro_id, Integer cliente_id, String numero_poliza, String marca, String patente) throws Exception {
        String sql = "insert into vehiculo (id, compania_seguro_id, cliente_id, numero_poliza, marca, patente) values (?, ?, ?, ?, ?, ?)";
        return ds.execDML(sql, id, compania_seguro_id, cliente_id, numero_poliza, marca, patente);
    }

    /*
        C(R)UD: vehiculo
     */
    public List<Vehiculo> read_vehiculo_list() throws Exception {
        String sql = "select * from vehiculo";
        final List<Vehiculo> res = new ArrayList<Vehiculo>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                Vehiculo obj = new Vehiculo();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
                obj.set_compania_seguro_id(rd.getValue(Integer.class, "compania_seguro_id"));  // t <- t
                obj.set_cliente_id(rd.getValue(Integer.class, "cliente_id"));  // t <- t
                obj.set_numero_poliza(rd.getValue(String.class, "numero_poliza"));  // t <- t
                obj.set_marca(rd.getValue(String.class, "marca"));  // t <- t
                obj.set_patente(rd.getValue(String.class, "patente"));  // t <- t
                res.add(obj);
            }
        });
        return res;
    }

    /*
        C(R)UD: vehiculo
     */
    public Vehiculo read_vehiculo(Integer id) throws Exception {
        String sql = "select * from vehiculo where id=?";
        DataStore.RowData rd = ds.queryRow(sql, id);
        Vehiculo obj = new Vehiculo();
        obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t
        obj.set_compania_seguro_id(rd.getValue(Integer.class, "compania_seguro_id"));  // t <- t
        obj.set_cliente_id(rd.getValue(Integer.class, "cliente_id"));  // t <- t
        obj.set_numero_poliza(rd.getValue(String.class, "numero_poliza"));  // t <- t
        obj.set_marca(rd.getValue(String.class, "marca"));  // t <- t
        obj.set_patente(rd.getValue(String.class, "patente"));  // t <- t
        return obj;
    }

    /*
        CR(U)D: vehiculo
        Returns the number of affected rows or -1 on error.
     */
    public int update_vehiculo(Vehiculo p) throws Exception {
        String sql = "update vehiculo set compania_seguro_id=?, cliente_id=?, numero_poliza=?, marca=?, patente=? where id=?";
        return ds.execDML(sql, p.get_compania_seguro_id(), p.get_cliente_id(), p.get_numero_poliza(), p.get_marca(), p.get_patente(), p.get_id());
    }

    /*
        CR(U)D: vehiculo
        Returns the number of affected rows or -1 on error.
     */
    public int update_vehiculo(Integer compania_seguro_id, Integer cliente_id, String numero_poliza, String marca, String patente, Integer id) throws Exception {
        String sql = "update vehiculo set compania_seguro_id=?, cliente_id=?, numero_poliza=?, marca=?, patente=? where id=?";
        return ds.execDML(sql, compania_seguro_id, cliente_id, numero_poliza, marca, patente, id);
    }

    /*
        CRU(D): vehiculo
        Returns the number of affected rows or -1 on error.
     */
    public int delete_vehiculo(Integer id) throws Exception {
        String sql = "delete from vehiculo where id=?";
        return ds.execDML(sql, id);
    }

    public List<Vehiculo> get_vehiculo_by_cliente(Integer cliente_id) throws Exception {
        String sql = "select * from vehiculo where cliente_id=?";
        final List<Vehiculo> res = new ArrayList<Vehiculo>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                Vehiculo obj = new Vehiculo();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t [INFO] SQL-shortcut
                obj.set_compania_seguro_id(rd.getValue(Integer.class, "compania_seguro_id"));  // t <- t
                obj.set_cliente_id(rd.getValue(Integer.class, "cliente_id"));  // t <- t
                obj.set_numero_poliza(rd.getValue(String.class, "numero_poliza"));  // t <- t
                obj.set_marca(rd.getValue(String.class, "marca"));  // t <- t
                obj.set_patente(rd.getValue(String.class, "patente"));  // t <- t
                res.add(obj);
            }
        }, cliente_id);
        return res;
    }

    public List<Vehiculo> get_vehiculo_by_compania_seguro(Integer compania_seguro_id) throws Exception {
        String sql = "select * from vehiculo where compania_seguro_id=?";
        final List<Vehiculo> res = new ArrayList<Vehiculo>();
        ds.queryAllRows(sql, new DataStore.RowHandler() {
            @Override
            public void handleRow(DataStore.RowData rd) throws Exception {
                Vehiculo obj = new Vehiculo();
                obj.set_id(rd.getValue(Integer.class, "id"));  // t <- t [INFO] SQL-shortcut
                obj.set_compania_seguro_id(rd.getValue(Integer.class, "compania_seguro_id"));  // t <- t
                obj.set_cliente_id(rd.getValue(Integer.class, "cliente_id"));  // t <- t
                obj.set_numero_poliza(rd.getValue(String.class, "numero_poliza"));  // t <- t
                obj.set_marca(rd.getValue(String.class, "marca"));  // t <- t
                obj.set_patente(rd.getValue(String.class, "patente"));  // t <- t
                res.add(obj);
            }
        }, compania_seguro_id);
        return res;
    }
}
