package models;

import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
public class Balance {

    User user;
    AmountType amountType;
    Double balanceAmount;
}
