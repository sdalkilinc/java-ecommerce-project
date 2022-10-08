package discount;

import java.util.UUID;

public class RateBasedDiscount extends Discount{

    private Double rateAmount;

    public RateBasedDiscount(UUID id, String name, Double thresholdAmount, Double rateAmount) {
        super(id, name, thresholdAmount);
        this.rateAmount = rateAmount;
    }

    @Override
    public Double calculateCartAmountAfterDiscountApplied(Double amount) {
        return null;
    }
}
