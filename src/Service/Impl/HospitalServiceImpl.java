package Service.Impl;

import Entity.OperationEntity;
import Entity.HospitalEntity;
import Factory.DataAccessFactory;
import Repository.IDataAccess;
import Service.IHospitalService;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class HospitalServiceImpl implements IHospitalService {
    private IDataAccess repository;

    public HospitalServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public HospitalEntity getHospitalById(int _id) {
        ArrayList<HospitalEntity> hospitalAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if (hospitalAsList.isEmpty()) {
            return null;
        }
        return hospitalAsList.get(0);
    }

    @Override
    public HospitalEntity saveHospital(HospitalEntity _hospital) {
        ArrayList<String> txtList = repository.getAll();
        ArrayList<HospitalEntity> hospitalList = this.convertStringListToEntityList(txtList);
        boolean isUpdated = false;
        for (HospitalEntity hospital : hospitalList) {
            if (hospital.getId() == _hospital.getId()) {
                hospital.setOpenAt(_hospital.getOpenAt());
                hospital.setCloseAt(_hospital.getCloseAt());
                hospital.setOperationList(_hospital.getOperationList());
                hospital.setAppointmentName(_hospital.getAppointmentName());
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            int id = hospitalList.size() == 0 ? 1 : (hospitalList.get(hospitalList.size() - 1).getId() + 1);
            _hospital.setId(id);
            hospitalList.add(_hospital);
        }
        this.repository.save(this.convertEntityListToStringList(hospitalList));
        return _hospital;
    }

    @Override
    public ArrayList<HospitalEntity> getAllHospitals() {
        return this.convertStringListToEntityList(
                this.repository.getAll()
        );
    }

    @Override
    public boolean deleteHospital(HospitalEntity _hospital) {
        return this.repository.delete(_hospital.getId());
    }

    @Override
    public ArrayList<HospitalEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<HospitalEntity> hospitalList = new ArrayList<HospitalEntity>();
        OperationServiceImpl operationService = new OperationServiceImpl(DataAccessFactory.getRepository("txt", "operations.txt"));
        for (String item : _strList) {
            HospitalEntity hospital = new HospitalEntity();
            ArrayList<OperationEntity> operationList = new ArrayList<>();

            hospital.setId(Integer.parseInt(item.split("\t")[0]));
            hospital.setOpenAt(new Date(Long.parseLong(item.split("\t")[1])));
            hospital.setCloseAt(new Date(Long.parseLong(item.split("\t")[2])));
            String[] operationIds = item.split("\t")[3].split(",");
            for (String operationId : operationIds) {
                operationList.add(operationService.getOperationById(Integer.parseInt(operationId)));
            }
            hospital.setOperationList(operationList);
            hospital.setAppointmentName(item.split("\t")[4]);
            hospitalList.add(hospital);
        }
        return hospitalList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<HospitalEntity> _hospitalList) {
        String operationIds = null;
        ArrayList<String> strList = new ArrayList<String>();
        for (HospitalEntity hospital : _hospitalList) {
            operationIds = null;
            for (OperationEntity operation : hospital.getOperationList()) {
                if(operationIds == null){
                    operationIds = String.valueOf(operation.getId());
                }else {
                    operationIds = operationIds+","+operation.getId();
                }
            }
            strList.add(
                    hospital.getId() + "\t" +
                            hospital.getOpenAt().getTime() + "\t" +
                            hospital.getCloseAt().getTime() + "\t" +
                            operationIds + "\t"+
                            hospital.getAppointmentName() +"\n"
            );
        }
        return strList;
    }
}
