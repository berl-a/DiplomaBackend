package system.model;

import system.database.StringDatabaseEntry;

import java.util.LinkedList;

public class HospitationPlan {

    private int id;
    private boolean zatwierdzony;
    private String wydzial;
    private int rok, semester;
    private LinkedList<Hospitation> hospitations;

    public HospitationPlan(LinkedList<StringDatabaseEntry> hospitationDatabaseEntries) {

    }

    public HospitationPlan(int id, boolean zatwierdzony, String wydzial, int rok, int semester, LinkedList<Hospitation> hospitations) {
        this.id = id;
        this.zatwierdzony = zatwierdzony;
        this.wydzial = wydzial;
        this.rok = rok;
        this.semester = semester;
        this.hospitations = hospitations;
    }

    public HospitationPlan(boolean zatwierdzony, String wydzial, int rok, int semester, LinkedList<Hospitation> hospitations) {
        this.zatwierdzony = zatwierdzony;
        this.wydzial = wydzial;
        this.rok = rok;
        this.semester = semester;
        this.hospitations = hospitations;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isZatwierdzony() {
        return zatwierdzony;
    }

    public void setZatwierdzony(boolean zatwierdzony) {
        this.zatwierdzony = zatwierdzony;
    }

    public String getWydzial() {
        return wydzial;
    }

    public void setWydzial(String wydzial) {
        this.wydzial = wydzial;
    }

    public int getRok() {
        return rok;
    }

    public void setRok(int rok) {
        this.rok = rok;
    }

    public int getSemester() {
        return semester;
    }

    public void setSemester(int semester) {
        this.semester = semester;
    }

    public LinkedList<Hospitation> getHospitations() {
        return hospitations;
    }

    public void setHospitations(LinkedList<Hospitation> hospitations) {
        this.hospitations = hospitations;
    }

    @Override
    public String toString() {
        return "HospitationPlan{" +
                "id=" + id +
                ", zatwierdzony=" + zatwierdzony +
                ", wydzial='" + wydzial + '\'' +
                ", rok=" + rok +
                ", semester=" + semester +
                ", hospitations=" + hospitations +
                '}';
    }
}
