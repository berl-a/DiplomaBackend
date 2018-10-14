package system.model;

import org.springframework.beans.factory.annotation.Autowired;
import system.controller.HospitationController;
import system.database.StringDatabaseEntry;
import system.service.HospitationService;

import java.util.HashMap;
import java.util.LinkedList;

public class Hospitation {

    @Autowired
    HospitationService hospitationService;

    private int id, planHospId;
    private boolean wykonana, pelna;

    private Teacher hospitowany;
    private LinkedList<Teacher> hospitujace;
    private Zajecia zajecia;

    public Hospitation(int id, int planHospId, boolean wykonana, boolean pelna, Teacher hospitowany, LinkedList<Teacher> hospitujace, Zajecia zajecia) {
        this.id = id;
        this.planHospId = planHospId;
        this.wykonana = wykonana;
        this.pelna = pelna;
        this.hospitowany = hospitowany;
        this.hospitujace = hospitujace;
        this.zajecia = zajecia;
    }

    public Hospitation(StringDatabaseEntry hospitationDatabaseEntry) {
        this.id = Integer.valueOf(hospitationDatabaseEntry.getField("ID"));
        this.planHospId = Integer.valueOf(hospitationDatabaseEntry.getField("Plan hospitacjiID"));
        this.wykonana = Boolean.valueOf(hospitationDatabaseEntry.getField("Wykonana"));
        this.pelna = Boolean.valueOf(hospitationDatabaseEntry.getField("Pelna"));
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPlanHospId() {
        return planHospId;
    }

    public void setPlanHospId(int planHospId) {
        this.planHospId = planHospId;
    }


    public boolean isWykonana() {
        return wykonana;
    }

    public void setWykonana(boolean wykonana) {
        this.wykonana = wykonana;
    }

    public boolean isPelna() {
        return pelna;
    }

    public void setPelna(boolean pelna) {
        this.pelna = pelna;
    }

    public HospitationService getHospitationService() {
        return hospitationService;
    }

    public void setHospitationService(HospitationService hospitationService) {
        this.hospitationService = hospitationService;
    }

    public Teacher getHospitowany() {
        return hospitowany;
    }

    public void setHospitowany(Teacher hospitowany) {
        this.hospitowany = hospitowany;
    }

    public LinkedList<Teacher> getHospitujace() {
        return hospitujace;
    }

    public void setHospitujace(LinkedList<Teacher> hospitujace) {
        this.hospitujace = hospitujace;
    }

    public Zajecia getZajecia() {
        return zajecia;
    }

    public void setZajecia(Zajecia zajecia) {
        this.zajecia = zajecia;
    }

    @Override
    public String toString() {
        return "Hospitation{" +
                "hospitationService=" + hospitationService +
                ", id=" + id +
                ", planHospId=" + planHospId +
                ", wykonana=" + wykonana +
                ", pelna=" + pelna +
                ", hospitowany=" + hospitowany +
                ", hospitujace=" + hospitujace +
                ", zajecia=" + zajecia +
                '}';
    }
}
