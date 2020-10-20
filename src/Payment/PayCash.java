package Payment;

public class PayCash implements IPayStrategy {
    @Override
    public String pay(int _amount) {
        return "PAID_CASH";
    }
}
