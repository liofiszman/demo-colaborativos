package Classes;

public class Arreglo {

    public Arreglo(String fecha, String cliente,String patente, String mecanico, String especialidad,
                   String conformidad, String servicio, String compania) {
        setCliente(cliente);
        setPatente(patente);
        setMecanico(mecanico);
        setConformidad(conformidad);
        setFecha(fecha);
        setServicio(servicio);
        setCompania(compania);
        setEspecialidad(especialidad);
    }

    public Arreglo(DTO.Turno turno, DTO.Mecanico mecanico, DTO.FichaConformidad fichaConformidad,
                   DTO.FichaMecanica fichaMecanica, DTO.Vehiculo vehiculo, DTO.CompaniaSeguro companiaSeguro)
    {
        setPatente(vehiculo.get_patente());
        setMecanico(mecanico.get_nombre());
        if(fichaConformidad.get_firmada_conforme())
            setConformidad("Conforme");
        else
            setConformidad("No conforme");
        setFecha(turno.get_fecha().toString());
        setServicio(fichaMecanica.get_actividades());
        setCompania(companiaSeguro.getNombre());
        setEspecialidad(mecanico.get_especialidad());
    }

    private String Especialidad;

    public void setEspecialidad(String value) { Especialidad = value; }
    public String getEspecialidad() { return Especialidad; }

    private String Compania;

    public void setCompania(String value) { Compania = value; }
    public String getCompania() { return Compania; }

    private String Servicio;

    public void setServicio(String value) { Servicio = value; }
    public String getServicio() { return Servicio; }

    private String Fecha;

    public void setFecha(String value) { Fecha = value; }
    public String getFecha() { return Fecha; }

    private String Cliente;

    public void setCliente(String value) { Cliente = value; }
    public String getCliente() { return Cliente; }

    private String Mecanico;
    public void setMecanico(String value) { Mecanico = value; }
    public String getMecanico() { return Mecanico; }

    private String Conformidad;
    public void setConformidad(String value) { Conformidad = value; }
    public String getConformidad() { return Conformidad; }

    private String Patente;

    public String getPatente() {
        return Patente;
    }

    public void setPatente(String patente) {
        Patente = patente;
    }
}

