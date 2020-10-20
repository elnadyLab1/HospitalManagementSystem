package Service.Impl;

import Entity.PatientEntity;
import Entity.PatientListEntity;
import Repository.IDataAccess;
import Service.IPatientListService;

import java.lang.reflect.Array;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class PatientListServiceImpl implements IPatientListService {
    private IDataAccess repository;

    public PatientListServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public PatientListEntity savePatientList(PatientListEntity _patientList) {
        ArrayList<PatientListEntity> patientListEntityArrayList = this.convertStringListToEntityList(this.repository.getAll());
        boolean isUpdated = false;
        for (PatientListEntity patientList : patientListEntityArrayList){
            if(patientList.getId() == _patientList.getId()){
                patientList.setEndDate(_patientList.getEndDate());
                patientList.setPatientId(_patientList.getPatientId());
                patientList.setDoctorId(_patientList.getDoctorId());
                patientList.setCureId(_patientList.getCureId());
                isUpdated = true;
            }
        }
        if(!isUpdated){
            int id = patientListEntityArrayList.size() == 0 ? 1 : (patientListEntityArrayList.get(patientListEntityArrayList.size() - 1).getId() + 1);
            _patientList.setId(id);
            patientListEntityArrayList.add(_patientList);
        }
        this.repository.save(this.convertEntityListToStringList(patientListEntityArrayList));
        return _patientList;
    }

    @Override
    public PatientListEntity getPatientListById(int _id) {
        ArrayList<PatientListEntity> patientListsAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(patientListsAsList.isEmpty()){
            return null;
        }
        return patientListsAsList.get(0);
    }

    @Override
    public ArrayList<PatientListEntity> getAllPatientLists() {
        return this.convertStringListToEntityList(
                this.repository.getAll()
        );
    }

    @Override
    public ArrayList<PatientListEntity> getPPatientList(PatientEntity _patientList) {
        ArrayList<PatientListEntity> allPatientLists = this.getAllPatientLists();
        ArrayList<PatientListEntity> PPatientList = new ArrayList<>();
        for(PatientListEntity patientListEntity : allPatientLists){
            if(patientListEntity.getPatientId() == _patientList.getId()){
                PPatientList.add(patientListEntity);
            }
        }
        return PPatientList;
    }

    @Override
    public boolean deletePatientList(PatientListEntity _patientList) {
        return this.repository.delete(_patientList.getId());
    }

    @Override
    public ArrayList<PatientListEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<PatientListEntity> Listpatient = new ArrayList<PatientListEntity>();
        for (String item : _strList) {
            PatientListEntity patientList = null;
            patientList = new PatientListEntity(
                    Integer.parseInt(item.split("\t")[0]),
                    new Date(Long.parseLong(item.split("\t")[1])),
                    Integer.parseInt(item.split("\t")[2]),
                    Integer.parseInt(item.split("\t")[3]),
                    Integer.parseInt(item.split("\t")[4])
            );
            Listpatient.add(patientList);
        }
        return Listpatient;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<PatientListEntity> _Listpatient) {
        ArrayList<String> strList = new ArrayList<String>();
        SimpleDateFormat format = new SimpleDateFormat("dd/mm/yyyy");
        for (PatientListEntity patientList : _Listpatient) {
            strList.add(
                    patientList.getId() + "\t" +
                            patientList.getEndDate().getTime() + "\t"+
                            patientList.getPatientId() + "\t"+
                            patientList.getDoctorId() + "\t"+
                            patientList.getCureId() + "\n"
            );
        }
        return strList;
    }
}
