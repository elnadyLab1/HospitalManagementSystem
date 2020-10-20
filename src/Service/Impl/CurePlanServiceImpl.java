package Service.Impl;

import Entity.PatientEntity;
import Entity.CurePlanEntity;
import Entity.DoctorEntity;
import Repository.IDataAccess;
import Service.ICureService;

import java.util.ArrayList;

public class CurePlanServiceImpl implements ICureService {
    private IDataAccess repository;

    public CurePlanServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public CurePlanEntity getCurePlanById(int _id) {
        ArrayList<CurePlanEntity> curePlanAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(curePlanAsList.isEmpty()){
            return null;
        }
        return curePlanAsList.get(0);
    }

    @Override
    public CurePlanEntity saveCurePlan(CurePlanEntity _curePlan) {
        ArrayList<String> txtList = repository.getAll();
        ArrayList<CurePlanEntity> curePlanList = this.convertStringListToEntityList(txtList);
        boolean isUpdated = false;
        for (CurePlanEntity curePlan : curePlanList) {
            if (curePlan.getId() == _curePlan.getId()) {
                curePlan.setDoctorId(_curePlan.getDoctorId());
                curePlan.setOperationId(_curePlan.getOperationId());
                curePlan.setDuration(_curePlan.getDuration());
                curePlan.setCureName(_curePlan.getCureName());
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            int id = curePlanList.size() == 0 ? 1 : (curePlanList.get(curePlanList.size() - 1).getId() + 1);
            _curePlan.setId(id);
            curePlanList.add(_curePlan);
        }
        this.repository.save(this.convertEntityListToStringList(curePlanList));
        return _curePlan;
    }

    @Override
    public ArrayList<CurePlanEntity> getAllCurePlans() {
        if (this.repository != null) {
            ArrayList<String> curePlanList = this.repository.getAll();
            if (!curePlanList.isEmpty()) {
                return this.convertStringListToEntityList(curePlanList);
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteCurePlan(CurePlanEntity _curePlan) {
        return this.repository.delete(_curePlan.getId());
    }

    @Override
    public ArrayList<CurePlanEntity> findDoctorCurePlan(DoctorEntity _doctor) {
        ArrayList<CurePlanEntity> allCurePlans = this.getAllCurePlans();
        ArrayList<CurePlanEntity> doctorPlans = new ArrayList<CurePlanEntity>();
        for(CurePlanEntity cure : allCurePlans){
            if(cure.getDoctorId() == _doctor.getId()){
                doctorPlans.add(cure);
            }
        }
        return doctorPlans;
    }

    @Override
    public ArrayList<CurePlanEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<CurePlanEntity> curePlanList = new ArrayList<CurePlanEntity>();
        for (String item : _strList) {
            CurePlanEntity curePlan = new CurePlanEntity(
                    Integer.parseInt(item.split("\t")[0]),
                    Integer.parseInt(item.split("\t")[1]),
                    item.split("\t")[2],
                    item.split("\t")[3],
                    item.split("\t")[4]
            );
            curePlanList.add(curePlan);
        }
        return curePlanList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<CurePlanEntity> _curePlanList) {
        ArrayList<String> strList = new ArrayList<String>();
        for (CurePlanEntity curePlan : _curePlanList) {
            strList.add(
                    curePlan.getId() + "\t" +
                            curePlan.getDoctorId() + "\t" +
                            curePlan.getOperationId() + "\t" +
                            curePlan.getDuration() + "\t"+
                            curePlan.getCureName() + "\n"
            );
        }
        return strList;
    }
}
