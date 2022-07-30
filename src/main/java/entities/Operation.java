package entities;


import java.util.Date;


public class Operation{

    private Date date;
    private OperationType operationType;
    private double amount;

    private double balanceAfterOperation;

    public Operation() {
    }

    public Operation(OperationType operationType, double amount, double balanceAfterOperation) {
        this.operationType = operationType;
        this.amount = amount;
        this.date = new Date();
        this.balanceAfterOperation = balanceAfterOperation;
    }

    public double getBalanceAfterOperation() {
        return balanceAfterOperation;
    }

    public void setBalanceAfterOperation(double balanceAfterOperation) {
        this.balanceAfterOperation = balanceAfterOperation;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }
    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }
    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }



    @Override
    public String toString() {
        return "Operation{" +
                ", date=" + date +
                ", operationType=" + operationType +
                ", amount=" + amount +
                '}';
    }
}
