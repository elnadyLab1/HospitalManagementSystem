package Service.Impl;

import Entity.DoctorEntity;
import Repository.IDataAccess;
import Service.IWorkerService;
import Service.IDoctorService;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class DoctorServiceImpl implements IDoctorService, IWorkerService {
    private IDataAccess repository;

    public DoctorServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public DoctorEntity saveDoctor(DoctorEntity _doctor) {
        ArrayList<DoctorEntity> doctorEntityArrayList = this.convertStringListToEntityList(this.repository.getAll());
        boolean isUpdated = false;
        SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
        for (DoctorEntity doctor : doctorEntityArrayList){
            if(doctor.getId() == _doctor.getId()){
                doctor.setName(_doctor.getName());
                doctor.setSurname(_doctor.getSurname());
                doctor.setPhoneNumber(_doctor.getPhoneNumber());
                doctor.setPassword(_doctor.getPassword());
                doctor.setStatus(_doctor.isStatus());
                doctor.setSalary(_doctor.getSalary());
                doctor.setAppointmentId(_doctor.getAppointmentId());
                doctor.setStartDate(_doctor.getStartDate());
                doctor.setEndDate(_doctor.getEndDate());
                isUpdated = true;
            }
        }
        if(!isUpdated){
            int id = doctorEntityArrayList.size() == 0 ? 1 : (doctorEntityArrayList.get(doctorEntityArrayList.size() - 1).getId() + 1);
            _doctor.setId(id);
            doctorEntityArrayList.add(_doctor);
        }
        this.repository.save(this.convertEntityListToStringList(doctorEntityArrayList));
        return _doctor;
    }

    @Override
    public DoctorEntity getDoctorById(int _id) {
        ArrayList<DoctorEntity> doctorsAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(doctorsAsList.isEmpty()){
            return null;
        }
        return doctorsAsList.get(0);
    }

    @Override
    public ArrayList<DoctorEntity> getAllDoctors() {
        return this.convertStringListToEntityList(
                this.repository.getAll()
        );
    }

    @Override
    public boolean deleteDoctor(DoctorEntity _doctor) {
        return this.repository.delete(_doctor.getId());
    }

    @Override
    public ArrayList<DoctorEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<DoctorEntity> patientList = new ArrayList<DoctorEntity>();
        for (String item : _strList) {
            DoctorEntity patient = null;

            patient = new DoctorEntity(
                    Integer.parseInt(item.split("\t")[0]),
                    item.split("\t")[1],
                    item.split("\t")[2],
                    item.split("\t")[3],
                    item.split("\t")[4],
                    Boolean.parseBoolean(item.split("\t")[5]),
                    Double.parseDouble(item.split("\t")[6]),
                    Integer.parseInt(item.split("\t")[7]),
                    new Date(Long.parseLong(item.split("\t")[8])),
                    new Date(Long.parseLong(item.split("\t")[9]))
            );

            patientList.add(patient);
        }
        return patientList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<DoctorEntity> _doctorList) {
        ArrayList<String> strList = new ArrayList<String>();
        for (DoctorEntity doctor : _doctorList) {
            strList.add(
                    doctor.getId() + "\t" +
                            doctor.getName() + "\t" +
                            doctor.getSurname() + "\t" +
                            doctor.getPhoneNumber() + "\t" +
                            doctor.getPassword() + "\t" +
                            doctor.isStatus() + "\t" +
                            doctor.getSalary() + "\t" +
                            doctor.getAppointmentId() + "\t" +
                            doctor.getStartDate().getTime() + "\t" +
                            doctor.getEndDate().getTime() + "\n"
            );
        }
        return strList;
    }

    @Override
    public Object login(String _name, String _password) {
        ArrayList<DoctorEntity> doctorEntities = this.getAllDoctors();
        for (DoctorEntity entity : doctorEntities){
            if(entity.getName().equals(_name) && entity.getPassword().equals(_password)){
                return entity;
            }
        }
        return null;
    }
}
