package Service;

import Entity.PatientEntity;
import Payment.IPayStrategy;

import java.util.ArrayList;

public interface IPatientService {
    PatientEntity getPatientById(int _id);
    PatientEntity savePatient(PatientEntity _patient);
    ArrayList<PatientEntity> getAllPatients();
    boolean deletePatient(PatientEntity _patient);
    void makePayment(PatientEntity _patient, int _amount, IPayStrategy _paymentGate);
    ArrayList<PatientEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<PatientEntity> _patientList);
}
