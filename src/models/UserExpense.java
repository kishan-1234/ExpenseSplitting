package models;

import lombok.Data;

@Data
public class UserExpense {

    User user;
    AmountType amountType;
    Double expenseAmount;
}
