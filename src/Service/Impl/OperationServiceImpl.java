package Service.Impl;

import Entity.OperationEntity;
import Repository.IDataAccess;
import Service.IOperationService;

import java.util.ArrayList;

public class OperationServiceImpl implements IOperationService {

    private IDataAccess repository;

    public OperationServiceImpl(IDataAccess repository) {
        this.repository = repository;
    }

    @Override
    public OperationEntity getOperationById(int _id) {
        ArrayList<OperationEntity> operationtAsList = this.convertStringListToEntityList(this.repository.getById(_id));
        if(operationtAsList.isEmpty()){
            return null;
        }
        return operationtAsList.get(0);
    }

    @Override
    public OperationEntity saveOperation(OperationEntity _operation) {
        ArrayList<String> txtList = repository.getAll();
        ArrayList<OperationEntity> operationList = this.convertStringListToEntityList(txtList);
        boolean isUpdated = false;
        for (OperationEntity operation : operationList) {
            if (operation.getId() == _operation.getId()) {
                operation.setOperationName(_operation.getOperationName());
                isUpdated = true;
            }
        }
        if (!isUpdated) {
            int id = operationList.size() == 0 ? 1 : (operationList.get(operationList.size() - 1).getId() + 1);
            _operation.setId(id);
            operationList.add(_operation);
        }
        this.repository.save(this.convertEntityListToStringList(operationList));
        return _operation;
    }

    @Override
    public ArrayList<OperationEntity> getAllOperations() {
        if (this.repository != null) {
            ArrayList<String> operationList = this.repository.getAll();
            if (!operationList.isEmpty()) {
                return this.convertStringListToEntityList(operationList);
            }
            return null;
        }
        return null;
    }

    @Override
    public boolean deleteOperation(OperationEntity _operation) {
        return this.repository.delete(_operation.getId());
    }

    @Override
    public ArrayList<OperationEntity> convertStringListToEntityList(ArrayList<String> _strList) {
        ArrayList<OperationEntity> operationList = new ArrayList<OperationEntity>();
        for (String item : _strList) {
            OperationEntity operation = new OperationEntity(
                    Integer.parseInt(item.split("\t")[0]),
                    item.split("\t")[1]
            );
            operationList.add(operation);
        }
        return operationList;
    }

    @Override
    public ArrayList<String> convertEntityListToStringList(ArrayList<OperationEntity> _operationList) {
        ArrayList<String> strList = new ArrayList<String>();
        for (OperationEntity operation : _operationList) {
            strList.add(
                    operation.getId() + "\t" +
                            operation.getOperationName() + "\n"
            );
        }
        return strList;
    }

}
