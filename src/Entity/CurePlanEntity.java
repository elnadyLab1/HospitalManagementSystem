package Entity;

public class CurePlanEntity {
    private int id;
    private int doctorId;
    private String operationId;
    private String duration;
    private String cureName;

    public CurePlanEntity(int id, int doctorId, String operationId, String duration, String cureName) {
        this.id = id;
        this.doctorId = doctorId;
        this.operationId = operationId;
        this.duration = duration;
        this.cureName = cureName;
    }

    public CurePlanEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDoctorId() {
        return doctorId;
    }

    public void setDoctorId(int doctorId) {
        this.doctorId = doctorId;
    }

    public String getOperationId() {
        return operationId;
    }

    public void setOperationId(String operationId) {
        this.operationId = operationId;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getCureName() {
        return cureName;
    }

    public void setCureName(String cureName) {
        this.cureName = cureName;
    }
}