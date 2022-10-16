package Classes;
public class FichaConformidad {
    public FichaConformidad(int id) {
        this.id = id;
    }

    public FichaConformidad(int id, boolean firmaConforme) {
        this.id = id;
        this.firmada = true;
        this.firmaConforme = firmaConforme;
    }

    public FichaConformidad(int id, boolean firmaConforme, String motivosDisconforme) {
        this.id = id;
        this.firmada = true;
        this.firmaConforme = firmaConforme;
        this.motivosDisconforme = motivosDisconforme;
    }

    public FichaConformidad(int id, boolean firmaConforme, boolean firmada, String motivosDisconforme) {
        this.id = id;
        this.firmada = firmada;
        this.firmaConforme = firmaConforme;
        this.motivosDisconforme = motivosDisconforme;
    }

    private int id;
    public int getId() {
        return id;
    }
    public void setId(int id) {
        this.id = id;
    }

    private String motivosDisconforme;
    public String getMotivosDisconforme() {
        return motivosDisconforme;
    }
    public void setMotivosDisconforme(String motivosDisconforme) {
        this.motivosDisconforme = motivosDisconforme;
    }

    private boolean firmaConforme;
    public boolean isFirmaConforme() {
        return firmaConforme;
    }
    public void setFirmaConforme(boolean firmaConforme) {
        this.firmaConforme = firmaConforme;
    }

    private boolean firmada;

    public boolean isFirmada() {
        return firmada;
    }

    public void setFirmada(boolean firmada) {
        this.firmada = firmada;
    }
}
