package Entity;

public class OperationEntity {
    private int id;
    private String operationName;

    public OperationEntity(int id, String operationName) {
        this.id = id;
        this.operationName = operationName;
    }

    public OperationEntity() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOperationName() {
        return operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }
}
