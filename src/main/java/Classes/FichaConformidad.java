package Classes;
public class FichaConformidad {
    private int id;
    private FichaMecanica fichaMecanica;
    private boolean fichaConforme;
    private String motivosDisconforme;

    public FichaConformidad() {
    }
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    public boolean getFichaConforme(){
        return fichaConforme;
    }
    public boolean setFichaConforme(boolean fichaConforme){
        return fichaConforme;
    }

    public String getMotivosDisconforme() {
        return motivosDisconforme;
    }
    public void setMotivosDisconforme(String motivosDisconforme) {
        this.motivosDisconforme = motivosDisconforme;
    }
}
