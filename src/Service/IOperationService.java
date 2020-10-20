package Service;

import Entity.OperationEntity;

import java.util.ArrayList;

public interface IOperationService {
    OperationEntity getOperationById(int _id);
    OperationEntity saveOperation(OperationEntity _operation);
    ArrayList<OperationEntity> getAllOperations();
    boolean deleteOperation(OperationEntity _operation);
    ArrayList<OperationEntity> convertStringListToEntityList(ArrayList<String> _strList);
    ArrayList<String> convertEntityListToStringList(ArrayList<OperationEntity> _operationList);
}
