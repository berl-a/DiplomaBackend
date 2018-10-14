package system.model;

import system.database.StringDatabaseEntry;

import java.util.LinkedList;

public class Teacher {

    private int id, przewdoniczacy, hospitowany;
    private String name, surname, profiles, tytul, wydzial;
    private LinkedList<Integer> komisjeHospitacyjne, protokolyHospitacji, hospitacje;

    public Teacher(StringDatabaseEntry teacherDatabaseEntry) {
        this.id = Integer.valueOf(teacherDatabaseEntry.getField("NauczycielID"));
        this.name = teacherDatabaseEntry.getField("Imie");
        this.surname = teacherDatabaseEntry.getField("Nazwisko");
        this.profiles = teacherDatabaseEntry.getField("Profile");
        this.tytul = teacherDatabaseEntry.getField("Tytul");
        this.wydzial = teacherDatabaseEntry.getField("Wydział");
//        this.przewdoniczacy = Integer.valueOf(teacherDatabaseEntry.getField("Przewodniczacy")); TEACHER DATABASE ENTRY DOESN'T CONTAIN THAT FIELD YET
    }

    public Teacher(int id, String name, String surname, String profiles, String tytul, String wydzial, int przewdoniczacy, int hospitowany) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profiles = profiles;
        this.tytul = tytul;
        this.wydzial = wydzial;
        this.przewdoniczacy = przewdoniczacy;
        this.hospitowany = hospitowany;
    }

    public Teacher(int id, String name, String surname, String profiles, String tytul, String wydzial, int przewdoniczacy, int hospitowany, LinkedList<Integer> komisjeHospitacyjne, LinkedList<Integer> protokolyHospitacji, LinkedList<Integer> hospitacje) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.profiles = profiles;
        this.tytul = tytul;
        this.wydzial = wydzial;
        this.przewdoniczacy = przewdoniczacy;
        this.hospitowany = hospitowany;
        this.komisjeHospitacyjne = komisjeHospitacyjne;
        this.protokolyHospitacji = protokolyHospitacji;
        this.hospitacje = hospitacje;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getProfiles() {
        return profiles;
    }

    public void setProfiles(String profiles) {
        this.profiles = profiles;
    }

    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    public String getWydzial() {
        return wydzial;
    }

    public void setWydzial(String wydzial) {
        this.wydzial = wydzial;
    }

    public LinkedList<Integer> getKomisjeHospitacyjne() {
        return komisjeHospitacyjne;
    }

    public void setKomisjeHospitacyjne(LinkedList<Integer> komisjeHospitacyjne) {
        this.komisjeHospitacyjne = komisjeHospitacyjne;
    }

    public LinkedList<Integer> getProtokolyHospitacji() {
        return protokolyHospitacji;
    }

    public void setProtokolyHospitacji(LinkedList<Integer> protokolyHospitacji) {
        this.protokolyHospitacji = protokolyHospitacji;
    }

    public LinkedList<Integer> getHospitacje() {
        return hospitacje;
    }

    public void setHospitacje(LinkedList<Integer> hospitacje) {
        this.hospitacje = hospitacje;
    }

    public int getPrzewdoniczacy() {
        return przewdoniczacy;
    }

    public void setPrzewdoniczacy(int przewdoniczacy) {
        this.przewdoniczacy = przewdoniczacy;
    }

    public int getHospitowany() {
        return hospitowany;
    }

    public void setHospitowany(int hospitowany) {
        this.hospitowany = hospitowany;
    }

    @Override
    public String toString() {
        return "Teacher{" +
                "id=" + id +
                ", przewdoniczacy=" + przewdoniczacy +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", profiles='" + profiles + '\'' +
                ", tytul='" + tytul + '\'' +
                ", wydzial='" + wydzial + '\'' +
                ", komisjeHospitacyjne=" + komisjeHospitacyjne +
                ", protokolyHospitacji=" + protokolyHospitacji +
                ", hospitacje=" + hospitacje +
                '}';
    }
}
