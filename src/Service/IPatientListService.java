package Service;

import Entity.PatientEntity;
import Entity.PatientListEntity;

import java.util.ArrayList;

public interface IPatientListService {
    PatientListEntity savePatientList(PatientListEntity _patientList);
    PatientListEntity getPatientListById(int _id);
    ArrayList<PatientListEntity> getAllPatientLists();
    ArrayList<PatientListEntity> getPPatientList(PatientEntity _patient);
    boolean deletePatientList(PatientListEntity _doctor);
    ArrayList<PatientListEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<PatientListEntity> _Listpatient);
}
