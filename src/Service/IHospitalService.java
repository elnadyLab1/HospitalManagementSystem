package Service;

import Entity.HospitalEntity;

import java.util.ArrayList;

public interface IHospitalService {
    HospitalEntity getHospitalById(int _id);
    HospitalEntity saveHospital(HospitalEntity _hospital);
    ArrayList<HospitalEntity> getAllHospitals();
    boolean deleteHospital(HospitalEntity _hospital);
    ArrayList<HospitalEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<HospitalEntity> _hospitalList);
}
