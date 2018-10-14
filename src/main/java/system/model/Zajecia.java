package system.model;

public class Zajecia {

    private int zajeciaId;
    private String nazwaPrzedmiotu, data, czas, profil;
    private FormaZajec formaZajec;

    public Zajecia(int zajeciaId, String nazwaPrzedmiotu, String data, String czas, String profil, FormaZajec formaZajec) {
        this.zajeciaId = zajeciaId;
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
        this.data = data;
        this.czas = czas;
        this.profil = profil;
        this.formaZajec = formaZajec;
    }

    public int getZajeciaId() {
        return zajeciaId;
    }

    public void setZajeciaId(int zajeciaId) {
        this.zajeciaId = zajeciaId;
    }

    public String getNazwaPrzedmiotu() {
        return nazwaPrzedmiotu;
    }

    public void setNazwaPrzedmiotu(String nazwaPrzedmiotu) {
        this.nazwaPrzedmiotu = nazwaPrzedmiotu;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public String getCzas() {
        return czas;
    }

    public void setCzas(String czas) {
        this.czas = czas;
    }

    public String getProfil() {
        return profil;
    }

    public void setProfil(String profil) {
        this.profil = profil;
    }

    public FormaZajec getFormaZajec() {
        return formaZajec;
    }

    public void setFormaZajec(FormaZajec formaZajec) {
        this.formaZajec = formaZajec;
    }
}
