package Entity;

import java.util.Date;

public class PatientListEntity {
    private int id;
    private Date endDate;
    private int patientId;
    private int doctorId;
    private int cureId;

    public PatientListEntity(int id, Date endDate, int patientId, int doctorId, int cureId) {
        this.id = id;
        this.endDate = endDate;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.cureId = cureId;
    }

    public PatientListEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    public int getPatientId() {
        return patientId;
    }

    public void setPatientId(int patientId) {
        this.patientId = patientId;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public int getCureId() {
        return cureId;
    }

    public void setCureId(int cureId) {
        this.cureId = cureId;
    }
}
