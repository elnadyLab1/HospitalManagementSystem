package Service;

import Entity.PatientEntity;
import Entity.CurePlanEntity;
import Entity.DoctorEntity;

import java.util.ArrayList;

public interface ICureService {
    CurePlanEntity getCurePlanById(int _id);
    CurePlanEntity saveCurePlan(CurePlanEntity _curePlan);
    ArrayList<CurePlanEntity> getAllCurePlans();
    boolean deleteCurePlan(CurePlanEntity _curePlan);
    ArrayList<CurePlanEntity> findDoctorCurePlan(DoctorEntity _doctor);
    ArrayList<CurePlanEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<CurePlanEntity> _curePlanList);
}
