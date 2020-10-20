package Entity;

import java.util.ArrayList;
import java.util.Date;

public class HospitalEntity {
    private int id;
    private Date openAt;
    private Date closeAt;
    private ArrayList<OperationEntity> operationList;
    private String appointmentName;

    public HospitalEntity(int id, Date openAt, Date closeAt, ArrayList<OperationEntity> operationList,String appointmentName) {
        this.id = id;
        this.openAt = openAt;
        this.closeAt = closeAt;
        this.operationList = operationList;
        this.appointmentName = appointmentName;
    }

    public HospitalEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Date getOpenAt() {
        return openAt;
    }

    public void setOpenAt(Date openAt) {
        this.openAt = openAt;
    }

    public Date getCloseAt() {
        return closeAt;
    }

    public void setCloseAt(Date closeAt) {
        this.closeAt = closeAt;
    }

    public ArrayList<OperationEntity> getOperationList() {
        return operationList;
    }

    public void setOperationList(ArrayList<OperationEntity> operationList) {
        this.operationList = operationList;
    }

    public String getAppointmentName() {
        return appointmentName;
    }

    public void setAppointmentName(String appointmentName) {
        this.appointmentName = appointmentName;
    }
}
