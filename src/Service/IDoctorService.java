package Service;

import Entity.PatientEntity;
import Entity.DoctorEntity;

import java.util.ArrayList;

public interface IDoctorService {
    DoctorEntity saveDoctor(DoctorEntity _doctor);
    DoctorEntity getDoctorById(int _id);
    ArrayList<DoctorEntity> getAllDoctors();
    boolean deleteDoctor(DoctorEntity _doctor);
    ArrayList<DoctorEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<DoctorEntity> _doctorList);
}
