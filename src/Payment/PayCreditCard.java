package Payment;

public class PayCreditCard implements IPayStrategy {
    @Override
    public String pay(int _amount) {
        return "PAID_CREDIT_CARD";
    }
}
