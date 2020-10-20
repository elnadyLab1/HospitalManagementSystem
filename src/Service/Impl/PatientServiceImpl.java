package Service.Impl;

import Entity.PatientEntity;
import Payment.IPayStrategy;
import Repository.IDataAccess;
import Service.IPatientService;
import Service.IWorkerService;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

public class PatientServiceImpl implements IPatientService, IWorkerService {
    private IDataAccess repository;

    public PatientServiceImpl(IDataAccess _repository) {
        this.repository = _repository;
    }

    @Override
    public PatientEntity getPatientById(int _id) {
        ArrayList<PatientEntity> patientAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(patientAsList.isEmpty()){
            return null;
        }
        return patientAsList.get(0);
    }

    @Override
    public PatientEntity savePatient(PatientEntity _patient) {
        ArrayList<String> txtList = repository.getAll();
        ArrayList<PatientEntity> patientList = this.convertStringListToEntityList(txtList);
        boolean isUpdated = false;
        for (PatientEntity patient : patientList) {
            if (patient.getId() == _patient.getId()) {
                patient.setName(_patient.getName());
                patient.setSurname(_patient.getSurname());
                patient.setPhoneNumber(_patient.getPhoneNumber());
                patient.setPassword(_patient.getPassword());
                patient.setStatus(_patient.isStatus());
                patient.setAddress(_patient.getAddress());
                patient.setEmail(_patient.getEmail());
                patient.setPayAmount(_patient.getPayAmount());
                patient.setPayMethodCode(_patient.getPayMethodCode());
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            int id = patientList.size() == 0 ? 1 : (patientList.get(patientList.size() - 1).getId() + 1);
            _patient.setId(id);
            patientList.add(_patient);
        }
        this.repository.save(this.convertEntityListToStringList(patientList));
        return _patient;
    }

    @Override
    public ArrayList<PatientEntity> getAllPatients() {
        if (this.repository != null) {
            ArrayList<String> patientList = this.repository.getAll();
            if (!patientList.isEmpty()) {
                return this.convertStringListToEntityList(patientList);
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean deletePatient(PatientEntity _patient) {
        return this.repository.delete(_patient.getId());
    }

    @Override
    public void makePayment(PatientEntity _patient, int _amount, IPayStrategy _paymentGate) {
        _patient.setPayMethodCode(_paymentGate.pay(_amount));
        _patient.setPayAmount(_amount);
    }

    @Override
    public ArrayList<PatientEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<PatientEntity> patientList = new ArrayList<PatientEntity>();
        for (String item : _strList) {
            PatientEntity patient = new PatientEntity(
                    Integer.parseInt(item.split("\t")[0]),
                    item.split("\t")[1],
                    item.split("\t")[2],
                    item.split("\t")[3],
                    item.split("\t")[4],
                    Boolean.parseBoolean(item.split("\t")[5]),
                    item.split("\t")[6],
                    item.split("\t")[7],
                    Integer.parseInt(item.split("\t")[8]),
                    item.split("\t")[9]
            );
            patientList.add(patient);
        }
        return patientList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<PatientEntity> _patientList) {
        ArrayList<String> strList = new ArrayList<String>();
        for (PatientEntity patient : _patientList) {
            strList.add(
                    patient.getId() + "\t" +
                            patient.getName() + "\t" +
                            patient.getSurname() + "\t" +
                            patient.getPhoneNumber() + "\t" +
                            patient.getPassword() + "\t" +
                            patient.isStatus() + "\t" +
                            patient.getAddress() + "\t" +
                            patient.getEmail() + "\t" +
                            patient.getPayAmount() + "\t"+
                            patient.getPayMethodCode() + "\n"
            );
        }
        return strList;
    }

    @Override
    public Object login(String _name, String _password) {
        for(PatientEntity patient:this.getAllPatients()){
            if(patient.getName().equals(_name) && patient.getPassword().equals(_password)){
                return patient;
            }
        }
        return null;
    }
}
