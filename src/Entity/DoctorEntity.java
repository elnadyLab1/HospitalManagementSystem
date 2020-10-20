package Entity;

import java.util.Date;

public class DoctorEntity extends WorkersEntity{
    private double salary;
    private int appointmentId;
    private Date startDate;
    private Date endDate;

    public DoctorEntity() {
    }

    public DoctorEntity(double salary, int appointmentId, Date startDate, Date endDate) {
        this.salary = salary;
        this.appointmentId = appointmentId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public DoctorEntity(int id, String name, String surname, String phoneNumber,String password, boolean status, double salary, int appointmentId, Date startDate, Date endDate) {
        super(id, name, surname, phoneNumber,password, status);
        this.salary = salary;
        this.appointmentId = appointmentId;
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public double getSalary() {
        return salary;
    }

    public void setSalary(double salary) {
        this.salary = salary;
    }

    public int getAppointmentId() {
        return appointmentId;
    }

    public void setAppointmentId(int appointmentId) {
        this.appointmentId = appointmentId;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    public Date getEndDate() {
        return endDate;
    }

    public void setEndDate(Date endDate) {
        this.endDate = endDate;
    }

    @Override
    String getPersonType() {
        return "Doctor";
    }
}
